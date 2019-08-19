package com.csl.demo;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

/**
 * 自定义内容提供器
 * @author Cherry
 * @date 2019年6月13日
 */
public class DatabaseContentProvider extends ContentProvider {
	private SQLiteDatasource sb;
	private static UriMatcher uriMatcher;
	/*
	 * 初始化uriMatcher
	 */
	static {
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI("content://" + SQLConstants.AUTHORITY, "/book", SQLConstants.BOOK_DIR);
		uriMatcher.addURI("content://" + SQLConstants.AUTHORITY, "/book/#", SQLConstants.BOOK_ITEM);
		uriMatcher.addURI("content://" + SQLConstants.AUTHORITY, "/user", SQLConstants.USER_DIR);
		uriMatcher.addURI("content://" + SQLConstants.AUTHORITY, "/user/#", SQLConstants.USER_ITEM);
		uriMatcher.addURI("content://" + SQLConstants.AUTHORITY, "/category", SQLConstants.CATEGORY_DIR);
		uriMatcher.addURI("content://" + SQLConstants.AUTHORITY, "/category/#", SQLConstants.CATEGORY_ITEM);
	}

	/*
	 * 初始化内容提供器的时候调用。通常会在这里完成对数据库的创建和升级等操作， 返回 true 表示内容提供器初始化成功，返回 false
	 * 则表示失败。注意，只有当存在 ContentResolver 尝试访问我们程序中的数据时，内容提供器才会被初始化。
	 */
	@Override
	public boolean onCreate() {
		sb = new SQLiteDatasource(getContext(), "0.db", null, 1);
		return true;
	}

	/*
	 * 从内容提供器中查询数据。使用 uri 参数来确定查询哪张表，projection 参数用于确 定查询哪些列，selection 和
	 * selectionArgs 参数用于约束查询哪些行，sortOrder 参数用于 对结果进行排序，查询的结果存放在 Cursor 对象中返回。
	 */
	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		SQLiteDatabase db = sb.getReadableDatabase();
		Cursor cursor = null;
		switch (uriMatcher.match(uri)) {
		case SQLConstants.BOOK_DIR:
			cursor = db.query("book", projection, selection, selectionArgs, null, null, sortOrder);
			break;
		case SQLConstants.BOOK_ITEM:
			String bookId = uri.getPathSegments().get(1);
			cursor = db.query("book", projection, "id = ?", new String[] { bookId }, null, null, sortOrder);
			break;
		case SQLConstants.USER_DIR:
			cursor = db.query("user", projection, selection, selectionArgs, null, null, sortOrder);
			break;
		case SQLConstants.USER_ITEM:
			String userId = uri.getPathSegments().get(1);
			cursor = db.query("user", projection, "id = ?", new String[] { userId }, null, null, sortOrder);
			break;
		case SQLConstants.CATEGORY_DIR:
			cursor = db.query("category", projection, selection, selectionArgs, null, null, sortOrder);
			break;
		case SQLConstants.CATEGORY_ITEM:
			String categoryId = uri.getPathSegments().get(1);
			cursor = db.query("category", projection, "id = ?", new String[] { categoryId }, null, null, sortOrder);
			break;
		default:
			break;
		}
		return cursor;
	}

	@Override
	public String getType(Uri uri) {
		switch (uriMatcher.match(uri)) {
		case SQLConstants.BOOK_DIR:
			return "vnd.android.cursor.dir/vnd.com.csl.demo.databases.provider.book";
		case SQLConstants.BOOK_ITEM:
			return "vnd.android.cursor.item/vnd.com.csl.demo.databases.provider.book";
		case SQLConstants.CATEGORY_DIR:
			return "vnd.android.cursor.dir/vnd.com.csl.demo.databases.provider.category";
		case SQLConstants.CATEGORY_ITEM:
			return "vnd.android.cursor.item/vnd.com.csl.demo.databases.provider.category";
		case SQLConstants.USER_DIR:
			return "vnd.android.cursor.dir/vnd.com.csl.demo.databases.provider.user";
		case SQLConstants.USER_ITEM:
			return "vnd.android.cursor.item/vnd.com.csl.demo.databases.provider.user";
		}
		return null;
	}

	/*
	 * 向内容提供器中添加一条数据。使用 uri 参数来确定要添加到的表，待添加的数据 保存在 values
	 * 参数中。添加完成后，返回一个用于表示这条新记录的 URI。
	 */
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		SQLiteDatabase db = sb.getWritableDatabase();
		long i = 0;
		Uri u = null;
		switch (uriMatcher.match(uri)) {
		case SQLConstants.BOOK_DIR:
		case SQLConstants.BOOK_ITEM:
			i = db.insert("book", null, values);
			u = Uri.parse("content://" + SQLConstants.AUTHORITY + "/book/" + i);
			break;
		case SQLConstants.CATEGORY_DIR:
		case SQLConstants.CATEGORY_ITEM:
			i = db.insert("category", null, values);
			u = Uri.parse("content://" + SQLConstants.AUTHORITY + "/category/" + i);
			break;
		case SQLConstants.USER_DIR:
		case SQLConstants.USER_ITEM:
			i = db.insert("user", null, values);
			u = Uri.parse("content://" + SQLConstants.AUTHORITY + "/user/" + i);
			break;
		}
		return u;
	}

	/*
	 * 从内容提供器中删除数据。使用 uri 参数来确定删除哪一张表中的数据，selection 和 selectionArgs
	 * 参数用于约束删除哪些行，被删除的行数将作为返回值返回。
	 */
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		SQLiteDatabase db = sb.getWritableDatabase();
		int deletedRows = 0;
		switch (uriMatcher.match(uri)) {
		case SQLConstants.BOOK_DIR:
			deletedRows = db.delete("Book", selection, selectionArgs);
			break;
		case SQLConstants.BOOK_ITEM:
			String bookId = uri.getPathSegments().get(1);
			deletedRows = db.delete("Book", "id = ?", new String[] { bookId });
			break;
		case SQLConstants.CATEGORY_DIR:
			deletedRows = db.delete("Category", selection, selectionArgs);
			break;
		case SQLConstants.CATEGORY_ITEM:
			String categoryId = uri.getPathSegments().get(1);
			deletedRows = db.delete("Category", "id = ?", new String[] { categoryId });
			break;
		case SQLConstants.USER_DIR:
			deletedRows = db.delete("user", selection, selectionArgs);
			break;
		case SQLConstants.USER_ITEM:
			String userId = uri.getPathSegments().get(1);
			deletedRows = db.delete("user", "id = ?", new String[] { userId });
			break;
		default:
			break;
		}
		return deletedRows;
	}

	/*
	 * 更新内容提供器中已有的数据。使用 uri 参数来确定更新哪一张表中的数据，新数 据保存在 values 参数中，selection 和
	 * selectionArgs 参数用于约束更新哪些行，受影响的 行数将作为返回值返回。
	 */
	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		SQLiteDatabase db = sb.getWritableDatabase();
		int updateRows = 0;
		switch (uriMatcher.match(uri)) {
		case SQLConstants.BOOK_DIR:
			updateRows = db.update("Book",values, selection, selectionArgs);
			break;
		case SQLConstants.BOOK_ITEM:
			String bookId = uri.getPathSegments().get(1);
			updateRows = db.update("Book",values, "id = ?", new String[] { bookId });
			break;
		case SQLConstants.CATEGORY_DIR:
			updateRows = db.update("Category",values, selection, selectionArgs);
			break;
		case SQLConstants.CATEGORY_ITEM:
			String categoryId = uri.getPathSegments().get(1);
			updateRows = db.update("Category", values,"id = ?", new String[] { categoryId });
			break;
		case SQLConstants.USER_DIR:
			updateRows = db.update("user",values, selection, selectionArgs);
			break;
		case SQLConstants.USER_ITEM:
			String userId = uri.getPathSegments().get(1);
			updateRows = db.update("user", values,"id = ?", new String[] { userId });
			break;
		default:
			break;
		}
		return updateRows;
	}

}
