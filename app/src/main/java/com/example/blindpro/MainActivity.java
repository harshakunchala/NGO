package com.example.blindpro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button ngo;
    Button vol;
    Button home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ngo   = findViewById(R.id.ngo);
        vol   = findViewById(R.id.vol);
        home   = findViewById(R.id.home);
    }



    public void ngo(View view) {
        startActivity(new Intent(getApplicationContext(),ngo_register.class));
    };

    public void vol(View view) {
        startActivity(new Intent(getApplicationContext(),volregister.class));
    }

    public void home(View view) {
        startActivity(new Intent(getApplicationContext(),pdf.class));
    }
}
