package com.sample.downloadablefontlistdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.koolio.library.DownloadableFontList;
import com.koolio.library.FontList;


public class MainActivity extends AppCompatActivity {

    private final String YOUR_API_KEY = "Your Key here";
    private Button getFontListButton;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        getFontListButton = findViewById(R.id.button);

        getFontListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                getDownloadableFontList();
            }
        });

    }

    public void getDownloadableFontList() {
        DownloadableFontList.FontListCallback fontListCallback = new DownloadableFontList.FontListCallback() {
            @Override
            public void onFontListRetrieved(FontList fontList) {
                // Some method usages
//                ArrayList<Font> fonts = fontList.getFontArrayList();
//                ArrayList<String> fontsStr = fontList.getFontFamilyList();
//                fontList.getFontByID(0).getQueryString(1);

                progressBar.setVisibility(View.GONE);

                Intent intent = new Intent(MainActivity.this, FontFamilyListActivity.class);
                intent.putExtra("FontList", fontList);
                startActivity(intent);
            }

            @Override
            public void onTypefaceRequestFailed(int reason) {

            }
        };

        DownloadableFontList.requestDownloadableFontList(fontListCallback, YOUR_API_KEY);

    }
}
