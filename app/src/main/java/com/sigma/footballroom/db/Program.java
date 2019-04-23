package com.sigma.footballroom.db;
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "program",
        indices = {@Index(value = "title", unique = true)})
public class Program {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public long id;

    @ColumnInfo(name = "title")
    @NonNull
    public String title;

    public Program(@NonNull String title) {
        this.title = title;
    }
}
