package com.vector.clearmemo.util;

import com.example.firstandroidprojectclearmemo.R;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SqliteDBOpenHelper extends SQLiteOpenHelper {
	private static int oldVersion;

	public SqliteDBOpenHelper(Context context, String name, int version) {
		super(context, name, null, version);
		oldVersion = version;
		// TODO Auto-generated constructor stub
	}

	public SqliteDBOpenHelper(Context context, String name) {
		super(context, name, null, oldVersion);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		// 创建users表
		// String userssql =
		// "create table users(id integer primary key autoincrement,"
		// +
		// "account varchar(20) not null,nicheng varchar(20) not null,password varchar(20) not null,"
		// +
		// "icon integer not null, address varchar(50) null,sex varchar(4) null,"
		// + "gexingqianming varchar(60) not null )";
		// db.execSQL(userssql);
		// // db.execSQL("CREATE TABLE `users` ("
		// // + "`id`  int UNSIGNED NOT NULL AUTO_INCREMENT ,"
		// // + "`account`  varchar(20) NOT NULL ,"
		// // + "`nicheng`  varchar(20) NOT NULL ,"
		// // + "`password`  varchar(20) NOT NULL ,"
		// // + "`icon`  int NOT NULL ," + "`tel`  varchar(30) NULL ,"
		// // + "`address`  varchar(50) NULL ," + "`sex`  varchar(4) NULL ,"
		// // + "`gexingqianming`  varchar(60) NULL ,"
		// // + "PRIMARY KEY (`id`),"
		// // + "UNIQUE INDEX `nocontaccount` (`account`) " + ")");
		// // 创建types表
		// String typessql =
		// "create table types(id integer primary key autoincrement,name varchar(20) not null,icon integer not null )";
		// db.execSQL(typessql);
		// // db.execSQL("CREATE TABLE `types` ("
		// // + "`id`  int UNSIGNED NOT NULL AUTO_INCREMENT ,"
		// // + "`icon`  int NOT NULL ," + "`name`  varchar(20) NOT NULL ,"
		// // + "PRIMARY KEY (`id`) " + ")");
		//
		// // 创建memos表
		// db.execSQL("CREATE TABLE `memos` ("
		// + "`id`  int UNSIGNED NOT NULL AUTOINCREMENT ,"
		// + "`title`  varchar(20) NOT NULL ,"
		// + "`createdate`  varchar(20) NOT NULL ,"
		// + "`clockdate`  varchar(20) NOT NULL ,"
		// + "`contentsummary`  varchar(20) NOT NULL ,"
		// + "`path`  varchar(255) NOT NULL ,"
		// + "`type_id`  int(10) UNSIGNED NOT NULL ,"
		// + "`user_id`  int(10) UNSIGNED NOT NULL ,"
		// + "PRIMARY KEY (`id`),"
		// +
		// "CONSTRAINT `FK_typeid` FOREIGN KEY (`type_id`) REFERENCES `types` (`id`),"
		// +
		// "CONSTRAINT `FK_userid` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)"
		// + ")");
		//
		db.execSQL("CREATE TABLE users ("
				+ "id integer primary key autoincrement," + "account  varchar,"
				+ "nicheng  varchar ," + "password  varchar ,"
				+ "icon  integer ," + "tel  varchar," + "address  varchar,"
				+ "sex  varchar ," + "gexingqianming  varchar " + ")");

		// 创建types表
		db.execSQL("CREATE TABLE `types` ("
				+ "id integer primary key autoincrement," + "`icon`  int ,"
				+ "`name`  varchar " + ")");

		// 创建memos表
		db.execSQL("CREATE TABLE `memos` ("
				+ "id integer primary key autoincrement,"
				+ "`title`  varchar  ,"
				+ "`createdate`  varchar(20)  ,"
				+ "`clockdate`  varchar(20) ,"
				+ "`contentsummary`  varchar(20) ,"
				+ "`path`  varchar(255)  ,"
				+ "`type_id`  int ,"
				+ "`user_id`  int ,"
				+ "foreign key (type_id) references  types(id) on delete cascade on update cascade ,"
				+ "foreign key (user_id) references  users(id) on delete cascade on update cascade "
				+ ")");
	
	
		//加入类型数据
		int icon;
		icon = R.drawable.work;
		db.execSQL("insert into types values(1,"+icon+",'工作')");
		icon = R.drawable.study;
		db.execSQL("insert into types values(2,"+icon+",'学习')");
		icon = R.drawable.play;
		db.execSQL("insert into types values(3,"+icon+",'娱乐')");
		icon = R.drawable.shopping;
		db.execSQL("insert into types values(4,"+icon+",'购物')");
		icon = R.drawable.other;
		db.execSQL("insert into types values(5,"+icon+",'其他')");


	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
