package com.example.week4daily4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.net.Uri;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements CelebrityListFragment.OnFragmentInteractionListener, CelebrityDetailsFragment.OnFragmentInteractionListener {
    FragmentManager fragmentManager;
    CelebrityListFragment celebrityListFragment;
    CelebrityDetailsFragment celebrityDetailsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();

        celebrityListFragment = CelebrityListFragment.newInstance("","");
        fragmentManager.beginTransaction().add(R.id.fgCelebrityList, celebrityListFragment).commit();

        celebrityDetailsFragment = CelebrityDetailsFragment.newInstance("", "");
        fragmentManager.beginTransaction().add(R.id.fgCelebrityDetails,celebrityDetailsFragment).commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void sendToActivity(String name, String job, String award, int position) {
        celebrityDetailsFragment.setPassedInput(name, job, award, position);
    }
}
