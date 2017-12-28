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
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DateListFragment extends Fragment {

    RecyclerView recyclerView;
    DateListAdapter adapter;
    AppSingleton outerSingleton;

    public DateListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_date_list, container, false);

        getActivity().setTitle(getResources().getString(R.string.date_list_title));

        recyclerView = (RecyclerView) view.findViewById(R.id.dateRecyclerListView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        outerSingleton = AppSingleton.get(getActivity());

        updateInterface();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        adapter.notifyDataSetChanged();

    }

    private void updateInterface() {
        AppSingleton singleton = AppSingleton.get(getActivity());
        List<Dates> dates = singleton.getDates();

        if (adapter == null) {
            adapter = new DateListAdapter(dates);
            recyclerView.setAdapter(adapter);
        } else {
            adapter.setDates(dates);
            adapter.notifyDataSetChanged();
        }


    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.dates_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addDateBtn:
                //check if date is already added to database
                if (outerSingleton.IsTodaysDateAdded(new String[]{new SimpleDateFormat("MM/dd/yy").format(new Date())})) {
                    Toast.makeText(getActivity(), "Today's Date already Added!", Toast.LENGTH_SHORT).show();
                } else {
                    Dates date = new Dates();
                    outerSingleton.addDate(date);
                    Intent intent = new Intent(getActivity(), FoodActivity.class);
                    intent.putExtra("id", date.getID());
                    startActivity(intent);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


    private class DateListHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView dateTxt, totalCalTxt;
        private Dates date;

        public DateListHolder(View itemView) {
            super(itemView);

            dateTxt = (TextView) itemView.findViewById(R.id.dateListDatesTxt);
            totalCalTxt = (TextView) itemView.findViewById(R.id.dateListTotalCalsTxt);

            itemView.setOnClickListener(this);
        }

        public void bindDateInfo(Dates date) {
            this.date = date;
            dateTxt.setText(date.getDate());
            totalCalTxt.setText(String.valueOf(AppSingleton.get(getActivity()).getTotalCalories(date)));
        }


        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), FoodActivity.class);
            intent.putExtra("id", date.getID());
            startActivity(intent);
        }
    }


    private class DateListAdapter extends RecyclerView.Adapter<DateListHolder> {

        private DateListHolder holder;
        private List<Dates> dates;

        public DateListAdapter(List<Dates> dates) {
            this.dates = dates;
        }

        @Override
        public DateListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.date_list_item, parent, false);

            holder = new DateListHolder(view);

            return holder;
        }

        @Override
        public void onBindViewHolder(DateListHolder holder, int position) {
            Dates date = dates.get(position);
            holder.bindDateInfo(date);
        }

        @Override
        public int getItemCount() {

            return dates.size();
        }

        public void setDates(List<Dates> dates) {
            this.dates = dates;
        }
    }

}
