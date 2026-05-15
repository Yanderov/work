package fun.Yanderov.display.hud;

import fun.Yanderov.common.animation.Animation;
import fun.Yanderov.common.animation.Direction;
import fun.Yanderov.common.animation.implement.Decelerate;
import fun.Yanderov.events.packet.PacketEvent;
import fun.Yanderov.utils.client.managers.api.draggable.AbstractDraggable;
import fun.Yanderov.utils.display.color.ColorAssist;
import fun.Yanderov.utils.display.font.FontRenderer;
import fun.Yanderov.utils.display.font.Fonts;
import fun.Yanderov.utils.display.geometry.Render2D;
import fun.Yanderov.utils.display.shape.ShapeProperties;
import fun.Yanderov.utils.interactions.interact.PlayerInteractionHelper;
import fun.Yanderov.utils.math.calc.Calculate;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import net.minecraft.class_124;
import net.minecraft.class_1291;
import net.minecraft.class_1293;
import net.minecraft.class_1294;
import net.minecraft.class_2596;
import net.minecraft.class_2678;
import net.minecraft.class_2718;
import net.minecraft.class_2724;
import net.minecraft.class_2783;
import net.minecraft.class_2960;
import net.minecraft.class_332;
import net.minecraft.class_4587;
import net.minecraft.class_6880;
import net.minecraft.class_746;
import net.minecraft.class_7923;

public class Potions extends AbstractDraggable {
   private final List list = new ArrayList();
   private static final class_6880[] NEGATIVE_EFFECTS;
   private long lastEffectChange = 0L;
   private class_6880 currentRandomEffect;

   public Potions() {
      super("Potions", 200, 40, 80, 23, true);
      this.currentRandomEffect = class_1294.field_5904;
   }

   public boolean visible() {
      return !this.list.isEmpty() || PlayerInteractionHelper.isChat(mc.field_1755);
   }

   public void tick() {
      this.list.removeIf((p) -> p.anim.isFinished(Direction.BACKWARDS));
      this.list.forEach((p) -> p.effect.method_5585(mc.field_1724, (Runnable)null));
      if (this.list.isEmpty() && PlayerInteractionHelper.isChat(mc.field_1755)) {
         long currentTime = System.currentTimeMillis();
         if (currentTime - this.lastEffectChange >= 1000L) {
            List<class_6880<class_1291>> effects = new ArrayList();

            for(class_2960 id : class_7923.field_41174.method_10235()) {
               Optional var10000 = class_7923.field_41174.method_10223(id);
               Objects.requireNonNull(effects);
               var10000.ifPresent(effects::add);
            }

            if (!effects.isEmpty()) {
               this.currentRandomEffect = (class_6880)effects.get((new Random()).nextInt(effects.size()));
               this.lastEffectChange = currentTime;
            }
         }
      }

   }

   public void packet(PacketEvent e) {
      class_2596 var10000 = e.getPacket();
      Objects.requireNonNull(var10000);
      class_2596 var2 = var10000;
      byte var3 = 0;
      //$FF: var3->value
      //0->net/minecraft/class_2783
      //1->net/minecraft/class_2718
      //2->net/minecraft/class_2724
      //3->net/minecraft/class_2678
      switch (var2.typeSwitch<invokedynamic>(var2, var3)) {
         case 0:
            class_2783 effect = (class_2783)var2;
            if (!PlayerInteractionHelper.nullCheck() && effect.method_11943() == ((class_746)Objects.requireNonNull(mc.field_1724)).method_5628()) {
               class_6880<class_1291> effectId = effect.method_11946();
               this.list.stream().filter((px) -> px.effect.method_5579().method_55840().equals(effectId.method_55840())).forEach((s) -> s.anim.setDirection(Direction.BACKWARDS));
               this.list.add(new Potion(new class_1293(effectId, effect.method_11944(), effect.method_11945(), effect.method_11950(), effect.method_11949(), effect.method_11942()), (new Decelerate()).setMs(150).setValue((double)1.0F)));
            }
            break;
         case 1:
            class_2718 effect = (class_2718)var2;
            this.list.stream().filter((s) -> s.effect.method_5579().method_55840().equals(effect.comp_2176().method_55840())).forEach((s) -> s.anim.setDirection(Direction.BACKWARDS));
            break;
         case 2:
            class_2724 p = (class_2724)var2;
            this.list.clear();
            break;
         case 3:
            class_2678 p = (class_2678)var2;
            this.list.clear();
      }

   }

   public void drawDraggable(class_332 context) {
      class_4587 matrix = context.method_51448();
      FontRenderer font = Fonts.getSize(13, Fonts.Type.DEFAULT);
      FontRenderer fontPotion = Fonts.getSize(13, Fonts.Type.DEFAULT);
      FontRenderer icon = Fonts.getSize(17, Fonts.Type.ICONS);
      FontRenderer items = Fonts.getSize(12, Fonts.Type.DEFAULT);
      long activeEffects = this.list.stream().filter((p) -> !p.anim.isFinished(Direction.BACKWARDS)).count();
      String effectCountText = String.valueOf(activeEffects);
      float textWidth = items.getStringWidth(effectCountText);
      float boxWidth = textWidth + 6.0F;
      blur.render(ShapeProperties.create(matrix, (double)this.getX(), (double)this.getY(), (double)this.getWidth(), (double)15.5F).round(1.5F, 0.0F, 1.5F, 0.0F).quality(12.0F).color((new Color(0, 0, 0, 150)).getRGB()).build());
      rectangle.render(ShapeProperties.create(matrix, (double)this.getX(), (double)this.getY(), (double)this.getWidth(), (double)15.5F).round(1.5F, 0.0F, 1.5F, 0.0F).thickness(0.1F).outlineColor((new Color(33, 33, 33, 255)).getRGB()).color((new Color(18, 19, 20, 75)).getRGB(), (new Color(0, 2, 5, 75)).getRGB(), (new Color(0, 2, 5, 75)).getRGB(), (new Color(18, 19, 20, 75)).getRGB()).build());
      items.drawString(matrix, "Active:", (double)((float)(this.getX() + this.getWidth()) - boxWidth - 22.0F), (double)(this.getY() + 7), ColorAssist.getText());
      items.drawString(matrix, effectCountText, (double)((float)(this.getX() + this.getWidth()) - boxWidth - 3.0F), (double)(this.getY() + 7), (new Color(225, 225, 255, 255)).getRGB());
      rectangle.render(ShapeProperties.create(matrix, (double)(this.getX() + 18), (double)(this.getY() + 5), (double)0.5F, (double)6.0F).color(ColorAssist.getText(0.5F)).round(0.0F).build());
      blur.render(ShapeProperties.create(matrix, (double)this.getX(), (double)((float)this.getY() + 16.5F), (double)this.getWidth(), (double)(this.getHeight() - 17)).round(0.0F, 1.5F, 0.0F, 1.5F).quality(12.0F).color((new Color(0, 0, 0, 150)).getRGB()).build());
      rectangle.render(ShapeProperties.create(matrix, (double)this.getX(), (double)((float)this.getY() + 16.5F), (double)this.getWidth(), (double)(this.getHeight() - 17)).round(0.0F, 1.5F, 0.0F, 1.5F).thickness(0.1F).outlineColor((new Color(33, 33, 33, 255)).getRGB()).color((new Color(18, 19, 20, 75)).getRGB(), (new Color(0, 2, 5, 75)).getRGB(), (new Color(0, 2, 5, 75)).getRGB(), (new Color(18, 19, 20, 75)).getRGB()).build());
      icon.drawString(matrix, "C", (double)((float)this.getX() + 5.0F), (double)((float)this.getY() + 6.5F), (new Color(225, 225, 255, 255)).getRGB());
      font.drawString(matrix, this.getName(), (double)(this.getX() + 22), (double)((float)this.getY() + 6.5F), ColorAssist.getText());
      float centerX = (float)this.getX() + (float)this.getWidth() / 2.0F;
      int offset = 23;
      int maxWidth = 95;
      if (this.list.isEmpty() && PlayerInteractionHelper.isChat(mc.field_1755)) {
         float centerY = (float)(this.getY() + offset);
         String name = "Example effect";
         String duration = "**:**";
         int textColor = ColorAssist.getText();
         int textAlpha = 255;
         int colorWithAlpha = ColorAssist.rgba(textColor >> 16 & 255, textColor >> 8 & 255, textColor & 255, textAlpha);
         int color = (new Color(225, 225, 255, 255)).getRGB();
         int colorWithAlphaRectangle = ColorAssist.rgba(textColor >> 16 & 205, textColor >> 8 & 205, textColor & 205, textAlpha - 125);
         float durationWidth = fontPotion.getStringWidth(duration);
         float durationBoxWidth = durationWidth + 6.0F;
         Calculate.scale(matrix, centerX, centerY, 1.0F, 1.0F, () -> {
            Render2D.drawSprite(matrix, mc.method_18505().method_18663(this.currentRandomEffect), (float)this.getX() + 3.5F, (float)((int)centerY - 2), 8.0F, 8, colorWithAlpha);
            rectangle.render(ShapeProperties.create(matrix, (double)(this.getX() + 14), (double)(centerY - 1.0F), (double)0.5F, (double)7.0F).color(colorWithAlphaRectangle).build());
            fontPotion.drawString(matrix, name, (double)(this.getX() + 18), (double)(centerY + 1.0F), colorWithAlpha);
            fontPotion.drawString(matrix, duration, (double)((float)(this.getX() + this.getWidth()) - durationWidth - 8.0F), (double)(centerY + 1.0F), color);
         });
         int width = (int)fontPotion.getStringWidth(name + duration) + 30;
         maxWidth = Math.max(width, maxWidth);
         offset += 11;
      } else {
         for(Potion potion : this.list) {
            class_1293 effect = potion.effect;
            float animation = potion.anim.getOutput().floatValue();
            float centerY = (float)(this.getY() + offset);
            int amplifier = effect.method_5578();
            String name = ((class_1291)effect.method_5579().comp_349()).method_5560().getString();
            String duration = this.getDuration(effect);
            String lvl = amplifier > 0 ? String.valueOf(class_124.field_1061) + " " + (amplifier + 1) + String.valueOf(class_124.field_1070) : "";
            boolean isBadEffect = this.isBadEffect(effect.method_5579());
            int textColor = isBadEffect ? ColorAssist.rgba(255, 85, 75, 255) : ColorAssist.getText();
            int textAlpha = 255;
            if (effect.method_5584() <= 200 && effect.method_5584() > 0) {
               double output = (double)0.5F + (double)0.5F * Math.cos((Math.PI * 2D) * (double)(System.currentTimeMillis() % 700L) / (double)700.0F);
               textAlpha = (int)((double)100.0F + (double)155.0F * output);
            } else if (effect.method_5584() == 0) {
               textAlpha = 0;
            }

            int colorWithAlpha = isBadEffect ? ColorAssist.rgba(255, 85, 75, textAlpha) : ColorAssist.rgba(textColor >> 16 & 255, textColor >> 8 & 255, textColor & 255, textAlpha);
            int color = (new Color(225, 225, 255, 255)).getRGB();
            int colorWithAlphaRectangle = isBadEffect ? ColorAssist.rgba(255, 85, 75, textAlpha - 125) : ColorAssist.rgba(textColor >> 16 & 205, textColor >> 8 & 205, textColor & 205, textAlpha - 125);
            float durationWidth = fontPotion.getStringWidth(duration);
            float durationBoxWidth = durationWidth + 6.0F;
            Calculate.scale(matrix, centerX, centerY, 1.0F, animation, () -> {
               Render2D.drawSprite(matrix, mc.method_18505().method_18663(effect.method_5579()), (float)this.getX() + 3.5F, (float)((int)centerY - 2), 8.0F, 8, colorWithAlpha);
               rectangle.render(ShapeProperties.create(matrix, (double)(this.getX() + 14), (double)(centerY - 1.0F), (double)0.5F, (double)7.0F).color(colorWithAlphaRectangle).build());
               fontPotion.drawString(matrix, name, (double)(this.getX() + 18), (double)(centerY + 1.0F), colorWithAlpha);
               if (amplifier > 0) {
                  String level = " " + (amplifier + 1);
                  fontPotion.drawString(matrix, level, (double)((float)(this.getX() + 18) + fontPotion.getStringWidth(name)), (double)(centerY + 1.0F), colorWithAlpha);
               }

               fontPotion.drawString(matrix, duration, (double)((float)(this.getX() + this.getWidth()) - durationWidth - 8.0F), (double)(centerY + 1.0F), color);
            });
            int width = (int)fontPotion.getStringWidth(name + lvl + duration) + 30;
            maxWidth = Math.max(width, maxWidth);
            offset += (int)(11.0F * animation);
         }
      }

      this.setWidth(maxWidth);
      this.setHeight(offset);
   }

   private String getDuration(class_1293 pe) {
      int var1 = pe.method_5584();
      int mins = var1 / 1200;
      return !pe.method_48559() && mins <= 60 ? mins + ":" + String.format("%02d", var1 % 1200 / 20) : "**:**";
   }

   private boolean isBadEffect(class_6880 effect) {
      for(class_6880 negativeEffect : NEGATIVE_EFFECTS) {
         if (effect == negativeEffect) {
            return true;
         }
      }

      return false;
   }

   static {
      NEGATIVE_EFFECTS = new class_6880[]{class_1294.field_5899, class_1294.field_5920, class_1294.field_5916, class_1294.field_5919, class_1294.field_5903, class_1294.field_5909, class_1294.field_5901, class_1294.field_5921, class_1294.field_5911, class_1294.field_5902, class_1294.field_5908, class_1294.field_16595};
   }

   private static record Potion(class_1293 effect, Animation anim) {
   }
}

