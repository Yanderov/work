package dev.client.yanderov.features.impl.movement;

import dev.client.yanderov.events.player.TickEvent;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.features.module.setting.Setting;
import dev.client.yanderov.features.module.setting.implement.BooleanSetting;
import dev.client.yanderov.features.module.setting.implement.SelectSetting;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import net.minecraft.class_1268;
import net.minecraft.class_1747;
import net.minecraft.class_1799;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_2828;
import net.minecraft.class_2879;
import net.minecraft.class_2885;
import net.minecraft.class_3965;

public class Scaffold extends Module {
   private final SelectSetting mode = (new SelectSetting("Mode", "Ð ÐµÐ¶Ð¸Ð¼")).value("Normal", "Tower").selected("Normal");
   private final BooleanSetting rotate = (new BooleanSetting("RotatePackets", "ÐŸÐ°ÐºÐµÑ‚Ð½Ð°Ñ Ñ€Ð¾Ñ‚Ð°Ñ†Ð¸Ñ")).setValue(true);
   private final BooleanSetting swing = (new BooleanSetting("Swing", "ÐÐ½Ð¸Ð¼Ð°Ñ†Ð¸Ñ Ñ€ÑƒÐºÐ¸")).setValue(true);
   private class_2338 lastPos;
   private int blockSlot = -1;
   private boolean isPlacing;
   private float serverYaw;
   private float serverPitch;

   public Scaffold() {
      super("Scaffold", "Scaffold", ModuleCategory.MOVEMENT);
      this.setup(new Setting[]{this.mode, this.rotate, this.swing});
   }

   public void activate() {
      super.activate();
      this.lastPos = null;
      this.blockSlot = -1;
      this.isPlacing = false;
      this.serverYaw = 0.0F;
      this.serverPitch = 0.0F;
   }

   @EventHandler
   public void onTick(TickEvent event) {
      if (event.getType() == 0) {
         if (this.isState()) {
            if (mc.field_1724 != null && mc.field_1687 != null) {
               class_243 pos = mc.field_1724.method_19538();
               class_2338 currentPos = class_2338.method_49637(pos.field_1352, pos.field_1351 + (double)0.5F, pos.field_1350);
               if (this.lastPos == null || !currentPos.equals(this.lastPos)) {
                  this.findBlockSlot();
                  if (this.blockSlot != -1) {
                     class_2338 placePos = currentPos.method_10074();
                     this.handlePlaceAtPos(placePos);
                  }

                  this.lastPos = currentPos;
               }

               if (this.mode.isSelected("Tower") && mc.field_1690.field_1903.method_1434()) {
                  class_2338 placePos = currentPos.method_10074();
                  this.handlePlaceAtPos(placePos);
               }

            }
         }
      }
   }

   private void handlePlaceAtPos(class_2338 placePos) {
      if (this.rotate.isValue()) {
         this.calculateServerRotation(placePos);
         this.sendRotationPacket();
         mc.field_1724.method_36456(this.serverYaw);
         mc.field_1724.method_36457(this.serverPitch);
         this.isPlacing = true;
      }

      this.placeBlock(placePos);
      this.isPlacing = false;
   }

   private void calculateServerRotation(class_2338 pos) {
      class_243 eyesPos = mc.field_1724.method_33571();
      class_243 posVec = new class_243((double)pos.method_10263() + (double)0.5F, (double)pos.method_10264() + (double)0.5F, (double)pos.method_10260() + (double)0.5F);
      class_243 diff = posVec.method_1020(eyesPos);
      double diffXZ = Math.sqrt(diff.field_1352 * diff.field_1352 + diff.field_1350 * diff.field_1350);
      float targetYaw = (float)Math.toDegrees(Math.atan2(diff.field_1350, diff.field_1352)) - 90.0F;
      float targetPitch = (float)(-Math.toDegrees(Math.atan2(diff.field_1351, diffXZ)));
      this.serverYaw = this.wrapDegrees(targetYaw);
      this.serverPitch = this.clampPitch(targetPitch);
   }

   private void sendRotationPacket() {
      this.sendPacket(new class_2828.class_2830(mc.field_1724.method_23317(), mc.field_1724.method_23318(), mc.field_1724.method_23321(), this.serverYaw, this.serverPitch, mc.field_1724.method_24828(), false));
   }

   private void findBlockSlot() {
      this.blockSlot = -1;
      if (mc.field_1724 != null) {
         for(int i = 0; i < 9; ++i) {
            class_1799 stack = mc.field_1724.method_31548().method_5438(i);
            if (stack.method_7909() instanceof class_1747 && stack.method_7947() > 0) {
               this.blockSlot = i;
               break;
            }
         }

      }
   }

   private void placeBlock(class_2338 pos) {
      if (mc.field_1687 != null && mc.field_1724 != null && mc.field_1761 != null) {
         if (this.blockSlot != -1) {
            if (mc.field_1687.method_8320(pos).method_26204() == class_2246.field_10124) {
               int oldSlot = mc.field_1724.method_31548().field_7545;
               mc.field_1724.method_31548().field_7545 = this.blockSlot;
               class_243 hitVec = new class_243((double)pos.method_10263() + (double)0.5F, (double)pos.method_10264() + (double)0.5F, (double)pos.method_10260() + (double)0.5F);
               class_3965 hitResult = new class_3965(hitVec, class_2350.field_11036, pos, false);
               this.sendPacket(new class_2885(class_1268.field_5808, hitResult, 0));
               if (this.swing.isValue()) {
                  mc.field_1724.method_6104(class_1268.field_5808);
                  this.sendPacket(new class_2879(class_1268.field_5808));
               }

               mc.field_1724.method_31548().field_7545 = oldSlot;
            }
         }
      }
   }

   private float wrapDegrees(float angle) {
      angle %= 360.0F;
      if (angle >= 180.0F) {
         angle -= 360.0F;
      }

      if (angle < -180.0F) {
         angle += 360.0F;
      }

      return angle;
   }

   private float clampPitch(float pitch) {
      if (pitch > 90.0F) {
         return 90.0F;
      } else {
         return pitch < -90.0F ? -90.0F : pitch;
      }
   }

   private void sendPacket(class_2596 packet) {
      if (mc.method_1562() != null) {
         mc.method_1562().method_52787(packet);
      }

   }
}

