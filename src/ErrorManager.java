import java.io.FileWriter;
import java.sql.Timestamp;
import java.time.Instant;

public class ErrorManager {
    private static final String FILE_PATH_LOGS = "/Users/mindera/IdeaProjects/untitled3/src/logs.txt";
    public static void writeError(String who, String action, int amount){
        try {
            Timestamp instant = Timestamp.from(Instant.now());
            FileWriter fw = new FileWriter(FILE_PATH_LOGS, true);
            String toWrite = ("\n"+who + " tried using a blocked account. " + action + " " + amount + "$");
            fw.write(toWrite);
            fw.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
