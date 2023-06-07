package ru.levelup.bank.system.action;

import ru.levelup.bank.system.domain.Account;
import ru.levelup.bank.system.menu.ConsoleMenu;
import ru.levelup.bank.system.repository.AccountRepository;
import ru.levelup.bank.system.repository.JdbcAccountRepository;

public class AccountDeleteAction implements ConsoleAction {

    private final AccountRepository accountRepository;

    public AccountDeleteAction () {
        this.accountRepository = new JdbcAccountRepository();
    }
@Override
    public void doAction(){
        System.out.println("--------------");
        Long clientId = ConsoleMenu.readLong("Введите идентификатора клиента");
        Long accountId = ConsoleMenu.readLong("Введите идентификатора счета");

        Account account = accountRepository.deleteAccount(clientId,accountId);

        System.out.println("Удаленный счет");
        System.out.println(account);

    }
}
