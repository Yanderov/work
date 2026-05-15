package dev.client.yanderov.features.impl.movement;

import dev.client.yanderov.display.hud.Notifications;
import dev.client.yanderov.events.keyboard.KeyEvent;
import dev.client.yanderov.events.player.AttackEvent;
import dev.client.yanderov.events.render.WorldRenderEvent;
import dev.client.yanderov.features.impl.combat.Aura;
import dev.client.yanderov.features.impl.render.Hud;
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
import dev.client.yanderov.utils.client.sound.SoundManager;
import dev.client.yanderov.utils.display.color.ColorAssist;
import dev.client.yanderov.utils.display.geometry.Render3D;
import java.awt.Color;
import net.minecraft.class_1297;
import net.minecraft.class_1304;
import net.minecraft.class_1309;
import net.minecraft.class_1802;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_3414;

public class ElytraTarget extends Module {
   public SliderSettings elytraFindRange = (new SliderSettings("Ð”Ð¸ÑÑ‚Ð°Ð½Ñ†Ð¸Ñ Ð½Ð°Ð²Ð¾Ð´ÐºÐ¸", "Ð”Ð°Ð»ÑŒÐ½Ð¾ÑÑ‚ÑŒ Ð¿Ð¾Ð¸ÑÐºÐ° Ñ†ÐµÐ»Ð¸ Ð²Ð¾ Ð²Ñ€ÐµÐ¼Ñ Ð¿Ð¾Ð»ÐµÑ‚Ð° Ð½Ð° ÑÐ»Ð¸Ñ‚Ñ€Ðµ")).setValue(32.0F).range(6.0F, 64.0F);
   public SliderSettings elytraForward = (new SliderSettings("Ð—Ð½Ð°Ñ‡ÐµÐ½Ð¸Ðµ Ð¿ÐµÑ€ÐµÐ³Ð¾Ð½Ð°", "Ð·Ð°ÐµÐ±Ð°Ð»ÑÑ")).setValue(3.0F).range(0.0F, 6.0F);
   private final SelectSetting mode = (new SelectSetting("Ð ÐµÐ¶Ð¸Ð¼", "Ð ÐµÐ¶Ð¸Ð¼ ElytraTarget")).value("Default", "HitRun").selected("Default");
   public final SliderSettings hitRunDistance = (new SliderSettings("HitRunDistance", "Ð”Ð¸ÑÑ‚Ð°Ð½Ñ†Ð¸Ñ Ð¾Ñ‚Ð»Ñ‘Ñ‚Ð° Hit&Run")).setValue(6.0F).range(2.0F, 16.0F).visible(() -> this.mode.isSelected("HitRun"));
   final BindSetting forward = new BindSetting("ÐšÐ½Ð¾Ð¿ÐºÐ° Ð²ÐºÐ»/Ð²Ñ‹ÐºÐ» Ð¿ÐµÑ€ÐµÐ³Ð¾Ð½Ð°", "ÑƒÐ¶Ð°Ñ");
   public static boolean shouldElytraTarget = false;
   private final BooleanSetting elytraPredictEsp = (new BooleanSetting("ElytraPredictEsp", "ÐžÑ‚Ð¾Ð±Ñ€Ð°Ð¶Ð°Ñ‚ÑŒ Ð¿Ñ€ÐµÐ´Ð¸ÐºÑ‚ ESP")).setValue(true);
   private final ColorSetting elytraPredictColor = (new ColorSetting("ElytraPredictColor", "Ð¦Ð²ÐµÑ‚ Ð¿Ñ€ÐµÐ´Ð¸ÐºÑ‚ ESP")).value((new Color(255, 0, 0, 180)).getRGB()).visible(() -> this.elytraPredictEsp.isValue());
   private class_243 lastPredictedPos = null;

   public static ElytraTarget getInstance() {
      return (ElytraTarget)Instance.get(ElytraTarget.class);
   }

   public ElytraTarget() {
      super("ElytraTarget", "Elytra Target", ModuleCategory.MOVEMENT);
      this.setup(new Setting[]{this.elytraFindRange, this.elytraForward, this.mode, this.hitRunDistance, this.forward, this.elytraPredictEsp, this.elytraPredictColor});
   }

   @EventHandler
   private void onEventKey(KeyEvent e) {
      if (e.isKeyDown(this.forward.getKey())) {
         float volume = Hud.getInstance().getModuleVolume();
         shouldElytraTarget = !shouldElytraTarget;
         Notifications var10000 = Notifications.getInstance();
         String var10001 = shouldElytraTarget ? "enabled!" : "disabled";
         var10000.addList((String)("Elytra Forward " + var10001), 1500L, (class_3414)null);
         SoundManager.playSound(shouldElytraTarget ? SoundManager.ENABLE_MODULE : SoundManager.DISABLE_MODULE, volume, 1.0F);
      }

   }

   @EventHandler
   public void onWorldRender(WorldRenderEvent event) {
      if (shouldElytraTarget && mc != null && mc.field_1687 != null && mc.field_1724 != null) {
         if (mc.field_1724.method_6128()) {
            if (mc.field_1724.method_6118(class_1304.field_6174).method_31574(class_1802.field_8833)) {
               Aura aura = Aura.getInstance();
               if (aura != null && aura.isState()) {
                  class_1309 target = aura.getCurrentTarget();
                  if (target != null && !target.method_31481()) {
                     if (!(mc.field_1724.method_5739(target) > this.elytraFindRange.getValue())) {
                        class_243 predictedPos = this.predictPosition(target);
                        if (predictedPos != null) {
                           if (this.lastPredictedPos == null) {
                              this.lastPredictedPos = predictedPos;
                           } else {
                              double smooth = 0.4;
                              this.lastPredictedPos = this.lastPredictedPos.method_1019(predictedPos.method_1020(this.lastPredictedPos).method_1021(smooth));
                           }

                           if (this.elytraPredictEsp.isValue()) {
                              this.renderPredictedHitbox(target, this.lastPredictedPos);
                           }

                        }
                     }
                  }
               }
            }
         }
      }
   }

   private class_243 predictPosition(class_1297 target) {
      if (target == null) {
         return null;
      } else {
         class_243 velocity = target.method_18798();
         int predictTicks = (int)this.elytraForward.getValue();
         return target.method_19538().method_1019(velocity.method_1021((double)predictTicks));
      }
   }

   private void renderPredictedHitbox(class_1309 target, class_243 pos) {
      class_238 realBox = target.method_5829();
      class_243 delta = pos.method_1020(target.method_19538());
      class_238 box = realBox.method_997(delta);
      int color = ColorAssist.multAlpha(this.elytraPredictColor.getColor(), 0.5F);
      Render3D.drawBox(box, color, 1.5F);
   }

   @EventHandler
   public void onAttack(AttackEvent e) {
      if (this.mode.isSelected("HitRun")) {
         if (shouldElytraTarget) {
            if (mc != null && mc.field_1687 != null && mc.field_1724 != null) {
               if (mc.field_1724.method_6128()) {
                  if (mc.field_1724.method_6118(class_1304.field_6174).method_31574(class_1802.field_8833)) {
                     class_1297 var3 = e.getEntity();
                     if (var3 instanceof class_1309) {
                        class_1309 target = (class_1309)var3;
                        if (!(mc.field_1724.method_5739(target) > this.elytraFindRange.getValue())) {
                           class_243 playerPos = mc.field_1724.method_19538();
                           class_243 targetPos = target.method_19538();
                           class_243 dir = playerPos.method_1020(targetPos);
                           dir = new class_243(dir.field_1352, (double)0.0F, dir.field_1350);
                           if (!(dir.method_1027() < 1.0E-4)) {
                              dir = dir.method_1029();
                              double distance = (double)this.hitRunDistance.getValue();
                              class_243 desiredOffset = dir.method_1021(distance);
                              playerPos.method_1019(desiredOffset);
                              class_238 futureBox = mc.field_1724.method_5829().method_997(desiredOffset);
                              boolean blocked = mc.field_1687.method_20812(mc.field_1724, futureBox).iterator().hasNext();
                              if (blocked) {
                                 dir = dir.method_1021((double)-1.0F);
                              }

                              class_243 impulse = dir.method_1021((double)1.0F);
                              mc.field_1724.method_5762(impulse.field_1352, (double)0.0F, impulse.field_1350);
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }
}

