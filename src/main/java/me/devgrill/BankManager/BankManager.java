package me.devgrill.BankManager;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Properties;

public class BankManager {
    public void addAccount(String name) {
        Properties p = new Properties();
        p.setProperty(name, "10000");
        try {
            p.store(new FileWriter("BankStorage.data"), "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkAccount(String name) {
        Properties p = new Properties();
        try {
            p.load(new FileReader("BankStorage.data"));
            String value = p.getProperty(name);
            return value != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void removeAccount(String name) {
        Properties p = new Properties();
        p.remove(name);
    }
}