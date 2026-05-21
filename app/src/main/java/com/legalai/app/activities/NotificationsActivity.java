package com.legalai.app.activities;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.legalai.app.R;
import com.legalai.app.adapters.NotificationAdapter;
import com.legalai.app.utils.FirebaseHelper;

public class NotificationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        ImageView ivBack = findViewById(R.id.iv_back);
        RecyclerView rv = findViewById(R.id.rv_notifications);
        ivBack.setOnClickListener(v -> onBackPressed());
        rv.setLayoutManager(new LinearLayoutManager(this));

        new FirebaseHelper().getNotifications(items -> {
            rv.setAdapter(new NotificationAdapter(this, items));
        });
    }
}
