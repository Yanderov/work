package dev.client.yanderov.display.hud;

import dev.client.yanderov.Yanderov;
import dev.client.yanderov.common.animation.Direction;
import dev.client.yanderov.features.impl.movement.ElytraTarget;
import dev.client.yanderov.features.impl.render.Hud;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.utils.client.chat.StringHelper;
import dev.client.yanderov.utils.client.managers.api.draggable.AbstractDraggable;
import dev.client.yanderov.utils.display.color.ColorAssist;
import dev.client.yanderov.utils.display.font.FontRenderer;
import dev.client.yanderov.utils.display.font.Fonts;
import dev.client.yanderov.utils.display.shape.ShapeProperties;
import dev.client.yanderov.utils.interactions.interact.PlayerInteractionHelper;
import dev.client.yanderov.utils.math.calc.Calculate;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.class_332;
import net.minecraft.class_4587;

public class HotKeys extends AbstractDraggable {
   private List keysList = new ArrayList();
   private long lastKeyChange = 0L;
   private String currentRandomKey = "NONE";

   public HotKeys() {
      super("Hot Keys", 300, 40, 80, 23, true);
   }

   public boolean visible() {
      return Hud.getInstance().interfaceSettings.isSelected("Hot Keys") && (!this.keysList.isEmpty() || PlayerInteractionHelper.isChat(mc.field_1755));
   }

   public void tick() {
      this.keysList = YanderovIntegration.getInstance().getModuleProvider().getModules().stream().filter((module) -> module.getAnimation().getOutput().floatValue() != 0.0F && module.getKey() != -1).toList();
      if (this.keysList.isEmpty() && PlayerInteractionHelper.isChat(mc.field_1755)) {
         long currentTime = System.currentTimeMillis();
         if (currentTime - this.lastKeyChange >= 1000L) {
            List<String> availableKeys = List.of("A", "B", "C", "D", "E");
            this.currentRandomKey = (String)availableKeys.get((new Random()).nextInt(availableKeys.size()));
            this.lastKeyChange = currentTime;
         }
      }

   }

   public void drawDraggable(class_332 context) {
      class_4587 matrix = context.method_51448();
      FontRenderer font = Fonts.getSize(13, Fonts.Type.DEFAULT);
      FontRenderer fontModule = Fonts.getSize(13, Fonts.Type.DEFAULT);
      FontRenderer icon = Fonts.getSize(23, Fonts.Type.ICONS);
      FontRenderer items = Fonts.getSize(12, Fonts.Type.DEFAULT);
      FontRenderer categoryIcon = Fonts.getSize(16, Fonts.Type.ICONSCATEGORY);
      long activeModules = this.keysList.stream().filter((m) -> !m.getAnimation().isFinished(Direction.BACKWARDS)).count();
      String moduleCountText = String.valueOf(activeModules);
      float textWidth = items.getStringWidth(moduleCountText);
      float boxWidth = textWidth + 6.0F;
      blur.render(ShapeProperties.create(matrix, (double)this.getX(), (double)this.getY(), (double)this.getWidth(), (double)15.5F).round(1.5F, 0.0F, 1.5F, 0.0F).quality(12.0F).color((new Color(0, 0, 0, 150)).getRGB()).build());
      rectangle.render(ShapeProperties.create(matrix, (double)this.getX(), (double)this.getY(), (double)this.getWidth(), (double)15.5F).round(1.5F, 0.0F, 1.5F, 0.0F).thickness(0.1F).outlineColor((new Color(33, 33, 33, 255)).getRGB()).color((new Color(18, 19, 20, 75)).getRGB(), (new Color(0, 2, 5, 75)).getRGB(), (new Color(0, 2, 5, 75)).getRGB(), (new Color(18, 19, 20, 75)).getRGB()).build());
      items.drawString(matrix, "Active:", (double)((float)(this.getX() + this.getWidth()) - boxWidth - 21.0F), (double)(this.getY() + 7), ColorAssist.getText());
      items.drawString(matrix, moduleCountText, (double)((float)(this.getX() + this.getWidth()) - boxWidth - 2.0F), (double)(this.getY() + 7), (new Color(225, 225, 255, 255)).getRGB());
      rectangle.render(ShapeProperties.create(matrix, (double)(this.getX() + 18), (double)(this.getY() + 5), (double)0.5F, (double)6.0F).color(ColorAssist.getText(0.5F)).round(0.0F).build());
      blur.render(ShapeProperties.create(matrix, (double)this.getX(), (double)((float)this.getY() + 16.5F), (double)this.getWidth(), (double)(this.getHeight() - 17)).round(0.0F, 1.5F, 0.0F, 1.5F).quality(12.0F).color((new Color(0, 0, 0, 150)).getRGB()).build());
      rectangle.render(ShapeProperties.create(matrix, (double)this.getX(), (double)((float)this.getY() + 16.5F), (double)this.getWidth(), (double)(this.getHeight() - 17)).round(0.0F, 1.5F, 0.0F, 1.5F).thickness(0.1F).outlineColor((new Color(33, 33, 33, 255)).getRGB()).color((new Color(18, 19, 20, 75)).getRGB(), (new Color(0, 2, 5, 75)).getRGB(), (new Color(0, 2, 5, 75)).getRGB(), (new Color(18, 19, 20, 75)).getRGB()).build());
      icon.drawString(matrix, "B", (double)((float)this.getX() + 4.0F), (double)((float)this.getY() + 5.0F), (new Color(225, 225, 255, 255)).getRGB());
      font.drawString(matrix, this.getName(), (double)(this.getX() + 22), (double)((float)this.getY() + 6.5F), ColorAssist.getText());
      float centerX = (float)this.getX() + (float)this.getWidth() / 2.0F;
      int offset = 23;
      int maxWidth = 80;
      if (this.keysList.isEmpty() && PlayerInteractionHelper.isChat(mc.field_1755)) {
         float centerY = (float)(this.getY() + offset);
         String name = "Example Module";
         String bind = "[" + this.currentRandomKey + "]";
         String iconChar = "A";
         int textColor = ColorAssist.getText();
         int textAlpha = 255;
         int colorWithAlpha = ColorAssist.rgba(textColor >> 16 & 255, textColor >> 8 & 255, textColor & 255, textAlpha);
         int color = (new Color(225, 225, 255, 255)).getRGB();
         float bindWidth = fontModule.getStringWidth(bind);
         float bindBoxWidth = bindWidth + 6.0F;
         Calculate.scale(matrix, centerX, centerY, 1.0F, 1.0F, () -> {
            categoryIcon.drawString(matrix, iconChar, (double)((float)this.getX() + 4.5F), (double)(centerY + 1.5F), color);
            rectangle.render(ShapeProperties.create(matrix, (double)((float)this.getX() + 15.0F), (double)(centerY - 1.0F), (double)0.5F, (double)7.0F).color(ColorAssist.getOutline(1.0F, 0.5F)).build());
            fontModule.drawString(matrix, name, (double)(this.getX() + 19), (double)(centerY + 1.0F), colorWithAlpha);
            fontModule.drawString(matrix, bind, (double)((float)(this.getX() + this.getWidth()) - bindWidth - 8.0F), (double)(centerY + 1.0F), color);
         });
         int width = (int)fontModule.getStringWidth(name + bind) + 25;
         maxWidth = Math.max(width, maxWidth);
         offset += 11;
      } else {
         for(Module module : this.keysList) {
            String bind = "[" + StringHelper.getBindName(module.getKey()) + "]";
            float centerY = (float)(this.getY() + offset);
            float animation = module.getAnimation().getOutput().floatValue();
            String iconChar;
            switch (module.getCategory()) {
               case COMBAT -> iconChar = "A";
               case MOVEMENT -> iconChar = "B";
               case RENDER -> iconChar = "C";
               case PLAYER -> iconChar = "D";
               case MISC -> iconChar = "E";
               case CONFIGS -> iconChar = "F";
               default -> iconChar = module.getCategory().getReadableName().substring(0, 1);
            }

            int textColor = ColorAssist.getText();
            int textAlpha = 255;
            int colorWithAlpha = ColorAssist.rgba(textColor >> 16 & 255, textColor >> 8 & 255, textColor & 255, textAlpha);
            int color = (new Color(225, 225, 255, 255)).getRGB();
            float bindWidth = fontModule.getStringWidth(bind);
            float bindBoxWidth = bindWidth + 6.0F;
            Calculate.scale(matrix, centerX, centerY, 1.0F, animation, () -> {
               categoryIcon.drawString(matrix, iconChar, (double)((float)this.getX() + 4.5F), (double)(centerY + 1.5F), color);
               rectangle.render(ShapeProperties.create(matrix, (double)((float)this.getX() + 15.0F), (double)(centerY - 1.0F), (double)0.5F, (double)7.0F).color(ColorAssist.getOutline(1.0F, 0.5F)).build());
               fontModule.drawString(matrix, module.getName(), (double)(this.getX() + 19), (double)(centerY + 1.0F), colorWithAlpha);
               fontModule.drawString(matrix, bind, (double)((float)(this.getX() + this.getWidth()) - bindWidth - 8.0F), (double)(centerY + 1.0F), color);
            });
            String var10001 = module.getName();
            float width = fontModule.getStringWidth(var10001 + bind) + 25.0F;
            maxWidth = (int)Math.max(width, (float)maxWidth);
            offset += (int)(animation * 11.0F);
         }

         if (ElytraTarget.shouldElytraTarget) {
            float centerY = (float)(this.getY() + offset);
            String name = "Elytra Forward";
            String bind = "[ON]";
            String iconChar = "B";
            int textColor = ColorAssist.getText();
            int textAlpha = 255;
            int colorWithAlpha = ColorAssist.rgba(textColor >> 16 & 255, textColor >> 8 & 255, textColor & 255, textAlpha);
            int color = (new Color(225, 225, 255, 255)).getRGB();
            float bindWidth = fontModule.getStringWidth(bind);
            float bindBoxWidth = bindWidth + 6.0F;
            Calculate.scale(matrix, centerX, centerY, 1.0F, 1.0F, () -> {
               categoryIcon.drawString(matrix, iconChar, (double)((float)this.getX() + 4.5F), (double)(centerY + 1.5F), color);
               rectangle.render(ShapeProperties.create(matrix, (double)((float)this.getX() + 15.0F), (double)(centerY - 1.0F), (double)0.5F, (double)7.0F).color(ColorAssist.getOutline(1.0F, 0.5F)).build());
               fontModule.drawString(matrix, name, (double)(this.getX() + 19), (double)(centerY + 1.0F), colorWithAlpha);
               fontModule.drawString(matrix, bind, (double)((float)(this.getX() + this.getWidth()) - bindWidth - 8.0F), (double)(centerY + 1.0F), color);
            });
            int width = (int)fontModule.getStringWidth(name + bind) + 25;
            maxWidth = Math.max(width, maxWidth);
            offset += 11;
         }
      }

      this.setWidth(maxWidth + 10);
      this.setHeight(offset);
   }
}

