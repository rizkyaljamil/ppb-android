package com.example.ppb_menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {
    TextView name, price, desc, resCount, totalHarga;
    ImageView img, btnBack;
    ImageButton btnPlus, btnMinus;
    Button btnOrder;
    SharedPreferences preferences;
    int mCount = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        preferences = getSharedPreferences("food_data", 0);

        name = findViewById(R.id.titleFood);
        price = findViewById(R.id.priceFood);
        desc = findViewById(R.id.descFood);
        img = findViewById(R.id.imageFood);

        btnMinus = findViewById(R.id.reduceCount);
        btnPlus = findViewById(R.id.addCount);
        resCount = findViewById(R.id.resultsCount);
        totalHarga = findViewById(R.id.totalHargaMakanan);
        btnOrder = findViewById(R.id.btnPesan);
        btnBack = findViewById(R.id.iv_back_toolbar);

        Intent intent = getIntent();
        if (intent != null){
            String titleFood = intent.getStringExtra("titleFood");
            String priceFood = intent.getStringExtra("priceFood");
            String descFood = intent.getStringExtra("descFood");
            int imgFood = intent.getIntExtra("imgFood", 0);

            name.setText(titleFood);
            price.setText(priceFood);
            desc.setText(descFood);
            img.setImageResource(imgFood);
            totalHarga.setText(priceFood);
            resCount.setText(Integer.toString(mCount));

            btnMinus.setOnClickListener(v -> {
                if (mCount > 1){
                    mCount--;
                    resCount.setText(Integer.toString(mCount));
                    int total = Integer.parseInt(priceFood) * mCount;
                    totalHarga.setText(Integer.toString(total));
                }
            });

            btnPlus.setOnClickListener(v -> {
                mCount++;
                resCount.setText(Integer.toString(mCount));
                int total = Integer.parseInt(priceFood) * mCount;
                totalHarga.setText(Integer.toString(total));
            });

            btnOrder.setOnClickListener(v -> {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("titleFood", name.getText().toString());
                editor.putString("priceFood", price.getText().toString());
                editor.putString("descFood", desc.getText().toString());
                editor.putInt("imgFood", imgFood);
                editor.putString("totalItem", resCount.getText().toString());
                editor.putString("totalHarga", totalHarga.getText().toString());
                editor.apply();
                Intent intentReceipt = new Intent(OrderActivity.this, ReceiptActivity.class);
                startActivity(intentReceipt);
                finish();
            });
        }

        btnBack.setOnClickListener(v -> {
            Intent intentBack = new Intent(OrderActivity.this, MainActivity.class);
            startActivity(intentBack);
            finish();
        });
    }
}