package org.sdu.dao;

import org.sdu.db.DBHelper;
import org.sdu.pojo.Values;

import android.content.Context;

import com.tgb.lk.ahibernate.dao.impl.BaseDaoImpl;

public class ValuesDao extends BaseDaoImpl<Values> {

	public ValuesDao(Context c) {
		super(new DBHelper(c));
	}

}
