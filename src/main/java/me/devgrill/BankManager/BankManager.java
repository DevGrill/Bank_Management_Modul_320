package me.devgrill.BankManager;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

public class BankManager {
    Properties p = new Properties();

    //adds Account to the Storage File.
    public void addAccount(String name) {
        p.setProperty("IBAN", UUID.randomUUID().toString());
        p.setProperty("creationDate", LocalDateTime.now().toString());
        p.setProperty("blocked", "false");
        p.setProperty("balance", "10000");
        try {
            p.store(new FileWriter(name + ".data"), "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Checks if Account with userName already exists
    public boolean checkAccount(String name) {
        try {
            p.load(new FileReader(name + ".data"));
            String value = p.getProperty("IBAN");
            return value != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //Removes an Account from the storage File.
    public void removeAccount(String name) {
        try {

            File f = new File(name + ".data");
            f.delete();
        }catch (Exception e){
            System.out.println("Es ist ein Fehler aufgetreten. Bitte versuchen sie es Spaeter erneut. \n");
        }
    }
}