package com.example.pawan.complaintregistration;

import android.graphics.Bitmap;

/**
 * Created by root on 31/10/16.
 */

public class Details {
    private static Bitmap image;

    public static Bitmap getImage() {
        return image;
    }

    public static void setImage(Bitmap image) {
        Details.image = image;
    }
}
