package ru.levelup.bank.system.action;

import ru.levelup.bank.system.domain.Account;
import ru.levelup.bank.system.menu.ConsoleMenu;
import ru.levelup.bank.system.repository.AccountRepository;
import ru.levelup.bank.system.repository.JdbcAccountRepository;

import java.util.Collection;

public class AccountListingAction implements ConsoleAction {

    private final AccountRepository accountRepository;

    public AccountListingAction () {
        this.accountRepository = new JdbcAccountRepository();
    }
    @Override
    public void doAction(){
        //Показать список счетов пользователя...
        //1. Получаем идентификатор пользователя

        Long userId = ConsoleMenu.readLong("Введите идентификатор пользователя");
        if (userId == null){
            System.out.println("Некорректный идентификатор пользователя");
            return;
        }
        //2. Делаем запрос в БД и получаем список счетов пользователя
        Collection<Account> accounts = accountRepository.findUserAccounts(userId);
        // 3. Выведем на эркан список счетов

        for (Account account:accounts){
            System.out.println(account);
        }
    }
}
