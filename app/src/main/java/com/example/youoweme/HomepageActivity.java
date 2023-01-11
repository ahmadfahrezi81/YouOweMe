package com.example.youoweme;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.youoweme.databinding.ActivityHomePageBinding;
import com.example.youoweme.friend_recycler_view.FriendFragment;


public class HomepageActivity extends AppCompatActivity {

    ActivityHomePageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());

        binding.bottomNavigationView.setOnItemReselectedListener(item -> {

            switch (item.getItemId()) {

                case R.id.home:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.friend:
                    replaceFragment(new FriendFragment());
                    break;
                case R.id.add:
                    replaceFragment(new AddFragment());
                    break;
                case R.id.history:
                    replaceFragment(new HistoryFragment());
                    break;
            }


            return;
        });
    }

    private void replaceFragment (Fragment fragment){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

    private void onNavigationItemReselected (MenuItem item){
    }
}