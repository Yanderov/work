package fun.Yanderov.features.impl.misc;

import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.features.module.setting.Setting;
import fun.Yanderov.features.module.setting.implement.MultiSelectSetting;

public class NoSlowBreak extends Module {
   private static NoSlowBreak INSTANCE;
   private final MultiSelectSetting when = (new MultiSelectSetting("When", "ÐšÐ¾Ð³Ð´Ð° Ð¿Ñ€Ð¸Ð¼ÐµÐ½ÑÑ‚ÑŒ")).value("MiningFatigue", "OnAir", "Underwater").selected("MiningFatigue", "OnAir");

   public NoSlowBreak() {
      super("NoSlowBreak", ModuleCategory.MISC);
      this.setup(new Setting[]{this.when});
      INSTANCE = this;
   }

   public static boolean miningFatigue() {
      return INSTANCE != null && INSTANCE.isState() && INSTANCE.when.isSelected("MiningFatigue");
   }

   public static boolean onAir() {
      return INSTANCE != null && INSTANCE.isState() && INSTANCE.when.isSelected("OnAir");
   }

   public static boolean water() {
      return INSTANCE != null && INSTANCE.isState() && INSTANCE.when.isSelected("Underwater");
   }
}

