package dev.client.yanderov.features.impl.combat;

import dev.client.yanderov.common.repository.friend.FriendUtils;
import dev.client.yanderov.events.packet.PacketEvent;
import dev.client.yanderov.events.player.EntitySpawnEvent;
import dev.client.yanderov.events.player.TickEvent;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.features.module.setting.Setting;
import dev.client.yanderov.features.module.setting.implement.MultiSelectSetting;
import dev.client.yanderov.features.module.setting.implement.SliderSettings;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import dev.client.yanderov.utils.interactions.interact.PlayerInteractionHelper;
import dev.client.yanderov.utils.interactions.inv.InventoryFlowManager;
import dev.client.yanderov.utils.interactions.inv.InventoryTask;
import dev.client.yanderov.utils.math.script.Script;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1511;
import net.minecraft.class_1542;
import net.minecraft.class_1657;
import net.minecraft.class_1735;
import net.minecraft.class_1802;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_2885;
import net.minecraft.class_3965;

public class AutoCrystal extends Module {
   private final Script script = new Script();
   private class_2338 obsPosition;
   private final MultiSelectSetting protections = (new MultiSelectSetting("Ð—Ð°Ñ‰Ð¸Ñ‚Ð°", "Ð§Ñ‚Ð¾ Ð½Ðµ Ð²Ð·Ñ€Ñ‹Ð²Ð°Ñ‚ÑŒ")).value("Ð¡ÐµÐ±Ñ", "Ð”Ñ€ÑƒÐ·ÐµÐ¹", "Ð ÐµÑÑƒÑ€ÑÑ‹").selected("Ð¡ÐµÐ±Ñ", "Ð”Ñ€ÑƒÐ·ÐµÐ¹", "Ð ÐµÑÑƒÑ€ÑÑ‹");
   private final SliderSettings itemRange = (new SliderSettings("Ð”Ð¸ÑÑ‚Ð°Ð½Ñ†Ð¸Ñ Ð´Ð¾ Ñ€ÐµÑÑƒÑ€ÑÐ¾Ð²", "ÐœÐ¸Ð½Ð¸Ð¼Ð°Ð»ÑŒÐ½Ð¾Ðµ Ñ€Ð°ÑÑÑ‚Ð¾ÑÐ½Ð¸Ðµ Ð´Ð¾ Ñ€ÐµÑÑƒÑ€ÑÐ¾Ð²")).range(1.0F, 12.0F).setValue(6.0F);

   public AutoCrystal() {
      super("AutoCrystal", "Auto Crystal", ModuleCategory.COMBAT);
      this.setup(new Setting[]{this.protections, this.itemRange});
   }

   public void activate() {
      this.obsPosition = null;
      super.activate();
   }

   @EventHandler
   public void onPacket(PacketEvent e) {
      class_2596 var3 = e.getPacket();
      if (var3 instanceof class_2885 interact) {
         if (interact.method_42080() != 0 && this.script.isFinished() && InventoryFlowManager.script.isFinished()) {
            this.script.addTickStep(0, () -> {
               class_2338 interactPos = interact.method_12543().method_17777();
               class_2338 spawnPos = interactPos.method_10093(interact.method_12543().method_17780());
               class_2338 blockPos = mc.field_1687.method_8320(spawnPos).method_26204().equals(class_2246.field_10540) ? spawnPos : (mc.field_1687.method_8320(interactPos).method_26204().equals(class_2246.field_10540) ? interactPos : null);
               class_1735 crystal = InventoryTask.getSlot(class_1802.field_8301);
               if (blockPos != null && crystal != null && this.isSafePosition(blockPos)) {
                  InventoryFlowManager.addTask(() -> {
                     this.obsPosition = blockPos;
                     InventoryTask.swapHand(crystal, class_1268.field_5808, false);
                     PlayerInteractionHelper.sendSequencedPacket((i) -> new class_2885(class_1268.field_5808, new class_3965(blockPos.method_46558(), class_2350.field_11036, blockPos, false), i));
                     InventoryTask.swapHand(crystal, class_1268.field_5808, false, true);
                     this.script.cleanup().addTickStep(6, () -> this.obsPosition = null);
                  });
               }

            });
         }
      }

   }

   @EventHandler
   public void onEntitySpawnEvent(EntitySpawnEvent e) {
      class_1297 var3 = e.getEntity();
      if (var3 instanceof class_1511 crystal) {
         if (this.obsPosition != null && this.obsPosition.equals(crystal.method_24515().method_10074())) {
            if (this.isSafeToDamage(crystal)) {
               mc.field_1761.method_2918(mc.field_1724, crystal);
            }

            this.obsPosition = null;
            this.script.cleanup();
         }
      }

   }

   @EventHandler
   public void onTick(TickEvent e) {
      this.script.update();
   }

   private boolean isSafePosition(class_2338 pos) {
      if (this.protections.isSelected("Ð¡ÐµÐ±Ñ") && mc.field_1724.method_23318() > (double)pos.method_10264()) {
         return false;
      } else {
         if (this.protections.isSelected("Ð”Ñ€ÑƒÐ·ÐµÐ¹")) {
            for(class_1657 player : mc.field_1687.method_18456()) {
               if (player != mc.field_1724 && FriendUtils.isFriend((class_1297)player) && player.method_23318() > (double)pos.method_10264()) {
                  return false;
               }
            }
         }

         if (this.protections.isSelected("Ð ÐµÑÑƒÑ€ÑÑ‹")) {
            class_243 crystalPos = pos.method_10084().method_46558();
            double range = (double)this.itemRange.getValue();
            class_238 box = new class_238(crystalPos.field_1352 - range, crystalPos.field_1351 - range, crystalPos.field_1350 - range, crystalPos.field_1352 + range, crystalPos.field_1351 + range, crystalPos.field_1350 + range);

            for(class_1297 entity : mc.field_1687.method_8335(mc.field_1724, box)) {
               if (entity instanceof class_1542) {
                  return false;
               }
            }
         }

         return true;
      }
   }

   private boolean isSafeToDamage(class_1511 crystal) {
      class_2338 crystalBlock = crystal.method_24515().method_10074();
      if (this.protections.isSelected("Ð¡ÐµÐ±Ñ") && mc.field_1724.method_23318() > (double)crystalBlock.method_10264()) {
         return false;
      } else {
         if (this.protections.isSelected("Ð”Ñ€ÑƒÐ·ÐµÐ¹")) {
            for(class_1657 player : mc.field_1687.method_18456()) {
               if (player != mc.field_1724 && FriendUtils.isFriend((class_1297)player) && player.method_23318() > (double)crystalBlock.method_10264()) {
                  return false;
               }
            }
         }

         if (this.protections.isSelected("Ð ÐµÑÑƒÑ€ÑÑ‹")) {
            class_243 crystalPos = crystal.method_19538();
            double range = (double)this.itemRange.getValue();
            class_238 box = new class_238(crystalPos.field_1352 - range, crystalPos.field_1351 - range, crystalPos.field_1350 - range, crystalPos.field_1352 + range, crystalPos.field_1351 + range, crystalPos.field_1350 + range);

            for(class_1297 entity : mc.field_1687.method_8335(mc.field_1724, box)) {
               if (entity instanceof class_1542) {
                  return false;
               }
            }
         }

         return true;
      }
   }

   public Script getScript() {
      return this.script;
   }

   public class_2338 getObsPosition() {
      return this.obsPosition;
   }

   public MultiSelectSetting getProtections() {
      return this.protections;
   }

   public SliderSettings getItemRange() {
      return this.itemRange;
   }
}

