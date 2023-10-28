package com.nhom02.stumanager.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom02.stumanager.R;
import com.nhom02.stumanager.activity.DetailMajorActivity;
import com.nhom02.stumanager.model.Major;

import java.util.List;

public class MajorAdapter extends RecyclerView.Adapter<MajorAdapter.MajorViewHolder>{

    private Context context;
    private List<Major> majorList;

    public MajorAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<Major> list) {
        this.majorList = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MajorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_major_item, parent, false);
        return new MajorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MajorViewHolder holder, int position) {
        final Major major = majorList.get(position);
        if (major == null) {
            return;
        }

        holder.tvMajorId.setText(major.getMajorId());
        holder.tvMajorName.setText(major.getMajorName());
        holder.cvMajorItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickGoToDetail(major);
            }
        });
    }
    private void onClickGoToDetail(Major major) {
        Intent intent = new Intent(context, DetailMajorActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_major", major);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    public void release() {
        context = null;
    }
    @Override
    public int getItemCount() {
        if (majorList != null) {
            return majorList.size();
        }
        return 0;
    }

    public class MajorViewHolder extends RecyclerView.ViewHolder {
        private TextView tvMajorId, tvMajorName;
        private CardView cvMajorItem;

        public MajorViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMajorId = itemView.findViewById(R.id.tvMajorId);
            tvMajorName = itemView.findViewById(R.id.tvMajorName);
            cvMajorItem = itemView.findViewById(R.id.cvMajorItem);
        }
    }
}
