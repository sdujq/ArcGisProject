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
	 * 注册
	 * @param name 姓名
	 * @param phone 电话
	 * @param pwd 密码
	 * @return
	 */
	public boolean reg(String name, String phone, String pwd) {
		UserDao uDao=new UserDao(context);
		List<User> u=uDao.find(new String[]{"id","password","name"},"name=?",new String[]{name},null,null,null,null);
		if(u!=null&&u.size()!=0){
			info="用户名已经存在";
			return false;
		}
		u=uDao.find(new String[]{"id","password","name"},"phoneNum=?",new String[]{phone},null,null,null,null);
		if(u!=null&&u.size()!=0){
			info="用户名手机已存在";
			return false;
		}else if(name.equals("")||pwd.equals("")||phone.equals("")){
			info="请填满用户信息";
			return false;
		}
		User user=new User();
		user.setPhoneNum(phone);
		user.setName(name);
		user.setPassword(pwd);
		uDao.insert(user);
		info="添加成功";
		return true;
	}
	public boolean login(int id, String pwd) {
		UserDao uDao=new UserDao(context);
		User u=uDao.get(id);
		if(u==null){
			info="用户不存在";
			return false;
		}else if(!pwd.equals(u.getPassword())){
			info="密码错误";
			return false;
		}else{
			info="登陆成功";
			currentUser=u;
		}
		return true;
	}
	/**
	 * 登陆
	 * @param name 姓名
	 * @param pwd 密码
	 * @return
	 */
	public boolean login(String name,String pwd){
		UserDao uDao=new UserDao(context);
		List<User> u=uDao.find(new String[]{"id","password","name"},"name=?",new String[]{name},null,null,null,null);
		if(u==null||u.size()==0){
			info="用户不存在";
			return false;
		}
		return login(u.get(0).getId(),pwd);
	}
	
	
}
