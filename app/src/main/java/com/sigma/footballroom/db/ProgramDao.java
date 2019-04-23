package com.sigma.footballroom.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface ProgramDao {
    @Query("SELECT * FROM program WHERE id = :id LIMIT 1")
    Program findProgramById(long id);

    @Query("SELECT * FROM program WHERE title = :title LIMIT 1")
    Program findProgramByName(String title);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(Program program);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Program... programs);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void update(Program program);

    @Delete
    void delete(Program program);

    @Query("DELETE FROM program")
    void deleteAll();

    @Query("SELECT * FROM program ORDER BY title ASC")
    LiveData<List<Program>> getAllPrograms();
}
