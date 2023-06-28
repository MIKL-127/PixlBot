package de.pixl.pixlbot;
import de.pixl.pixlbot.interactions.InteractionRegistry;
import de.pixl.pixlbot.listeners.InteractionListener;
import de.pixl.pixlbot.listeners.JoinLeaveListener;
import de.pixl.pixlbot.listeners.VoiceListener;
import de.pixl.pixlbot.secret.DoNotOpen;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.util.Arrays;

public class Main extends InteractionRegistry {
    private static JDA jda;

    public static void main(String[] args) throws InterruptedException {
        JDABuilder builder = JDABuilder.create(DoNotOpen.TOKEN, Arrays.asList(GatewayIntent.values()));
        builder.setEnableShutdownHook(true);

        builder.setActivity(Activity.playing("Minecraft 2"));
        builder.setStatus(OnlineStatus.IDLE);

        jda = builder.build();
        jda().awaitReady();

        jda.addEventListener(
                new JoinLeaveListener(),
                new VoiceListener(),
                new InteractionListener()
        );

        register();

    }

    public static JDA jda() {
        return jda;
    }
}
