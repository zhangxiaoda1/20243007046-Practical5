package com.example.practicalfive;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class Municipality extends AppCompatActivity {

    private String websiteUrl;

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
            return;
        }

        int imageRes;
        int colorRes;
        int summaryIndex;
        int urlRes;

        switch (name) {
            case "Beijing":
                imageRes = R.drawable.beijing;
                colorRes = R.color.municipality_beijing;
                summaryIndex = 0;
                urlRes = R.string.url_beijing;
                break;
            case "Shanghai":
                imageRes = R.drawable.shanghai;
                colorRes = R.color.municipality_shanghai;
                summaryIndex = 1;
                urlRes = R.string.url_shanghai;
                break;
            case "Chongqing":
                imageRes = R.drawable.chongqing;
                colorRes = R.color.municipality_chongqing;
                summaryIndex = 2;
                urlRes = R.string.url_chongqing;
                break;
            case "Tianjin":
            default:
                imageRes = R.drawable.tianjin;
                colorRes = R.color.municipality_tianjin;
                summaryIndex = 3;
                urlRes = R.string.url_tianjin;
                break;
        }

        int themeColor = ContextCompat.getColor(this, colorRes);
        int lightText = ContextCompat.getColor(this, R.color.color_text_light);

        // Name bar: municipality-coloured background with light text.
        tvName.setText(name);
        tvName.setBackgroundColor(themeColor);
        tvName.setTextColor(lightText);

        // Municipality image.
        ivImage.setImageResource(imageRes);

        // Information text uses the municipality colour on the light background.
        String[] summaries = getResources().getStringArray(R.array.municipalities_array);
        tvInfo.setText(summaries[summaryIndex]);
        tvInfo.setTextColor(themeColor);

        // Visit site button: municipality-coloured background with light text.
        btnVisit.setBackgroundTintList(ColorStateList.valueOf(themeColor));
        btnVisit.setTextColor(lightText);
        websiteUrl = getString(urlRes);

        // Implicit Intent: ask Android to open the official website in a
        // suitable application (normally a browser). The URL depends on
        // the municipality currently displayed.
        btnVisit.setOnClickListener(v -> {
            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(websiteUrl));
            try {
                startActivity(webIntent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(this, R.string.no_browser, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
