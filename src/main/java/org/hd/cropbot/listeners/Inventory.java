package org.hd.cropbot.listeners;

import java.util.ArrayList;

public class Inventory {
    private ArrayList<String> inventory = new ArrayList<>();

    public Inventory(ArrayList<String> inventory) {
        this.inventory = inventory;
    }

    public String getFormattedInventory() {
        String formattedInventory = ""; // goes through the inventory and appends
        for (String s : inventory) {
            formattedInventory.concat(s + "\n");
        }
        return formattedInventory;
    }

    /**
     * Changes the quantity of a particular plant in the inventory
     * @param selection the number representing the plant in the inventory. minimum value is 1
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
}
