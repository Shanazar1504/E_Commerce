package com.example.e_commerce_example.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.e_commerce_example.R;
import com.example.e_commerce_example.Storage.PrefConfig;
import com.smarteist.autoimageslider.SliderView;

public class fragment_settings extends Fragment {

   private EditText ip;
   private ImageView donne;
   private String strIp;

    public fragment_settings() {
        // Required empty public constructor
    }

    public static fragment_settings newInstance() {
        fragment_settings fragment = new fragment_settings();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        ip = (EditText) view.findViewById(R.id.inputIp);
        donne = (ImageView) view.findViewById(R.id.done);

        donne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ip.getText().toString().length() <= 0) {
                    ip.setError("Boşlugy dolduryň");
                } else {
                    saveData();
                }
            }
        });

        return view;
    }

    private void saveData() {
        strIp = ip.getText().toString();
        PrefConfig.saveIpPref(getContext(), strIp);
//        Toast.makeText(fragment_settings.newInstance().getContext(), "Ustunlikli", Toast.LENGTH_SHORT).show();
    }
}

