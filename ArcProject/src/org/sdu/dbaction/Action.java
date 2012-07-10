package org.sdu.dbaction;

import java.util.ArrayList;
import java.util.List;

import org.sdu.dao.UserDao;
import org.sdu.pojo.User;

import android.content.Context;

public class Action {
	String info="";
	Context context;
	public static User currentUser;
	public Action(Context c) {
		this.context = c;
	}
	/**
	 * 注册
	 * @param name 姓名
	 * @param phone 电话
	 * @param pwd 密码
	 * @param tag 备注信息
	 * @return
	 */
	public boolean reg(String name, String phone, String pwd,String tag) {
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
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	/**
	 * 返回用户的id列表
	 * @return
	 */
	public ArrayList<Integer>getUserIds(){
		UserDao uDao=new UserDao(context);
		ArrayList<Integer>ids=new ArrayList<Integer>();
		ArrayList<User>userList=(ArrayList<User>)uDao.find();
		if(userList!=null&&userList.size()>0){
			for(User user:userList){
				ids.add(user.getId());
			}
		}
		return ids;
	}
	/**
	 * 返回给定id列表的用户列表
	 * @param values
	 * @return
	 */
	public List<User> getList(ArrayList<Integer>values){
		UserDao uDao=new UserDao(context);
		ArrayList<User>userList=new ArrayList<User>();
		for(int value:values){
			User user=uDao.get(value);
			if(user!=null)
				userList.add(user);
		}
		return userList;
	}
}
