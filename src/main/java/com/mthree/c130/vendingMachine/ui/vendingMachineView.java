package com.mthree.c130.vendingMachine.ui;

import com.mthree.c130.vendingMachine.dto.Item;

import java.math.BigDecimal;
import java.util.Collection;

public class vendingMachineView {

   private final UserIO userInputOutput;
   private BigDecimal currentBalance = new BigDecimal("0"); // TODO where should this go?

   public vendingMachineView(UserIO userIO) {
      this.userInputOutput = userIO;
   }

   public void displayOptions() {
      //TODO add user input to chose option?
      userInputOutput.printMessage("Available Options:");
      userInputOutput.printMessage("1. Insert money");
      userInputOutput.printMessage("2. Purchase item");
   }

   public void displayStockedItems(Collection<Item> stockedItems) {
      if (stockedItems.size() == 0) {
         userInputOutput.printMessage("There are no items in stock.");
         return;
      }

      userInputOutput.printMessage("Current items in stock:");

      for (Item i : stockedItems) {
         System.out.println(i.toString());
      }
   }

   public void displayLoadFailure() {
      userInputOutput.printMessage("Failed to load vending machine items. Exiting program.");
   }

//   public void displayMessage(String message) {
//      userIO.printMessage(message);
//   }
}
