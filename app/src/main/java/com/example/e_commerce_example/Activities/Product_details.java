package com.example.e_commerce_example.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.request.RequestOptions;
import com.example.e_commerce_example.Models.Model_cat_details;
import com.example.e_commerce_example.R;
import com.example.e_commerce_example.Storage.PrefConfig;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Product_details extends AppCompatActivity {

    String UrL, Id, Id_prod_det, Port;
    TextView namee, pricee, descrip;
    ImageView imgg;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        Intent intent = getIntent();
          Id  = getIntent().getExtras().getString("id");
//        String descrip  = getIntent().getExtras().getString("description");
//        String price  = getIntent().getExtras().getString("price");
//        String img_url  = getIntent().getExtras().getString("img_url");

        UrL = PrefConfig.loadIpPref(this);
        Port = PrefConfig.loadPORTPref(this);
        Id_prod_det = "product/get-by-id/";
        getData_cat_Det_Products();

         namee = findViewById(R.id.products_name);
         pricee  = findViewById(R.id.products_price) ;
         descrip  = findViewById(R.id.products_descrip) ;
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView des  = findViewById(R.id.products_descrip) ;
         imgg = findViewById(R.id.products_img);

//        namee.setText(name);
//        pricee.setText(price + " TMT");
//        des.setText(descrip);
//        Picasso.get().load(img_url)
//                .into(imgg);
    }

    private void getData_cat_Det_Products() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, UrL + Port + Id_prod_det + Id,
                new Response.Listener<String>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(String response) {
                        Log.e("JSON OBJECT", "RESPONSE => " + response.toString());

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            namee.setText(jsonObject.getString("name"));
                            pricee.setText(jsonObject.getString("price") + " TMT");
                            descrip.setText(jsonObject.getString("description"));
                            JSONArray jsonArray = jsonObject.getJSONArray("products_images");
                            JSONObject jsonObject2 = jsonArray.getJSONObject(0);
                            String imgurl = jsonObject2.getString("img_url");
                            Picasso.get().load(imgurl)
                                    .into(imgg);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });
        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        //adding the string request to request queue
        requestQueue.add(stringRequest);

    }

}