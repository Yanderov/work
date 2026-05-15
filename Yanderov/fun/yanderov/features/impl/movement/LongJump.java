package fun.Yanderov.features.impl.movement;

import fun.Yanderov.events.player.TickEvent;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.features.module.setting.Setting;
import fun.Yanderov.features.module.setting.implement.SelectSetting;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import fun.Yanderov.utils.math.time.StopWatch;
import net.minecraft.class_2338;
import net.minecraft.class_2480;
import net.minecraft.class_2490;
import net.minecraft.class_495;

public class LongJump extends Module {
   private final SelectSetting modeSetting = (new SelectSetting("Ð ÐµÐ¶Ð¸Ð¼", "Ð ÐµÐ¶Ð¸Ð¼ Ð¿Ñ€Ñ‹Ð¶ÐºÐ°")).value("Boat", "Shulker Screen", "Slime Boost", "FunTime Soul Sand").selected("Always");
   private boolean wasInShulkerScreen = false;
   private boolean wasOnSlimeBlock = false;
   private final StopWatch timer = new StopWatch();

   public LongJump() {
      super("LongJump", "Long Jump", ModuleCategory.MOVEMENT);
      this.setup(new Setting[]{this.modeSetting});
   }

   @EventHandler
   private void tickEvent(TickEvent event) {
      if (this.modeSetting.isSelected("Shulker Screen") && mc.field_1755 instanceof class_495) {
         new StopWatch();
         float speed = 0.9F;
         mc.field_1724.method_5762((double)0.0F, (double)speed, (double)0.0F);
      }

      if (this.modeSetting.isSelected("FunTime Soul Sand") && mc.field_1724.method_5799() && !mc.field_1724.method_5869()) {
         mc.field_1724.method_5762((double)0.0F, 0.56, (double)0.0F);
      }

      if (this.modeSetting.isSelected("Boat")) {
         if (mc.field_1755 instanceof class_495) {
            float yaw = (float)Math.toRadians((double)mc.field_1724.method_36454());
            double x = -Math.sin((double)yaw) * (double)1.0F;
            double z = Math.cos((double)yaw) * (double)1.0F;
            mc.field_1724.method_5762((double)0.0F, (double)1.0F, (double)0.0F);
            mc.field_1724.method_23327(mc.field_1724.method_23317(), mc.field_1724.method_23318() + 0.24, mc.field_1724.method_23321());
         }

         if (mc.field_1755 instanceof class_495) {
            this.wasInShulkerScreen = true;
         } else if (this.wasInShulkerScreen && mc.field_1755 == null && this.isNearShulkerBox()) {
            this.wasInShulkerScreen = false;
         }
      }

      if (this.modeSetting.isSelected("Slime Boost")) {
         if (mc.field_1724.method_24828() && this.isOnSlimeBlock()) {
            this.wasOnSlimeBlock = true;
         } else if (this.wasOnSlimeBlock && !mc.field_1724.method_24828() && mc.field_1724.method_18798().method_10214() > (double)0.0F) {
            mc.field_1724.method_5762((double)0.0F, 1.35, (double)0.0F);
            this.wasOnSlimeBlock = false;
         } else if (!this.isOnSlimeBlock()) {
            this.wasOnSlimeBlock = false;
         }
      }

   }

   private boolean isNearShulkerBox() {
      class_2338 playerPos = mc.field_1724.method_24515();

      for(int x = -1; x <= 1; ++x) {
         for(int y = -1; y <= 1; ++y) {
            for(int z = -1; z <= 1; ++z) {
               class_2338 pos = playerPos.method_10069(x, y, z);
               if (mc.field_1687.method_8320(pos).method_26204() instanceof class_2480) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   private boolean isOnSlimeBlock() {
      class_2338 playerPos = mc.field_1724.method_24515();
      class_2338 belowPos = playerPos.method_10074();
      return mc.field_1687.method_8320(belowPos).method_26204() instanceof class_2490;
   }

   public SelectSetting getModeSetting() {
      return this.modeSetting;
   }

   public boolean isWasInShulkerScreen() {
      return this.wasInShulkerScreen;
   }

   public boolean isWasOnSlimeBlock() {
      return this.wasOnSlimeBlock;
   }

   public StopWatch getTimer() {
      return this.timer;
   }
}

