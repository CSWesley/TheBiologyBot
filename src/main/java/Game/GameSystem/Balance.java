package Game.GameSystem;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.FileNotFoundException;

public class Balance extends ListenerAdapter {

    GameSystem gs = new GameSystem();

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {

        if (e.getMessage().getContentRaw().equalsIgnoreCase("!balance") || e.getMessage().getContentRaw().equalsIgnoreCase("!bal")) {
            try {
                // check if user has started game. if yes, get their bio points. if no, tell user to start game.
                if (!gs.getUsername(Long.toString(e.getAuthor().getIdLong()))) {
                    e.getChannel().sendMessage("You need to start your game first! Do `!start`").queue();
                } else {
                    e.getChannel().sendMessage("You have `" + gs.getBioPoints(Long.toString(e.getAuthor().getIdLong())) + " BioPoints`!").queue();
                }
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        }
    }
}
