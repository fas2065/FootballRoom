package com.sigma.footballroom.workoutexercise;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sigma.footballroom.R;
import com.sigma.footballroom.db.Exercise;

import java.util.List;

public class WorkoutExerciseAdapter extends RecyclerView.Adapter<WorkoutExerciseAdapter.ExerciseViewHolder> {
    private LayoutInflater layoutInflater;
    private List<Exercise> exerciseList;
    private Context context;


    public WorkoutExerciseAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    public void setExerciseList(List<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
        notifyDataSetChanged();
    }

    @Override
    public WorkoutExerciseAdapter.ExerciseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = layoutInflater.inflate(R.layout.item_list_exercise, parent, false);
        return new WorkoutExerciseAdapter.ExerciseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WorkoutExerciseAdapter.ExerciseViewHolder holder, final int position) {
        final WorkoutExerciseFragment.OnItemSelectedListener listener;

        if (exerciseList == null) {
            return;
        }
        if (context instanceof WorkoutExerciseFragment.OnItemSelectedListener) {
            listener = (WorkoutExerciseFragment.OnItemSelectedListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement WorkoutExerciseFragment.OnItemSelectedListener");
        }
        final Exercise exercise = exerciseList.get(position);
        if (exercise != null) {
            holder.exerciseText.setText(exercise.title);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onExerciseItemSelected(exercise.id);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (exerciseList == null) {
            return 0;
        } else {
            return exerciseList.size();
        }
    }

    static class ExerciseViewHolder extends RecyclerView.ViewHolder {
        private TextView exerciseText;

        public ExerciseViewHolder(View itemView) {
            super(itemView);
            exerciseText = itemView.findViewById(R.id.tvExercise);

        }
    }

}
