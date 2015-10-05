package com.lfhzy.onit;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
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

    public static final String EXTRA_ROW_NUMBER = "com.lfhzy.onit.ROW_NUMBER";
    public static final String EXTRA_IS_NEW = "com.lfhzy.onit.IS_NEW";
    public static final String EXTRA_ROW_DATA = "com.lfhzy.onit.ROM_DATA";
    public static final int CREATE_NEW_TASK_REQUEST = 1;

    private OnItRowAdapter rowAdapter;
    private ArrayList<OnItRowData> rowItemsData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView rowItems = (ListView) findViewById(R.id.rowItems);
        rowItemsData = new ArrayList<OnItRowData>();
        rowAdapter = new OnItRowAdapter(this, rowItemsData);
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

    private void addRow(OnItRowData newRowData) {
        rowItemsData.add(0, newRowData);
        rowAdapter.notifyDataSetChanged();
    }

    public OnItRowAdapter getRowAdapter() {
        return rowAdapter;
    }

    private OnItRowData[] getDataForListView() {
        OnItRowData[] rowItemList = new OnItRowData[3];

        for(int i=0; i<3; i++)
        {
            OnItRowData rowItem = new OnItRowData("TEST item" + i);
            rowItemList[i] = rowItem;
        }

        return rowItemList;

    }

    /** Called when the user clicks the Add button */
    public void createNewTask(View view) {
        Intent intent = new Intent(this, EditActivity.class);
        intent.putExtra(EXTRA_ROW_NUMBER, 0);
        intent.putExtra(EXTRA_IS_NEW, true);
        intent.putExtra(EXTRA_ROW_DATA, OnItRowData.getDefaultInstance());
        startActivityForResult(intent, CREATE_NEW_TASK_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Toast.makeText(this, requestCode + ":code:" + resultCode, Toast.LENGTH_SHORT).show();

        // If the request went well (OK) and the request was CREATE_NEW_TASK_REQUEST
        if (resultCode == Activity.RESULT_OK && requestCode == CREATE_NEW_TASK_REQUEST) {
            addRow((OnItRowData) data.getParcelableExtra(EXTRA_ROW_DATA));
            Toast.makeText(this, ((OnItRowData) data.getParcelableExtra(EXTRA_ROW_DATA)).getTitle(), Toast.LENGTH_SHORT).show();
        }
    }
}
