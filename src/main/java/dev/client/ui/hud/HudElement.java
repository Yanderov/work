package dev.client.ui.hud;

import dev.client.WildClient;
import dev.client.managers.FontManager;
import dev.client.modules.settings.impl.BooleanSetting;
import dev.client.modules.settings.impl.FloatSetting;
import dev.client.ui.draggable.Draggable;
import dev.client.util.render.builders.Builder;
import dev.client.util.render.builders.states.QuadColorState;
import dev.client.util.render.builders.states.QuadRadiusState;
import dev.client.util.render.builders.states.SizeState;
import dev.client.util.render.msdf.MsdfFont;
import dev.client.util.render.renderers.impl.BuiltBlur;
import dev.client.util.render.renderers.impl.BuiltRectangle;
import dev.client.util.render.renderers.impl.BuiltText;
import java.awt.Color;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import org.joml.Matrix4f;

@Environment(EnvType.CLIENT)
public abstract class HudElement {
   protected final Draggable draggable;
   protected final BooleanSetting blur;
   protected final FloatSetting streght;
   protected boolean visiblePane;
   private final String name;

   public HudElement(Draggable draggable, String name) {
      this.draggable = draggable;
      WildClient.INSTANCE.getDraggableManager().addDraggable(draggable);
      this.blur = new BooleanSetting().name("Blur").value(false);
      this.streght = new FloatSetting().name("Streght").incriment(0.5F).minValue(1.0F).maxValue(25.0F).value(12.0F);
      this.visiblePane = false;
      this.name = name;
   }

   public HudElement() {
      this.draggable = null;
      this.blur = new BooleanSetting().name("Blur").value(false);
      this.streght = new FloatSetting().name("Streght").incriment(0.5F).minValue(1.0F).maxValue(25.0F).value(12.0F);
      this.visiblePane = false;
      this.name = "s";
   }

   public abstract void render(DrawContext context);

   public BooleanSetting getBlur() {
      return this.blur;
   }

   public FloatSetting getStreght() {
      return this.streght;
   }

   public void setVisiblePane(boolean visiblePane) {
      this.visiblePane = visiblePane;
   }

   public void renderPanel(DrawContext drawContext) {
      Matrix4f matrix = drawContext.getMatrices().peek().getPositionMatrix();
      double x = (double)(this.draggable.x + this.draggable.getWeight());
      double y = (double)(this.draggable.y + this.draggable.getHeight());
      BuiltBlur blur = Builder.blur().size(new SizeState(147.0F, 50.0F)).radius(new QuadRadiusState(8.0F)).blurRadius(12.0F).smoothness(1.0F).color(new QuadColorState(Color.white)).build();
      blur.render(matrix, x, y);
      BuiltRectangle rectangle = Builder.rectangle().size(new SizeState(147.0F, 50.0F)).color(new QuadColorState(new Color(0, 0, 0, 122))).radius(new QuadRadiusState(8.0F)).smoothness(1.15F).build();
      rectangle.render(matrix, x, y);
      rectangle = Builder.rectangle().size(new SizeState(139.0F, 28.0F)).color(new QuadColorState(new Color(0, 0, 0, 184))).radius(new QuadRadiusState(7.0F, 3.0F, 3.0F, 7.0F)).smoothness(1.15F).build();
      rectangle.render(matrix, x + 4.0D, y + 4.0D);
      rectangle = Builder.rectangle().size(new SizeState(18.0F, 18.0F)).color(new QuadColorState(WildClient.INSTANCE.getThemeManager().getTheme().color())).radius(new QuadRadiusState(5.0F)).smoothness(1.15F).build();
      rectangle.render(matrix, x + 9.0D, y + 9.0D);
      BuiltText text = (BuiltText)Builder.text().font(FontManager.CATEGORY.get()).text("E").color(Color.WHITE).size(10.0F).thickness(0.05F).build();
      text.render(matrix, x + 12.0D, y + 12.0D);
      BuiltText icon = (BuiltText)Builder.text().font(FontManager.WILD.get()).text("A").color(Color.WHITE).size(7.0F).thickness(0.05F).build();
      icon.render(matrix, x + 34.0D, y + 14.0D);
      text = (BuiltText)Builder.text().font(FontManager.SUISSEINTMEDIUM.get()).text("yanderovclient.org").color(Color.WHITE).size(7.4F).thickness(0.05F).build();
      text.render(matrix, x + 45.0D, y + 13.5D);
      text = (BuiltText)Builder.text().font(FontManager.WILD.get()).text("T").color(new Color(255, 255, 255, 61)).size(5.4F).thickness(0.05F).build();
      text.render(matrix, x + 98.5D, y + 15.0D);
      text = (BuiltText)Builder.text().font(FontManager.WILD.get()).text("C").color(WildClient.INSTANCE.getThemeManager().getTheme().color()).size(7.0F).thickness(0.05F).build();
      text.render(matrix, x + 106.0D, y + 13.5D);
      text = (BuiltText)Builder.text().font(FontManager.SUISSEINTMEDIUM.get()).text("Staff").color(Color.WHITE).size(8.0F).thickness(0.05F).build();
      text.render(matrix, x + 117.0D, y + 13.5D);
   }
}
