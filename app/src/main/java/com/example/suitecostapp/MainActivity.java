package com.example.suitecostapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

//    Цены и скидки имеют соответствующие id в массивах
    private final int[] PRICES = new int[] {70, 25, 53, 19, 41};
    private final int[] DISCOUNT = new int[] {77, 37, 44, 0, 32};
    private final int BALANCE = 312;

    private TextView possibilityOutView;
    private TextView changeOutView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        possibilityOutView = findViewById(R.id.possibilityOut);
        changeOutView = findViewById(R.id.changeOut);

        float cost = calculateSuiteCost();

        if (findPossibility(BALANCE, cost)) {
            possibilityOutView.setText("Средств достаточно для покупки");
            changeOutView.setText("Сдача - " + countChange(BALANCE, cost) + " серебрянных монет");
        }
        else {
            possibilityOutView.setText("Средств недостаточно для покупки");
            changeOutView.setText("-");
        }
    }

    private float calculateSuiteCost() {
        float cost = 0f;

        int clothesNumber = PRICES.length;
        for (int i = 0; i < clothesNumber; i++) {
            cost += calculateDiscount(PRICES[i], DISCOUNT[i]);
        }

        return cost;
    }

    private float calculateDiscount(int price, int percent) {
        return (price * (100 - percent) / 100.0f);
    }

    private boolean findPossibility(int balance, float cost) {
        return (balance > cost);
    }

    private float countChange(int balance, float cost) {
        return (balance - cost);
    }
}