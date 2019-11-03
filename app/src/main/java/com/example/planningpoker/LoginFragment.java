package com.example.planningpoker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;

public class LoginFragment extends Fragment {

    private EditText mEmail, mPassword;
    private Button btnLogin;
    FirebaseAuth mFireBaseAuth;
    private TextView mRegister;

    public LoginFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        btnLogin = view.findViewById(R.id.btnLogin);
        mFireBaseAuth = FirebaseAuth.getInstance();
        mEmail = view.findViewById(R.id.et_email_login);
        mPassword = view.findViewById(R.id.et_password_login);
        mRegister = view.findViewById(R.id.tv_register);

        /*btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_place, new SignUpFragment(),null).commit();
            }
        });
        return view;
    }
}
