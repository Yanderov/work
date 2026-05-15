package fun.Yanderov.features.impl.player;

import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import fun.Yanderov.utils.client.managers.event.impl.EventRender;
import fun.Yanderov.utils.display.font.FontRenderer;
import fun.Yanderov.utils.display.font.Fonts;
import java.awt.Color;
import java.util.Locale;
import java.util.regex.Pattern;
import net.minecraft.class_1297;
import net.minecraft.class_1531;
import net.minecraft.class_2561;
import net.minecraft.class_332;

public class MineViewer extends Module {
   private static final Pattern TIME_FORMAT_PATTERN = Pattern.compile("^\\d{2}:\\d+.*$");
   private String displayTimeText = "";
   private String displayLevelText = "";
   private float animationProgress = 0.0F;
   private boolean hasData = false;
   private long lastUpdateTime = System.currentTimeMillis();

   public MineViewer() {
      super("MineViewer", "Mine Viewer", ModuleCategory.PLAYER);
   }

   @EventHandler
   public void onRenderHud(EventRender.BeforeHud e) {
      if (mc != null && mc.field_1687 != null && mc.field_1724 != null) {
         String timeText = "";
         String levelText = "";
         boolean foundData = false;
         class_1297 nearestTimeEntity = null;
         class_1297 nearestLevelEntity = null;
         double minTimeDistance = Double.MAX_VALUE;
         double minLevelDistance = Double.MAX_VALUE;

         for(class_1297 entity : mc.field_1687.method_18112()) {
            if (entity instanceof class_1531) {
               class_1531 armorStand = (class_1531)entity;
               if (armorStand.method_16914()) {
                  class_2561 customNameComponent = armorStand.method_5797();
                  if (customNameComponent != null) {
                     String customName = customNameComponent.getString();
                     if (customName != null && !customName.isEmpty()) {
                        String lower = customName.toLowerCase(Locale.ROOT);
                        double distance = armorStand.method_5858(mc.field_1724);
                        if (this.isValidTimeText(lower)) {
                           if (distance < minTimeDistance) {
                              minTimeDistance = distance;
                              nearestTimeEntity = armorStand;
                           }
                        } else if (this.isValidLevelText(lower) && distance < minLevelDistance) {
                           minLevelDistance = distance;
                           nearestLevelEntity = armorStand;
                        }
                     }
                  }
               }
            }
         }

         if (nearestTimeEntity != null && nearestTimeEntity.method_5797() != null) {
            timeText = nearestTimeEntity.method_5797().getString();
            foundData = true;
         }

         if (nearestLevelEntity != null && nearestLevelEntity.method_5797() != null) {
            levelText = nearestLevelEntity.method_5797().getString();
            foundData = true;
         }

         boolean newHasData = foundData && (!timeText.isEmpty() || !levelText.isEmpty());
         if (newHasData) {
            this.displayTimeText = timeText;
            this.displayLevelText = levelText;
         }

         this.hasData = newHasData;
         this.updateAnimation();
         if (!(this.animationProgress <= 0.0F)) {
            this.drawMineInfo(e.getContext(), this.displayTimeText, this.displayLevelText);
         }
      }
   }

   private void updateAnimation() {
      long currentTime = System.currentTimeMillis();
      float deltaTime = (float)(currentTime - this.lastUpdateTime) / 1000.0F;
      this.lastUpdateTime = currentTime;
      float animationSpeed = this.hasData ? 3.0F : 1.5F;
      if (this.hasData) {
         this.animationProgress = Math.min(1.0F, this.animationProgress + deltaTime * animationSpeed);
      } else {
         this.animationProgress = Math.max(0.0F, this.animationProgress - deltaTime * animationSpeed);
      }

   }

   private float easeOutCubic(float t) {
      return 1.0F - (float)Math.pow((double)(1.0F - t), (double)3.0F);
   }

   private boolean isValidTimeText(String text) {
      if (text != null && !text.isEmpty()) {
         if (!TIME_FORMAT_PATTERN.matcher(text).matches()) {
            return false;
         } else {
            for(char c : text.toCharArray()) {
               if (c != ':' && Character.isLetter(c)) {
                  return false;
               }
            }

            return true;
         }
      } else {
         return false;
      }
   }

   private boolean isValidLevelText(String text) {
      if (text != null && !text.isEmpty()) {
         return text.contains("Ð¾Ð±Ñ‹Ñ‡Ð½Ñ‹Ð¹") || text.contains("Ð¼Ð¸Ñ„Ð¸Ñ‡ÐµÑÐºÐ¸Ð¹") || text.contains("Ð»ÐµÐ³ÐµÐ½Ð´Ð°Ñ€Ð½Ñ‹Ð¹") || text.contains("Ð¾Ð±Ñ‹Ñ‡") || text.contains("Ð¼Ð¸Ñ„") || text.contains("Ð»ÐµÐ³");
      } else {
         return false;
      }
   }

   private void drawMineInfo(class_332 context, String timeText, String levelText) {
      float eased = this.easeOutCubic(this.animationProgress);
      int screenWidth = mc.method_22683().method_4486();
      int screenHeight = mc.method_22683().method_4502();
      FontRenderer font = Fonts.getSize(14, Fonts.Type.DEFAULT);
      String timeLine = timeText.isEmpty() ? "" : "ÐÐ²Ñ‚Ð¾ ÑˆÐ°Ñ…Ñ‚Ð°: " + timeText;
      String levelLine = levelText == null ? "" : levelText;
      float maxWidth = 0.0F;
      if (!timeLine.isEmpty()) {
         maxWidth = Math.max(maxWidth, font.getStringWidth(timeLine));
      }

      if (!levelLine.isEmpty()) {
         maxWidth = Math.max(maxWidth, font.getStringWidth(levelLine));
      }

      float paddingX = 6.0F;
      float paddingY = 4.0F;
      float boxWidth = maxWidth + paddingX * 2.0F;
      float boxHeight = 0.0F;
      if (!timeLine.isEmpty()) {
         boxHeight += font.getStringHeight(timeLine) + 2.0F;
      }

      if (!levelLine.isEmpty()) {
         float var10000 = boxHeight + font.getStringHeight(levelLine) + 2.0F;
      }

      float baseX = (float)screenWidth / 2.0F - boxWidth / 2.0F;
      float baseY = (float)screenHeight - 70.0F;
      float yOffset = (1.0F - eased) * 30.0F;
      float y = baseY + yOffset;
      int alpha = (int)(255.0F * eased);
      if (alpha > 5) {
         int textColor = (new Color(255, 255, 255, alpha)).getRGB();
         float textY = y + paddingY;
         if (!timeLine.isEmpty()) {
            font.drawString(context.method_51448(), timeLine, (double)(baseX + paddingX), (double)textY, textColor);
            textY += font.getStringHeight(timeLine) + 2.0F;
         }

         if (!levelLine.isEmpty()) {
            font.drawString(context.method_51448(), levelLine, (double)(baseX + paddingX), (double)textY, textColor);
         }

      }
   }
}

