package fun.Yanderov.utils.display.color;

import net.minecraft.class_124;
import net.minecraft.class_2561;
import net.minecraft.class_5250;

public class GradientAssist {
   public static int[] getGradientColors(String itemName) {
      switch (itemName) {
         case "Ð”ÐµÐ·Ð¾Ñ€Ð¸ÐµÐ½Ñ‚Ð°Ñ†Ð¸Ñ" -> {
            return new int[]{ColorAssist.toColor("#00FF6A"), ColorAssist.toColor("#2761F5"), ColorAssist.toColor("#B80081")};
         }
         case "Ð‘Ð¾Ð¶ÑŒÑ Ð°ÑƒÑ€Ð°" -> {
            return new int[]{ColorAssist.toColor("#FBFF00"), ColorAssist.toColor("#FF9873"), ColorAssist.toColor("#FF9873")};
         }
         case "ÐŸÐ»Ð°ÑÑ‚" -> {
            return new int[]{ColorAssist.toColor("#3B005E"), ColorAssist.toColor("#8900DB"), ColorAssist.toColor("#8900DB")};
         }
         case "Ð¢Ñ€Ð°Ð¿ÐºÐ°" -> {
            return new int[]{ColorAssist.toColor("#8F0000"), ColorAssist.toColor("#DE0000"), ColorAssist.toColor("#FF0000")};
         }
         case "ÐžÐ³Ð½ÐµÐ½Ð½Ñ‹Ð¹ ÑÐ¼ÐµÑ€Ñ‡" -> {
            return new int[]{ColorAssist.toColor("#ED0000"), ColorAssist.toColor("#FF682B"), ColorAssist.toColor("#FF682B")};
         }
         case "Ð¡Ð½ÐµÐ¶Ð¾Ðº Ð·Ð°Ð¼Ð¾Ñ€Ð¾Ð·ÐºÐ°" -> {
            return new int[]{ColorAssist.toColor("#2BFFCE"), ColorAssist.toColor("#00E6B0"), ColorAssist.toColor("#00E6B0")};
         }
         case "Ð¯Ð²Ð½Ð°Ñ Ð¿Ñ‹Ð»ÑŒ" -> {
            return new int[]{ColorAssist.toColor("#00FFFA"), ColorAssist.toColor("#00FF95"), ColorAssist.toColor("#00FF95")};
         }
         case "Ð—ÐµÐ»ÑŒÐµ Ð¼Ð¾Ñ‡Ð¸ Ð¤Ð»ÐµÑˆÐ°" -> {
            return new int[]{ColorAssist.toColor("#00799E"), ColorAssist.toColor("#00FFFF")};
         }
         case "Ð—ÐµÐ»ÑŒÐµ Ð¼ÐµÐ´Ð¸ÐºÐ°" -> {
            return new int[]{ColorAssist.toColor("#8A007D"), ColorAssist.toColor("#FF00E8")};
         }
         case "Ð—ÐµÐ»ÑŒÐµ Ð°Ð³ÐµÐ½Ñ‚Ð°" -> {
            return new int[]{ColorAssist.toColor("#D99A00"), ColorAssist.toColor("#FFEE00")};
         }
         case "Ð—ÐµÐ»ÑŒÐµ Ð¿Ð¾Ð±ÐµÐ´Ð¸Ñ‚ÐµÐ»Ñ" -> {
            return new int[]{ColorAssist.toColor("#00821F"), ColorAssist.toColor("#00FF3D")};
         }
         case "Ð—ÐµÐ»ÑŒÐµ ÐºÐ¸Ð»Ð»ÐµÑ€Ð°" -> {
            return new int[]{ColorAssist.toColor("#9C0000"), ColorAssist.toColor("#FF0F0F")};
         }
         case "Ð—ÐµÐ»ÑŒÐµ Ð¾Ñ‚Ñ€Ñ‹Ð¶ÐºÐ¸" -> {
            return new int[]{ColorAssist.toColor("#CC4E00"), ColorAssist.toColor("#FFA100")};
         }
         case "Ð—ÐµÐ»ÑŒÐµ ÑÐµÑ€Ð½Ð¾Ð¹ ÐºÐ¸ÑÐ»Ð¾Ñ‚Ñ‹" -> {
            return new int[]{ColorAssist.toColor("#319C00"), ColorAssist.toColor("#4AEB00")};
         }
         case "Ð—ÐµÐ»ÑŒÐµ Ð²ÑÐ¿Ñ‹ÑˆÐºÐ¸" -> {
            return new int[]{ColorAssist.toColor("#D48600"), ColorAssist.toColor("#FFE600")};
         }
         default -> {
            return new int[]{ColorAssist.getText()};
         }
      }
   }

   public static class_5250 applyGradientToText(String text, int[] colors, boolean addStarPrefix) {
      class_5250 result = class_2561.method_43473();
      String fullText = addStarPrefix ? "[â˜…] " + text : text;
      if (colors.length == 2 && addStarPrefix) {
         result.method_10852(class_2561.method_43470("[â˜…] ").method_27692(class_124.field_1070).method_27694((style) -> style.method_36139(colors[0])));
         result.method_10852(class_2561.method_43470(text).method_27692(class_124.field_1070).method_27694((style) -> style.method_36139(colors[1])));
      } else if (colors.length != 0 && (colors.length != 1 || colors[0] != ColorAssist.getText())) {
         int length = fullText.length();
         int colorCount = colors.length;

         for(int i = 0; i < length; ++i) {
            float progress = (float)i / (float)(length - 1);
            int colorIndex = (int)(progress * (float)(colorCount - 1));
            int color1 = colors[colorIndex];
            int color2 = colors[Math.min(colorIndex + 1, colorCount - 1)];
            float segmentProgress = progress * (float)(colorCount - 1) - (float)colorIndex;
            int interpolatedColor = ColorAssist.interpolateColor(color1, color2, segmentProgress);
            result.method_10852(class_2561.method_43470(String.valueOf(fullText.charAt(i))).method_27692(class_124.field_1070).method_27694((style) -> style.method_36139(interpolatedColor)));
         }
      } else {
         result.method_10852(class_2561.method_43470(fullText).method_27692(class_124.field_1070));
      }

      return result;
   }
}

