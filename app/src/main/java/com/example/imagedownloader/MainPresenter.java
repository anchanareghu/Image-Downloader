package com.example.imagedownloader;

import android.app.DownloadManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;

import java.net.MalformedURLException;
import java.net.URL;

public class MainPresenter {
    ITalkToMainActivity talkToMainActivity;
    ImageDownloader imageDownloader;
    Context context;

    public MainPresenter(ITalkToMainActivity talkToMainActivity, Context context) {
        this.talkToMainActivity = talkToMainActivity;
        this.imageDownloader = new ImageDownloader();
        this.context = context;
    }

    public void onSearchButtonClick(String url) {
        try {
            URL imageUrl = new URL(url);
            imageDownloader.loadImageFromUrl(imageUrl, new ImageDownloader.ImageLoadListener() {
                @Override
                public void onImageLoadSuccess(Bitmap bitmap) {
                    talkToMainActivity.showImage(bitmap);
                }

                @Override
                public void onImageLoadFailed() {
                    talkToMainActivity.showImageLoadError();
                }
            });
        } catch (MalformedURLException e) {
            e.printStackTrace();
            talkToMainActivity.showImageLoadError();
        }
    }

    public void onDownloadButtonClicked(String imageUrl) {
        talkToMainActivity.showDownloadInProgress();

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(imageUrl));
        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE)
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "image.jpg");
        downloadManager.enqueue(request);

        talkToMainActivity.showDownloadCompleted();
    }
}

