package com.example.android.myapplication;

public class Values {
    private String itemName,Money;
    private String produced,stock;

    public Values(){}
    public Values(String itemName, String money, String produced,String stock) {
        this.itemName = itemName;
        Money = money;
        this.produced = produced;
        this.stock = stock;
    }

    public String getItemName() {
        return itemName;
    }

    public String getMoney() {
        return Money;
    }

    public String getProduced() {
        return produced;
    }

    public String getStock() {
        return stock;
    }
}
