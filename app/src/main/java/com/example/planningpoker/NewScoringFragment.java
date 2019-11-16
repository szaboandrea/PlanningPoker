package com.example.planningpoker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NewScoringFragment extends Fragment {

    private Database database;
    private OnGetDataListener onGetDataListener;
    private TextView mTask,mNext, mValue, mPrev,mSkip;
    private List<String> mNumbers = new ArrayList<>();
    private Integer count=0;
    private static final String TAG = "PlanningPokerNewScore";
    private String mLastElement;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_new_scoring, container, false);
        addElementToArrayList();
        mTask = view.findViewById(R.id.tv_tasks);
        mNext = view.findViewById(R.id.tv_next);
        mValue = view.findViewById(R.id.tv_numbers);
        mPrev = view.findViewById(R.id.tv_prev);
        mSkip = view.findViewById(R.id.tv_skips);
        database = new Database();
        onGetDataListener = new OnGetDataListener() {
            @Override
            public void onSuccess(final List<String> dataList) {

                mLastElement = dataList.get(dataList.size()-1);
                mTask.setText(dataList.get(count));
                mNext.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer index = mNumbers.indexOf(mValue.getText());
                        if (index+1<mNumbers.size()){
                            mValue.setText(mNumbers.get(index + 1));
                        }
                    }
                });

                mPrev.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer index = mNumbers.indexOf(mValue.getText());
                        if (index > 0){
                            mValue.setText(mNumbers.get(index-1));
                        }
                    }
                });

                mValue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mTask.getText() != mLastElement){
                            Database db = new Database();
                            db.addTaskScoreToDatabase(dataList.get(count),mValue.getText().toString());
                            mValue.setText("1");
                            count = count + 1;
                            mTask.setText(dataList.get(count));
                        }
                        else {
                            MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_place, new ResultFragment(),null).commit();
                        }
                    }
                });
                mSkip.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mTask.getText() != mLastElement){
                            mValue.setText("1");
                            count = count + 1;
                            mTask.setText(dataList.get(count));
                        }
                        else{
                            MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_place, new ResultFragment(),null).commit();
                        }
                    }
                });
            }

            @Override
            public void onSuccess(Map<String, Double> dataMap) {
            }
        };

        database.getTaskFromTasks(onGetDataListener);

        return view;
    }

    public void addElementToArrayList(){
        mNumbers.add("1");
        mNumbers.add("2");
        mNumbers.add("3");
        mNumbers.add("5");
        mNumbers.add("8");
        mNumbers.add("13");
        mNumbers.add("21");
        mNumbers.add("34");
        mNumbers.add("55");
    }
}
