package com.vector.clearmemo.dao.impl;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.vector.clearmemo.BaseActivity;
import com.vector.clearmemo.dao.IMemoDao;
import com.vector.clearmemo.domain.po.Memo;
import com.vector.clearmemo.util.SqliteHelpUtil;

public class MemoDaoImp implements IMemoDao {
	SQLiteDatabase db;
	Context context;

	public MemoDaoImp(Context context) {
		super();
		this.context = context;
	}

	@Override
	public void insertmemo(Memo memo) {
		db = SqliteHelpUtil.getSQLiteWritableDatabase(context);
		ContentValues values = new ContentValues();
		values.put("title", memo.getTitle());
		values.put("createdate", memo.getCreatedate());
		values.put("clockdate", memo.getClockdate());
		values.put("contentsummary", memo.getContentsummary());
		values.put("path", memo.getPath());
		values.put("type_id", memo.getType_id());
		values.put("user_id", memo.getUser_id());

		db.insert("memos", null, values);
		db.close();
	}

	@Override
	public List<Memo> selectMemoByUid(int uid) {
		db = SqliteHelpUtil.getSQLiteReadableDatabase(context);
		String sql = "select * from memos where user_id = ?";
		List<Memo> list = new ArrayList<Memo>();
		Cursor cursor = db.rawQuery(sql, new String[] { uid + "" });
		while (cursor.moveToNext()) {
			int id = cursor.getInt(0);
			String title = cursor.getString(1);
			String createdate = cursor.getString(2);
			String clockdate = cursor.getString(3);
			String contentsummary = cursor.getString(4);
			String path = cursor.getString(5);
			int type_id = cursor.getInt(6);
			int user_id = cursor.getInt(7);

			Memo memo = new Memo(id, title, createdate, clockdate,
					contentsummary, path, type_id, user_id);

			list.add(memo);
		}
		cursor.close();
		db.close();

		return list;
	}

	@Override
	public void updatememo(Memo memo) {
		db = SqliteHelpUtil.getSQLiteWritableDatabase(context);
		ContentValues values = new ContentValues();
		values.put("title", memo.getTitle());
		values.put("createdate", memo.getCreatedate());
		values.put("clockdate", memo.getClockdate());
		values.put("contentsummary", memo.getContentsummary());
		values.put("type_id", memo.getType_id());
		db.update("memos", values, "id=?", new String[] { memo.getId() + "" });
		db.close();

	}

	@Override
	public void deleteMemoByid(int id) {
		db = SqliteHelpUtil.getSQLiteWritableDatabase(context);
		db.delete("memos", "id=?", new String[] { id + "" });
		db.close();

	}

	@Override
	public List<Memo> selectMemoByUidAndTypeid(int uid, int typeid) {
		db = SqliteHelpUtil.getSQLiteReadableDatabase(context);
		String sql = "select * from memos where user_id = ? and type_id= ?";
		List<Memo> list = new ArrayList<Memo>();
		Cursor cursor = db
				.rawQuery(sql, new String[] { uid + "", typeid + "" });
		while (cursor.moveToNext()) {
			int id = cursor.getInt(0);
			String title = cursor.getString(1);
			String createdate = cursor.getString(2);
			String clockdate = cursor.getString(3);
			String contentsummary = cursor.getString(4);
			String path = cursor.getString(5);
			int type_id = cursor.getInt(6);
			int user_id = cursor.getInt(7);

			Memo memo = new Memo(id, title, createdate, clockdate,
					contentsummary, path, type_id, user_id);

			list.add(memo);
		}
		cursor.close();
		db.close();

		return list;
	}

	@Override
	public List<Memo> selectMemoSearchContent(String searchinputcontent) {
		db = SqliteHelpUtil.getSQLiteReadableDatabase(context);

		Cursor cursor = db.query(
				"memos",
				new String[] { "*" },
				"(title like ? or contentsummary like ? ) and user_id = ?",
				new String[] { "%" + searchinputcontent + "%",
						"%" + searchinputcontent + "%",
						BaseActivity.logingUser.getId() + "" }, null, null,
				"id", null);

		List<Memo> list = new ArrayList<Memo>();
		;
		while (cursor.moveToNext()) {
			int id = cursor.getInt(0);
			String title = cursor.getString(1);
			String createdate = cursor.getString(2);
			String clockdate = cursor.getString(3);
			String contentsummary = cursor.getString(4);
			String path = cursor.getString(5);
			int type_id = cursor.getInt(6);
			int user_id = cursor.getInt(7);

			Memo memo = new Memo(id, title, createdate, clockdate,
					contentsummary, path, type_id, user_id);

			list.add(memo);
		}
		cursor.close();
		db.close();

		return list;
	}

	@Override
	public Memo selectMemoByid(int id) {
		db = SqliteHelpUtil.getSQLiteReadableDatabase(context);

		Cursor cursor = db.query(
				"memos",
				new String[] { "*" },
				"id = ?",
				new String[] {id+""}, null, null,
				null, null);

		Memo memo = null;
		while (cursor.moveToNext()) {
			int mid = cursor.getInt(0);
			String title = cursor.getString(1);
			String createdate = cursor.getString(2);
			String clockdate = cursor.getString(3);
			String contentsummary = cursor.getString(4);
			String path = cursor.getString(5);
			int type_id = cursor.getInt(6);
			int user_id = cursor.getInt(7);

			memo = new Memo(mid, title, createdate, clockdate,
					contentsummary, path, type_id, user_id);

		}
		cursor.close();
		db.close();
		return memo;
	}
}
