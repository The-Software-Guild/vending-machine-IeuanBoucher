package com.mthree.c130.vendingMachine.ui;

import java.util.Scanner;

public class UserIOConsoleImplementation implements UserIO {
   final private Scanner consoleScanner = new Scanner(System.in);

   @Override
   public void printMessage(String message) {
      System.out.println(message);
   }

   @Override
   public String readString(String prompt) {
      System.out.println(prompt);
      return consoleScanner.nextLine();
   }

   @Override
   public int readInt(String prompt) {
      String input;
      int inputInteger;
      while (true) {
         input = readString(prompt);
         try {
            inputInteger = Integer.parseInt(input);
            return inputInteger;
         } catch (NumberFormatException e) {
            System.out.println("Input incorrect. Please enter a valid integer");
         }
      }
   }

   @Override
   public int readInt(String prompt, int minValue, int maxValue) {
      int input;

      while (true) {
         input = readInt(prompt);
         if (input > maxValue || input < minValue) {
            System.out.println("Entered value is out of range.");
         } else {
            break;
         }
      }

      return input;
   }
}
