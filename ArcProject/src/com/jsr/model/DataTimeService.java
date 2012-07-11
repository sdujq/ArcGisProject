package com.jsr.model;

import com.jsr.controller.WeatherWidget;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.widget.RemoteViews;

public class DataTimeService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		System.out.println("start service time updata");
		AppWidgetManager manager = AppWidgetManager.getInstance(this);
		RemoteViews updateViews = WeatherWidget.updateTime(this);

		if (updateViews != null) {
			manager.updateAppWidget(manager.getAppWidgetIds(new ComponentName(
					this, WeatherWidget.class)), updateViews);
		}

		long now = System.currentTimeMillis();
		long updateMilis = 5 * 1000;

		PendingIntent pendingIntent = PendingIntent.getService(this, 0, intent,
				0);

		// Schedule alarm, and force the device awake for this update
		AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		alarmManager.set(AlarmManager.RTC_WAKEUP, now + updateMilis,
				pendingIntent);

		// No updates remaining, so stop service
		stopSelf();
	}
}
