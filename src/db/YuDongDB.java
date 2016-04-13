package db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import model.User;

public class YuDongDB {
	/**
	 * ���ݿ���
	 */
	public static final String DB_NAME = "users";
	/**
	 * ���ݿ�汾
	 */
	public static final int VERSION = 1;
	private static YuDongDB yuDongDB;
	private SQLiteDatabase db;

	/**
	 * �����췽��˽�л�
	 */
	private YuDongDB(Context context) {
		MyDatabaseHelper dbHelper = new MyDatabaseHelper(context, DB_NAME, null, VERSION);
		db = dbHelper.getWritableDatabase();
	}

	/**
	 * ��ȡyuDongDB��ʵ����
	 */
	public synchronized static YuDongDB getInstance(Context context) {
		if (yuDongDB == null) {
			yuDongDB = new YuDongDB(context);
		}
		return yuDongDB;
	}

	/**
	 * ���û��ʺź�����������ݿ⡣
	 */
	public void saveUser(User user) {
		if (user != null) {
			ContentValues values = new ContentValues();
			values.put("user_number", user.getNumber());
			values.put("user_password", user.getPassword());
			db.insert("Users", null, values);
		}
	}

	/**
	 * �����ݿ��ȡĳ���������е�����Ϣ��
	 */
	public String loadusers(String number) {
		String pwd = null;	
		Cursor cursor = db.query("Users", null, "number = ?", new String[] { String.valueOf(number) }, null, null,
				null);
		if (cursor.moveToFirst()) {
			do {
				pwd = cursor.getString(cursor.getColumnIndex("password"));
			} while (cursor.moveToNext());
		}
		return pwd;
	}
}
