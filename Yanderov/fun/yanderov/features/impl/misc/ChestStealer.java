package fun.Yanderov.features.impl.misc;

import fun.Yanderov.events.player.TickEvent;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.features.module.setting.Setting;
import fun.Yanderov.features.module.setting.implement.MultiSelectSetting;
import fun.Yanderov.features.module.setting.implement.SelectSetting;
import fun.Yanderov.features.module.setting.implement.SliderSettings;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import fun.Yanderov.utils.interactions.inv.InventoryTask;
import fun.Yanderov.utils.math.time.StopWatch;
import net.minecraft.class_1703;
import net.minecraft.class_1707;
import net.minecraft.class_1713;
import net.minecraft.class_1792;
import net.minecraft.class_1802;
import net.minecraft.class_437;
import net.minecraft.class_476;

public class ChestStealer extends Module {
   private final StopWatch stopWatch = new StopWatch();
   private final SelectSetting modeSetting = (new SelectSetting("Ð¢Ð¸Ð¿", "Ð’Ñ‹Ð±Ð¸Ñ€Ð°ÐµÑ‚ Ñ‚Ð¸Ð¿ ÑÑ‚Ð¸Ð»Ð°")).value("FunTime", "WhiteList", "Default").selected("FunTime");
   private final SliderSettings delaySetting = (new SliderSettings("Ð—Ð°Ð´ÐµÑ€Ð¶ÐºÐ°", "Ð—Ð°Ð´ÐµÑ€Ð¶ÐºÐ° Ð¼ÐµÐ¶Ð´Ñƒ ÐºÐ»Ð¸ÐºÐ°Ð¼Ð¸ Ð¿Ð¾ ÑÐ»Ð¾Ñ‚Ñƒ")).setValue(100.0F).range(0, 1000).visible(() -> this.modeSetting.isSelected("WhiteList") || this.modeSetting.isSelected("Default"));
   private final MultiSelectSetting itemSettings = (new MultiSelectSetting("ÐŸÑ€ÐµÐ´Ð¼ÐµÑ‚Ñ‹", "Ð’Ñ‹Ð±ÐµÑ€Ð¸Ñ‚Ðµ Ð¿Ñ€ÐµÐ´Ð¼ÐµÑ‚Ñ‹, ÐºÐ¾Ñ‚Ð¾Ñ€Ñ‹Ðµ Ð²Ð¾Ñ€ Ð±ÑƒÐ´ÐµÑ‚ Ð¿Ð¾Ð´Ð±Ð¸Ñ€Ð°Ñ‚ÑŒ")).value("Player Head", "Totem Of Undying", "Elytra", "Netherite Sword", "Netherite Helmet", "Netherite ChestPlate", "Netherite Leggings", "Netherite Boots", "Netherite Ingot", "Netherite Scrap").visible(() -> this.modeSetting.isSelected("WhiteList"));

   public ChestStealer() {
      super("ChestStealer", "Chest Stealer", ModuleCategory.MISC);
      this.setup(new Setting[]{this.modeSetting, this.delaySetting, this.itemSettings});
   }

   @EventHandler
   public void onTick(TickEvent e) {
      switch (this.modeSetting.getSelected()) {
         case "FunTime":
            class_437 var7 = mc.field_1755;
            if (var7 instanceof class_476 sh) {
               if (sh.method_25440().getString().toLowerCase().contains("Ð¼Ð¸ÑÑ‚Ð¸Ñ‡ÐµÑÐºÐ¸Ð¹") && !mc.field_1724.method_7357().method_7904(class_1802.field_8054.method_7854())) {
                  ((class_1707)sh.method_17577()).field_7761.stream().filter((s) -> s.method_7681() && !s.field_7871.equals(mc.field_1724.method_31548()) && this.stopWatch.every((double)150.0F)).forEach((s) -> InventoryTask.clickSlot(s, 0, class_1713.field_7794, true));
               }
            }
            break;
         case "WhiteList":
         case "Default":
            class_1703 var5 = mc.field_1724.field_7512;
            if (var5 instanceof class_1707 sh) {
               sh.field_7761.forEach((s) -> {
                  if (s.method_7681() && !s.field_7871.equals(mc.field_1724.method_31548()) && (this.modeSetting.isSelected("Default") || this.whiteList(s.method_7677().method_7909())) && this.stopWatch.every((double)this.delaySetting.getValue())) {
                     InventoryTask.clickSlot(s, 0, class_1713.field_7794, true);
                  }

               });
            }
      }

   }

   private boolean whiteList(class_1792 item) {
      return this.itemSettings.getSelected().toString().toLowerCase().contains(item.toString().toLowerCase().replace("_", ""));
   }
}

