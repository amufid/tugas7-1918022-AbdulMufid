package com.example.tugas7_sqlite;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
public class MainCreate extends AppCompatActivity {
    private MyDatabase db;
    private EditText Enama, Ejenis;
    private String Snama, Sjenis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_create);
        db = new MyDatabase(this);
        Enama = (EditText) findViewById(R.id.create_nama);
        Ejenis = (EditText) findViewById(R.id.create_jenis);
        Button btnCreate = (Button) findViewById(R.id.create_btn);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snama = String.valueOf(Enama.getText());
                Sjenis = String.valueOf(Ejenis.getText());
                if (Snama.equals("")){
                    Enama.requestFocus();
                    Toast.makeText(MainCreate.this, "Silahkan isi nama kapal", Toast.LENGTH_SHORT).show();
                }
                else if (Sjenis.equals("")) {
                    Ejenis.requestFocus();
                    Toast.makeText(MainCreate.this, "Silahkan isi jenis kapal", Toast.LENGTH_SHORT).show();
                }
                else {
                        Enama.setText("");
                        Ejenis.setText("");
                        Toast.makeText(MainCreate.this, "Data telah ditambah",
                                Toast.LENGTH_SHORT).show();
                        db.CreateKapal(new Kapal(null, Snama, Sjenis));
                        Intent a = new Intent(MainCreate.this, MainActivity.class);
                        startActivity(a);
                    }
                }
            });
        }
    }


