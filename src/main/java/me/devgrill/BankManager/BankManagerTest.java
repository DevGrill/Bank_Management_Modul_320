package me.devgrill.BankManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BankManagerTest {

    @Test()
    void addAccount() {
        BankManager bankManager = new BankManager("Test");
        Assertions.assertDoesNotThrow(bankManager::addAccount);
        Assertions.assertTrue(bankManager.checkAccount());
        bankManager.removeAccount();
    }

    @Test
    void checkAccount() {
        BankManager bankManager = new BankManager("Test");
        bankManager.addAccount();
        Assertions.assertTrue(bankManager.checkAccount());
        bankManager.removeAccount();
        Assertions.assertFalse(bankManager.checkAccount());
    }

    @Test
    void removeAccount() {
        BankManager bankManager = new BankManager("Test");
        bankManager.addAccount();
        Assertions.assertDoesNotThrow(bankManager::removeAccount);
        Assertions.assertFalse(bankManager.checkAccount());
    }
}