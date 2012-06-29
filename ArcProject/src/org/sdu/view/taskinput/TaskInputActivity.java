package org.sdu.view.taskinput;

import org.sdu.gis.R;

import com.tgb.lk.ahibernate.annotation.Id;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

//11
public class TaskInputActivity extends Activity {
	public Button bt_zhiding, bt_qingkong, bt_xuanzequyu, bt_xunjianquyu,
			bt_baocunrenwu, bt_faburenwu;
	public TextView tv_luduanming, tv_renwuleibie, tv_xunjianrenyuan,
			tv_renwuneirong, tv_kaishishijian, tv_jiezhishijian,
			tv_xunjianzhouqi, tv_gerenwu, tv_beizhu, tv_zhidingren,
			tv_zhidingshijian;

	@Override
	public void onCreate(Bundle saved) {
		super.onCreate(saved);
		setContentView(R.layout.task_input);

		bt_zhiding = (Button) findViewById(R.id.t_button_zhiding);
		bt_qingkong = (Button) findViewById(R.id.t_button_qingkong);
		bt_xuanzequyu = (Button) findViewById(R.id.t_button_xuanzequyu);
		bt_xunjianquyu = (Button) findViewById(R.id.t_button_xunjianquyu);
		bt_baocunrenwu = (Button) findViewById(R.id.t_button_baocunrenwu);
		bt_faburenwu = (Button) findViewById(R.id.t_button_faburenwu);
		
		tv_luduanming=(TextView) findViewById(R.id.t_nameOfRoad);
		tv_renwuleibie=(TextView) findViewById(R.id.t_renwuleibie);
		tv_xunjianrenyuan=(TextView) findViewById(R.id.t_xunjianrenyuan);
		tv_renwuneirong=(TextView) findViewById(R.id.t_renwuneirong);
		tv_kaishishijian=(TextView) findViewById(R.id.t_Qx_kaishi);
		tv_jiezhishijian=(TextView) findViewById(R.id.t_Qx_jiezhi);
		tv_xunjianzhouqi=(TextView) findViewById(R.id.t_xunjianzhouqi);
		tv_gerenwu=(TextView) findViewById(R.id.t_gerenwu);
		tv_beizhu=(TextView) findViewById(R.id.t_beizhu);
		tv_zhidingren=(TextView) findViewById(R.id.t_zhidingren);
		tv_zhidingshijian=(TextView) findViewById(R.id.t_zhidingshijian);
		
		
		
		
		
		

	}
}
