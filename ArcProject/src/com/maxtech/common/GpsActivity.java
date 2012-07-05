package com.maxtech.common;

import org.sdu.gis.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.maxtech.common.gps.GpsTask;
import com.maxtech.common.gps.GpsTask.GpsData;
import com.maxtech.common.gps.GpsTaskCallBack;
import com.maxtech.common.gps.IAddressTask.MLocation;

public class GpsActivity extends Activity implements OnClickListener {

	private TextView gps_tip = null;
	private AlertDialog dialog = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		//gps_tip = (TextView) findViewById(R.id.gps_tip);
		/*findViewById(R.id.do_gps).setOnClickListener(GpsActivity.this);
		findViewById(R.id.do_apn).setOnClickListener(GpsActivity.this);
		findViewById(R.id.do_wifi).setOnClickListener(GpsActivity.this);*/

		dialog = new ProgressDialog(GpsActivity.this);
		dialog.setTitle("请稍等...");
		dialog.setMessage("正在定位...");
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onClick(View v) {
		gps_tip.setText("");
		switch (v.getId()) {
		case 1:
			do_apn();
			break;
		case 2:
			GpsTask gpstask = new GpsTask(GpsActivity.this,
					new GpsTaskCallBack() {

						@Override
						public void gpsConnectedTimeOut() {
							gps_tip.setText("获取GPS超时了");
						}

						@Override
						public void gpsConnected(GpsData gpsdata) {
							do_gps(gpsdata);
						}

					}, 3000);
			gpstask.execute();
			break;
		case 3:
			do_wifi();
			break;
		}
	}

	private void do_apn() {
		new AsyncTask<Void, Void, MLocation>() {

			@Override
			protected MLocation doInBackground(Void... params) {
				MLocation location = null;
				try {
					location = new AddressTask(GpsActivity.this,
							AddressTask.DO_APN).doApnPost();
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(location == null)
					return null;
				return location;
			}

			@Override
			protected void onPreExecute() {
				dialog.show();
				super.onPreExecute();
			}

			@Override
			protected void onPostExecute(MLocation result) {
				if(result == null){
					gps_tip.setText("基站定位失败了...");					
				}else {
					gps_tip.setText(result.toString());
				}
				dialog.dismiss();
				super.onPostExecute(result);
			}
			
		}.execute();
	}

	private void do_gps(final GpsData gpsdata) {
		new AsyncTask<Void, Void, MLocation>() {

			@Override
			protected MLocation doInBackground(Void... params) {
				MLocation location = null;
				try {
					Log.i("do_gpspost", "经纬度：" + gpsdata.getLatitude() + "----" + gpsdata.getLongitude());
					location = new AddressTask(GpsActivity.this,
							AddressTask.DO_GPS).doGpsPost(gpsdata.getLatitude(),
									gpsdata.getLongitude());
				} catch (Exception e) {
					e.printStackTrace();
				}
				return location;
			}

			@Override
			protected void onPreExecute() {
				dialog.show();
				super.onPreExecute();
			}

			@Override
			protected void onPostExecute(MLocation result) {
				gps_tip.setText(result.toString());
				dialog.dismiss();
				super.onPostExecute(result);
			}
			
		}.execute();
	}

	private void do_wifi() {
		new AsyncTask<Void, Void, MLocation>() {

			@Override
			protected MLocation doInBackground(Void... params) {
				MLocation location = null;
				try {
					location = new AddressTask(GpsActivity.this,
							AddressTask.DO_WIFI).doWifiPost();
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(location == null)
					return null;
				return location;
			}

			@Override
			protected void onPreExecute() {
				dialog.show();
				super.onPreExecute();
			}

			@Override
			protected void onPostExecute(MLocation result) {
				if(result != null){
					gps_tip.setText(result.toString());
				}else {
					gps_tip.setText("WIFI定位失败了...");
				}
				
				dialog.dismiss();
				super.onPostExecute(result);
			}
			
		}.execute();
	}

}