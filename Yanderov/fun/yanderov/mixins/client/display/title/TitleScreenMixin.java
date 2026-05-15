package fun.Yanderov.mixins.client.display.title;

import fun.Yanderov.display.screens.mainmenu.MainMenu;
import fun.Yanderov.features.impl.misc.SelfDestruct;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_437;
import net.minecraft.class_442;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_442.class})
public abstract class TitleScreenMixin extends class_437 {
   protected TitleScreenMixin() {
      super((class_2561)null);
   }

   @Inject(
      method = {"init"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void replaceTitleScreen(CallbackInfo ci) {
      if (!SelfDestruct.unhooked) {
         class_310.method_1551().method_1507(new MainMenu());
         ci.cancel();
      }
   }
}

