package com.mthree.c130.vendingMachine.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ChangeTest {

   @Test
   public void noChange() {
      Change change = new Change();
      int[] result = change.calculateChange(0);

      int[] expected = new int[]{0, 0, 0, 0, 0, 0, 0};

      Assertions.assertArrayEquals(result, expected);
   }

   @Test
   public void onePoundChange() {
      Change change = new Change();
      int[] result = change.calculateChange(100);
      int[] expected = new int[]{1, 0, 0, 0, 0, 0, 0};

      Assertions.assertArrayEquals(result, expected);
   }

   @Test
   public void fiftyPenceChange() {
      Change change = new Change();
      int[] result = change.calculateChange(50);
      int[] expected = new int[]{0, 1, 0, 0, 0, 0, 0};

      Assertions.assertArrayEquals(result, expected);
   }

   @Test
   public void thirtyThreePenceChange() {
      Change change = new Change();
      int[] result = change.calculateChange(33);
      int[] expected = new int[]{0, 0, 1, 1, 0, 1, 1};

      Assertions.assertArrayEquals(result, expected);
   }

   @Test
   public void onePoundNinetyThreeChance() {
      Change change = new Change();
      int[] result = change.calculateChange(193);
      int[] expected = new int[]{1, 1, 2, 0, 0, 1, 1};

      Assertions.assertArrayEquals(result, expected);
   }
}