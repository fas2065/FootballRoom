package com.sigma.footballroom.program;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.sigma.footballroom.db.FootballDatabase;
import com.sigma.footballroom.db.Program;
import com.sigma.footballroom.db.ProgramDao;

import java.util.List;

public class ProgramsViewModel extends AndroidViewModel {
    private ProgramDao programDao;
    private LiveData<List<Program>> programsLiveData;

    public ProgramsViewModel(@NonNull Application application) {
        super(application);
        programDao = FootballDatabase.getDatabase(application).programDao();
        programsLiveData = programDao.getAllPrograms();
    }

    public LiveData<List<Program>> getProgramList() {
        return programsLiveData;
    }

    public void insert(Program... programs) {
        programDao.insert(programs);
    }

    public void update(Program program) {
        programDao.update(program);
    }

    public void deleteAll() {
        programDao.deleteAll();
    }
}
