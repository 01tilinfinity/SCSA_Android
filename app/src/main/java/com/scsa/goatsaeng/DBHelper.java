package com.scsa.goatsaeng;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "todo.db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "todo";
    private static final String COL_ID = "id";
    private static final String COL_CONTENT = "content";
    private static final String COL_DONE = "done";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_CONTENT + " TEXT, " +
                COL_DONE + " INTEGER)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insertTodo(String content) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_CONTENT, content);
        values.put(COL_DONE, 0);
        db.insert(TABLE_NAME, null, values);
    }

    public void updateTodo(int id, String content) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_CONTENT, content);
        db.update(TABLE_NAME, values, COL_ID + "=?", new String[]{String.valueOf(id)});
    }

    public void deleteTodo(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COL_ID + "=?", new String[]{String.valueOf(id)});
    }

    public void setDone(int id, boolean done) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_DONE, done ? 1 : 0);
        db.update(TABLE_NAME, values, COL_ID + "=?", new String[]{String.valueOf(id)});
    }

    public List<TodoItem> getAllTodos() {
        List<TodoItem> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(COL_ID));
            String content = cursor.getString(cursor.getColumnIndexOrThrow(COL_CONTENT));
            int done = cursor.getInt(cursor.getColumnIndexOrThrow(COL_DONE));
            list.add(new TodoItem(id, content, done == 1));
        }

        cursor.close();
        return list;
    }
}
