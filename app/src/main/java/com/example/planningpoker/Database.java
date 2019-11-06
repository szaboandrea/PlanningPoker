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
    /*public OnGetDataListener onGetDataListener = new OnGetDataListener() {
        @Override
        public void onSuccess(final List<String> dataList) {
            for (String names : dataList){
                myRef=mDatabase.getReference().child("Scores").child(names);
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds : dataSnapshot.getChildren()){
                            Log.d(TAG, ds.getKey());
                            String key = ds.getKey();
                            Double value = Double.parseDouble(ds.getValue().toString());
                            if (!averageMap.containsKey(key)){
                                averageMap.put(key,value);
                            }
                            else {
                                averageMap.put(key, averageMap.get(key)+value);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        }
    };*/

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



    public List<String> getUserId(final OnGetDataListener onGetDataListener){
        mDatabase = FirebaseDatabase.getInstance();
        myRef = mDatabase.getReference().child("Users");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    String userId = ds.getKey();

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference reference = database.getReference().child("Scores").child(userId);
                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot ds : dataSnapshot.getChildren()){
                                Log.d(TAG, ds.getKey());
                                String key = ds.getKey();
                                Double value = Double.parseDouble(ds.getValue().toString());
                                if (!averageMap.containsKey(key)){
                                    averageMap.put(key,value);
                                }
                                else {
                                    averageMap.put(key, averageMap.get(key)+value);
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
                //onGetDataListener.onSuccess(averageMap);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return nameList;
    }



}
