package com.example.labassistant;

import static com.example.labassistant.LoginActivity.usernm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomepageActivity extends AppCompatActivity {
    protected static String semester,smt,matkul,matkulId, jobsheet,jobsheetId;
    private ImageView pinjam, kembali;
    private Integer stats;
    protected static Integer status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        pinjam = findViewById(R.id.pinjam_img);
        kembali = findViewById(R.id.kembali_img);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("user").child(usernm);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                smt = snapshot.child("semester").getValue(String.class);
                matkul = snapshot.child("matkul").getValue(String.class);
                jobsheet = snapshot.child("jobsheet").getValue(String.class);
                status = snapshot.child("status").getValue(Integer.class);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        pinjam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(status==1){
                    Intent i = new Intent(HomepageActivity.this,
                            PeminjamanActivity.class);
                    startActivity(i);
                }else {
                    Intent i = new Intent(HomepageActivity.this,
                            SemesterActivity.class);
                    startActivity(i);
                }
            }
        });

        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference myRef2 = database.getReference("user").child(usernm).child("status");
                myRef2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        stats = snapshot.getValue(Integer.class);
                        if (stats == 0) {
                            Toast.makeText(
                                    getApplicationContext(),
                                    "Tidak Ada Data Pemiinjaman",
                                    Toast.LENGTH_LONG)
                                    .show();
                        } else {
                            Intent i = new Intent(HomepageActivity.this,
                                    QRReturnActivity.class);
                            startActivity(i);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
    }
}