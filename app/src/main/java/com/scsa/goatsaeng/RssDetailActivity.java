package com.scsa.goatsaeng;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RssDetailActivity extends AppCompatActivity {

    TextView title;
    WebView desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rss_detail);

        title = findViewById(R.id.title);
        desc = findViewById(R.id.desc);

        String getTitle = getIntent().getStringExtra("title");
        String getLink = getIntent().getStringExtra("link");

        title.setText(getTitle);
        desc.loadUrl(getLink);
    }
}
