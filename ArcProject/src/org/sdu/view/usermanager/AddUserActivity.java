package org.sdu.view.usermanager;

import org.sdu.dao.UserDao;
import org.sdu.dbaction.Action;
import org.sdu.gis.R;
import org.sdu.pojo.User;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddUserActivity extends Activity{
	private Button addUser,cancelAdd;
	private EditText name,password1,password2,comment,telphone;
	private Action userAction;
	private UserDao ud;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.adduser);
		init();
	}
	/**
	 * 该方法用来向应点击添加事件或取消添加，查看用户列表等
	 */
	public void UserAction(View view){
		switch (view.getId()) {
		case R.id.addUser:
			//添加用户
			String _name=name.getText().toString().trim();
			String _password1=password1.getText().toString().trim();
			String _passwrod2=password2.getText().toString().trim();
			String _telphone=telphone.getText().toString().trim();
			String _comment=comment.getText().toString().trim();
			String info="";
			//对信息进行验证，如何符合要求则添加
			if(_name.equals("")){
				info="用户名不能为空";
			}
			if(_password1.equals("")||_passwrod2.equals("")){
				//提示密码不能为空
				info="密码不能为空";
			}else if(!_password1.equals(_passwrod2)){
				//提示两次密码输入不对
				info="两次密码不匹配";
			}else{
				//添加用户
				User user=new User();
				user.setName(_name);
				user.setPassword(_password1);
				user.setPhoneNum(_telphone);
				user.setTag(_comment);
				ud.insert(user);
				clear();
				info=userAction.getInfo();
				Toast.makeText(this, userAction.getInfo(), Toast.LENGTH_SHORT);
				
			}
			Toast.makeText(this, info,Toast.LENGTH_SHORT);
			break;
		case R.id.addUser_cancel:
			//取消添加
			clear();
			break;
		case R.id.addUser_list:
			Intent intent=new Intent();
			intent.setClass(AddUserActivity.this, UserListActivity.class);
			startActivity(intent);
		default:
			break;
		}
	}
	/**
	 * 获取组件
	 */
	public void init(){
		addUser=(Button) this.findViewById(R.id.addUser);
		cancelAdd=(Button) this.findViewById(R.id.addUser_cancel);
		name=(EditText) this.findViewById(R.id.addUser_name);
		password1=(EditText) this.findViewById(R.id.addUser_password1);
		password2=(EditText) this.findViewById(R.id.addUser_password2);
		comment=(EditText) this.findViewById(R.id.addUser_comment);
		telphone=(EditText) this.findViewById(R.id.addUser_telphone);
		userAction=new Action(this);
		ud=new UserDao(this);
	}
	/**
	 * 清空各組件
	 */
	public void clear(){
		name.setText("");
		password1.setText("");
		password2.setText("");
		telphone.setText("");
		comment.setText("");
	}
}
