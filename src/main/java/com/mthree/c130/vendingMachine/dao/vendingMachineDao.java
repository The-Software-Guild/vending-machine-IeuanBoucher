package com.mthree.c130.vendingMachine.dao;

import com.mthree.c130.vendingMachine.dto.Item;

import java.util.Collection;

public interface vendingMachineDao {

   Collection<Item> getAllItems();

   Item getItem(String name);

   boolean loadData();

   boolean saveData();
}