package com.nhom02.stumanager.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.nhom02.stumanager.R;
import com.nhom02.stumanager.adapter.EduProgAdapter;
import com.nhom02.stumanager.model.EducationProgram;
import com.nhom02.stumanager.model.Major;
import com.nhom02.stumanager.sqlite.EduProgDao;
import com.nhom02.stumanager.sqlite.MajorDao;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.util.List;

public class AddOrEditMajorActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etMajorId, etMajorName, etMajorPhone, etMajorLink;
    private TextView tvHeader;
    private Button btnSave;
    private Spinner spEduProg;
    private List<EducationProgram> eduList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_major);

        tvHeader = findViewById(R.id.tvHeader);
        etMajorId = findViewById(R.id.etMajorId);
        etMajorName = findViewById(R.id.etMajorName);
        etMajorPhone = findViewById(R.id.etMajorPhone);
        etMajorLink = findViewById(R.id.etMajorLink);
        spEduProg = findViewById(R.id.spEduProg);

        fillEduProgToSpinner();

        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);
        findViewById(R.id.btnExit).setOnClickListener(this);

        try {
            readMajor();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private void fillEduProgToSpinner(){
        EduProgDao dao = new EduProgDao(this);

        eduList = dao.getAll();

        EduProgAdapter eduAdapter = new EduProgAdapter(this, eduList);
        spEduProg.setAdapter(eduAdapter);

        spEduProg.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {}

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void readMajor() throws ParseException {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("data");
        if (bundle == null)
            return;
        String id = bundle.getString("id");

        MajorDao dao = new MajorDao(this);
        Major major = dao.getById(id);

        etMajorId.setText(major.getMajorId());
        etMajorName.setText(major.getMajorName());
        etMajorPhone.setText(major.getMajorPhone());
        etMajorLink.setText(major.getMajorLink());

        tvHeader.setText("Sửa chuyên ngành");
        etMajorId.setEnabled(false);
        btnSave.setText("Cập nhật");

        String eduProgId = major.getEduProgId();

        int position = -1;
        for (int i = 0; i < eduList.size(); i++) {
            if (eduList.get(i).getEduId().equals(eduProgId)) {
                position = i;
                break;
            }
        }

        if (position != -1) {
            spEduProg.setSelection(position);
        }
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnSave) {
            Major major = new Major();
            major.setMajorId(etMajorId.getText().toString());
            major.setMajorName(etMajorName.getText().toString());
            major.setMajorPhone(etMajorPhone.getText().toString());
            major.setMajorLink(etMajorLink.getText().toString());
            EducationProgram edu = (EducationProgram) spEduProg.getSelectedItem();
            major.setEduProgId(edu.getEduId());

            MajorDao dao = new MajorDao(this);

            if (btnSave.getText().equals("Lưu"))
                dao.insert(major);
            else
                dao.update(major);

            Toast.makeText(this, "Đã lưu chuyên ngành: [" + major.getMajorId() + "] " + major.getMajorName(), Toast.LENGTH_SHORT).show();
            finish();
        } else if (v.getId() == R.id.btnExit) {
            finish();
        }
    }
}