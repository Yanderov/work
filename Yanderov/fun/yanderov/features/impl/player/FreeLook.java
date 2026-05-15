package fun.Yanderov.features.impl.player;

import fun.Yanderov.events.keyboard.KeyEvent;
import fun.Yanderov.events.keyboard.MouseRotationEvent;
import fun.Yanderov.events.render.CameraEvent;
import fun.Yanderov.events.render.FovEvent;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.features.module.setting.Setting;
import fun.Yanderov.features.module.setting.implement.BindSetting;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import fun.Yanderov.utils.features.aura.utils.MathAngle;
import fun.Yanderov.utils.features.aura.warp.Turns;
import fun.Yanderov.utils.interactions.interact.PlayerInteractionHelper;
import net.minecraft.class_3532;
import net.minecraft.class_5498;

public class FreeLook extends Module {
   private class_5498 perspective;
   private Turns angle;
   public static BindSetting freeLookSetting = new BindSetting("Ð¡Ð²Ð¾Ð±Ð¾Ð´Ð½Ñ‹Ð¹ Ð¾Ð±Ð·Ð¾Ñ€", "ÐšÐ»Ð°Ð²Ð¸ÑˆÐ° Ð´Ð»Ñ ÑÐ²Ð¾Ð±Ð¾Ð´Ð½Ð¾Ð³Ð¾ Ð¾Ð±Ð·Ð¾Ñ€Ð°");

   public FreeLook() {
      super("FreeLook", "Free Look", ModuleCategory.RENDER);
      this.setup(new Setting[]{freeLookSetting});
      this.angle = null;
   }

   @EventHandler
   public void onKey(KeyEvent e) {
      if (e.isKeyDown(freeLookSetting.getKey())) {
         this.perspective = mc.field_1690.method_31044();
         if (this.angle == null) {
            this.angle = MathAngle.cameraAngle();
         }
      }

   }

   @EventHandler
   public void onFov(FovEvent e) {
      if (PlayerInteractionHelper.isKey(freeLookSetting)) {
         if (mc.field_1690.method_31044().method_31034()) {
            mc.field_1690.method_31043(class_5498.field_26665);
         }

         if (this.angle == null) {
            this.angle = MathAngle.cameraAngle();
         }
      } else if (this.perspective != null) {
         mc.field_1690.method_31043(this.perspective);
         this.perspective = null;
         this.angle = null;
      }

   }

   @EventHandler
   public void onMouseRotation(MouseRotationEvent e) {
      if (PlayerInteractionHelper.isKey(freeLookSetting)) {
         if (this.angle == null) {
            this.angle = MathAngle.cameraAngle();
         }

         this.angle.setYaw(this.angle.getYaw() + e.getCursorDeltaX() * 0.15F);
         this.angle.setPitch(class_3532.method_15363(this.angle.getPitch() + e.getCursorDeltaY() * 0.15F, -90.0F, 90.0F));
         e.cancel();
      } else {
         this.angle = null;
      }

   }

   @EventHandler
   public void onCamera(CameraEvent e) {
      if (PlayerInteractionHelper.isKey(freeLookSetting) && this.angle != null) {
         e.setAngle(this.angle);
         e.cancel();
      }

   }
}

