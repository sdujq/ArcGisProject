package org.sdu.view.buginput;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.sdu.bmputil.BitmapTool;
import org.sdu.bmputil.PhotoDrawerTemp;
import org.sdu.dao.BugDao;
import org.sdu.dao.BugTypeDao;
import org.sdu.dbaction.Action;
import org.sdu.gis.R;
import org.sdu.pojo.Bug;
import org.sdu.pojo.BugType;
import org.sdujq.map.InputActivity;
import org.sdujq.map.Photo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.maxtech.common.AddressTask;
import com.maxtech.common.gps.GpsTask;
import com.maxtech.common.gps.GpsTask.GpsData;
import com.maxtech.common.gps.GpsTaskCallBack;
import com.maxtech.common.gps.IAddressTask.MLocation;

public class BugInputActivity extends Activity {
	Context mContext = null;

	// 照片文件绝对路径

	public TextView textView1, textView2, textView3, textView4, textView5,
			textView6, textView7;
	public EditText editText1, editText3, editText4, editText5, editText6;
	public Spinner bugtype_spinner;
	public Button button1, button2, button3, button4, editPhoto, clear, apn,
			gps, wifi;
	public ImageView image;
	private AlertDialog dialog = null;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.debug);
		mContext = this;

		textView1 = (TextView) findViewById(R.id.wentijianshu_textView);
		textView2 = (TextView) findViewById(R.id.wentileibie_textView);
		textView3 = (TextView) findViewById(R.id.guzhangneirong_textView);
		textView4 = (TextView) findViewById(R.id.guzhangzuobiao_textView);
		textView5 = (TextView) findViewById(R.id.guzhangdizhi_textView);
		textView6 = (TextView) findViewById(R.id.wentibeizhu_textView);
		textView7 = (TextView) findViewById(R.id.xianchangfujian_textView);

		editText1 = (EditText) findViewById(R.id.wentijianshu_editText);
		editText3 = (EditText) findViewById(R.id.guzhangneirong_editText);
		editText4 = (EditText) findViewById(R.id.guzhangzuobiao_editText);
		editText5 = (EditText) findViewById(R.id.guzhangdizhi_editText);
		editText6 = (EditText) findViewById(R.id.wentibeizhu_editText);
		image = (ImageView) findViewById(R.id.imageView1);
		bugtype_spinner = (Spinner) findViewById(R.id.wentileibie_spinner);
		ArrayAdapter<BugType> adapter = new ArrayAdapter<BugType>(
				BugInputActivity.this, android.R.layout.simple_spinner_item);
		List<BugType> typeList = (new BugTypeDao(this)).find();
		for (BugType b : typeList) {
			adapter.add(b);
		}
		bugtype_spinner.setAdapter(adapter);
		// 现场附件 获取图片（拍照片）
		button1 = (Button) findViewById(R.id.xianchangfujian_button);
		button1.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				File out = new File("/mnt/sdcard/temp100.jpg");
				Uri uri = Uri.fromFile(out);
				intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
				BugInputActivity.this.startActivityForResult(intent,
						Photo.TAKE_PHOTO_REQUEST_CODE);
			}
		});

		// 删除图片 重新获取
		button2 = (Button) findViewById(R.id.shanchu_button);
		button2.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				image.setImageBitmap(null);
			}
		});

		// 确定问题输入后，点击“发布”来发布故障
		button4 = (Button) findViewById(R.id.fabu_button);
		button4.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				Bug bug = new Bug();
				bug.setAddress(editText5.getText().toString());
				if (image.getDrawable() != null) {
					Bitmap bitmap = BitmapTool.drawableToBitmap(image
							.getDrawable());
					bug.setAttachment(BitmapTool.Bitmap2Bytes(bitmap));
				}
				bug.setBugTypeId(((BugType) bugtype_spinner.getSelectedItem())
						.getId());
				bug.setContent(editText3.getText().toString());
				bug.setState("未完成");
				bug.setTag(editText6.getText().toString());
				bug.setPoint(editText4.getText().toString());
				try {
					bug.setUserId(Action.currentUser.getId());
				} catch (Exception e) {
					bug.setUserId(0);
				}
				bug.setTime(new Date().getTime());
				new BugDao(BugInputActivity.this).insert(bug);
				clear();
				Toast.makeText(BugInputActivity.this, "保存成功", 0).show();
			}
		});

		editPhoto = (Button) findViewById(R.id.editphoto);
		editPhoto.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				PhotoDrawerTemp.shareBMP = BitmapTool.drawableToBitmap(image
						.getDrawable());
				Intent it = new Intent();
				it.setClass(BugInputActivity.this, PhotoDrawerTemp.class);
				BugInputActivity.this.startActivityForResult(it, 111);
			}
		});

		clear = (Button) findViewById(R.id.button5);
		clear.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				clear();
			}
		});

		editText4.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View v) {

				return false;
			}
		});
		dialog = new ProgressDialog(this);
		dialog.setTitle("请稍等...");
		dialog.setMessage("正在定位...");

		apn = (Button) findViewById(R.id.apn);
		wifi = (Button) findViewById(R.id.wifi);
		gps = (Button) findViewById(R.id.gps);

		apn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				do_apn();
			}
		});
		gps.setOnClickListener(new OnClickListener() {

			@SuppressWarnings("unchecked")
			@Override
			public void onClick(View v) {
				dialog.show();
				GpsTask gpstask = new GpsTask(BugInputActivity.this,
						new GpsTaskCallBack() {

							@Override
							public void gpsConnectedTimeOut() {
								dialog.dismiss();
								Toast.makeText(BugInputActivity.this,
										"GPS获取超时", 0).show();
							}

							@Override
							public void gpsConnected(GpsData gpsdata) {
								do_gps(gpsdata);
								dialog.dismiss();
							}

						}, 3000);
				gpstask.execute();
			}
		});
		wifi.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				do_wifi();
			}
		});
	}

	public void clear() {
		editText1.setText("");
		editText3.setText("");
		editText4.setText("");
		editText5.setText("");
		editText6.setText("");
		image.setImageBitmap(null);
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == InputActivity.TAKE_PHOTO_REQUEST_CODE) {

			if (resultCode == RESULT_OK) {
				Uri uri = Uri.fromFile(new File("/mnt/sdcard/temp100.jpg"));
				if (uri != null) {
					final Intent intent1 = new Intent(
							"com.android.camera.action.CROP");
					intent1.setDataAndType(uri, "image/*");
					intent1.putExtra("crop", "true");
					intent1.putExtra("aspectX", 1);
					intent1.putExtra("aspectY", 1);
					intent1.putExtra("outputX", 300);
					intent1.putExtra("outputY", 300);
					intent1.putExtra("return-data", true);
					intent1.putExtra("output",
							Uri.fromFile(new File("/mnt/sdcard/temp100.jpg")));
					intent1.putExtra("outputFormat", "JPEG");
					startActivityForResult(intent1,
							Photo.CUT_PHOTO_REQUEST_CODE);
				}
			}
		} else if (requestCode == Photo.CUT_PHOTO_REQUEST_CODE) {
			if (resultCode == RESULT_OK && data != null) {
				ContentResolver cr = getContentResolver();
				try {
					InputStream in = cr.openInputStream(Uri.fromFile(new File(
							"/mnt/sdcard/temp100.jpg")));
					Bitmap bitmap = BitmapFactory.decodeStream(in);
					image.setImageBitmap(bitmap);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		} else if (requestCode == 111 && resultCode == RESULT_OK) {
			ContentResolver cr = getContentResolver();
			try {
				InputStream in = cr.openInputStream(Uri.fromFile(new File(
						"/mnt/sdcard/temp100.jpg")));
				Bitmap bitmap = BitmapFactory.decodeStream(in);
				image.setImageBitmap(bitmap);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	private void do_apn() {
		new AsyncTask<Void, Void, MLocation>() {

			@Override
			protected MLocation doInBackground(Void... params) {
				MLocation location = null;
				try {
					location = new AddressTask(BugInputActivity.this,
							AddressTask.DO_APN).doApnPost();
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (location == null)
					return null;
				return location;
			}

			@Override
			protected void onPreExecute() {
				dialog.show();
				super.onPreExecute();
			}

			@Override
			protected void onPostExecute(MLocation result) {
				if (result == null) {
					Toast.makeText(BugInputActivity.this, "基站定位失败", 0).show();
				} else {
					editText4.setText(result.Latitude + "," + result.Longitude);
				}
				dialog.dismiss();
				super.onPostExecute(result);
			}

		}.execute();
	}

	private void do_gps(final GpsData gpsdata) {
		new AsyncTask<Void, Void, MLocation>() {

			@Override
			protected MLocation doInBackground(Void... params) {
				MLocation location = null;
				try {
					Log.i("do_gpspost", "经纬度：" + gpsdata.getLatitude() + "----"
							+ gpsdata.getLongitude());
					location = new AddressTask(BugInputActivity.this,
							AddressTask.DO_GPS).doGpsPost(
							gpsdata.getLatitude(), gpsdata.getLongitude());
				} catch (Exception e) {
					e.printStackTrace();
				}
				return location;
			}

			@Override
			protected void onPreExecute() {
				dialog.show();
				super.onPreExecute();
			}

			@Override
			protected void onPostExecute(MLocation result) {
				if (result != null) {
					editText4.setText(result.Latitude + "," + result.Longitude);
				}
				dialog.dismiss();
				super.onPostExecute(result);
			}

		}.execute();
	}

	private void do_wifi() {
		new AsyncTask<Void, Void, MLocation>() {

			@Override
			protected MLocation doInBackground(Void... params) {
				MLocation location = null;
				try {
					location = new AddressTask(BugInputActivity.this,
							AddressTask.DO_WIFI).doWifiPost();
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (location == null)
					return null;
				return location;
			}

			@Override
			protected void onPreExecute() {
				dialog.show();
				super.onPreExecute();
			}

			@Override
			protected void onPostExecute(MLocation result) {
				if (result != null) {
					editText4.setText(result.Latitude + "," + result.Longitude);
				} else {
					Toast.makeText(BugInputActivity.this, "WIFI定位失败了", 0)
							.show();
				}

				dialog.dismiss();
				super.onPostExecute(result);
			}

		}.execute();
	}
}
