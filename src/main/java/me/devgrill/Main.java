package me.devgrill;

import me.devgrill.AccountManager.AccountManager;
import me.devgrill.BankManager.BankManager;

public class Main {
    BankManager bankManager = new BankManager();
    public static void main(String[] args) {
        System.out.println("Hello world!");
        AccountManager accountManager = new AccountManager("1");
        String BalanceNow = accountManager.getBalance();
        System.out.println(BalanceNow);
        accountManager.addMoney(10000000);
        BalanceNow = accountManager.getBalance();
        System.out.println(BalanceNow);
        accountManager.removeMoney(10000000);
        BalanceNow = accountManager.getBalance();
        System.out.println(BalanceNow);
    }
}