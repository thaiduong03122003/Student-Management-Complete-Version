package com.nhom02.stumanager.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.nhom02.stumanager.R;
import com.nhom02.stumanager.model.Major;

public class DetailMajorActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvMajorId, tvMajorName, tvMajorPhone, tvMajorLink, tvEduProgName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_major);

        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }

        Major major = (Major) bundle.get("object_major");

        tvMajorId = findViewById(R.id.tvMajorId);
        tvMajorName = findViewById(R.id.tvMajorName);
        tvMajorPhone = findViewById(R.id.tvMajorPhone);
        tvMajorLink = findViewById(R.id.tvMajorLink);
        tvEduProgName = findViewById(R.id.tvEduProgName);

        tvMajorId.setText(major.getMajorId());
        tvMajorName.setText(major.getMajorName());
        if (major.getMajorPhone() != null)
            tvMajorPhone.setText(major.getMajorPhone());
        if (major.getMajorLink() != null)
            tvMajorLink.setText(major.getMajorLink());
        tvEduProgName.setText(major.getEduProgId());

        findViewById(R.id.btnEdit).setOnClickListener(this);
        findViewById(R.id.btnDelete).setOnClickListener(this);
        findViewById(R.id.btnClose).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnEdit) {
            finish();
        }
        else if (v.getId() == R.id.btnDelete) {
            finish();
        }
        else if (v.getId() == R.id.btnClose) {
            finish();
        }
    }
}