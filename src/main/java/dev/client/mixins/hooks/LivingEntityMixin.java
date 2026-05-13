package dev.client.mixins.hooks;

import dev.client.WildClient;
import dev.client.event.classes.EntityDeathEvent;
import dev.client.event.classes.MoveCorrectionEvent;
import dev.client.event.classes.TravelEvent;
import dev.client.mixins.other.ILivingEntityMixin;
import dev.client.modules.impl.render.SwingAnimation;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
@Mixin({LivingEntity.class})
public class LivingEntityMixin implements ILivingEntityMixin {
   @Shadow
   private int jumpingCooldown;

   @Inject(
      method = {"getHandSwingDuration"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void getArmSwingAnimationEnd(CallbackInfoReturnable<Integer> info) {
      SwingAnimation swingAnimation = (SwingAnimation)WildClient.INSTANCE.getModuleManager().getByClass(SwingAnimation.class);
      if (swingAnimation.isEnabled()) {
         info.setReturnValue((int)swingAnimation.slow.getValue());
      }

   }

   @Inject(
      method = {"jump"},
      at = {@At("HEAD")},
      cancellable = true
   )
   void receivePacket(CallbackInfo ci) {
      LivingEntity entity = (LivingEntity)(Object)this;
      if (entity instanceof ClientPlayerEntity) {
         float f = this.getJumpVelocityCustom();
         if (!(f <= 1.0E-5F)) {
            Vec3d vec3d = entity.getVelocity();
            entity.setVelocity(vec3d.x, Math.max((double)f, vec3d.y), vec3d.z);
            if (entity.isSprinting()) {
               MoveCorrectionEvent moveCorrectionEvent = new MoveCorrectionEvent(entity.getYaw(), 0.0F);
               WildClient.INSTANCE.getEventManager().hookEvent(moveCorrectionEvent);
               float yaw = moveCorrectionEvent.getYaw();
               float g = yaw * ((float)Math.PI / 180F);
               entity.addVelocityInternal(new Vec3d((double)(-MathHelper.sin(g)) * 0.2, 0.0D, (double)MathHelper.cos(g) * 0.2));
            }

            entity.velocityDirty = true;
         }
      } else {
         float f = this.getJumpVelocityCustom();
         if (!(f <= 1.0E-5F)) {
            Vec3d vec3d = entity.getVelocity();
            entity.setVelocity(vec3d.x, Math.max((double)f, vec3d.y), vec3d.z);
            if (entity.isSprinting()) {
               float g = entity.getYaw() * ((float)Math.PI / 180F);
               entity.addVelocityInternal(new Vec3d((double)(-MathHelper.sin(g)) * 0.2, 0.0D, (double)MathHelper.cos(g) * 0.2));
            }

            entity.velocityDirty = true;
         }
      }

      ci.cancel();
   }

   @Unique
   private float getJumpVelocityCustom() {
      return this.getJumpVelocityCustom(1.0F);
   }

   @Unique
   private float getJumpVelocityCustom(float strength) {
      LivingEntity entity = (LivingEntity)(Object)this;
      return (float)entity.getAttributeValue(EntityAttributes.JUMP_STRENGTH) * strength * this.getCustomJumpVelocityMultiplier() + this.getJumpBoostVelocityModifier();
   }

   @Unique
   private float getJumpBoostVelocityModifier() {
      LivingEntity entity = (LivingEntity)(Object)this;
      return entity.hasStatusEffect(StatusEffects.JUMP_BOOST) ? 0.1F * ((float)entity.getStatusEffect(StatusEffects.JUMP_BOOST).getAmplifier() + 1.0F) : 0.0F;
   }

   @Unique
   private float getCustomJumpVelocityMultiplier() {
      LivingEntity entity = (LivingEntity)(Object)this;
      float f = entity.getWorld().getBlockState(entity.getBlockPos()).getBlock().getJumpVelocityMultiplier();
      float g = entity.getWorld().getBlockState(entity.getVelocityAffectingPos()).getBlock().getJumpVelocityMultiplier();
      return (double)f == 1.0D ? g : f;
   }

   @Inject(
      method = {"onDeath"},
      at = {@At("HEAD")}
   )
   private void onDeath(DamageSource source, CallbackInfo ci) {
      LivingEntity entity = (LivingEntity)(Object)this;
      EntityDeathEvent event = new EntityDeathEvent(entity, source);
      WildClient.INSTANCE.getEventManager().hookEvent(event);
   }

   @Inject(
      method = {"handleStatus"},
      at = {@At("HEAD")}
   )
   private void handleStatus(byte status, CallbackInfo ci) {
      if (status == 3) {
         LivingEntity entity = (LivingEntity)(Object)this;
         EntityDeathEvent event = new EntityDeathEvent(entity, (DamageSource)null);
         WildClient.INSTANCE.getEventManager().hookEvent(event);
      }

   }

   @Inject(
      method = {"calcGlidingVelocity"},
      at = {@At("HEAD")},
      cancellable = true
   )
   void receivePacket(Vec3d oldVelocity, CallbackInfoReturnable<Vec3d> cir) {
      LivingEntity entity = (LivingEntity)(Object)this;
      if (entity == MinecraftClient.getInstance().player) {
         Vec3d vec3d = entity.getRotationVector();
         TravelEvent travelEvent = new TravelEvent((double)entity.getYaw(), (double)entity.getPitch());
         travelEvent.setPitch((double)entity.getPitch());
         WildClient.INSTANCE.getEventManager().hookEvent(travelEvent);
         float f = (float)(travelEvent.getPitch() * (double)((float)Math.PI / 180F));
         double d = Math.sqrt(vec3d.x * vec3d.x + vec3d.z * vec3d.z);
         double e = oldVelocity.horizontalLength();
         double g = this.getEffectiveGravitys();
         double h = MathHelper.square(Math.cos((double)f));
         oldVelocity = oldVelocity.add(0.0D, g * (-1.0D + h * 0.75D), 0.0D);
         if (oldVelocity.y < 0.0D && d > 0.0D) {
            double i = oldVelocity.y * -0.1 * h;
            oldVelocity = oldVelocity.add(vec3d.x * i / d, i, vec3d.z * i / d);
         }

         if (f < 0.0F && d > 0.0D) {
            double i = e * (double)(-MathHelper.sin(f)) * 0.04;
            oldVelocity = oldVelocity.add(-vec3d.x * i / d, i * 3.2, -vec3d.z * i / d);
         }

         if (d > 0.0D) {
            oldVelocity = oldVelocity.add((vec3d.x / d * e - oldVelocity.x) * 0.1, 0.0D, (vec3d.z / d * e - oldVelocity.z) * 0.1);
         }

         cir.setReturnValue(oldVelocity.multiply(0.99D, 0.98D, 0.99D));
      }

   }

   double getEffectiveGravitys() {
      LivingEntity entity = (LivingEntity)(Object)this;
      boolean bl = entity.getVelocity().y <= 0.0D;
      return bl && entity.hasStatusEffect(StatusEffects.SLOW_FALLING) ? Math.min(entity.getFinalGravity(), 0.01) : entity.getFinalGravity();
   }

   public void setJumpingCooldown(int value) {
      this.jumpingCooldown = value;
   }
}
