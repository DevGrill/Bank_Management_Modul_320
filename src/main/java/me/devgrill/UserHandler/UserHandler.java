package me.devgrill.UserHandler;

import me.devgrill.AccountManager.AccountManager;
import me.devgrill.BankManager.BankManager;

import java.util.Scanner;

public class UserHandler {

    static String userName;

    //This starts a new Session with a userName
    public void startUserSession() {
        askForName();
        BankManager bankManager = new BankManager(userName);
        boolean accountExits = bankManager.checkAccount();
        if (!accountExits) {
            bankManager.addAccount();
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
        System.out.println("Schreibe 'info' um Infos ueber deinen Account anzuzeigen.");
        System.out.println("Schreibe 'addMoney {Amount}' um dein Guthaben anzuzeigen.");
        System.out.println("Schreibe 'removeMoney {Amount}' um dein Guthaben anzuzeigen.");
        System.out.println("Schreibe 'deleteAccount' um deinen Account zu loeschen.");
        System.out.println("Schreibe 'blockAccount' um deinen Account zu blockieren.");
        System.out.println("Schreibe 'unblockAccount' um deinen Account freizuschalten.");
        handleUserInputs();
    }

    //Handles Command with Arguments from the User.
    private static void handleUserInputs() {
        BankManager bankManager = new BankManager(userName);
        Scanner input = new Scanner(System.in);
        AccountManager accountManager = new AccountManager(userName);

        String command = input.nextLine();
        String args[] = command.split(" ", 2);
        switch (args[0]) {
            //Gets Balance.
            case "info":
                System.out.println("Guten tag " + accountManager.getOwner());
                System.out.println("Deine IBAN Nummer ist: " + accountManager.getIBAN());
                System.out.println("Dein Account sperr Status ist: " + accountManager.isAccountBlocked());
                System.out.println("Dein Account wurde an folgendem Datum erstellt: " + accountManager.getAccountCreationDate());
                System.out.println("Dein Guthaben betraegt: " + accountManager.getBalance() + "\n");
                break;

            //Deletes user Account.
            case "deleteAccount":
                Boolean result = bankManager.removeAccount();
                if (result) {
                    System.out.println("Dein Account wurde erfolgreich entfernt. \n");
                } else {
                    System.out.println("Dein Account konnte nicht entfernt werden. \n");
                }
                UserHandler userHandler = new UserHandler();
                userHandler.startUserSession();
                break;

            //checks Arguments and calls addMoney function.
            case "addMoney":
                if (args.length == 2) {
                    try {
                        int amountToAdd = Integer.parseInt(args[1]);
                        accountManager.addMoney(amountToAdd);
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
                    } catch (Exception e) {
                        invalidInput();
                    }

                } else {
                    invalidInput();
                }
                break;

            case "blockAccount":
                accountManager.setBlocked(true);
                System.out.println("Dein Account wurde blockiert.");
                break;

            case "unblockAccount":
                accountManager.setBlocked(false);
                System.out.println("Dein Account wurde freigeschalten.");
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
