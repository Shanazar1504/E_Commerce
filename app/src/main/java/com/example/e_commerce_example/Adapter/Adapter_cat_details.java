package com.example.e_commerce_example.Adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commerce_example.Models.Model_cat_details;
import com.example.e_commerce_example.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter_cat_details extends RecyclerView.Adapter<Adapter_cat_details.ViewHolder> {

    private List<Model_cat_details> productsList;
    private final Context context;



    public Adapter_cat_details(Context context, List<Model_cat_details>productsList) {
        this.productsList = productsList;
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_category_details, parent, false);
        return new ViewHolder(inflate);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {


        holder.name.setText(productsList.get(position).getName_cat_details());
        holder.price.setText(productsList.get(position).getPrice_cat_details() + " TMT");
        Picasso
                .get()
                .load(productsList.get(position).getImg_cat_details())
                .into(holder.img);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent i = new Intent(context, Product_details.class);
//                i.putExtra("name", productsList.get(position).getName());
//                i.putExtra("price", productsList.get(position).getPrice());
//                i.putExtra("img_url", productsList.get(position).getImg_url());
//                context.startActivity(i);
            }
        });
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

            name = itemView.findViewById(R.id.cat_name_products);
            price = itemView.findViewById(R.id.cat_price_products);
            img = (ImageView) itemView.findViewById(R.id.cat_img_products);
            cardView = (CardView) itemView.findViewById(R.id.cat_details_products_view);

        }
    }
}

