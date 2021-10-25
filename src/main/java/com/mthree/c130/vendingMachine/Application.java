package com.mthree.c130.vendingMachine;

import com.mthree.c130.vendingMachine.controller.VendingMachineController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {
   public static void main(String[] args) {

      ApplicationContext appContext
              = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

      VendingMachineController controller = appContext.getBean("controller", VendingMachineController.class);
      controller.run();
   }
}
