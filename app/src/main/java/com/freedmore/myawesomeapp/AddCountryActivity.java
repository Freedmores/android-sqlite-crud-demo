package com.freedmore.myawesomeapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AddCountryActivity extends Activity implements OnClickListener {

    private Button addBtn;
    private EditText CountryNameEditText;
    private EditText CurrencyEditText;
    private EditText PopulationEditText;

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Add Record");

        setContentView(R.layout.activity_add_country);

        CountryNameEditText = findViewById(R.id.name_edittext);
        CurrencyEditText = findViewById(R.id.currency_edittext);
        PopulationEditText = findViewById(R.id.population_edittext);

        addBtn = (Button) findViewById(R.id.add_record);

        dbManager = new DBManager(this);
        dbManager.open();
        addBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_record:

                final String name = CountryNameEditText.getText().toString();
                final String currency = CurrencyEditText.getText().toString();
                final double population = Double.parseDouble(PopulationEditText.getText().toString());

                dbManager.insert(name, currency,population);

                Intent main = new Intent(AddCountryActivity.this, MainActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(main);
                break;
        }
    }

}
