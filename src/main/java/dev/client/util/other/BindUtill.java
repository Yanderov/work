package dev.client.util.other;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class BindUtill {
   public static String getBind(int bind) {
      switch (bind) {
         case 66 -> {
            return "B";
         }
         case 67 -> {
            return "C";
         }
         case 68 -> {
            return "D";
         }
         case 69 -> {
            return "E";
         }
         case 70 -> {
            return "F";
         }
         case 71 -> {
            return "G";
         }
         case 72 -> {
            return "H";
         }
         case 73 -> {
            return "I";
         }
         case 74 -> {
            return "J";
         }
         case 75 -> {
            return "K";
         }
         case 76 -> {
            return "L";
         }
         case 77 -> {
            return "M";
         }
         case 78 -> {
            return "N";
         }
         case 79 -> {
            return "O";
         }
         case 80 -> {
            return "P";
         }
         case 81 -> {
            return "Q";
         }
         case 82 -> {
            return "R";
         }
         case 83 -> {
            return "S";
         }
         case 84 -> {
            return "T";
         }
         case 85 -> {
            return "U";
         }
         case 86 -> {
            return "V";
         }
         case 87 -> {
            return "W";
         }
         case 88 -> {
            return "X";
         }
         case 89 -> {
            return "Y";
         }
         case 90 -> {
            return "Z";
         }
         case 258 -> {
            return "TAB";
         }
         case 280 -> {
            return "CAPS";
         }
         case 340 -> {
            return "SHIFT";
         }
         case 341 -> {
            return "CTRL";
         }
         case 342 -> {
            return "ALT";
         }
         case 1330 -> {
            return "M1";
         }
         case 1331 -> {
            return "M2";
         }
         case 1332 -> {
            return "M3";
         }
         case 1333 -> {
            return "M4";
         }
         case 1334 -> {
            return "M5";
         }
         case 1335 -> {
            return "M6";
         }
         case 1336 -> {
            return "M7";
         }
         case 1337 -> {
            return "M8";
         }
         default -> {
            return "n/a";
         }
      }
   }

   public static int getBind(String bind) {
      switch (bind) {
         case "a" -> {
            return 65;
         }
         case "b" -> {
            return 66;
         }
         case "c" -> {
            return 67;
         }
         case "d" -> {
            return 68;
         }
         case "e" -> {
            return 69;
         }
         case "f" -> {
            return 70;
         }
         case "g" -> {
            return 71;
         }
         case "h" -> {
            return 72;
         }
         case "i" -> {
            return 73;
         }
         case "j" -> {
            return 74;
         }
         case "k" -> {
            return 75;
         }
         case "l" -> {
            return 76;
         }
         case "m" -> {
            return 77;
         }
         case "n" -> {
            return 78;
         }
         case "o" -> {
            return 79;
         }
         case "p" -> {
            return 80;
         }
         case "q" -> {
            return 81;
         }
         case "r" -> {
            return 82;
         }
         case "s" -> {
            return 83;
         }
         case "t" -> {
            return 84;
         }
         case "u" -> {
            return 85;
         }
         case "v" -> {
            return 86;
         }
         case "w" -> {
            return 87;
         }
         case "x" -> {
            return 88;
         }
         case "y" -> {
            return 89;
         }
         case "z" -> {
            return 90;
         }
         case "ctrl" -> {
            return 341;
         }
         case "alt" -> {
            return 342;
         }
         case "tab" -> {
            return 258;
         }
         case "shift" -> {
            return 340;
         }
         case "caps" -> {
            return 280;
         }
         case "0" -> {
            return 48;
         }
         case "1" -> {
            return 49;
         }
         case "2" -> {
            return 50;
         }
         case "3" -> {
            return 51;
         }
         case "4" -> {
            return 52;
         }
         case "5" -> {
            return 53;
         }
         case "6" -> {
            return 54;
         }
         case "7" -> {
            return 55;
         }
         case "8" -> {
            return 56;
         }
         case "9" -> {
            return 57;
         }
         default -> {
            return 0;
         }
      }
   }
}
