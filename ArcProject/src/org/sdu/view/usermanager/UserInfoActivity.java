package org.sdu.view.usermanager;

import org.sdu.dao.UserDao;
import org.sdu.gis.R;
import org.sdu.pojo.User;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserInfoActivity extends Activity{
	private Button saveUpdate;
	private Button back;
	private EditText nameEdit,pwdEdit1,pwdEdit2,phoneEdit,commentEdit;
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
		pwdEdit1=(EditText) this.findViewById(R.id.updateUser_password1);
		pwdEdit2=(EditText) this.findViewById(R.id.updateUser_password2);
		phoneEdit=(EditText) this.findViewById(R.id.updateUser_telphone);
		commentEdit=(EditText) this.findViewById(R.id.updateUser_comment);
		nameEdit.setText(user.getName());
		pwdEdit1.setText(user.getPassword());
		pwdEdit2.setText(user.getPassword());
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
			UserListView.currentView.h.sendEmptyMessage(1);
			finish();
			break;
		case R.id.updateUser_back:
			clear();
		default:
			break;
		}
	}
	/**
	 * 该方法用来保存修改后的用户信息
	 */
	public void update(){
		String info="";//提示信息
		String _name=nameEdit.getText().toString().trim();
		String _password1=pwdEdit1.getText().toString().trim();
		String _passwrod2=pwdEdit2.getText().toString().trim();
		String _telphone=phoneEdit.getText().toString().trim();
		String _comment=commentEdit.getText().toString().trim();
		if(_name.equals("")){
			info="用户名不能为空";
		}
		if(_password1.equals("")||_passwrod2.equals("")){
			//提示密码不能为空
			info="密码不能为空";
		}else if(!_password1.equals(_passwrod2)){
			//提示两次密码输入不对
			info="两次密码不匹配";
			pwdEdit2.setText("");
		}else{
			//添加用户
			//User user=new User();
			user.setName(_name);
			user.setPassword(_password1);
			user.setPhoneNum(_telphone);
			user.setTag(_comment);
			userDao.update(user);
			info="修改成功";
		}
		Toast.makeText(this, info, Toast.LENGTH_LONG).show();
	}
	/**
	 * 
	 */
	public void clear(){
		nameEdit.setText("");
		pwdEdit1.setText("");
		phoneEdit.setText("");
		pwdEdit2.setText("");
		commentEdit.setText("");
	}
	
}
