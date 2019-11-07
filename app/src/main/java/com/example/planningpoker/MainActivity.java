package com.example.planningpoker;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "PlanningPokerMain";

    public static FragmentManager fragmentManager;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        Log.d(TAG, "App starting");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();

        if (findViewById(R.id.fragment_place)!=null){

            if (savedInstanceState != null){
                return;
            }

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            LoginFragment loginFragment = new LoginFragment();
            fragmentTransaction.add(R.id.fragment_place, loginFragment, null);
            fragmentTransaction.commit();
        }
    }

}