package swordgit.canadianmortgagecalculator;

import android.util.Log;

import java.text.DecimalFormat;
import java.util.ArrayList;
import static java.lang.Math.pow;

public class Calculator
{
    /* Attributes for calculation
       PERCENT_TO_DECIMAL : constant for converting given percentage to decimal
       COMPOUND_FACTOR : compound factor for converting nominal rate to effective monthly rate,
                         since Canada's mortgage compounds semi-annually it is 2 divided by 12
                         becomes 1 / 6
       down_payment_amount : the exact down payment the borrower pays
       effectiveRate : the effective monthly rate converted from the nominal rate given by bank
       principal : the principal portion of the payment
       interest : the interest portion of the payment
       payment : the payment that the borrower pays monthly
       rate_in_decimal : the interest rate converted from percentage to decimal
       year : the amortization period in terms of year
       month : a counting variable indicating which month the payment info is belong to
       paymentList : a list that stored the String presentation of the payment stream
     */
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

    //constructor which stores the amortization period and calculate the needed number for calculation
    //then invoke the calculation
    public Calculator(double value, double downPayment, double rate, double period)
    {
        year = period;
        rate_in_decimal = rate / PERCENT_TO_DECIMAL;
        down_payment_amount = (downPayment / PERCENT_TO_DECIMAL) * value;
        mortgageAmount = value - down_payment_amount;
        paymentList = new ArrayList<String>();
        this.calculate();
    }

    /*
        the formula of effective rate is (1+(r/m))^(m/f) -1, where r is the interest rate and m is
        the current compounding period

        the formula for calculating payment is pmt = (PV * r) / (1-(1+r)^N)
        PV is the present value of the mortgage r is the interest rate, and N is the period
     */
    private void calculate()
    {
        effectiveRate = pow(1 + rate_in_decimal / 2, COMPOUND_FACTOR) - 1;
        payment = (effectiveRate * mortgageAmount) / (1 - pow(1.0 + effectiveRate, -12.0 * year));

        for(month = 1; month <= year * 12.0; month++) //convert from year to month, that is why year needs to times 12
        {
            if(mortgageAmount < payment)
            {

                interest = mortgageAmount * effectiveRate;
                payment = interest + mortgageAmount;
                principal = payment - interest;
                mortgageAmount -= principal;

            }
            else
            {
                interest = mortgageAmount * effectiveRate;
                principal = payment - interest;
                mortgageAmount -= principal;
            }
            String statement;
            statement = String.format("\t%5.5s\t", String.valueOf(month)) + String.format("\t%10.2f\t", payment)
                                    + String.format("\t%8.2f\t", interest) + String.format("\t%10.2f\t", principal)
                                    + String.format("\t%13.2f", mortgageAmount);
            paymentList.add(statement);
        }

    }

    public ArrayList<String> getPaymentList()
    {
        return paymentList;
    }

}
