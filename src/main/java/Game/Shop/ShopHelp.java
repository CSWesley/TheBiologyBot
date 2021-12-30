package Game.Shop;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class ShopHelp extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        // this shop command will display the help menu for the shop
        if (e.getMessage().getContentRaw().equalsIgnoreCase("!shop help") || e.getMessage().getContentRaw().equalsIgnoreCase("!sh")) {

            EmbedBuilder eb = new EmbedBuilder()
                    .setTitle("Shop Help • Biology Bot")
                    .setColor(Color.CYAN)
                    .addField("Shop Commands", "\n" +
                            "**!shop** => Displays a list of available items in the shop.\n" +
                            "**!buy [item]** => Allows you to purchase an item using BioPoints.\n" +
                            "**!sell [item]** => Allows you to sell an item and get the BioPoints you paid for back.\n" +
                            "**!create item [name] [price]** => Allows you to create an item and set a name and price.", true)
                    .setFooter("Thank you, " + e.getAuthor().getName() + "!");

            e.getAuthor().openPrivateChannel().queue((channel) -> channel.sendMessage(eb.build()).queue());

            e.getChannel().sendMessage("Check your DMs, I have sent information on the shop!").queue();

        }
    }
}
