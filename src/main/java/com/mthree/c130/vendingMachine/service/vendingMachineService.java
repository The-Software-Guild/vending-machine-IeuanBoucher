package com.mthree.c130.vendingMachine.service;

import com.mthree.c130.vendingMachine.dao.vendingMachineDao;
import com.mthree.c130.vendingMachine.dto.Item;

import java.math.BigDecimal;
import java.util.Collection;

public class vendingMachineService {
   private final vendingMachineDao dao;

   private BigDecimal currentBalance = new BigDecimal("5.00"); // TODO where should this go?

   private final Change change = new Change();

   public vendingMachineService(vendingMachineDao dao) {
      this.dao = dao;
   }

   public Collection<Item> getStockedItems() {
      dao.loadData();
      Collection<Item> items = dao.getAllItems();

      items.removeIf(i -> i.getRemainingStock() == 0);

      return items;
   }

   public boolean initialiseProgram() {
      return dao.loadData();
   }

   public boolean attemptPurchase(String userItemChoice) throws serviceLayerExceptions.NoItemInventoryException, serviceLayerExceptions.InsufficientFundsException {
      Collection<Item> stockedItems = getStockedItems();

      Item chosenItem = null;

      for (Item i : stockedItems) {
         if (i.getName().equalsIgnoreCase(userItemChoice)) {
            chosenItem = i;
            break;
         }
      }

      if (chosenItem == null) {
         throw new serviceLayerExceptions.NoItemInventoryException("This machine has no stock for that item");
      }

      if (chosenItem.getPrice().compareTo(currentBalance) > 0) {
         throw new serviceLayerExceptions.InsufficientFundsException("Current balance is insufficient to buy that item");
      } else {
         currentBalance = currentBalance.subtract(chosenItem.getPrice());
         dao.decreaseStock(chosenItem);
         dao.saveData();

         return true;
      }
   }

   public BigDecimal getCurrentBalance() {
      return currentBalance;
   }

   public int[] calculateChange() {
      Change change = new Change();
      return change.calculateChange(currentBalance.multiply(new BigDecimal("100")).intValue());
   }

   public void saveData() {
      dao.saveData();
   }
}
