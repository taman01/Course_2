package ru.levelup.bank.system;

import ru.levelup.bank.system.action.AccountCreationAction;
import ru.levelup.bank.system.action.AccountDeleteAction;
import ru.levelup.bank.system.action.AccountListingAction;
import ru.levelup.bank.system.action.AccountUpdateAction;
import ru.levelup.bank.system.menu.ConsoleMenu;

public class BankSystemApplication {

    public static void main(String[] args) {
        ConsoleMenu.printGeneralMenu();
        Long command = ConsoleMenu.readLong("Введите норме команды");
        if (command != null && command == 1) {
            new AccountListingAction().doAction();
        } else if (command != null && command == 2) {
            new AccountCreationAction().doAction();
        }else if (command != null && command == 3) {
            new AccountUpdateAction().doAction();
        }   else if (command != null && command == 4) {
                new AccountDeleteAction().doAction();
        } else {
            System.out.println("Вы ввели неправильный номер команды");
        }
    }
}
