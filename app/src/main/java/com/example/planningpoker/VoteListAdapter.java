package com.example.planningpoker;

import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class VoteListAdapter extends RecyclerView.Adapter<VoteListAdapter.VoteViewHolder> {

    public static class VoteViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;
        public Spinner spinner;

        public VoteViewHolder(TextView textView, Spinner spinner){
            super(textView);
            this.textView = textView;
            this.spinner = spinner;
        }
    }


    @NonNull
    @Override
    public VoteListAdapter.VoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull VoteListAdapter.VoteViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
