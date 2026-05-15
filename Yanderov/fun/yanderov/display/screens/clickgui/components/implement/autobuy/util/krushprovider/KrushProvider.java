package fun.Yanderov.display.screens.clickgui.components.implement.autobuy.util.krushprovider;

import fun.Yanderov.display.screens.clickgui.components.implement.autobuy.items.AutoBuyableItem;
import fun.Yanderov.display.screens.clickgui.components.implement.autobuy.items.defaultsetpricec.Defaultpricec;
import fun.Yanderov.display.screens.clickgui.components.implement.autobuy.items.list.KrushItems;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_1802;

public class KrushProvider {
   public static List getKrush() {
      List<AutoBuyableItem> krush = new ArrayList();
      krush.add(new KrushItem("Ð¨Ð»ÐµÐ¼ ÐšÑ€ÑƒÑˆÐ¸Ñ‚ÐµÐ»Ñ", class_1802.field_22027, KrushItems.getHelmet(), Defaultpricec.getPrice("Ð¨Ð»ÐµÐ¼ ÐºÑ€ÑƒÑˆÐ¸Ñ‚ÐµÐ»Ñ")));
      krush.add(new KrushItem("ÐÐ°Ð³Ñ€ÑƒÐ´Ð½Ð¸Ðº ÐšÑ€ÑƒÑˆÐ¸Ñ‚ÐµÐ»Ñ", class_1802.field_22028, KrushItems.getChestplate(), Defaultpricec.getPrice("ÐÐ°Ð³Ñ€ÑƒÐ´Ð½Ð¸Ðº ÐºÑ€ÑƒÑˆÐ¸Ñ‚ÐµÐ»Ñ")));
      krush.add(new KrushItem("ÐŸÐ¾Ð½Ð¾Ð¶Ð¸ ÐšÑ€ÑƒÑˆÐ¸Ñ‚ÐµÐ»Ñ", class_1802.field_22029, KrushItems.getLeggings(), Defaultpricec.getPrice("ÐŸÐ¾Ð½Ð¾Ð¶Ð¸ ÐºÑ€ÑƒÑˆÐ¸Ñ‚ÐµÐ»Ñ")));
      krush.add(new KrushItem("Ð‘Ð¾Ñ‚Ð¸Ð½ÐºÐ¸ ÐšÑ€ÑƒÑˆÐ¸Ñ‚ÐµÐ»Ñ", class_1802.field_22030, KrushItems.getBoots(), Defaultpricec.getPrice("Ð‘Ð¾Ñ‚Ð¸Ð½ÐºÐ¸ ÐºÑ€ÑƒÑˆÐ¸Ñ‚ÐµÐ»Ñ")));
      krush.add(new KrushItem("ÐœÐµÑ‡ ÐšÑ€ÑƒÑˆÐ¸Ñ‚ÐµÐ»Ñ", class_1802.field_22022, KrushItems.getSword(), Defaultpricec.getPrice("ÐœÐµÑ‡ ÐºÑ€ÑƒÑˆÐ¸Ñ‚ÐµÐ»Ñ")));
      krush.add(new KrushItem("ÐšÐ¸Ñ€ÐºÐ° ÐšÑ€ÑƒÑˆÐ¸Ñ‚ÐµÐ»Ñ", class_1802.field_22024, KrushItems.getPickaxe(), Defaultpricec.getPrice("ÐšÐ¸Ñ€ÐºÐ° ÐºÑ€ÑƒÑˆÐ¸Ñ‚ÐµÐ»Ñ")));
      krush.add(new KrushItem("ÐÑ€Ð±Ð°Ð»ÐµÑ‚ ÐšÑ€ÑƒÑˆÐ¸Ñ‚ÐµÐ»Ñ", class_1802.field_8399, KrushItems.getCrossbow(), Defaultpricec.getPrice("ÐÑ€Ð±Ð°Ð»ÐµÑ‚ ÐºÑ€ÑƒÑˆÐ¸Ñ‚ÐµÐ»Ñ")));
      krush.add(new KrushItem("Ð¢Ñ€ÐµÐ·ÑƒÐ±ÐµÑ† ÐšÑ€ÑƒÑˆÐ¸Ñ‚ÐµÐ»Ñ", class_1802.field_8547, KrushItems.getTrident(), Defaultpricec.getPrice("Ð¢Ñ€ÐµÐ·ÑƒÐ±ÐµÑ† ÐºÑ€ÑƒÑˆÐ¸Ñ‚ÐµÐ»Ñ")));
      krush.add(new KrushItem("Ð‘ÑƒÐ»Ð°Ð²Ð° ÐšÑ€ÑƒÑˆÐ¸Ñ‚ÐµÐ»Ñ", class_1802.field_49814, KrushItems.getMace(), Defaultpricec.getPrice("Ð‘ÑƒÐ»Ð°Ð²Ð° ÐºÑ€ÑƒÑˆÐ¸Ñ‚ÐµÐ»Ñ")));
      return krush;
   }
}

