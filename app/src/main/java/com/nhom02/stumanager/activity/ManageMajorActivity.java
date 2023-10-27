package com.nhom02.stumanager.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.nhom02.stumanager.R;
import com.nhom02.stumanager.adapter.MajorAdapter;
import com.nhom02.stumanager.model.Major;

import java.util.ArrayList;
import java.util.List;

public class ManageMajorActivity extends AppCompatActivity {
    private RecyclerView rcvMajor;
    private MajorAdapter majorAdapter;
    private TextView tvNoResults;


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

        majorAdapter.setData(getListMajor());
        rcvMajor.setAdapter(majorAdapter);

    }

    //Sau này sẽ thay thế bằng dữ liệu lấy từ SQLite
    private List<Major> getListMajor(){
        List<Major> list = new ArrayList<>();
        list.add(new Major("SPA1", "Sư phạm Anh", "http://youtube.com"));
        list.add(new Major("SPT1", "Sư phạm Trung", "http://youtube.com"));
        list.add(new Major("VNH1", "Việt Nam học", "http://google.com"));
        list.add(new Major("CNTT1", "Công nghệ thông tin", "http://youtube.com"));

        list.add(new Major("SPA1", "Sư phạm Anh", "http://youtube.com"));
        list.add(new Major("SPT1", "Sư phạm Trung", "http://youtube.com"));
        list.add(new Major("VNH1", "Việt Nam học", "http://youtube.com"));
        list.add(new Major("CNTT1", "Công nghệ thông tin", "http://youtube.com"));

        list.add(new Major("SPA1", "Sư phạm Anh", "http://youtube.com"));
        list.add(new Major("SPT1", "Sư phạm Trung", "http://youtube.com"));
        list.add(new Major("VNH1", "Việt Nam học", "http://youtube.com"));
        list.add(new Major("CNTT1", "Công nghệ thông tin", "http://youtube.com"));

        list.add(new Major("SPA1", "Sư phạm Anh", "http://youtube.com"));
        list.add(new Major("SPT1", "Sư phạm Trung", "http://youtube.com"));
        list.add(new Major("VNH1", "Việt Nam học", "http://youtube.com"));
        list.add(new Major("CNTT1", "Công nghệ thông tin", "http://youtube.com"));
        return list;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_search_toolbar, menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Nhập mã/tên chuyên ngành cần tìm");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Nhập việc sau khi nhấn nút search vào đây
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
        for (Major major : getListMajor()) {
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
}