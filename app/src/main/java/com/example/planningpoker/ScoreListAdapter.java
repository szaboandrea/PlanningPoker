package com.example.planningpoker;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ScoreListAdapter extends RecyclerView.Adapter<ScoreListAdapter.ScoreViewHolder> {
    public static final String TAG = "PlanningPokerScoreAdap";
    List<String> tasks;
    List<String> spinnerValues;

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
        this.spinnerValues = new ArrayList<>();
    }

    public Map<String, String> getListData(){
        Map<String, String> data = new HashMap<>();
        int listSize = getItemCount();
        for (int i=0; i<listSize; ++i){
            data.put(tasks.get(i), spinnerValues.get(i));
        }
        return data;
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
    public void onBindViewHolder(@NonNull final ScoreViewHolder holder, final int position) {
        Log.d(TAG, "Binding view holder");
        holder.textView.setText(tasks.get(position));
        holder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinnerValues.add(position, holder.spinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }
}
