package Commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class Help extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {

        EmbedBuilder eb = new EmbedBuilder()
                .setTitle("Help • Biology Bot")
                .setColor(Color.CYAN)
                .addField("Commands", "Here are the commands!\n\n" +
                        "- **!help** => Shows this menu.\n" +
                        "- **!info** => Shows information on the bot and the link to the code.\n" +
                        "- **!study guide** => Sends the study guide link.\n" +
                        "- **!aliases** => Alternate commands to use to shorten your typing.\n" +
                        "- **!game help** => Get help for the economy game.\n" +
                        "", true)
                .setFooter("Thank you, " + e.getAuthor().getName() + "!");


        if (e.getMessage().getContentRaw().equalsIgnoreCase("!help") || e.getMessage().getContentRaw().equalsIgnoreCase("!h")) {
            e.getChannel().sendMessage(eb.build()).queue();
        }
    }
}
