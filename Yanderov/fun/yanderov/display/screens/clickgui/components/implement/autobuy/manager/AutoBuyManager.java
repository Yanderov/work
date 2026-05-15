package fun.Yanderov.display.screens.clickgui.components.implement.autobuy.manager;

import fun.Yanderov.display.screens.clickgui.components.implement.autobuy.items.AutoBuyableItem;
import fun.Yanderov.display.screens.clickgui.components.implement.autobuy.originalitems.ItemRegistry;
import java.util.List;

public class AutoBuyManager {
   private static AutoBuyManager instance;
   private boolean enabled = false;

   private AutoBuyManager() {
   }

   public static AutoBuyManager getInstance() {
      if (instance == null) {
         instance = new AutoBuyManager();
      }

      return instance;
   }

   public void setEnabled(boolean enabled) {
      this.enabled = enabled;
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public List getAllItems() {
      return ItemRegistry.getAllItems();
   }

   public void toggleItem(AutoBuyableItem item) {
      item.setEnabled(!item.isEnabled());
   }
}

