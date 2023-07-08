package com.example.e_commerce_example.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.ui.AppBarConfiguration;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toolbar;

import com.example.e_commerce_example.Fragments.fragment_category;
import com.example.e_commerce_example.Fragments.fragment_drawer;
import com.example.e_commerce_example.Fragments.fragment_home;
import com.example.e_commerce_example.Fragments.fragment_orderlist;
import com.example.e_commerce_example.Fragments.fragment_wishlist;
import com.example.e_commerce_example.R;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class Main_Page extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    Toolbar toolbar, searchtollbar;
    Menu search_menu;
    MenuItem item_search;

    BottomAppBar appbar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);


        BottomNavigationView bottomNav = findViewById(R.id.nav_view);
        bottomNav.setOnNavigationItemSelectedListener(navListner);

        appbar = findViewById(R.id.appbar);
        drawerLayout = findViewById(R.id.drawer_layout1);

        fragment_drawer drawerFragment=new fragment_drawer();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_drawer, drawerFragment, drawerFragment.getClass().getSimpleName()).commit();

        appbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDrawer();
            }
        });


        //f statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.mainfragment,
                    new fragment_home()).commit();
        }
    }

    private void openDrawer() {


        drawerLayout.openDrawer(GravityCompat.START);

    }


    private BottomNavigationView.OnNavigationItemSelectedListener navListner =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @SuppressLint("NonConstantResourceId")
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()) {
                        case R.id.navigation_home:
                            selectedFragment = new fragment_home();
                            break;
                        case R.id.navigation_category:
                            selectedFragment = new fragment_category();
                            break;
                        case R.id.navigation_wishlist:
                            selectedFragment = new fragment_wishlist();
                            break;
                        case R.id.navigation_order_list:
                            selectedFragment = new fragment_orderlist();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.mainfragment,
                            selectedFragment).commit();
                    return true;
                }
            };

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {

        if (drawerLayout.isOpen()) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }
}


