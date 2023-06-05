package ru.levelup.bank.system.menu;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ConsoleMenu {

    public static final BufferedReader CONSOLE_READER = new BufferedReader(new InputStreamReader(System.in));

    private ConsoleMenu(){}

    public static void printGeneralMenu(){
        System.out.println("----------------");
        System.out.println("1. Показать все счета пользователя");
        System.out.println("2. Открыть новый счет");
        System.out.println("3. Изменить счет");
        System.out.println("4. Удалить счет");
        System.out.println();
    }

    public static Long readLong(String message){
        System.out.println(message);
        try {
            return Long.parseLong(CONSOLE_READER.readLine());
        } catch (Exception exc) {
            return null;
        }
    }

    public static String readString(String message) {
        System.out.println(message);
        try {
            return CONSOLE_READER.readLine();
        } catch (Exception exc) {
            return null;
        }
    }
}
