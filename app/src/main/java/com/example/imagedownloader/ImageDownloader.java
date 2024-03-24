package com.example.imagedownloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ImageDownloader {
    public interface ImageLoadListener {
        void onImageLoadSuccess(Bitmap bitmap);
        void onImageLoadFailed();
    }

    public void loadImageFromUrl(URL url, ImageLoadListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(url)
                            .build();
                    Response response = client.newCall(request).execute();

                    if (response.isSuccessful()) {
                        byte[] bytes = response.body().bytes();
                        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                        listener.onImageLoadSuccess(bitmap);
                    } else {
                        listener.onImageLoadFailed();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
