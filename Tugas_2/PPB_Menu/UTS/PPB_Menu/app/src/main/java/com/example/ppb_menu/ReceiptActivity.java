package com.example.ppb_menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ReceiptActivity extends AppCompatActivity {
    SharedPreferences preferences;
    TextView name, price, desc, namaOrder, hargaOrder, totalItem, totalHarga;
    ImageView img, btnBack;
    Button btnGoHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);
        preferences = getSharedPreferences("food_data", 0);

        name = findViewById(R.id.titleFood);
        price = findViewById(R.id.priceFood);
        desc = findViewById(R.id.descFood);
        img = findViewById(R.id.imageFood);
        namaOrder = findViewById(R.id.nameFoodOrder);
        hargaOrder = findViewById(R.id.hargaMakanan);
        totalItem = findViewById(R.id.totalMakanan);
        totalHarga = findViewById(R.id.totalHargaMakanan);
        btnBack = findViewById(R.id.iv_back_toolbar);
        btnGoHome = findViewById(R.id.backToMenu);

        btnBack.setOnClickListener(v -> {
            Intent intentBack = new Intent(ReceiptActivity.this, MainActivity.class);
            startActivity(intentBack);
            finish();
        });

        btnGoHome.setOnClickListener(v -> {
            Intent intentBack = new Intent(ReceiptActivity.this, MainActivity.class);
            startActivity(intentBack);
            finish();
        });

        String namePref = preferences.getString("titleFood", "");
        String pricePref = preferences.getString("priceFood", "");
        String descPref = preferences.getString("descFood", "");
        int imgPref = preferences.getInt("imgFood", 0);
        String totalItemPref = preferences.getString("totalItem", "");
        String totalHargaPref = preferences.getString("totalHarga", "");

        name.setText(namePref);
        price.setText(pricePref);
        desc.setText(descPref);
        img.setImageResource(imgPref);
        namaOrder.setText(namePref);
        hargaOrder.setText(pricePref.toString());
        totalItem.setText(totalItemPref);
        totalHarga.setText(totalHargaPref);
    }
}