package com.example.sqllite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHandler extends SQLiteOpenHelper {

    private static final String DATABASE="Student";
    private static final String COL1="USN";
    private static final String COL2="NAME";
    private static final String COL3="GENDER";
    private static final String COL4="BRANCH";
    private static final String COL5="PHONE";
    private static final String COL6="SEM";
    private static final String COL7="SEC";
    private static final String ID="id";
    private static final String UNAME="username";
    private static final String PASSWORD="password";
    private static final String TABLE_USER="Users";



    private static final String TABLE_NAME="STUDENT";
    private static final String uqu=" CREATE TABLE Users(id INTEGER PRIMARY KEY AUTOINCREMENT,username VARCHAR(20),password VARCHAR(20)) ";




    public DbHandler(@Nullable Context context) {
        super(context, DATABASE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String qu=" CREATE TABLE STUDENT(USN INTEGER,NAME VARCHAR(20),GENDER CHAR(1),BRANCH VARCHAR(10),PHONE NUMBER(10),SEM CHAR(4),SEC CHAR(1)) ";
        sqLiteDatabase.execSQL(qu);
        sqLiteDatabase.execSQL(uqu);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS "+ TABLE_NAME);
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS "+ TABLE_USER);


        onCreate(sqLiteDatabase);


    }

    public boolean insertData(String usn, String name, String gender, String branch,String phone,String sem,String sec){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL1,usn);
        contentValues.put(COL2,name);
        contentValues.put(COL3,gender);
        contentValues.put(COL4,branch);
        contentValues.put(COL5,phone);
        contentValues.put(COL6,sem);
        contentValues.put(COL7,sec);


        long result= db.insert(TABLE_NAME,null,contentValues);
       if(result==-1)
           return false;
       else
           return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }

    public boolean updateData(String usn, String name, String gender, String branch,String phone,String sem,String sec){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL1,usn);
        contentValues.put(COL2,name);
        contentValues.put(COL3,gender);
        contentValues.put(COL4,branch);
        contentValues.put(COL5,phone);
        contentValues.put(COL6,sem);
        contentValues.put(COL7,sec);


         db.update(TABLE_NAME,contentValues,"USN = ?",new String[] {usn});

         return true;
    }
    public int deletedata(String usn){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(TABLE_NAME,"USN = ?",new String[] { usn});
    }

    public  boolean addUsers(String user,String pwd){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(UNAME,user);
        contentValues.put(PASSWORD,pwd);
        long result=db.insert("Users",null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }

    public boolean checkUser(String user, String pwd){
        String[] columns = { ID };
        SQLiteDatabase db = getReadableDatabase();
        String selection = UNAME + "=?" + " and " + PASSWORD + "=?";
        String[] selectionArgs = { user, pwd };
        Cursor cursor = db.query(TABLE_USER,columns,selection,selectionArgs,null,null,null);
        int count = cursor.getCount();
        cursor.close();
        if(count>0)
            return  true;
        else
            return  false;
    }







}
