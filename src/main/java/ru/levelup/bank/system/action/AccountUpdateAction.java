package ru.levelup.bank.system.action;

import ru.levelup.bank.system.domain.Account;
import ru.levelup.bank.system.menu.ConsoleMenu;
import ru.levelup.bank.system.repository.AccountRepository;
import ru.levelup.bank.system.repository.JdbcAccountRepository;

public class AccountUpdateAction {

    private final AccountRepository accountRepository;

    public AccountUpdateAction () {
        this.accountRepository = new JdbcAccountRepository();
    }

    public void doAction(){
        System.out.println("---------------");
        Long accountId = ConsoleMenu.readLong("Введите идентификатор счета, который необходимо изменить");
        String number = ConsoleMenu.readString("Введите новый номер счета");


        Account account = accountRepository.updateAccount(accountId,number);

        System.out.println("Измененный счет");
        System.out.println(account);

    }

}
