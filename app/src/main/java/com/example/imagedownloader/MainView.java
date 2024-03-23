package com.example.imagedownloader;

import android.graphics.Bitmap;

public interface MainView {
    void showImage(Bitmap bitmap);
    void showImageLoadError();
}
