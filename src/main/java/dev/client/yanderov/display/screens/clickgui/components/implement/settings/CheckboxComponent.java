package dev.client.yanderov.display.screens.clickgui.components.implement.settings;

import dev.client.yanderov.Yanderov;
import dev.client.yanderov.display.screens.clickgui.components.implement.other.CheckComponent;
import dev.client.yanderov.features.module.setting.implement.BooleanSetting;
import dev.client.yanderov.utils.display.font.Fonts;
import dev.client.yanderov.utils.display.scissor.ScissorAssist;
import net.minecraft.class_332;

public class CheckboxComponent extends AbstractSettingComponent {
   private final CheckComponent checkComponent = new CheckComponent();
   private final BooleanSetting setting;

   public CheckboxComponent(BooleanSetting setting) {
      super(setting);
      this.setting = setting;
   }

   public void render(class_332 context, int mouseX, int mouseY, float delta) {
      this.height = 18.0F;
      float nameX = this.x + 8.0F;
      float nameY = this.y + 7.25F;
      float toggleLeftX = this.x + this.width - 26.0F;
      float maxNameW = Math.max(0.0F, toggleLeftX - 6.0F - nameX);
      ScissorAssist scissor = YanderovIntegration.getInstance().getScissorManager();
      scissor.push(context.method_51448().method_23760().method_23761(), nameX, this.y, maxNameW, this.height);
      Fonts.getSize(14, Fonts.Type.DEFAULT).drawStringWithScroll(context.method_51448(), this.setting.getName(), (double)nameX, (double)nameY, maxNameW, -2828575);
      scissor.pop();
      this.checkComponent.position(this.x + this.width - 20.0F, this.y + 3.0F).setRunnable(() -> this.setting.setValue(!this.setting.isValue())).setState(this.setting.isValue()).render(context, mouseX, mouseY, delta);
   }

   public boolean mouseClicked(double mouseX, double mouseY, int button) {
      this.checkComponent.mouseClicked(mouseX, mouseY, button);
      return super.mouseClicked(mouseX, mouseY, button);
   }
}

