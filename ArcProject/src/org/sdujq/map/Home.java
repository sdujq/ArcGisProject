package org.sdujq.map;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.sdu.db.DBHelper;
import org.sdu.gis.R;

import android.app.Activity;
import android.app.LocalActivityManager;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class Home extends Activity {
	private Button attention;
	private Button home;
	private Button collect;
	private Button photo;
	private Button person;
	ViewPager viewPager;
	private ImageView cycle;
	private Button title;
	// private Button exit;
	private LocalActivityManager manager = null;
	private List<View> listViews;
	private Animation animation;
	private int positon_x = 0;
	private int c_width;

	public static Home home_con;
	SQLiteDatabase db;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		DBHelper dbhelper = new DBHelper(this);
		db = dbhelper.getWritableDatabase();
		
		home_con = Home.this;

		manager = new LocalActivityManager(this, true);
		manager.dispatchCreate(savedInstanceState);
		refreshlst();
		/*listViews = new ArrayList<View>();
		Intent intent = new Intent(this, InputActivity.class);
		listViews.add(getView("Home", intent));
		Intent intent2 = new Intent(this, RecordListActivity.class);
		listViews.add(getView("Attention", intent2));
		Intent intent3 = new Intent(this, WhiteActivity.class);
		listViews.add(getView("Map", intent3));
		Intent intent4 = new Intent(this, WhiteActivity.class);
		listViews.add(getView("Photo", intent4));
		Intent intent5 = new Intent(this, WhiteActivity.class);
		listViews.add(getView("Person", intent5));*/

		viewPager = (ViewPager) findViewById(R.id.guidePages);
		viewPager.setAdapter(new GuidePageAdapter());
		viewPager.setOnPageChangeListener(new GuidePageChangeListener());

		initial_view();
	}

	public void refreshlst(){
		manager.removeAllActivities();
		listViews = new ArrayList<View>();
		Intent intent = new Intent(this, InputActivity.class);
		listViews.add(getView("Home", intent));
		Intent intent2 = new Intent(this, RecordListActivity.class);
		listViews.add(getView("Attention", intent2));
		Intent intent3 = new Intent(this, WhiteActivity.class);
		listViews.add(getView("Map", intent3));
		Intent intent4 = new Intent(this, WhiteActivity.class);
		listViews.add(getView("Photo", intent4));
		Intent intent5 = new Intent(this, WhiteActivity.class);
		listViews.add(getView("Person", intent5));
	}
	class GuidePageAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return listViews.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public int getItemPosition(Object object) {
			return super.getItemPosition(object);
		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPager) arg0).removeView(listViews.get(arg1));
		}

		@Override
		public Object instantiateItem(View arg0, int arg1) {
			((ViewPager) arg0).addView(listViews.get(arg1));
			return listViews.get(arg1);
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

		@Override
		public void finishUpdate(View arg0) {

		}
	}

	/**
	 * 瀹炵幇OnPageChangeListener
	 * 
	 * @author yyt&&ls
	 * 
	 */
	class GuidePageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		@Override
		public void onPageSelected(int arg0) {

			if (arg0 == 0) {
				animation = new TranslateAnimation(positon_x, 0, 0, 0);
				title.setBackgroundResource(R.drawable.title_home);
				positon_x = 0;
				cycle.setBackgroundResource(R.drawable.cycle_1);
			} else if (arg0 == 1) {
				animation = new TranslateAnimation(positon_x, c_width, 0, 0);
				title.setBackgroundResource(R.drawable.title_home);
				positon_x = c_width;
				cycle.setBackgroundResource(R.drawable.cycle_2);
			} else if (arg0 == 2) {
				animation = new TranslateAnimation(positon_x, c_width * 2, 0, 0);
				title.setBackgroundResource(R.drawable.title_home);
				positon_x = c_width * 2;
				cycle.setBackgroundResource(R.drawable.cycle_3);
			} else if (arg0 == 3) {
				animation = new TranslateAnimation(positon_x, c_width * 3, 0, 0);
				title.setBackgroundResource(R.drawable.title_home);
				cycle.setBackgroundResource(R.drawable.cycle_4);
				positon_x = c_width * 3;
			} else if (arg0 == 4) {
				animation = new TranslateAnimation(positon_x, c_width * 4, 0, 0);
				title.setBackgroundResource(R.drawable.title_home);
				cycle.setBackgroundResource(R.drawable.cycle_1);
				positon_x = c_width * 4;
			}
			animation.setFillAfter(true);
			animation.setDuration(200);
			cycle.startAnimation(animation);
		}
	}

	/**
	 * 灞忓箷涓嬫柟浜斾釜鎸夐挳鐨勫唴閮ㄧ洃鍚被
	 * 
	 * @author yyt&&ls
	 * 
	 */
	class ButtonListener implements OnClickListener {
		int item = 0;

		public ButtonListener(int item) {
			this.item = item;
		}

		@Override
		public void onClick(View v) {
			viewPager.setCurrentItem(this.item);
		}

	}

	/***
	 * 鑾峰彇鐩稿簲椤甸潰鐨刟ctivity
	 * 
	 * @param id
	 *            瀵瑰簲Id锟�
	 * @param intent
	 *            Intent瀵硅薄
	 * @return View
	 */
	private View getView(String id, Intent intent) {
		return manager.startActivity(id, intent).getDecorView();
	}

	/**
	 * 浠庡垵濮嬪寲鐣岄潰鎺т欢
	 */
	private void initial_view() {
		cycle = (ImageView) findViewById(R.id.cycle);
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenW = dm.widthPixels;// 鑾峰彇鍒嗚鲸鐜囧锟�
		c_width = screenW / 5;

		attention = (Button) findViewById(R.id.attention);
		home = (Button) findViewById(R.id.home);
		collect = (Button) findViewById(R.id.collect);
		photo = (Button) findViewById(R.id.photo);
		person = (Button) findViewById(R.id.person);

		home.setOnClickListener(new ButtonListener(0));
		attention.setOnClickListener(new ButtonListener(1));
		collect.setOnClickListener(new ButtonListener(2));
		photo.setOnClickListener(new ButtonListener(3));
		person.setOnClickListener(new ButtonListener(4));

		title = (Button) findViewById(R.id.title);

	}

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
				
			}
		}
	}

}