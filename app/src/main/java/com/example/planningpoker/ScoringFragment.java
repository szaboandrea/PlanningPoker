package com.example.planningpoker;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Map;


public class ScoringFragment extends Fragment {
    private static final String TAG = "PlanningPokerScore";
    private OnScoringFragmentInteractionListener mListener;

    private Button mButtonSubmit;
    private TextView mTextViewAddTask;

    private RecyclerView mRecyclerViewScoreList;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private Database database;
    private OnGetDataListener onGetDataListener;

    public ScoringFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "Creating view");

        View view = getView();
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_scoring, container, false);
        }

        mRecyclerViewScoreList = view.findViewById(R.id.recyclerViewScoreList);
        mButtonSubmit = view.findViewById(R.id.buttonSubmit);
        mTextViewAddTask = view.findViewById(R.id.textViewAddTask);

        database = new Database();
        onGetDataListener = new OnGetDataListener() {
            @Override
            public void onSuccess(List<String> dataList) {
                layoutManager = new LinearLayoutManager(getContext());
                adapter = new ScoreListAdapter(dataList);
                mRecyclerViewScoreList.setLayoutManager(layoutManager);
                mRecyclerViewScoreList.setAdapter(adapter);
            }

            @Override
            public void onSuccess(Map<String, Double> dataMap) {
            }


        };

        database.getTasks(onGetDataListener);

        mButtonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Submit button click");
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_place, new ResultFragment(),null).commit();
                Map<String, String> datas = ((ScoreListAdapter) mRecyclerViewScoreList.getAdapter()).getListData();
                Database db = new Database();
                db.addScores(datas);
            }
        });

        mTextViewAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Add task text view click");
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_place, new AddNewTasksFragment(),null).commit();
            }
        });

        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.replaceFragment(0);
        }
    }

    public interface OnScoringFragmentInteractionListener {
        void replaceFragment(int fragment);
    }
}