package com.example.mobilesystems;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;


public class NotificationClickerActivity extends AppCompatActivity {

    private static int count = 0;
    private static final String ACTION_INCREMENT = "action_increment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createNotificationChannel();
        showNotification(this, count);
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Counter Channel";
            String description = "Channel for Counter Notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("counter_channel", name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private static void showNotification(Context context, int count) {
        PendingIntent increasePendingIntent = PendingIntent.getBroadcast(
                context,
                0,
                new Intent(context, NotificationReceiver.class)
                        .setAction(ACTION_INCREMENT),
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_MUTABLE
        );

        PendingIntent decreasePendingIntent = PendingIntent.getBroadcast(
                context,
                1,
                new Intent(context, NotificationReceiver.class)
                        .setAction("decrease"),
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_MUTABLE
        );

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "counter_channel")
                .setContentTitle("Counter")
                .setContentText("Current count: " + count)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .addAction(R.drawable.ic_launcher_background, "Increase", increasePendingIntent)
                .addAction(R.drawable.ic_launcher_background, "Decrease", decreasePendingIntent);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
            notificationManager.notify(1, builder.build());
        }
    }

    public static class NotificationReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action != null && action.equals(ACTION_INCREMENT)) {
                count++;
                // Отправляем широковещательное намерение с обновленным значением count
                Intent updateIntent = new Intent("update_count");
                updateIntent.putExtra("count", count);
                context.sendBroadcast(updateIntent);
            } else if (action != null && action.equals("decrease")) {
                if (count > 0) {
                    count--;
                }
            }
            showNotification(context, count);
        }
    }
}