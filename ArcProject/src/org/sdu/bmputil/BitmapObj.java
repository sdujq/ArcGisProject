package org.sdu.bmputil;

import java.io.Serializable;

import android.graphics.Bitmap;

public class BitmapObj implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1815770776804414443L;
	private Bitmap bmp;
	public Bitmap getBmp() {
		return bmp;
	}
	public void setBmp(Bitmap bmp) {
		this.bmp = bmp;
	}
}
