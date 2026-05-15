package dev.client.yanderov.display.hud;

import dev.client.yanderov.common.animation.Animation;
import dev.client.yanderov.common.animation.Direction;
import dev.client.yanderov.common.animation.implement.Decelerate;
import dev.client.yanderov.events.packet.PacketEvent;
import dev.client.yanderov.utils.client.Instance;
import dev.client.yanderov.utils.client.chat.StringHelper;
import dev.client.yanderov.utils.client.managers.api.draggable.AbstractDraggable;
import dev.client.yanderov.utils.display.color.ColorAssist;
import dev.client.yanderov.utils.display.font.FontRenderer;
import dev.client.yanderov.utils.display.font.Fonts;
import dev.client.yanderov.utils.display.geometry.Render2D;
import dev.client.yanderov.utils.display.shape.ShapeProperties;
import dev.client.yanderov.utils.interactions.interact.PlayerInteractionHelper;
import dev.client.yanderov.utils.math.calc.Calculate;
import dev.client.yanderov.utils.math.time.StopWatch;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import net.minecraft.class_1792;
import net.minecraft.class_1802;
import net.minecraft.class_2596;
import net.minecraft.class_2656;
import net.minecraft.class_2724;
import net.minecraft.class_332;
import net.minecraft.class_4587;
import net.minecraft.class_746;
import net.minecraft.class_7923;

public class CoolDowns extends AbstractDraggable {
   public final List list = new ArrayList();
   private long lastItemChange = 0L;
   private int currentItemIndex = 0;
   private static final class_1792[] EXAMPLE_ITEMS;

   public static CoolDowns getInstance() {
      return (CoolDowns)Instance.getDraggable(CoolDowns.class);
   }

   public CoolDowns() {
      super("Cool Downs", 10, 40, 80, 23, true);
   }

   public boolean visible() {
      return !this.list.isEmpty() || PlayerInteractionHelper.isChat(mc.field_1755);
   }

   public void tick() {
      this.list.removeIf((c) -> c.anim.isFinished(Direction.BACKWARDS));
      this.list.stream().filter((c) -> !((class_746)Objects.requireNonNull(mc.field_1724)).method_7357().method_7904(c.item.method_7854())).forEach((coolDown) -> coolDown.anim.setDirection(Direction.BACKWARDS));
      if (this.list.isEmpty() && PlayerInteractionHelper.isChat(mc.field_1755)) {
         long currentTime = System.currentTimeMillis();
         if (currentTime - this.lastItemChange >= 1000L) {
            this.currentItemIndex = (this.currentItemIndex + 1) % EXAMPLE_ITEMS.length;
            this.lastItemChange = currentTime;
         }
      }

   }

   public void packet(PacketEvent e) {
      if (!PlayerInteractionHelper.nullCheck()) {
         class_2596 var10000 = e.getPacket();
         Objects.requireNonNull(var10000);
         class_2596 var2 = var10000;
         byte var3 = 0;
         //$FF: var3->value
         //0->net/minecraft/class_2656
         //1->net/minecraft/class_2724
         // TODO: Fix switch statement for var2
        if (var2 != null) {
            // // case 0:
            // class_2656 c = (class_2656)var2;
            // class_1792 item = (class_1792)class_7923.field_41178.method_63535(c.comp_3082());
            // this.list.stream().filter((coolDown) -> coolDown.item.equals(item)).forEach((coolDown) -> coolDown.anim.setDirection(Direction.BACKWARDS));
            // if (c.comp_2199() != 0) {
            // this.list.add(new CoolDown(item, (new StopWatch()).setMs((long)(-c.comp_2199()) * 50L), (new Decelerate()).setMs(150).setValue((double)1.0F)));
            // }
            // break;
            // // case 1:
            // class_2724 p = (class_2724)var2;
            // this.list.clear();
         }

      }
   }

   public void drawDraggable(class_332 context) {
      class_4587 matrix = context.method_51448();
      FontRenderer font = Fonts.getSize(13, Fonts.Type.DEFAULT);
      FontRenderer fontCoolDown = Fonts.getSize(13, Fonts.Type.DEFAULT);
      FontRenderer icon = Fonts.getSize(21, Fonts.Type.ICONS);
      FontRenderer items = Fonts.getSize(12, Fonts.Type.DEFAULT);
      long activeCooldowns = this.list.stream().filter((c) -> !c.anim.isFinished(Direction.BACKWARDS)).count();
      String cooldownCountText = String.valueOf(activeCooldowns);
      float textWidth = items.getStringWidth(cooldownCountText);
      float boxWidth = textWidth + 6.0F;
      blur.render(ShapeProperties.create(matrix, (double)this.getX(), (double)this.getY(), (double)this.getWidth(), (double)15.5F).round(1.5F, 0.0F, 1.5F, 0.0F).quality(12.0F).color((new Color(0, 0, 0, 150)).getRGB()).build());
      rectangle.render(ShapeProperties.create(matrix, (double)this.getX(), (double)this.getY(), (double)this.getWidth(), (double)15.5F).round(1.5F, 0.0F, 1.5F, 0.0F).thickness(0.1F).outlineColor((new Color(33, 33, 33, 255)).getRGB()).color((new Color(18, 19, 20, 75)).getRGB(), (new Color(0, 2, 5, 75)).getRGB(), (new Color(0, 2, 5, 75)).getRGB(), (new Color(18, 19, 20, 75)).getRGB()).build());
      items.drawString(matrix, "Active:", (double)((float)(this.getX() + this.getWidth()) - boxWidth - 22.0F), (double)(this.getY() + 7), ColorAssist.getText());
      items.drawString(matrix, cooldownCountText, (double)((float)(this.getX() + this.getWidth()) - boxWidth - 2.5F), (double)(this.getY() + 7), (new Color(225, 225, 255, 255)).getRGB());
      rectangle.render(ShapeProperties.create(matrix, (double)(this.getX() + 18), (double)(this.getY() + 5), (double)0.5F, (double)6.0F).color(ColorAssist.getText(0.5F)).round(0.0F).build());
      blur.render(ShapeProperties.create(matrix, (double)this.getX(), (double)((float)this.getY() + 16.5F), (double)this.getWidth(), (double)(this.getHeight() - 17)).round(0.0F, 1.5F, 0.0F, 1.5F).quality(12.0F).color((new Color(0, 0, 0, 150)).getRGB()).build());
      rectangle.render(ShapeProperties.create(matrix, (double)this.getX(), (double)((float)this.getY() + 16.5F), (double)this.getWidth(), (double)(this.getHeight() - 17)).round(0.0F, 1.5F, 0.0F, 1.5F).thickness(0.1F).outlineColor((new Color(33, 33, 33, 255)).getRGB()).color((new Color(18, 19, 20, 75)).getRGB(), (new Color(0, 2, 5, 75)).getRGB(), (new Color(0, 2, 5, 75)).getRGB(), (new Color(18, 19, 20, 75)).getRGB()).build());
      icon.drawString(matrix, "D", (double)((float)this.getX() + 4.0F), (double)((float)this.getY() + 5.5F), (new Color(225, 225, 255, 255)).getRGB());
      font.drawString(matrix, "CoolDowns", (double)(this.getX() + 22), (double)((float)this.getY() + 6.5F), ColorAssist.getText());
      float centerX = (float)this.getX() + (float)this.getWidth() / 2.0F;
      int offset = 23;
      int maxWidth = 110;
      if (this.list.isEmpty() && PlayerInteractionHelper.isChat(mc.field_1755)) {
         float centerY = (float)(this.getY() + offset);
         class_1792 item = EXAMPLE_ITEMS[this.currentItemIndex];
         String name = "Example CoolDowns";
         String duration = "**:**";
         int textColor = ColorAssist.getText();
         int textAlpha = 255;
         int colorWithAlpha = ColorAssist.rgba(textColor >> 16 & 255, textColor >> 8 & 255, textColor & 255, textAlpha);
         int color = (new Color(225, 225, 255, 255)).getRGB();
         float durationWidth = fontCoolDown.getStringWidth(duration);
         float durationBoxWidth = durationWidth + 6.0F;
         Calculate.scale(matrix, centerX, centerY, 1.0F, 1.0F, () -> {
            Render2D.drawStack(matrix, item.method_7854(), (float)this.getX() + 3.5F, centerY - 3.0F, false, 0.5F);
            rectangle.render(ShapeProperties.create(matrix, (double)(this.getX() + 14), (double)(centerY - 1.0F), (double)0.5F, (double)6.0F).color(ColorAssist.getOutline(1.0F, 0.5F)).build());
            fontCoolDown.drawString(matrix, name, (double)(this.getX() + 18), (double)(centerY + 1.0F), colorWithAlpha);
            fontCoolDown.drawString(matrix, duration, (double)((float)(this.getX() + this.getWidth()) - durationWidth - 8.0F), (double)(centerY + 1.0F), color);
         });
         int width = (int)fontCoolDown.getStringWidth(name + duration) + 30;
         maxWidth = Math.max(width, maxWidth);
         offset += 11;
      } else {
         for(CoolDown coolDown : this.list) {
            float animation = coolDown.anim.getOutput().floatValue();
            float centerY = (float)(this.getY() + offset);
            long elapsedTime = coolDown.time.elapsedTime();
            int time = 0;
            if (elapsedTime >= -2147483648L && elapsedTime <= 2147483647L) {
               time = (int)(-elapsedTime / 1000L);
            } else {
               time = elapsedTime < 0L ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            String name = coolDown.item.method_7854().method_7964().getString();
            String duration = StringHelper.getDuration(time);
            int textColor = ColorAssist.getText();
            int textAlpha = 255;
            int colorWithAlpha = ColorAssist.rgba(textColor >> 16 & 255, textColor >> 8 & 255, textColor & 255, textAlpha);
            int color = (new Color(225, 225, 255, 255)).getRGB();
            float durationWidth = fontCoolDown.getStringWidth(duration);
            float durationBoxWidth = durationWidth + 6.0F;
            Calculate.scale(matrix, centerX, centerY, 1.0F, animation, () -> {
               Render2D.drawStack(matrix, coolDown.item.method_7854(), (float)this.getX() + 3.5F, centerY - 3.0F, false, 0.5F);
               rectangle.render(ShapeProperties.create(matrix, (double)(this.getX() + 15), (double)(centerY - 1.0F), (double)0.5F, (double)6.0F).color(ColorAssist.getOutline(1.0F, 0.5F)).build());
               fontCoolDown.drawString(matrix, name, (double)(this.getX() + 18), (double)(centerY + 1.0F), colorWithAlpha);
               fontCoolDown.drawString(matrix, duration, (double)((float)(this.getX() + this.getWidth()) - durationWidth - 8.0F), (double)(centerY + 1.0F), color);
            });
            int width = (int)fontCoolDown.getStringWidth(name + duration) + 30;
            maxWidth = Math.max(width, maxWidth);
            offset += (int)(11.0F * animation);
         }
      }

      this.setWidth(maxWidth + 10);
      this.setHeight(offset);
   }

   static {
      EXAMPLE_ITEMS = new class_1792[]{class_1802.field_8449, class_1802.field_8634, class_1802.field_8479, class_1802.field_49814, class_1802.field_8367, class_1802.field_8547, class_1802.field_8399, class_1802.field_8551, class_1802.field_22021};
   }

   public static record CoolDown(class_1792 item, StopWatch time, Animation anim) {
   }
}

