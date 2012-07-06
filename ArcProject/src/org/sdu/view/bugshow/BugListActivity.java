package org.sdu.view.bugshow;

import java.util.ArrayList;

import org.sdu.dao.BugDao;
import org.sdu.dao.UserDao;
import org.sdu.dbaction.BugAction;
import org.sdu.gis.R;
import org.sdu.pojo.Bug;
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
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class BugListActivity extends Activity{
	private ArrayList<Bug> bugList;
	private ListView lv;
	private BugDao bd;
	private BugAction ba;
	private String type="";
	private String value="";
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		//获取用户列表
		setContentView(R.layout.bug_listview);
		//接收传递的参数
		Intent intent=this.getIntent();
		Bundle bundle=intent.getExtras();
		//type=bundle.getString("type");
		//value=bundle.getString("value");
		type="id";
		value="1";
		ba=new BugAction(this);
		bd=new BugDao(this);
		init();
	}
	public void init(){
		bugList=(ArrayList<Bug>) ba.search(type+"=?",value);
		lv=(ListView) findViewById(R.id.userlist);
		lv.setAdapter(new UserAdapter());
	}
	class UserAdapter extends BaseAdapter{

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
			View v=View.inflate(BugListActivity.this, R.layout.bug_list_item, null);
			final TextView bugId=(TextView)v.findViewById(R.id.bugId);
			final TextView bugAdress=(TextView)v.findViewById(R.id.bugAddress);
			final TextView bugState=(TextView)v.findViewById(R.id.bugState); 
			final TextView bugUserId=(TextView)v.findViewById(R.id.bugUserId);
			ImageView delete=(ImageView)v.findViewById(R.id.deleteUser);
			if(bugList.size()>position){
				Bug bug=bugList.get(position);
				bugId.setText(bug.getId()+"");
				bugAdress.setText(bug.getAddress());
				bugState.setText(bug.getState());
				bugUserId.setText(bug.getUserId()+"");
			}
			delete.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if(v.getId()==R.id.deleteUser){
						 //获取用户id
						String _id=bugId.getText().toString().trim();
						int id=Integer.parseInt(_id);
						//通过id删除用户
						bd.delete(id);
						//刷新列表
						bugList=(ArrayList<Bug>)ba.search(type, value);
						lv.setAdapter(new UserAdapter());
					}
					
				}
			});
			//为用户查看详情
			v.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					String _id=bugId.getText().toString().trim();
					int id=Integer.parseInt(_id);
					Bug bug=bd.get(id);	
					Intent intent=new Intent();
					intent.putExtra("bug", bug);
					intent.setClass(BugListActivity.this, BugDetailActivity.class);
					startActivity(intent);
				}
			});
			return v;
		}
		
	}
}
