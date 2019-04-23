package com.sigma.footballroom.exercise;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sigma.footballroom.R;
import com.sigma.footballroom.db.Exercise;
import com.sigma.footballroom.workoutexercise.WorkoutExerciseViewModel;

import java.util.List;

public class ExerciseFragment extends Fragment {

    private Exercise exercise;

    ExerciseViewModel exerciseViewModel;

    TextView tvTitle;
    TextView tvDetails;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState == null){
            // Get back arguments
            if(getArguments() != null) {
                initData(getArguments().getLong("exerciseId", 0));
            }
        }

    }
    private void initData(long exerciseId) {
        ExerciseViewModel exerciseViewModel = ViewModelProviders.of(this).get(ExerciseViewModel.class);
        exercise = exerciseViewModel.findExerciseById(exerciseId);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {

        // Inflate the xml file for the fragment
        return inflater.inflate(R.layout.fragment_exercise_detail, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Set values for view here
        tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        tvDetails = (TextView) view.findViewById(R.id.tvDetails);

        tvTitle.setText(exercise.title);
    }

    // Activity is calling this to update view on Fragment
    public void updateView(int position){
        tvTitle.setText(exercise.title);
    }
}