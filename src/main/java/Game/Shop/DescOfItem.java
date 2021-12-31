package Game.Shop;

import Game.Shop.Items.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class DescOfItem extends ListenerAdapter {

    BlueRole blueRole = new BlueRole();
    GreenRole greenRole = new GreenRole();
    OrangeRole orangeRole = new OrangeRole();
    PinkRole pinkRole = new PinkRole();
    PurpleRole purpleRole = new PurpleRole();
    RedRole redRole = new RedRole();
    YellowRole yellowRole = new YellowRole();

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        if (e.getMessage().getContentRaw().startsWith("!description")) {
            String[] args = e.getMessage().getContentRaw().split(" ");
            if (args.length == 2) {

                if (args[1].equalsIgnoreCase("bluerole")) {
                    e.getChannel().sendMessage(blueRole.getName() + ": " + blueRole.getDescription() + " (" + blueRole.getPrice() + " BioPoints)").queue();
                } else if (args[1].equalsIgnoreCase("greenrole")) {
                    e.getChannel().sendMessage(greenRole.getName() + ": " + greenRole.getDescription() + " (" + greenRole.getPrice() + " BioPoints)").queue();
                } else if (args[1].equalsIgnoreCase("orangerole")) {
                    e.getChannel().sendMessage(orangeRole.getName() + ": " + orangeRole.getDescription() + " (" + orangeRole.getPrice() + " BioPoints)").queue();
                } else if (args[1].equalsIgnoreCase("pinkrole")) {
                    e.getChannel().sendMessage(pinkRole.getName() + ": " + pinkRole.getDescription() + " (" + pinkRole.getPrice() + " BioPoints)").queue();
                } else if (args[1].equalsIgnoreCase("purplerole")) {
                    e.getChannel().sendMessage(purpleRole.getName() + ": " + purpleRole.getDescription() + " (" + purpleRole.getPrice() + " BioPoints)").queue();
                } else if (args[1].equalsIgnoreCase("redrole")) {
                    e.getChannel().sendMessage(redRole.getName() + ": " + redRole.getDescription() + " (" + redRole.getPrice() + " BioPoints)").queue();
                } else if (args[1].equalsIgnoreCase("yellowrole")) {
                    e.getChannel().sendMessage(yellowRole.getName() + ": " + yellowRole.getDescription() + " (" + yellowRole.getPrice() + " BioPoints)").queue();
                } else {
                    e.getChannel().sendMessage("Insufficient arguments! Example: `!description bluerole`").queue();
                }

            } else {
                e.getChannel().sendMessage("Insufficient arguments! Example: `!description bluerole`").queue();
            }
        }
    }
}
