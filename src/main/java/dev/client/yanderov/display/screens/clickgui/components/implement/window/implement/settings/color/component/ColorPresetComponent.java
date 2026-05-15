package dev.client.yanderov.display.screens.clickgui.components.implement.window.implement.settings.color.component;

import dev.client.yanderov.display.screens.clickgui.components.AbstractComponent;
import dev.client.yanderov.display.screens.clickgui.components.implement.window.implement.settings.color.ColorPresetButton;
import dev.client.yanderov.features.module.setting.implement.ColorSetting;
import dev.client.yanderov.utils.display.font.Fonts;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_332;

public class ColorPresetComponent extends AbstractComponent {
   private final List colorPresetButtonList = new ArrayList();
   private final ColorSetting setting;
   private float windowHeight;
   private float windowWidth;

   public ColorPresetComponent(ColorSetting setting) {
      this.setting = setting;

      for(int preset : setting.getPresets()) {
         this.colorPresetButtonList.add(new ColorPresetButton(setting, preset));
      }

   }

   public void render(class_332 context, int mouseX, int mouseY, float delta) {
      if (!this.colorPresetButtonList.isEmpty()) {
         Fonts.getSize(13, Fonts.Type.DEFAULT).drawString(context.method_51448(), "Ð“Ð¾Ñ‚Ð¾Ð²Ñ‹Ðµ Ñ†Ð²ÐµÑ‚Ð°", (double)(this.x + 6.0F), (double)(this.y + 95.0F), -1);
      }

      int xOffset = 0;
      int yOffset = 0;
      int colorIndex = 0;
      int size = 13;

      for(ColorPresetButton button : this.colorPresetButtonList) {
         button.x = this.x + 6.0F + (float)xOffset;
         button.y = this.y + 103.0F + (float)yOffset;
         button.render(context, mouseX, mouseY, delta);
         xOffset += size;
         ++colorIndex;
         if (colorIndex >= 11) {
            colorIndex = 0;
            xOffset = 0;
            yOffset += size - 1;
         }
      }

      this.windowHeight = this.colorPresetButtonList.isEmpty() ? 132.0F : (float)(166 + yOffset) - (float)yOffset / 2.0F;
   }

   public boolean mouseClicked(double mouseX, double mouseY, int button) {
      this.colorPresetButtonList.forEach((colorPresetButton) -> colorPresetButton.mouseClicked(mouseX, mouseY, button));
      return super.mouseClicked(mouseX, mouseY, button);
   }

   public List getColorPresetButtonList() {
      return this.colorPresetButtonList;
   }

   public ColorSetting getSetting() {
      return this.setting;
   }

   public float getWindowHeight() {
      return this.windowHeight;
   }

   public float getWindowWidth() {
      return this.windowWidth;
   }
}

