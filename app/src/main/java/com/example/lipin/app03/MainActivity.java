package com.example.lipin.app03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private Myview myview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getter_setter.setBitmap(getResources(),R.drawable.pokeball);
        getter_setter.setbgimage(getResources(),R.drawable.bg);
        myview = new Myview(this);
        setContentView(myview);

    }

}