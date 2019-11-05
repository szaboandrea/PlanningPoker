package com.example.planningpoker;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class ResultFragment extends Fragment {
    private static final String TAG = "PlanningPokerResult";
    private OnResultFragmentInteractionListener mListener;

    private RecyclerView mRecyclerViewResultList;
    private Button mButtonBack;

    public ResultFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "Creating view");

        View view = getView();
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_result, container, false);
        }

        mRecyclerViewResultList = view.findViewById(R.id.recyclerViewResultList);
        mButtonBack = view.findViewById(R.id.buttonBack);

        mButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Back button click");
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_place, new ScoringFragment(),null).commit();
            }
        });

        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.replaceFragment(0);
        }
    }

    public interface OnResultFragmentInteractionListener {
        void replaceFragment(int fragment);
    }
}
