package com.sigma.footballroom.workoutexercise;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sigma.footballroom.R;
import com.sigma.footballroom.db.Exercise;

import java.util.List;

public class WorkoutExerciseFragment extends Fragment {

    private WorkoutExerciseViewModel workoutExerciseViewModel;
    private WorkoutExerciseAdapter workoutExerciseAdapter;
    private Context context;

    private long workoutId;

    public static WorkoutExerciseFragment newInstance() {
        return new WorkoutExerciseFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        workoutExerciseAdapter = new WorkoutExerciseAdapter(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState == null){
            // Get back arguments
            if(getArguments() != null) {
                workoutId = getArguments().getLong("workoutId", 0);
            }
        }
        initData(workoutId);
    }

    // Define the events that the fragment will use to communicate
    public interface OnItemSelectedListener {
        // This can be any number of events to be sent to the activity
        void onExerciseItemSelected(long position);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercises, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview_exercise);
        recyclerView.setAdapter(workoutExerciseAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        return view;
    }

    private void initData(long workoutId) {
        workoutExerciseViewModel = ViewModelProviders.of(this).get(WorkoutExerciseViewModel.class);
        workoutExerciseViewModel.getExercisesForWorkout(workoutId).observe(this, new Observer<List<Exercise>>() {
            @Override
            public void onChanged(@Nullable List<Exercise> exerciseList) {
                workoutExerciseAdapter.setExerciseList(exerciseList);
            }
        });
    }
   
}