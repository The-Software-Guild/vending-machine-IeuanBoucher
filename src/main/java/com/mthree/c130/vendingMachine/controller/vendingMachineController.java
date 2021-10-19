package com.mthree.c130.vendingMachine.controller;

import com.mthree.c130.vendingMachine.dto.Item;
import com.mthree.c130.vendingMachine.service.serviceLayerExceptions;
import com.mthree.c130.vendingMachine.service.vendingMachineService;
import com.mthree.c130.vendingMachine.ui.vendingMachineView;

import java.util.Collection;

public class vendingMachineController {
   private final vendingMachineService service;
   private final vendingMachineView view;
   
   public vendingMachineController(vendingMachineService service, vendingMachineView view) {
      this.service = service;
      this.view = view;
   }

   public void run() {
      if (!service.initialiseProgram()) {
         view.displayLoadFailure();
         return;
      }

      Collection<Item> stockedItems = service.getStockedItems();

      view.displayStockedItems(stockedItems);

      boolean loopMenu = true;
      int choice;
      do {
         choice = view.displayOptionsGetChoice();
         switch (choice) {
            case 1:
               handleInsertMoney();
               break;
            case 2:
               boolean success = handlePurchaseItem();
               if (success) loopMenu = false;
               break;
            case 3:
               loopMenu = false;
               System.out.println(service.getCurrentBalance());
               view.displayChange(service.calculateChange());
               break;
         }
      } while (loopMenu);

      service.saveData();
      view.displayProgramExitMessage();
   }

   private boolean handlePurchaseItem() {
      Collection<Item> stockedItems = service.getStockedItems();
      view.displayStockedItems(stockedItems);

      if (stockedItems.size() == 0) {
         view.displayNoItemsMessage();
         return false;
      }

      String userItemChoice = view.getUserItemChoice(stockedItems);
      if (userItemChoice.equals("Cancel")) {
         return false;
      }

      try {
         boolean success = service.attemptPurchase(userItemChoice);
         if (success) {
            System.out.println("Purchase successful.");
            System.out.println("Unspent balance: £" + service.getCurrentBalance().toString());

            view.displayChange(service.calculateChange());
         }
         return success;
      } catch (serviceLayerExceptions.NoItemInventoryException | serviceLayerExceptions.InsufficientFundsException e) {
         view.displayExceptionMessage(e.getMessage());
         return false;
      }
   }

   private void handleInsertMoney() {
      //todo
      throw new UnsupportedOperationException("no implemented");
   }
}
