package dev.client.yanderov.display.screens.clickgui.components.implement.autobuy.util.krushprovider;

import dev.client.yanderov.display.screens.clickgui.components.implement.autobuy.items.AutoBuyableItem;
import dev.client.yanderov.display.screens.clickgui.components.implement.autobuy.settings.AutoBuyItemSettings;
import dev.client.yanderov.display.screens.clickgui.components.implement.autobuy.settings.AutoBuySettingsManager;
import net.minecraft.class_1792;
import net.minecraft.class_1799;

public class KrushItem implements AutoBuyableItem {
   private final String displayName;
   private final class_1799 reference;
   private final class_1792 material;
   private final int price;
   private final AutoBuyItemSettings settings;
   private boolean enabled;

   public KrushItem(String displayName, class_1792 material, class_1799 reference, int price) {
      this.displayName = displayName;
      this.material = material;
      this.reference = reference;
      this.price = price;
      this.enabled = true;
      this.settings = new AutoBuyItemSettings(price, material, displayName);
      AutoBuySettingsManager.getInstance().loadSettings(displayName, this.settings);
   }

   public String getDisplayName() {
      return this.displayName;
   }

   public class_1799 createItemStack() {
      return this.reference.method_7972();
   }

   public int getPrice() {
      return this.price;
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public void setEnabled(boolean enabled) {
      this.enabled = enabled;
   }

   public AutoBuyItemSettings getSettings() {
      return this.settings;
   }
}

