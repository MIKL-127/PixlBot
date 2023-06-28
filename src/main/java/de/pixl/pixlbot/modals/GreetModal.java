package de.pixl.pixlbot.modals;

import de.pixl.pixlbot.interactions.interfaces.ModalExecutor;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;

public class GreetModal implements ModalExecutor {

    @Override
    public void run(ModalInteractionEvent event) {
        event.reply("Hey " + event.getValue("name").getAsString() + "! You get Greeted!").queue();
    }
}
