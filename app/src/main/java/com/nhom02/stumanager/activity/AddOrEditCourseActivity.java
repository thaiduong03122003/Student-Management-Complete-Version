package com.nhom02.stumanager.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.nhom02.stumanager.R;
import com.nhom02.stumanager.model.Course;
import com.nhom02.stumanager.model.Major;
import com.nhom02.stumanager.sqlite.CourseDao;
import com.nhom02.stumanager.sqlite.MajorDao;

import java.text.ParseException;
import java.util.List;

public class AddOrEditCourseActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etCourseId, etCourseName, etSchYear;
    private TextView tvHeader;
    private Spinner spMajor;
    private Button btnSave;
    private List<String> majorlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        etCourseId = findViewById(R.id.etCourseId);
        etCourseName = findViewById(R.id.etCourseName);
        etSchYear = findViewById(R.id.etSchYear);
        spMajor = findViewById(R.id.spMajor);
        tvHeader = findViewById(R.id.tvHeader);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(this);
        findViewById(R.id.btnClose).setOnClickListener(this);

        fillMajorIdToSpinner();

        try {
            readCourse();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private void fillMajorIdToSpinner(){
        MajorDao dao = new MajorDao(this);
        majorlist = dao.getAllMajorId();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, majorlist);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spMajor.setAdapter(adapter);

        spMajor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {}

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void readCourse() throws ParseException {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("data");
        if (bundle == null)
            return;
        String id = bundle.getString("id");

        CourseDao dao = new CourseDao(this);
        Course course = dao.getById(id);

        etCourseId.setText(course.getCourseId());
        etCourseName.setText(course.getCourseName());
        etSchYear.setText(course.getSchYear());

        tvHeader.setText("Sửa khóa học");
        etCourseId.setEnabled(false);
        btnSave.setText("Cập nhật");

        String majorId = course.getMajorId();

        int position = -1;
        for (int i = 0; i < majorlist.size(); i++) {
            if (majorlist.get(i).equals(majorId)) {
                position = i;
                break;
            }
        }

        if (position != -1) {
            spMajor.setSelection(position);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnSave) {
            Course course = new Course();
            course.setCourseId(etCourseId.getText().toString());
            course.setCourseName(etCourseName.getText().toString());
            course.setSchYear(etSchYear.getText().toString());
            course.setMajorId((String) spMajor.getSelectedItem());

            CourseDao dao = new CourseDao(this);
            if (btnSave.getText().equals("Lưu"))
                dao.insert(course);
            else
                dao.update(course);

            Toast.makeText(this, "Đã lưu khóa học: [" + course.getCourseId() + "] " + course.getCourseName(), Toast.LENGTH_SHORT).show();
            finish();
        }
        else if (v.getId() == R.id.btnClose) {
            finish();
        }
    }
}