package de.pixl.pixlbot.context;

import de.pixl.pixlbot.interactions.interfaces.ContextExecutor;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.MessageContextInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.UserContextInteractionEvent;

import java.util.Objects;

public class KickContext implements ContextExecutor {

    @Override
    public void run(UserContextInteractionEvent event) {
        kick(Objects.requireNonNull(event.getTargetMember()));
        event.reply("Success!").complete().deleteOriginal().queue();
    }

    @Override
    public void run(MessageContextInteractionEvent event) {
        kick(Objects.requireNonNull(event.getTarget().getMember()));
        event.reply("Success!").complete().deleteOriginal().queue();
    }

    private void kick(Member member) {
        member.kick().queue();
    }
}
