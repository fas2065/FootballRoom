package com.sigma.footballroom.exercise;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.sigma.footballroom.db.FootballDatabase;
import com.sigma.footballroom.db.Exercise;
import com.sigma.footballroom.db.ExerciseDao;

import java.util.List;

public class ExerciseViewModel extends AndroidViewModel {
    private ExerciseDao exerciseDao;
    private LiveData<List<Exercise>> exercisesLiveData;

    public ExerciseViewModel(@NonNull Application application) {
        super(application);
        exerciseDao = FootballDatabase.getDatabase(application).exerciseDao();
        exercisesLiveData = exerciseDao.getAllExercises();
    }

    public Exercise findExerciseById(long id) {
        return exerciseDao.findExerciseById(id);
    }

    public Exercise findExerciseById(String title) {
        return exerciseDao.findExerciseByName(title);
    }

    public LiveData<List<Exercise>> getExerciseList() {
        return exercisesLiveData;
    }

    public void insert(Exercise... exercises) {
        exerciseDao.insert(exercises);
    }

    public void update(Exercise exercise) {
        exerciseDao.update(exercise);
    }

    public void deleteAll() {
        exerciseDao.deleteAll();
    }
}
