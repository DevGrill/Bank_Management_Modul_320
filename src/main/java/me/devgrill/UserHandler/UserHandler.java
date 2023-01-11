package me.devgrill.UserHandler;

import me.devgrill.AccountManager.AccountManager;
import me.devgrill.BankManager.BankManager;

import java.util.Scanner;

public class UserHandler {
    static BankManager bankManager = new BankManager();

    static String userName;

    public void startUserSession() {
        askForName();
        boolean accountExits = bankManager.checkAccount(userName);
        if (!accountExits) {
            bankManager.addAccount(userName);
            System.out.println("Dein Account auf den Namen " + userName + " wurde erfolgreich angelegt. \n");
        }
        sendUserCommands();
    }

    private static void askForName() {
        Scanner input = new Scanner(System.in);
        System.out.println("Wie heist du?");
        userName = input.next();
    }

    private static void sendUserCommands() {
        System.out.println("Schreibe 'balance' um dein Guthaben anzuzeigen.");
        System.out.println("Schreibe 'addMoney {Amount}' um dein Guthaben anzuzeigen.");
        System.out.println("Schreibe 'removeMoney {Amount}' um dein Guthaben anzuzeigen.");
        System.out.println("Schreibe 'deleteAccount' um deinen Account zu löschen.");
        handleUserInputs();
    }

    private static void handleUserInputs() {
        Scanner input = new Scanner(System.in);
        AccountManager accountManager = new AccountManager(userName);

        String command = input.nextLine();
        String args[] = command.split(" ", 2);
        switch (args[0]) {
            case "balance":
                String bal = accountManager.getBalance();
                System.out.println("Dein Guthaben betraegt: " + bal + "\n");
                break;

            case "deleteAccount":
                    bankManager.removeAccount(userName);
                    System.out.println("Dein Account wurde erfolgreich entfernt. \n");
                    UserHandler userHandler = new UserHandler();
                    userHandler.startUserSession();
                break;

            case "addMoney":
                if (args.length == 2) {
                    try {
                        int amountToAdd = Integer.parseInt(args[1]);
                        accountManager.addMoney(amountToAdd);
                        System.out.println("Geld wurde hinzugefuegt. \n");
                    } catch (Exception e) {
                        invalidInput();
                    }
                } else {
                    invalidInput();
                }
                break;

            case "removeMoney":
                if (args.length == 2) {
                    try {
                        int amountToRemove = Integer.parseInt(args[1]);
                        accountManager.removeMoney(amountToRemove);
                        System.out.println("Geld wurde entfernt. \n");
                    } catch (Exception e) {
                        invalidInput();
                    }

                } else {
                    invalidInput();
                }
                break;

            default:
                invalidInput();
                break;
        }
        sendUserCommands();
    }

    private static void invalidInput() {
        System.out.println("Ungueltige Eingabe\n");
    }
}
