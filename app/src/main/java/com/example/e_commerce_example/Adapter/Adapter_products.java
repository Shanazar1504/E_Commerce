package com.example.e_commerce_example.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.e_commerce_example.Fragments.fragment_home;
import com.example.e_commerce_example.Models.Model_Category;
import com.example.e_commerce_example.Models.Model_Products;
import com.example.e_commerce_example.R;

import java.util.ArrayList;
import java.util.List;

public class Adapter_products extends RecyclerView.Adapter<Adapter_products.ViewHolder> {

    private List<Model_Products> productsList;
    private final Context context;



    public Adapter_products(Context context, List<Model_Products>productsList) {
        this.productsList = productsList;
        this.context = context;

    }

    @NonNull
    @Override
    public Adapter_products.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_products, parent, false);
        return new ViewHolder(inflate);

    }



    @Override
    public void onBindViewHolder(@NonNull Adapter_products.ViewHolder holder, int position) {


        holder.name.setText(productsList.get(position).getName());
        holder.price.setText(productsList.get(position).getPrice());
        Glide
                .with(context)
                .load(productsList.get(position).getImg_url())
                .centerCrop()
                .placeholder(R.drawable.pers)
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name, price;
        ImageView img;

        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name_products);
            price = itemView.findViewById(R.id.price_products);
            img = (ImageView) itemView.findViewById(R.id.img_products);
            cardView = (CardView) itemView.findViewById(R.id.products_view);

        }
    }
}
