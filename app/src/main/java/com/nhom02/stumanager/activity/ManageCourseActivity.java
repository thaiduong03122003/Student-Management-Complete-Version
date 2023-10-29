package com.nhom02.stumanager.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.nhom02.stumanager.R;
import com.nhom02.stumanager.adapter.CourseAdapter;
import com.nhom02.stumanager.adapter.CourseCateAdapter;
import com.nhom02.stumanager.category.CourseCategory;
import com.nhom02.stumanager.model.Course;
import com.nhom02.stumanager.sqlite.CourseDao;
import com.nhom02.stumanager.sqlite.MajorDao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ManageCourseActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView rcvMajorCate;
    private CourseCateAdapter courseCateAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_course);

        rcvMajorCate = findViewById(R.id.rcvMajorCate);
        courseCateAdapter = new CourseCateAdapter(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcvMajorCate.setLayoutManager(linearLayoutManager);

        try {
            courseCateAdapter.setData(getListCategory());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        rcvMajorCate.setAdapter(courseCateAdapter);

        findViewById(R.id.fabAdd).setOnClickListener(this);
    }

    private List<CourseCategory> getListCategory() throws ParseException {

        CourseDao dao = new CourseDao(this);
        MajorDao majorDao = new MajorDao(this);

        List<String> majorNameList = majorDao.getAllMajorName();
        List<CourseCategory> categoryList = new ArrayList<>();
        List<Course> courseList;

        for (int i = 0; i < majorNameList.size(); i++) {
            courseList = dao.getCoursesByMajorName(majorNameList.get(i));
            categoryList.add(new CourseCategory(majorNameList.get(i), courseList));
        };

        return categoryList;
    }

    protected void onResume() {
        super.onResume();

        try {
            List<CourseCategory> updatedCategories = getListCategory();
            courseCateAdapter.setData(updatedCategories);
            courseCateAdapter.notifyDataSetChanged();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fabAdd) {
            Intent intent = new Intent(this, AddOrEditCourseActivity.class);
            startActivity(intent);
        }
    }
}