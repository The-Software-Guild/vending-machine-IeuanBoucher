package com.mthree.c130.vendingMachine.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class vendingMachineAuditDaoImplementation implements vendingMachineAuditDao {
   private final String AUDIT_LOG_FILENAME = "src/main/resources/auditLog.txt";

   @Override
   public void writeAuditEntry(String event) throws IOException {
      PrintWriter out;

      try {
         out = new PrintWriter(new FileWriter(AUDIT_LOG_FILENAME, true));
      } catch (IOException e) {
         throw new IOException("Could not log purchase event.");
      }

      LocalDateTime timestamp = LocalDateTime.now();
      out.println(timestamp + " : " + event);
      out.flush();
   }
}
