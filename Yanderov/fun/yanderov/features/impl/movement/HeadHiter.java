package fun.Yanderov.features.impl.movement;

import fun.Yanderov.events.player.TickEvent;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.features.module.setting.Setting;
import fun.Yanderov.features.module.setting.implement.BooleanSetting;
import fun.Yanderov.features.module.setting.implement.SliderSettings;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import fun.Yanderov.utils.display.interfaces.QuickImports;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_746;

public final class HeadHiter extends Module implements QuickImports {
   private final SliderSettings jumpPeriodTicks = (new SliderSettings("Ð§Ð°ÑÑ‚Ð¾Ñ‚Ð°", "ÐŸÐµÑ€Ð¸Ð¾Ð´ Ð¿Ñ€Ñ‹Ð¶ÐºÐ° Ð² Ñ‚Ð¸ÐºÐ°Ñ…")).setValue(6.0F).range(1, 40);
   private final BooleanSetting onlyMove = (new BooleanSetting("Only Move", "ÐŸÑ€Ñ‹Ð³Ð°Ñ‚ÑŒ Ñ‚Ð¾Ð»ÑŒÐºÐ¾ Ð¿Ñ€Ð¸ Ð´Ð²Ð¸Ð¶ÐµÐ½Ð¸Ð¸")).setValue(true);
   private int lastJumpAge = -1000;

   public HeadHiter() {
      super("Head Hiter", ModuleCategory.MOVEMENT);
      this.setup(new Setting[]{this.jumpPeriodTicks, this.onlyMove});
   }

   @EventHandler
   public void onTick(TickEvent e) {
      if (e.getType() == 0) {
         if (mc != null && mc.field_1724 != null && mc.field_1687 != null) {
            class_746 p = mc.field_1724;
            double baseY = Math.floor(p.method_23318());
            class_243 center = new class_243(p.method_23317(), baseY, p.method_23321());
            double r = 0.3;
            class_238 midBox = new class_238(center.field_1352 - r, baseY + (double)1.0F, center.field_1350 - r, center.field_1352 + r, baseY + 1.6, center.field_1350 + r);
            class_238 ceilBox = new class_238(center.field_1352 - r, baseY + 1.8, center.field_1350 - r, center.field_1352 + r, baseY + 2.05, center.field_1350 + r);
            boolean midFree = !mc.field_1687.method_20812(p, midBox).iterator().hasNext();
            boolean ceilHit = mc.field_1687.method_20812(p, ceilBox).iterator().hasNext();
            if (midFree && ceilHit && p.method_24828()) {
               if (this.onlyMove.isValue() && !this.isMovingKeys()) {
                  return;
               }

               int period = Math.max(1, (int)this.jumpPeriodTicks.getValue());
               if (p.field_6012 - this.lastJumpAge >= period) {
                  try {
                     p.method_6043();
                     this.lastJumpAge = p.field_6012;
                  } catch (Throwable var14) {
                  }
               }
            }

         }
      }
   }

   private boolean isMovingKeys() {
      if (mc.field_1690 == null) {
         return false;
      } else {
         return mc.field_1690.field_1894.method_1434() || mc.field_1690.field_1881.method_1434() || mc.field_1690.field_1913.method_1434() || mc.field_1690.field_1849.method_1434();
      }
   }
}

