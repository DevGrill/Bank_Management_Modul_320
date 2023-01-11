package me.devgrill.UserHandler;

import me.devgrill.AccountManager.AccountManager;
import me.devgrill.BankManager.BankManager;

import java.util.Scanner;

public class UserHandler {
    static BankManager bankManager = new BankManager();

    static String userName;

    //This starts a new Session with a userName
    public void startUserSession() {
        askForName();
        boolean accountExits = bankManager.checkAccount(userName);
        if (!accountExits) {
            bankManager.addAccount(userName);
            System.out.println("Dein Account auf den Namen " + userName + " wurde erfolgreich angelegt. \n");
        }
        sendUserCommands();
    }

    //Asks user for his Name to use.
    private static void askForName() {
        Scanner input = new Scanner(System.in);
        System.out.println("Wie heist du?");
        userName = input.next();
    }

    //Sends available commands to User.
    private static void sendUserCommands() {
        System.out.println("Schreibe 'balance' um dein Guthaben anzuzeigen.");
        System.out.println("Schreibe 'addMoney {Amount}' um dein Guthaben anzuzeigen.");
        System.out.println("Schreibe 'removeMoney {Amount}' um dein Guthaben anzuzeigen.");
        System.out.println("Schreibe 'deleteAccount' um deinen Account zu löschen.");
        handleUserInputs();
    }

    //Handles Command with Arguments from the User.
    private static void handleUserInputs() {
        Scanner input = new Scanner(System.in);
        AccountManager accountManager = new AccountManager(userName);

        String command = input.nextLine();
        String args[] = command.split(" ", 2);
        switch (args[0]) {
            //Gets Balance.
            case "balance":
                String bal = accountManager.getBalance();
                System.out.println("Dein Guthaben betraegt: " + bal + "\n");
                break;

            //Deletes user Account.
            case "deleteAccount":
                bankManager.removeAccount(userName);
                System.out.println("Dein Account wurde erfolgreich entfernt. \n");
                UserHandler userHandler = new UserHandler();
                userHandler.startUserSession();
                break;

            //checks Arguments and calls addMoney function.
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

            //checks Arguments and calls removeMoney function.
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
        //Restarts Function for user to send next Command.
        sendUserCommands();
    }

    //Responds with an Error for input. Class exists to prevent redundancy.
    private static void invalidInput() {
        System.out.println("Ungueltige Eingabe\n");
    }
}
