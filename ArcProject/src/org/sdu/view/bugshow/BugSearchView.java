package org.sdu.view.bugshow;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.sdu.dao.BugTypeDao;
import org.sdu.dao.UserDao;
import org.sdu.gis.R;
import org.sdu.pojo.BugType;
import org.sdu.pojo.User;
import org.sdujq.frame.AbsShow;
import org.sdujq.frame.FrameActivity;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

public class BugSearchView extends AbsShow {
	Button search, reset, btStime, btEtime;
	EditText id, roadName, startTime, endTime;
	Spinner type, user;
	Date sdate;
	Date edate;
	CheckBox cb1,cb2;
	public BugSearchView(FrameActivity activity, int layout, String name) {
		super(activity, layout, name);
	}

	@Override
	public View initView() {
		search = (Button) view.findViewById(R.id.search);
		reset = (Button) view.findViewById(R.id.reset);
		btStime = (Button) view.findViewById(R.id.button1);
		btEtime = (Button) view.findViewById(R.id.button2);
		id = (EditText) view.findViewById(R.id.bugid);
		roadName = (EditText) view.findViewById(R.id.roadname);
		startTime = (EditText) view.findViewById(R.id.starttime);
		startTime.setEnabled(false);
		endTime = (EditText) view.findViewById(R.id.endtime);
		endTime.setEnabled(false);
		type = (Spinner) view.findViewById(R.id.bugtype);
		user = (Spinner) view.findViewById(R.id.user);
		cb1=(CheckBox)view.findViewById(R.id.checkBox1);
		cb2=(CheckBox)view.findViewById(R.id.checkBox2);
		cb1.setText("查看已解决问题");
		cb2.setText("查看未解决问题");
		ArrayAdapter<BugType> adapter = new ArrayAdapter<BugType>(activity,
				android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		List<BugType> typeList = (new BugTypeDao(activity)).find();
		BugType bhead=new BugType();
		bhead.setId(0);
		bhead.setName("请选择故障类型");
		adapter.add(bhead);
		for (BugType b : typeList) {
			adapter.add(b);
		}
		type.setAdapter(adapter);
		type.setPrompt("选择故障类型");
		ArrayAdapter<User> adapter2 = new ArrayAdapter<User>(activity,
				android.R.layout.simple_spinner_item);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		List<User> userList = (new UserDao(activity)).find();
		User u=new User();
		u.setId(0);
		u.setName("请选择巡检人员");
		adapter2.add(u);
		for (User b : userList) {
			adapter2.add(b);
		}
		user.setAdapter(adapter2);
		user.setPrompt("选择上报人员");

		search.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				BugTypes bt = new BugTypes();
				bt.setAddress(roadName.getText().toString());
				bt.setBugTypeId(((BugType) type.getSelectedItem()).getId());
				if (sdate == null) {
					sdate = new Date(1, 1, 1);
				}
				if (edate == null) {
					edate = new Date(6000, 1, 1);
				}
				bt.setEndTime(edate.getTime());
				bt.setStartTime(sdate.getTime());
				try {
					bt.setId(Integer.parseInt(id.getText().toString().trim()));
				} catch (Exception e) {
					bt.setId(0);
					e.printStackTrace();
				}
				bt.setUserId(((User)user.getSelectedItem()).getId());
				if(cb1.isChecked()){
					bt.setState("完成");
				}
				if(cb2.isChecked()){
					bt.setState("未完成");
				}
				if(cb1.isChecked()&&cb2.isChecked()){
					bt.setState(null);
				}
				BugListView.bt=bt;
				if(BugListView.currentView!=null){
					BugListView.refreshData();
					activity.changeTo(0, 1);
				}
				
			}
		});
		reset.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				clear();
			}
		});
		btStime.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showDateDialog(startTime);
			}
		});
		btEtime.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showDateDialog(endTime);
			}
		});
		return view;
	}

	public void clear() {
		id.setText("");
		roadName.setText("");
		startTime.setText("");
		endTime.setText("");
	}

	public void showDateDialog(final EditText t) {
		final Calendar c = Calendar.getInstance();
		int mYear = c.get(Calendar.YEAR);
		int mMonth = c.get(Calendar.MONTH);
		int mDay = c.get(Calendar.DAY_OF_MONTH);
		DatePickerDialog d = new DatePickerDialog(activity,
				new OnDateSetListener() {

					@Override
					public void onDateSet(DatePicker view, int year,
							int monthOfYear, int dayOfMonth) {
						c.set(Calendar.YEAR, year);
						c.set(Calendar.MONTH, monthOfYear);
						c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
						t.setText(c.getTime().toLocaleString());
						if (t == startTime) {
							sdate = c.getTime();
						} else {
							edate = c.getTime();
						}
					}
				}, mYear, mMonth, mDay){
		};
		d.show();
	}
}
