package org.sdu.view.bugshow;

import org.sdu.gis.R;
import org.sdu.pojo.Bug;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class BugDetailActivity extends Activity{
	private TextView _bugId;
	private TextView _bug_address;
	private ImageView _bug_attachment  ;
	private TextView _bug_tag;
	private TextView _bug_content;
	private TextView _bug_point;
	private TextView _bug_userId;
	private TextView _bug_state;
	private TextView _bug_bugTypeId;
	private Button finish; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bug_detail);
		init();
	}
	public void init(){
		_bugId=(TextView) this.findViewById(R.id.bug_id);
		_bug_address=(TextView) this.findViewById(R.id.bug_address);
		_bug_attachment=(ImageView) this.findViewById(R.id.bug_attachment);
		_bug_tag=(TextView) this.findViewById(R.id.bug_tag);
		_bug_content=(TextView) this.findViewById(R.id.bug_content);
		_bug_point=(TextView) this.findViewById(R.id.bug_point);
		_bug_userId=(TextView) this.findViewById(R.id.bug_userId);
		_bug_state=(TextView) this.findViewById(R.id.bug_state);
		_bug_bugTypeId=(TextView) this.findViewById(R.id.bug_bugTypeId);
		finish=(Button) this.findViewById(R.id.bug_finish);
		//Ìí¼Ó¼àÌý
		finish.setOnClickListener(new FinishListener());
		Intent intent=this.getIntent();
		Bundle bundle=intent.getExtras();
		Bug bug=(Bug) bundle.get("bug");
		_bugId.setText(bug.getId()+"");
		_bug_address.setText(bug.getAddress());
		_bug_attachment.setTag(bug.getAttachment());
		_bug_tag.setText(bug.getTag());
		_bug_content.setText(bug.getContent());
		_bug_point.setText(bug.getPoint());
		_bug_userId.setText(bug.getUserId()+"");
		_bug_state.setText(bug.getState());
		_bug_bugTypeId.setText(bug.getBugTypeId()+"");
	}
	class FinishListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			_bug_state.setText("Íê³É");
		}
		
	}
}
