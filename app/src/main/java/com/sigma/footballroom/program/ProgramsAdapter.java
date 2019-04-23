package com.sigma.footballroom.program;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sigma.footballroom.R;
import com.sigma.footballroom.db.Program;

import java.util.List;

public class ProgramsAdapter extends RecyclerView.Adapter<ProgramsAdapter.ProgramsViewHolder> {
    private LayoutInflater layoutInflater;
    private List<Program> programList;
    private Context context;


    public ProgramsAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    public void setProgramList(List<Program> programList) {
        this.programList = programList;
        notifyDataSetChanged();
    }

    @Override
    public ProgramsAdapter.ProgramsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = layoutInflater.inflate(R.layout.item_list_program, parent, false);
        return new ProgramsAdapter.ProgramsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProgramsAdapter.ProgramsViewHolder holder, final int position) {
        final ProgramsFragment.OnItemSelectedListener listener;

        if (programList == null) {
            return;
        }
        if(context instanceof ProgramsFragment.OnItemSelectedListener){      // context instanceof YourActivity
            listener = (ProgramsFragment.OnItemSelectedListener) context; // = (YourActivity) context
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement ProgramsFragment.OnItemSelectedListener");
        }
        final Program program = programList.get(position);
        if (program != null) {
            holder.programText.setText(program.title);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onProgramItemSelected(program.id);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (programList == null) {
            return 0;
        } else {
            return programList.size();
        }
    }

    static class ProgramsViewHolder extends RecyclerView.ViewHolder {
        private TextView programText;

        public ProgramsViewHolder(View itemView) {
            super(itemView);
            programText = itemView.findViewById(R.id.tvProgram);

        }
    }
}

