package com.mthree.c130.vendingMachine.controller;

import com.mthree.c130.vendingMachine.dto.Item;
import com.mthree.c130.vendingMachine.service.ServiceLayerExceptions;
import com.mthree.c130.vendingMachine.service.VendingMachineService;
import com.mthree.c130.vendingMachine.ui.VendingMachineView;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collection;

public class VendingMachineController {
   private final VendingMachineService service;
   private final VendingMachineView view;

   public VendingMachineController(VendingMachineService service, VendingMachineView view) {
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
               if (service.getCurrentBalance().compareTo(new BigDecimal("0")) == 0) {
                  view.displayNoFundsMessage();
                  break;
               }
               boolean success = handlePurchaseItem();
               if (success) loopMenu = false;
               break;
            case 3:
               loopMenu = false;
               int[] amounts = service.calculateChange();
               view.displayChange(amounts);
               break;
         }
      } while (loopMenu);

      service.saveData();
      view.displayProgramExitMessage();
   }

   private boolean handlePurchaseItem() {
      Collection<Item> stockedItems = service.getStockedItems();
      System.out.println("Entered balance: £" + service.getCurrentBalance().toString());
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
         boolean success = service.makePurchase(userItemChoice);
         if (success) {
            System.out.println("Purchase successful.");
            System.out.println("Unspent balance: £" + service.getCurrentBalance().toString());

            view.displayChange(service.calculateChange());
         }
         return success;
      } catch (ServiceLayerExceptions.NoItemInventoryException | ServiceLayerExceptions.InsufficientFundsException | IOException e) {
         view.displayExceptionMessage(e.getMessage());
         return false;
      }
   }

   private void handleInsertMoney() {
      service.setMoney(view.getEnteredMoney());
   }
}
