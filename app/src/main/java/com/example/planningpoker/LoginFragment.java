package com.example.planningpoker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginFragment extends Fragment {

    private EditText mEmail, mPassword;
    private Button btnLogin;
    private TextView mRegister;

    FirebaseAuth mFireBaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

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

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFireBaseAuth.getCurrentUser();
                if (mFirebaseUser != null){
                    Toast.makeText(getActivity(), "You are logged in", Toast.LENGTH_LONG).show();
                    MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_place, new ScoringFragment(),null).commit();
                }
                else{
                    Toast.makeText(getActivity(), "Please log in", Toast.LENGTH_LONG).show();
                }
            }
        };

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString();
                String password = mPassword.getText().toString();
                Database db = new Database();
                db.getTasks();
                login(email,password);
            }
        });

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_place, new SignUpFragment(),null).commit();
            }
        });

        return view;

    }

    public void login(String email, String password){
        if (email.isEmpty()){
            mEmail.setError("Please enter the email address");
            mEmail.requestFocus();
        }
        else if (password.isEmpty()){
            mPassword.setError("Please enter your password");
            mPassword.requestFocus();
        }
        else if (!(email.isEmpty()) && !(password.isEmpty())){
            mFireBaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()){
                        Toast.makeText(getActivity(), "Please check your email or password", Toast.LENGTH_LONG).show();
                    }
                    else{
                        MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_place, new ScoringFragment(),null).commit();
                    }
                }
            });
        }else{
            Toast.makeText(getActivity(), "Error occured", Toast.LENGTH_SHORT).show();
        }
    }
}
