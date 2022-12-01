package com.vector.clearmemo.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class SqliteHelpUtil {
	
	public static SQLiteDatabase getSQLiteReadableDatabase(Context context){
		
		SqliteDBOpenHelper sqlitHelper = new SqliteDBOpenHelper(context, "clearmemoDatabase",1);
		return sqlitHelper.getReadableDatabase();
		
	}
	
	public static SQLiteDatabase getSQLiteWritableDatabase(Context context){
		
		SqliteDBOpenHelper sqlitHelper = new SqliteDBOpenHelper(context, "clearmemoDatabase",1);
		return sqlitHelper.getWritableDatabase();
		
	}
}
