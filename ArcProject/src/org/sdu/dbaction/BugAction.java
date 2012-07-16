package org.sdu.dbaction;

import java.util.ArrayList;
import java.util.List;

import org.sdu.dao.BugDao;
import org.sdu.pojo.Bug;

import android.content.Context;
/**
 * 
 * @author laofeifd
 * 
 * @该类的主要作用是处理与Bug的相关的业务逻辑
 */
public class BugAction  {
	private Context context; 
	private BugDao bugDao;
	public BugAction(Context context){
		this.context=context;
		bugDao=new BugDao(this.context);
	}
	/**
	 * 建立问题
	 * @param bug 需要记录的问题
	 * @return	      保存是否成功
	 */
	public boolean establishBug(Bug bug){
		return bugDao.insert(bug)>0?true:false;
	}
	/**
	 * 问题发布
	 * @param bug	需要发布的问题
	 */
	public void releaseBug(Bug bug){
		 bugDao.update(bug);
	}
	/**
	 * 获取问题列表
	 * @return	返回问题列表
	 */
	public List<Bug> search(){
		return bugDao.find();
	}
	/**
	 * 返回问题的详细信息
	 * @param bugId	标识问题的id
	 * @return		返回一个问题(bug)对象
	 */
	public Bug getDetail(int bugId){
		return bugDao.get(bugId);
	}
	/**
	 * 将表识为bugId的问题设置成已解决
	 * @param bugId	已完成问题的id
	 */
	public void finish(int bugId){
		Bug bug=bugDao.get(bugId);
		bug.setState("1");
		bugDao.update(bug);
	}
	public List<Bug> search(String selection,String args){
		String selectionArgs[]=null;
		ArrayList<String>list=new ArrayList<String>();
		while(args.indexOf('|')!=-1){
			list.add(args.substring(0,args.indexOf('|')));
			args=args.substring(args.indexOf('|')+1);
		}
		list.add(args);
		selectionArgs=new String[list.size()];
		int i=0;
		for(String s:list){
			selectionArgs[i]=s;
			 i++;
		}
		return bugDao.find(new String[]{"id","address","state","userid"}, selection,selectionArgs,null,null,null ,null);
	}
	/**
	 * 该方法返回符合条件的问题编号列表
	 * @param selection 筛选条件
	 * @param args		条件值
	 * @return
	 */
	public ArrayList<Integer>getBugIds(String selection,String args){
		ArrayList<Integer>ids=new ArrayList<Integer>();
		ArrayList<Bug>bugList=(ArrayList<Bug>) search(selection,args);
		if(bugList!=null&&bugList.size()>0){
			for(Bug bug:bugList){
				ids.add(0,bug.getId());
			}
		}
		return ids;
	}
	/**
	 * 返回给定编号列表的Bug列表
	 * @param values 问题编号列表
	 * @return
	 */
	public List<Bug> getList(ArrayList<Integer>values){
		ArrayList<Bug>bugList=new ArrayList<Bug>();
		for(int value:values){
			Bug bug=bugDao.get(value);
			if(bug!=null)
				bugList.add(bug);
		}
		return bugList;
	}
}
