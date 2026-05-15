package dev.client.yanderov.mixins.client.screen.ingame;

import dev.client.yanderov.events.container.HandledScreenEvent;
import dev.client.yanderov.utils.client.managers.event.EventManager;
import net.minecraft.class_1735;
import net.minecraft.class_332;
import net.minecraft.class_465;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_465.class})
public abstract class HandledScreenMixin {
   @Shadow
   public int field_2792;
   @Shadow
   public int field_2779;
   @Shadow
   protected @Nullable class_1735 field_2787;

   @Inject(
      method = {"render"},
      at = {@At("RETURN")}
   )
   public void render(class_332 context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
      EventManager.callEvent(new HandledScreenEvent(context, this.field_2787, this.field_2792, this.field_2779));
   }
}

