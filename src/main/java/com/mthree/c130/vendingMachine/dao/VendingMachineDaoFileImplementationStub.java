package com.mthree.c130.vendingMachine.dao;

import com.mthree.c130.vendingMachine.dto.Item;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class VendingMachineDaoFileImplementationStub implements VendingMachineDao {

   private final Map<String, Item> itemMap = new HashMap<>();

   @Override
   public Collection<Item> getAllItems() {
      return itemMap.values();
   }

   @Override
   public Item getItem(String name) {
      return itemMap.get(name);
   }

   @Override
   public boolean loadData() {
      itemMap.clear();
      itemMap.put("pepsi", new Item("pepsi", new BigDecimal("1.70"), 5));
      itemMap.put("tango", new Item("tango", new BigDecimal("1.50"), 7));
      itemMap.put("7up", new Item("7up", new BigDecimal("1.60"), 4));

      return true;
   }

   @Override
   public boolean saveData() {
      return true;
   }

   @Override
   public void decreaseStock(Item chosenItem) {
      chosenItem.setRemainingStock(chosenItem.getRemainingStock() - 1);
      itemMap.put(chosenItem.getName(), chosenItem);
   }
}
