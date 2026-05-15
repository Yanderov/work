package dev.client.yanderov.features.impl.misc;

import dev.client.yanderov.Yanderov;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.utils.client.chat.ChatMessage;

public class IRC extends Module {
   public IRC() {
      super("IRC", ModuleCategory.MISC);
   }

   public void setState(boolean state) {
      super.setState(state);
      if (state) {
         this.activate();
      } else {
         this.deactivate();
      }

   }

   public void activate() {
      YanderovIntegration.getInstance().setShowIrcMessages(true);
      YanderovIntegration.getInstance().getIrcManager().connect();
   }

   public void deactivate() {
      YanderovIntegration.getInstance().setShowIrcMessages(false);
      YanderovIntegration.getInstance().getIrcManager().disconnect();
   }

   public void sendMessage(String message) {
      if (!this.isState()) {
         ChatMessage.ircmessageWithRed("ÐœÐ¾Ð´ÑƒÐ»ÑŒ IRC Ð²Ñ‹ÐºÐ»ÑŽÑ‡ÐµÐ½");
      } else {
         if (YanderovIntegration.getInstance().getIrcManager().getClient() != null && YanderovIntegration.getInstance().getIrcManager().getClient().isOpen()) {
            YanderovIntegration.getInstance().getIrcManager().getClient().sendMessage(message);
         }

      }
   }
}

