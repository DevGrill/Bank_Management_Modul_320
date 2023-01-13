package me.devgrill.BankManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BankManagerTest {

    @Test()
    void addAccount() {
        BankManager bankManager = new BankManager();
        Assertions.assertDoesNotThrow(() -> bankManager.addAccount("Test"));
        Assertions.assertTrue(bankManager.checkAccount("Test"));
        bankManager.removeAccount("Test");
    }

    @Test
    void checkAccount() {
        BankManager bankManager = new BankManager();
        bankManager.addAccount("Test");
        Assertions.assertTrue(bankManager.checkAccount("Test"));
        bankManager.removeAccount("Test");
        Assertions.assertFalse(bankManager.checkAccount("Test"));
    }

    @Test
    void removeAccount() {
        BankManager bankManager = new BankManager();
        bankManager.addAccount("Test");
        Assertions.assertDoesNotThrow(() -> bankManager.removeAccount("Test"));
        Assertions.assertFalse(bankManager.checkAccount("Test"));
    }
}