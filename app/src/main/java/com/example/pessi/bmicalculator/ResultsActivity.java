package com.example.pessi.bmicalculator;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        setUpToolbar();
        setUpFAB();
        processIncomingData();

    }

    private void processIncomingData() {
        //create the references to each of the text views in xml

        TextView tv_height = findViewById(R.id.tv_height);
        TextView tv_weight = findViewById(R.id.tv_weight);
        TextView tv_bmi = findViewById(R.id.tv_bmi);
        TextView tv_bmi_group = findViewById(R.id.tv_bmi_group);

        //bring in the bundle from the intent object
        Bundle incomingData = getIntent().getExtras();
        if(incomingData!=null)
        {
            String JSON = incomingData.getString("BMI");
            BMICalc mCurrentBMI = BMICalc.getObjectFromJSONString(JSON);

            //store locally the values from the bundle
            double height = mCurrentBMI.getHeight();
            double weight = mCurrentBMI.getWeight();
            double bmi = mCurrentBMI.getBMI();
            String bmi_group = mCurrentBMI.getBMIGroup();

            //append each of the values into the respective text views
            tv_height.setText("Height: " + height);
            tv_weight.setText("Weight: " + weight);
            tv_bmi.setText("BMI: " + bmi);
            tv_bmi_group.setText("BMI Group: ".concat(bmi_group));
        }





    }

    private void setUpToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setUpFAB() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}
