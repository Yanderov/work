package fun.Yanderov.features.impl.misc;

import fun.Yanderov.events.player.TickEvent;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.features.module.setting.Setting;
import fun.Yanderov.features.module.setting.implement.MultiSelectSetting;
import fun.Yanderov.features.module.setting.implement.SliderSettings;
import fun.Yanderov.mixins.client.IMinecraftClient;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import fun.Yanderov.utils.display.interfaces.QuickImports;
import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.class_1747;
import net.minecraft.class_1792;
import net.minecraft.class_9463;

public class FastPlace extends Module implements QuickImports {
   private final SliderSettings cooldownMin = (new SliderSettings("CooldownMin", "ÐœÐ¸Ð½. ÐºÐ´, Ñ‚Ð¸ÐºÐ¸")).setValue(0.0F).range(0.0F, 4.0F);
   private final SliderSettings cooldownMax = (new SliderSettings("CooldownMax", "ÐœÐ°ÐºÑ. ÐºÐ´, Ñ‚Ð¸ÐºÐ¸")).setValue(0.0F).range(0.0F, 4.0F);
   private final MultiSelectSetting applyTo = (new MultiSelectSetting("ApplyTo", "Ðš Ñ‡ÐµÐ¼Ñƒ Ð¿Ñ€Ð¸Ð¼ÐµÐ½ÑÑ‚ÑŒ")).value("Projectiles", "Blocks").selected("Projectiles", "Blocks");
   private final SliderSettings startDelayMs = (new SliderSettings("StartDelay", "Ð—Ð°Ð´ÐµÑ€Ð¶ÐºÐ° Ð¿ÐµÑ€ÐµÐ´ ÑƒÑÐºÐ¾Ñ€ÐµÐ½Ð¸ÐµÐ¼ (Ð¼Ñ)")).setValue(0.0F).range(0.0F, 1000.0F);
   private boolean prevUsePressed = false;
   private long lastUsePressAt = 0L;

   public FastPlace() {
      super("FastPlace", ModuleCategory.MISC);
      this.setup(new Setting[]{this.cooldownMin, this.cooldownMax, this.applyTo, this.startDelayMs});
   }

   @EventHandler
   public void onTick(TickEvent e) {
      if (e.getType() == 0) {
         if (mc != null && mc.field_1724 != null) {
            boolean pressed;
            try {
               pressed = mc.field_1690 != null && mc.field_1690.field_1904.method_1434();
            } catch (Throwable var16) {
               pressed = false;
            }

            long now = System.currentTimeMillis();
            if (pressed && !this.prevUsePressed) {
               this.lastUsePressAt = now;
            }

            if (pressed) {
               class_1792 main = null;
               class_1792 off = null;

               try {
                  main = mc.field_1724.method_6047().method_7909();
               } catch (Throwable var15) {
               }

               try {
                  off = mc.field_1724.method_6079().method_7909();
               } catch (Throwable var14) {
               }

               boolean applies = false;
               if (this.applyTo.isSelected("Projectiles")) {
                  applies |= main instanceof class_9463 || off instanceof class_9463;
               }

               if (this.applyTo.isSelected("Blocks")) {
                  applies |= main instanceof class_1747 || off instanceof class_1747;
               }

               if (applies) {
                  float needMs = this.startDelayMs.getValue();
                  if (needMs <= 0.0F || now - this.lastUsePressAt >= (long)needMs) {
                     int min = Math.round(Math.min(this.cooldownMin.getValue(), this.cooldownMax.getValue()));
                     int max = Math.round(Math.max(this.cooldownMin.getValue(), this.cooldownMax.getValue()));
                     int cd = ThreadLocalRandom.current().nextInt(max - min + 1) + min;

                     try {
                        ((IMinecraftClient)mc).setUseCooldown(cd);
                     } catch (Throwable var13) {
                     }
                  }
               }
            }

            this.prevUsePressed = pressed;
         }
      }
   }
}

