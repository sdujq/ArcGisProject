package org.sdu.bmputil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.util.Log;

public class BitmapTool {

	public static Bitmap Bytes2Bimap(byte[] b) {
		Log.e("qq", "byte length is"+b.length);
		if (b.length != 0) {
			return BitmapFactory.decodeByteArray(b, 0, b.length);
		} else {
			return null;

		}

	}
	public static Bitmap drawableToBitmap(Drawable drawable) {

	       

        Bitmap bitmap = Bitmap.createBitmap(

                                        drawable.getIntrinsicWidth(),

                                        drawable.getIntrinsicHeight(),

                                        drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888

                                                        : Bitmap.Config.RGB_565);

        Canvas canvas = new Canvas(bitmap);

        //canvas.setBitmap(bitmap);

        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());

        drawable.draw(canvas);

        return bitmap;

}
	public static byte[] Bitmap2Bytes(Bitmap bm) {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);

		try {
			baos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] b=baos.toByteArray();
		Log.e("qq", "byte length is"+b.length);

		return b;

	}

	/*
	 * public static Bitmap Bytes2Bimap(byte[] b) { try { return ((BitmapObj)
	 * Trans.getObjectFromBytes(b)).getBmp(); } catch (Exception e) {
	 * e.printStackTrace(); } return null; }
	 * 
	 * public static byte[] Bitmap2Bytes(Bitmap bm) { BitmapObj bo = new
	 * BitmapObj(); bo.setBmp(bm); try { return Trans.getBytesFromObject(bo); }
	 * catch (Exception e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } return null; }
	 */

	/**
	 * �ϳ�ˮӡͼƬ
	 * 
	 * @param src
	 *            the bitmap object you want proecss
	 * @param watermark
	 *            the water mark above the src
	 * @return return a bitmap object ,if paramter's length is 0,return null
	 */
	public static Bitmap createWaterBitmap(Bitmap src, Bitmap watermark) {
		String tag = "createBitmap";
		Log.d(tag, "create a new bitmap");
		if (src == null) {
			return null;
		}

		int w = src.getWidth();
		int h = src.getHeight();
		int ww = watermark.getWidth();
		int wh = watermark.getHeight();
		// create the new blank bitmap
		Bitmap newb = Bitmap.createBitmap(w, h, Config.ARGB_8888);// ����һ���µĺ�SRC���ȿ��һ����λͼ
		Canvas cv = new Canvas(newb);
		// draw src into
		cv.drawBitmap(src, 0, 0, null);// �� 0��0���꿪ʼ����src
		// draw watermark into
		cv.drawBitmap(watermark, w - ww + 5, h - wh + 5, null);// ��src�����½ǻ���ˮӡ
		// save all clip
		cv.save(Canvas.ALL_SAVE_FLAG);// ����
		// store
		cv.restore();// �洢
		return newb;
	}

	/**
	 * lessen the bitmap
	 * 
	 * @param src
	 *            bitmap
	 * @param destWidth
	 *            the dest bitmap width
	 * @param destHeigth
	 * @return new bitmap if successful ,oherwise null
	 */
	public static Bitmap lessenBitmap(Bitmap src, int destWidth, int destHeigth) {
		if (src == null) {
			return null;
		}
		int w = src.getWidth();// Դ�ļ��Ĵ�С
		int h = src.getHeight();
		// calculate the scale - in this case = 0.4f
		float scaleWidth = ((float) destWidth) / w;// �����С����
		float scaleHeight = ((float) destHeigth) / h;// �߶���С����
		Matrix m = new Matrix();// ����
		m.postScale(scaleWidth, scaleHeight);// ���þ������
		Bitmap resizedBitmap = Bitmap.createBitmap(src, 0, 0, w, h, m, true);// ֱ�Ӱ��վ���ı�����Դ�ļ��������
		return resizedBitmap;
	}

	public static Bitmap lessenBitmap(Bitmap src, float scale) {
		if (src == null) {
			return null;
		}
		int w = src.getWidth();// Դ�ļ��Ĵ�С
		int h = src.getHeight();
		Matrix m = new Matrix();// ����
		m.postScale(scale, scale);// ���þ������
		Bitmap resizedBitmap = Bitmap.createBitmap(src, 0, 0, w, h, m, true);// ֱ�Ӱ��վ���ı�����Դ�ļ��������
		return resizedBitmap;
	}

}
