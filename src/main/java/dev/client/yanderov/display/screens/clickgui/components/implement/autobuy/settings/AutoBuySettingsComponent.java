package dev.client.yanderov.display.screens.clickgui.components.implement.autobuy.settings;

import dev.client.yanderov.display.screens.clickgui.components.AbstractComponent;
import dev.client.yanderov.display.screens.clickgui.components.implement.autobuy.originalitems.ItemRegistry;
import dev.client.yanderov.utils.display.color.ColorAssist;
import dev.client.yanderov.utils.display.font.Fonts;
import dev.client.yanderov.utils.display.shape.ShapeProperties;
import dev.client.yanderov.utils.math.calc.Calculate;
import java.awt.Color;
import net.minecraft.class_332;
import net.minecraft.class_4587;

public abstract class AutoBuySettingsComponent extends AbstractComponent {
   protected final AutoBuyItemSettings settings;

   public AutoBuySettingsComponent(AutoBuyItemSettings settings) {
      this.settings = settings;
      this.height = 15.0F;
   }

   public static class BuyBelowComponent extends AutoBuySettingsComponent {
      private boolean editing = false;
      private String inputText = "";

      public BuyBelowComponent(AutoBuyItemSettings settings) {
         super(settings);
      }

      public void render(class_332 context, int mouseX, int mouseY, float delta) {
         class_4587 matrix = context.method_51448();
         Fonts.getSize(13, Fonts.Type.SEMI).drawString(matrix, "ÐŸÐ¾ÐºÑƒÐ¿Ð°Ñ‚ÑŒ Ð½Ð¸Ð¶Ðµ:", (double)(this.x + 10.0F), (double)(this.y + 10.0F), ColorAssist.getText(0.8F));
         String displayText = this.editing ? this.inputText + "_" : this.settings.getBuyBelow() + "$";
         float textWidth = Fonts.getSize(13, Fonts.Type.DEFAULT).getStringWidth(displayText);
         rectangle.render(ShapeProperties.create(matrix, (double)(this.x + this.width - textWidth - 16.0F), (double)(this.y + 5.0F), (double)(textWidth + 9.0F), (double)12.0F).round(3.0F).thickness(2.5F).outlineColor(this.editing ? (new Color(41, 42, 40, 40)).getRGB() : (new Color(41, 42, 40, 140)).getRGB()).color((new Color(41, 42, 40, 40)).getRGB()).build());
         Fonts.getSize(13, Fonts.Type.DEFAULT).drawString(matrix, displayText, (double)(this.x + this.width - textWidth - 12.0F), (double)(this.y + 10.0F), ColorAssist.getText());
      }

      public boolean mouseClicked(double mouseX, double mouseY, int button) {
         String displayText = this.editing ? this.inputText + "_" : this.settings.getBuyBelow() + "$";
         float textWidth = Fonts.getSize(13, Fonts.Type.DEFAULT).getStringWidth(displayText);
         if (Calculate.isHovered(mouseX, mouseY, (double)(this.x + this.width - textWidth - 20.0F), (double)(this.y + 5.0F), (double)(textWidth + 15.0F), (double)15.0F) && button == 0) {
            this.editing = true;
            this.inputText = String.valueOf(this.settings.getBuyBelow());
            return true;
         } else {
            return super.mouseClicked(mouseX, mouseY, button);
         }
      }

      public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
         if (this.editing) {
            if (keyCode == 257) {
               try {
                  int value = Integer.parseInt(this.inputText);
                  this.settings.setBuyBelow(Math.max(1, value));
                  AutoBuySettingsManager.getInstance().saveSettings(this.settings.getItemName(), this.settings);
                  ItemRegistry.reloadSettings();
               } catch (NumberFormatException var5) {
               }

               this.editing = false;
               return true;
            }

            if (keyCode == 256) {
               this.editing = false;
               return true;
            }

            if (keyCode == 259 && !this.inputText.isEmpty()) {
               this.inputText = this.inputText.substring(0, this.inputText.length() - 1);
               return true;
            }
         }

         return super.keyPressed(keyCode, scanCode, modifiers);
      }

      public boolean charTyped(char chr, int modifiers) {
         if (this.editing && Character.isDigit(chr) && this.inputText.length() < 9) {
            this.inputText = this.inputText + chr;
            return true;
         } else {
            return super.charTyped(chr, modifiers);
         }
      }

      public boolean isHover(double mouseX, double mouseY) {
         return Calculate.isHovered(mouseX, mouseY, (double)this.x, (double)this.y, (double)this.width, (double)this.height);
      }
   }

   public static class MinQuantityComponent extends AutoBuySettingsComponent {
      private boolean editing = false;
      private String inputText = "";

      public MinQuantityComponent(AutoBuyItemSettings settings) {
         super(settings);
      }

      public void render(class_332 context, int mouseX, int mouseY, float delta) {
         class_4587 matrix = context.method_51448();
         Fonts.getSize(13, Fonts.Type.SEMI).drawString(matrix, "ÐšÐ¾Ð»Ð¸Ñ‡ÐµÑÑ‚Ð²Ð¾ Ð¾Ñ‚:", (double)(this.x + 10.0F), (double)(this.y + 8.0F), ColorAssist.getText(0.8F));
         String displayText = this.editing ? this.inputText + "_" : String.valueOf(this.settings.getMinQuantity());
         float textWidth = Fonts.getSize(13, Fonts.Type.DEFAULT).getStringWidth(displayText);
         rectangle.render(ShapeProperties.create(matrix, (double)(this.x + this.width - textWidth - 16.0F), (double)(this.y + 5.0F), (double)(textWidth + 9.0F), (double)12.0F).round(3.0F).thickness(2.5F).outlineColor(this.editing ? (new Color(41, 42, 40, 40)).getRGB() : (new Color(41, 42, 40, 140)).getRGB()).color((new Color(41, 42, 40, 40)).getRGB()).build());
         Fonts.getSize(13, Fonts.Type.DEFAULT).drawString(matrix, displayText, (double)(this.x + this.width - textWidth - 11.5F), (double)(this.y + 10.0F), ColorAssist.getText());
      }

      public boolean mouseClicked(double mouseX, double mouseY, int button) {
         String displayText = this.editing ? this.inputText + "_" : String.valueOf(this.settings.getMinQuantity());
         float textWidth = Fonts.getSize(13, Fonts.Type.DEFAULT).getStringWidth(displayText);
         if (Calculate.isHovered(mouseX, mouseY, (double)(this.x + this.width - textWidth - 20.0F), (double)(this.y + 5.0F), (double)(textWidth + 15.0F), (double)15.0F) && button == 0) {
            this.editing = true;
            this.inputText = String.valueOf(this.settings.getMinQuantity());
            return true;
         } else {
            return super.mouseClicked(mouseX, mouseY, button);
         }
      }

      public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
         if (this.editing) {
            if (keyCode == 257) {
               try {
                  int value = Integer.parseInt(this.inputText);
                  this.settings.setMinQuantity(Math.max(1, Math.min(64, value)));
                  AutoBuySettingsManager.getInstance().saveSettings(this.settings.getItemName(), this.settings);
                  ItemRegistry.reloadSettings();
               } catch (NumberFormatException var5) {
               }

               this.editing = false;
               return true;
            }

            if (keyCode == 256) {
               this.editing = false;
               return true;
            }

            if (keyCode == 259 && !this.inputText.isEmpty()) {
               this.inputText = this.inputText.substring(0, this.inputText.length() - 1);
               return true;
            }
         }

         return super.keyPressed(keyCode, scanCode, modifiers);
      }

      public boolean charTyped(char chr, int modifiers) {
         if (this.editing && Character.isDigit(chr) && this.inputText.length() < 2) {
            this.inputText = this.inputText + chr;
            return true;
         } else {
            return super.charTyped(chr, modifiers);
         }
      }

      public boolean isHover(double mouseX, double mouseY) {
         return Calculate.isHovered(mouseX, mouseY, (double)this.x, (double)this.y, (double)this.width, (double)this.height);
      }
   }
}

