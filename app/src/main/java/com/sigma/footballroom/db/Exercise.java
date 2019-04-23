package com.sigma.footballroom.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "exercise",
        indices = {@Index(value = "title", unique = true)})
public class Exercise {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "title")
    @NonNull
    public String title;

    @ColumnInfo(name = "category")
    public String category;



    public Exercise(@NonNull String title) {
        this.title = title;
    }
}
