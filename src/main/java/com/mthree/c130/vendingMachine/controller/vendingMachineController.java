package com.mthree.c130.vendingMachine.controller;

import com.mthree.c130.vendingMachine.dto.Item;
import com.mthree.c130.vendingMachine.service.vendingMachineService;
import com.mthree.c130.vendingMachine.ui.vendingMachineView;

import java.util.Collection;

public class vendingMachineController {
   private final vendingMachineService service;
   private final vendingMachineView view;

   public vendingMachineController(vendingMachineService service, vendingMachineView view) {
      this.service = service;
      this.view = view;
   }

   public void run() {
      if (!service.initialiseProgram()) {
         view.displayLoadFailure();
         return;
      }
      Collection<Item> stockedItems = service.getStockedItems();

      view.displayStockedItems(stockedItems);

      view.displayOptions();
   }
}
