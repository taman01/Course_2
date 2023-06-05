package ru.levelup.bank.system.repository;

import ru.levelup.bank.system.domain.Account;

import java.util.Collection;

public interface AccountRepository {

    Collection<Account> findUserAccounts(long userId);

    Account createAccount(String number, String currency, long clientId);

    Account updateAccount(long accountId,String number);

    Account deleteAccount(long clientId, long accountId);


}
