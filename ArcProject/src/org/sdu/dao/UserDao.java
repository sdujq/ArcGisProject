package org.sdu.dao;

import org.sdu.db.DBHelper;
import org.sdu.pojo.User;

import android.content.Context;

import com.tgb.lk.ahibernate.dao.impl.BaseDaoImpl;

public class UserDao extends BaseDaoImpl<User> {

	public UserDao(Context c) {
		super(new DBHelper(c));
	}

}
