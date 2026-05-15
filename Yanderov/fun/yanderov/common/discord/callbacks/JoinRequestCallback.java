package fun.Yanderov.common.discord.callbacks;

import com.sun.jna.Callback;
import fun.Yanderov.common.discord.utils.DiscordUser;

public interface JoinRequestCallback extends Callback {
   void apply(DiscordUser var1);
}

