package com.freedmore.myawesomeapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ModifyCountryActivity extends Activity implements OnClickListener {

    private EditText CountryNameEditText;
    private EditText CurrencyEditText;
    private EditText PopulationEditText;
    private Button updateBtn, deleteBtn;

    private long _id;

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Modify Record");

        setContentView(R.layout.activity_modify_country);

        dbManager = new DBManager(this);
        dbManager.open();

        CountryNameEditText = findViewById(R.id.name_edittext);
        CurrencyEditText = findViewById(R.id.currency_edittext);
        PopulationEditText = findViewById(R.id.population_edittext);


        updateBtn = findViewById(R.id.btn_update);
        deleteBtn = findViewById(R.id.btn_delete);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("country_name");
        String currency = intent.getStringExtra("currency");
        String population = intent.getStringExtra("population");


        _id = Long.parseLong(id);



        CountryNameEditText.setText(name);
        CurrencyEditText.setText(currency);
        PopulationEditText.setText(population);


        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update:
                String name = CountryNameEditText.getText().toString();
                String currency = CurrencyEditText.getText().toString();
                double population = Double.parseDouble(PopulationEditText.getText().toString());


                dbManager.update(_id, name, currency,population);
                this.returnHome();
                break;

            case R.id.btn_delete:
                dbManager.delete(_id);
                this.returnHome();
                break;
        }
    }

    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(), MainActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }
}
