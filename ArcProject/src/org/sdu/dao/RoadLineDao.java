package org.sdu.dao;

import org.sdu.db.DBHelper;
import org.sdu.pojo.RoadLine;

import android.content.Context;

import com.tgb.lk.ahibernate.dao.impl.BaseDaoImpl;

public class RoadLineDao extends BaseDaoImpl<RoadLine> {

	public RoadLineDao(Context c) {
		super(new DBHelper(c));
	}

}
