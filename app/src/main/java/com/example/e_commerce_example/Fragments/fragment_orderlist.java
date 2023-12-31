package com.example.e_commerce_example.Fragments;

import static android.content.Intent.getIntent;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.e_commerce_example.Adapter.Adapter_cat_details;
import com.example.e_commerce_example.Models.Model_cat_details;
import com.example.e_commerce_example.R;
import com.example.e_commerce_example.Storage.PrefConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class fragment_orderlist extends Fragment {

    private List<Model_cat_details> model_cat_detailsList = new ArrayList<>();
    String UrL, Id,Id_catDet;
    RecyclerView recyclerViewCat_Details;

    public fragment_orderlist() {
        // Required empty public constructor
    }

    @SuppressLint("MissingInflatedId")
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_orderlist, container, false);
//        recyclerViewCat_Details = (RecyclerView) view.findViewById(R.id.recycle_category_details);
//        Id = getIntent().getStringExtra("id");
//        UrL = PrefConfig.loadIpPref(getContext());
//        Id_catDet = "category/get-by-category-id/";
//        getData_cat_Det_Products();

        return view;
    }

    private void getData_cat_Det_Products() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, UrL + Id_catDet + Id,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("JSON OBJECT", "RESPONSE => " + response.toString());

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray dataArray = jsonObject.getJSONArray("product_category");

                            for (int i = 0 ; i < dataArray.length(); i++ ) {
                                Model_cat_details model_cat_details1 = new Model_cat_details();
                                JSONObject dataobj = dataArray.getJSONObject(i);
                                model_cat_details1.setId_cat_details(dataobj.getInt("id"));
                                model_cat_details1.setName_cat_details(dataobj.getString("name"));
                                model_cat_details1.setPrice_cat_details(dataobj.getString("price"));
                                JSONArray jsonArray = dataobj.getJSONArray("images");
//                                System.out.println(jsonArray);
                                JSONObject jsonObjImage = jsonArray.getJSONObject(0);
                                String imgurl = jsonObjImage.getString("img_url");

                                model_cat_details1.setImg_cat_details(imgurl);
                                model_cat_detailsList.add(model_cat_details1);

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        PutDataIntoRecyc_cat_details(model_cat_detailsList);

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

    private void PutDataIntoRecyc_cat_details(List<Model_cat_details> model_cat_detailsList) {

        Adapter_cat_details adapter_cat_details = new Adapter_cat_details(getContext(), model_cat_detailsList );
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        recyclerViewCat_Details.setLayoutManager(layoutManager);
        recyclerViewCat_Details.setAdapter(adapter_cat_details);

    }

    private Intent getIntent() {
        return null;
    }
}

