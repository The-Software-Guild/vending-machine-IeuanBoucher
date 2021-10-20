package com.mthree.c130.vendingMachine.service;

import com.mthree.c130.vendingMachine.dao.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class VendingMachineServiceTest {

   final VendingMachineDao dao = new VendingMachineDaoFileImplementationStub();
   final VendingMachineAuditDao auditDao = new VendingMachineAuditDaoStubImplementation();
   final VendingMachineService service = new VendingMachineService(dao, auditDao);

   @BeforeEach
   void resetData() {
      dao.loadData();
      service.setMoney(new BigDecimal("5.00"));
   }

   @Test
   void purchaseNonExistentItem() {
      assertThrows(ServiceLayerExceptions.NoItemInventoryException.class, () -> service.attemptPurchase(""));
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

      assertThrows(ServiceLayerExceptions.InsufficientFundsException.class, () -> service.attemptPurchase("pepsi"));
   }
}