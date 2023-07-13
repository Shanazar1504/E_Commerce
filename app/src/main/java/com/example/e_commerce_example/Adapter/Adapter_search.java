package com.example.e_commerce_example.Adapter;

import android.annotation.SuppressLint;
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

import com.example.e_commerce_example.Activities.Activity_wishlist;
import com.example.e_commerce_example.Activities.Product_details;
import com.example.e_commerce_example.Models.Model_Products;
import com.example.e_commerce_example.Models.Model_Search;
import com.example.e_commerce_example.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter_search extends RecyclerView.Adapter<Adapter_search.ViewHolder> {

    private List<Model_Search> searchList;
    private Adapter_search adapterProducts;
    private final Context context;



    public Adapter_search(Context context, List<Model_Search>searchList) {
        this.searchList = searchList;
        this.context = context;

    }

    @NonNull
    @Override
    public Adapter_search.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_products, parent, false);
        return new ViewHolder(inflate);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull Adapter_search.ViewHolder holder, int position) {

        holder.name.setText(searchList.get(position).getName());
        holder.price.setText(searchList.get(position).getPrice() + " TMT");
        Picasso
                .get()
                .load(searchList.get(position).getImg_url())
                .into(holder.img);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, Product_details.class);
                i.putExtra("id", searchList.get(position).getId());
//                i.putExtra("description", productsList.get(position).getDescription());
//                i.putExtra("price", productsList.get(position).getPrice());
//                i.putExtra("img_url", productsList.get(position).getImg_url());
                context.startActivity(i);
            }
        });

        holder.heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, Activity_wishlist.class);
                i.putExtra("id", searchList.get(position).getId());
//                i.putExtra("description", searchList.get(position).getDescription());
//                i.putExtra("price", searchList.get(position).getPrice());
//                i.putExtra("img_url", searchList.get(position).getImg_url());
//                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return searchList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name, price;
        ImageView img, heart;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name_products);
            price = itemView.findViewById(R.id.price_products);
            img = (ImageView) itemView.findViewById(R.id.img_products);
            cardView = (CardView) itemView.findViewById(R.id.products_view);
            heart = (ImageView) itemView.findViewById(R.id.add_wishlist);

        }
    }
}
