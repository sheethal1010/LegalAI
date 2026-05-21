package com.legalai.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.legalai.app.R;
import com.legalai.app.utils.AppUtils;
import com.legalai.app.utils.FirebaseHelper;

public class EditProfileActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextInputEditText etName, etPhone, etLocation;
    private MaterialButton btnSave, btnDeleteAccount;
    private ProgressBar progressBar;
    private FirebaseHelper firebaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        firebaseHelper = new FirebaseHelper();
        ivBack = findViewById(R.id.iv_back);
        etName = findViewById(R.id.et_name);
        etPhone = findViewById(R.id.et_phone);
        etLocation = findViewById(R.id.et_location);
        btnSave = findViewById(R.id.btn_save);
        btnDeleteAccount = findViewById(R.id.btn_delete_account);
        progressBar = findViewById(R.id.progress_bar);

        ivBack.setOnClickListener(v -> onBackPressed());
        loadProfile();
        btnSave.setOnClickListener(v -> saveProfile());
        btnDeleteAccount.setOnClickListener(v -> confirmDelete());
    }

    private void loadProfile() {
        firebaseHelper.getUserProfile(profile -> {
            if (profile != null) {
                if (etName != null) etName.setText(profile.getName());
                if (etPhone != null) etPhone.setText(profile.getPhone());
                if (etLocation != null) etLocation.setText(profile.getLocation());
            }
        });
    }

    private void saveProfile() {
        String name = etName.getText() != null ? etName.getText().toString().trim() : "";
        String phone = etPhone.getText() != null ? etPhone.getText().toString().trim() : "";
        String location = etLocation.getText() != null ? etLocation.getText().toString().trim() : "";
        if (name.isEmpty()) { etName.setError("Name required"); return; }

        progressBar.setVisibility(View.VISIBLE);
        btnSave.setEnabled(false);
        firebaseHelper.updateUserProfile(name, phone, location, success -> {
            progressBar.setVisibility(View.GONE);
            btnSave.setEnabled(true);
            if (success) {
                AppUtils.cacheUserName(this, name);
                Toast.makeText(this, "Profile updated!", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Update failed. Try again.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void confirmDelete() {
        new AlertDialog.Builder(this)
                .setTitle("Delete Account")
                .setMessage("This will permanently delete your account and all data. This cannot be undone.")
                .setPositiveButton("Delete", (d, w) -> deleteAccount())
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void deleteAccount() {
        progressBar.setVisibility(View.VISIBLE);
        firebaseHelper.deleteAccount((success, error) -> {
            progressBar.setVisibility(View.GONE);
            if (success) {
                AppUtils.clearCache(this);
                startActivity(new Intent(this, LoginActivity.class));
                finishAffinity();
            } else {
                Toast.makeText(this, "Failed: " + error, Toast.LENGTH_LONG).show();
            }
        });
    }
}
