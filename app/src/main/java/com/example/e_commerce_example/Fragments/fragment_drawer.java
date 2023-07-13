package com.example.e_commerce_example.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import com.example.e_commerce_example.R;
import com.example.e_commerce_example.Storage.PrefConfig;

public class fragment_drawer extends Fragment {

    LinearLayout account, call, about, lang, setting, logout;
    private MenuItem item;
    private TextView user, email;
    private static fragment_drawer INSTANCE;

    public fragment_drawer() {
        // Required empty public constructor
    }

    public static fragment_drawer newInstance() {
        fragment_drawer fragment = new fragment_drawer();
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
        View view = inflater.inflate(R.layout.fragment_drawer, container, false);

        INSTANCE=this;

        account = (LinearLayout) view.findViewById(R.id.drawer_profile);
        lang = (LinearLayout) view.findViewById(R.id.drawer_lang);
        setting = (LinearLayout) view.findViewById(R.id.drawer_setting);
        call = (LinearLayout) view.findViewById(R.id.drawer_call);
        about = (LinearLayout) view.findViewById(R.id.drawer_about);
        logout = (LinearLayout) view.findViewById(R.id.drawer_logout);
        email = (TextView) view.findViewById(R.id.prof_gmail);
        user = (TextView) view.findViewById(R.id.prof_name);

        email.setText(PrefConfig.loadEmail(getContext()));
        user.setText(PrefConfig.loadUser(getContext()));


        setListener();
        return view;
    }

    private void setListener() {

        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new fragment_login();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.mainfragment, fragment);
                fragmentTransaction.commit();
            }
        });

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new fragment_settings();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.mainfragment, fragment);
                fragmentTransaction.commit();
            }
        });


        lang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog();
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog_about_us();
            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setUpViews();
            }
        });

    }

    private void setUpViews() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        // Setting Dialog Title
        alertDialog.setTitle("Do you want to call?");
        // Setting Dialog Message
        alertDialog.setMessage("+99361991271");
        // Setting Icon to Dialog
        //alertDialog.setIcon(R.drawable.warning);
        // Setting Negative "NO" Button
        alertDialog.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        // Write your code here to execute after dialog
                        dialog.cancel();
                    }
                });
        // Setting Positive "Yes" Button
        alertDialog.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        // Write your code here to execute after dialog
                        Intent callIntent = new Intent(Intent.ACTION_CALL);
                        callIntent.setData(Uri.parse("+99361991271"));
                        callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(callIntent);
                    }
                });

        // Showing Alert Message
        alertDialog.show();
    }

    private void showAlertDialog_about_us() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(fragment_drawer.INSTANCE.getContext());
        alertDialog.setMessage("Version: 1.0.9");
        alertDialog.setTitle("Biz barada");
        alertDialog.setCancelable(false);
        alertDialog.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
            alertDialog.setCancelable(true);
        });
        AlertDialog alert = alertDialog.create();
        alert.show();
    }

    private void showAlertDialog() {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(fragment_drawer.INSTANCE.getContext());
        alertDialog.setTitle("Dil saýlamak");
        alertDialog.setIcon(R.drawable.outline_notifications_active_24);
        String[] items = {"Turkmen","Russian","English"};
        int checkedItem = 0;
        alertDialog.setSingleChoiceItems(items, checkedItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        Toast.makeText(fragment_drawer.INSTANCE.getContext(), "Turkmen dili", Toast.LENGTH_LONG).show();
                        break;
                    case 1:
                        Toast.makeText(fragment_drawer.INSTANCE.getContext(), "Russain", Toast.LENGTH_LONG).show();
                        break;
                    case 2:
                        Toast.makeText(fragment_drawer.INSTANCE.getContext(), "English", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });
        alertDialog.setPositiveButton("Tamamla", (DialogInterface.OnClickListener) (dialog, which) -> {
            alertDialog.setCancelable(true);
        });
        AlertDialog alert = alertDialog.create();
        alert.setCanceledOnTouchOutside(false);
        alert.show();
    }
}