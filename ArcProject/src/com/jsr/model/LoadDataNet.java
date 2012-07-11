/**
 * 
 */
package com.jsr.model;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import com.jsr.model.WeatherProvider.WeatherWidgets;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

/**
 * @author HANJUE
 * 
 */
public class LoadDataNet {
	private static final String WEBDATA_URI = "http://www.google.com/ig/api?weather=%s&hl=zh-cn";
	private static final String FORECAST_INFORMATION = "forecast_information";
	private static final String CURRENT_CONDITIONS = "current_conditions";
	private static final String FORECAST_CONDITIONS = "forecast_conditions";
	private static final String PROBLEM_CAUSE = "problem_cause";

	private static final String CITY = "city";
	private static final String FORECAST_DATE = "forecast_date";
	private static final String CONDITION = "condition";
	private static final String TEMP_F = "temp_f";
	private static final String TEMP_C = "temp_c";
	private static final String HUMIDITY = "humidity";
	private static final String ICON = "icon";
	private static final String WIND_CONDITION = "wind_condition";

	private static final String DAY_OF_WEEK = "day_of_week";
	private static final String LOW = "low";
	private static final String HIGH = "high";

	public static void updateForecasts(Context context, Uri widgetUri)
			throws ForecastParseException {
		System.out.println("updateforecasts is running");
		Uri forecastUri = Uri.withAppendedPath(widgetUri,
				WeatherWidgets.FORECAST_END);
		ContentResolver resolver = context.getContentResolver();
		Cursor cursor = null;
		String postcode = null;
		try {
			cursor = resolver.query(widgetUri,
					new String[] { DataWidget.POSTCODE }, null, null, null);
			if (cursor != null && cursor.moveToFirst()) {
				postcode = cursor.getString(0);
			}
		} finally {
			if (cursor != null) {
				cursor.close();
			}
		}
		DataWidget dataWidget = queryWebData(postcode);

		if (dataWidget == null) {
			throw new ForecastParseException(
					"No forecasts found from webservice query");
		}

		resolver.delete(forecastUri, null, null);
		ContentValues values = new ContentValues();
		for (DetailDateWidget forecast : dataWidget.getDetails()) {
			values.clear();
			values.put(DetailDateWidget.DAYOFWEEK, forecast.getDayofweek());
			values.put(DetailDateWidget.HIGHT, forecast.getHight());
			values.put(DetailDateWidget.LOW, forecast.getLow());
			values.put(DetailDateWidget.ICON, forecast.getIcon());
			values.put(DetailDateWidget.CONDITION, forecast.getCondition());

			resolver.insert(forecastUri, values);
		}

		values.clear();
		values.put(DataWidget.CITY, dataWidget.getCity());
		values.put(DataWidget.CONDITION, dataWidget.getCondition());
		values.put(DataWidget.FORECASTDATE, dataWidget.getForecastdate());
		values.put(DataWidget.HUMIDITY, dataWidget.getHumidity());
		values.put(DataWidget.ICON, dataWidget.getIcon());
		values.put(DataWidget.TEMPC, dataWidget.getTempc());
		values.put(DataWidget.TEMPF, dataWidget.getTempf());
		values.put(DataWidget.WINDCONDITION, dataWidget.getWindcondition());
		values.put(DataWidget.LASTUPDATETIME, System.currentTimeMillis());

		System.out.println("update the weather infomation");
		resolver.update(widgetUri, values, null, null);

	}

	public static DataWidget queryWebData(String postcode)
			throws ForecastParseException {
		if (postcode == null) {
			throw new ForecastParseException("can not empty");

		}
		System.out.println(String.format(WEBDATA_URI, postcode));
		Reader responseReader;
		DataWidget dataWidget = null;
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(String.format(WEBDATA_URI, postcode));
		try {

			HttpResponse response = client.execute(request);

			StatusLine status = response.getStatusLine();
			System.out.println(status);

			HttpEntity entity = response.getEntity();
			responseReader = new InputStreamReader(entity.getContent(), "GBK");

		} catch (Exception e) {
			throw new ForecastParseException("Problem calling forecast API", e);
		}
		if (responseReader != null) {
			dataWidget = parseResponse(responseReader);
		}
		return dataWidget;

	}

	private static DataWidget parseResponse(Reader responseReader)
			throws ForecastParseException {
		// TODO Auto-generated method stub
		System.out.println("conver xml to dataWiget");
		DataWidget dataWidget = new DataWidget();

		try {
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			XmlPullParser xpp = factory.newPullParser();

			String tagName = null;

			xpp.setInput(responseReader);
			int eventType = xpp.getEventType();
			System.out.println("eventType:" + eventType);

			while (eventType != XmlPullParser.END_DOCUMENT) {
				// Log.d(TAG, "eventType:" + eventType);
				if (eventType == XmlPullParser.START_TAG) {
					tagName = xpp.getName();
					System.out.println("tag Name:" + tagName);

					if (PROBLEM_CAUSE.equals(tagName)) {
						throw new ForecastParseException(
								"the city is non correct!");
					} else if (FORECAST_INFORMATION.equals(tagName)) {
						dealWithInfomation(tagName, dataWidget, xpp);
					} else if (CURRENT_CONDITIONS.equals(tagName)) {
						dealWithCurrentConditions(tagName, dataWidget, xpp);
					} else if (FORECAST_CONDITIONS.equals(tagName)) {
						dealWithForecastConditions(tagName, dataWidget, xpp);
					}
				}

				eventType = xpp.next();
			}

		} catch (Exception e) {
			throw new ForecastParseException("Problem parsing XML forecast", e);
		}
		System.out.println("covert xml to entity success");
		return dataWidget;
	}

	private static void dealWithForecastConditions(String name,
			DataWidget dataWidget, XmlPullParser xpp) throws IOException,
			XmlPullParserException {
		// TODO Auto-generated method stub
		System.out.println("dealWithForecastConditions");
		DetailDateWidget forecast = new DetailDateWidget();
		xpp.next();

		int eventType = xpp.getEventType();
		String tagName = xpp.getName();
		while ((!name.equals(tagName) || eventType != XmlPullParser.END_TAG)
				&& eventType != XmlPullParser.END_DOCUMENT) {
			if (eventType == XmlPullParser.START_TAG) {
				if (tagName.equals(CONDITION)) {
					forecast.setCondition(xpp.getAttributeValue(null, "data"));
				} else if (tagName.equals(DAY_OF_WEEK)) {
					forecast.setDayofweek(xpp.getAttributeValue(null, "data"));
				} else if (tagName.equals(HIGH)) {
					forecast.setHight(Integer.parseInt(xpp.getAttributeValue(
							null, "data")));
				} else if (tagName.equals(LOW)) {
					forecast.setLow(Integer.parseInt(xpp.getAttributeValue(
							null, "data")));
				} else if (tagName.equals(ICON)) {
					forecast.setIcon(xpp.getAttributeValue(null, "data"));
				}
			}
			xpp.next();

			eventType = xpp.getEventType();
			tagName = xpp.getName();
		}
		dataWidget.getDetails().add(forecast);
	}

	private static void dealWithCurrentConditions(String name,
			DataWidget dataWidget, XmlPullParser xpp) throws IOException,
			XmlPullParserException {
		// TODO Auto-generated method stub
		System.out.println("dealWithCurrentConditions");
		xpp.next();

		int eventType = xpp.getEventType();
		String tagName = xpp.getName();
		while ((!name.equals(tagName) || eventType != XmlPullParser.END_TAG)
				&& eventType != XmlPullParser.END_DOCUMENT) {
			if (eventType == XmlPullParser.START_TAG) {
				if (tagName.equals(CONDITION)) {
					dataWidget
							.setCondition(xpp.getAttributeValue(null, "data"));
					System.out.println("condition" + dataWidget.getCondition());
				} else if (tagName.equals(TEMP_F)) {
					dataWidget.setTempf(Integer.parseInt(xpp.getAttributeValue(
							null, "data")));
				} else if (tagName.equals(TEMP_C)) {
					dataWidget.setTempc(Integer.parseInt(xpp.getAttributeValue(
							null, "data")));
				} else if (tagName.equals(HUMIDITY)) {
					dataWidget.setHumidity(xpp.getAttributeValue(null, "data"));
				} else if (tagName.equals(ICON)) {
					dataWidget.setIcon(xpp.getAttributeValue(null, "data"));
				} else if (tagName.equals(WIND_CONDITION)) {
					dataWidget.setWindcondition(xpp.getAttributeValue(null,
							"data"));
				}
			}
			xpp.next();

			eventType = xpp.getEventType();
			tagName = xpp.getName();
		}
	}

	private static void dealWithInfomation(String name, DataWidget dataWidget,
			XmlPullParser xpp) throws IOException, XmlPullParserException {
		// TODO Auto-generated method stub
		System.out.println("dealWithInfomation");
		xpp.next();

		int eventType = xpp.getEventType();
		String tagName = xpp.getName();
		while ((!name.equals(tagName) || eventType != XmlPullParser.END_TAG)
				&& eventType != XmlPullParser.END_DOCUMENT) {
			if (eventType == XmlPullParser.START_TAG) {
				if (tagName.equals(CITY)) {
					dataWidget.setCity(xpp.getAttributeValue(null, "data"));
				} else if (tagName.equals(FORECAST_DATE)) {
					dataWidget.setForecastdate(convertStr2Date(
							xpp.getAttributeValue(null, "data")).getTime());
				}
			}
			xpp.next();

			eventType = xpp.getEventType();
			tagName = xpp.getName();
		}
	}

	public static Date convertStr2Date(String str) {
		Date d = null;
		try {
			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
			d = f.parse(str);
		} catch (ParseException e) {
			System.out.println("date format exception");
		}
		return d;
	}

	public static DataWidget getDataWidget(Context context, Uri uri,
			boolean needDetails) {
		// TODO Auto-generated method stub
		System.out.println("get forcast infomation from database");
		DataWidget widgetEn = null;
		ContentResolver res = context.getContentResolver();

		Cursor cursor = null;

		try {
			cursor = res.query(uri, DataWidget.widgetDataProjection, null,
					null, null);
			if (cursor != null && cursor.moveToFirst()) {
				widgetEn = new DataWidget();
				widgetEn.setUpdatemilis(cursor.getInt(0));
				widgetEn.setCity(cursor.getString(1));
				widgetEn.setPostcode(cursor.getString(2));
				widgetEn.setForecastdate(cursor.getLong(3));
				widgetEn.setCondition(cursor.getString(4));
				widgetEn.setTempf(cursor.getInt(5));
				widgetEn.setTempc(cursor.getInt(6));
				widgetEn.setHumidity(cursor.getString(7));
				widgetEn.setIcon(cursor.getString(8));
				widgetEn.setWindcondition(cursor.getString(9));
				widgetEn.setLastupdatetime(cursor.getLong(10));
				widgetEn.setIsconfigured(cursor.getInt(11));
			} else {
				return null;
			}
		} finally {
			if (cursor != null) {
				cursor.close();
			}
		}

		if (needDetails) {
			try {
				Uri forecastUri = Uri.withAppendedPath(uri,
						WeatherWidgets.FORECAST_END);
				cursor = res.query(forecastUri,
						DetailDateWidget.detailProjection, null, null, null);
				DetailDateWidget forecast = null;
				while (cursor != null && cursor.moveToNext()) {
					forecast = new DetailDateWidget();
					forecast.setDayofweek(cursor.getString(1));
					forecast.setLow(cursor.getInt(2));
					forecast.setHight(cursor.getInt(3));
					forecast.setIcon(cursor.getString(4));
					forecast.setCondition(cursor.getString(5));
					forecast.setWidgetid(cursor.getInt(6));

					widgetEn.getDetails().add(forecast);
				}
			} finally {
				if (cursor != null) {
					cursor.close();
				}
			}
		}

		return widgetEn;
	}

	public static final class ForecastParseException extends Exception {
		/**
		 * serialVersionUID
		 */
		private static final long serialVersionUID = -891526452631557227L;

		public ForecastParseException(String detailMessage) {
			super(detailMessage);
		}

		public ForecastParseException(String detailMessage, Throwable throwable) {
			super(detailMessage, throwable);
		}
	}
}
