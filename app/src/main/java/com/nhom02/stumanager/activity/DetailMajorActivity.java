package com.nhom02.stumanager.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.nhom02.stumanager.R;
import com.nhom02.stumanager.model.Major;
import com.nhom02.stumanager.sqlite.MajorDao;

import java.text.ParseException;

public class DetailMajorActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvMajorId, tvMajorName, tvMajorPhone, tvMajorLink, tvEduProgName;
    private String majorId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_major);

        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }

        Major major = (Major) bundle.get("object_major");

        tvMajorId = findViewById(R.id.tvMajorId);
        tvMajorName = findViewById(R.id.tvMajorName);
        tvMajorPhone = findViewById(R.id.tvMajorPhone);
        tvMajorLink = findViewById(R.id.tvMajorLink);
        tvEduProgName = findViewById(R.id.tvEduProgName);

        majorId = major.getMajorId();
        tvMajorId.setText(majorId);
        tvMajorName.setText(major.getMajorName());
        if (major.getMajorPhone() != null && major.getMajorPhone() != "")
            tvMajorPhone.setText(major.getMajorPhone());
        if (major.getMajorLink() != null && major.getMajorLink() != "")
            tvMajorLink.setText(major.getMajorLink());
        tvEduProgName.setText(major.getEduProgId());

        findViewById(R.id.btnEdit).setOnClickListener(this);
        findViewById(R.id.btnDelete).setOnClickListener(this);
        findViewById(R.id.btnClose).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnEdit) {
            Intent intentEdit = new Intent(this, AddOrEditMajorActivity.class);

            Bundle bundle = new Bundle();
            bundle.putString("id", majorId);
            intentEdit.putExtra("data", bundle);

            startActivity(intentEdit);
            finish();
        }
        else if (v.getId() == R.id.btnDelete) {
            MajorDao dao = new MajorDao(this);
            Major major = new Major();
            try {
                major = dao.getById(majorId);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            AlertDialog.Builder builder = new AlertDialog.Builder(DetailMajorActivity.this);
            builder.setTitle("Cảnh báo!");
            String message = "Khi bạn xóa, các lớp thuộc chuyên ngành này sẽ mất Mã chuyên ngành<br/><br/>" + "Bạn vẫn muốn xóa ngành " + major.getMajorName() + " chứ?";

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                builder.setMessage(Html.fromHtml(message, Html.FROM_HTML_MODE_COMPACT));
            } else {
                builder.setMessage(Html.fromHtml(message));
            }

            Major finalMajor = major;
            Major finalMajor1 = major;
            builder.setPositiveButton("Xóa", (dialog, which) -> {
                Toast.makeText(this, "Đã xóa ngành " + finalMajor.getMajorName() + " thành công!", Toast.LENGTH_SHORT).show();
                dao.delete("" + finalMajor1.getMajorId());
                finish();
            });
            builder.setNegativeButton("Hủy", (dialog, which) -> dialog.dismiss());

            builder.show();
        }
        else if (v.getId() == R.id.btnClose) {
            finish();
        }
    }
}