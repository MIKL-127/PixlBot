package de.pixl.pixlbot.buttons;

import de.pixl.pixlbot.interactions.interfaces.ButtonExecutor;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.entities.channel.concrete.Category;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.time.OffsetDateTime;
import java.util.Objects;

public class TicketCreateButton implements ButtonExecutor {

    @Override
    public void run(ButtonInteractionEvent event) {
        event.deferReply(true).complete().deleteOriginal().queue();

        Guild guild = event.getGuild();
        assert guild != null;

        Category category = guild.getCategoryById(1121844526827450428L);
        assert category != null;

        TextChannel channel = category.createTextChannel(Objects.requireNonNull(event.getMember()).getEffectiveName()).complete();

        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(0xf48363)
                .setTimestamp(OffsetDateTime.now())
                .setFooter("© PixlBot")
                .setTitle("Welcome to the Ticket Support!")
                .setDescription("Our Support Team will shortly work on your Inquiry!");

        channel.upsertPermissionOverride(guild.getPublicRole())
                .setDenied(Permission.values()).queue();

        channel.upsertPermissionOverride(event.getMember())
                .setDenied(Permission.values()).setAllowed(
                Permission.VIEW_CHANNEL,
                Permission.MESSAGE_SEND,
                Permission.MESSAGE_HISTORY,
                Permission.MESSAGE_ATTACH_FILES,
                Permission.MESSAGE_ADD_REACTION
        ).queue();

        channel.upsertPermissionOverride(Objects.requireNonNull(guild.getRoleById(1121857232515104912L)))
                        .setDenied(Permission.values()).setAllowed(
                        Permission.VIEW_CHANNEL,
                        Permission.MESSAGE_SEND,
                        Permission.MESSAGE_HISTORY,
                        Permission.MESSAGE_ATTACH_FILES,
                        Permission.MESSAGE_ADD_REACTION,
                        Permission.MESSAGE_MANAGE
                ).queue();

        channel.sendMessage(event.getMember().getAsMention())
                .addEmbeds(builder.build())
                .addActionRow(
                        Button.danger("ticket_cloe", "Close the Ticket")
                        .withEmoji(Emoji.fromUnicode("🔒"))
                ).queue();
    }
}
