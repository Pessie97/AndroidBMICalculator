package com.example.pessi.bmicalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //model
    private BMICalc mBmiCalc;

    //view components
    private EditText mEditTextHeight, mEditTextWeight;
    //debug tag
    private final String TAG = "MAIN_ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupToolbar();
        setupFAB();

        mBmiCalc = new BMICalc();
        mEditTextHeight = findViewById(R.id.input_height);
        mEditTextWeight = findViewById(R.id.input_weight);
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }




    private void setupFAB() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            // new instance of a new anonymous class that implements the View.OnClickListener interface
            @Override
            public void onClick(View view) {
                //this method overrides the method from the interface that is necessary
                // copy into the model what the user typed in the EditTexts
                String height, weight;
                height = mEditTextHeight.getText().toString();
                weight = mEditTextWeight.getText().toString();
                Log.d(TAG,  "From EditText: " );
                Log.d(TAG,  "Height: " +height);
                Log.d(TAG,  "Weight: " +weight);

                try {
                    mBmiCalc.setHeight(Double.parseDouble(height));
                    mBmiCalc.setWeight(Double.parseDouble(weight));

                    Log.d(TAG, "From BMI Model: ");
                    Log.d(TAG, "Height: " + mBmiCalc.getHeight());
                    Log.d(TAG, "Weight: " + mBmiCalc.getWeight());
                    //then get from model the BMI for those numbers (height/weight)
                    double bmi = mBmiCalc.getBMI();
                    //output the BMI to the user

                    Snackbar.make(view, "BMI: " + bmi, Snackbar.LENGTH_LONG)
                            .setAction("More Details...", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //need to create intent object
                                    //intend to send data that needs to be launched
                                    Intent intent = new Intent(getApplicationContext(),
                                            ResultsActivity.class);
                                    //convert bmi object into a string
                                    String currentBMI_JSON = BMICalc.getJSONStringFromObject(mBmiCalc);
                                    // send into intent
                                    intent.putExtra("BMI", currentBMI_JSON);


                                    startActivity(intent);
                                }
                            })
                            .show();
                }
                catch (IllegalArgumentException  e){
                    //toast is another quick message to user
                    Toast.makeText(MainActivity.this,
                            "Error: Height and Weight must both be greater than 0",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
