package fun.Yanderov.features.impl.misc;

import fun.Yanderov.events.player.TickEvent;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.features.module.setting.Setting;
import fun.Yanderov.features.module.setting.implement.MultiSelectSetting;
import fun.Yanderov.features.module.setting.implement.SliderSettings;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import fun.Yanderov.utils.display.interfaces.QuickImports;
import net.minecraft.class_1297;
import net.minecraft.class_1542;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1812;
import net.minecraft.class_243;
import net.minecraft.class_3532;

public class Magnitism extends Module implements QuickImports {
   private final MultiSelectSetting targets = (new MultiSelectSetting("Ð¦ÐµÐ»Ð¸", "Ð¢Ð¸Ð¿Ñ‹ Ð¾Ð±ÑŠÐµÐºÑ‚Ð¾Ð² Ð´Ð»Ñ Ð½Ð°Ð²Ð¾Ð´ÐºÐ¸")).value("Ð­Ð»Ð¸Ñ‚Ñ€Ð°", "Ð¨Ð°Ñ€", "Ð§Ð°Ñ€ÐºÐ°").selected("Ð­Ð»Ð¸Ñ‚Ñ€Ð°", "Ð¨Ð°Ñ€", "Ð§Ð°Ñ€ÐºÐ°");
   private final SliderSettings range = (new SliderSettings("Ð”Ð¸ÑÑ‚Ð°Ð½Ñ†Ð¸Ñ", "ÐœÐ°ÐºÑ. Ñ€Ð°Ð´Ð¸ÑƒÑ Ð¿Ð¾Ð¸ÑÐºÐ°")).setValue(10.0F).range(1.0F, 64.0F);

   public Magnitism() {
      super("Magnitism", ModuleCategory.MISC);
      this.setup(new Setting[]{this.targets, this.range});
   }

   @EventHandler
   public void onTick(TickEvent e) {
      if (this.isState()) {
         if (e.getType() == 0) {
            if (mc != null && mc.field_1724 != null && mc.field_1687 != null) {
               double bestDistSq = Double.MAX_VALUE;
               class_1542 best = null;
               double r = (double)this.range.getValue();
               double r2 = r * r;

               for(class_1297 ent : mc.field_1687.method_18112()) {
                  if (ent instanceof class_1542) {
                     class_1542 itemEnt = (class_1542)ent;
                     class_1799 stack = itemEnt.method_6983();
                     if (stack != null && !stack.method_7960()) {
                        class_1792 it = stack.method_7909();
                        boolean match = false;
                        if (this.targets.isSelected("Ð­Ð»Ð¸Ñ‚Ñ€Ð°") && it == class_1802.field_8833) {
                           match = true;
                        }

                        if (this.targets.isSelected("Ð¨Ð°Ñ€") && it == class_1802.field_8575) {
                           match = true;
                        }

                        if (this.targets.isSelected("Ð§Ð°Ñ€ÐºÐ°") && (it instanceof class_1812 || it == class_1802.field_8436 || it == class_1802.field_8150 || it == class_1802.field_8574)) {
                           match = true;
                        }

                        if (match) {
                           double d2 = mc.field_1724.method_5858(itemEnt);
                           if (d2 <= r2 && d2 < bestDistSq) {
                              bestDistSq = d2;
                              best = itemEnt;
                           }
                        }
                     }
                  }
               }

               if (best != null) {
                  this.lookAt(best.method_19538());
               }

            }
         }
      }
   }

   private void lookAt(class_243 target) {
      class_243 eyes = mc.field_1724.method_33571();
      double dx = target.field_1352 - eyes.field_1352;
      double dy = target.field_1351 - eyes.field_1351;
      double dz = target.field_1350 - eyes.field_1350;
      double distXZ = Math.sqrt(dx * dx + dz * dz);
      float yaw = (float)class_3532.method_15338(Math.toDegrees(Math.atan2(dz, dx)) - (double)90.0F);
      float pitch = (float)(-Math.toDegrees(Math.atan2(dy, distXZ)));
      mc.field_1724.method_36456(yaw);
      mc.field_1724.method_36457(pitch);
      mc.field_1724.method_5636(yaw);
      mc.field_1724.method_5847(yaw);
   }
}

