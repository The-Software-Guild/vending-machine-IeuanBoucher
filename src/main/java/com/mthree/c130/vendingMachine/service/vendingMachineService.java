package com.mthree.c130.vendingMachine.service;

import com.mthree.c130.vendingMachine.dao.vendingMachineAuditDao;
import com.mthree.c130.vendingMachine.dao.vendingMachineDao;
import com.mthree.c130.vendingMachine.dto.Item;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;

public class vendingMachineService {
   private final vendingMachineDao dao;
   private final vendingMachineAuditDao auditDao;

   private BigDecimal currentBalance = new BigDecimal("0.00");

   public vendingMachineService(vendingMachineDao dao, vendingMachineAuditDao auditDao) {
      this.dao = dao;
      this.auditDao = auditDao;
   }

   public Collection<Item> getStockedItems() {
      dao.loadData();
      Collection<Item> items = dao.getAllItems();

      items.removeIf(i -> i.getRemainingStock() <= 0);

      return items;
   }

   public boolean initialiseProgram() {
      return dao.loadData();
   }

   public boolean attemptPurchase(String userItemChoice) throws serviceLayerExceptions.NoItemInventoryException, serviceLayerExceptions.InsufficientFundsException, IOException {
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
         currentBalance = currentBalance.setScale(2, RoundingMode.FLOOR);
         dao.decreaseStock(chosenItem);

         boolean success = dao.saveData();
         if (!success) {
            System.out.println("Failed to record purchase, please exit program.");
            return false;
         }

         logEvent("Purchase " + chosenItem.getName() + ". stock remaining: " + chosenItem.getRemainingStock());

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

   public void logEvent(String event) throws IOException {
      auditDao.writeAuditEntry(event);
   }

   public void setMoney(BigDecimal enteredMoney) {
      currentBalance = (enteredMoney).setScale(2, RoundingMode.FLOOR);
   }
}
