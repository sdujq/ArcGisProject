package org.sdu.view.bugshow;

import java.util.ArrayList;

import org.sdu.dao.BugDao;
import org.sdu.dao.UserDao;
import org.sdu.dbaction.BugAction;
import org.sdu.gis.R;
import org.sdu.pojo.Bug;
import org.sdujq.frame.AbsShow;
import org.sdujq.frame.FrameActivity;

import android.content.Intent;
import android.database.DataSetObserver;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class BugListView extends AbsShow {
	private ArrayList<Bug> bugList;
	private ListView lv;
	private BugDao bd;
	private BugAction ba;
	UserDao udao;
	private String type = "";
	private String value = "";
	public static BugTypes bt;
	public static BugListView currentView;
	private String selection = "";
	private String selectionArgs = "";

	public BugListView(FrameActivity activity, int layout, String name) {
		super(activity, layout, name);
		currentView = this;
	}

	public static void refreshData() {
		if (currentView == null) {
			return;
		}
		currentView.init();
	}

	@Override
	public View initView() {
		init();
		return view;
	}

	public void init() {
		ba = new BugAction(activity);
		bd = new BugDao(activity);
		udao=new UserDao(activity);
		selection="";
		selectionArgs="";
		if (bt != null) {
			if (bt.getId() != 0) {
				selection += "id=? and ";
				selectionArgs += bt.getId() + "|";
			}
			if (bt.getAddress() != null&&!bt.getAddress().equals("")) {
				selection += "address like ? and ";
				selectionArgs += bt.getAddress().trim() + "|";
			}
			if (bt.getState() != null&&!bt.getState().equals("")) {
				selection += "state=? and ";
				selectionArgs += "%"+bt.getState().trim() + "%|";
			}
			if (bt.getBugTypeId() != 0) {
				selection += "bugTypeId=? and ";
				selectionArgs += bt.getBugTypeId() + "|";
			}
			if (bt.getUserId() != 0) {
				selection += "userId=? and ";
				selectionArgs += bt.getUserId() + "|";
			}
			if (bt.getStartTime() != 0 && bt.getEndTime() != 0) {
				selection += "time between ? and ? and";
				selectionArgs += bt.getStartTime() + "|" + bt.getEndTime()
						+ "|";
			}
			selection = selection.substring(0, selection.lastIndexOf("and"));
			selectionArgs = selectionArgs.substring(0,
					selectionArgs.lastIndexOf('|'));
			bugList = (ArrayList<Bug>) ba.search(selection, selectionArgs);
		}
		else{
			bugList=(ArrayList<Bug>) (new BugDao(activity)).find();
		}
		lv = (ListView) view.findViewById(R.id.userlist);
		lv.setAdapter(new UserAdapter());

	}

	class UserAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return bugList.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View v = View.inflate(activity, R.layout.bug_list_item, null);
			final TextView bugId = (TextView) v.findViewById(R.id.bugId);
			final TextView bugAdress = (TextView) v
					.findViewById(R.id.bugAddress);
			final TextView bugState = (TextView) v.findViewById(R.id.bugState);
			final TextView bugUserId = (TextView) v
					.findViewById(R.id.bugUserId);
			//ImageView delete = (ImageView) v.findViewById(R.id.deleteUser);
			if (bugList.size() > position) {
				Bug bug = bugList.get(position);
				bugId.setText(bug.getId() + "");
				bugAdress.setText(bug.getAddress());
				bugState.setText(bug.getState());
				bugUserId.setText(udao.get(bug.getUserId()).toString());
			}
			/*delete.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if (v.getId() == R.id.deleteUser) {
						// 获取用户id
						String _id = bugId.getText().toString().trim();
						int id = Integer.parseInt(_id);
						// 通过id删除用户
						bd.delete(id);
						// 刷新列表
						bugList = (ArrayList<Bug>) ba.search(selection,
								selectionArgs);
						lv.setAdapter(new UserAdapter());
					}

				}
			});*/
			// 为用户查看详情
			v.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					String _id = bugId.getText().toString().trim();
					int id = Integer.parseInt(_id);
					Bug bug = bd.get(id);
					Intent intent = new Intent();
					intent.putExtra("bug", bug);
					intent.setClass(activity, BugDetailActivity.class);
					activity.startActivity(intent);
				}
			});
			return v;
		}
		@Override
		public void unregisterDataSetObserver(DataSetObserver observer) {
			if (observer != null) {
				super.unregisterDataSetObserver(observer);
			}
		}

	}
}
