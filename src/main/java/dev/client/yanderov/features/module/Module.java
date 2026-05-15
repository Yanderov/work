package dev.client.yanderov.features.module;

import dev.client.yanderov.Yanderov;
import dev.client.yanderov.common.animation.Animation;
import dev.client.yanderov.common.animation.Direction;
import dev.client.yanderov.common.animation.implement.Decelerate;
import dev.client.yanderov.display.hud.Notifications;
import dev.client.yanderov.features.impl.render.Hud;
import dev.client.yanderov.features.module.setting.SettingRepository;
import dev.client.yanderov.utils.client.managers.event.EventManager;
import dev.client.yanderov.utils.client.sound.SoundManager;
import dev.client.yanderov.utils.display.interfaces.QuickImports;
import net.minecraft.class_124;
import net.minecraft.class_310;
import net.minecraft.class_3414;

public class Module extends SettingRepository implements QuickImports {
   private final String name;
   private final String visibleName;
   private final ModuleCategory category;
   private final Animation animation = (new Decelerate()).setMs(175).setValue((double)1.0F);
   private int key = -1;
   private int type = 1;
   public boolean state;

   public Module(String name, ModuleCategory category) {
      this.name = name;
      this.category = category;
      this.visibleName = name;
   }

   public Module(String name, String visibleName, ModuleCategory category) {
      this.name = name;
      this.visibleName = visibleName;
      this.category = category;
   }

   public void switchState() {
      this.setState(!this.state);
   }

   public void setState(boolean state) {
      this.animation.setDirection(state ? Direction.FORWARDS : Direction.BACKWARDS);
      if (state != this.state) {
         this.state = state;
         this.handleStateChange();
      }

   }

   private void handleStateChange() {
      class_310 mc = class_310.method_1551();
      float volume = Hud.getInstance().getModuleVolume();
      if (mc.field_1724 != null && mc.field_1687 != null) {
         if (this.state) {
            if (Hud.getInstance().notificationSettings.isSelected("Module Switch")) {
               Notifications var10000 = Notifications.getInstance();
               String var10001 = String.valueOf(class_124.field_1080);
               var10000.addList((String)("Feature " + var10001 + this.visibleName + String.valueOf(class_124.field_1070) + " - enabled!"), 2000L, (class_3414)null);
               SoundManager.playSound(SoundManager.ENABLE_MODULE, volume, 1.0F);
            }

            this.activate();
         } else {
            if (Hud.getInstance().notificationSettings.isSelected("Module Switch")) {
               Notifications var3 = Notifications.getInstance();
               String var4 = String.valueOf(class_124.field_1080);
               var3.addList((String)("Feature " + var4 + this.visibleName + String.valueOf(class_124.field_1070) + " - disabled!"), 2000L, (class_3414)null);
               SoundManager.playSound(SoundManager.DISABLE_MODULE, volume, 1.0F);
            }

            this.deactivate();
         }
      }

      this.toggleSilent(this.state);
   }

   private void toggleSilent(boolean activate) {
      EventManager eventManager = YanderovIntegration.getInstance().getEventManager();
      if (activate) {
         eventManager.register(this);
      } else {
         eventManager.unregister(this);
      }

   }

   public void activate() {
   }

   public void deactivate() {
   }

   public String getName() {
      return this.name;
   }

   public String getVisibleName() {
      return this.visibleName;
   }

   public ModuleCategory getCategory() {
      return this.category;
   }

   public Animation getAnimation() {
      return this.animation;
   }

   public int getKey() {
      return this.key;
   }

   public int getType() {
      return this.type;
   }

   public boolean isState() {
      return this.state;
   }

   public void setKey(int key) {
      this.key = key;
   }

   public void setType(int type) {
      this.type = type;
   }
}

