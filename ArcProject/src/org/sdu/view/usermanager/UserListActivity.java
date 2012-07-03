package org.sdu.view.usermanager;

import java.util.ArrayList;

import org.sdu.dao.UserDao;
import org.sdu.gis.R;
import org.sdu.pojo.User;

import android.app.Activity;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class UserListActivity extends Activity{
	private ArrayList<User>userList;
	private ListView lv;
	private UserDao ud;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		//获取用户列表
		setContentView(R.layout.userlist);
		ud=new UserDao(this);
		userList=(ArrayList<User>) ud.find();
		lv=(ListView) findViewById(R.id.userlist);
		lv.setAdapter(new UserAdapter());
	}
	class UserAdapter extends BaseAdapter{

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
			View v=View.inflate(UserListActivity.this, R.layout.userlist_item, null);
			final TextView tv=(TextView)v.findViewById(R.id.userInfo);
			Button delete=(Button)v.findViewById(R.id.deleteUser);
			if(userList.size()>position){
				User user=userList.get(position);
				tv.setText(user.getId()+"     "+user.getName());
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
					intent.setClass(UserListActivity.this, UserInfoActivity.class);
					startActivity(intent);
				}
			});
			return v;
		}
		
	}
}
