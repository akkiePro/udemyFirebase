package com.example.udemyfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signupActivity extends AppCompatActivity {
    private EditText email,password;
    private Button signup;
    private FirebaseAuth fa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        signup=findViewById(R.id.signup);
        fa = FirebaseAuth.getInstance();
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String e_text=email.getText().toString();
                String p_text=password.getText().toString();
                if(TextUtils.isEmpty(e_text)||TextUtils.isEmpty(p_text)){
                    Toast.makeText(signupActivity.this, "empty credentials", Toast.LENGTH_SHORT).show();
                }else if(p_text.length()<6)
                {
                    Toast.makeText(signupActivity.this, "password lengh less then 6 characters", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    registeruser(e_text,p_text);
                }


            }
        });
    }

    private void registeruser(String e_text, String p_text) {
        fa.createUserWithEmailAndPassword(e_text,p_text).addOnCompleteListener(signupActivity.this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(signupActivity.this, "Registering user successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(signupActivity.this,HomeActivity.class));
                }else{
                    Toast.makeText(signupActivity.this, "Registering user unsuccessful", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}