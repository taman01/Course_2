package ru.levelup.bank.system.action;

import ru.levelup.bank.system.domain.Account;
import ru.levelup.bank.system.menu.ConsoleMenu;
import ru.levelup.bank.system.repository.AccountRepository;
import ru.levelup.bank.system.repository.JdbcAccountRepository;

public class AccountCreationAction {

    private final AccountRepository accountRepository;

    public AccountCreationAction(){
        this.accountRepository = new JdbcAccountRepository();
    }

    public void doAction(){
        System.out.println("---------------");
        String number = ConsoleMenu.readString("Введите номер счета");
        String currency = ConsoleMenu.readString("Введите валюту");
        Long clientId = ConsoleMenu.readLong("Введите идентификатор клиента");

        Account account = accountRepository.createAccount(number,currency,clientId);

        System.out.println("Добавленный счет");
        System.out.println(account);

    }

}
