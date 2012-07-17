package org.sdu.view.usermanager;

import java.io.File;

import org.apache.log4j.Level;
import org.sdu.dao.UserDao;
import org.sdu.db.DBHelper;
import org.sdu.dbaction.Action;
import org.sdu.gis.R;
import org.sdujq.map.TabHomeActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import de.mindpipe.android.logging.log4j.LogConfigurator;

public class LoginActivity extends Activity {
	private Button login, cancel;
	private EditText username, password;
	private Action userAction;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loginmh);
		DBHelper dbhelper = new DBHelper(this);
		dbhelper.getWritableDatabase();
		dbhelper.initDb();
		dbhelper.close();
		configure();
		init();
	}
	public static void configure() {
        final LogConfigurator logConfigurator = new LogConfigurator();
        
        logConfigurator.setFileName(Environment.getExternalStorageDirectory() + File.separator + "gis.log");
        logConfigurator.setRootLevel(Level.ERROR);
        // Set log level of a specific logger
        //logConfigurator.setLevel("gis", Level.ERROR);
        logConfigurator.configure();
    }
	/**
	 * �÷���������login������м��������е�¼��֤��ȡ����¼
	 */
	public void LoginAction(View view) {
		Intent intent;
		switch (view.getId()) {
		case R.id.login:
			// ���Ȼ�ȡ��¼��Ϣ
			String name = username.getText().toString().trim();
			String pwd = password.getText().toString().trim();
			// ��ת����Ӧҳ��
			if (name.equals("") || pwd.equals("")) {
				if (new UserDao(LoginActivity.this).find().size() == 0) {
					intent = new Intent();
					intent.setClass(LoginActivity.this, TabHomeActivity.class);
					startActivity(intent);
					overridePendingTransition(R.anim.scale_rotate,
							R.anim.my_alpha_action);
					Toast.makeText(this, "�����ϵͳ�û�", Toast.LENGTH_LONG)
					.show();
				} else {
					Toast.makeText(this, "�û��������벻��Ϊ��", Toast.LENGTH_LONG)
							.show();
				}
			} else {
				if (userAction.login(name, pwd)) {
					// ��ת�����ҳ��
					intent = new Intent();
					intent.setClass(LoginActivity.this, TabHomeActivity.class);
					startActivity(intent);
					overridePendingTransition(R.anim.scale_rotate,
							R.anim.my_alpha_action);
				} else {
					// ��ʾ�����������
					password.setText("");
					Toast.makeText(this, "�û������������", Toast.LENGTH_LONG).show();
				}
			}
			break;
		case R.id.cancelLogin:
			// �������
			password.setText("");
			break;
		default:
			break;
		}

	}

	public void init() {
		// ��ȡ���
		login = (Button) this.findViewById(R.id.login);
		cancel = (Button) this.findViewById(R.id.cancelLogin);
		username = (EditText) this.findViewById(R.id.username);
		password = (EditText) this.findViewById(R.id.password);
		userAction = new Action(this);
	}
}
