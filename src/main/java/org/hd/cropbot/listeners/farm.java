package org.hd.cropbot.listeners;

import java.util.ArrayList;

public class farm {

    String dirt = ":brown_square:";
    private plot[][] list;
    private int plotsize;

    public farm(int plotsize) {
        this.plotsize = plotsize;
        list = new plot[plotsize][plotsize];
        for (int i = 0; i < plotsize; i++) {
            for (int j = 0; j < plotsize; j++) {
                list[i][j] = new plot(0,":brown_square:",0,0);
            }
        }
    }

    public void plantPlant(plot plot) {
        boolean loop = true;
        for (int i = 0; i < plotsize; i++) {
            if (loop == false) {
                break;
            }
            for (int j = 0; j < plotsize; j++) {
                if (list[i][j].getType().equals(":brown_square:")) {
                    list[i][j] = plot;
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
                farm += list[i][j].getType();
            }
            farm += "\n";
        }
        return farm;
    }

    public void resizePlot(int newSize) {

    }

    public void setPlotsize(int plotsize) {
        this.plotsize = plotsize;
    }
}
