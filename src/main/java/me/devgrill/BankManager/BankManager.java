package me.devgrill.BankManager;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Properties;

public class BankManager {
    Properties p = new Properties();

    public void addAccount(String name) {
        p.setProperty(name, "10000");
        try {
            p.store(new FileWriter("BankStorage.data"), "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkAccount(String name) {
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
        try {
            p.load(new FileReader("BankStorage.data"));
            p.remove(name);
            p.store(new FileWriter("BankStorage.data"), "");
        }catch (Exception e){
            System.out.println("Es ist ein Fehler aufgetreten. Bitte versuchen sie es Spaeter erneut. \n");
        }
    }
}