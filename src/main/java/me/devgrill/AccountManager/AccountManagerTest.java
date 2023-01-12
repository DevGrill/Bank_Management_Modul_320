package me.devgrill.AccountManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AccountManagerTest {

    @Test
    void getBalance() {
        AccountManager accountManager = new AccountManager("Test");
        Assertions.assertNotEquals("null", accountManager.getBalance());
    }

    @Test
    void addMoney() {
        AccountManager accountManager = new AccountManager("Test");
        Assertions.assertDoesNotThrow(() -> accountManager.addMoney(10000));
        Assertions.assertThrows(Exception.class, () -> accountManager.addMoney(Integer.parseInt("Test")));
    }

    @Test
    void removeMoney() {
        AccountManager accountManager = new AccountManager("Test");
        Assertions.assertDoesNotThrow(() -> accountManager.removeMoney(10000));
        Assertions.assertThrows(Exception.class, () -> accountManager.removeMoney(Integer.parseInt("Test")));
    }
}