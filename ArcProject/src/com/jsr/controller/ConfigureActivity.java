package com.jsr.controller;

import org.sdu.gis.R;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.jsr.model.DataForecastService;
import com.jsr.model.DataWidget;
import com.jsr.model.WeatherProvider.WeatherWidgets;

public class ConfigureActivity extends Activity implements View.OnClickListener{
    /** Called when the activity is first created. */
	private EditText editCity, editUpdatetime;
	private Button btnSave;
	private String city;
	private int updatetime;

    private int widgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
      System.out.println("ConfigureActivity is running");
        
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);        
        setContentView(R.layout.configuremain);
        
        editCity = (EditText)findViewById(R.id.editCity);
        editUpdatetime = (EditText)findViewById(R.id.editUpdatetime);
        btnSave = (Button)findViewById(R.id.btnSave);
        
        btnSave.setOnClickListener(this);        
        
        // Read the appWidgetId to configure from the incoming intent
        widgetId = getIntent().getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
        setConfigureResult(Activity.RESULT_CANCELED);
        if (widgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish();
            return;
        }
        
        if (savedInstanceState != null){
        	city = savedInstanceState.getString("city");
        	updatetime = savedInstanceState.getInt("updatetime");
        	
        	editCity.setText(city);
        	editUpdatetime.setText(updatetime);
        }
    }
    
    @Override
    protected void onSaveInstanceState(Bundle outState) {
    	// TODO Auto-generated method stub
    	super.onSaveInstanceState(outState);
    	
    	outState.putString("city", city);
    	outState.putInt("updatetime", updatetime);    	

		System.out.println("onSaveInstanceState!");
    }

	public void onClick(View v) {
		// TODO Auto-generated method stub
	System.out.println("configure save!");
		switch (v.getId()) {
		case R.id.btnSave: {
			city = editCity.getText().toString();
			updatetime = Integer.parseInt(editUpdatetime.getText().toString());
			
			ContentValues values = new ContentValues();
			values.put(BaseColumns._ID, widgetId);
			values.put(DataWidget.POSTCODE, city);
			values.put(DataWidget.UPDATEMILIS, updatetime);
			values.put(DataWidget.LASTUPDATETIME, -1);
			values.put(DataWidget.ISCONFIGURED, 1);
			
			ContentResolver resolver = getContentResolver();
			resolver.insert(WeatherWidgets.CONTENT_URI, values);
			
			// start service
		    System.out.println("start Service!");
			DataForecastService.addWidgetIDs(new int[]{widgetId});
			startService(new Intent(this, DataForecastService.class));
			
			setConfigureResult(Activity.RESULT_OK);
            finish();
            break;
			
		}
		
		}
	}
	
	/**
     * Convenience method to always include {@link #widgetId} when setting
     * the result {@link Intent}.
     */
    public void setConfigureResult(int resultCode) {
        final Intent data = new Intent();
        data.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
        setResult(resultCode, data);
    }
}