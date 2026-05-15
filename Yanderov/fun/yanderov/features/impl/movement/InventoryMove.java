package fun.Yanderov.features.impl.movement;

import antidaunleak.api.annotation.Native;
import fun.Yanderov.events.container.CloseScreenEvent;
import fun.Yanderov.events.item.ClickSlotEvent;
import fun.Yanderov.events.packet.PacketEvent;
import fun.Yanderov.events.player.TickEvent;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.features.module.setting.Setting;
import fun.Yanderov.features.module.setting.implement.BooleanSetting;
import fun.Yanderov.features.module.setting.implement.SelectSetting;
import fun.Yanderov.features.module.setting.implement.SliderSettings;
import fun.Yanderov.utils.client.Instance;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import fun.Yanderov.utils.interactions.interact.PlayerInteractionHelper;
import fun.Yanderov.utils.interactions.inv.InventoryFlowManager;
import fun.Yanderov.utils.interactions.inv.InventoryTask;
import fun.Yanderov.utils.interactions.simulate.Simulations;
import fun.Yanderov.utils.network.PacketFactory;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMaps;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import net.minecraft.class_1713;
import net.minecraft.class_1799;
import net.minecraft.class_2596;
import net.minecraft.class_2645;
import net.minecraft.class_2813;
import net.minecraft.class_2848;
import net.minecraft.class_304;
import net.minecraft.class_3675;
import net.minecraft.class_408;
import net.minecraft.class_490;
import net.minecraft.class_498;
import net.minecraft.class_744;
import net.minecraft.class_2848.class_2849;

public class InventoryMove extends Module {
   private final SelectSetting bypassMode = (new SelectSetting("Ð¾Ð±Ñ…Ð¾Ð´", "ÐœÐµÑ‚Ð¾Ð´ Ñ€Ð°Ð±Ð¾Ñ‚Ñ‹ InventoryMove")).value("V1", "V2 (Alternative)").selected("V1");
   private final List packets = new ArrayList();
   private final List fanTimePackets = new ArrayList();
   private final List fanTimeNewQueue = new ArrayList();
   private final SelectSetting mode = (new SelectSetting("Ð ÐµÐ¶Ð¸Ð¼", "Ð’Ñ‹Ð±ÐµÑ€Ð¸Ñ‚Ðµ Ñ€ÐµÐ¶Ð¸Ð¼ Ð¿ÐµÑ€ÐµÐ´Ð²Ð¸Ð¶ÐµÐ½Ð¸Ñ Ð² Ð¸Ð½Ð²ÐµÐ½Ñ‚Ð°Ñ€Ðµ")).value("Normal", "Legit", "Ð¤Ð°Ð½Ñ‚Ð°Ð¹Ð¼", "Spooky", "Grim").selected("Legit").visible(() -> this.bypassMode.isSelected("V1"));
   private final BooleanSetting syncSwap = (new BooleanSetting("Sync Swaps", "Ð¡Ð¸Ð½Ñ…Ñ€Ð¾Ð½Ð¸Ð·Ð°Ñ†Ð¸Ñ ÑÐ²Ð°Ð¿Ð¾Ð²")).setValue(false).visible(() -> this.bypassMode.isSelected("V1") && !this.mode.isSelected("Normal") && !this.mode.isSelected("Legit"));
   private final BooleanSetting slowInInventory = (new BooleanSetting("Ð—Ð°Ð¼ÐµÐ´Ð»ÐµÐ½Ð¸Ðµ Ð² Ð¸Ð½Ð²ÐµÐ½Ñ‚Ð°Ñ€Ðµ", "Ð—Ð°Ð¼ÐµÐ´Ð»ÑÑ‚ÑŒ Ð´Ð²Ð¸Ð¶ÐµÐ½Ð¸Ðµ Ð¿Ñ€Ð¸ Ð¾Ñ‚ÐºÑ€Ñ‹Ñ‚Ð¾Ð¼ Ð¸Ð½Ð²ÐµÐ½Ñ‚Ð°Ñ€Ðµ (Normal)")).setValue(false).visible(() -> this.bypassMode.isSelected("V1") && this.mode.isSelected("Normal"));
   private final SliderSettings inventorySpeed = (new SliderSettings("Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Ð² Ð¸Ð½Ð²ÐµÐ½Ñ‚Ð°Ñ€Ðµ", "ÐšÐ¾ÑÑ„Ñ„Ð¸Ñ†Ð¸ÐµÐ½Ñ‚ ÑÐºÐ¾Ñ€Ð¾ÑÑ‚Ð¸ (0-1)")).setValue(1.0F).range(0.0F, 1.0F).visible(() -> this.bypassMode.isSelected("V1") && this.mode.isSelected("Normal") && this.slowInInventory.isValue());
   private final BooleanSetting sneakOnPickupWhileMoving = (new BooleanSetting("ÐŸÑ€Ð¸ÑÐµÑÑ‚ÑŒ Ð¿Ñ€Ð¸ Ð²Ð·ÑÑ‚Ð¸Ð¸", "ÐŸÑ€Ð¸ Ð²Ð·ÑÑ‚Ð¸Ð¸ Ð¿Ñ€ÐµÐ´Ð¼ÐµÑ‚Ð° Ð² Ð¸Ð½Ð²ÐµÐ½Ñ‚Ð°Ñ€Ðµ Ð²Ð¾ Ð²Ñ€ÐµÐ¼Ñ Ð±ÐµÐ³Ð° Ð¾Ð´Ð¸Ð½ Ñ€Ð°Ð· Ð¿Ñ€Ð¸ÑÐµÑÑ‚ÑŒ Ð¸ Ð²ÑÑ‚Ð°Ñ‚ÑŒ (Normal)")).setValue(false).visible(() -> this.bypassMode.isSelected("V1") && this.mode.isSelected("Normal"));
   private final BooleanSetting stopMoveWhenDragging = (new BooleanSetting("Ð¡Ñ‚Ð¾Ð¿ Ð¿Ñ€Ð¸ Ð¿ÐµÑ€ÐµÐ½Ð¾ÑÐµ", "ÐÐµ Ð´Ð²Ð¸Ð³Ð°Ñ‚ÑŒÑÑ, Ð¿Ð¾ÐºÐ° Ð¿Ñ€ÐµÐ´Ð¼ÐµÑ‚ Ð² ÐºÑƒÑ€ÑÐ¾Ñ€Ðµ (Normal)")).setValue(false).visible(() -> this.bypassMode.isSelected("V1") && this.mode.isSelected("Normal"));
   private final BooleanSetting funTimeBypass = (new BooleanSetting("ÐžÐ±Ñ…Ð¾Ð´ FunTime", "V2 FunTime Ð¾Ð±Ñ…Ð¾Ð´")).setValue(true).visible(() -> this.bypassMode.isSelected("V2 (Alternative)"));
   private final BooleanSetting grimMultiActionsD = (new BooleanSetting("ÐžÐ±Ñ…Ð¾Ð´ GrimAC", "V2 Grim Ð¾Ð±Ñ…Ð¾Ð´")).setValue(true).visible(() -> this.bypassMode.isSelected("V2 (Alternative)") && this.funTimeBypass.isValue());
   private final BooleanSetting spookyTimeBypass = (new BooleanSetting("ÐžÐ±Ñ…Ð¾Ð´ SpookyTime", "V2 SpookyTime Ð¾Ð±Ñ…Ð¾Ð´")).setValue(false).visible(() -> this.bypassMode.isSelected("V2 (Alternative)") && this.funTimeBypass.isValue());
   private MovePhase movePhase;
   private long actionStartTime;
   private boolean wasForwardPressed;
   private boolean wasBackPressed;
   private boolean wasLeftPressed;
   private boolean wasRightPressed;
   private boolean wasJumpPressed;
   private boolean keysOverridden;
   private boolean inventoryOpened;
   private boolean packetsHeld;
   private int stopTicksOut;
   private boolean stoppedStatus;
   private boolean previousStoppedStatus;
   private int ticksPostOnStop;
   public int tick;
   private final List pendingPackets;
   private ScheduledExecutorService scheduler;
   private final List v2Packets;
   private final List v2DelayedClosePackets;
   private boolean v2WasMoving;
   private long v2LastMovementTime;
   private class_304[] v2MoveKeys;

   public InventoryMove() {
      super("InventoryMove", "Inventory Move", ModuleCategory.MOVEMENT);
      this.movePhase = InventoryMove.MovePhase.READY;
      this.actionStartTime = 0L;
      this.keysOverridden = false;
      this.inventoryOpened = false;
      this.packetsHeld = false;
      this.stopTicksOut = 0;
      this.stoppedStatus = false;
      this.previousStoppedStatus = false;
      this.ticksPostOnStop = 0;
      this.tick = 0;
      this.pendingPackets = new ArrayList();
      this.v2Packets = new CopyOnWriteArrayList();
      this.v2DelayedClosePackets = new CopyOnWriteArrayList();
      this.v2WasMoving = false;
      this.v2LastMovementTime = 0L;
      this.setup(new Setting[]{this.bypassMode, this.mode, this.syncSwap, this.slowInInventory, this.inventorySpeed, this.sneakOnPickupWhileMoving, this.stopMoveWhenDragging, this.funTimeBypass, this.grimMultiActionsD, this.spookyTimeBypass});
   }

   private void startFanTimeNewStop() {
      if (this.stopTicksOut <= 0) {
         this.stopTicksOut = this.stopTicksOut > 1 ? 2 : 3;
      }

   }

   private boolean shouldStopOnDrag() {
      return mc.field_1724 != null && mc.field_1724.field_7512 != null && !mc.field_1724.field_7512.method_34255().method_7960() && !InventoryTask.isServerScreen();
   }

   private void useFanTimeNewPackets() {
      if (!this.fanTimeNewQueue.isEmpty()) {
         this.fanTimeNewQueue.forEach((data) -> PlayerInteractionHelper.sendPacketWithOutEvent(PacketFactory.createClickSlotC2SPacket(data.syncId(), data.slotId(), data.buttonId(), data.actionType(), data.stack(), data.modifiedSlots())));
         this.fanTimeNewQueue.clear();
         this.ticksPostOnStop = 0;
      }
   }

   private boolean isMovingInput() {
      if (mc.field_1724 != null && mc.field_1724.field_3913 != null) {
         return mc.field_1724.field_3913.field_3905 != 0.0F || mc.field_1724.field_3913.field_3907 != 0.0F;
      } else {
         return false;
      }
   }

   @EventHandler
   public void onPacket(PacketEvent e) {
      if (mc.field_1724 != null && mc.field_1724.field_7512 != null) {
         if (this.bypassMode.isSelected("V2 (Alternative)")) {
            class_2596<?> packet = e.getPacket();
            if (mc.field_1755 instanceof class_490) {
               if (this.funTimeBypass.isValue()) {
                  if (packet instanceof class_2813) {
                     class_2813 slot = (class_2813)packet;
                     if (this.isMovingInput()) {
                        this.v2Packets.add(new ClickData(slot.method_12194(), slot.method_12192(), slot.method_12193(), slot.method_12195(), slot.method_12190(), Int2ObjectMaps.emptyMap()));
                        e.cancel();
                     }
                  }

                  if (packet instanceof class_2645) {
                     e.cancel();
                     return;
                  }
               }

               if (this.spookyTimeBypass.isValue()) {
                  if (packet instanceof class_2813) {
                     class_2813 slot = (class_2813)packet;
                     if (Simulations.hasPlayerMovement() && mc.field_1724.method_24828()) {
                        this.v2Packets.add(new ClickData(slot.method_12194(), slot.method_12192(), slot.method_12193(), slot.method_12195(), slot.method_12190(), Int2ObjectMaps.emptyMap()));
                        e.cancel();
                     }
                  }

                  if (packet instanceof class_2645) {
                     class_2645 closePacket = (class_2645)packet;
                     if (Simulations.hasPlayerMovement()) {
                        this.v2DelayedClosePackets.add(closePacket);
                        e.cancel();
                        this.scheduleV2DelayedClosePackets();
                     }
                  }
               }

               if (this.grimMultiActionsD.isValue() && !this.spookyTimeBypass.isValue() && packet instanceof class_2645) {
                  class_2645 closePacket = (class_2645)packet;
                  if (Simulations.hasPlayerMovement()) {
                     this.v2DelayedClosePackets.add(closePacket);
                     e.cancel();
                     this.scheduleV2DelayedClosePackets();
                  }
               }

            }
         } else {
            class_2596 clickData = e.getPacket();
            if (clickData instanceof class_2813) {
               class_2813 slot = (class_2813)clickData;
               ClickData clickData = new ClickData(slot.method_12194(), slot.method_12192(), slot.method_12193(), slot.method_12195(), slot.method_12190(), Int2ObjectMaps.emptyMap());
               if (this.mode.isSelected("Legit")) {
                  if ((this.packetsHeld || Simulations.hasPlayerMovement()) && InventoryFlowManager.shouldSkipExecution()) {
                     this.packets.add(slot);
                     e.cancel();
                     this.packetsHeld = true;
                  }
               } else if (this.mode.isSelected("Ð¤Ð°Ð½Ñ‚Ð°Ð¹Ð¼")) {
                  if (mc.field_1755 != null && !InventoryTask.isServerScreen() && Simulations.hasPlayerMovement()) {
                     this.fanTimePackets.add(clickData);
                     e.cancel();
                  }
               } else if (!this.mode.isSelected("Ð¤Ð°Ð½Ð¢Ð°Ð¹Ð¼ Ð½Ð¾Ð²Ñ‹Ð¹") && !this.mode.isSelected("Ð¡Ð¿ÑƒÐºÐ¸ Ð¢Ð°Ð¹Ð¼")) {
                  if ((this.mode.isSelected("Spooky") || this.mode.isSelected("Grim")) && mc.field_1755 != null && !InventoryTask.isServerScreen() && Simulations.hasPlayerMovement()) {
                     this.pendingPackets.add(clickData);
                     e.cancel();
                  }
               } else if (!InventoryTask.isServerScreen() && Simulations.hasPlayerMovement() && slot.method_12192() != -1) {
                  if (this.mode.isSelected("Ð¡Ð¿ÑƒÐºÐ¸ Ð¢Ð°Ð¹Ð¼")) {
                     this.startFanTimeNewStop();
                     this.stopTicksOut = 4;
                  } else {
                     this.startFanTimeNewStop();
                  }

                  if (!this.stoppedStatus && !this.fanTimeNewQueue.stream().anyMatch((d) -> d.slotId() == slot.method_12192())) {
                     this.fanTimeNewQueue.add(clickData);
                     this.ticksPostOnStop = 0;
                     e.cancel();
                  }
               }
            } else {
               clickData = e.getPacket();
               if (clickData instanceof class_2645) {
                  class_2645 screen = (class_2645)clickData;
                  if (screen.method_36148() == 0) {
                     e.cancel();
                  }
               }
            }

         }
      }
   }

   @EventHandler
   public void onTick(TickEvent event) {
      if (mc.field_1724 != null) {
         class_304[] pressedKeys = new class_304[]{mc.field_1690.field_1894, mc.field_1690.field_1881, mc.field_1690.field_1913, mc.field_1690.field_1849, mc.field_1690.field_1903, mc.field_1690.field_1867};
         if (this.tick != 0) {
            for(class_304 keyBinding : pressedKeys) {
               keyBinding.method_23481(false);
            }

            --this.tick;
         } else if (!(mc.field_1755 instanceof class_408) && !(mc.field_1755 instanceof class_498)) {
            if (this.bypassMode.isSelected("V2 (Alternative)")) {
               if (mc.field_1755 != null) {
                  if (!(mc.field_1755 instanceof class_408) && !(mc.field_1755 instanceof class_498)) {
                     if (this.v2MoveKeys == null && mc.field_1690 != null) {
                        this.v2MoveKeys = new class_304[]{mc.field_1690.field_1894, mc.field_1690.field_1881, mc.field_1690.field_1913, mc.field_1690.field_1849, mc.field_1690.field_1903};
                     }

                     boolean currentlyMoving = Simulations.hasPlayerMovement();
                     if (this.grimMultiActionsD.isValue()) {
                        if (currentlyMoving) {
                           this.v2LastMovementTime = System.currentTimeMillis();
                        }

                        if (this.v2WasMoving && !currentlyMoving && !this.v2DelayedClosePackets.isEmpty()) {
                           this.v2DelayedClosePackets.forEach(PlayerInteractionHelper::sendPacketWithOutEvent);
                           this.v2DelayedClosePackets.clear();
                        }

                        this.v2WasMoving = currentlyMoving;
                     }

                     if (this.v2MoveKeys != null) {
                        for(class_304 keyBinding : this.v2MoveKeys) {
                           keyBinding.method_23481(class_3675.method_15987(mc.method_22683().method_4490(), keyBinding.method_1429().method_1444()));
                        }
                     }

                  }
               }
            } else {
               if (mc.field_1755 != null && (this.mode.isSelected("Spooky") || this.mode.isSelected("Grim"))) {
                  for(class_304 keyBinding : pressedKeys) {
                     keyBinding.method_23481(class_3675.method_15987(mc.method_22683().method_4490(), class_3675.method_15981(keyBinding.method_1428()).method_1444()));
                  }
               }

               if (!this.mode.isSelected("Legit") && !this.mode.isSelected("Ð¤Ð°Ð½Ñ‚Ð°Ð¹Ð¼")) {
                  if (this.mode.isSelected("Ð¤Ð°Ð½Ð¢Ð°Ð¹Ð¼ Ð½Ð¾Ð²Ñ‹Ð¹")) {
                     this.processFanTimeNew();
                  } else if (this.mode.isSelected("Normal") && !InventoryTask.isServerScreen() && InventoryFlowManager.shouldSkipExecution()) {
                     if (this.stopMoveWhenDragging.isValue() && mc.field_1724.field_7512 != null && !mc.field_1724.field_7512.method_34255().method_7960()) {
                        if (mc.field_1724.field_3913 != null) {
                           mc.field_1724.field_3913.field_3905 = 0.0F;
                           mc.field_1724.field_3913.field_3907 = 0.0F;
                        }

                        mc.field_1690.field_1894.method_23481(false);
                        mc.field_1690.field_1881.method_23481(false);
                        mc.field_1690.field_1913.method_23481(false);
                        mc.field_1690.field_1849.method_23481(false);
                        return;
                     }

                     InventoryFlowManager.updateMoveKeys();
                     if (this.slowInInventory.isValue() && mc.field_1724.field_3913 != null) {
                        float factor = this.inventorySpeed.getValue();
                        class_744 var10000 = mc.field_1724.field_3913;
                        var10000.field_3905 *= factor;
                        var10000 = mc.field_1724.field_3913;
                        var10000.field_3907 *= factor;
                     }
                  }
               } else {
                  this.processLegitMovement();
               }

            }
         }
      }
   }

   @EventHandler
   @Native(
      type = Native.Type.VMProtectBeginUltra
   )
   public void onOriginalTick(TickEvent e) {
      if (!this.bypassMode.isSelected("V2 (Alternative)")) {
         if (!this.mode.isSelected("Legit") && !this.mode.isSelected("Ð¤Ð°Ð½Ñ‚Ð°Ð¹Ð¼")) {
            if (this.mode.isSelected("Ð¤Ð°Ð½Ð¢Ð°Ð¹Ð¼ Ð½Ð¾Ð²Ñ‹Ð¹")) {
               this.processFanTimeNew();
            } else if (this.mode.isSelected("Normal") && !InventoryTask.isServerScreen() && InventoryFlowManager.shouldSkipExecution()) {
               if (this.stopMoveWhenDragging.isValue() && mc.field_1724.field_7512 != null && !mc.field_1724.field_7512.method_34255().method_7960()) {
                  if (mc.field_1724.field_3913 != null) {
                     mc.field_1724.field_3913.field_3905 = 0.0F;
                     mc.field_1724.field_3913.field_3907 = 0.0F;
                  }

                  mc.field_1690.field_1894.method_23481(false);
                  mc.field_1690.field_1881.method_23481(false);
                  mc.field_1690.field_1913.method_23481(false);
                  mc.field_1690.field_1849.method_23481(false);
                  return;
               }

               InventoryFlowManager.updateMoveKeys();
               if (this.slowInInventory.isValue() && mc.field_1724.field_3913 != null) {
                  float factor = this.inventorySpeed.getValue();
                  class_744 var10000 = mc.field_1724.field_3913;
                  var10000.field_3905 *= factor;
                  var10000 = mc.field_1724.field_3913;
                  var10000.field_3907 *= factor;
               }
            }
         } else {
            this.processLegitMovement();
         }

      }
   }

   private void processLegitMovement() {
      boolean hasOpenScreen = mc.field_1755 != null;
      if (hasOpenScreen && !this.inventoryOpened && this.movePhase == InventoryMove.MovePhase.READY) {
         this.startLegitMovement();
         this.inventoryOpened = true;
      }

      if (!hasOpenScreen && this.inventoryOpened) {
         if (this.packetsHeld && this.movePhase == InventoryMove.MovePhase.ALLOW_MOVEMENT) {
            this.movePhase = InventoryMove.MovePhase.SLOWING_DOWN;
            this.actionStartTime = System.currentTimeMillis();
         } else if (!this.packetsHeld) {
            this.resetState();
         }

         this.inventoryOpened = false;
      } else {
         if (this.movePhase != InventoryMove.MovePhase.READY) {
            this.handleMovementStates();
         }

      }
   }

   private void processFanTimeNew() {
      boolean hasOpenScreen = mc.field_1755 != null;
      if (!hasOpenScreen && !this.fanTimeNewQueue.isEmpty()) {
         this.fanTimeNewQueue.forEach((data) -> PlayerInteractionHelper.sendPacketWithOutEvent(PacketFactory.createClickSlotC2SPacket(data.syncId(), data.slotId(), data.buttonId(), data.actionType(), data.stack(), data.modifiedSlots())));
         this.fanTimeNewQueue.clear();
         this.stopTicksOut = 0;
         this.stoppedStatus = false;
         this.previousStoppedStatus = false;
      } else {
         if (this.previousStoppedStatus && this.stoppedStatus && this.stopTicksOut > 0) {
            this.useFanTimeNewPackets();
         }

         this.previousStoppedStatus = this.stoppedStatus;
         if (this.shouldStopOnDrag()) {
            this.startFanTimeNewStop();
         } else if (this.stopTicksOut > 0) {
            --this.stopTicksOut;
         }

         this.stoppedStatus = this.stopTicksOut > 0;
         ++this.ticksPostOnStop;
         if (this.stoppedStatus) {
            this.ticksPostOnStop = 0;
         }

         if (this.stoppedStatus && mc.field_1724 != null) {
            if (mc.field_1724.field_3913 != null) {
               mc.field_1724.field_3913.field_3905 = 0.0F;
               mc.field_1724.field_3913.field_3907 = 0.0F;
            }

            mc.field_1690.field_1894.method_23481(false);
            mc.field_1690.field_1881.method_23481(false);
            mc.field_1690.field_1913.method_23481(false);
            mc.field_1690.field_1849.method_23481(false);
            mc.field_1690.field_1903.method_23481(false);
         }

      }
   }

   private void startLegitMovement() {
      this.wasForwardPressed = class_3675.method_15987(mc.method_22683().method_4490(), mc.field_1690.field_1894.method_1429().method_1444());
      this.wasBackPressed = class_3675.method_15987(mc.method_22683().method_4490(), mc.field_1690.field_1881.method_1429().method_1444());
      this.wasLeftPressed = class_3675.method_15987(mc.method_22683().method_4490(), mc.field_1690.field_1913.method_1429().method_1444());
      this.wasRightPressed = class_3675.method_15987(mc.method_22683().method_4490(), mc.field_1690.field_1849.method_1429().method_1444());
      this.wasJumpPressed = class_3675.method_15987(mc.method_22683().method_4490(), mc.field_1690.field_1903.method_1429().method_1444());
      this.movePhase = InventoryMove.MovePhase.ALLOW_MOVEMENT;
      this.keysOverridden = false;
      this.packetsHeld = false;
   }

   private void handleMovementStates() {
      long elapsed = System.currentTimeMillis() - this.actionStartTime;
      switch (this.movePhase.ordinal()) {
         case 1:
            if (mc.field_1724 != null && mc.field_1724.field_3913 != null) {
               mc.field_1724.field_3913.field_3905 = 0.0F;
               mc.field_1724.field_3913.field_3907 = 0.0F;
            }

            if (!this.keysOverridden) {
               mc.field_1690.field_1894.method_23481(false);
               mc.field_1690.field_1881.method_23481(false);
               mc.field_1690.field_1913.method_23481(false);
               mc.field_1690.field_1849.method_23481(false);
               mc.field_1690.field_1903.method_23481(false);
               this.keysOverridden = true;
            }

            if (elapsed > 1L) {
               this.movePhase = InventoryMove.MovePhase.SEND_PACKETS;
               this.actionStartTime = System.currentTimeMillis();
            }
            break;
         case 2:
            if (!InventoryTask.isServerScreen() && InventoryFlowManager.shouldSkipExecution()) {
               InventoryFlowManager.updateMoveKeys();
            }
            break;
         case 3:
            long speedupElapsed = System.currentTimeMillis() - this.actionStartTime;
            float speedupProgress = Math.min(1.0F, (float)speedupElapsed / 1.0F);
            if (this.keysOverridden) {
               this.restoreKeyStates();
            }

            if (mc.field_1724 != null && mc.field_1724.field_3913 != null) {
               boolean forward = class_3675.method_15987(mc.method_22683().method_4490(), mc.field_1690.field_1894.method_1429().method_1444());
               float targetForward = forward ? 1.0F : 0.0F;
               mc.field_1724.field_3913.field_3905 = this.lerp(mc.field_1724.field_3913.field_3905, targetForward * speedupProgress, 0.4F);
               if (speedupProgress > 0.5F && forward && !mc.field_1724.method_5624()) {
                  mc.field_1724.method_5728(true);
               }
            }

            if (speedupElapsed > 1L) {
               this.movePhase = InventoryMove.MovePhase.FINISHED;
            }
            break;
         case 4:
            if (!this.packets.isEmpty()) {
               this.packets.forEach(PlayerInteractionHelper::sendPacketWithOutEvent);
               this.packets.clear();
               InventoryTask.updateSlots();
            }

            this.packetsHeld = false;
            this.movePhase = InventoryMove.MovePhase.SPEEDING_UP;
            this.actionStartTime = System.currentTimeMillis();
            break;
         case 5:
            this.resetState();
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

   private void resetState() {
      if (this.keysOverridden) {
         this.restoreKeyStates();
      }

      this.movePhase = InventoryMove.MovePhase.READY;
      this.inventoryOpened = false;
      this.packetsHeld = false;
      this.packets.clear();
      this.fanTimePackets.clear();
      this.fanTimeNewQueue.clear();
      this.stopTicksOut = 0;
      this.stoppedStatus = false;
      this.previousStoppedStatus = false;
      this.ticksPostOnStop = 0;
   }

   @EventHandler
   public void onClickSlot(ClickSlotEvent e) {
      if (!this.bypassMode.isSelected("V2 (Alternative)")) {
         if (!this.mode.isSelected("Legit") && !this.mode.isSelected("Ð¤Ð°Ð½Ñ‚Ð°Ð¹Ð¼")) {
            if (this.mode.isSelected("Normal") && this.sneakOnPickupWhileMoving.isValue() && mc.field_1724 != null) {
               class_1713 type = e.getActionType();
               boolean isPickup = type == class_1713.field_7790 || type == class_1713.field_7793;
               if (isPickup) {
                  boolean moving = class_3675.method_15987(mc.method_22683().method_4490(), mc.field_1690.field_1894.method_1429().method_1444()) || class_3675.method_15987(mc.method_22683().method_4490(), mc.field_1690.field_1881.method_1429().method_1444()) || class_3675.method_15987(mc.method_22683().method_4490(), mc.field_1690.field_1913.method_1429().method_1444()) || class_3675.method_15987(mc.method_22683().method_4490(), mc.field_1690.field_1849.method_1429().method_1444());
                  if (moving && mc.method_1562() != null) {
                     mc.method_1562().method_52787(new class_2848(mc.field_1724, class_2849.field_12979));
                     mc.method_1562().method_52787(new class_2848(mc.field_1724, class_2849.field_12984));
                  }
               }
            }

         } else {
            class_1713 actionType = e.getActionType();
            if ((this.packetsHeld || Simulations.hasPlayerMovement()) && (e.getButton() == 1 && !actionType.equals(class_1713.field_7791) && !actionType.equals(class_1713.field_7795) || actionType.equals(class_1713.field_7793))) {
               e.cancel();
            }

         }
      }
   }

   @EventHandler
   public void onCloseScreen(CloseScreenEvent e) {
      if (mc.field_1724 != null && mc.field_1687 != null) {
         if (this.bypassMode.isSelected("V2 (Alternative)")) {
            if (this.funTimeBypass.isValue() && e.getScreen() instanceof class_490 && !this.v2Packets.isEmpty()) {
               List<ClickData> toSend = new ArrayList(this.v2Packets);
               this.v2Packets.clear();
               Runnable task = () -> toSend.forEach((data) -> {
                     if (mc.field_1724 != null && mc.field_1724.field_3944 != null) {
                        mc.field_1724.field_3944.method_52787(PacketFactory.createClickSlotC2SPacket(data.syncId(), data.slotId(), data.buttonId(), data.actionType(), data.stack(), data.modifiedSlots()));
                     }

                  });
               InventoryFlowManager.addTask(task);
               e.setCancelled(true);
            }

         } else {
            if (mc.field_1755 instanceof class_490 && !this.pendingPackets.isEmpty() && Simulations.hasPlayerMovement()) {
               if (this.mode.isSelected("Grim") || this.mode.isSelected("Spooky")) {
                  (new Thread(() -> {
                     this.tick = 5;

                     try {
                        Thread.sleep(this.mode.isSelected("Spooky") ? 90L : 40L);
                     } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                     }

                     for(ClickData data : this.pendingPackets) {
                        mc.field_1724.field_3944.method_52787(PacketFactory.createClickSlotC2SPacket(data.syncId(), data.slotId(), data.buttonId(), data.actionType(), data.stack(), data.modifiedSlots()));
                     }

                     this.pendingPackets.clear();
                  })).start();
                  e.setCancelled(true);
               }
            } else {
               this.pendingPackets.clear();
            }

            if (this.mode.isSelected("Legit") && this.packetsHeld && this.movePhase == InventoryMove.MovePhase.ALLOW_MOVEMENT) {
               this.movePhase = InventoryMove.MovePhase.SLOWING_DOWN;
               this.actionStartTime = System.currentTimeMillis();
            }

            if (this.mode.isSelected("Ð¤Ð°Ð½Ñ‚Ð°Ð¹Ð¼") && !this.fanTimePackets.isEmpty()) {
               this.fanTimePackets.forEach((data) -> PlayerInteractionHelper.sendPacketWithOutEvent(PacketFactory.createClickSlotC2SPacket(data.syncId(), data.slotId(), data.buttonId(), data.actionType(), data.stack(), data.modifiedSlots())));
               this.fanTimePackets.clear();
            }

         }
      }
   }

   public static void stopMovementTemporarily(int ticks) {
      InventoryMove inventoryMove = (InventoryMove)Instance.get(InventoryMove.class);
      if (inventoryMove != null && inventoryMove.isState()) {
         inventoryMove.tick = Math.max(inventoryMove.tick, ticks);
      }

   }

   public void deactivate() {
      super.deactivate();
      this.pendingPackets.clear();
      this.resetState();
      if (!this.v2Packets.isEmpty()) {
         this.v2Packets.forEach((data) -> {
            if (mc.field_1724 != null && mc.field_1724.field_3944 != null) {
               mc.field_1724.field_3944.method_52787(PacketFactory.createClickSlotC2SPacket(data.syncId(), data.slotId(), data.buttonId(), data.actionType(), data.stack(), data.modifiedSlots()));
            }

         });
         this.v2Packets.clear();
      }

      if (!this.v2DelayedClosePackets.isEmpty()) {
         this.v2DelayedClosePackets.forEach(PlayerInteractionHelper::sendPacketWithOutEvent);
         this.v2DelayedClosePackets.clear();
      }

      if (this.scheduler != null && !this.scheduler.isShutdown()) {
         this.scheduler.shutdown();
         this.scheduler = null;
      }

   }

   public void activate() {
      super.activate();
      if (this.bypassMode.isSelected("V2 (Alternative)")) {
         this.ensureV2Scheduler();
      }

   }

   private void ensureV2Scheduler() {
      if (this.scheduler == null || this.scheduler.isShutdown()) {
         this.scheduler = Executors.newScheduledThreadPool(1);
      }

   }

   private void scheduleV2DelayedClosePackets() {
      this.ensureV2Scheduler();
      this.scheduler.schedule(() -> {
         if (!this.v2Packets.isEmpty()) {
            this.v2Packets.forEach((data) -> {
               if (mc.field_1724 != null && mc.field_1724.field_3944 != null) {
                  mc.field_1724.field_3944.method_52787(PacketFactory.createClickSlotC2SPacket(data.syncId(), data.slotId(), data.buttonId(), data.actionType(), data.stack(), data.modifiedSlots()));
               }

            });
            this.v2Packets.clear();
         }

         if (!Simulations.hasPlayerMovement() && !this.v2DelayedClosePackets.isEmpty()) {
            this.v2DelayedClosePackets.forEach(PlayerInteractionHelper::sendPacketWithOutEvent);
            this.v2DelayedClosePackets.clear();
         }

      }, 0L, TimeUnit.MILLISECONDS);
   }

   public SelectSetting getBypassMode() {
      return this.bypassMode;
   }

   public List getPackets() {
      return this.packets;
   }

   public List getFanTimePackets() {
      return this.fanTimePackets;
   }

   public List getFanTimeNewQueue() {
      return this.fanTimeNewQueue;
   }

   public SelectSetting getMode() {
      return this.mode;
   }

   public BooleanSetting getSyncSwap() {
      return this.syncSwap;
   }

   public BooleanSetting getSlowInInventory() {
      return this.slowInInventory;
   }

   public SliderSettings getInventorySpeed() {
      return this.inventorySpeed;
   }

   public BooleanSetting getSneakOnPickupWhileMoving() {
      return this.sneakOnPickupWhileMoving;
   }

   public BooleanSetting getStopMoveWhenDragging() {
      return this.stopMoveWhenDragging;
   }

   public BooleanSetting getFunTimeBypass() {
      return this.funTimeBypass;
   }

   public BooleanSetting getGrimMultiActionsD() {
      return this.grimMultiActionsD;
   }

   public BooleanSetting getSpookyTimeBypass() {
      return this.spookyTimeBypass;
   }

   public MovePhase getMovePhase() {
      return this.movePhase;
   }

   public long getActionStartTime() {
      return this.actionStartTime;
   }

   public boolean isWasForwardPressed() {
      return this.wasForwardPressed;
   }

   public boolean isWasBackPressed() {
      return this.wasBackPressed;
   }

   public boolean isWasLeftPressed() {
      return this.wasLeftPressed;
   }

   public boolean isWasRightPressed() {
      return this.wasRightPressed;
   }

   public boolean isWasJumpPressed() {
      return this.wasJumpPressed;
   }

   public boolean isKeysOverridden() {
      return this.keysOverridden;
   }

   public boolean isInventoryOpened() {
      return this.inventoryOpened;
   }

   public boolean isPacketsHeld() {
      return this.packetsHeld;
   }

   public int getStopTicksOut() {
      return this.stopTicksOut;
   }

   public boolean isStoppedStatus() {
      return this.stoppedStatus;
   }

   public boolean isPreviousStoppedStatus() {
      return this.previousStoppedStatus;
   }

   public int getTicksPostOnStop() {
      return this.ticksPostOnStop;
   }

   public int getTick() {
      return this.tick;
   }

   public List getPendingPackets() {
      return this.pendingPackets;
   }

   public ScheduledExecutorService getScheduler() {
      return this.scheduler;
   }

   public List getV2Packets() {
      return this.v2Packets;
   }

   public List getV2DelayedClosePackets() {
      return this.v2DelayedClosePackets;
   }

   public boolean isV2WasMoving() {
      return this.v2WasMoving;
   }

   public long getV2LastMovementTime() {
      return this.v2LastMovementTime;
   }

   public class_304[] getV2MoveKeys() {
      return this.v2MoveKeys;
   }

   private static record ClickData(int syncId, int slotId, int buttonId, class_1713 actionType, class_1799 stack, Int2ObjectMap modifiedSlots) {
   }

   static enum MovePhase {
      READY,
      SLOWING_DOWN,
      ALLOW_MOVEMENT,
      SPEEDING_UP,
      SEND_PACKETS,
      FINISHED;

      // $FF: synthetic method
      private static MovePhase[] $values() {
         return new MovePhase[]{READY, SLOWING_DOWN, ALLOW_MOVEMENT, SPEEDING_UP, SEND_PACKETS, FINISHED};
      }
   }
}

