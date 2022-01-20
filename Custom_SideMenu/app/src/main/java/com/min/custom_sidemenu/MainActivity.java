package com.min.custom_sidemenu;

import android.os.Bundle;

import com.min.custom_sidemenu.menu.SideMenu;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new SideMenu(this);
    }
}