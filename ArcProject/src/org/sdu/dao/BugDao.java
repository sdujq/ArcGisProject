package org.sdu.dao;

import java.util.List;

import org.sdu.db.DBHelper;
import org.sdu.pojo.Bug;

import android.content.Context;

import com.tgb.lk.ahibernate.dao.impl.BaseDaoImpl;

public class BugDao extends BaseDaoImpl<Bug> {
	public BugDao(Context c) {
		super(new DBHelper(c));
	}
}
