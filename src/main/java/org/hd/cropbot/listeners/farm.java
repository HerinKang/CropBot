package org.hd.cropbot.listeners;

import java.awt.desktop.AppForegroundListener;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class farm {

    String dirt = ":brown_square:";
    private plot[][] list;
    private int plotsize;
    private Inventory inventory;

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    private LocalDateTime now;

    public farm(int plotsize) {
        this.plotsize = plotsize;
        list = new plot[plotsize][plotsize];
        for (int i = 0; i < plotsize; i++) {
            for (int j = 0; j < plotsize; j++) {
                list[i][j] = new plot(LocalDateTime.now(),":brown_square:",0,0);
            }
        }
    }

    public void plantPlant(int selection, int buyPrice, int sellPrice, Inventory inventory) {

        /*
        String type = inventory.getItemFromSelection(selection);
        boolean loop = true;
        for (int i = 0; i < plotsize; i++) {
            if (loop == false) {
                break;
            }
            for (int j = 0; j < plotsize; j++) {
                if (list[i][j].getType().equals(":brown_square:")) {
                    list[i][j] = new plot(LocalDateTime.now(), type, 0, 0);
                    loop = false;
                    break;
                }
            }
        }
         */
        inventory = inventory;
        String type = inventory.getItemFromSelection(selection);
        boolean loop = true;
        for (int i = 0; i < plotsize; i++) {
            if (loop == false) {
                break;
            }
            for (int j = 0; j < plotsize; j++) {
                if (list[i][j].getType().equals(":brown_square:")) {
                    list[i][j] = new plot(LocalDateTime.now(), type, buyPrice, sellPrice);
                    inventory.decreaseCount(selection);
                    loop = false;
                    break;
                }
            }
        }


    }

    public String displayFarm() {
        String farm = "";
        for (int i = 0; i < plotsize; i++) {
            for (int j = 0; j < plotsize; j++) {
                if (timeDifference(list[i][j].getTime()) >= 0.01 && list[i][j].getType() != ":brown_square:") {
                    list[i][j].setType(list[i][j].getType());
                    farm += list[i][j].getType();
                } else if (list[i][j].getType() != ":brown_square:") {
                    farm += ":seedling:";
                } else {
                    farm += ":brown_square:";
                }
            }
            farm += "\n";
        }
        return farm;
    }

    public void resizePlot() {
        plotsize++;
        plot[][] newPlot = new plot[this.plotsize][this.plotsize];
        for (int i = 0; i < this.plotsize; i++) {
            for (int j = 0; j < this.plotsize; j++) {
                if (newPlot[i][j] == null) {
                    newPlot[i][j] = new plot(LocalDateTime.now(),":brown_square:",0,0);
                }
                if (i < plotsize - 1 && j < plotsize - 1) {
                    newPlot[i][j] = list[i][j];
                }
            }
        }
        list = newPlot;

    }

    public void setPlotsize(int plotsize) {
        this.plotsize = plotsize;
    }

    public double timeDifference(String time) {
        String x = time.substring(time.indexOf(" ") + 1);
        String y = dtf.format(LocalDateTime.now()).substring(time.indexOf(" ") + 1);
        //System.out.println(x);
        //System.out.println(y);
        String[] a = x.split(":");
        String[] b = y.split(":");
        double hourDiff = Integer.parseInt(b[0]) - Integer.parseInt(a[0]);
        double minDiff = Integer.parseInt(b[1]) - Integer.parseInt(a[1]);
        minDiff = minDiff/60;
        return hourDiff + minDiff;
    }

}
