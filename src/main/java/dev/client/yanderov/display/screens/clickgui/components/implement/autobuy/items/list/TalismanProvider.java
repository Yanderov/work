package dev.client.yanderov.display.screens.clickgui.components.implement.autobuy.items.list;

import dev.client.yanderov.display.screens.clickgui.components.implement.autobuy.items.AutoBuyableItem;
import dev.client.yanderov.display.screens.clickgui.components.implement.autobuy.items.customitem.CustomItem;
import dev.client.yanderov.display.screens.clickgui.components.implement.autobuy.items.defaultsetpricec.Defaultpricec;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_1802;
import net.minecraft.class_1844;
import net.minecraft.class_2487;
import net.minecraft.class_2561;

public class TalismanProvider {
   public static List getTalismans() {
      List<AutoBuyableItem> talismans = new ArrayList();
      List<class_2561> graniLore = List.of(class_2561.method_43470("Ð¢Ð°Ð»Ð¸ÑÐ¼Ð°Ð½ Ð“Ñ€Ð°Ð½Ð¸ - ÑÑ‚Ð¾"), class_2561.method_43470("Ð±ÐµÐ·Ð³Ñ€Ð°Ð½Ð¸Ñ‡Ð½Ð¾ÑÑ‚ÑŒ ÑÐ¸Ð»Ñ‹"), class_2561.method_43470("Ð¸ Ð´ÑƒÑ…Ð° ÑÐ²Ð¾Ð±Ð¾Ð´Ñ‹"));
      talismans.add(new CustomItem("[â˜…] Ð¢Ð°Ð»Ð¸ÑÐ¼Ð°Ð½ Ð“Ñ€Ð°Ð½Ð¸", (class_2487)null, class_1802.field_8288, Defaultpricec.getPrice("Ð¢Ð°Ð»Ð¸ÑÐ¼Ð°Ð½ Ð“Ñ€Ð°Ð½Ð¸"), (class_1844)null, graniLore));
      List<class_2561> dedalLore = List.of(class_2561.method_43470("Ð¢Ð°Ð»Ð¸ÑÐ¼Ð°Ð½ Ð”ÐµÐ´Ð°Ð»Ð° - ÑÑ‚Ð¾"), class_2561.method_43470("ÑÐ¸Ð»Ð° Ð¸Ð½Ð¶ÐµÐ½ÐµÑ€Ð½Ð¾Ð³Ð¾ Ð´ÑƒÑ…Ð°,"), class_2561.method_43470("Ð²Ð´Ð¾Ñ…Ð½Ð¾Ð²ÐµÐ½Ð¸Ñ Ð¸ Ð¼Ð°ÑÑ‚ÐµÑ€ÑÑ‚Ð²Ð°"));
      talismans.add(new CustomItem("[â˜…] Ð¢Ð°Ð»Ð¸ÑÐ¼Ð°Ð½ Ð”ÐµÐ´Ð°Ð»Ð°", (class_2487)null, class_1802.field_8288, Defaultpricec.getPrice("Ð¢Ð°Ð»Ð¸ÑÐ¼Ð°Ð½ Ð”ÐµÐ´Ð°Ð»Ð°"), (class_1844)null, dedalLore));
      List<class_2561> tritonLore = List.of(class_2561.method_43470("Ð¢Ð°Ð»Ð¸ÑÐ¼Ð°Ð½ Ð¢Ñ€Ð¸Ñ‚Ð¾Ð½Ð° - ÑÑ‚Ð¾"), class_2561.method_43470("Ð½ÐµÐ¸ÑÐºÐ¾Ð½Ñ‡Ð°ÐµÐ¼Ð°Ñ Ð¼Ð¾Ñ‰ÑŒ"), class_2561.method_43470("Ð½Ð°Ð´ Ð¿Ñ€Ð¸Ñ€Ð¾Ð´Ð½Ñ‹Ð¼Ð¸ ÑÑ‚Ð¸Ñ…Ð¸ÑÐ¼Ð¸"));
      talismans.add(new CustomItem("[â˜…] Ð¢Ð°Ð»Ð¸ÑÐ¼Ð°Ð½ Ð¢Ñ€Ð¸Ñ‚Ð¾Ð½Ð°", (class_2487)null, class_1802.field_8288, Defaultpricec.getPrice("Ð¢Ð°Ð»Ð¸ÑÐ¼Ð°Ð½ Ð¢Ñ€Ð¸Ñ‚Ð¾Ð½Ð°"), (class_1844)null, tritonLore));
      List<class_2561> harmonyLore = List.of(class_2561.method_43470("Ð¢Ð°Ð»Ð¸ÑÐ¼Ð°Ð½ Ð“Ð°Ñ€Ð¼Ð¾Ð½Ð¸Ð¸ - ÑÑ‚Ð¾"), class_2561.method_43470("ÑÐ±Ð°Ð»Ð°Ð½ÑÐ¸Ñ€Ð¾Ð²Ð°Ð½Ð½Ñ‹Ðµ ÑÐ¸Ð»Ñ‹"), class_2561.method_43470("Ð·Ð°Ñ‰Ð¸Ñ‚Ñ‹, Ð¼Ð¾Ñ‰Ð¸ Ð¸ Ð·Ð´Ð¾Ñ€Ð¾Ð²ÑŒÑ"));
      talismans.add(new CustomItem("[â˜…] Ð¢Ð°Ð»Ð¸ÑÐ¼Ð°Ð½ Ð“Ð°Ñ€Ð¼Ð¾Ð½Ð¸Ð¸", (class_2487)null, class_1802.field_8288, Defaultpricec.getPrice("Ð¢Ð°Ð»Ð¸ÑÐ¼Ð°Ð½ Ð“Ð°Ñ€Ð¼Ð¾Ð½Ð¸Ð¸"), (class_1844)null, harmonyLore));
      List<class_2561> phoenixLore = List.of(class_2561.method_43470("Ð¢Ð°Ð»Ð¸ÑÐ¼Ð°Ð½ Ð¤ÐµÐ½Ð¸ÐºÑÐ° - ÑÑ‚Ð¾"), class_2561.method_43470("ÑÑ‚Ð¸Ñ…Ð¸Ð¸ ÑÐ¸Ð»Ñ‹ Ð¸ Ð²Ð¾Ð·Ñ€Ð¾Ð¶Ð´ÐµÐ½Ð¸Ñ,"), class_2561.method_43470("ÐºÐ¾Ð²Ð°Ð½Ñ‹Ðµ Ð°Ð½Ð³ÐµÐ»ÑŒÑÐºÐ¸Ð¼ Ð¿Ð»Ð°Ð¼ÐµÐ½ÐµÐ¼"));
      talismans.add(new CustomItem("[â˜…] Ð¢Ð°Ð»Ð¸ÑÐ¼Ð°Ð½ Ð¤ÐµÐ½Ð¸ÐºÑÐ°", (class_2487)null, class_1802.field_8288, Defaultpricec.getPrice("Ð¢Ð°Ð»Ð¸ÑÐ¼Ð°Ð½ Ð¤ÐµÐ½Ð¸ÐºÑÐ°"), (class_1844)null, phoenixLore));
      List<class_2561> echidnaLore = List.of(class_2561.method_43470("Ð¢Ð°Ð»Ð¸ÑÐ¼Ð°Ð½ Ð•Ñ…Ð¸Ð´Ð½Ñ‹ - ÑÑ‚Ð¾"), class_2561.method_43470("ÑÐ¼ÐµÑ€Ñ‚Ð¾Ð½Ð¾ÑÐ½Ñ‹Ðµ Ð·Ð¼ÐµÐ¸Ð½Ñ‹Ðµ Ñ€Ñ‹Ð²ÐºÐ¸,"), class_2561.method_43470("Ð¾ÑÐ»Ð°Ð±Ð»ÐµÐ½Ð½Ñ‹Ðµ ÑÐ´Ð¾Ð²Ð¸Ñ‚Ð¾Ð¹ Ð°ÑƒÑ€Ð¾Ð¹"));
      talismans.add(new CustomItem("[â˜…] Ð¢Ð°Ð»Ð¸ÑÐ¼Ð°Ð½ Ð•Ñ…Ð¸Ð´Ð½Ñ‹", (class_2487)null, class_1802.field_8288, Defaultpricec.getPrice("Ð¢Ð°Ð»Ð¸ÑÐ¼Ð°Ð½ Ð•Ñ…Ð¸Ð´Ð½Ñ‹"), (class_1844)null, echidnaLore));
      List<class_2561> punisherLore = List.of(class_2561.method_43470("Ð¢Ð°Ð»Ð¸ÑÐ¼Ð°Ð½ ÐšÐ°Ñ€Ð°Ñ‚ÐµÐ»Ñ - ÑÑ‚Ð¾"), class_2561.method_43470("Ð¼Ð¾Ñ‰ÑŒ Ð½ÐµÐ±ÐµÑÐ½Ð¾Ð³Ð¾ Ð¿Ð°Ð»Ð°Ñ‡Ð°,"), class_2561.method_43470("ÑÐ¾ÐºÑ€ÑƒÑˆÐ°ÑŽÑ‰ÐµÐ³Ð¾ Ð½ÐµÐ´Ñ€ÑƒÐ³Ð¾Ð²"));
      talismans.add(new CustomItem("[â˜…] Ð¢Ð°Ð»Ð¸ÑÐ¼Ð°Ð½ ÐšÐ°Ñ€Ð°Ñ‚ÐµÐ»Ñ", (class_2487)null, class_1802.field_8288, Defaultpricec.getPrice("Ð¢Ð°Ð»Ð¸ÑÐ¼Ð°Ð½ ÐšÐ°Ñ€Ð°Ñ‚ÐµÐ»Ñ"), (class_1844)null, punisherLore));
      List<class_2561> krushitelLore = List.of(class_2561.method_43470("Ð¢Ð°Ð»Ð¸ÑÐ¼Ð°Ð½ ÐšÑ€ÑƒÑˆÐ¸Ñ‚ÐµÐ»Ñ - ÑÑ‚Ð¾"), class_2561.method_43470("Ð»ÐµÐ³ÐµÐ½Ð´Ð°Ñ€Ð½Ñ‹Ð¹ Ñ‚Ð°Ð»Ð¸ÑÐ¼Ð°Ð½"), class_2561.method_43470("Ð½ÐµÑÐ¾ÐºÑ€ÑƒÑˆÐ¸Ð¼Ñ‹Ñ… ÐºÑ€ÑƒÑˆÐ¸Ñ‚ÐµÐ»ÐµÐ¹"));
      talismans.add(new CustomItem("[â˜…] Ð¢Ð°Ð»Ð¸ÑÐ¼Ð°Ð½ ÐšÑ€ÑƒÑˆÐ¸Ñ‚ÐµÐ»Ñ", (class_2487)null, class_1802.field_8288, Defaultpricec.getPrice("Ð¢Ð°Ð»Ð¸ÑÐ¼Ð°Ð½ ÐšÑ€ÑƒÑˆÐ¸Ñ‚ÐµÐ»Ñ"), (class_1844)null, krushitelLore));
      return talismans;
   }
}

