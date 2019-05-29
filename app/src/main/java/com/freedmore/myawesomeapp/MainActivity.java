package com.freedmore.myawesomeapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DBManager dbManager;
    private ArrayList<Country> dataList;

    //private ListView listView;
    private RecyclerView recyclerView;

    //private SimpleCursorAdapter adapter;
    private CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        dbManager = new DBManager(this);
        dbManager.open();
        dataList = (ArrayList<Country>) dbManager.getAllCountries();

        recyclerView = findViewById(R.id.my_recycler_view);
        adapter = new CustomAdapter(dataList,this);

        LinearLayoutManager lnmanager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(lnmanager);
        recyclerView.setHasFixedSize(true);


        recyclerView.setAdapter(adapter);
        Toast.makeText(this,String.valueOf(dataList.size()),Toast.LENGTH_LONG).show();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.add_record) {

            Intent add_mem = new Intent(this, AddCountryActivity.class);
            startActivity(add_mem);

        }
        return super.onOptionsItemSelected(item);
    }

}

