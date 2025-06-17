package com.scsa.goatsaeng;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class RssAdapter extends ArrayAdapter<RssItem> {

    public RssAdapter(Context context, List<RssItem> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RssItem item = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        }
        TextView tv = convertView.findViewById(android.R.id.text1);
        tv.setText(item.title);
        return convertView;
    }
}
