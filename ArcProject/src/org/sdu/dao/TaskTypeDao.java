package org.sdu.dao;

import org.sdu.db.DBHelper;
import org.sdu.pojo.TaskType;

import android.content.Context;

import com.tgb.lk.ahibernate.dao.impl.BaseDaoImpl;

public class TaskTypeDao extends BaseDaoImpl<TaskType> {

	public TaskTypeDao(Context c) {
		super(new DBHelper(c));
	}

}
