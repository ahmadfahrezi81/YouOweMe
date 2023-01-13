package com.example.youoweme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.youoweme.object_model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.api.Logging;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    EditText username, email, phone, password, passwordConfirmation;
    Button btnRegistration;
    String emailPattern = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    ProgressDialog progressDialog;

    FirebaseAuth fbAuth;
    FirebaseUser myUser;
    FirebaseFirestore db;

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

        //this is important to setup FB
        db = FirebaseFirestore.getInstance();

        //Registration set up
        username = findViewById(R.id.ETUsername);
        email = findViewById(R.id.ETEmail);
        phone = findViewById(R.id.ETPhone);
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

//                Map<String, Object> user = new HashMap<>();
//                user.put("username", username.getText().toString());
//                user.put("email", email.getText().toString());
//                user.put("phone", phone.getText().toString());
//
//                db.collection("user").document(fbAuth.getUid()).set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        Toast.makeText(RegisterActivity.this, "Adding user successful", Toast.LENGTH_SHORT).show();
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(RegisterActivity.this, "Adding user failed", Toast.LENGTH_SHORT).show();
//                    }
//                });

                //send data to Firestore here
//                db.collection("user").add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
//                        Toast.makeText(RegisterActivity.this, "Adding user successful", Toast.LENGTH_SHORT).show();
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(RegisterActivity.this, "Adding user failed", Toast.LENGTH_SHORT).show();
//                    }
//                });
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

                        Map<String, Object> user = new HashMap<>();
                        user.put("username", username.getText().toString());
                        user.put("email", email.getText().toString());
                        user.put("phone", phone.getText().toString());

                        User.setEmail(email.getText().toString());
                        User.setUsername(username.getText().toString());
                        User.setPhone(phone.getText().toString());
                        User.setUuid(fbAuth.getUid());

                        db.collection("user").document(fbAuth.getUid()).set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(RegisterActivity.this, "Adding user successful", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(RegisterActivity.this, "Adding user failed", Toast.LENGTH_SHORT).show();
                            }
                        });

                    } else{
                        progressDialog.dismiss();
                        Toast.makeText(RegisterActivity.this, "" + task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}