package com.sigma.footballroom.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;

@Entity(tableName = "workout_exercise",
        primaryKeys = { "workoutId", "exerciseId" },
        foreignKeys = {
                @ForeignKey(entity = Exercise.class,
                        parentColumns = "id",
                        childColumns = "exerciseId",
                        onDelete = ForeignKey.CASCADE

                ),
                @ForeignKey(entity = Workout.class,
                        parentColumns = "id",
                        childColumns = "workoutId",
                        onDelete = ForeignKey.CASCADE)
        },
        indices = {@Index("workoutId"), @Index("exerciseId")})


public class WorkoutExercise {

    public final long workoutId;
    public final long exerciseId;

//    @ColumnInfo(name = "duration")
//    public final int duration;
//
//    @ColumnInfo(name = "repetitions")
//    public final int repetitions;

    public WorkoutExercise(final long workoutId, final long exerciseId) {
        this.workoutId = workoutId;
        this.exerciseId = exerciseId;
    }
}
