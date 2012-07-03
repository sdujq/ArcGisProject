package org.sdu.view.usermanager;

import org.sdu.dao.UserDao;
import org.sdu.gis.R;
import org.sdu.pojo.User;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserInfoActivity extends Activity{
	private Button saveUpdate;
	private Button back;
	private EditText nameEdit,pwdEdit,phoneEdit,commentEdit;
	private Bundle bundle;
	private User user;
	private UserDao userDao;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.updateuser);
		init();
	}
	public void init(){
		saveUpdate=(Button) this.findViewById(R.id.updateUser);
		back=(Button) this.findViewById(R.id.updateUser_back);
		bundle=this.getIntent().getExtras();
		user=new User();
		user.setId(bundle.getInt("user_id"));
		user.setName(bundle.getString("user_name"));
		user.setPassword(bundle.getString("user_password"));
		user.setPhoneNum(bundle.getString("user_phone"));
		user.setTag(bundle.getString("user_comment"));
		nameEdit=(EditText) this.findViewById(R.id.updateUser_name);
		pwdEdit=(EditText) this.findViewById(R.id.updateUser_password1);
		phoneEdit=(EditText) this.findViewById(R.id.updateUser_telphone);
		commentEdit=(EditText) this.findViewById(R.id.updateUser_comment);
		nameEdit.setText(user.getName());
		pwdEdit.setText(user.getPassword());
		phoneEdit.setText(user.getPhoneNum());
		commentEdit.setText(user.getTag());
		userDao=new UserDao(this);
	}
	/**
	 * 该方法用来响应修改用户信息界面的组件监听事件
	 * @param view
	 */
	public void updateUserAction(View view){
		switch (view.getId()) {
		case R.id.updateUser:
			update();
			Toast.makeText(this, "修改成功", Toast.LENGTH_LONG).show();
			break;
		case R.id.updateUser_back:
			Intent intent=new Intent();
			intent.setClass(UserInfoActivity.this, UserListActivity.class);
			startActivity(intent);
		default:
			break;
		}
	}
	/**
	 * 该方法用来保存修改后的用户信息
	 */
	public void update(){
		user.setId(user.getId());
		user.setName(nameEdit.getText().toString().trim());
		user.setPassword(pwdEdit.getText().toString().trim());
		user.setPhoneNum(phoneEdit.getText().toString());
		user.setTag(commentEdit.getText().toString());
		userDao.update(user);
	}
	
}
