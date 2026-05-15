package fun.Yanderov.display.screens.clickgui.components.implement.settings;

import fun.Yanderov.Yanderov;
import fun.Yanderov.display.screens.clickgui.components.implement.other.ButtonComponent;
import fun.Yanderov.features.module.setting.implement.ButtonSetting;
import fun.Yanderov.utils.display.font.Fonts;
import fun.Yanderov.utils.display.scissor.ScissorAssist;
import net.minecraft.class_332;

public class SButtonComponent extends AbstractSettingComponent {
   private final ButtonComponent buttonComponent = new ButtonComponent();
   private final ButtonSetting setting;

   public SButtonComponent(ButtonSetting setting) {
      super(setting);
      this.setting = setting;
   }

   public void render(class_332 context, int mouseX, int mouseY, float delta) {
      this.height = 22.0F;
      ((ButtonComponent)this.buttonComponent.setText("Click on me").setRunnable(this.setting.getRunnable()).position(this.x + this.width - 9.0F - this.buttonComponent.width, this.y + 6.5F)).render(context, mouseX, mouseY, delta);
      float nameX = this.x + 9.0F;
      float nameY = this.y + 12.25F;
      float maxNameW = Math.max(0.0F, this.x + this.width - 12.0F - this.buttonComponent.width - nameX);
      ScissorAssist scissor = Yanderov.getInstance().getScissorManager();
      scissor.push(context.method_51448().method_23760().method_23761(), nameX, this.y, maxNameW, this.height);
      Fonts.getSize(14, Fonts.Type.BOLD).drawStringWithScroll(context.method_51448(), this.setting.getName(), (double)nameX, (double)nameY, maxNameW, -2828575);
      scissor.pop();
   }

   public boolean mouseClicked(double mouseX, double mouseY, int button) {
      this.buttonComponent.mouseClicked(mouseX, mouseY, button);
      return super.mouseClicked(mouseX, mouseY, button);
   }
}

