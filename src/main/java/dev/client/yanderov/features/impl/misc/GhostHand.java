package dev.client.yanderov.features.impl.misc;

import dev.client.yanderov.events.player.TickEvent;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.features.module.setting.Setting;
import dev.client.yanderov.features.module.setting.implement.BooleanSetting;
import dev.client.yanderov.features.module.setting.implement.MultiSelectSetting;
import dev.client.yanderov.features.module.setting.implement.SliderSettings;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import dev.client.yanderov.utils.display.interfaces.QuickImports;
import net.minecraft.class_1268;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_243;
import net.minecraft.class_3965;

public class GhostHand extends Module implements QuickImports {
   private final MultiSelectSetting targeted = (new MultiSelectSetting("TargetedBlocks", "Ð‘Ð»Ð¾ÐºÐ¸ Ð´Ð»Ñ Ð²Ð·Ð°Ð¸Ð¼Ð¾Ð´ÐµÐ¹ÑÑ‚Ð²Ð¸Ñ")).value("CHEST", "ENDER_CHEST", "TRAPPED_CHEST", "SHULKER_BOX", "WHITE_SHULKER_BOX", "ORANGE_SHULKER_BOX", "MAGENTA_SHULKER_BOX", "LIGHT_BLUE_SHULKER_BOX", "PINK_SHULKER_BOX", "YELLOW_SHULKER_BOX", "LIME_SHULKER_BOX", "GRAY_SHULKER_BOX", "LIGHT_GRAY_SHULKER_BOX", "CYAN_SHULKER_BOX", "PURPLE_SHULKER_BOX", "BLUE_SHULKER_BOX", "BROWN_SHULKER_BOX", "GREEN_SHULKER_BOX", "RED_SHULKER_BOX", "BLACK_SHULKER_BOX").selected("CHEST", "ENDER_CHEST", "TRAPPED_CHEST", "SHULKER_BOX", "WHITE_SHULKER_BOX", "ORANGE_SHULKER_BOX", "MAGENTA_SHULKER_BOX", "LIGHT_BLUE_SHULKER_BOX", "PINK_SHULKER_BOX", "YELLOW_SHULKER_BOX", "LIME_SHULKER_BOX", "GRAY_SHULKER_BOX", "LIGHT_GRAY_SHULKER_BOX", "CYAN_SHULKER_BOX", "PURPLE_SHULKER_BOX", "BLUE_SHULKER_BOX", "BROWN_SHULKER_BOX", "GREEN_SHULKER_BOX", "RED_SHULKER_BOX", "BLACK_SHULKER_BOX");
   private final SliderSettings range = (new SliderSettings("Range", "Ð”Ð¸ÑÑ‚Ð°Ð½Ñ†Ð¸Ñ ÑÐºÐ°Ð½Ð¸Ñ€Ð¾Ð²Ð°Ð½Ð¸Ñ")).setValue(5.0F).range(1.0F, 6.0F);
   private final BooleanSetting onlyOnUseKey = (new BooleanSetting("OnlyUseKey", "Ð¢Ð¾Ð»ÑŒÐºÐ¾ Ð¿Ñ€Ð¸ Ð·Ð°Ð¶Ð°Ñ‚Ð¾Ð¹ ÐŸÐšÐœ")).setValue(true);
   private long lastInteractMs = 0L;

   public GhostHand() {
      super("GhostHand", ModuleCategory.MISC);
      this.setup(new Setting[]{this.targeted, this.range, this.onlyOnUseKey});
   }

   @EventHandler
   public void onTick(TickEvent e) {
      if (e.getType() == 0) {
         if (mc.field_1724 != null && mc.field_1687 != null && mc.field_1761 != null) {
            if (mc.field_1755 == null) {
               if (this.onlyOnUseKey.isValue()) {
                  try {
                     if (mc.field_1690 == null || !mc.field_1690.field_1904.method_1434()) {
                        return;
                     }
                  } catch (Throwable var20) {
                     return;
                  }
               }

               long now = System.currentTimeMillis();
               if (now - this.lastInteractMs >= 150L) {
                  class_2338 pPos = mc.field_1724.method_24515();
                  float r = this.range.getValue();
                  int R = (int)Math.ceil((double)r);
                  class_2338 best = null;
                  double bestDist = Double.MAX_VALUE;
                  class_243 eye = mc.field_1724.method_33571();

                  for(int dx = -R; dx <= R; ++dx) {
                     for(int dy = -R; dy <= R; ++dy) {
                        for(int dz = -R; dz <= R; ++dz) {
                           class_2338 bp = pPos.method_10069(dx, dy, dz);
                           class_2248 b = mc.field_1687.method_8320(bp).method_26204();
                           if (this.isTarget(b)) {
                              class_243 center = class_243.method_24953(bp);
                              double dist = eye.method_1022(center);
                              if (dist <= (double)r && dist < bestDist) {
                                 best = bp;
                                 bestDist = dist;
                              }
                           }
                        }
                     }
                  }

                  if (best != null) {
                     try {
                        class_243 hitVec = class_243.method_24953(best);
                        class_2350 face = this.pickFace(eye, hitVec);
                        class_3965 bhr = new class_3965(hitVec, face, best, false);
                        mc.field_1761.method_2896(mc.field_1724, class_1268.field_5808, bhr);
                        this.lastInteractMs = now;
                     } catch (Throwable var19) {
                     }

                  }
               }
            }
         }
      }
   }

   private boolean isTarget(class_2248 b) {
      if (b == class_2246.field_10034 && this.targeted.isSelected("CHEST")) {
         return true;
      } else if (b == class_2246.field_10443 && this.targeted.isSelected("ENDER_CHEST")) {
         return true;
      } else if (b == class_2246.field_10380 && this.targeted.isSelected("TRAPPED_CHEST")) {
         return true;
      } else if (b == class_2246.field_10603 && this.targeted.isSelected("SHULKER_BOX")) {
         return true;
      } else if (b == class_2246.field_10199 && this.targeted.isSelected("WHITE_SHULKER_BOX")) {
         return true;
      } else if (b == class_2246.field_10407 && this.targeted.isSelected("ORANGE_SHULKER_BOX")) {
         return true;
      } else if (b == class_2246.field_10063 && this.targeted.isSelected("MAGENTA_SHULKER_BOX")) {
         return true;
      } else if (b == class_2246.field_10203 && this.targeted.isSelected("LIGHT_BLUE_SHULKER_BOX")) {
         return true;
      } else if (b == class_2246.field_10051 && this.targeted.isSelected("PINK_SHULKER_BOX")) {
         return true;
      } else if (b == class_2246.field_10600 && this.targeted.isSelected("YELLOW_SHULKER_BOX")) {
         return true;
      } else if (b == class_2246.field_10275 && this.targeted.isSelected("LIME_SHULKER_BOX")) {
         return true;
      } else if (b == class_2246.field_10140 && this.targeted.isSelected("GRAY_SHULKER_BOX")) {
         return true;
      } else if (b == class_2246.field_10320 && this.targeted.isSelected("LIGHT_GRAY_SHULKER_BOX")) {
         return true;
      } else if (b == class_2246.field_10532 && this.targeted.isSelected("CYAN_SHULKER_BOX")) {
         return true;
      } else if (b == class_2246.field_10268 && this.targeted.isSelected("PURPLE_SHULKER_BOX")) {
         return true;
      } else if (b == class_2246.field_10605 && this.targeted.isSelected("BLUE_SHULKER_BOX")) {
         return true;
      } else if (b == class_2246.field_10373 && this.targeted.isSelected("BROWN_SHULKER_BOX")) {
         return true;
      } else if (b == class_2246.field_10055 && this.targeted.isSelected("GREEN_SHULKER_BOX")) {
         return true;
      } else if (b == class_2246.field_10068 && this.targeted.isSelected("RED_SHULKER_BOX")) {
         return true;
      } else {
         return b == class_2246.field_10371 && this.targeted.isSelected("BLACK_SHULKER_BOX");
      }
   }

   private class_2350 pickFace(class_243 eye, class_243 center) {
      class_243 v = center.method_1020(eye);
      double ax = Math.abs(v.field_1352);
      double ay = Math.abs(v.field_1351);
      double az = Math.abs(v.field_1350);
      if (ay >= ax && ay >= az) {
         return v.field_1351 > (double)0.0F ? class_2350.field_11036 : class_2350.field_11033;
      } else if (ax >= az) {
         return v.field_1352 > (double)0.0F ? class_2350.field_11034 : class_2350.field_11039;
      } else {
         return v.field_1350 > (double)0.0F ? class_2350.field_11035 : class_2350.field_11043;
      }
   }
}

