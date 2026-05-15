package fun.Yanderov.display.screens.clickgui.components.implement.other;

import antidaunleak.api.UserProfile;
import com.google.gson.Gson;
import com.mojang.blaze3d.systems.RenderSystem;
import fun.Yanderov.Yanderov;
import fun.Yanderov.common.discord.DiscordManager;
import fun.Yanderov.display.screens.clickgui.MenuScreen;
import fun.Yanderov.display.screens.clickgui.components.AbstractComponent;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.utils.client.managers.file.exception.FileLoadException;
import fun.Yanderov.utils.client.managers.file.exception.FileSaveException;
import fun.Yanderov.utils.display.color.ColorAssist;
import fun.Yanderov.utils.display.font.Fonts;
import fun.Yanderov.utils.display.geometry.Render2D;
import fun.Yanderov.utils.display.scissor.ScissorAssist;
import fun.Yanderov.utils.display.shape.ShapeProperties;
import fun.Yanderov.utils.math.calc.Calculate;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_3532;
import net.minecraft.class_4587;
import org.joml.Matrix4f;

public class BackgroundComponent extends AbstractComponent {
   private String editingConfig = null;
   private String newName = "";
   private int editCursor = 0;
   private boolean isDefaultTab = true;
   private float highlightX = 55.0F;
   private List configs = new ArrayList();
   private String configInput = "";
   private boolean editingInput = false;
   private int inputCursor = 0;
   private float scroll = 0.0F;
   private float smoothedScroll = 0.0F;
   private boolean loadedConfigs = false;
   private boolean cloudLoading = false;
   private long cloudLoadStartTime = 0L;
   private boolean cloudDataReady = false;
   private List tempConfigs = new ArrayList();
   private float loadingAlpha = 0.0F;
   private float configsAlpha = 0.0F;

   public void render(class_332 context, int mouseX, int mouseY, float delta) {
      class_4587 matrix = context.method_51448();
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
      String currentTime = LocalTime.now().format(formatter);
      String point = " â€¢ ";
      DiscordManager discord = Yanderov.getInstance().getDiscordManager();
      Yanderov.getInstance().getScissorManager().push(matrix.method_23760().method_23761(), 0.0F, 0.0F, (float)window.method_4486(), (float)window.method_4502());
      blur.render(ShapeProperties.create(matrix, (double)this.x, (double)this.y, (double)this.width, (double)this.height).round(8.0F).quality(64.0F).color((new Color(0, 0, 0, 200)).getRGB()).build());
      rectangle.render(ShapeProperties.create(matrix, (double)this.x, (double)this.y, (double)this.width, (double)this.height).round(8.0F).softness(22.0F).thickness(0.1F).outlineColor((new Color(18, 19, 20, 225)).getRGB()).color((new Color(18, 19, 20, 175)).getRGB(), (new Color(0, 2, 5, 175)).getRGB(), (new Color(0, 2, 5, 175)).getRGB(), (new Color(18, 19, 20, 175)).getRGB()).build());
      List<Map<String, Object>> displayedConfigs = new ArrayList();
      if (MenuScreen.INSTANCE.getCategory() == ModuleCategory.CONFIGS) {
         if (!this.loadedConfigs) {
            this.refreshConfigs();
            this.loadedConfigs = true;
         }

         if (this.cloudLoading && !this.isDefaultTab) {
            if (this.cloudDataReady && System.currentTimeMillis() - this.cloudLoadStartTime >= 3000L) {
               this.configs = this.tempConfigs;
               this.cloudLoading = false;
            }

            this.loadingAlpha = Calculate.interpolate(this.loadingAlpha, 1.0F, 0.1F);
            this.configsAlpha = Calculate.interpolate(this.configsAlpha, 0.0F, 0.1F);
         } else {
            this.loadingAlpha = Calculate.interpolate(this.loadingAlpha, 0.0F, 0.1F);
            this.configsAlpha = Calculate.interpolate(this.configsAlpha, 1.0F, 0.1F);
         }

         rectangle.render(ShapeProperties.create(context.method_51448(), (double)(this.x + 55.0F), (double)(this.y + 38.0F), (double)70.0F, (double)15.0F).round(3.0F).thickness(2.0F).softness(1.0F).outlineColor((new Color(54, 54, 56, 255)).getRGB()).color((new Color(31, 27, 35, 75)).getRGB(), (new Color(31, 27, 35, 75)).getRGB(), (new Color(31, 27, 35, 75)).getRGB(), (new Color(31, 27, 35, 75)).getRGB()).build());
         float targetX = this.isDefaultTab ? 55.0F : 90.0F;
         this.highlightX = Calculate.interpolate(this.highlightX, targetX, 0.2F);
         rectangle.render(ShapeProperties.create(context.method_51448(), (double)(this.x + this.highlightX), (double)(this.y + 38.0F), (double)35.0F, (double)15.0F).round(3.0F).thickness(0.0F).softness(0.0F).outlineColor((new Color(54, 54, 56, 0)).getRGB()).color((new Color(65, 65, 65, 255)).getRGB(), (new Color(65, 65, 65, 255)).getRGB(), (new Color(65, 65, 65, 255)).getRGB(), (new Color(65, 65, 65, 255)).getRGB()).build());
         rectangle.render(ShapeProperties.create(context.method_51448(), (double)(this.x + 43.0F), (double)(this.y + 60.0F), (double)(this.width - 43.0F), (double)0.5F).color((new Color(55, 55, 70, 250)).getRGB(), (new Color(55, 55, 70, 15)).getRGB(), (new Color(55, 55, 70, 250)).getRGB(), (new Color(55, 55, 70, 15)).getRGB()).build());
         Fonts.getSize(16, Fonts.Type.DEFAULT).drawString(matrix, "Default", (double)(this.x + 60.0F), (double)(this.y + 43.0F), ColorAssist.getText(0.7F));
         Fonts.getSize(16, Fonts.Type.DEFAULT).drawString(matrix, "Cloud", (double)(this.x + 97.0F), (double)(this.y + 43.0F), ColorAssist.getText(0.7F));
         blur.render(ShapeProperties.create(context.method_51448(), (double)(this.x + 340.0F), (double)(this.y + 38.0F), (double)80.0F, (double)15.0F).round(3.0F).quality(64.0F).color((new Color(0, 0, 0, 200)).getRGB()).build());
         rectangle.render(ShapeProperties.create(context.method_51448(), (double)(this.x + 340.0F), (double)(this.y + 38.0F), (double)80.0F, (double)15.0F).round(3.0F).softness(2.0F).thickness(0.5F).outlineColor((new Color(18, 19, 20, 225)).getRGB()).color((new Color(18, 19, 20, 175)).getRGB(), (new Color(0, 2, 5, 175)).getRGB(), (new Color(0, 2, 5, 175)).getRGB(), (new Color(18, 19, 20, 175)).getRGB()).build());
         blur.render(ShapeProperties.create(context.method_51448(), (double)(this.x + 292.0F), (double)(this.y + 38.0F), (double)40.0F, (double)15.0F).round(3.0F).quality(64.0F).color((new Color(0, 0, 0, 200)).getRGB()).build());
         rectangle.render(ShapeProperties.create(context.method_51448(), (double)(this.x + 292.0F), (double)(this.y + 38.0F), (double)40.0F, (double)15.0F).round(3.0F).softness(2.0F).thickness(0.5F).outlineColor((new Color(18, 19, 20, 225)).getRGB()).color((new Color(18, 19, 20, 175)).getRGB(), (new Color(0, 2, 5, 175)).getRGB(), (new Color(0, 2, 5, 175)).getRGB(), (new Color(18, 19, 20, 175)).getRGB()).build());
         rectangle.render(ShapeProperties.create(matrix, (double)(this.x + 405.0F), (double)(this.y + 42.0F), (double)0.5F, (double)7.0F).color((new Color(155, 155, 155, 55)).getRGB()).build());
         Fonts.getSize(20, Fonts.Type.GUIICONS).drawString(matrix, "M", (double)(this.x + 296.0F), (double)(this.y + 42.0F), ColorAssist.getText(1.0F));
         Fonts.getSize(16, Fonts.Type.REGULAR).drawString(matrix, "Save", (double)(this.x + 307.0F), (double)(this.y + 43.5F), ColorAssist.getText(1.0F));
         blur.render(ShapeProperties.create(context.method_51448(), (double)(this.x + 250.0F), (double)(this.y + 38.0F), (double)38.0F, (double)15.0F).round(3.0F).quality(64.0F).color((new Color(0, 0, 0, 200)).getRGB()).build());
         rectangle.render(ShapeProperties.create(context.method_51448(), (double)(this.x + 250.0F), (double)(this.y + 38.0F), (double)38.0F, (double)15.0F).round(3.0F).softness(2.0F).thickness(0.5F).outlineColor((new Color(18, 19, 20, 225)).getRGB()).color((new Color(18, 19, 20, 175)).getRGB(), (new Color(0, 2, 5, 175)).getRGB(), (new Color(0, 2, 5, 175)).getRGB(), (new Color(18, 19, 20, 175)).getRGB()).build());
         Fonts.getSize(21, Fonts.Type.GUIICONS).drawString(matrix, "O", (double)(this.x + 253.0F), (double)(this.y + 43.0F), ColorAssist.getText(1.0F));
         Fonts.getSize(16, Fonts.Type.REGULAR).drawString(matrix, "Clear", (double)(this.x + 263.0F), (double)(this.y + 43.5F), ColorAssist.getText(1.0F));
         String placeholder = this.isDefaultTab ? "ÐŸÐ¾Ð¸ÑÐº" : "Ð”Ð¾Ð±Ð°Ð²Ð¸Ñ‚ÑŒ Ð¿Ð¾ ID";
         String inputDisplay = this.configInput.isEmpty() && !this.editingInput ? placeholder : this.configInput;
         Fonts.getSize(15, Fonts.Type.REGULAR).drawString(matrix, inputDisplay, (double)(this.x + 343.0F), (double)(this.y + 43.5F), ColorAssist.getText(0.6F));
         Fonts.getSize(26, Fonts.Type.ICONS).drawString(matrix, "U", (double)(this.x + 405.0F), (double)(this.y + 41.0F), ColorAssist.getText(0.6F));
         if (this.editingInput && System.currentTimeMillis() % 1000L < 500L) {
            float curWidth = Fonts.getSize(15, Fonts.Type.REGULAR).getStringWidth(this.configInput.substring(0, this.inputCursor));
            Fonts.getSize(15, Fonts.Type.DEFAULT).drawString(matrix, "|", (double)(this.x + 342.0F + curWidth), (double)(this.y + 43.5F - 0.5F), ColorAssist.getText(0.7F));
         }

         displayedConfigs = this.isDefaultTab ? (List)this.configs.stream().filter((m) -> ((String)m.get("name")).toLowerCase().contains(this.configInput.toLowerCase())).collect(Collectors.toList()) : this.configs;
         int configsPerRow = 2;
         int numConfigs = displayedConfigs.size();
         int rows = (numConfigs + configsPerRow - 1) / configsPerRow;
         float contentHeight = numConfigs > 0 ? 50.0F + (float)(rows - 1) * 55.0F : 0.0F;
         float viewHeight = this.height - 70.0F;
         float maxScrollAmount = Math.max(0.0F, contentHeight - viewHeight) + 7.0F;
         if (numConfigs < 7) {
            maxScrollAmount = 0.0F;
            this.scroll = 0.0F;
            this.smoothedScroll = 0.0F;
         }

         this.scroll = class_3532.method_15363(this.scroll, -maxScrollAmount, 0.0F);
         this.smoothedScroll = Calculate.interpolate(this.smoothedScroll, this.scroll, 0.2F);
         Matrix4f positionMatrix = matrix.method_23760().method_23761();
         ScissorAssist scissorManager = Yanderov.getInstance().getScissorManager();
         float listX = this.x + 43.0F;
         float listY = this.y + 65.0F;
         float listWidth = this.width - 43.0F - 15.0F;
         scissorManager.push(positionMatrix, listX, listY, listWidth, viewHeight);
         if (!this.isDefaultTab) {
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, this.configsAlpha);
         }

         float configY = this.y + 70.0F + this.smoothedScroll;
         int index = 0;

         for(Map map : displayedConfigs) {
            String config = (String)map.get("name");
            float configX = this.x + 55.0F + (float)(index % configsPerRow * 190);
            if (index % configsPerRow == 0 && index > 0) {
               configY += 55.0F;
            }

            if (configY + 50.0F > this.y + 60.0F && configY < this.y + this.height) {
               blur.render(ShapeProperties.create(matrix, (double)configX, (double)configY, (double)180.0F, (double)50.0F).round(5.0F).quality(64.0F).color((new Color(0, 0, 0, 200)).getRGB()).build());
               rectangle.render(ShapeProperties.create(matrix, (double)configX, (double)configY, (double)180.0F, (double)50.0F).round(5.0F).softness(2.0F).thickness(0.1F).outlineColor((new Color(28, 29, 30, 225)).getRGB()).color((new Color(18, 19, 20, 175)).getRGB(), (new Color(0, 2, 5, 175)).getRGB(), (new Color(0, 2, 5, 175)).getRGB(), (new Color(18, 19, 20, 175)).getRGB()).build());
               rectangle.render(ShapeProperties.create(context.method_51448(), (double)configX, (double)(configY + 22.0F), (double)180.0F, (double)0.5F).color((new Color(55, 55, 70, 250)).getRGB(), (new Color(55, 55, 70, 15)).getRGB(), (new Color(55, 55, 70, 250)).getRGB(), (new Color(55, 55, 70, 15)).getRGB()).build());
               rectangle.render(ShapeProperties.create(context.method_51448(), (double)configX, (double)configY, (double)20.5F, (double)19.0F).round(1.0F, 7.0F, 4.0F, 1.0F).thickness(2.0F).softness(1.0F).outlineColor((new Color(54, 54, 56, 255)).getRGB()).color((new Color(55, 55, 55, 255)).getRGB(), (new Color(55, 55, 55, 255)).getRGB(), (new Color(55, 55, 55, 255)).getRGB(), (new Color(55, 55, 55, 255)).getRGB()).build());
               Fonts.getSize(26, Fonts.Type.ICONSCATEGORY).drawString(matrix, "F", (double)(configX + 3.5F), (double)(configY + 5.0F), ColorAssist.getText());
               if (config.equals(this.editingConfig)) {
                  String displayName = this.newName;
                  Fonts.getSize(16, Fonts.Type.DEFAULT).drawString(matrix, displayName, (double)(configX + 25.0F), (double)(configY + 9.0F), ColorAssist.getText());
                  if (System.currentTimeMillis() % 1000L < 500L) {
                     float curWidth = Fonts.getSize(16, Fonts.Type.DEFAULT).getStringWidth(this.newName.substring(0, this.editCursor));
                     Fonts.getSize(16, Fonts.Type.DEFAULT).drawString(matrix, "|", (double)(configX + 24.0F + curWidth), (double)(configY + 9.0F - 0.5F), ColorAssist.getText());
                  }
               } else {
                  Fonts.getSize(16, Fonts.Type.DEFAULT).drawString(matrix, config, (double)(configX + 25.0F), (double)(configY + 9.0F), ColorAssist.getText());
               }

               Number createdNum = (Number)map.getOrDefault("created", 0L);
               Number updatedNum = (Number)map.getOrDefault("updated", 0L);
               long createdTime = createdNum.longValue();
               long updatedTime = updatedNum.longValue();
               String createdStr = createdTime == 0L ? "unknown" : LocalDateTime.ofInstant(Instant.ofEpochMilli(createdTime), ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
               String updatedStr = updatedTime == 0L ? "unknown" : LocalDateTime.ofInstant(Instant.ofEpochMilli(updatedTime), ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
               Fonts.getSize(11, Fonts.Type.REGULAR).drawString(matrix, "Created: " + createdStr, (double)(configX + 4.0F), (double)(configY + 35.0F), ColorAssist.getText(1.0F));
               String updateText = "Updated: " + updatedStr;
               boolean hasUpdate = (Boolean)map.getOrDefault("has_update", false);
               if (hasUpdate) {
                  updateText = updateText + " (Ð´Ð¾ÑÑ‚ÑƒÐ¿Ð½Ð¾ Ð¾Ð±Ð½Ð¾Ð²Ð»ÐµÐ½Ð¸Ð¹: 1)";
               }

               Fonts.getSize(11, Fonts.Type.REGULAR).drawString(matrix, updateText, (double)(configX + 4.0F), (double)(configY + 28.0F), ColorAssist.getText(1.0F));
               String author = (String)map.getOrDefault("owner", this.isDefaultTab ? UserProfile.getInstance().profile("username") : "Unknown");
               Fonts.getSize(11, Fonts.Type.REGULAR).drawString(matrix, "Author: " + author, (double)(configX + 4.0F), (double)(configY + 42.0F), ColorAssist.getText(1.0F));
               Object avatarObj = map.getOrDefault("avatar_hash", discord.getAvatarId());
               String avatarHash;
               if (avatarObj instanceof Map) {
                  Map<String, String> idMap = (Map)avatarObj;
                  String var10000 = (String)idMap.get("namespace");
                  avatarHash = var10000 + ":" + (String)idMap.get("path");
               } else {
                  avatarHash = avatarObj.toString();
               }

               Render2D.drawTexture(context, class_2960.method_60654(avatarHash), configX + 157.0F, configY + 3.0F, 16.0F, 7.5F, 0, 15, 21, ColorAssist.getGuiRectColor(1.0F));
               blur.render(ShapeProperties.create(context.method_51448(), (double)(configX + 162.0F), (double)(configY + 35.0F), (double)14.0F, (double)15.0F).round(3.0F, 0.0F, 3.0F, 0.0F).quality(64.0F).color((new Color(0, 0, 0, 200)).getRGB()).build());
               rectangle.render(ShapeProperties.create(context.method_51448(), (double)(configX + 162.0F), (double)(configY + 35.0F), (double)14.0F, (double)15.0F).round(3.0F, 0.0F, 3.0F, 0.0F).softness(2.0F).thickness(0.1F).outlineColor((new Color(18, 19, 20, 225)).getRGB()).color((new Color(28, 29, 30, 175)).getRGB(), (new Color(0, 2, 5, 175)).getRGB(), (new Color(0, 2, 5, 175)).getRGB(), (new Color(28, 29, 30, 175)).getRGB()).build());
               blur.render(ShapeProperties.create(context.method_51448(), (double)(configX + 146.0F), (double)(configY + 35.0F), (double)14.0F, (double)15.0F).round(3.0F, 0.0F, 3.0F, 0.0F).quality(64.0F).color((new Color(0, 0, 0, 200)).getRGB()).build());
               rectangle.render(ShapeProperties.create(context.method_51448(), (double)(configX + 146.0F), (double)(configY + 35.0F), (double)14.0F, (double)15.0F).round(3.0F, 0.0F, 3.0F, 0.0F).softness(2.0F).thickness(0.1F).outlineColor((new Color(18, 19, 20, 225)).getRGB()).color((new Color(28, 29, 30, 175)).getRGB(), (new Color(0, 2, 5, 175)).getRGB(), (new Color(0, 2, 5, 175)).getRGB(), (new Color(28, 29, 30, 175)).getRGB()).build());
               blur.render(ShapeProperties.create(context.method_51448(), (double)(configX + 130.25F), (double)(configY + 35.0F), (double)14.0F, (double)15.0F).round(3.0F, 0.0F, 3.0F, 0.0F).quality(64.0F).color((new Color(0, 0, 0, 200)).getRGB()).build());
               rectangle.render(ShapeProperties.create(context.method_51448(), (double)(configX + 130.25F), (double)(configY + 35.0F), (double)14.0F, (double)15.0F).round(3.0F, 0.0F, 3.0F, 0.0F).softness(2.0F).thickness(0.1F).outlineColor((new Color(18, 19, 20, 225)).getRGB()).color((new Color(28, 29, 30, 175)).getRGB(), (new Color(0, 2, 5, 175)).getRGB(), (new Color(0, 2, 5, 175)).getRGB(), (new Color(28, 29, 30, 175)).getRGB()).build());
               blur.render(ShapeProperties.create(context.method_51448(), (double)(configX + 114.35F), (double)(configY + 35.0F), (double)14.0F, (double)15.0F).round(3.0F, 0.0F, 3.0F, 0.0F).quality(64.0F).color((new Color(0, 0, 0, 200)).getRGB()).build());
               rectangle.render(ShapeProperties.create(context.method_51448(), (double)(configX + 114.35F), (double)(configY + 35.0F), (double)14.0F, (double)15.0F).round(3.0F, 0.0F, 3.0F, 0.0F).softness(2.0F).thickness(0.1F).outlineColor((new Color(18, 19, 20, 225)).getRGB()).color((new Color(28, 29, 30, 175)).getRGB(), (new Color(0, 2, 5, 175)).getRGB(), (new Color(0, 2, 5, 175)).getRGB(), (new Color(28, 29, 30, 175)).getRGB()).build());
               Fonts.getSize(31, Fonts.Type.GUIICONS).drawString(matrix, "P", (double)(configX + 164.0F), (double)(configY + 36.0F), ColorAssist.getText(1.0F));
               Fonts.getSize(21, Fonts.Type.GUIICONS).drawString(matrix, "N", (double)(configX + 149.0F), (double)(configY + 39.5F), ColorAssist.getText(1.0F));
               Fonts.getSize(22, Fonts.Type.GUIICONS).drawString(matrix, "M", (double)(configX + 133.0F), (double)(configY + 38.5F), ColorAssist.getText(1.0F));
               Fonts.getSize(24, Fonts.Type.GUIICONS).drawString(matrix, "O", (double)(configX + 117.0F), (double)(configY + 38.0F), ColorAssist.getText(1.0F));
            }

            ++index;
         }

         if (!this.isDefaultTab) {
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
         }

         scissorManager.pop();
         if (maxScrollAmount > 0.0F) {
            float scrollbarWidth = 4.0F;
            float scrollbarX = this.x + this.width - 10.0F;
            float scrollbarY = this.y + 65.0F;
            float scrollbarHeight = this.height - 70.0F;
            rectangle.render(ShapeProperties.create(context.method_51448(), (double)scrollbarX, (double)scrollbarY, (double)scrollbarWidth, (double)scrollbarHeight).round(2.0F).color((new Color(30, 30, 30, 100)).getRGB()).build());
            float handleHeight = Math.max(20.0F, scrollbarHeight * (viewHeight / contentHeight));
            float scrollRatio = maxScrollAmount > 0.0F ? -this.smoothedScroll / maxScrollAmount : 0.0F;
            float handleY = scrollbarY + (scrollbarHeight - handleHeight) * scrollRatio;
            rectangle.render(ShapeProperties.create(context.method_51448(), (double)scrollbarX, (double)handleY, (double)scrollbarWidth, (double)handleHeight).round(2.0F).color((new Color(100, 100, 100, 150)).getRGB()).build());
         }
      } else {
         this.loadedConfigs = false;
      }

      rectangle.render(ShapeProperties.create(context.method_51448(), (double)(this.x + 42.5F), (double)this.y, (double)0.5F, (double)this.height).color((new Color(55, 55, 70, 15)).getRGB(), (new Color(55, 55, 70, 50)).getRGB(), (new Color(55, 55, 70, 15)).getRGB(), (new Color(55, 55, 70, 250)).getRGB()).build());
      rectangle.render(ShapeProperties.create(context.method_51448(), (double)(this.x + 43.0F), (double)(this.y + 28.0F), (double)(this.width - 43.0F), (double)0.5F).color((new Color(55, 55, 70, 250)).getRGB(), (new Color(55, 55, 70, 15)).getRGB(), (new Color(55, 55, 70, 250)).getRGB(), (new Color(55, 55, 70, 15)).getRGB()).build());
      blur.render(ShapeProperties.create(matrix, (double)(this.x + 10.5F), (double)(this.y + 10.0F), (double)20.0F, (double)20.0F).round(5.0F).quality(64.0F).color((new Color(0, 0, 0, 200)).getRGB()).build());
      rectangle.render(ShapeProperties.create(matrix, (double)(this.x + 10.5F), (double)(this.y + 10.0F), (double)20.0F, (double)20.0F).round(5.0F).softness(22.0F).thickness(0.1F).outlineColor((new Color(18, 19, 20, 225)).getRGB()).color((new Color(18, 19, 20, 175)).getRGB(), (new Color(0, 2, 5, 175)).getRGB(), (new Color(0, 2, 5, 175)).getRGB(), (new Color(18, 19, 20, 175)).getRGB()).build());
      Fonts.getSize(26, Fonts.Type.ICONS).drawString(matrix, "A ", (double)(this.x + 14.0F), (double)(this.y + 15.0F), (new Color(225, 225, 255, 255)).getRGB());
      switch (MenuScreen.INSTANCE.getCategory()) {
         case COMBAT:
            String icon = "A";
            Fonts.getSize(17, Fonts.Type.ICONSCATEGORY).drawString(matrix, icon, (double)(this.x + 55.0F), (double)(this.y + 14.5F), (new Color(225, 225, 255, 255)).getRGB());
            break;
         case MOVEMENT:
            String icon = "B";
            Fonts.getSize(18, Fonts.Type.ICONSCATEGORY).drawString(matrix, icon, (double)(this.x + 54.0F), (double)(this.y + 14.0F), (new Color(225, 225, 255, 255)).getRGB());
            break;
         case RENDER:
            String icon = "C";
            Fonts.getSize(17, Fonts.Type.ICONSCATEGORY).drawString(matrix, icon, (double)(this.x + 54.0F), (double)(this.y + 14.0F), (new Color(225, 225, 255, 255)).getRGB());
            break;
         case PLAYER:
            String icon = "D";
            Fonts.getSize(17, Fonts.Type.ICONSCATEGORY).drawString(matrix, icon, (double)(this.x + 54.0F), (double)(this.y + 14.0F), (new Color(225, 225, 255, 255)).getRGB());
            break;
         case MISC:
            String icon = "E";
            Fonts.getSize(18, Fonts.Type.ICONSCATEGORY).drawString(matrix, icon, (double)(this.x + 54.0F), (double)(this.y + 14.0F), (new Color(225, 225, 255, 255)).getRGB());
            break;
         case CONFIGS:
            String icon = "F";
            Fonts.getSize(17, Fonts.Type.ICONSCATEGORY).drawString(matrix, icon, (double)(this.x + 54.0F), (double)(this.y + 14.0F), (new Color(225, 225, 255, 255)).getRGB());
            break;
         case AUTOBUY:
            String icon = "H";
            Fonts.getSize(33, Fonts.Type.ICONSCATEGORY).drawString(matrix, icon, (double)(this.x + 54.0F), (double)(this.y + 9.0F), (new Color(225, 225, 255, 255)).getRGB());
            break;
         default:
            String icon = MenuScreen.INSTANCE.getCategory().getReadableName().substring(0, 1);
            Fonts.getSize(21, Fonts.Type.ICONSCATEGORY).drawString(matrix, icon, (double)(this.x + 50.0F), (double)(this.y + 13.5F), (new Color(225, 225, 255, 255)).getRGB());
      }

      if (MenuScreen.INSTANCE.getCategory() == ModuleCategory.CONFIGS && displayedConfigs.isEmpty()) {
         String message = "Ð¢ÑƒÑ‚Ð° Ð¿ÑƒÑÑ‚Ð° :(";
         float textAlpha = 0.7F;
         if (!this.isDefaultTab && this.cloudLoading) {
            long time = System.currentTimeMillis() - this.cloudLoadStartTime;
            int dotCount = (int)(time / 500L % 3L) + 1;
            message = "Loading" + ".".repeat(dotCount);
            textAlpha = this.loadingAlpha;
         } else if (!this.cloudLoading) {
            textAlpha = this.configsAlpha;
         }

         Fonts.getSize(20, Fonts.Type.DEFAULT).drawString(matrix, message, (double)(this.x + this.width / 2.0F - Fonts.getSize(20, Fonts.Type.DEFAULT).getStringWidth(message) / 2.0F + 10.0F), (double)(this.y + this.height / 2.0F + 15.0F), ColorAssist.getText(textAlpha));
      }

      if (MenuScreen.INSTANCE.getCategory() == ModuleCategory.CONFIGS) {
         Fonts.getSize(15, Fonts.Type.DEFAULT).drawString(matrix, point + MenuScreen.INSTANCE.getCategory().getReadableName() + " | Beta", (double)(this.x + 63.0F), (double)(this.y + 13.5F), (new Color(245, 245, 255, 255)).getRGB());
      } else {
         Fonts.getSize(15, Fonts.Type.DEFAULT).drawString(matrix, point + MenuScreen.INSTANCE.getCategory().getReadableName(), (double)(this.x + 63.0F), (double)(this.y + 13.5F), (new Color(245, 245, 255, 255)).getRGB());
      }

      Yanderov.getInstance().getScissorManager().pop();
   }

   public boolean mouseClicked(double mouseX, double mouseY, int button) {
      if (button == 0) {
         this.editingInput = false;
      }

      if (MenuScreen.INSTANCE.getCategory() == ModuleCategory.CONFIGS && button == 0) {
         if (Calculate.isHovered(mouseX, mouseY, (double)(this.x + 55.0F), (double)(this.y + 38.0F), (double)35.0F, (double)15.0F)) {
            this.isDefaultTab = true;
            this.loadingAlpha = 0.0F;
            this.configsAlpha = 1.0F;
            this.refreshConfigs();
            return true;
         }

         if (Calculate.isHovered(mouseX, mouseY, (double)(this.x + 90.0F), (double)(this.y + 38.0F), (double)35.0F, (double)15.0F)) {
            this.isDefaultTab = false;
            this.loadingAlpha = 0.0F;
            this.configsAlpha = 0.0F;
            this.refreshConfigs();
            return true;
         }

         if (Calculate.isHovered(mouseX, mouseY, (double)(this.x + 340.0F), (double)(this.y + 38.0F), (double)80.0F, (double)15.0F)) {
            this.editingInput = true;
            this.inputCursor = this.configInput.length();
            return true;
         }

         if (Calculate.isHovered(mouseX, mouseY, (double)(this.x + 292.0F), (double)(this.y + 38.0F), (double)40.0F, (double)15.0F)) {
            if (this.isDefaultTab) {
               this.createDefaultConfig();
            } else {
               this.createCloudConfig();
            }

            this.refreshConfigs();
            return true;
         }

         if (Calculate.isHovered(mouseX, mouseY, (double)(this.x + 250.0F), (double)(this.y + 38.0F), (double)38.0F, (double)15.0F)) {
            this.clearAllConfigs();
            this.refreshConfigs();
            return true;
         }

         List<Map<String, Object>> displayedConfigs = this.isDefaultTab ? (List)this.configs.stream().filter((m) -> ((String)m.get("name")).toLowerCase().contains(this.configInput.toLowerCase())).collect(Collectors.toList()) : this.configs;
         float configY = this.y + 70.0F + this.smoothedScroll;
         int index = 0;

         for(Map map : displayedConfigs) {
            String config = (String)map.get("name");
            float configX = this.x + 55.0F + (float)(index % 2 * 190);
            if (index % 2 == 0 && index > 0) {
               configY += 55.0F;
            }

            double nameX = (double)(configX + 25.0F);
            double nameY = (double)(configY + 9.0F);
            double nameWidth = (double)Fonts.getSize(16, Fonts.Type.DEFAULT).getStringWidth(config);
            double nameHeight = (double)10.0F;
            if (Calculate.isHovered(mouseX, mouseY, nameX, nameY - (double)2.0F, nameWidth, nameHeight)) {
               if (this.isDefaultTab) {
                  this.editingConfig = config;
                  this.newName = config;
                  this.editCursor = this.newName.length();
               } else {
                  class_310.method_1551().field_1774.method_1455(config);
               }

               return true;
            }

            if (Calculate.isHovered(mouseX, mouseY, (double)(configX + 162.0F), (double)(configY + 35.0F), (double)14.0F, (double)15.0F)) {
               if (this.isDefaultTab) {
                  try {
                     File dir = new File(Yanderov.getInstance().getClientInfoProvider().clientDir(), "Custom");
                     File configFile = new File(dir, config + ".json");
                     String json = new String(Files.readAllBytes(configFile.toPath()));
                     File tempFile = new File(Yanderov.getInstance().getClientInfoProvider().configsDir(), "temp.json");
                     Files.write(tempFile.toPath(), json.getBytes(), new OpenOption[0]);
                     Yanderov.getInstance().getFileController().loadFile("temp.json");
                     tempFile.delete();
                  } catch (FileLoadException | IOException var25) {
                  }
               } else {
                  this.loadCloudConfig(config);
                  this.isDefaultTab = true;
                  this.refreshConfigs();
               }

               return true;
            }

            if (Calculate.isHovered(mouseX, mouseY, (double)(configX + 146.0F), (double)(configY + 35.0F), (double)14.0F, (double)15.0F)) {
               if (this.isDefaultTab) {
                  try {
                     File dir = new File(Yanderov.getInstance().getClientInfoProvider().clientDir(), "Custom");
                     File configFile = new File(dir, config + ".json");
                     String json = new String(Files.readAllBytes(configFile.toPath()));
                     File tempFile = new File(Yanderov.getInstance().getClientInfoProvider().configsDir(), "temp.json");
                     Files.write(tempFile.toPath(), json.getBytes(), new OpenOption[0]);
                     Yanderov.getInstance().getFileController().loadFile("temp.json");
                     tempFile.delete();
                  } catch (FileLoadException | IOException var26) {
                  }
               } else {
                  this.loadCloudConfig(config);
                  this.isDefaultTab = true;
                  this.refreshConfigs();
               }

               return true;
            }

            if (Calculate.isHovered(mouseX, mouseY, (double)configX + (double)130.25F, (double)(configY + 35.0F), (double)14.0F, (double)15.0F)) {
               if (this.isDefaultTab) {
                  String cloudId = (String)map.get("cloud_id");
                  if (cloudId != null) {
                     this.updateFromCloud(config, cloudId);
                  } else {
                     try {
                        File dir = new File(Yanderov.getInstance().getClientInfoProvider().clientDir(), "Custom");
                        File configFile = new File(dir, config + ".json");
                        String json = this.getCurrentConfigJson();
                        Files.write(configFile.toPath(), json.getBytes(), new OpenOption[0]);
                     } catch (IOException var27) {
                     }
                  }
               } else {
                  this.saveCloudConfig(config);
               }

               this.refreshConfigs();
               return true;
            }

            if (Calculate.isHovered(mouseX, mouseY, (double)configX + 114.35, (double)(configY + 35.0F), (double)14.0F, (double)15.0F)) {
               if (this.isDefaultTab) {
                  File dir = new File(Yanderov.getInstance().getClientInfoProvider().clientDir(), "Custom");
                  File file = new File(dir, config + ".json");
                  file.delete();
               } else {
                  this.removeCloudConfig(config);
               }

               this.refreshConfigs();
               return true;
            }

            ++index;
         }
      } else {
         this.editingConfig = null;
         this.editingInput = false;
      }

      return super.mouseClicked(mouseX, mouseY, button);
   }

   public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
      if (MenuScreen.INSTANCE.getCategory() == ModuleCategory.CONFIGS && Calculate.isHovered(mouseX, mouseY, (double)(this.x + 43.0F), (double)(this.y + 65.0F), (double)(this.width - 43.0F - 15.0F), (double)(this.height - 70.0F))) {
         this.scroll = (float)((double)this.scroll + amount * (double)20.0F);
         return true;
      } else {
         return super.mouseScrolled(mouseX, mouseY, amount);
      }
   }

   public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
      if (this.editingInput) {
         if (keyCode == 257) {
            if (!this.isDefaultTab && this.configInput.length() == 8 && this.configInput.matches("\\d+")) {
               this.loadCloudConfig(this.configInput);
               this.isDefaultTab = true;
            }

            this.configInput = "";
            this.inputCursor = 0;
            this.editingInput = false;
            this.refreshConfigs();
            return true;
         }

         if (keyCode == 256) {
            this.configInput = "";
            this.inputCursor = 0;
            this.editingInput = false;
            return true;
         }

         if (keyCode == 259) {
            if (this.inputCursor > 0) {
               String var10001 = this.configInput.substring(0, this.inputCursor - 1);
               this.configInput = var10001 + this.configInput.substring(this.inputCursor);
               --this.inputCursor;
            }

            return true;
         }

         if (keyCode == 263) {
            if (this.inputCursor > 0) {
               --this.inputCursor;
            }

            return true;
         }

         if (keyCode == 262) {
            if (this.inputCursor < this.configInput.length()) {
               ++this.inputCursor;
            }

            return true;
         }

         if (keyCode == 86 && (modifiers & 2) != 0) {
            String clip = class_310.method_1551().field_1774.method_1460().trim();
            if (!this.isDefaultTab) {
               clip = clip.replaceAll("\\D", "");
               int avail = 8 - this.configInput.length();
               if (clip.length() > avail) {
                  clip = clip.substring(0, avail);
               }
            } else {
               int avail = 15 - this.configInput.length();
               if (clip.length() > avail) {
                  clip = clip.substring(0, avail);
               }
            }

            this.configInput = this.configInput.substring(0, this.inputCursor) + clip + this.configInput.substring(this.inputCursor);
            this.inputCursor += clip.length();
            return true;
         }
      } else if (this.editingConfig != null) {
         if (keyCode == 257) {
            if (this.isDefaultTab) {
               File dir = new File(Yanderov.getInstance().getClientInfoProvider().clientDir(), "Custom");
               File oldFile = new File(dir, this.editingConfig + ".json");
               File newFile = new File(dir, this.newName + ".json");
               if (!this.newName.isEmpty() && this.newName.length() <= 15 && oldFile.exists() && (!newFile.exists() || this.newName.equals(this.editingConfig))) {
                  oldFile.renameTo(newFile);
               }
            }

            this.editingConfig = null;
            this.refreshConfigs();
            return true;
         }

         if (keyCode == 256) {
            this.editingConfig = null;
            return true;
         }

         if (keyCode == 259) {
            if (this.editCursor > 0) {
               String var12 = this.newName.substring(0, this.editCursor - 1);
               this.newName = var12 + this.newName.substring(this.editCursor);
               --this.editCursor;
            }

            return true;
         }

         if (keyCode == 263) {
            if (this.editCursor > 0) {
               --this.editCursor;
            }

            return true;
         }

         if (keyCode == 262) {
            if (this.editCursor < this.newName.length()) {
               ++this.editCursor;
            }

            return true;
         }

         if (keyCode == 86 && (modifiers & 2) != 0) {
            String clip = class_310.method_1551().field_1774.method_1460().trim();
            int avail = 15 - this.newName.length();
            if (clip.length() > avail) {
               clip = clip.substring(0, avail);
            }

            this.newName = this.newName.substring(0, this.editCursor) + clip + this.newName.substring(this.editCursor);
            this.editCursor += clip.length();
            return true;
         }
      }

      return super.keyPressed(keyCode, scanCode, modifiers);
   }

   public boolean charTyped(char chr, int modifiers) {
      if (this.editingInput) {
         if (this.isDefaultTab && this.configInput.length() < 15 && Character.isLetterOrDigit(chr)) {
            this.configInput = this.configInput.substring(0, this.inputCursor) + chr + this.configInput.substring(this.inputCursor);
            ++this.inputCursor;
            return true;
         }

         if (!this.isDefaultTab && this.configInput.length() < 8 && Character.isDigit(chr)) {
            this.configInput = this.configInput.substring(0, this.inputCursor) + chr + this.configInput.substring(this.inputCursor);
            ++this.inputCursor;
            return true;
         }
      } else if (this.editingConfig != null && this.newName.length() < 15 && Character.isLetterOrDigit(chr)) {
         this.newName = this.newName.substring(0, this.editCursor) + chr + this.newName.substring(this.editCursor);
         ++this.editCursor;
         return true;
      }

      return super.charTyped(chr, modifiers);
   }

   private void refreshConfigs() {
      if (this.isDefaultTab) {
         this.configs = this.getLocalConfigs();
      } else {
         if (this.cloudLoading) {
            return;
         }

         this.cloudLoading = true;
         this.cloudDataReady = false;
         this.configs = new ArrayList();
         this.cloudLoadStartTime = System.currentTimeMillis();
         (new Thread(() -> {
            List<Map<String, Object>> cl = this.getCloudConfigs();
            class_310.method_1551().execute(() -> {
               this.tempConfigs = cl;
               this.cloudDataReady = true;
            });
         })).start();
      }

   }

   private List getLocalConfigs() {
      List<Map<String, Object>> localConfigs = new ArrayList();
      File dir = new File(Yanderov.getInstance().getClientInfoProvider().clientDir(), "Custom");
      File[] configFiles = dir.listFiles();
      if (configFiles != null) {
         for(File configFile : configFiles) {
            if (configFile.isFile() && configFile.getName().endsWith(".json")) {
               String configName = configFile.getName().replace(".json", "");
               long mod = configFile.lastModified();
               Map<String, Object> map = new HashMap();
               map.put("name", configName);
               map.put("created", mod);
               map.put("updated", mod);
               String jsonContent = "";

               try {
                  jsonContent = new String(Files.readAllBytes(configFile.toPath()));
               } catch (IOException var19) {
               }

               if (!jsonContent.isEmpty()) {
                  Gson gson = new Gson();
                  Map<String, Object> configData = (Map)gson.fromJson(jsonContent, Map.class);
                  String cloudId = (String)configData.get("cloud_id");
                  if (cloudId != null) {
                     map.put("cloud_id", cloudId);
                     Map<String, Object> metadata = this.getCloudMetadata(cloudId);
                     if (metadata != null) {
                        long serverUpdated = ((Number)metadata.get("updated")).longValue();
                        if (serverUpdated > mod) {
                           map.put("has_update", true);
                        }

                        map.put("owner", metadata.get("owner"));
                        map.put("avatar_hash", metadata.get("avatar_hash"));
                     }
                  }
               }

               localConfigs.add(map);
            }
         }
      }

      return localConfigs;
   }

   private List getCloudConfigs() {
      try {
         if (!Yanderov.getInstance().getCloudConfigClient().isConnected()) {
            return new ArrayList();
         }

         Gson gson = new Gson();
         Map<String, Object> request = new HashMap();
         request.put("command", "list");
         request.put("username", UserProfile.getInstance().profile("username"));
         request.put("uuid", UserProfile.getInstance().profile("uid"));
         String message = gson.toJson(request);
         String response = Yanderov.getInstance().getCloudConfigClient().sendAndWaitForResponse(message);
         if (response == null) {
            return new ArrayList();
         }

         Map<String, Object> respMap = (Map)gson.fromJson(response, Map.class);
         if ((Boolean)respMap.get("success")) {
            return (List)respMap.get("data");
         }
      } catch (Exception e) {
         System.err.println("Failed to get cloud configs: " + e.getMessage());
      }

      return new ArrayList();
   }

   private void createDefaultConfig() {
      Random random = new Random();
      File dir = new File(Yanderov.getInstance().getClientInfoProvider().clientDir(), "Custom");

      String name;
      do {
         Object[] var10001 = new Object[]{random.nextInt(1000)};
         name = "YanderovConfig" + String.format("%03d", var10001);
      } while((new File(dir, name + ".json")).exists());

      try {
         File configFile = new File(dir, name + ".json");
         String json = this.getCurrentConfigJson();
         Files.write(configFile.toPath(), json.getBytes(), new OpenOption[0]);
      } catch (IOException var6) {
      }

   }

   private void createCloudConfig() {
      Random random = new Random();

      String id;
      do {
         id = String.format("%08d", random.nextInt(100000000));
      } while(this.getCloudConfigJson(id) != null);

      this.saveCloudConfig(id);
   }

   private void clearAllConfigs() {
      if (this.isDefaultTab) {
         File dir = new File(Yanderov.getInstance().getClientInfoProvider().clientDir(), "Custom");
         File[] files = dir.listFiles();
         if (files != null) {
            for(File f : files) {
               if (f.getName().endsWith(".json")) {
                  f.delete();
               }
            }
         }
      } else {
         for(Map map : this.configs) {
            this.removeCloudConfig((String)map.get("name"));
         }
      }

   }

   private String getCurrentConfigJson() {
      String json = "";
      File dir = new File(Yanderov.getInstance().getClientInfoProvider().clientDir(), "Custom");
      File temp = new File(dir, "temp.json");

      try {
         Yanderov.getInstance().getFileController().saveFile("temp.json");
         json = new String(Files.readAllBytes(temp.toPath()));
      } catch (IOException | FileSaveException var8) {
      } finally {
         temp.delete();
      }

      return json;
   }

   private String getCloudConfigJson(String name) {
      try {
         if (!Yanderov.getInstance().getCloudConfigClient().isConnected()) {
            return null;
         }

         Gson gson = new Gson();
         Map<String, Object> request = new HashMap();
         request.put("command", "load");
         request.put("username", UserProfile.getInstance().profile("username"));
         request.put("uuid", UserProfile.getInstance().profile("uid"));
         request.put("configName", name);
         String message = gson.toJson(request);
         String response = Yanderov.getInstance().getCloudConfigClient().sendAndWaitForResponse(message);
         if (response == null) {
            return null;
         }

         Map<String, Object> respMap = (Map)gson.fromJson(response, Map.class);
         if ((Boolean)respMap.get("success")) {
            Object data = respMap.get("data");
            return gson.toJson(data);
         }
      } catch (Exception e) {
         System.err.println("Failed to get cloud config: " + e.getMessage());
      }

      return null;
   }

   private Map getCloudMetadata(String name) {
      try {
         if (!Yanderov.getInstance().getCloudConfigClient().isConnected()) {
            return null;
         }

         Gson gson = new Gson();
         Map<String, Object> request = new HashMap();
         request.put("command", "metadata");
         request.put("username", UserProfile.getInstance().profile("username"));
         request.put("uuid", UserProfile.getInstance().profile("uid"));
         request.put("configName", name);
         String message = gson.toJson(request);
         String response = Yanderov.getInstance().getCloudConfigClient().sendAndWaitForResponse(message);
         if (response == null) {
            return null;
         }

         Map<String, Object> respMap = (Map)gson.fromJson(response, Map.class);
         if ((Boolean)respMap.get("success")) {
            return (Map)respMap.get("data");
         }
      } catch (Exception e) {
         System.err.println("Failed to get cloud metadata: " + e.getMessage());
      }

      return null;
   }

   private void saveCloudConfig(String name) {
      String json = this.getCurrentConfigJson();
      if (!json.isEmpty()) {
         Gson gson = new Gson();
         Map<String, Object> data = new LinkedHashMap();
         long now = System.currentTimeMillis();
         String existing = this.getCloudConfigJson(name);
         long created = now;
         if (existing != null) {
            Map<String, Object> oldData = (Map)gson.fromJson(existing, Map.class);
            created = ((Number)oldData.get("created")).longValue();
         }

         data.put("owner", UserProfile.getInstance().profile("username"));
         data.put("created", created);
         data.put("updated", now);
         data.put("avatar_hash", Yanderov.getInstance().getDiscordManager().getAvatarId().toString());
         Map<String, Object> configData = (Map)gson.fromJson(json, Map.class);
         data.putAll(configData);
         json = gson.toJson(data);
         this.saveCloudConfigWithJson(name, json);
      }
   }

   private void saveCloudConfigWithJson(String name, String json) {
      if (!json.isEmpty()) {
         try {
            if (!Yanderov.getInstance().getCloudConfigClient().isConnected()) {
               System.err.println("Cannot save: WebSocket not connected");
               return;
            }

            Gson gson = new Gson();
            Map<String, Object> request = new HashMap();
            request.put("command", "save");
            request.put("username", UserProfile.getInstance().profile("username"));
            request.put("uuid", UserProfile.getInstance().profile("uid"));
            request.put("configName", name);
            request.put("configData", gson.fromJson(json, Map.class));
            String message = gson.toJson(request);
            Yanderov.getInstance().getCloudConfigClient().sendAndWaitForResponse(message);
         } catch (Exception e) {
            System.err.println("Failed to save cloud config: " + e.getMessage());
         }

      }
   }

   private void loadCloudConfig(String name) {
      String json = this.getCloudConfigJson(name);
      if (json != null) {
         Gson gson = new Gson();
         Map<String, Object> data = (Map)gson.fromJson(json, Map.class);
         String owner = (String)data.get("owner");
         long created = ((Number)data.get("created")).longValue();
         long updated = ((Number)data.get("updated")).longValue();
         Object avatarObj = data.get("avatar_hash");
         if (avatarObj instanceof Map) {
            Map<String, String> idMap = (Map)avatarObj;
            String var10000 = (String)idMap.get("namespace");
            String avatarHash = var10000 + ":" + (String)idMap.get("path");
         } else {
            String avatarHash = String.valueOf(avatarObj);
         }

         data.remove("owner");
         data.remove("created");
         data.remove("updated");
         data.remove("avatar_hash");
         data.put("cloud_id", name);
         json = gson.toJson(data);
         File dir = new File(Yanderov.getInstance().getClientInfoProvider().clientDir(), "Custom");
         File temp = new File(dir, "temp.json");

         try {
            Files.write(temp.toPath(), json.getBytes(), new OpenOption[0]);
            Yanderov.getInstance().getFileController().loadFile("temp.json");
         } catch (FileLoadException | IOException var21) {
         } finally {
            temp.delete();
         }

         String baseName = owner + " Config";
         String localName = baseName;
         if (!owner.equals(UserProfile.getInstance().profile("username"))) {
            for(int num = 1; (new File(dir, localName + ".json")).exists(); localName = baseName + " (" + num++ + ")") {
            }
         } else {
            localName = "Cloud_" + name;
         }

         try {
            Files.write((new File(dir, localName + ".json")).toPath(), json.getBytes(), new OpenOption[0]);
         } catch (IOException var20) {
         }

      }
   }

   private void updateFromCloud(String localName, String cloudId) {
      String json = this.getCloudConfigJson(cloudId);
      if (json != null) {
         Gson gson = new Gson();
         Map<String, Object> data = (Map)gson.fromJson(json, Map.class);
         data.remove("owner");
         data.remove("created");
         data.remove("updated");
         data.remove("avatar_hash");
         data.put("cloud_id", cloudId);
         json = gson.toJson(data);
         File dir = new File(Yanderov.getInstance().getClientInfoProvider().clientDir(), "Custom");
         File temp = new File(dir, "temp.json");

         try {
            Files.write(temp.toPath(), json.getBytes(), new OpenOption[0]);
            Yanderov.getInstance().getFileController().loadFile("temp.json");
            Files.write((new File(dir, localName + ".json")).toPath(), json.getBytes(), new OpenOption[0]);
         } catch (FileLoadException | IOException var12) {
         } finally {
            temp.delete();
         }

      }
   }

   private void removeCloudConfig(String name) {
      try {
         if (!Yanderov.getInstance().getCloudConfigClient().isConnected()) {
            System.err.println("Cannot remove: WebSocket not connected");
            return;
         }

         Gson gson = new Gson();
         Map<String, Object> request = new HashMap();
         request.put("command", "remove");
         request.put("username", UserProfile.getInstance().profile("username"));
         request.put("uuid", UserProfile.getInstance().profile("uid"));
         request.put("configName", name);
         String message = gson.toJson(request);
         Yanderov.getInstance().getCloudConfigClient().sendAndWaitForResponse(message);
      } catch (Exception e) {
         System.err.println("Failed to remove cloud config: " + e.getMessage());
      }

   }

   public BackgroundComponent setEditingConfig(String editingConfig) {
      this.editingConfig = editingConfig;
      return this;
   }

   public BackgroundComponent setNewName(String newName) {
      this.newName = newName;
      return this;
   }

   public BackgroundComponent setEditCursor(int editCursor) {
      this.editCursor = editCursor;
      return this;
   }

   public BackgroundComponent setDefaultTab(boolean isDefaultTab) {
      this.isDefaultTab = isDefaultTab;
      return this;
   }

   public BackgroundComponent setHighlightX(float highlightX) {
      this.highlightX = highlightX;
      return this;
   }

   public BackgroundComponent setConfigs(List configs) {
      this.configs = configs;
      return this;
   }

   public BackgroundComponent setConfigInput(String configInput) {
      this.configInput = configInput;
      return this;
   }

   public BackgroundComponent setEditingInput(boolean editingInput) {
      this.editingInput = editingInput;
      return this;
   }

   public BackgroundComponent setInputCursor(int inputCursor) {
      this.inputCursor = inputCursor;
      return this;
   }

   public BackgroundComponent setScroll(float scroll) {
      this.scroll = scroll;
      return this;
   }

   public BackgroundComponent setSmoothedScroll(float smoothedScroll) {
      this.smoothedScroll = smoothedScroll;
      return this;
   }

   public BackgroundComponent setLoadedConfigs(boolean loadedConfigs) {
      this.loadedConfigs = loadedConfigs;
      return this;
   }

   public BackgroundComponent setCloudLoading(boolean cloudLoading) {
      this.cloudLoading = cloudLoading;
      return this;
   }

   public BackgroundComponent setCloudLoadStartTime(long cloudLoadStartTime) {
      this.cloudLoadStartTime = cloudLoadStartTime;
      return this;
   }

   public BackgroundComponent setCloudDataReady(boolean cloudDataReady) {
      this.cloudDataReady = cloudDataReady;
      return this;
   }

   public BackgroundComponent setTempConfigs(List tempConfigs) {
      this.tempConfigs = tempConfigs;
      return this;
   }

   public BackgroundComponent setLoadingAlpha(float loadingAlpha) {
      this.loadingAlpha = loadingAlpha;
      return this;
   }

   public BackgroundComponent setConfigsAlpha(float configsAlpha) {
      this.configsAlpha = configsAlpha;
      return this;
   }
}

