package com.nhom02.stumanager.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom02.stumanager.R;
import com.nhom02.stumanager.model.Course;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder>{

    private List<Course> courseList;

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

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);

            tvCourseName = itemView.findViewById(R.id.tvcourseName);
            tvSchYear = itemView.findViewById(R.id.tvSchYear);
        }
    }
}
