package com.techelevator;

import com.techelevator.inventory.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static com.techelevator.InventoryConstants.*;

public class InventoryProvider {
    Scanner fileScanner;

    public InventoryProvider() throws FileNotFoundException {
        String filePath = System.getProperty(INVENTORY_FILE, "vendingmachine.csv");
        File inventoryFile = new File(filePath);
        if (inventoryFile.isDirectory()) {
            throw new FileNotFoundException("The provided path is a folder");
        }
        fileScanner = new Scanner(inventoryFile);
    }

    // TODO: decide how to handle unknown snack types
    // so far, we are ignoring those entries
    public Map<String, Product> loadInventory() {
        //  SlotId
        Map<String, Product> map = new HashMap<>();
        Product product;

        while (fileScanner.hasNextLine()) {
            String inventoryLine = fileScanner.nextLine();
            String[] inventoryLineArr = inventoryLine.split("\\|");
            String slot = inventoryLineArr[INVENTORY_LINE_SLOT];
            String name = inventoryLineArr[INVENTORY_LINE_NAME];
            String type = inventoryLineArr[INVENTORY_LINE_TYPE];
            try {
                double price = Double.parseDouble(inventoryLineArr[INVENTORY_LINE_PRICE]);
                if (type.equalsIgnoreCase(INVENTORY_TYPE_CANDY)) {
                    product = new Candy(name, price);
                } else if (type.equalsIgnoreCase(INVENTORY_TYPE_GUM)) {
                    product = new Gum(name, price);
                } else if (type.equalsIgnoreCase(INVENTORY_TYPE_DRINK)) {
                    product = new Drink(name, price);
                } else if (type.equalsIgnoreCase(INVENTORY_TYPE_CHIP)) {
                    product = new Chip(name, price);
                } else {
                    continue;
                }
            } catch (NumberFormatException e) {
                continue;
            }
            map.put(slot, product);
        }
        return map;
    }
}
