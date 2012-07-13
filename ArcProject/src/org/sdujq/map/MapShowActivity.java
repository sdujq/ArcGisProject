package org.sdujq.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.sdu.dao.RoadLineDao;
import org.sdu.dbaction.RoadLineAction;
import org.sdu.gis.R;
import org.sdu.pojo.RoadLine;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.esri.android.map.GraphicsLayer;
import com.esri.android.map.MapOnTouchListener;
import com.esri.android.map.MapView;
import com.esri.android.map.ags.ArcGISLocalTiledLayer;
import com.esri.android.map.ags.ArcGISTiledMapServiceLayer;
import com.esri.android.map.event.OnStatusChangedListener;
import com.esri.core.geometry.GeometryEngine;
import com.esri.core.geometry.MultiPath;
import com.esri.core.geometry.Point;
import com.esri.core.geometry.Polyline;
import com.esri.core.geometry.SpatialReference;
import com.esri.core.map.Graphic;
import com.esri.core.map.popup.PopupInfo;
import com.esri.core.renderer.SimpleRenderer;
import com.esri.core.symbol.PictureMarkerSymbol;
import com.esri.core.symbol.SimpleLineSymbol;

public class MapShowActivity extends Activity implements OnClickListener,
		OnStatusChangedListener {

	private static final long serialVersionUID = -8063970613592622322L;
	static final int state_nothing = 0;
	static final int state_making = 1;
	static final int state_delating = 2;

	MapView map = null;
	Button bt1, bt2, bt3, bt5;
	Spinner spinner;
	GraphicsLayer glayer;
	GraphicsLayer lineLayer;
	GraphicsLayer graphicsLayer;
	MultiPath path;
	public static RoadLine roadLine;
	public RoadLineAction action;
	public int state = 0;
	ArcGISTiledMapServiceLayer tileLayer;
	ArcGISLocalTiledLayer local;
	boolean needSave = false;
	boolean gpsPoint = false;

	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent it = this.getIntent();
		roadLine = (RoadLine) it.getSerializableExtra("roadLine");
		needSave = it.getBooleanExtra("needSave", false);
		gpsPoint = it.getBooleanExtra("gpsPoint", false);
		Log.e("qq", needSave + "");
		setContentView(R.layout.mapview);
		init();
		local = new ArcGISLocalTiledLayer("file:///mnt/sdcard/Layers");
		tileLayer = new ArcGISTiledMapServiceLayer(
				"http://services.arcgisonline.com/ArcGIS/rest/services/World_Street_Map/MapServer");
		graphicsLayer = new GraphicsLayer();
		// map.addLayer(tileLayer);
		map.addLayer(local);
		map.setOnStatusChangedListener(this);
		map.addLayer(graphicsLayer);
	}

	public void drawCurrentLine() {
		List<Point> plist = action.getPointList();
		path = new Polyline();
		graphicsLayer.removeAll();
		HashMap<Integer, PopupInfo> infoMap = new HashMap<Integer, PopupInfo>();
		for (int i = 0; i < plist.size(); i++) {
			Point p = plist.get(i);
			if (i == 0) {
				path.startPath(p);
			} else {
				path.lineTo(p);
			}
			PopupInfo pInfo = new PopupInfo();
			Graphic pointGraphic = new Graphic(p, new PictureMarkerSymbol(
					getResources().getDrawable(R.drawable.pointer)));
			int pid = graphicsLayer.addGraphic(pointGraphic);
			pInfo.setTitle(pid + "");
			infoMap.put(pid, pInfo);
		}
		Graphic pathGraphic = new Graphic(path, new SimpleLineSymbol(
				Color.BLUE, 2));
		graphicsLayer.addGraphic(pathGraphic);

		graphicsLayer.setPopupInfos(infoMap);

	}

	public void init() {
		map = (MapView) findViewById(R.id.map);
		bt1 = (Button) findViewById(R.id.button1);
		bt2 = (Button) findViewById(R.id.button2);
		bt3 = (Button) findViewById(R.id.button3);
		//bt4 = (Button) findViewById(R.id.button4);
		bt5 = (Button) findViewById(R.id.button5);
		bt1.setOnClickListener(this);
		bt2.setOnClickListener(this);
		bt3.setOnClickListener(this);
		//bt4.setOnClickListener(this);
		bt5.setOnClickListener(this);

		spinner = (Spinner) findViewById(R.id.spinner1);
		List<RoadLine> lineLst = (new RoadLineDao(this)).find();
		final ArrayAdapter<RoadLine> adapter = new ArrayAdapter<RoadLine>(this,
				android.R.layout.simple_spinner_item);
		RoadLine head = new RoadLine();
		head.setId(-1);
		head.setName("点击查看已有路径 ");
		adapter.add(head);
		for (RoadLine r : lineLst) {
			adapter.add(r);
		}

		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				if (arg2 == 0) {
					return;
				}
				roadLine = adapter.getItem(arg2);
				action = new RoadLineAction(MapShowActivity.this);
				action.getRoadLine(roadLine);
				drawCurrentLine();
				if (action.getPointList().size() != 0) {
					map.centerAt(action.getPointList().get(0), true);
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});
	}

	public Point getGPoint(double a, double b) {
		Point p = new Point(a, b);
		SpatialReference sp = SpatialReference.create(4326);
		Point ptMap = (Point) GeometryEngine.project(p, sp,
				map.getSpatialReference());
		return ptMap;
	}

	public void initPointLayer() {
		glayer = new GraphicsLayer();
		glayer.setRenderer(new SimpleRenderer(new PictureMarkerSymbol(
				getResources().getDrawable(R.drawable.pointer))));
		Graphic g = new Graphic(map.getCenter(), new PictureMarkerSymbol(
				getResources().getDrawable(R.drawable.pointer)));
		glayer.addGraphic(g);
		map.addLayer(glayer);
	}

	public void initLineLayer() {
		lineLayer = new GraphicsLayer();
		lineLayer.setRenderer(new SimpleRenderer(new SimpleLineSymbol(
				Color.BLUE, 5)));
		path = new Polyline();
		Graphic g = new Graphic(path, new SimpleLineSymbol(Color.BLUE, 5));
		lineLayer.addGraphic(g);
		map.addLayer(lineLayer);
	}

	@Override
	public void onClick(View v) {
		if (v == bt1) {
			state = state_making;
		} else if (v == bt2) {
			state = state_nothing;
			Point p = action.next();
			if (p != null) {
				map.centerAt(p, true);
			}
		} else if (v == bt3) {
			state = state_delating;
			action.removeLastPoint();
			drawCurrentLine();
		}  else if (v == bt5) {
			/*
			 * Log.e("qq", "scale" + map.getScale()); Log.e("qq", "X" +
			 * map.getCenter().getX()); Log.e("qq", "Y" +
			 * map.getCenter().getY());
			 */
			if (needSave) {
				int id = 0;
				if (roadLine != null && roadLine.getId() > 0) {
					id=action.updateRoadLine(roadLine);
				} else {
					id = action.saveCurrentRoadLine("");
				}
				Intent it = new Intent();
				it.putExtra("id", id);
				setResult(RESULT_OK, it);
				Log.e("qq", "road saved");
			}
			finish();
		}
	}

	protected void onPause() {
		super.onPause();
		map.pause();
	}

	protected void onResume() {
		super.onResume();
		map.unpause();
	}

	@Override
	public void onStatusChanged(Object arg0, STATUS arg1) {
		if (arg1 == STATUS.INITIALIZED) {
			map.setScale(30000);
			map.centerAt(new Point(60454.299497581625,55406.27427414925),
					true);
			action = new RoadLineAction(MapShowActivity.this);
			if (roadLine != null) {
				action.getRoadLine(roadLine);
				if (gpsPoint) {
					ArrayList<Point> tempPList = new ArrayList<Point>();
					for (Point p : action.getPointList()) {
						tempPList.add(getGPoint(p.getY(), p.getX()));
					}
					action.setPointList(tempPList);
				}
				drawCurrentLine();
				if (action.getPointList().size() != 0) {
					map.centerAt(action.getPointList().get(0), true);
				}
			}

			map.setOnTouchListener(new MapOnTouchListener(this, map) {
				public boolean onSingleTap(MotionEvent event) {
					if (event.getAction() != MotionEvent.ACTION_DOWN) {
						return false;
					}
					if (state == state_making) {
						Point p = map.toMapPoint(event.getX(), event.getY());
						action.addPoint(p);
						drawCurrentLine();
					}
					return false;
				}
			});
			map.centerAt(new Point(55351.7478343218,50838.0816699881), true);
		}
	}

	@Override
	public Object onRetainNonConfigurationInstance() {
		return map.retainState();
	}

	public static void startMapForShow(Activity activity, RoadLine roadLine,
			Boolean needSave, boolean gpsPoint) {
		// Intent it = new Intent(activity, MapShowActivity.class);

		Intent it = new Intent();
		it.setClass(activity, MapShowActivity.class);
		it.putExtra("roadLine", roadLine);
		it.putExtra("needSave", needSave);
		it.putExtra("gpsPoint", gpsPoint);
		activity.startActivityForResult(it, 1);

	}

	public static void startMapForShow(Activity activity, RoadLine roadLine,
			Boolean needSave) {
		Intent it = new Intent();
		it.setClass(activity, MapShowActivity.class);
		it.putExtra("roadLine", roadLine);
		it.putExtra("needSave", needSave);
		activity.startActivityForResult(it, 1);
		activity.overridePendingTransition(R.anim.myanimation_simple,
				R.anim.my_alpha_action);
	}
}