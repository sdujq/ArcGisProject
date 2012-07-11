package org.sdu.view.usermanager;

import java.util.ArrayList;

import org.sdu.dao.UserDao;
import org.sdu.gis.R;
import org.sdu.pojo.User;
import org.sdujq.frame.AbsShow;
import org.sdujq.frame.FrameActivity;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Handler;
import android.os.Message;
import android.text.util.Linkify;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class UserListView extends AbsShow{
	public static UserListView currentView;
	private ArrayList<User>userList;
	public  ListView lv;
	private UserDao ud;
	
	Handler h=new Handler(){
		@Override
		public void handleMessage(Message m){
			if(lv==null){
				lv=(ListView) view.findViewById(R.id.userlist);
			}
			lv.setAdapter(new UserAdapter());
			view.postInvalidate();
		}
	};
	public UserListView(FrameActivity activity, int layout, String name) {
		super(activity, layout, name);
		currentView=this;
	}

	@Override
	public View initView() {
		/*ud=new UserDao(activity);
		userList=(ArrayList<User>) ud.find();*/
		lv=(ListView) view.findViewById(R.id.userlist);
		lv.setAdapter(new UserAdapter());
		return view;
	}
	
	class UserAdapter extends BaseAdapter{

		public UserAdapter(){
			ud=new UserDao(activity);
			userList=(ArrayList<User>) ud.find();
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return userList.size();
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
			View v=View.inflate(activity, R.layout.userlist_item, null);
			final TextView tv=(TextView)v.findViewById(R.id.userInfo);
			final TextView phone=(TextView)v.findViewById(R.id.phone);
			Linkify.addLinks(phone, Linkify.PHONE_NUMBERS);
			View delete=(View)v.findViewById(R.id.deleteUser);
			if(userList.size()>position){
				User user=userList.get(position);
				tv.setText(user.getId()+"     "+user.getName());
				phone.setText(user.getPhoneNum());
			}
			else
				tv.setText("");
			delete.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if(v.getId()==R.id.deleteUser){
						 //获取用户id
						String userInfo=tv.getText().toString().trim();
						String _id=userInfo.substring(0,userInfo.indexOf(" ") );
						int id=Integer.parseInt(_id);
						//通过id删除用户
						ud.delete(id);
						//刷新列表
						userList=(ArrayList<User>) ud.find();
						lv.setAdapter(new UserAdapter());
					}
					
				}
			});
			//为用户查看详情
			v.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					String userInfo=tv.getText().toString().trim();
					String _id=userInfo.substring(0,userInfo.indexOf(" "));
					int id=Integer.parseInt(_id);
					User user=ud.get(id);	
					Intent intent=new Intent();
					intent.putExtra("user_id", user.getId());
					intent.putExtra("user_password", user.getPassword());
					intent.putExtra("user_comment", user.getTag());
					intent.putExtra("user_name", user.getName());
					intent.putExtra("user_phone", user.getPhoneNum());
					intent.setClass(activity, UserInfoActivity.class);
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
