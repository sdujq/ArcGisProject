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
		//��ȡ�û��б�
		setContentView(R.layout.userlist);
		//���մ��ݵĲ���
		Intent intent=this.getIntent();
		Bundle bundle=intent.getExtras();
		type=bundle.getString("type");
		value=bundle.getString("value");
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
			View v=View.inflate(BugListActivity.this, R.layout.userlist_item, null);
			final TextView tv=(TextView)v.findViewById(R.id.userInfo);
			ImageView delete=(ImageView)v.findViewById(R.id.deleteUser);
			if(bugList.size()>position){
				Bug bug=bugList.get(position);
				//tv.setText(user.getId()+"     "+user.getName());
				tv.setText(bug.getId()+"     "+bug.getAddress()+"     "+bug.getState()+"     "+bug.getUserId());
			}
			else
				tv.setText("");
			delete.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if(v.getId()==R.id.deleteUser){
						 //��ȡ�û�id
						String userInfo=tv.getText().toString().trim();
						String _id=userInfo.substring(0,userInfo.indexOf(" ") );
						int id=Integer.parseInt(_id);
						//ͨ��idɾ���û�
						bd.delete(id);
						//ˢ���б�
						bugList=(ArrayList<Bug>)ba.search(type, value);
						lv.setAdapter(new UserAdapter());
					}
					
				}
			});
			//Ϊ�û��鿴����
			v.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					String bugInfo=tv.getText().toString().trim();
					String _id=bugInfo.substring(0,bugInfo.indexOf(" "));
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
