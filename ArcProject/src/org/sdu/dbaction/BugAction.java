package org.sdu.dbaction;

import java.util.ArrayList;
import java.util.List;

import org.sdu.dao.BugDao;
import org.sdu.pojo.Bug;
import org.sdu.view.bugshow.BugTypes;

import android.content.Context;
/**
 * 
 * @author laofeifd
 * 
 * @�������Ҫ�����Ǵ�����Bug����ص�ҵ���߼�
 */
public class BugAction  {
	private Context context; 
	private BugDao bugDao;
	public BugAction(Context context){
		this.context=context;
		bugDao=new BugDao(this.context);
	}
	/**
	 * ��������
	 * @param bug ��Ҫ��¼������
	 * @return	      �����Ƿ�ɹ�
	 */
	public boolean establishBug(Bug bug){
		return bugDao.insert(bug)>0?true:false;
	}
	/**
	 * ���ⷢ��
	 * @param bug	��Ҫ����������
	 */
	public void releaseBug(Bug bug){
		 bugDao.update(bug);
	}
	/**
	 * ��ȡ�����б�
	 * @return	���������б�
	 */
	public List<Bug> search(){
		return bugDao.find();
	}
	/**
	 * �����������ϸ��Ϣ
	 * @param bugId	��ʶ�����id
	 * @return		����һ������(bug)����
	 */
	public Bug getDetail(int bugId){
		return bugDao.get(bugId);
	}
	/**
	 * ����ʶΪbugId���������ó��ѽ��
	 * @param bugId	����������id
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
		return bugDao.find(null, selection,selectionArgs,null,null,null ,null);
	}
	/**
	 * �÷������ط����������������б�
	 * @param selection ɸѡ����
	 * @param args		����ֵ
	 * @return
	 */
	public ArrayList<Integer>getBugIds(String selection,String args){
		ArrayList<Integer>ids=new ArrayList<Integer>();
		ArrayList<Bug>bugList=(ArrayList<Bug>) search(selection,args);
		if(bugList!=null&&bugList.size()>0){
			for(Bug bug:bugList){
				ids.add(bug.getId());
			}
		}
		return ids;
	}
	/**
	 * ���ظ�������б��Bug�б�
	 * @param values �������б�
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
