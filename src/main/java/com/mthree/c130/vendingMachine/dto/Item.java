package com.mthree.c130.vendingMachine.dto;

import java.math.BigDecimal;

public class Item {
   private final String name;
   private final BigDecimal cost;
   private int remainingStock;


   public Item(String name, BigDecimal cost, int remainingStock) {
      this.name = name;
      this.cost = cost;
      this.remainingStock = remainingStock;
   }

   public void setRemainingStock(int remainingStock) {
      this.remainingStock = remainingStock;
   }
}
