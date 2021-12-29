import Commands.Aliases;
import Commands.Help;
import Commands.Info;
import Commands.StudyGuide;
import Game.GameSystem;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

public class BiologyBot {

    public static void main(String[] args) {

        String token = "";

        // loop through all the environment variables and make a variable for each one
        for (String env : System.getenv().keySet()) {
            // if the variable is named "TOKEN" set the variable "token" to the value of the variable
            if (env.equals("TOKEN")) {
                token = System.getenv(env);
                break;
            }
        }


        JDABuilder jdaBuilder = JDABuilder.createDefault(token).setActivity(Activity.playing("Helping with Biology class!"));
        jdaBuilder.addEventListeners(new Help(), new Aliases(), new StudyGuide(), new GameSystem(), new Info());
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
