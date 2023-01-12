package com.example.youoweme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    EditText email, password, passwordConfirmation;
    Button btnRegistration;
    String emailPattern = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    ProgressDialog progressDialog;

    FirebaseAuth fbAuth;
    FirebaseUser myUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        //to go to login page
        TextView clickableLoginTxt = findViewById(R.id.clickableLoginTxt);
        clickableLoginTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        //Registration set up
        email = findViewById(R.id.ETEmail);
        password = findViewById(R.id.ETPassword);
        passwordConfirmation = findViewById(R.id.ETPasswordConfirmation);
        btnRegistration = findViewById(R.id.btnRegistration);

        //loading pop up
        progressDialog = new ProgressDialog(this);

        //FB registration logic
        fbAuth = FirebaseAuth.getInstance();
        myUser = fbAuth.getCurrentUser();

        btnRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PerformAuth();
            }
        });
    }

    private void PerformAuth(){
        if(!email.getText().toString().matches(emailPattern)){
            email.setError("Enter correct Email");
        } else if(password.getText().toString().isEmpty() || password.getText().toString().length() < 6){
            password.setError("Enter Proper Password");
        } else if (!password.getText().toString().equals(passwordConfirmation.getText().toString())){
            passwordConfirmation.setError("Password doesn't match");
        } else{
            progressDialog.setMessage("Please wait while registration...");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            fbAuth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        progressDialog.dismiss();
                        Toast.makeText(RegisterActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();

                        //send user to homeActivity
                        startActivity(new Intent(RegisterActivity.this, HomepageActivity.class));

                    } else{
                        progressDialog.dismiss();
                        Toast.makeText(RegisterActivity.this, "" + task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}