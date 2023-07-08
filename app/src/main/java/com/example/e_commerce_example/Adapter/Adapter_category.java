package com.example.e_commerce_example.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.e_commerce_example.Models.Model_Category;
import com.example.e_commerce_example.R;

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
        Glide
                .with(context)
                .load(categoryList.get(position).getImg())
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .into(holder.img);



    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        ImageView img;

        public ViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name_category);
            img = (ImageView) itemView.findViewById(R.id.img_category);

        }
    }
}
