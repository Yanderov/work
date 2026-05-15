package fun.Yanderov.features.module.setting;

import com.google.common.collect.Lists;
import fun.Yanderov.utils.client.interfaces.Setupable;
import java.util.Arrays;
import java.util.List;

public class SettingRepository implements Setupable {
   private final List settings = Lists.newArrayList();

   public final void setup(Setting... setting) {
      this.settings.addAll(Arrays.asList(setting));
   }

   public Setting get(String name) {
      return (Setting)this.settings.stream().filter((setting) -> setting.getName().equalsIgnoreCase(name)).findFirst().orElse((Object)null);
   }

   public List settings() {
      return this.settings;
   }
}

