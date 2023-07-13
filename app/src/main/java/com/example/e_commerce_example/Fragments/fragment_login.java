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
import com.example.e_commerce_example.Models.Model_Category;
import com.example.e_commerce_example.R;
import com.example.e_commerce_example.Storage.PrefConfig;
import com.example.e_commerce_example.databinding.FragmentLoginBinding;
import com.example.e_commerce_example.databinding.FragmentRegisterBinding;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class fragment_login extends Fragment {

    private Context context;
    private FragmentLoginBinding binding;

    private String URL, token, Port;

    JSONObject obj = new JSONObject();

    public fragment_login() {
        // Required empty public constructor
    }

    public static fragment_login newInstance() {
        fragment_login fragment = new fragment_login();
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
        binding = FragmentLoginBinding.inflate(inflater,container,false);
        try {
            setListener();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        URL = PrefConfig.loadIpPref(context);
        token = PrefConfig.loadToken(context);
        Port = PrefConfig.loadPORTPref(context);
        return binding.getRoot();
    }

    private void setListener() throws JSONException {
        binding.loginnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int getti = Integer.parseInt(Objects.requireNonNull(binding.phoneee1.getText()).toString());
                if (binding.username1.getText().toString().isEmpty() || binding.password1.getText().toString().isEmpty() || binding.phoneee1.getText().toString().isEmpty()) {
                    binding.username1.setError("Error");
                    return;
                }
                try {
                    obj.put("name", binding.username1.getText().toString());
                    obj.put("password", binding.password1.getText().toString());
                    obj.put("phone_num", getti);
                    binding.username1.getText().clear();
                    binding.password1.getText().clear();
                    binding.phoneee1.getText().clear();
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                RequestQueue queue = Volley.newRequestQueue(getContext());
                JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST, URL + Port + "user/login", obj,
                        response -> {
                            try {
                                JSONObject jsonObject = new JSONObject(response.toString());
                                JSONObject token = jsonObject.getJSONObject("access_token");
                                PrefConfig.saveToken(context, token.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        },
                        error -> Log.e("JSON OBJECT", "ERROR => " + error));
                queue.add(jsObjRequest);
            }

        });

        binding.registerr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new fragment_register();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.mainfragment, fragment);
                fragmentTransaction.commit();
            }
        });
    }

}

