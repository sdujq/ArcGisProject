package org.sdu.pojo;

import java.io.Serializable;

import com.tgb.lk.ahibernate.annotation.Column;
import com.tgb.lk.ahibernate.annotation.Id;
import com.tgb.lk.ahibernate.annotation.Table;
@Table(name="t_roadLine")
public class RoadLine implements Serializable{

	private static final long serialVersionUID = -3486524065161934542L;
	@Id
	@Column(name="id")
	private int id;
	@Column(name="points")
	private String points;
	@Column(name="name")
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPoints() {
		return points;
	}
	public void setPoints(String points) {
		this.points = points;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
