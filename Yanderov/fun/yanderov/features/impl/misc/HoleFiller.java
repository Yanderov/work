package fun.Yanderov.features.impl.misc;

import fun.Yanderov.events.player.TickEvent;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.features.module.setting.Setting;
import fun.Yanderov.features.module.setting.implement.MultiSelectSetting;
import fun.Yanderov.features.module.setting.implement.SliderSettings;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import fun.Yanderov.utils.display.interfaces.QuickImports;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import net.minecraft.class_1268;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1747;
import net.minecraft.class_1799;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_3965;

public class HoleFiller extends Module implements QuickImports {
   private final MultiSelectSetting features = (new MultiSelectSetting("Features", "ÐžÐ¿Ñ†Ð¸Ð¸")).value("Smart", "PreventSelfFill", "OnlyWhenSelfInHole", "CheckMovement", "Only1x1").selected("Smart", "PreventSelfFill");
   private final SliderSettings area = (new SliderSettings("Area", "Ð Ð°Ð´Ð¸ÑƒÑ Ð²Ð¾ÐºÑ€ÑƒÐ³ Ð½Ð¾Ð³ Ñ†ÐµÐ»ÐµÐ¹")).setValue(2.0F).range(1.0F, 5.0F);
   private final SliderSettings perTick = (new SliderSettings("PerTick", "Ð›Ð¸Ð¼Ð¸Ñ‚ Ð¿Ð¾ÑÑ‚Ð°Ð½Ð¾Ð²ÐºÐ¸ Ð·Ð° Ñ‚Ð¸Ðº")).setValue(4.0F).range(1.0F, 10.0F);

   public HoleFiller() {
      super("HoleFiller", ModuleCategory.MISC);
      this.setup(new Setting[]{this.features, this.area, this.perTick});
   }

   @EventHandler
   public void onTick(TickEvent e) {
      if (e.getType() == 0) {
         if (mc.field_1724 != null && mc.field_1687 != null && mc.field_1761 != null) {
            int placed = 0;
            Set<class_2338> targets = this.collectTargets();
            if (!targets.isEmpty()) {
               int slot = this.findHotbarBlockSlot();
               if (slot != -1) {
                  int prev = mc.field_1724.method_31548().field_7545;
                  if (prev != slot) {
                     mc.field_1724.method_31548().field_7545 = slot;
                  }

                  for(class_2338 bp : targets) {
                     if (mc.field_1687.method_8320(bp).method_26215() && this.placeAt(bp)) {
                        ++placed;
                        if (placed >= (int)this.perTick.getValue()) {
                           break;
                        }
                     }
                  }

                  if (prev != slot) {
                     mc.field_1724.method_31548().field_7545 = prev;
                  }

               }
            }
         }
      }
   }

   private boolean placeAt(class_2338 pos) {
      for(class_2350 dir : class_2350.values()) {
         class_2338 neighbor = pos.method_10093(dir);
         class_2680 ns = mc.field_1687.method_8320(neighbor);
         if (!ns.method_26215()) {
            try {
               class_1268 hand = class_1268.field_5808;
               class_3965 bhr = new class_3965(class_243.method_24953(pos), dir.method_10153(), pos, false);
               mc.field_1761.method_2896(mc.field_1724, hand, bhr);
               mc.field_1724.method_6104(class_1268.field_5808);
               return true;
            } catch (Throwable var10) {
            }
         }
      }

      return false;
   }

   private Set collectTargets() {
      Set<class_2338> out = new LinkedHashSet();
      List<class_1309> candidates = new ArrayList();

      for(class_1657 pe : mc.field_1687.method_18456()) {
         if (pe != mc.field_1724 && !pe.method_7325()) {
            candidates.add(pe);
         }
      }

      candidates.add(mc.field_1724);
      int a = Math.round(this.area.getValue());

      for(class_1309 ent : candidates) {
         class_2338 center = ent.method_24515();
         class_238 region = (new class_238(center)).method_1009((double)a, (double)a, (double)a);
         if (ent != mc.field_1724 || !this.features.isSelected("OnlyWhenSelfInHole") || this.isInHole(mc.field_1724.method_24515())) {
            for(int dx = -a; dx <= a; ++dx) {
               for(int dz = -a; dz <= a; ++dz) {
                  for(int dy = -a; dy <= a; ++dy) {
                     class_2338 holeTop = center.method_10069(dx, dy, dz);
                     if (region.method_1008((double)holeTop.method_10263() + (double)0.5F, (double)holeTop.method_10264() + (double)0.5F, (double)holeTop.method_10260() + (double)0.5F) && this.isValidHoleTop(holeTop) && (!this.features.isSelected("Only1x1") || this.isOneByOne(holeTop)) && (!this.features.isSelected("PreventSelfFill") || !mc.field_1724.method_5829().method_994(new class_238(holeTop))) && (!this.features.isSelected("Smart") || ent == mc.field_1724 || (!this.features.isSelected("CheckMovement") || this.movingTowards(ent, holeTop)) && !((double)holeTop.method_10264() + (double)1.0F > ent.method_23318()))) {
                        out.add(holeTop);
                        out.add(holeTop.method_10084());
                     }
                  }
               }
            }
         }
      }

      return out;
   }

   private boolean movingTowards(class_1309 ent, class_2338 holeTop) {
      class_243 vel = ent.method_18798();
      if (vel.method_1027() < 1.0E-4) {
         return true;
      } else {
         class_243 toHole = class_243.method_24953(holeTop).method_1020(ent.method_19538()).method_1029();
         class_243 v = vel.method_1029();
         double dot = toHole.method_1026(v);
         return dot >= (double)0.5F;
      }
   }

   private boolean isValidHoleTop(class_2338 top) {
      class_2680 a = mc.field_1687.method_8320(top);
      class_2680 b = mc.field_1687.method_8320(top.method_10084());
      if (a.method_26215() && b.method_26215()) {
         if (!this.isSolid(top.method_10074())) {
            return false;
         } else {
            return this.isSolid(top.method_10095()) && this.isSolid(top.method_10072()) && this.isSolid(top.method_10078()) && this.isSolid(top.method_10067());
         }
      } else {
         return false;
      }
   }

   private boolean isInHole(class_2338 top) {
      return this.isValidHoleTop(top);
   }

   private boolean isSolid(class_2338 pos) {
      class_2680 st = mc.field_1687.method_8320(pos);
      if (st.method_26215()) {
         return false;
      } else {
         class_265 shape = st.method_26220(mc.field_1687, pos);
         if (shape.method_1110()) {
            try {
               return st.method_26225();
            } catch (Throwable var5) {
               return false;
            }
         } else {
            class_238 box = shape.method_1107();
            return box.field_1320 - box.field_1323 >= 0.99 && box.field_1325 - box.field_1322 >= 0.99 && box.field_1324 - box.field_1321 >= 0.99;
         }
      }
   }

   private boolean isOneByOne(class_2338 top) {
      return true;
   }

   private int findHotbarBlockSlot() {
      try {
         for(int i = 0; i < 9; ++i) {
            class_1799 st = mc.field_1724.method_31548().method_5438(i);
            if (st.method_7909() instanceof class_1747) {
               return i;
            }
         }
      } catch (Throwable var3) {
      }

      return -1;
   }
}

