package com.sample.downloadablefontlistdemo;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.koolio.library.Font;
import com.koolio.library.FontList;

import java.util.List;

public class FontFamilyListActivity extends AppCompatActivity {
    private FontList fontList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_font_list);

        fontList = (FontList) getIntent().getSerializableExtra("FontList");

        ListView listView = (ListView) findViewById(R.id.listview);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.listview_item, R.id.textView, fontList.getFontFamilyList().toArray(new String[0]));

        listView.setAdapter(adapter);

        Toast.makeText(FontFamilyListActivity.this,
                getString(R.string.select_font_family), Toast.LENGTH_LONG)
                .show();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(FontFamilyListActivity.this, FontVariantListActivity.class);
                intent.putExtra("FontList", fontList);
                intent.putExtra("position", position);
                startActivity(intent);

            }
        });


    }
}
