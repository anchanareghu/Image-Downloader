package com.example.imagedownloader;

import android.graphics.Bitmap;

import java.net.MalformedURLException;
import java.net.URL;

public class MainPresenter {
    MainView mainView;
    ImageModel imageModel;

    public MainPresenter(MainView mainView, ImageModel imageModel) {
        this.mainView = mainView;
        this.imageModel = imageModel;
    }

    public void onSearchButtonClick(String url) {
        try {
            URL imageUrl = new URL(url);
            imageModel.loadImageFromUrl(imageUrl, new ImageModel.ImageLoadListener() {
                @Override
                public void onImageLoadSuccess(Bitmap bitmap) {
                    mainView.showImage(bitmap);
                }

                @Override
                public void onImageLoadFailed() {
                    mainView.showImageLoadError();
                }
            });
        } catch (MalformedURLException e) {
            e.printStackTrace();
            mainView.showImageLoadError();
        }
    }
}
