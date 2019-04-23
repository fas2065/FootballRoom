package com.sigma.footballroom;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.sigma.footballroom.db.FootballDatabase;
import com.sigma.footballroom.exercise.ExerciseFragment;
import com.sigma.footballroom.workoutexercise.WorkoutExerciseFragment;
import com.sigma.footballroom.program.ProgramsFragment;
import com.sigma.footballroom.workout.WorkoutsFragment;


public class MainActivity extends AppCompatActivity implements
        ProgramsFragment.OnItemSelectedListener,
        WorkoutsFragment.OnItemSelectedListener,
        WorkoutExerciseFragment.OnItemSelectedListener {

    private Fragment nextFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        reCreateDatabase();

        if (savedInstanceState == null) {
            ProgramsFragment firstFragment = ProgramsFragment.newInstance();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.flContainer, firstFragment);
            ft.commit();
        }
    }

    @Override
    public void onProgramItemSelected(long id) {
        nextFragment = new WorkoutsFragment();
        passArgToNextFragment(id, "programId");
        replaceContainer(nextFragment);
    }

    @Override
    public void onWorkoutItemSelected(long id) {
        nextFragment = new WorkoutExerciseFragment();
        passArgToNextFragment(id, "workoutId");
        replaceContainer(nextFragment);
    }

    @Override
    public void onExerciseItemSelected(long id) {
        nextFragment = new ExerciseFragment();
        passArgToNextFragment(id, "exerciseId");
        replaceContainer(nextFragment);
    }


    private void passArgToNextFragment(long id, String objectId) {
        Bundle args = new Bundle();
        args.putLong(objectId, id);
        nextFragment.setArguments(args);          // (1) Communicate with Fragment using Bundle
    }

    private void replaceContainer(Fragment secondFragment) {
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flContainer2, secondFragment) // replace flContainer
                    //.addToBackStack(null)
                    .commit();
        }else{
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flContainer, secondFragment) // replace flContainer
                    .addToBackStack(null)
                    .commit();
        }
    }



    private void reCreateDatabase() {
        FootballDatabase.getDatabase(this).clearDb();
    }
}
