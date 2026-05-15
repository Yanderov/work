package fun.Yanderov.features.impl.combat;

import fun.Yanderov.events.player.TickEvent;
import fun.Yanderov.events.render.WorldRenderEvent;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.features.module.setting.Setting;
import fun.Yanderov.features.module.setting.implement.BooleanSetting;
import fun.Yanderov.features.module.setting.implement.SelectSetting;
import fun.Yanderov.features.module.setting.implement.SliderSettings;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import fun.Yanderov.utils.display.geometry.Render3D;
import fun.Yanderov.utils.display.interfaces.QuickImports;
import fun.Yanderov.utils.interactions.interact.PlayerInteractionHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_1657;
import net.minecraft.class_1747;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_2680;
import net.minecraft.class_3959;
import net.minecraft.class_3965;
import net.minecraft.class_746;
import net.minecraft.class_239.class_240;
import net.minecraft.class_3959.class_242;
import net.minecraft.class_3959.class_3960;

public class AutoWeb extends Module implements QuickImports {
   private final SliderSettings range = (new SliderSettings("Range", "Ð”Ð¸ÑÑ‚Ð°Ð½Ñ†Ð¸Ñ Ð´Ð¾ Ñ†ÐµÐ»Ð¸")).setValue(5.0F).range(1.0F, 7.0F);
   private final SliderSettings wallRange = (new SliderSettings("WallRange", "ÐœÐ°ÐºÑ Ð´Ð¸ÑÑ‚Ð°Ð½Ñ†Ð¸Ñ Ñ‡ÐµÑ€ÐµÐ· ÑÑ‚ÐµÐ½Ñƒ")).setValue(5.0F).range(1.0F, 7.0F);
   private final SelectSetting placeTiming = (new SelectSetting("PlaceTiming", "Ð ÐµÐ¶Ð¸Ð¼ ÑƒÑÑ‚Ð°Ð½Ð¾Ð²ÐºÐ¸")).value("Default", "Vanilla").selected("Default");
   private final SliderSettings blocksPerTick = (new SliderSettings("Block/Tick", "Ð‘Ð»Ð¾ÐºÐ¾Ð² Ð·Ð° Ñ‚Ð¸Ðº")).setValue(8.0F).range(1.0F, 12.0F).visible(() -> this.placeTiming.isSelected("Default"));
   private final SliderSettings placeDelay = (new SliderSettings("Delay/Place", "Ð—Ð°Ð´ÐµÑ€Ð¶ÐºÐ° Ð¼ÐµÐ¶Ð´Ñƒ Ð¿Ð¾Ð¿Ñ‹Ñ‚ÐºÐ°Ð¼Ð¸ (Ñ‚Ð¸Ðº)")).setValue(3.0F).range(0.0F, 10.0F);
   private final BooleanSetting head = (new BooleanSetting("Head", "Ð¡Ñ‚Ð°Ð²Ð¸Ñ‚ÑŒ Ð²ÐµÐ± Ð½Ð° Ð³Ð¾Ð»Ð¾Ð²Ñƒ")).setValue(true);
   private final BooleanSetting legs = (new BooleanSetting("Legs", "Ð¡Ñ‚Ð°Ð²Ð¸Ñ‚ÑŒ Ð²ÐµÐ± Ð¿Ð¾Ð´ Ð½Ð¾Ð³Ð¸")).setValue(true);
   private final BooleanSetting surround = (new BooleanSetting("Surround", "ÐžÐºÑ€ÑƒÐ¶Ð°Ñ‚ÑŒ Ð¿Ð¾ Ð±Ð¾ÐºÐ°Ð¼")).setValue(true);
   private final BooleanSetting upperSurround = (new BooleanSetting("UpperSurround", "ÐžÐºÑ€ÑƒÐ¶Ð°Ñ‚ÑŒ ÑÐ²ÐµÑ€Ñ…Ñƒ Ð¿Ð¾ Ð±Ð¾ÐºÐ°Ð¼")).setValue(false);
   private final BooleanSetting render = (new BooleanSetting("Render", "Ð ÐµÐ½Ð´ÐµÑ€Ð¸Ñ‚ÑŒ Ð¿Ð¾ÑÑ‚Ð°Ð²Ð»ÐµÐ½Ð½Ñ‹Ðµ Ð±Ð»Ð¾ÐºÐ¸")).setValue(true);
   private final SliderSettings renderDurationMs;
   private final Map renderPoses;
   private int delay;

   public AutoWeb() {
      super("AutoWeb", ModuleCategory.COMBAT);
      SliderSettings var10001 = (new SliderSettings("RenderDuration", "Ð’Ñ€ÐµÐ¼Ñ ÑÐ²ÐµÑ‡ÐµÐ½Ð¸Ñ (ms)")).setValue(500.0F).range(0.0F, 5000.0F);
      BooleanSetting var10002 = this.render;
      Objects.requireNonNull(var10002);
      this.renderDurationMs = var10001.visible(var10002::isValue);
      this.renderPoses = new ConcurrentHashMap();
      this.delay = 0;
      this.setup(new Setting[]{this.range, this.wallRange, this.placeTiming, this.blocksPerTick, this.placeDelay, this.head, this.legs, this.surround, this.upperSurround, this.render, this.renderDurationMs});
   }

   public void activate() {
      this.renderPoses.clear();
      this.delay = 0;
   }

   @EventHandler
   public void onTick(TickEvent e) {
      if (this.isState()) {
         if (e.getType() == 0) {
            if (!PlayerInteractionHelper.nullCheck()) {
               if (!mc.field_1724.method_6115()) {
                  class_2338 firstCheck = this.getSequentialPos();
                  if (firstCheck != null) {
                     if (this.delay > 0) {
                        --this.delay;
                     } else {
                        if (this.placeTiming.isSelected("Default")) {
                           for(int placed = 0; placed < (int)this.blocksPerTick.getValue(); this.delay = (int)this.placeDelay.getValue()) {
                              class_2338 targetBlock = this.getSequentialPos();
                              if (targetBlock == null || !this.placeOne(targetBlock)) {
                                 break;
                              }

                              ++placed;
                           }
                        } else {
                           class_2338 targetBlock = this.getSequentialPos();
                           if (targetBlock == null) {
                              return;
                           }

                           if (this.placeOne(targetBlock)) {
                              this.delay = (int)this.placeDelay.getValue();
                           }
                        }

                     }
                  }
               }
            }
         }
      }
   }

   @EventHandler
   public void onWorldRender(WorldRenderEvent e) {
      if (this.isState()) {
         if (this.render.isValue()) {
            if (!this.renderPoses.isEmpty()) {
               long now = System.currentTimeMillis();
               long life = (long)this.renderDurationMs.getValue();
               Iterator<Map.Entry<class_2338, Long>> it = this.renderPoses.entrySet().iterator();

               while(it.hasNext()) {
                  Map.Entry<class_2338, Long> en = (Map.Entry)it.next();
                  class_2338 pos = (class_2338)en.getKey();
                  long ts = (Long)en.getValue();
                  if (life > 0L && now - ts > life) {
                     it.remove();
                  } else {
                     class_238 box = new class_238(pos);
                     Render3D.drawBox(box, 1442840575, 1.5F);
                  }
               }

            }
         }
      }
   }

   private class_2338 getSequentialPos() {
      if (mc.field_1724 != null && mc.field_1687 != null) {
         class_1657 target = this.findNearestTarget();
         if (target == null) {
            return null;
         } else {
            class_2338 base = class_2338.method_49638(target.method_19538());
            List<class_2338> positions = new ArrayList();
            if (this.legs.isValue()) {
               positions.add(base);
            }

            if (this.head.isValue()) {
               positions.add(base.method_10084());
            }

            if (this.surround.isValue()) {
               positions.add(base.method_10078());
               positions.add(base.method_10067());
               positions.add(base.method_10072());
               positions.add(base.method_10095());
            }

            if (this.upperSurround.isValue()) {
               positions.add(base.method_10078().method_10084());
               positions.add(base.method_10067().method_10084());
               positions.add(base.method_10072().method_10084());
               positions.add(base.method_10095().method_10084());
            }

            class_243 eyes = mc.field_1724.method_33571();
            double maxWallDistSq = (double)(this.wallRange.getValue() * this.wallRange.getValue());
            double maxRangeSq = (double)(this.range.getValue() * this.range.getValue());

            for(class_2338 bp : positions) {
               class_243 center = class_243.method_24953(bp).method_1031((double)0.0F, (double)0.5F, (double)0.0F);

               class_3965 wallCheck;
               try {
                  wallCheck = mc.field_1687.method_17742(new class_3959(eyes, center, class_3960.field_17558, class_242.field_1348, mc.field_1724));
               } catch (Throwable var14) {
                  wallCheck = null;
               }

               if ((wallCheck == null || wallCheck.method_17783() != class_240.field_1332 || wallCheck.method_17777().equals(bp) || !(eyes.method_1025(center) > maxWallDistSq)) && !(eyes.method_1025(center) > maxRangeSq) && this.canPlaceAt(bp)) {
                  return bp;
               }
            }

            return null;
         }
      } else {
         return null;
      }
   }

   private class_1657 findNearestTarget() {
      if (mc.field_1687 != null && mc.field_1724 != null) {
         double maxDistSq = (double)(this.range.getValue() * this.range.getValue());
         class_1657 best = null;
         double bestD = Double.MAX_VALUE;

         for(class_1657 pe : mc.field_1687.method_18456()) {
            if (pe != mc.field_1724 && !pe.method_7325() && !pe.method_7337() && !pe.method_29504()) {
               double d = mc.field_1724.method_5858(pe);
               if (d <= maxDistSq && d < bestD) {
                  bestD = d;
                  best = pe;
               }
            }
         }

         return best;
      } else {
         return null;
      }
   }

   private boolean canPlaceAt(class_2338 pos) {
      class_2680 state = mc.field_1687.method_8320(pos);
      if (!state.method_26215()) {
         return false;
      } else {
         boolean hasNeighbor = false;

         for(class_2350 dir : class_2350.values()) {
            class_2338 neighbor = pos.method_10093(dir);
            class_2680 ns = mc.field_1687.method_8320(neighbor);
            if (!ns.method_26215()) {
               hasNeighbor = true;
               break;
            }
         }

         return hasNeighbor;
      }
   }

   private boolean placeOne(class_2338 pos) {
      if (mc.field_1761 != null && mc.field_1724 != null && mc.field_1687 != null) {
         int slot = this.findCobwebInHotbar();
         if (slot == -1) {
            return false;
         } else {
            class_746 p = mc.field_1724;
            int prev = p.method_31548().field_7545;
            if (slot != prev) {
               p.method_31548().field_7545 = slot;
            }

            boolean placed = false;

            try {
               for(class_2350 dir : class_2350.values()) {
                  class_2338 neighbor = pos.method_10093(dir);
                  class_2680 ns = mc.field_1687.method_8320(neighbor);
                  if (!ns.method_26215()) {
                     class_243 hit = class_243.method_24953(pos);
                     class_3965 bhr = new class_3965(hit, dir.method_10153(), pos, false);

                     try {
                        class_1269 res = mc.field_1761.method_2896(p, class_1268.field_5808, bhr);
                        if (res.method_23665()) {
                           p.method_6104(class_1268.field_5808);
                           placed = true;
                           break;
                        }
                     } catch (Throwable var18) {
                     }
                  }
               }
            } finally {
               if (slot != prev) {
                  p.method_31548().field_7545 = prev;
               }

            }

            if (placed) {
               this.renderPoses.put(pos, System.currentTimeMillis());
            }

            return placed;
         }
      } else {
         return false;
      }
   }

   private int findCobwebInHotbar() {
      if (mc.field_1724 == null) {
         return -1;
      } else {
         for(int i = 0; i < 9; ++i) {
            class_1799 st = mc.field_1724.method_31548().method_5438(i);
            if (st != null && !st.method_7960()) {
               class_1792 var4 = st.method_7909();
               if (var4 instanceof class_1747) {
                  class_1747 bi = (class_1747)var4;
                  class_2248 block = bi.method_7711();
                  if (block == class_2246.field_10343) {
                     return i;
                  }
               }
            }
         }

         return -1;
      }
   }
}

