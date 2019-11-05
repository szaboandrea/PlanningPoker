package com.example.planningpoker;

import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ResultListAdapter extends RecyclerView.Adapter<ResultListAdapter.ResultViewHolder>  {
    public static final String TAG = "PlanningPokerResAdap";
    List<Pair<String, Double>> results;

    public class ResultViewHolder  extends RecyclerView.ViewHolder{
        public TextView textViewTask;
        public TextView textViewScore;


        public ResultViewHolder(@NonNull View view) {
            super(view);
            this.textViewTask = view.findViewById(R.id.resultTextViewTask);
            this.textViewScore = view.findViewById(R.id.resultTextViewScore);
        }
    }

    public ResultListAdapter(List<Pair<String, Double>> results) {
        this.results = results;
    }

    @NonNull
    @Override
    public ResultListAdapter.ResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "Creating view holder");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_list_item, parent, false);
        ResultListAdapter.ResultViewHolder resultViewHolder = new ResultListAdapter.ResultViewHolder(view);
        return resultViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ResultListAdapter.ResultViewHolder holder, int position) {
        Log.d(TAG, "Binding view holder");
        holder.textViewTask.setText(results.get(position).first);
        holder.textViewScore.setText(String.valueOf(results.get(position).second));
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

}
