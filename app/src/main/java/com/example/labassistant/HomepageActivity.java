package com.example.labassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class HomepageActivity extends AppCompatActivity {

    ImageView pinjam, kembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        pinjam = findViewById(R.id.pinjam_img);
        kembali = findViewById(R.id.kembali_img);

        pinjam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomepageActivity.this,
                        MatkulActivity.class);
                startActivity(i);
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