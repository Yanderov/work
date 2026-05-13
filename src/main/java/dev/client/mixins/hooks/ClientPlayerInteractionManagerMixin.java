package dev.client.mixins.hooks;

import dev.client.WildClient;
import dev.client.event.EventType;
import dev.client.event.classes.AttackEvent;
import dev.client.event.classes.BreakEvent;
import dev.client.event.classes.ClickSlotEvent;
import dev.client.event.classes.UsingItemEvent;
import dev.client.mixins.other.IClientPlayerInteractionManagerMixin;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.client.network.SequencedPacketCreator;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ActionResult.SwingSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
@Mixin({ClientPlayerInteractionManager.class})
public abstract class ClientPlayerInteractionManagerMixin implements IClientPlayerInteractionManagerMixin {
   @Shadow
   private float currentBreakingProgress;

   @Shadow
   protected abstract void sendSequencedPacket(ClientWorld clientWorld, SequencedPacketCreator sequencedPacketCreator);

   @Inject(
      method = {"attackEntity"},
      at = {@At("HEAD")}
   )
   void attack(PlayerEntity player, Entity target, CallbackInfo ci) {
      WildClient.INSTANCE.getEventManager().hookEvent(new AttackEvent(target));
   }

   @Inject(
      method = {"clickSlot"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void clickSlotHook(int syncId, int slotId, int button, SlotActionType actionType, PlayerEntity player, CallbackInfo ci) {
      ClickSlotEvent event = new ClickSlotEvent(actionType, slotId, button, syncId);
      WildClient.INSTANCE.getEventManager().hookEvent(event);
      if (event.isCancelled()) {
         event.resetCancel();
         ci.cancel();
      }

   }

   @Inject(
      method = {"updateBlockBreakingProgress"},
      at = {@At("HEAD")}
   )
   void attack(BlockPos pos, Direction direction, CallbackInfoReturnable<Boolean> cir) {
      WildClient.INSTANCE.getEventManager().hookEvent(new BreakEvent(pos, direction));
   }

   public float getBreakingProgress() {
      return this.currentBreakingProgress;
   }

   @Inject(
      method = {"interactItem"},
      at = {@At("RETURN")}
   )
   public void interactItemHook(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
      Object result = cir.getReturnValue();
      if (result instanceof ActionResult.Success success) {
         if (!success.swingSource().equals(SwingSource.CLIENT)) {
            UsingItemEvent event = new UsingItemEvent(EventType.PRE);
            WildClient.INSTANCE.getEventManager().hookEvent(event);
         }
      }

   }

   @Inject(
      method = {"stopUsingItem"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void stopUsingItemHook(CallbackInfo ci) {
      UsingItemEvent event = new UsingItemEvent(EventType.POST);
      WildClient.INSTANCE.getEventManager().hookEvent(event);
   }

   @Inject(
      method = {"interactItem"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void gameModeHook(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
      UsingItemEvent event = new UsingItemEvent(EventType.START);
      WildClient.INSTANCE.getEventManager().hookEvent(event);
      if (event.isCancelled()) {
         cir.setReturnValue(ActionResult.PASS);
      }

   }

   public void invokeSendSequencedPacket(ClientWorld world, SequencedPacketCreator packetCreator) {
      this.sendSequencedPacket(world, packetCreator);
   }
}
