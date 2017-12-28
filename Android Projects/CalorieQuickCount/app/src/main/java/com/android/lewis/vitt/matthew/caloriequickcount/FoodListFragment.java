package com.android.lewis.vitt.matthew.caloriequickcount;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
public class FoodListFragment extends Fragment {

    RecyclerView recyclerView;
    FoodListAdapter adapter;
    AppSingleton outerSingleton;
    private Dates dates;


    public FoodListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        Intent intent = getActivity().getIntent();
        UUID dateId = (UUID) intent.getSerializableExtra("id");
        dates = AppSingleton.get(getActivity()).getDate(dateId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_food_list, container, false);


        getActivity().setTitle(dates.getDate());

        recyclerView = (RecyclerView) view.findViewById(R.id.foodRecyclerListView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        outerSingleton = AppSingleton.get(getActivity());

        updateInterface();

        return view;
    }


    private void updateInterface() {
        AppSingleton singleton = AppSingleton.get(getActivity());
        //add in dates as an argument to filter out the foods for each day
        List<Foods> foods = singleton.getFoods(dates);

        //commented out this code because it causes issues with refreshing foodlist after hitting submit button
        // if (adapter == null){
        adapter = new FoodListAdapter(foods);
        recyclerView.setAdapter(adapter);
        //}else {
        //     adapter.setFoods(foods);
        adapter.notifyDataSetChanged();
        //}
    }

    @Override
    public void onResume() {
        super.onResume();
        updateInterface();


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.foods_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addFoodBtn:
                Foods food = new Foods();
                outerSingleton.addFood(food, dates);
                Intent intent = new Intent(getActivity(), FoodDetailActivity.class);
                intent.putExtra("foodId", food.getID());
                intent.putExtra("id", dates.getID());
                startActivity(intent);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


    private class FoodListHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView foodNameTxt, foodCalTxt;
        private Foods food;


        public FoodListHolder(View itemView) {
            super(itemView);

            foodNameTxt = (TextView) itemView.findViewById(R.id.foodListNamesTxt);
            foodCalTxt = (TextView) itemView.findViewById(R.id.foodListItemCalTxt);

            itemView.setOnClickListener(this);
        }

        public void bindFoodInfo(Foods food) {
            this.food = food;
            foodNameTxt.setText(food.getName());
            foodCalTxt.setText(String.valueOf(food.getCalories()));
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), FoodDetailActivity.class);
            intent.putExtra("foodId", food.getID());
            intent.putExtra("id", dates.getID());
            startActivity(intent);
        }
    }


    private class FoodListAdapter extends RecyclerView.Adapter<FoodListHolder> {

        private FoodListHolder holder;
        private List<Foods> foods;

        public FoodListAdapter(List<Foods> foods) {
            this.foods = foods;
        }

        @Override
        public FoodListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.food_list_item, parent, false);

            holder = new FoodListHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(FoodListHolder holder, int position) {
            Foods food = foods.get(position);
            holder.bindFoodInfo(food);
        }

        @Override
        public int getItemCount() {
            return foods.size();
        }

        public void setFoods(List<Foods> foods) {
            this.foods = foods;
        }
    }

}
