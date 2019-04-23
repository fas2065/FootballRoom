package com.sigma.footballroom.db;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface WorkoutExerciseDao {

    @Insert
    void insert(WorkoutExercise workoutExercise);

    @Query("SELECT * FROM exercise INNER JOIN workout_exercise ON " +
            "exercise.id=workout_exercise.exerciseId WHERE " +
            "workout_exercise.workoutId = :workoutId")
    LiveData<List<Exercise>> getExercisesForWorkout(final long workoutId);

    @Query("DELETE FROM workout_exercise")
    void deleteAll();


    @Query("SELECT * FROM workout_exercise WHERE workout_exercise.workoutId=:workoutId ")
    LiveData<List<WorkoutExercise>> getWorkoutExercise(long workoutId);
}
