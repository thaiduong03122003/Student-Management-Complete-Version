package com.nhom02.stumanager.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.nhom02.stumanager.R;
import com.nhom02.stumanager.adapter.EduProgAdapter;
import com.nhom02.stumanager.adapter.MajorAdapter;
import com.nhom02.stumanager.model.Course;
import com.nhom02.stumanager.model.EducationProgram;
import com.nhom02.stumanager.model.Major;
import com.nhom02.stumanager.sqlite.CourseDao;
import com.nhom02.stumanager.sqlite.EduProgDao;
import com.nhom02.stumanager.sqlite.MajorDao;

import java.util.List;

public class AddCourseActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etCourseId, etCourseName, etSchYear, etMajorId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        etCourseId = findViewById(R.id.etCourseId);
        etCourseName = findViewById(R.id.etCourseName);
        etSchYear = findViewById(R.id.etSchYear);
        etMajorId = findViewById(R.id.etMajorId);

        findViewById(R.id.btnSave).setOnClickListener(this);
        findViewById(R.id.btnClose).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnSave) {
            Course course = new Course();
            course.setCourseId(etCourseId.getText().toString());
            course.setCourseName(etCourseName.getText().toString());
            course.setSchYear(etSchYear.getText().toString());
            course.setMajorId(etMajorId.getText().toString());

            CourseDao dao = new CourseDao(this);
            dao.insert(course);
            Toast.makeText(this, "Thêm thành công!", Toast.LENGTH_SHORT).show();
            finish();
        }
        else if (v.getId() == R.id.btnClose) {
            finish();
        }
    }
}