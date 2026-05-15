package fun.Yanderov.mixins.player.input;

import fun.Yanderov.commands.defaults.BindCommand;
import fun.Yanderov.display.screens.clickgui.MenuScreen;
import fun.Yanderov.events.keyboard.KeyEvent;
import fun.Yanderov.utils.client.managers.event.EventManager;
import net.minecraft.class_309;
import net.minecraft.class_310;
import net.minecraft.class_3675.class_307;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_309.class})
public class KeyboardMixin {
   @Final
   @Shadow
   private class_310 field_1678;

   @Inject(
      method = {"onKey"},
      at = {@At("HEAD")}
   )
   private void onKey(long window, int key, int scanCode, int action, int modifiers, CallbackInfo ci) {
      if (key != -1 && window == this.field_1678.method_22683().method_4490()) {
         if (action == 0 && key == BindCommand.ClickGuiManager.getClickGuiKey() && this.field_1678.field_1755 == null) {
            MenuScreen.INSTANCE.openGui();
         }

         EventManager.callEvent(new KeyEvent(this.field_1678.field_1755, class_307.field_1668, key, action));
      }

   }
}

