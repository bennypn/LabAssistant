package com.example.labassistant;

import static com.example.labassistant.LoginActivity.usernm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MatkulActivity extends AppCompatActivity {

    ImageView pst, pijfo, pstr, pkr;
    protected static String matkul, jobsheet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matkul);

        pst = findViewById(R.id.pst_iv);
        pstr = findViewById(R.id.pstr_iv);
        pijfo = findViewById(R.id.pijfo_tv);
        pkr = findViewById(R.id.pkr_iv);

        pst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                matkul = "Praktik Sistem Telekomunikasi";
                // Write a message to the database
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("user").child(usernm).child("matkul");
                myRef.setValue(matkul);
                Intent i = new Intent(MatkulActivity.this, JobsheetActivity.class);
                startActivity(i);
            }
        });

        pstr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                matkul = "Praktik Sistem Transmisi";
                // Write a message to the database
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("user").child(usernm).child("matkul");
                myRef.setValue(matkul);
                Intent i = new Intent(MatkulActivity.this, JobsheetActivity.class);
                startActivity(i);
            }
        });

        pijfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                matkul = "Praktik Instalasi Jaringan Fiber Optik";
                // Write a message to the database
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("user").child(usernm).child("matkul");
                myRef.setValue(matkul);
                Intent i = new Intent(MatkulActivity.this, JobsheetActivity.class);
                startActivity(i);
            }
        });

        pkr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                matkul = "Praktik Komunikasi Radio";

                // Write a message to the database
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("user").child(usernm).child("matkul");
                myRef.setValue(matkul);

                Intent i = new Intent(MatkulActivity.this, JobsheetActivity.class);
                startActivity(i);
            }
        });

    }
}