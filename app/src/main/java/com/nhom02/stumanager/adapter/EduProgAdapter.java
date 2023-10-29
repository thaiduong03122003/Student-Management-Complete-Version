package com.nhom02.stumanager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nhom02.stumanager.R;
import com.nhom02.stumanager.model.EducationProgram;

import java.util.List;

public class EduProgAdapter extends BaseAdapter {

    private Context context;
    List<EducationProgram> list;

    public EduProgAdapter(Context context, List<EducationProgram> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.layout_eduprog_item, null);
        }

        TextView tvEduName = view.findViewById(R.id.tvEduName);

        EducationProgram edu = list.get(i);
        tvEduName.setText("" + edu.getEduName());

        return view;
    }
}
