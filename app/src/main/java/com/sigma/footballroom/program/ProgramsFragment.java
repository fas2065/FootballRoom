package com.sigma.footballroom.program;

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
import com.sigma.footballroom.db.Program;

import java.util.List;

public class ProgramsFragment extends Fragment {
    private ProgramsAdapter programsAdapter;
    private ProgramsViewModel programsViewModel;
    private Context context;

    public static ProgramsFragment newInstance() {
        return new ProgramsFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        programsAdapter = new ProgramsAdapter(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
    }

    // Define the events that the fragment will use to communicate
    public interface OnItemSelectedListener {
        // This can be any number of events to be sent to the activity
        void onProgramItemSelected(long id);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_programs, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview_programs);
        recyclerView.setAdapter(programsAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        return view;
    }

    private void initData() {
        programsViewModel = ViewModelProviders.of(this).get(ProgramsViewModel.class);
        programsViewModel.getProgramList().observe(this, new Observer<List<Program>>() {
            @Override
            public void onChanged(@Nullable List<Program> programs) {
                programsAdapter.setProgramList(programs);
            }
        });
    }

    public void removeData() {
        if (programsViewModel != null) {
            programsViewModel.deleteAll();
        }
    }
}