package com.example.ppb_menu;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

public class FoodAdapter extends BaseAdapter {
    Context context;
    List<FoodItem> foodItemList;
    LayoutInflater inflater;

    public FoodAdapter(Context context, List<FoodItem> foodItemList) {
        this.context = context;
        this.foodItemList = foodItemList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return foodItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.list_menu_adapter, null);
        TextView nameFood = convertView.findViewById(R.id.titleFood);
        TextView descFood = convertView.findViewById(R.id.descFood);
        TextView priceFood = convertView.findViewById(R.id.priceFood);
        ImageView imgFood = convertView.findViewById(R.id.imageFood);
        Button btnOrder = convertView.findViewById(R.id.btnOrder);

        nameFood.setText(foodItemList.get(position).getTitle());
        descFood.setText(foodItemList.get(position).getDescription());
        String price = foodItemList.get(position).getPrice();
        priceFood.setText(price);
        imgFood.setImageResource(foodItemList.get(position).getImage());

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, OrderActivity.class);
                intent.putExtra("titleFood", foodItemList.get(position).getTitle());
                intent.putExtra("descFood", foodItemList.get(position).getDescription());
                intent.putExtra("priceFood", foodItemList.get(position).getPrice());
                intent.putExtra("imgFood", foodItemList.get(position).getImage());
                context.startActivity(intent);
            }
        });
        return convertView;
    }
}
