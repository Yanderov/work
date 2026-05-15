package fun.Yanderov.mixins.player.input;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import fun.Yanderov.events.player.InputEvent;
import fun.Yanderov.utils.client.managers.event.EventManager;
import fun.Yanderov.utils.features.aura.warp.Turns;
import fun.Yanderov.utils.features.aura.warp.TurnsConnection;
import fun.Yanderov.utils.features.aura.warp.TurnsConstructor;
import fun.Yanderov.utils.interactions.inv.InventoryFlowManager;
import net.minecraft.class_10185;
import net.minecraft.class_3532;
import net.minecraft.class_743;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin({class_743.class})
public class KeyboardInputMixin extends InputMixin {
   @ModifyExpressionValue(
      method = {"tick"},
      at = {@At(
   value = "NEW",
   target = "(ZZZZZZZ)Lnet/minecraft/util/PlayerInput;"
)}
   )
   private class_10185 tickHook(class_10185 original) {
      InputEvent event = new InputEvent(original);
      EventManager.callEvent(event);
      InventoryFlowManager.input(event);
      return this.transformInput(event.getInput());
   }

   @Unique
   private class_10185 transformInput(class_10185 input) {
      TurnsConnection rotationController = TurnsConnection.INSTANCE;
      Turns angle = rotationController.getCurrentAngle();
      TurnsConstructor configurable = rotationController.getCurrentRotationPlan();
      if (mc.field_1724 != null && angle != null && configurable != null && configurable.isMoveCorrection() && configurable.isFreeCorrection()) {
         float deltaYaw = mc.field_1724.method_36454() - angle.getYaw();
         float z = class_743.method_40218(input.comp_3159(), input.comp_3160());
         float x = class_743.method_40218(input.comp_3161(), input.comp_3162());
         float newX = x * class_3532.method_15362(deltaYaw * ((float)Math.PI / 180F)) - z * class_3532.method_15374(deltaYaw * ((float)Math.PI / 180F));
         float newZ = z * class_3532.method_15362(deltaYaw * ((float)Math.PI / 180F)) + x * class_3532.method_15374(deltaYaw * ((float)Math.PI / 180F));
         int movementSideways = Math.round(newX);
         int movementForward = Math.round(newZ);
         return new class_10185((float)movementForward > 0.0F, (float)movementForward < 0.0F, (float)movementSideways > 0.0F, (float)movementSideways < 0.0F, input.comp_3163(), input.comp_3164(), input.comp_3165());
      } else {
         return input;
      }
   }
}

