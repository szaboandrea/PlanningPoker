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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Database {

    private static final String TAG = "PlanningPokerDatabase";
    DatabaseReference myRef;
    FirebaseDatabase mDatabase;
    public List<String> listTask = new ArrayList<String>();
    public List<String> nameList = new ArrayList<String>();
    public Map<String, Double> averageMap = new HashMap<>();
    public OnGetDataListener onGetDataListener;

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


    public void addTaskScoreToDatabase(String task_name, String score){
        FirebaseDatabase.getInstance().getReference("Tasks")
                .child(task_name)
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .setValue(score)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull com.google.android.gms.tasks.Task<Void> task) {
                        if (!task.isSuccessful()){
                            Log.d(TAG, "Doesn't add score");
                        }
                    }
                });
    }

    public List<String> getTasks(final OnGetDataListener onGetDataListener){
        mDatabase = FirebaseDatabase.getInstance();
        myRef = mDatabase.getReference();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataSnapshot = dataSnapshot.child("Tasks");
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    listTask.add(ds.getKey());
                }
                onGetDataListener.onSuccess(listTask);
                Log.d(TAG, listTask.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Log.d(TAG,"Atad:" + listTask.toString());
        return listTask;
    }



    public List<String> getResult(final OnGetDataListener onGetDataListener){
        mDatabase = FirebaseDatabase.getInstance();
        myRef = mDatabase.getReference().child("Scores");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Double size = (double) dataSnapshot.getChildrenCount();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Log.d(TAG, size.toString());

                    for (DataSnapshot task : ds.getChildren()) {
                        String key = task.getKey();
                        Double value = Double.parseDouble(task.getValue().toString())/size;
                        if (!averageMap.containsKey(key)) {
                            averageMap.put(key, value);
                        } else {
                            averageMap.put(key, averageMap.get(key) + value);
                        }
                    }
                }

                onGetDataListener.onSuccess(averageMap);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return nameList;
    }

    public void getAverage(final OnGetDataListener onGetDataListener){
        mDatabase = FirebaseDatabase.getInstance();
        myRef = mDatabase.getReference("Tasks");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Log.d(TAG, ds.toString());
                    String key = ds.getKey();
                    Double size = (double) dataSnapshot.child(key).getChildrenCount();

                    for (DataSnapshot people : ds.getChildren()) {
                        Double value = Double.parseDouble(people.getValue().toString());
//                        Log.d(TAG, "ertek:" +  value.toString());
                        if (!averageMap.containsKey(key)) {
                            averageMap.put(key, value);
                        } else {
                            averageMap.put(key, averageMap.get(key) + value);
                        }
                    }
                    averageMap.put(key, averageMap.get(key) / size);
                }
                onGetDataListener.onSuccess(averageMap);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}