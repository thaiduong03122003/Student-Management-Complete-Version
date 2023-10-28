package com.nhom02.stumanager.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.nhom02.stumanager.R;
import com.nhom02.stumanager.model.Major;
import com.nhom02.stumanager.sqlite.MajorDao;

public class AddMajorActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etMajorId, etMajorName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_major);

        etMajorId = findViewById(R.id.etMajorId);
        etMajorName = findViewById(R.id.etMajorName);

        findViewById(R.id.btnSave).setOnClickListener(this);
        findViewById(R.id.btnExit).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnSave) {
            Major major = new Major();
            major.setMajorId(etMajorId.getText().toString());
            major.setMajorName(etMajorName.getText().toString());
            MajorDao dao = new MajorDao(this);
            dao.insert(major);
            Toast.makeText(this, "Đã lưu chuyên ngành: [" + major.getMajorId() + "] " + major.getMajorName(), Toast.LENGTH_SHORT).show();
            finish();
        } else if (v.getId() == R.id.btnExit) {
            finish();
        }
    }
}