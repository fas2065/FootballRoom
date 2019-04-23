package com.sigma.footballroom.workout;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sigma.footballroom.R;
import com.sigma.footballroom.db.Workout;

import java.util.List;

public class WorkoutsAdapter extends RecyclerView.Adapter<WorkoutsAdapter.WorkoutsViewHolder> {
    private LayoutInflater layoutInflater;
    private List<Workout> workoutList;
    private Context context;


    public WorkoutsAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    public void setWorkoutList(List<Workout> workoutList) {
        this.workoutList = workoutList;
        notifyDataSetChanged();
    }

    @Override
    public WorkoutsAdapter.WorkoutsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = layoutInflater.inflate(R.layout.item_list_workout, parent, false);
        return new WorkoutsAdapter.WorkoutsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WorkoutsAdapter.WorkoutsViewHolder holder, final int position) {
        final WorkoutsFragment.OnItemSelectedListener listener;

        if (workoutList == null) {
            return;
        }
        if(context instanceof WorkoutsFragment.OnItemSelectedListener){      // context instanceof YourActivity
            listener = (WorkoutsFragment.OnItemSelectedListener) context; // = (YourActivity) context
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement WorkoutsFragment.OnItemSelectedListener");
        }
        final Workout workout = workoutList.get(position);
        if (workout != null) {
            holder.workoutText.setText(workout.title);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onWorkoutItemSelected(workout.id);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (workoutList == null) {
            return 0;
        } else {
            return workoutList.size();
        }
    }

    static class WorkoutsViewHolder extends RecyclerView.ViewHolder {
        private TextView workoutText;

        public WorkoutsViewHolder(View itemView) {
            super(itemView);
            workoutText = itemView.findViewById(R.id.ftWorkout);

        }
    }
}

