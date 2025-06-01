package com.hidayahhakimi.dividaisy;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText investedAmount, dividendRate, monthsInvested;
    private TextView monthlyResult, totalResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        investedAmount = findViewById(R.id.investedAmount);
        dividendRate = findViewById(R.id.dividendRate);
        monthsInvested = findViewById(R.id.monthsInvested);
        monthlyResult = findViewById(R.id.monthlyResult);
        totalResult = findViewById(R.id.totalResult);
        Button calculateButton = findViewById(R.id.calculateButton);

        calculateButton.setOnClickListener(v -> calculateDividend());
    }

    private void calculateDividend() {
        try {
            double amount = Double.parseDouble(investedAmount.getText().toString());
            double rate = Double.parseDouble(dividendRate.getText().toString());
            int months = Integer.parseInt(monthsInvested.getText().toString());

            if (months < 1 || months > 12) {
                Toast.makeText(this, "Months must be 1-12", Toast.LENGTH_SHORT).show();
                return;
            }

            double monthlyDividend = (rate / 100 / 12) * amount;
            double totalDividend = monthlyDividend * months;

            DecimalFormat df = new DecimalFormat("#.00");
            monthlyResult.setText(String.format("Monthly Dividend: RM%s", df.format(monthlyDividend)));
            totalResult.setText(String.format("Total Dividend: RM%s", df.format(totalDividend)));

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter valid numbers", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_about) {
            startActivity(new Intent(this, AboutActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}