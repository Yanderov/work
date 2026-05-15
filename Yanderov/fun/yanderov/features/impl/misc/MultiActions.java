package fun.Yanderov.features.impl.misc;

import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.features.module.setting.Setting;
import fun.Yanderov.features.module.setting.implement.MultiSelectSetting;
import fun.Yanderov.utils.display.interfaces.QuickImports;

public class MultiActions extends Module implements QuickImports {
   private static MultiActions INSTANCE;
   private final MultiSelectSetting actions = (new MultiSelectSetting("Actions", "Ð Ð°Ð·Ñ€ÐµÑˆÐµÐ½Ð½Ñ‹Ðµ Ð¾Ð´Ð½Ð¾Ð²Ñ€ÐµÐ¼ÐµÐ½Ð½Ñ‹Ðµ Ð´ÐµÐ¹ÑÑ‚Ð²Ð¸Ñ")).value("PlacingWhileBreaking", "AttackingWhileUsing", "BreakingWhileUsing");

   public MultiActions() {
      super("MultiActions", ModuleCategory.MISC);
      this.setup(new Setting[]{this.actions});
      INSTANCE = this;
   }

   public static boolean mayPlaceWhileBreaking() {
      return INSTANCE != null && INSTANCE.isState() && INSTANCE.actions.isSelected("PlacingWhileBreaking");
   }

   public static boolean mayAttackWhileUsing() {
      return INSTANCE != null && INSTANCE.isState() && INSTANCE.actions.isSelected("AttackingWhileUsing");
   }

   public static boolean mayBreakWhileUsing() {
      return INSTANCE != null && INSTANCE.isState() && INSTANCE.actions.isSelected("BreakingWhileUsing");
   }
}

