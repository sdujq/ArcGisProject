package com.jsr.controller;

import java.util.List;

import org.sdu.bmputil.BitmapTool;
import org.sdu.dao.BugDao;
import org.sdu.dao.TaskDao;
import org.sdu.gis.R;
import org.sdu.pojo.Bug;
import org.sdu.pojo.Task;
import org.sdu.view.usermanager.AccountActivity;
import org.sdujq.map.TabHomeActivity;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.format.DateFormat;
import android.text.format.Time;
import android.widget.RemoteViews;

import com.jsr.model.DataForecastService;
import com.jsr.model.DataTimeService;
import com.jsr.model.DataWidget;
import com.jsr.model.LoadDataNet;
import com.jsr.model.WeatherUtil;

public class WeatherWidget extends AppWidgetProvider {
	private static Time TIME_WIDGET = new Time();
	public static int uid;

	public void onEnabled(Context context) {
		RemoteViews views = new RemoteViews(context.getPackageName(),
				R.layout.weatherwidget);
		Intent it = new Intent(context, AccountActivity.class);
		PendingIntent pit = PendingIntent.getActivity(context, 0, it,
				PendingIntent.FLAG_ONE_SHOT);
		views.setOnClickPendingIntent(R.id.hour01Image, pit);
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		DataForecastService.addWidgetIDs(appWidgetIds);
		context.startService(new Intent(context, DataForecastService.class));
		context.startService(new Intent(context, DataTimeService.class));
	}

	public static RemoteViews updataViews(Context context, Uri uri) {
		System.out.println("WeatherWidget RemoteViews is running");
		RemoteViews views = new RemoteViews(context.getPackageName(),
				R.layout.weatherwidget);
		DataWidget dataWidget = LoadDataNet.getDataWidget(context, uri, true);
		if (dataWidget != null&&dataWidget.getDetails().size()>0) {
			System.out.println("update View from dataWidget");
			views.setImageViewResource(R.id.forecastImage, WeatherUtil
					.getForecastImage(dataWidget.getIcon(),
							WeatherUtil.isDaytime()));
			views.setTextViewText(R.id.cityText, dataWidget.getCity());
			views.setTextViewText(R.id.conditionText, dataWidget.getCondition());
			views.setTextViewText(R.id.tempCText, dataWidget.getTempc()
					.toString() + "℃");

			views.setTextViewText(R.id.hightText, "H:"
					+ dataWidget.getDetails().get(0).getHight().toString()
					+ "℃");

			views.setTextViewText(R.id.lowText, "L:"
					+ dataWidget.getDetails().get(0).getLow().toString() + "℃");

		}

		// Connect click intent to launch details dialog
		Intent it = new Intent(context, TabHomeActivity.class);
		it.putExtra("uid", uid);
		PendingIntent pending = PendingIntent.getActivity(context, 0, it, 0);
		views.setOnClickPendingIntent(R.id.forecastImage, pending);

		PendingIntent pending1 = PendingIntent.getActivity(context, 0, it, 0);
		views.setOnClickPendingIntent(R.id.cityText, pending1);

		PendingIntent pending2 = PendingIntent.getActivity(context, 0, it, 0);
		views.setOnClickPendingIntent(R.id.conditionText, pending2);

		PendingIntent pending3 = PendingIntent.getActivity(context, 0, it, 0);
		views.setOnClickPendingIntent(R.id.dateText, pending3);

		PendingIntent pending4 = PendingIntent.getActivity(context, 0, it, 0);
		views.setOnClickPendingIntent(R.id.dayText, pending4);

		PendingIntent pendingIntent01 = PendingIntent.getActivity(context, 0,
				it, 0);
		views.setOnClickPendingIntent(R.id.minute01Image, pendingIntent01);

		PendingIntent pendingIntent02 = PendingIntent.getActivity(context, 0,
				it, 0);
		views.setOnClickPendingIntent(R.id.minute02Image, pendingIntent02);

		PendingIntent pendingIntent03 = PendingIntent.getActivity(context, 0,
				it, 0);
		views.setOnClickPendingIntent(R.id.hour01Image, pendingIntent03);

		PendingIntent pendingIntent04 = PendingIntent.getActivity(context, 0,
				it, 0);
		views.setOnClickPendingIntent(R.id.hour02Image, pendingIntent04);

		PendingIntent pendingIntent05 = PendingIntent.getActivity(context, 0,
				it, 0);
		views.setOnClickPendingIntent(R.id.lowText, pendingIntent05);

		PendingIntent pendingIntent06 = PendingIntent.getActivity(context, 0,
				it, 0);
		views.setOnClickPendingIntent(R.id.hightText, pendingIntent06);

		PendingIntent pendingIntent07 = PendingIntent.getActivity(context, 0,
				it, 0);
		views.setOnClickPendingIntent(R.id.tempCText, pendingIntent07);

		return views;
	}

	static int id = 0;
	static int id2 = 0;

	public static RemoteViews updateTime(Context context) {
		// TODO Auto-generated method stub
		RemoteViews views = new RemoteViews(context.getPackageName(),
				R.layout.weatherwidget);

		TIME_WIDGET.setToNow();
		int hour01 = TIME_WIDGET.hour / 10;
		int hour02 = TIME_WIDGET.hour % 10;
		int minute01 = TIME_WIDGET.minute / 10;
		int minute02 = TIME_WIDGET.minute % 10;
		int dayOfWeek = TIME_WIDGET.weekDay;
		CharSequence dt = DateFormat.format(
				context.getString(R.string.dateFormat),
				TIME_WIDGET.toMillis(false));

		views.setTextViewText(R.id.dateText, dt);
		views.setTextViewText(R.id.dayText,
				WeatherUtil.getDayofWeek(context, dayOfWeek));
		views.setImageViewResource(R.id.hour01Image,
				WeatherUtil.getImageNumber(hour01));
		views.setImageViewResource(R.id.hour02Image,
				WeatherUtil.getImageNumber(hour02));
		views.setImageViewResource(R.id.minute01Image,
				WeatherUtil.getImageNumber(minute01));
		views.setImageViewResource(R.id.minute02Image,
				WeatherUtil.getImageNumber(minute02));
		TaskDao tdao = new TaskDao(context);
		BugDao bdao = new BugDao(context);
		List<Task> lst = tdao.find();
		List<Bug> bugList = bdao.find(new String[] { "id" }, null, null, null,
				null, null, null);

		// up
		if (lst.size() > 0) {
			Task task = lst.get(id);
			String str = task.getId() + "号任务:" + task.getRoadName()
					+ task.getTaskType();
			if (id % 2 == 0) {
				RemoteViews subViews = new RemoteViews(
						context.getPackageName(), R.layout.tv2);
				views.removeAllViews(R.id.message);
				views.addView(R.id.message, subViews);
				views.setTextViewText(R.id.data, "" + str);
			} else {
				RemoteViews subViews = new RemoteViews(
						context.getPackageName(), R.layout.tv1);
				views.removeAllViews(R.id.message);
				views.addView(R.id.message, subViews);
				views.setTextViewText(R.id.data, "" + str);
			}
			id++;
			if (id >= lst.size()) {
				id = 0;
			}
		}
		// down
		if (bugList.size() > 0) {
			Bug bug = bugList.get(id2);

			if (id2 % 2 == 0) {
				bug = bdao.get(bug.getId());
				RemoteViews subViews = new RemoteViews(
						context.getPackageName(), R.layout.bv2);
				views.removeAllViews(R.id.bugs);
				views.addView(R.id.bugs, subViews);
				try {
					subViews.setImageViewBitmap(R.id.bvimageView1,
							BitmapTool.Bytes2Bimap(bug.getAttachment()));
					subViews.setTextViewText(R.id.bvtextView1, bug.getId()
							+ "号问题");
					subViews.setTextViewText(R.id.bvtextView2, bug.getAddress());
					subViews.setTextViewText(R.id.bvtextView3, bug.getState());
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				bug = bdao.get(bug.getId());
				RemoteViews subViews = new RemoteViews(
						context.getPackageName(), R.layout.bv1);
				views.removeAllViews(R.id.bugs);
				views.addView(R.id.bugs, subViews);
				try {
					subViews.setImageViewBitmap(R.id.bvimageView1,
							BitmapTool.Bytes2Bimap(bug.getAttachment()));
					subViews.setTextViewText(R.id.bvtextView1, bug.getId()
							+ "号问题");
					subViews.setTextViewText(R.id.bvtextView2, bug.getAddress());
					subViews.setTextViewText(R.id.bvtextView3, bug.getState());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			id2++;
			if (id2 >= bugList.size()) {
				id2 = 0;
			}
		}
		return views;
	}
}
