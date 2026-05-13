package dev.client.modules.impl.combat;

import com.mojang.authlib.GameProfile;
import dev.client.event.classes.ReceivePacketEvent;
import dev.client.event.classes.TickEvent;
import dev.client.event.interfaces.IReceivePacketable;
import dev.client.event.interfaces.ITickable;
import dev.client.modules.Category;
import dev.client.modules.IDisableable;
import dev.client.modules.Module;
import dev.client.modules.ModuleBranding;
import dev.client.modules.settings.impl.BooleanSetting;
import dev.client.modules.settings.impl.ModeSetting;
import dev.client.util.IUtil;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity.RemovalReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.PlayerListS2CPacket;
import net.minecraft.network.packet.s2c.play.PlayerRemoveS2CPacket;
import net.minecraft.util.Hand;

@Environment(EnvType.CLIENT)
public class AntiBot extends Module implements ITickable, IReceivePacketable, IUtil, IDisableable {
   Set<UUID> suspectSet = new HashSet<>();
   static Set<UUID> botSet = new HashSet<>();
   private final ModeSetting mode = new ModeSetting().name("Mode").value("Matrix").modes("Matrix", "ReallyWorld");
   public final BooleanSetting removeBot = new BooleanSetting().name("RemoveBot").value(false);

   public AntiBot() {
      super(new ModuleBranding("AntiBot", Category.COMBAT, "Спасает от банов, определяя серверных ботов"));
      this.addSetting(this.mode, this.removeBot);
   }

   public void onReceivePacket(ReceivePacketEvent receivePacketEvent) {
      Packet<?> packet = receivePacketEvent.getPacket();
      if (packet instanceof PlayerListS2CPacket list) {
         this.checkPlayerAfterSpawn(list);
      } else if (packet instanceof PlayerRemoveS2CPacket remove) {
         this.removePlayerBecauseLeftServer(remove);
      }
   }

   public void onTick(TickEvent event) {
      if (mc.world == null) return;

      if (!this.suspectSet.isEmpty()) {
         mc.world.getPlayers().stream().filter((p) -> p != null && this.suspectSet.contains(p.getUuid())).forEach(this::evaluateSuspectPlayer);
      }

      if (this.mode.is("Matrix")) {
         this.matrixMode();
      } else if (this.mode.is("ReallyWorld")) {
         this.ReallyWorldMode();
      }

      if (this.removeBot.getValue()) {
         List<Integer> toRemove = mc.world.getPlayers().stream().filter((p) -> p != null && p.getUuid() != null && botSet.contains(p.getUuid())).map((p) -> p.getId()).toList();
         toRemove.forEach((id) -> mc.world.removeEntity(id, RemovalReason.DISCARDED));
      }

   }

   private void checkPlayerAfterSpawn(PlayerListS2CPacket listS2CPacket) {
      listS2CPacket.getPlayerAdditionEntries().forEach((entry) -> {
         GameProfile profile = entry.profile();
         if (profile != null && !this.isRealPlayer(entry, profile)) {
            if (this.isDuplicateProfile(profile)) {
               botSet.add(profile.getId());
            } else {
               this.suspectSet.add(profile.getId());
            }

         }
      });
   }

   private void removePlayerBecauseLeftServer(PlayerRemoveS2CPacket removeS2CPacket) {
      removeS2CPacket.profileIds().forEach((uuid) -> {
         this.suspectSet.remove(uuid);
         botSet.remove(uuid);
      });
   }

   private boolean isRealPlayer(PlayerListS2CPacket.Entry entry, GameProfile profile) {
      return entry.latency() < 2 || (profile.getProperties() != null && !profile.getProperties().isEmpty());
   }

   private void evaluateSuspectPlayer(PlayerEntity player) {
      Iterable<ItemStack> armor = null;
      if (!this.isFullyEquipped(player)) {
         armor = player.getArmorItems();
      }

      if (this.isFullyEquipped(player) || this.hasArmorChanged(player, armor)) {
         botSet.add(player.getUuid());
      }

      this.suspectSet.remove(player.getUuid());
   }

   private void matrixMode() {
      if (mc.world == null) return;

      for(Iterator<UUID> iterator = this.suspectSet.iterator(); iterator.hasNext(); ) {
         UUID susPlayer = iterator.next();
         PlayerEntity entity = mc.world.getPlayerByUuid(susPlayer);
         if (entity != null) {
            String playerName = entity.getName().getString();
            boolean isNameBot = playerName.startsWith("CIT-") && !playerName.contains("NPC") && !playerName.contains("[ZNPC]");
            int armorCount = 0;

            for(ItemStack item : entity.getArmorItems()) {
               if (!item.isEmpty()) {
                  ++armorCount;
               }
            }

            boolean isFullArmor = armorCount == 4;
            boolean isFakeUUID = !entity.getUuid().equals(UUID.nameUUIDFromBytes(("OfflinePlayer:" + playerName).getBytes()));
            if (isFullArmor || isNameBot || isFakeUUID) {
               botSet.add(susPlayer);
            }
            iterator.remove();
         }
      }

      if (mc.player != null && mc.player.age % 100 == 0) {
         botSet.removeIf((uuid) -> mc.world.getPlayerByUuid(uuid) == null);
      }

   }

   private void ReallyWorldMode() {
      if (mc.world == null) return;
      for(PlayerEntity entity : mc.world.getPlayers()) {
         if (entity != null && !entity.getUuid().equals(UUID.nameUUIDFromBytes(("OfflinePlayer:" + entity.getName().getString()).getBytes())) && !botSet.contains(entity.getUuid()) && !entity.getName().getString().contains("NPC") && !entity.getName().getString().startsWith("[ZNPC]")) {
            botSet.add(entity.getUuid());
         }
      }

   }

   public boolean isDuplicateProfile(GameProfile profile) {
      if (mc.getNetworkHandler() == null || profile == null) return false;
      ClientPlayNetworkHandler networkHandler = mc.getNetworkHandler();
      return networkHandler.getPlayerList().stream().anyMatch((player) -> player.getProfile().getName().equals(profile.getName()) && !player.getProfile().getId().equals(profile.getId()));
   }

   public boolean isFullyEquipped(PlayerEntity entity) {
      PlayerInventory inventory = entity.getInventory();
      return IntStream.rangeClosed(0, 3).mapToObj(inventory::getArmorStack).allMatch((stack) -> stack.getItem() instanceof ArmorItem && !stack.hasEnchantments());
   }

   public boolean hasArmorChanged(PlayerEntity entity, Iterable<ItemStack> prevArmor) {
      if (prevArmor == null) {
         return true;
      } else {
         List<ItemStack> currentArmorList = StreamSupport.stream(entity.getArmorItems().spliterator(), false).toList();
         List<ItemStack> prevArmorList = StreamSupport.stream(prevArmor.spliterator(), false).toList();
         return !IntStream.range(0, Math.min(currentArmorList.size(), prevArmorList.size())).allMatch((i) -> ((ItemStack)currentArmorList.get(i)).equals(prevArmorList.get(i))) || currentArmorList.size() != prevArmorList.size();
      }
   }

   public boolean isBot(LivingEntity entity) {
      if (entity == null) return false;
      String playerName = entity.getName().getString();
      boolean isNameBot = playerName.startsWith("CIT-") && !playerName.contains("NPC") && !playerName.startsWith("[ZNPC]");
      boolean isMarkedBot = botSet.contains(entity.getUuid());
      return isNameBot || isMarkedBot || this.isBotU(entity);
   }

   public boolean isBot(UUID uuid) {
      return uuid != null && botSet.contains(uuid);
   }

   public boolean isBotU(Entity entity) {
      if (entity == null) return false;
      return !entity.getUuid().equals(UUID.nameUUIDFromBytes(("OfflinePlayer:" + entity.getName().getString()).getBytes())) && entity.isInvisible() && !entity.getName().getString().contains("NPC") && !entity.getName().getString().startsWith("[ZNPC]");
   }

   public void reset() {
      this.suspectSet.clear();
      botSet.clear();
   }

   public void onDisable() {
      this.reset();
   }
}
