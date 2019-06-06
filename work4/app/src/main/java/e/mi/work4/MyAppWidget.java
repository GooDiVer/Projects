package e.mi.work4;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MyAppWidget extends AppWidgetProvider {
    public static final String ACTION_DATE_SET = "android.provider.DATE_SET";
    public static final String ACTION_AUTO_UPDATE = "android.provider.AUTO_UPDATE_WIDGET";
    public static final String ACTION_REMIND = "android.provider.REMIND";

    @Override
    public void onEnabled(Context context) {

        super.onEnabled(context);


        Intent intent = new Intent(context,MyAppWidget.class);
        intent.setAction(ACTION_AUTO_UPDATE);

    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);


    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);


        SharedPreferences prefs = context.getSharedPreferences(MainActivity.APP_PREFERENCES,context.MODE_PRIVATE);
        String setDate = prefs.getString(MainActivity.APP_PREFERENCES_DATE," ");

        //Create object with current date and methods for date calculations
        DateUtils dateUtils = new DateUtils();

        //set date retrieved from SharedPreferences
        dateUtils.setSetDateString(setDate);

        Calendar cal = dateUtils.getDateToNotificate(context);

        int daysLeft = dateUtils.getDaysBetwen();
        
        if(daysLeft >= 0) {

            for (int i = 0; i < appWidgetIds.length; i++) {

                Intent intent = new Intent(context, MainActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

                Intent updateIntent = new Intent(context, MyAppWidget.class);
                updateIntent.setAction(ACTION_AUTO_UPDATE);
                PendingIntent alarmIntent = PendingIntent.getBroadcast(context, 0, updateIntent, 0);

                RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget);

                views.setTextViewText(R.id.wtv1, "Your deadline " + setDate + "\n ends in: ");
                views.setTextViewText(R.id.wtv, daysLeft + " days");

                views.setOnClickPendingIntent(R.id.wtv, pendingIntent);
                views.setOnClickPendingIntent(R.id.wtv1, pendingIntent);
                views.setOnClickPendingIntent(R.id.wb, alarmIntent);

                Intent reminderIntent = new Intent(context,MyAppWidget.class);
                reminderIntent.setAction(ACTION_REMIND);

                PendingIntent pendingRemind = PendingIntent.getBroadcast(context,0,reminderIntent,0);

                AlarmManager alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                alarmMgr.setRepeating(AlarmManager.RTC, cal.getTimeInMillis(),AlarmManager.INTERVAL_DAY, pendingRemind);

                appWidgetManager.updateAppWidget(appWidgetIds, views);
            }
        }
        else
            Toast.makeText(context, "You entered the past time", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        String setDateString = intent.getStringExtra("NewDate");

        RemoteViews views = new RemoteViews(context.getPackageName(),R.layout.widget);
        ComponentName componentName = new ComponentName(context,MyAppWidget.class);


        if(intent.getAction().equals(ACTION_DATE_SET)) {

            DateUtils dateUtils = new DateUtils();

            dateUtils.setSetDateString(setDateString);

            AppWidgetManager.getInstance(context).updateAppWidget(componentName,views);

            onUpdate(context,AppWidgetManager.getInstance(context),AppWidgetManager.getInstance(context).getAppWidgetIds(componentName));
        }

        if(intent.getAction().equals(ACTION_AUTO_UPDATE)) {
            onUpdate(context,AppWidgetManager.getInstance(context),AppWidgetManager.getInstance(context).getAppWidgetIds(componentName));
        }
        
        if(intent.getAction().equals(ACTION_REMIND)) {
            Toast.makeText(context, "REMIND YOU ABOUT DEADLINE!", Toast.LENGTH_SHORT).show();
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                    .setSmallIcon(R.drawable.widgeticon)
                    .setContentTitle("Time to worry")
                    .setContentText("Deadline has come!");

            NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O)
            {
                int importance = NotificationManager.IMPORTANCE_DEFAULT;
                NotificationChannel notificationChannel = new NotificationChannel("10001", "NOTIFICATION_CHANNEL_NAME", importance);
                notificationChannel.enableLights(true);
                notificationChannel.setLightColor(Color.RED);
                notificationChannel.enableVibration(false);
                notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
                notificationChannel.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION),null);
                builder.setChannelId("10001");
                notificationManager.createNotificationChannel(notificationChannel);
            }

            builder.build().flags |= Notification.FLAG_AUTO_CANCEL;
            notificationManager.notify(1,builder.build());
        }

        super.onReceive(context, intent);

    }
}
