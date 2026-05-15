package dev.client.yanderov.display.screens.clickgui.components.implement.autobuy.items;

import dev.client.yanderov.display.screens.clickgui.components.implement.autobuy.settings.AutoBuyItemSettings;
import net.minecraft.class_1799;

public interface AutoBuyableItem {
   String getDisplayName();

   class_1799 createItemStack();

   int getPrice();

   boolean isEnabled();

   void setEnabled(boolean var1);

   AutoBuyItemSettings getSettings();
}

