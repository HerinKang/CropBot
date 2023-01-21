package org.hd.cropbot.listeners;

public class plot {

    private int time;
    private String type;
    private int buyPrice;
    private int sellPrice;

    public plot(int time, String type, int buyPrice, int sellPrice) {
        this.time = time;
        this.type = type;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
    }

    public void setSellPrice(int sellPrice) {
        this.sellPrice = sellPrice;
    }

    public void setBuyPrice(int buyPrice) {
        this.buyPrice = buyPrice;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getBuyPrice() {
        return buyPrice;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public int getTime() {
        return time;
    }

    public String getType() {
        return type;
    }
}
