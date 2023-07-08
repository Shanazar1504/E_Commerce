package com.example.e_commerce_example.Activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.e_commerce_example.databinding.NoInternetBinding;

public class NoInternet {

    private RelativeLayout view;
    private int id;
    private Context context;
    private NoInternetBinding noInternetBinding;

    public NoInternet(RelativeLayout view, int id, Context context) {
        this.view = view;
        this.context = context;
        this.id=id;
        noInternetBinding=NoInternetBinding.inflate(LayoutInflater.from(context));
    }

    public interface Listener{
        void OnRetry();
    }

    public void showError(){
        try{
            view.findViewById(id).setVisibility(View.GONE);
            view.addView(noInternetBinding.getRoot(), RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void setListener(Listener listener){
        noInternetBinding.retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.OnRetry();
            }
        });
    }

    public void hideError(){
        try {
            view.findViewById(id).setVisibility(View.VISIBLE);
            view.removeView(noInternetBinding.getRoot());
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }


}
