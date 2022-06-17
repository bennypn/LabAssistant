package com.example.labassistant;

import static com.example.labassistant.LoginActivity.usernm;
import static com.example.labassistant.MatkulActivity.jobsheet;
import static com.example.labassistant.MatkulActivity.matkul;
import static com.example.labassistant.MatkulActivity.status;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomepageActivity extends AppCompatActivity {

    ImageView pinjam, kembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        pinjam = findViewById(R.id.pinjam_img);
        kembali = findViewById(R.id.kembali_img);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("user").child(usernm);


        pinjam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        matkul = snapshot.child("matkul").getValue(String.class);
                        jobsheet = snapshot.child("jobsheet").getValue(String.class);
                        status = snapshot.child("status").getValue(Integer.class);

                        Intent i = new Intent(HomepageActivity.this,
                                MatkulActivity.class);
                        startActivity(i);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomepageActivity.this,
                        MainActivity.class);
                startActivity(i);
            }
        });
    }
}