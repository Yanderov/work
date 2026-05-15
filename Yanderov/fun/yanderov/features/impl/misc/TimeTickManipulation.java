package fun.Yanderov.features.impl.misc;

import fun.Yanderov.events.packet.PacketEvent;
import fun.Yanderov.events.player.TickEvent;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.features.module.setting.Setting;
import fun.Yanderov.features.module.setting.implement.BooleanSetting;
import fun.Yanderov.features.module.setting.implement.MultiSelectSetting;
import fun.Yanderov.features.module.setting.implement.SelectSetting;
import fun.Yanderov.features.module.setting.implement.SliderSettings;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import fun.Yanderov.utils.display.interfaces.QuickImports;

public final class TimeTickManipulation extends Module implements QuickImports {
   private final SliderSettings nextDelay = (new SliderSettings("Next Delay", "ÐŸÐ°ÑƒÐ·Ð° Ð¼ÐµÐ¶Ð´Ñƒ Ñ†Ð¸ÐºÐ»Ð°Ð¼Ð¸ (Ð¼Ñ)")).setValue(300.0F).range(0.0F, 5000.0F);
   private final SelectSetting packetSendMode = (new SelectSetting("PacketSendMode", "Ð ÐµÐ¶Ð¸Ð¼ Ð²Ð¾Ð·Ð´ÐµÐ¹ÑÑ‚Ð²Ð¸Ñ")).value("Server", "Client", "Both").selected("Server");
   private final SliderSettings tickFreezeDelay = (new SliderSettings("Tick Freeze Delay", "Ð”Ð»Ð¸Ñ‚ÐµÐ»ÑŒÐ½Ð¾ÑÑ‚ÑŒ Ñ„Ñ€Ð¸Ð·Ð° (Ð¼Ñ)")).setValue(200.0F).range(0.0F, 1000.0F);
   private final MultiSelectSetting unfreezeEvents = (new MultiSelectSetting("Unfreeze Event", "Ð¡Ð¾Ð±Ñ‹Ñ‚Ð¸Ñ Ñ€Ð°Ð·Ð¼Ð¾Ñ€Ð¾Ð·ÐºÐ¸")).value("velocity", "move", "useaction", "message", "action").selected("velocity", "move", "useaction", "message", "action");
   private final BooleanSetting sneakDelay = (new BooleanSetting("Sneak Delay", "Ð—Ð°Ð¿ÑƒÑÐº Ñ‚Ð¾Ð»ÑŒÐºÐ¾ Ð¿Ñ€Ð¸ Ð·Ð°Ð¶Ð°Ñ‚Ð¾Ð¼ Shift")).setValue(false);
   private long lastCycleEnd = 0L;
   private long freezeStart = 0L;
   private boolean freezing = false;

   public TimeTickManipulation() {
      super("TimeTickManipulation", ModuleCategory.MISC);
      this.setup(new Setting[]{this.nextDelay, this.packetSendMode, this.tickFreezeDelay, this.unfreezeEvents, this.sneakDelay});
   }

   public void activate() {
      super.activate();
      this.lastCycleEnd = 0L;
      this.freezeStart = 0L;
      this.freezing = false;
   }

   @EventHandler
   public void onTick(TickEvent e) {
      if (e.getType() == 0) {
         if (mc != null && mc.field_1724 != null) {
            long now = System.currentTimeMillis();
            if (this.freezing) {
               if (now - this.freezeStart >= (long)this.tickFreezeDelay.getValue()) {
                  this.freezing = false;
                  this.lastCycleEnd = now;
               }

            } else if (this.lastCycleEnd == 0L || now - this.lastCycleEnd >= (long)this.nextDelay.getValue()) {
               if (this.sneakDelay.isValue()) {
                  try {
                     if (mc.field_1690 == null || !mc.field_1690.field_1832.method_1434()) {
                        return;
                     }
                  } catch (Throwable var5) {
                  }
               }

               this.freezeStart = now;
               this.freezing = true;
            }
         }
      }
   }

   @EventHandler
   public void onPacket(PacketEvent e) {
      if (mc != null && mc.field_1724 != null) {
         long now = System.currentTimeMillis();
         if (this.freezing) {
            if (e.getType() == PacketEvent.Type.RECEIVE) {
               if (this.unfreezeEvents.isSelected("velocity")) {
                  Object pkt = e.getPacket();
                  if (pkt != null) {
                     String name = pkt.getClass().getSimpleName();
                     if (name.contains("Velocity") || name.contains("Explosion")) {
                        this.freezing = false;
                        this.lastCycleEnd = now;
                        return;
                     }
                  }
               }

            } else {
               boolean affectServer = "Server".equals(this.packetSendMode.getSelected()) || "Both".equals(this.packetSendMode.getSelected());
               if (affectServer) {
                  Object pkt = e.getPacket();
                  if (pkt != null) {
                     String simple = pkt.getClass().getSimpleName();
                     boolean shouldUnfreeze = false;
                     if (this.unfreezeEvents.isSelected("message") && (simple.contains("ChatMessage") || simple.contains("Command"))) {
                        shouldUnfreeze = true;
                     }

                     if (this.unfreezeEvents.isSelected("useaction") && (simple.contains("Interact") || simple.contains("Use") || simple.contains("Click") || simple.contains("HandSwing"))) {
                        shouldUnfreeze = true;
                     }

                     if (this.unfreezeEvents.isSelected("action") && (simple.contains("Action") || simple.contains("Button") || simple.contains("Confirm") || simple.contains("PickFromInventory"))) {
                        shouldUnfreeze = true;
                     }

                     if (this.unfreezeEvents.isSelected("move") && (simple.contains("Move") || simple.contains("Input") || simple.equals("PlayerMoveC2SPacket"))) {
                        shouldUnfreeze = true;
                     }

                     if (shouldUnfreeze) {
                        this.freezing = false;
                        this.lastCycleEnd = now;
                     } else {
                        e.cancel();
                     }
                  }
               }
            }
         }
      }
   }
}

