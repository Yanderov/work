package dev.client.yanderov.features.impl.player;

import dev.client.yanderov.events.player.InputEvent;
import dev.client.yanderov.events.player.TickEvent;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.features.module.setting.Setting;
import dev.client.yanderov.features.module.setting.implement.SelectSetting;
import dev.client.yanderov.features.module.setting.implement.SliderSettings;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import dev.client.yanderov.utils.display.interfaces.QuickImports;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import net.minecraft.class_1291;
import net.minecraft.class_1293;
import net.minecraft.class_1297;
import net.minecraft.class_1667;
import net.minecraft.class_1686;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1844;
import net.minecraft.class_243;
import net.minecraft.class_2561;
import net.minecraft.class_3959;
import net.minecraft.class_3965;
import net.minecraft.class_4081;
import net.minecraft.class_9334;
import net.minecraft.class_239.class_240;
import net.minecraft.class_3959.class_242;
import net.minecraft.class_3959.class_3960;

public final class AutoDodge extends Module implements QuickImports {
   private final SelectSetting mode = (new SelectSetting("Ð ÐµÐ¶Ð¸Ð¼", "Ð ÐµÐ¶Ð¸Ð¼ Ñ€ÐµÐ°ÐºÑ†Ð¸Ð¸")).value("ÐÐ²Ñ‚Ð¾", "Ð£Ð²Ð¾Ñ€Ð¾Ñ‚", "ÐŸÐ»Ð°ÑÑ‚").selected("ÐÐ²Ñ‚Ð¾");
   private final SliderSettings reactDistance = (new SliderSettings("Ð”Ð¸ÑÑ‚Ð°Ð½Ñ†Ð¸Ñ Ñ€ÐµÐ°ÐºÑ†Ð¸Ð¸", "ÐœÐ°ÐºÑ. Ð´Ð¸ÑÑ‚Ð°Ð½Ñ†Ð¸Ñ Ð´Ð»Ñ Ñ€ÐµÐ°ÐºÑ†Ð¸Ð¸")).setValue(6.0F).range(3.0F, 12.0F);
   private final Map predictedHits = new HashMap();

   public AutoDodge() {
      super("AutoDodge", ModuleCategory.PLAYER);
      this.setup(new Setting[]{this.mode, this.reactDistance});
   }

   @EventHandler
   public void onTick(TickEvent e) {
      if (e.getType() == 0) {
         if (mc != null && mc.field_1724 != null && mc.field_1687 != null) {
            this.predictedHits.clear();
            double scanR = (double)32.0F;

            for(class_1686 pot : mc.field_1687.method_8390(class_1686.class, mc.field_1724.method_5829().method_1014(scanR), Objects::nonNull)) {
               class_1799 st = this.getPotionStackSafe(pot);
               if (this.isHarmfulOnly(st)) {
                  class_243 hit = this.predictImpactPoint(pot, 150);
                  if (hit != null) {
                     this.predictedHits.put(pot, hit);
                  }
               }
            }

            for(class_1667 arr : mc.field_1687.method_8390(class_1667.class, mc.field_1724.method_5829().method_1014(scanR), Objects::nonNull)) {
               class_1799 st = this.getArrowStackSafe(arr);
               if (this.isHarmfulOnly(st)) {
                  class_243 hit = this.predictImpactPoint(arr, 150);
                  if (hit != null) {
                     this.predictedHits.put(arr, hit);
                  }
               }
            }

         }
      }
   }

   @EventHandler
   public void onInput(InputEvent e) {
      if (mc != null && mc.field_1724 != null) {
         if (!this.predictedHits.isEmpty()) {
            double maxD = (double)this.reactDistance.getValue();

            for(Map.Entry en : this.predictedHits.entrySet()) {
               class_243 hit = (class_243)en.getValue();
               if (hit != null) {
                  double d = mc.field_1724.method_19538().method_1022(hit);
                  if (!(d > maxD)) {
                     String m = this.mode.getSelected();
                     if ("ÐÐ²Ñ‚Ð¾".equals(m)) {
                        if (!this.hasKelpCooldown() && d > (double)3.0F) {
                           this.useKelp();
                        } else {
                           e.setDirectional(true, false, false, false);
                           e.setJumping(mc.field_1724.field_5976 || mc.field_1724.method_5624());
                        }
                     } else if ("Ð£Ð²Ð¾Ñ€Ð¾Ñ‚".equals(m)) {
                        e.setDirectional(true, false, false, false);
                        e.setJumping(mc.field_1724.field_5976 || mc.field_1724.method_5624());
                     } else if ("ÐŸÐ»Ð°ÑÑ‚".equals(m) && !this.hasKelpCooldown() && d > (double)3.0F) {
                        this.useKelp();
                     }
                     break;
                  }
               }
            }

         }
      }
   }

   private boolean hasKelpCooldown() {
      try {
         return mc.field_1724.method_7357().method_7904(new class_1799(class_1802.field_8551));
      } catch (Throwable var2) {
         return false;
      }
   }

   private void useKelp() {
      try {
         Class<?> invTask = Class.forName("fun.Yanderov.utils.interactions.inv.InventoryTask");
         invTask.getMethod("swapAndUse", class_1792.class).invoke((Object)null, class_1802.field_8551);
      } catch (Throwable var4) {
         try {
            if (mc.field_1705 != null) {
               mc.field_1705.method_1743().method_1812(class_2561.method_30163("Use kelp!"));
            }
         } catch (Throwable var3) {
         }
      }

   }

   private class_1799 getPotionStackSafe(class_1686 pot) {
      try {
         Object s = class_1686.class.getMethod("getStack").invoke(pot);
         if (s instanceof class_1799 st) {
            return st;
         }
      } catch (Throwable var4) {
      }

      return class_1799.field_8037;
   }

   private class_1799 getArrowStackSafe(class_1667 arr) {
      try {
         Method m = arr.getClass().getMethod("asItemStack");
         Object o = m.invoke(arr);
         if (o instanceof class_1799 st) {
            return st;
         }
      } catch (Throwable var7) {
      }

      try {
         Method m = arr.getClass().getMethod("getItemStack");
         Object o = m.invoke(arr);
         if (o instanceof class_1799 st) {
            return st;
         }
      } catch (Throwable var6) {
      }

      try {
         Field f = arr.getClass().getDeclaredField("item");
         f.setAccessible(true);
         Object o = f.get(arr);
         if (o instanceof class_1799 st) {
            return st;
         }
      } catch (Throwable var5) {
      }

      return class_1799.field_8037;
   }

   private boolean isHarmfulOnly(class_1799 stack) {
      if (stack != null && !stack.method_7960()) {
         try {
            class_1844 comp = (class_1844)stack.method_57824(class_9334.field_49651);
            if (comp == null) {
               return false;
            } else {
               int harmful = 0;
               int beneficial = 0;

               for(class_1293 eff : comp.method_57397()) {
                  if (eff != null && eff.method_5579() != null) {
                     class_4081 cat = ((class_1291)eff.method_5579().comp_349()).method_18792();
                     if (cat == class_4081.field_18272) {
                        ++harmful;
                     } else if (cat == class_4081.field_18271) {
                        ++beneficial;
                     }
                  }
               }

               return harmful > 0 && beneficial == 0;
            }
         } catch (Throwable var8) {
            return false;
         }
      } else {
         return false;
      }
   }

   private class_243 predictImpactPoint(class_1297 ent, int steps) {
      class_243 motion = ent.method_18798();
      class_243 pos = ent.method_19538();

      for(int i = 0; i < steps; ++i) {
         class_243 prev = pos;
         pos = pos.method_1019(motion);
         motion = motion.method_18805(0.99, 0.99, 0.99);
         motion = motion.method_1031((double)0.0F, -0.05, (double)0.0F);
         class_3965 bhr = mc.field_1687.method_17742(new class_3959(prev, pos, class_3960.field_17558, class_242.field_1348, ent));
         if (bhr != null && bhr.method_17783() == class_240.field_1332) {
            return bhr.method_17784();
         }

         if (pos.field_1351 < (double)mc.field_1687.method_31607()) {
            break;
         }
      }

      return null;
   }
}

