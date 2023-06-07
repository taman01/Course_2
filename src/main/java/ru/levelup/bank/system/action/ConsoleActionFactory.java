package ru.levelup.bank.system.action;

import java.util.HashMap;
import java.util.Map;

public class ConsoleActionFactory {

    private static final Map<Long, ConsoleAction> CONSOLE_ACTION_MAP = new HashMap<>();

    static {
        CONSOLE_ACTION_MAP.put(1L, new AccountListingAction());
        CONSOLE_ACTION_MAP.put(2L, new AccountCreationAction());
        CONSOLE_ACTION_MAP.put(3L, new AccountUpdateAction());
        CONSOLE_ACTION_MAP.put(4L, new AccountDeleteAction());
    }
    private ConsoleActionFactory(){}

    public static ConsoleAction findAction(Long command){
        if(command == null){
            return null;
        }

        return CONSOLE_ACTION_MAP.get(command);
    }

}
