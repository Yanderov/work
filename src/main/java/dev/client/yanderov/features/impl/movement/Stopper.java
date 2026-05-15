package dev.client.yanderov.features.impl.movement;

import dev.client.yanderov.events.packet.PacketEvent;
import dev.client.yanderov.events.player.AttackEvent;
import dev.client.yanderov.events.player.TickEvent;
import dev.client.yanderov.features.impl.combat.Aura;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.features.module.setting.Setting;
import dev.client.yanderov.features.module.setting.implement.BooleanSetting;
import dev.client.yanderov.features.module.setting.implement.SliderSettings;
import dev.client.yanderov.utils.client.Instance;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import dev.client.yanderov.utils.display.interfaces.QuickImports;
import dev.client.yanderov.utils.math.time.StopWatch;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_2596;
import net.minecraft.class_2828;

public class Stopper extends Module implements QuickImports {
   private double posX;
   private double posY;
   private double posZ;
   private float yaw;
   private float pitch;
   private boolean onGround;
   private boolean activated = false;
   private boolean readyForCrit = false;
   private final StopWatch attackTimer = new StopWatch();
   private final StopWatch damageCooldownTimer = new StopWatch();
   private int critStreak = 0;
   private int lastHurtTime = 0;
   private final SliderSettings fallDistance = (new SliderSettings("FallDistance", "ÐœÐ¸Ð½Ð¸Ð¼Ð°Ð»ÑŒÐ½Ð°Ñ Ð´Ð¸ÑÑ‚Ð°Ð½Ñ†Ð¸Ñ Ð¿Ð°Ð´ÐµÐ½Ð¸Ñ")).setValue(0.5F).range(0.1F, 3.0F);
   private final BooleanSetting allowAttacking = (new BooleanSetting("AllowAttacking", "Ð Ð°Ð·Ñ€ÐµÑˆÐ¸Ñ‚ÑŒ Ð°Ð²Ñ‚Ð¾-Ð°Ñ‚Ð°ÐºÑƒ Ð²Ð¾ Ð²Ñ€ÐµÐ¼Ñ ÑÑ‚Ð¾Ð¿Ð°")).setValue(true);
   private final BooleanSetting preserveRotations = (new BooleanSetting("PreserveRotations", "Ð¡Ð¾Ñ…Ñ€Ð°Ð½ÑÑ‚ÑŒ Ñ‚ÐµÐºÑƒÑ‰Ð¸Ðµ Ð¿Ð¾Ð²Ð¾Ñ€Ð¾Ñ‚Ñ‹")).setValue(true).visible(() -> this.allowAttacking.isValue());
   private final BooleanSetting directAttack = (new BooleanSetting("DirectAttack", "Ð‘Ð¸Ñ‚ÑŒ Ð½Ð°Ð¿Ñ€ÑÐ¼ÑƒÑŽ Ñ‡ÐµÑ€ÐµÐ· Aura")).setValue(true).visible(() -> this.allowAttacking.isValue());
   private final BooleanSetting onlyCrits = (new BooleanSetting("OnlyCrits", "Ð‘Ð¸Ñ‚ÑŒ Ñ‚Ð¾Ð»ÑŒÐºÐ¾ Ñ ÐºÑ€Ð¸Ñ‚Ð°Ð¼Ð¸")).setValue(true).visible(() -> this.allowAttacking.isValue() && this.directAttack.isValue());
   private final SliderSettings attackDelay = (new SliderSettings("AttackDelay", "Ð—Ð°Ð´ÐµÑ€Ð¶ÐºÐ° Ð¼ÐµÐ¶Ð´Ñƒ Ð°Ñ‚Ð°ÐºÐ°Ð¼Ð¸ (ms)")).setValue(850.0F).range(100, 2000).visible(() -> this.allowAttacking.isValue() && this.directAttack.isValue());
   private final SliderSettings damageCooldown = (new SliderSettings("DamageCooldown", "ÐšÑƒÐ»Ð´Ð°ÑƒÐ½ Ð¿Ð¾ÑÐ»Ðµ Ð¿Ð¾Ð»ÑƒÑ‡ÐµÐ½Ð¸Ñ ÑƒÑ€Ð¾Ð½Ð° (ms)")).setValue(1000.0F).range(0, 5000).visible(() -> this.allowAttacking.isValue());
   private final BooleanSetting pauseOnDamage = (new BooleanSetting("PauseOnDamage", "ÐŸÐ°ÑƒÐ·Ð° Ð°Ð²Ñ‚Ð¾-Ð°Ñ‚Ð°ÐºÐ¸ Ð¿Ð¾ÑÐ»Ðµ Ð¿Ð¾Ð»ÑƒÑ‡ÐµÐ½Ð¸Ñ ÑƒÑ€Ð¾Ð½Ð°")).setValue(true).visible(() -> this.allowAttacking.isValue());

   public Stopper() {
      super("Stopper", ModuleCategory.MOVEMENT);
      this.setup(new Setting[]{this.fallDistance, this.allowAttacking, this.preserveRotations, this.directAttack, this.onlyCrits, this.attackDelay, this.damageCooldown, this.pauseOnDamage});
   }

   public static Stopper getInstance() {
      return (Stopper)Instance.get(Stopper.class);
   }

   public void activate() {
      this.resetState();
   }

   public void deactivate() {
      this.resetState();
   }

   private void resetState() {
      this.activated = false;
      this.readyForCrit = false;
      this.attackTimer.reset();
      this.damageCooldownTimer.reset();
      this.critStreak = 0;
      this.lastHurtTime = 0;
   }

   private void savePosition() {
      if (mc.field_1724 != null) {
         this.posX = mc.field_1724.method_23317();
         this.posY = mc.field_1724.method_23318();
         this.posZ = mc.field_1724.method_23321();
         this.yaw = mc.field_1724.method_36454();
         this.pitch = mc.field_1724.method_36455();
         this.onGround = mc.field_1724.method_24828();
      }
   }

   private void restorePositionAndMotion(boolean keepRotations) {
      if (mc.field_1724 != null) {
         float curYaw = mc.field_1724.method_36454();
         float curPitch = mc.field_1724.method_36455();
         mc.field_1724.method_23327(this.posX, this.posY, this.posZ);
         if (this.allowAttacking.isValue() && this.preserveRotations.isValue() && keepRotations) {
            mc.field_1724.method_36456(curYaw);
            mc.field_1724.method_36457(curPitch);
         } else {
            mc.field_1724.method_36456(this.yaw);
            mc.field_1724.method_36457(this.pitch);
         }

         mc.field_1724.method_18800((double)0.0F, (double)0.0F, (double)0.0F);
         if (this.onlyCrits.isValue()) {
            mc.field_1724.method_24830(false);
            this.readyForCrit = true;
         } else {
            mc.field_1724.method_24830(this.onGround);
            this.updateCritReadiness();
         }

      }
   }

   private void updateCritReadiness() {
      if (mc.field_1724 != null) {
         this.readyForCrit = !mc.field_1724.method_24828() && !mc.field_1724.method_5869() && !mc.field_1724.method_5771();
      }
   }

   private boolean canAttackNow() {
      if (this.allowAttacking.isValue() && this.directAttack.isValue()) {
         return this.pauseOnDamage.isValue() && !this.damageCooldownTimer.finished((double)this.damageCooldown.getValue()) ? false : this.attackTimer.finished((double)this.attackDelay.getValue());
      } else {
         return false;
      }
   }

   private void tryAttackAuraTarget() {
      if (this.canAttackNow()) {
         Aura aura = Aura.getInstance();
         if (aura != null && aura.isState()) {
            class_1297 target = aura.getCurrentTarget();
            if (target != null) {
               if (!this.onlyCrits.isValue() || this.readyForCrit) {
                  this.performAttack(target);
                  this.attackTimer.reset();
                  this.readyForCrit = false;
                  ++this.critStreak;
               }
            }
         }
      }
   }

   private void performAttack(class_1297 target) {
      if (mc.field_1724 != null && mc.field_1761 != null) {
         boolean wasOnGround = mc.field_1724.method_24828();
         if (this.onlyCrits.isValue()) {
            mc.field_1724.method_24830(false);
            mc.method_1562().method_52787(new class_2828.class_5911(false, false));
         }

         mc.field_1761.method_2918(mc.field_1724, target);
         mc.field_1724.method_6104(class_1268.field_5808);
         if (!this.onlyCrits.isValue()) {
            mc.field_1724.method_24830(wasOnGround);
         }

      }
   }

   @EventHandler
   public void onTick(TickEvent e) {
      if (this.isState() && mc.field_1724 != null && mc.field_1687 != null) {
         if (mc.field_1724.field_6235 > 0 && mc.field_1724.field_6235 != this.lastHurtTime) {
            this.lastHurtTime = mc.field_1724.field_6235;
            this.damageCooldownTimer.reset();
         }

         if (!this.activated) {
            if (mc.field_1724.method_18798().field_1351 < (double)(-this.fallDistance.getValue()) && !mc.field_1724.method_24828()) {
               this.savePosition();
               this.activated = true;
            }
         } else if (mc.field_1724.method_24828()) {
            this.activated = false;
         }

         if (this.activated) {
            this.restorePositionAndMotion(true);
            if (this.allowAttacking.isValue() && this.directAttack.isValue()) {
               this.tryAttackAuraTarget();
            }

         }
      }
   }

   @EventHandler
   public void onPacket(PacketEvent e) {
      if (this.isState() && mc.field_1724 != null && mc.field_1687 != null) {
         if (this.activated) {
            if (e.isSend()) {
               class_2596<?> pkt = e.getPacket();
               if (pkt instanceof class_2828) {
                  e.cancel();
                  boolean keepRot = this.allowAttacking.isValue() && this.preserveRotations.isValue();
                  float sendYaw = keepRot ? mc.field_1724.method_36454() : this.yaw;
                  float sendPitch = keepRot ? mc.field_1724.method_36455() : this.pitch;
                  boolean sendOnGround = !this.onlyCrits.isValue() || !this.allowAttacking.isValue();
                  mc.method_1562().method_52787(new class_2828.class_2830(this.posX, this.posY, this.posZ, sendYaw, sendPitch, sendOnGround, false));
               }

            }
         }
      }
   }

   @EventHandler
   public void onAttack(AttackEvent e) {
      if (this.isState()) {
         ++this.critStreak;
      }
   }

   public String getSuffix() {
      return this.activated ? "Active" + (this.critStreak > 0 ? " | Crits: " + this.critStreak : "") : null;
   }
}

