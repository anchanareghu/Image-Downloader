package com.example.imagedownloader;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;

public class DownloadPresenter {
    private final DownloadView view;
    private final Context context;

    public DownloadPresenter(DownloadView view, Context context) {
        this.view = view;
        this.context = context;
    }

    public void onDownloadButtonClicked(String imageUrl) {

        view.showDownloadInProgress();
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(imageUrl));
        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE)
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "image.jpg");
          downloadManager.enqueue(request);

        view.showDownloadCompleted();
    }
}
