package com.android.lewis.vitt.matthew.caloriequickcount;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.UUID;

public class FoodDetailActivity extends AppCompatActivity {

    AppSingleton singleton;
    Foods foods;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.foodDetailFragmentContainer);

        if (fragment == null){
            UUID foodItemId = (UUID)getIntent().getSerializableExtra("foodId");
            fragment = FoodDetailFragment.newInstance(foodItemId);
            singleton = AppSingleton.get(this);
            foods = singleton.getFood(foodItemId);
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.foodDetailFragmentContainer, fragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onBackPressed() {
        //used to act live the up button in toolbar
        //this one requires android:launchMode="singleTop" in manifest

        if (foods.getName().equals("Food") || foods.getCalories() == 0) {
            AppSingleton.get(this).removeFood(foods.getID());
        }
        NavUtils.navigateUpFromSameTask(this);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                if (foods.getName().equals("Food") || foods.getCalories() == 0) {
                    AppSingleton.get(this).removeFood(foods.getID());
                }
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
