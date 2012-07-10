package org.sdu.dbaction;

import java.util.ArrayList;
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
	public Task getDetail(int taskId){
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
	/**
	 * ���ط��������ļ�¼��id
	 * @param userId ������Ա���ƶ���Ա��id
	 * @return
	 */
	public List<Integer> getTaskIds(String userId){
		ArrayList<Integer>ids=new ArrayList<Integer>();
		ArrayList<Task>list=(ArrayList<Task>) taskDao.find(new String[]{"id"},"inspectionPersonId= ? or createPersonId= ? ", new String[]{userId,userId},null,null,null, null);
		for(Task task:list)
			ids.add(0,task.getId());
		return ids;
	}
	/**
	 * �÷������ص��Ǹ���id�������¼
	 * @param values
	 * @return
	 */
	public List<Task> getList(ArrayList<Integer>values){
		ArrayList<Task>taskList=new ArrayList<Task>();
		for(int value:values){
			Task task=taskDao.get(value);
			if(task!=null)
				taskList.add(task);
		}
		return taskList;
		
	}
}
