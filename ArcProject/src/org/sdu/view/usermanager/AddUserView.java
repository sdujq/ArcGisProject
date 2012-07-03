package org.sdu.view.usermanager;

import org.sdu.dao.UserDao;
import org.sdu.dbaction.Action;
import org.sdu.gis.R;
import org.sdu.pojo.User;
import org.sdujq.frame.AbsShow;
import org.sdujq.frame.FrameActivity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddUserView extends AbsShow implements OnClickListener{
	private Button addUser,cancelAdd;
	private EditText name,password1,password2,comment,telphone;
	private Action userAction;
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
			//����û�
			String _name=name.getText().toString().trim();
			String _password1=password1.getText().toString().trim();
			String _passwrod2=password2.getText().toString().trim();
			String _telphone=telphone.getText().toString().trim();
			String _comment=comment.getText().toString().trim();
			String info="";
			//����Ϣ������֤����η���Ҫ�������
			if(_name.equals("")){
				info="�û�������Ϊ��";
			}
			if(_password1.equals("")||_passwrod2.equals("")){
				//��ʾ���벻��Ϊ��
				info="���벻��Ϊ��";
			}else if(!_password1.equals(_passwrod2)){
				//��ʾ�����������벻��
				info="�������벻ƥ��";
				password2.setText("");
			}else{
				//����û�
				User user=new User();
				user.setName(_name);
				user.setPassword(_password1);
				user.setPhoneNum(_telphone);
				user.setTag(_comment);
				ud.insert(user);
				clear();
				info="��ӳɹ�";
				
			}
			Toast.makeText(activity, info,Toast.LENGTH_SHORT).show();
			break;
		case R.id.addUser_cancel:
			//ȡ�����
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
