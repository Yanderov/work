package fun.Yanderov.features.impl.player;

import fun.Yanderov.events.player.InputEvent;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import fun.Yanderov.utils.display.interfaces.QuickImports;

public class AutoWalk extends Module implements QuickImports {
   public AutoWalk() {
      super("AutoWalk", ModuleCategory.PLAYER);
   }

   @EventHandler
   public void onInput(InputEvent e) {
      e.setDirectional(true, false, false, false);
   }
}

