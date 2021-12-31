package Game.Shop.Operations;

import Game.GameSystem.GameSystem;
import Game.Shop.Items.*;
import Game.Shop.Shop;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.IOException;

public class Buy extends ListenerAdapter {
    BlueRole blueRole = new BlueRole();
    GreenRole greenRole = new GreenRole();
    OrangeRole orangeRole = new OrangeRole();
    PinkRole pinkRole = new PinkRole();
    PurpleRole purpleRole = new PurpleRole();
    RedRole redRole = new RedRole();
    YellowRole yellowRole = new YellowRole();

    GameSystem gs = new GameSystem();

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        if (e.getMessage().getContentRaw().startsWith("!buy")) {
            long id = e.getAuthor().getIdLong();

            String[] args = e.getMessage().getContentRaw().split(" ");

            if (args.length == 2) {
                if (args[1].equalsIgnoreCase("bluerole")) {
                    buyRole(blueRole, id, e);

                } else if (args[1].equalsIgnoreCase("greenrole")) {
                    buyRole(greenRole, id, e);
                } else if (args[1].equalsIgnoreCase("orangerole")) {
                    buyRole(orangeRole, id, e);
                } else if (args[1].equalsIgnoreCase("pinkrole")) {
                    buyRole(pinkRole, id, e);
                } else if (args[1].equalsIgnoreCase("purplerole")) {
                    buyRole(purpleRole, id, e);
                } else if (args[1].equalsIgnoreCase("redrole")) {
                    buyRole(redRole, id, e);
                } else if (args[1].equalsIgnoreCase("yellowrole")) {
                    buyRole(yellowRole, id, e);
                } else {
                    e.getChannel().sendMessage("Invalid item! Example: `!buy bluerole`").queue();
                }

            } else {
                e.getChannel().sendMessage("Insufficient arguments. Example: `!buy bluerole`").queue();
            }
        }
    }

    public void buyRole(Shop role, long id, MessageReceivedEvent e) {

        // Check if user has enough money. if yes, remove money and add role. if no, send message.
        try {
            Role roleToAdd = e.getGuild().getRoleById(role.getId());

            // check if user has roleToAdd
            if (e.getMember().getRoles().contains(roleToAdd)) {
                e.getChannel().sendMessage("You already have this role!").queue();
            } else {
                if (role.getPrice() <= Integer.parseInt(gs.getCurrentBalance(Long.toString(id)))) {
                    gs.removeBioPoints(Long.toString(id), role.getPrice());
                    e.getGuild().addRoleToMember(id, roleToAdd).queue();
                    e.getChannel().sendMessage("You have bought the `" + role.getName() + "`!").queue();
                } else {
                    e.getChannel().sendMessage("You do not have enough BioPoints to buy this role!").queue();
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
