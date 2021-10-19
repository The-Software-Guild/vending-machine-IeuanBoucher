package com.mthree.c130.vendingMachine.ui;

import java.util.List;
import java.util.Locale;
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

   @Override
   public String readString(String s, List<String> validOptions) {
      String input;

      while (true) {
         input = readString("Type the name of an item, or cancel").toLowerCase(Locale.UK);
         if (validOptions.contains(input)) {
            return input;
         } else {
            System.out.println("Unrecognised item or option, please try again.");
         }
      }
   }

   @Override
   public double readDouble(String prompt) {
      double amount;
      while (true) {
         System.out.println(prompt);
         try {
            amount = Double.parseDouble(consoleScanner.next());
            if (amount <= 0.0) {
               System.out.println("Please enter a value greater than zero.");
            } else {
               break;
            }
         } catch (Exception e) {
            System.out.println("Please enter a valid value.");
         }
      }
      return amount;
   }
}
