package com.mthree.c130.vendingMachine.service;

public class Change {
   public int[] calculateChange(int pennies) {
      int remaining = pennies;
      int quantity;

      int[] changeAmounts = new int[]{0, 0, 0, 0, 0, 0, 0};

      if (pennies <= 0) {
         return changeAmounts;
      }

      for (int index = 0; index < Coin.values().length; index++) {
         int coinValue = Coin.values()[index].value;
         if (remaining % coinValue == 0) {
            changeAmounts[index] = remaining / coinValue;
            break;
         } else {
            quantity = remaining / coinValue;
            changeAmounts[index] = quantity;
            remaining -= quantity * coinValue;
         }
      }

      return changeAmounts;
   }
}