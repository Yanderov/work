package fun.Yanderov.features.module.setting;

import fun.Yanderov.display.screens.clickgui.components.implement.settings.AbstractSettingComponent;
import fun.Yanderov.display.screens.clickgui.components.implement.settings.BindComponent;
import fun.Yanderov.display.screens.clickgui.components.implement.settings.CheckboxComponent;
import fun.Yanderov.display.screens.clickgui.components.implement.settings.ColorComponent;
import fun.Yanderov.display.screens.clickgui.components.implement.settings.GroupComponent;
import fun.Yanderov.display.screens.clickgui.components.implement.settings.SButtonComponent;
import fun.Yanderov.display.screens.clickgui.components.implement.settings.SliderComponent;
import fun.Yanderov.display.screens.clickgui.components.implement.settings.TextComponent;
import fun.Yanderov.display.screens.clickgui.components.implement.settings.multiselect.MultiSelectComponent;
import fun.Yanderov.display.screens.clickgui.components.implement.settings.select.SelectComponent;
import fun.Yanderov.features.module.setting.implement.BindSetting;
import fun.Yanderov.features.module.setting.implement.BooleanSetting;
import fun.Yanderov.features.module.setting.implement.ButtonSetting;
import fun.Yanderov.features.module.setting.implement.ColorSetting;
import fun.Yanderov.features.module.setting.implement.GroupSetting;
import fun.Yanderov.features.module.setting.implement.MultiSelectSetting;
import fun.Yanderov.features.module.setting.implement.SelectSetting;
import fun.Yanderov.features.module.setting.implement.SliderSettings;
import fun.Yanderov.features.module.setting.implement.TextSetting;
import java.util.List;

public class SettingComponentAdder {
   public void addSettingComponent(List settings, List components) {
      settings.forEach((setting) -> {
         if (setting instanceof BooleanSetting booleanSetting) {
            components.add(new CheckboxComponent(booleanSetting));
         }

         if (setting instanceof BindSetting bindSetting) {
            components.add(new BindComponent(bindSetting));
         }

         if (setting instanceof ColorSetting colorSetting) {
            components.add(new ColorComponent(colorSetting));
         }

         if (setting instanceof TextSetting textSetting) {
            components.add(new TextComponent(textSetting));
         }

         if (setting instanceof SliderSettings valueSetting) {
            components.add(new SliderComponent(valueSetting));
         }

         if (setting instanceof GroupSetting groupSetting) {
            components.add(new GroupComponent(groupSetting));
         }

         if (setting instanceof ButtonSetting buttonSetting) {
            components.add(new SButtonComponent(buttonSetting));
         }

         if (setting instanceof SelectSetting selectSetting) {
            components.add(new SelectComponent(selectSetting));
         }

         if (setting instanceof MultiSelectSetting multiSelectSetting) {
            components.add(new MultiSelectComponent(multiSelectSetting));
         }

      });
   }
}

