package fun.Yanderov.features.impl.player;

import fun.Yanderov.events.container.HandledScreenEvent;
import fun.Yanderov.events.keyboard.KeyEvent;
import fun.Yanderov.events.player.InputEvent;
import fun.Yanderov.events.player.TickEvent;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.features.module.setting.Setting;
import fun.Yanderov.features.module.setting.implement.BindSetting;
import fun.Yanderov.features.module.setting.implement.BooleanSetting;
import fun.Yanderov.features.module.setting.implement.SelectSetting;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import fun.Yanderov.utils.display.color.ColorAssist;
import fun.Yanderov.utils.display.font.FontRenderer;
import fun.Yanderov.utils.display.font.Fonts;
import fun.Yanderov.utils.display.shape.ShapeProperties;
import fun.Yanderov.utils.math.time.StopWatch;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.class_1703;
import net.minecraft.class_1713;
import net.minecraft.class_1735;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2561;
import net.minecraft.class_332;
import net.minecraft.class_437;
import net.minecraft.class_4587;
import net.minecraft.class_476;
import net.minecraft.class_9290;
import net.minecraft.class_9334;

public class AhHelper extends Module {
   private BooleanSetting three = (new BooleanSetting("Ð¡Ð°Ð¼Ð¾Ðµ Ð´ÐµÑˆÐµÐ²Ð¾Ðµ", "ÐŸÐ¾Ð´ÑÐ²ÐµÑ‡Ð¸Ð²Ð°Ñ‚ÑŒ Ñ‚Ð¾Ð¿â€‘3 ÑÐ»Ð¾Ñ‚Ð°")).setValue(true);
   private BooleanSetting showPricePerItem = (new BooleanSetting("Ð¦ÐµÐ½Ð° Ð·Ð° ÑˆÑ‚ÑƒÐºÑƒ", "ÐŸÐ¾ÐºÐ°Ð·Ñ‹Ð²Ð°Ñ‚ÑŒ Ñ†ÐµÐ½Ñƒ Ð·Ð° Ð¿Ñ€ÐµÐ´Ð¼ÐµÑ‚")).setValue(true);
   private BindSetting searchBind = new BindSetting("ÐŸÐ¾Ð¸ÑÐº Ð¿Ð¾ Ð±Ð¸Ð½Ð´Ñƒ", "Ð˜ÑÐºÐ°Ñ‚ÑŒ Ð¿Ñ€ÐµÐ´Ð¼ÐµÑ‚ Ð½Ð° Ð°ÑƒÐºÑ†Ð¸Ð¾Ð½Ðµ Ð¿Ð¾ Ð±Ð¸Ð½Ð´Ñƒ");
   private BooleanSetting autoRelist = (new BooleanSetting("ÐÐ²Ñ‚Ð¾ Ð¿ÐµÑ€ÐµÐ²Ñ‹ÑÑ‚Ð°Ð²Ð»ÐµÐ½Ð¸Ðµ", "ÐÐ²Ñ‚Ð¾Ð¼Ð°Ñ‚Ð¸Ñ‡ÐµÑÐºÐ¸ Ð¿ÐµÑ€ÐµÐ²Ñ‹ÑÑ‚Ð°Ð²Ð»ÑÑ‚ÑŒ Ð»Ð¾Ñ‚")).setValue(false);
   private SelectSetting autoRelistMode = (new SelectSetting("Ð ÐµÐ¶Ð¸Ð¼ Ð°Ð²Ñ‚Ð¾ Ð¿ÐµÑ€ÐµÐ²Ñ‹ÑÑ‚Ð°Ð²Ð»ÐµÐ½Ð¸Ñ", "Ð’Ñ‹Ð±Ð¾Ñ€ Ñ€ÐµÐ¶Ð¸Ð¼Ð°")).value("Ð­Ð»Ð¸Ñ‚Ð°", "Ð˜Ð³Ñ€Ð¾Ðº").selected("Ð­Ð»Ð¸Ñ‚Ð°").visible(() -> this.autoRelist.isValue());
   private float x = 0.0F;
   private float y = 0.0F;
   private float x2 = 0.0F;
   private float y2 = 0.0F;
   private float x3 = 0.0F;
   private float y3 = 0.0F;
   private PriceInfo priceInfo1 = null;
   private PriceInfo priceInfo2 = null;
   private PriceInfo priceInfo3 = null;
   private StopWatch eliteTimer = new StopWatch();
   private boolean eliteMovingForward = true;
   private boolean eliteActive = false;
   private boolean eliteMoved2Blocks = false;
   private double eliteStartX = (double)0.0F;
   private double eliteStartZ = (double)0.0F;
   private StopWatch playerTimer = new StopWatch();
   private static final Pattern NUMBER_PATTERN = Pattern.compile("\\d{1,3}(?:[,\\s]?\\d{3})*");

   public AhHelper() {
      super("AhHelper", "Ah Helper", ModuleCategory.PLAYER);
      this.setup(new Setting[]{this.three, this.showPricePerItem, this.autoRelist, this.autoRelistMode, this.searchBind});
   }

   public void deactivate() {
      this.eliteActive = false;
      this.eliteMoved2Blocks = false;
      this.eliteStartX = (double)0.0F;
      this.eliteStartZ = (double)0.0F;
      this.eliteTimer.reset();
      this.playerTimer.reset();
      super.deactivate();
   }

   @EventHandler
   public void onKey(KeyEvent e) {
      int bindKey = this.searchBind.getKey();
      if (bindKey != 0 && bindKey != -1) {
         if (e.isKeyDown(bindKey)) {
            if (mc.field_1724 != null) {
               class_1799 held = mc.field_1724.method_6047();
               if (held.method_7960()) {
                  held = mc.field_1724.method_6079();
               }

               if (!held.method_7960()) {
                  String itemName = held.method_7964().getString();
                  if (itemName != null && !itemName.isEmpty()) {
                     itemName = itemName.replaceAll("^\\[.*?]\\s*", "").trim();
                     itemName = itemName.replaceAll("^.{1,3}\\s+", "");
                     itemName = itemName.replaceAll("\\s+.{1,3}$", "");
                     itemName = itemName.trim();
                     if (!itemName.isEmpty()) {
                        String command = "/ah search " + itemName;
                        mc.field_1724.field_3944.method_45730(command.substring(1));
                     }

                  }
               }
            }
         }
      }
   }

   @EventHandler
   public void onInput(InputEvent event) {
      if (this.autoRelist.isValue() && mc.field_1724 != null) {
         boolean modePlayer = this.autoRelistMode.isSelected("Ð˜Ð³Ñ€Ð¾Ðº");
         boolean modeElite = this.autoRelistMode.isSelected("Ð­Ð»Ð¸Ñ‚Ð°");
         class_437 screen = mc.field_1755;
         if (modePlayer && screen instanceof class_476 && mc.field_1724.method_24828()) {
            event.setJumping(true);
         }

         if (modeElite && this.eliteActive) {
            if (this.eliteMoved2Blocks) {
               event.inputNone();
               mc.field_1724.field_3944.method_45730("ah resell");
               this.eliteActive = false;
               this.eliteMoved2Blocks = false;
               this.eliteStartX = (double)0.0F;
               this.eliteStartZ = (double)0.0F;
               this.eliteTimer.reset();
               return;
            }

            if (!this.eliteMoved2Blocks) {
               boolean forward = this.eliteMovingForward;
               event.setDirectional(forward, !forward, false, false);
               if (this.eliteStartX == (double)0.0F && this.eliteStartZ == (double)0.0F) {
                  this.eliteStartX = mc.field_1724.method_23317();
                  this.eliteStartZ = mc.field_1724.method_23321();
               }

               if (mc.field_1724.method_24828()) {
                  event.setJumping(true);
               }

               if (this.eliteStartX != (double)0.0F || this.eliteStartZ != (double)0.0F) {
                  double currentX = mc.field_1724.method_23317();
                  double currentZ = mc.field_1724.method_23321();
                  double dx = currentX - this.eliteStartX;
                  double dz = currentZ - this.eliteStartZ;
                  double dist = Math.sqrt(dx * dx + dz * dz);
                  if (dist >= (double)2.0F) {
                     this.eliteMoved2Blocks = true;
                     event.inputNone();
                  }
               }
            }
         }

      }
   }

   @EventHandler
   public void onTick(TickEvent e) {
      if (mc.field_1724 != null && mc.field_1687 != null) {
         boolean modePlayer = this.autoRelist.isValue() && this.autoRelistMode.isSelected("Ð˜Ð³Ñ€Ð¾Ðº");
         boolean modeElite = this.autoRelist.isValue() && this.autoRelistMode.isSelected("Ð­Ð»Ð¸Ñ‚Ð°");
         if (modeElite && this.eliteTimer.finished((double)65000.0F)) {
            this.eliteActive = true;
            this.eliteMoved2Blocks = false;
            this.eliteStartX = (double)0.0F;
            this.eliteStartZ = (double)0.0F;
            this.eliteMovingForward = !this.eliteMovingForward;
            this.eliteTimer.reset();
         }

         if (modePlayer) {
            class_437 var5 = mc.field_1755;
            if (var5 instanceof class_476) {
               class_476 screen = (class_476)var5;
               class_1703 handler = screen.method_17577();
               if (this.playerTimer.finished((double)65000.0F)) {
                  for(class_1735 slot : handler.field_7761) {
                     if (slot != null && slot.method_7681()) {
                        class_1799 stack = slot.method_7677();
                        if (stack.method_7909() == class_1802.field_8557) {
                           mc.field_1761.method_2906(handler.field_7763, slot.field_7874, 0, class_1713.field_7790, mc.field_1724);
                           this.playerTimer.reset();
                           break;
                        }
                     }
                  }
               }
            }
         }

         class_437 current = mc.field_1755;
         if (current instanceof class_476) {
            class_476 screen = (class_476)current;
            String ts = screen.method_25440().getString();
            if (!ts.contains("ÐÑƒÐºÑ†Ð¸Ð¾Ð½") && !ts.contains("ÐŸÐ¾Ð¸ÑÐº:")) {
               this.resetSlots();
               return;
            }

            class_1703 handler = screen.method_17577();
            class_1735 slot1 = null;
            class_1735 slot2 = null;
            class_1735 slot3 = null;
            int fsPrice = Integer.MAX_VALUE;
            int medPrice = Integer.MAX_VALUE;
            int thPrice = Integer.MAX_VALUE;

            for(class_1735 slot : handler.field_7761) {
               if (slot != null && slot.field_7874 <= 44 && slot.method_7681()) {
                  PriceInfo info = this.extractPriceInfoFromStack(slot.method_7677());
                  if (info != null) {
                     int currentPrice = info.getPricePerItem();
                     if (currentPrice != -1) {
                        if (currentPrice < fsPrice) {
                           fsPrice = currentPrice;
                           slot1 = slot;
                           this.priceInfo1 = info;
                        }

                        if (this.three.isValue()) {
                           if (currentPrice < medPrice && currentPrice > fsPrice) {
                              medPrice = currentPrice;
                              slot2 = slot;
                              this.priceInfo2 = info;
                           }

                           if (currentPrice < thPrice && currentPrice > medPrice) {
                              thPrice = currentPrice;
                              slot3 = slot;
                              this.priceInfo3 = info;
                           }
                        } else {
                           this.x2 = this.y2 = 0.0F;
                           this.x3 = this.y3 = 0.0F;
                           this.priceInfo2 = null;
                           this.priceInfo3 = null;
                        }
                     }
                  }
               }
            }

            if (slot1 != null) {
               this.x = (float)slot1.field_7873;
               this.y = (float)slot1.field_7872;
            } else {
               this.priceInfo1 = null;
            }

            if (slot2 != null) {
               this.x2 = (float)slot2.field_7873;
               this.y2 = (float)slot2.field_7872;
            } else {
               this.priceInfo2 = null;
            }

            if (slot3 != null) {
               this.x3 = (float)slot3.field_7873;
               this.y3 = (float)slot3.field_7872;
            } else {
               this.priceInfo3 = null;
            }
         } else {
            this.resetSlots();
         }

      }
   }

   private void resetSlots() {
      this.x = this.y = 0.0F;
      this.x2 = this.y2 = 0.0F;
      this.x3 = this.y3 = 0.0F;
      this.priceInfo1 = null;
      this.priceInfo2 = null;
      this.priceInfo3 = null;
   }

   @EventHandler
   public void onHandledScreen(HandledScreenEvent e) {
      class_437 var3 = mc.field_1755;
      if (var3 instanceof class_476 screen) {
         String title = screen.method_25440().getString();
         if (title.contains("ÐÑƒÐºÑ†Ð¸Ð¾Ð½") || title.contains("ÐŸÐ¾Ð¸ÑÐº:")) {
            class_332 context = e.getDrawContext();
            class_4587 matrix = context.method_51448();
            int offsetX = (screen.field_22789 - e.getBackgroundWidth()) / 2;
            int offsetY = (screen.field_22790 - e.getBackgroundHeight()) / 2;
            matrix.method_22903();
            matrix.method_46416((float)offsetX, (float)offsetY, 0.0F);
            if (this.priceInfo1 != null && this.x != 0.0F && this.y != 0.0F) {
               this.drawSlotInfo(context, this.x, this.y, this.priceInfo1, ColorAssist.getColor(75, 255, 75, 160));
            }

            if (this.three.isValue() && this.priceInfo2 != null && this.x2 != 0.0F && this.y2 != 0.0F) {
               this.drawSlotInfo(context, this.x2, this.y2, this.priceInfo2, ColorAssist.getColor(75, 75, 255, 160));
            }

            if (this.three.isValue() && this.priceInfo3 != null && this.x3 != 0.0F && this.y3 != 0.0F) {
               this.drawSlotInfo(context, this.x3, this.y3, this.priceInfo3, ColorAssist.getColor(255, 75, 75, 160));
            }

            matrix.method_22909();
         }
      }
   }

   private void drawSlotInfo(class_332 context, float slotX, float slotY, PriceInfo info, int rectColor) {
      class_4587 matrix = context.method_51448();
      rectangle.render(ShapeProperties.create(matrix, (double)slotX, (double)slotY, (double)16.0F, (double)16.0F).color(rectColor).build());
      FontRenderer font = Fonts.getSize(10, Fonts.Type.DEFAULT);
      int price = this.showPricePerItem.isValue() ? info.getPricePerItem() : info.getTotalPrice();
      String priceText = this.formatNumberWithSpaces(price);
      String line = this.showPricePerItem.isValue() ? priceText + " /ÑˆÑ‚" : priceText;
      float textWidth = font.getStringWidth(line);
      float padX = 2.0F;
      float padY = 1.5F;
      float boxWidth = textWidth + padX * 2.0F;
      float boxHeight = font.getStringHeight(line) + padY * 2.0F;
      float boxX = slotX + 16.0F + 2.0F;
      float boxY = slotY - 2.0F;
      rectangle.render(ShapeProperties.create(matrix, (double)boxX, (double)boxY, (double)boxWidth, (double)boxHeight).round(2.0F).color(ColorAssist.HALF_BLACK).build());
      font.drawString(matrix, line, (double)(boxX + padX), (double)(boxY + padY + 0.5F), ColorAssist.getColor(255));
   }

   public PriceInfo extractPriceInfoFromStack(class_1799 stack) {
      if (stack != null && !stack.method_7960()) {
         class_9290 loreComponent = (class_9290)stack.method_57824(class_9334.field_49632);
         if (loreComponent == null) {
            return null;
         } else {
            List<class_2561> lore = loreComponent.comp_2400();
            int count = stack.method_7947();
            if (count <= 0) {
               return null;
            } else {
               for(class_2561 line : lore) {
                  String s = line.getString();
                  if (s != null && !s.isEmpty()) {
                     Integer totalPrice = this.findPriceInText(s);
                     if (totalPrice != null && totalPrice > 0) {
                        return new PriceInfo(totalPrice, count);
                     }
                  }
               }

               return null;
            }
         }
      } else {
         return null;
      }
   }

   private Integer findPriceInText(String text) {
      if (text != null && !text.isEmpty()) {
         String lower = text.toLowerCase(Locale.ROOT);
         Matcher matcher = NUMBER_PATTERN.matcher(text);
         Integer largestPrice = null;
         int maxValue = 0;

         while(matcher.find()) {
            String numberStr = matcher.group();
            String cleaned = numberStr.replaceAll("[,\\s]", "");

            try {
               int value = Integer.parseInt(cleaned);
               int startPos = matcher.start();
               int endPos = matcher.end();
               int contextStart = Math.max(0, startPos - 50);
               int contextEnd = Math.min(text.length(), endPos + 50);
               String context = text.substring(contextStart, contextEnd).toLowerCase(Locale.ROOT);
               if ((context.contains("Ñ†ÐµÐ½Ð°") || context.contains("price") || context.contains("$") || lower.contains("Ñ†ÐµÐ½Ð°") || lower.contains("price")) && value > maxValue && value >= 100) {
                  maxValue = value;
                  largestPrice = value;
               }
            } catch (NumberFormatException var14) {
            }
         }

         return largestPrice;
      } else {
         return null;
      }
   }

   /** @deprecated */
   @Deprecated
   protected int extractPriceFromStack(class_1799 stack) {
      PriceInfo info = this.extractPriceInfoFromStack(stack);
      return info != null ? info.getPricePerItem() : -1;
   }

   private String formatNumberWithSpaces(int number) {
      String numberStr = String.valueOf(number);
      StringBuilder formatted = new StringBuilder();
      int length = numberStr.length();

      for(int i = 0; i < length; ++i) {
         if (i > 0 && (length - i) % 3 == 0) {
            formatted.append(" ");
         }

         formatted.append(numberStr.charAt(i));
      }

      return formatted.toString();
   }

   public BooleanSetting getThree() {
      return this.three;
   }

   public BooleanSetting getShowPricePerItem() {
      return this.showPricePerItem;
   }

   public BindSetting getSearchBind() {
      return this.searchBind;
   }

   public BooleanSetting getAutoRelist() {
      return this.autoRelist;
   }

   public SelectSetting getAutoRelistMode() {
      return this.autoRelistMode;
   }

   public float getX() {
      return this.x;
   }

   public float getY() {
      return this.y;
   }

   public float getX2() {
      return this.x2;
   }

   public float getY2() {
      return this.y2;
   }

   public float getX3() {
      return this.x3;
   }

   public float getY3() {
      return this.y3;
   }

   public PriceInfo getPriceInfo1() {
      return this.priceInfo1;
   }

   public PriceInfo getPriceInfo2() {
      return this.priceInfo2;
   }

   public PriceInfo getPriceInfo3() {
      return this.priceInfo3;
   }

   public StopWatch getEliteTimer() {
      return this.eliteTimer;
   }

   public boolean isEliteMovingForward() {
      return this.eliteMovingForward;
   }

   public boolean isEliteActive() {
      return this.eliteActive;
   }

   public boolean isEliteMoved2Blocks() {
      return this.eliteMoved2Blocks;
   }

   public double getEliteStartX() {
      return this.eliteStartX;
   }

   public double getEliteStartZ() {
      return this.eliteStartZ;
   }

   public StopWatch getPlayerTimer() {
      return this.playerTimer;
   }

   public void setThree(BooleanSetting three) {
      this.three = three;
   }

   public void setShowPricePerItem(BooleanSetting showPricePerItem) {
      this.showPricePerItem = showPricePerItem;
   }

   public void setSearchBind(BindSetting searchBind) {
      this.searchBind = searchBind;
   }

   public void setAutoRelist(BooleanSetting autoRelist) {
      this.autoRelist = autoRelist;
   }

   public void setAutoRelistMode(SelectSetting autoRelistMode) {
      this.autoRelistMode = autoRelistMode;
   }

   public void setX(float x) {
      this.x = x;
   }

   public void setY(float y) {
      this.y = y;
   }

   public void setX2(float x2) {
      this.x2 = x2;
   }

   public void setY2(float y2) {
      this.y2 = y2;
   }

   public void setX3(float x3) {
      this.x3 = x3;
   }

   public void setY3(float y3) {
      this.y3 = y3;
   }

   public void setPriceInfo1(PriceInfo priceInfo1) {
      this.priceInfo1 = priceInfo1;
   }

   public void setPriceInfo2(PriceInfo priceInfo2) {
      this.priceInfo2 = priceInfo2;
   }

   public void setPriceInfo3(PriceInfo priceInfo3) {
      this.priceInfo3 = priceInfo3;
   }

   public void setEliteTimer(StopWatch eliteTimer) {
      this.eliteTimer = eliteTimer;
   }

   public void setEliteMovingForward(boolean eliteMovingForward) {
      this.eliteMovingForward = eliteMovingForward;
   }

   public void setEliteActive(boolean eliteActive) {
      this.eliteActive = eliteActive;
   }

   public void setEliteMoved2Blocks(boolean eliteMoved2Blocks) {
      this.eliteMoved2Blocks = eliteMoved2Blocks;
   }

   public void setEliteStartX(double eliteStartX) {
      this.eliteStartX = eliteStartX;
   }

   public void setEliteStartZ(double eliteStartZ) {
      this.eliteStartZ = eliteStartZ;
   }

   public void setPlayerTimer(StopWatch playerTimer) {
      this.playerTimer = playerTimer;
   }

   public static class PriceInfo {
      int totalPrice;
      int count;
      int pricePerItem;

      public PriceInfo(int totalPrice, int count) {
         this.totalPrice = totalPrice;
         this.count = count;
         this.pricePerItem = count > 0 ? totalPrice / count : 0;
      }

      public int getTotalPrice() {
         return this.totalPrice;
      }

      public int getCount() {
         return this.count;
      }

      public int getPricePerItem() {
         return this.pricePerItem;
      }

      public void setTotalPrice(int totalPrice) {
         this.totalPrice = totalPrice;
      }

      public void setCount(int count) {
         this.count = count;
      }

      public void setPricePerItem(int pricePerItem) {
         this.pricePerItem = pricePerItem;
      }
   }
}

