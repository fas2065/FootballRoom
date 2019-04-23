package com.sigma.footballroom.workout;

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
import com.sigma.footballroom.db.Workout;

import java.util.List;

public class WorkoutsFragment extends Fragment {

    long programId = 0;

    private WorkoutsAdapter workoutsAdapter;
    private WorkoutsViewModel workoutsViewModel;
    private Context context;



    public static WorkoutsFragment newInstance() {
        return new WorkoutsFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        workoutsAdapter = new WorkoutsAdapter(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState == null){
            // Get back arguments
            if(getArguments() != null) {
                programId = getArguments().getLong("programId", 0);
            }
        }
        initData(programId);
    }

    // Define the events that the fragment will use to communicate
    public interface OnItemSelectedListener {
        // This can be any number of events to be sent to the activity
        void onWorkoutItemSelected(long position);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_workouts, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview_workouts);
        recyclerView.setAdapter(workoutsAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        return view;
    }

    private void initData(long programId) {
        workoutsViewModel = ViewModelProviders.of(this).get(WorkoutsViewModel.class);
        workoutsViewModel.getProgramWorkoutList(programId).observe(this, new Observer<List<Workout>>() {
            @Override
            public void onChanged(@Nullable List<Workout> workouts) {
                workoutsAdapter.setWorkoutList(workouts);
            }
        });
    }

    public void removeData() {
        if (workoutsViewModel != null) {
            workoutsViewModel.deleteAll();
        }
    }
}
