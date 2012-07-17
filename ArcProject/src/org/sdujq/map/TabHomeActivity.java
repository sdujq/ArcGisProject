package org.sdujq.map;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.sdu.bmputil.PhotoDrawerTemp;
import org.sdu.dao.UserDao;
import org.sdu.dao.ValuesDao;
import org.sdu.dbaction.Action;
import org.sdu.gis.R;
import org.sdu.view.buginput.BugInputActivity;
import org.sdu.view.bugshow.BugMhActivity;
import org.sdu.view.taskinput.TaskInputActivity;
import org.sdu.view.taskshow.TaskShowMhActivity;
import org.sdu.view.usermanager.AccountActivity;

import android.app.LocalActivityManager;
import android.app.TabActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;

/**
 * 框架类
 * 
 * @author shadow
 * 
 */
public class TabHomeActivity extends TabActivity {
	public static TabHomeActivity home;
	private TabHost tabHost;
	public ViewPager mPager;
	private List<View> listViews;
	private LocalActivityManager manager = null;
	public static MyPagerAdapter mpAdapter;

	private static final String n1 = "任务制定";
	private static final String n2 = "任务查看";
	private static final String n3 = "故障提交";
	private static final String n4 = "故障查看";
	private static final String n5 = "账户管理";
	int icon1 = R.drawable.inputtask;
	int icon2 = R.drawable.shwotask;
	int icon3 = R.drawable.inputerror;
	int icon4 = R.drawable.showbug;
	int icon5 = R.drawable.account;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		// getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		// WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.tabhost);
		Intent it=getIntent();
		int uid=it.getIntExtra("uid", -2);
		if(uid!=-2){
			Action.currentUser=(new UserDao(this)).get(Integer.parseInt(new ValuesDao(this).get(1).toString()));
		}
		tabHost = this.getTabHost();
		manager = new LocalActivityManager(this, true);
		manager.dispatchCreate(savedInstanceState);
		listViews = new ArrayList<View>();

		Intent intent = new Intent(this, TaskInputActivity.class);
		Intent intent2 = new Intent(this, TaskShowMhActivity.class);
		Intent intent3 = new Intent(this, BugInputActivity.class);
		Intent intent4 = new Intent(this, BugMhActivity.class);
		Intent intent5 = new Intent(this, AccountActivity.class);
		listViews.add(getView("1", intent));
		listViews.add(getView("2", intent2));
		listViews.add(getView("3", intent3));
		listViews.add(getView("4", intent4));
		listViews.add(getView("5", intent5));

		View view1 = View.inflate(TabHomeActivity.this, R.layout.tab, null);
		((ImageView) view1.findViewById(R.id.tab_imageview_icon))
				.setImageResource(icon1);
		((TextView) view1.findViewById(R.id.tab_textview_title)).setText(n1);

		TabHost.TabSpec spec1 = tabHost.newTabSpec(n1).setIndicator(view1)
				.setContent(intent5);
		tabHost.addTab(spec1);

		View view2 = View.inflate(TabHomeActivity.this, R.layout.tab, null);
		((ImageView) view2.findViewById(R.id.tab_imageview_icon))
				.setImageResource(icon2);
		((TextView) view2.findViewById(R.id.tab_textview_title)).setText(n2);

		TabHost.TabSpec spec5 = tabHost.newTabSpec(n2).setIndicator(view2)
				.setContent(intent5);
		tabHost.addTab(spec5);

		View view3 = View.inflate(TabHomeActivity.this, R.layout.tab, null);
		((ImageView) view3.findViewById(R.id.tab_imageview_icon))
				.setImageResource(icon3);
		((TextView) view3.findViewById(R.id.tab_textview_title)).setText(n3);
		TabHost.TabSpec spec2 = tabHost.newTabSpec(n3).setIndicator(view3)
				.setContent(intent5);
		tabHost.addTab(spec2);

		View view4 = View.inflate(TabHomeActivity.this, R.layout.tab, null);
		((ImageView) view4.findViewById(R.id.tab_imageview_icon))
				.setImageResource(icon4);
		((TextView) view4.findViewById(R.id.tab_textview_title)).setText(n4);

		TabHost.TabSpec spec3 = tabHost.newTabSpec(n4).setIndicator(view4)
				.setContent(intent5);
		tabHost.addTab(spec3);

		View view5 = View.inflate(TabHomeActivity.this, R.layout.tab, null);
		((ImageView) view5.findViewById(R.id.tab_imageview_icon))
				.setImageResource(icon5);
		((TextView) view5.findViewById(R.id.tab_textview_title)).setText(n5);

		TabHost.TabSpec spec4 = tabHost.newTabSpec(n5).setIndicator(view5)
				.setContent(intent5);
		tabHost.addTab(spec4);

		InitViewPager();
		tabHost.setOnTabChangedListener(new OnTabChangeListener() {

			@Override
			public void onTabChanged(String tabId) {
				if (tabId.equals(n1)) {
					mPager.setCurrentItem(0);
				} else if (tabId.equals(n2)) {
					mPager.setCurrentItem(1);

				} else if (tabId.equals(n3)) {
					mPager.setCurrentItem(2);

				} else if (tabId.equals(n4)) {
					mPager.setCurrentItem(3);

				} else if (tabId.equals(n5)) {
					mPager.setCurrentItem(4);
				}
				mpAdapter.notifyDataSetChanged();
			}
		});
		home = this;
		TextView tv1=(TextView) findViewById(R.id.btleft);
		tv1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				TabHomeActivity.this.finish();
				overridePendingTransition(R.anim.scale_rotate,
						R.anim.my_alpha_action);
			}
		});
		TextView tv2=(TextView)findViewById(R.id.btright);
		tv2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/sdujq/ArcGisProject"));
				it.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
				TabHomeActivity.this.startActivity(it);
			}
		});
	}

	/**
	 * 初始化ViewPager
	 */
	private void InitViewPager() {
		mpAdapter = new MyPagerAdapter(listViews);
		mPager = (ViewPager) findViewById(R.id.vPager);

		mPager.setAdapter(mpAdapter);
		mPager.setCurrentItem(0);
		mPager.setOnPageChangeListener(new MyOnPageChangeListener());
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == InputActivity.TAKE_PHOTO_REQUEST_CODE) {

			if (resultCode == RESULT_OK) {
				Uri uri = Uri.fromFile(new File("/mnt/sdcard/temp100.jpg"));
				if (uri != null) {
					final Intent intent1 = new Intent(
							"com.android.camera.action.CROP");
					intent1.setDataAndType(uri, "image/*");
					intent1.putExtra("crop", "true");
					intent1.putExtra("aspectX", 1);
					intent1.putExtra("aspectY", 1);
					intent1.putExtra("outputX", 300);
					intent1.putExtra("outputY", 300);
					intent1.putExtra("return-data", true);
					intent1.putExtra("output",
							Uri.fromFile(new File("/mnt/sdcard/temp100.jpg")));
					intent1.putExtra("outputFormat", "JPEG");
					startActivityForResult(intent1,
							Photo.CUT_PHOTO_REQUEST_CODE);
				}
			}
		} else if (requestCode == Photo.CUT_PHOTO_REQUEST_CODE) {
			if (resultCode == RESULT_OK && data != null) {
				((BugInputActivity) manager.getActivity("3")).onActivityResult(
						requestCode, resultCode, data);
			}
		} else if (requestCode == PhotoDrawerTemp.DrawRquest
				&& resultCode == RESULT_OK) {
			((BugInputActivity) manager.getActivity("3")).onActivityResult(
					requestCode, resultCode, data);
		} else if (resultCode == RESULT_OK
				&& requestCode == TaskInputActivity.REQUEST_CODE) {
			((TaskInputActivity) manager.getActivity("1")).onActivityResult(
					requestCode, resultCode, data);
		} else if (requestCode == 1 && resultCode == RESULT_OK) {
			((TaskInputActivity) manager.getActivity("1")).onActivityResult(
					requestCode, resultCode, data);
		}
		Log.e("qq", "request" + requestCode);
		mpAdapter.notifyDataSetChanged();
	}

	/**
	 * 页卡切换监听
	 */
	public class MyOnPageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageSelected(int arg0) {
			tabHost.setCurrentTab(arg0);
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}
	}

	private View getView(String id, Intent intent) {
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		return manager.startActivity(id, intent).getDecorView();
	}

	public class MyPagerAdapter extends PagerAdapter {
		public List<View> mListViews;

		public MyPagerAdapter(List<View> mListViews) {
			this.mListViews = mListViews;
		}


		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPager) arg0).removeView(mListViews.get(arg1));
		}

		@Override
		public void finishUpdate(View arg0) {
		}

		@Override
		public int getCount() {
			return mListViews.size();
		}

		@Override
		public Object instantiateItem(View arg0, int arg1) {
			((ViewPager) arg0).addView(mListViews.get(arg1), 0);
			return mListViews.get(arg1);
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == (arg1);
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {
		}

		@Override
		public Parcelable saveState() {
			return null;
		}

		@Override
		public void startUpdate(View arg0) {
		}
	}
}