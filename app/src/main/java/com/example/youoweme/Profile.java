package com.example.youoweme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.youoweme.object_model.User;

public class Profile extends AppCompatActivity {
    TextView username, phone, email;
    Button editProfileBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //set profile image
        TextView tvUsernameUp = findViewById(R.id.titleName);
        username = findViewById(R.id.tvProfileUsername);
        email = findViewById(R.id.tvProfilemail);
        phone = findViewById(R.id.tvProfilePhone);

        tvUsernameUp.setText(User.getUsername());
        username.setText(User.getUsername());
        email.setText(User.getEmail());
        phone.setText(User.getPhone());

        editProfileBtn = (Button)findViewById(R.id.editButton);

        editProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (Profile.this,EditProfile.class);
                startActivity(intent);
            }
        });
    }
}