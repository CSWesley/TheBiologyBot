package Game.Shop;

import Game.Shop.Items.*;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.util.ArrayList;

public class ShopItems extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        if (e.getMessage().getContentRaw().equalsIgnoreCase("!shop")) {
            // this command will display the shop items with their descriptions, price, etc.

            ArrayList<String> items = getShopItems();
            StringBuilder builder = new StringBuilder();

            for (String item : items) {
                builder.append(item).append("\n");
            }

            EmbedBuilder eb = new EmbedBuilder()
                    .setTitle("Shop • Biology Bot")
                    .setColor(Color.CYAN)
                    .addField("Items", "You can do `!description [item]` to get information on it.\n\n" +
                            builder, true)
                    .setFooter("Thank you, " + e.getAuthor().getName() + "!");

            e.getChannel().sendMessage(eb.build()).queue();
        }
    }

    private ArrayList<String> getShopItems() {
        // colors
        BlueRole blueRole = new BlueRole();
        GreenRole greenRole = new GreenRole();
        OrangeRole orangeRole = new OrangeRole();
        PinkRole pinkRole = new PinkRole();
        PurpleRole purpleRole = new PurpleRole();
        RedRole redRole = new RedRole();
        YellowRole yellowRole = new YellowRole();

        ArrayList<String> items = new ArrayList<>();
        // color roles
        items.add("Pink role - " + pinkRole.getPrice() + " BioPoints");
        items.add("Red role - " + redRole.getPrice() + " BioPoints");
        items.add("Blue role - " + blueRole.getPrice() + " BioPoints");
        items.add("Green role - " + greenRole.getPrice() + " BioPoints");
        items.add("Yellow role - " + yellowRole.getPrice() + " BioPoints");
        items.add("Purple role - " + purpleRole.getPrice() + " BioPoints");
        items.add("Orange role - " + orangeRole.getPrice() + " BioPoints");

        return items;
    }
}
