package com.lfhzy.onit;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView rowItems = (ListView) findViewById(R.id.rowItems);
        final OnItRowAdapter rowAdapter = new OnItRowAdapter(this, getDataForListView());
        rowItems.setAdapter(rowAdapter);
        rowItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //get selected items
                OnItRowData selectedValue = (OnItRowData) rowAdapter.getItem(position);
                Toast.makeText(MainActivity.this, selectedValue.getTitle(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private OnItRowData[] getDataForListView() {
        OnItRowData[] rowItemList = new OnItRowData[10];

        for(int i=0;i<10;i++)
        {
            OnItRowData rowItem = new OnItRowData("TEST item" + i);
            rowItemList[i] = rowItem;
        }

        return rowItemList;

    }
}
