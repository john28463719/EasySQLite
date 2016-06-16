package com.easysqlite.mylibrary.johnwu;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.StringDef;
import android.util.Log;
import static com.easysqlite.mylibrary.johnwu.Form.*;
import static com.easysqlite.mylibrary.johnwu.Storage.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JohnWu on 2016/5/3.
 */
public class MySQLite extends SQLiteOpenHelper {

    private Builder mBuilder;

    public MySQLite(Context context, Builder builder) {
        super(context, builder.DATABASE_NAME, null, builder.DATABASE_VERSION);
        mBuilder = builder;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table if not exists " + mBuilder.TABLE_NAME +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                mBuilder.tableBuilder;
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + mBuilder.TABLE_NAME);
        onCreate(db);
    }

    public String getString(String cols_name) {
        Cursor cursor = this.getWritableDatabase().query(mBuilder.TABLE_NAME, new String[]{cols_name}, null, null, null, null, null);
        if (cursor.moveToNext()) {
            int name_index = cursor.getColumnIndex(cols_name);
            String name = cursor.getString(name_index);
            return name;
        } else {
            return null;
        }
    }

    public List<String> getStrings(String cols_name) {
        List<String> list = new ArrayList<String>();
        Cursor cursor = this.getWritableDatabase().query(mBuilder.TABLE_NAME, new String[]{cols_name}, null, null, null, null, null);
        while (cursor.moveToNext()) {
            int name_index = cursor.getColumnIndex(cols_name);
            String name = cursor.getString(name_index);
            list.add(name);
        }
        return list;
    }

    public int getInt(String cols_name) {
        Cursor cursor = this.getWritableDatabase().query(mBuilder.TABLE_NAME, new String[]{cols_name}, null, null, null, null, null);
        if (cursor.moveToNext()) {
            int name_index = cursor.getColumnIndex(cols_name);
            int values = cursor.getInt(name_index);
            return values;
        } else {
            return -1;
        }
    }

    public List<Integer> getInts(String cols_name) {
        List<Integer> list = new ArrayList<Integer>();
        Cursor cursor = this.getWritableDatabase().query(mBuilder.TABLE_NAME, new String[]{cols_name}, null, null, null, null, null);
        while (cursor.moveToNext()) {
            int name_index = cursor.getColumnIndex(cols_name);
            int values = cursor.getInt(name_index);
            list.add(values);
        }
        return list;
    }

    public Bitmap getImage(String cols_name) {
        Cursor cursor = this.getWritableDatabase().query(mBuilder.TABLE_NAME, new String[]{cols_name}, null, null, null, null, null);
        if (cursor.moveToNext()) {
            try {
                int image_index = cursor.getColumnIndex(cols_name);
                byte[] imgByte = cursor.getBlob(image_index);
                return BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length);
            } catch (NullPointerException e) {
                return null;
            }
        } else {
            return null;
        }
    }

    public boolean deleteAll() {
        int result = this.getWritableDatabase().delete(mBuilder.TABLE_NAME, null, null);
        return false;
    }

    public static class Builder {

        @StringDef({TEXT,INT,INTEGER,BLOB})
        private @interface Form {}

        @StringDef({NOT_NULL,UNIQUE})
        private @interface Storage {}

        private StringBuilder tableBuilder;
        private static String TABLE_NAME = "table_name";
        private static String DATABASE_NAME = "my_database";
        private static int DATABASE_VERSION = 1;

        private Context mContext;

        public Builder(Context context) {
            tableBuilder = new StringBuilder();
            mContext = context;
        }

        public Builder append(String columnName,@Form String form) {
            tableBuilder.append(columnName + " " + form + " , ");
            return this;
        }

        public Builder append(String columnName,@Form String form,@Storage String storage) {
            if (storage == null) {
                append(columnName, form);
            }
            tableBuilder.append(columnName + " " + form + " " + storage + " , ");
            return this;
        }

        public Builder tableName(String tableName) {
            TABLE_NAME = tableName;
            return this;
        }

        public Builder databaseName(String databaseName) {
            DATABASE_NAME = databaseName;
            return this;
        }

        public Builder version(int version) {
            DATABASE_VERSION = version;
            return this;
        }

        public MySQLite build() {
            int lastIndex = tableBuilder.lastIndexOf(",");
            tableBuilder.replace(lastIndex, lastIndex + 1, ")");
            return new MySQLite(mContext, this);
        }
    }
}
