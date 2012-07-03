package org.sdujq.map;

import org.sdu.gis.R;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

/**
 * 框架类
 * 
 * @author shadow
 * 
 */
public class TabHomeActivity extends TabActivity {
	private TabHost tabHost;
	private static final String n1 = "任务制定";
	private static final String n2 = "任务查看";
	private static final String n3 = "故障提交";
	private static final String n4 = "故障查看";
	private static final String n5 = "账户管理";
	int icon1=R.drawable.inputtask;
	int icon2=R.drawable.shwotask;
	int icon3=R.drawable.inputerror;
	int icon4=R.drawable.showbug;
	int icon5=R.drawable.account;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		// getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		// WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.tabhost);

		tabHost = this.getTabHost();

		View view1 = View.inflate(TabHomeActivity.this, R.layout.tab, null);
		((ImageView) view1.findViewById(R.id.tab_imageview_icon))
				.setImageResource(icon1);
		((TextView) view1.findViewById(R.id.tab_textview_title)).setText(n1);

		TabHost.TabSpec spec1 = tabHost.newTabSpec(n1).setIndicator(view1)
				.setContent(new Intent(this, WhiteActivity.class));
		tabHost.addTab(spec1);

		View view2 = View.inflate(TabHomeActivity.this, R.layout.tab, null);
		((ImageView) view2.findViewById(R.id.tab_imageview_icon))
				.setImageResource(icon2);
		((TextView) view2.findViewById(R.id.tab_textview_title)).setText(n2);

		TabHost.TabSpec spec5 = tabHost.newTabSpec(n2).setIndicator(view2)
				.setContent(new Intent(this, WhiteActivity.class));
		tabHost.addTab(spec5);
		
		View view3 = View.inflate(TabHomeActivity.this, R.layout.tab, null);
		((ImageView) view3.findViewById(R.id.tab_imageview_icon))
				.setImageResource(icon3);
		((TextView) view3.findViewById(R.id.tab_textview_title))
				.setText(n3);
		TabHost.TabSpec spec2 = tabHost.newTabSpec(n3).setIndicator(view3)
				.setContent(new Intent(this, WhiteActivity.class));
		tabHost.addTab(spec2);
		
		View view4 = View.inflate(TabHomeActivity.this, R.layout.tab, null);
		((ImageView) view4.findViewById(R.id.tab_imageview_icon))
				.setImageResource(icon4);
		((TextView) view4.findViewById(R.id.tab_textview_title)).setText(n4);

		TabHost.TabSpec spec3 = tabHost.newTabSpec(n4).setIndicator(view4)
				.setContent(new Intent(this, WhiteActivity.class));
		tabHost.addTab(spec3);

		View view5 = View.inflate(TabHomeActivity.this, R.layout.tab, null);
		((ImageView) view5.findViewById(R.id.tab_imageview_icon))
				.setImageResource(icon5);
		((TextView) view5.findViewById(R.id.tab_textview_title))
				.setText(n5);

		TabHost.TabSpec spec4 = tabHost.newTabSpec(n5).setIndicator(view5)
				.setContent(new Intent(this, WhiteActivity.class));
		tabHost.addTab(spec4);

	}
}