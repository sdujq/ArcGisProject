package org.sdu.view.taskinput;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.sdu.dao.RoadLineDao;
import org.sdu.dao.TaskTypeDao;
import org.sdu.dao.UserDao;
import org.sdu.db.DBHelper;
import org.sdu.dbaction.Action;
import org.sdu.dbaction.TaskAction;
import org.sdu.gis.R;
import org.sdu.pojo.RoadLine;
import org.sdu.pojo.Task;
import org.sdu.pojo.TaskType;
import org.sdu.pojo.User;
import org.sdu.view.taskshow.TaskShowMhActivity;
import org.sdujq.map.MapShowActivity;
import org.sdujq.map.TabHomeActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class TaskInputActivity extends Activity {
	public Button bt_zhiding, bt_qingkong, bt_xuanzequyu, bt_baocunrenwu,
			bt_faburenwu, bt_kaishiriqi, bt_jieshuriqi;
	public TextView tv_luduanming, tv_renwuleibie, tv_xunjianrenyuan,
			tv_renwuneirong, tv_qx_kaishishijian, tv_qx_jiezhishijian,
			tv_xunjianzhouqi, tv_gerenwu, tv_beizhu, tv_zhidingren,
			tv_zhidingshijian, tv_kaishiriqi, tv_jiezhiriqi;
	public EditText et_luduanming, et_renwuneirong, et_beizhu,
			et_xunjianzhouqi, et_gerenwu;

	public String str_luduanming, str_renwuleibie, str_xunjianrenyuan,
			str_renwuneirong, str_kaishishijian, str_jiezhishijian,
			str_xunjianzhouqi, str_gerenwu, str_beizhu, str_zhidingren;

	public Spinner sp_selectTask, sp_kaishishijian, sp_jiezhishijian,
			sp_xunjianrenyuan;

	public CheckBox chb_zidongfabu, chb_zidongshengcheng;

	private Task task;
	private TaskAction ta;
	private int year;
	private int month;
	private int day;
	private int mHour1, mHour2, mTime1 = 0, mTime2 = 0, timeCount1 = 0,
			timeCount2 = 0, typeOfTaskCount = 0, perCount = 0, state = 0,
			timeState = 0;
	private String strTime, str_mHour1, str_mHour2;
	public static int REQUEST_CODE = 0;
	public static int roadLineCODE = 1;
	private String DEFAULT_TIME_FORMAT = "yyyy-MM-dd hh:mm:ss";
	private String str_typeOfTask, str_idOfPerson, str_timeOfStart,
			str_timeOfEnd;
	private int idOfPerson = 0;
	private long long_timeOfStart, long_timeOfEnd;
	private int roadLineId = -1;

	@Override
	public void onCreate(Bundle saved) {
		super.onCreate(saved);
		setContentView(R.layout.task_inputmh);
		ta = new TaskAction(this);
		task = new Task();

		chb_zidongfabu = (CheckBox) findViewById(R.id.t_checkBox_zidongfabu);
		// 为自动发布按钮添加监听器
		chb_zidongfabu
				.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// TODO Auto-generated method stub
						if (isChecked) {
							Toast.makeText(TaskInputActivity.this, "该任务将自动发布！",
									Toast.LENGTH_SHORT).show();

						} else {
							// Toast.makeText(TaskInputActivity.this,
							// "该任务不会自动发布", Toast.LENGTH_SHORT)
							// .show();

						}
					}
				});

		chb_zidongshengcheng = (CheckBox) findViewById(R.id.t_checkBox_zidongshengcheng);
		// 为自动生成按钮添加监听器
		chb_zidongshengcheng
				.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// TODO Auto-generated method stub
						if (isChecked) {
							Toast.makeText(TaskInputActivity.this, "将自动生成多个任务",
									Toast.LENGTH_SHORT).show();
						} else {
							// Toast.makeText(TaskInputActivity.this,
							// "不会生成多个任务", Toast.LENGTH_SHORT)
							// .show();

						}
					}
				});

		// bt_zhiding = (Button) findViewById(R.id.t_button_zhiding);
		// bt_zhiding.setOnClickListener(new ZhidingListener());

		bt_qingkong = (Button) findViewById(R.id.t_button_qingkong);
		bt_qingkong.setOnClickListener(new QingkongListener());

		bt_xuanzequyu = (Button) findViewById(R.id.t_button_xuanzequyu);
		bt_xuanzequyu.setOnClickListener(new XuanzequyuListener());

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
		// 通过createFromResource方法创建一个ArrayAdapter对象

		ArrayAdapter<TaskType> adapter1 = new ArrayAdapter<TaskType>(this,
				android.R.layout.simple_spinner_item);
		// 设置Spinner当中每个条目的样式，引用一个Android系统提供的布局文件
		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		List<TaskType> bugTypelst = (new TaskTypeDao(this)).find();
		for (TaskType b : bugTypelst) {
			adapter1.add(b);
		}
		sp_selectTask.setAdapter(adapter1);
		sp_selectTask.setPrompt("任务类型");
		sp_selectTask
				.setOnItemSelectedListener(new SpinnerOnSelectedListener());

		sp_xunjianrenyuan = (Spinner) findViewById(R.id.t_spinner_xunjianrenyuan);

		List<String> list = new ArrayList<String>();
		List<User> ulist = (new UserDao(this)).find();
		for (User u : ulist) {
			list.add(u.getId() + ":" + u.getName());
		}

		// 调用ArrayAdapter的构造函数来创建ArrayAdapter对象
		// 第一个参数是指上下文对象
		// 第二个参数指定了下拉菜单当中每一个条目的样式
		// 第三个参数指定了TextView控件的ID
		// 第四个参数为整个列表提供数据
		ArrayAdapter<String> adapter_person = new ArrayAdapter<String>(this,
				R.layout.person_item, R.id.textViewId, list);
		sp_xunjianrenyuan.setAdapter(adapter_person);
		sp_xunjianrenyuan.setPrompt("请选择巡检人员");
		// 为spinner对象绑定监听器
		sp_xunjianrenyuan
				.setOnItemSelectedListener(new SpinnerOnSelectedPersonListener());

		sp_kaishishijian = (Spinner) findViewById(R.id.t_spinner_kaishishijian);
		ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(
				this, R.array.str_array_time,
				android.R.layout.simple_spinner_item);
		// 设置Spinner当中每个条目的样式，引用一个Android系统提供的布局文件
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		sp_kaishishijian.setAdapter(adapter2);
		sp_kaishishijian.setPrompt("请设置开始时间");
		sp_kaishishijian
				.setOnItemSelectedListener(new SpinnerOnSelectedTimeOneListener());

		sp_jiezhishijian = (Spinner) findViewById(R.id.t_spinner_jiezhishijian);
		sp_jiezhishijian.setAdapter(adapter2);
		sp_jiezhishijian.setPrompt("请设置结束时间");
		sp_jiezhishijian
				.setOnItemSelectedListener(new SpinnerOnSelectedTimeTwoListener());

		// tv_kaishiriqi.setText(R.string.kaishiriqi);
		bt_kaishiriqi.setText(R.string.kaishiriqi);
		bt_jieshuriqi.setText(R.string.jiezhiriqi);
		// tv_jiezhiriqi.setText(R.string.jiezhiriqi);

		tv_zhidingren.setText(R.string.zhidingren);

		et_luduanming = (EditText) findViewById(R.id.t_text_nameOfRoad);

		et_renwuneirong = (EditText) findViewById(R.id.t_text_renwuneirong);
		et_beizhu = (EditText) findViewById(R.id.t_text_beizhu);
		et_xunjianzhouqi = (EditText) findViewById(R.id.t_text_xunjianzhouqi);
		et_gerenwu = (EditText) findViewById(R.id.t_text_gerenwu);

		// 刷新时间的线程
		handler.post(updateThread);
		String uname = Action.currentUser == null ? "无" : Action.currentUser
				.getName();
		tv_zhidingren.setText("制定人：" + uname);
	}

	// 创建Handler对象

	Handler handler = new Handler();

	// 新建一个线程对象

	Runnable updateThread = new Runnable() {

		// 将要执行的操作写在线程对象的run方法当中

		public void run() {

			handler.postDelayed(updateThread, 1000);
			SimpleDateFormat dateFormatter = new SimpleDateFormat(
					DEFAULT_TIME_FORMAT);

			strTime = dateFormatter.format(Calendar.getInstance().getTime());

			tv_zhidingshijian.setText("制定时间：" + strTime);
		}

	};

	// 这个监听器主要用来监听任务类别选择列表的动作
	class SpinnerOnSelectedListener implements OnItemSelectedListener {

		// 当用户选定了一个条目时，就会调用该方法
		@Override
		public void onItemSelected(AdapterView<?> adapterView, View view,
				int position, long id) {
			str_typeOfTask = adapterView.getItemAtPosition(position).toString();
			typeOfTaskCount++;
			if (typeOfTaskCount > 1) {
				Toast.makeText(TaskInputActivity.this,
						"任务类别为：" + str_typeOfTask, Toast.LENGTH_SHORT).show();
			}

		}

		@Override
		public void onNothingSelected(AdapterView<?> adapterView) {
			// TODO Auto-generated method stub
			Toast.makeText(TaskInputActivity.this, "你还未选择任务类别",
					Toast.LENGTH_SHORT).show();
		}

	}

	// 这个监听器主要用来监听巡检人员类别选择列表的动作
	class SpinnerOnSelectedPersonListener implements OnItemSelectedListener {

		// 当用户选定了一个条目时，就会调用该方法
		@Override
		public void onItemSelected(AdapterView<?> adapterView, View view,
				int position, long id) {
			str_idOfPerson = adapterView.getItemAtPosition(position).toString();

			str_idOfPerson = str_idOfPerson.substring(0,
					str_idOfPerson.indexOf(':'));
			System.out.println(str_idOfPerson);
			perCount++;
			if (perCount > 1) {
				Toast.makeText(TaskInputActivity.this,
						"巡检人员为：" + str_idOfPerson, Toast.LENGTH_SHORT).show();
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> adapterView) {
			// TODO Auto-generated method stub
			Toast.makeText(TaskInputActivity.this, "你还未选择巡检人员",
					Toast.LENGTH_SHORT).show();
		}

	}

	// 这个监听器主要用来监听时间类别选择列表的动作
	class SpinnerOnSelectedTimeOneListener implements OnItemSelectedListener {

		// 当用户选定了一个条目时，就会调用该方法
		@Override
		public void onItemSelected(AdapterView<?> adapterView, View view,
				int position, long id) {
			str_mHour1 = adapterView.getItemAtPosition(position).toString();
			timeCount1++;
			if (timeCount1 > 1) {
				Toast.makeText(TaskInputActivity.this, "任务起始时间为：" + str_mHour1,
						Toast.LENGTH_SHORT).show();
			}
			str_mHour1 = str_mHour1.substring(0, 2);

		}

		@Override
		public void onNothingSelected(AdapterView<?> adapterView) {
			// TODO Auto-generated method stub
			Toast.makeText(TaskInputActivity.this, "你还未选择任务起始时间",
					Toast.LENGTH_SHORT).show();
		}

	}

	// 这个监听器主要用来监听时间类别选择列表的动作
	class SpinnerOnSelectedTimeTwoListener implements OnItemSelectedListener {

		// 当用户选定了一个条目时，就会调用该方法
		@Override
		public void onItemSelected(AdapterView<?> adapterView, View view,
				int position, long id) {
			str_mHour2 = adapterView.getItemAtPosition(position).toString();
			timeCount2++;
			if (timeCount2 > 1) {
				Toast.makeText(TaskInputActivity.this, "任务结束时间为：" + str_mHour2,
						Toast.LENGTH_SHORT).show();
			}
			str_mHour2 = str_mHour2.substring(0, 2);

		}

		@Override
		public void onNothingSelected(AdapterView<?> adapterView) {
			// TODO Auto-generated method stub
			Toast.makeText(TaskInputActivity.this, "你还未选择任务结束时间",
					Toast.LENGTH_SHORT).show();
		}

	}

	// 用于返回从DataPickActivity中设置的日期，并设置在本activity中显示
	public void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (requestCode == REQUEST_CODE) {

			if (resultCode == RESULT_CANCELED) {
				return;
			}
			if (resultCode == RESULT_OK && requestCode == 0) {

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
						str_timeOfStart = year + "" + Smonth + "" + Sday;
						mTime1 = 1;

					} else if (a == 2) {
						bt_jieshuriqi.setText(year + "-" + Smonth + "-" + Sday);
						str_timeOfEnd = year + "" + Smonth + "" + Sday;
						mTime2 = 1;
					}

				}

			}

		} else if (requestCode == 1 && resultCode == RESULT_OK) {
			roadLineId = data.getIntExtra("id", -1);
			if (roadLineId != -1) {
				RoadLine line = (new RoadLineDao(this)).get(roadLineId);
				if (line.getName() != null && !line.getName().equals("")) {
					et_luduanming.setText(line.getName());
				}
			}
			Log.e("qq", "saved road id is " + roadLineId);
		}

	}

	// 按钮 “kaishiriqi” 的监听
	class KaishirijiListener implements OnClickListener {

		public void onClick(View v) {
			// TODO Auto-generated method stub

			Intent intent1 = new Intent(TaskInputActivity.this,
					DatePickActivity.class);

			intent1.putExtra("values", 1);

			TabHomeActivity.home.startActivityForResult(intent1, REQUEST_CODE);

		}

	}

	// 按钮 "jieshuriqi" 的监听
	class JieshuriqiListener implements OnClickListener {

		public void onClick(View v) {
			// TODO Auto-generated method stub

			Intent intent = new Intent(TaskInputActivity.this,
					DatePickActivity.class);
			intent.putExtra("values", 2);
			TabHomeActivity.home.startActivityForResult(intent, REQUEST_CODE);

		}

	}

	// 按钮 制定 的监听
	// class ZhidingListener implements OnClickListener {
	//
	// public void onClick(View v) {
	// // TODO Auto-generated method stub
	// AnimationSet animationSet = new AnimationSet(true);
	// ScaleAnimation scaleAnimation = new ScaleAnimation(1, 1.3f, 1,
	// 1.3f, Animation.RELATIVE_TO_SELF, 0.5f,
	// Animation.RELATIVE_TO_SELF, 0.5f);
	// animationSet.addAnimation(scaleAnimation);
	// animationSet.setFillBefore(true);
	// animationSet.setDuration(200);
	// bt_zhiding.startAnimation(animationSet);
	//
	// }
	//
	// }

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

			et_luduanming.setText("");
			et_renwuneirong.setText("");
			et_beizhu.setText("");
			et_xunjianzhouqi.setText("");
			et_gerenwu.setText("");

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
			RoadLine r = null;
			if (roadLineId != -1) {
				r = (new RoadLineDao(TaskInputActivity.this)).get(roadLineId);
			}
			MapShowActivity.startMapForShow(TabHomeActivity.home, r, true);
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

			saveTask();
			// if (timeState == 1) {
			// 设置任务状态为未发布
			task.setState("0");

			ta.establishTask(task);
			Toast.makeText(TaskInputActivity.this, "任务保存成功！", Toast.LENGTH_LONG)
					.show();

			et_luduanming.setText("");
			et_renwuneirong.setText("");
			et_beizhu.setText("");
			et_xunjianzhouqi.setText("");
			et_gerenwu.setText("");
			// } else {
			// Toast.makeText(TaskInputActivity.this, "请保证起止时间的正确性！",
			// Toast.LENGTH_SHORT).show();
			// }
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

			str_luduanming = et_luduanming.getText().toString();

			str_renwuneirong = et_renwuneirong.getText().toString();

			str_xunjianzhouqi = et_xunjianzhouqi.getText().toString();
			if ((str_luduanming != null && str_luduanming.length() != 0)
					&& (str_renwuneirong != null && str_renwuneirong.length() != 0)) {
				state = 1;
			}

			if (state == 0 || roadLineId == -1) {
				Toast.makeText(TaskInputActivity.this, "请完善任务信息后再发布！",
						Toast.LENGTH_SHORT).show();
			}

			else {

				saveTask();
				// 设置任务状态为已发布
				SimpleDateFormat formatter = new SimpleDateFormat(
						"yyyyMMddHHmmss");
				Date curDate = new Date(System.currentTimeMillis());
				String strRealseTime = formatter.format(curDate);
				System.out.println(strRealseTime);
				long realseTime = Long.parseLong(strRealseTime);
				task.setRealseTime(realseTime);

				task.setState("1");

				ta.establishTask(task);
				Toast.makeText(TaskInputActivity.this, "任务发布成功！",
						Toast.LENGTH_LONG).show();
				et_luduanming.setText("");
				et_renwuneirong.setText("");
				et_beizhu.setText("");
				et_xunjianzhouqi.setText("");
				et_gerenwu.setText("");
			}
			TaskInputActivity.this.roadLineId = -1;
			TaskShowMhActivity.currentActivity.refreshData();
		}

	}

	private void saveTask() {
		task.setCreatePersonId(Action.currentUser.getId());
		str_luduanming = et_luduanming.getText().toString();
		task.setRoadName(str_luduanming);

		task.setTaskType(str_typeOfTask);

		idOfPerson = Integer.parseInt(str_idOfPerson);
		task.setInspectionPersonId(idOfPerson);

		str_renwuneirong = et_renwuneirong.getText().toString();
		task.setContent(str_renwuneirong);

		if (mTime1 == 1) {
			long_timeOfStart = Integer.parseInt(str_timeOfStart + ""
					+ str_mHour1);
			task.setStartTime(long_timeOfStart);
		}
		if (mTime2 == 1) {
			long_timeOfEnd = Integer.parseInt(str_timeOfEnd + "" + str_mHour2);
			task.setEndTime(long_timeOfEnd);
		}

		// 判断时间设置是否正确
		if (long_timeOfStart < long_timeOfEnd) {
			timeState = 1;
		}

		str_xunjianzhouqi = et_xunjianzhouqi.getText().toString();
		int cycle = 0;
		if (str_xunjianzhouqi != null && str_xunjianzhouqi.length() != 0) {
			cycle = Integer.parseInt(str_xunjianzhouqi);
		}
		task.setCycle(cycle);

		str_gerenwu = et_gerenwu.getText().toString();

		str_beizhu = et_beizhu.getText().toString();
		task.setTag(str_beizhu);

		if (roadLineId != -1) {
			task.setRoadLineId(roadLineId);
			RoadLineDao dao = new RoadLineDao(this);
			RoadLine line = dao.get(roadLineId);
			line.setName(str_luduanming);
			dao.update(line);
		}
		TaskShowMhActivity.currentActivity.refreshData();
	}

	private void ReadTask(int TaskId) {

		DBHelper dbHelper = new DBHelper(TaskInputActivity.this);
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor cursor = db.query("t_task", new String[] { "id", "content",
				"taskType", "taskName", "tag", "state", "roadName",
				"realseTime", "endTime", "startTime", "inspectionPersionId",
				"roadlineId", "createPersonId" }, "id=?", new String[] { TaskId
				+ "" }, null, null, null);
		while (cursor.moveToNext()) {
			
			et_luduanming.setText(cursor.getString(cursor.getColumnIndex("roadlineId")));
			et_renwuneirong.setText(cursor.getString(cursor.getColumnIndex("content")));
			et_beizhu.setText(cursor.getString(cursor.getColumnIndex("tag")));

		}

	}

}
