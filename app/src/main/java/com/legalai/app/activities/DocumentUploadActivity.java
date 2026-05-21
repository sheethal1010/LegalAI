package com.legalai.app.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.legalai.app.R;

public class DocumentUploadActivity extends AppCompatActivity {

    private ActivityResultLauncher<String[]> filePickerLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_upload);

        ImageView ivBack = findViewById(R.id.iv_back);
        MaterialButton btnUpload = findViewById(R.id.btn_upload_document);
        MaterialButton btnCamera = findViewById(R.id.btn_camera_scan);

        ivBack.setOnClickListener(v -> onBackPressed());

        filePickerLauncher = registerForActivityResult(
                new ActivityResultContracts.OpenDocument(), uri -> {
                    if (uri != null) handleFileSelected(uri);
                });

        btnUpload.setOnClickListener(v ->
                filePickerLauncher.launch(new String[]{"application/pdf", "image/*", "application/msword"}));

        btnCamera.setOnClickListener(v ->
                Toast.makeText(this, "Camera scan — Coming Soon", Toast.LENGTH_SHORT).show());
    }

    private void handleFileSelected(Uri uri) {
        String name = uri.getLastPathSegment();
        Toast.makeText(this, "Selected: " + name + "\nOCR analysis coming soon.", Toast.LENGTH_LONG).show();
        // TODO: Upload to Firebase Storage + trigger analysis
    }
}
