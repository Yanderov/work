package fun.Yanderov.features.impl.render;

import fun.Yanderov.events.keyboard.HotBarScrollEvent;
import fun.Yanderov.events.keyboard.KeyEvent;
import fun.Yanderov.events.render.CameraEvent;
import fun.Yanderov.events.render.FovEvent;
import fun.Yanderov.features.impl.player.FreeLook;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.features.module.setting.Setting;
import fun.Yanderov.features.module.setting.implement.BindSetting;
import fun.Yanderov.features.module.setting.implement.BooleanSetting;
import fun.Yanderov.features.module.setting.implement.SliderSettings;
import fun.Yanderov.utils.client.Instance;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import fun.Yanderov.utils.features.aura.utils.MathAngle;
import fun.Yanderov.utils.interactions.interact.PlayerInteractionHelper;
import fun.Yanderov.utils.math.calc.Calculate;
import net.minecraft.class_3532;

public class CameraSettings extends Module {
   private float fov = 110.0F;
   private float smoothFov = 30.0F;
   private float lastChangedFov = 30.0F;
   private BooleanSetting clipSetting = (new BooleanSetting("ÐŸÑ€Ð¾Ñ…Ð¾Ð´ ÐºÐ°Ð¼ÐµÑ€Ñ‹", "ÐšÐ°Ð¼ÐµÑ€Ð° Ð¿Ñ€Ð¾Ñ…Ð¾Ð´Ð¸Ñ‚ ÑÐºÐ²Ð¾Ð·ÑŒ Ð±Ð»Ð¾ÐºÐ¸")).setValue(true);
   private SliderSettings distanceSetting = (new SliderSettings("Ð”Ð¸ÑÑ‚Ð°Ð½Ñ†Ð¸Ñ ÐºÐ°Ð¼ÐµÑ€Ñ‹", "ÐÐ°ÑÑ‚Ñ€Ð¾Ð¹ÐºÐ° Ñ€Ð°ÑÑÑ‚Ð¾ÑÐ½Ð¸Ñ ÐºÐ°Ð¼ÐµÑ€Ñ‹")).setValue(3.0F).range(2.0F, 5.0F);
   private BindSetting zoomSetting = new BindSetting("Ð—ÑƒÐ¼", "ÐšÐ»Ð°Ð²Ð¸ÑˆÐ° Ð´Ð»Ñ ÑƒÐ²ÐµÐ»Ð¸Ñ‡ÐµÐ½Ð¸Ñ ÐºÐ°Ð¼ÐµÑ€Ñ‹");

   public CameraSettings() {
      super("CameraSettings", "Camera Settings", ModuleCategory.RENDER);
      this.setup(new Setting[]{this.clipSetting, this.distanceSetting, this.zoomSetting});
   }

   @EventHandler
   public void onKey(KeyEvent e) {
      if (e.isKeyDown(this.zoomSetting.getKey())) {
         this.fov = Math.min(this.lastChangedFov, (float)((Integer)mc.field_1690.method_41808().method_41753() - 20));
      }

      if (e.isKeyReleased(this.zoomSetting.getKey(), true)) {
         this.lastChangedFov = this.fov;
         this.fov = (float)(Integer)mc.field_1690.method_41808().method_41753();
      }

   }

   @EventHandler
   public void onHotBarScroll(HotBarScrollEvent e) {
      if (PlayerInteractionHelper.isKey(this.zoomSetting)) {
         this.fov = (float)((int)class_3532.method_15350((double)this.fov - e.getVertical() * (double)10.0F, (double)10.0F, (double)(Integer)mc.field_1690.method_41808().method_41753()));
         e.cancel();
      }

   }

   @EventHandler
   public void onFov(FovEvent e) {
      e.setFov((int)class_3532.method_15363((this.smoothFov = Calculate.interpolateSmooth(1.6, this.smoothFov, this.fov)) + 1.0F, 10.0F, (float)(Integer)mc.field_1690.method_41808().method_41753()));
      e.cancel();
   }

   @EventHandler
   public void onCamera(CameraEvent e) {
      e.setCameraClip(this.clipSetting.isValue());
      e.setDistance(this.distanceSetting.getValue());
      FreeLook freeLook = (FreeLook)Instance.get(FreeLook.class);
      if (!freeLook.isState() || !PlayerInteractionHelper.isKey(FreeLook.freeLookSetting)) {
         e.setAngle(MathAngle.cameraAngle());
      }

      e.cancel();
   }
}

