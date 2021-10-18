package com.mthree.c130.vendingMachine.ui;

import java.util.List;

public interface UserIO {
   void printMessage(String message);

   String readString(String prompt);

   int readInt(String prompt);

   int readInt(String prompt, int minValue, int maxValue);

   String readString(String s, List<String> validOptions);
}
