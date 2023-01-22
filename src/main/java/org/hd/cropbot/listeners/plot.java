package org.hd.cropbot.listeners;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class plot {

    private String time;
    private String type;
    private int buyPrice;
    private int sellPrice;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    public plot(LocalDateTime now, String type, int buyPrice, int sellPrice) {
        this.time = dtf.format(LocalDateTime.now());
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

    public void setTime(String time) {
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

    public String getTime() {
        return time;
    }

    public String getType() {
        return type;
    }
}
