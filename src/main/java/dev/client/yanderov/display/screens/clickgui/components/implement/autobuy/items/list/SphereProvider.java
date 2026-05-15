package dev.client.yanderov.display.screens.clickgui.components.implement.autobuy.items.list;

import dev.client.yanderov.display.screens.clickgui.components.implement.autobuy.items.AutoBuyableItem;
import dev.client.yanderov.display.screens.clickgui.components.implement.autobuy.items.customitem.CustomItem;
import dev.client.yanderov.display.screens.clickgui.components.implement.autobuy.items.defaultsetpricec.Defaultpricec;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import net.minecraft.class_1802;
import net.minecraft.class_1844;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2561;

public class SphereProvider {
   public static List getSpheres() {
      List<AutoBuyableItem> spheres = new ArrayList();
      List<class_2561> andromedaLore = List.of(class_2561.method_43470("Ð¡Ñ„ÐµÑ€Ð° Ñ…Ñ€Ð°Ð½Ð¸Ñ‚ Ð²Ð·Ð³Ð»ÑÐ´"), class_2561.method_43470("ÐÐ½Ð´Ñ€Ð¾Ð¼ÐµÐ´Ñ‹, Ð²ÐµÐ´ÑƒÑ‰Ð¸Ð¹"), class_2561.method_43470("ÑÐºÐ²Ð¾Ð·ÑŒ Ð¼Ñ€Ð°Ðº Ð¸ Ð·Ð²Ñ‘Ð·Ð´Ñ‹"));
      spheres.add(createSphere("[â˜…] Ð¡Ñ„ÐµÑ€Ð° ÐÐ½Ð´Ñ€Ð¾Ð¼ÐµÐ´Ñ‹", "9d1ee31a-65ad-4d5c-850e-b8dda3875e1e", "ewogICJ0aW1lc3RhbXAiIDogMTcxNzM2NTEwODQzNywKICAicHJvZmlsZUlkIiA6ICIzMjNiYjlkYzkwZWU0Nzk5YjUxYzE3NjRmZDRhNjI3OSIsCiAgInByb2ZpbGVOYW1lIiA6ICJOcGllIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzQ0ZmZlM2YzNThmMjA5YmFkOGZmZjRkYzQ4MjQ1ZDliYWYwYTAzMWIzYzFlZTZiNzU4NDYwYTMzOWIxNTE5ZTIiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ==", Defaultpricec.getPrice("Ð¡Ñ„ÐµÑ€Ð° ÐÐ½Ð´Ñ€Ð¾Ð¼ÐµÐ´Ñ‹"), andromedaLore));
      List<class_2561> pandoraLore = List.of(class_2561.method_43470("Ð¡Ñ„ÐµÑ€Ð° Ñ‚Ð°Ð¸Ñ‚ ÑÐµÐºÑ€ÐµÑ‚Ñ‹"), class_2561.method_43470("ÐŸÐ°Ð½Ð´Ð¾Ñ€Ñ‹, Ð´Ð°Ñ€ÑƒÑŽÑ‰Ð¸Ðµ"), class_2561.method_43470("Ð±Ð»Ð°Ð³Ð¾Ð´Ð°Ñ‚ÑŒ ÐµÑ‘ Ñ…Ð¾Ð·ÑÐ¸Ð½Ñƒ"));
      spheres.add(createSphere("[â˜…] Ð¡Ñ„ÐµÑ€Ð° ÐŸÐ°Ð½Ð´Ð¾Ñ€Ñ‹", "812d254a-5d3b-41b6-93f8-bd8b08a0c07c", "ewogICJ0aW1lc3RhbXAiIDogMTcxNzM2NTY2NTExNCwKICAicHJvZmlsZUlkIiA6ICJkNzJlNGJjZDIyZGI0NjQ4OTUxNTc0M2UyYTRmMWFjMCIsCiAgInByb2ZpbGVOYW1lIiA6ICJhdnZheSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS84ZTUxZTY1ZWI0MDUyNzcyMzgyYzllNTA3YTU0YmRlZDQzZTM5Zjc1NWI1ZGRmNTViM2YzOTQ0M2NlZDQ2N2Y0IiwKICAgICAgIm1ldGFkYXRhIiA6IHsKICAgICAgICAibW9kZWwiIDogInNsaW0iCiAgICAgIH0KICAgIH0KICB9Cn0=", Defaultpricec.getPrice("Ð¡Ñ„ÐµÑ€Ð° ÐŸÐ°Ð½Ð´Ð¾Ñ€Ñ‹"), pandoraLore));
      List<class_2561> titanLore = List.of(class_2561.method_43470("ÐœÐ¸Ñ„Ð¸Ñ‡ÐµÑÐºÐ°Ñ ÑÑ„ÐµÑ€Ð° Ð´Ñ€ÐµÐ²Ð½Ð¸Ñ…"), class_2561.method_43470("Ð¢Ð¸Ñ‚Ð°Ð½Ð¾Ð², Ð¾Ð±Ð»Ð°Ð´Ð°ÑŽÑ‰Ð°Ñ"), class_2561.method_43470("Ð¸Ñ… Ð¼Ð¾Ñ‰ÑŒÑŽ Ð¸ Ð¿Ñ€Ð¾Ñ‡Ð½Ð¾ÑÑ‚ÑŒÑŽ"));
      spheres.add(createSphere("[â˜…] Ð¡Ñ„ÐµÑ€Ð° Ð¢Ð¸Ñ‚Ð°Ð½Ð°", "05c21710-125c-4738-a102-2e1a4cd577e1", "ewogICJ0aW1lc3RhbXAiIDogMTc1MDM1NDQ1NTE5MiwKICAicHJvZmlsZUlkIiA6ICJkOTcwYzEzZTM4YWI0NzlhOTY1OGM1ZDQ1MjZkMTM0YiIsCiAgInByb2ZpbGVOYW1lIiA6ICJDcmltcHlMYWNlODUxMjciLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODFlOTY5ODQ1OGI3ODQxYzk2YWU0ZjI0ZWM4NGFlMDE3MjQxMDA2NDFjNTY0ZTJhN2IxODVmNDA2ZThlZDIzIiwKICAgICAgIm1ldGFkYXRhIiA6IHsKICAgICAgICAibW9kZWwiIDogInNsaW0iCiAgICAgIH0KICAgIH0KICB9Cn0=", Defaultpricec.getPrice("Ð¡Ñ„ÐµÑ€Ð° Ð¢Ð¸Ñ‚Ð°Ð½Ð°"), titanLore));
      List<class_2561> apolloLore = List.of(class_2561.method_43470("Ð¡Ð²ÑÑ‚Ð¾Ð¹ ÑÐ²ÐµÑ‚ ÐÐ¿Ð¾Ð»Ð»Ð¾Ð½Ð°,"), class_2561.method_43470("Ð¿ÐµÑ€ÐµÐ¿Ð¾Ð»Ð½ÑÑŽÑ‰Ð¸Ð¹ ÑÐ¸Ð»Ð¾Ð¹,"), class_2561.method_43470("Ñ‚Ð°Ð¸Ñ‚ÑÑ Ð² ÑÑ‚Ð¾Ð¹ ÑÑ„ÐµÑ€Ðµ"));
      spheres.add(createSphere("[â˜…] Ð¡Ñ„ÐµÑ€Ð° ÐÐ¿Ð¾Ð»Ð»Ð¾Ð½Ð°", "478bd194-bd00-4c33-b3df-31115657f9a3", "ewogICJ0aW1lc3RhbXAiIDogMTcxNzM2NjYyNTM0NywKICAicHJvZmlsZUlkIiA6ICJhMjk1ODZmYmU1ZDk0Nzk2OWZjOGQ4ZGE0NzlhNDNlZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJMZXZlMjQiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjQxMTdiNjAxOGZlZjBkNTE1NjcyMTczZTNiMjZlNjYwZDY1MWU1ODc2YmE2ZDAzZTUzNDIyNzBjNDliZWM4MCIsCiAgICAgICJtZXRhZGF0YSIgOiB7CiAgICAgICAgIm1vZGVsIiA6ICJzbGltIgogICAgICB9CiAgICB9CiAgfQp9", Defaultpricec.getPrice("Ð¡Ñ„ÐµÑ€Ð° ÐÐ¿Ð¾Ð»Ð»Ð¾Ð½Ð°"), apolloLore));
      List<class_2561> astreaLore = List.of(class_2561.method_43470("Ð¡Ð¿Ñ€Ð°Ð²ÐµÐ´Ð»Ð¸Ð²Ð¾ÑÑ‚ÑŒ ÐÑÑ‚Ñ€ÐµÐ¸"), class_2561.method_43470("Ð´Ð°Ñ€ÑƒÐµÑ‚ Ð¶Ð¸Ð·Ð½ÑŒ ÐºÐ°Ð¶Ð´Ð¾Ð¼Ñƒ,"), class_2561.method_43470("ÐºÑ‚Ð¾ ÐµÑ‘ Ð´Ð¾ÑÑ‚Ð¾Ð¸Ð½"));
      spheres.add(createSphere("[â˜…] Ð¡Ñ„ÐµÑ€Ð° ÐÑÑ‚Ñ€ÐµÑ", "89e3c3fb-65c0-4960-964a-62416b1b3f14", "ewogICJ0aW1lc3RhbXAiIDogMTcxNzM2NTA2MjQwNywKICAicHJvZmlsZUlkIiA6ICJlMzcxMWU2Y2E0ZmY0NzA4YjY5ZjhiNGZlYzNhZjdhMSIsCiAgInByb2ZpbGVOYW1lIiA6ICJNckJ1cnN0IiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzFhNWFhZGQ1MmE1ZmFiOTcwODgxNDUxYWRmNTZmYmI0OTNhMzU4NTZlYTk2ZjU0ZTMyZWVhNjYyZDc4N2VkMjAiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ==", Defaultpricec.getPrice("Ð¡Ñ„ÐµÑ€Ð° ÐÑÑ‚Ñ€ÐµÑ"), astreaLore));
      List<class_2561> osirisLore = List.of(class_2561.method_43470("Ð¡Ð¸Ð»Ñ‹ Ð¸ Ð¼Ð¾Ñ‰ÑŒ Ð¼Ñ‘Ñ€Ñ‚Ð²Ñ‹Ñ…,"), class_2561.method_43470("Ð´Ð°Ñ€Ð¾Ð²Ð°Ð½Ð½Ñ‹Ðµ ÐžÑÐ¸Ñ€Ð¸ÑÐ¾Ð¼,"), class_2561.method_43470("Ñ‚Ð°ÑÑ‚ÑÑ Ð² ÑÑ‚Ð¾Ð¹ ÑÑ„ÐµÑ€Ðµ"));
      spheres.add(createSphere("[â˜…] Ð¡Ñ„ÐµÑ€Ð° ÐžÑÐ¸Ñ€Ð¸ÑÐ°", "5053c3bc-dda9-437f-8caf-e8517e0154ba", "ewogICJ0aW1lc3RhbXAiIDogMTcxNzM2NjY2Mzg3NiwKICAicHJvZmlsZUlkIiA6ICI3NGEwMzQxNWY1OTI0ZTA4YjMyMGM2MmU1NGE3ZjJhYiIsCiAgInByb2ZpbGVOYW1lIiA6ICJNZXp6aXIiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDgxMzYzNWJkODZiMTcxYmJlMTQzYWQ3MWUwOTAyMjkyNjQ5Y2IzYWI4NDQwZWQwMGY4NWNhNmNhMzgyOTkzNiIsCiAgICAgICJtZXRhZGF0YSIgOiB7CiAgICAgICAgIm1vZGVsIiA6ICJzbGltIgogICAgICB9CiAgICB9CiAgfQp9", Defaultpricec.getPrice("Ð¡Ñ„ÐµÑ€Ð° ÐžÑÐ¸Ñ€Ð¸ÑÐ°"), osirisLore));
      List<class_2561> chimeraLore = List.of(class_2561.method_43470("Ð¡Ñ„ÐµÑ€Ð° Ñ€Ð¾Ð¶Ð´ÐµÐ½Ð° Ð²"), class_2561.method_43470("Ð±Ð¾Ð¶ÐµÑÑ‚Ð²ÐµÐ½Ð½Ð¾-Ð¶Ð³ÑƒÑ‡ÐµÐ¼"), class_2561.method_43470("Ð¿Ð»Ð°Ð¼ÐµÐ½Ð¸ Ð¥Ð¸Ð¼ÐµÑ€Ñ‹"));
      spheres.add(createSphere("[â˜…] Ð¡Ñ„ÐµÑ€Ð° Ð¥Ð¸Ð¼ÐµÑ€Ñ‹", "8ac3951d-c8f9-463c-be7a-f29b558f6376", "ewogICJ0aW1lc3RhbXAiIDogMTcxNzM2NjE4MTEwOSwKICAicHJvZmlsZUlkIiA6ICJiNzRiMGQzNTBkNTk0NTU4YmYyYjBlMDJlYmE4NjE4NCIsCiAgInByb2ZpbGVOYW1lIiA6ICJCcmFuZG9uYnBtMjg0IiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzlmYWJlZWQ0MjRiMjUyYTg5NDVhNjQ0MmI0NjJkNWYzMTQ3MDFhODE2ZGEyZDBhNjljY2RmY2ZkNzQ2ZTU4OGUiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ==", Defaultpricec.getPrice("Ð¡Ñ„ÐµÑ€Ð° Ð¥Ð¸Ð¼ÐµÑ€Ñ‹"), chimeraLore));
      return spheres;
   }

   private static AutoBuyableItem createSphere(String displayName, String headUuid, String texture, int price, List lore) {
      class_2487 nbt = new class_2487();
      nbt.method_10556("HideFlags", true);
      nbt.method_10556("Unbreakable", true);
      class_2487 skullOwner = new class_2487();
      skullOwner.method_25927("Id", UUID.fromString(headUuid));
      class_2487 properties = new class_2487();
      class_2499 textures = new class_2499();
      class_2487 textureNbt = new class_2487();
      textureNbt.method_10582("Value", texture);
      textures.add(textureNbt);
      properties.method_10566("textures", textures);
      skullOwner.method_10566("Properties", properties);
      nbt.method_10566("SkullOwner", skullOwner);
      return new CustomItem(displayName, nbt, class_1802.field_8575, price, (class_1844)null, lore);
   }
}

