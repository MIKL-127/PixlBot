package de.pixl.pixlbot.interactions.interfaces;

import net.dv8tion.jda.api.events.interaction.component.SelectMenuInteractionEvent;

public interface SelectMenuExecutor {

    void run(SelectMenuInteractionEvent event);
}
