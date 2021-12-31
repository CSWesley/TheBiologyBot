package Game.GameSystem;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.FileNotFoundException;

public class DailyReward extends ListenerAdapter {

    GameSystem gs = new GameSystem();

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {

        if (e.getMessage().getContentRaw().equalsIgnoreCase("!daily")) {
            try {
                if (!gs.getUsername(Long.toString(e.getAuthor().getIdLong()))) {
                    e.getChannel().sendMessage("You need to start your game first! Do `!start`").queue();
                } else
                    try {
                        long currentTime = System.currentTimeMillis();

                        long lastTime = Long.parseLong(gs.getTimeFromLastAction(Long.toString(e.getAuthor().getIdLong())));

                        long difference = currentTime - lastTime;

                        if (Long.toString(lastTime).equals("0")) {
                            // add to user's bio points.
                            gs.addDailyReward(Long.toString(e.getAuthor().getIdLong()), 100);

                            e.getChannel().sendMessage("You have collected your daily reward of `100 BioPoints`! It's your first one too!").queue();

                            gs.setTimeFromLastAction(Long.toString(e.getAuthor().getIdLong()));

                        } else if (difference > 86400000) {
                            gs.addBioPoints(Long.toString(e.getAuthor().getIdLong()), 100);

                            e.getChannel().sendMessage("You have collected your daily reward of `100 BioPoints`!").queue();

                            gs.setTimeFromLastAction(Long.toString(e.getAuthor().getIdLong()));

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
        }
    }
}
