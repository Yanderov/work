package fun.Yanderov.display.hud;

import com.mojang.blaze3d.systems.RenderSystem;
import fun.Yanderov.common.animation.Animation;
import fun.Yanderov.common.animation.Direction;
import fun.Yanderov.common.animation.implement.Decelerate;
import fun.Yanderov.events.container.SetScreenEvent;
import fun.Yanderov.features.impl.misc.ServerHelper;
import fun.Yanderov.features.impl.render.Hud;
import fun.Yanderov.utils.client.chat.StringHelper;
import fun.Yanderov.utils.client.managers.api.draggable.AbstractDraggable;
import fun.Yanderov.utils.display.font.FontRenderer;
import fun.Yanderov.utils.display.font.Fonts;
import fun.Yanderov.utils.display.shape.ShapeProperties;
import fun.Yanderov.utils.interactions.interact.PlayerInteractionHelper;
import fun.Yanderov.utils.interactions.inv.InventoryTask;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1844;
import net.minecraft.class_308;
import net.minecraft.class_332;
import net.minecraft.class_4587;
import net.minecraft.class_9334;

public class Binds extends AbstractDraggable {
   private final ServerHelper serverHelper = ServerHelper.getInstance();
   private final List binds = new ArrayList();
   private final Map cooldownStartTimes = new HashMap();
   private final Map itemCounts = new HashMap();
   private final Set activeCooldowns = new HashSet();
   private static final int BINDS_PER_ROW = 5;
   private float width;
   private float height;
   private long lastBindChange = 0L;
   private String currentRandomKey1 = "None";
   private String currentRandomKey2 = "None";
   private String currentRandomKey3 = "None";
   private int currentItemIndex1 = 0;
   private int currentItemIndex2 = 1;
   private int currentItemIndex3 = 2;
   private static final class_1792[] EXAMPLE_ITEMS;
   private final Animation animation = (new Decelerate()).setMs(300).setValue((double)1.0F);

   public Binds() {
      super("Binds", 10, 180, 150, 40, true);
      this.initializeBinds();
   }

   private void initializeBinds() {
      for(ServerHelper.KeyBind keyBind : this.serverHelper.getKeyBindings()) {
         String name = keyBind.setting().getName();
         String searchName = this.getSearchNameForBind(name);
         int color = this.getColorForBind(name);
         this.binds.add(new BindInfo(keyBind, name, color, searchName));
      }

   }

   private String getSearchNameForBind(String name) {
      String var10000;
      switch (name) {
         case "Ð—ÐµÐ»ÑŒÐµ Ð¾Ñ‚Ñ€Ñ‹Ð¶ÐºÐ¸" -> var10000 = "Ð¾Ñ‚Ñ€Ñ‹Ð¶ÐºÐ¸";
         case "Ð—ÐµÐ»ÑŒÐµ ÑÐµÑ€Ð½Ð¾Ð¹ ÐºÐ¸ÑÐ»Ð¾Ñ‚Ñ‹" -> var10000 = "ÑÐµÑ€Ð½Ð°Ñ";
         case "Ð—ÐµÐ»ÑŒÐµ Ð²ÑÐ¿Ñ‹ÑˆÐºÐ¸" -> var10000 = "Ð²ÑÐ¿Ñ‹ÑˆÐºÐ°";
         case "Ð—ÐµÐ»ÑŒÐµ Ð¼Ð¾Ñ‡Ð¸ Ð¤Ð»ÐµÑˆÐ°" -> var10000 = "Ð¼Ð¾Ñ‡Ð° Ñ„Ð»ÐµÑˆÐ°";
         case "Ð—ÐµÐ»ÑŒÐµ Ð¿Ð¾Ð±ÐµÐ´Ð¸Ñ‚ÐµÐ»Ñ" -> var10000 = "Ð¿Ð¾Ð±ÐµÐ´Ð¸Ñ‚ÐµÐ»Ñ";
         case "Ð—ÐµÐ»ÑŒÐµ Ð°Ð³ÐµÐ½Ñ‚Ð°" -> var10000 = "Ð°Ð³ÐµÐ½Ñ‚Ð°";
         case "Ð—ÐµÐ»ÑŒÐµ Ð¼ÐµÐ´Ð¸ÐºÐ°" -> var10000 = "Ð¼ÐµÐ´Ð¸ÐºÐ°";
         case "Ð—ÐµÐ»ÑŒÐµ ÐºÐ¸Ð»Ð»ÐµÑ€Ð°" -> var10000 = "ÐºÐ¸Ð»Ð»ÐµÑ€Ð°";
         default -> var10000 = null;
      }

      return var10000;
   }

   private int getColorForBind(String name) {
      int var10000;
      switch (name) {
         case "Ð—ÐµÐ»ÑŒÐµ Ð¾Ñ‚Ñ€Ñ‹Ð¶ÐºÐ¸" -> var10000 = 16735488;
         case "Ð—ÐµÐ»ÑŒÐµ ÑÐµÑ€Ð½Ð¾Ð¹ ÐºÐ¸ÑÐ»Ð¾Ñ‚Ñ‹" -> var10000 = 49664;
         case "Ð—ÐµÐ»ÑŒÐµ Ð²ÑÐ¿Ñ‹ÑˆÐºÐ¸" -> var10000 = 16777215;
         case "Ð—ÐµÐ»ÑŒÐµ Ð¼Ð¾Ñ‡Ð¸ Ð¤Ð»ÐµÑˆÐ°" -> var10000 = 6092799;
         case "Ð—ÐµÐ»ÑŒÐµ Ð¿Ð¾Ð±ÐµÐ´Ð¸Ñ‚ÐµÐ»Ñ" -> var10000 = 65280;
         case "Ð—ÐµÐ»ÑŒÐµ Ð°Ð³ÐµÐ½Ñ‚Ð°" -> var10000 = 16775936;
         case "Ð—ÐµÐ»ÑŒÐµ Ð¼ÐµÐ´Ð¸ÐºÐ°" -> var10000 = 16711902;
         case "Ð—ÐµÐ»ÑŒÐµ ÐºÐ¸Ð»Ð»ÐµÑ€Ð°" -> var10000 = 16711680;
         default -> var10000 = -1;
      }

      return var10000;
   }

   private class_1799 createColoredPotion(class_1792 item, int color) {
      class_1799 stack = new class_1799(item);
      if (color != -1 && item == class_1802.field_8436) {
         stack.method_57379(class_9334.field_49651, new class_1844(Optional.empty(), Optional.of(color), List.of(), Optional.empty()));
      }

      return stack;
   }

   private void renderItemStack(class_332 context, class_1799 stack, float x, float y, float scale) {
      class_4587 matrices = context.method_51448();
      matrices.method_22903();
      matrices.method_46416(x, y, 100.0F);
      matrices.method_22905(scale, scale, 1.0F);
      RenderSystem.enableBlend();
      RenderSystem.defaultBlendFunc();
      class_308.method_24211();
      context.method_51427(stack, 0, 0);
      class_308.method_24210();
      matrices.method_22909();
   }

   private int countItemInInventory(class_1792 targetItem, String searchName) {
      if (PlayerInteractionHelper.nullCheck()) {
         return 0;
      } else {
         int count = 0;

         for(int i = 0; i < mc.field_1724.method_31548().method_5439(); ++i) {
            class_1799 itemStack = mc.field_1724.method_31548().method_5438(i);
            if (!itemStack.method_7960()) {
               if (searchName != null && targetItem == class_1802.field_8436) {
                  if (itemStack.method_7909() == targetItem && InventoryTask.getCleanName(itemStack.method_7964()).contains(searchName.toLowerCase())) {
                     count += itemStack.method_7947();
                  }
               } else if (itemStack.method_7909() == targetItem) {
                  count += itemStack.method_7947();
               }
            }
         }

         return count;
      }
   }

   private float getCooldownProgress(class_1792 item) {
      if (PlayerInteractionHelper.nullCheck()) {
         return 0.0F;
      } else {
         class_1799 stack = new class_1799(item);
         return mc.field_1724.method_7357().method_7905(stack, mc.method_61966().method_60637(false));
      }
   }

   private Color getProgressColor(float progress) {
      if (progress <= 0.5F) {
         float t = progress * 2.0F;
         int r = (int)(255.0F * (1.0F - t) + 255.0F * t);
         int g = (int)(0.0F * (1.0F - t) + 165.0F * t);
         int b = 0;
         return new Color(r, g, b);
      } else {
         float t = (progress - 0.5F) * 2.0F;
         int r = (int)(255.0F * (1.0F - t) + 0.0F * t);
         int g = (int)(165.0F * (1.0F - t) + 255.0F * t);
         int b = 0;
         return new Color(r, g, b);
      }
   }

   public boolean visible() {
      return Hud.getInstance().interfaceSettings.isSelected("Binds") && Hud.getInstance().state && !this.animation.isFinished(Direction.BACKWARDS);
   }

   public void tick() {
      if (PlayerInteractionHelper.nullCheck()) {
         this.animation.setDirection(Direction.BACKWARDS);
      } else {
         List<BindInfo> activeBinds = this.binds.stream().filter(this::isBindActive).toList();
         if (!activeBinds.isEmpty()) {
            this.animation.setDirection(Direction.FORWARDS);
         } else if (PlayerInteractionHelper.isChat(mc.field_1755)) {
            this.animation.setDirection(Direction.FORWARDS);
         } else {
            this.animation.setDirection(Direction.BACKWARDS);
         }

         this.itemCounts.clear();

         for(BindInfo bind : this.binds) {
            int count = this.countItemInInventory(bind.keyBind.item(), bind.searchName);
            this.itemCounts.put(bind.keyBind.item(), count);
         }

         this.activeCooldowns.clear();
         long currentTime = System.currentTimeMillis();

         for(int i = 0; i < mc.field_1724.method_31548().method_5439(); ++i) {
            class_1799 itemStack = mc.field_1724.method_31548().method_5438(i);
            if (!itemStack.method_7960() && mc.field_1724.method_7357().method_7904(itemStack)) {
               class_1792 item = itemStack.method_7909();
               if (!this.cooldownStartTimes.containsKey(item)) {
                  this.cooldownStartTimes.put(item, currentTime);
               }

               this.activeCooldowns.add(item);
            }
         }

         this.cooldownStartTimes.keySet().removeIf((itemx) -> !this.activeCooldowns.contains(itemx));
         if (activeBinds.isEmpty() && PlayerInteractionHelper.isChat(mc.field_1755) && currentTime - this.lastBindChange >= 1000L) {
            List<String> availableKeys = List.of("A", "B", "C", "D", "E");
            this.currentRandomKey1 = (String)availableKeys.get((new Random()).nextInt(availableKeys.size()));
            this.currentRandomKey2 = (String)availableKeys.get((new Random()).nextInt(availableKeys.size()));
            this.currentRandomKey3 = (String)availableKeys.get((new Random()).nextInt(availableKeys.size()));
            this.currentItemIndex1 = (this.currentItemIndex1 + 1) % EXAMPLE_ITEMS.length;
            this.currentItemIndex2 = (this.currentItemIndex2 + 1) % EXAMPLE_ITEMS.length;
            this.currentItemIndex3 = (this.currentItemIndex3 + 1) % EXAMPLE_ITEMS.length;
            this.lastBindChange = currentTime;
         }

      }
   }

   private boolean isBindActive(BindInfo bind) {
      return !PlayerInteractionHelper.nullCheck() && bind.keyBind.setting().isVisible() && bind.keyBind.setting().getKey() != -1 && bind.keyBind.setting().getKey() != 0;
   }

   public void setScreen(SetScreenEvent e) {
      super.setScreen(e);
      if (PlayerInteractionHelper.nullCheck()) {
         this.animation.setDirection(Direction.BACKWARDS);
      } else {
         if (PlayerInteractionHelper.isChat(e.getScreen())) {
            this.animation.setDirection(Direction.FORWARDS);
         } else {
            List<BindInfo> activeBinds = this.binds.stream().filter(this::isBindActive).toList();
            if (!activeBinds.isEmpty()) {
               this.animation.setDirection(Direction.FORWARDS);
            } else {
               this.animation.setDirection(Direction.BACKWARDS);
            }
         }

      }
   }

   public void drawDraggable(class_332 context) {
      if (Hud.getInstance().interfaceSettings.isSelected("Binds") && Hud.getInstance().state && !this.animation.isFinished(Direction.BACKWARDS)) {
         class_4587 matrix = context.method_51448();
         float animationValue = this.animation.getOutput().floatValue();
         if (!(animationValue <= 0.0F)) {
            List<BindInfo> activeBinds = (List)this.binds.stream().filter(this::isBindActive).collect(Collectors.toList());
            float padding = 3.0F;
            float iconSize = 16.0F;
            float spacing = 3.0F;
            float rowHeight = iconSize + 2.0F * padding;
            float posX = (float)this.getX();
            float posY = (float)this.getY();
            int totalBinds = activeBinds.size();
            int rowCount = (int)Math.ceil((double)totalBinds / (double)5.0F);
            List<Float> rowWidths = new ArrayList();
            float firstRowWidth = 0.0F;
            FontRenderer font = Fonts.getSize(13, Fonts.Type.DEFAULT);
            if (activeBinds.isEmpty() && PlayerInteractionHelper.isChat(mc.field_1755)) {
               rowCount = 1;
               String name = "Example Bind";
               String[] keys = new String[]{this.currentRandomKey1, this.currentRandomKey2, this.currentRandomKey3};
               float textWidth = font.getStringWidth((String)Arrays.stream(keys).max((a, b) -> Float.compare(font.getStringWidth(a), font.getStringWidth(b))).orElse(""));
               float bindWidth = iconSize + padding + textWidth + padding + 4.0F;
               float totalWidth = bindWidth * 3.0F + spacing * 2.0F;
               rowWidths.add(totalWidth);
               firstRowWidth = totalWidth;
            } else {
               for(int row = 0; row < rowCount; ++row) {
                  float currentX = padding;
                  int bindsInThisRow = Math.min(5, totalBinds - row * 5);

                  for(int i = 0; i < bindsInThisRow; ++i) {
                     int bindIndex = row * 5 + i;
                     BindInfo bind = (BindInfo)activeBinds.get(bindIndex);
                     String keyName = StringHelper.getBindName(bind.keyBind.setting().getKey());
                     float textWidth = font.getStringWidth(keyName);
                     float bindWidth = iconSize + padding + textWidth + padding + 4.0F;
                     currentX += bindWidth + spacing;
                  }

                  if (bindsInThisRow > 0) {
                     currentX -= spacing;
                  }

                  rowWidths.add(currentX);
                  if (row == 0) {
                     firstRowWidth = currentX;
                  }
               }
            }

            this.width = firstRowWidth + padding;
            this.height = rowHeight * (float)rowCount;
            if (activeBinds.isEmpty() && PlayerInteractionHelper.isChat(mc.field_1755)) {
               String name = "Example Bind";
               String[] keys = new String[]{this.currentRandomKey1, this.currentRandomKey2, this.currentRandomKey3};
               class_1799[] stacks = new class_1799[]{new class_1799(EXAMPLE_ITEMS[this.currentItemIndex1]), new class_1799(EXAMPLE_ITEMS[this.currentItemIndex2]), new class_1799(EXAMPLE_ITEMS[this.currentItemIndex3])};
               float offsetX = padding;
               float currentY = posY;

               for(int i = 0; i < 3; ++i) {
                  float textWidth = Fonts.getSize(11, Fonts.Type.DEFAULT).getStringWidth(keys[i]) + 1.5F;
                  float backgroundWidth = iconSize + padding + textWidth + padding + 4.0F;
                  blur.render(ShapeProperties.create(matrix, (double)(posX + offsetX - 1.0F), (double)currentY, (double)backgroundWidth, (double)(rowHeight - 6.0F)).round(3.0F).quality(12.0F).color((new Color(0, 0, 0, 150)).getRGB()).build());
                  rectangle.render(ShapeProperties.create(matrix, (double)(posX + offsetX - 1.0F), (double)currentY, (double)backgroundWidth, (double)(rowHeight - 6.0F)).round(3.0F).thickness(0.1F).outlineColor((new Color(33, 33, 33, 255)).getRGB()).color((new Color(18, 19, 20, 75)).getRGB(), (new Color(18, 19, 20, 75)).getRGB(), (new Color(0, 2, 5, 75)).getRGB(), (new Color(0, 2, 5, 75)).getRGB()).build());
                  rectangle.render(ShapeProperties.create(matrix, (double)(posX + offsetX - 1.0F), (double)currentY, (double)16.0F, (double)(rowHeight - 6.0F)).round(0.0F, 0.0F, 1.5F, 1.5F).outlineColor((new Color(33, 33, 33, 255)).getRGB()).color((new Color(18, 19, 20, 75)).getRGB(), (new Color(18, 19, 20, 75)).getRGB(), (new Color(0, 2, 5, 75)).getRGB(), (new Color(0, 2, 5, 75)).getRGB()).build());
                  this.renderItemStack(context, stacks[i], posX + offsetX + 2.0F, currentY + padding + 0.5F, 0.5F);
                  float textX = posX + offsetX + iconSize + padding;
                  float textY = currentY + padding + (iconSize - Fonts.getSize(11, Fonts.Type.DEFAULT).getStringHeight(keys[i])) / 2.0F;
                  int textAlpha = (int)Math.min(255.0F, Math.max(0.0F, 255.0F * animationValue));
                  Fonts.getSize(11, Fonts.Type.DEFAULT).drawString(matrix, keys[i], (double)(textX + 1.0F), (double)(textY + 3.0F), (new Color(225, 225, 255, textAlpha)).getRGB());
                  Fonts.getSize(10, Fonts.Type.DEFAULT).drawString(matrix, "64", (double)(posX + offsetX + 7.0F), (double)(currentY + rowHeight - 12.0F), (new Color(255, 255, 255, textAlpha)).getRGB());
                  offsetX += backgroundWidth + spacing;
               }
            } else {
               for(int row = 0; row < rowCount; ++row) {
                  float rowWidth = (Float)rowWidths.get(row);
                  float offsetX = row == 0 ? padding : padding + (firstRowWidth - rowWidth) / 2.0F;
                  float currentY = posY + (float)row * rowHeight;
                  int bindsInThisRow = Math.min(5, totalBinds - row * 5);

                  for(int i = 0; i < bindsInThisRow; ++i) {
                     int bindIndex = row * 5 + i;
                     BindInfo bind = (BindInfo)activeBinds.get(bindIndex);
                     class_1799 stack = this.createColoredPotion(bind.keyBind.item(), bind.color);
                     String keyName = StringHelper.getBindName(bind.keyBind.setting().getKey());
                     float textWidth = Fonts.getSize(11, Fonts.Type.DEFAULT).getStringWidth(keyName) + 1.5F;
                     float backgroundWidth = iconSize + padding + textWidth + padding + 4.0F;
                     int itemCount = (Integer)this.itemCounts.getOrDefault(bind.keyBind.item(), 0);
                     boolean isEmpty = itemCount == 0;
                     float cooldownProgress = this.getCooldownProgress(bind.keyBind.item());
                     float totalHeight = rowHeight - 6.0F;
                     float cooldownHeight = totalHeight * cooldownProgress;
                     float cooldownStartY = currentY + totalHeight - cooldownHeight;
                     blur.render(ShapeProperties.create(matrix, (double)(posX + offsetX - 1.0F), (double)currentY, (double)backgroundWidth, (double)(rowHeight - 6.0F)).round(3.0F).quality(12.0F).color((new Color(0, 0, 0, 150)).getRGB()).build());
                     if (isEmpty) {
                        rectangle.render(ShapeProperties.create(matrix, (double)(posX + offsetX - 1.0F), (double)currentY, (double)backgroundWidth, (double)(rowHeight - 6.0F)).round(3.0F).outlineColor((new Color(33, 33, 33, 255)).getRGB()).color((new Color(250, 10, 10, 35)).getRGB(), (new Color(250, 10, 10, 35)).getRGB(), (new Color(250, 5, 5, 35)).getRGB(), (new Color(250, 5, 5, 35)).getRGB()).build());
                        rectangle.render(ShapeProperties.create(matrix, (double)(posX + offsetX - 1.0F), (double)currentY, (double)16.0F, (double)(rowHeight - 6.0F)).round(0.0F, 0.0F, 1.5F, 1.5F).outlineColor((new Color(33, 33, 33, 255)).getRGB()).color((new Color(80, 10, 10, 65)).getRGB(), (new Color(20, 5, 5, 65)).getRGB(), (new Color(20, 5, 5, 65)).getRGB(), (new Color(20, 5, 5, 65)).getRGB()).build());
                     } else {
                        rectangle.render(ShapeProperties.create(matrix, (double)(posX + offsetX - 1.0F), (double)currentY, (double)backgroundWidth, (double)(rowHeight - 6.0F)).round(3.0F).outlineColor((new Color(33, 33, 33, 255)).getRGB()).color((new Color(18, 19, 20, 75)).getRGB(), (new Color(18, 19, 20, 75)).getRGB(), (new Color(0, 2, 5, 75)).getRGB(), (new Color(0, 2, 5, 75)).getRGB()).build());
                        if (cooldownProgress > 0.0F) {
                           Color progressColor = this.getProgressColor(cooldownProgress);
                           new Color(Math.min(255, (int)((double)progressColor.getRed() * 1.3)), Math.min(255, (int)((double)progressColor.getGreen() * 1.3)), Math.min(255, (int)((double)progressColor.getBlue() * 1.3)), 180);
                           new Color((int)((double)progressColor.getRed() * 0.6), (int)((double)progressColor.getGreen() * 0.6), (int)((double)progressColor.getBlue() * 0.6), 180);
                        }

                        rectangle.render(ShapeProperties.create(matrix, (double)(posX + offsetX - 1.0F), (double)currentY, (double)16.0F, (double)(rowHeight - 6.0F)).round(0.0F, 0.0F, 1.5F, 1.5F).outlineColor((new Color(33, 33, 33, 255)).getRGB()).color((new Color(18, 19, 20, 75)).getRGB(), (new Color(18, 19, 20, 75)).getRGB(), (new Color(0, 2, 5, 75)).getRGB(), (new Color(0, 2, 5, 75)).getRGB()).build());
                     }

                     this.renderItemStack(context, stack, posX + offsetX + 2.0F, currentY + padding + 0.5F, 0.5F);
                     float textX = posX + offsetX + iconSize + padding;
                     float textY = currentY + padding + (iconSize - Fonts.getSize(11, Fonts.Type.DEFAULT).getStringHeight(keyName)) / 2.0F;
                     int textAlpha = (int)Math.min(255.0F, Math.max(0.0F, 255.0F * animationValue));
                     Fonts.getSize(13, Fonts.Type.DEFAULT).drawString(matrix, keyName, (double)(textX + 1.0F), (double)(textY + 3.0F), (new Color(225, 225, 255, textAlpha)).getRGB());
                     Fonts.getSize(10, Fonts.Type.DEFAULT).drawString(matrix, String.valueOf(itemCount), (double)(posX + offsetX + 7.0F), (double)(currentY + rowHeight - 12.0F), (new Color(255, 255, 255, textAlpha)).getRGB());
                     offsetX += backgroundWidth + spacing;
                  }
               }
            }

            this.setWidth((int)this.width);
            this.setHeight((int)this.height);
         }
      }
   }

   static {
      EXAMPLE_ITEMS = new class_1792[]{class_1802.field_8449, class_1802.field_8479, class_1802.field_8551, class_1802.field_22021};
   }

   private static class BindInfo {
      ServerHelper.KeyBind keyBind;
      String displayName;
      int color;
      String searchName;

      BindInfo(ServerHelper.KeyBind keyBind, String displayName, int color, String searchName) {
         this.keyBind = keyBind;
         this.displayName = displayName;
         this.color = color;
         this.searchName = searchName;
      }
   }
}

