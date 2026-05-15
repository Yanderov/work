package fun.Yanderov.display.hud;

import fun.Yanderov.common.animation.Animation;
import fun.Yanderov.common.animation.Direction;
import fun.Yanderov.common.animation.implement.OutBack;
import fun.Yanderov.events.container.SetScreenEvent;
import fun.Yanderov.events.packet.PacketEvent;
import fun.Yanderov.features.impl.render.Hud;
import fun.Yanderov.utils.client.Instance;
import fun.Yanderov.utils.client.managers.api.draggable.AbstractDraggable;
import fun.Yanderov.utils.client.sound.SoundManager;
import fun.Yanderov.utils.display.font.FontRenderer;
import fun.Yanderov.utils.display.font.Fonts;
import fun.Yanderov.utils.display.shape.ShapeProperties;
import fun.Yanderov.utils.interactions.interact.PlayerInteractionHelper;
import fun.Yanderov.utils.math.calc.Calculate;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import net.minecraft.class_124;
import net.minecraft.class_1297;
import net.minecraft.class_1542;
import net.minecraft.class_1799;
import net.minecraft.class_2561;
import net.minecraft.class_2596;
import net.minecraft.class_2653;
import net.minecraft.class_2775;
import net.minecraft.class_332;
import net.minecraft.class_3414;
import net.minecraft.class_408;
import net.minecraft.class_4587;
import net.minecraft.class_5250;
import net.minecraft.class_638;
import net.minecraft.class_746;
import net.minecraft.class_9288;
import net.minecraft.class_9334;

public class Notifications extends AbstractDraggable {
   private final List list = new ArrayList();
   private final List stacks = new ArrayList();

   public static Notifications getInstance() {
      return (Notifications)Instance.getDraggable(Notifications.class);
   }

   public Notifications() {
      super("Notifications", 0, 0, 100, 15, false);
   }

   public void tick() {
      this.list.forEach((notif) -> {
         if (System.currentTimeMillis() > notif.removeTime || notif.text.getString().contains("Hi I'm a notification") && !PlayerInteractionHelper.isChat(mc.field_1755)) {
            notif.anim.setDirection(Direction.BACKWARDS);
         }

      });
      this.list.removeIf((notif) -> notif.anim.isFinished(Direction.BACKWARDS));

      while(!this.stacks.isEmpty()) {
         this.addTextIfNotEmpty(Notifications.TypePickUp.INVENTORY, "Items raised: ");
         this.addTextIfNotEmpty(Notifications.TypePickUp.SHULKER_INVENTORY, "Items placed in shulker: ");
         this.addTextIfNotEmpty(Notifications.TypePickUp.SHULKER, "Raised shulker with: ");
      }

   }

   public void packet(PacketEvent e) {
      if (!PlayerInteractionHelper.nullCheck()) {
         class_2596 var10000 = e.getPacket();
         Objects.requireNonNull(var10000);
         class_2596 var5 = var10000;
         byte var6 = 0;

         while(true) {
            //$FF: var6->value
            //0->net/minecraft/class_2775
            //1->net/minecraft/class_2653
            switch (var5.typeSwitch<invokedynamic>(var5, var6)) {
               case 0:
                  class_2775 item = (class_2775)var5;
                  if (Hud.getInstance().notificationSettings.isSelected("Item Pick Up") && item.method_11912() == ((class_746)Objects.requireNonNull(mc.field_1724)).method_5628()) {
                     class_1297 var11 = ((class_638)Objects.requireNonNull(mc.field_1687)).method_8469(item.method_11915());
                     if (var11 instanceof class_1542) {
                        class_1542 entity = (class_1542)var11;
                        class_1799 itemStack = entity.method_6983();
                        class_9288 component = (class_9288)itemStack.method_57824(class_9334.field_49622);
                        if (component == null) {
                           class_2561 itemText = itemStack.method_7964();
                           if (itemText.method_10851().toString().equals("empty")) {
                              class_5250 text = class_2561.method_43473().method_10852(itemText);
                              if (itemStack.method_7947() > 1) {
                                 String var10001 = String.valueOf(class_124.field_1070);
                                 text.method_27693(var10001 + " [" + String.valueOf(class_124.field_1061) + itemStack.method_7947() + String.valueOf(class_124.field_1080) + "x" + String.valueOf(class_124.field_1070) + "]");
                              }

                              this.stacks.add(new Stack(Notifications.TypePickUp.INVENTORY, text));
                              return;
                           }
                        } else {
                           component.method_57489().filter((s) -> s.method_7964().method_10851().toString().equals("empty")).forEach((stack) -> {
                              class_5250 text = class_2561.method_43473().method_10852(stack.method_7964());
                              if (stack.method_7947() > 1) {
                                 String var10001 = String.valueOf(class_124.field_1070);
                                 text.method_27693(var10001 + " [" + String.valueOf(class_124.field_1061) + stack.method_7947() + String.valueOf(class_124.field_1080) + "x" + String.valueOf(class_124.field_1070) + "]");
                              }

                              this.stacks.add(new Stack(Notifications.TypePickUp.SHULKER, text));
                           });
                        }

                        return;
                     }
                  }

                  var6 = 1;
                  break;
               case 1:
                  class_2653 slot = (class_2653)var5;
                  if (!Hud.getInstance().notificationSettings.isSelected("Item Pick Up")) {
                     var6 = 2;
                     break;
                  } else {
                     int slotId = slot.method_11450();
                     class_9288 updatedContainer = (class_9288)slot.method_11449().method_57824(class_9334.field_49622);
                     if (updatedContainer != null && slotId < ((class_746)Objects.requireNonNull(mc.field_1724)).field_7512.field_7761.size() && slot.method_11452() == 0) {
                        class_9288 currentContainer = (class_9288)mc.field_1724.field_7512.method_7611(slotId).method_7677().method_57824(class_9334.field_49622);
                        if (currentContainer != null) {
                           updatedContainer.method_57489().filter((stack) -> currentContainer.method_57489().noneMatch((s) -> Objects.equals(s.method_57353(), stack.method_57353()) && s.toString().equals(stack.toString()))).forEach((stack) -> {
                              class_5250 text = class_2561.method_43473().method_10852(stack.method_7964());
                              this.stacks.add(new Stack(Notifications.TypePickUp.SHULKER_INVENTORY, text));
                           });
                           return;
                        }
                     }

                     return;
                  }
               default:
                  return;
            }
         }
      }
   }

   public void setScreen(SetScreenEvent e) {
      if (e.getScreen() instanceof class_408) {
         this.addList("Hi I'm a notification", 99999999L);
      }

   }

   public void drawDraggable(class_332 context) {
      class_4587 matrix = context.method_51448();
      FontRenderer font = Fonts.getSize(12, Fonts.Type.DEFAULT);
      int windowHeight = mc.method_22683().method_4502();
      int windowWidth = mc.method_22683().method_4486();
      int crosshairY = windowHeight / 2;
      int crosshairX = windowWidth / 2;
      this.setX(crosshairX - 55);
      this.setY(crosshairY + 100);
      float offsetY = 0.0F;
      float offsetX = 5.0F;

      for(Notification notification : this.list) {
         float anim = notification.anim.getOutput().floatValue();
         float width = font.getStringWidth(notification.text) + offsetX * 2.0F;
         float startY = (float)this.getY() + offsetY;
         float startX = (float)this.getX() + (100.0F - width) / 2.0F;
         Calculate.setAlpha(anim, () -> {
            blur.render(ShapeProperties.create(matrix, (double)startX, (double)startY, (double)(width + 10.0F), (double)(this.getHeight() + 1)).round(4.0F).quality(12.0F).color((new Color(0, 0, 0, 150)).getRGB()).build());
            rectangle.render(ShapeProperties.create(matrix, (double)startX, (double)startY, (double)(width + 10.0F), (double)(this.getHeight() + 1)).round(4.0F).thickness(0.1F).outlineColor((new Color(33, 33, 33, 255)).getRGB()).color((new Color(18, 19, 20, 75)).getRGB(), (new Color(0, 2, 5, 75)).getRGB(), (new Color(0, 2, 5, 75)).getRGB(), (new Color(18, 19, 20, 75)).getRGB()).build());
            font.drawText(matrix, notification.text, (double)((int)(startX + offsetX) + 6), (double)(startY + 7.0F));
            if (!notification.isExpired()) {
               long elapsed = System.currentTimeMillis() - notification.startTime;
               long totalTime = notification.removeTime - notification.startTime;
               float progress = 1.0F - Math.min(1.0F, (float)elapsed / (float)totalTime);
               float progressWidth = width * progress;
               if (progressWidth > 0.0F) {
                  rectangle.render(ShapeProperties.create(matrix, (double)(startX + 2.0F), (double)(startY + 0.05F), (double)(progressWidth + 6.0F), (double)1.0F).round(0.75F, 0.0F, 0.75F, 0.0F).color((new Color(155, 155, 155, 255)).getRGB()).build());
               }
            }

         });
         offsetY += (float)(this.getHeight() + 3) * anim;
      }

   }

   private void addTextIfNotEmpty(TypePickUp type, String prefix) {
      class_5250 text = class_2561.method_43473();
      List<Stack> list = this.stacks.stream().filter((stackx) -> stackx.type.equals(type)).toList();
      int i = 0;

      for(int size = list.size(); i < size; ++i) {
         Stack stack = (Stack)list.get(i);
         if (stack.type == type) {
            text.method_10852(stack.text);
            this.stacks.remove(stack);
            if (text.getString().length() > 150) {
               break;
            }

            if (i + 1 != size) {
               text.method_27693(" , ");
            }
         }
      }

      if (!text.equals(class_2561.method_43473())) {
         this.addList((class_2561)class_2561.method_43473().method_27693(prefix).method_10852(text), 8000L);
      }

   }

   public void addList(String text, long removeTime) {
      this.addList((String)text, removeTime, (class_3414)null);
   }

   public void addList(class_2561 text, long removeTime) {
      this.addList((class_2561)text, removeTime, (class_3414)null);
   }

   public void addList(String text, long removeTime, class_3414 sound) {
      this.addList((class_2561)class_2561.method_43473().method_27693(text), removeTime, sound);
   }

   public void addList(class_2561 text, long removeTime, class_3414 sound) {
      this.list.add(new Notification(text, (new OutBack()).setMs(400).setValue((double)1.0F), System.currentTimeMillis(), System.currentTimeMillis() + removeTime));
      if (this.list.size() > 12) {
         this.list.removeFirst();
      }

      this.list.sort(Comparator.comparingDouble((notif) -> (double)(-notif.removeTime)));
      if (sound != null) {
         SoundManager.playSound(sound);
      }

   }

   public static record Notification(class_2561 text, Animation anim, long startTime, long removeTime) {
      public boolean isExpired() {
         return System.currentTimeMillis() > this.removeTime;
      }
   }

   public static record Stack(TypePickUp type, class_5250 text) {
   }

   public static enum TypePickUp {
      INVENTORY,
      SHULKER,
      SHULKER_INVENTORY;

      // $FF: synthetic method
      private static TypePickUp[] $values() {
         return new TypePickUp[]{INVENTORY, SHULKER, SHULKER_INVENTORY};
      }
   }
}

