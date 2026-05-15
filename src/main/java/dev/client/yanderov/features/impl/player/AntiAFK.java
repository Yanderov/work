package dev.client.yanderov.features.impl.player;

import dev.client.yanderov.events.player.RotationUpdateEvent;
import dev.client.yanderov.events.player.TickEvent;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.features.module.setting.Setting;
import dev.client.yanderov.features.module.setting.implement.MultiSelectSetting;
import dev.client.yanderov.features.module.setting.implement.SliderSettings;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import dev.client.yanderov.utils.interactions.interact.PlayerInteractionHelper;
import dev.client.yanderov.utils.math.time.StopWatch;
import net.minecraft.class_2338;
import net.minecraft.class_243;
import net.minecraft.class_2680;

public class AntiAFK extends Module {
   StopWatch timer = new StopWatch();
   MultiSelectSetting multiSetting = (new MultiSelectSetting("Ð ÐµÐ¶Ð¸Ð¼", "Ð’Ñ‹Ð±ÐµÑ€Ð¸Ñ‚Ðµ, Ñ‡Ñ‚Ð¾ Ð±ÑƒÐ´ÐµÑ‚ Ð¿Ñ€Ð¾Ð¸ÑÑ…Ð¾Ð´Ð¸Ñ‚ÑŒ")).value("Walk", "Jump", "Spin", "Rotation change");
   SliderSettings time = (new SliderSettings("Ð’Ñ‹Ð¿Ð¾Ð»Ð½ÑÑ‚ÑŒ ÐºÐ°Ð¶Ð´Ñ‹Ðµ (Ð² Ð¼Ð¸Ð½ÑƒÑ‚Ð°Ñ…)", "Ð¿Ð¸Ñ„")).setValue(10.0F).range(1.0F, 25.0F);
   private double yawTarget = (double)0.0F;
   private boolean rotating = false;
   private class_243 walkStartPos = null;
   private boolean walking = false;
   private class_2338 breakingBlockPos = null;

   public AntiAFK() {
      super("AntiAFK", "Anti AFK", ModuleCategory.PLAYER);
      this.setup(new Setting[]{this.multiSetting, this.time});
   }

   public void activate() {
      this.timer.reset();
   }

   public void deactivate() {
      this.rotating = false;
   }

   @EventHandler
   public void rotate(RotationUpdateEvent e) {
      if (this.rotating) {
         double diff = this.yawTarget - (double)mc.field_1724.method_36454();
         double diff2 = this.yawTarget - (double)mc.field_1724.method_36455();
         if (Math.abs(diff) > (double)1.0F) {
            mc.field_1724.method_36456((float)((double)mc.field_1724.method_36454() + diff * 0.1));
            mc.field_1724.method_36457((float)((double)mc.field_1724.method_36455() + diff * 0.1));
         } else {
            this.rotating = false;
         }
      }

   }

   @EventHandler
   public void tick(TickEvent e) {
      if (!PlayerInteractionHelper.nullCheck()) {
         long intervalMs = (long)(this.time.getValue() * 60.0F * 100.0F);
         if (this.timer.every((double)intervalMs)) {
            for(String mode : this.multiSetting.getSelected()) {
               switch (mode) {
                  case "Walk":
                     this.walkForward();
                     break;
                  case "Jump":
                     if (mc.field_1724.method_24828()) {
                        mc.field_1690.field_1903.method_23481(true);
                     }
                     break;
                  case "Spin":
                     this.spin();
                     break;
                  case "Rotation change":
                     this.randomRotation();
                     break;
                  case "Break Block":
                     this.breakBlockUnder();
               }
            }
         }

      }
   }

   private void walkForward() {
      if (!this.walking) {
         this.walkStartPos = mc.field_1724.method_19538();
         this.walking = true;
      }

      mc.field_1690.field_1881.method_23481(false);
      mc.field_1690.field_1913.method_23481(false);
      mc.field_1690.field_1849.method_23481(false);
      class_243 look = mc.field_1724.method_5720();
      class_2338 target = mc.field_1724.method_24515().method_10069((int)look.field_1352 * 5, 0, (int)look.field_1350 * 5);
      double distance = mc.field_1724.method_19538().method_1022(this.walkStartPos);
      if (distance >= (double)5.0F) {
         mc.field_1690.field_1894.method_23481(false);
         mc.field_1690.field_1881.method_23481(false);
         mc.field_1690.field_1913.method_23481(false);
         mc.field_1690.field_1849.method_23481(false);
         this.walking = false;
         this.walkStartPos = null;
      } else {
         if (!mc.field_1687.method_8320(target).method_26215()) {
            mc.field_1690.field_1881.method_23481(true);
         } else {
            mc.field_1690.field_1894.method_23481(true);
         }

      }
   }

   private void spin() {
      this.yawTarget = (double)(mc.field_1724.method_36454() + 360.0F);
      this.rotating = true;
   }

   private void randomRotation() {
      this.yawTarget = (double)mc.field_1724.method_36454() + (Math.random() * (double)360.0F - (double)180.0F);
      this.rotating = true;
   }

   private void breakBlockUnder() {
      class_2338 pos = mc.field_1724.method_24515().method_10074();
      class_2680 block = mc.field_1687.method_8320(pos);
      if (block.method_26215()) {
         mc.field_1690.field_1886.method_23481(false);
         this.breakingBlockPos = null;
      } else {
         if (!pos.equals(this.breakingBlockPos)) {
            this.breakingBlockPos = pos;
         }

         mc.field_1690.field_1886.method_23481(true);
      }
   }
}

