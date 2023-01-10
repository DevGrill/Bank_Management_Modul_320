package me.devgrill.BankManager;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Properties;

public class BankManager {
    public void addAccount() {
        Properties p = new Properties();
        p.setProperty("1", "12345");
        try {
        p.store(new FileWriter("BankStorage.data"), "");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String returnAccount(Integer accountIdProvided) {
        Properties p = new Properties();
        try {
            p.load(new FileReader("BankStorage.data"));
            return p.getProperty("1");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "null";
    }
}
