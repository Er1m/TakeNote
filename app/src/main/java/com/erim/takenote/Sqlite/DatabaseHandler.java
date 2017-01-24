package com.erim.takenote.Sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

/**
 * Created by UsErim on 20.1.2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "TakeNote";
    private static int VERSION = 1;

    private String Table_name = "notes";
    private String Column_title = "title";
    private String Column_note = "note";
    private String Column_date = "date";

    private final ArrayList<Note> notlar = new ArrayList<>();


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME,null,VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+Table_name+" ( id INTEGER PRIMARY KEY,"+Column_title+" TEXT,"+Column_note+" TEXT,"+Column_date+" TEXT"+ ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Table_name);
        onCreate(db);
    }



    public void addNote(Note note){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Column_title,note.getNtitle());
        values.put(Column_note,note.getNnote());
        values.put(Column_date,note.getNdate());

        db.insert(Table_name,null,values);
        db.close();
    }



    public ArrayList<Note> getNotes() {
        notlar.clear();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Table_name, new String[]{"id", Column_title, Column_note,Column_date}, null, null, null, null, null);
         if(cursor.moveToFirst())
             do{
                 Note note = new Note();
                 note.setId(cursor.getInt(cursor.getColumnIndex("id")));
                 note.setNtitle(cursor.getString(cursor.getColumnIndex(Column_title)));
                 note.setNnote(cursor.getString(cursor.getColumnIndex(Column_note)));

                 //Çalışmayan kodlar
                 /*DateFormat dateFormat = DateFormat.getDateInstance();
                 String date = dateFormat.format(new Date(cursor.getLong(cursor.getColumnIndex(Column_date))).getTime());
                 note.setNdate(date);*/

                 note.setNdate(cursor.getString(cursor.getColumnIndex(Column_date)));

                notlar.add(note);
             }while (cursor.moveToNext());

        cursor.close();
        db.close();

        return notlar;
    }


    public Note getNote(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + Table_name + " WHERE id=" + id;
        Cursor cursor = db.rawQuery(selectQuery,null);


        Note note = new Note();
        if (cursor != null && cursor.moveToFirst()){


            note.setNtitle(cursor.getString(cursor.getColumnIndex(Column_title)));
            note.setNnote(cursor.getString(cursor.getColumnIndex(Column_note)));
            note.setId(cursor.getInt(cursor.getColumnIndex("id")));

            note.setNdate(cursor.getString(cursor.getColumnIndex(Column_date)));

        }
        return note;
    }



    public void updateNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Column_title, note.getNtitle());
        values.put(Column_note, note.getNnote());
        values.put(Column_date, note.getNdate());

        // updating row
        db.update(Table_name, values, "id" + " = ?",
                new String[] { String.valueOf(note.getId()) });
    }



    public void deleteNote(Note note) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Table_name, "id" + " = ?",
                new String[] { String.valueOf(note.getId()) });
        db.close();
    }


}
