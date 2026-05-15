package fun.Yanderov.display.screens.clickgui.components.implement.settings.select;

import fun.Yanderov.common.animation.Animation;
import fun.Yanderov.common.animation.Direction;
import fun.Yanderov.common.animation.implement.Decelerate;
import fun.Yanderov.display.screens.clickgui.components.AbstractComponent;
import fun.Yanderov.features.module.setting.implement.SelectSetting;
import fun.Yanderov.utils.display.color.ColorAssist;
import fun.Yanderov.utils.display.font.Fonts;
import fun.Yanderov.utils.display.shape.ShapeProperties;
import fun.Yanderov.utils.math.calc.Calculate;
import java.awt.Color;
import java.util.List;
import net.minecraft.class_332;
import net.minecraft.class_4587;
import org.joml.Vector4f;

public class SelectedButton extends AbstractComponent {
   private final SelectSetting setting;
   private final String text;
   private float alpha;
   private final Animation alphaAnimation = (new Decelerate()).setMs(300).setValue((double)0.5F);

   public SelectedButton(SelectSetting setting, String text) {
      this.setting = setting;
      this.text = text;
      this.alphaAnimation.setDirection(Direction.BACKWARDS);
   }

   public void render(class_332 context, int mouseX, int mouseY, float delta) {
      class_4587 matrix = context.method_51448();
      this.alphaAnimation.setDirection(this.setting.isSelected(this.text) ? Direction.FORWARDS : Direction.BACKWARDS);
      float opacity = this.alphaAnimation.getOutput().floatValue();
      int adjustedAlpha = (int)Calculate.clamp(opacity * this.alpha * 255.0F, 0.0F, 255.0F);
      if (!this.alphaAnimation.isFinished(Direction.BACKWARDS)) {
         rectangle.render(ShapeProperties.create(context.method_51448(), (double)(this.x + 0.5F), (double)this.y, (double)(this.width - 1.0F), (double)(this.height - 0.5F)).round(getRound(this.setting.getList(), this.text)).color((new Color(58, 58, 60, adjustedAlpha)).getRGB(), (new Color(58, 58, 60, adjustedAlpha)).getRGB(), (new Color(58, 58, 60, 0)).getRGB(), (new Color(58, 58, 60, 0)).getRGB()).build());
      }

      Fonts.getSize(13, Fonts.Type.BOLD).drawString(matrix, this.text, (double)(this.x + 4.0F), (double)(this.y + 5.0F), ColorAssist.multAlpha((new Color(225, 225, 225, 225)).getRGB(), Calculate.clamp(this.alpha, 0.0F, 1.0F)));
   }

   public boolean mouseClicked(double mouseX, double mouseY, int button) {
      if (Calculate.isHovered(mouseX, mouseY, (double)this.x, (double)this.y, (double)this.width, (double)this.height) && button == 0) {
         this.setting.setSelected(this.text);
      }

      return super.mouseClicked(mouseX, mouseY, button);
   }

   public static Vector4f getRound(List list, String text) {
      if (list.size() == 1) {
         return new Vector4f(3.0F);
      } else if (((String)list.get(list.size() - 1)).equals(text)) {
         return new Vector4f(0.0F, 3.0F, 0.0F, 3.0F);
      } else {
         return ((String)list.get(0)).equals(text) ? new Vector4f(3.0F, 0.0F, 3.0F, 0.0F) : new Vector4f(0.0F);
      }
   }

   public SelectedButton setAlpha(float alpha) {
      this.alpha = alpha;
      return this;
   }
}

