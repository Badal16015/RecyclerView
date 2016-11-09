package com.code.abhishek.recycler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.code.abhishek.recycler.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME="dbname";
    private static final int DB_VERSION = 1;
    private static final String DBTB = "dbtb";
    private static final String RECYCLE = "create table "+DBTB +"(title TEXT primary key,description TEXT)";
    Context context;
   public static DatabaseHelper dh;
  public static List<Student> al=new ArrayList<Student>();
    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }
    public static DatabaseHelper get(Context context) {
        if (dh == null) {
            dh = new DatabaseHelper(context);
        }
        return dh;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + RECYCLE);
        onCreate(db);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(RECYCLE);
    }


    public void insertIntoDB(String title,String description){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("describe", description);
        db.insert(DBTB, null, values);
        db.close();
    }
    public List<Student> getDataFromDB(){
       List<Student> sList = new ArrayList<Student>();
        String query = "select * from "+DBTB;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()){
            do {

                Student st = new Student();
                st.setTitle(cursor.getString(0));
                st.setDescribe(cursor.getString(1));
                al.add(st);
                sList.add(st);
            }while (cursor.moveToNext());

        }
        return sList;
    }
    public Student getStudent(UUID id) {
        for (Student stt : al) {
            if (stt.getsId().equals(id)) {
                return stt;
            }
        }
        return null;
    }
    public List<Student> getData() {
        return al;
    }

}
