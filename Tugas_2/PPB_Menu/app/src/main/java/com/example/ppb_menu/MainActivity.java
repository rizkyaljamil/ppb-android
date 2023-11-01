package com.example.ppb_menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    FoodAdapter foodAdapter;
    List<FoodItem> foodItemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        foodItemList.add(new FoodItem("Nasi Goreng Komplit", "Nasi Goreng Jawa komplit dengan sate ayam", "56000", R.drawable.nasi_goreng_komplit));
        foodItemList.add(new FoodItem("Nasi Goreng Katsu", "Nasi Goreng dengan ayam katsu dan sayuran", "56000", R.drawable.nasgor));
        foodItemList.add(new FoodItem("Mie Goreng Kampung", "Mie Goreng Jawa komplit dengan sate ayam dan telur", "51000", R.drawable.mie_goreng_kampung));
        foodItemList.add(new FoodItem("Mie Nyemek Jawa", "Mie Nyemek khas Jawa dengan Bumbu Khas", "45000", R.drawable.mie_nyemek));
        foodItemList.add(new FoodItem("Sausage and Fries", "Sosis Cocktail dan Kentang Goreng", "38000", R.drawable.sausage_fries));
        foodItemList.add(new FoodItem("Spaghetti Bolognese", "Spaghetti dengan saus bolognese sapi cincang", "50000", R.drawable.spaghetti_bolognese));
        foodItemList.add(new FoodItem("Jasmine Tea Jumbo", "Es Teh memiliki rasa sepet-sepet", "15000", R.drawable.jasmine_tea));
        foodItemList.add(new FoodItem("Green Tea", "Es Teh dengan sensasi teh hijau dari Jepang", "12000", R.drawable.green_tea));
        foodItemList.add(new FoodItem("Taro Tea", "Es Teh dengan campuran susu berwarna ungu", "22000", R.drawable.taro_tea));
        foodItemList.add(new FoodItem("Teh Tarik", "Es Teh dengan campuran susu kental manis", "20000", R.drawable.teh_tarik));

        listView = findViewById(R.id.listMenu);
        foodAdapter = new FoodAdapter(this, foodItemList);
        listView.setAdapter(foodAdapter);


    }
}