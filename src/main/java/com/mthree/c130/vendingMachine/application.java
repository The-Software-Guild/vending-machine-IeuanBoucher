package com.mthree.c130.vendingMachine;

import com.mthree.c130.vendingMachine.controller.vendingMachineController;
import com.mthree.c130.vendingMachine.dao.vendingMachineDao;
import com.mthree.c130.vendingMachine.dao.vendingMachineDaoFileImplementation;
import com.mthree.c130.vendingMachine.service.vendingMachineService;
import com.mthree.c130.vendingMachine.ui.UserIO;
import com.mthree.c130.vendingMachine.ui.UserIOConsoleImplementation;
import com.mthree.c130.vendingMachine.ui.vendingMachineView;

public class application {
   public static void main(String[] args) {
      UserIO io = new UserIOConsoleImplementation();
      vendingMachineView view = new vendingMachineView(io);

      vendingMachineDao dao = new vendingMachineDaoFileImplementation();
      vendingMachineService service = new vendingMachineService(dao);

      vendingMachineController controller = new vendingMachineController(service, view);

      controller.run();
   }
}
