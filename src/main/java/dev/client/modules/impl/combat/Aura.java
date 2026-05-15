package dev.client.modules.impl.combat;

import dev.client.WildClient;
import dev.client.event.classes.FireworkEvent;
import dev.client.event.classes.InputEvent;
import dev.client.event.classes.MoveCorrectionEvent;
import dev.client.event.classes.Render2DEvent;
import dev.client.event.classes.Render3DEvent;
import dev.client.event.classes.RotationEvent;
import dev.client.event.classes.SendPacketEvent;
import dev.client.event.classes.TickEvent;
import dev.client.event.classes.TravelEvent;
import dev.client.event.interfaces.IFireworkable;
import dev.client.event.interfaces.IInputable;
import dev.client.event.interfaces.IMoveCorrectionable;
import dev.client.event.interfaces.IRenderable2D;
import dev.client.event.interfaces.IRenderable3D;
import dev.client.event.interfaces.IRotateable;
import dev.client.event.interfaces.ISendPacketable;
import dev.client.event.interfaces.ITickable;
import dev.client.event.interfaces.ITravelable;
import dev.client.mixins.other.IClientCommandC2SPacketMixin;
import dev.client.mixins.other.IPlayerMoveC2SPacket;
import dev.client.modules.Category;
import dev.client.modules.IDisableable;
import dev.client.modules.IEnableable;
import dev.client.modules.Module;
import dev.client.modules.ModuleBranding;
import dev.client.modules.impl.movement.Sprint;
import dev.client.modules.settings.impl.BooleanSetting;
import dev.client.modules.settings.impl.FloatSetting;
import dev.client.modules.settings.impl.ModeSetting;
import dev.client.modules.settings.impl.MultiBoxSetting;
import dev.client.util.IUtil;
import dev.client.util.aura.AuraUtil;
import dev.client.util.aura.Gcd;
import dev.client.util.aura.esp.EspMode;
import dev.client.util.aura.esp.impl.CircleMode;
import dev.client.util.aura.esp.impl.DefaultMode;
import dev.client.util.aura.esp.impl.FireMode;
import dev.client.util.aura.rotate.GRURotation;
import dev.client.util.math.TimerUtil;
import dev.client.util.player.MovementUtil;
import dev.client.util.player.PacketUtil;
import dev.client.util.player.inventory.InventoryUtil;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.network.packet.c2s.play.UpdateSelectedSlotC2SPacket;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket.Mode;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.joml.Vector2f;

@Environment(EnvType.CLIENT)
public class Aura extends Module implements ITickable, IRenderable3D, IUtil, ITravelable, IFireworkable, IInputable, ISendPacketable, IEnableable, IRotateable, IMoveCorrectionable, IRenderable2D, IDisableable {
   private final ModeSetting mode = new ModeSetting().name("Mode").value("ReallyWorld").modes("ReallyWorld", "Snaps", "Normal", "1.8.9", "Aggressive", "FunTime", "Matrix", "SpookyTime", "HolyWorld", "Advanced", "Smooth");
   private final FloatSetting preaim = new FloatSetting().name("PreAim").value(0.5F).minValue(0.0F).maxValue(2.0F).incriment(0.1F);
   private final FloatSetting distance = new FloatSetting().name("Distance").value(3.0F).minValue(3.0F).maxValue(6.0F).incriment(0.1F);
   private final BooleanSetting alwayse = new BooleanSetting() {
      public boolean isVisible() {
         return Aura.this.mode.is("Aggressive");
      }
   }.name("Constant aim").value(true);
   private final BooleanSetting cooldown = new BooleanSetting().name("Attack delay").value(true);
   private final ModeSetting clickMode = new ModeSetting().name("ClickMode").value("1.9").modes("1.9", "1.8");
   private final FloatSetting clickCps = new FloatSetting() {
      public boolean isVisible() {
         return Aura.this.clickMode.is("1.8");
      }
   }.name("CPS").value(12.0F).minValue(1.0F).maxValue(20.0F).incriment(0.5F);
   private final FloatSetting horizontalSpeed = new FloatSetting() {
      public boolean isVisible() {
         return Aura.this.mode.is("Advanced");
      }
   }.name("HorizontalSpeed").value(4.0F).minValue(0.1F).maxValue(360.0F).incriment(0.1F);
   private final FloatSetting verticalSpeed = new FloatSetting() {
      public boolean isVisible() {
         return Aura.this.mode.is("Advanced");
      }
   }.name("VerticalSpeed").value(3.0F).minValue(0.1F).maxValue(360.0F).incriment(0.1F);
   private final FloatSetting randomization = new FloatSetting() {
      public boolean isVisible() {
         return Aura.this.mode.is("Advanced");
      }
   }.name("Randomization").value(2.0F).minValue(0.0F).maxValue(10.0F).incriment(0.1F);
   private final FloatSetting hitChance = new FloatSetting().name("HitChance").value(100.0F).minValue(1.0F).maxValue(100.0F).incriment(1.0F);
   private final ModeSetting espMode = new ModeSetting() {
      public void onChangeState(String val) {
         switch (val) {
            case "Marker":
               Aura.this.curretEspMode = new DefaultMode();
               ((DefaultMode)Aura.this.curretEspMode).setMode(Aura.this.getMode(Aura.this.markerMode.getValue()));
               Aura.this.curretEspMode.setColorMode(Aura.this.markerColor.getValue());
               break;
            case "Fire":
               Aura.this.curretEspMode = new FireMode();
               break;
            case "Circle":
               Aura.this.curretEspMode = new CircleMode();
         }

      }
   }.name("TargetEsp").value("Marker").modes("Marker", "Fire", "Circle");
   private final ModeSetting markerMode = new ModeSetting() {
      public boolean isVisible() {
         return Aura.this.espMode.is("Marker");
      }

      public void onChangeState(String val) {
         ((DefaultMode)Aura.this.curretEspMode).setMode(Aura.this.getMode(val));
         super.onChangeState(val);
      }
   }.name("MarketMode").value("Type 1").modes("Type 1", "Type 2", "Type 3", "Type 4");
   private final ModeSetting markerColor = new ModeSetting() {
      public boolean isVisible() {
         return Aura.this.espMode.is("Marker");
      }

      public void onChangeState(String val) {
         if (Aura.this.curretEspMode != null) {
            Aura.this.curretEspMode.setColorMode(val);
         }
         super.onChangeState(val);
      }
   }.name("MarketColor").value("White").modes("White", "Black", "Black-White");
   private final MultiBoxSetting targets = new MultiBoxSetting().name("Targets").booleanSettings(new BooleanSetting().name("Players").value(false), new BooleanSetting().name("Nakeds").value(false), new BooleanSetting().name("Mobs").value(false), new BooleanSetting().name("Animals").value(false), new BooleanSetting().name("Friends").value(false), new BooleanSetting().name("Naked invisibles").value(false), new BooleanSetting().name("Invisibles").value(false), new BooleanSetting().name("Villager").value(false), new BooleanSetting().name("Bots").value(false));
   private final MultiBoxSetting sort = new MultiBoxSetting().name("Sort").booleanSettings(new BooleanSetting().name("Hp").value(false), new BooleanSetting().name("Effects").value(false), new BooleanSetting().name("Distance").value(false), new BooleanSetting().name("Armor").value(false));
   public ModeSetting correction = new ModeSetting().name("Correction").value("Free").modes("Focused", "Free", "None");
   private final MultiBoxSetting options = new MultiBoxSetting().name("Options").booleanSettings(new BooleanSetting().name("Walls").value(false), new BooleanSetting().name("OnlyCrit").value(false), new BooleanSetting().name("NoEatAttack").value(false), new BooleanSetting().name("ElytraTarget").value(false), new BooleanSetting().name("OffSprint").value(false), new BooleanSetting().name("ShieldBreaker").value(false), new BooleanSetting().name("StopUseItem").value(false), new BooleanSetting().name("MakeBoost").value(false), new BooleanSetting().name("SaveTarget").value(false), new BooleanSetting().name("CameraTarget").value(false));
   public ModeSetting offSprintMode = new ModeSetting() {
      public boolean isVisible() {
         return Aura.this.options.getValueByName("OffSprint");
      }
   }.name("OffSprintMode").value("Default").modes("Default", "Legit");
   private final BooleanSetting smartCrits = new BooleanSetting() {
      public boolean isVisible() {
         return Aura.this.options.getValueByName("OnlyCrit");
      }
   }.name("SmartCrits").value(false);
   private final BooleanSetting miniJump = new BooleanSetting() {
      public boolean isVisible() {
         return Aura.this.options.getValueByName("OnlyCrit");
      }
   }.name("MiniJump").value(false);
   private final BooleanSetting autoFireWork = new BooleanSetting() {
      public boolean isVisible() {
         return Aura.this.options.getValueByName("ElytraTarget");
      }
   }.name("AutoFirework").value(false);
   private final FloatSetting fireworkCooldown = new FloatSetting() {
      public boolean isVisible() {
         return Aura.this.autoFireWork.isVisible() && Aura.this.autoFireWork.getValue();
      }
   }.name("FireworkCooldown").value(1500.0F).minValue(500.0F).maxValue(3000.0F).incriment(50.0F);
   private final BooleanSetting betterTarget = new BooleanSetting() {
      public boolean isVisible() {
         return Aura.this.options.getValueByName("ElytraTarget");
      }
   }.name("BetterTarget").value(false);
   private final FloatSetting elytraDistance = new FloatSetting() {
      public boolean isVisible() {
         return Aura.this.options.getValueByName("ElytraTarget");
      }
   }.name("ElytraDistance").value(15.0F).minValue(10.0F).maxValue(30.0F).incriment(1.0F);
   private EspMode curretEspMode;
   private LivingEntity target;
   private final TimerUtil fireWork = new TimerUtil();
   private final TimerUtil attack = new TimerUtil();
   private final TimerUtil cpsTimer = new TimerUtil();
   private Vector2f rotate = new Vector2f(0.0F, 0.0F);
   private float lastYaw;
   private Sprint sprint;
   private SyncTps syncTps;

   public Aura() {
      super(new ModuleBranding("Aura", Category.COMBAT, "Атакует противников рядом с игроком"));
      this.curretEspMode = new DefaultMode();
      this.addSetting(this.mode, this.preaim, this.distance, this.alwayse, this.cooldown, this.clickMode, this.clickCps, this.horizontalSpeed, this.verticalSpeed, this.randomization, this.hitChance, this.espMode, this.markerMode, this.markerColor, this.targets, this.sort, this.correction, this.options, this.offSprintMode, this.smartCrits, this.miniJump, this.autoFireWork, this.fireworkCooldown, this.betterTarget, this.elytraDistance);
   }

   private String getMode(String mode) {
      switch (mode) {
         case "Type 1" -> {
            return "targetesp1.jpg";
         }
         case "Type 2" -> {
            return "targetesp2.jpg";
         }
         case "Type 3" -> {
            return "targetesp3.png";
         }
         case "Type 4" -> {
            return "targetesp4.png";
         }
         default -> {
            return "targetesp1.jpg";
         }
      }
   }

   public void onTick(TickEvent event) {
      if (this.target == null || !this.isValid(this.target) || !this.options.getValueByName("SaveTarget")) {
         this.updateTarget();
      }

      if (mc.player.isGliding() && this.target != null && this.options.getValueByName("ElytraTarget") && this.fireWork.isReached((long)this.fireworkCooldown.getValue()) && this.autoFireWork.getValue()) {
         InventoryUtil.sendFireWork(this.rotate.x, this.rotate.y);
         this.fireWork.reset();
      }

      if (this.target != null) {
         if (this.options.getValueByName("OnlyCrit") && this.miniJump.getValue() && mc.player.getAttackCooldownProgress(this.syncTps.isEnabled() ? SyncTps.adjustTicks : 2.0F) == 1.0F && mc.player.isOnGround() && this.getTarget() != null) {
            mc.player.setVelocity(0.0D, 0.04, 0.0D);
         }

         boolean isElytraPvP = mc.player.isGliding() && this.options.getValueByName("ElytraTarget");
         boolean canRotate = !isElytraPvP;
         if (this.options.getValueByName("ElytraTarget")) {
            float maxDistance = mc.player.isGliding() ? this.elytraDistance.getValue() : this.distance.getValue() + this.preaim.getValue();
            canRotate = canRotate && this.target.distanceTo(mc.player) <= maxDistance;
         }

         if (canRotate) {
            this.rotations(this.target);
         }

         if (!this.options.getValueByName("NoEatAttack") || !this.isUseItems()) {
            if (this.mode.is("1.8.9")) {
                if (this.target.distanceTo(mc.player) <= this.distance.getValue() && this.attack.isReached((long)(540 + ThreadLocalRandom.current().nextInt(30)))) {
                    this.attack();
                } else if (this.target != null) {
                    mc.player.swingHand(Hand.MAIN_HAND);
                }
            } else {
                this.attack();
            }
         }
      } else {
         if (this.mode.is("Aggressive") && this.alwayse.getValue()) {
             // Keep rotations even without target? No, standard behavior is usually reset.
             // Sirius Aggressive resets if target is null, but maybe constant aim means something else.
         }
         this.rotate = new Vector2f(mc.player.getYaw(), mc.player.getPitch());
      }

      if (this.options.getValueByName("CameraTarget") && mc.targetedEntity != null && this.target != mc.targetedEntity) {
         if (mc.targetedEntity instanceof LivingEntity) {
            LivingEntity living =  (LivingEntity) mc.targetedEntity;
            if (this.isValid(living)) {
               this.target = living;
            }
         }
      }

   }

   private boolean isUseItems() {
      if (this.options.getValueByName("StopUseItem") && mc.player.getBlockingItem() != null && mc.player.getBlockingItem().getItem() == Items.SHIELD) {
         return false;
      } else {
         return mc.player.isUsingItem();
      }
   }

   public void onRender3D(Render3DEvent event) {
      if (this.target != null && mc.player.distanceTo(this.target) <= this.distance.getValue() + this.preaim.getValue()) {
         this.curretEspMode.render(this.target, event.getMatrixStack());
      }

   }

   private void updateTarget() {
      List<LivingEntity> targets = new ArrayList<>();

      for(Entity entity : mc.world.getEntities()) {
         if (entity instanceof LivingEntity living) {
            if (this.isValid(living)) {
               targets.add(living);
            }
         }
      }

      if (targets.isEmpty()) {
         this.target = null;
      } else if (targets.size() == 1) {
         this.target = targets.get(0);
      } else {
         targets.sort(Comparator.comparingDouble((entityx) -> AuraUtil.entity(entityx, this.sort.getValueByName("Hp"), this.sort.getValueByName("Armor"), this.sort.getValueByName("Distance"), (this.distance.getValue() + this.preaim.getValue()), this.sort.getValueByName("Effects"))));
         this.target = targets.get(0);
      }

   }

   private boolean isValid(LivingEntity entity) {
      if (entity != null && entity != mc.player) {
         if (entity.isAlive() && !(entity.getHealth() <= 0.0F)) {
            if (this.options.getValueByName("ElytraTarget") && mc.player.isGliding()) {
               return entity instanceof PlayerEntity;
            } else {
               AntiBot antiBot = (AntiBot)WildClient.INSTANCE.getModuleManager().getByClass(AntiBot.class);
               if (!this.targets.getValueByName("Bots") && antiBot.isBot(entity)) {
                  return false;
               } else if (entity instanceof ClientPlayerEntity) {
                  return false;
               } else if (!this.options.getValueByName("Walls") && !mc.player.canSee(entity)) {
                  return false;
               } else {
                  if ((mc.player.isGliding() || mc.player.getEquippedStack(EquipmentSlot.CHEST).getItem() == Items.ELYTRA) && this.options.getValueByName("ElytraTarget")) {
                     if (mc.player.distanceTo(entity) > this.elytraDistance.getValue()) {
                        return false;
                     }
                  } else if (mc.player.distanceTo(entity) >= this.distance.getValue() + this.preaim.getValue()) {
                     return false;
                  }

                  if (entity instanceof PlayerEntity) {
                     PlayerEntity p = (PlayerEntity)entity;
                     if (!this.targets.getValueByName("Friends") && WildClient.INSTANCE.getFriendManager().isFriend(p.getName().getString())) {
                        return false;
                     }

                     if (p.getName().getString().equalsIgnoreCase(mc.player.getName().getString())) {
                        return false;
                     }
                  }

                  if (entity instanceof PlayerEntity && !this.targets.getValueByName("Players")) {
                     return false;
                  } else if (entity instanceof VillagerEntity && !this.targets.getValueByName("Villager")) {
                     return false;
                  } else if (entity instanceof PlayerEntity && entity.getArmor() == 0 && !this.targets.getValueByName("Nakeds")) {
                     return false;
                  } else if (entity instanceof PlayerEntity && entity.isInvisible() && entity.getArmor() == 0 && !this.targets.getValueByName("Naked invisibles")) {
                     return false;
                  } else if (entity instanceof PlayerEntity && entity.isInvisible() && !this.targets.getValueByName("Invisibles")) {
                     return false;
                  } else if (entity instanceof Monster && !this.targets.getValueByName("Mobs")) {
                     return false;
                  } else if (entity instanceof AnimalEntity && !this.targets.getValueByName("Animals")) {
                     return false;
                  } else {
                     return !entity.isInvulnerable() && entity.isAlive() && !(entity instanceof ArmorStandEntity);
                  }
               }
            }
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public void rotations(LivingEntity targetEntity) {
      if (mc.player.isGliding() && this.options.getValueByName("ElytraTarget")) {
         Vec3d vec = this.target.getPos().add(0.0D, MathHelper.clamp(mc.player.getEyeY() - this.target.getY(), 0.0D, (this.target.getHeight() * (mc.player.distanceTo(this.target) / this.distance.getValue()))), 0.0D).subtract(mc.player.getCameraPosVec(1.0F));
         if (!targetEntity.isGliding()) {
            vec.add(0.0D, (this.distance.getValue() - mc.player.distanceTo(this.target)) * 3.5D, 0.0D);
         }

         float yawToTarget = (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(vec.z, vec.x)) - 90.0D);
         float pitchToTarget = (float)(-Math.toDegrees(Math.atan2(vec.y, Math.hypot(vec.x, vec.z))));
         float yawDelta = MathHelper.wrapDegrees(yawToTarget - this.rotate.x);
         float pitchDelta = MathHelper.wrapDegrees(pitchToTarget - this.rotate.y);
         float s = (new Random()).nextFloat(0.53F);
         float clampedYaw = Math.min(Math.max(Math.abs(yawDelta), 1.0F), 6.0F + s);
         float clampedPitch = Math.min(Math.max(Math.abs(pitchDelta), 1.0F), 5.07F + s);
         if (Math.abs(clampedYaw - this.lastYaw) <= 3.0F) {
            clampedYaw = this.lastYaw + 2.5F;
         }

         float yaw = this.rotate.x + (yawDelta > 0.0F ? clampedYaw : -clampedYaw);
         float pitch = MathHelper.clamp(this.rotate.y + (pitchDelta > 0.0F ? clampedPitch : -clampedPitch), -89.0F, 89.0F);
         float gcd = Gcd.getGCDValue();
         yaw -= (yaw - this.rotate.x) % gcd;
         pitch -= (pitch - this.rotate.y) % gcd;
         this.rotate = new Vector2f(yaw, pitch);
         this.lastYaw = clampedYaw;
      } else {
          switch (this.mode.getValue()) {
            case "ReallyWorld" -> {
               Vec3d targetTorso = this.target.getPos().add(0.0D, (this.target.getHeight() / 2.0F), 0.0D);
               Vec3d eyePos = mc.player.getEyePos();
               Vec3d vec = targetTorso.subtract(eyePos);
               float yawToTarget = (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(vec.z, vec.x)) - 90.0D);
               float pitchToTarget = (float)(-Math.toDegrees(Math.atan2(vec.y, Math.hypot(vec.x, vec.z))));
               float yawDelta = MathHelper.wrapDegrees(yawToTarget - this.rotate.x);
               float pitchDelta = MathHelper.wrapDegrees(pitchToTarget - this.rotate.y);
               float clampedYaw2 = Math.max(Math.abs(yawDelta), 0.0F);
               float clampedPitch2 = Math.max(Math.abs(pitchDelta), 0.0F);
               float yaw = this.rotate.x + (yawDelta > 0.0F ? clampedYaw2 : -clampedYaw2) + ThreadLocalRandom.current().nextFloat(-0.1F, 2.8F);
               float pitch = MathHelper.clamp(this.rotate.y + (pitchDelta > 0.0F ? clampedPitch2 : -clampedPitch2) + ThreadLocalRandom.current().nextFloat(-0.1F, 0.1F), -89.0F, 89.0F);
               float gcd2 = Gcd.getGCD();
               yaw -= (yaw - this.rotate.x) % gcd2;
               pitch -= (pitch - this.rotate.y) % gcd2;
               this.rotate = new Vector2f(yaw, pitch);
            }
            case "FunTime", "Matrix" -> {
               // Плавная ротация с рандомизацией для FunTime/Matrix
               Vec3d targetTorso = this.target.getPos().add(0.0D, (this.target.getHeight() / 2.0F), 0.0D);
               Vec3d eyePos = mc.player.getEyePos();
               Vec3d vec = targetTorso.subtract(eyePos);
               float targetYaw = (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(vec.z, vec.x)) - 90.0D);
               float targetPitch = (float)(-Math.toDegrees(Math.atan2(vec.y, Math.hypot(vec.x, vec.z))));
               float yawDelta = MathHelper.wrapDegrees(targetYaw - this.rotate.x);
               float pitchDelta = MathHelper.wrapDegrees(targetPitch - this.rotate.y);
               
               float clampedYaw = Math.min(Math.abs(yawDelta), 15.0F);
               float clampedPitch = Math.min(Math.abs(pitchDelta), 12.0F);
               
               float yaw = this.rotate.x + (yawDelta > 0 ? clampedYaw : -clampedYaw);
               float pitch = MathHelper.clamp(this.rotate.y + (pitchDelta > 0 ? clampedPitch : -clampedPitch), -89.0F, 89.0F);
               
               // Добавляем рандомизацию
               yaw += ThreadLocalRandom.current().nextFloat(-0.5F, 0.5F);
               pitch += ThreadLocalRandom.current().nextFloat(-0.3F, 0.3F);
               
               float gcd = Gcd.getGCD();
               yaw -= (yaw - this.rotate.x) % gcd;
               pitch -= (pitch - this.rotate.y) % gcd;
               this.rotate = new Vector2f(yaw, pitch);
            }
            case "SpookyTime", "HolyWorld" -> {
               // Средняя скорость ротации
               Vec3d targetTorso = this.target.getPos().add(0.0D, (this.target.getHeight() / 2.0F), 0.0D);
               Vec3d eyePos = mc.player.getEyePos();
               Vec3d vec = targetTorso.subtract(eyePos);
               float targetYaw = (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(vec.z, vec.x)) - 90.0D);
               float targetPitch = (float)(-Math.toDegrees(Math.atan2(vec.y, Math.hypot(vec.x, vec.z))));
               float yawDelta = MathHelper.wrapDegrees(targetYaw - this.rotate.x);
               float pitchDelta = MathHelper.wrapDegrees(targetPitch - this.rotate.y);
               
               float step = 20.0F;
               float yaw = this.rotate.x + MathHelper.clamp(yawDelta, -step, step);
               float pitch = MathHelper.clamp(this.rotate.y + MathHelper.clamp(pitchDelta, -step, step), -89.0F, 89.0F);
               
               float gcd = Gcd.getGCD();
               yaw -= (yaw - this.rotate.x) % gcd;
               pitch -= (pitch - this.rotate.y) % gcd;
               this.rotate = new Vector2f(yaw, pitch);
            }
            case "Advanced" -> {
               // Продвинутая ротация с настройками
               Vec3d targetTorso = this.target.getPos().add(0.0D, (this.target.getHeight() / 2.0F), 0.0D);
               Vec3d eyePos = mc.player.getEyePos();
               Vec3d vec = targetTorso.subtract(eyePos);
               float targetYaw = (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(vec.z, vec.x)) - 90.0D);
               float targetPitch = (float)(-Math.toDegrees(Math.atan2(vec.y, Math.hypot(vec.x, vec.z))));
               float yawDelta = MathHelper.wrapDegrees(targetYaw - this.rotate.x);
               float pitchDelta = MathHelper.wrapDegrees(targetPitch - this.rotate.y);
               
               float hSpeed = this.horizontalSpeed.getValue();
               float vSpeed = this.verticalSpeed.getValue();
               float rand = this.randomization.getValue();
               
               float clampedYaw = Math.min(Math.abs(yawDelta), hSpeed);
               float clampedPitch = Math.min(Math.abs(pitchDelta), vSpeed);
               
               float yaw = this.rotate.x + (yawDelta > 0 ? clampedYaw : -clampedYaw);
               float pitch = MathHelper.clamp(this.rotate.y + (pitchDelta > 0 ? clampedPitch : -clampedPitch), -89.0F, 89.0F);
               
               // Рандомизация
               if (rand > 0) {
                  yaw += ThreadLocalRandom.current().nextFloat(-rand, rand);
                  pitch += ThreadLocalRandom.current().nextFloat(-rand / 2, rand / 2);
               }
               
               float gcd = Gcd.getGCD();
               yaw -= (yaw - this.rotate.x) % gcd;
               pitch -= (pitch - this.rotate.y) % gcd;
               this.rotate = new Vector2f(yaw, pitch);
            }
            case "Smooth" -> {
               // Очень плавная ротация
               Vec3d targetTorso = this.target.getPos().add(0.0D, (this.target.getHeight() / 2.0F), 0.0D);
               Vec3d eyePos = mc.player.getEyePos();
               Vec3d vec = targetTorso.subtract(eyePos);
               float targetYaw = (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(vec.z, vec.x)) - 90.0D);
               float targetPitch = (float)(-Math.toDegrees(Math.atan2(vec.y, Math.hypot(vec.x, vec.z))));
               float yawDelta = MathHelper.wrapDegrees(targetYaw - this.rotate.x);
               float pitchDelta = MathHelper.wrapDegrees(targetPitch - this.rotate.y);
               
               float smoothFactor = 0.3F;
               float yaw = this.rotate.x + yawDelta * smoothFactor;
               float pitch = MathHelper.clamp(this.rotate.y + pitchDelta * smoothFactor, -89.0F, 89.0F);
               
               float gcd = Gcd.getGCD();
               yaw -= (yaw - this.rotate.x) % gcd;
               pitch -= (pitch - this.rotate.y) % gcd;
               this.rotate = new Vector2f(yaw, pitch);
            }
            case "Snaps" -> {
                Vec3d targetTorso = this.target.getPos().add(0.0D, (this.target.getHeight() / 2.0F), 0.0D);
                Vec3d eyePos = mc.player.getEyePos();
                Vec3d vec = targetTorso.subtract(eyePos);
                float yaw = (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(vec.z, vec.x)) - 90.0D);
                float pitch = (float)(-Math.toDegrees(Math.atan2(vec.y, Math.hypot(vec.x, vec.z))));
                float gcd = Gcd.getGCD();
                yaw -= (yaw - this.rotate.x) % gcd;
                pitch -= (pitch - this.rotate.y) % gcd;
                this.rotate = new Vector2f(yaw, pitch);
            }
            case "Normal", "1.8.9" -> {
                Vec3d targetTorso = this.target.getPos().add(0.0D, (this.target.getHeight() / 2.0F), 0.0D);
                Vec3d eyePos = mc.player.getEyePos();
                Vec3d vec = targetTorso.subtract(eyePos);
                float targetYaw = (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(vec.z, vec.x)) - 90.0D);
                float targetPitch = (float)(-Math.toDegrees(Math.atan2(vec.y, Math.hypot(vec.x, vec.z))));
                float diffYaw = MathHelper.wrapDegrees(targetYaw - this.rotate.x);
                float diffPitch = MathHelper.wrapDegrees(targetPitch - this.rotate.y);
                float step = 15.0F;
                float yaw = this.rotate.x + MathHelper.clamp(diffYaw, -step, step);
                float pitch = MathHelper.clamp(this.rotate.y + MathHelper.clamp(diffPitch, -step, step), -90.0F, 90.0F);
                float gcd = Gcd.getGCD();
                yaw -= (yaw - this.rotate.x) % gcd;
                pitch -= (pitch - this.rotate.y) % gcd;
                this.rotate = new Vector2f(yaw, pitch);
            }
            case "Aggressive" -> {
                Vec3d targetTorso = this.target.getPos().add(0.0D, (this.target.getHeight() / 2.0F), 0.0D);
                Vec3d eyePos = mc.player.getEyePos();
                Vec3d vec = targetTorso.subtract(eyePos);
                float targetYaw = (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(vec.z, vec.x)) - 90.0D);
                float targetPitch = (float)(-Math.toDegrees(Math.atan2(vec.y, Math.hypot(vec.x, vec.z))));
                double d3 = Math.pow((Double)mc.options.getMouseSensitivity().getValue() * 0.6F + 0.2F, 3.0);
                double d4 = d3 * 8.0;
                float diffYaw = MathHelper.wrapDegrees(targetYaw - this.rotate.x);
                float diffPitch = MathHelper.wrapDegrees(targetPitch - this.rotate.y);
                int x = (int)Math.round(diffYaw / (d4 * 0.15));
                int y = (int)Math.round(diffPitch / (d4 * 0.15));
                float f = (float)(x * d4 * 0.15);
                float f1 = (float)(y * d4 * 0.15);
                this.rotate.x += f;
                this.rotate.y += f1;
                this.rotate.y = MathHelper.clamp(this.rotate.y, -90.0F, 90.0F);
            }
         }
      }

   }

   private void disableSprint() {
      if (!mc.player.isGliding() && (WildClient.INSTANCE.getRotationManager().isServerSprint() || mc.player.isSprinting()) && this.options.getValueByName("OffSprint")) {
         switch (this.offSprintMode.getValue()) {
            case "Default":
               mc.player.input.movementSideways = 0.0F;
               mc.player.input.movementForward = 0.0F;
               mc.player.setSprinting(false);
               mc.options.sprintKey.setPressed(false);
               PacketUtil.sendPacket(new ClientCommandC2SPacket(mc.player, Mode.STOP_SPRINTING));
               break;
            case "Legit":
               mc.player.input.movementForward = 0.0F;
               mc.player.input.movementSideways = 0.0F;
               mc.options.sprintKey.setPressed(false);
               mc.player.setSprinting(false);
               mc.options.sprintKey.setPressed(false);
         }
      }

   }

   private void attack() {
      boolean canAttack = this.options.getValueByName("OnlyCrit") ? AuraUtil.canAttack(this.smartCrits.getValue(), this.syncTps) : mc.player.getAttackCooldownProgress(2.0F) >= 0.97;
      
      // Проверка шанса удара
      if (ThreadLocalRandom.current().nextFloat() * 100.0F > this.hitChance.getValue()) {
         return;
      }
      
      // Режим 1.8 с CPS
      if (this.clickMode.is("1.8")) {
         float cps = this.clickCps.getValue();
         if (cps <= 0.0F) {
            return;
         }
         long delay = (long)(1000.0F / cps);
         if (!this.cpsTimer.isReached(delay)) {
            return;
         }
         this.cpsTimer.reset();
      } else {
         // Режим 1.9 - проверка кулдауна
         if (!canAttack) {
            return;
         }
      }
      
      if (canAttack && this.target.distanceTo(mc.player) <= this.distance.getValue() && this.attack.isReached((long)(540 + ThreadLocalRandom.current().nextInt(30)))) {
         int b = mc.player.getInventory().selectedSlot;
         boolean swapped = false;
         if (this.options.getValueByName("MakeBoost")) {
            for(int i = 0; i < 9; ++i) {
               if (mc.player.getInventory().getStack(i).getItem() == Items.MACE && !swapped) {
                  mc.player.networkHandler.sendPacket(new UpdateSelectedSlotC2SPacket(i));
                  swapped = true;
               }
            }
         }

         this.sprint.setCanSprint(false);
         this.disableSprint();
         if (this.options.getValueByName("StopUseItem") && mc.player.getBlockingItem() != null && mc.player.getBlockingItem().getItem() == Items.SHIELD) {
            mc.interactionManager.stopUsingItem(mc.player);
         }

         mc.interactionManager.attackEntity(mc.player, this.target);
         mc.player.swingHand(Hand.MAIN_HAND);
         if (this.options.getValueByName("MakeBoost") && swapped) {
            mc.player.networkHandler.sendPacket(new UpdateSelectedSlotC2SPacket(b));
         }

         if (this.options.getValueByName("ShieldBreaker")) {
            LivingEntity livingEntity = this.target;
            if (livingEntity instanceof PlayerEntity) {
               PlayerEntity player = (PlayerEntity)livingEntity;
               AuraUtil.breakShield(player);
            }
         }

         if (mc.player.isGliding() && this.options.getValueByName("ElytraTarget") && (this.fireWork.isReached(1200L) || mc.player.isGliding() && this.options.getValueByName("ElytraTarget") && this.betterTarget.getValue())) {
            InventoryUtil.sendFireWork(this.rotate.x, this.rotate.y);
            this.fireWork.reset();
         }

         this.sprint.getTimerUtil().reset();
         this.sprint.setCanSprint(true);
         this.attack.reset();
      }

   }

   public void onFirework(FireworkEvent fireworkEvent) {
      if (this.options.getValueByName("ElytraTarget")) {
         fireworkEvent.setYaw(this.rotate.x);
         fireworkEvent.setPitch(this.rotate.y);
      }

   }

   public void onTravel(TravelEvent travelEvent) {
      if (this.options.getValueByName("ElytraTarget")) {
         travelEvent.setPitch(this.rotate.y);
      }

   }

   public void onInput(InputEvent event) {
      if (this.target != null && this.correction.is("Free")) {
         MovementUtil.fixMovement(event, this.rotate.x);
      }

   }

   public void onSendPacket(SendPacketEvent sendPacketEvent) {
      Packet packet = sendPacketEvent.getPacket();
      if (packet instanceof PlayerMoveC2SPacket movePacket) {
         ((IPlayerMoveC2SPacket)movePacket).setPitch(this.rotate.y);
         ((IPlayerMoveC2SPacket)movePacket).setYaw(this.rotate.x);
      }

      if (!this.sprint.isCanSprint() && packet instanceof ClientCommandC2SPacket commandPacket) {
         if (commandPacket.getMode() == Mode.START_SPRINTING) {
            ((IClientCommandC2SPacketMixin)commandPacket).setMode(Mode.STOP_SPRINTING);
         }
      }

   }

   public void onEnable() {
      this.syncTps = WildClient.INSTANCE.getModuleManager().getByClass(SyncTps.class);
      GRURotation.get().reload();
      this.rotate = new Vector2f(mc.player.getYaw(), mc.player.getPitch());
      this.sprint = WildClient.INSTANCE.getModuleManager().getByClass(Sprint.class);
      this.target = null;
   }

   public void onRotate(RotationEvent rotationEvent) {
      mc.player.bodyYaw = this.rotate.x;
      mc.player.headYaw = this.rotate.x;
      WildClient.INSTANCE.setBodyPitch(this.rotate.y);
      rotationEvent.setYaw(this.rotate.x);
      rotationEvent.setPitch(this.rotate.y);
   }

   public void moveCorrection(MoveCorrectionEvent event) {
      if (!this.correction.is("None")) {
         event.setYaw(this.rotate.x);
         event.setPitch(this.rotate.y);
      }

   }

   public LivingEntity getTarget() {
      return this.target;
   }

   public void onRender2D(Render2DEvent event) {
      boolean isElytraPvP = mc.player.isGliding() && this.options.getValueByName("ElytraTarget");
      if (this.target != null) {
         if (this.options.getValueByName("ElytraTarget")) {
            if (mc.player.isGliding()) {
               if (!(this.target.distanceTo(mc.player) <= this.elytraDistance.getValue())) {
                  return;
               }
            } else if (!(this.target.distanceTo(mc.player) <= this.distance.getValue() + this.preaim.getValue())) {
               return;
            }
         }

         if (isElytraPvP) {
            this.rotations(this.target);
         }
      }

   }

   public Vector2f getRotate() {
      return this.rotate;
   }

   public void onDisable() {
      this.target = null;
   }
}

