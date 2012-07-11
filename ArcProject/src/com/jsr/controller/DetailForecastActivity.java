package com.jsr.controller;

import org.sdu.gis.R;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsoluteLayout;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

import com.jsr.model.DataWidget;
import com.jsr.model.DetailDateWidget;
import com.jsr.model.LoadDataNet;
import com.jsr.model.WeatherProvider.WeatherWidgets;
import com.jsr.model.WeatherUtil;

public class DetailForecastActivity extends ListActivity {
	private static final String TAG = "DetailForecastActivity";

	private Uri mData;
	private ListAdapter listAdapter;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail);

		// Use provided data to figure out which widget was selected
		final Intent intent = getIntent();
		mData = intent.getData();
		if (mData == null) {
			finish();
			return;
		} else {
			Log.d(TAG, "Showing details for data=" + mData);
		}

		// Pull widget title and desired units
		DataWidget dw = LoadDataNet.getDataWidget(this, mData, false);

		
		if (dw != null) {
			ImageView forecastImage = (ImageView) findViewById(R.id.dForecastImage);
			TextView cityText = (TextView) findViewById(R.id.dCityText);
			TextView conditionText = (TextView) findViewById(R.id.dConditionText);
			TextView humidityText = (TextView) findViewById(R.id.dHumidityText);
			TextView windText = (TextView) findViewById(R.id.dWindText);
			TextView tempcText = (TextView) findViewById(R.id.dTempCText);

			forecastImage.setImageResource(WeatherUtil.getForecastImage(
					dw.getIcon(), WeatherUtil.isDaytime()));
			cityText.setText(dw.getCity());
			conditionText.setText(dw.getCondition());
			humidityText.setText(dw.getHumidity());
			windText.setText(dw.getWindcondition());
			tempcText.setText(dw.getTempc() + "¡æ");
			
			
			updateAnimtation(dw.getIcon());
		}

		// Query for any matching forecast data and create adapter
		Uri forecastUri = Uri.withAppendedPath(mData,
				WeatherWidgets.FORECAST_END);
		Cursor forecastCursor = managedQuery(forecastUri,
				DetailDateWidget.detailProjection, null, null, null);

		listAdapter = new ForecastAdapter(this, forecastCursor);

		setListAdapter(listAdapter);
	}

	@SuppressWarnings("deprecation")
	private void updateAnimtation(String url) {
		// TODO Auto-generated method stub
		int icon = WeatherUtil.getCurrentForecastIcon(url);		
		ImageView currentIcon = (ImageView)findViewById(R.id.dForecastImage);
		Animation curIconAnim = AnimationUtils.loadAnimation(this, R.anim.aphlacurrentweather);
		currentIcon.setAnimation(curIconAnim);
		if (icon == R.drawable.weather_cloudy){
			AbsoluteLayout absLayout = (AbsoluteLayout)findViewById(R.id.imagesLayout);
			ImageView cloud01 = new ImageView(this);
			ImageView cloud02 = new ImageView(this);
			cloud01.setAdjustViewBounds(true);
			cloud02.setAdjustViewBounds(true);
			cloud01.setImageResource(R.drawable.layer_cloud1);
			cloud02.setImageResource(R.drawable.layer_cloud2);
			cloud01.setMaxHeight(48);
			cloud01.setMinimumHeight(48);
			cloud01.setMaxWidth(100);
			cloud01.setMinimumWidth(100);
			
			cloud02.setMaxHeight(58);
			cloud02.setMinimumHeight(58);
			cloud02.setMaxWidth(83);
			cloud02.setMinimumWidth(83);
			
			Animation leftAnim = AnimationUtils.loadAnimation(this, R.anim.translatecloudleft);
			Animation rightAnim = AnimationUtils.loadAnimation(this, R.anim.translatecloudright);
			
			leftAnim.setRepeatCount(Animation.INFINITE);
			rightAnim.setRepeatCount(Animation.INFINITE);
			
			cloud01.setAnimation(leftAnim);
			cloud02.setAnimation(rightAnim);
			
			absLayout.addView(cloud01);
			absLayout.addView(cloud02);			
			
		}
		
	
	}

	
	private class ForecastAdapter extends ResourceCursorAdapter {

		public ForecastAdapter(Context context, Cursor c) {
			super(context, R.layout.detailitems, c);
			// TODO Auto-generated constructor stub
			System.out.println("ForecastAdapter");
		}

		@Override
		public void bindView(View view, Context context, Cursor c) {
			// TODO Auto-generated method stub
			System.out.println("View ForecastAdapter");
			ImageView icon = (ImageView) view.findViewById(R.id.dDetailImage);
			TextView day = (TextView) view.findViewById(R.id.ddDayText);
			TextView condition = (TextView) view.findViewById(R.id.ddConditionText);
			TextView temp = (TextView) view.findViewById(R.id.ddTempCText);

			icon.setImageResource(WeatherUtil.getDetailForecastIcon(c.getString(4)));
			day.setText(c.getString(1));
			condition.setText(c.getString(5));
			temp.setText(c.getInt(2) + "¡æ/" + c.getInt(3) + "¡æ");
		}
	}
}