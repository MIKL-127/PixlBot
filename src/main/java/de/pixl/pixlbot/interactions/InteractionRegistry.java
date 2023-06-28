package de.pixl.pixlbot.interactions;

import de.pixl.pixlbot.buttons.TestButton;
import de.pixl.pixlbot.buttons.TicketCloseButton;
import de.pixl.pixlbot.buttons.TicketCreateButton;
import de.pixl.pixlbot.commands.GreetCommand;
import de.pixl.pixlbot.commands.TestCommand;
import de.pixl.pixlbot.commands.TicketCommand;
import de.pixl.pixlbot.context.BanContext;
import de.pixl.pixlbot.context.KickContext;
import de.pixl.pixlbot.context.MessageCountContext;
import de.pixl.pixlbot.context.MuteContext;
import de.pixl.pixlbot.interactions.classes.Context;
import de.pixl.pixlbot.modals.GreetModal;
import de.pixl.pixlbot.selectMenu.TestSelectMenu;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

public class InteractionRegistry extends InteractionManager{

    public static void register() {
        registerCommands();
        registerButtons();
        registerSelectMenus();
        registerContexts();
        registerModals();
        push();
    }

    private static void registerCommands() {
        getCommand("test").setExecutor(new TestCommand());
        getCommand("test").setData(Commands.slash("test", "Test command for testing SlashCommands").setGuildOnly(true));

        getCommand("greet").setExecutor(new GreetCommand());
        getCommand("greet").setData(Commands.slash("greet", "Greet someone!").setGuildOnly(true));

        getCommand("ticket").setExecutor(new TicketCommand());
        getCommand("ticket").setData(Commands.slash("ticket", "Create a Ticket Channel!").setGuildOnly(true));
    }

    private static void registerButtons() {
        getButton("random").setExecutor(new TestButton());
        getButton("ticket_create").setExecutor(new TicketCreateButton());
        getButton("ticket_close").setExecutor(new TicketCloseButton());
    }

    private static void registerSelectMenus() {
        getSelectMenu("role").setExecutor(new TestSelectMenu());
    }

    private static void registerContexts() {
        getContext("Mute User").setExecutor(new MuteContext());
        getContext("Mute User").setData(new Context.CommandDataCollection()
                .setUser(Commands.user("Mute User"))
                .setMessage(Commands.message("Mute User"))
        );

        getContext("Kick User").setExecutor(new KickContext());
        getContext("Kick User").setData(new Context.CommandDataCollection()
                .setUser(Commands.user("Kick User"))
                .setMessage(Commands.message("Kick User"))
        );

        getContext("Ban User").setExecutor(new BanContext());
        getContext("Ban User").setData(new Context.CommandDataCollection()
                .setUser(Commands.user("Ban User"))
                .setMessage(Commands.message("Ban User"))
        );

        getContext("Word Counter").setExecutor(new MessageCountContext());
        getContext("Word Counter").setData(new Context.CommandDataCollection()
                .setMessage(Commands.message("Word Counter"))
        );
    }

    private static void registerModals() {
        getModal("greet").setExecutor(new GreetModal());
    }
}
