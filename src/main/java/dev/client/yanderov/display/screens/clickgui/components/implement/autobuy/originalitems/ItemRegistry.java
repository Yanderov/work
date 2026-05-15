package dev.client.yanderov.display.screens.clickgui.components.implement.autobuy.originalitems;

import dev.client.yanderov.display.screens.clickgui.components.implement.autobuy.items.AutoBuyableItem;
import dev.client.yanderov.display.screens.clickgui.components.implement.autobuy.items.list.DonatorProvider;
import dev.client.yanderov.display.screens.clickgui.components.implement.autobuy.items.list.MiscProvider;
import dev.client.yanderov.display.screens.clickgui.components.implement.autobuy.items.list.PotionProvider;
import dev.client.yanderov.display.screens.clickgui.components.implement.autobuy.items.list.SphereProvider;
import dev.client.yanderov.display.screens.clickgui.components.implement.autobuy.items.list.TalismanProvider;
import dev.client.yanderov.display.screens.clickgui.components.implement.autobuy.settings.AutoBuySettingsManager;
import dev.client.yanderov.display.screens.clickgui.components.implement.autobuy.util.krushprovider.KrushProvider;
import java.util.ArrayList;
import java.util.List;

public class ItemRegistry {
   private static List allItems = null;
   private static boolean settingsLoaded = false;

   public static void ensureSettingsLoaded() {
      if (!settingsLoaded) {
         settingsLoaded = true;
      }

   }

   public static List getAllItems() {
      if (allItems == null) {
         allItems = new ArrayList();
         allItems.addAll(getKrush());
         allItems.addAll(getTalismans());
         allItems.addAll(getSpheres());
         allItems.addAll(getMisc());
         allItems.addAll(getDonator());
         allItems.addAll(getPotions());

         for(AutoBuyableItem item : allItems) {
            AutoBuySettingsManager.getInstance().loadSettings(item.getDisplayName(), item.getSettings());
         }
      }

      return allItems;
   }

   public static void reloadSettings() {
      if (allItems != null) {
         for(AutoBuyableItem item : allItems) {
            AutoBuySettingsManager.getInstance().loadSettings(item.getDisplayName(), item.getSettings());
         }
      }

   }

   public static List getKrush() {
      return KrushProvider.getKrush();
   }

   public static List getTalismans() {
      return TalismanProvider.getTalismans();
   }

   public static List getSpheres() {
      return SphereProvider.getSpheres();
   }

   public static List getMisc() {
      return MiscProvider.getMisc();
   }

   public static List getDonator() {
      return DonatorProvider.getDonator();
   }

   public static List getPotions() {
      return PotionProvider.getPotions();
   }
}

