package de.pixl.pixlbot.listeners;

import de.pixl.pixlbot.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Invite;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.guild.invite.GuildInviteCreateEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

public class JoinLeaveListener extends ListenerAdapter {

    private final ConcurrentHashMap<Invite, Integer> usages = new ConcurrentHashMap<>();

    public JoinLeaveListener() {
        new Thread(() -> {
            try {
                Main.jda().awaitReady();
                for (Guild guild : Main.jda().getGuilds())
                    for (Invite invite : guild.retrieveInvites().complete())
                        usages.put(invite, invite.getUses());
                System.out.println(usages);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Thread.currentThread().interrupt();
        }).start();
    }

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        Member member = event.getMember();
        Guild guild = event.getGuild();
        TextChannel textChannel = Objects.requireNonNull(guild.getDefaultChannel()).asTextChannel();
        EmbedBuilder builder = new EmbedBuilder();
        builder.setDescription(member.getAsMention() + " has joined the Server! Welcome! Our Guild has now **" + guild.getMembers().size() +"** Members!");
        builder.setTimestamp(OffsetDateTime.now());
        builder.setColor(0x00b16a);
        if (member.getUser().getAvatarUrl() != null)
            builder.setThumbnail(member.getUser().getAvatarUrl());
        else
            builder.setThumbnail(member.getUser().getDefaultAvatarUrl());

        Invite used = null;
        for (Invite invite : guild.retrieveInvites().complete())
            if (!usages.containsKey(invite)) {
                used = invite;
                usages.put(invite, invite.getUses());
                break;
            } else if (usages.get(invite) < invite.getUses()) {
                used = invite;
                usages.put(invite, invite.getUses());
                break;
            }
        if (used != null)
            builder.setAuthor("Invited by: " + used.getInviter().getName(), "https://www.google.com", used.getInviter().getAvatarUrl());

        textChannel.sendMessageEmbeds(builder.build()).queue();
    }

    @Override
    public void onGuildMemberRemove(GuildMemberRemoveEvent event) {
        User user = event.getUser();
        Guild guild = event.getGuild();
        TextChannel textChannel = Objects.requireNonNull(guild.getDefaultChannel()).asTextChannel();
        EmbedBuilder builder = new EmbedBuilder();
        builder.setDescription(user.getName() + " has left the Server! Bye! Our Guild has now **" + guild.getMembers().size() +"** Members!");
        builder.setTimestamp(OffsetDateTime.now());
        builder.setColor(0xfe7968);

        textChannel.sendMessageEmbeds(builder.build()).queue();
    }

    @Override
    public void onGuildInviteCreate(GuildInviteCreateEvent event) {
        usages.put(event.getInvite(), event.getInvite().getUses());
    }
}
