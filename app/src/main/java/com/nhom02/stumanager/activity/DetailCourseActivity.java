package com.nhom02.stumanager.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.nhom02.stumanager.R;
import com.nhom02.stumanager.model.Course;
import com.nhom02.stumanager.sqlite.CourseDao;

import java.text.ParseException;

public class DetailCourseActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvCourseId, tvCourseName, tvSchYear, tvMajorId;
    private String courseId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_course);

        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }

        Course course = (Course) bundle.get("object_course");

        tvCourseId = findViewById(R.id.tvCourseId);
        tvCourseName = findViewById(R.id.tvCourseName);
        tvSchYear = findViewById(R.id.tvSchYear);
        tvMajorId = findViewById(R.id.tvMajorId);

        courseId = course.getCourseId();
        tvCourseId.setText(courseId);
        tvCourseName.setText(course.getCourseName());
        tvSchYear.setText(course.getSchYear());
        tvMajorId.setText(course.getMajorId());

        findViewById(R.id.btnEdit).setOnClickListener(this);
        findViewById(R.id.btnDelete).setOnClickListener(this);
        findViewById(R.id.btnClose).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnEdit) {
            Intent intentEdit = new Intent(this, AddOrEditCourseActivity.class);

            Bundle bundle = new Bundle();
            bundle.putString("id", courseId);
            intentEdit.putExtra("data", bundle);

            startActivity(intentEdit);
            finish();
        }
        else if (v.getId() == R.id.btnDelete) {
            CourseDao dao = new CourseDao(this);
            Course course = new Course();
            try {
                course = dao.getById(courseId);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            AlertDialog.Builder builder = new AlertDialog.Builder(DetailCourseActivity.this);
            builder.setTitle("Cảnh báo!");
            String message = "Khi bạn xóa, các lớp thuộc khóa học này sẽ mất Mã khóa học <br/><br/>" + "Bạn vẫn muốn xóa khóa có mã: " + course.getCourseId() + " chứ?";

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                builder.setMessage(Html.fromHtml(message, Html.FROM_HTML_MODE_COMPACT));
            } else {
                builder.setMessage(Html.fromHtml(message));
            }

            Course finalCourse = course;
            Course finalCourse1 = course;
            builder.setPositiveButton("Xóa", (dialog, which) -> {
                Toast.makeText(this, "Đã xóa khóa " + finalCourse.getCourseId() + " thành công!", Toast.LENGTH_SHORT).show();
                dao.delete("" + finalCourse1.getCourseId());
                finish();
            });
            builder.setNegativeButton("Hủy", (dialog, which) -> dialog.dismiss());

            builder.show();
        }
        else if (v.getId() == R.id.btnClose) {
            finish();
        }
    }
}