package com.example.e_commerce_example.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.e_commerce_example.Adapter.Adapter_wishlist;
import com.example.e_commerce_example.Models.Model_Aksiya;
import com.example.e_commerce_example.Models.Model_Whishlist;
import com.example.e_commerce_example.Models.Model_cat_details;
import com.example.e_commerce_example.R;
import com.example.e_commerce_example.Storage.PrefConfig;

import java.util.ArrayList;
import java.util.List;

public class Activity_wishlist extends AppCompatActivity{

    private List<Model_Whishlist> modelWhishlists = new ArrayList<>();
    String UrL, Id, Id_catDet, Port;
    String name, descrip, price, img_url;
    RecyclerView recyclerViewWishlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_whishlist);

        Intent intent = getIntent();
         name  = getIntent().getExtras().getString("name");
         descrip  = getIntent().getExtras().getString("description");
         price  = getIntent().getExtras().getString("price");
         img_url  = getIntent().getExtras().getString("img_url");

        recyclerViewWishlist = (RecyclerView)findViewById(R.id.recycle_wishlists);
        UrL = PrefConfig.loadIpPref(this);

        getWishlist();

    }

    private void getWishlist() {

        Model_Whishlist modelWhishlist = new Model_Whishlist();
        modelWhishlist.setName(name);
        modelWhishlist.setDescription(descrip);
        modelWhishlist.setPrice(price);
        modelWhishlist.setImg_url(img_url);

        modelWhishlists.add(modelWhishlist);

        Adapter_wishlist adapterWishlist = new Adapter_wishlist(this,modelWhishlists);
        recyclerViewWishlist.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewWishlist.setAdapter(adapterWishlist);


    }
}