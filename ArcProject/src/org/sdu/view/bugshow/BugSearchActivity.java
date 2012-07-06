package org.sdu.view.bugshow;

import org.sdu.gis.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class BugSearchActivity extends Activity {
    /** Called when the activity is first created. */
	 Spinner spinner;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bug_search);
        ArrayAdapter<CharSequence>adapter=ArrayAdapter.createFromResource(this, R.array.bug_types, android.R.layout.simple_spinner_item);
        spinner=(Spinner) this.findViewById(R.id.spinner1);
        spinner.setAdapter(adapter);
        spinner.setOnItemClickListener(new OnItemClickListener() {
        	
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
			}
		});
    }
    
}