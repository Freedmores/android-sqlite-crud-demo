package com.freedmore.myawesomeapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private ArrayList<Country> dataSet;
    private Context context;

    public CustomAdapter(ArrayList<Country> dataSet, Context context) {
        this.dataSet = dataSet;
        this.context = context;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName;
        TextView textViewCurrency;
        TextView textViewPopulation;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewName = itemView.findViewById(R.id.textViewName);
            this.textViewCurrency = itemView.findViewById(R.id.textViewCurrency);
            this.textViewPopulation = itemView.findViewById(R.id.textViewPopulation);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cards_layout, parent, false);

       // view.setOnClickListener(MainActivity.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        final TextView textViewName = holder.textViewName;
        final TextView textViewCurrency = holder.textViewCurrency;
        final TextView textViewPopulation = holder.textViewPopulation;

        textViewName.setText(dataSet.get(listPosition).getName());
        textViewCurrency.setText(dataSet.get(listPosition).getCurrency());
        textViewPopulation.setText(String.valueOf(dataSet.get(listPosition).getPopulation()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent toModify = new Intent(v.getContext(),ModifyCountryActivity.class);
                toModify.putExtra("id",String.valueOf(dataSet.get(listPosition).getId()));
                toModify.putExtra("country_name",textViewName.getText().toString());
                toModify.putExtra("currency",textViewCurrency.getText().toString());
                toModify.putExtra("population",textViewPopulation.getText().toString());

             //   toModify.putExtra();
                context.startActivity(toModify);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}


