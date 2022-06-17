package com.example.labassistant;

import static com.example.labassistant.LoginActivity.usernm;
import static com.example.labassistant.MatkulActivity.jobsheet;
import static com.example.labassistant.MatkulActivity.matkul;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class JobsheetActivity extends AppCompatActivity {

    Button btnNext;
    RadioGroup radioGroup;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobsheet);

        btnNext = findViewById(R.id.Jobsheet_btn);
        radioGroup = findViewById(R.id.RadioJobsheet);
        RadioButton job1 = (RadioButton)findViewById(R.id.rad1);
        RadioButton job2 = (RadioButton)findViewById(R.id.rad2);
        RadioButton job3 = (RadioButton)findViewById(R.id.rad3);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref1 = database.getReference("/master/"+matkul+"/1/jobsheet");
        DatabaseReference ref2 = database.getReference("/master/"+matkul+"/2/jobsheet");
        DatabaseReference ref3 = database.getReference("/master/"+matkul+"/3/jobsheet");

        radioGroup.clearCheck();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                RadioButton radioButton = findViewById(id);
                switch (id){
                    case R.id.rad1:
                        jobsheet = radioButton.getText().toString();
                        Toast.makeText(getApplication(), jobsheet, Toast.LENGTH_SHORT).show();
                        
                        break;
                    case R.id.rad2:
                        jobsheet = radioButton.getText().toString();
                        Toast.makeText(getApplication(), jobsheet, Toast.LENGTH_SHORT).show();
                        
                        break;
                    case R.id.rad3:
                        jobsheet = radioButton.getText().toString();
                        Toast.makeText(getApplication(), jobsheet, Toast.LENGTH_SHORT).show();
                        
                        break;
                }
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("user").child(usernm).child("jobsheet");
                myRef.setValue(jobsheet);

                Intent i = new Intent(JobsheetActivity.this, BarangActivity.class);
                startActivity(i);
            }
        });

        // Read from the database
        ref1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String val1 = dataSnapshot.getValue(String.class);
                job1.setText(val1);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        // Read from the database
        ref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String val2 = dataSnapshot.getValue(String.class);
                job2.setText(val2);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        // Read from the database
        ref3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String val3 = dataSnapshot.getValue(String.class);
                if (val3 == null) {
                    job3.setVisibility(View.GONE);
                } else {
                    job3.setText(val3);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
    }
}