package com.example.planningpoker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class AddNewTasksFragment extends Fragment {

    private EditText mAddTask;
    private Button mAdd;
    private TextView mBack;
    Database data = new Database();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_new_tasks, container, false);
        mAddTask = view.findViewById(R.id.et_add_new_task);
        mAdd = view.findViewById(R.id.btn_add);
        mBack = view.findViewById(R.id.tv_back_to_login);

        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mAddTask.getText().toString().isEmpty()){
                    data.addTask(mAddTask.getText().toString());
                    mAddTask.setText("");
                }
            }
        });

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_place, new ScoringFragment(),null).commit();
            }
        });
        return view;
    }

}
