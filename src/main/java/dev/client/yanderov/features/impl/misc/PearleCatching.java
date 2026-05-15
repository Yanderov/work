package dev.client.yanderov.features.impl.misc;

import antidaunleak.api.annotation.Native;
import dev.client.yanderov.events.player.TickEvent;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.features.module.setting.Setting;
import dev.client.yanderov.features.module.setting.implement.BindSetting;
import dev.client.yanderov.utils.client.chat.ChatMessage;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import dev.client.yanderov.utils.interactions.inv.InventoryResult;
import dev.client.yanderov.utils.interactions.inv.InventoryToolkit;
import net.minecraft.class_1268;
import net.minecraft.class_1802;
import net.minecraft.class_2886;
import net.minecraft.class_310;
import net.minecraft.class_3675;
import org.lwjgl.glfw.GLFW;

public class PearleCatching extends Module {
   private static final class_310 MC = class_310.method_1551();
   private final BindSetting keySetting = new BindSetting("ÐšÐ½Ð¾Ð¿ÐºÐ°", "ÐšÐ½Ð¾Ð¿ÐºÐ° Ð´Ð»Ñ Ð¿ÐµÑ€Ð»Ð°+Ð·Ð°Ñ€ÑÐ´");
   private boolean prevKeyPressed = false;
   private long lastUseTime = 0L;
   private int packetSequence = 0;
   private int savedSlot = -1;

   public PearleCatching() {
      super("PearleCatching", "Pearle Catching", ModuleCategory.MISC);
      this.setup(new Setting[]{this.keySetting});
   }

   @EventHandler
   @Native(
      type = Native.Type.VMProtectBeginUltra
   )
   public void onTick(TickEvent e) {
      if (MC.field_1724 != null && MC.field_1687 != null) {
         boolean keyDown = this.isBindActive();
         if (!this.prevKeyPressed && keyDown && System.currentTimeMillis() - this.lastUseTime > 100L) {
            this.lastUseTime = System.currentTimeMillis();
            this.doPearlCatch();
         }

         this.prevKeyPressed = keyDown;
      }
   }

   private void doPearlCatch() {
      if (MC.field_1755 == null) {
         if (MC.field_1724 != null) {
            this.savedSlot = MC.field_1724.method_31548().field_7545;
            InventoryResult pearlRes = InventoryToolkit.findItemInHotBar(class_1802.field_8634);
            if (!pearlRes.found()) {
               ChatMessage.brandmessage("ÐÐµÑ‚ ÑÐ½Ð´ÐµÑ€-Ð¶ÐµÐ¼Ñ‡ÑƒÐ³Ð° Ð² Ñ…Ð¾Ñ‚Ð±Ð°Ñ€Ðµ");
            } else {
               InventoryResult windRes = InventoryToolkit.findItemInHotBar(class_1802.field_49098);
               if (!windRes.found()) {
                  ChatMessage.brandmessage("ÐÐµÑ‚ Ð·Ð°Ñ€ÑÐ´Ð° Ð²ÐµÑ‚Ñ€Ð° Ð² Ñ…Ð¾Ñ‚Ð±Ð°Ñ€Ðµ");
               } else {
                  int pearlSlot = pearlRes.slot();
                  int windSlot = windRes.slot();
                  MC.field_1724.method_36457(-90.0F);
                  InventoryToolkit.switchTo(pearlSlot);
                  InventoryToolkit.sendPacket(new class_2886(class_1268.field_5808, this.packetSequence++, MC.field_1724.method_36454(), MC.field_1724.method_36455()));
                  MC.field_1724.method_6104(class_1268.field_5808);
                  InventoryToolkit.switchTo(windSlot);
                  InventoryToolkit.sendPacket(new class_2886(class_1268.field_5808, this.packetSequence++, MC.field_1724.method_36454(), MC.field_1724.method_36455()));
                  MC.field_1724.method_6104(class_1268.field_5808);
                  if (this.savedSlot >= 0 && this.savedSlot < 9) {
                     InventoryToolkit.switchTo(this.savedSlot);
                  }

                  this.savedSlot = -1;
               }
            }
         }
      }
   }

   private boolean isBindActive() {
      long window = MC.method_22683().method_4490();
      int key = this.keySetting.getKey();
      if (key >= 0 && key <= 7) {
         return GLFW.glfwGetMouseButton(window, key) == 1;
      } else {
         return class_3675.method_15987(window, key);
      }
   }
}

