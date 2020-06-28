package com.bud.localdatastore;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

/**
 * </br>
 * <p>
 * &nbsp
 *
 * @author: NIO
 * @since: 1.0.0, 6/28/20 9:57 AM
 * @version: 1.0.0
 */
public class SharedPreferencesHelper {

    private static SharedPreferencesHelper instance;
    private static SharedPreferences mSharedPreferences;

    public static SharedPreferencesHelper getInstance(Context context) {
        if (instance == null) {
            synchronized (SharedPreferencesHelper.class) {
                if (instance == null) {
                    instance = new SharedPreferencesHelper();
                    mSharedPreferences = context.getApplicationContext().
                            getSharedPreferences("test", context.MODE_PRIVATE);
                }
            }
        }
        return instance;
    }

    public void putString(String key, String value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public void putBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public void putInt(String key, int value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public void putFloat(String key, Float value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putFloat(key, value);
        editor.commit();
    }

    public void putLong(String key, Long value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    public void putStringSet(String key, Set<String> values) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putStringSet(key, values);
        editor.commit();
    }

    public String getString(String key) {
        return mSharedPreferences.getString(key, "");
    }

    public Boolean getBoolean(String key) {
        return mSharedPreferences.getBoolean(key, false);
    }

    public int getInt(String key) {
        return mSharedPreferences.getInt(key, 0);
    }

    public Float getFloat(String key) {
        return mSharedPreferences.getFloat(key, 0.0f);
    }

    public Long getLong(String key) {
        return mSharedPreferences.getLong(key, 0L);
    }

    public Set<String> getSet(String key) {
        return mSharedPreferences.getStringSet(key, new HashSet<String>());
    }

    public void remove(String key) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.remove(key);
        editor.commit();
    }

    public void clear() {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.clear();
        editor.commit();
    }
}
