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

public class DonatorProvider {
   public static List getDonator() {
      List<AutoBuyableItem> donator = new ArrayList();
      List<class_2561> lightDustLore = List.of(class_2561.method_43470("ÐšÐ°ÑÑ‚: Ð¡Ð²ÐµÑ‚Ð¾Ð²Ð°Ñ Ð²ÑÐ¿Ñ‹ÑˆÐºÐ°"), class_2561.method_43470("Ð Ð°Ð´Ð¸ÑƒÑ: 10 Ð±Ð»Ð¾ÐºÐ¾Ð²"), class_2561.method_43470("Ð­Ñ„Ñ„ÐµÐºÑ‚Ñ‹ Ð´Ð»Ñ Ð¿Ñ€Ð¾Ñ‚Ð¸Ð²Ð½Ð¸ÐºÐ¾Ð²:"), class_2561.method_43470(" â€¢ Ð¡Ð²ÐµÑ‡ÐµÐ½Ð¸Ðµ (00:30)"), class_2561.method_43470(" â€¢ Ð¡Ð»ÐµÐ¿Ð¾Ñ‚Ð° (00:01)"), class_2561.method_43470("Ð§ÐµÐ¼ Ð±Ð»Ð¸Ð¶Ðµ Ñ†ÐµÐ»ÑŒ, Ñ‚ÐµÐ¼ Ð´Ð¾Ð»ÑŒÑˆÐµ Ð´Ð»Ð¸Ñ‚ÐµÐ»ÑŒÐ½Ð¾ÑÑ‚ÑŒ ÑÑ„Ñ„ÐµÐºÑ‚Ð¾Ð²"));
      donator.add(new CustomItem("[â˜…] Ð¯Ð²Ð½Ð°Ñ Ð¿Ñ‹Ð»ÑŒ", (class_2487)null, class_1802.field_8479, Defaultpricec.getPrice("Ð¯Ð²Ð½Ð°Ñ Ð¿Ñ‹Ð»ÑŒ"), (class_1844)null, lightDustLore));
      List<class_2561> disorientationLore = List.of(class_2561.method_43470("Ð§ÐµÐ¼ Ð±Ð»Ð¸Ð¶Ðµ Ñ†ÐµÐ»ÑŒ, Ñ‚ÐµÐ¼ Ð´Ð¾Ð»ÑŒÑˆÐµ Ð´Ð»Ð¸Ñ‚ÐµÐ»ÑŒÐ½Ð¾ÑÑ‚ÑŒ ÑÑ„Ñ„ÐµÐºÑ‚Ð¾Ð²"));
      donator.add(new CustomItem("[â˜…] Ð”ÐµÐ·Ð¾Ñ€Ð¸ÐµÐ½Ñ‚Ð°Ñ†Ð¸Ñ", (class_2487)null, class_1802.field_8449, Defaultpricec.getPrice("Ð”ÐµÐ·Ð¾Ñ€Ð¸ÐµÐ½Ñ‚Ð°Ñ†Ð¸Ñ"), (class_1844)null, disorientationLore));
      List<class_2561> trapkaLore = List.of(class_2561.method_43470("ÐšÐ°ÑÑ‚: ÐÐµÑ€ÑƒÑˆÐ¸Ð¼Ð°Ñ ÐºÐ»ÐµÑ‚ÐºÐ°"), class_2561.method_43470("Ð”Ð»Ð¸Ñ‚ÐµÐ»ÑŒÐ½Ð¾ÑÑ‚ÑŒ: 15 ÑÐµÐºÑƒÐ½Ð´"), class_2561.method_43470("Ð˜ÑÐ¿Ð¾Ð»ÑŒÐ·ÑƒÐ¹Ñ‚Ðµ ÑÐºÐ¸Ð½Ñ‹: /tskins"));
      donator.add(new CustomItem("[â˜…] Ð¢Ñ€Ð°Ð¿ÐºÐ°", (class_2487)null, class_1802.field_22021, Defaultpricec.getPrice("Ð¢Ñ€Ð°Ð¿ÐºÐ°"), (class_1844)null, trapkaLore));
      List<class_2561> lockpickLore = List.of(class_2561.method_43470("Ð­Ñ‚Ð¾Ð¹ Ð¾Ñ‚Ð¼Ñ‹Ñ‡ÐºÐ¾Ð¹ Ð¼Ð¾Ð¶Ð½Ð¾"), class_2561.method_43470("ÐžÑ‚ÐºÑ€Ñ‹Ñ‚ÑŒ Ñ…Ñ€Ð°Ð½Ð¸Ð»Ð¸Ñ‰Ðµ"), class_2561.method_43470("Ð¡ Ð¡Ñ„ÐµÑ€Ð°Ð¼Ð¸"));
      donator.add(new CustomItem("ÐžÑ‚Ð¼Ñ‹Ñ‡ÐºÐ° Ðº Ð¡Ñ„ÐµÑ€Ð°Ð¼", (class_2487)null, class_1802.field_8366, Defaultpricec.getPrice("ÐžÑ‚Ð¼Ñ‹Ñ‡ÐºÐ° Ðº Ð¡Ñ„ÐµÑ€Ð°Ð¼"), (class_1844)null, lockpickLore));
      List<class_2561> plast = List.of(class_2561.method_43470("ÐšÐ°ÑÑ‚: ÐÐµÑ€ÑƒÑˆÐ¸Ð¼Ð°Ñ ÑÑ‚ÐµÐ½Ð°"), class_2561.method_43470("Ð”Ð»Ð¸Ñ‚ÐµÐ»ÑŒÐ½Ð¾ÑÑ‚ÑŒ:"), class_2561.method_43470("Ð’ÐµÑ€Ñ‚Ð¸ÐºÐ°Ð»ÑŒÐ½Ñ‹Ð¹: 20 ÑÐµÐºÑƒÐ½Ð´"), class_2561.method_43470("Ð“Ð¾Ñ€Ð¸Ð·Ð¾Ð½Ñ‚Ð°Ð»ÑŒÐ½Ñ‹Ð¹: 60 ÑÐµÐºÑƒÐ½Ð´"));
      donator.add(new CustomItem("[â˜…] ÐŸÐ»Ð°ÑÑ‚", (class_2487)null, class_1802.field_8551, Defaultpricec.getPrice("ÐŸÐ»Ð°ÑÑ‚"), (class_1844)null, plast));
      List<class_2561> exp15Lore = List.of(class_2561.method_43470("Ð¡Ð¾Ð´ÐµÑ€Ð¶Ð¸Ñ‚ 15 ÑƒÑ€ Ð¾Ð¿Ñ‹Ñ‚Ð°"));
      donator.add(new CustomItem("ÐŸÑƒÐ·Ñ‹Ñ€ÐµÐº Ð¾Ð¿Ñ‹Ñ‚Ð° [15 ÑƒÑ€]", (class_2487)null, class_1802.field_8287, Defaultpricec.getPrice("ÐŸÑƒÐ·Ñ‹Ñ€ÐµÐº Ð¾Ð¿Ñ‹Ñ‚Ð° [15 ÑƒÑ€]"), (class_1844)null, exp15Lore));
      List<class_2561> exp30Lore = List.of(class_2561.method_43470("Ð¡Ð¾Ð´ÐµÑ€Ð¶Ð¸Ñ‚: 30 Ð£Ñ€. Ð¾Ð¿Ñ‹Ñ‚Ð°"));
      donator.add(new CustomItem("ÐŸÑƒÐ·Ñ‹Ñ€Ñ‘Ðº Ð¾Ð¿Ñ‹Ñ‚Ð° [30 Ð£Ñ€.]", (class_2487)null, class_1802.field_8287, Defaultpricec.getPrice("ÐŸÑƒÐ·Ñ‹Ñ€Ñ‘Ðº Ð¾Ð¿Ñ‹Ñ‚Ð° [30 Ð£Ñ€.]"), (class_1844)null, exp30Lore));
      List<class_2561> exp50Lore = List.of(class_2561.method_43470("Ð¡Ð¾Ð´ÐµÑ€Ð¶Ð¸Ñ‚ 50 ÑƒÑ€ Ð¾Ð¿Ñ‹Ñ‚Ð°"));
      donator.add(new CustomItem("ÐŸÑƒÐ·Ñ‹Ñ€ÐµÐº Ð¾Ð¿Ñ‹Ñ‚Ð° [50 ÑƒÑ€]", (class_2487)null, class_1802.field_8287, Defaultpricec.getPrice("ÐŸÑƒÐ·Ñ‹Ñ€ÐµÐº Ð¾Ð¿Ñ‹Ñ‚Ð° [50 ÑƒÑ€]"), (class_1844)null, exp50Lore));
      List<class_2561> tntWhiteLore = List.of(class_2561.method_43470("Ð­Ñ‚Ð¾Ñ‚ Ð´Ð¸Ð½Ð°Ð¼Ð¸Ñ‚ Ð²Ð·Ñ€Ñ‹Ð²Ð°ÐµÑ‚ÑÑ"), class_2561.method_43470("Ð² 10 Ñ€Ð°Ð· ÑÐ¸Ð»ÑŒÐ½ÐµÐµ Ð¾Ð±Ñ‹Ñ‡Ð½Ð¾Ð³Ð¾"));
      donator.add(new CustomItem("[â˜…] TNT - TIER WHITE", (class_2487)null, class_1802.field_8626, Defaultpricec.getPrice("TNT - TIER WHITE"), (class_1844)null, tntWhiteLore));
      List<class_2561> tntBlackLore = List.of(class_2561.method_43470("Ð­Ñ‚Ð¾Ñ‚ Ð´Ð¸Ð½Ð°Ð¼Ð¸Ñ‚ Ð²Ð·Ñ€Ñ‹Ð²Ð°ÐµÑ‚ÑÑ"), class_2561.method_43470("Ð² 10 Ñ€Ð°Ð· ÑÐ¸Ð»ÑŒÐ½ÐµÐµ Ð¾Ð±Ñ‹Ñ‡Ð½Ð¾Ð³Ð¾"), class_2561.method_43470("Ð¸ ÑÐ¿Ð¾ÑÐ¾Ð±ÐµÐ½ Ð²Ð·Ð¾Ñ€Ð²Ð°Ñ‚ÑŒ Ð¾Ð±ÑÐ¸Ð´Ð¸Ð°Ð½"));
      donator.add(new CustomItem("[â˜…] TNT - TIER BLACK", (class_2487)null, class_1802.field_8626, Defaultpricec.getPrice("TNT - TIER BLACK"), (class_1844)null, tntBlackLore));
      List<class_2561> signalRandomLore = List.of(class_2561.method_43470("Ð£Ñ€Ð¾Ð²ÐµÐ½ÑŒ Ð»ÑƒÑ‚Ð°: Ð¡Ð»ÑƒÑ‡Ð°Ð¹Ð½Ñ‹Ð¹"));
      donator.add(new CustomItem("Ð¡Ð¸Ð³Ð½Ð°Ð»ÑŒÐ½Ñ‹Ð¹ Ð¾Ð³Ð¾Ð½ÑŒ [Ð¡Ð»ÑƒÑ‡Ð°Ð¹Ð½Ñ‹Ð¹]", (class_2487)null, class_1802.field_17346, Defaultpricec.getPrice("Ð¡Ð¸Ð³Ð½Ð°Ð»ÑŒÐ½Ñ‹Ð¹ Ð¾Ð³Ð¾Ð½ÑŒ [Ð¡Ð»ÑƒÑ‡Ð°Ð¹Ð½Ñ‹Ð¹]"), (class_1844)null, signalRandomLore));
      List<class_2561> signalOrdinaryLore = List.of(class_2561.method_43470("Ð£Ñ€Ð¾Ð²ÐµÐ½ÑŒ Ð»ÑƒÑ‚Ð°: ÐžÐ±Ñ‹Ñ‡Ð½Ñ‹Ð¹"));
      donator.add(new CustomItem("Ð¡Ð¸Ð³Ð½Ð°Ð»ÑŒÐ½Ñ‹Ð¹ Ð¾Ð³Ð¾Ð½ÑŒ [ÐžÐ±Ñ‹Ñ‡Ð½Ñ‹Ð¹]", (class_2487)null, class_1802.field_17346, Defaultpricec.getPrice("Ð¡Ð¸Ð³Ð½Ð°Ð»ÑŒÐ½Ñ‹Ð¹ Ð¾Ð³Ð¾Ð½ÑŒ [ÐžÐ±Ñ‹Ñ‡Ð½Ñ‹Ð¹]"), (class_1844)null, signalOrdinaryLore));
      List<class_2561> signalRichLore = List.of(class_2561.method_43470("Ð£Ñ€Ð¾Ð²ÐµÐ½ÑŒ Ð»ÑƒÑ‚Ð°: Ð‘Ð¾Ð³Ð°Ñ‚Ñ‹Ð¹"));
      donator.add(new CustomItem("Ð¡Ð¸Ð³Ð½Ð°Ð»ÑŒÐ½Ñ‹Ð¹ Ð¾Ð³Ð¾Ð½ÑŒ [Ð‘Ð¾Ð³Ð°Ñ‚Ñ‹Ð¹]", (class_2487)null, class_1802.field_17346, Defaultpricec.getPrice("Ð¡Ð¸Ð³Ð½Ð°Ð»ÑŒÐ½Ñ‹Ð¹ Ð¾Ð³Ð¾Ð½ÑŒ [Ð‘Ð¾Ð³Ð°Ñ‚Ñ‹Ð¹]"), (class_1844)null, signalRichLore));
      List<class_2561> signalLegendaryLore = List.of(class_2561.method_43470("Ð£Ñ€Ð¾Ð²ÐµÐ½ÑŒ Ð»ÑƒÑ‚Ð°: Ð›ÐµÐ³ÐµÐ½Ð´Ð°Ñ€Ð½Ñ‹Ð¹"));
      donator.add(new CustomItem("Ð¡Ð¸Ð³Ð½Ð°Ð»ÑŒÐ½Ñ‹Ð¹ Ð¾Ð³Ð¾Ð½ÑŒ [Ð›ÐµÐ³ÐµÐ½Ð´Ð°Ñ€Ð½Ñ‹Ð¹]", (class_2487)null, class_1802.field_17346, Defaultpricec.getPrice("Ð¡Ð¸Ð³Ð½Ð°Ð»ÑŒÐ½Ñ‹Ð¹ Ð¾Ð³Ð¾Ð½ÑŒ [Ð›ÐµÐ³ÐµÐ½Ð´Ð°Ñ€Ð½Ñ‹Ð¹]"), (class_1844)null, signalLegendaryLore));
      List<class_2561> blockDamagerLore = List.of(class_2561.method_43470("â— ÐšÐ°ÑÑ‚: ÐÐ°Ð½ÐµÑÐµÐ½Ð¸Ðµ ÑƒÑ€Ð¾Ð½Ð°"), class_2561.method_43470("â— Ð Ð°Ð´Ð¸ÑƒÑ: 1,5 Ð±Ð»Ð¾ÐºÐ°"));
      donator.add(new CustomItem("[â˜…] Ð‘Ð»Ð¾Ðº Ð´Ð°Ð¼Ð°Ð³ÐµÑ€", (class_2487)null, class_1802.field_16538, Defaultpricec.getPrice("Ð‘Ð»Ð¾Ðº Ð´Ð°Ð¼Ð°Ð³ÐµÑ€"), (class_1844)null, blockDamagerLore));
      List<class_2561> chunkLoader1x1Lore = List.of(class_2561.method_43470("ÐŸÑ€Ð¾Ð³Ñ€ÑƒÐ¶Ð°ÐµÑ‚ Ñ‡Ð°Ð½Ðº, Ð² ÐºÐ¾Ñ‚Ð¾Ñ€Ð¾Ð¼"), class_2561.method_43470("Ð½Ð°Ñ…Ð¾Ð´Ð¸Ñ‚ÑÑ ÑÑ‚Ð¾Ñ‚ Ð¿Ñ€Ð¾Ð³Ñ€ÑƒÐ·Ñ‡Ð¸Ðº."), class_2561.method_43470("ÐÐ°Ð¶Ð¼Ð¸Ñ‚Ðµ Ð½Ð° Ð½ÐµÐ³Ð¾, Ñ‡Ñ‚Ð¾Ð±Ñ‹ Ð½Ð°"), class_2561.method_43470("30 ÑÐµÐºÑƒÐ½Ð´ ÑƒÐ²Ð¸Ð´ÐµÑ‚ÑŒ Ð³Ñ€Ð°Ð½Ð¸Ñ†Ñ‹"));
      donator.add(new CustomItem("ÐŸÑ€Ð¾Ð³Ñ€ÑƒÐ·Ñ‡Ð¸Ðº Ñ‡Ð°Ð½ÐºÐ¾Ð² [1x1]", (class_2487)null, class_1802.field_8238, Defaultpricec.getPrice("ÐŸÑ€Ð¾Ð³Ñ€ÑƒÐ·Ñ‡Ð¸Ðº Ñ‡Ð°Ð½ÐºÐ¾Ð² [1x1]"), (class_1844)null, chunkLoader1x1Lore));
      List<class_2561> chunkLoader3x3Lore = List.of(class_2561.method_43470("ÐŸÑ€Ð¾Ð³Ñ€ÑƒÐ¶Ð°ÐµÑ‚ Ñ‡Ð°Ð½Ðº, Ð² ÐºÐ¾Ñ‚Ð¾Ñ€Ð¾Ð¼"), class_2561.method_43470("Ð½Ð°Ñ…Ð¾Ð´Ð¸Ñ‚ÑÑ ÑÑ‚Ð¾Ñ‚ Ð¿Ñ€Ð¾Ð³Ñ€ÑƒÐ·Ñ‡Ð¸Ðº."), class_2561.method_43470("ÐÐ°Ð¶Ð¼Ð¸Ñ‚Ðµ Ð½Ð° Ð½ÐµÐ³Ð¾, Ñ‡Ñ‚Ð¾Ð±Ñ‹ Ð½Ð°"), class_2561.method_43470("30 ÑÐµÐºÑƒÐ½Ð´ ÑƒÐ²Ð¸Ð´ÐµÑ‚ÑŒ Ð³Ñ€Ð°Ð½Ð¸Ñ†Ñ‹"));
      donator.add(new CustomItem("ÐŸÑ€Ð¾Ð³Ñ€ÑƒÐ·Ñ‡Ð¸Ðº Ñ‡Ð°Ð½ÐºÐ¾Ð² [3x3]", (class_2487)null, class_1802.field_8238, Defaultpricec.getPrice("ÐŸÑ€Ð¾Ð³Ñ€ÑƒÐ·Ñ‡Ð¸Ðº Ñ‡Ð°Ð½ÐºÐ¾Ð² [3x3]"), (class_1844)null, chunkLoader3x3Lore));
      List<class_2561> chunkLoader5x5Lore = List.of(class_2561.method_43470("ÐŸÑ€Ð¾Ð³Ñ€ÑƒÐ¶Ð°ÐµÑ‚ Ñ‡Ð°Ð½Ðº, Ð² ÐºÐ¾Ñ‚Ð¾Ñ€Ð¾Ð¼"), class_2561.method_43470("Ð½Ð°Ñ…Ð¾Ð´Ð¸Ñ‚ÑÑ ÑÑ‚Ð¾Ñ‚ Ð¿Ñ€Ð¾Ð³Ñ€ÑƒÐ·Ñ‡Ð¸Ðº."), class_2561.method_43470("ÐÐ°Ð¶Ð¼Ð¸Ñ‚Ðµ Ð½Ð° Ð½ÐµÐ³Ð¾, Ñ‡Ñ‚Ð¾Ð±Ñ‹ Ð½Ð°"), class_2561.method_43470("30 ÑÐµÐºÑƒÐ½Ð´ ÑƒÐ²Ð¸Ð´ÐµÑ‚ÑŒ Ð³Ñ€Ð°Ð½Ð¸Ñ†Ñ‹"));
      donator.add(new CustomItem("ÐŸÑ€Ð¾Ð³Ñ€ÑƒÐ·Ñ‡Ð¸Ðº Ñ‡Ð°Ð½ÐºÐ¾Ð² [5x5]", (class_2487)null, class_1802.field_8238, Defaultpricec.getPrice("ÐŸÑ€Ð¾Ð³Ñ€ÑƒÐ·Ñ‡Ð¸Ðº Ñ‡Ð°Ð½ÐºÐ¾Ð² [5x5]"), (class_1844)null, chunkLoader5x5Lore));
      List<class_2561> mysteriousBeaconLore = List.of(class_2561.method_43470("ÐœÐ°ÑÐº ÑƒÑÑ‚Ð°Ð½Ð¾Ð²Ð¸Ñ‚ Ð²Ñ€ÐµÐ¼ÐµÐ½Ð½Ñ‹Ð¹"), class_2561.method_43470("Ð¸Ð²ÐµÐ½Ñ‚, Ñ€Ð°Ð·Ð´Ð°ÑŽÑ‰Ð¸Ð¹ ÐœÐ¾Ð½ÐµÑ‚Ñ‹"), class_2561.method_43470("Ð¸Ð³Ñ€Ð¾ÐºÐ°Ð¼ Ð¿Ð¾Ð±Ð»Ð¸Ð·Ð¾ÑÑ‚Ð¸."));
      donator.add(new CustomItem("Ð—Ð°Ð³Ð°Ð´Ð¾Ñ‡Ð½Ñ‹Ð¹ Ð¼Ð°ÑÐº", (class_2487)null, class_1802.field_8668, Defaultpricec.getPrice("Ð—Ð°Ð³Ð°Ð´Ð¾Ñ‡Ð½Ñ‹Ð¹ Ð¼Ð°ÑÐº"), (class_1844)null, mysteriousBeaconLore));
      List<class_2561> cursedSoulLore = List.of(class_2561.method_43470("ÐžÐ±Ð¼ÐµÐ½ÑÐ¹ Ð´ÑƒÑˆÐ¸ Ð½Ð° Ñ†ÐµÐ½Ð½Ñ‹Ðµ"), class_2561.method_43470("Ñ€ÐµÑÑƒÑ€ÑÑ‹ Ñƒ Ð¡Ð¾Ð±Ð¸Ñ€Ð°Ñ‚ÐµÐ»Ñ Ð´ÑƒÑˆ"), class_2561.method_43470("/warp soulcollector"));
      donator.add(new CustomItem("[â˜…] ÐŸÑ€Ð¾ÐºÐ»ÑÑ‚Ð°Ñ Ð´ÑƒÑˆÐ°", (class_2487)null, class_1802.field_22016, Defaultpricec.getPrice("ÐŸÑ€Ð¾ÐºÐ»ÑÑ‚Ð°Ñ Ð´ÑƒÑˆÐ°"), (class_1844)null, cursedSoulLore));
      List<class_2561> dragonSkinLore = List.of(class_2561.method_43470("Ð˜ÑÐ¿Ð¾Ð»ÑŒÐ·ÑƒÑ ÑÑ‚Ð¾Ñ‚ Ð¿Ñ€ÐµÐ´Ð¼ÐµÑ‚"), class_2561.method_43470("Ð’Ñ‹ ÐµÐ³Ð¾ Ñ€Ð°ÑÑ…Ð¾Ð´ÑƒÐµÑ‚Ðµ"), class_2561.method_43470("Ð¸ Ð¿Ð¾Ð»ÑƒÑ‡Ð°ÐµÑ‚Ðµ Ð”Ñ€Ð°ÐºÐ¾Ð½Ð¸Ð¹ ÑÐºÐ¸Ð½ Ð²Ð·Ð°Ð¼ÐµÐ½"), class_2561.method_43470("[ÐŸÐšÐœ] Ñ‡Ñ‚Ð¾Ð±Ñ‹ Ð¸ÑÐ¿Ð¾Ð»ÑŒÐ·Ð¾Ð²Ð°Ñ‚ÑŒ x1 ÑÐºÐ¸Ð½"), class_2561.method_43470("[SHIFT+ÐŸÐšÐœ] Ñ‡Ñ‚Ð¾Ð±Ñ‹ Ð¸ÑÐ¿Ð¾Ð»ÑŒÐ·Ð¾Ð²Ð°Ñ‚ÑŒ Ð²ÑÐµ ÑÐºÐ¸Ð½Ñ‹"), class_2561.method_43470("ÐŸÑ€ÐµÐ´Ð¼ÐµÑ‚ Ð½ÑƒÐ¶Ð½Ð¾ Ð´ÐµÑ€Ð¶Ð°Ñ‚ÑŒ Ð² Ñ€ÑƒÐºÐµ"));
      donator.add(new CustomItem("[â˜…] Ð”Ñ€Ð°ÐºÐ¾Ð½Ð¸Ð¹ ÑÐºÐ¸Ð½", (class_2487)null, class_1802.field_8407, Defaultpricec.getPrice("Ð”Ñ€Ð°ÐºÐ¾Ð½Ð¸Ð¹ ÑÐºÐ¸Ð½"), (class_1844)null, dragonSkinLore));
      List<class_2561> fireWhirlwindLore = List.of(class_2561.method_43470("â— ÐšÐ°ÑÑ‚: ÐžÐ³Ð½ÐµÐ½Ð½Ð°Ñ Ð²Ð¾Ð»Ð½Ð°"), class_2561.method_43470("â— Ð Ð°Ð´Ð¸ÑƒÑ: 10 Ð±Ð»Ð¾ÐºÐ¾Ð²"), class_2561.method_43470(""), class_2561.method_43470("â— Ð­Ñ„Ñ„ÐµÐºÑ‚Ñ‹ Ð´Ð»Ñ Ð¿Ñ€Ð¾Ñ‚Ð¸Ð²Ð½Ð¸ÐºÐ¾Ð²:"), class_2561.method_43470(" - ÐŸÐ¾Ð´Ð¶Ð¾Ð³ (00:03)"), class_2561.method_43470(""), class_2561.method_43470("Ð§ÐµÐ¼ Ð±Ð»Ð¸Ð¶Ðµ Ñ†ÐµÐ»ÑŒ, Ñ‚ÐµÐ¼ Ð´Ð¾Ð»ÑŒÑˆÐµ"), class_2561.method_43470("Ð´Ð»Ð¸Ñ‚ÐµÐ»ÑŒÐ½Ð¾ÑÑ‚ÑŒ ÑÑ„Ñ„ÐµÐºÑ‚Ð¾Ð²"));
      donator.add(new CustomItem("[â˜…] ÐžÐ³Ð½ÐµÐ½Ð½Ñ‹Ð¹ ÑÐ¼ÐµÑ€Ñ‡", (class_2487)null, class_1802.field_8814, Defaultpricec.getPrice("ÐžÐ³Ð½ÐµÐ½Ð½Ñ‹Ð¹ ÑÐ¼ÐµÑ€Ñ‡"), (class_1844)null, fireWhirlwindLore));
      List<class_2561> freezingSnowballLore = List.of(class_2561.method_43470("â— ÐšÐ°ÑÑ‚: Ð›ÐµÐ´ÑÐ½Ð°Ñ ÑÑ„ÐµÑ€Ð°"), class_2561.method_43470("â— Ð Ð°Ð´Ð¸ÑƒÑ: 7 Ð±Ð»Ð¾ÐºÐ¾Ð²"), class_2561.method_43470(""), class_2561.method_43470("â— Ð­Ñ„Ñ„ÐµÐºÑ‚Ñ‹ Ð´Ð»Ñ Ð¿Ñ€Ð¾Ñ‚Ð¸Ð²Ð½Ð¸ÐºÐ¾Ð²:"), class_2561.method_43470(" - Ð—Ð°Ð¼Ð¾Ñ€Ð¾Ð·ÐºÐ° (00:01)"), class_2561.method_43470(" - Ð¡Ð»Ð°Ð±Ð¾ÑÑ‚ÑŒ (03:00)"));
      donator.add(new CustomItem("[â˜…] Ð¡Ð½ÐµÐ¶Ð¾Ðº Ð·Ð°Ð¼Ð¾Ñ€Ð¾Ð·ÐºÐ°", (class_2487)null, class_1802.field_8543, Defaultpricec.getPrice("Ð¡Ð½ÐµÐ¶Ð¾Ðº Ð·Ð°Ð¼Ð¾Ñ€Ð¾Ð·ÐºÐ°"), (class_1844)null, freezingSnowballLore));
      List<class_2561> godsAuraLore = List.of(class_2561.method_43470("â— ÐšÐ°ÑÑ‚: Ð‘Ð¾Ð¶ÐµÑÑ‚Ð²ÐµÐ½Ð½Ð°Ñ Ð°ÑƒÑ€Ð°"), class_2561.method_43470("â— Ð Ð°Ð´Ð¸ÑƒÑ: 2 Ð±Ð»Ð¾ÐºÐ°"), class_2561.method_43470(""), class_2561.method_43470("â— Ð­Ñ„Ñ„ÐµÐºÑ‚Ñ‹ Ð´Ð»Ñ ÑÐ¾ÑŽÐ·Ð½Ð¸ÐºÐ¾Ð²:"), class_2561.method_43470(" - Ð¡Ð½ÑÑ‚Ð¸Ðµ Ð²ÑÐµÑ… ÑÑ„Ñ„ÐµÐºÑ‚Ð¾Ð²"), class_2561.method_43470(" - ÐÐµÐ²Ð¸Ð´Ð¸Ð¼Ð¾ÑÑ‚ÑŒ (04:00)"), class_2561.method_43470(" - Ð¡Ð¸Ð»Ð° II (03:00)"), class_2561.method_43470(" - Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ II (03:00)"));
      donator.add(new CustomItem("[â˜…] Ð‘Ð¾Ð¶ÑŒÑ Ð°ÑƒÑ€Ð°", (class_2487)null, class_1802.field_8614, Defaultpricec.getPrice("Ð‘Ð¾Ð¶ÑŒÑ Ð°ÑƒÑ€Ð°"), (class_1844)null, godsAuraLore));
      List<class_2561> silverLore = List.of(class_2561.method_43470("Ð­Ñ‚Ð¾ Ð²Ð°Ð»ÑŽÑ‚Ð° Ð´Ð»Ñ Ð¿Ð¾ÐºÑƒÐ¿ÐºÐ¸"), class_2561.method_43470("Ð¾Ñ‚Ð¼Ñ‹Ñ‡ÐµÐº Ðº Ñ‚Ð°Ð¹Ð½Ð¸ÐºÐ°Ð¼"), class_2561.method_43470("Ñƒ Ð—Ð½Ð°Ñ…Ð°Ñ€Ñ (/warp stash)"));
      donator.add(new CustomItem("[â˜…] Ð¡ÐµÑ€ÐµÐ±Ñ€Ð¾", (class_2487)null, class_1802.field_8675, Defaultpricec.getPrice("Ð¡ÐµÑ€ÐµÐ±Ñ€Ð¾"), (class_1844)null, silverLore));
      List<class_2561> godsTouchLore = List.of(class_2561.method_43470("Ð‘Ð¾Ð¶ÑŒÐµ ÐºÐ°ÑÐ°Ð½Ð¸Ðµ"), class_2561.method_43470(""), class_2561.method_43470("ÐœÐ¾Ð¶ÐµÑ‚ Ð´Ð¾Ð±Ñ‹Ñ‚ÑŒ ÑÐ¿Ð°Ð²Ð½ÐµÑ€,"), class_2561.method_43470("Ð½Ð¾ Ñ‚Ð¾Ð»ÑŒÐºÐ¾ Ð¾Ð´Ð¸Ð½ Ñ€Ð°Ð·"));
      donator.add(new CustomItem("[â˜…] Ð‘Ð¾Ð¶ÑŒÐµ ÐºÐ°ÑÐ°Ð½Ð¸Ðµ", (class_2487)null, class_1802.field_8335, Defaultpricec.getPrice("Ð‘Ð¾Ð¶ÑŒÐµ ÐºÐ°ÑÐ°Ð½Ð¸Ðµ"), (class_1844)null, godsTouchLore));
      List<class_2561> megaBulldozerLore = List.of(class_2561.method_43470("Ð’ÑÐºÐ°Ð¿Ñ‹Ð²Ð°ÐµÑ‚ Ñ‚ÐµÑ€Ñ€Ð¸Ñ‚Ð¾Ñ€Ð¸ÑŽ"), class_2561.method_43470("Ñ€Ð°Ð·Ð¼ÐµÑ€Ð¾Ð¼ 9x9x5 Ð±Ð»Ð¾ÐºÐ¾Ð²"));
      donator.add(new CustomItem("[â˜…] ÐšÐ¸Ñ€ÐºÐ° Ð¼ÐµÐ³Ð°-Ð±ÑƒÐ»ÑŒÐ´Ð¾Ð·ÐµÑ€", (class_2487)null, class_1802.field_22024, Defaultpricec.getPrice("ÐšÐ¸Ñ€ÐºÐ° Ð¼ÐµÐ³Ð°-Ð±ÑƒÐ»ÑŒÐ´Ð¾Ð·ÐµÑ€"), (class_1844)null, megaBulldozerLore));
      List<class_2561> caramelAppleLore = List.of(class_2561.method_43470("Ð­Ñ‚Ð¾ ÐºÐ¾ÑˆÐ¼Ð°Ñ€Ð½Ð°Ñ ÐºÐ¾Ð½Ñ„ÐµÑ‚Ð° Ð´Ð»Ñ Ð¿Ñ€Ð¾Ñ…Ð¾Ð¶Ð´ÐµÐ½Ð¸Ð¸"), class_2561.method_43470("ÐºÐ°Ñ€Ñ‚Ñ‹ Ñ‚Ð°Ð¸Ð½ÑÑ‚Ð² - Ð²Ð²Ð¾Ð´Ð¸ /hellmap"), class_2561.method_43470(""), class_2561.method_43470("ÐšÐ¾ÑˆÐ¼Ð°Ñ€Ð½Ð¾ÑÑ‚ÑŒ: +5"));
      donator.add(new CustomItem("ÐšÐ°Ñ€Ð°Ð¼ÐµÐ»ÑŒÐ½Ð¾Ðµ ÑÐ±Ð»Ð¾ÐºÐ¾", (class_2487)null, class_1802.field_8279, Defaultpricec.getPrice("ÐšÐ°Ñ€Ð°Ð¼ÐµÐ»ÑŒÐ½Ð¾Ðµ ÑÐ±Ð»Ð¾ÐºÐ¾"), (class_1844)null, caramelAppleLore));
      return donator;
   }
}

