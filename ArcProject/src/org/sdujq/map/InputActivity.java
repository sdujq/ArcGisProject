package org.sdujq.map;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import org.sdu.bmputil.BitmapTool;
import org.sdu.gis.R;
import org.sdujq.db.dao.RecordDao;
import org.sdujq.pojo.Record;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
				Record r = new Record();
				r.setTag(tag.getText().toString());
				String[] loc = location.getText().toString().split(",");
				try {
					r.setLocationX(Double.parseDouble(loc[0]));
					r.setLocationY(Double.parseDouble(loc[1]));
				} catch (Exception e) {
					r.setLocationX(0);
					r.setLocationY(0);
				}
				r.setTime(new Date().getTime());
				try {
					ContentResolver cr = getContentResolver();
					InputStream in = cr.openInputStream(Uri.fromFile(new File(
							"/mnt/sdcard/temp100.jpg")));
					Bitmap bitmap = BitmapFactory.decodeStream(in);
					r.setPhoto(BitmapTool.Bitmap2Bytes(bitmap));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
					r.setPhoto(null);
				}
				RecordDao rdao=new RecordDao();
				rdao.insert(r);
				Home.home_con.refreshlst();
				Home.home_con.viewPager.setCurrentItem(1);
			}
		});
	}
}
