package com.example.lipin.app03;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class getter_setter {
    private static Bitmap bitmap,bgimage;

    public static Bitmap getbgimage() {
        return  bgimage;
    }
    public static void setbgimage(Resources resources, int imag){
        bgimage = BitmapFactory.decodeResource(resources,imag);
    }

    public static Bitmap getBitmap() {
        return  bitmap;
    }
    public static void setBitmap(Resources resources, int imag){
        bitmap = BitmapFactory.decodeResource(resources,imag);
    }
}
