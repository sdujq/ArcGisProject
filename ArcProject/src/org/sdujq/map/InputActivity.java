package org.sdujq.map;

import java.io.File;

import org.sdu.gis.R;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class InputActivity extends Activity {
	public final static int SELECT_PHOTO_REQUEST_CODE = 1;
	public final static int TAKE_PHOTO_REQUEST_CODE = 2;
	public final static int CUT_PHOTO_REQUEST_CODE = 3;

	EditText tag, location;
	Button photo, save;

	@Override
	public void onCreate(Bundle saved) {
		super.onCreate(saved);
		setContentView(R.layout.inputtable);
		init();
	}

	public void init() {
		tag = (EditText) findViewById(R.id.tag);
		location = (EditText) findViewById(R.id.location);
		photo = (Button) findViewById(R.id.takephoto);
		save = (Button) findViewById(R.id.save);

		photo.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				File out = new File("/mnt/sdcard/temp100.jpg");
				Uri uri = Uri.fromFile(out);
				intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
				((Activity) Home.home_con).startActivityForResult(intent,
						Photo.TAKE_PHOTO_REQUEST_CODE);
			}
		});

		save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
	}
}
