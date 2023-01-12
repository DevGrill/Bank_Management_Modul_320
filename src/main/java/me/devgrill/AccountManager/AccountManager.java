package me.devgrill.AccountManager;

import me.devgrill.BankManager.BankManager;

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
    public AccountManager(String userNameProvided){
        userName = userNameProvided;
        try {
            p.load(new FileReader(userName + ".data"));
            IBANNumber = p.getProperty("IBAN");
            balance = p.getProperty("balance");
            creationDate = LocalDate.parse(p.getProperty("creationDate"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            blocked = Boolean.parseBoolean(p.getProperty("blocked"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Returns accounts IBAN number
    public String getIBAN(){
        return IBANNumber;
    }

    //returns Owners userName
    public String getOwner(){
        return userName;
    }

    //Returns balance from userNames Account
    public String getBalance(){
        return balance;
    }

    //Returns Account Creation Date
    public LocalDate getAccountCreation(){
        return creationDate;
    }

    //returns Boolean if account is blocked or not
    public boolean isAccountBlocked(){
        return blocked;
    }

    //Adds Money to the userNames account.
    public void addMoney(Integer moneyToAdd){
        int newMoney = (Integer.parseInt(getBalance()) + moneyToAdd);

        try {
            p.setProperty("balance", Integer.toString(newMoney));
            p.store(new FileWriter(userName + ".data"), "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Removes Money to userNames account.
    public void removeMoney(Integer moneyToRemove){
        int newMoney = (Integer.parseInt(getBalance()) - moneyToRemove);
        try {
            p.setProperty("balance", Integer.toString(newMoney));
            p.store(new FileWriter(userName + ".data"), "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setBlocked(boolean newBlockedStatus){
        try {
            p.setProperty("blocked", Boolean.toString(newBlockedStatus));
            p.store(new FileWriter(userName + ".data"), "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
