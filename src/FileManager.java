import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {

    private static final String FILE_PATH_DATABASE = "/Users/mindera/IdeaProjects/untitled3/src/database.txt";
    private static final String FILE_PATH_LOGS = "/Users/mindera/IdeaProjects/untitled3/src/logs.txt";

    static public boolean createDatabase() {
        try {
            File dataStore = new File(FILE_PATH_DATABASE);
            File dataStore1 = new File(FILE_PATH_LOGS);
            dataStore1.createNewFile();
            if (dataStore.createNewFile()) {
                System.out.println("No previous database found. Creating a new one.");
                return true;
            } else {
                System.out.println("Loading previous database...");
                return false;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    static public boolean writeDatabase(String toWrite) {

        try {

            FileWriter fw = new FileWriter(FILE_PATH_DATABASE,false);
            fw.write (toWrite.replaceAll(" ", "").replaceAll("," , "\n").replaceAll("]","").replaceAll("\\[",""));
            fw.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return true;
    }
    static public ArrayList<Account> readDatabase() {
        try {
            File obj = new File(FILE_PATH_DATABASE);
            Scanner reader = new Scanner(obj);
            ArrayList<Account> accounts = new ArrayList<>();
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] splitString = line.split("/");
                Account account = new Account(splitString[0], splitString[1], Integer.parseInt(splitString[2]), (splitString[3] == "true")? true:false);
                accounts.add(account);
            }
            reader.close();
            return accounts;
        } catch (IOException e) {
            System.out.println("Something went wrong reading database.");
        }
        return null;
    }

    static public void writeLog(String who, int amount,String action ,String toWho){
        try {
            Timestamp instant = Timestamp.from(Instant.now());
            FileWriter fw = new FileWriter(FILE_PATH_LOGS, true);
            String toWrite = (who + " " + action + ", " + amount + "$ to " + toWho + " account at " + instant);
            fw.write(toWrite);
            fw.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    static public void writeEntryLog(String who){
        try {
            Timestamp instant = Timestamp.from(Instant.now());
            FileWriter fw = new FileWriter(FILE_PATH_LOGS, true);
            String toWrite = (who + " logged in at " + instant + "\n");
            fw.write(toWrite);
            fw.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
