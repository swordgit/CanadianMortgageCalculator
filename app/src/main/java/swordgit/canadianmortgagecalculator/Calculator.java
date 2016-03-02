package swordgit.canadianmortgagecalculator;

import android.util.Log;

import java.text.DecimalFormat;
import java.util.ArrayList;
import static java.lang.Math.pow;

public class Calculator
{
    private final double PERCENT_TO_DECIMAL = 100;
    private final double COMPOUND_FACTOR = (double)1 / (double)6;
    private double down_payment_amount;
    private double effectiveRate;
    private double mortgageAmount;
    private double principal;
    private double interest;
    private double payment;
    private double rate_in_decimal;
    private double year;
    private int month;
    private ArrayList<String> paymentList;

    public Calculator(double value, double downPayment, double rate, double period)
    {
        year = period;
        rate_in_decimal = rate / PERCENT_TO_DECIMAL;
        down_payment_amount = (downPayment / PERCENT_TO_DECIMAL) * value;
        mortgageAmount = value - down_payment_amount;
        paymentList = new ArrayList<String>();
        Log.v("Calculator", String.valueOf(year));
        Log.v("Calculator", String.valueOf(rate_in_decimal));
        Log.v("Calculator", String.valueOf(down_payment_amount));
        Log.v("Calculator", String.valueOf(mortgageAmount));
        this.calculate();
    }

    private void calculate()
    {
        effectiveRate = pow(1 + rate_in_decimal / 2, COMPOUND_FACTOR) - 1;
        payment = (effectiveRate * mortgageAmount) / (1 - pow(1.0 + effectiveRate, -12.0 * year));

        for(month = 1; month < year * 12.0; month++)
        {
            if(mortgageAmount < payment)
            {
                payment = mortgageAmount * effectiveRate + mortgageAmount;
                principal = payment - interest;
                mortgageAmount -= principal;
            }
            else
            {
                interest = mortgageAmount * effectiveRate;
                principal = payment - interest;
                mortgageAmount -= principal;
            }
         //   DecimalFormat formatInterest = new DecimalFormat("#.##");
         //   DecimalFormat formatPrincipal = new DecimalFormat("#.##");
         //   DecimalFormat formatMortgage = new DecimalFormat("#.##");
         //   formatMortgage.format(mortgageAmount);
         //   formatInterest.format(interest);
         //   formatPrincipal.format(principal);
            String statement;
            statement = "  " + month + "  " + payment + "  " + String.valueOf(interest) + "  "+
                    String.valueOf(principal) + "  " + String.valueOf(mortgageAmount);
            paymentList.add(statement);
        }

    }

    public ArrayList<String> getPaymentList()
    {
        return paymentList;
    }

}
