package com.example.tugas7_sqlite;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;
public class MainRead extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private ListView mListView;
    private CustomListAdapter adapter_off;
    private MyDatabase db;
    private List<Kapal> ListKapal = new ArrayList<Kapal>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_read);
        db = new MyDatabase(this);
        adapter_off = new CustomListAdapter(this, ListKapal);
        mListView = (ListView) findViewById(R.id.list_kapal);
        mListView.setAdapter(adapter_off);
        mListView.setOnItemClickListener(this);
        mListView.setClickable(true);
        ListKapal.clear();
        List<Kapal> kapal = db.ReadKapal();
        for (Kapal tmh : kapal) {
            Kapal daftar = new Kapal();
            daftar.set_id(tmh.get_id());
            daftar.set_nama(tmh.get_nama());
            daftar.set_jenis(tmh.get_jenis());
            ListKapal.add(daftar);
            if ((ListKapal.isEmpty()))
                Toast.makeText(MainRead.this, "Tidak ada data", Toast.LENGTH_SHORT).show();
            else {
            }
        }
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
        Object o = mListView.getItemAtPosition(i);
        Kapal detailTmh = (Kapal) o;
        String Sid = detailTmh.get_id();
        String Snama = detailTmh.get_nama();
        String Sjenis = detailTmh.get_jenis();
        Intent goUpdel = new Intent(MainRead.this, MainUpdel.class);
        goUpdel.putExtra("Iid", Sid);
        goUpdel.putExtra("Inama", Snama);
        goUpdel.putExtra("Ijenis", Sjenis);
        startActivity(goUpdel);
    }
    @Override
    protected void onResume() {
        super.onResume();
        ListKapal.clear();
        mListView.setAdapter(adapter_off);
        List<Kapal> kapal = db.ReadKapal();
        for (Kapal tmh : kapal) {
            Kapal daftar = new Kapal();
            daftar.set_id(tmh.get_id());
            daftar.set_nama(tmh.get_nama());
            daftar.set_jenis(tmh.get_jenis());
            ListKapal.add(daftar);
            if ((ListKapal.isEmpty()))
                Toast.makeText(MainRead.this, "Tidak ada data",
                        Toast.LENGTH_SHORT).show();
            else {
            }
        }
    }
}
