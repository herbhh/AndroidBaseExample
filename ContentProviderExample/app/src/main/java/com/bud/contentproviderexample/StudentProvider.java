package com.bud.contentproviderexample;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * </br>
 * <p>
 * &nbsp
 *
 * @author: NIO
 * @since: 1.0.0, 6/23/20 8:27 PM
 * @version: 1.0.0
 */
public class StudentProvider extends ContentProvider {

    private final static String TAG = StudentProvider.class.getSimpleName();

    private final static String AUTHORITY = "com.bud.contentproviderexample.provider";
    private final static int STUDENT_URI_CODE = 0;

    private Context mContext;
    private SQLiteDatabase mDataBase;
    private final static UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(AUTHORITY, "student", STUDENT_URI_CODE);
    }

    @Override
    public boolean onCreate() {
        mContext = getContext();
        mDataBase = new DBOpenHelper(mContext).getWritableDatabase();

        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        int uriType = sUriMatcher.match(uri);
        Cursor cursor;
        switch (uriType) {
            case STUDENT_URI_CODE:
                cursor = mDataBase.query(
                        DBOpenHelper.DATABASE_STUDENT_TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder,
                        null);
                break;
            default:
                throw new IllegalArgumentException("UnSupport Uri:" + uri);
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        int uriType = sUriMatcher.match(uri);
        long row;

        switch (uriType) {
            case STUDENT_URI_CODE:
                row = mDataBase.insert(
                        DBOpenHelper.DATABASE_STUDENT_TABLE_NAME,
                        null,
                        contentValues);
                break;
            default:
                throw new IllegalArgumentException("UnSupport Uri:" + uri);
        }

        if (row > -1) {
            mContext.getContentResolver().notifyChange(uri, null);
            return ContentUris.withAppendedId(uri, row);
        }

        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int uriType = sUriMatcher(uri);
        int rowDelete;

        switch (uriType) {
            case STUDENT_URI_CODE:
                rowDelete = mDataBase.delete(
                        DBOpenHelper.DATABASE_STUDENT_TABLE_NAME,
                        selection,
                        selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("UnSupport Uri:" + uri);
        }

        if (rowDelete > 0) {
            mContext.getContentResolver().notifyChange(uri, null);
        }

        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String selection, @Nullable String[] selectionArgs) {
        int uriType = sUriMatcher.match(uri);
        int rowUpdate;

        switch (uriType) {
            case STUDENT_URI_CODE:
                rowUpdate = mDataBase.update(
                        DBOpenHelper.DATABASE_STUDENT_TABLE_NAME,
                        contentValues,
                        selection,
                        selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("UnSupport Uri:" + uri);
        }

        if (rowUpdate > 0) {
            mContext.getContentResolver().notifyChange(uri, null);
        }

        return rowUpdate;
    }
}
