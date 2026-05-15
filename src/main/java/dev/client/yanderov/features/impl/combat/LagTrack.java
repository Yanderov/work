package dev.client.yanderov.features.impl.combat;

import dev.client.yanderov.events.player.AttackEvent;
import dev.client.yanderov.events.player.TickEvent;
import dev.client.yanderov.events.render.WorldRenderEvent;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.features.module.setting.Setting;
import dev.client.yanderov.features.module.setting.implement.BooleanSetting;
import dev.client.yanderov.features.module.setting.implement.SelectSetting;
import dev.client.yanderov.features.module.setting.implement.SliderSettings;
import dev.client.yanderov.utils.client.Instance;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import dev.client.yanderov.utils.display.color.ColorAssist;
import dev.client.yanderov.utils.display.geometry.Render3D;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1799;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_3532;

public class LagTrack extends Module {
   private final SelectSetting method = (new SelectSetting("ÐœÐµÑ‚Ð¾Ð´", "ÐœÐµÑ‚Ð¾Ð´ ÑÐ¼ÐµÑ‰ÐµÐ½Ð¸Ñ Ñ…Ð¸Ñ‚Ð±Ð¾ÐºÑÐ°")).value("Predict", "Ping (test)").selected("Predict");
   private final SliderSettings distance = (new SliderSettings("Ð”Ð¸ÑÑ‚Ð°Ð½Ñ†Ð¸Ñ", "ÐÐ°ÑÐºÐ¾Ð»ÑŒÐºÐ¾ ÑÐ¼ÐµÑ‰Ð°ÐµÑ‚ÑÑ Ñ…Ð¸Ñ‚Ð±Ð¾ÐºÑ (Ð² Ð±Ð»Ð¾ÐºÐ°Ñ…)")).setValue(0.1F).range(0.0F, 3.0F).visible(() -> this.method.isSelected("Predict"));
   private final BooleanSetting onlyForward = (new BooleanSetting("Ð¢Ð¾Ð»ÑŒÐºÐ¾ Ð²Ð¿ÐµÑ€ÐµÐ´", "Ð¡Ð¼ÐµÑ‰Ð°Ñ‚ÑŒ Ñ‚Ð¾Ð»ÑŒÐºÐ¾ ÐºÐ¾Ð³Ð´Ð° Ñ†ÐµÐ»ÑŒ ÑÐ¿Ð¸Ð½Ð¾Ð¹ Ðº Ð²Ð°Ð¼")).setValue(false).visible(() -> this.method.isSelected("Predict"));
   private final BooleanSetting renderBox = (new BooleanSetting("ÐžÑ‚Ð¾Ð±Ñ€Ð°Ð¶Ð°Ñ‚ÑŒ Ñ…Ð¸Ñ‚Ð±Ð¾ÐºÑ", "Ð Ð¸ÑÐ¾Ð²Ð°Ñ‚ÑŒ ÑÐ¼ÐµÑ‰Ñ‘Ð½Ð½Ñ‹Ð¹ Ñ…Ð¸Ñ‚Ð±Ð¾ÐºÑ")).setValue(true).visible(() -> this.method.isSelected("Predict"));
   private final SelectSetting offsetSide = (new SelectSetting("Ð¡Ñ‚Ð¾Ñ€Ð¾Ð½Ð° ÑÐ¼ÐµÑ‰ÐµÐ½Ð¸Ñ", "ÐšÑƒÐ´Ð° ÑÐ¼ÐµÑ‰Ð°Ñ‚ÑŒ Ñ…Ð¸Ñ‚Ð±Ð¾ÐºÑ")).value("Ð¡Ð¿Ð¸Ð½Ð°", "Ð’Ð·Ð³Ð»ÑÐ´", "Ð£Ð¼Ð½Ñ‹Ð¹").selected("Ð£Ð¼Ð½Ñ‹Ð¹").visible(() -> this.method.isSelected("Predict"));
   private int targetId = -1;

   public LagTrack() {
      super("LagTrack", "LagTrack", ModuleCategory.COMBAT);
      this.setup(new Setting[]{this.method, this.distance, this.onlyForward, this.renderBox, this.offsetSide});
   }

   public static LagTrack getInstance() {
      return (LagTrack)Instance.get(LagTrack.class);
   }

   @EventHandler
   public void onAttack(AttackEvent e) {
      if (mc.field_1724 != null && mc.field_1687 != null) {
         class_1297 hit = e.getEntity();
         if (hit instanceof class_1309) {
            class_1309 living = (class_1309)hit;
            if (living.method_5805() && !living.method_5655()) {
               this.targetId = living.method_5628();
            }
         }
      }
   }

   @EventHandler
   public void onTick(TickEvent e) {
      if (e.getType() == 0) {
         if (mc.field_1724 != null && mc.field_1687 != null) {
            if (!this.isState()) {
               this.targetId = -1;
            } else if (!this.method.isSelected("Ping (test)")) {
               Aura aura = Aura.getInstance();
               if (aura != null && aura.isState()) {
                  class_1309 living = aura.getCurrentTarget();
                  if (living instanceof class_1309 && living.method_5805() && !living.method_5655()) {
                     this.targetId = living.method_5628();
                  }
               }

            }
         }
      }
   }

   @EventHandler
   public void onWorldRender(WorldRenderEvent e) {
      if (this.isState()) {
         if (mc.field_1724 != null && mc.field_1687 != null) {
            if (this.method.isSelected("Predict")) {
               if (this.renderBox.isValue()) {
                  if (this.targetId != -1) {
                     class_1297 ent = mc.field_1687.method_8469(this.targetId);
                     if (ent instanceof class_1309) {
                        class_1309 living = (class_1309)ent;
                        if (living.method_5805() && !living.method_5655()) {
                           class_238 baseBox = living.method_5829();
                           double dist = (double)this.distance.getValue();
                           if (this.isThrowableInHand(mc.field_1724.method_6047())) {
                              this.drawBox(baseBox);
                           } else {
                              class_243 offset = this.computeOffset(living, dist);
                              if (offset == null) {
                                 this.drawBox(baseBox);
                              } else {
                                 class_238 shifted = baseBox.method_989(offset.field_1352, offset.field_1351, offset.field_1350);
                                 this.drawBox(shifted);
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

   private void drawBox(class_238 box) {
      int color = ColorAssist.getColor(255, 255, 255, 150);
      Render3D.drawBox(box, color, 1.0F);
   }

   private class_243 computeOffset(class_1309 target, double dist) {
      if (dist <= (double)0.0F) {
         return null;
      } else {
         float yaw = target.method_36454();
         float yawRad = yaw * ((float)Math.PI / 180F);
         class_243 look = new class_243((double)(-class_3532.method_15374(yawRad)), (double)0.0F, (double)class_3532.method_15362(yawRad));
         class_243 targetPos = target.method_19538();
         class_243 playerPos = mc.field_1724.method_19538();
         class_243 toPlayer = playerPos.method_1020(targetPos).method_1029();
         double dot = look.method_1029().method_1026(toPlayer);
         boolean backToMe = dot < (double)0.0F;
         if (this.onlyForward.isValue() && !backToMe) {
            return null;
         } else {
            class_243 dir;
            if (this.offsetSide.isSelected("Ð¡Ð¿Ð¸Ð½Ð°")) {
               dir = look.method_22882();
            } else if (this.offsetSide.isSelected("Ð’Ð·Ð³Ð»ÑÐ´")) {
               dir = look;
            } else if (backToMe) {
               dir = look.method_22882();
            } else {
               dir = look;
            }

            return dir.method_1027() == (double)0.0F ? null : dir.method_1029().method_1021(dist);
         }
      }
   }

   private boolean isThrowableInHand(class_1799 stack) {
      if (stack != null && !stack.method_7960()) {
         String key = stack.method_7909().method_7876().toLowerCase();
         return key.contains("snowball") || key.contains("egg") || key.contains("pearl") || key.contains("ender_pearl") || key.contains("trident") || key.contains("bow") || key.contains("crossbow") || key.contains("arrow");
      } else {
         return false;
      }
   }
}

