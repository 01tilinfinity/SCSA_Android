package com.scsa.goatsaeng;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RssMainActivity extends AppCompatActivity {
    private static final String TAG = "RssMainActivity_SCSA";

    private ListView listView;
    private RssAdapter adapter;
    private List<RssItem> rssItemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rss_main);

        listView = findViewById(R.id.result);
        adapter = new RssAdapter(this, rssItemList);
        listView.setAdapter(adapter);

        new RssAsyncTask().execute("https://www.hani.co.kr/rss/");

        // 여기 새로 추가!
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RssItem item = rssItemList.get(position);
                Intent intent = new Intent(RssMainActivity.this, RssDetailActivity.class);
                intent.putExtra("title", item.title);
                intent.putExtra("description", item.description);
                intent.putExtra("link", item.link);
                startActivity(intent);
            }
        });
    }

    private class RssAsyncTask extends AsyncTask<String, Void, List<RssItem>> {

        @Override
        protected List<RssItem> doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                InputStream inputStream = url.openConnection().getInputStream();
                parseRss(inputStream);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return rssItemList;
        }

        @Override
        protected void onPostExecute(List<RssItem> result) {
            if (result.isEmpty()) {
                Toast.makeText(RssMainActivity.this, "불러올 데이터가 없습니다", Toast.LENGTH_SHORT).show();
            } else {
                adapter.notifyDataSetChanged();
            }
        }

        private void parseRss(InputStream inputStream) {
            try {
                XmlPullParser parser = Xml.newPullParser();
                parser.setInput(new InputStreamReader(inputStream));

                int eventType = parser.getEventType();
                RssItem currentItem = null;
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    String name;
                    switch (eventType) {
                        case XmlPullParser.START_TAG:
                            name = parser.getName();
                            if (name.equalsIgnoreCase("item")) {
                                currentItem = new RssItem();
                            } else if (currentItem != null) {
                                if (name.equalsIgnoreCase("title")) {
                                    currentItem.title = parser.nextText();
                                } else if (name.equalsIgnoreCase("link")) {
                                    currentItem.link = parser.nextText();
                                } else if (name.equalsIgnoreCase("description")) {
                                    currentItem.description = parser.nextText();
                                }
                            }
                            break;
                        case XmlPullParser.END_TAG:
                            name = parser.getName();
                            if (name.equalsIgnoreCase("item") && currentItem != null) {
                                rssItemList.add(currentItem);
                                currentItem = null;
                            }
                            break;
                    }
                    eventType = parser.next();
                }
            } catch (Exception e) {
                Log.e(TAG, "Parsing error", e);
            }
        }
    }
}
