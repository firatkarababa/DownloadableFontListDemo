package com.sample.downloadablefontlistdemo;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.provider.FontRequest;
import android.support.v4.provider.FontsContractCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class ShowTextWithFontActivity extends AppCompatActivity {
    private TextView title, bodyText;
    private ProgressBar progressBar;
    private String fontVariant, fontFamily;
    private Handler mHandler = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_text_with_font);

        title = findViewById(R.id.formatted_textview_title);
        bodyText = findViewById(R.id.body_textview);
        progressBar = findViewById(R.id.progressBar2);
        title.setVisibility(View.GONE);
        bodyText.setVisibility(View.GONE);

        String query = getIntent().getStringExtra("queryString");
        fontFamily = getIntent().getStringExtra("fontFamily");
        fontVariant = getIntent().getStringExtra("fontVariant");

        startFontDownload(query);

    }

    private void startFontDownload(String query) {
        final FontRequest request = new FontRequest(
                "com.google.android.gms.fonts",
                "com.google.android.gms",
                query,
                R.array.com_google_android_gms_fonts_certs);

        final FontsContractCompat.FontRequestCallback callback = new FontsContractCompat
                .FontRequestCallback() {
            @Override
            public void onTypefaceRetrieved(Typeface typeface) {
                bodyText.setTypeface(typeface);  // Font has been downloaded successfully
                title.setTypeface(typeface);  // Font has been downloaded successfully

                title.setVisibility(View.VISIBLE);
                bodyText.setVisibility(View.VISIBLE);

                title.setText(fontFamily + ",\t" + fontVariant);
                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onTypefaceRequestFailed(int reason) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(ShowTextWithFontActivity.this,
                        getString(R.string.request_failed), Toast.LENGTH_LONG)
                        .show();
            }
        };

        FontsContractCompat
                .requestFont(ShowTextWithFontActivity.this, request, callback,
                        getHandlerThreadHandler());
    }

    private Handler getHandlerThreadHandler() {
        if (mHandler == null) {
            HandlerThread handlerThread = new HandlerThread("fonts");
            handlerThread.start();
            mHandler = new Handler(handlerThread.getLooper());
        }
        return mHandler;
    }

}
