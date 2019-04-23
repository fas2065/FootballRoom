package com.sigma.footballroom.workoutexercise;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.sigma.footballroom.db.Exercise;
import com.sigma.footballroom.db.FootballDatabase;
import com.sigma.footballroom.db.WorkoutExercise;
import com.sigma.footballroom.db.WorkoutExerciseDao;

import java.util.List;

public class WorkoutExerciseViewModel extends AndroidViewModel {
    private WorkoutExerciseDao workoutExerciseDao;

    public WorkoutExerciseViewModel(@NonNull Application application) {
        super(application);
        workoutExerciseDao = FootballDatabase.getDatabase(application).workoutExerciseDao();
    }

    public LiveData<List<WorkoutExercise>> getWorkoutExercises(long workoutId) {
        return workoutExerciseDao.getWorkoutExercise(workoutId);
    }

    public  LiveData<List<Exercise>> getExercisesForWorkout(long workoutId) {
        return workoutExerciseDao.getExercisesForWorkout(workoutId);
    }

    public void deleteAll() {
        workoutExerciseDao.deleteAll();
    }
}
