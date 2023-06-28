package de.pixl.pixlbot.buttons;

import de.pixl.pixlbot.interactions.interfaces.ButtonExecutor;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.PermissionOverride;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.InteractionHook;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.util.Objects;

public class TicketCloseButton implements ButtonExecutor {

    @Override
    public void run(ButtonInteractionEvent event) {
        InteractionHook hook = event.deferReply(true).complete();

        Guild guild = event.getGuild();
        TextChannel channel = event.getChannel().asTextChannel();

        channel.getManager().setName("🔒 | " + channel.getName()).queue();

        for (PermissionOverride override : channel.getPermissionOverrides()) {
            if (override.isMemberOverride()) {
                override.delete().queue();
            }
        }

        channel.getPermissionOverride(Objects.requireNonNull(guild.getRoleById(1121857232515104912L)))
                .getManager()
                .setDenied(Permission.values())
                .setAllowed(
                        Permission.VIEW_CHANNEL,
                        Permission.MESSAGE_HISTORY
                ).queue();

        event.getMessage().editMessageEmbeds(event.getMessage().getEmbeds()).setActionRow(
                Button.danger("ticket_close", "Close the Ticket")
                        .withEmoji(Emoji.fromUnicode("🔒"))
                        .asDisabled()
        ).queue();

        hook.deleteOriginal().queue();
    }
}
