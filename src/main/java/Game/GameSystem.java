package Game;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
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

        Files.write(file.toPath(), Collections.singleton(fullFile + Long.toString(id) + ": 0,0"), Charset.defaultCharset());
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

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        if (e.getMessage().getContentRaw().equalsIgnoreCase("!start")) {
            try {
                long id = e.getAuthor().getIdLong();

                if (getUsername(Long.toString(id)) == null) {
                    // User doesn't exist do create user
                    createUser(id);
                    e.getChannel().sendMessage("Welcome to the game " + e.getMember().getEffectiveName() + "!").queue();

                } else {
                    // User already exists, tell them
                    e.getChannel().sendMessage("You already have started your game! Do `!game help` for help on how to play!").queue();
                }


            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getMessage().getContentRaw().equalsIgnoreCase("!game help") || e.getMessage().getContentRaw().equalsIgnoreCase("!gh") || e.getMessage().getContentRaw().equalsIgnoreCase("!game")) {

            // create embed message to send with cyan color set the title as "Game Help • Biology Bot" and set footer as "Thank you, " e.getAuthor().getName() + "!"
            EmbedBuilder eb = new EmbedBuilder()
                    .setTitle("Game Help • Biology Bot")
                    .setColor(Color.CYAN)
                    .addField("How to Play", "\n" +
                            "\n" +
                            "**!start** => Starts a game for user.\n" +
                            "**!daily** => Collect your daily reward of BioPoints (Resets every 24 hours).\n" +
                            "**!shop help** => Sends help on how the shop works.\n" +
                            "**!balance** => View your total BioPoints.\n" +
                            "**!quit** => Destroys all your stats and BioPoints.\n" +
                            "\n" +
                            "**Please suggest some features to add if you'd like! (DM or ping Wesley_#0145)**\n", false)
                    .setFooter("Thank you, " + e.getAuthor().getName() + "!");

            e.getChannel().sendMessage("Check you DMs, I sent you information on how to play!").queue();
            e.getAuthor().openPrivateChannel().queue(privateChannel -> {
                privateChannel.sendMessage(eb.build()).queue();
            });
        } else if (e.getMessage().getContentRaw().equalsIgnoreCase("!daily")) {
            try {
                if (getUsername(Long.toString(e.getAuthor().getIdLong())) == null) {
                    e.getChannel().sendMessage("You need to start your game first! Do `!start`").queue();
                } else
                    try {
                        long currentTime = System.currentTimeMillis();

                        long lastTime = Long.parseLong(getTimeFromLastDaily(Long.toString(e.getAuthor().getIdLong())));

                        long difference = currentTime - lastTime;

                        if (Long.toString(lastTime).equals("0")) {
                            // add to user's bio points.
                            addBioPoints(Long.toString(e.getAuthor().getIdLong()), 100);

                            e.getChannel().sendMessage("You have collected your daily reward of `100 BioPoints`! It's your first one too!").queue();

                            setTimeFromLastDaily(Long.toString(e.getAuthor().getIdLong()));

                        } else if (difference > 86400000) {
                            addBioPoints(Long.toString(e.getAuthor().getIdLong()), 100);

                            e.getChannel().sendMessage("You have collected your daily reward of `100 BioPoints`!").queue();

                            setTimeFromLastDaily(Long.toString(e.getAuthor().getIdLong()));

                        } else {
                            // tell user they can't get daily reward yet.
                            e.getChannel().sendMessage("You can't get your daily reward yet! You have to wait 24 hours between each daily reward.").queue();
                        }

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        } else if (e.getMessage().getContentRaw().equalsIgnoreCase("!balance") || e.getMessage().getContentRaw().equalsIgnoreCase("!bal")) {
            try {
                // check if user has started game. if yes, get their bio points. if no, tell user to start game.
                if (getUsername(Long.toString(e.getAuthor().getIdLong())) == null) {
                    e.getChannel().sendMessage("You need to start your game first! Do `!start`").queue();
                } else {
                    e.getChannel().sendMessage("You have `" + getBioPoints(Long.toString(e.getAuthor().getIdLong())) + " BioPoints`!").queue();
                }
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        }
    }

    private String getBioPoints(String userID) {
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
