package swordgit.canadianmortgagecalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    /*Attribute that stored user input and system out
      value : the value of the real estate
      downPercent : the percentage of down payment
      year : the amortization period in terms of year
    */
    private double value;
    private double downPercent;
    private double year;
    private double ratePercent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button cal = (Button) findViewById(R.id.buttonCalculate);
        /*when the button is clicked, the input will be passed to a EditText variable,
          and then parse the text from String to double and store to corresponding variable
         */
        cal.setOnClickListener
                (
                        new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View v)
                            {
                                EditText yearOfAmortization = (EditText)findViewById(R.id.year);
                                EditText valueOfEstate= (EditText)findViewById(R.id.value);
                                EditText down = (EditText)findViewById(R.id.downPayment);
                                EditText rate = (EditText)findViewById(R.id.rate);
                                value = Double.parseDouble(valueOfEstate.getText().toString());
                                ratePercent = Double.parseDouble(rate.getText().toString());
                                year = Double.parseDouble(yearOfAmortization.getText().toString());
                                downPercent = Double.parseDouble(down.getText().toString());
                                //passing the attributes as argument to a the calculator to process
                                Calculator calculator = new Calculator(value, downPercent, ratePercent, year);
                                Intent i = new Intent(MainActivity.this, ResultActivity.class);
                                //passing the list of payment stream to ResultActivity class
                                i.putStringArrayListExtra("list", calculator.getPaymentList());
                                MainActivity.this.startActivity(i);


                            }
                        }
                );

    }
}
