package fun.Yanderov.features.impl.movement;

import fun.Yanderov.events.player.TickEvent;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.features.module.setting.Setting;
import fun.Yanderov.features.module.setting.implement.BindSetting;
import fun.Yanderov.features.module.setting.implement.BooleanSetting;
import fun.Yanderov.features.module.setting.implement.SelectSetting;
import fun.Yanderov.features.module.setting.implement.SliderSettings;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import fun.Yanderov.utils.display.interfaces.QuickImports;
import java.util.function.Consumer;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2828;
import net.minecraft.class_3675;

public class Clip extends Module implements QuickImports {
   private final SelectSetting mode = (new SelectSetting("Ð ÐµÐ¶Ð¸Ð¼", "Ð’Ñ‹Ð±ÐµÑ€Ð¸Ñ‚Ðµ Ñ€ÐµÐ¶Ð¸Ð¼")).value("Fancy", "Old").selected("Fancy");
   private final SliderSettings horizontal = (new SliderSettings("Horizontal", "Ð“Ð¾Ñ€Ð¸Ð·Ð¾Ð½Ñ‚Ð°Ð»ÑŒÐ½Ð°Ñ Ð´Ð»Ð¸Ð½Ð°")).setValue(0.0F).range(0, 30).visible(() -> this.mode.isSelected("Fancy"));
   private final SliderSettings vertical = (new SliderSettings("Vertical", "Ð’ÐµÑ€Ñ‚Ð¸ÐºÐ°Ð»ÑŒÐ½Ð°Ñ Ð´Ð»Ð¸Ð½Ð°")).setValue(5.0F).range(0, 30).visible(() -> this.mode.isSelected("Fancy"));
   private final BooleanSetting requiresStandOn = (new BooleanSetting("RequiresStandOn", "ÐÑƒÐ¶Ð½Ð° Ð¾Ð¿Ð¾Ñ€Ð°")).setValue(true).visible(() -> this.mode.isSelected("Fancy"));
   private final SliderSettings oldHorizontal = (new SliderSettings("Old Horizontal", "Ð“Ð¾Ñ€. ÑÐ¼ÐµÑ‰ÐµÐ½Ð¸Ðµ (Old)")).setValue(0.0F).range(-50, 50).visible(() -> this.mode.isSelected("Old"));
   private final SliderSettings oldVertical = (new SliderSettings("Old Vertical", "Ð’ÐµÑ€Ñ‚Ð¸Ðº. ÑÐ¼ÐµÑ‰ÐµÐ½Ð¸Ðµ (Old)")).setValue(5.0F).range(-50, 50).visible(() -> this.mode.isSelected("Old"));
   private final BooleanSetting resetVelocity = (new BooleanSetting("Reset Velocity", "Ð¡Ð±Ñ€Ð¾Ñ ÑÐºÐ¾Ñ€Ð¾ÑÑ‚Ð¸ (Old)")).setValue(true).visible(() -> this.mode.isSelected("Old"));
   private final BindSetting bindHorizontal = new BindSetting("Bind Horizontal", "Ð’Ñ‹Ð¿Ð¾Ð»Ð½Ð¸Ñ‚ÑŒ Ð³Ð¾Ñ€Ð¸Ð·Ð¾Ð½Ñ‚Ð°Ð»ÑŒÐ½Ñ‹Ð¹ ÐºÐ»Ð¸Ð¿");
   private final BindSetting bindVertical = new BindSetting("Bind Vertical", "Ð’Ñ‹Ð¿Ð¾Ð»Ð½Ð¸Ñ‚ÑŒ Ð²ÐµÑ€Ñ‚Ð¸ÐºÐ°Ð»ÑŒÐ½Ñ‹Ð¹ ÐºÐ»Ð¸Ð¿");
   private boolean lastHPressed = false;
   private boolean lastVPressed = false;

   public Clip() {
      super("Clip", ModuleCategory.MOVEMENT);
      this.setup(new Setting[]{this.mode, this.horizontal, this.vertical, this.requiresStandOn, this.oldHorizontal, this.oldVertical, this.resetVelocity, this.bindHorizontal, this.bindVertical});
   }

   @EventHandler
   public void onTick(TickEvent event) {
      if (mc.field_1724 != null && mc.field_1687 != null) {
         this.handleBindActions();
         if (this.isState()) {
            if (this.mode.isSelected("Old")) {
               this.performOldClip();
               this.setState(false);
            } else {
               this.handleFancyClip();
            }
         }
      }
   }

   private void handleBindActions() {
      long window = mc.method_22683().method_4490();
      boolean hPressed = this.bindHorizontal.getKey() != 0 && class_3675.method_15987(window, this.bindHorizontal.getKey());
      boolean vPressed = this.bindVertical.getKey() != 0 && class_3675.method_15987(window, this.bindVertical.getKey());
      if (hPressed && !this.lastHPressed) {
         this.performHorizontalOnly();
      }

      if (vPressed && !this.lastVPressed) {
         this.performVerticalOnly();
      }

      this.lastHPressed = hPressed;
      this.lastVPressed = vPressed;
   }

   private void performOldClip() {
      double yaw = Math.toRadians((double)mc.field_1724.method_36454());
      double dx = -Math.sin(yaw) * (double)this.oldHorizontal.getValue();
      double dz = Math.cos(yaw) * (double)this.oldHorizontal.getValue();
      double x = mc.field_1724.method_23317() + dx;
      double y = mc.field_1724.method_23318() + (double)this.oldVertical.getValue();
      double z = mc.field_1724.method_23321() + dz;
      this.teleportTo(x, y, z);
      if (this.resetVelocity.isValue()) {
         mc.field_1724.method_18800((double)0.0F, (double)0.0F, (double)0.0F);
      }

   }

   private void performHorizontalOnly() {
      if (this.mode.isSelected("Old")) {
         double yaw = Math.toRadians((double)mc.field_1724.method_36454());
         double dx = -Math.sin(yaw) * (double)this.oldHorizontal.getValue();
         double dz = Math.cos(yaw) * (double)this.oldHorizontal.getValue();
         double x = mc.field_1724.method_23317() + dx;
         double y = mc.field_1724.method_23318();
         double z = mc.field_1724.method_23321() + dz;
         this.teleportTo(x, y, z);
         if (this.resetVelocity.isValue()) {
            mc.field_1724.method_18800((double)0.0F, (double)0.0F, (double)0.0F);
         }

      } else {
         class_2350 dir = this.determineHorizontalDirection();
         if (dir == null) {
            dir = mc.field_1724.method_5735();
         }

         int len = (int)this.horizontal.getValue();
         this.tryClip(dir, len, (pos) -> {
            double x = (double)pos.method_10263() + (double)0.5F;
            double y = (double)pos.method_10264();
            double z = (double)pos.method_10260() + (double)0.5F;
            this.teleportTo(x, y, z);
            mc.field_1724.method_18800((double)0.0F, (double)0.0F, (double)0.0F);
         });
      }
   }

   private void performVerticalOnly() {
      if (this.mode.isSelected("Old")) {
         double x = mc.field_1724.method_23317();
         double y = mc.field_1724.method_23318() + (double)this.oldVertical.getValue();
         double z = mc.field_1724.method_23321();
         this.teleportTo(x, y, z);
         if (this.resetVelocity.isValue()) {
            mc.field_1724.method_18800((double)0.0F, (double)0.0F, (double)0.0F);
         }

      } else {
         class_2350 dir = mc.field_1690.field_1903.method_1434() ? class_2350.field_11036 : class_2350.field_11033;
         int len = (int)this.vertical.getValue();
         this.tryClip(dir, len, (pos) -> {
            double x = (double)pos.method_10263() + (double)0.5F;
            double y = (double)pos.method_10264();
            double z = (double)pos.method_10260() + (double)0.5F;
            this.teleportTo(x, y, z);
            mc.field_1724.method_18800((double)0.0F, (double)0.0F, (double)0.0F);
         });
      }
   }

   private void handleFancyClip() {
      class_2350 direction = this.determineMovementDirection();
      if (direction != null) {
         int clipLength = direction != class_2350.field_11036 && direction != class_2350.field_11033 ? (int)this.horizontal.getValue() : (int)this.vertical.getValue();
         this.tryClip(direction, clipLength, (pos) -> {
            double x = (double)pos.method_10263() + (double)0.5F;
            double y = (double)pos.method_10264();
            double z = (double)pos.method_10260() + (double)0.5F;
            this.teleportTo(x, y, z);
            mc.field_1724.method_18800((double)0.0F, (double)0.0F, (double)0.0F);
         });
      }
   }

   private class_2350 determineHorizontalDirection() {
      class_2350 facing = mc.field_1724.method_5735();
      if (mc.field_1690.field_1894.method_1434()) {
         return facing;
      } else if (mc.field_1690.field_1881.method_1434()) {
         return facing.method_10153();
      } else if (mc.field_1690.field_1913.method_1434()) {
         return facing.method_10170().method_10153();
      } else {
         return mc.field_1690.field_1849.method_1434() ? facing.method_10170() : null;
      }
   }

   private class_2350 determineMovementDirection() {
      if (mc.field_1724.field_5976) {
         class_2350 facing = mc.field_1724.method_5735();
         if (mc.field_1690.field_1894.method_1434()) {
            return facing;
         } else if (mc.field_1690.field_1881.method_1434()) {
            return facing.method_10153();
         } else if (mc.field_1690.field_1913.method_1434()) {
            return facing.method_10170().method_10153();
         } else {
            return mc.field_1690.field_1849.method_1434() ? facing.method_10170() : null;
         }
      } else if (mc.field_1690.field_1832.method_1434()) {
         return class_2350.field_11033;
      } else {
         return mc.field_1690.field_1903.method_1434() ? class_2350.field_11036 : null;
      }
   }

   private void tryClip(class_2350 movementDirection, int length, Consumer clip) {
      if (length > 0 && mc.field_1724 != null && mc.field_1687 != null) {
         class_2338.class_2339 position = mc.field_1724.method_24515().method_25503();
         boolean wallBetween = false;

         for(int i = 0; i < length; ++i) {
            position.method_10098(movementDirection);
            if (this.isPossibleLocation(position, this.requiresStandOn.isValue() && movementDirection != class_2350.field_11036)) {
               if (wallBetween) {
                  clip.accept(position.method_10062());
                  return;
               }
            } else {
               wallBetween = true;
            }
         }

      }
   }

   private boolean isPossibleLocation(class_2338 blockPos, boolean requiresStandOn) {
      if (mc.field_1687 == null) {
         return false;
      } else {
         if (requiresStandOn) {
            class_2338 below = blockPos.method_10074();
            if (!mc.field_1687.method_8320(below).method_26206(mc.field_1687, below, class_2350.field_11036)) {
               return false;
            }
         }

         return mc.field_1687.method_8320(blockPos).method_26215() && mc.field_1687.method_8320(blockPos.method_10084()).method_26215();
      }
   }

   private void teleportTo(double x, double y, double z) {
      if (mc.method_1562() != null && mc.field_1724 != null) {
         try {
            mc.method_1562().method_52787(new class_2828.class_2829(x, y, z, false, false));
         } catch (Throwable var8) {
         }

         mc.field_1724.method_5814(x, y, z);
      }
   }
}

