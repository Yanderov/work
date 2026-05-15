package dev.client.yanderov.features.impl.misc;

import dev.client.yanderov.events.player.TickEvent;
import dev.client.yanderov.events.render.WorldRenderEvent;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.features.module.setting.Setting;
import dev.client.yanderov.features.module.setting.implement.BooleanSetting;
import dev.client.yanderov.features.module.setting.implement.MultiSelectSetting;
import dev.client.yanderov.features.module.setting.implement.SelectSetting;
import dev.client.yanderov.features.module.setting.implement.SliderSettings;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import dev.client.yanderov.utils.display.geometry.Render3D;
import dev.client.yanderov.utils.display.interfaces.QuickImports;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1747;
import net.minecraft.class_1799;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import net.minecraft.class_2382;
import net.minecraft.class_239;
import net.minecraft.class_243;
import net.minecraft.class_2680;
import net.minecraft.class_3532;
import net.minecraft.class_3965;
import net.minecraft.class_746;

public class BlockTrap extends Module implements QuickImports {
   private final SliderSettings range = (new SliderSettings("Range", "Ð”Ð¸ÑÑ‚Ð°Ð½Ñ†Ð¸Ñ Ð´Ð¾ Ñ†ÐµÐ»Ð¸")).setValue(4.0F).range(1.0F, 6.0F);
   private final MultiSelectSetting placeAt = (new MultiSelectSetting("PlaceAt", "Ð—Ð¾Ð½Ñ‹ ÑƒÑÑ‚Ð°Ð½Ð¾Ð²ÐºÐ¸")).value("Legs", "Floor").selected("Legs", "Floor");
   private final MultiSelectSetting doublePlace = (new MultiSelectSetting("DoublePlace", "Ð”Ð¾Ð¿. ÑÐ»Ð¾Ð¸")).value("Above", "Below");
   private final SelectSetting placePriority = (new SelectSetting("PlacePriority", "ÐŸÑ€Ð¸Ð¾Ñ€Ð¸Ñ‚ÐµÑ‚ ÑƒÑÑ‚Ð°Ð½Ð¾Ð²ÐºÐ¸")).value("Furthest", "Closest", "Highest", "Lowest").selected("Furthest");
   private final BooleanSetting placeBlocks = (new BooleanSetting("Place", "Ð¡Ñ‚Ð°Ð²Ð¸Ñ‚ÑŒ Ð±Ð»Ð¾ÐºÐ¸ (Ð¸Ð½Ð°Ñ‡Ðµ Ñ‚Ð¾Ð»ÑŒÐºÐ¾ Ñ€ÐµÐ½Ð´ÐµÑ€)")).setValue(false);
   private Set plan = Collections.emptySet();
   private class_1309 target;

   public BlockTrap() {
      super("BlockTrap", ModuleCategory.MISC);
      this.setup(new Setting[]{this.range, this.placeAt, this.doublePlace, this.placePriority, this.placeBlocks});
   }

   @EventHandler
   public void onTick(TickEvent e) {
      if (e.getType() == 0) {
         if (mc.field_1724 != null && mc.field_1687 != null) {
            this.target = this.selectTarget();
            if (this.target == null) {
               this.plan = Collections.emptySet();
            } else {
               this.plan = this.findTrapPlan(this.target);
               this.plan = this.applyPriority(this.plan);
               if (this.placeBlocks.isValue()) {
                  this.tryPlaceSome(this.plan);
               }

            }
         }
      }
   }

   @EventHandler
   public void onRender(WorldRenderEvent e) {
      if (this.plan != null && !this.plan.isEmpty()) {
         for(class_2338 bp : this.plan) {
            Render3D.drawBox(new class_238(bp), 1442775040, 2.0F);
         }

      }
   }

   private class_1309 selectTarget() {
      try {
         class_239 hr = mc.field_1765;
         if (hr instanceof class_3965) {
         }
      } catch (Throwable var10) {
      }

      double r2 = (double)(this.range.getValue() * this.range.getValue());
      class_1309 best = null;
      double bestD = Double.MAX_VALUE;

      for(class_1657 pe : mc.field_1687.method_18456()) {
         if (pe != mc.field_1724 && !pe.method_7325() && !pe.method_7337()) {
            double d = mc.field_1724.method_5858(pe);
            if (d <= r2 && d < bestD) {
               bestD = d;
               best = pe;
            }
         }
      }

      return best;
   }

   private Set findTrapPlan(class_1297 tgt) {
      class_238 bb = tgt.method_5829();
      Set<class_2338> result = new HashSet();
      int xMin = class_3532.method_15357(bb.field_1323) - 1;
      int xMax = class_3532.method_15357(bb.field_1320) + 1;
      int yMin = class_3532.method_15357(bb.field_1322) - 1;
      int yMax = class_3532.method_15357(bb.field_1325) + 1;
      int zMin = class_3532.method_15357(bb.field_1321) - 1;
      int zMax = class_3532.method_15357(bb.field_1324) + 1;
      int bbXMin = class_3532.method_15357(bb.field_1323);
      int bbXMax = class_3532.method_15357(bb.field_1320);
      int bbYMin = class_3532.method_15357(bb.field_1322);
      int bbYMax = class_3532.method_15357(bb.field_1325);
      int bbZMin = class_3532.method_15357(bb.field_1321);
      int bbZMax = class_3532.method_15357(bb.field_1324);
      int highestY = Integer.MIN_VALUE;
      int lowestY = Integer.MAX_VALUE;

      for(int x = xMin; x <= xMax; ++x) {
         for(int y = yMin; y <= yMax; ++y) {
            for(int z = zMin; z <= zMax; ++z) {
               boolean inBb = x >= bbXMin && x <= bbXMax && y >= bbYMin && y <= bbYMax && z >= bbZMin && z <= bbZMax;
               boolean onlyZIn = x < bbXMin || x > bbXMax;
               onlyZIn &= y < bbYMin || y > bbYMax;
               boolean onlyXIn = y < bbYMin || y > bbYMax;
               onlyXIn &= z < bbZMin || z > bbZMax;
               boolean onlyYIn = z < bbZMin || z > bbZMax;
               onlyYIn &= x < bbXMin || x > bbXMax;
               if (!inBb && !onlyZIn && !onlyXIn && !onlyYIn) {
                  class_2338 p = new class_2338(x, y, z);
                  result.add(p);
                  highestY = Math.max(highestY, y);
                  lowestY = Math.min(lowestY, y);
               }
            }
         }
      }

      boolean keepLegs = this.placeAt.isSelected("Legs");
      boolean keepFloor = this.placeAt.isSelected("Floor");
      List<class_2338> filtered = new ArrayList();

      for(class_2338 p : result) {
         boolean isLegs = p.method_10264() == lowestY + 1;
         boolean isFloor = p.method_10264() == lowestY;
         if ((keepLegs || !isLegs) && (keepFloor || !isFloor)) {
            filtered.add(p);
         }
      }

      List<class_2338> additions = new ArrayList();
      boolean addBelow = this.doublePlace.isSelected("Below") && keepFloor;
      boolean addAbove = this.doublePlace.isSelected("Above");

      for(class_2338 p : filtered) {
         if (addBelow && p.method_10264() == lowestY) {
            additions.add(p.method_10074());
         }

         if (addAbove && p.method_10264() == highestY) {
            additions.add(p.method_10084());
         }
      }

      filtered.addAll(additions);
      return new HashSet(filtered);
   }

   private Set applyPriority(Set in) {
      if (in.isEmpty()) {
         return in;
      } else {
         class_243 pos = mc.field_1724.method_19538();
         Comparator<class_2338> comp;
         switch (this.placePriority.getSelected()) {
            case "Closest" -> comp = Comparator.comparingDouble((p) -> p.method_46558().method_1025(pos));
            case "Highest" -> comp = Comparator.comparingInt(class_2382::method_10264).reversed();
            case "Lowest" -> comp = Comparator.comparingInt(class_2382::method_10264);
            default -> comp = Comparator.comparingDouble((p) -> p.method_46558().method_1025(pos)).reversed();
         }

         List<class_2338> list = new ArrayList(in);
         list.sort(comp);
         return new LinkedHashSet(list);
      }
   }

   private void tryPlaceSome(Set positions) {
      if (mc.field_1761 != null) {
         class_746 p = mc.field_1724;
         if (p != null) {
            int slot = this.findHotbarBlockSlot();
            if (slot != -1) {
               int prev = p.method_31548().field_7545;
               if (slot != prev) {
                  p.method_31548().field_7545 = slot;
               }

               int placed = 0;

               for(class_2338 bp : positions) {
                  if (mc.field_1687.method_8320(bp).method_26215()) {
                     boolean ok = false;

                     for(class_2350 dir : class_2350.values()) {
                        class_2338 neighbor = bp.method_10093(dir);
                        class_2680 ns = mc.field_1687.method_8320(neighbor);
                        if (!ns.method_26215()) {
                           class_243 hit = class_243.method_24953(bp);
                           class_3965 bhr = new class_3965(hit, dir.method_10153(), bp, false);

                           try {
                              mc.field_1761.method_2896(p, class_1268.field_5808, bhr);
                              p.method_6104(class_1268.field_5808);
                              ok = true;
                              break;
                           } catch (Throwable var18) {
                           }
                        }
                     }

                     if (ok) {
                        ++placed;
                        if (placed >= 4) {
                           break;
                        }
                     }
                  }
               }

               if (slot != prev) {
                  p.method_31548().field_7545 = prev;
               }

            }
         }
      }
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

