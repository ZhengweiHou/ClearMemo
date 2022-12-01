package com.vector.clearmemo.dao.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.vector.clearmemo.dao.IUserDao;
import com.vector.clearmemo.domain.po.User;
import com.vector.clearmemo.util.SqliteHelpUtil;

public class UserDaoImpl implements IUserDao {
	String sql = "";
	Context context;
	SQLiteDatabase db;

	public UserDaoImpl(Context context) {
		this.context = context;
	}

	// µÈ´ýÐÞ¸Ä
	@Override
	public void addUser(String account, String password, String nicheng) {
		User user = new User(account, password);
		db = SqliteHelpUtil.getSQLiteWritableDatabase(context);
		sql = "insert into users values(null,?,?,?,?,null,null,?,null)";
		Object[] userAgrs = new Object[] { user.getAccount(), nicheng,
				user.getPassword(), 1, "Î´Öª" };
		db.execSQL(sql, userAgrs);
		db.close();
	}

	@Override
	public User findUser(String account) {
		// TODO Auto-generated method stub
		db = SqliteHelpUtil.getSQLiteReadableDatabase(context);
		User user = null;
		sql = "select * from users where account = ?";
		Cursor cursor = db.rawQuery(sql, new String[] { account });
		while (cursor.moveToNext()) {
			user = new User();
			user.setId(cursor.getInt(0));
			user.setAccount(cursor.getString(1));
			user.setNicheng(cursor.getString(2));
			user.setPassword(cursor.getString(3));
			user.setIcon(cursor.getInt(4));
			user.setTel(cursor.getString(5));
			user.setAddress(cursor.getString(6));
			user.setSex(cursor.getString(7));
			user.setGexingqianming(cursor.getString(8));
		}
		cursor.close();
		db.close();
		return user;
	}

	@Override
	public void updatenicheng(User user) {
		// TODO Auto-generated meth
		db = SqliteHelpUtil.getSQLiteWritableDatabase(context);
		sql = "update users set nicheng = ? where id = ?";
		db.execSQL(sql, new Object[] { user.getNicheng(), user.getId() });
		db.close();
	}

	@Override
	public void updatepassword(User user) {
		// TODO Auto-generated method stub
		db = SqliteHelpUtil.getSQLiteWritableDatabase(context);
		sql = "update users set password = ? where id = ?";
		db.execSQL(sql, new Object[] { user.getPassword(), user.getId() });
		db.close();
	}

	@Override
	public void updatetel(User user) {
		// TODO Auto-generated method stub'
		db = SqliteHelpUtil.getSQLiteWritableDatabase(context);
		sql = "update users set tel = ? where id = ?";
		db.execSQL(sql, new Object[] { user.getTel(), user.getId() });
		db.close();
	}

	@Override
	public void uppdatesex(User user) {
		// TODO Auto-generated method stub
		db = SqliteHelpUtil.getSQLiteWritableDatabase(context);
		sql = "update users set sex = ? where id = ?";
		db.execSQL(sql, new Object[] { user.getSex(), user.getId() });
		db.close();
	}

	@Override
	public void updateaddress(User user) {
		// TODO Auto-generated method stub
		db = SqliteHelpUtil.getSQLiteWritableDatabase(context);
		sql = "update users set address = ? where id = ?";
		db.execSQL(sql, new Object[] { user.getAddress(), user.getId() });
		db.close();
	}

	@Override
	public void updategexingqianming(User user) {
		// TODO Auto-generated method stub
		db = SqliteHelpUtil.getSQLiteWritableDatabase(context);
		sql = "update users set gexingqianming = ? where id = ?";
		db.execSQL(sql, new Object[] { user.getGexingqianming(), user.getId() });
		db.close();
	}

}
