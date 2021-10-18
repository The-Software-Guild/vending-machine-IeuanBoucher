package com.mthree.c130.vendingMachine.dao;

import com.mthree.c130.vendingMachine.dto.Item;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class vendingMachineDaoFileImplementation implements vendingMachineDao {
   private final Map<String, Item> itemMap = new HashMap<>();
   private final String VENDING_MACHINE_FILENAME = "src/main/resources/machineItems.txt";
   private final String DELIMITER = "//";

   @Override
   public Collection<Item> getAllItems() {
      return itemMap.values();
   }

   @Override
   public Item getItem(String name) {
      return null;
   }

   @Override
   public boolean loadData() {
      return loadDataFile();
   }

   private boolean loadDataFile() {
      Scanner scanner;

      try {
         scanner = new Scanner(new BufferedReader(new FileReader(VENDING_MACHINE_FILENAME)));
      } catch (FileNotFoundException e) {
         return false;
      }

      Item currentItem;
      String currentFileLine;

      while (scanner.hasNext()) {
         currentFileLine = scanner.nextLine();

         currentItem = unmarshallItem(currentFileLine);

         if (currentItem == null) {
            System.out.println("Found malformed Item, skipping");
         } else {
//            System.out.println("loaded item:\n" + currentItem);
            itemMap.put(currentItem.getName(), currentItem);
         }
      }
      return true;
   }

   private Item unmarshallItem(String currentFileLine) {
      try {
         String[] dataValues = currentFileLine.split(DELIMITER);
         return new Item(dataValues[0], new BigDecimal(dataValues[1]), Integer.parseInt(dataValues[2]));
      } catch (Exception e) {
         return null;
      }
   }


   @Override
   public boolean saveData() {
      return false;
   }
}
