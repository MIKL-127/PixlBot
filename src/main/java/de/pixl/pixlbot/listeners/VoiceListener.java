package de.pixl.pixlbot.listeners;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.concrete.Category;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class VoiceListener extends ListenerAdapter {

    private final ConcurrentLinkedQueue<Long> tempChannels = new ConcurrentLinkedQueue<>();

    @Override
    public void onGuildVoiceUpdate(GuildVoiceUpdateEvent event) {
        if (event.getNewValue() == null) {
            leave(event);
        } else if (event.getOldValue() == null) {
            join(event);
        } else {
            leave(event);
            join(event);
        }
    }


    private void join(GuildVoiceUpdateEvent event) {
        assert event.getNewValue() != null;
        if (event.getNewValue().getIdLong() == 1121170981386453133L) {
            Guild guild = event.getGuild();
            Member member = event.getMember();
            Category category = event.getNewValue().getParentCategory();
            VoiceChannel voiceChannel;
            if (category != null)
                voiceChannel = category.createVoiceChannel("TEMP | " + member.getUser().getName()).complete();
            else
                voiceChannel = category.createVoiceChannel("TEMP | " + member.getUser().getName()).complete();
            guild.moveVoiceMember(member, voiceChannel).queue();
            tempChannels.add(voiceChannel.getIdLong());

        }
    }

    private void leave(GuildVoiceUpdateEvent event) {
        assert event.getOldValue() != null;
        VoiceChannel voiceChannel = (VoiceChannel) event.getOldValue();
        if (tempChannels.contains(voiceChannel.getIdLong()) && voiceChannel.getMembers().size() <= 0) {
            voiceChannel.delete().queue();
        }
    }
}
