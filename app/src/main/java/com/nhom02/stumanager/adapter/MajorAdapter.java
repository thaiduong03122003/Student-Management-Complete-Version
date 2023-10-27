package com.nhom02.stumanager.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom02.stumanager.R;
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
        Major major = majorList.get(position);
        if (major == null) {
            return;
        }

        holder.tvMajorId.setText(major.getMajorId());
        holder.tvMajorName.setText(major.getMajorName());
        holder.tvMajorLink.setText(major.getMajorLink());

        holder.tvMajorLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = major.getMajorLink();
                if (url != null && !url.isEmpty()) {
                    Intent intentLink = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    context.startActivity(intentLink);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (majorList != null) {
            return majorList.size();
        }
        return 0;
    }

    public class MajorViewHolder extends RecyclerView.ViewHolder {
        private TextView tvMajorId, tvMajorName, tvMajorLink;

        public MajorViewHolder(@NonNull View itemView) {
            super(itemView);

            tvMajorId = itemView.findViewById(R.id.tvMajorId);
            tvMajorName = itemView.findViewById(R.id.tvMajorName);
            tvMajorLink = itemView.findViewById(R.id.tvMajorWebsite);
        }
    }
}