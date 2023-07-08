package com.example.e_commerce_example.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.e_commerce_example.Fragments.fragment_home;
import com.example.e_commerce_example.R;
import com.example.e_commerce_example.Slider.SliderItems;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderAdapterViewHolder> {

    private final List<SliderItems> sliderItems;

    public SliderAdapter(fragment_home context, ArrayList<SliderItems> sliderDataArrayList) {
        this.sliderItems = sliderDataArrayList;
    }

    @Override
    public SliderAdapterViewHolder onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.banner_item, null);
        return new SliderAdapterViewHolder(inflate);
    }


    @Override
    public void onBindViewHolder(SliderAdapterViewHolder viewHolder, final int position) {
        final SliderItems sliderItems1 = sliderItems.get(position);
        Glide.with(viewHolder.imageView)
                .load(sliderItems1.getImgUrl())
                .fitCenter()
                .into(viewHolder.imageView);
    }

    @Override
    public int getCount() {
        return sliderItems.size();
    }

    public class SliderAdapterViewHolder extends  SliderViewAdapter.ViewHolder {
        private ImageView imageView;
        public SliderAdapterViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.banner_image);
        }
    }
}
