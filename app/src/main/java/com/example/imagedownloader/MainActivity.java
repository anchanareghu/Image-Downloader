package com.example.imagedownloader;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MainActivity extends Activity implements ITalkToMainActivity {
    ProgressBar progressBar;
    Button downloadButton;
    ImageView imageView;
    ImageButton searchButton;
    EditText editText;
    MainPresenter mainPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_main);

        imageView = findViewById(R.id.view_image);
        editText = findViewById(R.id.edittext_search);
        searchButton = findViewById(R.id.search_button);
        progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.GONE);
        downloadButton = findViewById(R.id.download_button);

        mainPresenter = new MainPresenter(this, this.getApplicationContext());

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                mainPresenter.onSearchButtonClick(editText.getText().toString());
            }
        });
        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainPresenter.onDownloadButtonClicked(editText.getText().toString());
            }
        });
    }

    @Override
    public void showImage(Bitmap bitmap) {
        runOnUiThread(new Runnable() {
            public void run() {
                progressBar.setVisibility(View.GONE);
                imageView.setImageBitmap(bitmap);
                downloadButton.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void showImageLoadError() {
        setToastMessage("Failed to load image.");
    }

    @Override
    public void showDownloadInProgress() {
        setToastMessage("Image download started.");
    }

    @Override
    public void showDownloadCompleted() {
        setToastMessage("Image download complete.");
    }

    public void setToastMessage(String message) {
        runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
