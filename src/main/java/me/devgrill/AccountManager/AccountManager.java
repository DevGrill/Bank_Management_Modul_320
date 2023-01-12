package me.devgrill.AccountManager;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Date;
import java.util.Properties;

public class AccountManager {
    String userName;
    String IBANNumber;
    String balance;
    Date creationDate;
    Boolean blocked;
    Properties p = new Properties();

    //Stores Username for other Functions
    public AccountManager(String userNameProvided){
        userName = userNameProvided;
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
        try {
            p.load(new FileReader(userName + ".data"));
            balance = p.getProperty(userName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return balance;
    }

    //Returns Account Creation Date
    public Date getAccountCreation(){
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
            p.setProperty(userName, Integer.toString(newMoney));
            p.store(new FileWriter("BankStorage.data"), "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Removes Money to userNames account.
    public void removeMoney(Integer moneyToRemove){
        int newMoney = (Integer.parseInt(getBalance()) - moneyToRemove);

        try {
            p.setProperty(userName, Integer.toString(newMoney));
            p.store(new FileWriter("BankStorage.data"), "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setBlocked(boolean newBlockedStatus){
        blocked = newBlockedStatus;
    }
}
