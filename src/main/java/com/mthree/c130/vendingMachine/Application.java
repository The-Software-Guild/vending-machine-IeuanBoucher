package com.mthree.c130.vendingMachine;

import com.mthree.c130.vendingMachine.controller.VendingMachineController;
import com.mthree.c130.vendingMachine.dao.VendingMachineAuditDao;
import com.mthree.c130.vendingMachine.dao.VendingMachineAuditDaoImplementation;
import com.mthree.c130.vendingMachine.dao.VendingMachineDao;
import com.mthree.c130.vendingMachine.dao.VendingMachineDaoFileImplementation;
import com.mthree.c130.vendingMachine.service.VendingMachineService;
import com.mthree.c130.vendingMachine.ui.UserIO;
import com.mthree.c130.vendingMachine.ui.UserIOConsoleImplementation;
import com.mthree.c130.vendingMachine.ui.VendingMachineView;

public class Application {
   public static void main(String[] args) {
      UserIO io = new UserIOConsoleImplementation();
      VendingMachineView view = new VendingMachineView(io);

      VendingMachineDao dao = new VendingMachineDaoFileImplementation();
      VendingMachineAuditDao auditDao = new VendingMachineAuditDaoImplementation();
      VendingMachineService service = new VendingMachineService(dao, auditDao);

      VendingMachineController controller = new VendingMachineController(service, view);

      controller.run();
   }
}
