package com.example.labassistant;

import static com.example.labassistant.MatkulActivity.jobsheet;
import static com.example.labassistant.MatkulActivity.matkul;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BarangActivity extends AppCompatActivity {

    Button btnNext;
    private RecyclerView recyclerView;
    toolsAdapter adapter; // Create Object of the Adapter class
    DatabaseReference mbase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barang);

        btnNext = findViewById(R.id.barang_btn);
        recyclerView = findViewById(R.id.toolList_rv);

        mbase = FirebaseDatabase.getInstance().getReference("pointer").child(matkul).child(jobsheet).child("tools");

        recyclerView.setLayoutManager(
                new LinearLayoutManager(this));

        // It is a class provide by the FirebaseUI to make a
        // query in the database to fetch appropriate data
        FirebaseRecyclerOptions<Tools> options
                = new FirebaseRecyclerOptions.Builder<Tools>()
                .setQuery(mbase, Tools.class)
                .build();
        // Connecting object of required Adapter class to
        // the Adapter class itself
        adapter = new toolsAdapter(options);
        // Connecting Adapter class with the Recycler view*/
        recyclerView.setAdapter(adapter);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(BarangActivity.this, QRActivity.class);
                startActivity(i);
            }
        });

    }

    // Function to tell the app to start getting
    // data from database on starting of the activity
    @Override protected void onStart()
    {
        super.onStart();
        adapter.startListening();
    }

    // Function to tell the app to stop getting
    // data from database on stopping of the activity
    @Override protected void onStop()
    {
        super.onStop();
        adapter.stopListening();
    }

}