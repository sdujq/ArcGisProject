package org.sdu.view.bugshow;

import java.util.Calendar;

import org.sdu.gis.R;

import android.R.integer;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

public class BugShowActivity extends Activity{

	private Button mbtn1,mbtn2,mbtns;
	private TextView mtv1,mtv2;
	private int mYear,mMonth,mDay;
	static final int DIALOG1=0,DIALOG2=1;
	public void onCreate(Bundle saved){
		super.onCreate(saved);
		setContentView(R.layout.bugshow);
		
		mtv1=(TextView) findViewById(R.id.tv1);
		mtv2=(TextView) findViewById(R.id.tv2);
		mbtn1=(Button) findViewById(R.id.btn1);
		mbtn2=(Button) findViewById(R.id.btn2);
		mbtn1.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				showDialog(DIALOG1);
				
			}
		});
		mbtn2.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				showDialog(DIALOG2);
				
			}
		});
		final Calendar c=Calendar.getInstance();
		mYear=c.get(Calendar.YEAR);
		mMonth=c.get(Calendar.MONTH);
		mDay=c.get(Calendar.DAY_OF_MONTH);
		
		mbtns=(Button) findViewById(R.id.btns);
		mbtns.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				
				
			}
		});
	}
	protected Dialog onCreateDialog(int id)
	{
		switch (id)
		{
		case DIALOG1:
			return new DatePickerDialog(this,listener1,mYear,mMonth,mDay);
		case DIALOG2:
			return new DatePickerDialog(this,listener2,mYear,mMonth,mDay);	
		}
		return null;
	}
	private DatePickerDialog.OnDateSetListener listener1=new DatePickerDialog.OnDateSetListener()
	{
		
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
		{
			mYear=year;
			mMonth=monthOfYear;
			mDay=dayOfMonth;
			mtv1.setText(new StringBuilder().append(mtv1.getText()).append(mYear).append("-").append(mMonth+1).append("-").append(mDay));
			
		}
	};
	private DatePickerDialog.OnDateSetListener listener2=new DatePickerDialog.OnDateSetListener()
	{
		
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
		{
			mYear=year;
			mMonth=monthOfYear;
			mDay=dayOfMonth;
			mtv2.setText(new StringBuilder().append(mtv2.getText()).append(mYear).append("-").append(mMonth+1).append("-").append(mDay));
			
		}
	};
}
