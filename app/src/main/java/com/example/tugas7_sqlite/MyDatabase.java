package com.example.tugas7_sqlite;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;
public class MyDatabase extends SQLiteOpenHelper {
    private static int DATABASE_VERSION = 1;
    private static String DATABASE_NAME = "db_pelabuhan";
    private static final String tb_kapal = "tb_kapal";
    private static final String tb_kapal_id = "id";
    private static final String tb_kapal_nama = "nama";
    private static final String tb_kapal_jenis = "jenis";
    private static final String CREATE_TABLE_KAPAL = "CREATE TABLE "
            + tb_kapal +"("
            + tb_kapal_id + " INTEGER PRIMARY KEY ,"
            + tb_kapal_nama + " TEXT ,"
            + tb_kapal_jenis + " TEXT " + ")";
    public MyDatabase (Context context){
        super(context, DATABASE_NAME, null , DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_KAPAL);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion) {
    }
    public void CreateKapal(Kapal data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(tb_kapal_id, data.get_id());
        values.put(tb_kapal_nama, data.get_nama());
        values.put(tb_kapal_jenis, data.get_jenis());
        db.insert(tb_kapal, null, values);
        db.close();
    }
    public List<Kapal> ReadKapal() {
        List<Kapal> listMhs = new ArrayList<Kapal>();
        String selectQuery = "SELECT * FROM " + tb_kapal;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Kapal data = new Kapal();
                data.set_id(cursor.getString(0));
                data.set_nama(cursor.getString(1));
                data.set_jenis(cursor.getString(2));
                listMhs.add(data);
            } while (cursor.moveToNext());
        }
        db.close();
        return listMhs;
    }
    public int UpdateKapal (Kapal data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(tb_kapal_nama, data.get_nama());
        values.put(tb_kapal_jenis, data.get_jenis());
        return db.update(tb_kapal, values, tb_kapal_id + " = ?",
                new String[]{String.valueOf((data.get_id()))});
    }
    public void DeleteKapal(Kapal data){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tb_kapal,tb_kapal_id+ " = ?",
                new String[]{String.valueOf(data.get_id())});
        db.close();
    }
}
