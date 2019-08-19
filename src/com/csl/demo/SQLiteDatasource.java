package com.csl.demo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
/**
 * SQLiteOpenHelper实现类
 * @author Cherry
 * @date  2019年6月12日
 */
public class SQLiteDatasource extends SQLiteOpenHelper {
	private Context c;
	public SQLiteDatasource(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
		c = context;
	}
	
	/*
	 * 创建数据库
	 * 此方法只会执行一次
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SQLConstants.DROP_BOOK);
		db.execSQL(SQLConstants.DROP_CATEGORY);
		db.execSQL(SQLConstants.CREATE_BOOK);
		db.execSQL(SQLConstants.CREATE_CATEGORY);
		db.execSQL(SQLConstants.DROP_USER);
		db.execSQL(SQLConstants.CREATE_USER);
		Toast.makeText(c, "Create succeeded", Toast.LENGTH_SHORT).show();
	}
	/*
	 * 升级数据库
	 * 传入一个比 1 大的数，即新的数据库版本，就可以让 onUpgrade()方法得到执行。
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		/*
		 * 新增一张表
		 */
		db.execSQL(SQLConstants.DROP_USER);
		db.execSQL(SQLConstants.CREATE_USER);
		Toast.makeText(c, "upgrade succeeded", Toast.LENGTH_SHORT).show();
	}
}
