package com.example.labassistant;

import static com.example.labassistant.MatkulActivity.jobsheet;
import static com.example.labassistant.MatkulActivity.matkul;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class PeminjamanActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    Button btn;
    TextView matakuliah, job, peminjaman, pengembalian;
    pinjamToolsAdapter adapter; // Create Object of the Adapter class
    DatabaseReference mbase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peminjaman);

        recyclerView = findViewById(R.id.toolList_rv);
        matakuliah = findViewById(R.id.matkul_tv);
        job = findViewById(R.id.jobsheet_tv);
        peminjaman = findViewById(R.id.peminjaman_tv);
        pengembalian = findViewById(R.id.pengembalian_tv);

        matakuliah.setText(matkul);
        job.setText(jobsheet);

        Date c = Calendar.getInstance().getTime();
        //System.out.println("Current time => " + c);
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault());
        String pinjam = df.format(c);
        peminjaman.setText(pinjam);

        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 6);// for 6 hour
        calendar.set(Calendar.MINUTE, 0);// for 0 min
        calendar.set(Calendar.SECOND, 0);// for 0 sec
        System.out.println(calendar.getTime());// print 'Mon Mar 28 06:00:00 ALMT
        pengembalian.setText(calendar.getTime().toString());


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
        adapter = new pinjamToolsAdapter(options);
        // Connecting Adapter class with the Recycler view*/
        recyclerView.setAdapter(adapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PeminjamanActivity.this, HomepageActivity.class);
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