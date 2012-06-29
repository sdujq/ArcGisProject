package org.sdu.dbaction;

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
	public Bug BugDetail(int bugId){
		return bugDao.get(bugId);
	}
	/**
	 * 将表识为bugId的问题设置成已解决
	 * @param bugId	已完成问题的id
	 */
	public void finish(int bugId){
		Bug bug=bugDao.get(bugId);
		bug.setState("1");
	}
	
}
