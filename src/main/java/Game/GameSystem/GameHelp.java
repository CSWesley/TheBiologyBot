package Game.GameSystem;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class GameHelp extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        if (e.getMessage().getContentRaw().equalsIgnoreCase("!game help") || e.getMessage().getContentRaw().equalsIgnoreCase("!gh") || e.getMessage().getContentRaw().equalsIgnoreCase("!game")) {


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
        }
    }
}
