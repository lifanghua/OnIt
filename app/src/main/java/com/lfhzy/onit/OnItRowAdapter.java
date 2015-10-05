package com.lfhzy.onit;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Adapter for OnIt Rows.
 */
public class OnItRowAdapter extends ArrayAdapter<OnItRowData> {

    private final Context context;
    private final List<OnItRowData> rowItems;

    public OnItRowAdapter(Context context, List<OnItRowData> rowItems) {
        super(context, R.layout.onit_row, rowItems);
        this.context = context;
        this.rowItems = rowItems;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.onit_row, parent, false);
        }

        final OnItRowData rowItem = rowItems.get(position);

        final TextView rowTitle = (TextView) convertView.findViewById(R.id.rowTitle);
        ImageButton toTop = (ImageButton) convertView.findViewById(R.id.toTop);

        toTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, rowItem.getTitle(), Toast.LENGTH_SHORT).show();
                rowItems.remove(rowItem);
                rowItems.add(0, rowItem);
                OnItRowAdapter.this.notifyDataSetChanged();
                updateNotification(rowItem, position);
            }
        });

        return convertView;
    }

    private void updateNotification(OnItRowData rowData, int position) {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.notification_icon)
                        .setContentTitle("My notification" + rowData.getTitle())
                        .setContentText(rowData.getTitle())
                        .setOngoing(true)
                        .setPriority(NotificationCompat.PRIORITY_HIGH);
        // Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(context, MainActivity.class);
        resultIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(
                context, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);

        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        // Clear all previous messages.
        mNotificationManager.cancelAll();
        // mId allows you to update the notification later on.
        mNotificationManager.notify(position, mBuilder.build());
    }
}
