package org.hd.cropbot.listeners;

import java.util.ArrayList;

public class Inventory {
    private ArrayList<String> inventory = new ArrayList<>();

    public Inventory() {
    }

    /**
     * Returns whatever plant was selected, only including the emoji that was planted (the plot type)
     *
     * @param selection The number the user selected in the inventory
     * @return example returned String: :seedling: or :tulip:
     */
    public String getItemFromSelection(int selection) {
        String emoji = inventory.get(selection - 1);
        String temp = emoji.substring(emoji.indexOf(":") + 1);
        emoji = ":" + temp.substring(0,temp.indexOf(":") + 1);
        return emoji;
    }

    public String getFormattedInventory() {

        for(int i = 0; i < inventory.size(); i++) {
            System.out.println(inventory.get(i));
        }

        String formattedInventory = ""; // goes through the inventory and appends
        for (int i = 0; i < inventory.size(); i++) {
            formattedInventory += inventory.get(i) + "\n";
        }
        if (formattedInventory.equals("")) {
            formattedInventory = "the inventory is empty";
        }
        return formattedInventory;
    }

    /**
     * Changes the quantity of a particular plant in the inventory
     * @param selection the number representing the plant in the inventory. minimum value is 1
     */
    public void increaseCount(int selection) {
        // must check to see if the last of something is used.
        // if so, it shouldn't even be an option, so, remove it
        try {

            // gets the element being changed
            String thing = inventory.get(selection - 1);
            // replaces the last character (the quantity) with the new quantity
            int newValue = Integer.parseInt(thing.substring(thing.indexOf("-")+2)) + 1;
            String order = inventory.get(selection-1).substring(0,inventory.get(selection-1).indexOf("."));
            String plantType = inventory.get(selection-1).substring(inventory.get(selection-1).indexOf(".")+2,inventory.get(selection-1).indexOf("-")-1);

            inventory.set(selection-1, order + ". " + plantType + " - " + newValue); // replaces item

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Not a valid selection!");
        }
    }

    public void decreaseCount(int selection) {
        try {

            // gets the element being changed
            String thing = inventory.get(selection - 1);
            // replaces the last character (the quantity) with the new quantity
            int newValue = Integer.parseInt(thing.substring(thing.indexOf("-")+2)) - 1;
            if (newValue == 0) {
                this.inventory.remove(selection - 1);
                for (int i = 0; i < inventory.size(); i++) {
                    int temp1 = Integer.parseInt(inventory.get(i).substring(0, inventory.get(i).indexOf(".")));
                    String temp2 = inventory.get(i).substring(inventory.get(i).indexOf(".") + 2);
                    inventory.set(i, (temp1 - 1) + ". " + temp2);
                }
                return;
            }
            String order = inventory.get(selection-1).substring(0,inventory.get(selection-1).indexOf("."));
            String plantType = inventory.get(selection-1).substring(inventory.get(selection-1).indexOf(".")+2,inventory.get(selection-1).indexOf("-")-1);

            inventory.set(selection-1, order + ". " + plantType + " - " + newValue); // replaces item

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Not a valid selection!");
        }
    }

    public void addPlantToInventory(String plantType) {
        String lineWithPlant = "";

        if (inventory.size() == 0) {
            int order = inventory.size() + 1;
            inventory.add(order + ". " + plantType + " - 1");
            return;
        }

        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).contains(plantType)) {
                lineWithPlant = inventory.get(i); // get the current line of the inventory
                // then find the current quantity so it can be adjusted
                String quantity = lineWithPlant.substring(lineWithPlant.indexOf("-") + 2);
                int currentQuantity = Integer.parseInt(quantity);
                this.increaseCount(i + 1);
                return;
            }
        }
        // If we made it down here, the plant is not currently in the inventory. So we need to add it

        int order = inventory.size() + 1;
        inventory.add(order + ". " + plantType + " - 1");
    }
}