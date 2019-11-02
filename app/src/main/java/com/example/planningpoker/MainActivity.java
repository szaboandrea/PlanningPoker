package com.example.planningpoker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements
        ScoringFragment.OnScoringFragmentInteractionListener {
    private static final String TAG = "PlanningPokerMain";

    private ScoringFragment mScoringFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "App starting");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container);

        mScoringFragment = ScoringFragment.newInstance();

        if (findViewById(R.id.fragmentContainer) != null) {
            if (savedInstanceState != null) {
                mScoringFragment = (ScoringFragment)
                        getSupportFragmentManager().findFragmentByTag("scoring_fragment");
                Log.d(TAG, "Fragment loaded.");
            }
            else{
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragmentContainer, mScoringFragment, "scoring_fragment").commit();
                Log.d(TAG, "Fragment added.");
            }
        }
        else{
            Log.d(TAG, "Fragment container not found.");
        }
    }

    public void replaceFragment(int fragment){
    }
}
