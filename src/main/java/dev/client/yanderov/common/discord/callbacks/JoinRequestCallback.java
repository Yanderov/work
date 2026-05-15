package dev.client.yanderov.common.discord.callbacks;

import com.sun.jna.Callback;
import dev.client.yanderov.common.discord.utils.DiscordUser;

public interface JoinRequestCallback extends Callback {
   void apply(DiscordUser var1);
}

