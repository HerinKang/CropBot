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

    public void resizePlot() {
        plotsize++;
        plot[][] newPlot = new plot[this.plotsize][this.plotsize];
        for (int i = 0; i < this.plotsize; i++) {
            for (int j = 0; j < this.plotsize; j++) {
                if (newPlot[i][j] == null) {
                    newPlot[i][j] = new plot(0,":brown_square:",0,0);
                }
                if (i < plotsize - 1 && j < plotsize - 1) {
                    newPlot[i][j] = list[i][j];
                }
            }
        }
        list = newPlot;
    }
    public String help() {
        String help = "Welcome to CropBot! \n\n" +
                "A list of general commands: \n" +
                "/farm - Displays your current farm, as is.\n" +
                "/plant - Plants a seedling in the first available spot.\n" +
                "/expand - Expands the farm by 1 row and 1 column. The new tiles become dirt.\n";
        return help;
    }
}
