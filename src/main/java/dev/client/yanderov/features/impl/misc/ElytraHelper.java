package dev.client.yanderov.features.impl.misc;

import antidaunleak.api.annotation.Native;
import dev.client.yanderov.events.keyboard.KeyEvent;
import dev.client.yanderov.events.player.InputEvent;
import dev.client.yanderov.events.player.TickEvent;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.features.module.setting.Setting;
import dev.client.yanderov.features.module.setting.implement.BindSetting;
import dev.client.yanderov.features.module.setting.implement.BooleanSetting;
import dev.client.yanderov.features.module.setting.implement.SelectSetting;
import dev.client.yanderov.features.module.setting.implement.SliderSettings;
import dev.client.yanderov.utils.client.chat.ChatMessage;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import dev.client.yanderov.utils.interactions.interact.PlayerInteractionHelper;
import dev.client.yanderov.utils.interactions.inv.InventoryResult;
import dev.client.yanderov.utils.interactions.inv.InventoryTask;
import dev.client.yanderov.utils.interactions.inv.InventoryToolkit;
import dev.client.yanderov.utils.math.script.Script;
import java.util.List;
import java.util.Objects;
import net.minecraft.class_1268;
import net.minecraft.class_1304;
import net.minecraft.class_1713;
import net.minecraft.class_1735;
import net.minecraft.class_1802;
import net.minecraft.class_3675;
import net.minecraft.class_746;

public class ElytraHelper extends Module {
   private final SelectSetting modeSetting = (new SelectSetting("Ð ÐµÐ¶Ð¸Ð¼", "Ð¡Ð¿Ð¾ÑÐ¾Ð± Ð·Ð°Ð¼ÐµÐ½Ñ‹")).value("Default", "Legit").selected("Default");
   private final BindSetting elytraSetting = new BindSetting("Ð—Ð°Ð¼ÐµÐ½Ð° ÑÐ»Ð¸Ñ‚Ñ€", "ÐœÐµÐ½ÑÐµÑ‚ Ð½Ð°Ð³Ñ€ÑƒÐ´Ð½Ð¸Ðº Ð½Ð° ÑÐ»Ð¸Ñ‚Ñ€Ñ‹");
   private final BindSetting fireworkSetting = new BindSetting("Ð˜ÑÐ¿Ð¾Ð»ÑŒÐ·Ð¾Ð²Ð°Ñ‚ÑŒ Ñ„ÐµÐ¹ÐµÑ€Ð²ÐµÑ€Ðº", "ÐœÐµÐ½ÑÐµÑ‚ Ð¸ Ð¸ÑÐ¿Ð¾Ð»ÑŒÐ·ÑƒÐµÑ‚ Ñ„ÐµÐ¹ÐµÑ€Ð²ÐµÑ€ÐºÐ¸");
   private final BooleanSetting startSetting = (new BooleanSetting("Ð‘Ñ‹ÑÑ‚Ñ€Ñ‹Ð¹ ÑÑ‚Ð°Ñ€Ñ‚", "ÐŸÑ€Ð¸ Ð·Ð°Ð¼ÐµÐ½Ðµ Ð½Ð° ÑÐ»Ð¸Ñ‚Ñ€Ñ‹ Ð°Ð²Ñ‚Ð¾Ð¼Ð°Ñ‚Ð¸Ñ‡ÐµÑÐºÐ¸ Ð²Ð·Ð»ÐµÑ‚Ð°ÐµÑ‚ Ð¸ Ð¸ÑÐ¿Ð¾Ð»ÑŒÐ·ÑƒÐµÑ‚ Ñ„ÐµÐ¹ÐµÑ€Ð²ÐµÑ€ÐºÐ¸")).setValue(false);
   private final BooleanSetting recast = (new BooleanSetting("ÐÐ²Ñ‚Ð¾ Ð²Ð·Ð»ÐµÑ‚", "ÐÐ²Ñ‚Ð¾Ð¼Ð°Ñ‚Ð¸Ñ‡ÐµÑÐºÐ¸ Ð½Ð°Ñ‡Ð¸Ð½Ð°ÐµÑ‚ Ð¿Ð¾Ð»ÐµÑ‚")).setValue(false);
   private final BooleanSetting autoFireworkSetting = (new BooleanSetting("ÐÐ²Ñ‚Ð¾ Ñ„ÐµÐ¹ÐµÑ€Ð²ÐµÑ€Ðº", "ÐÐ²Ñ‚Ð¾Ð¼Ð°Ñ‚Ð¸Ñ‡ÐµÑÐºÐ¸ Ð¸ÑÐ¿Ð¾Ð»ÑŒÐ·Ð¾Ð²Ð°Ñ‚ÑŒ Ñ„ÐµÐ¹ÐµÑ€Ð²ÐµÑ€Ðº ÐºÐ°Ð¶Ð´Ñ‹Ðµ X Ð¼Ñ")).setValue(false);
   private final SliderSettings fireworkDelay = (new SliderSettings("Ð—Ð°Ð´ÐµÑ€Ð¶ÐºÐ° Ð¸ÑÐ¿Ð¾Ð»ÑŒÐ·Ð¾Ð²Ð°Ð½Ð¸Ñ Ð² Ð¼Ñ", "Ð—Ð°Ð´ÐµÑ€Ð¶ÐºÐ° Ð¸ÑÐ¿Ð¾Ð»ÑŒÐ·Ð¾Ð²Ð°Ð½Ð¸Ñ Ñ„ÐµÐ¹ÐµÑ€Ð²ÐµÑ€ÐºÐ° Ð² Ð¼Ñ")).setValue(500.0F).range(10.0F, 1500.0F).visible(() -> this.autoFireworkSetting.isValue());
   private final Script script = new Script();
   private ElytraPhase elytraPhase;
   private long actionStartTime;
   private class_1735 targetSlot;
   private boolean playerFullyStopped;
   private boolean wasForwardPressed;
   private boolean wasBackPressed;
   private boolean wasLeftPressed;
   private boolean wasRightPressed;
   private boolean wasJumpPressed;
   private boolean keysOverridden;
   private FireworkPhase fireworkPhase;
   private int fireworkSlot;
   private int savedHotbarSlot;
   private int originalHotbarSlot;
   private long useDelayStartTime;
   private long lastAutoFireworkTime;

   public ElytraHelper() {
      super("ElytraHelper", "Elytra Helper", ModuleCategory.MISC);
      this.elytraPhase = ElytraHelper.ElytraPhase.READY;
      this.actionStartTime = 0L;
      this.targetSlot = null;
      this.playerFullyStopped = false;
      this.keysOverridden = false;
      this.fireworkPhase = ElytraHelper.FireworkPhase.READY;
      this.fireworkSlot = -1;
      this.savedHotbarSlot = -1;
      this.originalHotbarSlot = -1;
      this.useDelayStartTime = 0L;
      this.lastAutoFireworkTime = 0L;
      this.setup(new Setting[]{this.modeSetting, this.elytraSetting, this.fireworkSetting, this.startSetting, this.recast, this.autoFireworkSetting, this.fireworkDelay});
   }

   @EventHandler
   public void onInput(InputEvent e) {
      if (mc.field_1724.method_6118(class_1304.field_6174).method_7909().equals(class_1802.field_8833) && this.recast.isValue()) {
         if (mc.field_1724.method_24828()) {
            e.setJumping(true);
         } else if (!mc.field_1724.method_6128()) {
            PlayerInteractionHelper.startFallFlying();
         }
      }

   }

   @EventHandler
   public void onKey(KeyEvent e) {
      if (this.script.isFinished()) {
         if (e.isKeyDown(this.elytraSetting.getKey())) {
            if (this.modeSetting.getSelected().equals("Default")) {
               this.executeDefaultSwap();
            } else if (this.modeSetting.getSelected().equals("Legit") && this.elytraPhase == ElytraHelper.ElytraPhase.READY) {
               this.startLegitSwap();
            }
         } else if (e.isKeyDown(this.fireworkSetting.getKey()) && mc.field_1724.method_6128() && this.fireworkPhase == ElytraHelper.FireworkPhase.READY) {
            this.fireworkPhase = ElytraHelper.FireworkPhase.START;
         }

      }
   }

   @EventHandler
   @Native(
      type = Native.Type.VMProtectBeginUltra
   )
   public void onTick(TickEvent e) {
      this.script.update();
      if (mc.field_1724 != null && !mc.field_1724.method_6118(class_1304.field_6174).method_7909().equals(class_1802.field_8833) && mc.field_1724.method_6128()) {
         try {
            mc.field_1724.method_23670();
         } catch (Throwable var8) {
         }
      }

      if (this.elytraPhase != ElytraHelper.ElytraPhase.READY) {
         this.processLegitSwap();
      }

      if (this.fireworkPhase != ElytraHelper.FireworkPhase.READY) {
         if (this.modeSetting.getSelected().equals("Default")) {
            this.processDefaultFireworkUsage();
         } else {
            this.processLegitFireworkUsage();
         }
      }

      if (this.autoFireworkSetting.isValue() && mc.field_1724 != null && mc.field_1724.method_6118(class_1304.field_6174).method_7909().equals(class_1802.field_8833)) {
         long now = System.currentTimeMillis();
         if (now - this.lastAutoFireworkTime >= (long)this.fireworkDelay.getValue() && this.fireworkPhase == ElytraHelper.FireworkPhase.READY && this.elytraPhase == ElytraHelper.ElytraPhase.READY) {
            if (this.modeSetting.getSelected().equals("Default")) {
               InventoryResult hotbar = InventoryToolkit.findItemInHotBar(class_1802.field_8639);
               if (hotbar.found()) {
                  InventoryTask.swapAndUse(class_1802.field_8639);
                  this.lastAutoFireworkTime = now;
               } else {
                  InventoryResult inv = InventoryToolkit.findItemInInventory(class_1802.field_8639);
                  if (inv.found()) {
                     int fireworkInvSlot = inv.slot();
                     int currentHotbarSlot = mc.field_1724.method_31548().field_7545;
                     InventoryToolkit.clickSlot(fireworkInvSlot, currentHotbarSlot, class_1713.field_7791);
                     PlayerInteractionHelper.interactItem(class_1268.field_5808);
                     InventoryToolkit.clickSlot(fireworkInvSlot, currentHotbarSlot, class_1713.field_7791);
                     this.lastAutoFireworkTime = now;
                  }
               }
            } else {
               this.fireworkPhase = ElytraHelper.FireworkPhase.START;
               this.lastAutoFireworkTime = now;
            }
         }
      }

   }

   private void processDefaultFireworkUsage() {
      if (mc.field_1724 == null) {
         this.resetFireworkState();
      } else {
         InventoryResult hotbar = InventoryToolkit.findItemInHotBar(class_1802.field_8639);
         if (hotbar.found()) {
            InventoryTask.swapAndUse(class_1802.field_8639);
         } else {
            InventoryResult inv = InventoryToolkit.findItemInInventory(class_1802.field_8639);
            if (inv.found()) {
               int fireworkInvSlot = inv.slot();
               int currentHotbarSlot = mc.field_1724.method_31548().field_7545;
               InventoryToolkit.clickSlot(fireworkInvSlot, currentHotbarSlot, class_1713.field_7791);
               PlayerInteractionHelper.interactItem(class_1268.field_5808);
               InventoryToolkit.clickSlot(fireworkInvSlot, currentHotbarSlot, class_1713.field_7791);
            } else {
               ChatMessage.brandmessage("ÐÐµÑ‚Ñƒ Ñ„ÐµÐ¹ÐµÑ€Ð²ÐµÑ€ÐºÐ¾Ð²");
            }
         }

         this.resetFireworkState();
      }
   }

   private void processLegitFireworkUsage() {
      if (mc.field_1724 != null && mc.field_1755 == null) {
         switch (this.fireworkPhase.ordinal()) {
            case 1:
               this.originalHotbarSlot = mc.field_1724.method_31548().field_7545;
               InventoryResult hotbar = InventoryToolkit.findItemInHotBar(class_1802.field_8639);
               if (hotbar.found()) {
                  this.savedHotbarSlot = hotbar.slot();
                  InventoryToolkit.switchTo(this.savedHotbarSlot);
                  if (mc.field_1724.method_6047().method_7909() == class_1802.field_8639) {
                     PlayerInteractionHelper.interactItem(class_1268.field_5808);
                  }

                  this.fireworkPhase = ElytraHelper.FireworkPhase.FINISH;
               } else {
                  InventoryResult inv = InventoryToolkit.findItemInInventory(class_1802.field_8639);
                  if (inv.found()) {
                     this.fireworkSlot = inv.slot();
                     this.savedHotbarSlot = this.originalHotbarSlot;
                     InventoryToolkit.clickSlot(this.fireworkSlot, this.savedHotbarSlot, class_1713.field_7791);
                     if (mc.field_1724.method_6047().method_7909() == class_1802.field_8639) {
                        PlayerInteractionHelper.interactItem(class_1268.field_5808);
                     }

                     InventoryToolkit.clickSlot(this.fireworkSlot, this.savedHotbarSlot, class_1713.field_7791);
                     this.fireworkPhase = ElytraHelper.FireworkPhase.FINISH;
                  } else {
                     ChatMessage.brandmessage("ÐÐµÑ‚Ñƒ Ñ„ÐµÐ¹ÐµÑ€Ð²ÐµÑ€ÐºÐ¾Ð²");
                     this.resetFireworkState();
                  }
               }
               break;
            case 6:
               this.resetFireworkState();
               break;
            default:
               this.resetFireworkState();
         }

      } else {
         this.resetFireworkState();
      }
   }

   private void resetFireworkState() {
      if (this.originalHotbarSlot != -1 && mc.field_1724 != null) {
         InventoryToolkit.switchTo(this.originalHotbarSlot);
      }

      this.fireworkPhase = ElytraHelper.FireworkPhase.READY;
      this.fireworkSlot = -1;
      this.savedHotbarSlot = -1;
      this.originalHotbarSlot = -1;
      this.useDelayStartTime = 0L;
   }

   private void executeDefaultSwap() {
      class_1735 slot = this.chestPlate();
      if (slot != null) {
         boolean elytra = slot.method_7677().method_7909().equals(class_1802.field_8833);
         InventoryTask.moveItem(slot, 6, false, true);
         if (!elytra) {
            try {
               if (mc.field_1724.method_6128()) {
                  mc.field_1724.method_23670();
               }
            } catch (Throwable var4) {
            }
         }

         if (this.startSetting.isValue() && elytra) {
            if (mc.field_1724.method_24828()) {
               mc.field_1724.method_6043();
            }

            PlayerInteractionHelper.startFallFlying();
            this.useFireworkInstant();
         }
      }

   }

   private void startLegitSwap() {
      this.targetSlot = this.chestPlate();
      if (this.targetSlot != null) {
         boolean elytra = this.targetSlot.method_7677().method_7909().equals(class_1802.field_8833);
         InventoryTask.moveItem(this.targetSlot, 6, false, false);
         if (!elytra) {
            try {
               if (mc.field_1724.method_6128()) {
                  mc.field_1724.method_23670();
               }
            } catch (Throwable var3) {
            }
         }

         if (this.startSetting.isValue() && elytra) {
            if (mc.field_1724.method_24828()) {
               mc.field_1724.method_6043();
            }

            PlayerInteractionHelper.startFallFlying();
            this.useFireworkInstant();
         }

         this.elytraPhase = ElytraHelper.ElytraPhase.FINISHED;
         this.resetLegitState();
      }
   }

   private void processLegitSwap() {
      if (mc.field_1724 != null && mc.field_1755 == null) {
         this.resetLegitState();
      } else {
         this.resetLegitState();
      }
   }

   private void restoreKeyStates() {
      boolean currentForward = class_3675.method_15987(mc.method_22683().method_4490(), mc.field_1690.field_1894.method_1429().method_1444());
      boolean currentBack = class_3675.method_15987(mc.method_22683().method_4490(), mc.field_1690.field_1881.method_1429().method_1444());
      boolean currentLeft = class_3675.method_15987(mc.method_22683().method_4490(), mc.field_1690.field_1913.method_1429().method_1444());
      boolean currentRight = class_3675.method_15987(mc.method_22683().method_4490(), mc.field_1690.field_1849.method_1429().method_1444());
      boolean currentJump = class_3675.method_15987(mc.method_22683().method_4490(), mc.field_1690.field_1903.method_1429().method_1444());
      mc.field_1690.field_1894.method_23481(this.wasForwardPressed && currentForward);
      mc.field_1690.field_1881.method_23481(this.wasBackPressed && currentBack);
      mc.field_1690.field_1913.method_23481(this.wasLeftPressed && currentLeft);
      mc.field_1690.field_1849.method_23481(this.wasRightPressed && currentRight);
      mc.field_1690.field_1903.method_23481(this.wasJumpPressed && currentJump);
      this.keysOverridden = false;
   }

   private float lerp(float start, float end, float delta) {
      return start + (end - start) * delta;
   }

   private void resetLegitState() {
      if (this.keysOverridden) {
         this.restoreKeyStates();
      }

      this.elytraPhase = ElytraHelper.ElytraPhase.READY;
      this.targetSlot = null;
      this.playerFullyStopped = false;
   }

   private class_1735 chestPlate() {
      return ((class_746)Objects.requireNonNull(mc.field_1724)).method_6118(class_1304.field_6174).method_7909().equals(class_1802.field_8833) ? InventoryTask.getSlot(List.of(class_1802.field_22028, class_1802.field_8058, class_1802.field_8523, class_1802.field_8678, class_1802.field_8873, class_1802.field_8577)) : InventoryTask.getSlot(class_1802.field_8833);
   }

   private void useFireworkInstant() {
      if (mc.field_1724 != null) {
         InventoryResult hotbar = InventoryToolkit.findItemInHotBar(class_1802.field_8639);
         if (hotbar.found()) {
            InventoryTask.swapAndUse(class_1802.field_8639);
         } else {
            InventoryResult inv = InventoryToolkit.findItemInInventory(class_1802.field_8639);
            if (inv.found()) {
               int fireworkInvSlot = inv.slot();
               int currentHotbarSlot = mc.field_1724.method_31548().field_7545;
               InventoryToolkit.clickSlot(fireworkInvSlot, currentHotbarSlot, class_1713.field_7791);
               PlayerInteractionHelper.interactItem(class_1268.field_5808);
               InventoryToolkit.clickSlot(fireworkInvSlot, currentHotbarSlot, class_1713.field_7791);
            }
         }
      }
   }

   static enum ElytraPhase {
      READY,
      SLOWING_DOWN,
      WAITING_STOP,
      SWAP,
      SPEEDING_UP,
      FINISHED;

      // $FF: synthetic method
      private static ElytraPhase[] $values() {
         return new ElytraPhase[]{READY, SLOWING_DOWN, WAITING_STOP, SWAP, SPEEDING_UP, FINISHED};
      }
   }

   static enum FireworkPhase {
      READY,
      START,
      SWAP_TO_HOTBAR,
      WAITING_TO_USE,
      USE_FIREWORK,
      SWAP_BACK_TO_INV,
      FINISH;

      // $FF: synthetic method
      private static FireworkPhase[] $values() {
         return new FireworkPhase[]{READY, START, SWAP_TO_HOTBAR, WAITING_TO_USE, USE_FIREWORK, SWAP_BACK_TO_INV, FINISH};
      }
   }
}

