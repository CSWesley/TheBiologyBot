package Commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class Aliases extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {

        EmbedBuilder eb = new EmbedBuilder()
                .setTitle("Aliases • Biology Bot")
                .setColor(Color.CYAN)
                .addField("Commands", "Here are the commands!\n\n" +
                        "**!study guide** can be abbreviated to **!sg**.\n" +
                        "**!help** can be abbreviated to **!h**.\n" +
                        "**!game help** can be abbreviated to **!gh**.\n", true)
                .setFooter("Thank you, " + e.getAuthor().getName() + "!");


        if (e.getMessage().getContentRaw().equalsIgnoreCase("!aliases")) {
            e.getChannel().sendMessage(eb.build()).queue();
        }
    }
}
