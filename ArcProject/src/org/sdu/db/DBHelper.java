package org.sdu.db;

import org.sdu.dao.BugTypeDao;
import org.sdu.dao.TaskTypeDao;
import org.sdu.pojo.Bug;
import org.sdu.pojo.BugType;
import org.sdu.pojo.RoadLine;
import org.sdu.pojo.Task;
import org.sdu.pojo.TaskType;
import org.sdu.pojo.User;
import org.sdu.pojo.Values;

import android.content.Context;

import com.tgb.lk.ahibernate.util.MyDBHelper;

public class DBHelper extends MyDBHelper {
	private static final String DBNAME = "gis.db";
	private static final int DBVERSION = 4;
	private static final Class<?>[] classes = { Task.class, Bug.class,
			TaskType.class, BugType.class, User.class, RoadLine.class,Values.class};
	private Context context;
	public DBHelper(Context context) {
		super(context, DBNAME, null, DBVERSION, classes);
		this.context=context;
	}

	@Override
	public void initDb() {
		TaskTypeDao taskTypeDao=new TaskTypeDao(context);
		BugTypeDao bugTypeDao=new BugTypeDao(context);
		String[] type1=new String[]{"设备巡检","设备维护"};
		String[] type2=new String[]{"水管破裂","水管冰冻"};
		TaskType t=new TaskType();
		BugType b=new BugType();
		if(taskTypeDao.find().size()!=0){
			return;
		}
		for(String str:type1){
			t.setName(str);
			taskTypeDao.insert(t);
		}
		for(String str:type2){
			b.setName(str);
			bugTypeDao.insert(b);
		}
	}

}