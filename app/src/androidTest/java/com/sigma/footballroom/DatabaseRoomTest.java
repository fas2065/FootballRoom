package com.sigma.footballroom;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.sigma.footballroom.db.Exercise;
import com.sigma.footballroom.db.ExerciseDao;
import com.sigma.footballroom.db.FootballDatabase;
import com.sigma.footballroom.db.Program;
import com.sigma.footballroom.db.ProgramDao;
import com.sigma.footballroom.db.Workout;
import com.sigma.footballroom.db.WorkoutDao;
import com.sigma.footballroom.db.WorkoutExerciseDao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class DatabaseRoomTest {
    private ProgramDao programDao;
    private WorkoutDao workoutDao;
    private ExerciseDao exerciseDao;
    private WorkoutExerciseDao workoutExerciseDao;

    private FootballDatabase db;

    public DatabaseRoomTest() {
    }

    @Before
    public void createDb() {

        //        db = FootballDatabase.getDatabase(InstrumentationRegistry.getTargetContext());
        db = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getTargetContext(),
                FootballDatabase.class).allowMainThreadQueries().build();
        programDao = db.programDao();
        workoutDao = db.workoutDao();
    }

    @After
    public void closeDb() throws IOException {
        programDao.deleteAll();
        workoutDao.deleteAll();
        db.close();
    }

    @Test
    public void crudWorkout() throws Exception {
        Program programOne = new Program("Easy");

        programDao.insert(programOne);
        Program byName = programDao.findProgramByName("Easy");
        assertNotNull(byName);

        Workout workout = new Workout("day one", byName.id);

        workoutDao.insert(workout);

        Workout dayOne = workoutDao.findWorkoutByName("day one");
        dayOne.title = "DayOne";

        workoutDao.update(dayOne);

        workout = workoutDao.findWorkoutById(dayOne.id);

        assertEquals("DayOne", workout.title);

        workoutDao.insert(new Workout("dayTwo", byName.id),
                new Workout("dayThree", byName.id));

        List<Workout> workoutLiveData = workoutDao.getProgramWorkouts(programOne.id).getValue();
        programDao.deleteAll();
    }

    @Test
    public void crudProgram() throws Exception {
        Program programOne = new Program("Easy");
        Program programTwo = new Program("Mediate");
        Program programThree = new Program("Advanced");

        programDao.insert(programOne);
        Program byName = programDao.findProgramByName("Easy");
        assertNotNull(byName);

        byName.title = "Easy1";

        programDao.update(byName);

        Program updated = programDao.findProgramByName("Easy1");
        assertEquals(updated.id, byName.id);

        programDao.insert(programTwo, programThree);

        List<Program> programs = programDao.getAllPrograms().getValue();

        Program med = programDao.findProgramByName("Mediate");
        assertNotNull(med);

        Program adv = programDao.findProgramByName("Advanced");
        assertNotNull(adv);

//        assertEquals(3, programs.size());

        programDao.delete(updated);

//        programs = programDao.getAllPrograms().getValue();

//        assertEquals(2, programs.size());

        programDao.deleteAll();
    }


}