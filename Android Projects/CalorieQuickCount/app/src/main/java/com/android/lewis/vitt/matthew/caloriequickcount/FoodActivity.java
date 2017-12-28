package com.android.lewis.vitt.matthew.caloriequickcount;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FoodActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);


        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.foodFragmentContainer);

        if (fragment == null) {
            fragment = new FoodListFragment();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.foodFragmentContainer, fragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onBackPressed() {
        //used to act live the up button in toolbar
        NavUtils.navigateUpFromSameTask(this);

    }
}
