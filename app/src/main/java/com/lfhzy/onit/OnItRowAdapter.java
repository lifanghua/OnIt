package com.lfhzy.onit;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lifanghua0602 on 9/13/15.
 */
public class OnItRowAdapter extends ArrayAdapter<OnItRowData> {

    private final Context context;
    private final OnItRowData[] rowItems;

    public OnItRowAdapter(Context context, OnItRowData[] rowItems) {
        super(context, R.layout.onit_row, rowItems);
        this.context = context;
        this.rowItems = rowItems;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.onit_row, parent, false);
        }

        final TextView rowTitle = (TextView) convertView.findViewById(R.id.rowTitle);
        ImageButton toTop = (ImageButton) convertView.findViewById(R.id.toTop);
        final OnItRowData rowItem = rowItems[position];

        toTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, rowItem.getTitle(), Toast.LENGTH_LONG).show();
            }
        });


        rowTitle.setText(rowItem.getTitle());

        return convertView;
    }
}
