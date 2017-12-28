package com.android.lewis.vitt.matthew.caloriequickcount;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
public class FoodDetailFragment extends Fragment {

    private EditText etFoodName, etCalories;
    private Button btnSubmit;

    AppSingleton singleton;

    Foods food;
    Dates date;

    UUID dateId, foodId;

    public static FoodDetailFragment newInstance(UUID foodId) {
        Bundle args = new Bundle();
        args.putSerializable("foodId", foodId);
        FoodDetailFragment fragment = new FoodDetailFragment();
        fragment.setArguments(args);

        return fragment;
    }

    public FoodDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        foodId = (UUID) getActivity().getIntent().getSerializableExtra("foodId");
        dateId = (UUID) getActivity().getIntent().getSerializableExtra("id");
        food = AppSingleton.get(getActivity()).getFood(foodId);
        date = AppSingleton.get(getActivity()).getDate(dateId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_food_detail, container, false);

        getActivity().setTitle(getString(R.string.enter_info));

        etFoodName = (EditText) view.findViewById(R.id.foodDetailFoodName);
        etCalories = (EditText) view.findViewById(R.id.foodDetailCalories);
        btnSubmit = (Button) view.findViewById(R.id.submitFoodBtn);
        singleton = AppSingleton.get(getActivity());


        if (!food.getName().equals("Food")) {
            etFoodName.setText(food.getName());
        }

        if (food.getCalories() != 0) {
            etCalories.setText(String.valueOf(food.getCalories()));
        }


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etFoodName.getText().toString().equals("") || etCalories.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "Please complete both items!", Toast.LENGTH_SHORT).show();
                } else {
                    food.setName(etFoodName.getText().toString());
                    food.setCalories(Integer.parseInt(etCalories.getText().toString()));
                    singleton.updateFood(food, date);
                    getActivity().finish();
                }
            }
        });


        return view;
    }


}
