package org.sdu.db;

import org.sdu.pojo.Bug;
import org.sdu.pojo.BugType;
import org.sdu.pojo.RoadLine;
import org.sdu.pojo.Task;
import org.sdu.pojo.TaskType;
import org.sdu.pojo.User;

import android.content.Context;

import com.tgb.lk.ahibernate.util.MyDBHelper;

public class DBHelper extends MyDBHelper {
	private static final String DBNAME = "gis.db";
	private static final int DBVERSION = 2;
	private static final Class<?>[] classes = { Task.class, Bug.class,
			TaskType.class, BugType.class, User.class, RoadLine.class };

	public DBHelper(Context context) {
		super(context, DBNAME, null, DBVERSION, classes);
	}

}