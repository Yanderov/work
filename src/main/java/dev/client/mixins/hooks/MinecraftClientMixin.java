package dev.client.mixins.hooks;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.client.WildClient;
import dev.client.event.classes.DropItemEvent;
import dev.client.mixins.other.IMinecraftClientMixin;
import dev.client.modules.impl.combat.NoInteract;
import dev.client.modules.impl.render.PlayerEsp;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.SharedConstants;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.session.Session;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ActionResult.SwingSource;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
@Mixin({MinecraftClient.class})
public abstract class MinecraftClientMixin implements IMinecraftClientMixin {
   @Shadow
   private int itemUseCooldown;
   @Shadow
   public @Nullable ClientPlayerEntity player;
   @Shadow
   public @Nullable ClientPlayerInteractionManager interactionManager;
   @Shadow
   @Final
   public GameRenderer gameRenderer;
   @Mutable
   @Final
   @Shadow
   private Session session;

   @Shadow
   protected abstract boolean doAttack();

   public void mouseClick() {
      this.doAttack();
   }

   @Inject(method = "doItemUse", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/Hand;values()[Lnet/minecraft/util/Hand;"), cancellable = true)
   public void doItemUseHook(CallbackInfo ci) {
      if (WildClient.INSTANCE.getModuleManager().getByClass(NoInteract.class).isEnabled()) {
         for(Hand hand : Hand.values()) {
            if (!this.player.getStackInHand(hand).isEmpty()) {
               ActionResult result = this.interactionManager.interactItem(this.player, hand);
               if (result.isAccepted()) {
                  if (result instanceof ActionResult.Success) {
                     ActionResult.Success success = (ActionResult.Success)result;
                     if (success.swingSource().equals(SwingSource.CLIENT)) {
                        this.gameRenderer.firstPersonRenderer.resetEquipProgress(hand);
                        this.player.swingHand(hand);
                     }
                  }

                  ci.cancel();
               }
            }
         }
      }

   }

   @Inject(method = "hasOutline", at = @At("HEAD"), cancellable = true)
   private void hasOutline(Entity entity, CallbackInfoReturnable<Boolean> cir) {
      PlayerEsp esp = WildClient.INSTANCE.getModuleManager().getByClass(PlayerEsp.class);
      if (esp != null && esp.isEnabled() && esp.chamsMode.is("Glow") && esp.options.getValueByName("Chams")) {
         if (entity instanceof PlayerEntity) {
            cir.setReturnValue(true);
         }

      }
   }

   @Inject(method = "getWindowTitle", at = @At("HEAD"), cancellable = true)
   private void onWindowTitle(CallbackInfoReturnable<String> cir) {
      cir.setReturnValue("YanderovClient");
   }

   @Unique
   public void setSession(Session session) {
      this.session = session;
   }

   public int getUseCooldown() {
      return this.itemUseCooldown;
   }

   public void setUseCooldown(int val) {
      this.itemUseCooldown = val;
   }

   @WrapOperation(method = "handleInputEvents", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;dropSelectedItem(Z)Z"))
   private boolean hookDropSelectedItem(ClientPlayerEntity instance, boolean entireStack, Operation<Boolean> original) {
      DropItemEvent eventDropItem = new DropItemEvent();
      WildClient.INSTANCE.getEventManager().hookEvent(eventDropItem);
      if (eventDropItem.isCancelled()) {
         eventDropItem.resetCancel();
         return false;
      } else {
         return (Boolean)original.call(this.player, entireStack);
      }
   }
}
