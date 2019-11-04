package com.example.planningpoker;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ScoreListAdapter extends RecyclerView.Adapter<ScoreListAdapter.ScoreViewHolder> {
    public static final String TAG = "PlanningPoker_ScoreAdap";
    List<String> tasks;

    public static class ScoreViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;
        public Spinner spinner;

        public ScoreViewHolder(View view){
            super(view);
            this.textView = view.findViewById(R.id.scoreTextViewTask);
            this.spinner = view.findViewById(R.id.spinnerScore);
        }
    }

    public ScoreListAdapter(List<String> tasks){
        this.tasks = tasks;
    }

    @NonNull
    @Override
    public ScoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "Creating view holder");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.score_list_item, parent, false);
        ScoreViewHolder scoreViewHolder = new ScoreViewHolder(view);
        return scoreViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreViewHolder holder, int position) {
        Log.d(TAG, "Binding view holder");
        holder.textView.setText(tasks.get(position));
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }
}
