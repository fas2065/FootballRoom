package com.sigma.footballroom.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface ExerciseDao {

    @Query("SELECT * FROM exercise WHERE id = :id LIMIT 1")
    Exercise findExerciseById(long id);

    @Query("SELECT * FROM exercise WHERE title = :title LIMIT 1")
    Exercise findExerciseByName(String title);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(Exercise exercise);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Exercise... exercises);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void update(Exercise exercise);

    @Query("DELETE FROM exercise")
    void deleteAll();

    @Query("SELECT * FROM exercise ORDER BY title ASC")
    LiveData<List<Exercise>> getAllExercises();
}
