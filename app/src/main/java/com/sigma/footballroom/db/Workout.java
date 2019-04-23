package com.sigma.footballroom.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "workout",
        foreignKeys = @ForeignKey(entity = Program.class,
                parentColumns = "id",
                childColumns = "programId",
                onDelete = ForeignKey.CASCADE),
        indices = {@Index("title"), @Index("id"), @Index("programId")})
public class Workout {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public long id;

    @ColumnInfo(name = "title")
    @NonNull
    public String title;

    @ColumnInfo(name = "programId")
    public long programId;

    public Workout(@NonNull String title, long programId) {
        this.title = title;
        this.programId = programId;
    }
}
