package dev.client.yanderov.features.impl.misc;

import antidaunleak.api.annotation.Native;
import dev.client.yanderov.events.player.TickEvent;
import dev.client.yanderov.features.impl.movement.AutoSprint;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.features.module.setting.Setting;
import dev.client.yanderov.features.module.setting.implement.BindSetting;
import dev.client.yanderov.features.module.setting.implement.SelectSetting;
import dev.client.yanderov.utils.client.chat.ChatMessage;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import dev.client.yanderov.utils.interactions.inv.InventoryResult;
import dev.client.yanderov.utils.interactions.inv.InventoryToolkit;
import net.minecraft.class_1268;
import net.minecraft.class_1713;
import net.minecraft.class_1802;
import net.minecraft.class_2886;
import net.minecraft.class_310;
import net.minecraft.class_3675;
import org.lwjgl.glfw.GLFW;

public class ClickPearl extends Module {
   private static final class_310 MC = class_310.method_1551();
   private final SelectSetting modeSetting = (new SelectSetting("Ð ÐµÐ¶Ð¸Ð¼", "Ð¡Ð¿Ð¾ÑÐ¾Ð± Ð±Ñ€Ð¾ÑÐºÐ°")).value("Default", "Legit").selected("Default");
   private final BindSetting keySetting = new BindSetting("ÐšÐ½Ð¾Ð¿ÐºÐ°", "ÐšÐ½Ð¾Ð¿ÐºÐ° Ð´Ð»Ñ Ð¸ÑÐ¿Ð¾Ð»ÑŒÐ·Ð¾Ð²Ð°Ð½Ð¸Ñ");
   private boolean prevKeyPressed = false;
   private long lastThrowTime = 0L;
   private int packetSequence = 0;
   private int savedSlot = -1;
   private int pearlSlot = -1;
   private long actionStartTime = 0L;
   private boolean keysOverridden = false;
   private boolean wasForwardPressed;
   private boolean wasBackPressed;
   private boolean wasLeftPressed;
   private boolean wasRightPressed;
   private boolean wasJumpPressed;
   private Phase phase;

   public ClickPearl() {
      super("ClickPearl", "Click Pearl", ModuleCategory.MISC);
      this.phase = ClickPearl.Phase.READY;
      this.setup(new Setting[]{this.modeSetting, this.keySetting});
   }

   @EventHandler
   @Native(
      type = Native.Type.VMProtectBeginUltra
   )
   public void onTick(TickEvent e) {
      if (MC.field_1724 != null && MC.field_1687 != null) {
         boolean keyDown = this.isBindActive();
         if (!this.prevKeyPressed && keyDown && System.currentTimeMillis() - this.lastThrowTime > 100L && this.phase == ClickPearl.Phase.READY) {
            this.lastThrowTime = System.currentTimeMillis();
            this.startPearlProcess();
         }

         this.prevKeyPressed = keyDown;
         if (this.phase != ClickPearl.Phase.READY) {
            this.execute();
         }

      } else {
         this.resetState();
      }
   }

   private void startPearlProcess() {
      if (MC.field_1755 == null) {
         this.savedSlot = MC.field_1724.method_31548().field_7545;
         InventoryResult hotbar = InventoryToolkit.findItemInHotBar(class_1802.field_8634);
         if (hotbar.found()) {
            this.pearlSlot = hotbar.slot();
            InventoryToolkit.switchTo(this.pearlSlot);
            this.phase = ClickPearl.Phase.AWAIT_SWITCH;
         } else {
            InventoryResult inv = InventoryToolkit.findItemInInventory(class_1802.field_8634);
            if (inv.found()) {
               this.pearlSlot = inv.slot();
               if (this.modeSetting.getSelected().equals("Legit")) {
                  this.wasForwardPressed = class_3675.method_15987(MC.method_22683().method_4490(), MC.field_1690.field_1894.method_1429().method_1444());
                  this.wasBackPressed = class_3675.method_15987(MC.method_22683().method_4490(), MC.field_1690.field_1881.method_1429().method_1444());
                  this.wasLeftPressed = class_3675.method_15987(MC.method_22683().method_4490(), MC.field_1690.field_1913.method_1429().method_1444());
                  this.wasRightPressed = class_3675.method_15987(MC.method_22683().method_4490(), MC.field_1690.field_1849.method_1429().method_1444());
                  this.wasJumpPressed = class_3675.method_15987(MC.method_22683().method_4490(), MC.field_1690.field_1903.method_1429().method_1444());
                  this.phase = ClickPearl.Phase.SLOWING_DOWN;
                  this.actionStartTime = System.currentTimeMillis();
               } else {
                  this.phase = ClickPearl.Phase.PREPARE;
               }
            } else {
               ChatMessage.brandmessage("ÐÐµÑ‚Ñƒ Ð¶ÐµÐ¼Ñ‡ÑƒÐ³Ð°");
               this.resetState();
            }

         }
      }
   }

   private void execute() {
      if (MC.field_1724 != null && MC.field_1755 == null) {
         long elapsed = System.currentTimeMillis() - this.actionStartTime;
         switch (this.phase.ordinal()) {
            case 1:
               MC.field_1724.field_3913.field_3905 = 0.0F;
               MC.field_1724.field_3913.field_3907 = 0.0F;
               if (MC.field_1724.method_5624()) {
                  MC.field_1724.method_5728(false);
                  AutoSprint.tickStop = 1;
               }

               if (!this.keysOverridden) {
                  MC.field_1690.field_1894.method_23481(false);
                  MC.field_1690.field_1881.method_23481(false);
                  MC.field_1690.field_1913.method_23481(false);
                  MC.field_1690.field_1849.method_23481(false);
                  MC.field_1690.field_1903.method_23481(false);
                  this.keysOverridden = true;
               }

               if (elapsed > 1L) {
                  this.phase = ClickPearl.Phase.PREPARE;
               }
               break;
            case 2:
               int quickSwapSlot = MC.field_1724.method_31548().field_7545;
               InventoryToolkit.clickSlot(this.pearlSlot, quickSwapSlot, class_1713.field_7791);
               InventoryToolkit.switchTo(quickSwapSlot);
               this.phase = ClickPearl.Phase.AWAIT_SWITCH;
               break;
            case 3:
               if (MC.field_1724.method_6047().method_7909() == class_1802.field_8634) {
                  this.phase = ClickPearl.Phase.THROW;
               }
               break;
            case 4:
               InventoryToolkit.sendPacket(new class_2886(class_1268.field_5808, this.packetSequence++, MC.field_1724.method_36454(), MC.field_1724.method_36455()));
               MC.field_1724.method_6104(class_1268.field_5808);
               boolean fromInventory = this.pearlSlot >= 9 && this.pearlSlot <= 35;
               if (fromInventory) {
                  int quickSwapSlot = MC.field_1724.method_31548().field_7545;
                  InventoryToolkit.clickSlot(this.pearlSlot, quickSwapSlot, class_1713.field_7791);
               }

               InventoryToolkit.switchTo(this.savedSlot);
               if (this.modeSetting.getSelected().equals("Legit") && fromInventory) {
                  this.restoreKeyStates();
                  this.actionStartTime = System.currentTimeMillis();
                  this.phase = ClickPearl.Phase.SPEEDING_UP;
               } else {
                  this.phase = ClickPearl.Phase.FINISH;
               }
               break;
            case 5:
               long speedupElapsed = System.currentTimeMillis() - this.actionStartTime;
               float speedupProgress = Math.min(1.0F, (float)speedupElapsed / 20.0F);
               if (MC.field_1724.field_3913 != null) {
                  boolean forward = class_3675.method_15987(MC.method_22683().method_4490(), MC.field_1690.field_1894.method_1429().method_1444());
                  float targetForward = forward ? 1.0F : 0.0F;
                  MC.field_1724.field_3913.field_3905 = this.lerp(MC.field_1724.field_3913.field_3905, targetForward * speedupProgress, 0.4F);
                  if (speedupProgress > 0.4F && forward && !MC.field_1724.method_5624()) {
                     MC.field_1724.method_5728(true);
                  }
               }

               if (speedupElapsed > 25L) {
                  this.phase = ClickPearl.Phase.FINISH;
               }
               break;
            case 6:
               this.resetState();
         }

      } else {
         this.resetState();
      }
   }

   private float lerp(float start, float end, float delta) {
      return start + (end - start) * delta;
   }

   private void restoreKeyStates() {
      if (this.keysOverridden) {
         boolean currentForward = class_3675.method_15987(MC.method_22683().method_4490(), MC.field_1690.field_1894.method_1429().method_1444());
         boolean currentBack = class_3675.method_15987(MC.method_22683().method_4490(), MC.field_1690.field_1881.method_1429().method_1444());
         boolean currentLeft = class_3675.method_15987(MC.method_22683().method_4490(), MC.field_1690.field_1913.method_1429().method_1444());
         boolean currentRight = class_3675.method_15987(MC.method_22683().method_4490(), MC.field_1690.field_1849.method_1429().method_1444());
         boolean currentJump = class_3675.method_15987(MC.method_22683().method_4490(), MC.field_1690.field_1903.method_1429().method_1444());
         MC.field_1690.field_1894.method_23481(this.wasForwardPressed && currentForward);
         MC.field_1690.field_1881.method_23481(this.wasBackPressed && currentBack);
         MC.field_1690.field_1913.method_23481(this.wasLeftPressed && currentLeft);
         MC.field_1690.field_1849.method_23481(this.wasRightPressed && currentRight);
         MC.field_1690.field_1903.method_23481(this.wasJumpPressed && currentJump);
         this.keysOverridden = false;
      }
   }

   private void resetState() {
      if (this.keysOverridden) {
         this.restoreKeyStates();
      }

      this.pearlSlot = -1;
      this.savedSlot = -1;
      this.actionStartTime = 0L;
      this.phase = ClickPearl.Phase.READY;
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

   private static enum Phase {
      READY,
      SLOWING_DOWN,
      PREPARE,
      AWAIT_SWITCH,
      THROW,
      SPEEDING_UP,
      FINISH;

      // $FF: synthetic method
      private static Phase[] $values() {
         return new Phase[]{READY, SLOWING_DOWN, PREPARE, AWAIT_SWITCH, THROW, SPEEDING_UP, FINISH};
      }
   }
}

