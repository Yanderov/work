package dev.client.modules.impl.movement;

import dev.client.WildClient;
import dev.client.event.classes.TickEvent;
import dev.client.event.interfaces.ITickable;
import dev.client.modules.Category;
import dev.client.modules.IEnableable;
import dev.client.modules.Module;
import dev.client.modules.ModuleBranding;
import dev.client.modules.impl.combat.Aura;
import dev.client.modules.settings.impl.BooleanSetting;
import dev.client.modules.settings.impl.FloatSetting;
import dev.client.util.IUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoulSandBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.math.BlockPos;

@Environment(EnvType.CLIENT)
public class TargetStrafe extends Module implements ITickable, IUtil, IEnableable {
   private final FloatSetting distance = new FloatSetting().name("Distance").value(1.0F).minValue(0.0F).maxValue(6.0F).incriment(0.05F);
   private final BooleanSetting potionBoost = new BooleanSetting().name("PotionBoost").value(false);
   private final BooleanSetting jump = new BooleanSetting().name("Jump").value(false);
   private float side = 1.0F;
   private LivingEntity target = null;
   private int potionBoostLvl = 0;
   private Aura aura;

   public TargetStrafe() {
      super(new ModuleBranding("TargetStrafe", Category.MOVEMENT, "Преследует противника"));
      this.addSetting(this.distance, this.potionBoost, this.jump);
   }

   public void onTick(TickEvent event) {
      if (mc.player != null && mc.world != null) {
         if (mc.player.age >= 10) {
            LivingEntity auraTarget = this.getTarget();
            if (auraTarget != null) {
               this.target = auraTarget;
               if (mc.player.isOnGround() && !mc.options.jumpKey.isPressed() && this.target.isAlive() && this.jump.getValue()) {
                  mc.player.jump();
               }

               if (this.target.isAlive() && !(this.target.getHealth() <= 0.0F)) {
                  if (mc.player.horizontalCollision) {
                     this.side *= -1.0F;
                  }

                  if (mc.options.leftKey.isPressed()) {
                     this.side = 1.0F;
                  }

                  if (mc.options.rightKey.isPressed()) {
                     this.side = -1.0F;
                  }

                  double angle = Math.atan2(mc.player.getZ() - this.target.getZ(), mc.player.getX() - this.target.getX());
                  angle += this.getMotion() / (double)Math.max(mc.player.distanceTo(this.target), this.distance.getMinValue()) * (double)this.side;
                  double x = this.target.getX() + (double)this.distance.getValue() * Math.cos(angle);
                  double z = this.target.getZ() + (double)this.distance.getValue() * Math.sin(angle);
                  double yaw = this.getYaw(mc.player, x, z);
                  double speed = this.getSpeed() * 0.93 + this.getGroundBoost();
                  if (this.potionBoost.getValue()) {
                     this.potionBoostLvl = 0;
                     mc.player.getStatusEffects().forEach((effect) -> {
                        if (effect.getEffectType().value() == StatusEffects.SPEED.value()) {
                           this.potionBoostLvl = Math.min(effect.getAmplifier() + 1, 4);
                        }

                     });
                  } else {
                     this.potionBoostLvl = 0;
                  }

                  double value;
                  switch (this.potionBoostLvl) {
                     case 1 -> value = 1.01;
                     case 2 -> value = 1.03;
                     case 3 -> value = 1.38;
                     case 4 -> value = 1.0475;
                     default -> value = 1.0D;
                  }

                  double multiplier = value;
                  mc.player.setVelocity(speed * multiplier * -Math.sin(Math.toRadians(yaw)), mc.player.getVelocity().y, speed * multiplier * Math.cos(Math.toRadians(yaw)));
               }
            }
         }
      }
   }

   public LivingEntity getTarget() {
      return this.aura.getTarget();
   }

   private double getSpeed() {
      double del = mc.player.hasStatusEffect(StatusEffects.SLOWNESS) ? 0.7 : 1.0D;
      double speed = mc.player.isOnGround() ? 0.26 * del : 0.36 * del;
      return mc.player.getVelocity().y < 0.0D ? speed * 0.98 : speed;
   }

   private double getMotion() {
      return Math.hypot(mc.player.getVelocity().x, mc.player.getVelocity().z);
   }

   private double getGroundBoost() {
      return 0.0D;
   }

   private double getYaw(LivingEntity entity, double x, double z) {
      return Math.toDegrees(Math.atan2(z - entity.getZ(), x - entity.getX())) - 90.0D;
   }

   public boolean strafes() {
      if (mc.player != null && mc.world != null) {
         if (mc.player.isSneaking()) {
            return false;
         } else if (!mc.player.isTouchingWater() && !mc.player.isInLava()) {
            BlockPos pos = mc.player.getBlockPos();
            if (mc.world.getBlockState(pos.up()).isAir() && mc.world.getBlockState(pos.down()).getBlock() == Blocks.WATER) {
               return false;
            } else if (mc.world.getBlockState(pos).getBlock() == Blocks.COBWEB) {
               return false;
            } else if (mc.world.getBlockState(pos.down()).getBlock() instanceof SoulSandBlock) {
               return false;
            } else if (mc.player.getAbilities().flying) {
               return false;
            } else {
               return !mc.player.hasStatusEffect(StatusEffects.LEVITATION);
            }
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public void onEnable() {
      this.aura = (Aura)WildClient.INSTANCE.getModuleManager().getByClass(Aura.class);
      this.target = null;
   }
}

