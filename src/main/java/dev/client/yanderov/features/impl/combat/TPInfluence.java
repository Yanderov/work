package dev.client.yanderov.features.impl.combat;

import dev.client.yanderov.common.repository.friend.FriendUtils;
import dev.client.yanderov.events.player.TickEvent;
import dev.client.yanderov.events.render.WorldRenderEvent;
import dev.client.yanderov.features.impl.movement.BoatFly;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.features.module.setting.Setting;
import dev.client.yanderov.features.module.setting.implement.BindSetting;
import dev.client.yanderov.features.module.setting.implement.BooleanSetting;
import dev.client.yanderov.features.module.setting.implement.ColorSetting;
import dev.client.yanderov.features.module.setting.implement.SelectSetting;
import dev.client.yanderov.features.module.setting.implement.SliderSettings;
import dev.client.yanderov.utils.client.Instance;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import dev.client.yanderov.utils.display.color.ColorAssist;
import dev.client.yanderov.utils.display.geometry.Render3D;
import dev.client.yanderov.utils.display.interfaces.QuickImports;
import dev.client.yanderov.utils.interactions.interact.PlayerInteractionHelper;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1690;
import net.minecraft.class_2261;
import net.minecraft.class_2320;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_2680;
import net.minecraft.class_2828;

public class TPInfluence extends Module implements QuickImports {
   private final SelectSetting mainMode = (new SelectSetting("Mode", "Ð ÐµÐ¶Ð¸Ð¼ Ñ€Ð°Ð±Ð¾Ñ‚Ñ‹")).value("FunSky", "BoatFly").selected("FunSky");
   private final SliderSettings boatAttackDistance = (new SliderSettings("BoatAttackDistance", "Ð”Ð¸ÑÑ‚Ð°Ð½Ñ†Ð¸Ñ Ð°Ñ‚Ð°ÐºÐ¸ (BoatFly)")).setValue(3.0F).range(0.0F, 10.0F).visible(() -> this.mainMode.isSelected("BoatFly"));
   private final SliderSettings boatTeleportRange = (new SliderSettings("BoatTeleportRange", "Ð Ð°Ð´Ð¸ÑƒÑ Ð¿Ð¾Ð¸ÑÐºÐ° Ñ†ÐµÐ»Ð¸ (BoatFly)")).setValue(25.0F).range(0.0F, 100.0F).visible(() -> this.mainMode.isSelected("BoatFly"));
   private final BooleanSetting boatTeleportMethod = (new BooleanSetting("BoatTeleportMethod", "ÐœÐµÑ‚Ð¾Ð´ Ñ‚ÐµÐ»ÐµÐ¿Ð¾Ñ€Ñ‚Ð° (BoatFly)")).setValue(false).visible(() -> this.mainMode.isSelected("BoatFly"));
   private final SliderSettings boatStepSpeed = (new SliderSettings("BoatStepSpeed", "Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ ÑÐ¼ÐµÑ‰ÐµÐ½Ð¸Ñ (BoatFly)")).setValue(0.6F).range(0.05F, 5.0F).visible(() -> this.mainMode.isSelected("BoatFly") && !this.boatTeleportMethod.isValue());
   private final SliderSettings attackDistance = (new SliderSettings("AttackDistance", "Ð Ð°Ð´Ð¸ÑƒÑ Ð´Ð¾ Ñ†ÐµÐ»Ð¸ Ð¿Ð¾ÑÐ»Ðµ Ñ‚Ð¿")).setValue(3.0F).range(0.0F, 10.0F).visible(() -> this.mainMode.isSelected("FunSky"));
   private final SliderSettings teleportRange = (new SliderSettings("TeleportRange", "Ð Ð°Ð´Ð¸ÑƒÑ Ð¿Ð¾Ð¸ÑÐºÐ° Ñ†ÐµÐ»Ð¸ Ð´Ð»Ñ Ñ‚Ð¿")).setValue(25.0F).range(0.0F, 100.0F).visible(() -> this.mainMode.isSelected("FunSky"));
   private final SliderSettings teleportDelay = (new SliderSettings("TeleportDelay", "Ð—Ð°Ð´ÐµÑ€Ð¶ÐºÐ° Ð¼ÐµÐ¶Ð´Ñƒ Ñ‚Ð¿ Ñ†Ð¸ÐºÐ»Ð°Ð¼Ð¸ (Ñ‚Ð¸Ðº)")).setValue(0.0F).range(0.0F, 40.0F).visible(() -> this.mainMode.isSelected("FunSky"));
   private final SliderSettings stayDelay = (new SliderSettings("StayDelay", "Ð¢Ð¸ÐºÐ¾Ð² Ñƒ Ñ†ÐµÐ»Ð¸ Ð´Ð¾ Ð²Ð¾Ð·Ð²Ñ€Ð°Ñ‚Ð°")).setValue(1.0F).range(0.0F, 40.0F).visible(() -> this.mainMode.isSelected("FunSky"));
   private final SliderSettings maxHitWaitTicks = (new SliderSettings("MaxHitWait", "ÐœÐ°ÐºÑ. Ð¾Ð¶Ð¸Ð´Ð°Ð½Ð¸Ðµ ÑƒÐ´Ð°Ñ€Ð° (Ñ‚Ð¸Ðº)")).setValue(10.0F).range(0.0F, 80.0F).visible(() -> this.mainMode.isSelected("FunSky"));
   private final BooleanSetting cdFixTeleport = (new BooleanSetting("CDfixTeleport", "Ð¡Ð¸Ð½Ñ…Ñ€Ð¾Ð½Ð¸Ð·Ð°Ñ†Ð¸Ñ Ñ‚Ð¿ Ñ ÐºÐ´ ÑƒÐ´Ð°Ñ€Ð°")).setValue(false).visible(() -> this.mainMode.isSelected("FunSky"));
   private final SliderSettings postHitDelay = (new SliderSettings("PostHitDelay", "Ð¢Ð¸ÐºÐ¾Ð² Ñƒ Ñ†ÐµÐ»Ð¸ Ð¿Ð¾ÑÐ»Ðµ ÑƒÐ´Ð°Ñ€Ð° (CDFix)")).setValue(2.0F).range(0.0F, 40.0F).visible(() -> this.mainMode.isSelected("FunSky") && this.cdFixTeleport.isValue());
   private final BooleanSetting teleportFix = (new BooleanSetting("TeleportFix", "Ð¢Ð¿ Ð¿Ñ€ÑÐ¼Ð¾ Ð¿Ð¾Ð´ Ñ‚Ð°Ñ€Ð³ÐµÑ‚Ð° (Y-2)")).setValue(false).visible(() -> this.mainMode.isSelected("FunSky"));
   private final BooleanSetting improvedCrits = (new BooleanSetting("ImprovedCrits", "Ð£Ð»ÑƒÑ‡ÑˆÐ¸Ñ‚ÑŒ ÐºÑ€Ð¸Ñ‚Ñ‹ (Ñ‡ÑƒÑ‚ÑŒ Ð²Ñ‹ÑˆÐµ Ð² Ð±Ð»Ð¾ÐºÐµ)")).setValue(false).visible(() -> this.mainMode.isSelected("FunSky"));
   private final BooleanSetting hitStopFix = (new BooleanSetting("HitStopFix", "Ð’Ð¾Ð·Ð²Ñ€Ð°Ñ‚ Ñ‚Ð¾Ð»ÑŒÐºÐ¾ Ð¿Ð¾ÑÐ»Ðµ ÑƒÑ€Ð¾Ð½Ð°")).setValue(false).visible(() -> this.mainMode.isSelected("FunSky"));
   private final BooleanSetting stopOnMove = (new BooleanSetting("StopOnMove", "ÐÐµ Ñ‚Ð¿ Ð¿Ñ€Ð¸ Ð´Ð²Ð¸Ð¶ÐµÐ½Ð¸Ð¸")).setValue(false).visible(() -> this.mainMode.isSelected("FunSky"));
   private final SliderSettings distanceThreshold = (new SliderSettings("DistanceThreshold", "ÐŸÐ¾Ñ€Ð¾Ð³ Ñ€Ð°ÑÑÑ‚Ð¾ÑÐ½Ð¸Ñ Ð´Ð»Ñ Ñ‚Ð¿")).setValue(0.0F).range(0, 20).visible(() -> this.mainMode.isSelected("FunSky"));
   private final SelectSetting tpMode = (new SelectSetting("TPMode", "Ð ÐµÐ¶Ð¸Ð¼ Ñ‚Ð¿ Ð°ÑƒÑ€Ñ‹")).value("Default", "Bind").selected("Default").visible(() -> this.mainMode.isSelected("FunSky"));
   private final SelectSetting backTeleportMode = (new SelectSetting("BackTeleportMode", "ÐœÐµÑ‚Ð¾Ð´ Ð¾Ð±Ñ€Ð°Ñ‚Ð½Ð¾Ð³Ð¾ Ñ‚Ð¿Ð°")).value("Default", "ServerLag").selected("Default").visible(() -> this.mainMode.isSelected("FunSky"));
   private final BindSetting tpBackBind = (new BindSetting("TPBackBind", "ÐšÐ»Ð°Ð²Ð¸ÑˆÐ° Ð²Ð¾Ð·Ð²Ñ€Ð°Ñ‚Ð° Ñ‚Ð¿")).setKey(-1).visible(() -> this.mainMode.isSelected("FunSky") && this.tpMode.isSelected("Bind"));
   private final BooleanSetting autoDisable = (new BooleanSetting("AutoDisable", "Ð’Ñ‹ÐºÐ»ÑŽÑ‡Ð°Ñ‚ÑŒ Ð±ÐµÐ· Ñ†ÐµÐ»Ð¸")).setValue(false).visible(() -> this.mainMode.isSelected("FunSky"));
   private final BooleanSetting lockTarget = (new BooleanSetting("LockTarget", "ÐÐµ Ð¿ÐµÑ€ÐµÐºÐ»ÑŽÑ‡Ð°Ñ‚ÑŒ Ñ†ÐµÐ»ÑŒ, Ð¿Ð¾ÐºÐ° Ð¶Ð¸Ð²Ð°")).setValue(false).visible(() -> this.mainMode.isSelected("FunSky"));
   private final BooleanSetting silentRotations = (new BooleanSetting("SilentRotations", "Ð¡ÐºÑ€Ñ‹Ñ‚Ð½Ñ‹Ðµ Ð¿Ð¾Ð²Ð¾Ñ€Ð¾Ñ‚Ñ‹ ÐºÐ°Ð¼ÐµÑ€Ñ‹")).setValue(true).visible(() -> this.mainMode.isSelected("FunSky"));
   private final BooleanSetting clientEsp = (new BooleanSetting("ClientESP", "Ð¢Ð¾Ð»ÑŒÐºÐ¾ ESP Ñ‚ÐµÐ»ÐµÐ¿Ð¾Ñ€Ñ‚Ð¾Ð²")).setValue(false).visible(() -> this.mainMode.isSelected("FunSky"));
   private final ColorSetting clientEspColor = (new ColorSetting("ClientESPColor", "Ð¦Ð²ÐµÑ‚ ESP Ñ‚Ð¿")).value(-1).visible(() -> this.clientEsp.isValue() && this.mainMode.isSelected("FunSky"));
   private final BooleanSetting targetLine = (new BooleanSetting("TargetLine", "Ð›Ð¸Ð½Ð¸Ñ Ðº Ñ‚Ð°Ñ€Ð³ÐµÑ‚Ñƒ")).setValue(false).visible(() -> this.mainMode.isSelected("FunSky"));
   private final BooleanSetting areaTarget = (new BooleanSetting("AreaTarget", "Ð Ð°Ð´Ð¸ÑƒÑÐ½Ð¾Ðµ ÑÐ¾Ð¿Ñ€Ð¾Ð²Ð¾Ð¶Ð´ÐµÐ½Ð¸Ðµ Ñ†ÐµÐ»Ð¸")).setValue(false).visible(() -> this.mainMode.isSelected("FunSky"));
   private final SliderSettings areaRadius = (new SliderSettings("AreaRadius", "Ð Ð°Ð´Ð¸ÑƒÑ Ð²Ð¾ÐºÑ€ÑƒÐ³ Ñ†ÐµÐ»Ð¸")).setValue(4.0F).range(0.5F, 20.0F).visible(() -> this.mainMode.isSelected("FunSky") && this.areaTarget.isValue());
   private final SelectSetting areaMoveMode = (new SelectSetting("AreaMoveMode", "ÐœÐµÐ½ÑÑ‚ÑŒ Ð¿Ð¾Ð»Ð¾Ð¶ÐµÐ½Ð¸Ðµ")).value("Ð¡ ÑƒÐ´Ð°Ñ€Ð¾Ð¼", "Ð Ð°Ð½Ð´Ð¾Ð¼").selected("Ð¡ ÑƒÐ´Ð°Ñ€Ð¾Ð¼").visible(() -> this.mainMode.isSelected("FunSky") && this.areaTarget.isValue());
   private final SliderSettings randomStepDistance = (new SliderSettings("RandomStep", "ÐœÐ¸Ð½. Ð´Ð¸ÑÑ‚Ð°Ð½Ñ†Ð¸Ñ Ð¼ÐµÐ¶Ð´Ñƒ Ñ‚Ð¿")).setValue(6.0F).range(1.0F, 30.0F).visible(() -> this.mainMode.isSelected("FunSky") && this.areaTarget.isValue() && this.areaMoveMode.isSelected("Ð Ð°Ð½Ð´Ð¾Ð¼"));
   private final SliderSettings randomTeleportDelay = (new SliderSettings("RandomDelay", "Ð§Ð°ÑÑ‚Ð¾Ñ‚Ð° Ñ‚Ð¿ Ð¿Ð¾ Ñ€Ð°Ð´Ð¸ÑƒÑÑƒ (Ñ‚Ð¸Ðº)")).setValue(10.0F).range(1.0F, 100.0F).visible(() -> this.mainMode.isSelected("FunSky") && this.areaTarget.isValue() && this.areaMoveMode.isSelected("Ð Ð°Ð½Ð´Ð¾Ð¼"));
   private class_1657 target;
   private class_243 returnPos;
   private boolean inTeleportCycle;
   private int stayTicks;
   private boolean waitingForHit;
   private int cycleCooldownTicks;
   private int hitWaitTicks;
   private float initialTargetHealth;
   private boolean hitConfirmed;
   private int postHitCooldownTicks;
   private boolean returningHome;
   private boolean serverLagBackPending;
   private boolean lastBackPressed;
   private class_243 lastTpPos;
   private class_243 randomCenterPos;
   private class_243 lastRandomPos;
   private int randomTeleportTicks;
   private boolean boatInCycle;
   private class_243 boatReturnPos;
   private int boatStayTicks;

   public TPInfluence() {
      super("TPInfluence", ModuleCategory.COMBAT);
      this.returnPos = class_243.field_1353;
      this.inTeleportCycle = false;
      this.stayTicks = 0;
      this.waitingForHit = false;
      this.cycleCooldownTicks = 0;
      this.hitWaitTicks = 0;
      this.initialTargetHealth = 0.0F;
      this.hitConfirmed = false;
      this.postHitCooldownTicks = 0;
      this.returningHome = false;
      this.serverLagBackPending = false;
      this.lastBackPressed = false;
      this.lastTpPos = null;
      this.randomCenterPos = null;
      this.lastRandomPos = null;
      this.randomTeleportTicks = 0;
      this.boatInCycle = false;
      this.boatReturnPos = class_243.field_1353;
      this.boatStayTicks = 0;
      this.setup(new Setting[]{this.mainMode, this.boatAttackDistance, this.boatTeleportRange, this.boatTeleportMethod, this.boatStepSpeed, this.attackDistance, this.teleportRange, this.teleportDelay, this.stayDelay, this.maxHitWaitTicks, this.postHitDelay, this.cdFixTeleport, this.teleportFix, this.improvedCrits, this.hitStopFix, this.stopOnMove, this.distanceThreshold, this.tpMode, this.backTeleportMode, this.tpBackBind, this.autoDisable, this.lockTarget, this.silentRotations, this.clientEsp, this.clientEspColor, this.targetLine, this.areaTarget, this.areaRadius, this.areaMoveMode, this.randomStepDistance, this.randomTeleportDelay});
   }

   public static TPInfluence getInstance() {
      return (TPInfluence)Instance.get(TPInfluence.class);
   }

   public class_1657 getTarget() {
      return this.target;
   }

   public boolean isReturningHome() {
      return this.returningHome;
   }

   public boolean isInTeleportCycle() {
      return this.inTeleportCycle;
   }

   public boolean isSilentRotations() {
      return this.silentRotations.isValue();
   }

   public boolean isClientEsp() {
      return this.clientEsp.isValue();
   }

   public class_243 getLastTpPos() {
      return this.lastTpPos;
   }

   @EventHandler
   public void onTick(TickEvent e) {
      if (this.isState() && mc.field_1724 != null && mc.field_1687 != null && mc.method_1562() != null) {
         if (this.mainMode.isSelected("BoatFly")) {
            this.handleBoatFlyMode();
         } else {
            Aura aura = Aura.getInstance();
            if (aura != null && aura.isState()) {
               this.updateTarget(aura);
               if (this.target != null && this.target.method_5805() && !this.target.method_31481()) {
                  double maxDistSq = (double)(this.teleportRange.getValue() * this.teleportRange.getValue());
                  if (mc.field_1724.method_5858(this.target) > maxDistSq) {
                     if (this.autoDisable.isValue()) {
                        this.setState(false);
                     }

                  } else {
                     boolean bindMode = this.tpMode.isSelected("Bind");
                     if (this.randomCenterPos == null) {
                        this.randomCenterPos = mc.field_1724.method_19538();
                        this.lastRandomPos = this.randomCenterPos;
                     }

                     boolean backPressed = this.isBindPressed(this.tpBackBind);
                     if (bindMode && this.inTeleportCycle && !this.returningHome && backPressed && !this.lastBackPressed) {
                        this.returningHome = true;
                        if (this.backTeleportMode.isSelected("ServerLag")) {
                           this.serverLagBackPending = true;
                        }
                     }

                     this.lastBackPressed = backPressed;
                     if (this.returningHome) {
                        if (this.backTeleportMode.isSelected("ServerLag")) {
                           if (this.serverLagBackPending) {
                              if (this.clientEsp.isValue()) {
                                 this.teleportClickStylePacketsOnly(this.returnPos.field_1352, this.returnPos.field_1351, this.returnPos.field_1350);
                              } else {
                                 this.teleportClickStylePacketsOnly(this.returnPos.field_1352, this.returnPos.field_1351, this.returnPos.field_1350);
                              }

                              this.serverLagBackPending = false;
                           }

                           this.returningHome = false;
                           this.inTeleportCycle = false;
                           this.waitingForHit = false;
                           this.hitWaitTicks = 0;
                           this.hitConfirmed = false;
                           if (!bindMode) {
                              this.cycleCooldownTicks = (int)this.teleportDelay.getValue();
                           } else {
                              this.cycleCooldownTicks = 0;
                           }

                        } else {
                           class_243 desiredPos = this.returnPos;
                           double distSq = mc.field_1724.method_19538().method_1025(desiredPos);
                           if (distSq < 0.01) {
                              this.returningHome = false;
                              this.inTeleportCycle = false;
                              this.waitingForHit = false;
                              this.hitWaitTicks = 0;
                              this.hitConfirmed = false;
                              if (!bindMode) {
                                 this.cycleCooldownTicks = (int)this.teleportDelay.getValue();
                              } else {
                                 this.cycleCooldownTicks = 0;
                              }
                           } else if (this.clientEsp.isValue()) {
                              this.teleportClickStylePacketsOnly(this.returnPos.field_1352, this.returnPos.field_1351, this.returnPos.field_1350);
                           } else {
                              this.teleportClickStyle(this.returnPos.field_1352, this.returnPos.field_1351, this.returnPos.field_1350);
                           }

                        }
                     } else {
                        if (!this.inTeleportCycle) {
                           if (!bindMode && this.cycleCooldownTicks > 0) {
                              --this.cycleCooldownTicks;
                              this.handleRandomAreaTeleportIdle();
                              return;
                           }

                           if (this.stopOnMove.isValue() && mc.field_1724.field_3913 != null && (mc.field_1724.field_3913.field_3905 != 0.0F || mc.field_1724.field_3913.field_3907 != 0.0F)) {
                              this.handleRandomAreaTeleportIdle();
                              return;
                           }

                           if (this.cdFixTeleport.isValue()) {
                              float cooldownProgress = mc.field_1724.method_7261(0.0F);
                              if (cooldownProgress < 0.8F) {
                                 this.handleRandomAreaTeleportIdle();
                                 return;
                              }
                           }

                           this.returnPos = mc.field_1724.method_19538();
                           double currentDist = (double)mc.field_1724.method_5739(this.target);
                           if (this.distanceThreshold.getValue() > 0.0F && currentDist <= (double)this.distanceThreshold.getValue()) {
                              this.handleRandomAreaTeleportIdle();
                              return;
                           }

                           class_243 tpPos = this.computeAroundTargetPos(this.target, this.attackDistance.getValue());
                           this.lastTpPos = tpPos;
                           if (this.clientEsp.isValue()) {
                              this.teleportClickStylePacketsOnly(tpPos.field_1352, tpPos.field_1351, tpPos.field_1350);
                           } else {
                              this.teleportClickStyle(tpPos.field_1352, tpPos.field_1351, tpPos.field_1350);
                           }

                           this.inTeleportCycle = true;
                           if (bindMode) {
                              this.waitingForHit = false;
                              this.stayTicks = 0;
                              this.hitWaitTicks = 0;
                              this.hitConfirmed = false;
                           } else if (this.cdFixTeleport.isValue()) {
                              this.waitingForHit = true;
                              this.stayTicks = 0;
                              this.hitWaitTicks = 0;
                              if (this.target != null) {
                                 this.initialTargetHealth = this.target.method_6032();
                                 this.hitConfirmed = false;
                              }
                           } else {
                              this.waitingForHit = false;
                              this.stayTicks = (int)this.stayDelay.getValue();
                           }
                        } else {
                           if (!bindMode && this.cdFixTeleport.isValue() && this.waitingForHit && !this.areaTarget.isValue()) {
                              if (this.hitStopFix.isValue()) {
                                 if (this.target != null && this.target.method_5805()) {
                                    float currentHealth = this.target.method_6032();
                                    if (!this.hitConfirmed && currentHealth < this.initialTargetHealth - 0.001F) {
                                       this.hitConfirmed = true;
                                       this.postHitCooldownTicks = (int)this.postHitDelay.getValue();
                                    }
                                 }

                                 if (this.hitConfirmed) {
                                    if (this.postHitCooldownTicks > 0) {
                                       --this.postHitCooldownTicks;
                                       return;
                                    }
                                 } else if (this.target != null && this.target.method_5805()) {
                                    return;
                                 }
                              } else {
                                 float cooldownProgressAfterHit = mc.field_1724.method_7261(0.0F);
                                 if (cooldownProgressAfterHit > 0.1F) {
                                    int maxWait = (int)this.maxHitWaitTicks.getValue();
                                    if (this.hitWaitTicks < maxWait) {
                                       ++this.hitWaitTicks;
                                       return;
                                    }
                                 }
                              }
                           } else if (this.stayTicks > 0) {
                              --this.stayTicks;
                              return;
                           }

                           if (this.areaTarget.isValue() && this.areaMoveMode.isSelected("Ð¡ ÑƒÐ´Ð°Ñ€Ð¾Ð¼")) {
                              class_243 newPos = this.randomPosAroundTarget(this.target, this.areaRadius.getValue(), this.returnPos.field_1351);
                              if (newPos != null) {
                                 if (this.clientEsp.isValue()) {
                                    this.teleportClickStylePacketsOnly(newPos.field_1352, newPos.field_1351, newPos.field_1350);
                                 } else {
                                    this.teleportClickStyle(newPos.field_1352, newPos.field_1351, newPos.field_1350);
                                 }

                                 this.lastRandomPos = newPos;
                              }

                              this.inTeleportCycle = false;
                              this.waitingForHit = false;
                              this.hitWaitTicks = 0;
                              this.hitConfirmed = false;
                              if (!bindMode) {
                                 this.cycleCooldownTicks = (int)this.teleportDelay.getValue();
                              } else {
                                 this.cycleCooldownTicks = 0;
                              }
                           } else if (!bindMode) {
                              this.returningHome = true;
                              if (this.backTeleportMode.isSelected("ServerLag")) {
                                 this.serverLagBackPending = true;
                              }
                           } else {
                              this.returningHome = true;
                           }
                        }

                     }
                  }
               } else {
                  if (this.autoDisable.isValue()) {
                     this.setState(false);
                  }

               }
            }
         }
      }
   }

   private void handleBoatFlyMode() {
      Aura aura = Aura.getInstance();
      if (aura != null && aura.isState()) {
         BoatFly boatFlyMovement = (BoatFly)Instance.get(BoatFly.class);
         if (boatFlyMovement != null && boatFlyMovement.isState()) {
            class_1297 var4 = mc.field_1724.method_5854();
            if (var4 instanceof class_1690) {
               class_1690 boat = (class_1690)var4;
               class_1309 auraTarget = aura.getCurrentTarget();
               class_1657 var10000;
               if (auraTarget instanceof class_1657) {
                  class_1657 p = (class_1657)auraTarget;
                  var10000 = p;
               } else {
                  var10000 = null;
               }

               class_1657 pe = var10000;
               if (pe != null && pe.method_5805() && !pe.method_31481()) {
                  double maxDistSq = (double)(this.boatTeleportRange.getValue() * this.boatTeleportRange.getValue());
                  if (mc.field_1724.method_5858(pe) > maxDistSq) {
                     this.boatInCycle = false;
                  } else {
                     if (!this.boatInCycle) {
                        this.boatInCycle = true;
                        this.boatStayTicks = 0;
                        this.boatReturnPos = boat.method_19538();
                     }

                     double attackDist = (double)this.boatAttackDistance.getValue();
                     class_243 targetPos = pe.method_19538();
                     class_243 boatPos = boat.method_19538();
                     class_243 dir = boatPos.method_1020(targetPos);
                     dir = new class_243(dir.field_1352, (double)0.0F, dir.field_1350);
                     if (dir.method_1027() < 1.0E-4) {
                        dir = new class_243((double)1.0F, (double)0.0F, (double)0.0F);
                     }

                     dir = dir.method_1029().method_1021(attackDist);
                     class_243 desiredPos = new class_243(targetPos.field_1352 + dir.field_1352, boatPos.field_1351, targetPos.field_1350 + dir.field_1350);
                     if (!this.boatTeleportMethod.isValue()) {
                        double stepSpeed = (double)this.boatStepSpeed.getValue();
                        class_243 delta = desiredPos.method_1020(boatPos);
                        double dist = delta.method_1033();
                        if (dist > stepSpeed) {
                           delta = delta.method_1029().method_1021(stepSpeed);
                        }

                        if (dist > 0.1) {
                           boat.method_5814(boatPos.field_1352 + delta.field_1352, boatPos.field_1351, boatPos.field_1350 + delta.field_1350);
                           mc.field_1724.method_5814(boat.method_23317(), boat.method_23318(), boat.method_23321());
                        } else {
                           ++this.boatStayTicks;
                           if ((float)this.boatStayTicks > this.stayDelay.getValue()) {
                              class_243 backDelta = this.boatReturnPos.method_1020(boat.method_19538());
                              double backDist = backDelta.method_1033();
                              if (backDist > stepSpeed) {
                                 backDelta = backDelta.method_1029().method_1021(stepSpeed);
                                 boat.method_5814(boat.method_23317() + backDelta.field_1352, boat.method_23318(), boat.method_23321() + backDelta.field_1350);
                                 mc.field_1724.method_5814(boat.method_23317(), boat.method_23318(), boat.method_23321());
                              } else {
                                 boat.method_5814(this.boatReturnPos.field_1352, this.boatReturnPos.field_1351, this.boatReturnPos.field_1350);
                                 mc.field_1724.method_5814(this.boatReturnPos.field_1352, this.boatReturnPos.field_1351, this.boatReturnPos.field_1350);
                                 this.boatInCycle = false;
                                 this.boatStayTicks = 0;
                              }
                           }
                        }
                     } else if (this.boatInCycle && this.boatStayTicks == 0) {
                        mc.method_1562().method_52787(new class_2828.class_2829(desiredPos.field_1352, desiredPos.field_1351, desiredPos.field_1350, mc.field_1724.method_24828(), false));
                        boat.method_5814(desiredPos.field_1352, desiredPos.field_1351, desiredPos.field_1350);
                        mc.field_1724.method_5814(desiredPos.field_1352, desiredPos.field_1351, desiredPos.field_1350);
                        this.boatStayTicks = 1;
                     } else if (this.boatInCycle && this.boatStayTicks > 0) {
                        ++this.boatStayTicks;
                        if ((float)this.boatStayTicks > this.stayDelay.getValue()) {
                           mc.method_1562().method_52787(new class_2828.class_2829(this.boatReturnPos.field_1352, this.boatReturnPos.field_1351, this.boatReturnPos.field_1350, mc.field_1724.method_24828(), false));
                           boat.method_5814(this.boatReturnPos.field_1352, this.boatReturnPos.field_1351, this.boatReturnPos.field_1350);
                           mc.field_1724.method_5814(this.boatReturnPos.field_1352, this.boatReturnPos.field_1351, this.boatReturnPos.field_1350);
                           this.boatInCycle = false;
                           this.boatStayTicks = 0;
                        }
                     }

                  }
               } else {
                  this.boatInCycle = false;
               }
            } else {
               this.boatInCycle = false;
            }
         }
      }
   }

   private void handleRandomAreaTeleportIdle() {
      if (this.areaTarget.isValue()) {
         if (this.areaMoveMode.isSelected("Ð Ð°Ð½Ð´Ð¾Ð¼")) {
            if (mc.field_1724 != null && mc.field_1687 != null) {
               if (this.randomTeleportTicks > 0) {
                  --this.randomTeleportTicks;
               } else {
                  float cooldownProgress = mc.field_1724.method_7261(0.0F);
                  if (!(cooldownProgress >= 0.8F) || this.cdFixTeleport.isValue()) {
                     class_243 baseCenter = this.randomCenterPos != null ? this.randomCenterPos : mc.field_1724.method_19538();
                     class_243 next = this.randomPosInRadius(baseCenter, this.areaRadius.getValue(), this.randomStepDistance.getValue(), baseCenter.field_1351);
                     if (next != null) {
                        if (this.clientEsp.isValue()) {
                           this.teleportClickStylePacketsOnly(next.field_1352, next.field_1351, next.field_1350);
                        } else {
                           this.teleportClickStyle(next.field_1352, next.field_1351, next.field_1350);
                        }

                        this.lastRandomPos = next;
                     }

                     this.randomTeleportTicks = (int)this.randomTeleportDelay.getValue();
                  }
               }
            }
         }
      }
   }

   private void updateTarget(Aura aura) {
      double maxDistSqLock = (double)(this.teleportRange.getValue() * this.teleportRange.getValue());
      if (this.target == null || this.target.method_31481() || !this.target.method_5805() || !(mc.field_1724.method_5858(this.target) <= maxDistSqLock) || !this.lockTarget.isValue()) {
         this.target = null;
         class_1309 auraTarget = aura.getCurrentTarget();
         if (auraTarget instanceof class_1657) {
            class_1657 player = (class_1657)auraTarget;
            if (!player.method_31481() && player.method_5805()) {
               double maxDistSq = (double)(this.teleportRange.getValue() * this.teleportRange.getValue());
               if (mc.field_1724.method_5858(player) <= maxDistSq) {
                  this.target = player;
                  return;
               }
            }
         }

         double maxDistSq = (double)(this.teleportRange.getValue() * this.teleportRange.getValue());
         class_1657 nearest = null;
         double bestDistSq = Double.MAX_VALUE;

         for(class_1297 e : mc.field_1687.method_18112()) {
            if (e instanceof class_1657) {
               class_1657 p = (class_1657)e;
               if (p != mc.field_1724 && !p.method_31481() && p.method_5805() && !FriendUtils.isFriend((class_1297)p)) {
                  double distSq = mc.field_1724.method_5858(p);
                  if (!(distSq > maxDistSq) && distSq < bestDistSq) {
                     bestDistSq = distSq;
                     nearest = p;
                  }
               }
            }
         }

         this.target = nearest;
      }
   }

   private class_243 computeAroundTargetPos(class_1297 ent, float radius) {
      class_243 targetPos = ent.method_19538();
      if (this.teleportFix.isValue()) {
         double yOffset = this.improvedCrits.isValue() ? (double)-2.0F : (double)-1.0F;
         return new class_243(targetPos.field_1352, targetPos.field_1351 + yOffset, targetPos.field_1350);
      } else {
         class_2338 targetBlockPos = ent.method_24515();
         class_243 bestPos = null;
         double bestDistSq = Double.MAX_VALUE;
         int searchRadius = (int)Math.ceil((double)radius) + 1;

         for(int y = -1; y <= 1; ++y) {
            for(int x = -searchRadius; x <= searchRadius; ++x) {
               for(int z = -searchRadius; z <= searchRadius; ++z) {
                  class_2338 currentPos = targetBlockPos.method_10069(x, y, z);
                  class_2680 state = mc.field_1687.method_8320(currentPos);
                  if (state.method_51367()) {
                     class_2338 abovePos = currentPos.method_10084();
                     class_2680 aboveState = mc.field_1687.method_8320(abovePos);
                     if (!this.isPlantBlock(state) && !this.isPlantBlock(aboveState)) {
                        class_243 blockCenter = new class_243((double)currentPos.method_10263() + (double)0.5F, (double)currentPos.method_10264(), (double)currentPos.method_10260() + (double)0.5F);
                        double distSqToTarget = blockCenter.method_1025(targetPos);
                        if (distSqToTarget <= (double)(radius * radius)) {
                           double distSqToPlayer = blockCenter.method_1025(mc.field_1724.method_19538());
                           if (bestPos == null || distSqToPlayer < bestDistSq) {
                              bestPos = blockCenter;
                              bestDistSq = distSqToPlayer;
                           }
                        }
                     }
                  }
               }
            }
         }

         if (bestPos != null) {
            return bestPos;
         } else {
            boolean targetInWater;
            try {
               targetInWater = ent.method_5799();
            } catch (Throwable var21) {
               class_2680 stateBelow = mc.field_1687.method_8320(ent.method_24515());
               targetInWater = stateBelow.method_26227() != null && !stateBelow.method_26227().method_15769();
            }

            if (targetInWater) {
               return new class_243(targetPos.field_1352, targetPos.field_1351, targetPos.field_1350);
            } else {
               class_243 selfPos = mc.field_1724.method_19538();
               class_243 dir = (new class_243(selfPos.field_1352 - targetPos.field_1352, (double)0.0F, selfPos.field_1350 - targetPos.field_1350)).method_1029();
               return targetPos.method_1019(dir.method_1021((double)radius));
            }
         }
      }
   }

   private boolean isPlantBlock(class_2680 state) {
      if (state == null) {
         return false;
      } else {
         return state.method_26204() instanceof class_2261 || state.method_26204() instanceof class_2320;
      }
   }

   private boolean canTeleportNow() {
      return mc.field_1724 != null;
   }

   private void teleportClickStyle(double x, double y, double z) {
      if (this.canTeleportNow()) {
         class_243 from = mc.field_1724.method_19538();
         double distance = from.method_1022(new class_243(x, y, z));
         int packetCount = (int)Math.min(Math.abs(distance / (double)11.0F) + (double)1.0F, (double)19.0F);

         for(int i = 0; i < packetCount; ++i) {
            mc.method_1562().method_52787(new class_2828.class_5911(false, false));
         }

         mc.method_1562().method_52787(new class_2828.class_2829(x, y, z, false, false));
         mc.field_1724.method_5814(x, y, z);
      }
   }

   private void teleportClickStylePacketsOnly(double x, double y, double z) {
      if (this.canTeleportNow()) {
         class_243 from = mc.field_1724.method_19538();
         double distance = from.method_1022(new class_243(x, y, z));
         int packetCount = (int)Math.min(Math.abs(distance / (double)11.0F) + (double)1.0F, (double)19.0F);

         for(int i = 0; i < packetCount; ++i) {
            mc.method_1562().method_52787(new class_2828.class_5911(false, false));
         }

         mc.method_1562().method_52787(new class_2828.class_2829(x, y, z, false, false));
      }
   }

   @EventHandler
   public void onWorldRender(WorldRenderEvent e) {
      if (this.isState() && mc.field_1724 != null && mc.field_1687 != null) {
         class_1657 renderTarget = this.target;
         if ((renderTarget == null || renderTarget.method_31481() || !renderTarget.method_5805()) && Aura.getInstance() != null) {
            class_1309 var4 = Aura.getInstance().getCurrentTarget();
            if (var4 instanceof class_1657) {
               class_1657 auraTarget = (class_1657)var4;
               renderTarget = auraTarget;
            }
         }

         if (renderTarget != null && renderTarget.method_5805() && !renderTarget.method_31481()) {
            if (this.clientEsp.isValue() && this.lastTpPos != null) {
               class_238 box = new class_238(this.lastTpPos.field_1352 - 0.3, this.lastTpPos.field_1351, this.lastTpPos.field_1350 - 0.3, this.lastTpPos.field_1352 + 0.3, this.lastTpPos.field_1351 + 1.8, this.lastTpPos.field_1350 + 0.3);
               Render3D.drawBox(box, this.clientEspColor.getColor(), 1.0F);
            } else {
               class_238 box = renderTarget.method_5829();
               Render3D.drawBox(box, ColorAssist.getClientColor(), 1.0F);
            }

            if (this.targetLine.isValue()) {
               class_243 start = mc.field_1724.method_5836(e.getPartialTicks());
               class_243 tpEnd = this.lastTpPos;
               if (tpEnd == null) {
                  tpEnd = this.computeAroundTargetPos(renderTarget, this.attackDistance.getValue());
               }

               class_243 end = tpEnd.method_1031((double)0.0F, (double)1.0F, (double)0.0F);
               Render3D.drawLine(start, end, -1, 1.0F, true);
            }

         }
      }
   }

   public void deactivate() {
      super.deactivate();
      this.target = null;
      this.inTeleportCycle = false;
      this.stayTicks = 0;
      this.returnPos = class_243.field_1353;
      this.waitingForHit = false;
      this.hitWaitTicks = 0;
      this.cycleCooldownTicks = 0;
      this.hitConfirmed = false;
      this.returningHome = false;
      this.serverLagBackPending = false;
      this.lastTpPos = null;
      this.randomCenterPos = null;
      this.lastRandomPos = null;
      this.randomTeleportTicks = 0;
      this.boatInCycle = false;
      this.boatReturnPos = class_243.field_1353;
      this.boatStayTicks = 0;
   }

   private boolean isBindPressed(BindSetting bind) {
      int code = bind.getKey();
      return code != -1 && PlayerInteractionHelper.isKey(PlayerInteractionHelper.getKeyType(code), code);
   }

   public String getSuffix() {
      return this.target != null ? this.target.method_5477().getString() : null;
   }

   private class_243 randomPosAroundTarget(class_1297 ent, float radius, double yLevel) {
      if (ent == null) {
         return null;
      } else {
         class_243 center = ent.method_19538();
         double angle = Math.random() * Math.PI * (double)2.0F;
         double r = (double)radius * (0.4 + Math.random() * 0.6);
         double x = center.field_1352 + Math.cos(angle) * r;
         double z = center.field_1350 + Math.sin(angle) * r;
         return new class_243(x, yLevel, z);
      }
   }

   private class_243 randomPosInRadius(class_243 center, float radius, float minStep, double yLevel) {
      if (center == null) {
         return null;
      } else {
         for(int i = 0; i < 16; ++i) {
            double angle = Math.random() * Math.PI * (double)2.0F;
            double r = (double)radius * Math.random();
            double x = center.field_1352 + Math.cos(angle) * r;
            double z = center.field_1350 + Math.sin(angle) * r;
            class_243 cand = new class_243(x, yLevel, z);
            if (this.lastRandomPos == null || cand.method_1025(this.lastRandomPos) >= (double)(minStep * minStep)) {
               return cand;
            }
         }

         return new class_243(center.field_1352 + (double)radius, yLevel, center.field_1350);
      }
   }
}

