package com.example.imagedownloader;

import android.graphics.Bitmap;

public interface ITalkToMainActivity {
    void showImage(Bitmap bitmap);
    void showImageLoadError();
    void showDownloadInProgress();
    void showDownloadCompleted();
}
