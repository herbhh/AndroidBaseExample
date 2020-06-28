package com.bud.localdatastore;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

/**
 * </br>
 * <p>
 * &nbsp
 *
 * @author: NIO
 * @since: 1.0.0, 6/28/20 2:24 PM
 * @version: 1.0.0
 */
public class FileActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTvFileContent;
    private EditText mEdFileName;
    private EditText mEdFileContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_files);
        mTvFileContent = (TextView) findViewById(R.id.tv_file_content);
        mEdFileName = (EditText) findViewById(R.id.ed_file_name);
        mEdFileContent = (EditText) findViewById(R.id.ed_file_content);
        findViewById(R.id.btn_save_file).setOnClickListener(this);
        findViewById(R.id.btn_read_file).setOnClickListener(this);
        findViewById(R.id.btn_read_sdcard_file).setOnClickListener(this);
        findViewById(R.id.btn_save_sdcard_file).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_save_file:
                String writeFileName = mEdFileName.getText().toString();
                String writeFileContent = mEdFileContent.getText().toString();
                FileStoreHelper.getInstance(this).writeFile(writeFileName, writeFileContent);
                break;
            case R.id.btn_read_file:
                String readFileName = mEdFileName.getText().toString();
                String readFileContent = FileStoreHelper.getInstance(this).readFile(readFileName);
                mTvFileContent.setText(readFileContent);
                break;
            case R.id.btn_save_sdcard_file:
                String wsdfn = mEdFileName.getText().toString();
                String sdfc = mEdFileContent.getText().toString();
                FileStoreHelper.getInstance(this).writeSDCard(wsdfn, sdfc);
                break;
            case R.id.btn_read_sdcard_file:
                String rsdfn = mEdFileName.getText().toString();
                String rsdfc = FileStoreHelper.getInstance(this).readSDCard(rsdfn);
                mTvFileContent.setText(rsdfc);
                break;
        }
    }
}
