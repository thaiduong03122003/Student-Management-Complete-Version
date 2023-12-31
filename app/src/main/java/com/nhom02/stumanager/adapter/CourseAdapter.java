package com.nhom02.stumanager.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom02.stumanager.R;
import com.nhom02.stumanager.activity.DetailCourseActivity;
import com.nhom02.stumanager.activity.DetailMajorActivity;
import com.nhom02.stumanager.model.Course;
import com.nhom02.stumanager.model.Major;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder>{

    private Context context;
    private List<Course> courseList;

    public CourseAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<Course> list) {
        this.courseList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_course_item, parent, false);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        Course course = courseList.get(position);
        if (course == null) {
            return;
        }

        holder.tvCourseName.setText(course.getCourseName());
        holder.tvSchYear.setText(course.getSchYear());

        holder.cvCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickGoToDetail(course);
            }
        });
    }

    private void onClickGoToDetail(Course course) {

        Intent intent = new Intent(context, DetailCourseActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_course", course);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        if (courseList != null) {
            return courseList.size();
        }
        return 0;
    }

    public class CourseViewHolder extends RecyclerView.ViewHolder {
        private TextView tvCourseName, tvSchYear;
        private CardView cvCourse;
        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);

            tvCourseName = itemView.findViewById(R.id.tvCourseName);
            tvSchYear = itemView.findViewById(R.id.tvSchYear);
            cvCourse = itemView.findViewById(R.id.cvCourse);
        }
    }
}
