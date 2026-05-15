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
      float width = 110.0F;
      float height = 60.0F;
      
      if (this.nameProtect == null) {
         this.nameProtect = (NameProtect)WildClient.INSTANCE.getModuleManager().getByClass(NameProtect.class);
      }

      Matrix4f matrix = drawContext.getMatrices().peek().getPositionMatrix();
      Color white = ColorUtil.setAlpha(this.animation.getOutput(), Color.WHITE);
      Color darkBg = ColorUtil.setAlpha(this.animation.getOutput(), new Color(18, 19, 20, 200));
      Color lightBg = ColorUtil.setAlpha(this.animation.getOutput(), new Color(33, 33, 33, 200));
      
      if (!this.animation.finished(Direction.BACKWARDS)) {
         // Фон с блюром
         BuiltBlur blur = Builder.blur().size(new SizeState(width, height)).radius(new QuadRadiusState(5.0F)).blurRadius((float)(10.0D * this.animation.getOutput())).smoothness((float)(1.0D * this.animation.getOutput())).color(new QuadColorState(Color.white)).build();
         blur.render(matrix, (float)this.draggable.x, (float)this.draggable.y);
         
         // Основной фон
         BuiltRectangle rectangle = Builder.rectangle().size(new SizeState(width, height)).color(new QuadColorState(darkBg)).radius(new QuadRadiusState(5.0F)).smoothness(1.15F).build();
         rectangle.render(matrix, (float)this.draggable.x, (float)this.draggable.y);
         
         // Верхняя панель с именем
         rectangle = Builder.rectangle().size(new SizeState(width - 10, 15.0F)).color(new QuadColorState(lightBg)).radius(new QuadRadiusState(3.0F)).smoothness(1.15F).build();
         rectangle.render(matrix, (float)(this.draggable.x + 5), (float)(this.draggable.y + 5));
         
         // Имя игрока
         BuiltText nameText = Builder.text().font(FontManager.SUISSEINTMEDIUM.get()).text(this.nameProtect.replace(this.lastTarget.getName())).color(white).size(7.0F).thickness(0.05F).build();
         nameText.render(matrix, (float)(this.draggable.x + 10), (float)(this.draggable.y + 10));
         
         // Аватар
         AbstractTexture abstractTexture = MinecraftClient.getInstance().getTextureManager().getTexture(this.lastTarget.getAvatar());
         if (this.lastTarget.isPlayer()) {
            BuiltTexture texture = Builder.texture().size(new SizeState(25.0F, 25.0F)).radius(new QuadRadiusState(3.0F)).texture(0.125F, 0.125F, 0.125F, 0.125F, abstractTexture).color(new QuadColorState(white)).build();
            texture.render(matrix, (float)(this.draggable.x + width / 2 - 12.5F), (float)(this.draggable.y + 23));
         }
         
         // Полоска здоровья
         float barWidth = width - 20;
         float barHeight = 4.0F;
         float barX = this.draggable.x + 10;
         float barY = this.draggable.y + height - 10;
         
         rectangle = Builder.rectangle().size(new SizeState(barWidth, barHeight)).color(new QuadColorState(ColorUtil.setAlpha(this.animation.getOutput(), new Color(50, 50, 50, 255)))).radius(new QuadRadiusState(2.0F)).smoothness(1.15F).build();
         rectangle.render(matrix, barX, barY);
         
         rectangle = Builder.rectangle().size(new SizeState(barWidth * (this.health > 1.0F ? 1.0F : this.health), barHeight)).color(new QuadColorState(white)).radius(new QuadRadiusState(2.0F)).smoothness(1.15F).build();
         rectangle.render(matrix, barX, barY);
         
         // HP текст
         double healthValue = (double)this.health * this.lastTarget.getMaxHealth();
         BuiltText hpText = Builder.text().font(FontManager.SUISSEINTMEDIUM.get()).text(String.format("%.1f HP", healthValue)).color(white).size(6.0F).thickness(0.05F).build();
         hpText.render(matrix, barX, barY - 8);
         
         // WIN/LOSE статус
         if (MinecraftClient.getInstance().player != null) {
            boolean winning = MinecraftClient.getInstance().player.getHealth() >= this.lastTarget.getHealth();
            String status = winning ? "WIN" : "LOSE";
            Color statusColor = ColorUtil.setAlpha(this.animation.getOutput(), winning ? new Color(0, 255, 0) : new Color(255, 0, 0));
            BuiltText statusText = Builder.text().font(FontManager.SUISSEINTMEDIUM.get()).text(status).color(statusColor).size(6.0F).thickness(0.05F).build();
            statusText.render(matrix, barX + barWidth - 20, barY - 8);
         }
         
         this.health = MathUtil.fast(this.health, (float)(this.lastTarget.getHealth() / this.lastTarget.getMaxHealth()), 5.0F);
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
