package Game.Shop;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.ArrayList;

public class ShopItems extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        if (e.getMessage().getContentRaw().equalsIgnoreCase("!shop")) {
            // this command will display the shop items with their descriptions, price, etc.

            ArrayList<String> items = getShopItems();
        }
    }

    private ArrayList<String> getShopItems() {
        ArrayList<String> items = new ArrayList<>();
        // color roles
        items.add("Pink role");
        items.add("Red role");
        items.add("Blue role");
        items.add("Green role");
        items.add("Yellow role");
        items.add("Purple role");
        items.add("Orange role");

        // actual items
        items.add("");

        return items;
    }
}
