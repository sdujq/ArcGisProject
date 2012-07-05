package org.sdu.view.buginput;

import org.sdu.gis.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class BugInputActivity extends Activity{
	Context mContext = null;
	
	public TextView textView1,textView2,textView3,textView4,textView5,textView6,textView7;
	public EditText editText1,editText3,editText4,editText5,editText6,editText7; 
    public Spinner bugtype_spinner;
	public Button button1,button2,button3,button4;
	
	
	public void onCreate(Bundle saved){
		super.onCreate(saved);
		setContentView(R.layout.debug);
		mContext = this;
		
		textView1 = (TextView)findViewById(R.id.wentijianshu_textView);
		textView2 = (TextView)findViewById(R.id.wentileibie_textView);
		textView3 = (TextView)findViewById(R.id.guzhangneirong_textView);
		textView4 = (TextView)findViewById(R.id.guzhangzuobiao_textView);
		textView5 = (TextView)findViewById(R.id.guzhangdizhi_textView);
		textView6 = (TextView)findViewById(R.id.wentibeizhu_textView);
		textView7 = (TextView)findViewById(R.id.xianchangfujian_textView);
		
		editText1 = (EditText)findViewById(R.id.wentijianshu_editText);
		editText3 = (EditText)findViewById(R.id.guzhangneirong_editText);
		editText4 = (EditText)findViewById(R.id.guzhangzuobiao_editText);
		editText5 = (EditText)findViewById(R.id.guzhangdizhi_editText);
		editText6 = (EditText)findViewById(R.id.wentibeizhu_editText);
		editText7 = (EditText)findViewById(R.id.xianchangfujian_editText);
		
		//现场附件   获取图片（拍照片）
		button1 = (Button)findViewById(R.id.xianchangfujian_button);
		button1.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0){
				
			}
		});
		
		//删除图片  重新获取
		button2 = (Button)findViewById(R.id.shanchu_button);
		button2.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0){
				
			}
		});
		
		//对输入的问题页面进行保存
		button3 = (Button)findViewById(R.id.baocun_button);
		button3.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0){
				
			}
		});
		
		//确定问题输入后，点击“发布”来发布故障
		button4 = (Button)findViewById(R.id.fabu_button);
		button4.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0){
				
			}
		});
		
	}
}
