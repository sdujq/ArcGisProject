package org.sdujq.frame;

import java.util.ArrayList;

import org.sdu.gis.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 预订Activity
 * 
 * @author shadow
 * 
 */
public abstract class FrameActivity extends Activity {
	public static final int msg_showDialog = 9001;
	public static final int msg_dismissDialog = 9002;
	public static final int msg_finish = 9003;
	public FrameInfo info;
	//TextView left;
	//TextView right;
	TopMenu menu;
	public AlertDialog alert;
	public int screenWidth;
	// 当前ViewPager索引
	private int pagerIndex = 0;
	private ArrayList<View> menuViews = null;

	public ViewPager viewPager = null;
	// 左右导航图片按钮
	private ImageView imagePrevious = null;
	private ImageView imageNext = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		loadInfo();
		init();
	}

	// init FrameInfo here
	public abstract void loadInfo();

	public Handler superhandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			int w = msg.what;
			if (w == msg_showDialog) {
				showAndroidProgress("正在同步", "同步中请稍等");
			} else if (w == msg_dismissDialog) {
				clearProgress();
			}
		}
	};

	public AlertDialog showAndroidProgress(final String title,
			final String message) {
		if (alert == null || !alert.isShowing()) {
			Runnable showProgress = new Runnable() {
				public void run() {
					alert = ProgressDialog.show(FrameActivity.this, title,
							message);
				}
			};
			runOnUiThread(showProgress);
		}
		return alert;
	}

	public void clearProgress() {
		if (alert != null) {
			if (alert.isShowing()) {
				alert.dismiss();
			}
		}
	}

	public void init() {

		if (info.getViews().length == 1 && info.getViews()[0].length == 1) {
			setContentView(R.layout.showwithnotitle);
			info.getViews()[0][0].show();
		} else {
			setContentView(R.layout.show);

			DisplayMetrics dm = new DisplayMetrics();
			getWindowManager().getDefaultDisplay().getMetrics(dm);
			screenWidth = dm.widthPixels;
			menuViews = new ArrayList<View>();
			menu = new TopMenu(this);
			if (info.getViews() != null) {
				for (int i = 0; i < info.getViews().length; i++) {
					menuViews.add(menu.getSlideMenuLinerLayout(
							info.getViews()[i], screenWidth));
				}
			}
			imagePrevious = (ImageView) findViewById(R.id.ivPreviousButton);
			imageNext = (ImageView) findViewById(R.id.ivNextButton);
			imagePrevious
					.setOnClickListener(new ImagePreviousOnclickListener());
			imageNext.setOnClickListener(new ImageNextOnclickListener());
			if (menuViews.size() > 1) {
				imageNext.setVisibility(View.VISIBLE);
			}
			viewPager = (ViewPager) this.findViewById(R.id.slideMenu);
			viewPager.setAdapter(new SlideMenuAdapter());
			viewPager.setOnPageChangeListener(new SlideMenuChangeListener());
		}

	//	((TextView) findViewById(R.id.title)).setText(info.getTitle());
	/*	left = (TextView) findViewById(R.id.btleft);
		right = (TextView) findViewById(R.id.btright);
		right.setText(info.getRightText());
		left.setText(info.getLeftText());
		left.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (AbsShow.currentView != null) {
					if (AbsShow.currentView.faView != null) {
						AbsShow.currentView.faView.show();
					} else {
						FrameActivity.this.finish();
					}
				}
			}
		});
		if (info.getRightListener() != null) {
			right.setOnClickListener(info.getRightListener());
		} else {
			right.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					startActivity(new Intent(FrameActivity.this,
							NewAccountActivity.class));
				}
			});
		}*/
	}

	// 切换menu
	public void changeTo(int index, int num) {
		if (viewPager != null)
			viewPager.setCurrentItem(index);
		if (info.getViews()[index][num] != null) {
			info.getViews()[index][num].show();
		}
	}

	// 右导航图片按钮事件
	class ImageNextOnclickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			pagerIndex++;
			viewPager.setCurrentItem(pagerIndex);
		}
	}

	// 左导航图片按钮事件
	class ImagePreviousOnclickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			pagerIndex--;
			viewPager.setCurrentItem(pagerIndex);
		}
	}

	class SlideMenuChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		@Override
		public void onPageSelected(int arg0) {
			int pageCount = menuViews.size() - 1;
			pagerIndex = arg0;

			// 显示右边导航图片
			if (arg0 >= 0 && arg0 < pageCount) {
				imageNext.setVisibility(View.VISIBLE);
			} else {
				imageNext.setVisibility(View.INVISIBLE);
			}

			// 显示左边导航图片
			if (arg0 > 0 && arg0 <= pageCount) {
				imagePrevious.setVisibility(View.VISIBLE);
			} else {
				imagePrevious.setVisibility(View.INVISIBLE);
			}
		}
	}

	class SlideMenuAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return menuViews.size();
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
			((ViewPager) arg0).removeView(menuViews.get(arg1));
		}

		@Override
		public Object instantiateItem(View arg0, int arg1) {
			((ViewPager) arg0).addView(menuViews.get(arg1));

			return menuViews.get(arg1);
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
}
