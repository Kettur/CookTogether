package com.example.cooktogether.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.cooktogether.R;
import com.example.cooktogether.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        mAuth = FirebaseAuth.getInstance();
        View v = mBinding.getRoot();
        mBinding.RegButton.setOnClickListener(v1 -> {
            startActivity(new Intent(MainActivity.this, Register_User.class));
        }); 
        
        mBinding.LogButton.setOnClickListener(v1 -> {
            logUser();
        });

        setContentView(v);
    }

    private void logUser() {
        String email = "123456@mail.ru";
        String password = "123456";
        //String email = mBinding.mail.getText().toString().trim();
        //String password = mBinding.password.getText().toString().trim();

        if (email.isEmpty()){
            mBinding.mail.setError("Email is requird!");
            mBinding.mail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            mBinding.mail.setError("Please, provide a valid email");
            mBinding.mail.requestFocus();
            return;
        }
        if (password.isEmpty()){
            mBinding.password.setError("Password is requird!");
            mBinding.password.requestFocus();
            return;
        }

        mBinding.progressCircular.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    //if (user.isEmailVerified()){
                        startActivity(new Intent(MainActivity.this, NavigateActivity.class));
                    //}
//                    else{
//                        user.sendEmailVerification();
//                        Toast.makeText(MainActivity.this, "Chek your email to verify your account!", Toast.LENGTH_LONG).show();
//                    }
                }
                else{
                    Toast.makeText(MainActivity.this, "Failed to login! Try again", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
