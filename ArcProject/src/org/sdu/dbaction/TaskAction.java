package org.sdu.dbaction;

import java.util.List;

import org.sdu.dao.TaskDao;
import org.sdu.pojo.Task;

import android.content.Context;
/**
 * 
 * @author laofeifd
 * @�������Ҫ�����Ǵ���������(Task)����ص�ҵ���߼�
 */
public class TaskAction  {
	private TaskDao taskDao;
	private Context context;
	public TaskAction(Context context){
		this.context=context;
		taskDao=new TaskDao(this.context);
	}
	/**
	 * �����¼����
	 * @param task ��װ���������ϸ��Ϣ
	 * @return	   ���ؼ�¼�����Ƿ�ɹ�
	 */
	public boolean establishTask(Task task){
		return taskDao.insert(task)>0?true:false;
	}
	/**
	 * ��������
	 * @param task ��װ���������ϸ��Ϣ
	 */
	public void releaseTask(Task task){
		taskDao.update(task);
	}
	/**
	 * ��ȡ�����б�
	 * @return	����һ��List��װ�������б�
	 */
	public List<Task> getTaskList(){
		return taskDao.find();
	}
	/**
	 * ��ȡ�������ϸ��Ϣ
	 * @param taskId	�����id
	 * @return			����һ���������
	 */
	public Task taskDetail(int taskId){
		return taskDao.get(taskId);
	}
	/**
	 * �޸������״̬��1 ��ʾ�ѷ��� ��0 ��ʾ��δ����
	 * @param taskId	�����id
	 */
	public void setState(int taskId){
		Task task=taskDao.get(taskId);
		task.setState("1");
		taskDao.update(task);
	}
}
