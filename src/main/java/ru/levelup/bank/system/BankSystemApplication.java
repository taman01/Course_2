package ru.levelup.bank.system;

import ru.levelup.bank.system.action.*;
import ru.levelup.bank.system.menu.ConsoleMenu;

public class BankSystemApplication {

    public static void main(String[] args) {
        Long command = null;
        do {
            ConsoleMenu.printGeneralMenu();
            command = ConsoleMenu.readLong("Введите норме команды");

            ConsoleAction action = ConsoleActionFactory.findAction(command);
            if (action != null){
                action.doAction();
            }else {
                System.out.println("Вы ввели неправильный номер команды");
            }

        }while (command == null || command !=0);
    }
}
