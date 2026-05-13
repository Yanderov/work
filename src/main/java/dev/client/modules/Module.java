package dev.client.modules;

import dev.client.WildClient;
import dev.client.managers.FontManager;
import dev.client.modules.impl.render.Interface;
import dev.client.modules.impl.util.ClientSound;
import dev.client.modules.settings.Setting;
import dev.client.ui.notify.Status;
import dev.client.util.animations.Animation;
import dev.client.util.animations.Direction;
import dev.client.util.animations.impl.EaseBackIn;
import dev.client.util.other.BindUtill;
import dev.client.util.other.SoundUtil;
import dev.client.util.render.msdf.MsdfFont;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;

@Environment(EnvType.CLIENT)
public abstract class Module {
   private final PlayerModel PlayerModel;
   private boolean enabled;
   private boolean binded;
   private int bind;
   private final List<Setting> settings;
   private double height;
   private final Animation animation;
   private final Animation animSettings;
   private boolean opened;
   private String nameBind;
   private double defaultHeight;
   private String[] desc;
   private boolean doubleDesc = false;

   public Module(PlayerModel PlayerModel) {
      this.animation = new EaseBackIn(325, 1.0D, 0.1F, Direction.BACKWARDS);
      this.animSettings = new EaseBackIn(325, 1.0D, 0.1F, Direction.BACKWARDS);
      this.PlayerModel = PlayerModel;
      this.enabled = false;
      this.bind = -1;
      this.settings = new ArrayList<>();
      this.height = 38.5D;
      this.defaultHeight = 38.5D;
      this.nameBind = "n/a";
      this.opened = false;
      this.binded = false;
      this.desc = new String[2];
   }

   public void initDesc() {
      this.defaultHeight = 38.5D;
      double xBind = 159.5D - (double)FontManager.SUISSEINTMEDIUM.get().getWidth(this.getNameBind(), 5.8F);
      String[] array = this.getPlayerModel().desc().split(" ");
      double maxWidthText = xBind - 28.0D;
      this.desc = new String[2];
      this.desc[0] = "";
      this.desc[1] = "";
      boolean twoLine = false;

      for(String s : array) {
         if ((double)FontManager.SUISSEINTMEDIUM.get().getWidth(this.desc[0] + s, 5.8F) < maxWidthText && !twoLine) {
            String[] text = this.desc;
            text[0] = text[0] + s + " ";
         } else {
            String[] text = this.desc;
            text[1] = text[1] + s + " ";
            twoLine = true;
         }
      }

      if (this.desc[1] != null && !this.desc[1].isEmpty()) {
         if (!this.opened) {
            this.height = 45.5D;
         }

         this.defaultHeight = 45.5D;
         this.doubleDesc = true;
      }

   }

   public PlayerModel getPlayerModel() {
      return this.PlayerModel;
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public void setBind(int bind) {
      this.nameBind = BindUtill.getBind(bind);
      this.bind = bind;
      if (WildClient.INSTANCE.getMainScreen().isInit()) {
         this.initDesc();
      }

   }

   public String getNameBind() {
      return this.nameBind;
   }

   public void setEnabled(boolean enabled) {
      if (enabled && this instanceof IEnableable iEnableable) {
         iEnableable.onEnable();
      } else if (!enabled && this instanceof IDisableable iDisableable) {
         iDisableable.onDisable();
      }

      Interface hud = WildClient.INSTANCE.getModuleManager() == null ? null : (Interface)WildClient.INSTANCE.getModuleManager().getByClass(Interface.class);
      boolean hasNofify = hud != null && hud.isEnabled() & hud.getElements().getValueByName("Notify");
      if (enabled) {
         WildClient.INSTANCE.getEventManager().register(this);
         this.animation.setDirection(Direction.FORWARDS);
         if (hasNofify) {
            WildClient.INSTANCE.getNotifyManager().addNotify(this.getPlayerModel().name() + " was enabled", Status.SUCCESS);
         }
      } else {
         WildClient.INSTANCE.getEventManager().unregister(this);
         this.animation.setDirection(Direction.BACKWARDS);
         if (hasNofify) {
            WildClient.INSTANCE.getNotifyManager().addNotify(this.getPlayerModel().name() + " was disabled", Status.SUCCESS);
         }
      }

      if (MinecraftClient.getInstance().player != null) {
         ClientSound clientSound = (ClientSound)WildClient.INSTANCE.getModuleManager().getByClass(ClientSound.class);
         if (clientSound.isEnabled()) {
            if (enabled) {
               SoundUtil.playSound("module_disable.wav", 65.0F + clientSound.value.getValue(), false);
            } else {
               SoundUtil.playSound("module_enable.wav", 65.0F + clientSound.value.getValue(), false);
            }
         }
      }

      this.enabled = enabled;
   }

   public void setEnabledNotNotify(boolean enabled) {
      if (enabled && this instanceof IEnableable iEnableable) {
         iEnableable.onEnable();
      } else if (!enabled && this instanceof IDisableable iDisableable) {
         iDisableable.onDisable();
      }

      if (enabled) {
         WildClient.INSTANCE.getEventManager().register(this);
         this.animation.setDirection(Direction.FORWARDS);
      } else {
         WildClient.INSTANCE.getEventManager().unregister(this);
         this.animation.setDirection(Direction.BACKWARDS);
      }

      this.enabled = enabled;
   }

   public int getBind() {
      return this.bind;
   }

   public List<Setting> getSettings() {
      return this.settings;
   }

   protected void addSetting(Setting... settings) {
      this.settings.addAll(Arrays.asList(settings));
   }

   public double getHeight() {
      return this.height;
   }

   public void setHeight(double height) {
      this.height = height;
   }

   public Animation getAnimation() {
      return this.animation;
   }

   public boolean isOpened() {
      return this.opened;
   }

   public void setOpened(boolean opened) {
      this.opened = opened;
      if (opened) {
         this.animSettings.setDirection(Direction.FORWARDS);
      } else {
         this.animSettings.setDirection(Direction.BACKWARDS);
      }

   }

   public Animation getAnimSettings() {
      return this.animSettings;
   }

   public void setBinded(boolean binded) {
      this.binded = binded;
   }

   public boolean isBinded() {
      return this.binded;
   }

   public double getDefaultHeight() {
      return this.defaultHeight;
   }

   public String[] getDesc() {
      return this.desc;
   }

   public boolean isDoubleDesc() {
      return this.doubleDesc;
   }
}

