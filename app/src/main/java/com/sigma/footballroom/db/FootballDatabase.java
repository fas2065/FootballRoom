package com.sigma.footballroom.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

@Database(entities = {Exercise.class, Program.class, Workout.class, WorkoutExercise.class}, version = 1)
public abstract class FootballDatabase extends RoomDatabase {
    private static FootballDatabase INSTANCE;
    private static final String DB_NAME = "football.db";

    public static FootballDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (FootballDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            FootballDatabase.class, DB_NAME)
                            .allowMainThreadQueries() // SHOULD NOT BE USED IN PRODUCTION !!!
                            .addCallback(new RoomDatabase.Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    Log.d("FootballDatabase", "populating with data...");
                                    new PopulateDbAsync(INSTANCE).execute();
                                }
                            })
                            .build();
                }
            }
        }

        return INSTANCE;
    }

    public void clearDb() {
        if (INSTANCE != null) {
            new PopulateDbAsync(INSTANCE).execute();
        }
    }

    public abstract ProgramDao programDao();

    public abstract WorkoutDao workoutDao();

    public abstract ExerciseDao exerciseDao();

    public abstract WorkoutExerciseDao workoutExerciseDao();

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        private final ProgramDao programDao;
        private final WorkoutDao workoutDao;
        private final ExerciseDao exerciseDao;
        private final WorkoutExerciseDao workoutExerciseDao;

        public PopulateDbAsync(FootballDatabase instance) {
            programDao = instance.programDao();
            workoutDao = instance.workoutDao();
            exerciseDao = instance.exerciseDao();
            workoutExerciseDao = instance.workoutExerciseDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            programDao.deleteAll();
            workoutDao.deleteAll();
            exerciseDao.deleteAll();
            workoutExerciseDao.deleteAll();

            Log.i("FootballDatabase", "Adding three Programs...");

            Program programOne = new Program("Easy");
            Program programTwo = new Program("Mediate");
            Workout workout = null;
            long id = programDao.insert(programOne);

            for (int i = 1; i <= 30; i++) {
                workout = new Workout(getWorkoutTitle(i) + i, id);
                workoutDao.insert(workout);
            }

                    

            Exercise exercise1 = new Exercise("exercise1");
            Exercise exercise2 = new Exercise("exercise2");

            exerciseDao.insert(exercise1, exercise2);

            exercise1 = exerciseDao.findExerciseByName(exercise1.title);
            exercise2 = exerciseDao.findExerciseByName(exercise2.title);

//            final int pIdTwo = (int) programDao.insert(programTwo);
//            Workout workoutTwo = new Workout("Arrival", pIdTwo);
//            Workout workoutThree = new Workout("Blade Runner 2049", pIdTwo);
//            Workout workoutFour = new Workout("Passengers", (int) workoutDao.insert(workoutThree));

//            workoutDao.insert(workoutOneP1, workoutTwoP1, workoutThreeP1, workoutFourP1);
//
            Workout workoutTwoP1 = workoutDao.findWorkoutByName("Day02");

            workoutExerciseDao.insert(new WorkoutExercise(workoutTwoP1.id, exercise1.id ));
            workoutExerciseDao.insert(new WorkoutExercise(workoutTwoP1.id, exercise2.id ));


            return null;
        }

        private String getWorkoutTitle(int i) {
            if (i < 10)
                return "Day0";
            return "Day";
        }
    }
}

