package com.example.foodmatters;

import java.util.ArrayList;

public class Fridge {
    static ArrayList<Food> FOOD = new ArrayList<Food>();

    static int FOOD_CNT = 0;

    public static int getNewId(){
        return FOOD_CNT++;
    }
}
