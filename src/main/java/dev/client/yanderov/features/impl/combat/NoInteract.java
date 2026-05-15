package dev.client.yanderov.features.impl.combat;

import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.utils.client.Instance;

public class NoInteract extends Module {
   public static NoInteract getInstance() {
      return (NoInteract)Instance.get(NoInteract.class);
   }

   public NoInteract() {
      super("NoInteract", "No Interact", ModuleCategory.COMBAT);
   }
}

