package org.sdu.pojo;

import java.io.Serializable;

import com.tgb.lk.ahibernate.annotation.Column;
import com.tgb.lk.ahibernate.annotation.Id;
import com.tgb.lk.ahibernate.annotation.Table;

@Table(name="t_task")
public class Task implements Serializable{

	private static final long serialVersionUID = 1907598005390500896L;

	@Id
	@Column(name="id")
	private int id;
	@Column(name="taskType")
	private String taskType;
	@Column(name="taskName")
	private String taskName;
	@Column(name="roadName")
	private String roadName;
	@Column(name="inspectionPersonId")
	private int inspectionPersonId;
	@Column(name="createPersonId")
	private int createPersonId;
	@Column(name="roadLineId")
	private int roadLineId;
	@Column(name="startTime")
	private long startTime;
	@Column(name="endTime")
	private long endTime;
	@Column(name="realseTime")
	private long realseTime;
	@Column(name="content")
	private String content;
	@Column(name="cycle")
	private long cycle;
	@Column(name="tag")
	private String tag;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTaskType() {
		return taskType;
	}
	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getRoadName() {
		return roadName;
	}
	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}
	public int getInspectionPersonId() {
		return inspectionPersonId;
	}
	public void setInspectionPersonId(int inspectionPersonId) {
		this.inspectionPersonId = inspectionPersonId;
	}
	public int getCreatePersonId() {
		return createPersonId;
	}
	public void setCreatePersonId(int createPersonId) {
		this.createPersonId = createPersonId;
	}
	public int getRoadLineId() {
		return roadLineId;
	}
	public void setRoadLineId(int roadLineId) {
		this.roadLineId = roadLineId;
	}
	public long getStartTime() {
		return startTime;
	}
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	public long getEndTime() {
		return endTime;
	}
	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
	public long getRealseTime() {
		return realseTime;
	}
	public void setRealseTime(long realseTime) {
		this.realseTime = realseTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public long getCycle() {
		return cycle;
	}
	public void setCycle(long cycle) {
		this.cycle = cycle;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
}
