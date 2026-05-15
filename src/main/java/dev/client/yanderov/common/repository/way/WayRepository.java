package dev.client.yanderov.common.repository.way;

import dev.client.yanderov.events.render.DrawEvent;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import dev.client.yanderov.utils.client.managers.event.EventManager;
import dev.client.yanderov.utils.display.color.ColorAssist;
import dev.client.yanderov.utils.display.font.FontRenderer;
import dev.client.yanderov.utils.display.font.Fonts;
import dev.client.yanderov.utils.display.interfaces.QuickImports;
import dev.client.yanderov.utils.display.interfaces.QuickLogger;
import dev.client.yanderov.utils.display.shape.ShapeProperties;
import dev.client.yanderov.utils.math.calc.Calculate;
import dev.client.yanderov.utils.math.projection.Projection;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_2338;
import net.minecraft.class_243;
import net.minecraft.class_4587;

public class WayRepository implements QuickImports, QuickLogger {
   public List wayList = new ArrayList();

   public WayRepository(EventManager eventManager) {
      eventManager.register(this);
   }

   public boolean isEmpty() {
      return this.wayList.isEmpty();
   }

   public void addWay(String name, class_2338 pos, String server) {
      this.wayList.add(new Way(name, pos, server));
   }

   public boolean hasWay(String text) {
      return this.wayList.stream().anyMatch((s) -> s.name().equalsIgnoreCase(text));
   }

   public void deleteWay(String name) {
      this.wayList.removeIf((macro) -> macro.name().equalsIgnoreCase(name));
   }

   public void clearList() {
      if (!this.isEmpty()) {
         this.wayList.clear();
      }

   }

   @EventHandler
   public void onDraw(DrawEvent e) {
      if (!this.isEmpty() && mc.method_1562() != null && mc.method_1562().method_45734() != null) {
         class_4587 matrix = e.getDrawContext().method_51448();
         this.wayList.forEach((way) -> {
            class_243 wayVec = way.pos().method_46558();
            class_243 vec = Projection.worldSpaceToScreenSpace(wayVec);
            if (Projection.canSee(wayVec) && way.server().equalsIgnoreCase(mc.method_1562().method_45734().field_3761)) {
               String var10000 = way.name();
               String text = var10000 + " - " + Calculate.round(mc.method_1561().field_4686.method_19326().method_1022(wayVec), (double)0.1F) + "m";
               FontRenderer font = Fonts.getSize(14, Fonts.Type.SEMI);
               float height = font.getStringHeight(text) / 4.0F;
               float width = font.getStringWidth(text);
               float padding = 3.0F;
               double x = vec.method_10216() - (double)(width / 2.0F);
               double y = vec.method_10214() - (double)(height / 2.0F);
               rectangle.render(ShapeProperties.create(matrix, x - (double)padding, y - (double)padding, (double)(width + padding * 2.0F), (double)(height + padding * 2.0F)).round(2.0F).thickness(2.0F).outlineColor((new Color(55, 52, 55, 155)).getRGB()).color((new Color(19, 19, 21, 225)).getRGB(), (new Color(19, 19, 21, 225)).getRGB(), (new Color(19, 19, 21, 225)).getRGB(), (new Color(19, 19, 21, 225)).getRGB()).build());
               font.drawString(matrix, text, x, y + (double)0.5F, ColorAssist.getText());
            }

         });
      }
   }
}

