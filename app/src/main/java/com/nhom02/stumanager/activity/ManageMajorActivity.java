package com.nhom02.stumanager.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.nhom02.stumanager.R;
import com.nhom02.stumanager.adapter.MajorAdapter;
import com.nhom02.stumanager.model.Major;
import com.nhom02.stumanager.sqlite.MajorDao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ManageMajorActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView rcvMajor;
    private MajorAdapter majorAdapter;
    private TextView tvNoResults;
    List<Major> majorList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_major);

        getSupportActionBar().setTitle("Quản lý chuyên ngành");

        tvNoResults = findViewById(R.id.tvNoResults);
        rcvMajor = findViewById(R.id.rcvMajor);
        majorAdapter = new MajorAdapter(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcvMajor.setLayoutManager(linearLayoutManager);

        MajorDao dao = new MajorDao(this);
        try {
            majorList = dao.getALL();
            majorAdapter.setData(majorList);
            rcvMajor.setAdapter(majorAdapter);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            Toast.makeText(this, "Error: " + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

        findViewById(R.id.fabAdd).setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_search_toolbar, menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Nhập mã/tên chuyên ngành...");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterMajorList(newText);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void filterMajorList(String searchText) {
        List<Major> filteredList = new ArrayList<>();
        for (Major major : majorList) {
            if (major.getMajorId().toLowerCase().contains(searchText.toLowerCase()) ||
                    major.getMajorName().toLowerCase().contains(searchText.toLowerCase())) {
                filteredList.add(major);
            }
        }

        if (filteredList.isEmpty()) {
            rcvMajor.setVisibility(View.GONE);
            tvNoResults.setVisibility(View.VISIBLE);
        } else {
            rcvMajor.setVisibility(View.VISIBLE);
            tvNoResults.setVisibility(View.GONE);
        }

        majorAdapter.setData(filteredList);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fabAdd) {
            Intent intentAddMajor = new Intent(this, AddOrEditMajorActivity.class);
            startActivity(intentAddMajor);
        }
    }

    // Tự động làm mới khi thực hiện thao tác CRUD (chuyển đổi giữa các activity)
    @Override
    protected void onResume() {
        super.onResume();
        MajorDao dao = new MajorDao(this);
        List<Major> updatedList = null;
        try {
            updatedList = dao.getALL();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        majorList.clear();
        updatedList.forEach(item->majorList.add(item));
        majorAdapter.notifyDataSetChanged();
    }

    // Tránh rò rỉ bộ nhớ
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (majorAdapter != null) {
            majorAdapter.release();
        }
    }
}