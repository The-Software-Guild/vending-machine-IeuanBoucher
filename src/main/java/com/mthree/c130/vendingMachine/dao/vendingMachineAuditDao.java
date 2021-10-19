package com.mthree.c130.vendingMachine.dao;

import java.io.IOException;

public interface vendingMachineAuditDao {
   void writeAuditEntry(String event) throws IOException;
}
