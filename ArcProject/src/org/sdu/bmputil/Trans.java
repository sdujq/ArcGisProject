package org.sdu.bmputil;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class Trans {

	/**
	 * 文件转化为字节数组
	 * 
	 */
	public static byte[] getBytesFromFile(File f) {
		if (f == null) {
			return null;
		}
		try {
			FileInputStream stream = new FileInputStream(f);
			ByteArrayOutputStream out = new ByteArrayOutputStream(1000);
			byte[] b = new byte[1000];
			int n;
			while ((n = stream.read(b)) != -1)
				out.write(b, 0, n);
			stream.close();
			out.close();
			return out.toByteArray();
		} catch (IOException e) {
		}
		return null;
	}

	/**
	 * 把字节数组保存为一个文件
	 * 
	 */
	public static File getFileFromBytes(byte[] b, String outDir,String outputFile) {
		File dir = new File(outDir);
		if (!dir.exists()) {
			dir.mkdir();
		}
		BufferedOutputStream stream = null;
		File file = null;
		try {
			file = new File(outDir+"/" + outputFile);
			FileOutputStream fstream = new FileOutputStream(file);
			stream = new BufferedOutputStream(fstream);
			stream.write(b);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return file;
	}

	/**
	 * 从字节数组获取对象
	 * 
	 */
	public static Object getObjectFromBytes(byte[] objBytes) throws Exception {
		if (objBytes == null || objBytes.length == 0) {
			return null;
		}
		ByteArrayInputStream bi = new ByteArrayInputStream(objBytes);
		ObjectInputStream oi = new ObjectInputStream(bi);
		return oi.readObject();
	}

	/**
	 * 从对象获取一个字节数组
	 * 
	 */
	public static byte[] getBytesFromObject(Serializable obj) throws Exception {
		if (obj == null) {
			return null;
		}
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		ObjectOutputStream oo = new ObjectOutputStream(bo);
		oo.writeObject(obj);
		return bo.toByteArray();
	}

	/**
	 * 根据文件得到Drawable
	 * 
	 * @param name
	 * @return
	 */
	public static Drawable getImg(File name) {
		try {
			FileInputStream is = new FileInputStream(name);
			Bitmap bmp = BitmapFactory.decodeStream(is);
			return new BitmapDrawable(bmp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void writeObject(String outFile, Object object) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(
					new BufferedOutputStream(new FileOutputStream(outFile)));
			out.writeObject(object);
			out.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	public static Object readObject(String filePath) {
		File inFile = new File(filePath);
		if (!inFile.exists()) {
			return null;
		}
		Object o = null;
		try {
			ObjectInputStream in = new ObjectInputStream(
					new BufferedInputStream(new FileInputStream(inFile)));
			o = in.readObject();
			in.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return o;
	}
}
