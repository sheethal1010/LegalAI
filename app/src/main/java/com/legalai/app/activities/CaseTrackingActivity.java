package com.legalai.app.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.legalai.app.R;
import com.legalai.app.models.LegalCase;

public class CaseTrackingActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvCaseTitle, tvCaseType, tvCaseDescription, tvAgentUsed, tvAssignedLawyer;
    private View stepSubmitted, stepAi, stepReview, stepApproved, stepDelivered;
    private View stepLine1, stepLine2, stepLine3, stepLine4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_tracking);
        bindViews();
        loadCase();
    }

    private void bindViews() {
        ivBack = findViewById(R.id.iv_back);
        tvCaseTitle = findViewById(R.id.tv_case_title);
        tvCaseType = findViewById(R.id.tv_case_type);
        tvCaseDescription = findViewById(R.id.tv_case_description);
        tvAgentUsed = findViewById(R.id.tv_agent_used);
        tvAssignedLawyer = findViewById(R.id.tv_assigned_lawyer);
        stepSubmitted = findViewById(R.id.step_submitted);
        stepAi = findViewById(R.id.step_ai);
        stepReview = findViewById(R.id.step_review);
        stepApproved = findViewById(R.id.step_approved);
        stepDelivered = findViewById(R.id.step_delivered);
        stepLine1 = findViewById(R.id.step_line_1);
        stepLine2 = findViewById(R.id.step_line_2);
        stepLine3 = findViewById(R.id.step_line_3);
        stepLine4 = findViewById(R.id.step_line_4);
        ivBack.setOnClickListener(v -> onBackPressed());
    }

    private void loadCase() {
        Intent i = getIntent();
        String title = i.getStringExtra("case_title");
        String type = i.getStringExtra("case_type");
        String description = i.getStringExtra("case_description");
        String agent = i.getStringExtra("case_agent");
        String lawyer = i.getStringExtra("case_lawyer");
        int status = i.getIntExtra("case_status", 0);

        tvCaseTitle.setText(title != null ? title : "Case");
        tvCaseType.setText(type != null ? type : "");
        tvCaseDescription.setText(description != null ? description : "");
        tvAgentUsed.setText(agent != null ? agent : "General AI");
        tvAssignedLawyer.setText(lawyer != null ? lawyer : "Not yet assigned");

        applyTimeline(status);
    }

    private void applyTimeline(int status) {
        int gold = ContextCompat.getColor(this, R.color.counsel_gold);
        int muted = Color.parseColor("#CCCCCC");
        View[] steps = {stepSubmitted, stepAi, stepReview, stepApproved, stepDelivered};
        View[] lines = {stepLine1, stepLine2, stepLine3, stepLine4};

        for (int i = 0; i < steps.length; i++) {
            if (i <= status) {
                steps[i].setBackgroundResource(R.drawable.bg_timeline_circle_done);
            } else {
                steps[i].setBackgroundResource(R.drawable.bg_timeline_circle_pending);
            }
        }
        for (int i = 0; i < lines.length; i++) {
            lines[i].setBackgroundColor(i < status ? gold : muted);
        }
    }
}
