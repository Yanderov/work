package dev.client.yanderov.display.screens.clickgui.components.implement.autobuy.items.customitem;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import dev.client.yanderov.display.screens.clickgui.components.implement.autobuy.items.AutoBuyableItem;
import dev.client.yanderov.display.screens.clickgui.components.implement.autobuy.settings.AutoBuyItemSettings;
import dev.client.yanderov.display.screens.clickgui.components.implement.autobuy.settings.AutoBuySettingsManager;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1844;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2561;
import net.minecraft.class_9279;
import net.minecraft.class_9290;
import net.minecraft.class_9296;
import net.minecraft.class_9334;

public class CustomItem implements AutoBuyableItem {
   private final String displayName;
   private final class_2487 nbt;
   private final class_1792 material;
   private final int price;
   private final class_1844 potionContents;
   private final List loreTexts;
   private final AutoBuyItemSettings settings;
   private boolean enabled;

   public CustomItem(String displayName, class_2487 nbt, class_1792 material, int price, class_1844 potionContents, List loreTexts) {
      this.displayName = displayName;
      this.nbt = nbt;
      this.material = material;
      this.price = price;
      this.potionContents = potionContents;
      this.loreTexts = loreTexts;
      this.enabled = true;
      this.settings = new AutoBuyItemSettings(price, material, displayName);
      AutoBuySettingsManager.getInstance().loadSettings(displayName, this.settings);
   }

   public CustomItem(String displayName, class_2487 nbt, class_1792 material, int price) {
      this(displayName, nbt, material, price, (class_1844)null, (List)null);
   }

   public String getDisplayName() {
      return this.displayName;
   }

   public class_1799 createItemStack() {
      class_1799 stack = new class_1799(this.material);
      stack.method_57379(class_9334.field_49631, class_2561.method_43470(this.displayName));
      if (this.material == class_1802.field_8574) {
         int var10000;
         switch (this.displayName) {
            case "Ð—ÐµÐ»ÑŒÐµ Ð¾Ñ‚Ñ€Ñ‹Ð¶ÐºÐ¸" -> var10000 = 16735488;
            case "Ð—ÐµÐ»ÑŒÐµ ÑÐµÑ€Ð½Ð¾Ð¹ ÐºÐ¸ÑÐ»Ð¾Ñ‚Ñ‹" -> var10000 = 49664;
            case "Ð—ÐµÐ»ÑŒÐµ Ð²ÑÐ¿Ñ‹ÑˆÐºÐ¸" -> var10000 = 16777215;
            case "Ð—ÐµÐ»ÑŒÐµ Ð¼Ð¾Ñ‡Ð¸ Ð¤Ð»ÐµÑˆÐ°" -> var10000 = 6092799;
            case "Ð—ÐµÐ»ÑŒÐµ Ð¿Ð¾Ð±ÐµÐ´Ð¸Ñ‚ÐµÐ»Ñ" -> var10000 = 65280;
            case "Ð—ÐµÐ»ÑŒÐµ Ð°Ð³ÐµÐ½Ñ‚Ð°" -> var10000 = 16775936;
            case "Ð—ÐµÐ»ÑŒÐµ Ð¼ÐµÐ´Ð¸ÐºÐ°" -> var10000 = 16711902;
            case "Ð—ÐµÐ»ÑŒÐµ ÐºÐ¸Ð»Ð»ÐµÑ€Ð°" -> var10000 = 16711680;
            default -> var10000 = 3694022;
         }

         int color = var10000;
         stack.method_57379(class_9334.field_49651, new class_1844(Optional.empty(), Optional.of(color), List.of(), Optional.empty()));
      } else if (this.potionContents != null) {
         stack.method_57379(class_9334.field_49651, this.potionContents);
      }

      if (this.loreTexts != null) {
         stack.method_57379(class_9334.field_49632, new class_9290(this.loreTexts));
      }

      if (this.material == class_1802.field_8288) {
         stack.method_57379(class_9334.field_49641, true);
      }

      if (this.nbt != null) {
         class_2487 nbtCopy = this.nbt.method_10553();
         if (this.material == class_1802.field_8575 && nbtCopy.method_10573("SkullOwner", 10)) {
            class_2487 skullOwner = nbtCopy.method_10562("SkullOwner");
            UUID id = skullOwner.method_25926("Id");
            GameProfile profile = new GameProfile(id, "");
            if (skullOwner.method_10573("Properties", 10)) {
               class_2487 properties = skullOwner.method_10562("Properties");
               if (properties.method_10573("textures", 9)) {
                  class_2499 textures = properties.method_10554("textures", 10);
                  if (!textures.isEmpty()) {
                     class_2487 textureNbt = textures.method_10602(0);
                     String value = textureNbt.method_10558("Value");
                     profile.getProperties().put("textures", new Property("textures", value));
                  }
               }
            }

            stack.method_57379(class_9334.field_49617, new class_9296(profile));
            nbtCopy.method_10551("SkullOwner");
         }

         if (!nbtCopy.method_33133()) {
            stack.method_57379(class_9334.field_49628, class_9279.method_57456(nbtCopy));
         }
      }

      return stack;
   }

   public int getPrice() {
      return this.price;
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public void setEnabled(boolean enabled) {
      this.enabled = enabled;
   }

   public AutoBuyItemSettings getSettings() {
      return this.settings;
   }
}

