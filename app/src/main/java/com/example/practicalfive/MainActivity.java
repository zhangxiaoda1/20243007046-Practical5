package com.example.practicalfive;

import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Single source of truth for the Intent extra key; Municipality
    // references this same constant.
    public static final String EXTRA_MUNICIPALITY =
            "com.example.practicalfive.EXTRA_MUNICIPALITY";

    private EditText etMunicipality;
    private final TextProcessor textProcessor = new TextProcessor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etMunicipality = findViewById(R.id.et_municipality);

        // The keyboard's Enter/Go key triggers validation.
        etMunicipality.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_GO) {
                openMunicipality();
                return true;
            }
            return false;
        });
    }

    private void openMunicipality() {
        String raw = etMunicipality.getText().toString();

        // Distinguish "nothing entered" from "unsupported name" only for
        // the error message; the four-name matching itself lives solely
        // in TextProcessor.
        if (raw.trim().isEmpty()) {
            etMunicipality.setError(getString(R.string.error_empty));
            return;
        }

        String name = textProcessor.processMunicipality(raw);
        if (name == null) {
            etMunicipality.setError(getString(R.string.error_invalid));
            return;
        }

        Intent intent = new Intent(this, Municipality.class);
        intent.putExtra(EXTRA_MUNICIPALITY, name);
        startActivity(intent);
    }
}
