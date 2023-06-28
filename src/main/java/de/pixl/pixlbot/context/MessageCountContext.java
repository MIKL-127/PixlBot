package de.pixl.pixlbot.context;

import de.pixl.pixlbot.interactions.interfaces.ContextExecutor;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.interaction.command.MessageContextInteractionEvent;

public class MessageCountContext implements ContextExecutor {

    @Override
    public void run(MessageContextInteractionEvent event) {
        String message = event.getTarget().getContentDisplay();
        event.reply("**Characters: ** " + message.length() + "\n** Words: **" + message.split(" ").length).setEphemeral(true).queue();
    }
}
