package com.example.e_commerce_example.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.e_commerce_example.R;
import com.example.e_commerce_example.Storage.PrefConfig;
import com.example.e_commerce_example.databinding.FragmentRegisterBinding;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class fragment_register extends Fragment {

    private Context context;
    private FragmentRegisterBinding binding;

    private String URL, Port;

    JSONObject obj = new JSONObject();

    public fragment_register() {
        // Required empty public constructor
    }

    public static fragment_register newInstance() {
        fragment_register fragment = new fragment_register();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context=getContext();
//        Utils.setLocale(Utils.getSharedPreference(context, "language"), context);
        binding = FragmentRegisterBinding.inflate(inflater,container,false);
        setListener();
        URL = PrefConfig.loadIpPref(context);
        Port = PrefConfig.loadPORTPref(context);
        return binding.getRoot();
    }


    private void setListener() {
        binding.register2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int getti = Integer.parseInt(Objects.requireNonNull(binding.phoneee.getText()).toString());

//                PrefConfig.saveUser(binding.username.getText().toString());
//                PrefConfig.saveEmail(binding.email.getText().toString());

                if (binding.username.getText().toString().isEmpty() || binding.email.getText().toString().isEmpty() || binding.password.getText().toString().isEmpty() || binding.phoneee.getText().toString().isEmpty()) {
                    binding.username.setError("Empty");
                    return;
                }

                try {
                    obj.put("name", binding.username.getText().toString());
                    obj.put("email", binding.email.getText().toString());
                    obj.put("password", binding.password.getText().toString());
                    obj.put("phone_num", getti);
                    binding.username.getText().clear();
                    binding.email.getText().clear();
                    binding.password.getText().clear();
                    binding.phoneee.getText().clear();
                    PrefConfig.saveUser(getContext(), binding.username.getText().toString());
                    PrefConfig.saveEmail(getContext(), binding.email.getText().toString());
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                RequestQueue queue = Volley.newRequestQueue(getContext());
                JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST, URL + Port + "user/register", obj,
                        response -> {
                            System.out.println(response);
                            Log.e("JSON OBJECT", "RESPONSE => " + response);
                        },
                        error -> Log.e("JSON OBJECT", "ERROR => " + error));
                queue.add(jsObjRequest);
            }

        });

        binding.loginn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new fragment_login();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.mainfragment, fragment);
                fragmentTransaction.commit();
            }
        });
    }
}

