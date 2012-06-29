package org.sdu.dbaction;

import java.util.List;

import org.sdu.dao.TaskDao;
import org.sdu.pojo.Task;

import android.content.Context;
/**
 * 
 * @author laofeifd
 * @该类的主要作用是处理与任务(Task)的相关的业务逻辑
 */
public class TaskAction  {
	private TaskDao taskDao;
	private Context context;
	public TaskAction(Context context){
		this.context=context;
		taskDao=new TaskDao(this.context);
	}
	/**
	 * 保存记录任务
	 * @param task 封装有任务的详细信息
	 * @return	   返回记录保存是否成功
	 */
	public boolean establishTask(Task task){
		return taskDao.insert(task)>0?true:false;
	}
	/**
	 * 发布任务
	 * @param task 封装有任务的详细信息
	 */
	public void releaseTask(Task task){
		taskDao.update(task);
	}
	/**
	 * 获取任务列表
	 * @return	返回一个List封装的任务列表
	 */
	public List<Task> getTaskList(){
		return taskDao.find();
	}
	/**
	 * 获取任务的详细信息
	 * @param taskId	任务的id
	 * @return			返回一个任务对象
	 */
	public Task getDetail(int taskId){
		return taskDao.get(taskId);
	}
	/**
	 * 修改任务的状态：1 表示已发布 ；0 表示尚未发布
	 * @param taskId	任务的id
	 */
	public void setState(int taskId){
		Task task=taskDao.get(taskId);
		task.setState("1");
		taskDao.update(task);
	}
}
