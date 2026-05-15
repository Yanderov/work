package dev.client.yanderov.utils.connection.irc;

import antidaunleak.api.annotation.Native;
import dev.client.yanderov.Yanderov;
import dev.client.yanderov.utils.client.chat.ChatMessage;
import java.net.URI;
import java.util.concurrent.atomic.AtomicBoolean;

public class IRCManager {
   private IRCWebSocketClient client;
   final AtomicBoolean isConnecting = new AtomicBoolean(false);

   public IRCWebSocketClient getClient() {
      return this.client;
   }

   @Native(
      type = Native.Type.VMProtectBeginUltra
   )
   public void connect() {
      if (!this.isConnecting.get()) {
         try {
            this.isConnecting.set(true);
            if (this.client != null && !this.client.isClosed()) {
               this.client.close();
            }

            this.client = new IRCWebSocketClient(new URI("ws://45.155.205.202:8081"));
            this.client.connect();
         } catch (Exception var2) {
            if (YanderovIntegration.getInstance().isShowIrcMessages()) {
               ChatMessage.ircmessageWithRed("ÐÐµ ÑƒÐ´Ð°Ð»Ð¾ÑÑŒ Ð¿Ð¾Ð´ÐºÐ»ÑŽÑ‡Ð¸Ñ‚ÑŒÑÑ Ðº ÑÐµÑ€Ð²ÐµÑ€Ñƒ IRC");
            }

            this.isConnecting.set(false);
         }

      }
   }

   public void disconnect() {
      if (this.client != null) {
         this.client.close();
         this.client = null;
      }

      this.isConnecting.set(false);
   }

   public void sendMessage(String msg, String prefix) {
      if (this.client != null && this.client.isOpen()) {
         this.client.sendMessage(msg);
      } else if (YanderovIntegration.getInstance().isShowIrcMessages()) {
      }

   }
}

