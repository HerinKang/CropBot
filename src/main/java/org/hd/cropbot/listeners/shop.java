package org.hd.cropbot.listeners;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;

public class shop {

    private ArrayList<plant> options = new ArrayList<>();
    private ArrayList<plant> shop = new ArrayList<>();
    private int shopSize;
    private String lastRefresh;

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    public shop(int shopSize, int refreshMin) {
        this.shopSize = shopSize;
        this.lastRefresh = dtf.format(LocalDateTime.now());
    }

    public void setOptions(ArrayList<plant> options) {
        this.options = options;
    }

    public plant getPlant(int index) {
        return shop.get(index);
    }

    public void randomizeNewShop() {
        Random rand = new Random();

        for (int i = 0; i < shopSize; i++) {
            shop.add(options.get(rand.nextInt(options.size())));
        }

    }

    public String displayShop() {
        String shopVisual = "";
        int count = 0;
        for (int i = 0; i < shopSize; i++) {
            if (count%5 != 0 && count != 0) {
                shopVisual += shop.get(i).getName();
                count++;
            } else {
                shopVisual += "\n" + shop.get(i).getName();
                count++;
            }
        }
        return shopVisual;
    }

    public boolean buyPlant(int position) {
        if (shop.get(position-1).getName() != ":black_large_square:") {
            shop.set(position-1,new plant(":black_large_square:",0,0));
            return true;
        } else {
            return false;
        }
    }

    public String getLastRefresh() {
        return lastRefresh;
    }

    public void setLastRefresh(String lastRefresh) {
        this.lastRefresh = lastRefresh;
    }
}
