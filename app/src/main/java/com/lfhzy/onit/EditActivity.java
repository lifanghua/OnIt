package com.lfhzy.onit;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class EditActivity extends AppCompatActivity {

    private boolean isNew;
    private int rowNumber;
    private OnItRowData rowData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        isNew = intent.getBooleanExtra(MainActivity.EXTRA_IS_NEW, true);
        rowNumber = intent.getIntExtra(MainActivity.EXTRA_ROW_NUMBER, 0);
        rowData = intent.getParcelableExtra(MainActivity.EXTRA_ROW_DATA);
        setContentView(R.layout.activity_edit);

        initFromRowData(rowData);
    }

    private void initFromRowData(OnItRowData rowData) {
        EditText rowTitle = (EditText) findViewById(R.id.rowTitleEdit);
        rowTitle.setText(rowData.getTitle());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit, menu);
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

    public void saveTask(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(MainActivity.EXTRA_ROW_NUMBER, rowNumber);
        intent.putExtra(MainActivity.EXTRA_IS_NEW, isNew);
        final OnItRowData newRowData = getRowDataFromViews();
        intent.putExtra(MainActivity.EXTRA_ROW_DATA, newRowData);

        setResult(Activity.RESULT_OK, intent);
        finishActivity(MainActivity.CREATE_NEW_TASK_REQUEST);
    }

    private OnItRowData getRowDataFromViews() {
        String rowTitle = ((EditText) findViewById(R.id.rowTitleEdit)).getText().toString();
        return new OnItRowData(rowTitle);
    }
}
