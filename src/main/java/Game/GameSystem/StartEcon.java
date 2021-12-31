package Game.GameSystem;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class StartEcon extends ListenerAdapter {

    GameSystem gs = new GameSystem();

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        if (e.getMessage().getContentRaw().equalsIgnoreCase("!start")) {
            try {
                long id = e.getAuthor().getIdLong();

                if (gs.getUsername(Long.toString(id)) == null) {
                    // User doesn't exist do create user
                    gs.createUser(id);
                    e.getChannel().sendMessage("Welcome to the game " + e.getMember().getEffectiveName() + "!").queue();

                } else {
                    // User already exists, tell them
                    e.getChannel().sendMessage("You already have started your game! Do `!game help` for help on how to play!").queue();
                }


            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
