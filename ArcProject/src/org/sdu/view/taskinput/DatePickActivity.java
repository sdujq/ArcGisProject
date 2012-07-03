package org.sdu.view.taskinput;

import java.util.Calendar;

import org.sdu.gis.R;

import android.app.Activity;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;

public class DatePickActivity extends Activity {

	public Button bt_shezhi, bt_quxiao;
	private int a = 0;
	private DatePicker dp_datepick;
	private int year;
	private int month;
	private int day;

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.datepick);

		Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);

		bt_shezhi = (Button) findViewById(R.id.d_bt_shezhi);
		bt_shezhi.setOnClickListener(new ShizhiListener());

		bt_quxiao = (Button) findViewById(R.id.d_bt_quxiao);
		bt_quxiao.setOnClickListener(new QuxiaoListener());

		dp_datepick = (DatePicker) findViewById(R.id.d_datePicker);
		dp_datepick.init(year, month, day, new OnDateChangedListener() {

			public void onDateChanged(DatePicker view, int year,
					int monthOfYear, int dayOfMonth) {
				DatePickActivity.this.year = year;
				DatePickActivity.this.month = monthOfYear;
				DatePickActivity.this.day = dayOfMonth;

			}
		});

		Intent intent = getIntent();
		a = intent.getIntExtra("values", 0);

	}

	// 按钮 "shezhi" 的监听
	class ShizhiListener implements OnClickListener {

		public void onClick(View v) {
			// TODO Auto-generated method stub

			Bundle bundle = new Bundle();

			bundle.putInt("values", a);
			bundle.putInt("year", year);
			bundle.putInt("month", month + 1);
			bundle.putInt("day", day);
			Intent mIntent = new Intent();

			mIntent.putExtras(bundle);

			setResult(RESULT_OK, mIntent);

			finish();

		}

	}

	// 按钮 "quxiao" 的监听
	class QuxiaoListener implements OnClickListener {

		public void onClick(View v) {
			// TODO Auto-generated method stub

			Bundle bundle = new Bundle();

			Intent mIntent = new Intent();

			mIntent.putExtras(bundle);

			setResult(RESULT_CANCELED, mIntent);

			finish();

		}

	}

}
