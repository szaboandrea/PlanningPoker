package com.example.planningpoker;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;


public class ScoringFragment extends Fragment {
    private static final String TAG = "PlanningPokerScore";
    private OnScoringFragmentInteractionListener mListener;

    private RecyclerView mRecyclerViewScoreList;
    private Button mButtonSubmit;

    public ScoringFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = getView();
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_scoring, container, false);
        }

        mRecyclerViewScoreList = view.findViewById(R.id.recyclerViewScoreList);
        mButtonSubmit = view.findViewById(R.id.buttonSubmit);

        mButtonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
