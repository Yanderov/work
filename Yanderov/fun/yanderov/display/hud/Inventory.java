package fun.Yanderov.display.hud;

import fun.Yanderov.utils.client.managers.api.draggable.AbstractDraggable;
import fun.Yanderov.utils.display.color.ColorAssist;
import fun.Yanderov.utils.display.font.FontRenderer;
import fun.Yanderov.utils.display.font.Fonts;
import fun.Yanderov.utils.display.geometry.Render2D;
import fun.Yanderov.utils.display.shape.ShapeProperties;
import fun.Yanderov.utils.interactions.interact.PlayerInteractionHelper;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import net.minecraft.class_1799;
import net.minecraft.class_332;
import net.minecraft.class_4587;

public class Inventory extends AbstractDraggable {
   List stacks = new ArrayList();

   public Inventory() {
      super("Inventory", 385, 40, 123, 60, true);
   }

   public boolean visible() {
      return !this.stacks.stream().filter((stack) -> !stack.method_7960()).toList().isEmpty() || PlayerInteractionHelper.isChat(mc.field_1755);
   }

   public void tick() {
      this.stacks = IntStream.range(9, 36).mapToObj((i) -> mc.field_1724.field_7514.method_5438(i)).toList();
   }

   public void drawDraggable(class_332 context) {
      class_4587 matrix = context.method_51448();
      FontRenderer font = Fonts.getSize(14, Fonts.Type.DEFAULT);
      FontRenderer items = Fonts.getSize(12, Fonts.Type.DEFAULT);
      FontRenderer icon = Fonts.getSize(20, Fonts.Type.ICONS);
      long itemCount = (long)this.stacks.stream().filter((stackx) -> !stackx.method_7960()).mapToInt(class_1799::method_7947).sum();
      String itemCountText = String.valueOf(itemCount);
      float textWidth = items.getStringWidth(itemCountText);
      float boxWidth = textWidth + 6.0F;
      blur.render(ShapeProperties.create(matrix, (double)this.getX(), (double)this.getY(), (double)this.getWidth(), (double)15.5F).round(1.5F, 0.0F, 1.5F, 0.0F).quality(12.0F).color((new Color(0, 0, 0, 150)).getRGB()).build());
      rectangle.render(ShapeProperties.create(matrix, (double)this.getX(), (double)this.getY(), (double)this.getWidth(), (double)15.5F).round(1.5F, 0.0F, 1.5F, 0.0F).thickness(0.1F).outlineColor((new Color(33, 33, 33, 255)).getRGB()).color((new Color(18, 19, 20, 75)).getRGB(), (new Color(0, 2, 5, 75)).getRGB(), (new Color(0, 2, 5, 75)).getRGB(), (new Color(18, 19, 20, 75)).getRGB()).build());
      items.drawString(matrix, "Items:", (double)((float)(this.getX() + this.getWidth()) - boxWidth - 21.0F), (double)(this.getY() + 7), ColorAssist.getText());
      items.drawString(matrix, itemCountText, (double)((float)(this.getX() + this.getWidth()) - boxWidth - 2.0F), (double)(this.getY() + 7), (new Color(225, 225, 255, 255)).getRGB());
      rectangle.render(ShapeProperties.create(matrix, (double)(this.getX() + 18), (double)(this.getY() + 5), (double)0.5F, (double)6.0F).color(ColorAssist.getText(0.5F)).round(0.0F).build());
      blur.render(ShapeProperties.create(matrix, (double)this.getX(), (double)((float)this.getY() + 16.4F), (double)this.getWidth(), (double)(this.getHeight() - 15)).round(0.0F, 1.5F, 0.0F, 1.5F).quality(12.0F).color((new Color(0, 0, 0, 150)).getRGB()).build());
      rectangle.render(ShapeProperties.create(matrix, (double)this.getX(), (double)((float)this.getY() + 16.4F), (double)this.getWidth(), (double)(this.getHeight() - 15)).round(0.0F, 1.5F, 0.0F, 1.5F).thickness(0.1F).outlineColor((new Color(33, 33, 33, 255)).getRGB()).color((new Color(18, 19, 20, 75)).getRGB(), (new Color(0, 2, 5, 75)).getRGB(), (new Color(0, 2, 5, 75)).getRGB(), (new Color(18, 19, 20, 75)).getRGB()).build());
      icon.drawString(matrix, "F", (double)((float)this.getX() + 4.5F), (double)(this.getY() + 6), (new Color(225, 225, 255, 255)).getRGB());
      font.drawString(matrix, this.getName(), (double)(this.getX() + 22), (double)((float)this.getY() + 6.5F), ColorAssist.getText());
      int offsetY = 20;
      int offsetX = 4;
      int itemsPerRow = 9;
      int itemIndex = 0;

      for(class_1799 stack : this.stacks) {
         float itemX = (float)(this.getX() + offsetX + 1);
         float itemY = (float)(this.getY() + offsetY) + 1.0F;
         if (itemIndex % itemsPerRow != itemsPerRow - 1) {
            rectangle.render(ShapeProperties.create(matrix, (double)(itemX + 10.0F), (double)itemY, (double)0.5F, (double)9.0F).color(ColorAssist.getText(0.1F)).round(0.0F).build());
         }

         if (itemIndex < this.stacks.size() - itemsPerRow) {
            rectangle.render(ShapeProperties.create(matrix, (double)(itemX - 0.5F), (double)(itemY + 10.0F), (double)9.0F, (double)0.5F).color(ColorAssist.getText(0.1F)).round(0.0F).build());
         }

         Render2D.defaultDrawStack(context, stack, itemX - 1.0F, itemY - 1.0F, false, true, 0.5F);
         offsetX += 13;
         ++itemIndex;
         if (itemIndex % itemsPerRow == 0) {
            offsetY += 13;
            offsetX = 4;
         }
      }

   }
}

