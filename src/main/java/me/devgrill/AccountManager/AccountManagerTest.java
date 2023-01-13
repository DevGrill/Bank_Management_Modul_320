package me.devgrill.AccountManager;

import me.devgrill.BankManager.BankManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AccountManagerTest {

    @Test
    void getIBAN(){
        BankManager bankManager = new BankManager();
        bankManager.addAccount("Test");
        AccountManager accountManager = new AccountManager("Test");
        Assertions.assertNotNull(accountManager.getIBAN());
        bankManager.removeAccount("Test");
    }

    @Test
    void getOwner(){
        BankManager bankManager = new BankManager();
        bankManager.addAccount("Test");
        AccountManager accountManager = new AccountManager("Test");
        Assertions.assertNotNull(accountManager.getOwner());
        bankManager.removeAccount("Test");
    }

    @Test
    void getBalance() {
        BankManager bankManager = new BankManager();
        bankManager.addAccount("Test");
        AccountManager accountManager = new AccountManager("Test");
        Assertions.assertNotNull(accountManager.getOwner());
        bankManager.removeAccount("Test");
    }

    @Test
    void getAccountCreationDate(){
        BankManager bankManager = new BankManager();
        bankManager.addAccount("Test");
        AccountManager accountManager = new AccountManager("Test");
        Assertions.assertNotNull(accountManager.getAccountCreationDate());
        bankManager.removeAccount("Test");
    }

    @Test
    void isAccountBlocked(){
        BankManager bankManager = new BankManager();
        bankManager.addAccount("Test");
        AccountManager accountManager = new AccountManager("Test");
        bankManager.removeAccount("Test");
    }

    @Test
    void addMoney() {
        BankManager bankManager = new BankManager();
        bankManager.addAccount("Test");
        AccountManager accountManager = new AccountManager("Test");
        Assertions.assertDoesNotThrow(() -> accountManager.addMoney(10000));
        Assertions.assertThrows(Exception.class, () -> accountManager.addMoney(Integer.parseInt("Test")));
        Assertions.assertEquals("20000", accountManager.getBalance());
        bankManager.removeAccount("Test");
    }

    @Test
    void removeMoney() {
        BankManager bankManager = new BankManager();
        bankManager.addAccount("Test");
        AccountManager accountManager = new AccountManager("Test");
        Assertions.assertDoesNotThrow(() -> accountManager.removeMoney(10000));
        Assertions.assertThrows(Exception.class, () -> accountManager.removeMoney(Integer.parseInt("Test")));
        Assertions.assertEquals("0", accountManager.getBalance());
        bankManager.removeAccount("Test");
    }

    @Test
    void setBlocked(){
        BankManager bankManager = new BankManager();
        bankManager.addAccount("Test");
        AccountManager accountManager = new AccountManager("Test");
        Assertions.assertDoesNotThrow(() -> accountManager.setBlocked(false));
        Assertions.assertDoesNotThrow(() -> accountManager.setBlocked(true));
        Assertions.assertEquals(true, accountManager.isAccountBlocked());
        bankManager.removeAccount("Test");
    }
}