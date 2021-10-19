package com.mthree.c130.vendingMachine.ui;

import com.mthree.c130.vendingMachine.dto.Item;
import com.mthree.c130.vendingMachine.service.Coin;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;

public class vendingMachineView {

   private final UserIO userInputOutput;

   public vendingMachineView(UserIO userIO) {
      this.userInputOutput = userIO;
   }

   public int displayOptionsGetChoice() {
      userInputOutput.printMessage("Available Options:");
      userInputOutput.printMessage("1. Insert money");
      userInputOutput.printMessage("2. Purchase item");
      userInputOutput.printMessage("3. Exit program");

      return userInputOutput.readInt("Choose an option from the menu.", 1, 3);
   }

   public void displayStockedItems(Collection<Item> stockedItems) {
      if (stockedItems.size() == 0) {
         return;
      }

      userInputOutput.printMessage("Current items in stock:");

      for (Item i : stockedItems) {
         userInputOutput.printMessage(i.toString());
      }
   }

   public void displayLoadFailure() {
      userInputOutput.printMessage("Failed to load vending machine items. Exiting program.");
   }

   public String getUserItemChoice(Collection<Item> stockedItems) {
      List<String> validOptions = stockedItems.stream()
              .map(stockedItem -> stockedItem.getName().toLowerCase(Locale.UK))
              .collect(Collectors.toList());

      validOptions.add("cancel");

      return userInputOutput.readString("Choose an option to purchase, or select cancel.", validOptions);
   }

   public void displayNoItemsMessage() {
      userInputOutput.printMessage("There are no items in stock.");
   }

   public void displayProgramExitMessage() {
      System.out.println("Thank you for using this machine.");
   }

   public void displayExceptionMessage(String message) {
      System.out.println(message);
   }

   public void displayChange(int[] changeAmounts) {
      ArrayList<Coin> coins = new ArrayList<>(List.of(Coin.values()));
      int quantity;

      boolean noChangeNeed = true;
      for (int i : changeAmounts) {
         if (i != 0) {
            noChangeNeed = false;
            break;
         }
      }

      if (noChangeNeed) return;

      System.out.println("Dispensing change:");

      for (int index = 0; index < coins.size(); index++) {
         quantity = changeAmounts[index];
         if (quantity != 0) {
            userInputOutput.printMessage(quantity + " x " + coins.get(index).displayName);
         }
      }
   }

   public BigDecimal getEnteredMoney() {
      double amountD = userInputOutput.readDouble("Please enter an amount of money in GBP");

      return new BigDecimal(Double.toString(amountD));
   }
}
