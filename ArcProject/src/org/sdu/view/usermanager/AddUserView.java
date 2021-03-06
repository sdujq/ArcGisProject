package org.sdu.view.usermanager;

import org.sdu.dao.UserDao;
import org.sdu.dbaction.Action;
import org.sdu.gis.R;
import org.sdu.pojo.User;
import org.sdujq.frame.AbsShow;
import org.sdujq.frame.FrameActivity;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddUserView extends AbsShow implements OnClickListener{
	private Button addUser,cancelAdd;
	private EditText name,password1,password2,comment,telphone;
	public Action userAction;
	private UserDao ud;
	
	public AddUserView(FrameActivity activity, int layout, String name) {
		super(activity, layout, name);
	}

	@Override
	public View initView() {
		addUser=(Button) view.findViewById(R.id.addUser);
		addUser.setOnClickListener(this);
		cancelAdd=(Button) view.findViewById(R.id.addUser_cancel);
		cancelAdd.setOnClickListener(this);
		name=(EditText) view.findViewById(R.id.addUser_name);
		password1=(EditText) view.findViewById(R.id.addUser_password1);
		password2=(EditText) view.findViewById(R.id.addUser_password2);
		comment=(EditText) view.findViewById(R.id.addUser_comment);
		telphone=(EditText) view.findViewById(R.id.addUser_telphone);
		userAction=new Action(activity);
		ud=new UserDao(activity);
		return view;
	}

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
				password2.setText("");
			}else{
				//添加用户
				User user=new User();
				user.setName(_name);
				user.setPassword(_password1);
				user.setPhoneNum(_telphone);
				user.setTag(_comment);
				ud.insert(user);
				clear();
				info="添加成功";
				
			}
			Toast.makeText(activity, info,Toast.LENGTH_SHORT).show();
			break;
		case R.id.addUser_cancel:
			//取消添加
			clear();
			break;
			/*Intent intent=new Intent();
			intent.setClass(AddUserActivity.this, UserListActivity.class);
			startActivity(intent);*/
		default:
			break;
		}
	}
	
	public void clear(){
		name.setText("");
		password1.setText("");
		password2.setText("");
		telphone.setText("");
		comment.setText("");
	}

	@Override
	public void onClick(View v) {
		UserAction(v);
	}
}
