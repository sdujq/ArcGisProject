package org.sdu.view.taskinput;

import java.util.Calendar;
import org.sdu.gis.R;
import com.tgb.lk.ahibernate.annotation.Id;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class TaskInputActivity extends Activity {
	public Button bt_zhiding, bt_qingkong, bt_xuanzequyu, bt_xunjianquyu,
			bt_baocunrenwu, bt_faburenwu, bt_kaishiriqi, bt_jieshuriqi;
	public TextView tv_luduanming, tv_renwuleibie, tv_xunjianrenyuan,
			tv_renwuneirong, tv_qx_kaishishijian, tv_qx_jiezhishijian,
			tv_xunjianzhouqi, tv_gerenwu, tv_beizhu, tv_zhidingren,
			tv_zhidingshijian, tv_kaishiriqi, tv_jiezhiriqi;
	public EditText et_luduanming, et_xunjianrenyuan, et_renwuneirong,
			et_beizhu;

	public Spinner sp_selectTask, sp_kaishishijian, sp_jiezhishijian;

	private int year;
	private int month;
	private int day;
	private int check = 0, count = 0, x = 0;
	private int REQUEST_CODE = 0;

	@Override
	public void onCreate(Bundle saved) {
		super.onCreate(saved);
		setContentView(R.layout.task_input);

		bt_zhiding = (Button) findViewById(R.id.t_button_zhiding);
		bt_zhiding.setOnClickListener(new ZhidingListener());

		bt_qingkong = (Button) findViewById(R.id.t_button_qingkong);
		bt_qingkong.setOnClickListener(new QingkongListener());

		bt_xuanzequyu = (Button) findViewById(R.id.t_button_xuanzequyu);
		bt_xuanzequyu.setOnClickListener(new XuanzequyuListener());

		bt_xunjianquyu = (Button) findViewById(R.id.t_button_xunjianquyu);
		bt_xunjianquyu.setOnClickListener(new XunjianquyuListener());

		bt_baocunrenwu = (Button) findViewById(R.id.t_button_baocunrenwu);
		bt_baocunrenwu.setOnClickListener(new BaocunrenwuListener());

		bt_faburenwu = (Button) findViewById(R.id.t_button_faburenwu);
		bt_faburenwu.setOnClickListener(new FaburenwuListener());

		bt_kaishiriqi = (Button) findViewById(R.id.t_bt_kaishiriqi);
		bt_kaishiriqi.setOnClickListener(new KaishirijiListener());

		bt_jieshuriqi = (Button) findViewById(R.id.t_bt_jieshuriqi);
		bt_jieshuriqi.setOnClickListener(new JieshuriqiListener());

		tv_luduanming = (TextView) findViewById(R.id.t_nameOfRoad);
		tv_renwuleibie = (TextView) findViewById(R.id.t_renwuleibie);
		tv_xunjianrenyuan = (TextView) findViewById(R.id.t_xunjianrenyuan);
		tv_renwuneirong = (TextView) findViewById(R.id.t_renwuneirong);
		tv_qx_kaishishijian = (TextView) findViewById(R.id.t_Qx_kaishi);
		// tv_kaishiriqi = (TextView) findViewById(R.id.t_kaishiriqi);
		tv_qx_jiezhishijian = (TextView) findViewById(R.id.t_Qx_jiezhi);
		// tv_jiezhiriqi = (TextView) findViewById(R.id.t_jiezhiriqi);
		tv_xunjianzhouqi = (TextView) findViewById(R.id.t_xunjianzhouqi);
		tv_gerenwu = (TextView) findViewById(R.id.t_gerenwu);
		tv_beizhu = (TextView) findViewById(R.id.t_beizhu);
		tv_zhidingren = (TextView) findViewById(R.id.t_zhidingren);
		tv_zhidingshijian = (TextView) findViewById(R.id.t_zhidingshijian);

		sp_selectTask = (Spinner) findViewById(R.id.t_spinner_selectTask);
		sp_kaishishijian = (Spinner) findViewById(R.id.t_spinner_kaishishijian);
		sp_jiezhishijian = (Spinner) findViewById(R.id.t_spinner_jiezhishijian);

		// tv_kaishiriqi.setText(R.string.kaishiriqi);
		bt_kaishiriqi.setText(R.string.kaishiriqi);
		bt_jieshuriqi.setText(R.string.jiezhiriqi);
		// tv_jiezhiriqi.setText(R.string.jiezhiriqi);
		tv_zhidingshijian.setText(R.string.zhidingren);
		tv_zhidingren.setText(R.string.zhidingshijian);

	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (requestCode == REQUEST_CODE) {

			if (resultCode == RESULT_CANCELED) {

			} else if (resultCode == RESULT_OK) {

				String txt = "";

				Bundle extras = data.getExtras();

				if (extras != null) {

					int a = extras.getInt("values");
					year = extras.getInt("year");
					month = extras.getInt("month");
					day = extras.getInt("day");

					String Smonth = "", Sday = "";
					if (month < 10) {
						Smonth = "0" + month;
					} else {
						Smonth = month + "";
					}

					if (day < 10) {
						Sday = "0" + day;
					} else {
						Sday = day + "";
					}

					if (a == 1) {

						bt_kaishiriqi.setText(year + "-" + Smonth + "-" + Sday);

					} else if (a == 2) {
						bt_jieshuriqi.setText(year + "-" + Smonth + "-" + Sday);

					}

				}

			}

		}

	}

	// 按钮 “kaishiriqi” 的监听
	class KaishirijiListener implements OnClickListener {

		public void onClick(View v) {
			// TODO Auto-generated method stub

			Intent intent1 = new Intent(TaskInputActivity.this,
					DatePickActivity.class);

			intent1.putExtra("values", 1);

			startActivityForResult(intent1, REQUEST_CODE);

		}

	}

	// 按钮 "jieshuriqi" 的监听
	class JieshuriqiListener implements OnClickListener {

		public void onClick(View v) {
			// TODO Auto-generated method stub

			Intent intent = new Intent(TaskInputActivity.this,
					DatePickActivity.class);
			intent.putExtra("values", 2);
			startActivityForResult(intent, REQUEST_CODE);

		}

	}

	// 按钮 制定 的监听
	class ZhidingListener implements OnClickListener {

		public void onClick(View v) {
			// TODO Auto-generated method stub
			AnimationSet animationSet = new AnimationSet(true);
			ScaleAnimation scaleAnimation = new ScaleAnimation(1, 1.3f, 1,
					1.3f, Animation.RELATIVE_TO_SELF, 0.5f,
					Animation.RELATIVE_TO_SELF, 0.5f);
			animationSet.addAnimation(scaleAnimation);
			animationSet.setFillBefore(true);
			animationSet.setDuration(200);
			bt_zhiding.startAnimation(animationSet);

		}

	}

	// 按钮 清空 的监听
	class QingkongListener implements OnClickListener {

		public void onClick(View v) {
			// TODO Auto-generated method stub
			AnimationSet animationSet = new AnimationSet(true);
			ScaleAnimation scaleAnimation = new ScaleAnimation(1, 1.3f, 1,
					1.3f, Animation.RELATIVE_TO_SELF, 0.5f,
					Animation.RELATIVE_TO_SELF, 0.5f);
			animationSet.addAnimation(scaleAnimation);
			animationSet.setFillBefore(true);
			animationSet.setDuration(200);
			bt_qingkong.startAnimation(animationSet);

		}

	}

	// 按钮 选择区域 的监听
	class XuanzequyuListener implements OnClickListener {

		public void onClick(View v) {
			// TODO Auto-generated method stub
			AnimationSet animationSet = new AnimationSet(true);
			ScaleAnimation scaleAnimation = new ScaleAnimation(1, 1.3f, 1,
					1.3f, Animation.RELATIVE_TO_SELF, 0.5f,
					Animation.RELATIVE_TO_SELF, 0.5f);
			animationSet.addAnimation(scaleAnimation);
			animationSet.setFillBefore(true);
			animationSet.setDuration(200);
			bt_xuanzequyu.startAnimation(animationSet);

		}

	}

	// 按钮 巡检区域 的监听
	class XunjianquyuListener implements OnClickListener {

		public void onClick(View v) {
			// TODO Auto-generated method stub
			AnimationSet animationSet = new AnimationSet(true);
			ScaleAnimation scaleAnimation = new ScaleAnimation(1, 1.3f, 1,
					1.3f, Animation.RELATIVE_TO_SELF, 0.5f,
					Animation.RELATIVE_TO_SELF, 0.5f);
			animationSet.addAnimation(scaleAnimation);
			animationSet.setFillBefore(true);
			animationSet.setDuration(200);
			bt_xunjianquyu.startAnimation(animationSet);

		}

	}

	// 按钮 保存任务 的监听
	class BaocunrenwuListener implements OnClickListener {

		public void onClick(View v) {
			// TODO Auto-generated method stub
			AnimationSet animationSet = new AnimationSet(true);
			ScaleAnimation scaleAnimation = new ScaleAnimation(1, 1.3f, 1,
					1.3f, Animation.RELATIVE_TO_SELF, 0.5f,
					Animation.RELATIVE_TO_SELF, 0.5f);
			animationSet.addAnimation(scaleAnimation);
			animationSet.setFillBefore(true);
			animationSet.setDuration(200);
			bt_baocunrenwu.startAnimation(animationSet);

		}

	}

	// 按钮 发布任务 的监听
	class FaburenwuListener implements OnClickListener {

		public void onClick(View v) {
			// TODO Auto-generated method stub
			AnimationSet animationSet = new AnimationSet(true);
			ScaleAnimation scaleAnimation = new ScaleAnimation(1, 1.3f, 1,
					1.3f, Animation.RELATIVE_TO_SELF, 0.5f,
					Animation.RELATIVE_TO_SELF, 0.5f);
			animationSet.addAnimation(scaleAnimation);
			animationSet.setFillBefore(true);
			animationSet.setDuration(200);
			bt_faburenwu.startAnimation(animationSet);

		}

	}

}
