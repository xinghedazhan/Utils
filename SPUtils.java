/*
 *   Copyright (C)  2016 android@19code.com
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package com.mooc.test;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Create YHK
 * 2016.6.10
 */
public class SPUtils {
//    private static final String FILE_NAME = "sp";
//    SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);

    public static String getString(Context context, String key,
                                    String defaultValue) {
         SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(context);
        return sp.getString(key, defaultValue);
    }

    public static void setString(Context context,String key,
                                 String value) {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(context);
        sp.edit().putString(key, value).apply();
    }

    public static boolean getBoolean(Context context, String key,
                                      boolean defaultValue) {
         SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(context);
        return sp.getBoolean(key, defaultValue);
    }

    public static boolean hasKey(Context context, String key) {
        return PreferenceManager.getDefaultSharedPreferences(context).contains(
                key);
    }

    public static void setBoolean(Context context, String key,
                                  boolean value) {
         SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(context);
        sp.edit().putBoolean(key, value).apply();
    }

    public static void setInt(Context context, String key,
                              int value) {
         SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(context);
        sp.edit().putInt(key, value).apply();
    }

    public static int getInt(Context context,  String key,
                             int defaultValue) {
         SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(context);
        return sp.getInt(key, defaultValue);
    }

    public static void setFloat(Context context,  String key,
                                 float value) {
         SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(context);
        sp.edit().putFloat(key, value).apply();
    }

    public static float getFloat(Context context,  String key,
                                  float defaultValue) {
         SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(context);
        return sp.getFloat(key, defaultValue);
    }

    public static void cleanAllSP(Context context) {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(context);
       sp.edit().clear().apply();
    }


}
