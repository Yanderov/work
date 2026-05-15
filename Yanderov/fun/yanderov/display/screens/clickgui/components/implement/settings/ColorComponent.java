package fun.Yanderov.display.screens.clickgui.components.implement.settings;

import fun.Yanderov.Yanderov;
import fun.Yanderov.display.screens.clickgui.components.implement.window.implement.settings.color.component.AlphaComponent;
import fun.Yanderov.display.screens.clickgui.components.implement.window.implement.settings.color.component.ColorEditorComponent;
import fun.Yanderov.display.screens.clickgui.components.implement.window.implement.settings.color.component.ColorPresetComponent;
import fun.Yanderov.display.screens.clickgui.components.implement.window.implement.settings.color.component.HueComponent;
import fun.Yanderov.display.screens.clickgui.components.implement.window.implement.settings.color.component.SaturationComponent;
import fun.Yanderov.features.module.setting.implement.ColorSetting;
import fun.Yanderov.utils.display.color.ColorAssist;
import fun.Yanderov.utils.display.font.Fonts;
import fun.Yanderov.utils.display.scissor.ScissorAssist;
import fun.Yanderov.utils.display.shape.ShapeProperties;
import fun.Yanderov.utils.math.calc.Calculate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.minecraft.class_332;
import net.minecraft.class_4587;
import org.joml.Matrix4f;

public class ColorComponent extends AbstractSettingComponent {
   private final ColorSetting setting;
   private static final float ROW_HEIGHT = 22.0F;
   private static final float PICKER_TOP_OVERLAP = 16.0F;
   private static final float PICKER_BOTTOM_TRIM = 10.0F;
   private static final float PICKER_SIDE_PADDING = 2.2F;
   private boolean expanded;
   private final List pickerComponents = new ArrayList();
   private final HueComponent hueComponent;
   private final SaturationComponent saturationComponent;
   private final AlphaComponent alphaComponent;
   private final ColorEditorComponent colorEditorComponent;
   private final ColorPresetComponent colorPresetComponent;
   private float pickerX;
   private float pickerY;
   private float pickerW;
   private float pickerH;

   public ColorComponent(ColorSetting setting) {
      super(setting);
      this.setting = setting;
      this.pickerComponents.addAll(Arrays.asList(this.hueComponent = new HueComponent(setting), this.saturationComponent = new SaturationComponent(setting), this.alphaComponent = new AlphaComponent(setting), this.colorEditorComponent = new ColorEditorComponent(setting), this.colorPresetComponent = new ColorPresetComponent(setting)));
   }

   public void render(class_332 context, int mouseX, int mouseY, float delta) {
      class_4587 matrix = context.method_51448();
      Matrix4f positionMatrix = matrix.method_23760().method_23761();
      this.height = 22.0F;
      float nameX = this.x + 8.0F;
      float nameY = this.y + 12.25F;
      float circleLeftX = this.x + this.width - 16.0F;
      float maxNameW = Math.max(0.0F, circleLeftX - 6.0F - nameX);
      ScissorAssist scissor = Yanderov.getInstance().getScissorManager();
      scissor.push(matrix.method_23760().method_23761(), nameX, this.y, maxNameW, this.height);
      Fonts.getSize(14, Fonts.Type.DEFAULT).drawStringWithScroll(context.method_51448(), this.setting.getName(), (double)nameX, (double)nameY, maxNameW, -2828575);
      scissor.pop();
      rectangle.render(ShapeProperties.create(matrix, (double)(this.x + this.width - 16.0F), (double)(this.y + 10.5F), (double)7.0F, (double)7.0F).round(3.5F).color(this.setting.getColor()).build());
      rectangle.render(ShapeProperties.create(matrix, (double)(this.x + this.width - 16.0F), (double)(this.y + 10.5F), (double)7.0F, (double)7.0F).round(3.5F).thickness(2.0F).softness(1.0F).outlineColor(ColorAssist.getText()).color(16777215).build());
      if (this.expanded) {
         this.pickerX = this.x + 2.2F;
         this.pickerW = Math.max(0.0F, this.width - 4.4F);
         this.pickerY = this.y + 22.0F - 16.0F;
         this.alphaComponent.position(this.pickerX, this.pickerY).size(this.pickerW, 0.0F);
         this.hueComponent.position(this.pickerX, this.pickerY).size(this.pickerW, 0.0F);
         this.saturationComponent.position(this.pickerX, this.pickerY).size(this.pickerW, 0.0F);
         this.colorEditorComponent.position(this.pickerX, this.pickerY).size(this.pickerW, 0.0F);
         this.pickerH = Math.max(0.0F, ((ColorPresetComponent)this.colorPresetComponent.position(this.pickerX, this.pickerY).size(this.pickerW, 0.0F)).getWindowHeight() - 40.0F - 10.0F);
         ScissorAssist pickerScissor = Yanderov.getInstance().getScissorManager();
         pickerScissor.push(positionMatrix, this.pickerX, this.pickerY, this.pickerW, this.pickerH);
         this.pickerComponents.forEach((component) -> component.render(context, mouseX, mouseY, delta));
         pickerScissor.pop();
         this.height = 22.0F + this.pickerH - 16.0F;
      }

   }

   public boolean mouseClicked(double mouseX, double mouseY, int button) {
      boolean overPreview = Calculate.isHovered(mouseX, mouseY, (double)(this.x + this.width - 13.0F), (double)(this.y + 10.0F), (double)7.0F, (double)7.0F);
      if (button == 0 && overPreview) {
         this.expanded = !this.expanded;
         return true;
      } else if (this.expanded) {
         boolean overPicker = Calculate.isHovered(mouseX, mouseY, (double)this.pickerX, (double)this.pickerY, (double)this.pickerW, (double)this.pickerH);
         if (!overPicker) {
            this.expanded = false;
            return false;
         } else {
            this.pickerComponents.forEach((component) -> component.mouseClicked(mouseX, mouseY, button));
            return true;
         }
      } else {
         return super.mouseClicked(mouseX, mouseY, button);
      }
   }

   public boolean mouseReleased(double mouseX, double mouseY, int button) {
      if (this.expanded) {
         this.pickerComponents.forEach((component) -> component.mouseReleased(mouseX, mouseY, button));
      }

      return super.mouseReleased(mouseX, mouseY, button);
   }

   public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
      if (this.expanded) {
         boolean overPicker = Calculate.isHovered(mouseX, mouseY, (double)this.pickerX, (double)this.pickerY, (double)this.pickerW, (double)this.pickerH);
         if (overPicker) {
            this.pickerComponents.forEach((component) -> component.mouseScrolled(mouseX, mouseY, amount));
            return true;
         }
      }

      return super.mouseScrolled(mouseX, mouseY, amount);
   }
}

