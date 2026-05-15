package dev.client.yanderov.display.screens.clickgui.components.implement.settings;

import dev.client.yanderov.display.screens.clickgui.components.implement.other.CheckComponent;
import dev.client.yanderov.display.screens.clickgui.components.implement.other.SettingComponent;
import dev.client.yanderov.display.screens.clickgui.components.implement.window.AbstractWindow;
import dev.client.yanderov.display.screens.clickgui.components.implement.window.implement.settings.group.GroupWindow;
import dev.client.yanderov.features.module.setting.implement.GroupSetting;
import dev.client.yanderov.utils.display.font.Fonts;
import net.minecraft.class_332;

public class GroupComponent extends AbstractSettingComponent {
   private final CheckComponent checkComponent = new CheckComponent();
   private final SettingComponent settingComponent = new SettingComponent();
   private final GroupSetting setting;

   public GroupComponent(GroupSetting setting) {
      super(setting);
      this.setting = setting;
   }

   public void render(class_332 context, int mouseX, int mouseY, float delta) {
      this.height = 18.0F;
      Fonts.getSize(14, Fonts.Type.DEFAULT).drawString(context.method_51448(), this.setting.getName(), (double)(this.x + 8.0F), (double)(this.y + 7.25F), -2828575);
      this.checkComponent.position(this.x + this.width - 20.0F, this.y + 3.0F).setRunnable(() -> this.setting.setValue(!this.setting.isValue())).setState(this.setting.isValue()).render(context, mouseX, mouseY, delta);
      ((SettingComponent)this.settingComponent.position(this.x + this.width - 31.0F, this.y + 2.5F)).setRunnable(() -> this.spawnWindow(mouseX, mouseY)).render(context, mouseX, mouseY, delta);
   }

   public boolean mouseClicked(double mouseX, double mouseY, int button) {
      this.checkComponent.mouseClicked(mouseX, mouseY, button);
      this.settingComponent.mouseClicked(mouseX, mouseY, button);
      return super.mouseClicked(mouseX, mouseY, button);
   }

   private void spawnWindow(int mouseX, int mouseY) {
      AbstractWindow existingWindow = null;

      for(AbstractWindow window : windowManager.getWindows()) {
         if (window instanceof GroupWindow && ((GroupWindow)window).getSetting() == this.setting) {
            existingWindow = window;
            break;
         }
      }

      if (existingWindow != null) {
         windowManager.delete(existingWindow);
      } else {
         AbstractWindow groupWindow = (new GroupWindow(this.setting)).position((float)(mouseX + 10), (float)mouseY).size(137.0F, 23.0F).draggable(false);
         windowManager.add(groupWindow);
      }

   }
}

