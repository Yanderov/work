package dev.client.mixins.hooks;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import dev.client.WildClient;
import dev.client.event.EventType;
import dev.client.event.classes.MoveEvent;
import dev.client.event.classes.RotationEvent;
import dev.client.event.classes.UsingItemEvent;
import dev.client.modules.impl.player.NoPush;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MovementType;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket.Mode;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin({ClientPlayerEntity.class})
public abstract class ClientPlayerMixin {
   @Shadow
   private boolean lastSprinting;
   @Shadow
   private double lastX;
   @Shadow
   private double lastBaseY;
   @Shadow
   private double lastZ;
   @Shadow
   private float lastYaw;
   @Shadow
   private float lastPitch;
   @Shadow
   private boolean lastOnGround;
   @Final
   @Shadow
   public ClientPlayNetworkHandler networkHandler;
   @Shadow
   private boolean lastHorizontalCollision;
   @Shadow
   private boolean autoJumpEnabled;
   @Shadow
   private int ticksSinceLastPositionPacketSent;

   @Shadow
   protected abstract void autoJump(float dx, float dz);

   @Inject(
      method = {"tick"},
      at = {@At("HEAD")}
   )
   void allowMultiPlayer(CallbackInfo ci) {
      WildClient.INSTANCE.getEventManager().hookEvent(WildClient.INSTANCE.getEventManager().getTickEvent());
   }

   @ModifyExpressionValue(
      method = {"tickMovement"},
      at = {@At(
   value = "INVOKE",
   target = "Lnet/minecraft/client/network/ClientPlayerEntity;isUsingItem()Z"
)}
   )
   private boolean usingItemHook(boolean original) {
      if (original) {
         UsingItemEvent event = new UsingItemEvent(EventType.ON);
         WildClient.INSTANCE.getEventManager().hookEvent(event);
         if (event.isCancelled()) {
            return false;
         }
      }

      return original;
   }

   @Inject(
      method = {"pushOutOfBlocks"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void onPushOutOfBlocksHook(double x, double d, CallbackInfo info) {
      NoPush noPush = (NoPush)WildClient.INSTANCE.getModuleManager().getByClass(NoPush.class);
      if (noPush.isEnabled() && noPush.options.getValueByName("Blocks")) {
         info.cancel();
      }

   }

   @Inject(
      method = {"sendMovementPackets"},
      at = {@At("HEAD")},
      cancellable = true
   )
   void aVoid(CallbackInfo ci) {
      if (MinecraftClient.getInstance() != null) {
         RotationEvent rotationEvent = new RotationEvent(MinecraftClient.getInstance().player.getPitch(), MinecraftClient.getInstance().player.getYaw());
         rotationEvent.setPitch(MinecraftClient.getInstance().player.getPitch());
         rotationEvent.setYaw(MinecraftClient.getInstance().player.getYaw());
         WildClient.INSTANCE.getEventManager().hookEvent(rotationEvent);
         ClientPlayerEntity player = MinecraftClient.getInstance().player;
         boolean bl = player.isSprinting();
         if (bl != this.lastSprinting) {
            ClientCommandC2SPacket.Mode mode = bl ? Mode.START_SPRINTING : Mode.STOP_SPRINTING;
            this.networkHandler.sendPacket(new ClientCommandC2SPacket(player, mode));
            this.lastSprinting = bl;
         }

         if (MinecraftClient.getInstance().getCameraEntity() == MinecraftClient.getInstance().player) {
            double d = player.getX() - this.lastX;
            double e = player.getY() - this.lastBaseY;
            double f = player.getZ() - this.lastZ;
            double g = (double)(rotationEvent.getYaw() - this.lastYaw);
            double h = (double)(rotationEvent.getPitch() - this.lastPitch);
            ++this.ticksSinceLastPositionPacketSent;
            bl = MathHelper.squaredMagnitude(d, e, f) > MathHelper.square(2.0E-4) || this.ticksSinceLastPositionPacketSent >= 20;
            boolean bl2 = g != 0.0D || h != 0.0D;
            if (bl && bl2) {
               this.networkHandler.sendPacket(new PlayerMoveC2SPacket.Full(player.getX(), player.getY(), player.getZ(), rotationEvent.getYaw(), rotationEvent.getPitch(), player.isOnGround(), player.horizontalCollision));
            } else if (bl) {
               this.networkHandler.sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(player.getX(), player.getY(), player.getZ(), player.isOnGround(), player.horizontalCollision));
            } else if (bl2) {
               this.networkHandler.sendPacket(new PlayerMoveC2SPacket.LookAndOnGround(player.getYaw(), player.getPitch(), player.isOnGround(), player.horizontalCollision));
            } else if (this.lastOnGround != player.isOnGround() || this.lastHorizontalCollision != player.horizontalCollision) {
               this.networkHandler.sendPacket(new PlayerMoveC2SPacket.OnGroundOnly(player.isOnGround(), player.horizontalCollision));
            }

            if (bl) {
               this.lastX = player.getX();
               this.lastBaseY = player.getY();
               this.lastZ = player.getZ();
               this.ticksSinceLastPositionPacketSent = 0;
            }

            if (bl2) {
               this.lastYaw = rotationEvent.getYaw();
               this.lastPitch = rotationEvent.getPitch();
            }

            this.lastOnGround = player.isOnGround();
            this.lastHorizontalCollision = player.horizontalCollision;
            this.autoJumpEnabled = (Boolean)MinecraftClient.getInstance().options.getAutoJump().getValue();
         }

         ci.cancel();
      }
   }

   @Inject(
      method = {"move"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void onMoveHook(MovementType type, Vec3d movement, CallbackInfo ci) {
      MoveEvent event = new MoveEvent(movement);
      WildClient.INSTANCE.getEventManager().hookEvent(event);
      Vec3d modifiedMovement = event.getMovement();
      if (!modifiedMovement.equals(movement)) {
         ClientPlayerEntity player = (ClientPlayerEntity)(Object)this;
         double d = player.getX();
         double e = player.getZ();
         ((Entity)(Object)this).move(type, modifiedMovement);
         float f = (float)(player.getX() - d);
         float g = (float)(player.getZ() - e);
         this.autoJump(f, g);
         player.distanceMoved += MathHelper.hypot(f, g) * 0.6F;
         ci.cancel();
      }

   }
}
