package de.pixl.pixlbot.commands;

import de.pixl.pixlbot.interactions.interfaces.CommandExecutor;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.selections.SelectMenu;

public class TestCommand implements CommandExecutor {
    @Override
    public void run(SlashCommandInteractionEvent event) {
        event.reply("Test!").addActionRow(
                //Button.danger("random", "Change the Message."),
                SelectMenu.create("role")
                        .setPlaceholder("Choose Your Roles")
                        .addOption("Role1", "1121402971855528048", Emoji.fromCustom("dereck", 1121406620149219328L, false))
                        .addOption("Role2", "1121403054688833610", Emoji.fromCustom("grass", 1121406617188040745L, false))
                        .addOption("Role3", "1121403121608966205", Emoji.fromCustom("mmmm", 1121406615426453525L, false))
                        .addOption("Role4", "1121403160511135744", Emoji.fromCustom("pog", 1121406612360400968L, false))
                        .setMinValues(0)
                        .setMaxValues(4)
                        .build()
        ).queue();
    }
}
