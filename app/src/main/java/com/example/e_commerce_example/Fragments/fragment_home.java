package com.example.e_commerce_example.Fragments;

import static com.example.e_commerce_example.Utils.InternetUtils.isNetworkConnected;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.e_commerce_example.Activities.NoInternet;
import com.example.e_commerce_example.Adapter.Adapter_aksiya;
import com.example.e_commerce_example.Adapter.Adapter_category;
import com.example.e_commerce_example.Adapter.Adapter_products;
import com.example.e_commerce_example.Adapter.SliderAdapter;
import com.example.e_commerce_example.Broadcasts.NetworkChangeReceiver;
import com.example.e_commerce_example.Models.Model_Aksiya;
import com.example.e_commerce_example.Models.Model_Category;
import com.example.e_commerce_example.Models.Model_Products;
import com.example.e_commerce_example.R;
import com.example.e_commerce_example.Slider.SliderItems;
import com.example.e_commerce_example.Storage.PrefConfig;
import com.example.e_commerce_example.Utils.OnNetworkListener;
import com.google.android.material.snackbar.Snackbar;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class fragment_home extends Fragment implements OnNetworkListener {

    JSONObject obj = new JSONObject();
    ViewPager2 viewPager;
    private String url1 = "http://192.168.0.107:8091/public/mainpage/1.jpg";
    private String url2 = "http://192.168.0.107:8091/public/mainpage/2.jpg";
    private String url3= "http://192.168.0.107:8091/public/mainpage/3.jpg";
    RecyclerView recyclerViewProduct, recycleviewAksiya;
    private List<Model_Products> modelProducts;
    private List<Model_Aksiya> modelAksiyas;
    private ArrayList<SliderItems> sliderItemsArrayList;
    private SliderView sliderView;
    private String UrL, Port;

    private NetworkChangeReceiver mNetworkReceiver;
    private Snackbar snack;

    public fragment_home() {
        // Required empty public constructor
    }

    @SuppressLint("MissingInflatedId")
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        sliderView = (SliderView) view.findViewById(R.id.slider);
        recyclerViewProduct = (RecyclerView) view.findViewById(R.id.recycle_products);
        recycleviewAksiya = (RecyclerView) view.findViewById(R.id.recycle_aksiya);

        UrL = PrefConfig.loadIpPref(getContext());
        Port = PrefConfig.loadPORTPref(getContext());

        modelAksiyas = new ArrayList<>();
        modelProducts = new ArrayList<>();
        sliderItemsArrayList = new ArrayList<>();

        getDataAksiya();
        getDataProducts();
        getBanner();

        mNetworkReceiver = new NetworkChangeReceiver();
        mNetworkReceiver.setOnNetworkListener(this);

        return view;
    }

    private void getDataProducts() {
        if (isNetworkConnected(getContext())) {
            StringRequest stringRequest = new StringRequest(Request.Method.GET, UrL + Port + "product/all",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            System.out.println(response);

                            try {
                                JSONArray jsonArray1 = new JSONArray(response);
                                for (int i = 0 ; i < jsonArray1.length(); i++ ) {
                                    Model_Products modelProducts1 = new Model_Products();
                                    JSONObject jsonObject1 = jsonArray1.getJSONObject(i);
                                    modelProducts1.setName(jsonObject1.getString("name"));
                                    modelProducts1.setId(jsonObject1.getString("id"));
                                    modelProducts1.setDescription(jsonObject1.getString("description"));
                                    modelProducts1.setPrice(jsonObject1.getString("price"));
                                    JSONArray jsonArray = jsonObject1.getJSONArray("products_images");
                                    JSONObject jsonObject2 = jsonArray.getJSONObject(0);
                                    String imgurl = jsonObject2.getString("img_url");
                                    modelProducts1.setImg_url(imgurl);
                                    modelProducts.add(modelProducts1);
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            PutDataIntoRecycleview(modelProducts);
                        }

                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                        }
                    });
            //creating a request queue
            RequestQueue requestQueue = Volley.newRequestQueue(getContext());
            //adding the string request to request queue
            requestQueue.add(stringRequest);
        }
        else {
           showSnackBar();
        }

    }

    private void getDataAksiya() {
        if (isNetworkConnected(getContext())){
            StringRequest stringRequest = new StringRequest(Request.Method.GET, UrL + Port + "product/all",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            System.out.println(response);

                            try {
                                JSONArray jsonArray1 = new JSONArray(response);
                                for (int i = 0 ; i < jsonArray1.length(); i++ ) {
                                    Model_Aksiya modelAksiya = new Model_Aksiya();
                                    JSONObject jsonObject1 = jsonArray1.getJSONObject(i);
                                    modelAksiya.setName(jsonObject1.getString("name"));
                                    modelAksiya.setPrice(jsonObject1.getString("price"));
                                    JSONArray jsonArray = jsonObject1.getJSONArray("products_images");
                                    for (int j = 0 ; j < jsonArray1.length(); j++ ) {
                                        JSONObject jsonObject2 = jsonArray.getJSONObject(0);
                                        String imgurl = jsonObject2.getString("img_url");
                                        modelAksiya.setImg_url(imgurl);
                                    }
                                    modelAksiyas.add(modelAksiya);
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            PutDataIntoRecycleviewAksiya(modelAksiyas);
                        }

                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                        }
                    });
            //creating a request queue
            RequestQueue requestQueue = Volley.newRequestQueue(getContext());
            //adding the string request to request queue
            requestQueue.add(stringRequest);

        }
        else {
            showSnackBar();
        }
    }

    private void PutDataIntoRecycleview(List<Model_Products> modelProducts) {

        Adapter_products adapterProducts = new Adapter_products(getContext(), modelProducts );
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        recyclerViewProduct.setLayoutManager(layoutManager);
        recyclerViewProduct.setAdapter(adapterProducts);

    }

    private void PutDataIntoRecycleviewAksiya(List<Model_Aksiya> modelAksiyas) {

        Adapter_aksiya adapterAksiya = new Adapter_aksiya(getContext(), modelAksiyas );
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        recycleviewAksiya.setLayoutManager(layoutManager);
//        recycleviewAksiya.setLayoutManager(new LinearLayoutManager(getContext()));
        recycleviewAksiya.setAdapter(adapterAksiya);

    }


    private void getBanner() {
        if (isNetworkConnected(getContext())){
            sliderItemsArrayList.add(new SliderItems(url1));
            sliderItemsArrayList.add(new SliderItems(url2));
            sliderItemsArrayList.add(new SliderItems(url3));
            SliderAdapter adapter = new SliderAdapter(this, sliderItemsArrayList);
            sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
            sliderView.setSliderAdapter(adapter);
            sliderView.setScrollTimeInSec(3);
            sliderView.setAutoCycle(true);
            sliderView.startAutoCycle();
        }
        else {
            showSnackBar();
        }

    }

    private final Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
        }
    };

    public void showSnackBar() {
//        Toast.makeText(getContext(), getResources().getString(R.string.no_internet_connection), Toast.LENGTH_LONG).show();
        Fragment fragment = new fragment_noInternet();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.mainfragment, fragment);
        fragmentTransaction.commit();
    }

    public void hideSnackBar() {
        snack.dismiss();
    }

    @Override
    public void onNetworkConnected() {
        hideSnackBar();
        getBanner();
        getDataAksiya();
        getDataProducts();

    }

    @Override
    public void onNetworkDisconnected() {
        showSnackBar();
    }
}

