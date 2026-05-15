package fun.Yanderov.features.impl.combat;

import fun.Yanderov.events.player.AttackEvent;
import fun.Yanderov.events.player.TickEvent;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.features.module.setting.Setting;
import fun.Yanderov.features.module.setting.implement.BooleanSetting;
import fun.Yanderov.features.module.setting.implement.SliderSettings;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import fun.Yanderov.utils.display.interfaces.QuickImports;
import fun.Yanderov.utils.interactions.interact.PlayerInteractionHelper;
import net.minecraft.class_1297;
import net.minecraft.class_1304;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1839;
import net.minecraft.class_2561;

public class ElytraMace extends Module implements QuickImports {
   private final BooleanSetting autoElytra = (new BooleanSetting("AutoElytra", "ÐÐ²Ñ‚Ð¾Ð¼Ð°Ñ‚Ð¸Ñ‡ÐµÑÐºÐ¸ Ð¿Ñ€Ð¾Ð¶Ð¸Ð¼Ð°Ñ‚ÑŒ ÑÐ»Ð¸Ñ‚Ñ€Ñƒ Ð¿Ñ€Ð¸ ÑƒÐ´Ð°Ñ€Ðµ")).setValue(true);
   private final SliderSettings minFallDistance = (new SliderSettings("MinFallDistance", "ÐœÐ¸Ð½. Ð´Ð¸ÑÑ‚Ð°Ð½Ñ†Ð¸Ñ Ð¿Ð°Ð´ÐµÐ½Ð¸Ñ")).setValue(1.5F).range(0.1F, 10.0F);
   private final BooleanSetting onlyWithMace = (new BooleanSetting("OnlyWithMace", "Ð¢Ð¾Ð»ÑŒÐºÐ¾ Ñ Ð±ÑƒÐ»Ð°Ð²Ð¾Ð¹/Ð¾Ñ€ÑƒÐ¶Ð¸ÐµÐ¼")).setValue(true);
   private final BooleanSetting debug = (new BooleanSetting("Debug", "Ð¡Ð¾Ð¾Ð±Ñ‰ÐµÐ½Ð¸Ñ Ð² Ñ‡Ð°Ñ‚")).setValue(false);
   private boolean smash = false;
   private boolean wasFallFlying = false;

   public ElytraMace() {
      super("ElytraMace", ModuleCategory.COMBAT);
      this.setup(new Setting[]{this.autoElytra, this.minFallDistance, this.onlyWithMace, this.debug});
   }

   public void activate() {
      this.smash = false;
      this.wasFallFlying = false;
   }

   public void deactivate() {
      this.smash = false;
      this.wasFallFlying = false;
   }

   @EventHandler
   public void onAttack(AttackEvent e) {
      if (this.isState()) {
         if (mc.field_1724 != null && mc.field_1687 != null) {
            class_1297 ent = e.getEntity();
            if (ent instanceof class_1657) {
               class_1799 mainHand = mc.field_1724.method_6047();
               if (!this.onlyWithMace.isValue() || this.isMaceItem(mainHand)) {
                  if (!this.hasElytra()) {
                     if (this.debug.isValue()) {
                        mc.field_1724.method_7353(class_2561.method_30163("Â§c[ElytraMace] Â§fÐÐµÑ‚ ÑÐ»Ð¸Ñ‚Ñ€Ñ‹ Ð² ÑÐ»Ð¾Ñ‚Ðµ Ð³Ñ€ÑƒÐ´Ð¸"), false);
                     }

                  } else if (mc.field_1724.field_6017 < this.minFallDistance.getValue()) {
                     if (this.debug.isValue()) {
                        mc.field_1724.method_7353(class_2561.method_30163("Â§c[ElytraMace] Â§fÐÐµÐ´Ð¾ÑÑ‚Ð°Ñ‚Ð¾Ñ‡Ð½Ð°Ñ Ð´Ð¸ÑÑ‚Ð°Ð½Ñ†Ð¸Ñ Ð¿Ð°Ð´ÐµÐ½Ð¸Ñ: " + mc.field_1724.field_6017), false);
                     }

                  } else {
                     if (mc.field_1724.method_6128()) {
                        this.wasFallFlying = true;
                     }

                     if (this.autoElytra.isValue()) {
                        this.smash = true;
                        PlayerInteractionHelper.startFallFlying();
                        if (this.debug.isValue()) {
                           mc.field_1724.method_7353(class_2561.method_30163("Â§a[ElytraMace] Â§fÐÐºÑ‚Ð¸Ð²Ð¸Ñ€Ð¾Ð²Ð°Ð½Ð° ÑÐ»Ð¸Ñ‚Ñ€Ð° Ð´Ð»Ñ Ð°Ñ‚Ð°ÐºÐ¸"), false);
                        }
                     }

                  }
               }
            }
         }
      }
   }

   @EventHandler
   public void onTick(TickEvent e) {
      if (this.isState()) {
         if (mc.field_1724 != null && mc.field_1687 != null) {
            if (this.smash) {
               this.smash = false;
               if (!this.wasFallFlying) {
                  PlayerInteractionHelper.startFallFlying();
                  if (this.debug.isValue()) {
                     mc.field_1724.method_7353(class_2561.method_30163("Â§a[ElytraMace] Â§fÐŸÐ¾Ð²Ñ‚Ð¾Ñ€Ð½Ñ‹Ð¹ Ð·Ð°Ð¿ÑƒÑÐº ÑÐ»Ð¸Ñ‚Ñ€Ñ‹ Ð¿Ð¾ÑÐ»Ðµ Ð°Ñ‚Ð°ÐºÐ¸"), false);
                  }
               }

               this.wasFallFlying = false;
            }
         }
      }
   }

   private boolean isMaceItem(class_1799 stack) {
      if (stack != null && !stack.method_7960()) {
         String itemName = stack.method_7909().toString().toLowerCase();
         if (!itemName.contains("mace") && !itemName.contains("Ð±ÑƒÐ»Ð°Ð²Ð°") && !itemName.contains("Ð±ÑƒÐ»Ð°Ð²")) {
            class_1839 action = stack.method_7909().method_7853(stack);
            return action == class_1839.field_8949;
         } else {
            return true;
         }
      } else {
         return false;
      }
   }

   private boolean hasElytra() {
      return mc.field_1724 == null ? false : mc.field_1724.method_6118(class_1304.field_6174).method_31574(class_1802.field_8833);
   }
}

