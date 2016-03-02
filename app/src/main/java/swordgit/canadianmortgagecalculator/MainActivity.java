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
    private double value;
    private double downPercent;
    private double year;
    private double ratePercent;
    private ArrayList<String> al = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button cal = (Button) findViewById(R.id.buttonCalculate);
        cal.setOnClickListener
                (
                        new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View v)
                            {
                                EditText y = (EditText)findViewById(R.id.year);
                                EditText val = (EditText)findViewById(R.id.value);
                                EditText down = (EditText)findViewById(R.id.downPayment);
                                EditText rate = (EditText)findViewById(R.id.rate);
                                value = Double.parseDouble(val.getText().toString());
                                ratePercent = Double.parseDouble(rate.getText().toString());
                                year = Double.parseDouble(y.getText().toString());
                                downPercent = Double.parseDouble(down.getText().toString());
                                Calculator calculator = new Calculator(value, downPercent, ratePercent, year);
                                Intent i = new Intent(MainActivity.this, ResultActivity.class);
                                i.putStringArrayListExtra("list", calculator.getPaymentList());
                                MainActivity.this.startActivity(i);


                            }
                        }
                );

    }
}
