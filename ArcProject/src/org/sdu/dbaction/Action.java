package org.sdu.dbaction;

import java.util.List;

import org.sdu.dao.UserDao;
import org.sdu.pojo.User;

import android.content.Context;

public class Action {
	String info="";
	Context context;
	User currentUser;
	public Action(Context c) {
		this.context = c;
	}
	/**
	 * ע��
	 * @param name ����
	 * @param phone �绰
	 * @param pwd ����
	 * @return
	 */
	public boolean reg(String name, String phone, String pwd) {
		UserDao uDao=new UserDao(context);
		List<User> u=uDao.find(new String[]{"id","password","name"},"name=?",new String[]{name},null,null,null,null);
		if(u!=null&&u.size()!=0){
			info="�û����Ѿ�����";
			return false;
		}
		u=uDao.find(new String[]{"id","password","name"},"phoneNum=?",new String[]{phone},null,null,null,null);
		if(u!=null&&u.size()!=0){
			info="�û����ֻ��Ѵ���";
			return false;
		}else if(name.equals("")||pwd.equals("")||phone.equals("")){
			info="�������û���Ϣ";
			return false;
		}
		User user=new User();
		user.setPhoneNum(phone);
		user.setName(name);
		user.setPassword(pwd);
		uDao.insert(user);
		info="��ӳɹ�";
		return true;
	}
	public boolean login(int id, String pwd) {
		UserDao uDao=new UserDao(context);
		User u=uDao.get(id);
		if(u==null){
			info="�û�������";
			return false;
		}else if(!pwd.equals(u.getPassword())){
			info="�������";
			return false;
		}else{
			info="��½�ɹ�";
			currentUser=u;
		}
		return true;
	}
	/**
	 * ��½
	 * @param name ����
	 * @param pwd ����
	 * @return
	 */
	public boolean login(String name,String pwd){
		UserDao uDao=new UserDao(context);
		List<User> u=uDao.find(new String[]{"id","password","name"},"name=?",new String[]{name},null,null,null,null);
		if(u==null||u.size()==0){
			info="�û�������";
			return false;
		}
		return login(u.get(0).getId(),pwd);
	}
	
	
}
