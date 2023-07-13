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
import com.example.e_commerce_example.Activities.Activity_Category_Details;
import com.example.e_commerce_example.Models.Model_Category;
import com.example.e_commerce_example.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Adapter_category extends RecyclerView.Adapter<Adapter_category.ViewHolder> {

    private List<Model_Category> categoryList;

    private final Context context;

    public Adapter_category(Context context, List<Model_Category>categoryList) {
        this.categoryList = categoryList;
        this.context = context;
    }

    @NonNull
    @Override
    public Adapter_category.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);
        return new ViewHolder(inflate);

    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_category.ViewHolder holder, int position) {


        holder.name.setText(categoryList.get(position).getName());
        Picasso
                .get()
                .load(categoryList.get(position).getImg_url())
                .into(holder.img);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, Activity_Category_Details.class);
                i.putExtra("id", categoryList.get(position).getId());
//                i.putExtra("name", categoryList.get(position).getName());
//                i.putExtra("img_url", categoryList.get(position).getImg_url());
                context.startActivity(i);
            }
        });



    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        ImageView img;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name_category);
            img = (ImageView) itemView.findViewById(R.id.img_category);
            cardView = (CardView) itemView.findViewById(R.id.category_view);

        }
    }
}
