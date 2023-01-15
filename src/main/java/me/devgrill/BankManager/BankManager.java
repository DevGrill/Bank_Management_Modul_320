package me.devgrill.BankManager;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.Properties;
import java.util.UUID;

public class BankManager {
    String userName;

    public BankManager(String name){
        userName = name;
    }

    Properties p = new Properties();

    //adds Account to the Storage File.
    public void addAccount() {
        p.setProperty("IBAN", UUID.randomUUID().toString());
        p.setProperty("creationDate", LocalDate.now().toString());
        p.setProperty("blocked", "false");
        p.setProperty("balance", "10000");
        try {
            FileWriter fileWriter = new FileWriter(userName + ".data");
            p.store(fileWriter, "");
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Checks if Account with userName already exists
    public boolean checkAccount() {
        try {
            FileReader fileReader = new FileReader(userName + ".data");
            p.load(fileReader);
            String value = p.getProperty("IBAN");
            fileReader.close();
            return value != null;
        } catch (Exception e) {
            return false;
        }
    }

    //Removes an Account from the storage File.
    public boolean removeAccount() {
        try {
            File f = new File(userName + ".data");
            if(f.delete()){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            System.out.println("Es ist ein Fehler aufgetreten. Bitte versuchen sie es Spaeter erneut. \n");
            return false;
        }
    }
}