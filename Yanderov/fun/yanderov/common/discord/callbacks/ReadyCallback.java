package fun.Yanderov.common.discord.callbacks;

import com.sun.jna.Callback;
import fun.Yanderov.common.discord.utils.DiscordUser;

public interface ReadyCallback extends Callback {
   void apply(DiscordUser var1);
}

