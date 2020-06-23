package com.bud.contentresolverexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final static String TAG = MainActivity.class.getSimpleName();

    /**
     * student table
     *
     * @id primary key
     * @name student's name. e.g:peter.
     * @gender student's gender. e.g: 0 male; 1 female.
     * @number student's number. e.g: 201804081702.
     * @score student's score. more than 0 and less than 100. e.g:90.
     */
    private final static String AUTHORITY = "com.bud.contentproviderexample.provider";
    private final static Uri STUDENT_URI = Uri.parse("content://" + AUTHORITY + "/student");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_insert).setOnClickListener(this);
        findViewById(R.id.btn_query).setOnClickListener(this);
        findViewById(R.id.btn_update).setOnClickListener(this);
        findViewById(R.id.btn_delete).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_insert:
                inserValue();
                break;
            case R.id.btn_query:
                queryValue();
                break;
            case R.id.btn_update:
                updateValue();
                break;
            case R.id.btn_delete:
                deleteValue();
                break;
            default:
        }
    }

    private void inserValue() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", 0);
        contentValues.put("name", "peter");
        contentValues.put("gender", 0);
        contentValues.put("number", "202006241219");
        contentValues.put("score", "100");

        getContentResolver().insert(STUDENT_URI, contentValues);
    }

    private void queryValue() {
        Cursor cursor = getContentResolver().query(
                STUDENT_URI,
                new String[]{"id", "name", "gender", "number", "score"},
                null,
                null,
                null);
        while (cursor.moveToNext()) {
            Student student = new Student();
            student.id = cursor.getInt(cursor.getColumnIndex("id"));
            student.name = cursor.getString(cursor.getColumnIndex("name"));
            student.gender = cursor.getInt(cursor.getColumnIndex("gender"));
            student.number = cursor.getString(cursor.getColumnIndex("number"));
            student.score = cursor.getInt(cursor.getColumnIndex("score"));
            Log.d(TAG, "student = " + student.toString());
        }
    }

    private void updateValue() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", 0);
        contentValues.put("name", "update");
        contentValues.put("gender", 1);
        contentValues.put("number", "202006231122");
        contentValues.put("score", "99");

        getContentResolver().update(
                STUDENT_URI,
                contentValues,
                "id = ?",
                new String[]{"0"});
    }

    private void deleteValue() {
        getContentResolver().delete(
                STUDENT_URI,
                "name = ?",
                new String[]{"update"});
    }

    public class Student {
        private final static String TAG = "Student";

        public Integer id;
        public String name;
        public Integer gender;
        public String number;
        public Integer score;

        @Override
        public String toString() {
            return "Student{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", gender=" + gender +
                    ", number='" + number + '\'' +
                    ", score=" + score +
                    '}';
        }
    }
}