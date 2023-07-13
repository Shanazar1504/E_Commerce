package com.example.e_commerce_example.Fragments;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.e_commerce_example.Adapter.Adapter_category;
import com.example.e_commerce_example.Models.Model_Aksiya;
import com.example.e_commerce_example.Models.Model_Category;
import com.example.e_commerce_example.Models.Model_Products;
import com.example.e_commerce_example.R;
import com.example.e_commerce_example.Storage.PrefConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class fragment_category extends Fragment {

    private List<Model_Category> modelCategories;
    RecyclerView recyclerViewCategory;

    JSONObject obj = new JSONObject();

    private String UrL;
    private String Port;
    private String final_result;
//    private static final String urlll = "http://run.mocky.io/v3/8ba54e21-ff12-4dee-b1c9-b601e38274cb";
    private String json;


    public fragment_category() {
        // Required empty public constructor
    }

    @SuppressLint("MissingInflatedId")
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        recyclerViewCategory = (RecyclerView) view.findViewById(R.id.recycle_categories);

        modelCategories = new ArrayList<>();

        UrL = PrefConfig.loadIpPref(getContext());
        Port = PrefConfig.loadPORTPref(getContext());

        getData();

        return view;
    }

    private void getData() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, UrL + Port + "category/all",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        System.out.println(response);

                        try {
                            JSONArray jsonArray1 = new JSONArray(response);
                            for (int i = 0 ; i < jsonArray1.length(); i++ ) {
                                Model_Category modelCategory = new Model_Category();
                                JSONObject jsonObject1 = jsonArray1.getJSONObject(i);
                                modelCategory.setName(jsonObject1.getString("name"));
                                modelCategory.setId(jsonObject1.getString("id"));
                                String imgurl = jsonObject1.getString("img_url");
                                modelCategory.setImg_url(imgurl);
                                modelCategories.add(modelCategory);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        PutDataIntoRecycleview(modelCategories);
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



    private void PutDataIntoRecycleview(List<Model_Category> modelCategories) {

        Adapter_category adapterCategory = new Adapter_category(getContext(), modelCategories );
        recyclerViewCategory.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewCategory.setAdapter(adapterCategory);

    }

}
