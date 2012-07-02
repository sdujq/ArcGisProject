package org.sdu.view.taskinput;

import org.sdu.gis.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;

public class DatePickActivity extends Activity {
	
	public Button bt_shezhi,bt_quxiao;
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.datepick);
		bt_shezhi=(Button)findViewById(R.id.d_bt_shezhi);
		bt_quxiao=(Button)findViewById(R.id.d_bt_quxiao);
		
		
	}
	
}
