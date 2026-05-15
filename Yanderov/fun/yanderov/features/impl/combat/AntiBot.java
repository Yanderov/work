package fun.Yanderov.features.impl.combat;

import antidaunleak.api.annotation.Native;
import com.mojang.authlib.GameProfile;
import fun.Yanderov.events.packet.PacketEvent;
import fun.Yanderov.events.player.TickEvent;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.features.module.setting.Setting;
import fun.Yanderov.features.module.setting.implement.SelectSetting;
import fun.Yanderov.utils.client.Instance;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_1661;
import net.minecraft.class_1738;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2596;
import net.minecraft.class_2703;
import net.minecraft.class_634;
import net.minecraft.class_7828;

public class AntiBot extends Module {
   private final Set suspectSet = new HashSet();
   static Set botSet = new HashSet();
   private final SelectSetting mode = (new SelectSetting("Ð ÐµÐ¶Ð¸Ð¼", "Ð’Ñ‹Ð±ÐµÑ€Ð¸Ñ‚Ðµ Ñ€ÐµÐ¶Ð¸Ð¼ Ð¾Ð±Ð½Ð°Ñ€ÑƒÐ¶ÐµÐ½Ð¸Ñ Ð±Ð¾Ñ‚Ð¾Ð²")).value("Matrix", "ReallyWorld").selected("ReallyWorld");

   public static AntiBot getInstance() {
      return (AntiBot)Instance.get(AntiBot.class);
   }

   public AntiBot() {
      super("AntiBot", "Anti Bot", ModuleCategory.COMBAT);
      this.setup(new Setting[]{this.mode});
   }

   @EventHandler
   public void onPacket(PacketEvent e) {
      class_2596 var10000 = e.getPacket();
      Objects.requireNonNull(var10000);
      class_2596 var2 = var10000;
      byte var3 = 0;
      //$FF: var3->value
      //0->net/minecraft/class_2703
      //1->net/minecraft/class_7828
      switch (var2.typeSwitch<invokedynamic>(var2, var3)) {
         case 0:
            class_2703 list = (class_2703)var2;
            this.checkPlayerAfterSpawn(list);
            break;
         case 1:
            class_7828 remove = (class_7828)var2;
            this.removePlayerBecauseLeftServer(remove);
      }

   }

   @EventHandler
   @Native(
      type = Native.Type.VMProtectBeginUltra
   )
   public void onTick(TickEvent e) {
      if (!this.suspectSet.isEmpty()) {
         mc.field_1687.method_18456().stream().filter((p) -> this.suspectSet.contains(p.method_5667())).forEach(this::evaluateSuspectPlayer);
      }

      if (this.mode.isSelected("Matrix")) {
         this.matrixMode();
      } else if (this.mode.isSelected("ReallyWorld")) {
         this.ReallyWorldMode();
      }

   }

   private void checkPlayerAfterSpawn(class_2703 listS2CPacket) {
      listS2CPacket.method_46330().forEach((entry) -> {
         GameProfile profile = entry.comp_1107();
         if (profile != null && !this.isRealPlayer(entry, profile)) {
            if (this.isDuplicateProfile(profile)) {
               botSet.add(profile.getId());
            } else {
               this.suspectSet.add(profile.getId());
            }

         }
      });
   }

   private void removePlayerBecauseLeftServer(class_7828 removeS2CPacket) {
      removeS2CPacket.comp_1105().forEach((uuid) -> {
         this.suspectSet.remove(uuid);
         botSet.remove(uuid);
      });
   }

   private boolean isRealPlayer(class_2703.class_2705 entry, GameProfile profile) {
      return entry.comp_1109() < 2 || profile.getProperties() != null && !profile.getProperties().isEmpty();
   }

   private void evaluateSuspectPlayer(class_1657 player) {
      Iterable<class_1799> armor = null;
      if (!this.isFullyEquipped(player)) {
         armor = player.method_5661();
      }

      if (this.isFullyEquipped(player) || this.hasArmorChanged(player, armor)) {
         botSet.add(player.method_5667());
      }

      this.suspectSet.remove(player.method_5667());
   }

   private void matrixMode() {
      for(Iterator<UUID> iterator = this.suspectSet.iterator(); iterator.hasNext(); iterator.remove()) {
         UUID susPlayer = (UUID)iterator.next();
         class_1657 entity = mc.field_1687.method_18470(susPlayer);
         if (entity != null) {
            String playerName = entity.method_5477().getString();
            boolean isNameBot = playerName.startsWith("CIT-") && !playerName.contains("NPC") && !playerName.contains("[ZNPC]");
            int armorCount = 0;

            for(class_1799 item : entity.method_5661()) {
               if (!item.method_7960()) {
                  ++armorCount;
               }
            }

            boolean isFullArmor = armorCount == 4;
            boolean isFakeUUID = !entity.method_5667().equals(UUID.nameUUIDFromBytes(("OfflinePlayer:" + playerName).getBytes()));
            if (isFullArmor || isNameBot || isFakeUUID) {
               botSet.add(susPlayer);
            }
         }
      }

      if (mc.field_1724.field_6012 % 100 == 0) {
         botSet.removeIf((uuid) -> mc.field_1687.method_18470(uuid) == null);
      }

   }

   private void ReallyWorldMode() {
      for(class_1657 entity : mc.field_1687.method_18456()) {
         if (!entity.method_5667().equals(UUID.nameUUIDFromBytes(("OfflinePlayer:" + entity.method_5477().getString()).getBytes())) && !botSet.contains(entity.method_5667()) && !entity.method_5477().getString().contains("NPC") && !entity.method_5477().getString().startsWith("[ZNPC]")) {
            botSet.add(entity.method_5667());
         }
      }

   }

   private void newMatrixMode() {
      for(class_1657 entity : mc.field_1687.method_18456()) {
         if (entity != mc.field_1724) {
            List<class_1799> armorItems = StreamSupport.stream(entity.method_5661().spliterator(), false).toList();
            boolean allArmorValid = true;

            for(class_1799 item : armorItems) {
               if (item.method_7960() || !item.method_7923() || item.method_7919() > 0) {
                  allArmorValid = false;
                  break;
               }
            }

            boolean hasSpecificArmor = false;

            for(class_1799 item : armorItems) {
               if (item.method_7909() == class_1802.field_8370 || item.method_7909() == class_1802.field_8570 || item.method_7909() == class_1802.field_8577 || item.method_7909() == class_1802.field_8267 || item.method_7909() == class_1802.field_8660 || item.method_7909() == class_1802.field_8396 || item.method_7909() == class_1802.field_8523 || item.method_7909() == class_1802.field_8743) {
                  hasSpecificArmor = true;
                  break;
               }
            }

            if (allArmorValid && hasSpecificArmor && entity.method_5998(class_1268.field_5810).method_7909() == class_1802.field_8162 && entity.method_5998(class_1268.field_5808).method_7909() != class_1802.field_8162 && entity.method_7344().method_7586() == 20 && !entity.method_5477().getString().contains("NPC") && !entity.method_5477().getString().startsWith("[ZNPC]")) {
               botSet.add(entity.method_5667());
            } else {
               botSet.remove(entity.method_5667());
            }
         }
      }

   }

   public boolean isDuplicateProfile(GameProfile profile) {
      return ((class_634)Objects.requireNonNull(mc.method_1562())).method_2880().stream().filter((player) -> player.method_2966().getName().equals(profile.getName()) && !player.method_2966().getId().equals(profile.getId())).count() == 1L;
   }

   public boolean isFullyEquipped(class_1657 entity) {
      IntStream var10000 = IntStream.rangeClosed(0, 3);
      class_1661 var10001 = entity.method_31548();
      Objects.requireNonNull(var10001);
      return var10000.mapToObj(var10001::method_7372).allMatch((stack) -> stack.method_7909() instanceof class_1738 && !stack.method_7942());
   }

   public boolean hasArmorChanged(class_1657 entity, Iterable prevArmor) {
      if (prevArmor == null) {
         return true;
      } else {
         List<class_1799> currentArmorList = StreamSupport.stream(entity.method_5661().spliterator(), false).toList();
         List<class_1799> prevArmorList = StreamSupport.stream(prevArmor.spliterator(), false).toList();
         return !IntStream.range(0, Math.min(currentArmorList.size(), prevArmorList.size())).allMatch((i) -> ((class_1799)currentArmorList.get(i)).equals(prevArmorList.get(i))) || currentArmorList.size() != prevArmorList.size();
      }
   }

   public boolean isBot(class_1657 entity) {
      String playerName = entity.method_5477().getString();
      boolean isNameBot = playerName.startsWith("CIT-") && !playerName.contains("NPC") && !playerName.startsWith("[ZNPC]");
      boolean isMarkedBot = botSet.contains(entity.method_5667());
      this.isBotU(entity);
      return isNameBot || isMarkedBot;
   }

   public boolean isBot(UUID uuid) {
      return botSet.contains(uuid);
   }

   public boolean isBotU(class_1297 entity) {
      return !entity.method_5667().equals(UUID.nameUUIDFromBytes(("OfflinePlayer:" + entity.method_5477().getString()).getBytes())) && entity.method_5767() && !entity.method_5477().getString().contains("NPC") && !entity.method_5477().getString().startsWith("[ZNPC]");
   }

   public void reset() {
      this.suspectSet.clear();
      botSet.clear();
   }

   public void deactivate() {
      this.reset();
      super.deactivate();
   }
}

