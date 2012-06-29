package org.sdu.dao;

import org.sdu.db.DBHelper;
import org.sdu.pojo.Task;

import android.content.Context;

import com.tgb.lk.ahibernate.dao.impl.BaseDaoImpl;

public class TaskDao extends BaseDaoImpl<Task> {

	public TaskDao(Context c) {
		super(new DBHelper(c));
	}

}
