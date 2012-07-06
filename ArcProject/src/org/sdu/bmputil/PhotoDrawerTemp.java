package org.sdu.bmputil;

import java.io.File;
import java.io.FileOutputStream;

import org.sdu.bmputil.ColorPickerDialog.OnColorChangedListener;
import org.sdu.gis.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;


public class PhotoDrawerTemp extends Activity implements
		OnSeekBarChangeListener {
	public static Bitmap shareBMP=null;
	boolean painterBoo = false, eraserBoo = false, shapeBoo = false,
			colorBoo = false;// 一级按钮
	boolean rec = false, circle = false, oval = false, line = false,
			rays = false, fill_p = true, stroke_p = false, shader_p = false;// 绘制多边形
	boolean derectx = false;// eback,使用橡皮后置true，控制画笔颜色恢复；derectx，手机方向为横着时为true
	Pic pic;
	static int count = -1, colorC = 0;// 判断多边形类型
	public int currentColor = -14016737;
	// 按钮
	ImageButton brush_b;
	ImageButton eraser_b;
	ImageButton save_b;
	ImageButton exit_b;
	ImageButton recycle_b;
	ImageButton polygon_b;
	ImageButton color_b;
	// ImageButton next_b;
	ImageButton rec_b;
	ImageButton cicle_b;
	// ImageButton edit_b;
	// 布局
	LinearLayout all;
	LinearLayout up;
	LinearLayout barBox;

	LinearLayout.LayoutParams rlp1;
	LinearLayout.LayoutParams rlp;
	LinearLayout.LayoutParams rlp2;
	// 拖动条
	SeekBar sb;
	//SeekBar alph;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		// 设置窗口属性
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		// 新建画布并显示
		pic = new Pic(this);

		sb = new SeekBar(this);
		sb.setMax(100);
		sb.setProgress(3);
		sb.setOnSeekBarChangeListener(this);

		/*alph = new SeekBar(this);
		alph.setMax(100);
		alph.setProgress(1);
		alph.setOnSeekBarChangeListener(this);*/

		// 画面布局
		all = new LinearLayout(this);
		up = new LinearLayout(this);
		barBox = new LinearLayout(this);
		barBox.setOrientation(LinearLayout.HORIZONTAL);
		barBox.addView(sb, new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.FILL_PARENT, 1));
	/*	barBox.addView(alph, new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.FILL_PARENT, 1));*/
		barBox.setLayoutParams(new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.FILL_PARENT));
		if (!derectx) {
			all.setOrientation(LinearLayout.VERTICAL);
			up.setOrientation(LinearLayout.HORIZONTAL);
			rlp1 = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,
					LayoutParams.FILL_PARENT);
		}
		if (derectx) {
			all.setOrientation(LinearLayout.HORIZONTAL);
			up.setOrientation(LinearLayout.VERTICAL);
			rlp1 = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
					LayoutParams.FILL_PARENT);
		}
		rlp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);

	/*	rlp2 = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);*/
		rlp2=new LinearLayout.LayoutParams(shareBMP.getWidth(), shareBMP.getHeight());
		brush_b = new ImageButton(this);
		brush_b.setBackgroundResource(R.drawable.pencil);
		eraser_b = new ImageButton(this);
		eraser_b.setBackgroundResource(R.drawable.eraser);
		color_b = new ImageButton(this);
		color_b.setBackgroundResource(R.drawable.colorpicker);
		polygon_b = new ImageButton(this);
		// polygon_b.setImageResource(R.drawable.pencil);
		// polygon_b.setBackgroundColor(Color.WHITE);
		save_b = new ImageButton(this);
		save_b.setBackgroundResource(R.drawable.save);
		recycle_b = new ImageButton(this);
		recycle_b.setBackgroundResource(R.drawable.redo);
		exit_b = new ImageButton(this);
		exit_b.setBackgroundResource(R.drawable.quit);
		rec_b = new ImageButton(this);
		rec_b.setBackgroundResource(R.drawable.rect);
		/*
		 * next_b = new ImageButton(this);
		 * next_b.setBackgroundResource(R.drawable.contin);
		 */
		/*
		 * edit_b = new ImageButton(this);
		 * edit_b.setBackgroundResource(R.drawable.edit);
		 */
		up.addView(brush_b);
		up.addView(rec_b);
		up.addView(eraser_b);
		// rl.addView(polygon_b);
		up.addView(color_b);
		// up.addView(edit_b);
		up.addView(save_b);
		// up.addView(next_b);
		up.addView(recycle_b);
		up.addView(exit_b);
		// up.addView(sb, rlp1);
		// up.addView(alph,rlp1);
		up.addView(barBox, rlp1);
		up.setBackgroundColor(Color.BLACK);
		all.addView(up);
		all.addView(pic, rlp2);
		setContentView(all);
		// TODO edit
		/*
		 * edit_b.setOnClickListener(new OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { Intent i = new Intent();
		 * i.setClass(PhotoDrawerTemp.this, EditAcitvity.class);
		 * startActivity(i); } });
		 */
		/*
		 * next_b.setOnClickListener(new OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { Intent it1 = new Intent();
		 * it1.setClass(PhotoDrawerTemp.this, CameraActivity.class);
		 * PhotoDrawerTemp.this.startActivity(it1);
		 * PhotoDrawerTemp.this.finish(); } });
		 */
		brush_b.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View arg0) {
				painterBoo = true;
				eraserBoo = false;
				shapeBoo = false;
				colorBoo = false;

				fill_p = false;
				stroke_p = false;
				shader_p = false;
				count = 0;
			}
		});
		eraser_b.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View arg0) {
				painterBoo = false;
				eraserBoo = true;
				shapeBoo = false;
				colorBoo = false;
				mPaint.setColor(Color.WHITE);
				fill_p = false;
				stroke_p = false;
				shader_p = false;
				count = 0;
			}
		});
		// TODO shapes
		rec_b.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				painterBoo = false;
				eraserBoo = false;
				shapeBoo = true;
				colorBoo = false;
				fill_p = false;
				stroke_p = true;

				line = false;
				rec = true;
				circle = false;
				oval = false;
				rays = false;
			}
		});
		polygon_b.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View arg0) {
				count++;

				painterBoo = false;
				eraserBoo = false;
				shapeBoo = true;
				colorBoo = false;

				fill_p = false;
				stroke_p = false;

				if (count % 8 == 0) {
					line = true;
					rec = false;
					circle = false;
					oval = false;
					rays = false;
				}
				if (count % 8 == 1) {
					line = false;
					rec = true;
					circle = false;
					oval = false;
					rays = false;
					fill_p = true;
					stroke_p = false;
				}
				if (count % 8 == 2) {
					line = false;
					rec = true;
					circle = false;
					oval = false;
					rays = false;
					fill_p = false;
					stroke_p = true;
				}
				if (count % 8 == 3) {
					line = false;
					rec = false;
					circle = true;
					oval = false;
					rays = false;
					fill_p = true;
					stroke_p = false;
				}
				if (count % 8 == 4) {
					line = false;
					rec = false;
					circle = true;
					oval = false;
					rays = false;
					fill_p = false;
					stroke_p = true;

				}
				if (count % 8 == 5) {
					line = false;
					rec = false;
					circle = false;
					oval = true;
					rays = false;
					fill_p = true;
					stroke_p = false;

				}
				if (count % 8 == 6) {
					line = false;
					rec = false;
					circle = false;
					oval = true;
					rays = false;
					fill_p = false;
					stroke_p = true;

				}
				if (count % 8 == 7) {
					line = false;
					rec = false;
					circle = false;
					oval = false;
					rays = true;

				}

			}
		});
		color_b.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View arg0) {
				OnColorChangedListener listen = new OnColorChangedListener() {
					@Override
					public void colorChanged(String key, int color) {
						currentColor = color;
						mPaint.setColor(currentColor);
					}
				};
				new ColorPickerDialog(PhotoDrawerTemp.this, listen, "123",
						currentColor, currentColor).show();
			}
		});
		exit_b.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View arg0) {
				PhotoDrawerTemp.this.finish();
			}
		});
		recycle_b.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View arg0) {
				Intent intent1 = new Intent();
				intent1.setClass(PhotoDrawerTemp.this, PhotoDrawerTemp.class);
				startActivity(intent1);
				PhotoDrawerTemp.this.finish();
			}
		});
		//TODO save
		save_b.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View arg0) {
				FileOutputStream fos;
				try {
					mCanvas.drawBitmap(tempBitmap, 0,0, null);
					File f = new File("/mnt/sdcard/temp100.jpg");
					fos = new FileOutputStream(f);
					mBitmap.compress(Bitmap.CompressFormat.PNG, 50, fos);
					fos.flush();
					fos.close();
					Log.e("qq", "saved");
					PhotoDrawerTemp.this.setResult(PhotoDrawerTemp.RESULT_OK);
					PhotoDrawerTemp.this.finish();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		});
	}

	// 拖动条，设置画笔粗细
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		mPaint.setStrokeWidth(sb.getProgress());
	//	mPaint.setAlpha((int) (1.0 * alph.getProgress() / 100 * 255));

	}

	public void onStartTrackingTouch(SeekBar seekBar) {
	};

	public void onStopTrackingTouch(SeekBar seekBar) {
	};

	private Paint mPaint;
	private Canvas mCanvas;
	private Bitmap mBitmap;
	Bitmap tempBitmap;
	private static int mScreenWidth;
	private static int mScreenHeight;

	public class Pic extends View {
		private float mX, mY, fX, fY, r;
		private Path mPath;

		private static final float TOUCH_TOLERANCE = 4;

		private Paint mBitmapPaint;

		int BackGroundColor = -1;
		Canvas temptCanvas;

		public Pic(Context c) {
			super(c);
			mPaint = new Paint();
			mPaint.setAntiAlias(true);
			mPaint.setDither(true);
			mPaint.setColor(BackGroundColor);
			// mPaint.setStyle(Paint.Style.FILL);
			mPaint.setStrokeJoin(Paint.Join.ROUND);
			mPaint.setStrokeCap(Paint.Cap.ROUND);
			mPaint.setStrokeWidth(8);
			/*DisplayMetrics dm = getResources().getDisplayMetrics();
			mScreenWidth = dm.widthPixels;
			mScreenHeight = dm.heightPixels;*/
			mBitmap = Bitmap.createBitmap(shareBMP.getWidth(), shareBMP.getHeight(),
					Bitmap.Config.ARGB_8888);
			tempBitmap = Bitmap.createBitmap(shareBMP.getWidth(), shareBMP.getHeight(),
					Bitmap.Config.ARGB_8888);
			temptCanvas = new Canvas(tempBitmap);
			temptCanvas.drawColor(Color.TRANSPARENT);
			Bitmap backImg = null;
			backImg = shareBMP;
			/*backImg = BitmapTool.lessenBitmap(backImg, mScreenWidth,
					mScreenHeight);*/
			mCanvas = new Canvas(mBitmap);
			mPath = new Path();
			mCanvas.drawRect(0, 0, mScreenWidth, mScreenHeight, mPaint);
			if (backImg != null) {
				mCanvas.drawBitmap(backImg, 0, 0, mPaint);
			}
			mPaint.setColor(Color.BLACK);
			mPaint.setStyle(Paint.Style.STROKE);
			mBitmapPaint = new Paint(Paint.DITHER_FLAG);
		}

		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			if ((painterBoo) && (!eraserBoo) && (!shapeBoo) && (!colorBoo)) {
				mPaint.setAntiAlias(true);
				mPaint.setDither(true);
				mPaint.setColor(currentColor);
				mPaint.setStyle(Paint.Style.STROKE);
				mPaint.setStrokeJoin(Paint.Join.ROUND);
				mPaint.setStrokeCap(Paint.Cap.ROUND);
				canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);
				canvas.drawBitmap(tempBitmap, 0, 0, null);
				canvas.drawPath(mPath, mPaint);
			}
			if ((!painterBoo) && (eraserBoo) && (!shapeBoo) && (!colorBoo)) {
				mPaint.setShader(null);

				mPaint.setStyle(Paint.Style.STROKE);// 空心
				mPaint.setStrokeJoin(Paint.Join.ROUND);
				mPaint.setStrokeCap(Paint.Cap.ROUND);

				// canvas.drawColor(BackGroundColor);
				canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);
				canvas.drawBitmap(tempBitmap, 0, 0, null);
				canvas.drawPath(mPath, mPaint);

			}
			if ((!painterBoo) && (!eraserBoo) && (!shapeBoo) && (!colorBoo)) {
				// canvas.drawColor(BackGroundColor);
				canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);
				canvas.drawBitmap(tempBitmap, 0, 0, null);

			}

			// 绘制图形
			if ((!painterBoo) && (!eraserBoo) && (shapeBoo) && (!colorBoo)) {
				mPaint.setStyle(Paint.Style.STROKE);
				mPaint.setStrokeJoin(Paint.Join.ROUND);
				mPaint.setStrokeCap(Paint.Cap.ROUND);
				if (stroke_p)
					mPaint.setStyle(Paint.Style.STROKE);
				if (fill_p)
					mPaint.setStyle(Paint.Style.FILL);
				canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);
				canvas.drawBitmap(tempBitmap, 0, 0, null);
				canvas.drawPath(mPath, mPaint);
			}
		}

		private void onTouchDown(float x, float y) {
			// 画笔
			if ((painterBoo) && (!eraserBoo) && (!shapeBoo) && (!colorBoo)) {

				mPath.reset();// 将上次的路径保存起来，并重置新的路径。
				mPath.moveTo(x, y);// 设置新的路径“轮廓”的开始
				mX = x;
				mY = y;
			}
			// 擦除
			if ((!painterBoo) && (eraserBoo) && (!shapeBoo) && (!colorBoo)) {
				mPaint.setColor(BackGroundColor);
				mPath.reset();
				mPath.moveTo(x, y);
				mX = x;
				mY = y;
			}

			// 绘制图形
			if ((!painterBoo) && (!eraserBoo) && (shapeBoo) && (!colorBoo)) {
				fX = x;
				fY = y;
			}

			// 填充颜色
			if ((!painterBoo) && (!eraserBoo) && (!shapeBoo) && (colorBoo)) {

			}

		}

		private void onTouchMove(float x, float y) {
			// 画笔
			if ((painterBoo) && (!eraserBoo) && (!shapeBoo) && (!colorBoo)) {
				float dx = Math.abs(x - mX);
				float dy = Math.abs(y - mY);
				if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
					mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
					mX = x;
					mY = y;
				}

			}
			// 擦除
			if ((!painterBoo) && (eraserBoo) && (!shapeBoo) && (!colorBoo)) {
				float dx = Math.abs(x - mX);
				float dy = Math.abs(y - mY);
				if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
					mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
					mX = x;
					mY = y;
				}
			}
			// 绘制图形
			if ((!painterBoo) && (!eraserBoo) && (shapeBoo) && (!colorBoo)) {
				if (rays) {
					temptCanvas.drawLine(fX, fY, x, y, mPaint);
				}

			}

		}

		private void onTouchUp(float x, float y) {
			// 画笔
			if ((painterBoo) && (!eraserBoo) && (!shapeBoo) && (!colorBoo)) {
				mPath.lineTo(mX, mY);
				mPaint.setXfermode(null);
				temptCanvas.drawPath(mPath, mPaint);// 手指离开屏幕后，绘制创建的“所有”路径。
				// kill this so we don't double draw
				mPath.reset();
			}
			// 擦除
			if ((!painterBoo) && (eraserBoo) && (!shapeBoo) && (!colorBoo)) {
				mPath.lineTo(mX, mY);
				mPaint.setXfermode(new PorterDuffXfermode(Mode.DST_OUT));
				temptCanvas.drawPath(mPath, mPaint);
				mPath.reset();
			}

			// 绘制图形
			if ((!painterBoo) && (!eraserBoo) && (shapeBoo) && (!colorBoo)) {
				mPaint.setXfermode(null);

				if (rec)
					temptCanvas.drawRect(fX, fY, x, y, mPaint);
				else if (circle) {
					double r2 = (Math.pow(x - fX, 2) + Math.pow(y - fY, 2));
					r = (float) Math.sqrt(r2) / 2;
					temptCanvas.drawCircle(fX + r, fY + r, r, mPaint);
				} else if (oval) {
					RectF re = new RectF(fX, fY, x, y);
					temptCanvas.drawOval(re, mPaint);
				} else if (line) {
					temptCanvas.drawLine(fX, fY, x, y, mPaint);
				} else if (rays) {
					temptCanvas.drawLine(fX, fY, x, y, mPaint);
				}
				mPath.reset();
			}

		}

		@Override
		public boolean onTouchEvent(MotionEvent event) {
			float x = event.getX();
			float y = event.getY();
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:// 手指开始按压屏幕，这个动作包含了初始化位置
				onTouchDown(x, y);
				invalidate();// 刷新画布，重新运行onDraw（）方法
				break;
			case MotionEvent.ACTION_MOVE:// 手指按压屏幕时，位置的改变触发，这个方法在ACTION_DOWN和ACTION_UP之间。

				onTouchMove(x, y);
				invalidate();
				break;
			case MotionEvent.ACTION_UP:// 手指离开屏幕，不再按压屏幕
				onTouchUp(x, y);
				invalidate();
				break;
			default:
				break;
			}
			return true;
		}
	}

	public AlertDialog getDialog(View mView) {
		AlertDialog dio = new AlertDialog.Builder(this).create();
		dio.setView(mView);
		return dio;
	}

	public View getView(int layout) {
		View mView = null;
		mView = View.inflate(this, layout, null);
		return mView;
	}
}
