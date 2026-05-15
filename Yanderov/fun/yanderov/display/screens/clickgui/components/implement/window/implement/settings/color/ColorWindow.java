package fun.Yanderov.display.screens.clickgui.components.implement.window.implement.settings.color;

import fun.Yanderov.display.screens.clickgui.components.implement.window.AbstractWindow;
import fun.Yanderov.display.screens.clickgui.components.implement.window.implement.settings.color.component.AlphaComponent;
import fun.Yanderov.display.screens.clickgui.components.implement.window.implement.settings.color.component.ColorEditorComponent;
import fun.Yanderov.display.screens.clickgui.components.implement.window.implement.settings.color.component.ColorPresetComponent;
import fun.Yanderov.display.screens.clickgui.components.implement.window.implement.settings.color.component.HueComponent;
import fun.Yanderov.display.screens.clickgui.components.implement.window.implement.settings.color.component.SaturationComponent;
import fun.Yanderov.features.module.setting.implement.ColorSetting;
import fun.Yanderov.utils.display.shape.ShapeProperties;
import fun.Yanderov.utils.math.calc.Calculate;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.minecraft.class_332;

public class ColorWindow extends AbstractWindow {
   private final List components = new ArrayList();
   private final HueComponent hueComponent;
   private final SaturationComponent saturationComponent;
   private final AlphaComponent alphaComponent;
   private final ColorEditorComponent colorEditorComponent;
   private final ColorPresetComponent colorPresetComponent;

   public ColorWindow(ColorSetting setting) {
      this.components.addAll(Arrays.asList(this.hueComponent = new HueComponent(setting), this.saturationComponent = new SaturationComponent(setting), this.alphaComponent = new AlphaComponent(setting), this.colorEditorComponent = new ColorEditorComponent(setting), this.colorPresetComponent = new ColorPresetComponent(setting)));
   }

   public void drawWindow(class_332 context, int mouseX, int mouseY, float delta) {
      blur.render(ShapeProperties.create(context.method_51448(), (double)this.x, (double)(this.y + 10.0F), (double)this.width, (double)(this.height - 10.0F)).round(6.0F).thickness(1.0F).quality(32.0F).color((new Color(18, 19, 20, 200)).getRGB(), (new Color(0, 2, 5, 200)).getRGB(), (new Color(0, 2, 5, 200)).getRGB(), (new Color(18, 19, 20, 200)).getRGB()).build());
      this.alphaComponent.position(this.x, this.y);
      this.hueComponent.position(this.x, this.y);
      this.saturationComponent.position(this.x, this.y);
      this.colorEditorComponent.position(this.x, this.y);
      this.height = ((ColorPresetComponent)this.colorPresetComponent.position(this.x, this.y)).getWindowHeight() - 40.0F;
      this.components.forEach((component) -> component.render(context, mouseX, mouseY, delta));
   }

   public boolean mouseClicked(double mouseX, double mouseY, int button) {
      this.draggable(Calculate.isHovered(mouseX, mouseY, (double)this.x, (double)this.y, (double)this.width, (double)17.0F));
      this.components.forEach((component) -> component.mouseClicked(mouseX, mouseY, button));
      return super.mouseClicked(mouseX, mouseY, button);
   }

   public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
      this.components.forEach((component) -> component.mouseScrolled(mouseX, mouseY, amount));
      return super.mouseScrolled(mouseX, mouseY, amount);
   }

   public boolean mouseReleased(double mouseX, double mouseY, int button) {
      this.components.forEach((component) -> component.mouseReleased(mouseX, mouseY, button));
      return super.mouseReleased(mouseX, mouseY, button);
   }
}

