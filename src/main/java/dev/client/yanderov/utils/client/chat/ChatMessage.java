package dev.client.yanderov.utils.client.chat;

import dev.client.yanderov.utils.client.text.TextHelper;
import net.minecraft.class_124;
import net.minecraft.class_2561;
import net.minecraft.class_2583;
import net.minecraft.class_310;
import net.minecraft.class_5250;

public class ChatMessage {
   public static class_5250 brandmessage() {
      return (class_5250)TextHelper.applyPredefinedGradient("Yanderov Client", "black_light_purple", true);
   }

   public static class_5250 blockesp() {
      return (class_5250)TextHelper.applyPredefinedGradient("Block Esp", "black_light_purple", true);
   }

   public static class_5250 autobuy() {
      return (class_5250)TextHelper.applyPredefinedGradient("Auto Buy", "black_light_purple", true);
   }

   public static class_5250 autobuiparcer() {
      return (class_5250)TextHelper.applyPredefinedGradient("Parce price", "black_light_purple", true);
   }

   public static void brandmessage(String message) {
      if (class_310.method_1551().field_1724 != null) {
         class_2561 prefix = TextHelper.applyPredefinedGradient("Yanderov Client -> ", "black_light_purple", true);
         class_2561 formattedMessage = prefix.method_27661().method_10852(class_2561.method_43470(message));
         class_310.method_1551().field_1724.method_7353(formattedMessage, false);
      }

   }

   public static void ancientmessage(String message) {
      if (class_310.method_1551().field_1724 != null) {
         class_2561 prefix = TextHelper.applyPredefinedGradient("Ancient Xray -> ", "black_light_purple", true);
         class_2561 formattedMessage = prefix.method_27661().method_10852(class_2561.method_43470(message));
         class_310.method_1551().field_1724.method_7353(formattedMessage, false);
      }

   }

   public static void helpmessage(String message) {
      if (class_310.method_1551().field_1724 != null) {
         class_2561 prefix = TextHelper.applyPredefinedGradient("Help -> ", "black_light_purple", true);
         class_2561 formattedMessage = prefix.method_27661().method_10852(class_2561.method_43470(message));
         class_310.method_1551().field_1724.method_7353(formattedMessage, false);
      }

   }

   public static void swapmessage(String message) {
      if (class_310.method_1551().field_1724 != null) {
         class_2561 prefix = TextHelper.applyPredefinedGradient("AutoSwap -> ", "black_light_purple", true);
         class_2561 formattedMessage = prefix.method_27661().method_10852(class_2561.method_43470(message));
         class_310.method_1551().field_1724.method_7353(formattedMessage, false);
      }

   }

   public static void ircmessage(String message) {
      if (class_310.method_1551().field_1724 != null) {
         class_2561 prefix = TextHelper.applyPredefinedGradient("[IRC] ", "black_light_purple", true);
         class_2561 formattedMessage = prefix.method_27661().method_10852(class_2561.method_43470(message));
         class_310.method_1551().field_1724.method_7353(formattedMessage, false);
      }

   }

   public static void ircmessageWithGreen(String message) {
      if (class_310.method_1551().field_1724 != null) {
         class_2561 prefix = TextHelper.applyPredefinedGradient("[IRC] ", "black_light_purple", true);
         class_2561 formattedMessage = prefix.method_27661().method_10852(class_2561.method_43470(message).method_10862(class_2583.field_24360.method_10977(class_124.field_1060)));
         class_310.method_1551().field_1724.method_7353(formattedMessage, false);
      }

   }

   public static void ircmessageWithRed(String message) {
      if (class_310.method_1551().field_1724 != null) {
         class_2561 prefix = TextHelper.applyPredefinedGradient("[IRC] ", "black_light_purple", true);
         class_2561 formattedMessage = prefix.method_27661().method_10852(class_2561.method_43470(message).method_10862(class_2583.field_24360.method_10977(class_124.field_1061)));
         class_310.method_1551().field_1724.method_7353(formattedMessage, false);
      }

   }

   public static class_2561 ircprefixDeveloper(String message) {
      class_2561 prefix = TextHelper.applyPredefinedGradient("Developer ", "dark_red_bright_red", false);
      return prefix.method_27661().method_10852(class_2561.method_43470(message));
   }

   public static class_2561 ircprefixCurator(String message) {
      class_2561 prefix = TextHelper.applyPredefinedGradient("ÐšÑƒÑ€Ð°Ñ‚Ð¾Ñ€ ", "dark_red", false);
      return prefix.method_27661().method_10852(class_2561.method_43470(message));
   }

   public static class_2561 ircprefixYouTube(String message) {
      class_2561 prefix = TextHelper.applyPredefinedGradient("YouTube ", "red_white", false);
      return prefix.method_27661().method_10852(class_2561.method_43470(message));
   }

   public static class_2561 ircprefixPikmi(String message) {
      class_2561 prefix = TextHelper.applyPredefinedGradient("ÐŸÐ¸ÐºÐ¼Ð¸ ", "purple_bright_pink", false);
      return prefix.method_27661().method_10852(class_2561.method_43470(message));
   }

   public static class_2561 ircprefixLabuba(String message) {
      class_2561 prefix = TextHelper.applyPredefinedGradient("Ð›Ð°Ð±ÑƒÐ±Ð° ", "pink_dark_pink", false);
      return prefix.method_27661().method_10852(class_2561.method_43470(message));
   }

   public static class_2561 ircprefixZapen(String message) {
      class_2561 prefix = TextHelper.applyPredefinedGradient("Ð—Ð°Ð¿ÐµÐ½ ", "bright_red", false);
      return prefix.method_27661().method_10852(class_2561.method_43470(message));
   }

   public static class_2561 ircprefixBoost(String message) {
      class_2561 prefix = TextHelper.applyPredefinedGradient("Ð‘ÑƒÑÑ‚ ", "dark_green_bright_green", false);
      return prefix.method_27661().method_10852(class_2561.method_43470(message));
   }

   public static class_2561 ircprefixYanderov(String message) {
      class_2561 prefix = TextHelper.applyPredefinedGradient("Ð Ð¸Ñ‡ ", "red_orange", false);
      return prefix.method_27661().method_10852(class_2561.method_43470(message));
   }

   public static class_2561 ircprefixPanda(String message) {
      class_2561 prefix = TextHelper.applyPredefinedGradient("ÐŸÐ°Ð½Ð´Ð° ", "white_black", false);
      return prefix.method_27661().method_10852(class_2561.method_43470(message));
   }

   public static class_2561 ircprefixSmiley(String message) {
      class_2561 prefix = TextHelper.applyPredefinedGradient("(â—'â—¡'â—) ", "turquoise_blue", true);
      return prefix.method_27661().method_10852(class_2561.method_43470(message));
   }

   public static class_2561 ircprefixBibi(String message) {
      class_2561 prefix = TextHelper.applyPredefinedGradient("Ð‘Ð¸Ð±Ð¸...! ", "cyan_orange_fade", false);
      return prefix.method_27661().method_10852(class_2561.method_43470(message));
   }

   public static class_2561 ircprefixBenena(String message) {
      class_2561 prefix = TextHelper.applyPredefinedGradient("Ð‘ÑÐ½ÐµÐ½Ð° ", "yellow_cyan", false);
      return prefix.method_27661().method_10852(class_2561.method_43470(message));
   }

   public static class_2561 ircprefixBlyabuba(String message) {
      class_2561 prefix = TextHelper.applyPredefinedGradient("Ð‘Ð»ÑÐ±ÑƒÐ±Ð° ", "purple_red_fade", false);
      return prefix.method_27661().method_10852(class_2561.method_43470(message));
   }
}

