package org.hd.cropbot.listeners;

import java.util.ArrayList;

public class Inventory {
    private ArrayList<String> inventory;

    public Inventory(ArrayList<String> inventory) {
        this.inventory = inventory;
    }

    /**
     * Returns whatever plant was selected, only including the emoji that was planted (the plot type)
     *
     * @param selection The number the user selected in the inventory
     * @return example returned String: :seedling: or :tulip:
     */
    public String getItemFromSelection(int selection) {
        String emoji = inventory.get(selection - 1);
        emoji = emoji.substring(emoji.indexOf(":"), emoji.lastIndexOf(":"));
        return emoji;
    }

    public String getFormattedInventory() {
        String formattedInventory = ""; // goes through the inventory and appends
        for (String s : inventory) {
            formattedInventory.concat(s + "\n");
        }
        if (formattedInventory.equals("")) {
            formattedInventory = "the inventory is empty";
        }
        return formattedInventory;
    }

    /**
     * Changes the quantity of a particular plant in the inventory
     *
     * @param selection   the number representing the plant in the inventory. minimum value is 1
     * @param newQuantity the changed quantity. Generally is only +1 or -1 from old quantity
     */
    public void setInventory(int selection, int newQuantity) {
        // must check to see if the last of something is used.
        // if so, it shouldn't even be an option, so, remove it
        try {
            if (newQuantity == 0) {
                this.inventory.remove(selection - 1);
                return;
            }
            // gets the element being changed
            String currentQuantity = inventory.get(selection - 1);
            // replaces the last character (the quantity) with the new quantity
            currentQuantity = currentQuantity.substring(currentQuantity.length() - 1) + newQuantity;

            inventory.set(selection - 1, currentQuantity); // replaces item
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Not a valid selection!");
        }
    }

    public void addPlantToInventory(plant plantType) {
        String lineWithPlant = "";
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).contains(plantType.getName())) {
                lineWithPlant = inventory.get(i); // get the current line of the inventory
                // then find the current quantity so it can be adjusted
                String quantity = lineWithPlant.substring(lineWithPlant.indexOf("-") + 1);
                int currentQuantity = Integer.parseInt(quantity);
                this.setInventory(i + 1, currentQuantity + 1);
                return;
            }
        }
        // If we made it down here, the plant is not currently in the inventory. So we need to add it
        int order = inventory.size() + 1;
        inventory.add(order + ". " + plantType + " - 1");
    }
}