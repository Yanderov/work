package fun.Yanderov.features.module.setting.implement;

import fun.Yanderov.features.module.setting.Setting;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class GroupSetting extends Setting {
   private boolean value;
   private List subSettings = new ArrayList();

   public GroupSetting(String name, String description) {
      super(name, description);
   }

   public GroupSetting settings(Setting... setting) {
      this.subSettings.addAll(Arrays.asList(setting));
      return this;
   }

   public GroupSetting visible(Supplier visible) {
      this.setVisible(visible);
      return this;
   }

   public Setting getSubSetting(String name) {
      return (Setting)this.subSettings.stream().filter((setting) -> setting.getName().equalsIgnoreCase(name)).findFirst().orElse((Object)null);
   }

   public boolean isValue() {
      return this.value;
   }

   public List getSubSettings() {
      return this.subSettings;
   }

   public GroupSetting setValue(boolean value) {
      this.value = value;
      return this;
   }

   public GroupSetting setSubSettings(List subSettings) {
      this.subSettings = subSettings;
      return this;
   }
}

