package fun.Yanderov.features.impl.movement;

import fun.Yanderov.events.packet.PacketEvent;
import fun.Yanderov.events.player.TickEvent;
import fun.Yanderov.features.impl.combat.Aura;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.features.module.setting.Setting;
import fun.Yanderov.features.module.setting.implement.BooleanSetting;
import fun.Yanderov.features.module.setting.implement.SliderSettings;
import fun.Yanderov.utils.client.Instance;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import fun.Yanderov.utils.interactions.interact.PlayerInteractionHelper;
import fun.Yanderov.utils.math.time.StopWatch;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_2678;
import net.minecraft.class_2724;
import net.minecraft.class_2799;
import net.minecraft.class_2824;
import net.minecraft.class_2828;
import net.minecraft.class_2799.class_2800;

public class TickRangeManipulator extends Module {
   private final SliderSettings maxTeleportSkipTick = (new SliderSettings("MaxTeleportSkipTick", "ÐœÐ°ÐºÑÐ¸Ð¼ÑƒÐ¼ Ð¿Ñ€Ð¾Ð¿ÑƒÑ‰ÐµÐ½Ð½Ñ‹Ñ… Ñ‚Ð¸ÐºÐ¾Ð²")).setValue(4.0F).range(1, 20);
   private final SliderSettings minSkipTickDistance = (new SliderSettings("MinSkipTickDistance", "ÐœÐ¸Ð½. Ð´Ð¸ÑÑ‚Ð°Ð½Ñ†Ð¸Ñ Ð´Ð¾ Ñ†ÐµÐ»Ð¸ Ð´Ð»Ñ ÑÐºÐ¸Ð¿Ð°")).setValue(4.0F).range(0.5F, 15.0F);
   private final SliderSettings speedSkipTick = (new SliderSettings("SpeedSkipTick", "Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Ñ‚ÐµÐ»ÐµÐ¿Ð¾Ñ€Ñ‚Ð° (Ð¼Ð½Ð¾Ð¶Ð¸Ñ‚ÐµÐ»ÑŒ)")).setValue(1.5F).range(0.1F, 5.0F);
   private final SliderSettings minSkipDistanceTeleport = (new SliderSettings("MinSkipDistanceTeleport", "ÐœÐ¸Ð½. Ð´Ð¸ÑÑ‚Ð°Ð½Ñ†Ð¸Ñ Ñ‚ÐµÐ»ÐµÐ¿Ð¾Ñ€Ñ‚Ð°")).setValue(2.0F).range(0.5F, 10.0F);
   private final BooleanSetting instantTeleport = (new BooleanSetting("InstantTeleport", "ÐœÐ³Ð½Ð¾Ð²ÐµÐ½Ð½Ñ‹Ð¹ Ñ‚ÐµÐ»ÐµÐ¿Ð¾Ñ€Ñ‚ Ð±ÐµÐ· Ð¾Ð¶Ð¸Ð´Ð°Ð½Ð¸Ñ Ñ‚Ð¸ÐºÐ¾Ð²")).setValue(true);
   private final SliderSettings maxTeleportDistance = (new SliderSettings("MaxTeleportDistance", "ÐœÐ°ÐºÑÐ¸Ð¼Ð°Ð»ÑŒÐ½Ð°Ñ Ð´Ð¸ÑÑ‚Ð°Ð½Ñ†Ð¸Ñ Ð¾Ð´Ð½Ð¾Ð³Ð¾ Ñ‚Ð¿")).setValue(6.0F).range(0.5F, 30.0F);
   private final BooleanSetting lagRangeMoveCorrection = (new BooleanSetting("LagRangeMoveCorrection", "ÐšÐ¾Ñ€Ñ€ÐµÐºÑ†Ð¸Ñ Ñ LagRange")).setValue(false);
   private final BooleanSetting onlyGround = (new BooleanSetting("OnlyGround", "Ð¢Ð¾Ð»ÑŒÐºÐ¾ Ð½Ð° Ð·ÐµÐ¼Ð»Ðµ")).setValue(true);
   private final BooleanSetting onlyKillAura = (new BooleanSetting("OnlyKillAura", "Ð¢Ð¾Ð»ÑŒÐºÐ¾ Ð¿Ñ€Ð¸ Ð²ÐºÐ»ÑŽÑ‡ÐµÐ½Ð½Ð¾Ð¹ Aura")).setValue(false);
   private final SliderSettings delayBetweenLags = (new SliderSettings("DelayBetweenLags", "Ð—Ð°Ð´ÐµÑ€Ð¶ÐºÐ° Ð¼ÐµÐ¶Ð´Ñƒ Ð»Ð°Ð³Ð°Ð¼Ð¸ (ms)")).setValue(1500.0F).range(0, 10000);
   private final SliderSettings delayAfterAttack = (new SliderSettings("DelayAfterAttack", "Ð—Ð°Ð´ÐµÑ€Ð¶ÐºÐ° Ð¿Ð¾ÑÐ»Ðµ Ð°Ñ‚Ð°ÐºÐ¸ (ms)")).setValue(800.0F).range(0, 10000);
   private boolean skippingTicks = false;
   private int skippedTicks = 0;
   private class_243 serverPosDuringSkip = null;
   private class_243 lastLocalPos = null;
   private StopWatch lagTimer = new StopWatch();
   private StopWatch attackTimer = new StopWatch();

   public TickRangeManipulator() {
      super("TickRangeManipulator", ModuleCategory.MOVEMENT);
      this.setup(new Setting[]{this.maxTeleportSkipTick, this.minSkipTickDistance, this.speedSkipTick, this.minSkipDistanceTeleport, this.instantTeleport, this.maxTeleportDistance, this.lagRangeMoveCorrection, this.onlyGround, this.onlyKillAura, this.delayBetweenLags, this.delayAfterAttack});
   }

   public void activate() {
      this.skippingTicks = false;
      this.skippedTicks = 0;
      this.serverPosDuringSkip = null;
      this.lastLocalPos = null;
      this.lagTimer.reset();
      this.attackTimer.reset();
   }

   public void deactivate() {
      this.skippingTicks = false;
      this.skippedTicks = 0;
      this.serverPosDuringSkip = null;
      this.lastLocalPos = null;
   }

   private class_1309 getTarget() {
      Aura aura = (Aura)Instance.get(Aura.class);
      if (aura != null && aura.isState() && aura.getCurrentTarget() != null) {
         return aura.getCurrentTarget();
      } else if (mc.field_1687 != null && mc.field_1724 != null) {
         double best = Double.MAX_VALUE;
         class_1309 bestEnt = null;

         for(class_1297 ent : mc.field_1687.method_18112()) {
            if (ent instanceof class_1309) {
               class_1309 living = (class_1309)ent;
               if (ent != mc.field_1724 && !ent.method_31481() && ent.method_5805()) {
                  double d = (double)living.method_5739(mc.field_1724);
                  if (d < best) {
                     best = d;
                     bestEnt = living;
                  }
               }
            }
         }

         return bestEnt;
      } else {
         return null;
      }
   }

   private boolean isAuraRequiredAndActive() {
      if (!this.onlyKillAura.isValue()) {
         return true;
      } else {
         Aura aura = (Aura)Instance.get(Aura.class);
         return aura != null && aura.isState() && aura.getCurrentTarget() != null;
      }
   }

   private boolean canStartSkip(class_1309 target) {
      if (mc.field_1724 != null && mc.field_1687 != null && target != null) {
         if (!this.isAuraRequiredAndActive()) {
            return false;
         } else if (this.onlyGround.isValue() && !mc.field_1724.method_24828()) {
            return false;
         } else {
            double dist = (double)mc.field_1724.method_5739(target);
            if (dist < (double)this.minSkipTickDistance.getValue()) {
               return false;
            } else if (dist < (double)this.minSkipDistanceTeleport.getValue()) {
               return false;
            } else if (!this.lagTimer.finished((double)this.delayBetweenLags.getValue())) {
               return false;
            } else {
               return this.attackTimer.finished((double)this.delayAfterAttack.getValue());
            }
         }
      } else {
         return false;
      }
   }

   @EventHandler
   public void onTick(TickEvent e) {
      if (this.isState()) {
         if (!PlayerInteractionHelper.nullCheck()) {
            class_1309 target = this.getTarget();
            if (this.instantTeleport.isValue()) {
               if (this.canStartSkip(target)) {
                  this.performInstantTeleport(target);
                  this.lagTimer.reset();
                  this.skippingTicks = false;
                  this.skippedTicks = 0;
               }

            } else if (this.skippingTicks) {
               ++this.skippedTicks;
               if (this.lagRangeMoveCorrection.isValue()) {
                  this.correctServerPosTowardsLocal();
               }

               if (this.skippedTicks >= (int)this.maxTeleportSkipTick.getValue()) {
                  this.performTeleportStep(target);
                  this.skippingTicks = false;
                  this.skippedTicks = 0;
                  this.lagTimer.reset();
               }

            } else if (this.canStartSkip(target)) {
               this.skippingTicks = true;
               this.skippedTicks = 0;
               this.serverPosDuringSkip = mc.field_1724.method_19538();
               this.lastLocalPos = this.serverPosDuringSkip;
            }
         }
      }
   }

   private void performTeleportStep(class_1309 target) {
      if (mc.field_1724 != null && mc.field_1687 != null) {
         if (this.serverPosDuringSkip == null) {
            this.serverPosDuringSkip = mc.field_1724.method_19538();
         }

         if (target != null) {
            class_243 targetPos = target.method_19538();
            class_243 from = this.serverPosDuringSkip;
            class_243 dir = targetPos.method_1020(from);
            double dist = dir.method_1033();
            if (!(dist < 0.001)) {
               if (!(dist < (double)this.minSkipDistanceTeleport.getValue())) {
                  dir = dir.method_1029();
                  double maxStep = (double)(this.speedSkipTick.getValue() * this.maxTeleportSkipTick.getValue());
                  double step = Math.min(dist, maxStep);
                  class_243 newPos = from.method_1019(dir.method_1021(step));
                  this.sendOneBigMovePacket(newPos);
                  this.serverPosDuringSkip = newPos;
               }
            }
         }
      }
   }

   private void performInstantTeleport(class_1309 target) {
      if (mc.field_1724 != null && mc.field_1687 != null) {
         if (target != null) {
            class_243 from = mc.field_1724.method_19538();
            class_243 targetPos = target.method_19538();
            class_243 dir = targetPos.method_1020(from);
            double dist = dir.method_1033();
            if (!(dist < 0.001)) {
               if (!(dist < (double)this.minSkipDistanceTeleport.getValue())) {
                  dir = dir.method_1029();
                  double maxStep = (double)this.maxTeleportDistance.getValue();
                  double step = Math.min(dist, maxStep);
                  class_243 newPos = from.method_1019(dir.method_1021(step));
                  this.sendOneBigMovePacket(newPos);
                  this.serverPosDuringSkip = newPos;
               }
            }
         }
      }
   }

   private void correctServerPosTowardsLocal() {
      if (mc.field_1724 != null) {
         if (this.serverPosDuringSkip == null) {
            this.serverPosDuringSkip = mc.field_1724.method_19538();
         }

         class_243 local = mc.field_1724.method_19538();
         this.serverPosDuringSkip = this.serverPosDuringSkip.method_1019(local.method_1020(this.serverPosDuringSkip).method_1021((double)0.25F));
      }
   }

   private void sendOneBigMovePacket(class_243 pos) {
      if (mc.field_1724 != null) {
         class_2596<?> packet = new class_2828.class_2829(pos.field_1352, pos.field_1351, pos.field_1350, mc.field_1724.method_24828(), false);
         PlayerInteractionHelper.sendPacketWithOutEvent(packet);
      }
   }

   @EventHandler
   public void onPacket(PacketEvent e) {
      if (this.isState()) {
         if (!PlayerInteractionHelper.nullCheck()) {
            class_2596<?> packet = e.getPacket();
            if (e.isSend()) {
               if (packet instanceof class_2799) {
                  class_2799 status = (class_2799)packet;
                  if (status.method_12119().equals(class_2800.field_12774)) {
                     this.setState(false);
                     return;
                  }
               }

               if (packet instanceof class_2824) {
                  class_2824 interact = (class_2824)packet;

                  try {
                     Object type = this.getInteractTypeCompat(interact);
                     if (type != null && String.valueOf(type).toUpperCase().contains("ATTACK")) {
                        this.attackTimer.reset();
                     }
                  } catch (Throwable var5) {
                  }
               }

               if (this.skippingTicks && e.isSend() && packet instanceof class_2828) {
                  this.lastLocalPos = mc.field_1724 != null ? mc.field_1724.method_19538() : this.lastLocalPos;
                  e.cancel();
               }

            } else {
               if (packet instanceof class_2724 || packet instanceof class_2678) {
                  this.setState(false);
               }

            }
         }
      }
   }

   private Object getInteractTypeCompat(class_2824 pkt) {
      try {
         return class_2824.class.getMethod("getType").invoke(pkt);
      } catch (Throwable var5) {
         try {
            return class_2824.class.getMethod("getInteractionType").invoke(pkt);
         } catch (Throwable var4) {
            try {
               return class_2824.class.getMethod("getAction").invoke(pkt);
            } catch (Throwable var3) {
               return null;
            }
         }
      }
   }
}

