package fun.Yanderov.features.impl.misc;

import antidaunleak.api.UserProfile;
import fun.Yanderov.Yanderov;
import fun.Yanderov.commands.CommandDispatcher;
import fun.Yanderov.events.chat.ChatEvent;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.utils.client.chat.ChatMessage;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import fun.Yanderov.utils.math.calc.Calculate;
import fun.Yanderov.utils.math.time.StopWatch;

public class SelfDestruct extends Module {
   public static boolean unhooked;
   StopWatch timer = new StopWatch();

   public SelfDestruct() {
      super("SelfDestruct", "Self Destruct", ModuleCategory.MISC);
   }

   public void activate() {
      unhooked = true;
      Yanderov.getInstance().getDiscordManager().stopRPC();

      for(Module module : Yanderov.getInstance().getModuleProvider().getModules()) {
         if (module != this && module.isState()) {
            module.setState(false);
         }
      }

      ChatMessage.brandmessage("Ð”Ð»Ñ Ð²Ð¾Ð·Ð²Ñ€Ð°Ñ‰ÐµÐ½Ð¸Ñ Ñ‡Ð¸Ñ‚Ð° Ð²Ð¿Ð¸ÑˆÐ¸Ñ‚Ðµ Ð² Ñ‡Ð°Ñ‚ Ð²Ð°Ñˆ username Ð² Ñ‡Ð¸Ñ‚Ðµ");
      ChatMessage.brandmessage("Ð¡Ð¾Ð¾Ð±Ñ‰ÐµÐ½Ð¸Ðµ ÑƒÐ´Ð°Ð»Ð¸Ñ‚ÑÑ Ñ‡ÐµÑ€ÐµÐ· Ð¿Ð¾Ð» ÑÐµÐºÑƒÐ½Ð´Ñ‹");
      if (this.timer.every((double)500.0F)) {
         mc.field_1705.method_1743().method_1808(true);
      }

      for(Module module : Yanderov.getInstance().getModuleProvider().getModules()) {
         module.setKey(-1);
      }

      Yanderov.getInstance().getCommandDispatcher();
      CommandDispatcher.prefix = "" + Calculate.getRandom(0, 9999999);
      super.activate();
   }

   @EventHandler
   public void onChat(ChatEvent event) {
      String msg = event.getMessage().trim();
      if (msg.equalsIgnoreCase(UserProfile.getInstance().profile("username"))) {
         unhooked = false;
         Yanderov.getInstance().getDiscordManager().setRunning(true);
         this.state = false;
         Yanderov.getInstance().getCommandDispatcher();
         CommandDispatcher.prefix = ".";
         ChatMessage.brandmessage("Unhook reset to FALSE");
         event.setCancelled(true);
      }

   }

   public void deactivate() {
      unhooked = false;
      super.deactivate();
   }
}

