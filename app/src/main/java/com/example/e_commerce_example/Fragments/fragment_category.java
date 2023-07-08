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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.e_commerce_example.Adapter.Adapter_category;
import com.example.e_commerce_example.Models.Model_Category;
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
    private String final_result;
    private static final String urlll = "http://run.mocky.io/v3/8ba54e21-ff12-4dee-b1c9-b601e38274cb";
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

        getData();

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
                            Model_Category modelCategory = new Model_Category();
                            modelCategory.setName(jsonObject1.getString("name"));
                            modelCategories.add(modelCategory);
                        }

                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    PutDataIntoRecycleview(modelCategories);

                },
                error -> Log.e("JSON OBJECT", "ERROR => " + error));
        queue.add(jsObjRequest);
    }



    private void PutDataIntoRecycleview(List<Model_Category> modelCategories) {

        Adapter_category adapterCategory = new Adapter_category(getContext(), modelCategories );
        recyclerViewCategory.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewCategory.setAdapter(adapterCategory);

    }

}
