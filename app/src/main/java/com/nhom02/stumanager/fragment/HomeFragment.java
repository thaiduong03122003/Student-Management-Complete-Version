package com.nhom02.stumanager.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.nhom02.stumanager.MainActivity;
import com.nhom02.stumanager.R;
import com.nhom02.stumanager.activity.ManageMajorActivity;


public class HomeFragment extends Fragment implements View.OnClickListener {
    MainActivity mainActivity;


    public HomeFragment() {
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        view.findViewById(R.id.btnMajor).setOnClickListener(this);
        view.findViewById(R.id.btnCourse).setOnClickListener(this);
        view.findViewById(R.id.btnClass).setOnClickListener(this);
        view.findViewById(R.id.btnSubject).setOnClickListener(this);
        view.findViewById(R.id.btnStudent).setOnClickListener(this);
        view.findViewById(R.id.btnScore).setOnClickListener(this);


        mainActivity = (MainActivity)getActivity();

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnMajor) {
            Intent intentMajor = new Intent(getActivity(), ManageMajorActivity.class);
            startActivity(intentMajor);
        }
        else if (v.getId() == R.id.btnCourse) {
            //Intent intentCourse = new Intent(getActivity(), ManageMajorActivity.class);
            //startActivity(intentCourse);
        }
        else if (v.getId() == R.id.btnClass) {
            //Intent intentClass = new Intent(getActivity(), ManageMajorActivity.class);
            //startActivity(intentClass);
        }
        else if (v.getId() == R.id.btnSubject) {
            //Intent intentSubject = new Intent(getActivity(), ManageMajorActivity.class);
            //startActivity(intentSubject);
        }
        else if (v.getId() == R.id.btnStudent) {
            //Intent intentStudent = new Intent(getActivity(), ManageMajorActivity.class);
            //startActivity(intentStudent);
        }
        else if (v.getId() == R.id.btnScore) {
            //Intent intentScore = new Intent(getActivity(), ManageMajorActivity.class);
            //startActivity(intentScore);
        }
    }
}