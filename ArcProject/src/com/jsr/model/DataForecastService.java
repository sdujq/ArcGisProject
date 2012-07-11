/**
 * 
 */
package com.jsr.model;

import java.util.LinkedList;
import java.util.Queue;
import com.jsr.controller.WeatherWidget;
import com.jsr.model.WeatherProvider.WeatherWidgets;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import android.widget.RemoteViews;

/**
 * @author HANJUE
 * 
 */
public class DataForecastService extends Service implements Runnable {
	private static Queue<Integer> requestWidgetIDs = new LinkedList<Integer>();
	private static Object isLock = new Object();
	private static boolean isThreadRun = false;
	public static final String ACTION_UPDATE_ALL = "com.jsr.weather.UPDATE_ALL";
	public static final String[] widgetdata = new String[] {
			DataWidget.ISCONFIGURED, DataWidget.LASTUPDATETIME,
			DataWidget.UPDATEMILIS };

	public static void addWidgetIDs(int[] widgetIDs) {
		synchronized (isLock) {
			for (int id : widgetIDs) {
				System.out.println("add widget ID:" + id);
				requestWidgetIDs.add(id);
			}
		}
	}

	public static boolean hasMoreWidgetIDs() {
		synchronized (isLock) {
			boolean hasMore = !requestWidgetIDs.isEmpty();
			if (!hasMore) {
				isThreadRun = hasMore;
			}
			System.out.println("hasMore" + hasMore);
			return hasMore;
		}
	}

	public static Integer nextWidgetIDs() {
		synchronized (isLock) {
			if (requestWidgetIDs.peek() != null) {
				return requestWidgetIDs.poll();
			} else {
				return AppWidgetManager.INVALID_APPWIDGET_ID;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Service#onBind(android.content.Intent)
	 */
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		System.out.println("service started");
		if (ACTION_UPDATE_ALL.equals(intent.getAction())) {
			System.out.println("Requested UPDATE_ALL action");
			AppWidgetManager manager = AppWidgetManager.getInstance(this);
			addWidgetIDs(manager.getAppWidgetIds(new ComponentName(this,
					WeatherWidget.class)));
		}
		synchronized (isLock) {
			if (!isThreadRun) {
				isThreadRun = true;
				new Thread(this).start();
			}
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("DataForecastService is running");
		AppWidgetManager manager = AppWidgetManager.getInstance(this);
		ContentResolver resolver = getContentResolver();

		long nowtime = System.currentTimeMillis();
		long updateMilis = 2 * 60 * 60 * 1000;
		while (hasMoreWidgetIDs()) {
			System.out.println("while is running");
			int widgetId = nextWidgetIDs();
			Uri uri = ContentUris.withAppendedId(WeatherWidgets.CONTENT_URI,
					widgetId);
			Cursor cursor = null;
			boolean isConfigured = false;
			boolean shouldUpdate = false;
			try {
				cursor = resolver.query(uri, widgetdata, null, null, null);
				if (cursor != null && cursor.moveToFirst()) {
					System.out.println("query widget imformation");
					isConfigured = cursor.getInt(0) == 1;
					long lastUpdateTime = cursor.getLong(1);
					updateMilis = cursor.getLong(2) * 60 * 60 * 1000;
					shouldUpdate = Math.abs(nowtime - updateMilis) > lastUpdateTime;
				}
			} finally {
				if (cursor != null) {
					cursor.close();
				}
			}
			if (!isConfigured) {
				System.out.println("!isConfigured is display");
				continue;
			} else if (shouldUpdate) {
				try {
					LoadDataNet.updateForecasts(this, uri);
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("shouldUpdate has a exception");
				}
			}
			System.out.println("Widget refresh views");
			RemoteViews updateViews = WeatherWidget.updataViews(this, uri);
			if (updateViews != null) {
				manager.updateAppWidget(widgetId, updateViews);

			}
		}
		System.out.println("set nxet update time");
		nowtime = System.currentTimeMillis();
		long nextUpdate = nowtime + 30 * 60 * 1000;

		Intent updateIntent = new Intent(ACTION_UPDATE_ALL);
		updateIntent.setClass(this, DataForecastService.class);

		PendingIntent pendingIntent = PendingIntent.getService(this, 0,
				updateIntent, 0);

		// Schedule alarm, and force the device awake for this update
		AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		alarmManager.set(AlarmManager.RTC_WAKEUP, nextUpdate, pendingIntent);

		// No updates remaining, so stop service
		stopSelf();
	}

}
