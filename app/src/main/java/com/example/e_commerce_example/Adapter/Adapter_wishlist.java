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

import com.example.e_commerce_example.Activities.Product_details;
import com.example.e_commerce_example.Fragments.fragment_wishlist;
import com.example.e_commerce_example.Models.Model_Products;
import com.example.e_commerce_example.Models.Model_Whishlist;
import com.example.e_commerce_example.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter_wishlist extends RecyclerView.Adapter<Adapter_wishlist.ViewHolder> {

    private List<Model_Whishlist> whishlists;
    private final Context context;





    public Adapter_wishlist(Context context, List<Model_Whishlist>wishlistList) {
        this.whishlists = wishlistList;
        this.context = context;

    }

    @NonNull
    @Override
    public Adapter_wishlist.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_whishlist, parent, false);
        return new ViewHolder(inflate);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull Adapter_wishlist.ViewHolder holder, int position) {


        holder.name.setText(whishlists.get(position).getName());
        holder.desc.setText(whishlists.get(position).getDescription());
        holder.price.setText(whishlists.get(position).getPrice() + " TMT");
        Picasso
                .get()
                .load(whishlists.get(position).getImg_url())
                .into(holder.img);

//        holder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(context, Product_details.class);
//                i.putExtra("name", productsList.get(position).getName());
//                i.putExtra("description", productsList.get(position).getDescription());
//                i.putExtra("price", productsList.get(position).getPrice());
//                i.putExtra("img_url", productsList.get(position).getImg_url());
//                context.startActivity(i);
//            }
//        });

//        holder.heart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(context, fragment_wishlist.class);
//                i.putExtra("name", productsList.get(position).getName());
//                i.putExtra("description", productsList.get(position).getDescription());
//                i.putExtra("price", productsList.get(position).getPrice());
//                i.putExtra("img_url", productsList.get(position).getImg_url());
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return whishlists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name, price, desc;
        ImageView img, heart;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name_products);
            price = itemView.findViewById(R.id.price_products);
            desc = itemView.findViewById(R.id.description_wish);
            img = (ImageView) itemView.findViewById(R.id.img_products);
            cardView = (CardView) itemView.findViewById(R.id.products_view);
            heart = (ImageView) itemView.findViewById(R.id.add_wishlist);

        }
    }
}
