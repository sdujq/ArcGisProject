package org.sdu.dbaction;

import java.util.ArrayList;

import org.sdu.dao.RoadLineDao;
import org.sdu.pojo.RoadLine;

import android.content.Context;

import com.esri.core.geometry.Point;

public class RoadLineAction {

	Context context;
	ArrayList<Point> plist=new ArrayList<Point>();
	private int index=-1;
	private int last=0;
	public RoadLineAction(Context c){
		this.context=c;
	}
	
	public void clear(){
		plist.clear();
	}
	
	public void addPoint(Point p){
		plist.add(p);
		last=plist.size()-1;
	}
	
	public void removePoint(){
		plist.remove(plist.size()-1);
	}
	
	public void removeLastPoint(){
		if(last>=0&&last<plist.size()){
			plist.remove(last);
			last--;
		}
	}
	
	public int saveCurrentRoadLine(String lineName){
		if(plist.size()==0){
			return -1;
		}
		RoadLine line=new RoadLine();
		line.setName(lineName);
		String p="";
		for(Point temp:plist){
			p+=temp.getX()+","+temp.getY()+":";
		}
		line.setPoints(p);
		RoadLineDao rdao=new RoadLineDao(context);
		return (int)rdao.insert(line);
	}
	
	public ArrayList<Point> getRoadLine(int id){
		this.plist.clear();
		RoadLineDao rdao=new RoadLineDao(context);
		String line=rdao.get(id).getPoints();
		for(String temp:line.split(":")){
			String[] point=temp.split(",");
			plist.add(new Point(Double.parseDouble(point[0]), Double.parseDouble(point[1])));
		}
		return plist;
	}
	
	public ArrayList<Point> getRoadLine(RoadLine roadLine){
		this.plist.clear();
		String line=roadLine.getPoints();
		for(String temp:line.split(":")){
			String[] point=temp.split(",");
			plist.add(new Point(Double.parseDouble(point[0]), Double.parseDouble(point[1])));
		}
		return plist;
	}
	
	public ArrayList<Point> getPointList(){
		return plist;
	}
	
	public void setPointList(ArrayList<Point> Lst){
		this.plist=Lst;
	}
	public Point next(){
		index++;
		if(index>=plist.size()){
			if(plist.size()==0){
				index=-1;
				return null;
			}
			index=0;
		}
		last=index;
		return plist.get(index);
	}
}
