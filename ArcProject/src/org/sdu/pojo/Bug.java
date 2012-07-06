package org.sdu.pojo;

import java.io.Serializable;

import com.tgb.lk.ahibernate.annotation.Column;
import com.tgb.lk.ahibernate.annotation.Id;
import com.tgb.lk.ahibernate.annotation.Table;

@Table(name="t_bug")
public class Bug implements Serializable{

	private static final long serialVersionUID = -3586017449467417688L;
	@Id
	@Column(name="id")
	private int id;
	@Column(name="bugTypeId")
	private int bugTypeId;
	@Column(name="content")
	private String content;
	@Column(name="point")
	private String point;
	@Column(name="address")
	private String address;
	@Column(name="tag")
	private String tag;
	@Column(name="attachment")
	private byte[] attachment;
	@Column(name="state")
	private String state;
	@Column(name="userId")
	private int userId;
	@Column(name="time")
	private long time;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBugTypeId() {
		return bugTypeId;
	}
	public void setBugTypeId(int bugTypeId) {
		this.bugTypeId = bugTypeId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPoint() {
		return point;
	}
	public void setPoint(String point) {
		this.point = point;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public byte[] getAttachment() {
		return attachment;
	}
	public void setAttachment(byte[] attachment) {
		this.attachment = attachment;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
}
