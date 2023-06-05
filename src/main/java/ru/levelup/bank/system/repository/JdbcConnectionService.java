package ru.levelup.bank.system.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnectionService {

        public Connection openConnection() throws SQLException {
           return DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/bank-system",
                    "postgres",
                    "123_Barracus"
            );
        }

}
