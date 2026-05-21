package com.legalai.app.activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.legalai.app.R;
import com.legalai.app.utils.FirebaseHelper;

public class ForgotPasswordActivity extends AppCompatActivity {

    private TextInputEditText etEmail;
    private MaterialButton btnReset;
    private ProgressBar progressBar;
    private ImageView ivBack;
    private FirebaseHelper firebaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        firebaseHelper = new FirebaseHelper();
        etEmail = findViewById(R.id.et_email);
        btnReset = findViewById(R.id.btn_reset);
        progressBar = findViewById(R.id.progress_bar);
        ivBack = findViewById(R.id.iv_back);

        ivBack.setOnClickListener(v -> onBackPressed());
        btnReset.setOnClickListener(v -> attemptReset());
    }

    private void attemptReset() {
        String email = etEmail.getText() != null ? etEmail.getText().toString().trim() : "";
        if (TextUtils.isEmpty(email)) { etEmail.setError("Email required"); return; }

        progressBar.setVisibility(View.VISIBLE);
        btnReset.setEnabled(false);

        firebaseHelper.sendPasswordReset(email, (success, error) -> {
            progressBar.setVisibility(View.GONE);
            btnReset.setEnabled(true);
            if (success) {
                Toast.makeText(this, "Reset link sent to " + email, Toast.LENGTH_LONG).show();
                finish();
            } else {
                Toast.makeText(this, "Error: " + error, Toast.LENGTH_LONG).show();
            }
        });
    }
}
