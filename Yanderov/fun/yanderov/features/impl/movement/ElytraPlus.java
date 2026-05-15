package fun.Yanderov.features.impl.movement;

import fun.Yanderov.display.hud.Notifications;
import fun.Yanderov.events.packet.PacketEvent;
import fun.Yanderov.events.player.TickEvent;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.features.module.setting.Setting;
import fun.Yanderov.features.module.setting.implement.BindSetting;
import fun.Yanderov.features.module.setting.implement.BooleanSetting;
import fun.Yanderov.features.module.setting.implement.SelectSetting;
import fun.Yanderov.features.module.setting.implement.SliderSettings;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import fun.Yanderov.utils.interactions.simulate.Simulations;
import net.minecraft.class_124;
import net.minecraft.class_1268;
import net.minecraft.class_1304;
import net.minecraft.class_1713;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2338;
import net.minecraft.class_2596;
import net.minecraft.class_2708;
import net.minecraft.class_2848;
import net.minecraft.class_2868;
import net.minecraft.class_2886;
import net.minecraft.class_2848.class_2849;

public class ElytraPlus extends Module {
   private final SelectSetting mode = (new SelectSetting("Mode", "Ð ÐµÐ¶Ð¸Ð¼ ÑÐ»Ð¸Ñ‚Ñ€Ñ‹")).value("FireWork", "Boost", "Control").selected("FireWork");
   private final SliderSettings fwXZSpeed = (new SliderSettings("FW XZSpeed", "Ð“Ð¾Ñ€Ð¸Ð·. ÑÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Ð¿Ñ€Ð¸ Ñ„ÐµÐ¹ÐµÑ€Ð²ÐµÑ€ÐºÐ°Ñ…")).setValue(1.5F).range(0.1F, 5.0F).visible(() -> this.mode.isSelected("FireWork"));
   private final SliderSettings fwYSpeed = (new SliderSettings("FW YSpeed", "Ð’ÐµÑ€Ñ‚Ð¸ÐºÐ°Ð»ÑŒÐ½Ð°Ñ ÑÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Ð² FireWork")).setValue(0.4F).range(0.0F, 2.0F).visible(() -> this.mode.isSelected("FireWork"));
   private final SliderSettings fireDelay = (new SliderSettings("FireDelay", "Ð—Ð°Ð´ÐµÑ€Ð¶ÐºÐ° Ð¼ÐµÐ¶Ð´Ñƒ Ñ„ÐµÐ¹ÐµÑ€Ð²ÐµÑ€ÐºÐ°Ð¼Ð¸ (ÑÐµÐº)")).setValue(1.0F).range(0.0F, 2.0F).visible(() -> this.mode.isSelected("FireWork"));
   private final SliderSettings fireSlot = (new SliderSettings("FireSlot", "Ð¡Ð»Ð¾Ñ‚ Ð´Ð»Ñ Ñ„ÐµÐ¹ÐµÑ€Ð²ÐµÑ€ÐºÐ¾Ð² (1-9)")).setValue(1.0F).range(1.0F, 9.0F).visible(() -> this.mode.isSelected("FireWork"));
   private final BooleanSetting allowFireSwap = (new BooleanSetting("AllowFireSwap", "ÐÐ²Ñ‚Ð¾-Ð¿ÐµÑ€ÐµÐ½Ð¾Ñ Ñ„ÐµÐ¹ÐµÑ€Ð²ÐµÑ€ÐºÐ¾Ð² Ð² Ñ…Ð¾Ñ‚Ð±Ð°Ñ€")).setValue(true).visible(() -> this.mode.isSelected("FireWork"));
   private final SelectSetting antiKick = (new SelectSetting("AntiKick", "ÐÐ½Ñ‚Ð¸ÐºÐ¸Ðº")).value("Off", "Jitter", "Glide").selected("Jitter").visible(() -> this.mode.isSelected("FireWork"));
   private final BooleanSetting keepFlying = (new BooleanSetting("KeepFlying", "ÐÐµ ÑÐ±Ñ€Ð°ÑÑ‹Ð²Ð°Ñ‚ÑŒ Ð¿Ð¾Ð»Ñ‘Ñ‚ Ð¿Ñ€Ð¸ Ð²Ñ‹ÐºÐ»ÑŽÑ‡ÐµÐ½Ð¸Ð¸")).setValue(false).visible(() -> this.mode.isSelected("FireWork"));
   private final BooleanSetting disableOnFlag = (new BooleanSetting("DisableOnFlag", "Ð’Ñ‹ÐºÐ»ÑŽÑ‡Ð°Ñ‚ÑŒ Ð¿Ñ€Ð¸ Ñ„Ð»Ð°Ð³Ðµ Ð¿Ð¾Ð·Ð¸Ñ†Ð¸Ð¸")).setValue(false).visible(() -> this.mode.isSelected("FireWork"));
   private final BooleanSetting cruiseControl = (new BooleanSetting("CruiseControl", "ÐšÑ€ÑƒÐ¸Ð·-ÐºÐ¾Ð½Ñ‚Ñ€Ð¾Ð»ÑŒ Ð¿Ð¾ Ð²Ñ‹ÑÐ¾Ñ‚Ðµ")).setValue(false).visible(() -> this.mode.isSelected("Boost"));
   private final SliderSettings factor = (new SliderSettings("Factor", "Ð¤Ð°ÐºÑ‚Ð¾Ñ€ ÑƒÑÐºÐ¾Ñ€ÐµÐ½Ð¸Ñ Ð²Ð¿ÐµÑ€Ñ‘Ð´")).setValue(1.5F).range(0.1F, 10.0F).visible(() -> this.mode.isSelected("Boost"));
   private final SliderSettings upSpeed = (new SliderSettings("UpSpeed", "Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Ð¿Ð¾Ð´ÑŠÑ‘Ð¼Ð° (Space)")).setValue(1.0F).range(0.01F, 5.0F).visible(() -> this.mode.isSelected("Boost"));
   private final SliderSettings glideFactor = (new SliderSettings("Glide", "Ð“Ð»Ð°Ð¹Ð´ Ð²Ð½Ð¸Ð·")).setValue(1.0F).range(0.0F, 2.0F).visible(() -> this.mode.isSelected("Boost"));
   private final BooleanSetting stopMotion = (new BooleanSetting("StopMotion", "ÐžÑÑ‚Ð°Ð½Ð°Ð²Ð»Ð¸Ð²Ð°Ñ‚ÑŒ Ð´Ð²Ð¸Ð¶ÐµÐ½Ð¸Ðµ Ð¿Ñ€Ð¸ Ñ€ÐµÐ´ÐµÐ¿Ð»Ð¾Ðµ")).setValue(true).visible(() -> this.mode.isSelected("Boost"));
   private final BooleanSetting speedLimit = (new BooleanSetting("SpeedLimit", "ÐžÐ³Ñ€Ð°Ð½Ð¸Ñ‡Ð¸Ñ‚ÑŒ ÑÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ")).setValue(true).visible(() -> this.mode.isSelected("Boost"));
   private final SliderSettings maxSpeed = (new SliderSettings("MaxSpeed", "ÐœÐ°ÐºÑ. ÑÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ")).setValue(2.5F).range(0.5F, 10.0F).visible(() -> this.mode.isSelected("Boost") && this.speedLimit.isValue());
   private final SliderSettings redeployInterval = (new SliderSettings("RedeployInt", "Ð˜Ð½Ñ‚ÐµÑ€Ð²Ð°Ð» Ñ€ÐµÐ´ÐµÐ¿Ð»Ð¾Ñ (ÑÐµÐº)")).setValue(1.5F).range(0.3F, 5.0F).visible(() -> this.mode.isSelected("Boost"));
   private final SliderSettings redeployTimeout = (new SliderSettings("RedeployTO", "Ð¢Ð°Ð¹Ð¼Ð°ÑƒÑ‚ Ñ€ÐµÐ´ÐµÐ¿Ð»Ð¾Ñ (ÑÐµÐº)")).setValue(4.0F).range(0.5F, 10.0F).visible(() -> this.mode.isSelected("Boost"));
   private final SliderSettings ctrlXZSpeed = (new SliderSettings("Ctrl XZ", "XZ ÑÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Ð² Control")).setValue(1.5F).range(0.1F, 10.0F).visible(() -> this.mode.isSelected("Control"));
   private final SliderSettings ctrlUpSpeed = (new SliderSettings("Ctrl Up", "Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Ð¿Ð¾Ð´ÑŠÑ‘Ð¼Ð° (Space)")).setValue(1.0F).range(0.01F, 5.0F).visible(() -> this.mode.isSelected("Control"));
   private final SliderSettings ctrlDownSpeed = (new SliderSettings("Ctrl Down", "Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ ÑÐ¿ÑƒÑÐºÐ° (Shift)")).setValue(1.0F).range(0.01F, 5.0F).visible(() -> this.mode.isSelected("Control"));
   private final BooleanSetting ctrlAccel = (new BooleanSetting("Ctrl Accel", "Ð£ÑÐºÐ¾Ñ€ÐµÐ½Ð¸Ðµ")).setValue(false).visible(() -> this.mode.isSelected("Control"));
   private final SliderSettings ctrlAccelFactor = (new SliderSettings("AccelFactor", "Ð¤Ð°ÐºÑ‚Ð¾Ñ€ ÑƒÑÐºÐ¾Ñ€ÐµÐ½Ð¸Ñ")).setValue(8.0F).range(0.0F, 50.0F).visible(() -> this.mode.isSelected("Control") && this.ctrlAccel.isValue());
   private final BindSetting bombKey = (new BindSetting("BombKey", "ÐšÐ½Ð¾Ð¿ÐºÐ° Ð±Ð¾ÑƒÐ¼Ð±Ð°")).setKey(-1);
   private long lastFireworkTime = 0L;
   private boolean flying = false;
   private boolean hasTouchedGround = false;
   private float acceleration = 0.0F;
   private float accelerationY = 0.0F;
   private float cruiseHeight = 0.0F;
   private int prevElytraSlot = -1;
   private class_1792 prevChestItem;
   private class_1799 prevChestCopy;
   private boolean elytraEquipped;
   private int slotWithFireworks;
   private class_1792 prevItemInFireSlot;
   private class_1799 prevFireSlotCopy;
   private long lastRedeployTime;
   private long lastBoostStart;

   public ElytraPlus() {
      super("Elytra+", "Elytra Plus", ModuleCategory.MOVEMENT);
      this.prevChestItem = class_1802.field_8162;
      this.prevChestCopy = null;
      this.elytraEquipped = false;
      this.slotWithFireworks = -1;
      this.prevItemInFireSlot = class_1802.field_8162;
      this.prevFireSlotCopy = null;
      this.lastRedeployTime = 0L;
      this.lastBoostStart = 0L;
      this.setup(new Setting[]{this.mode, this.fwXZSpeed, this.fwYSpeed, this.fireDelay, this.fireSlot, this.allowFireSwap, this.antiKick, this.keepFlying, this.disableOnFlag, this.cruiseControl, this.factor, this.upSpeed, this.glideFactor, this.stopMotion, this.speedLimit, this.maxSpeed, this.redeployInterval, this.redeployTimeout, this.ctrlXZSpeed, this.ctrlUpSpeed, this.ctrlDownSpeed, this.ctrlAccel, this.ctrlAccelFactor, this.bombKey});
   }

   public void activate() {
      super.activate();
      this.flying = false;
      this.acceleration = 0.0F;
      this.accelerationY = 0.0F;
      this.hasTouchedGround = false;
      this.lastFireworkTime = 0L;
      this.lastRedeployTime = 0L;
      this.lastBoostStart = System.currentTimeMillis();
      if (mc.field_1724 != null) {
         this.cruiseHeight = (float)mc.field_1724.method_23318();
      }

      this.resetPrevArmor();
      this.resetPrevFireSlot();
      if (this.mode.isSelected("FireWork")) {
         this.fireworkOnEnable();
      }

   }

   public void deactivate() {
      super.deactivate();
      if (mc.field_1724 != null) {
         mc.field_1724.method_31549().field_7479 = false;
         mc.field_1724.method_31549().method_7248(0.05F);
      }

      if (this.mode.isSelected("FireWork")) {
         this.fireworkOnDisable();
      }

      this.resetPrevArmor();
      this.resetPrevFireSlot();
      this.flying = false;
   }

   @EventHandler
   public void onTick(TickEvent e) {
      if (mc.field_1724 != null && mc.field_1687 != null) {
         if (e.getType() == 0) {
            if (this.mode.isSelected("FireWork")) {
               this.fireworkTick();
            } else if (this.mode.isSelected("Boost")) {
               this.boostTick();
               this.doBoostNoEvent();
            } else if (this.mode.isSelected("Control")) {
               this.controlTick();
               this.doControlNoEvent();
            }

         }
      }
   }

   @EventHandler
   public void onPacketReceive(PacketEvent e) {
      if (e.getPacket() instanceof class_2708) {
         if (mc.field_1724 != null) {
            this.acceleration = 0.0F;
            this.accelerationY = 0.0F;
            this.lastBoostStart = System.currentTimeMillis();
            if (this.disableOnFlag.isValue() && this.mode.isSelected("FireWork")) {
               Notifications.getInstance().addList(String.valueOf(class_124.field_1061) + "Elytra+ " + String.valueOf(class_124.field_1070) + "- Ð²Ñ‹ÐºÐ»ÑŽÑ‡ÐµÐ½ (Ñ„Ð»Ð°Ð³ Ð¿Ð¾Ð·Ð¸Ñ†Ð¸Ð¸)", 3000L);
               this.setState(false);
            }

         }
      }
   }

   private void fireworkTick() {
      if (mc.field_1724 != null) {
         boolean inAir = mc.field_1687.method_22347(class_2338.method_49638(mc.field_1724.method_19538()));
         boolean falling = mc.field_1724.method_18798().field_1351 < (double)0.0F;
         if ((!(mc.field_1724.field_6017 > 0.0F) || !inAir) && (!falling || !inAir)) {
            if (mc.field_1724.method_24828()) {
               this.flying = false;
               return;
            }
         } else {
            this.equipElytra();
         }

         if (this.canFlyFirework()) {
            if (!mc.field_1724.method_6128() && mc.field_1724.method_18798().field_1351 < (double)0.0F) {
               this.sendPacket(new class_2848(mc.field_1724, class_2849.field_12982));
               this.flying = true;
            }

            if (mc.field_1724.method_6128()) {
               this.doFirework(true);
            } else {
               this.flying = false;
            }

         }
      }
   }

   private void fireworkOnEnable() {
      if (!this.hasAnyElytra()) {
         Notifications.getInstance().addList(String.valueOf(class_124.field_1061) + "Elytra+ " + String.valueOf(class_124.field_1070) + "- ÑÐ»Ð¸Ñ‚Ñ€Ð° Ð½Ðµ Ð½Ð°Ð¹Ð´ÐµÐ½Ð°!", 3000L);
         this.setState(false);
      } else if (this.getFireworksSlot(false) == -1) {
         Notifications.getInstance().addList(String.valueOf(class_124.field_1061) + "Elytra+ " + String.valueOf(class_124.field_1070) + "- Ñ„ÐµÐ¹ÐµÑ€Ð²ÐµÑ€ÐºÐ¸ Ð½Ðµ Ð½Ð°Ð¹Ð´ÐµÐ½Ñ‹!", 3000L);
         this.setState(false);
      } else if (this.getFireworksSlot(true) == -1) {
         int slotIndex = (int)this.fireSlot.getValue() - 1;
         class_1799 stack = mc.field_1724.method_31548().method_5438(slotIndex);
         this.prevFireSlotCopy = stack.method_7972();
         this.prevItemInFireSlot = stack.method_7909();
      }
   }

   private void fireworkOnDisable() {
      this.flying = false;
      if (!this.keepFlying.isValue() && mc.field_1724 != null) {
         mc.field_1724.method_18800((double)0.0F, mc.field_1724.method_18798().field_1351, (double)0.0F);
         (new Thread(() -> {
            try {
               this.sendPacket(new class_2848(mc.field_1724, class_2849.field_12982));
               Simulations.setVelocity((double)0.1F);
               this.returnPrevFireSlot();
               this.resetPrevFireSlot();
               Thread.sleep(200L);
               this.returnPrevArmor();
               this.resetPrevArmor();
            } catch (InterruptedException var2) {
            }

         })).start();
      }
   }

   private boolean canFlyFirework() {
      if (this.shouldSwapToElytra()) {
         return false;
      } else {
         return this.getFireworksSlot(false) != -1;
      }
   }

   private void doFirework(boolean started) {
      if (!started || System.currentTimeMillis() - this.lastFireworkTime >= (long)(this.fireDelay.getValue() * 1000.0F)) {
         if (!started || mc.field_1724.method_6128()) {
            int slot = this.getFireworksSlot(false);
            if (slot == -1) {
               this.slotWithFireworks = -1;
            } else {
               this.slotWithFireworks = slot;
               boolean inOffhand = mc.field_1724.method_6079().method_31574(class_1802.field_8639);
               int prevSlot = mc.field_1724.method_31548().field_7545;
               if (!inOffhand && prevSlot != slot) {
                  this.sendPacket(new class_2868(slot));
               }

               this.sendPacket(new class_2886(inOffhand ? class_1268.field_5810 : class_1268.field_5808, 0, mc.field_1724.method_36454(), mc.field_1724.method_36455()));
               if (!inOffhand && prevSlot != mc.field_1724.method_31548().field_7545) {
                  this.sendPacket(new class_2868(prevSlot));
               }

               this.flying = true;
               this.lastFireworkTime = System.currentTimeMillis();
            }
         }
      }
   }

   private void boostTick() {
      if (mc.field_1724 != null) {
         if (mc.field_1724.method_24828()) {
            this.hasTouchedGround = true;
         }

         if (!this.cruiseControl.isValue()) {
            this.cruiseHeight = (float)mc.field_1724.method_23318();
         }

         if (!mc.field_1724.method_6128() && this.hasTouchedGround && !mc.field_1724.method_24828() && mc.field_1724.field_6017 > 0.0F && mc.field_1724.method_18798().field_1351 < (double)0.0F && System.currentTimeMillis() - this.lastRedeployTime > (long)(this.redeployInterval.getValue() * 1000.0F)) {
            this.sendPacket(new class_2848(mc.field_1724, class_2849.field_12982));
            this.lastRedeployTime = System.currentTimeMillis();
            this.hasTouchedGround = false;
         }

      }
   }

   private void doBoostNoEvent() {
      if (mc.field_1724 != null) {
         if (this.mode.isSelected("Boost")) {
            if (mc.field_1724.method_6128()) {
               if (!mc.field_1724.method_5799() && !mc.field_1724.method_5771()) {
                  double x = mc.field_1724.method_18798().field_1352;
                  double y = mc.field_1724.method_18798().field_1351;
                  double z = mc.field_1724.method_18798().field_1350;
                  if (mc.field_1690.field_1903.method_1434()) {
                     y += (double)this.upSpeed.getValue() * 0.1;
                  } else {
                     y -= 0.08 * (double)this.glideFactor.getValue();
                  }

                  float yaw = mc.field_1724.method_36454();
                  float forward = mc.field_1724.field_3913.field_3905;
                  if (forward != 0.0F) {
                     double rad = Math.toRadians((double)(yaw + (forward < 0.0F ? 180.0F : 0.0F)));
                     double mul = (double)(this.factor.getValue() / 20.0F);
                     x -= Math.sin(rad) * mul;
                     z += Math.cos(rad) * mul;
                  }

                  double speed = Math.sqrt(x * x + z * z);
                  if (this.speedLimit.isValue() && speed > (double)this.maxSpeed.getValue()) {
                     double k = (double)this.maxSpeed.getValue() / speed;
                     x *= k;
                     z *= k;
                  }

                  mc.field_1724.method_18800(x, y, z);
               }
            }
         }
      }
   }

   private void controlTick() {
      if (mc.field_1724 != null) {
         if (mc.field_1724.method_6128()) {
            if (this.mode.isSelected("Control")) {
               ;
            }
         }
      }
   }

   private void doControlNoEvent() {
      if (mc.field_1724 != null) {
         if (this.mode.isSelected("Control")) {
            if (mc.field_1724.method_6128()) {
               double speed = (double)this.ctrlXZSpeed.getValue();
               if (this.ctrlAccel.isValue()) {
                  this.acceleration = Math.min(this.acceleration + this.ctrlAccelFactor.getValue() / 100.0F, 1.0F);
                  speed *= (double)this.acceleration;
               }

               double[] dir = this.forward(speed);
               double y = mc.field_1690.field_1903.method_1434() ? (double)this.ctrlUpSpeed.getValue() : (mc.field_1690.field_1832.method_1434() ? (double)(-this.ctrlDownSpeed.getValue()) : -0.08 * (double)this.glideFactor.getValue());
               if (!this.isMoving()) {
                  this.acceleration = 0.0F;
               }

               mc.field_1724.method_18800(dir[0], y, dir[1]);
            }
         }
      }
   }

   private boolean hasAnyElytra() {
      if (mc.field_1724 == null) {
         return false;
      } else if (mc.field_1724.method_6118(class_1304.field_6174).method_31574(class_1802.field_8833)) {
         return true;
      } else {
         for(int i = 0; i < mc.field_1724.method_31548().method_5439(); ++i) {
            if (mc.field_1724.method_31548().method_5438(i).method_31574(class_1802.field_8833)) {
               return true;
            }
         }

         return false;
      }
   }

   private int getElytraSlot() {
      if (mc.field_1724 == null) {
         return -1;
      } else {
         for(int i = 0; i < mc.field_1724.method_31548().method_5439(); ++i) {
            if (mc.field_1724.method_31548().method_5438(i).method_31574(class_1802.field_8833)) {
               return i;
            }
         }

         return -1;
      }
   }

   private boolean shouldSwapToElytra() {
      if (mc.field_1724 == null) {
         return false;
      } else {
         class_1799 chest = mc.field_1724.method_6118(class_1304.field_6174);
         return !chest.method_31574(class_1802.field_8833);
      }
   }

   private void equipElytra() {
      if (mc.field_1724 != null) {
         int elytra = this.getElytraSlot();
         if (elytra == -1 && !mc.field_1724.field_7512.method_34255().method_31574(class_1802.field_8833)) {
            Notifications.getInstance().addList(String.valueOf(class_124.field_1061) + "Elytra+ " + String.valueOf(class_124.field_1070) + "- ÑÐ»Ð¸Ñ‚Ñ€Ð° Ð½Ðµ Ð½Ð°Ð¹Ð´ÐµÐ½Ð°!", 3000L);
            this.setState(false);
         } else if (this.shouldSwapToElytra()) {
            if (this.prevElytraSlot == -1) {
               class_1799 chest = mc.field_1724.method_6118(class_1304.field_6174);
               this.prevElytraSlot = elytra;
               this.prevChestItem = chest.method_7909();
               this.prevChestCopy = chest.method_7972();
            }

            int invSlot = elytra;
            if (elytra >= 0 && elytra < 9) {
               invSlot = elytra + 36;
            }

            this.clickSlot(invSlot);
            this.clickSlot(6);
            if (this.prevElytraSlot != -1) {
               this.clickSlot(this.prevElytraSlot);
            }

            this.elytraEquipped = true;
         }
      }
   }

   private void returnPrevArmor() {
      if (this.prevElytraSlot != -1 && this.prevChestItem != class_1802.field_8162) {
         if (this.elytraEquipped) {
            if (mc.field_1724 != null) {
               int slot = findInInventory(this.prevChestCopy, this.prevChestItem);
               if (slot == -1) {
                  this.resetPrevArmor();
               } else {
                  if (slot < 9) {
                     slot += 36;
                  }

                  if (!mc.field_1724.field_7512.method_34255().method_7960()) {
                     this.clickSlot(6);
                     if (this.prevElytraSlot != -1) {
                        this.clickSlot(this.prevElytraSlot);
                     }

                     this.resetPrevArmor();
                  } else {
                     this.clickSlot(slot);
                     this.clickSlot(6);
                     this.resetPrevArmor();
                  }
               }
            }
         }
      }
   }

   private void resetPrevArmor() {
      this.prevElytraSlot = -1;
      this.prevChestItem = class_1802.field_8162;
      this.prevChestCopy = null;
      this.elytraEquipped = false;
   }

   private void resetPrevFireSlot() {
      this.slotWithFireworks = -1;
      this.prevItemInFireSlot = class_1802.field_8162;
      this.prevFireSlotCopy = null;
   }

   private void returnPrevFireSlot() {
      if (this.slotWithFireworks != -1 && this.prevFireSlotCopy != null && this.prevItemInFireSlot != class_1802.field_8162) {
         if (mc.field_1724 != null) {
            int prevSlotIndex = (int)this.fireSlot.getValue() - 1;
            class_1799 cur = mc.field_1724.method_31548().method_5438(prevSlotIndex);
            if (cur.method_7909() != this.prevItemInFireSlot) {
               int found = findInInventory(this.prevFireSlotCopy, this.prevItemInFireSlot);
               if (found != -1) {
                  if (found < 9) {
                     found += 36;
                  }

                  this.clickSlot(found);
                  this.clickSlot(prevSlotIndex + 36);
                  this.clickSlot(found);
               }
            }
         }
      }
   }

   private int getFireworksSlot(boolean onlyHotbar) {
      if (mc.field_1724 == null) {
         return -1;
      } else if (mc.field_1724.method_6079().method_31574(class_1802.field_8639)) {
         return -2;
      } else if (onlyHotbar) {
         for(int i = 0; i < 9; ++i) {
            if (mc.field_1724.method_31548().method_5438(i).method_31574(class_1802.field_8639)) {
               return i;
            }
         }

         return -1;
      } else {
         int hotbar = this.getFireworksSlot(true);
         if (hotbar != -1) {
            return hotbar;
         } else {
            int inv = -1;

            for(int i = 9; i < mc.field_1724.method_31548().method_5439(); ++i) {
               if (mc.field_1724.method_31548().method_5438(i).method_31574(class_1802.field_8639)) {
                  inv = i;
                  break;
               }
            }

            if (inv == -1) {
               return -1;
            } else if (!this.allowFireSwap.isValue()) {
               Notifications.getInstance().addList(String.valueOf(class_124.field_1061) + "Elytra+ " + String.valueOf(class_124.field_1070) + "- Ð½ÐµÑ‚ Ñ„ÐµÐ¹ÐµÑ€Ð²ÐµÑ€ÐºÐ¾Ð² Ð² Ñ…Ð¾Ñ‚Ð±Ð°Ñ€Ðµ!", 3000L);
               return (int)this.fireSlot.getValue() - 1;
            } else {
               this.moveFireworksToHotbar(inv);
               return (int)this.fireSlot.getValue() - 1;
            }
         }
      }
   }

   private void moveFireworksToHotbar(int invSlot) {
      if (mc.field_1724 != null) {
         int hotSlot = (int)this.fireSlot.getValue() - 1 + 36;
         if (invSlot < 9) {
            invSlot += 36;
         }

         this.clickSlot(invSlot);
         this.clickSlot(hotSlot);
         this.clickSlot(invSlot);
      }
   }

   private void clickSlot(int slot) {
      if (mc.field_1724 != null && mc.field_1761 != null) {
         mc.field_1761.method_2906(mc.field_1724.field_7512.field_7763, slot, 0, class_1713.field_7790, mc.field_1724);
      }
   }

   private static int findInInventory(class_1799 stack, class_1792 item) {
      if (stack != null && mc.field_1724 != null) {
         for(int i = 0; i < mc.field_1724.method_31548().method_5439(); ++i) {
            class_1799 is = mc.field_1724.method_31548().method_5438(i);
            if (class_1799.method_7984(is, stack) && is.method_7909() == item) {
               return i;
            }
         }

         return -1;
      } else {
         return -1;
      }
   }

   private boolean isMoving() {
      if (mc.field_1724 == null) {
         return false;
      } else {
         return mc.field_1724.field_3913.field_3905 != 0.0F || mc.field_1724.field_3913.field_3907 != 0.0F;
      }
   }

   private double[] forward(double speed) {
      if (mc.field_1724 == null) {
         return new double[]{(double)0.0F, (double)0.0F};
      } else {
         float yaw = mc.field_1724.method_36454();
         float forward = mc.field_1724.field_3913.field_3905;
         float strafe = mc.field_1724.field_3913.field_3907;
         if (forward == 0.0F && strafe == 0.0F) {
            return new double[]{(double)0.0F, (double)0.0F};
         } else {
            if (forward != 0.0F) {
               if (strafe > 0.0F) {
                  yaw += (float)(forward > 0.0F ? -45 : 45);
               } else if (strafe < 0.0F) {
                  yaw += (float)(forward > 0.0F ? 45 : -45);
               }

               strafe = 0.0F;
               forward = forward > 0.0F ? 1.0F : -1.0F;
            }

            double rad = Math.toRadians((double)(yaw + 90.0F));
            double sin = Math.sin(rad);
            double cos = Math.cos(rad);
            double mx = (double)forward * speed * cos + (double)strafe * speed * sin;
            double mz = (double)forward * speed * sin - (double)strafe * speed * cos;
            return new double[]{mx, mz};
         }
      }
   }

   private void sendPacket(class_2596 p) {
      if (mc.method_1562() != null) {
         mc.method_1562().method_52787(p);
      }

   }

   public static enum Mode {
      FireWork,
      Boost,
      Control;

      // $FF: synthetic method
      private static Mode[] $values() {
         return new Mode[]{FireWork, Boost, Control};
      }
   }

   public static enum AntiKick {
      Off,
      Jitter,
      Glide;

      // $FF: synthetic method
      private static AntiKick[] $values() {
         return new AntiKick[]{Off, Jitter, Glide};
      }
   }
}

