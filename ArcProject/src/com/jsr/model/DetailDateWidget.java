/**
 * 
 */
package com.jsr.model;
import android.provider.BaseColumns;

/**
 * @author HANJUE
 *
 */
public class DetailDateWidget {
	public static final String DAYOFWEEK="dayofweek";
	public static final String LOW="low";
	public static final String HIGHT="hight";
	public static final String ICON="icon";
	public static final String CONDITION="condition";
	public static final String WIDGET_ID="wigetid";
	public static final String[] detailProjection = new String[]{
		BaseColumns._ID,
		DAYOFWEEK, 
		LOW,
		HIGHT,
		ICON,
		CONDITION,
		WIDGET_ID		
	};
	private Integer id;
	private String dayofweek;
	private Integer low;
	private Integer hight;
	private String icon;
	private String condition;
	private Integer widgetid;
   

public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}

public String getDayofweek() {
	return dayofweek;
}
public void setDayofweek(String dayofweek) {
	this.dayofweek = dayofweek;
}
public Integer getLow() {
	return low;
}
public void setLow(Integer low) {
	this.low = low;
}
public Integer getHight() {
	return hight;
}
public void setHight(Integer hight) {
	this.hight = hight;
}
public String getIcon() {
	return icon;
}
public void setIcon(String icon) {
	this.icon = icon;
}
public String getCondition() {
	return condition;
}
public void setCondition(String condition) {
	this.condition = condition;
}
public Integer getWidgetid() {
	return widgetid;
}
public void setWidgetid(Integer widgetid) {
	this.widgetid = widgetid;
}


}