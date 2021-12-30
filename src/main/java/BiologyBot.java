import Commands.Aliases;
import Commands.Help;
import Commands.Info;
import Commands.StudyGuide;
import Game.GameSystem;
import Game.Shop.ShopHelp;
import Game.Shop.ShopItems;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

public class BiologyBot {

    public static void main(String[] args) {

        String token = "";

        for (String env : System.getenv().keySet()) {
            if (env.equals("TOKEN")) {
                token = System.getenv(env);
                break;
            }
        }

        // Maybe add a pet shop/store or something like that?

        JDABuilder jdaBuilder = JDABuilder.createDefault(token).setActivity(Activity.playing("Helping with Biology class!"));
        jdaBuilder.addEventListeners(new Help(), new Aliases(), new StudyGuide(), new GameSystem(), new Info(), new ShopHelp(), new ShopItems());
        JDA jda = null;
        try {

            jdaBuilder.build();
        } catch (LoginException e) {
            e.printStackTrace();
        }
        try {
            jda.awaitReady();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
