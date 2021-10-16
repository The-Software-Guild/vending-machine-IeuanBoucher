package com.mthree.c130.vendingMachine.ui;

public interface UserIO {
   void printMessage(String message);

   String readString(String prompt);

   int readInt(String prompt);

   int readInt(String prompt, int minValue, int maxValue);
}
