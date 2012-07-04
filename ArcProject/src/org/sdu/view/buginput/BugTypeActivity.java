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
      //  setContentView(R.id.bugtype_spinner);   
  
     //   bugtype_spinner = (Spinner) findViewById(R.id.bugtype_spinner);     
  
        //����ѡ������ArrayAdapter��������   
        adapter = ArrayAdapter.createFromResource(this, R.array.plantes, android.R.layout.simple_spinner_item);   
  
        //���������б�ķ��    
 adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);   
  
        //��adapter2 ��ӵ�spinner��   
        bugtype_spinner.setAdapter(adapter);   
  
        //����¼�Spinner�¼�����     
        bugtype_spinner.setOnItemSelectedListener(new SpinnerXMLSelectedListener());   
  
        //����Ĭ��ֵ   
        bugtype_spinner.setVisibility(View.VISIBLE);   
           
    }   
       
    //ʹ��XML��ʽ����   
    class SpinnerXMLSelectedListener implements OnItemSelectedListener{   
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,   
                long arg3) {   
            view.setText("�������"+adapter.getItem(arg2));   
        }   
  
        public void onNothingSelected(AdapterView<?> arg0) {   
               
        }   
           
    }   
}