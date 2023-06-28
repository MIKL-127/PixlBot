package de.pixl.pixlbot.buttons;

import de.pixl.pixlbot.interactions.interfaces.ButtonExecutor;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;

import java.util.UUID;

public class TestButton implements ButtonExecutor {
    @Override
    public void run(ButtonInteractionEvent event) {
        event.getMessage().editMessage(UUID.randomUUID().toString()).queue();
        event.deferEdit().closeResources().queue();
    }
}
