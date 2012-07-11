/**
 * 
 */
package com.jsr.model;

import java.util.ArrayList;

/**
 * @author HANJUE
 * 
 */
public class DataWidget {
	public static final String UPDATEMILIS = "updatemilis";
	public static final String CITY = "city";
	public static final String POSTCODE = "postcode";
	public static final String FORECASTDATE = "forecastdate";
	public static final String CONDITION = "condition";
	public static final String TEMPF = "tempf";
	public static final String TEMPC = "tempc";
	public static final String HUMIDITY = "humidity";
	public static final String ICON = "icon";
	public static final String WINDCONDITION = "windcondition";
	public static final String LASTUPDATETIME = "lastupdatetime";
	public static final String ISCONFIGURED = "isconfigured";
	public static final String[] widgetDataProjection = new String[] { UPDATEMILIS, CITY,
			POSTCODE, FORECASTDATE, CONDITION, TEMPF, TEMPC, HUMIDITY, ICON,
			WINDCONDITION, LASTUPDATETIME, ISCONFIGURED };
	private ArrayList<DetailDateWidget> details=new ArrayList<DetailDateWidget>();
	private Integer id;
	private Integer updatemilis;
	private String city;
	private String postcode;
	private Long forecastdate;
	private String condition;
	private Integer tempf;
	private Integer tempc;
	private String humidity;
	private String icon;
	private String windcondition;
	private Long lastupdatetime;
	private Integer isconfigured;
	public ArrayList<DetailDateWidget> getDetails() {
		return details;
	}
	public void setDetails(ArrayList<DetailDateWidget> details) {
		this.details = details;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUpdatemilis() {
		return updatemilis;
	}
	public void setUpdatemilis(Integer updatemilis) {
		this.updatemilis = updatemilis;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public Long getForecastdate() {
		return forecastdate;
	}
	public void setForecastdate(Long forecastdate) {
		this.forecastdate = forecastdate;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public Integer getTempf() {
		return tempf;
	}
	public void setTempf(Integer tempf) {
		this.tempf = tempf;
	}
	public Integer getTempc() {
		return tempc;
	}
	public void setTempc(Integer tempc) {
		this.tempc = tempc;
	}
	public String getHumidity() {
		return humidity;
	}
	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getWindcondition() {
		return windcondition;
	}
	public void setWindcondition(String windcondition) {
		this.windcondition = windcondition;
	}
	public Long getLastupdatetime() {
		return lastupdatetime;
	}
	public void setLastupdatetime(Long lastupdatetime) {
		this.lastupdatetime = lastupdatetime;
	}
	public Integer getIsconfigured() {
		return isconfigured;
	}
	public void setIsconfigured(Integer isconfigured) {
		this.isconfigured = isconfigured;
	}

}
