package com.example.youoweme;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.youoweme.object_model.User;


public class SettingsFragment extends Fragment {
    HomepageActivity homepageactivity;

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        Button ProfileButton = view.findViewById(R.id.ProfileButton);
        homepageactivity = (HomepageActivity) getActivity();

        //set textview username
        TextView tvSettingsUsername = view.findViewById(R.id.TVSettingUsername);
        tvSettingsUsername.setText(User.getUsername());


        ProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent (getActivity(), Profile.class);
                startActivity(i);
            }
        });

        Button PrivacyButton = view.findViewById(R.id.PrivacyButton);
        PrivacyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent (getActivity(), PrivacyPage.class);
                startActivity(i);
            }
        });

        Button AboutUsButton = view.findViewById(R.id.AboutUsButton);
        AboutUsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent (getActivity(), AboutPage.class);
                startActivity(i);
            }
        });

        Button LogoutButton = view.findViewById(R.id.LogOutButton);
        LogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent (getActivity(), LoginActivity.class);
                startActivity(i);
            }
        });


        // Inflate the layout for this fragment
        return view;
    }
}