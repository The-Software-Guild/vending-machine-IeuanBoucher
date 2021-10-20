package com.mthree.c130.vendingMachine.dao;

import java.io.IOException;

public interface VendingMachineAuditDao {
   void writeAuditEntry(String event) throws IOException;
}
