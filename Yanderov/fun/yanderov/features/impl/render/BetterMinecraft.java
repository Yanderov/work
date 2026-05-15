package fun.Yanderov.features.impl.render;

import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.features.module.setting.Setting;
import fun.Yanderov.features.module.setting.implement.BooleanSetting;
import fun.Yanderov.utils.client.Instance;

public class BetterMinecraft extends Module {
   private final BooleanSetting betterButton = (new BooleanSetting("ÐšÐ°ÑÑ‚Ð¾Ð¼Ð½Ñ‹Ðµ ÐºÐ½Ð¾Ð¿ÐºÐ¸", "ÑÐ·Ð°Ð¸Ð¿Ð°Ð»ÑÑÑÑ‚Ð¾Ð¿Ð°ÑÑ‚Ð¸Ñ‚ÑŒÑÐ¿Ð°ÑÐ¸Ñ‚Ðµ")).setValue(true);
   private final BooleanSetting tabVanishButton = (new BooleanSetting("Ð¡Ð¿ÐµÐºÑ‚Ð°Ñ‚Ð¾Ñ€Ñ‹ Ð² Ñ‚Ð°Ð±Ðµ", "ÑÐ·Ð°Ð¸Ð¿Ð°Ð»ÑÑÑÑ‚Ð¾Ð¿Ð°ÑÑ‚Ð¸Ñ‚ÑŒÑÐ¿Ð°ÑÐ¸Ñ‚Ðµ")).setValue(true);

   public static BetterMinecraft getInstance() {
      return (BetterMinecraft)Instance.get(BetterMinecraft.class);
   }

   public BetterMinecraft() {
      super("BetterMinecraft", "Better Minecraft", ModuleCategory.RENDER);
      this.setup(new Setting[]{this.betterButton, this.tabVanishButton});
   }

   public BooleanSetting getBetterButton() {
      return this.betterButton;
   }

   public BooleanSetting getTabVanishButton() {
      return this.tabVanishButton;
   }
}

