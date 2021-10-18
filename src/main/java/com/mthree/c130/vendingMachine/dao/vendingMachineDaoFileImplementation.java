package com.mthree.c130.vendingMachine.dao;

import com.mthree.c130.vendingMachine.dto.Item;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class vendingMachineDaoFileImplementation implements vendingMachineDao {
   private final Map<String, Item> itemMap = new HashMap<>();
   private final String VENDING_MACHINE_FILENAME = "src/main/resources/machineItems.txt";
   private final String DELIMITER = "//";

   @Override
   public List<Item> getAllItems() {
      return new ArrayList<>(itemMap.values());
   }

   @Override
   public Item getItem(String name) {
      return itemMap.get(name);
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

   private String marshallItem(Item unmarshalledItem) {
      return String.join(DELIMITER, unmarshalledItem.getName(), unmarshalledItem.getPrice().toString(), Integer.toString(unmarshalledItem.getRemainingStock()));
   }

   @Override
   public boolean saveData() {
      PrintWriter out;
      try {
         out = new PrintWriter(new FileWriter(VENDING_MACHINE_FILENAME));
      } catch (IOException e) {
         System.out.println("Could not save Item library.");
         return false;
      }

      String marshalledItem;
      for (Item Item : itemMap.values()) {
         marshalledItem = marshallItem(Item);
         out.println(marshalledItem);
         out.flush();
      }
      out.close();
      return true;
   }

   @Override
   public void decreaseStock(Item chosenItem) {
      chosenItem.setRemainingStock(chosenItem.getRemainingStock() - 1);
      itemMap.put(chosenItem.getName(), chosenItem);
   }
}
