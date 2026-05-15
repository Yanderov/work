package dev.client.yanderov.display.screens.clickgui.components.implement.autobuy.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import net.minecraft.class_1293;
import net.minecraft.class_1294;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1844;
import net.minecraft.class_1887;
import net.minecraft.class_2561;
import net.minecraft.class_5321;
import net.minecraft.class_6880;
import net.minecraft.class_9290;
import net.minecraft.class_9304;
import net.minecraft.class_9323;
import net.minecraft.class_9334;
import org.apache.commons.lang3.StringUtils;

public class AuctionUtils {
   public static final Pattern funTimePricePattern = Pattern.compile("\\$(\\d+(?:[\\s,]\\d{3})*(?:\\.\\d{2})?)");

   public static int getPrice(class_1799 stack) {
      class_9323 tag = stack.method_57353();
      if (tag == null) {
         return -1;
      } else {
         String priceStr = null;
         String componentString = tag.toString();
         priceStr = StringUtils.substringBetween(componentString, "literal{ $", "}[style={color=green}]");
         if (priceStr == null || priceStr.isEmpty()) {
            String customName = stack.method_7964().getString();
            if (customName != null) {
               Matcher matcher = funTimePricePattern.matcher(customName);
               if (matcher.find()) {
                  priceStr = matcher.group(1);
               }
            }
         }

         if (priceStr != null && !priceStr.isEmpty()) {
            try {
               priceStr = priceStr.replaceAll("[\\s,]", "");
               return Integer.parseInt(priceStr);
            } catch (NumberFormatException var6) {
               return -1;
            }
         } else {
            return -1;
         }
      }
   }

   private static String cleanString(String str) {
      return str == null ? "" : str.toLowerCase().trim().replaceAll("Â§.", "").replaceAll("[^a-zÐ°-ÑÑ‘0-9\\s\\[\\]â˜…+]", "").replaceAll("\\s+", " ");
   }

   public static boolean isArmorItem(class_1799 stack) {
      return stack.method_7909() == class_1802.field_22027 || stack.method_7909() == class_1802.field_22028 || stack.method_7909() == class_1802.field_22029 || stack.method_7909() == class_1802.field_22030 || stack.method_7909() == class_1802.field_8805 || stack.method_7909() == class_1802.field_8058 || stack.method_7909() == class_1802.field_8348 || stack.method_7909() == class_1802.field_8285 || stack.method_7909() == class_1802.field_8743 || stack.method_7909() == class_1802.field_8523 || stack.method_7909() == class_1802.field_8396 || stack.method_7909() == class_1802.field_8660 || stack.method_7909() == class_1802.field_8862 || stack.method_7909() == class_1802.field_8678 || stack.method_7909() == class_1802.field_8416 || stack.method_7909() == class_1802.field_8753 || stack.method_7909() == class_1802.field_8283 || stack.method_7909() == class_1802.field_8873 || stack.method_7909() == class_1802.field_8218 || stack.method_7909() == class_1802.field_8313 || stack.method_7909() == class_1802.field_8267 || stack.method_7909() == class_1802.field_8577 || stack.method_7909() == class_1802.field_8570 || stack.method_7909() == class_1802.field_8370 || stack.method_7909() == class_1802.field_8090;
   }

   public static boolean hasThornsEnchantment(class_1799 stack) {
      class_9304 enchants = (class_9304)stack.method_57824(class_9334.field_49633);
      if (enchants != null && !enchants.method_57543()) {
         for(class_6880 entry : enchants.method_57534()) {
            String enchantId = entry.method_55840();
            if (enchantId != null) {
               String lowerEnchantId = enchantId.toLowerCase();
               if (lowerEnchantId.contains("thorns") || lowerEnchantId.contains("ÑˆÐ¸Ð¿")) {
                  return true;
               }
            }
         }

         class_9290 lore = (class_9290)stack.method_57824(class_9334.field_49632);
         if (lore != null) {
            for(class_2561 line : lore.comp_2400()) {
               String loreStr = line.getString().toLowerCase();
               if (loreStr.contains("thorns") || loreStr.contains("ÑˆÐ¸Ð¿")) {
                  return true;
               }
            }
         }

         return false;
      } else {
         return false;
      }
   }

   private static boolean isKillerOriginal(class_1799 stack) {
      if (stack.method_7909() != class_1802.field_8436 && stack.method_7909() != class_1802.field_8574) {
         return false;
      } else {
         class_1844 potionContents = (class_1844)stack.method_57824(class_9334.field_49651);
         if (potionContents == null) {
            return false;
         } else {
            List<class_1293> effects = potionContents.comp_2380();
            if (effects.isEmpty()) {
               return false;
            } else {
               boolean hasStrengthIV = false;

               for(class_1293 effect : effects) {
                  int amplifier = effect.method_5578();
                  if (effect.method_5579().method_40225((class_5321)class_1294.field_5910.method_40230().get()) && amplifier >= 3) {
                     hasStrengthIV = true;
                  }
               }

               if (hasStrengthIV) {
                  return true;
               } else {
                  return false;
               }
            }
         }
      }
   }

   public static boolean compareItem(class_1799 a, class_1799 b) {
      if (a.method_7909() != b.method_7909()) {
         return false;
      } else if (isArmorItem(a) && hasThornsEnchantment(a)) {
         return false;
      } else {
         String aName = a.method_7964().getString();
         aName = funTimePricePattern.matcher(aName).replaceAll("").trim();
         String bName = b.method_7964().getString();
         String aNameClean = cleanString(aName);
         String bNameClean = cleanString(bName);
         class_9290 aLore = (class_9290)a.method_57824(class_9334.field_49632);
         class_9290 bLoreComp = (class_9290)b.method_57824(class_9334.field_49632);
         boolean hasLore = bLoreComp != null && !bLoreComp.comp_2400().isEmpty();
         boolean isKillerPotionTemplate = false;
         if (hasLore) {
            for(class_2561 expected : bLoreComp.comp_2400()) {
               String expectedStr = expected.getString().toLowerCase();
               if (expectedStr.contains("ÐºÐ¸Ð»Ð»ÐµÑ€") || expectedStr.contains("killer")) {
                  isKillerPotionTemplate = true;
                  break;
               }
            }
         }

         if (!isKillerPotionTemplate || a.method_7909() != class_1802.field_8436 && a.method_7909() != class_1802.field_8574) {
            if (!hasLore) {
               if (!aNameClean.contains(bNameClean) && !bNameClean.contains(aNameClean)) {
                  return false;
               }
            } else {
               List<class_2561> expectedLore = bLoreComp.comp_2400();
               if (aLore == null || aLore.comp_2400().isEmpty()) {
                  return false;
               }

               List<String> auctionLoreStrings = (List)aLore.comp_2400().stream().map((text) -> cleanString(text.getString())).filter((s) -> !s.isEmpty()).collect(Collectors.toList());
               String auctionLoreJoined = String.join(" ", auctionLoreStrings);
               boolean hasOriginalMarker = false;

               for(String line : auctionLoreStrings) {
                  if (line.contains("Ð¾Ñ€Ð¸Ð³Ð¸Ð½Ð°Ð»ÑŒÐ½Ñ‹Ð¹ Ð¿Ñ€ÐµÐ´Ð¼ÐµÑ‚") || line.contains("â˜…")) {
                     hasOriginalMarker = true;
                  }
               }

               int matchCount = 0;
               int requiredMatches = 0;

               for(class_2561 expected : expectedLore) {
                  String expectedStr = cleanString(expected.getString());
                  if (!expectedStr.isEmpty()) {
                     boolean isOriginalMarker = expectedStr.contains("Ð¾Ñ€Ð¸Ð³Ð¸Ð½Ð°Ð»ÑŒÐ½Ñ‹Ð¹ Ð¿Ñ€ÐµÐ´Ð¼ÐµÑ‚") || expectedStr.contains("â˜…");
                     if (isOriginalMarker) {
                        if (!hasOriginalMarker) {
                           return false;
                        }

                        ++matchCount;
                        ++requiredMatches;
                     } else {
                        ++requiredMatches;
                        boolean found = false;

                        for(String auctionLine : auctionLoreStrings) {
                           if (auctionLine.contains(expectedStr) || expectedStr.contains(auctionLine)) {
                              found = true;
                              break;
                           }
                        }

                        if (!found && auctionLoreJoined.contains(expectedStr)) {
                           found = true;
                        }

                        if (found) {
                           ++matchCount;
                        }
                     }
                  }
               }

               double matchRatio = requiredMatches > 0 ? (double)matchCount / (double)requiredMatches : (double)1.0F;
               if (matchRatio < (double)0.5F) {
                  return false;
               }

               if (hasOriginalMarker) {
                  class_9304 aEnchants = (class_9304)a.method_57824(class_9334.field_49633);
                  class_9304 bEnchants = (class_9304)b.method_57824(class_9334.field_49633);
                  if (bEnchants != null && !bEnchants.method_57543()) {
                     if (aEnchants == null || aEnchants.method_57543()) {
                        return false;
                     }

                     Map<String, Integer> aEnchantMap = new HashMap();

                     for(class_6880 entry : aEnchants.method_57534()) {
                        String enchantId = entry.method_55840();
                        if (enchantId != null) {
                           String enchantName = enchantId.replace("minecraft:", "").toLowerCase();
                           int level = aEnchants.method_57536(entry);
                           aEnchantMap.put(enchantName, level);
                        }
                     }

                     Map<String, Integer> bEnchantMap = new HashMap();

                     for(class_6880 entry : bEnchants.method_57534()) {
                        String enchantId = entry.method_55840();
                        if (enchantId != null) {
                           String enchantName = enchantId.replace("minecraft:", "").toLowerCase();
                           int level = bEnchants.method_57536(entry);
                           bEnchantMap.put(enchantName, level);
                        }
                     }

                     if (bEnchantMap.isEmpty()) {
                        return true;
                     }

                     int enchantMatchCount = 0;

                     for(Map.Entry bEntry : bEnchantMap.entrySet()) {
                        String bEnchantName = (String)bEntry.getKey();
                        Integer aLevel = (Integer)aEnchantMap.get(bEnchantName);
                        if (aLevel != null && aLevel >= 1) {
                           ++enchantMatchCount;
                        }
                     }

                     double enchantMatchRatio = (double)enchantMatchCount / (double)bEnchantMap.size();
                     if (enchantMatchRatio < (double)1.0F) {
                        return false;
                     }
                  }
               }
            }

            return true;
         } else {
            boolean result = isKillerOriginal(a);
            return result;
         }
      }
   }
}

