package dev.client.yanderov.features.impl.render;

import dev.client.yanderov.events.render.WorldRenderEvent;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.features.module.setting.Setting;
import dev.client.yanderov.features.module.setting.implement.MultiSelectSetting;
import dev.client.yanderov.features.module.setting.implement.SliderSettings;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import dev.client.yanderov.utils.display.geometry.Render3D;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_2680;

public class XRay extends Module {
   private final Set orePositions = ConcurrentHashMap.newKeySet();
   private final Set targetBlocks = new HashSet();
   private long lastScanTime = 0L;
   private final MultiSelectSetting blockTypeSetting = (new MultiSelectSetting("Ð‘Ð»Ð¾ÐºÐ¸", "Ð’Ñ‹Ð±Ð¾Ñ€ Ð±Ð»Ð¾ÐºÐ¾Ð² Ð´Ð»Ñ XRay")).value("Diamond", "Emerald", "Iron", "Gold", "Coal", "Redstone", "Lapis", "Copper", "Ancient Debris", "Netherite", "Quartz", "Amethyst");
   private final SliderSettings radiusFinder = (new SliderSettings("Ð”Ð¸ÑÑ‚Ð°Ð½Ñ†Ð¸Ñ Ð¿Ð¾Ð¸ÑÐºÐ°", "Ð”Ð¸Ð°Ð¿Ð°Ð·Ð¾Ð½ Ð¿Ð¾Ð¸ÑÐºÐ°")).setValue(16.0F).range(8.0F, 64.0F);
   private final SliderSettings scanDelay = (new SliderSettings("Ð—Ð°Ð´ÐµÑ€Ð¶ÐºÐ° ÑÐºÐ°Ð½Ð¸Ñ€Ð¾Ð²Ð°Ð½Ð¸Ñ", "Ð—Ð°Ð´ÐµÑ€Ð¶ÐºÐ° Ð² ÑÐµÐºÑƒÐ½Ð´Ð°Ñ…")).setValue(5.0F).range(1.0F, 30.0F);

   public XRay() {
      super("XRay", ModuleCategory.RENDER);
      this.setup(new Setting[]{this.blockTypeSetting, this.radiusFinder, this.scanDelay});
   }

   public void activate() {
      this.updateTargetBlocks();
      this.scanWorld();
      mc.field_1769.method_3279();
   }

   public void deactivate() {
      this.orePositions.clear();
      mc.field_1769.method_3279();
   }

   @EventHandler
   public void onRender3D(WorldRenderEvent e) {
      if (mc.field_1687 != null && mc.field_1724 != null) {
         this.updateTargetBlocks();
         long currentTime = System.currentTimeMillis();
         if ((float)(currentTime - this.lastScanTime) > this.scanDelay.getValue() * 1000.0F) {
            this.scanWorld();
            this.lastScanTime = currentTime;
         }

         double maxDistance = (double)(this.radiusFinder.getValue() * this.radiusFinder.getValue());
         class_2338 playerPos = mc.field_1724.method_24515();
         int rendered = 0;

         for(class_2338 pos : this.orePositions) {
            if (!(playerPos.method_10262(pos) > maxDistance)) {
               class_2680 state = mc.field_1687.method_8320(pos);
               if (this.targetBlocks.contains(state.method_26204())) {
                  int color = this.getColorByBlock(state.method_26204());
                  if (color != -1) {
                     Render3D.drawBox(new class_238(pos), color, 2.0F);
                     ++rendered;
                  }
               }
            }
         }

      }
   }

   private void updateTargetBlocks() {
      this.targetBlocks.clear();
      if (this.blockTypeSetting.isSelected("Diamond")) {
         Collections.addAll(this.targetBlocks, new class_2248[]{class_2246.field_10442, class_2246.field_29029, class_2246.field_10201});
      }

      if (this.blockTypeSetting.isSelected("Emerald")) {
         Collections.addAll(this.targetBlocks, new class_2248[]{class_2246.field_10013, class_2246.field_29220, class_2246.field_10234});
      }

      if (this.blockTypeSetting.isSelected("Iron")) {
         Collections.addAll(this.targetBlocks, new class_2248[]{class_2246.field_10212, class_2246.field_29027, class_2246.field_10085, class_2246.field_33508});
      }

      if (this.blockTypeSetting.isSelected("Gold")) {
         Collections.addAll(this.targetBlocks, new class_2248[]{class_2246.field_10571, class_2246.field_29026, class_2246.field_23077, class_2246.field_10205, class_2246.field_33510});
      }

      if (this.blockTypeSetting.isSelected("Coal")) {
         Collections.addAll(this.targetBlocks, new class_2248[]{class_2246.field_10418, class_2246.field_29219, class_2246.field_10381});
      }

      if (this.blockTypeSetting.isSelected("Redstone")) {
         Collections.addAll(this.targetBlocks, new class_2248[]{class_2246.field_10080, class_2246.field_29030, class_2246.field_10002});
      }

      if (this.blockTypeSetting.isSelected("Lapis")) {
         Collections.addAll(this.targetBlocks, new class_2248[]{class_2246.field_10090, class_2246.field_29028, class_2246.field_10441});
      }

      if (this.blockTypeSetting.isSelected("Copper")) {
         Collections.addAll(this.targetBlocks, new class_2248[]{class_2246.field_27120, class_2246.field_29221, class_2246.field_27119, class_2246.field_33509});
      }

      if (this.blockTypeSetting.isSelected("Ancient Debris")) {
         this.targetBlocks.add(class_2246.field_22109);
      }

      if (this.blockTypeSetting.isSelected("Netherite")) {
         this.targetBlocks.add(class_2246.field_22108);
      }

      if (this.blockTypeSetting.isSelected("Quartz")) {
         Collections.addAll(this.targetBlocks, new class_2248[]{class_2246.field_10213, class_2246.field_10153});
      }

      if (this.blockTypeSetting.isSelected("Amethyst")) {
         Collections.addAll(this.targetBlocks, new class_2248[]{class_2246.field_27161, class_2246.field_27162, class_2246.field_27163, class_2246.field_27164, class_2246.field_27159});
      }

   }

   private void scanWorld() {
      this.orePositions.clear();
      if (mc.field_1687 != null) {
         int radius = this.radiusFinder.getInt();
         class_2338 playerPos = mc.field_1724.method_24515();
         int found = 0;

         for(int x = -radius; x <= radius; ++x) {
            for(int y = -radius; y <= radius; ++y) {
               for(int z = -radius; z <= radius; ++z) {
                  class_2338 pos = playerPos.method_10069(x, y, z);
                  if (mc.field_1687.method_24794(pos)) {
                     class_2680 state = mc.field_1687.method_8320(pos);
                     if (this.targetBlocks.contains(state.method_26204())) {
                        this.orePositions.add(pos.method_10062());
                        ++found;
                     }
                  }
               }
            }
         }

      }
   }

   private int getColorByBlock(class_2248 block) {
      if (block != class_2246.field_10442 && block != class_2246.field_29029 && block != class_2246.field_10201) {
         if (block != class_2246.field_10013 && block != class_2246.field_29220 && block != class_2246.field_10234) {
            if (block != class_2246.field_10212 && block != class_2246.field_29027 && block != class_2246.field_10085 && block != class_2246.field_33508) {
               if (block != class_2246.field_10571 && block != class_2246.field_29026 && block != class_2246.field_23077 && block != class_2246.field_10205 && block != class_2246.field_33510) {
                  if (block != class_2246.field_10418 && block != class_2246.field_29219 && block != class_2246.field_10381) {
                     if (block != class_2246.field_10080 && block != class_2246.field_29030 && block != class_2246.field_10002) {
                        if (block != class_2246.field_10090 && block != class_2246.field_29028 && block != class_2246.field_10441) {
                           if (block != class_2246.field_27120 && block != class_2246.field_29221 && block != class_2246.field_27119 && block != class_2246.field_33509) {
                              if (block == class_2246.field_22109) {
                                 return -5868204;
                              } else if (block == class_2246.field_22108) {
                                 return -11776948;
                              } else if (block != class_2246.field_10213 && block != class_2246.field_10153) {
                                 return block != class_2246.field_27161 && block != class_2246.field_27162 && block != class_2246.field_27163 && block != class_2246.field_27164 && block != class_2246.field_27159 ? -1 : -6596170;
                              } else {
                                 return -1846076;
                              }
                           } else {
                              return -4954035;
                           }
                        } else {
                           return -15910005;
                        }
                     } else {
                        return -7667712;
                     }
                  } else {
                     return -15527149;
                  }
               } else {
                  return -3819208;
               }
            } else {
               return -9090017;
            }
         } else {
            return -12482789;
         }
      } else {
         return -15107199;
      }
   }
}

