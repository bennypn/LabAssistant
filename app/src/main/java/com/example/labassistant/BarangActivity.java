package com.example.labassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BarangActivity extends AppCompatActivity {

    CheckBox multimeter, osiloskop, frequency, solder, bor;
    Button btnNext;
    protected static String tools;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("message");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barang);

        multimeter = findViewById(R.id.multimeter_cb);
        osiloskop = findViewById(R.id.osiloskop_cb);
        frequency = findViewById(R.id.frequency_cb);
        solder = findViewById(R.id.solder_cb);
        bor = findViewById(R.id.bor_cb);
        btnNext = findViewById(R.id.barang_btn);

        //StringBuilder result = new StringBuilder();
        String result = "";

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(multimeter.isChecked())
                {
                    tools += "\nMultimeter";
                    //result.append("\nMultimeter");
                }
                if(osiloskop.isChecked())
                {
                    tools += "\nOsiloskop";
                    //result.append("\nOsiloskop");
                }
                if(frequency.isChecked())
                {
                    tools += "";
                    //result.append("\nFrequency Counter");
                }
                if(solder.isChecked())
                {
                    tools += "";
                    //result.append("\nSolder");
                }
                if(bor.isChecked())
                {
                    tools += "";
                    //result.append("\nBor Listrik");
                }
                tools += "";

                myRef.setValue("Hello, World!");
                Toast.makeText(getApplicationContext(), tools.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}