package dev.client.yanderov.features.impl.player;

import dev.client.yanderov.events.player.InputEvent;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import dev.client.yanderov.utils.display.interfaces.QuickImports;

public class AutoWalk extends Module implements QuickImports {
   public AutoWalk() {
      super("AutoWalk", ModuleCategory.PLAYER);
   }

   @EventHandler
   public void onInput(InputEvent e) {
      e.setDirectional(true, false, false, false);
   }
}

