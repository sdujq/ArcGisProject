package org.sdu.view.bugshow;

import org.sdu.bmputil.BitmapTool;
import org.sdu.dao.BugDao;
import org.sdu.gis.R;
import org.sdu.pojo.Bug;
import org.sdu.pojo.RoadLine;
import org.sdujq.map.MapShowActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class BugDetailActivity extends Activity implements OnClickListener{
	private TextView _bugId;
	private TextView _bug_address;
	private ImageView _bug_attachment;
	private TextView _bug_tag;
	private TextView _bug_content;
	private TextView _bug_point;
	private TextView _bug_userId;
	private TextView _bug_state;
	private TextView _bug_bugTypeId;
	private Button finish, map;
	private Bug bug;
	private BugDao bugDao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bug_detail);
		// getWindow().setLayout(LayoutParams.FILL_PARENT,
		// LayoutParams.FILL_PARENT);
		getWindow().setLayout(500, 620);
		init();
	}

	public void init() {
		_bugId = (TextView) this.findViewById(R.id.bug_id);
		_bug_address = (TextView) this.findViewById(R.id.bug_address);
		_bug_attachment = (ImageView) this.findViewById(R.id.bug_attachment);
		_bug_tag = (TextView) this.findViewById(R.id.bug_tag);
		_bug_content = (TextView) this.findViewById(R.id.bug_content);
		_bug_point = (TextView) this.findViewById(R.id.bug_point);
		_bug_userId = (TextView) this.findViewById(R.id.bug_userId);
		_bug_state = (TextView) this.findViewById(R.id.bug_state);
		_bug_bugTypeId = (TextView) this.findViewById(R.id.bug_bugTypeId);
		finish = (Button) this.findViewById(R.id.bug_finish);
		map = (Button) this.findViewById(R.id.showmap);
		// 添加监听
		finish.setOnClickListener(this);
		map.setOnClickListener(this);
		Intent intent = this.getIntent();
		Bundle bundle = intent.getExtras();
		bug = (Bug) bundle.get("bug");
		_bugId.setText(bug.getId() + "");
		_bug_address.setText(bug.getAddress());
		_bug_attachment.setTag(bug.getAttachment());
		_bug_tag.setText(bug.getTag());
		_bug_content.setText(bug.getContent());
		_bug_point.setText(bug.getPoint());
		_bug_userId.setText(bug.getUserId() + "");
		_bug_state.setText(bug.getState());
		_bug_bugTypeId.setText(bug.getBugTypeId() + "");
		bugDao = new BugDao(this);
		if (bug.getAttachment() != null) {
			_bug_attachment.setImageBitmap(BitmapTool.Bytes2Bimap(bug
					.getAttachment()));
		}
	}


	@Override
	public void onClick(View v) {
		if (v == finish) {
			_bug_state.setText("完成");
			bug.setState("完成");
			bugDao.update(bug);
		} else if (v == map) {
			RoadLine road=new RoadLine();
			road.setPoints(bug.getPoint());
			MapShowActivity.startMapForShow(this, road, false,true);
		}		
	}
}
