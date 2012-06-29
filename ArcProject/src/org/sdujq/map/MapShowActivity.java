
package org.sdujq.map;

import org.sdu.gis.R;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.esri.android.map.GraphicsLayer;
import com.esri.android.map.MapView;
import com.esri.android.map.event.OnStatusChangedListener;
import com.esri.core.geometry.GeometryEngine;
import com.esri.core.geometry.MultiPath;
import com.esri.core.geometry.Point;
import com.esri.core.geometry.Polyline;
import com.esri.core.geometry.SpatialReference;
import com.esri.core.map.Graphic;
import com.esri.core.renderer.SimpleRenderer;
import com.esri.core.symbol.PictureMarkerSymbol;
import com.esri.core.symbol.SimpleLineSymbol;

public class MapShowActivity extends Activity implements OnClickListener{
	MapView map = null;
	Button bt1,bt2,bt3,bt4,bt5;
	GraphicsLayer glayer;
	GraphicsLayer lineLayer;
	MultiPath path;
	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mapview);
		map = (MapView) findViewById(R.id.map);
		bt1=(Button)findViewById(R.id.button1);
		bt2=(Button)findViewById(R.id.button2);
		bt3=(Button)findViewById(R.id.button3);
		bt4=(Button)findViewById(R.id.button4);
		bt5=(Button)findViewById(R.id.button5);
		bt1.setOnClickListener(this);
		bt2.setOnClickListener(this);
		bt3.setOnClickListener(this);
		bt4.setOnClickListener(this);
		bt5.setOnClickListener(this);
		Object init = getLastNonConfigurationInstance();
		if (init != null) {
			map.restoreState((String) init);
		}
		/*map.setOnTouchListener(new OnTouchListener() {	
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if(event.getAction()!=MotionEvent.ACTION_DOWN){
					return false;
				}
				Point p=map.toMapPoint(event.getX(),event.getY());
				Log.e("qq", p.getX()+","+p.getY());
				if(path!=null){
					path.lineTo(p);
					lineLayer.postInvalidate();
				}
				return false;
			}
		});*/
		map.setOnStatusChangedListener(new OnStatusChangedListener(){

			private static final long serialVersionUID = -1001740038201073031L;

			@Override
			public void onStatusChanged(View arg0, STATUS arg1) {
				if(arg1==STATUS.INITIALIZED){
					/*if(currentData!=null){
						Point p=getGPoint(currentData.getLocationX(), currentData.getLocationY());
						map.centerAt(p);
						glayer=new GraphicsLayer(MapShowActivity.this);
						glayer.setRenderer(new SimpleRenderer(new PictureMarkerSymbol(getResources().getDrawable(R.drawable.ic_launcher))));
						Graphic g=new Graphic();
						g.setGeometry(p);
						glayer.addGraphic(g);
						map.addLayer(glayer);
					}*/
				}
			}});
		
	}

	public Point getGPoint(double a,double b){
		Point p=new Point(a ,b
				);
		SpatialReference sp=SpatialReference.create(4326);
		Point ptMap = (Point)GeometryEngine.project(p, sp, 
                 map.getSpatialReference());
		return ptMap;
	}
	public void initLayer(){
		glayer=new GraphicsLayer(this);
		glayer.setRenderer(new SimpleRenderer(new PictureMarkerSymbol(getResources().getDrawable(R.drawable.ic_launcher))));
		Graphic g=new Graphic();
		g.setGeometry(map.getCenter());
		glayer.addGraphic(g);
		map.addLayer(glayer);
	}
	
	public void initLineLayer(){
		lineLayer=new GraphicsLayer(this);
		lineLayer.setRenderer(new SimpleRenderer(new SimpleLineSymbol(Color.RED, 5)));
		path=new Polyline();
		path.startPath(map.getCenter());
		path.lineTo(-1.3296373526814876E7, 3930962.41823043);
		Graphic g=new Graphic();
		g.setGeometry(path);
		lineLayer.addGraphic(g);
		lineLayer.postInvalidate();
		map.addLayer(lineLayer);
	}

	public Object onRetainNonConfigurationInstance() {
		return map.retainState();
	}

	@Override
	public void onClick(View v) {
		if(v==bt1){
			map.zoomin();
		}else if(v==bt2){
			map.zoomout();
		}else if(v==bt3){
			initLayer();
		}else if(v==bt4){
			initLineLayer();
		}else if(v==bt5){
			Point p=new Point(116.806 ,36.555629
					);
			SpatialReference sp=SpatialReference.create(4326);
			Point ptMap = (Point)GeometryEngine.project(p, sp, 
	                 map.getSpatialReference());
			map.centerAt(ptMap);
		}
	}

}