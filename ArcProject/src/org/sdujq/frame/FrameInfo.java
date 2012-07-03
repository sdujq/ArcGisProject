package org.sdujq.frame;

import android.view.View.OnClickListener;

public class FrameInfo {

	private AbsShow[][] views;
	private String title="����";
	private String leftText="����";
	private String rightText="�˻�";
	private OnClickListener rightListener;
	
	public AbsShow[][] getViews() {
		return views;
	}
	public void setViews(AbsShow[][] views) {
		this.views = views;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLeftText() {
		return leftText;
	}
	public void setLeftText(String leftText) {
		this.leftText = leftText;
	}
	public String getRightText() {
		return rightText;
	}
	public void setRightText(String rightText) {
		this.rightText = rightText;
	}
	public OnClickListener getRightListener() {
		return rightListener;
	}
	public void setRightListener(OnClickListener rightListener) {
		this.rightListener = rightListener;
	}
}
