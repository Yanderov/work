package fun.Yanderov.display.screens.clickgui.components.implement.settings;

import fun.Yanderov.features.module.setting.implement.BindSetting;
import fun.Yanderov.utils.client.chat.StringHelper;
import fun.Yanderov.utils.display.font.Fonts;
import fun.Yanderov.utils.display.shape.ShapeProperties;
import fun.Yanderov.utils.math.calc.Calculate;
import java.awt.Color;
import net.minecraft.class_332;
import net.minecraft.class_4587;
import net.minecraft.class_9848;

public class IconBindComponent extends AbstractSettingComponent {
   private final BindSetting setting;
   private boolean binding;

   public IconBindComponent(BindSetting setting) {
      super(setting);
      this.setting = setting;
   }

   public void render(class_332 context, int mouseX, int mouseY, float delta) {
      class_4587 matrix = context.method_51448();
      String bindName = StringHelper.getBindName(this.setting.getKey());
      String name = this.binding ? "(" + bindName + ") ..." : bindName;
      float stringWidth = Fonts.getSize(12, Fonts.Type.SEMI).getStringWidth(name) - 2.0F;
      this.height = 22.0F;
      rectangle.render(ShapeProperties.create(matrix, (double)(this.x + this.width - stringWidth - 17.0F), (double)(this.y + 6.5F), (double)(stringWidth + 10.0F), (double)13.0F).round(3.0F).outlineColor((new Color(200, 200, 200, 255)).getRGB()).color((new Color(61, 67, 71, 80)).getRGB(), (new Color(71, 77, 81, 80)).getRGB(), (new Color(81, 87, 91, 80)).getRGB(), (new Color(91, 97, 101, 80)).getRGB()).build());
      int bindingColor = class_9848.method_61324(255, 135, 136, 148);
      Fonts.getSize(12, Fonts.Type.SEMI).drawString(matrix, name, (double)(this.x + this.width - 12.0F - stringWidth - 1.0F), (double)(this.y + 12.25F), bindingColor);
      Fonts.getSize(14, Fonts.Type.DEFAULT).drawString(context.method_51448(), this.setting.getName(), (double)(this.x + 8.0F), (double)(this.y + 12.25F), -2828575);
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

