package com.csl.demo;
/**
 * 创建表的SQL语句常量
 * @author Cherry
 * @date  2019年6月12日
 */
public class SQLConstants {
	public static final String CREATE_USER = "create table User ("
			+ "id integer primary key autoincrement, "
			+ "username text, "
			+ "password text, "
			+ "age integer, "
			+ "phone text)";
	public static final String CREATE_BOOK = "create table Book ("
			+ "id integer primary key autoincrement, "
			+ "author text, "
			+ "price real, "
			+ "pages integer, "
			+ "name text)";
	public static final String CREATE_CATEGORY = "create table Category ("
			+ "id integer primary key autoincrement, "
			+ "category_name text, "
			+ "category_code integer)";
	
	public static final String DROP_BOOK = "drop table if exists Book";
	public static final String DROP_USER = "drop table if exists User";
	public static final String DROP_CATEGORY = "drop table if exists Category";
	
	public static final int BOOK_DIR = 0;
	public static final int BOOK_ITEM = 1;
	public static final int USER_DIR = 2;
	public static final int USER_ITEM = 3;
	public static final int CATEGORY_DIR = 4;
	public static final int CATEGORY_ITEM = 5;
	
	public static final String AUTHORITY = "com.csl.demo.databases.provider";
}
