package de.pixl.pixlbot.commands;

import de.pixl.pixlbot.interactions.interfaces.CommandExecutor;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.components.Modal;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;

public class GreetCommand implements CommandExecutor {
    @Override
    public void run(SlashCommandInteractionEvent event) {
        event.replyModal(Modal.create("greet", "Who should be greeted?")
                        .addActionRow(
                                TextInput.create("name", "Name", TextInputStyle.SHORT)
                                    .setRequired(true)
                                    .setPlaceholder("Name You want to greet")
                                    .build()
                        )
                        .build()
        ).queue();
    }
}