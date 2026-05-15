package dev.client.yanderov.display.screens.clickgui.components.implement.settings;

import dev.client.yanderov.Yanderov;
import dev.client.yanderov.features.module.setting.implement.BindSetting;
import dev.client.yanderov.utils.client.chat.StringHelper;
import dev.client.yanderov.utils.display.font.Fonts;
import dev.client.yanderov.utils.display.scissor.ScissorAssist;
import dev.client.yanderov.utils.display.shape.ShapeProperties;
import dev.client.yanderov.utils.math.calc.Calculate;
import java.awt.Color;
import net.minecraft.class_332;
import net.minecraft.class_4587;
import net.minecraft.class_9848;

public class BindComponent extends AbstractSettingComponent {
   private final BindSetting setting;
   private boolean binding;

   public BindComponent(BindSetting setting) {
      super(setting);
      this.setting = setting;
   }

   public void render(class_332 context, int mouseX, int mouseY, float delta) {
      class_4587 matrix = context.method_51448();
      String bindName = StringHelper.getBindName(this.setting.getKey());
      String name = this.binding ? "(" + bindName + ") ..." : bindName;
      float stringWidth = Fonts.getSize(12, Fonts.Type.SEMI).getStringWidth(name) - 2.0F;
      this.height = 18.0F;
      rectangle.render(ShapeProperties.create(matrix, (double)(this.x + this.width - stringWidth - 19.0F), (double)(this.y + 6.0F), (double)(stringWidth + 10.0F), (double)12.5F).round(3.0F).softness(1.0F).thickness(2.0F).outlineColor((new Color(55, 52, 55, 255)).getRGB()).color((new Color(25, 22, 25, 0)).getRGB(), (new Color(31, 27, 35, 0)).getRGB(), (new Color(31, 27, 35, 0)).getRGB(), (new Color(25, 22, 25, 0)).getRGB()).build());
      int bindingColor = class_9848.method_61324(255, 135, 136, 148);
      Fonts.getSize(12, Fonts.Type.SEMI).drawString(matrix, name, (double)(this.x + this.width - 14.0F - stringWidth - 1.0F), (double)(this.y + 11.5F), bindingColor);
      float nameX = this.x + 8.0F;
      float nameY = this.y + 10.5F;
      float bindBoxX = this.x + this.width - stringWidth - 19.0F;
      float maxNameW = Math.max(0.0F, bindBoxX - 6.0F - nameX);
      ScissorAssist scissor = YanderovIntegration.getInstance().getScissorManager();
      scissor.push(matrix.method_23760().method_23761(), nameX, this.y, maxNameW, this.height);
      Fonts.getSize(14, Fonts.Type.DEFAULT).drawStringWithScroll(context.method_51448(), this.setting.getName(), (double)nameX, (double)nameY, maxNameW, -2828575);
      scissor.pop();
   }

   public boolean mouseClicked(double mouseX, double mouseY, int button) {
      if (button == 0) {
         if (Calculate.isHovered(mouseX, mouseY, (double)this.x, (double)this.y, (double)this.width, (double)this.height)) {
            this.binding = !this.binding;
         } else {
            this.binding = false;
         }
      }

      if (this.binding && button > 1) {
         this.setting.setKey(button);
         this.binding = false;
      }

      return super.mouseClicked(mouseX, mouseY, button);
   }

   public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
      int key = keyCode == 261 ? -1 : keyCode;
      if (this.binding) {
         this.setting.setKey(key);
         this.binding = false;
      }

      return super.keyPressed(keyCode, scanCode, modifiers);
   }
}

