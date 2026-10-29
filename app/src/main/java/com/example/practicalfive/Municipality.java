package com.example.practicalfive;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Municipality extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_municipality);

        TextView tvName = findViewById(R.id.tv_name);
        ImageView ivImage = findViewById(R.id.iv_municipality);
        TextView tvInfo = findViewById(R.id.tv_info);
        Button btnVisit = findViewById(R.id.btn_visit);

        // Re-validate the data passed from MainActivity; a missing or
        // invalid extra must not leave this screen in a broken state.
        String raw = getIntent().getStringExtra(MainActivity.EXTRA_MUNICIPALITY);
        String name = new TextProcessor().processMunicipality(raw);
        if (name == null) {
            Toast.makeText(this, R.string.error_invalid, Toast.LENGTH_LONG).show();
            finish();
        }
    }
}
