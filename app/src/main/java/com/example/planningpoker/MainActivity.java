package com.example.planningpoker;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements
        ScoringFragment.OnScoringFragmentInteractionListener {
    private static final String TAG = "PlanningPokerMain";

    private ScoringFragment mScoringFragment;
    private Button btnLogin;
    Fragment fragment;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        Log.d(TAG, "App starting");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new ScoringFragment();
                FragmentManager mFragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_place,fragment).commit();

                /*mScoringFragment = ScoringFragment.newInstance();

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
                }*/
            }
        });

    }



    public void replaceFragment(int fragment){
    }
}
