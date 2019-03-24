package com.engtrcard.vdtgk.eng_trkartlar.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.engtrcard.vdtgk.eng_trkartlar.R;

public class DicActivity extends AppCompatActivity {
    AutoCompleteTextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dic);
        tv = findViewById(R.id.tvauto);
        String[] asd = {
                "Türkiye", "Almanya", "Fransa", "Ukrayna", "İtalya"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_dropdown_item_1line,asd);
        tv.setAdapter(adapter);

    }
}
