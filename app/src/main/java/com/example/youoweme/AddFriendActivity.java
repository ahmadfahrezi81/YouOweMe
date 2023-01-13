package com.example.youoweme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.youoweme.R;

public class AddFriendActivity  extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);
        Button btnsettleup = findViewById(R.id.button2);
        btnsettleup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                startActivity(new Intent(AddFriendActivity.this, SettleUpFragment.class));
            }
        });

    }
}
