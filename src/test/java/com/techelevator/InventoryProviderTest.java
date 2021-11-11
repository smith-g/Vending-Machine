package com.techelevator;

import com.techelevator.inventory.Product;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.Map;

import static com.techelevator.InventoryConstants.INVENTORY_FILE;

public class InventoryProviderTest {

    InventoryProvider inventoryProvider;

    @Test
    public void test_noFileProvided() throws FileNotFoundException {
        System.clearProperty(INVENTORY_FILE);
        inventoryProvider = new InventoryProvider();
    }

    @Test(expected = FileNotFoundException.class)
    public void test_wrongFileProvided() throws FileNotFoundException {
        System.setProperty(INVENTORY_FILE, "wrongfile.csv");
        inventoryProvider = new InventoryProvider();
    }

    @Test
    public void test_loadInventory() throws FileNotFoundException {
        System.setProperty(INVENTORY_FILE, "src/test/java/com/techelevator/vendingmachine.csv");
        inventoryProvider = new InventoryProvider();
        Map<String, Product> inventory = inventoryProvider.loadInventory();
        int expectedNumber = 4;
        Assert.assertEquals("Wrong number of items loaded", expectedNumber, inventory.size());
    }

}
