package com.example.devs.openweather.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.devs.openweather.R;
import com.example.devs.openweather.model.City;

import java.util.List;

public class CitiesRecycleViewAdapter extends RecyclerView.Adapter<CitiesRecycleViewAdapter.DataObjectHolder> {
        private List<City> cities;
        private static MyClickListener myClickListener;

        public void setOnItemClickListener(MyClickListener myClickListener) {
            this.myClickListener = myClickListener;
        }

        public CitiesRecycleViewAdapter(final List<City> cities) {
            this.cities = cities;
        }

        @Override
        public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_row, parent, false);

            DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
            return dataObjectHolder;
        }

        @Override
        public void onBindViewHolder(DataObjectHolder holder, int position) {
            holder.labelCityName.setText(cities.get(position).getName());
        }

        public City getItem(int index) {
            return cities.get(index);
        }

        @Override
        public int getItemCount() {
            return cities.size();
        }

        public interface MyClickListener {
            void onItemClick(int position, View v);
        }

        public static class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            TextView labelCityName;

            public DataObjectHolder(View itemView) {
                super(itemView);
                labelCityName = (TextView) itemView.findViewById(R.id.textViewCityName);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                 myClickListener.onItemClick(getPosition(), v);
            }
        }
    }
