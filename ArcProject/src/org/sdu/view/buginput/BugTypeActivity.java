package org.sdu.view.buginput;

import org.sdu.gis.R;

import android.app.Activity;   
import android.os.Bundle;   
import android.view.View;   
import android.widget.AdapterView;   
import android.widget.AdapterView.OnItemSelectedListener;   
import android.widget.ArrayAdapter;   
import android.widget.Spinner;   
import android.widget.TextView;   
  
public class BugTypeActivity extends Activity {   
       
    private TextView view;   
    private Spinner bugtype_spinner;   
    private ArrayAdapter adapter;   
  
    @Override  
    protected void onCreate(Bundle savedInstanceState) {   
        // TODO Auto-generated method stub   
        super.onCreate(savedInstanceState);   
        setContentView(R.id.bugtype_spinner);   
  
        bugtype_spinner = (Spinner) findViewById(R.id.bugtype_spinner);     
  
        //将可选内容与ArrayAdapter连接起来   
        adapter = ArrayAdapter.createFromResource(this, R.array.plantes, android.R.layout.simple_spinner_item);   
  
        //设置下拉列表的风格    
 adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);   
  
        //将adapter2 添加到spinner中   
        bugtype_spinner.setAdapter(adapter);   
  
        //添加事件Spinner事件监听     
        bugtype_spinner.setOnItemSelectedListener(new SpinnerXMLSelectedListener());   
  
        //设置默认值   
        bugtype_spinner.setVisibility(View.VISIBLE);   
           
    }   
       
    //使用XML形式操作   
    class SpinnerXMLSelectedListener implements OnItemSelectedListener{   
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,   
                long arg3) {   
            view.setText("问题类别"+adapter.getItem(arg2));   
        }   
  
        public void onNothingSelected(AdapterView<?> arg0) {   
               
        }   
           
    }   
}