package dev.client.yanderov.utils.features.aura.striking;

import dev.client.yanderov.display.hud.Notifications;
import dev.client.yanderov.events.item.UsingItemEvent;
import dev.client.yanderov.events.packet.PacketEvent;
import dev.client.yanderov.features.impl.combat.Aura;
import dev.client.yanderov.features.impl.combat.TriggerBot;
import dev.client.yanderov.features.impl.movement.AutoSprint;
import dev.client.yanderov.features.impl.movement.ElytraTarget;
import dev.client.yanderov.main.listener.impl.EventListener;
import dev.client.yanderov.utils.display.interfaces.QuickImports;
import dev.client.yanderov.utils.features.aura.utils.MathAngle;
import dev.client.yanderov.utils.features.aura.utils.Pressing;
import dev.client.yanderov.utils.features.aura.utils.RaycastAngle;
import dev.client.yanderov.utils.features.aura.warp.Turns;
import dev.client.yanderov.utils.features.aura.warp.TurnsConnection;
import dev.client.yanderov.utils.interactions.interact.PlayerInteractionHelper;
import dev.client.yanderov.utils.interactions.inv.InventoryFlowManager;
import dev.client.yanderov.utils.interactions.inv.InventoryTask;
import dev.client.yanderov.utils.interactions.simulate.PlayerSimulation;
import dev.client.yanderov.utils.math.calc.Calculate;
import dev.client.yanderov.utils.math.time.StopWatch;
import java.util.function.Predicate;
import net.minecraft.class_124;
import net.minecraft.class_1268;
import net.minecraft.class_1294;
import net.minecraft.class_1309;
import net.minecraft.class_1735;
import net.minecraft.class_1743;
import net.minecraft.class_1802;
import net.minecraft.class_2246;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_2561;
import net.minecraft.class_2596;
import net.minecraft.class_2848;
import net.minecraft.class_2868;
import net.minecraft.class_2879;
import net.minecraft.class_4050;

public class StrikeManager implements QuickImports {
   private final StopWatch attackTimer = new StopWatch();
   private final StopWatch shieldWatch = new StopWatch();
   private final StopWatch sprintCooldown = new StopWatch();
   private final Pressing clickScheduler = new Pressing();
   private int count = 0;
   private boolean prevSprinting;
   private class_1309 lastAttackTarget;
   private float lastAttackHealth;
   private int lastAttackHurtTime;
   private long lastAttackTime;
   private boolean pendingHitCheck;
   private boolean lastAttackWasCrit;
   private class_2848.class_2849 lastSprintCommand = null;
   private boolean pendingStartSprint = false;
   private boolean pendingStopSprint = false;
   private boolean didStopSprint = false;
   private static final long SPRINT_COOLDOWN_MS = 200L;

   void tick() {
      if (this.pendingHitCheck && this.lastAttackTarget != null) {
         if (this.lastAttackTarget.field_6235 > this.lastAttackHurtTime) {
            this.pendingHitCheck = false;
            this.lastAttackTarget = null;
            return;
         }

         if (System.currentTimeMillis() - this.lastAttackTime > 400L) {
            if (Aura.getInstance().isState() && Aura.getInstance().getHitInfoMode() != null && this.lastAttackTarget.method_5805() && this.lastAttackTarget.method_6032() >= this.lastAttackHealth - 0.001F) {
               if (this.lastAttackWasCrit && Aura.getInstance().getHitInfoMode().isSelected("ÐÐµ Ð¿Ñ€Ð¾ÑˆÐµÐ» ÐºÑ€Ð¸Ñ‚")) {
                  this.notifyCritMiss(this.lastAttackTarget);
               } else if (!this.lastAttackWasCrit && Aura.getInstance().getHitInfoMode().isSelected("ÐÐµ Ð¿Ñ€Ð¾ÑˆÐµÐ» ÑƒÐ´Ð°Ñ€")) {
                  this.notifyMiss(this.lastAttackTarget);
               }
            }

            this.pendingHitCheck = false;
            this.lastAttackTarget = null;
         }
      }

   }

   void onPacket(PacketEvent e) {
      class_2596<?> packet = e.getPacket();
      if (packet instanceof class_2879 || packet instanceof class_2868) {
         this.clickScheduler.recalculate();
      }

   }

   void onUsingItem(UsingItemEvent e) {
      if (e.getType() == -1 && !this.shieldWatch.finished((double)50.0F)) {
         e.cancel();
      }

   }

   void handleAttack(StrikerConstructor.AttackPerpetratorConfigurable config) {
      if (this.canAttack(config, 0)) {
         this.preAttackEntity(config);
      }

      boolean elytraMode = Aura.getInstance().getTarget() != null && Aura.getInstance().getTarget().method_6128() && mc.field_1724.method_6128();
      boolean ignoreRaycast = Aura.getInstance().isState() && Aura.getInstance().getAttackSetting().isSelected("Ignore The Walls");
      if (elytraMode) {
         class_243 targetVelocity = config.getTarget().method_18798();
         double targetSpeed = targetVelocity.method_37267();
         float leadTicks = 0.0F;
         if (ElytraTarget.shouldElytraTarget) {
            leadTicks = ElytraTarget.getInstance().elytraForward.getValue();
         }

         class_243 predictedPos = config.getTarget().method_19538().method_1019(targetVelocity.method_1021((double)leadTicks));
         class_238 predictedBox = new class_238(predictedPos.field_1352 - (double)(config.getTarget().method_17681() / 2.0F), predictedPos.field_1351, predictedPos.field_1350 - (double)(config.getTarget().method_17681() / 2.0F), predictedPos.field_1352 + (double)(config.getTarget().method_17681() / 2.0F), predictedPos.field_1351 + (double)config.getTarget().method_17682(), predictedPos.field_1350 + (double)(config.getTarget().method_17681() / 2.0F));
         class_243 eyePos = mc.field_1724.method_33571();
         class_243 lookVec = TurnsConnection.INSTANCE.getRotation().toVector();
         if (!ignoreRaycast && !predictedBox.method_992(eyePos, eyePos.method_1019(lookVec.method_1021((double)config.getMaximumRange()))).isPresent()) {
            return;
         }

         if (!ignoreRaycast && !RaycastAngle.rayTrace(config)) {
            return;
         }

         if (!this.canAttack(config, 0)) {
            return;
         }
      } else {
         if (!ignoreRaycast && !RaycastAngle.rayTrace(config)) {
            return;
         }

         if (!this.canAttack(config, 0)) {
            return;
         }
      }

      String sprintMode = this.getSprintMode();
      if (sprintMode.equals("Legit") && !this.isSprinting()) {
         this.attackEntity(config);
      }

      if (sprintMode.equals("Packet")) {
         mc.field_1724.method_5728(false);
         mc.field_1724.method_46742();
         this.attackEntity(config);
      }

   }

   private void notifyMiss(class_1309 target) {
      if (Aura.getInstance().isState()) {
         if (target != null) {
            String name = target.method_5477().getString();
            class_2561 msg = class_2561.method_43470(name + " hit info: ").method_10852(class_2561.method_43470("miss").method_27692(class_124.field_1061));
            Notifications.getInstance().addList(msg, 3000L);
         }
      }
   }

   private void notifyCritMiss(class_1309 target) {
      if (Aura.getInstance().isState()) {
         if (target != null) {
            String name = target.method_5477().getString();
            class_2561 msg = class_2561.method_43470(name + " hit info: ").method_10852(class_2561.method_43470("Ð½Ðµ Ð¿Ñ€Ð¾ÑˆÐµÐ» ÐºÑ€Ð¸Ñ‚Ð¸Ñ‡ÐµÑÐºÐ¸Ð¹ ÑƒÐ´Ð°Ñ€").method_27692(class_124.field_1061));
            Notifications.getInstance().addList(msg, 3000L);
         }
      }
   }

   private String getSprintMode() {
      if (Aura.getInstance().isState()) {
         return Aura.getInstance().getSprintReset().getSelected();
      } else {
         return TriggerBot.getInstance().isState() ? TriggerBot.getInstance().sprintReset.getSelected() : "Legit";
      }
   }

   void preAttackEntity(StrikerConstructor.AttackPerpetratorConfigurable config) {
      if (config.isShouldUnPressShield() && mc.field_1724.method_6115() && mc.field_1724.method_6030().method_7909().equals(class_1802.field_8255)) {
         mc.field_1761.method_2897(mc.field_1724);
         this.shieldWatch.reset();
      }

      String sprintMode = this.getSprintMode();
      if (sprintMode.equals("Legit")) {
         if (mc.field_1724.method_5624() && this.getTargetDistance() <= (double)this.getAttackRange()) {
            AutoSprint.tickStop = 2;
            mc.field_1690.field_1867.method_23481(false);
            mc.field_1724.method_5728(false);
         }
      }
   }

   void postAttackEntity(StrikerConstructor.AttackPerpetratorConfigurable config) {
   }

   void attackEntity(StrikerConstructor.AttackPerpetratorConfigurable config) {
      if (Aura.getInstance().isState() && Aura.getInstance().getAttackSetting().isSelected("Fake Lag")) {
         Aura.getInstance();
         Aura.tickStop = 1;
      }

      this.attack(config);
      this.breakShield(config);
      this.attackTimer.reset();
      ++this.count;
   }

   private void breakShield(StrikerConstructor.AttackPerpetratorConfigurable config) {
      class_1309 target = config.getTarget();
      Turns angleToPlayer = MathAngle.fromVec3d(mc.field_1724.method_5829().method_1005().method_1020(target.method_33571()));
      boolean targetOnShield = target.method_6115() && target.method_6030().method_7909().equals(class_1802.field_8255);
      boolean angle = Math.abs(TurnsConnection.computeAngleDifference(target.method_36454(), angleToPlayer.getYaw())) < 90.0F;
      class_1735 axe = InventoryTask.getSlot((Predicate)((s) -> s.method_7677().method_7909() instanceof class_1743));
      boolean shieldBreakerEnabled = Aura.getInstance().getAttackSetting().isSelected("Break Shield") && Aura.getInstance().getShieldBreaker().isValue();
      if (shieldBreakerEnabled && targetOnShield && axe != null && angle && InventoryFlowManager.script.isFinished()) {
         InventoryTask.swapHand(axe, class_1268.field_5808, false);
         InventoryTask.closeScreen(true);
         this.attack(config);
         InventoryTask.swapHand(axe, class_1268.field_5808, false, true);
         InventoryTask.closeScreen(true);
      }

   }

   private void attack(StrikerConstructor.AttackPerpetratorConfigurable config) {
      float chance = (float)Calculate.getRandom(0, 100);
      boolean critAttempt = false;
      if (Aura.getInstance().isState()) {
         PlayerSimulation simulated = PlayerSimulation.simulateLocalPlayer(0);
         boolean fall = simulated.fallDistance > 0.0F;
         critAttempt = !simulated.onGround && fall;
      }

      if (Aura.getInstance().isState() && Aura.getInstance().getAttackSetting().isSelected("Hit Chance")) {
         if (chance < Aura.getInstance().getHitChance().getValue()) {
            if (config.getTarget() != null && Aura.getInstance().getHitInfoMode() != null) {
               this.lastAttackTarget = config.getTarget();
               this.lastAttackHealth = config.getTarget().method_6032();
               this.lastAttackHurtTime = config.getTarget().field_6235;
               this.lastAttackTime = System.currentTimeMillis();
               this.lastAttackWasCrit = critAttempt;
               this.pendingHitCheck = true;
            }

            mc.field_1761.method_2918(mc.field_1724, config.getTarget());
         }
      } else if (TriggerBot.getInstance().isState() && TriggerBot.getInstance().attackSetting.isSelected("Hit Chance")) {
         if (chance < TriggerBot.getInstance().hitChance.getValue()) {
            mc.field_1761.method_2918(mc.field_1724, config.getTarget());
         }
      } else {
         if (Aura.getInstance().isState() && config.getTarget() != null && Aura.getInstance().getHitInfoMode() != null) {
            this.lastAttackTarget = config.getTarget();
            this.lastAttackHealth = config.getTarget().method_6032();
            this.lastAttackHurtTime = config.getTarget().field_6235;
            this.lastAttackTime = System.currentTimeMillis();
            this.lastAttackWasCrit = critAttempt;
            this.pendingHitCheck = true;
         }

         mc.field_1761.method_2918(mc.field_1724, config.getTarget());
      }

      mc.field_1724.method_6104(class_1268.field_5808);
   }

   private boolean isSprinting() {
      return EventListener.serverSprint && !mc.field_1724.method_6128() && !mc.field_1724.method_5799();
   }

   private float getAttackRange() {
      if (Aura.getInstance().isState()) {
         return Aura.getInstance().getAttackRange().getValue();
      } else {
         return TriggerBot.getInstance().isState() ? TriggerBot.getInstance().attackRange.getValue() : 3.0F;
      }
   }

   private double getTargetDistance() {
      if (Aura.getInstance().isState() && Aura.getInstance().getTarget() != null) {
         return (double)mc.field_1724.method_5739(Aura.getInstance().getTarget());
      } else {
         return TriggerBot.getInstance().isState() && TriggerBot.getInstance().target != null ? (double)mc.field_1724.method_5739(TriggerBot.getInstance().target) : (double)0.0F;
      }
   }

   public boolean canAttack(StrikerConstructor.AttackPerpetratorConfigurable config, int ticks) {
      for(int i = 0; i <= ticks; ++i) {
         if (this.canCrit(config, i)) {
            return true;
         }
      }

      return false;
   }

   public boolean canCrit(StrikerConstructor.AttackPerpetratorConfigurable config, int ticks) {
      if (mc.field_1724.method_6115() && !mc.field_1724.method_6030().method_7909().equals(class_1802.field_8255) && config.isEatAndAttack()) {
         return false;
      } else if (!this.clickScheduler.isCooldownComplete(false, 1)) {
         return false;
      } else {
         PlayerSimulation simulated = PlayerSimulation.simulateLocalPlayer(ticks);
         boolean noRestrict = !this.hasMovementRestrictions(simulated);
         boolean critState = this.isPlayerInCriticalState(simulated, ticks);
         if (Aura.getInstance().getSmartCrits().isValue() && Aura.getInstance().isState()) {
            if (!noRestrict) {
               return true;
            } else {
               return critState || simulated.onGround;
            }
         } else if (TriggerBot.getInstance().smartCrits.isValue() && TriggerBot.getInstance().isState()) {
            if (!noRestrict) {
               return true;
            } else {
               return critState || simulated.onGround;
            }
         } else {
            return config.isOnlyCritical() && !this.hasMovementRestrictions(simulated) ? this.isPlayerInCriticalState(simulated, ticks) : true;
         }
      }
   }

   private boolean hasMovementRestrictions(PlayerSimulation simulated) {
      return simulated.hasStatusEffect(class_1294.field_5919) || simulated.hasStatusEffect(class_1294.field_5902) || PlayerInteractionHelper.isBoxInBlock(simulated.boundingBox.method_1014(-0.001), class_2246.field_10343) || simulated.isSubmergedInWater() || simulated.isInLava() || simulated.isClimbing() || !PlayerInteractionHelper.canChangeIntoPose(class_4050.field_18076, simulated.pos) || simulated.player.method_31549().field_7479;
   }

   private boolean isPlayerInCriticalState(PlayerSimulation simulated, int ticks) {
      boolean fall = simulated.fallDistance > 0.0F;
      return !simulated.onGround && fall;
   }

   public void setCount(int count) {
      this.count = count;
   }

   public void setPrevSprinting(boolean prevSprinting) {
      this.prevSprinting = prevSprinting;
   }

   public void setLastAttackTarget(class_1309 lastAttackTarget) {
      this.lastAttackTarget = lastAttackTarget;
   }

   public void setLastAttackHealth(float lastAttackHealth) {
      this.lastAttackHealth = lastAttackHealth;
   }

   public void setLastAttackHurtTime(int lastAttackHurtTime) {
      this.lastAttackHurtTime = lastAttackHurtTime;
   }

   public void setLastAttackTime(long lastAttackTime) {
      this.lastAttackTime = lastAttackTime;
   }

   public void setPendingHitCheck(boolean pendingHitCheck) {
      this.pendingHitCheck = pendingHitCheck;
   }

   public void setLastAttackWasCrit(boolean lastAttackWasCrit) {
      this.lastAttackWasCrit = lastAttackWasCrit;
   }

   public void setLastSprintCommand(class_2848.class_2849 lastSprintCommand) {
      this.lastSprintCommand = lastSprintCommand;
   }

   public void setPendingStartSprint(boolean pendingStartSprint) {
      this.pendingStartSprint = pendingStartSprint;
   }

   public void setPendingStopSprint(boolean pendingStopSprint) {
      this.pendingStopSprint = pendingStopSprint;
   }

   public void setDidStopSprint(boolean didStopSprint) {
      this.didStopSprint = didStopSprint;
   }

   public StopWatch getAttackTimer() {
      return this.attackTimer;
   }

   public StopWatch getShieldWatch() {
      return this.shieldWatch;
   }

   public StopWatch getSprintCooldown() {
      return this.sprintCooldown;
   }

   public Pressing getClickScheduler() {
      return this.clickScheduler;
   }

   public int getCount() {
      return this.count;
   }

   public boolean isPrevSprinting() {
      return this.prevSprinting;
   }

   public class_1309 getLastAttackTarget() {
      return this.lastAttackTarget;
   }

   public float getLastAttackHealth() {
      return this.lastAttackHealth;
   }

   public int getLastAttackHurtTime() {
      return this.lastAttackHurtTime;
   }

   public long getLastAttackTime() {
      return this.lastAttackTime;
   }

   public boolean isPendingHitCheck() {
      return this.pendingHitCheck;
   }

   public boolean isLastAttackWasCrit() {
      return this.lastAttackWasCrit;
   }

   public class_2848.class_2849 getLastSprintCommand() {
      return this.lastSprintCommand;
   }

   public boolean isPendingStartSprint() {
      return this.pendingStartSprint;
   }

   public boolean isPendingStopSprint() {
      return this.pendingStopSprint;
   }

   public boolean isDidStopSprint() {
      return this.didStopSprint;
   }
}

