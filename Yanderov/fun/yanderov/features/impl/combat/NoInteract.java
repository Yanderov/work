package fun.Yanderov.features.impl.combat;

import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.utils.client.Instance;

public class NoInteract extends Module {
   public static NoInteract getInstance() {
      return (NoInteract)Instance.get(NoInteract.class);
   }

   public NoInteract() {
      super("NoInteract", "No Interact", ModuleCategory.COMBAT);
   }
}

