package fun.Yanderov.features.impl.misc;

import fun.Yanderov.Yanderov;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.utils.client.chat.ChatMessage;

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
      Yanderov.getInstance().setShowIrcMessages(true);
      Yanderov.getInstance().getIrcManager().connect();
   }

   public void deactivate() {
      Yanderov.getInstance().setShowIrcMessages(false);
      Yanderov.getInstance().getIrcManager().disconnect();
   }

   public void sendMessage(String message) {
      if (!this.isState()) {
         ChatMessage.ircmessageWithRed("ÐœÐ¾Ð´ÑƒÐ»ÑŒ IRC Ð²Ñ‹ÐºÐ»ÑŽÑ‡ÐµÐ½");
      } else {
         if (Yanderov.getInstance().getIrcManager().getClient() != null && Yanderov.getInstance().getIrcManager().getClient().isOpen()) {
            Yanderov.getInstance().getIrcManager().getClient().sendMessage(message);
         }

      }
   }
}

