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

import com.example.e_commerce_example.Models.Model_Aksiya;
import com.example.e_commerce_example.Models.Model_Products;
import com.example.e_commerce_example.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter_aksiya extends RecyclerView.Adapter<Adapter_aksiya.ViewHolder> {

    private List<Model_Aksiya> aksiyaList;
    private final Context context;
    private String percent = "0.2";
    private String afterper;
    public Adapter_aksiya(Context context, List<Model_Aksiya>aksiyaList) {
        this.aksiyaList = aksiyaList;
        this.context = context;
    }

    @NonNull
    @Override
    public Adapter_aksiya.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_aksiya, parent, false);
        return new ViewHolder(inflate);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull Adapter_aksiya.ViewHolder holder, int position) {
        holder.name.setText(aksiyaList.get(position).getName());
        holder.price.setText(aksiyaList.get(position).getPrice() + " TMT");
        Picasso
                .get()
                .load(aksiyaList.get(position).getImg_url())
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return aksiyaList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, price, skidka;
        ImageView img;
        CardView cardView;
        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_products);
            skidka = itemView.findViewById(R.id.price_aksiya);
            price = itemView.findViewById(R.id.price_products);
            img = (ImageView) itemView.findViewById(R.id.img_products);
            cardView = (CardView) itemView.findViewById(R.id.products_view);

        }
    }
}