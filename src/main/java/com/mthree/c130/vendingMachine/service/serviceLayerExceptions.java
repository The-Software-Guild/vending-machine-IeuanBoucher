package com.mthree.c130.vendingMachine.service;

public class serviceLayerExceptions {
   public static class InsufficientFundsException extends Exception {
      public InsufficientFundsException(String message) {
         super(message);
      }

      public InsufficientFundsException(String message, Throwable cause) {
         super(message, cause);
      }
   }

   public static class NoItemInventoryException extends Exception {
      public NoItemInventoryException(String message) {
         super(message);
      }

      public NoItemInventoryException(String message, Throwable cause) {
         super(message, cause);
      }
   }
}
