package com.example.foodmatters;

import androidx.annotation.Nullable;

public class Food {
    private String name;
    private String amount;
    private String expirationDate;

    private int id = -1;

    Food(String name, String amount, String expirationDate){
        this.id = Fridge.getNewId();
        this.name = name;
        this.amount = amount + " g";
        this.expirationDate = expirationDate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAmount() {
        return amount;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public boolean equals(@Nullable Object obj) {

        if (obj instanceof Food){
            //return ((Food) obj).getName().equals(this.name) && ((Food) obj).getAmount().equals(this.amount) && ((Food) obj).getExpirationDate().equals(this.expirationDate);
            return this.id == ((Food) obj).getId();
        }

        return false;
    }
}
