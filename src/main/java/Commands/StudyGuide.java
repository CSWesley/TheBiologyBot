package Commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class StudyGuide extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {

        EmbedBuilder eb = new EmbedBuilder()
                .setTitle("Study Guide • Biology Bot")
                .setColor(Color.CYAN)
                .addField("Commands", "Here are the commands!\n\n" +
                        "\n" +
                        "Here is the biology group study guide! Please be respectful of the rules on the document. \n" +
                        "Everyone has edit access, so please be responsible:\n" +
                        "https://docs.google.com/document/d/186TXKR2e3h3EK0brx9SNscHu9X_kBCXqOuTzI-8o7eA/edit\n", true)
                .setFooter("Thank you, " + e.getAuthor().getName() + "!");


        if (e.getMessage().getContentRaw().equalsIgnoreCase("!study guide") || e.getMessage().getContentRaw().equalsIgnoreCase("!sg")) {
            e.getChannel().sendMessage(eb.build()).queue();
        }
    }
}
