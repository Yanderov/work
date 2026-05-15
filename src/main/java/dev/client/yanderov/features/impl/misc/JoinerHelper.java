package dev.client.yanderov.features.impl.misc;

import antidaunleak.api.annotation.Native;
import dev.client.yanderov.events.packet.PacketEvent;
import dev.client.yanderov.events.player.TickEvent;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.features.module.setting.Setting;
import dev.client.yanderov.features.module.setting.implement.SelectSetting;
import dev.client.yanderov.features.module.setting.implement.SliderSettings;
import dev.client.yanderov.utils.client.Instance;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import dev.client.yanderov.utils.client.packet.network.Network;
import dev.client.yanderov.utils.interactions.inv.InventoryTask;
import net.minecraft.class_1268;
import net.minecraft.class_1707;
import net.minecraft.class_1713;
import net.minecraft.class_1735;
import net.minecraft.class_2596;
import net.minecraft.class_2661;
import net.minecraft.class_2886;
import net.minecraft.class_437;
import net.minecraft.class_476;
import org.lwjgl.glfw.GLFW;

public class JoinerHelper extends Module {
   private final SelectSetting serverSelection = (new SelectSetting("Ð¡ÐµÑ€Ð²ÐµÑ€", "Ð’Ñ‹Ð±ÐµÑ€Ð¸Ñ‚Ðµ Ñ†ÐµÐ»ÐµÐ²Ð¾Ð¹ ÑÐµÑ€Ð²ÐµÑ€")).value("ReallyWorld", "SpookyTime Duels").selected("ReallyWorld");
   private final SliderSettings griefSelection = (new SliderSettings("ÐÐ¾Ð¼ÐµÑ€ Ð³Ñ€Ð¸Ñ„Ð°", "Ð’Ñ‹Ð±ÐµÑ€Ð¸Ñ‚Ðµ Ð½Ð¾Ð¼ÐµÑ€ ÑÐµÑ€Ð²ÐµÑ€Ð° Ð´Ð»Ñ Ð³Ñ€Ð¸Ñ„Ð°")).setValue(1.0F).range(1, 54).visible(() -> this.serverSelection.isSelected("ReallyWorld"));
   private long lastActionTime;
   private boolean isToggling;
   private boolean retryDuels;

   public static JoinerHelper getInstance() {
      return (JoinerHelper)Instance.get(JoinerHelper.class);
   }

   public JoinerHelper() {
      super("JoinerHelper", "Joiner Helper", ModuleCategory.MISC);
      this.setup(new Setting[]{this.serverSelection, this.griefSelection});
   }

   @EventHandler
   @Native(
      type = Native.Type.VMProtectBeginUltra
   )
   public void onTick(TickEvent event) {
      if (this.state) {
         if (GLFW.glfwGetKey(mc.method_22683().method_4490(), 260) == 1) {
            this.deactivate();
         } else {
            if (mc.field_1755 == null && mc.field_1724 != null && mc.field_1724.field_6012 < 5) {
               InventoryTask.selectCompass();
               mc.field_1724.field_3944.method_52787(new class_2886(class_1268.field_5808, mc.field_1724.method_31548().field_7545, mc.field_1724.method_36454(), mc.field_1724.method_36455()));
            } else {
               class_437 var3 = mc.field_1755;
               if (var3 instanceof class_476) {
                  class_476 container = (class_476)var3;

                  for(int i = 0; i < ((class_1707)container.method_17577()).field_7761.size(); ++i) {
                     String s = ((class_1735)((class_1707)container.method_17577()).field_7761.get(i)).method_7677().method_7964().getString().toLowerCase();
                     if (this.serverSelection.isSelected("ReallyWorld") && Network.isReallyWorld()) {
                        if (s.contains("Ð³Ñ€Ð¸Ñ„ÐµÑ€ÑÐºÐ¾Ðµ Ð²Ñ‹Ð¶Ð¸Ð²Ð°Ð½Ð¸Ðµ") && System.currentTimeMillis() - this.lastActionTime > 50L) {
                           InventoryTask.clickSlot(i, 0, class_1713.field_7790, false);
                           this.lastActionTime = System.currentTimeMillis();
                        }

                        int numberGrief = (int)this.griefSelection.getValue();
                        if (s.contains("Ð³Ñ€Ð¸Ñ„ #" + numberGrief) && System.currentTimeMillis() - this.lastActionTime > 50L) {
                           InventoryTask.clickSlot(i, 0, class_1713.field_7790, false);
                           this.lastActionTime = System.currentTimeMillis();
                        }
                     } else if (this.serverSelection.isSelected("SpookyTime Duels") && Network.isSpookyTime()) {
                        if (s.contains("Â» Ð´ÑƒÑÐ»Ð¸") && System.currentTimeMillis() - this.lastActionTime > 70L) {
                           mc.field_1724.field_7514.field_7545 = 0;
                           InventoryTask.clickSlot(i, 0, class_1713.field_7790, false);
                           this.lastActionTime = System.currentTimeMillis();
                           this.retryDuels = true;
                        }

                        if (s.contains("Ð»Ð¸Ð¿ÐºÐ¸Ð¹ Ð¿Ð¾Ñ€ÑˆÐµÐ½ÑŒ") && System.currentTimeMillis() - this.lastActionTime > 70L) {
                           InventoryTask.clickSlot(i, 0, class_1713.field_7790, false);
                           this.lastActionTime = System.currentTimeMillis();
                        }
                     }
                  }
               }
            }

         }
      }
   }

   @EventHandler
   public void onPacket(PacketEvent event) {
      if (this.state && event.getType() == PacketEvent.Type.RECEIVE) {
         class_2596 var3 = event.getPacket();
         if (var3 instanceof class_2661) {
            class_2661 packet = (class_2661)var3;
            String message = packet.comp_2325().getString().toLowerCase();
            if (message.contains("Ðº ÑÐ¾Ð¶Ð°Ð»ÐµÐ½Ð¸ÑŽ ÑÐµÑ€Ð²ÐµÑ€ Ð¿ÐµÑ€ÐµÐ¿Ð¾Ð»Ð½ÐµÐ½") || message.contains("Ð¿Ð¾Ð´Ð¾Ð¶Ð´Ð¸Ñ‚Ðµ 20 ÑÐµÐºÑƒÐ½Ð´!") || message.contains("Ð²Ñ‹ ÑƒÐ¶Ðµ Ð¿Ð¾Ð´ÐºÐ»ÑŽÑ‡ÐµÐ½Ñ‹ Ð½Ð° ÑÑ‚Ð¾Ñ‚ ÑÐµÑ€Ð²ÐµÑ€!") || message.contains("Ð¿Ð¾Ð´Ð¾Ð¶Ð´Ð¸Ñ‚Ðµ Ð½ÐµÑÐºÐ¾Ð»ÑŒÐºÐ¾ ÑÐµÐºÑƒÐ½Ð´ Ð¿ÐµÑ€ÐµÐ´ Ð¿Ð¾Ð²Ñ‚Ð¾Ñ€Ð½Ñ‹Ð¼ Ð¿Ð¾Ð´ÐºÐ»ÑŽÑ‡ÐµÐ½Ð¸ÐµÐ¼!") || message.contains("Ð²Ñ‹ Ð±Ñ‹Ð»Ð¸ ÐºÐ¸ÐºÐ½ÑƒÑ‚Ñ‹ Ñ ÑÐµÑ€Ð²ÐµÑ€Ð° 1duels:") || message.contains("Ð’Ñ‹ Ð±Ñ‹Ð»Ð¸ ÐºÐ¸ÐºÐ½ÑƒÑ‚Ñ‹") || message.contains("Ð±Ð¾Ð»ÑŒÑˆÐ¾Ð¹ Ð¿Ð¾Ñ‚Ð¾Ðº Ð¸Ð³Ñ€Ð¾ÐºÐ¾Ð²") || message.contains("ÑÐµÑ€Ð²ÐµÑ€ Ð·Ð°Ð¿Ð¾Ð»Ð½ÐµÐ½!")) {
               InventoryTask.selectCompass();
               mc.field_1724.field_3944.method_52787(new class_2886(class_1268.field_5808, mc.field_1724.method_31548().field_7545, mc.field_1724.method_36454(), mc.field_1724.method_36455()));
            }
         }

      }
   }

   public void activate() {
      super.activate();
      InventoryTask.selectCompass();
      mc.field_1724.field_3944.method_52787(new class_2886(class_1268.field_5808, mc.field_1724.method_31548().field_7545, mc.field_1724.method_36454(), mc.field_1724.method_36455()));
      this.isToggling = false;
      this.retryDuels = false;
      this.lastActionTime = 0L;
   }

   public void deactivate() {
      super.deactivate();
      this.lastActionTime = 0L;
      this.isToggling = false;
      this.retryDuels = false;
   }
}

