package com.example.ppb_menu;

public class FoodItem {
    private String title;
    private String description;
    private String price;
    private int image;

    public FoodItem(String title, String description, String price, int image) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.image = image;
    }

    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getPrice() { return price; }
    public int getImage() { return image; }
}
