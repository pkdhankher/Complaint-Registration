package com.example.pawan.complaintregistration;

import android.graphics.Bitmap;

/**
 * Created by root on 31/10/16.
 */

public class BitmapDetails {
    private static Bitmap bitmap;

    public static Bitmap getBitmap() {
        return bitmap;
    }

    public static void setBitmap(Bitmap bitmap) {
        BitmapDetails.bitmap = bitmap;
    }
}

