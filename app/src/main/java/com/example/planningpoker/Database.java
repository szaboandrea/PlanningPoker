package com.example.planningpoker;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Database {

    private static final String TAG = "PlanningPokerDatabase";
    DatabaseReference myRef;
    FirebaseDatabase mDatabase;
    public List<String> listTask = new ArrayList<String>();
    public interface OnGetDataListener {
        public void onStart();
        public void onSuccess(DataSnapshot data);
        public void onFailed(DatabaseError databaseError);
    }

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

    public void addScores(Map<String,String> data){
        FirebaseDatabase.getInstance().getReference("Scores")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull com.google.android.gms.tasks.Task<Void> task) {
                if (!task.isSuccessful()){
                    Log.d(TAG, "Doesn't add scores");
                }
            }
        });
    }

    public List<String> getTasks(){
        mDatabase = FirebaseDatabase.getInstance();
        myRef = mDatabase.getReference();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataSnapshot = dataSnapshot.child("Tasks");
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    listTask.add(ds.getKey());
                }
                Log.d(TAG, listTask.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Log.d(TAG,"Atad:" + listTask.toString());
        return listTask;
    }

    public List<String> showData(DataSnapshot dataSnapshot){
        for (DataSnapshot ds : dataSnapshot.getChildren()){
            listTask.add(ds.getKey());
        }
        Log.d(TAG, listTask.toString());
        return listTask;
    }
}
