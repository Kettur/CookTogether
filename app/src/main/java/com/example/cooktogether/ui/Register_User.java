package com.example.cooktogether.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.cooktogether.R;
import com.example.cooktogether.data.User_data;
import com.example.cooktogether.databinding.ActivityRegisterUserBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.FirebaseDatabase;

public class Register_User extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private ActivityRegisterUserBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        mAuth = FirebaseAuth.getInstance();

        mBinding = ActivityRegisterUserBinding.inflate(getLayoutInflater());
        mBinding.backButton.backB.setOnClickListener(v -> finish());

        mBinding.LogButton.setOnClickListener(v -> {
            registerUser();
        });
        setContentView(mBinding.getRoot());
    }

    private void registerUser() {
        String email = mBinding.mail.getText().toString().trim();
        String password = mBinding.password.getText().toString().trim();
        String password_again = mBinding.passwordAgain.getText().toString().trim();
        String Name = mBinding.name.getText().toString().trim();
        String Sername = mBinding.Secondname.getText().toString().trim();

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
        if (!password.equals(password_again)){
            mBinding.passwordAgain.setError("Passwords are not the same!");
            mBinding.passwordAgain.requestFocus();
            return;
        }
        if (Name.isEmpty()){
            mBinding.name.setError("Name is requird!");
            mBinding.name.requestFocus();
            return;
        }


        mBinding.progressCircular.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            if (!Sername.isEmpty()){
                                User_data user = new User_data(Name, Sername, email);
                                FirebaseDatabase.getInstance().getReference("User")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()){
                                                    Toast.makeText(Register_User.this, "User has been registered successfully!", Toast.LENGTH_LONG).show();
                                                    mBinding.progressCircular.setVisibility(View.GONE);
                                                }
                                                else{
                                                    Toast.makeText(Register_User.this, "Failed to register! Try again", Toast.LENGTH_LONG).show();
                                                    mBinding.progressCircular.setVisibility(View.GONE);
                                                }
                                            }
                                        });
                            }
                            else{
                                User_data user = new User_data(Name, email);
                                FirebaseDatabase.getInstance().getReference("User")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()){
                                                    Toast.makeText(Register_User.this, "User has been registered successfully!", Toast.LENGTH_LONG).show();
                                                    mBinding.progressCircular.setVisibility(View.GONE);
                                                    finish();
                                                }
                                                else{
                                                    Toast.makeText(Register_User.this, "Failed to register! Try again", Toast.LENGTH_LONG).show();
                                                    mBinding.progressCircular.setVisibility(View.GONE);
                                                }
                                            }
                                        });
                            }
                        }
                        else{
                            
                            Toast.makeText(Register_User.this, "Failed to register in base! Try again", Toast.LENGTH_LONG).show();
                            mBinding.progressCircular.setVisibility(View.GONE);
                            FirebaseAuthException e = (FirebaseAuthException )task.getException();
                            Log.e("LoginActivity", "Failed Registration", e);
                            
                        }
                    }
                });

    }
}
