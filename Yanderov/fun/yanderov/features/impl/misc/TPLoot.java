package fun.Yanderov.features.impl.misc;

import fun.Yanderov.events.player.TickEvent;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.features.module.setting.Setting;
import fun.Yanderov.features.module.setting.implement.SelectSetting;
import fun.Yanderov.features.module.setting.implement.SliderSettings;
import fun.Yanderov.features.module.setting.implement.TextSetting;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import fun.Yanderov.utils.display.interfaces.QuickImports;
import net.minecraft.class_1297;
import net.minecraft.class_1542;
import net.minecraft.class_1738;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1829;
import net.minecraft.class_2338;
import net.minecraft.class_2561;
import net.minecraft.class_2596;
import net.minecraft.class_2828;
import net.minecraft.class_634;

public class TPLoot extends Module implements QuickImports {
   private final SelectSetting lootEnd = (new SelectSetting("ÐŸÐ¾ÑÐ»Ðµ Ð»ÑƒÑ‚Ð°", "Ð”ÐµÐ¹ÑÑ‚Ð²Ð¸Ðµ Ð¿Ð¾ÑÐ»Ðµ Ð¿Ð¾Ð´Ð½ÑÑ‚Ð¸Ñ")).value("ÐÐ¸Ñ‡ÐµÐ³Ð¾", "/hub", "/spawn", "home", "Ð¡Ð²Ð¾Ñ").selected("ÐÐ¸Ñ‡ÐµÐ³Ð¾");
   private final TextSetting homeCmd = (new TextSetting("ÐšÐ¾Ð¼Ð°Ð½Ð´Ð° home", "ÐÐ°Ð¿Ñ€Ð¸Ð¼ÐµÑ€: /home home")).visible(() -> this.lootEnd.isSelected("home")).setText("/home home");
   private final TextSetting customCmd = (new TextSetting("Ð¡Ð²Ð¾Ñ ÐºÐ¾Ð¼Ð°Ð½Ð´Ð°", "ÐšÐ¾Ð¼Ð°Ð½Ð´Ð° Ð¿Ð¾ÑÐ»Ðµ Ð»ÑƒÑ‚Ð°")).visible(() -> this.lootEnd.isSelected("Ð¡Ð²Ð¾Ñ")).setText("/test");
   private final SliderSettings waitMs = (new SliderSettings("Ð—Ð°Ð´ÐµÑ€Ð¶ÐºÐ°, Ð¼Ñ", "ÐžÐ¶Ð¸Ð´Ð°Ð½Ð¸Ðµ Ð¿Ð¾ÑÐ»Ðµ Ð¢ÐŸ Ð¿ÐµÑ€ÐµÐ´ ÐºÐ¾Ð¼Ð°Ð½Ð´Ð¾Ð¹")).setValue(100.0F).range(0.0F, 1000.0F);
   private boolean pendingCheck = false;
   private long checkAt = 0L;

   public TPLoot() {
      super("TPLoot", ModuleCategory.MISC);
      this.setup(new Setting[]{this.lootEnd, this.homeCmd, this.customCmd, this.waitMs});
   }

   @EventHandler
   public void onTick(TickEvent e) {
      if (e.getType() == 0) {
         if (mc != null && mc.field_1724 != null && mc.field_1687 != null) {
            long now = System.currentTimeMillis();
            if (this.pendingCheck && now - this.checkAt >= (long)this.waitMs.getValue()) {
               if (this.hasValuableInHotbar() || this.hasValuableInInventory()) {
                  this.runPostLootCommand();
               }

               this.pendingCheck = false;
            }

            for(class_1297 ent : mc.field_1687.method_18112()) {
               if (ent instanceof class_1542) {
                  class_1542 itemEnt = (class_1542)ent;
                  class_1799 stack = itemEnt.method_6983();
                  if (this.isValuable(stack)) {
                     class_2338 below = itemEnt.method_24515().method_10074();
                     double itemY = itemEnt.method_23318();
                     boolean hasBlockBelow = false;

                     try {
                        hasBlockBelow = !mc.field_1687.method_8320(below).method_26215();
                     } catch (Throwable var22) {
                     }

                     if (hasBlockBelow && itemY - (double)below.method_10264() <= (double)1.0F) {
                        double x = itemEnt.method_23317() + (double)0.5F;
                        double y = itemEnt.method_23318();
                        double z = itemEnt.method_23321() + (double)0.5F;
                        boolean onGround = false;
                        boolean horiz = true;

                        try {
                           class_2828 pkt = new class_2828.class_2830(x, y, z, mc.field_1724.method_36454(), mc.field_1724.method_36455(), onGround, horiz);
                           mc.method_1562().method_52787(pkt);
                           this.pendingCheck = true;
                           this.checkAt = now;
                        } catch (Throwable var21) {
                        }
                     }
                  }
               }
            }

         }
      }
   }

   private boolean isValuable(class_1799 stack) {
      if (stack != null && !stack.method_7960()) {
         class_1792 item = stack.method_7909();

         try {
            return item instanceof class_1738 || item instanceof class_1829 || item == class_1802.field_8575 || item == class_1802.field_8288 || item == class_1802.field_8634 || item == class_1802.field_8301 || item == class_1802.field_8639 || item == class_1802.field_8833 || item == class_1802.field_8463 || item == class_1802.field_8367 || item == class_1802.field_8696 || item == class_1802.field_8135 || item == class_1802.field_22421;
         } catch (Throwable var4) {
            return false;
         }
      } else {
         return false;
      }
   }

   private boolean hasValuableInHotbar() {
      try {
         for(int i = 0; i < 9; ++i) {
            if (this.isValuable(mc.field_1724.method_31548().method_5438(i))) {
               return true;
            }
         }
      } catch (Throwable var2) {
      }

      return false;
   }

   private boolean hasValuableInInventory() {
      try {
         for(int i = 9; i < 36; ++i) {
            if (this.isValuable(mc.field_1724.method_31548().method_5438(i))) {
               return true;
            }
         }
      } catch (Throwable var2) {
      }

      return false;
   }

   private void runPostLootCommand() {
      try {
         class_634 nh = mc.method_1562();
         if (nh == null) {
            return;
         }

         if (this.lootEnd.isSelected("/hub")) {
            this.sendCommand(nh, "hub");
            return;
         }

         if (this.lootEnd.isSelected("/spawn")) {
            this.sendCommand(nh, "spawn");
            return;
         }

         if (this.lootEnd.isSelected("home")) {
            String cmd = this.homeCmd.getText();
            if (cmd != null && !cmd.isBlank()) {
               this.sendChatLine("/" + cmd.replaceFirst("^/+", ""));
            }

            return;
         }

         if (this.lootEnd.isSelected("Ð¡Ð²Ð¾Ñ")) {
            String cmd = this.customCmd.getText();
            if (cmd != null && !cmd.isBlank()) {
               this.sendChatLine(cmd);
            }

            return;
         }
      } catch (Throwable var3) {
      }

   }

   private void sendCommand(Object nh, String raw) {
      try {
         nh.getClass().getMethod("sendChatCommand", String.class).invoke(nh, raw);
      } catch (Throwable var6) {
         try {
            Class<?> cls = Class.forName("net.minecraft.network.packet.c2s.play.CommandExecutionC2SPacket");
            Object pkt = cls.getConstructor(String.class).newInstance(raw);
            mc.method_1562().method_52787((class_2596)pkt);
         } catch (Throwable var5) {
         }

      }
   }

   private void sendChatLine(String line) {
      try {
         class_634 nh = mc.method_1562();
         nh.getClass().getMethod("sendChatCommand", String.class).invoke(nh, line.replaceFirst("^/+", ""));
      } catch (Throwable var4) {
         try {
            mc.field_1724.method_7353(class_2561.method_30163(line), false);
         } catch (Throwable var3) {
         }

      }
   }
}

