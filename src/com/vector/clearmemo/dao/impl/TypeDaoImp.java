package com.vector.clearmemo.dao.impl;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.vector.clearmemo.dao.ITypeDao;
import com.vector.clearmemo.domain.po.Type;
import com.vector.clearmemo.util.SqliteHelpUtil;

public class TypeDaoImp implements ITypeDao {

	SQLiteDatabase db;
	Context context;

	public TypeDaoImp(Context context) {
		super();
		this.context = context;
	}

	@Override
	public List<Type> selectTypeAll() {
		// TODO Auto-generated method stub
		// 打开数据库 读-查询
		db = SqliteHelpUtil.getSQLiteReadableDatabase(context);

		// sql语句
		String sql = "select * from types";

		List<Type> list = new ArrayList<Type>();
		// 执行查询sql语句，返回是游标结果集
		Cursor cursor = db.rawQuery(sql, null);
		// 遍历游标
		while (cursor.moveToNext()) {
			// 获取第一列的值
			int id = cursor.getInt(0);
			// 获取第二列的值
			int icon = cursor.getInt(1);
			// 获取第三列的值
			String name = cursor.getString(2);

			Type type = new Type(id, icon, name);
			list.add(type);
		}

		db.close();
		return list;

	}



}
