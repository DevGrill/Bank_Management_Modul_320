package me.devgrill;

import me.devgrill.BankManager.BankManager;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        BankManager bankManager = new BankManager();
        System.out.println(bankManager.returnAccount(1));
    }
}