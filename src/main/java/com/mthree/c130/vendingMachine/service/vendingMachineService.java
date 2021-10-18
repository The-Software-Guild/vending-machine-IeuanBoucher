package com.mthree.c130.vendingMachine.service;

import com.mthree.c130.vendingMachine.dao.vendingMachineDao;
import com.mthree.c130.vendingMachine.dto.Item;

import java.util.Collection;

public class vendingMachineService {
   private final vendingMachineDao dao;

   public vendingMachineService(vendingMachineDao dao) {
      this.dao = dao;
   }

   public Collection<Item> getStockedItems() {
      Collection<Item> items = dao.getAllItems();
      items.removeIf(i -> i.getRemainingStock() == 0);
      return items;
   }

   public boolean initialiseProgram() {
      return dao.loadData();
   }
}
