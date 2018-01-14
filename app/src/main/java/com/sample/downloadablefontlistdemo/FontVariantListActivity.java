package com.sample.downloadablefontlistdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.koolio.library.Font;
import com.koolio.library.FontList;

public class FontVariantListActivity extends AppCompatActivity {

    private FontList fontList;
    private int fontFamilyPosition;
    private Font font;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_font_list);

        fontList = (FontList) getIntent().getSerializableExtra("FontList");
        fontFamilyPosition = getIntent().getIntExtra("position", 0);

        font = fontList.getFontByID(fontFamilyPosition);

        ListView listView = (ListView) findViewById(R.id.listview);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.listview_item, R.id.textView, font.getFontVariants());

        listView.setAdapter(adapter);

        Toast.makeText(FontVariantListActivity.this,
                getString(R.string.select_font_variant), Toast.LENGTH_LONG)
                .show();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(FontVariantListActivity.this, ShowTextWithFontActivity.class);
                intent.putExtra("queryString", font.getQueryString(position));
                intent.putExtra("fontFamily", font.getFontFamily());
                intent.putExtra("fontVariant", font.getFontVariants()[position]);
                startActivity(intent);
            }
        });
    }

}
