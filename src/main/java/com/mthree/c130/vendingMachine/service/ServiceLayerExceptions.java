package com.mthree.c130.vendingMachine.service;

public class ServiceLayerExceptions {
   public static class InsufficientFundsException extends Exception {
      public InsufficientFundsException(String message) {
         super(message);
      }
   }

   public static class NoItemInventoryException extends Exception {
      public NoItemInventoryException(String message) {
         super(message);
      }
   }
}
