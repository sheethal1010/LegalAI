package com.legalai.app.activities;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.legalai.app.R;
import com.legalai.app.adapters.GlossaryAdapter;
import com.legalai.app.utils.MockDataGenerator;

public class LegalGlossaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legal_glossary);

        ImageView ivBack = findViewById(R.id.iv_back);
        RecyclerView rv = findViewById(R.id.rv_glossary);
        EditText etSearch = findViewById(R.id.et_search);

        ivBack.setOnClickListener(v -> onBackPressed());

        GlossaryAdapter adapter = new GlossaryAdapter(this, MockDataGenerator.getGlossaryTerms());
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);

        etSearch.addTextChangedListener(new android.text.TextWatcher() {
            public void beforeTextChanged(CharSequence s, int st, int c, int a) {}
            public void onTextChanged(CharSequence s, int st, int b, int c) { adapter.filter(s.toString()); }
            public void afterTextChanged(android.text.Editable s) {}
        });
    }
}
