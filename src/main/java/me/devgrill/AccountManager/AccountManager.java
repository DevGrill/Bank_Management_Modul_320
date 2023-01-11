package me.devgrill.AccountManager;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Properties;

public class AccountManager {
    String userName;
    Properties p = new Properties();

    public AccountManager(String userNameProvided){
        userName = userNameProvided;
    }

    public String getBalance(){
        Properties p = new Properties();
        try {
            p.load(new FileReader("BankStorage.data"));
            return p.getProperty(userName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "null";
    }

    public void addMoney(Integer moneyToAdd){
        int newMoney = (Integer.parseInt(getBalance()) + moneyToAdd);

        try {
            p.load(new FileReader("BankStorage.data"));
            p.setProperty(userName, Integer.toString(newMoney));
            p.store(new FileWriter("BankStorage.data"), "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeMoney(Integer moneyToRemove){
        int newMoney = (Integer.parseInt(getBalance()) - moneyToRemove);

        try {
            p.load(new FileReader("BankStorage.data"));
            p.setProperty(userName, Integer.toString(newMoney));
            p.store(new FileWriter("BankStorage.data"), "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
