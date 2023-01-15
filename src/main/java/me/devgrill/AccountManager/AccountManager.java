package me.devgrill.AccountManager;

import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class AccountManager {
    String userName;
    String IBANNumber;
    String balance;
    LocalDate creationDate;
    Boolean blocked;
    Properties p = new Properties();

    //Stores Username for other Functions
    public AccountManager(String userNameProvided) {
        userName = userNameProvided;
        try {
            FileReader fileReader = new FileReader(userName + ".data");
            p.load(fileReader);
            IBANNumber = p.getProperty("IBAN");
            balance = p.getProperty("balance");
            creationDate = LocalDate.parse(p.getProperty("creationDate"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            blocked = Boolean.parseBoolean(p.getProperty("blocked"));
            fileReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Returns accounts IBAN number
    public String getIBAN() {
        return IBANNumber;
    }

    //returns Owners userName
    public String getOwner() {
        return userName;
    }

    //Returns balance from userNames Account
    public String getBalance() {
        return balance;
    }

    //Returns Account Creation Date
    public LocalDate getAccountCreationDate() {
        return creationDate;
    }

    //returns Boolean if account is blocked or not
    public boolean isAccountBlocked() {
        return blocked;
    }

    //Adds Money to the userNames account.
    public void addMoney(Integer moneyToAdd) {
        if (isAccountBlocked()) {
            System.out.println("Dein Account ist blockiert. Transaktion abgebrochen!");
        } else {
            int newMoney = (Integer.parseInt(getBalance()) + moneyToAdd);
            balance = Integer.toString(newMoney);
            saveFile();
            System.out.println("Geld wurde hinzugefuegt. \n");
        }
    }

    //Removes Money to userNames account.
    public void removeMoney(Integer moneyToRemove) {
        if (isAccountBlocked()) {
            System.out.println("Dein Account ist blockiert. Transaktion abgebrochen!");
        } else {
            int newMoney = (Integer.parseInt(getBalance()) - moneyToRemove);
            balance = Integer.toString(newMoney);
            saveFile();
            System.out.println("Geld wurde entfernt. \n");
        }
    }

    public void setBlocked(boolean newBlockedStatus) {
        blocked = newBlockedStatus;
        saveFile();
    }

    //Saves properties in File
    private void saveFile() {
        try {
            FileWriter fileWriter = new FileWriter(userName + ".data");
            p.setProperty("balance", balance);
            p.setProperty("blocked", Boolean.toString(blocked));
            p.store(fileWriter, "");
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
