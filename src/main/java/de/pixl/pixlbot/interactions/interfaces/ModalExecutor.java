package de.pixl.pixlbot.interactions.interfaces;

import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;

public interface ModalExecutor {

    void run(ModalInteractionEvent event);
}
