package org.sdu.dao;

import org.sdu.db.DBHelper;
import org.sdu.pojo.BugType;

import android.content.Context;

import com.tgb.lk.ahibernate.dao.impl.BaseDaoImpl;

public class BugTypeDao extends BaseDaoImpl<BugType> {

	public BugTypeDao(Context c) {
		super(new DBHelper(c));
	}

}
