package com.sigma.footballroom.workout;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.sigma.footballroom.db.FootballDatabase;
import com.sigma.footballroom.db.Program;
import com.sigma.footballroom.db.Workout;
import com.sigma.footballroom.db.WorkoutDao;

import java.util.List;

public class WorkoutsViewModel extends AndroidViewModel {
    private WorkoutDao workoutDao;
    private LiveData<List<Workout>> workoutsLiveData;

    public WorkoutsViewModel(@NonNull Application application) {
        super(application);
        workoutDao = FootballDatabase.getDatabase(application).workoutDao();
        workoutsLiveData = workoutDao.getAllWorkouts();
    }

    public LiveData<List<Workout>> getProgramWorkoutList(long programId) {

        return workoutDao.getProgramWorkouts(programId);
    }

    public void insert(Workout... workouts) {
        workoutDao.insert(workouts);
    }

    public void update(Workout workout) {
        workoutDao.update(workout);
    }

    public void deleteAll() {
        workoutDao.deleteAll();
    }
}

