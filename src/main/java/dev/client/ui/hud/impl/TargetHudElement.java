package dev.client.ui.hud.impl;

import dev.client.WildClient;
import dev.client.managers.FontManager;
import dev.client.modules.impl.combat.Aura;
import dev.client.modules.impl.combat.TriggerBot;
import dev.client.modules.impl.player.NameProtect;
import dev.client.modules.settings.impl.ModeSetting;
import dev.client.ui.draggable.impl.TargetHudDraggable;
import dev.client.ui.hud.HudElement;
import dev.client.util.animations.Animation;
import dev.client.util.animations.Direction;
import dev.client.util.animations.impl.EaseBackIn;
import dev.client.util.color.ColorUtil;
import dev.client.util.math.MathUtil;
import dev.client.util.render.AnimationUtil;
import dev.client.util.render.ItemRenderUtil;
import dev.client.util.render.LastTarget;
import dev.client.util.render.builders.Builder;
import dev.client.util.render.builders.impl.TextBuilder;
import dev.client.util.render.builders.states.QuadColorState;
import dev.client.util.render.builders.states.QuadRadiusState;
import dev.client.util.render.builders.states.SizeState;
import dev.client.util.render.msdf.MsdfFont;
import dev.client.util.render.renderers.impl.BuiltBlur;
import dev.client.util.render.renderers.impl.BuiltBorder;
import dev.client.util.render.renderers.impl.BuiltRectangle;
import dev.client.util.render.renderers.impl.BuiltText;
import dev.client.util.render.renderers.impl.BuiltTexture;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.joml.Matrix4f;

@Environment(EnvType.CLIENT)
public class TargetHudElement extends HudElement {
   private final Animation animation;
   private LastTarget lastTarget = new LastTarget();
   float health;
   private NameProtect nameProtect;
   
   // Настройка стиля Target HUD
   private final ModeSetting style = new ModeSetting()
           .name("Style")
           .value("WildClient")
           .modes("WildClient", "Yanderov", "Compact");

   public TargetHudElement() {
      super(new TargetHudDraggable(), "TargetHUD");
      this.animation = new EaseBackIn(300, 1.0D, 0.1F, Direction.BACKWARDS);
      this.health = 0.0F;
   }
   
   public ModeSetting getStyleSetting() {
      return this.style;
   }

   public void render(DrawContext drawContext) {
      // Выбор стиля рендеринга
      switch (this.style.getValue()) {
         case "WildClient":
            renderWildClientStyle(drawContext);
            break;
         case "Yanderov":
            renderYanderovStyle(drawContext);
            break;
         case "Compact":
            renderCompactStyle(drawContext);
            break;
      }
      
      // Обновление цели
      if (this.getTarget() != null) {
         Entity target = this.getTarget();
         this.animation.setDirection(Direction.FORWARDS);
         this.lastTarget.setEntity(target);
         this.lastTarget.update(target);
         if (target instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity)target;
            this.lastTarget.setName(player.getName().getString());
         } else {
            this.lastTarget.setName(target.getName().getString());
         }

         if (target instanceof LivingEntity livingEntity) {
            this.lastTarget.setHealth((double)livingEntity.getHealth());
            this.lastTarget.setMaxHealth(this.lastTarget.getMaxHealth());
         }
      } else {
         this.animation.setDirection(Direction.BACKWARDS);
      }
   }
   
   private void renderWildClientStyle(DrawContext drawContext) {
      float width = 130.0F;
      float widthBlack = 76.0F;
      if (this.nameProtect == null) {
         this.nameProtect = (NameProtect)WildClient.INSTANCE.getModuleManager().getByClass(NameProtect.class);
      }

      Matrix4f matrix = drawContext.getMatrices().peek().getPositionMatrix();
      Color clientColor = ColorUtil.setAlpha(this.animation.getOutput(), Color.white);
      Color white = ColorUtil.setAlpha(this.animation.getOutput(), Color.WHITE);
      Color gray = ColorUtil.setAlpha(this.animation.getOutput(), new Color(2063597567, true));
      Color black = ColorUtil.setAlpha(this.animation.getOutput(), Color.black);
      
      if (!this.animation.finished(Direction.BACKWARDS)) {
         BuiltBlur blur = Builder.blur().size(new SizeState(width, 50.0F)).radius(new QuadRadiusState(11.0F)).blurRadius((float)(12.0D * this.animation.getOutput())).smoothness((float)(1.0D * this.animation.getOutput())).color(new QuadColorState(Color.white)).build();
         blur.render(matrix, (float)this.draggable.x, (float)this.draggable.y);
         BuiltRectangle rectangle = Builder.rectangle().size(new SizeState(width, 50.0F)).color(new QuadColorState(ColorUtil.setAlpha(this.animation.getOutput(), new Color(2048202266, true)))).radius(new QuadRadiusState(11.0F)).smoothness(1.15F).build();
         rectangle.render(matrix, (float)this.draggable.x, (float)this.draggable.y);
         rectangle = Builder.rectangle().size(new SizeState(42.0F, 42.0F)).color(new QuadColorState(black)).radius(new QuadRadiusState(8.0F, 8.0F, 5.0F, 5.0F)).smoothness(1.15F).build();
         rectangle.render(matrix, (float)(this.draggable.x + 4), (float)(this.draggable.y + 4));
         AbstractTexture abstractTexture = MinecraftClient.getInstance().getTextureManager().getTexture(this.lastTarget.getAvatar());
         float hurtPercent = ((float)this.lastTarget.getHurtTime() - (this.lastTarget.getHurtTime() != 0 ? MinecraftClient.getInstance().getRenderTickCounter().getTickDelta(false) : 0.0F)) / 10.0F;
         Color imageColor = white;
         if (hurtPercent > 0.0F) {
            int f = (int)(hurtPercent * 90.0F);
            if (f > 200) {
               f = 200;
            }

            imageColor = new Color(255, 255 - f, 255 - f, white.getAlpha());
         }

         if (this.lastTarget.isPlayer()) {
            BuiltTexture texture = (BuiltTexture)Builder.texture().size(new SizeState(32.0F, 32.0F)).radius(new QuadRadiusState(6.0F)).texture(0.125F, 0.125F, 0.125F, 0.125F, abstractTexture).color(new QuadColorState(imageColor)).build();
            texture.render(matrix, (float)(this.draggable.x + 9), (float)(this.draggable.y + 9));
         } else {
            rectangle = Builder.rectangle().size(new SizeState(32.0F, 32.0F)).color(new QuadColorState(black)).radius(new QuadRadiusState(6.0F)).smoothness(1.15F).build();
            rectangle.render(matrix, (float)(this.draggable.x + 9), (float)(this.draggable.y + 9));
            Builder.text().font(FontManager.SUISSEINTMEDIUM.get()).text("?").color(white).size(25.0F).thickness(0.05F).build().render(matrix, (float)(this.draggable.x + 17), (float)(this.draggable.y + 10));
         }

         rectangle = Builder.rectangle().size(new SizeState((double)widthBlack, 19.5D)).color(new QuadColorState(black)).radius(new QuadRadiusState(3.0F, 3.0F, 3.0F, 7.0F)).smoothness(1.15F).build();
         rectangle.render(matrix, (float)(this.draggable.x + 48), (float)(this.draggable.y + 4));
         BuiltText themeText = (BuiltText)Builder.text().font(FontManager.SUISSEINTMEDIUM.get()).text(this.nameProtect.replace(this.lastTarget.getName())).color(white).size(7.5F).thickness(0.05F).build();
         themeText.render(matrix, (float)(this.draggable.x + 54), (float)(this.draggable.y + 9));
         rectangle = Builder.rectangle().size(new SizeState((double)widthBlack, 19.5D)).color(new QuadColorState(black)).radius(new QuadRadiusState(3.0F, 3.0F, 7.0F, 3.0F)).smoothness(1.15F).build();
         rectangle.render(matrix, (double)(this.draggable.x + 48), (double)this.draggable.y + 25.5D);
         rectangle = Builder.rectangle().size(new SizeState((double)(widthBlack - 10.0F), 3.25D)).color(new QuadColorState(ColorUtil.setAlpha(this.animation.getOutput(), new Color(184549375, true)))).radius(new QuadRadiusState(0.5F)).smoothness(1.15F).build();
         rectangle.render(matrix, (float)(this.draggable.x + 52), (float)(this.draggable.y + 38));
         rectangle = Builder.rectangle().size(new SizeState((double)((widthBlack - 10.0F) * (this.health > 1.0F ? 1.0F : this.health)), 3.25D)).color(new QuadColorState(clientColor)).radius(new QuadRadiusState(0.5F)).smoothness(1.15F).build();
         rectangle.render(matrix, (float)(this.draggable.x + 52), (float)(this.draggable.y + 38));
         TextBuilder textBuilder = Builder.text().font(FontManager.SUISSEINTMEDIUM.get());
         double healthValue = (double)this.health * this.lastTarget.getMaxHealth();
         themeText = (BuiltText)textBuilder.text("" + (int)MathUtil.round(healthValue, 0.5D)).color(white).size(6.0F).thickness(0.05F).build();
         themeText.render(matrix, (float)this.draggable.x + widthBlack + 25.0F, (float)(this.draggable.y + 29));
         themeText = (BuiltText)Builder.text().font(FontManager.SUISSEINTMEDIUM.get()).text("hp").color(gray).size(6.0F).thickness(0.05F).build();
         themeText.render(matrix, (float)this.draggable.x + widthBlack + 35.0F, (float)(this.draggable.y + 29));

         for(int i = 0; i < 4; ++i) {
            rectangle = Builder.rectangle().size(new SizeState(8.0F, 8.0F)).color(new QuadColorState(ColorUtil.setAlpha(this.animation.getOutput(), new Color(67108863, true)))).radius(new QuadRadiusState(0.5F)).smoothness(1.15F).build();
            rectangle.render(matrix, (double)(this.draggable.x + 52 + i * 10), (double)this.draggable.y + 28.5D);
            BuiltBorder border = (BuiltBorder)Builder.border().size(new SizeState(8.0F, 8.0F)).color(new QuadColorState(ColorUtil.colorAlpha(new Color(184549375, true), 3))).radius(new QuadRadiusState(0.5F)).thickness(0.01F).smoothness(0.65F, 0.65F).build();
            border.render(matrix, (double)(this.draggable.x + 52 + i * 10), (double)this.draggable.y + 28.5D);
         }

         if (this.lastTarget.getEntity() != null) {
            Entity entity = this.lastTarget.getEntity();
            if (entity instanceof PlayerEntity) {
               PlayerEntity player = (PlayerEntity)entity;
               this.renderItems(drawContext, this.draggable.x + 53, (int)((float)this.draggable.y + 30.5F), player, (float)this.animation.getOutput());
            }
         }

         this.health = MathUtil.fast(this.health, (float)(this.lastTarget.getHealth() / this.lastTarget.getMaxHealth()), 5.0F);
      }
   }
   
   private void renderYanderovStyle(DrawContext drawContext) {
      if (this.lastTarget.getEntity() == null && !(MinecraftClient.getInstance().currentScreen instanceof ChatScreen)) return;
      
      float scaleAnim = (float) this.animation.getOutput();
      if (scaleAnim <= 0.0F) return;

      Matrix4f matrix = drawContext.getMatrices().peek().getPositionMatrix();
      
      float width = 110.0F;
      float height = 70.0F;
      float centerX = (float)this.draggable.x + width / 2.0F;
      float centerY = (float)this.draggable.y + height / 2.0F;
      
      drawContext.getMatrices().push();
      AnimationUtil.sizeAnimation(drawContext, (double)centerX, (double)centerY, scaleAnim * 1.2);
      
      float x = (float)this.draggable.x;
      float y = (float)this.draggable.y;
      
      float headerHeight = 12.2F;
      float headerPaddingTop = 2.3F;
      float gap = 0.8F;
      float headerWidth = 54.0F;
      float lowerSectionY = y + headerHeight + headerPaddingTop + gap;
      float headerX = x + (width - headerWidth) / 2.0F;
      
      // Header Background
      Builder.blur().size(new SizeState(headerWidth, headerHeight)).radius(new QuadRadiusState(1.5F, 0.0F, 1.5F, 0.0F)).blurRadius(12.0F).smoothness(1.0F).color(new QuadColorState(new Color(0, 0, 0, 150))).build().render(matrix, headerX, y + headerPaddingTop);
      Builder.rectangle().size(new SizeState(headerWidth, headerHeight)).color(new QuadColorState(new Color(18, 19, 20, 75))).radius(new QuadRadiusState(3.0F)).smoothness(0.1F).build().render(matrix, headerX, y + headerPaddingTop);
      
      // Main Section Background
      Builder.blur().size(new SizeState(width - 2.0F, 43.6F)).radius(new QuadRadiusState(1.5F)).blurRadius(12.0F).smoothness(1.0F).color(new QuadColorState(new Color(0, 0, 0, 150))).build().render(matrix, x + 1.0F, lowerSectionY);
      Builder.rectangle().size(new SizeState(width - 2.0F, 42.0F)).color(new QuadColorState(new Color(18, 19, 20, 75))).radius(new QuadRadiusState(1.5F)).smoothness(0.1F).build().render(matrix, x + 1.0F, lowerSectionY);
      
      // Name (Info)
      String name = this.lastTarget.getName();
      if (this.nameProtect == null) this.nameProtect = (NameProtect)WildClient.INSTANCE.getModuleManager().getByClass(NameProtect.class);
      name = this.nameProtect.replace(name);
      float nameWidth = FontManager.SUISSEINTMEDIUM.get().getWidth(name, 6.0F);
      Builder.text().font(FontManager.SUISSEINTMEDIUM.get()).text(name).color(Color.WHITE).size(6.0F).thickness(0.05F).build().render(matrix, x + width / 2.0F - nameWidth / 2.0F, y + headerPaddingTop + 4.7F);
      
      // Face
      AbstractTexture abstractTexture = MinecraftClient.getInstance().getTextureManager().getTexture(this.lastTarget.getAvatar());
      float headSize = 25.0F;
      float headX = x + (width - headSize) / 2.0F;
      float headY = y + 15 + 4;
      Builder.texture().size(new SizeState(headSize, headSize)).radius(new QuadRadiusState(3.0F)).texture(0.125F, 0.125F, 0.125F, 0.125F, abstractTexture).color(new QuadColorState(Color.WHITE)).build().render(matrix, headX, headY);
      
      // Items
      if (this.lastTarget.getEntity() instanceof PlayerEntity player) {
          this.renderYanderovItems(drawContext, (int)x + 5, (int)(y + 19), player, true);
          this.renderYanderovItems(drawContext, (int)(x + width - 30), (int)(y + 19), player, false);
      }
      
      // Bottom Section (Health bar)
      float hp = (float) this.lastTarget.getHealth();
      float maxHp = (float) this.lastTarget.getMaxHealth();
      float barWidth = width - 10;
      float barHeight = 4.0F;
      float barX = x + 5.0F;
      float barY = y + 55;
      float targetHealth = hp / maxHp * barWidth;
      this.health = MathUtil.fast(this.health, targetHealth, 5.0F);
      
      Builder.rectangle().size(new SizeState(barWidth, barHeight)).color(new QuadColorState(new Color(50, 50, 50, 150))).radius(new QuadRadiusState(1.0F)).build().render(matrix, barX, barY);
      Builder.rectangle().size(new SizeState(Math.max(0, this.health), barHeight)).color(new QuadColorState(Color.WHITE)).radius(new QuadRadiusState(1.0F)).build().render(matrix, barX, barY);
      
      String hpString = String.format("%.1f HP", hp);
      Builder.text().font(FontManager.SUISSEINTMEDIUM.get()).text(hpString).color(Color.WHITE).size(5.0F).thickness(0.05F).build().render(matrix, barX, barY + 6);
      
      if (MinecraftClient.getInstance().player != null) {
          boolean winning = MinecraftClient.getInstance().player.getHealth() >= hp;
          String status = winning ? "WIN" : "LOSE";
          Color statusColor = winning ? Color.GREEN : Color.RED;
          float statusX = x + width - FontManager.SUISSEINTMEDIUM.get().getWidth(status, 5.0F) - 5.0F;
          Builder.text().font(FontManager.SUISSEINTMEDIUM.get()).text(status).color(statusColor).size(5.0F).thickness(0.05F).build().render(matrix, statusX, barY + 6);
      }
      
      drawContext.getMatrices().pop();
   }

   private void renderYanderovItems(DrawContext drawContext, int x, int y, PlayerEntity player, boolean armor) {
       MatrixStack matrices = drawContext.getMatrices();
       ArrayList<ItemStack> items = new ArrayList<>();
       if (armor) {
           player.getArmorItems().forEach(items::add);
           Collections.reverse(items);
       } else {
           items.add(player.getMainHandStack());
           items.add(player.getOffHandStack());
       }

       int i = 0;
       for (ItemStack item : items) {
           if (item.getItem() != Items.AIR) {
               float itemX = x + (i % 2) * 13;
               float itemY = y + (i / 2) * 13;
               
               Builder.rectangle().size(new SizeState(12.0F, 12.0F)).color(new QuadColorState(new Color(0, 0, 0, 100))).radius(new QuadRadiusState(1.5F)).build().render(matrices.peek().getPositionMatrix(), itemX, itemY);
               
               matrices.push();
               matrices.translate(itemX, itemY, 0);
               matrices.scale(0.65f, 0.65f, 0.65f);
               ItemRenderUtil.drawItemAlpha(matrices, item, 0, 0, (float)this.animation.getOutput());
               matrices.pop();
           }
           i++;
       }
   }

   private void renderCompactStyle(DrawContext drawContext) {
      float width = 100.0F;
      float height = 35.0F;
      
      if (this.nameProtect == null) {
         this.nameProtect = (NameProtect)WildClient.INSTANCE.getModuleManager().getByClass(NameProtect.class);
      }

      Matrix4f matrix = drawContext.getMatrices().peek().getPositionMatrix();
      Color white = ColorUtil.setAlpha(this.animation.getOutput(), Color.WHITE);
      Color darkBg = ColorUtil.setAlpha(this.animation.getOutput(), new Color(0, 0, 0, 150));
      
      if (!this.animation.finished(Direction.BACKWARDS)) {
         // Компактный фон
         BuiltRectangle rectangle = Builder.rectangle().size(new SizeState(width, height)).color(new QuadColorState(darkBg)).radius(new QuadRadiusState(5.0F)).smoothness(1.15F).build();
         rectangle.render(matrix, (float)this.draggable.x, (float)this.draggable.y);
         
         // Имя
         BuiltText nameText = Builder.text().font(FontManager.SUISSEINTMEDIUM.get()).text(this.nameProtect.replace(this.lastTarget.getName())).color(white).size(7.0F).thickness(0.05F).build();
         nameText.render(matrix, (float)(this.draggable.x + 5), (float)(this.draggable.y + 5));
         
         // Полоска здоровья
         float barWidth = width - 10;
         float barHeight = 3.0F;
         float barX = this.draggable.x + 5;
         float barY = this.draggable.y + height - 8;
         
         rectangle = Builder.rectangle().size(new SizeState(barWidth, barHeight)).color(new QuadColorState(ColorUtil.setAlpha(this.animation.getOutput(), new Color(50, 50, 50, 255)))).radius(new QuadRadiusState(1.5F)).smoothness(1.15F).build();
         rectangle.render(matrix, barX, barY);
         
         rectangle = Builder.rectangle().size(new SizeState(barWidth * (this.health > 1.0F ? 1.0F : this.health), barHeight)).color(new QuadColorState(white)).radius(new QuadRadiusState(1.5F)).smoothness(1.15F).build();
         rectangle.render(matrix, barX, barY);
         
         // HP
         double healthValue = (double)this.health * this.lastTarget.getMaxHealth();
         BuiltText hpText = Builder.text().font(FontManager.SUISSEINTMEDIUM.get()).text(String.format("%.0f", healthValue)).color(white).size(6.0F).thickness(0.05F).build();
         hpText.render(matrix, barX, barY - 10);
         
         this.health = MathUtil.fast(this.health, (float)(this.lastTarget.getHealth() / this.lastTarget.getMaxHealth()), 5.0F);
      }
   }

   void renderItems(DrawContext drawContext, int x, int y, PlayerEntity player, float alpha) {
      MatrixStack matrices = drawContext.getMatrices();
      int xoffset = 0;
      matrices.push();
      AnimationUtil.sizeAnimation(drawContext, (double)x, (double)y, 0.38);
      ArrayList<ItemStack> armorItems = new ArrayList<>();
      player.getArmorItems().forEach(armorItems::add);
      Collections.reverse(armorItems);

      for(ItemStack item : armorItems) {
         if (item.getItem() != Items.AIR) {
            ItemRenderUtil.drawItemAlpha(matrices, item, x + xoffset, y - 2, alpha);
            xoffset += 26;
         }
      }

      matrices.pop();
   }

   private Entity getTarget() {
      Aura aura = (Aura)WildClient.INSTANCE.getModuleManager().getByClass(Aura.class);
      TriggerBot triggerBot = (TriggerBot)WildClient.INSTANCE.getModuleManager().getByClass(TriggerBot.class);
      if (MinecraftClient.getInstance().currentScreen instanceof ChatScreen) {
         return MinecraftClient.getInstance().player;
      } else if (aura.isEnabled() && aura.getTarget() != null) {
         return aura.getTarget();
      } else {
         return triggerBot.isEnabled() && triggerBot.getTarget() != null ? triggerBot.getTarget() : null;
      }
   }
}
