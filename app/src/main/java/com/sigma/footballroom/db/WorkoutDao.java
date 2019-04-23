package com.sigma.footballroom.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;
@Dao
public interface WorkoutDao {
   
    @Query("SELECT * FROM workout WHERE id = :id LIMIT 1")
    Workout findWorkoutById(long id);

    @Query("SELECT * FROM workout WHERE title = :title LIMIT 1")
    Workout findWorkoutByName(String title);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(Workout workout);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Workout... workouts);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void update(Workout workout);

    @Query("DELETE FROM workout")
    void deleteAll();

    @Query("SELECT * FROM workout ORDER BY title ASC")
    LiveData<List<Workout>> getAllWorkouts();

    @Query("SELECT * FROM workout WHERE programId = :programId ORDER BY title ASC")
    LiveData<List<Workout>> getProgramWorkouts(long programId);
}


