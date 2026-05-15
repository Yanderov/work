package fun.Yanderov.features.impl.render;

import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.features.module.setting.Setting;
import fun.Yanderov.features.module.setting.implement.BooleanSetting;
import fun.Yanderov.features.module.setting.implement.ColorSetting;
import fun.Yanderov.features.module.setting.implement.MultiSelectSetting;
import fun.Yanderov.features.module.setting.implement.SliderSettings;
import fun.Yanderov.utils.client.Instance;
import java.awt.Color;

public class Hud extends Module {
   public final MultiSelectSetting interfaceSettings = (new MultiSelectSetting("Ð­Ð»ÐµÐ¼ÐµÐ½Ñ‚Ñ‹", "ÐÐ°ÑÑ‚Ñ€Ð¾Ð¹ÐºÐ° ÑÐ»ÐµÐ¼ÐµÐ½Ñ‚Ð¾Ð² Ð¸Ð½Ñ‚ÐµÑ€Ñ„ÐµÐ¹ÑÐ°")).value("Watermark", "Hot Keys", "Potions", "Staff List", "Target Hud", "Binds", "Cool Downs", "Inventory", "Player Info", "Notifications").selected("Watermark", "Hot Keys", "Potions", "Staff List", "Target Hud", "Binds", "Cool Downs", "Inventory", "Player Info", "Notifications");
   public final MultiSelectSetting notificationSettings = (new MultiSelectSetting("Ð£Ð²ÐµÐ´Ð¾Ð¼Ð»ÐµÐ½Ð¸Ñ", "Ð’Ñ‹Ð±ÐµÑ€Ð¸Ñ‚Ðµ, ÐºÐ¾Ð³Ð´Ð° Ð±ÑƒÐ´ÑƒÑ‚ Ð¿Ð¾ÑÐ²Ð»ÑÑ‚ÑŒÑÑ ÑƒÐ²ÐµÐ´Ð¾Ð¼Ð»ÐµÐ½Ð¸Ñ")).value("Module Switch", "Staff Join", "Staff Leave", "Item Pick Up", "Auto Armor", "Break Shield").selected("Module Switch", "Item Pick Up", "Auto Armor", "Break Shield").visible(() -> this.interfaceSettings.isSelected("Notifications"));
   public final ColorSetting colorSetting = (new ColorSetting("Ð˜Ð·Ð¼ÐµÐ½ÑÐµÑ‚ Ñ†Ð²ÐµÑ‚ Ð½ÐµÐºÐ¾Ñ‚Ð¾Ñ€Ñ‹Ñ… Ð¼Ð¾Ð´ÑƒÐ»ÐµÐ¹", "Ð’Ñ‹Ð±ÐµÑ€Ð¸Ñ‚Ðµ Ñ†Ð²ÐµÑ‚ ÐºÐ»Ð¸ÐµÐ½Ñ‚Ð°")).setColor((new Color(255, 255, 255, 255)).getRGB()).presets(-1, -16777216, -8355712, -4144960);
   public final BooleanSetting scoreboardHp = (new BooleanSetting("Scoreboard HP", "Ð˜ÑÐ¿Ð¾Ð»ÑŒÐ·Ð¾Ð²Ð°Ñ‚ÑŒ HP ÑÐ¾ ÑÐºÐ¾Ñ€Ð±Ð¾Ñ€Ð´Ð° Ð² Target Hud")).setValue(false).visible(() -> this.interfaceSettings.isSelected("Target Hud"));
   public final SliderSettings soundVolumeSetting = (new SliderSettings("Sound Volume", "Volume for module switch sounds")).range(0.0F, 1.0F).setValue(1.0F).visible(() -> this.interfaceSettings.isSelected("Notifications"));

   public static Hud getInstance() {
      return (Hud)Instance.get(Hud.class);
   }

   public float getModuleVolume() {
      return this.soundVolumeSetting.getValue();
   }

   public Hud() {
      super("Hud", ModuleCategory.RENDER);
      this.setup(new Setting[]{this.colorSetting, this.interfaceSettings, this.notificationSettings, this.scoreboardHp, this.soundVolumeSetting});
   }
}

