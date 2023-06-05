package ru.levelup.bank.system.repository;

import ru.levelup.bank.system.domain.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class JdbcAccountRepository implements AccountRepository {

    private final JdbcConnectionService jdbcConnectionService;

    public JdbcAccountRepository(){
        this.jdbcConnectionService = new JdbcConnectionService();
    }
    @Override
    public Collection<Account> findUserAccounts(long userId) {

        try (Connection conn = jdbcConnectionService.openConnection()){

          PreparedStatement statement = conn.prepareStatement("select * from accounts where client_id = ?");
          statement.setLong(1,userId);

            ResultSet resultSet = statement.executeQuery();

            return mapResultSet(resultSet);


        } catch (SQLException exc) {
            System.out.println("Ошибка взаимодействия с БД");
            System.err.println("Ошибка: " + exc.getMessage());
            exc.printStackTrace();
        }
        return null;
    }

    @Override
    public Account createAccount(String number, String currency, long clientId) {

        try (Connection conn = jdbcConnectionService.openConnection()){
            PreparedStatement findMaxAccountIdStatement =
                    conn.prepareStatement("select max(account_id) from accounts");
            ResultSet maxAccountIdResultSet = findMaxAccountIdStatement.executeQuery();
            maxAccountIdResultSet.next();
            long maxAccountId = maxAccountIdResultSet.getLong(1); // Максимальное значение колонки аккаунт id

            PreparedStatement insertStatement = conn.prepareStatement(
                    "insert into accounts values(?, ?, ?, ?);"
            );

            long accountId = maxAccountId + 1;
            insertStatement.setLong(1, accountId);
            insertStatement.setString(2, number);
            insertStatement.setString(3, currency);
            insertStatement.setLong(4, clientId);

            insertStatement.executeUpdate();

            PreparedStatement selectStatement =
                    conn.prepareStatement("select * from accounts where account_id = ?");
            selectStatement.setLong(1,accountId);
            ResultSet resultSet = selectStatement.executeQuery();
            resultSet.next();
            return mapAccount(resultSet);



        } catch (SQLException exc) {
            System.out.println("Ошибка взаимодействия с БД");
            System.err.println("Ошибка: " + exc.getMessage());
            exc.printStackTrace();
        }
        return null;
    }

    @Override
    public Account updateAccount(long accountId, String number) {
        try (Connection conn = jdbcConnectionService.openConnection()) {

            PreparedStatement updateStatement = conn.prepareStatement("update accounts set number = ? where account_id = ?");
            updateStatement.setString(1,number);
            updateStatement.setLong(2,accountId);

            updateStatement.executeUpdate();

            PreparedStatement selectUpdateStatement = conn.prepareStatement("select * from accounts where account_id = ?");
            selectUpdateStatement.setLong(1,accountId);
            ResultSet resultSet = selectUpdateStatement.executeQuery();
            resultSet.next();
            return mapAccount(resultSet);


        } catch (SQLException exc) {
            System.out.println("Ошибка взаимодействия с БД");
            System.err.println("Ошибка: " + exc.getMessage());
            exc.printStackTrace();
            return null;
        }
    }

    @Override
    public Account deleteAccount(long clientId, long accountId) {
        try (Connection conn = jdbcConnectionService.openConnection()) {

            PreparedStatement deleteStatement = conn.prepareStatement("update accounts set number = null where client_id = ? and account_id = ?");
            deleteStatement.setLong(1, clientId);
            deleteStatement.setLong(2, accountId);

           deleteStatement.executeUpdate();

           PreparedStatement selectDeleteStatement = conn.prepareStatement("select * from accounts where account_id = ?");
            selectDeleteStatement.setLong(1, accountId);
            ResultSet resultSet = selectDeleteStatement.executeQuery();
            resultSet.next();
            return mapAccount(resultSet);
        } catch (SQLException exc) {
            System.out.println("Ошибка взаимодействия с БД");
            System.err.println("Ошибка: " + exc.getMessage());
            exc.printStackTrace();
            return null;
        }
    }

    private Collection<Account> mapResultSet(ResultSet resultSet) throws SQLException{
        Collection<Account> accounts = new ArrayList<>();
        while (resultSet.next()) {
            accounts.add(mapAccount(resultSet));

        }
        return accounts;
    }

    private Account mapAccount(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("account_id");
        String number = resultSet.getString("number");
        String currency = resultSet.getString("currency");

        return new Account(id, number, currency);
    }

}
