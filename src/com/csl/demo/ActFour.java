package com.csl.demo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * SQLite是一个进程内的库，实现了自给自足的、无服务器的、零配置的、事务性的 SQL 数据库引擎。
 * 它是一个零配置的数据库，这意味着与其他数据库一样，您不需要在系统中配置。
 * @author Cherry
 * @date 2019年6月12日
 */
public class ActFour extends BaseActivity {
	private SQLiteDatasource ds = new SQLiteDatasource(ActFour.this, "0.db", null, 1);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_four);
		this.createListenButton(R.id.create);
		this.createListenButton(R.id.insert);
		this.createListenButton(R.id.update);
		this.createListenButton(R.id.query);
		this.createListenButton(R.id.delete);
		this.createListenButton(R.id.user);
		this.createListenButton(R.id.category);
		this.createListenButton(R.id.upgrade);
		this.createListenButton(R.id.G);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.create:
			create();
			break;
		case R.id.insert:
			insert();
			break;
		case R.id.delete:
			delete();
			break;
		case R.id.update:
			update();
			break;
		case R.id.query:
			query();
			break;
		case R.id.user:
			insertUser();
			break;
		case R.id.category:
			insertCategory();
			break;
		case R.id.G:
			//mySql();
			break;
		case R.id.upgrade:
			//upgrade();
			break;
		default:
			break;
		}
	}
	
	/*
	 * 自定義SQL語句執行
	 */
	private void mySql() {
		SQLiteDatabase db = ds.getWritableDatabase();
		db.beginTransaction();
		db.rawQuery("select * from book where author = ?",  new String[] { "Cherry Chen" });
		db.execSQL("delete book where author = ?", new String[] { "Cherry Chen" });
	}

	private void query() {
		SQLiteDatabase db = ds.getWritableDatabase();
		Cursor cursor = db.query("book", null, "author = ?", new String[] { "Cherry Chen" }, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				String name = cursor.getString(cursor.getColumnIndex("name"));
				String author = cursor.getString(cursor.getColumnIndex("author"));
				int pages = cursor.getInt(cursor.getColumnIndex("pages"));
				double price = cursor.getDouble(cursor.getColumnIndex("price"));
				Toast.makeText(this, name+author+pages+price, Toast.LENGTH_SHORT).show();
			} while (cursor.moveToNext());
		}
		cursor.close();
	}

	private void update() {
		SQLiteDatabase db = ds.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("author", "Cherry Chen");
		int i = db.update("book", values, "pages = ?", new String[] { "454" });
		if (i != 0) {
			Toast.makeText(this, "Update succeeded", Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(this, "Operate Failed", Toast.LENGTH_SHORT).show();
		}
	}

	private void delete() {
		SQLiteDatabase db = ds.getWritableDatabase();
		int i = db.delete("Book", "author = ?", new String[] { "Dan Brown" });
		if (i != 0) {
			Toast.makeText(this, "Delete succeeded", Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(this, "Operate Failed", Toast.LENGTH_SHORT).show();
		}
	}

	/*
	 * ContentValues来对要添加的数据进行组装
	 */
	private void insert() {
		SQLiteDatabase db = ds.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("name", "The Da Vinci Code");
		values.put("author", "Dan Brown");
		values.put("pages", 454);
		values.put("price", 16.96);
		db.insert("Book", null, values);
		values.clear();
		values.put("name", "The Lost Symbol");
		values.put("author", "Dan Brown");
		values.put("pages", 510);
		values.put("price", 19.95);
		db.insert("Book", null, values);
		Toast.makeText(this, "Insert succeeded", Toast.LENGTH_SHORT).show();
	}
	
	public void insertUser(){
		SQLiteDatabase db = ds.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("username", "Cherry");
		values.put("password", "DanBrown");
		values.put("age", 29);
		values.put("phone","18772319133");
		db.insert("user", null, values);
		values.clear();
		values.put("username", "Chen");
		values.put("password", "abcdaed");
		values.put("age", 35);
		values.put("phone","15772319133");
		db.insert("user", null, values);
	}
	
	public void insertCategory(){
		SQLiteDatabase db = ds.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("category_name", "哲学类");
		values.put("category_code", "001");
		db.insert("category", null, values);
		values.clear();
		values.put("category_name", "心理学");
		values.put("category_code", "002");
		db.insert("category", null, values);
	}
	
	private void create() {
		ds.getReadableDatabase();
	}
	
	private void upgrade(){
		ds = new SQLiteDatasource(ActFour.this, "0.db", null, 2);
		SQLiteDatabase db = ds.getReadableDatabase();
		if(db != null){
			Toast.makeText(this, "upgrade succeeded", Toast.LENGTH_SHORT).show();
		}else{
			Toast.makeText(this, "upgrade failed", Toast.LENGTH_SHORT).show();
		}
	}
}
