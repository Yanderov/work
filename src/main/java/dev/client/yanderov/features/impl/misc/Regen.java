package dev.client.yanderov.features.impl.misc;

import dev.client.yanderov.events.player.TickEvent;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.features.module.setting.Setting;
import dev.client.yanderov.features.module.setting.implement.BindSetting;
import dev.client.yanderov.features.module.setting.implement.BooleanSetting;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import dev.client.yanderov.utils.display.interfaces.QuickImports;
import dev.client.yanderov.utils.interactions.interact.PlayerInteractionHelper;
import net.minecraft.class_2561;
import net.minecraft.class_2596;
import net.minecraft.class_634;

public class Regen extends Module implements QuickImports {
   private final BooleanSetting useBind = (new BooleanSetting("Ð Ð°Ð±Ð¾Ñ‚Ð°Ñ‚ÑŒ Ð¿Ð¾ Ð±Ð¸Ð½Ð´Ñƒ", "ÐžÑ‚Ð¿Ñ€Ð°Ð²Ð»ÑÑ‚ÑŒ /heal Ð¿Ð¾ ÐºÐ½Ð¾Ð¿ÐºÐµ")).setValue(true);
   private final BindSetting healKey = (new BindSetting("ÐšÐ»Ð°Ð²Ð¸ÑˆÐ° /heal", "ÐšÐ½Ð¾Ð¿ÐºÐ° Ð´Ð»Ñ /heal")).setKey(-1).visible(() -> this.useBind.isValue());
   private boolean lastPressed = false;

   public Regen() {
      super("Regen", ModuleCategory.MISC);
      this.setup(new Setting[]{this.useBind, this.healKey});
   }

   @EventHandler
   public void onTick(TickEvent e) {
      if (e.getType() == 0) {
         if (mc != null && mc.field_1724 != null) {
            if (this.useBind.isValue()) {
               boolean pressed = PlayerInteractionHelper.isKey(this.healKey);
               if (pressed && !this.lastPressed) {
                  this.sendHealCommand();
               }

               this.lastPressed = pressed;
            }
         }
      }
   }

   private void sendHealCommand() {
      try {
         class_634 nh = mc.method_1562();
         if (nh != null) {
            try {
               nh.getClass().getMethod("sendChatCommand", String.class).invoke(nh, "heal");
               return;
            } catch (Throwable var6) {
               try {
                  Class<?> cmdCls = Class.forName("net.minecraft.network.packet.c2s.play.CommandExecutionC2SPacket");
                  Object pkt = cmdCls.getConstructor(String.class).newInstance("heal");
                  mc.method_1562().method_52787((class_2596)pkt);
                  return;
               } catch (Throwable var5) {
               }
            }
         }
      } catch (Throwable var7) {
      }

      try {
         mc.field_1724.method_7353(class_2561.method_30163("/heal"), false);
      } catch (Throwable var4) {
      }

   }
}

