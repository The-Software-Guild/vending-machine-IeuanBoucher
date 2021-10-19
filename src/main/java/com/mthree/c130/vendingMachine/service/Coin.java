package com.mthree.c130.vendingMachine.service;

public enum Coin {
   POUND(100, "Pound coin"),
   FIFTY_PENCE(50, "Fifty pence coin"),
   TWENTY_PENCE(20, "Twenty pence coin"),
   TEN_PENCE(10, "Ten pence coin"),
   FIVE_PENCE(5, "Five pence coin"),
   TWO_PENCE(2, "Two pence coin"),
   PENNY(1, "Penny");

   public int value;
   public String displayName;

   Coin(int value, String displayName) {
      this.value = value;
      this.displayName = displayName;
   }
}
