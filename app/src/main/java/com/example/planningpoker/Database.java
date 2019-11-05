package com.example.planningpoker;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.database.FirebaseDatabase;

public class Database {

    private static final String TAG = "PlanningPokerDatabase";

    public void addTask(String task_name){
        Task task = new Task(task_name);
        FirebaseDatabase.getInstance().getReference("Tasks")
                .child(task_name)
                .setValue(task).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull com.google.android.gms.tasks.Task<Void> task) {
                    if (!task.isSuccessful()){
                        Log.d(TAG, "Doesn't add task");
                    }
                }
        });
    }
}
