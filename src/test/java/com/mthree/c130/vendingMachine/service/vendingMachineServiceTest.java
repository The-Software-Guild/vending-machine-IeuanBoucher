package com.mthree.c130.vendingMachine.service;

import com.mthree.c130.vendingMachine.dao.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class vendingMachineServiceTest {

   final vendingMachineDao dao = new vendingMachineDaoFileImplementationStub();
   final vendingMachineAuditDao auditDao = new vendingMachineAuditDaoStubImplementation();
   final vendingMachineService service = new vendingMachineService(dao, auditDao);

   @BeforeEach
   void resetData() {
      dao.loadData();
      service.setMoney(new BigDecimal("5.00"));
   }

   @Test
   void purchaseNonExistentItem() {
      assertThrows(serviceLayerExceptions.NoItemInventoryException.class, () -> service.attemptPurchase(""));
   }

   @Test
   void purchaseInStockItem() {
      try {
         service.attemptPurchase("pepsi");
      } catch (Exception e) {
         System.err.println(e.getMessage());
         fail();
      }
   }

   @Test
   void decreaseStockOfValidItem() {
      int initialStock = dao.getItem("pepsi").getRemainingStock();
      try {
         service.attemptPurchase("pepsi");
      } catch (Exception e) {
         fail();
      }
      int newStock = dao.getItem("pepsi").getRemainingStock();

      assertEquals(initialStock - 1, newStock);
   }

   @Test
   void purchaseWithInsufficientMoney() {
      service.setMoney(new BigDecimal("0.00"));

      assertThrows(serviceLayerExceptions.InsufficientFundsException.class, () -> service.attemptPurchase("pepsi"));
   }
}