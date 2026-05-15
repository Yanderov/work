package fun.Yanderov.display.screens.clickgui.components.implement.autobuy.originalitems;

import fun.Yanderov.display.screens.clickgui.components.implement.autobuy.items.AutoBuyableItem;
import fun.Yanderov.display.screens.clickgui.components.implement.autobuy.items.list.DonatorProvider;
import fun.Yanderov.display.screens.clickgui.components.implement.autobuy.items.list.MiscProvider;
import fun.Yanderov.display.screens.clickgui.components.implement.autobuy.items.list.PotionProvider;
import fun.Yanderov.display.screens.clickgui.components.implement.autobuy.items.list.SphereProvider;
import fun.Yanderov.display.screens.clickgui.components.implement.autobuy.items.list.TalismanProvider;
import fun.Yanderov.display.screens.clickgui.components.implement.autobuy.settings.AutoBuySettingsManager;
import fun.Yanderov.display.screens.clickgui.components.implement.autobuy.util.krushprovider.KrushProvider;
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

