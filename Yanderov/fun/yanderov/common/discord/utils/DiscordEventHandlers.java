package fun.Yanderov.common.discord.utils;

import com.sun.jna.Structure;
import fun.Yanderov.common.discord.callbacks.DisconnectedCallback;
import fun.Yanderov.common.discord.callbacks.ErroredCallback;
import fun.Yanderov.common.discord.callbacks.JoinGameCallback;
import fun.Yanderov.common.discord.callbacks.JoinRequestCallback;
import fun.Yanderov.common.discord.callbacks.ReadyCallback;
import fun.Yanderov.common.discord.callbacks.SpectateGameCallback;
import java.util.Arrays;
import java.util.List;

public class DiscordEventHandlers extends Structure {
   public DisconnectedCallback disconnected;
   public JoinRequestCallback joinRequest;
   public SpectateGameCallback spectateGame;
   public ReadyCallback ready;
   public ErroredCallback errored;
   public JoinGameCallback joinGame;

   protected List getFieldOrder() {
      return Arrays.asList("ready", "disconnected", "errored", "joinGame", "spectateGame", "joinRequest");
   }

   public static class Builder {
      private final DiscordEventHandlers handlers = new DiscordEventHandlers();

      public DiscordEventHandlers build() {
         return this.handlers;
      }

      public Builder disconnected(DisconnectedCallback var1) {
         this.handlers.disconnected = var1;
         return this;
      }

      public Builder errored(ErroredCallback var1) {
         this.handlers.errored = var1;
         return this;
      }

      public Builder ready(ReadyCallback var1) {
         this.handlers.ready = var1;
         return this;
      }

      public Builder joinRequest(JoinRequestCallback var1) {
         this.handlers.joinRequest = var1;
         return this;
      }

      public Builder joinGame(JoinGameCallback var1) {
         this.handlers.joinGame = var1;
         return this;
      }

      public Builder spectateGame(SpectateGameCallback var1) {
         this.handlers.spectateGame = var1;
         return this;
      }
   }
}

