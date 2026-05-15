package dev.client.yanderov.display.screens.clickgui.components.implement.autobuy.items.list;

import dev.client.yanderov.display.screens.clickgui.components.implement.autobuy.items.AutoBuyableItem;
import dev.client.yanderov.display.screens.clickgui.components.implement.autobuy.items.customitem.CustomItem;
import dev.client.yanderov.display.screens.clickgui.components.implement.autobuy.items.defaultsetpricec.Defaultpricec;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import net.minecraft.class_1802;
import net.minecraft.class_1844;
import net.minecraft.class_2487;
import net.minecraft.class_2561;

public class PotionProvider {
   public static List getPotions() {
      List<AutoBuyableItem> potions = new ArrayList();
      List<class_2561> mochaFlashLore = List.of(class_2561.method_43470("Ð˜ÑÐ¿ÐµÐ¹ ÑÐ¾Ðº Ð¤Ð»ÐµÑˆÐ°,"), class_2561.method_43470("Ð´Ð°Ð±Ñ‹ Ð¿Ð¾Ð»ÑƒÑ‡Ð¸Ñ‚ÑŒ ÐµÐ³Ð¾"), class_2561.method_43470("ÑÐ¸Ð»Ñƒ Ð¸ ÑÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ"));
      potions.add(new CustomItem("[â˜…] ÐœÐ¾Ñ‡Ð° Ð¤Ð»ÐµÑˆÐ°", (class_2487)null, class_1802.field_8436, Defaultpricec.getPrice("ÐœÐ¾Ñ‡Ð° Ð¤Ð»ÐµÑˆÐ°"), new class_1844(Optional.empty(), Optional.of(6092799), List.of(), Optional.empty()), mochaFlashLore));
      List<class_2561> medicLore = List.of(class_2561.method_43470("Ð­Ð»Ð¸ÐºÑÐ¸Ñ€ Ð¾Ñ‚ ÐœÐµÐ´Ð¸ÐºÐ°"), class_2561.method_43470("Ð¿Ð¾Ð¼Ð¾Ð³Ð°ÐµÑ‚ Ð²Ñ‹ÑÑ‚Ð¾ÑÑ‚ÑŒ"), class_2561.method_43470("Ð´Ð°Ð¶Ðµ Ð² ÑÐ¼ÐµÑ€Ñ‚ÐµÐ»ÑŒÐ½Ð¾Ð¼ Ð±Ð¾ÑŽ"));
      potions.add(new CustomItem("[â˜…] Ð—ÐµÐ»ÑŒÐµ ÐœÐµÐ´Ð¸ÐºÐ°", (class_2487)null, class_1802.field_8436, Defaultpricec.getPrice("Ð—ÐµÐ»ÑŒÐµ ÐœÐµÐ´Ð¸ÐºÐ°"), new class_1844(Optional.empty(), Optional.of(16711902), List.of(), Optional.empty()), medicLore));
      List<class_2561> agentLore = List.of(class_2561.method_43470("Ð›Ð¾Ð²ÐºÐ¾ÑÑ‚ÑŒ Ð¸ ÑÐºÑ€Ñ‹Ñ‚Ð¾ÑÑ‚ÑŒ"), class_2561.method_43470("Ñ‚Ð°Ð¹Ð½Ñ‹Ñ… ÐÐ³ÐµÐ½Ñ‚Ð¾Ð²"), class_2561.method_43470("Ñ‚Ð°ÑÑ‚ÑÑ Ð² ÑÑ‚Ð¾Ð¼ Ð½Ð°Ð¿Ð¸Ñ‚ÐºÐµ"));
      potions.add(new CustomItem("[â˜…] Ð—ÐµÐ»ÑŒÐµ ÐÐ³ÐµÐ½Ñ‚Ð°", (class_2487)null, class_1802.field_8436, Defaultpricec.getPrice("Ð—ÐµÐ»ÑŒÐµ ÐÐ³ÐµÐ½Ñ‚Ð°"), new class_1844(Optional.empty(), Optional.of(16775936), List.of(), Optional.empty()), agentLore));
      List<class_2561> winnerLore = List.of(class_2561.method_43470("Ð¥Ñ€Ð°Ð±Ñ€Ð°Ñ Ð´ÑƒÑˆÐ° ÐŸÐ¾Ð±ÐµÐ´Ð¸Ñ‚ÐµÐ»Ñ"), class_2561.method_43470("Ð¸ Ð½ÐµÐ¼Ð½Ð¾Ð³Ð¾ Ð¼Ð°Ð³Ð¸Ð¸"), class_2561.method_43470("Ð¾Ð±Ñ€Ð°Ð·Ð¾Ð²Ð°Ð»Ð¸ ÑÑ‚Ð¾ Ð·ÐµÐ»ÑŒÐµ"));
      potions.add(new CustomItem("[â˜…] Ð—ÐµÐ»ÑŒÐµ ÐŸÐ¾Ð±ÐµÐ´Ð¸Ñ‚ÐµÐ»Ñ", (class_2487)null, class_1802.field_8436, Defaultpricec.getPrice("Ð—ÐµÐ»ÑŒÐµ ÐŸÐ¾Ð±ÐµÐ´Ð¸Ñ‚ÐµÐ»Ñ"), new class_1844(Optional.empty(), Optional.of(65280), List.of(), Optional.empty()), winnerLore));
      List<class_2561> killerLore = List.of(class_2561.method_43470("ÐžÑÑ‚Ð¾Ñ€Ð¾Ð¶Ð½Ð¾! Ð—ÐµÐ»ÑŒÐµ ÐšÐ¸Ð»Ð»ÐµÑ€Ð°"), class_2561.method_43470("Ð²Ñ‹Ð·Ñ‹Ð²Ð°ÐµÑ‚ ÐºÑ€Ð¾Ð²Ð¾Ð¶Ð°Ð´Ð½Ð¾ÑÑ‚ÑŒ"), class_2561.method_43470("Ð¸ Ð¿Ð¾Ð²Ñ‹ÑˆÐ°ÐµÑ‚ Ð²Ñ‹Ð½Ð¾ÑÐ»Ð¸Ð²Ð¾ÑÑ‚ÑŒ!"));
      potions.add(new CustomItem("[â˜…] Ð—ÐµÐ»ÑŒÐµ ÐšÐ¸Ð»Ð»ÐµÑ€Ð°", (class_2487)null, class_1802.field_8436, Defaultpricec.getPrice("Ð—ÐµÐ»ÑŒÐµ ÐšÐ¸Ð»Ð»ÐµÑ€Ð°"), new class_1844(Optional.empty(), Optional.of(16711680), List.of(), Optional.empty()), killerLore));
      List<class_2561> burpLore = List.of(class_2561.method_43470("ÐžÐ¿Ð°ÑÐ½Ð°Ñ Ð¶Ð¸Ð´ÐºÐ¾ÑÑ‚ÑŒ Ð² ÑÐ¾ÑÑƒÐ´Ðµ"), class_2561.method_43470("ÑÐ¾Ð´ÐµÑ€Ð¶Ð¸Ñ‚ ÐžÑ‚Ñ€Ñ‹Ð¶ÐºÑƒ Ð²Ð°ÑÐ¸Ð»Ð¸ÑÐºÐ°"), class_2561.method_43470("Ð¸ Ð´Ñ€ÑƒÐ³Ð¸Ñ… Ð¼ÐµÑ€Ð·ÐºÐ¸Ñ… Ñ‚Ð²Ð°Ñ€ÐµÐ¹"));
      potions.add(new CustomItem("[â˜…] Ð—ÐµÐ»ÑŒÐµ ÐžÑ‚Ñ€Ñ‹Ð¶ÐºÐ¸", (class_2487)null, class_1802.field_8436, Defaultpricec.getPrice("Ð—ÐµÐ»ÑŒÐµ ÐžÑ‚Ñ€Ñ‹Ð¶ÐºÐ¸"), new class_1844(Optional.empty(), Optional.of(16735488), List.of(), Optional.empty()), burpLore));
      List<class_2561> acidLore = List.of(class_2561.method_43470("ÐšÑƒÑ‡Ð° Ð°Ð²Ð°Ð½Ñ‚ÑŽÑ€Ð¸ÑÑ‚Ð¾Ð² Ð¿Ð¾Ð»Ð¾Ð¶Ð¸Ð»Ð¸"), class_2561.method_43470("ÑÐ²Ð¾Ð¸ Ð¶Ð¸Ð·Ð½Ð¸ Ð² Ð¿Ð¾Ð¿Ñ‹Ñ‚ÐºÐ°Ñ…"), class_2561.method_43470("ÑÐ¾Ð±Ñ€Ð°Ñ‚ÑŒ ÑÑ‚Ñƒ ÐšÐ¸ÑÐ»Ð¾Ñ‚Ñƒ"));
      potions.add(new CustomItem("[â˜…] Ð¡ÐµÑ€Ð½Ð°Ñ ÐºÐ¸ÑÐ»Ð¾Ñ‚Ð°", (class_2487)null, class_1802.field_8436, Defaultpricec.getPrice("Ð¡ÐµÑ€Ð½Ð°Ñ ÐºÐ¸ÑÐ»Ð¾Ñ‚Ð°"), new class_1844(Optional.empty(), Optional.of(49664), List.of(), Optional.empty()), acidLore));
      List<class_2561> flashLore = List.of(class_2561.method_43470("Ð’ÑÐµÐ³Ð¾ Ð¾Ð´Ð½Ð° Ð±ÑƒÑ‚Ñ‹Ð»Ð¾Ñ‡ÐºÐ°"), class_2561.method_43470("Ð’ÑÐ¿Ñ‹ÑˆÐºÐ¸ ÑÐ¿Ð¾ÑÐ¾Ð±Ð½Ð° Ð¾ÑÐ»ÐµÐ¿Ð¸Ñ‚ÑŒ"), class_2561.method_43470("Ñ†ÐµÐ»ÑƒÑŽ Ð¾Ñ€Ð´Ñƒ Ð²Ñ€Ð°Ð³Ð¾Ð²!"));
      potions.add(new CustomItem("[â˜…] Ð’ÑÐ¿Ñ‹ÑˆÐºÐ°", (class_2487)null, class_1802.field_8436, Defaultpricec.getPrice("Ð’ÑÐ¿Ñ‹ÑˆÐºÐ°"), new class_1844(Optional.empty(), Optional.of(16777215), List.of(), Optional.empty()), flashLore));
      return potions;
   }
}

