package me.devgrill.BankManager;

import java.io.FileWriter;
import java.util.Properties;

public class BankManager {
    public void addAccount(String name) {
        Properties p = new Properties();
        p.setProperty(name, "10000");
        try {
        p.store(new FileWriter("BankStorage.data"), "");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
