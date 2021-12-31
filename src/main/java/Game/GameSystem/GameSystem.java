package Game.GameSystem;

import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.*;

public class GameSystem extends ListenerAdapter {

    // note to self: check discord for answers to question.
    File file = new File(System.getProperty("user.dir") + "/src/main/java/Game/saves.txt");

    public String getUsername(String id) throws FileNotFoundException {
        Scanner readFile = new Scanner(file);

        while (readFile.hasNextLine()) {
            String line = readFile.nextLine();
            if (line.startsWith(id)) {
                return "exists";
            }
        }

        return null;
    }

    public void addBioPoints(String userID, int amount) throws IOException {
        // first get the time from last daily.
        // replace that with the new one, which is the current time.
        String time = Long.toString(System.currentTimeMillis());

        Scanner scanner = new Scanner(file);

        StringBuilder fullFile = new StringBuilder();

        while (scanner.hasNextLine()) {
            fullFile.append(scanner.nextLine());
        }
        // fullfile now has all the details in the file.
        int amountt = Integer.parseInt(getCurrentBalance(userID)) + amount;
        String newAmount = Integer.toString(amountt);

        Files.write(file.toPath(), Collections.singleton(fullFile.toString().replace(userID + ": " + getTimeFromLastDaily(userID) + "," + getCurrentBalance(userID), userID + ": " + time + "," + newAmount)), Charset.defaultCharset());
    }

    public String getCurrentBalance(String userID) throws FileNotFoundException {
        Scanner readFile = new Scanner(file);

        ArrayList<String> stuff = new ArrayList<>();

        while (readFile.hasNextLine()) {
            stuff.add(readFile.nextLine());
        }
        // stuff array now has all the details for all users.
        String userDetails = "";

        for (int i = 0; i < stuff.size(); i++) {
            if (stuff.get(i).startsWith(userID)) {
                userDetails = stuff.get(i);
            }
        }

        String[] arr = userDetails.split(",");

        return arr[1];

    }

    public void createUser(long id) throws IOException {
        Scanner scanner = new Scanner(file);

        StringBuilder fullFile = new StringBuilder();

        while (scanner.hasNextLine()) {
            fullFile.append(scanner.nextLine()).append("\n");
        }

        Files.write(file.toPath(), Collections.singleton(fullFile + "\n" + id + ": 0,0\n"), Charset.defaultCharset());
    }

    public String getTimeFromLastDaily(String userID) throws FileNotFoundException {
        Scanner readFile = new Scanner(file);

        ArrayList<String> stuff = new ArrayList<>();

        while (readFile.hasNextLine()) {
            stuff.add(readFile.nextLine());
        }
        // stuff array now has all the details for all users.
        String userDetails = "";

        for (int i = 0; i < stuff.size(); i++) {
            if (stuff.get(i).startsWith(userID)) {
                userDetails = stuff.get(i);
            }
        }

        userDetails = userDetails.replace(userID + ": ", "");

        // now userdetails only has # of BioPoints and time since last daily.

        String[] userDetailsArray = userDetails.split(",");

        // return the time from last daily.
        return userDetailsArray[0];
    }

    public void setTimeFromLastDaily(String userID) throws IOException {
        // first get the time from last daily.
        // replace that with the new one, which is the current time.
        String time = Long.toString(System.currentTimeMillis());

        Scanner scanner = new Scanner(file);

        StringBuilder fullFile = new StringBuilder();

        while (scanner.hasNextLine()) {
            fullFile.append(scanner.nextLine());
        }

        Files.write(file.toPath(), Collections.singleton(fullFile.toString().replace(userID + ": " + getTimeFromLastDaily(userID) + ",", userID + ": " + time + ",")), Charset.defaultCharset());
    }

    String getBioPoints(String userID) {
        // read the saves.txt file and get the bio points.
        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (line.contains(userID)) {
                    String[] split = line.split(",");

                    return split[1];
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
