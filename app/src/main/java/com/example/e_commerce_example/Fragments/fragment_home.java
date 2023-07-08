package com.example.e_commerce_example.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
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
import com.android.volley.toolbox.Volley;
import com.example.e_commerce_example.Adapter.Adapter_category;
import com.example.e_commerce_example.Adapter.Adapter_products;
import com.example.e_commerce_example.Adapter.SliderAdapter;
import com.example.e_commerce_example.Models.Model_Category;
import com.example.e_commerce_example.Models.Model_Products;
import com.example.e_commerce_example.R;
import com.example.e_commerce_example.Slider.SliderItems;
import com.example.e_commerce_example.Storage.PrefConfig;
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

public class fragment_home extends Fragment {

    JSONObject obj = new JSONObject();

    ViewPager2 viewPager;
    private Handler sliderHandler = new Handler();

    private String url1 = "http://192.168.0.103:8091/public/mainpage/1.jpg";
    private String url2 = "http://192.168.0.103:8091/public/mainpage/2.jpg";
    private String url3= "http://192.168.0.103:8091/public/mainpage/3.jpg";

    Handler handler;
    Runnable runnable;
    String final_result;
    int loop_time;



    RecyclerView recyclerViewProduct, recyclerViewCategory;


    private static final String JSON_URL = "http://192.168.0.103:8091/category/all";

    private static final String urlll = "http://run.mocky.io/v3/47e62357-5585-45b7-81b0-f51448bbf10d";



    private  String UrL;
    private JsonArrayRequest request;
    private RequestQueue queue;
    private List<Model_Products> modelProducts;
    private List<Model_Category> modelCategories;
    private ArrayList<SliderItems> sliderItemsArrayList;
    private RecyclerView recyclerView;

    private SliderView sliderView;

    public fragment_home() {
        // Required empty public constructor
    }

    @SuppressLint("MissingInflatedId")
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        sliderView = (SliderView) view.findViewById(R.id.slider);
        recyclerViewCategory = (RecyclerView) view.findViewById(R.id.recycle_category);
        recyclerViewProduct = (RecyclerView) view.findViewById(R.id.recycle_products);

        UrL = PrefConfig.loadIpPref(getContext());

        modelCategories = new ArrayList<>();
        modelProducts = new ArrayList<>();
        sliderItemsArrayList = new ArrayList<>();

        getData();

        getBanner();

        return view;
    }


    private void getData() {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, urlll, obj,
                response -> {
                    System.out.println(response);
                    Log.e("JSON OBJECT", "RESPONSE => " + response);
                    try {
                        JSONArray jsonArray1 = response.getJSONArray("data");
                        for (int i = 0 ; i < jsonArray1.length(); i++ ) {
                            JSONObject jsonObject1 = jsonArray1.getJSONObject(i);
                            Model_Products modelProducts1 = new Model_Products();
                            modelProducts1.setName(jsonObject1.getString("name"));
                            modelProducts1.setPrice(jsonObject1.getString("description"));
                            String imgurl = jsonObject1.getString("img_url");
                            modelProducts1.setImg_url(imgurl);
//                            JSONArray jsonArray = jsonObject1.getJSONArray("img_url");
//                            for (int j = 0 ; j < jsonArray1.length(); j++ ) {
//                                JSONObject jsonObject2 = jsonArray.getJSONObject(0);
//                                String imgurl = jsonObject2.getString("img_url");
//                                modelProducts1.setImg_url(imgurl);
//                            }
                            modelProducts.add(modelProducts1);
                        }

                    } catch (JSONException e) {
                       e.printStackTrace();
                    }
                    PutDataIntoRecycleview(modelProducts);

                },
                error -> Log.e("JSON OBJECT", "ERROR => " + error));
        queue.add(jsObjRequest);
    }


    private void PutDataIntoRecycleview(List<Model_Products> modelProducts) {

        Adapter_products adapterProducts = new Adapter_products(getContext(), modelProducts );
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        recyclerViewProduct.setLayoutManager(layoutManager);
        recyclerViewProduct.setAdapter(adapterProducts);

    }

    private void getBanner() {
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


    private final Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
        }
    };
}

