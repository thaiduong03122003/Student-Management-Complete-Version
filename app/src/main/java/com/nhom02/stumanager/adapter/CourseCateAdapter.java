package com.nhom02.stumanager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom02.stumanager.R;
import com.nhom02.stumanager.category.CourseCategory;

import java.util.List;

public class CourseCateAdapter extends RecyclerView.Adapter<CourseCateAdapter.CCViewHolder>{

    private Context context;
    private List<CourseCategory> cCList;

    public CourseCateAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<CourseCategory> list) {
        this.cCList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CCViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_category_major_item, parent, false);
        return new CCViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CCViewHolder holder, int position) {
        CourseCategory cCategory = cCList.get(position);
        if (cCategory == null) {
            return;
        }

        holder.tvMajorCateName.setText(cCategory.getCourseCateName());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);
        holder.rcvCourse.setLayoutManager(linearLayoutManager);

        CourseAdapter courseAdapter = new CourseAdapter(context);
        courseAdapter.setData(cCategory.getCourseList());
        holder.rcvCourse.setAdapter(courseAdapter);

    }

    @Override
    public int getItemCount() {
        if (cCList != null) {
            return cCList.size();
        }
        return 0;
    }

    public class CCViewHolder extends RecyclerView.ViewHolder {

        private TextView tvMajorCateName;
        private RecyclerView rcvCourse;

        public CCViewHolder(@NonNull View itemView) {
            super(itemView);

            tvMajorCateName = itemView.findViewById(R.id.tvMajorCateName);
            rcvCourse = itemView.findViewById(R.id.rcvCourse);
        }
    }
}
