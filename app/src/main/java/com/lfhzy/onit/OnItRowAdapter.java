package com.lfhzy.onit;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Adapter for OnIt Rows.
 */
public class OnItRowAdapter extends ArrayAdapter<OnItRowData> {

    private final Context context;
    private final ArrayList<OnItRowData> rowItems;

    public OnItRowAdapter(Context context, OnItRowData[] rowItems) {
        super(context, R.layout.onit_row, rowItems);
        this.context = context;
        this.rowItems = new ArrayList<>();
        Collections.addAll(this.rowItems, rowItems);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.onit_row, parent, false);
        }

        final TextView rowTitle = (TextView) convertView.findViewById(R.id.rowTitle);
        ImageButton toTop = (ImageButton) convertView.findViewById(R.id.toTop);
        final OnItRowData rowItem = rowItems.get(position);

        toTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, rowItem.getTitle(), Toast.LENGTH_SHORT).show();
                rowItems.remove(rowItem);
                rowItems.add(0, rowItem);
                OnItRowAdapter.this.notifyDataSetChanged();
                updateNotification(position);
            }
        });


        rowTitle.setText(rowItem.getTitle());

        return convertView;
    }

    private void updateNotification(int position) {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.notification_icon)
                        .setContentTitle("My notification" + position)
                        .setContentText("Hello World!" + position)
                        .setOngoing(true)
                        .setPriority(NotificationCompat.PRIORITY_HIGH);
        // Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(context, MainActivity.class);
        resultIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

        // The stack builder object will contain an artificial back stack for the
        // started Activity.
        // This ensures that navigating backward from the Activity leads out of
        // your application to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        // Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(MainActivity.class);
        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);

        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        // Clear all previous messages.
        mNotificationManager.cancelAll();
        // mId allows you to update the notification later on.
        mNotificationManager.notify(position, mBuilder.build());
    }
}
