package org.sdu.view.usermanager;

import org.sdu.dbaction.Action;
import org.sdu.gis.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
	private Button login, cancel;
	private EditText username, password;
	private Action userAction;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		init();
	}

	/**
	 * �÷���������login������м��������е�¼��֤��ȡ����¼
	 */
	public void LoginAction(View view) {
		Intent intent;
		switch (view.getId()) {
		case R.id.login:
			// ���Ȼ�ȡ��¼��Ϣ
			String name = username.getText().toString();
			String pwd = password.getText().toString();
			// ��ת����Ӧҳ��
			if(name.equals("")||pwd.equals("")){
				Toast.makeText(this, "�û��������벻��Ϊ��", Toast.LENGTH_LONG).show();
			}else{
				if(userAction.login(name, pwd)){
					//��ת�����ҳ��
					 intent=new Intent();
					 intent.setClass(LoginActivity.this, AddUserActivity.class);
					 startActivity(intent);
				}else{
					//��ʾ�����������
					password.setText("");
					Toast.makeText(this, "�û������������", Toast.LENGTH_LONG).show();
				}
			}
			break;
		case R.id.cancelLogin:
			 //�������
			password.setText("");
			break;
		default:
			break;
		}

	}
	public void init(){
		// ��ȡ���
		login = (Button) this.findViewById(R.id.login);
		cancel = (Button) this.findViewById(R.id.cancelLogin);
		username = (EditText) this.findViewById(R.id.username);
		password = (EditText) this.findViewById(R.id.password);
		userAction=new Action(this);
	}
}
