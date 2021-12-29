package Commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class Info extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        if (e.getMessage().getContentRaw().equalsIgnoreCase("!info")) {

            EmbedBuilder eb = new EmbedBuilder()
                    .setTitle("Info • Biology Bot")
                    .setColor(Color.CYAN)
                    .addField("Information on bot", "Hello, this bot was made by Wesley_#0145 and please report all bugs to him! It is open sourced, so you can view" +
                            "the code at: \n" +
                            "**<https://github.com/CSWesley/TheBiologyBot>**\n" +
                            "", true)
                    .addField("Purpose", "This bot was made for biology students of WTMA and has an economy/game type thing that you can start with `!game`.\n" +
                            "It also is a general bot so you can try `!help` for information. BiologyBot was originally created for the study guide for the midterm, but evolved beyond that " +
                            "to an economy. If you have any suggestions please tell Wesley_#0145 because he has no ideas.", true)
                    .setFooter("Thank you, " + e.getAuthor().getName() + "!");

            e.getChannel().sendMessage(eb.build()).queue();

        }


    }
}
