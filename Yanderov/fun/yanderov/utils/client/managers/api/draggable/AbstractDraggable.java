package fun.Yanderov.utils.client.managers.api.draggable;

import fun.Yanderov.Yanderov;
import fun.Yanderov.common.animation.Animation;
import fun.Yanderov.common.animation.Direction;
import fun.Yanderov.common.animation.implement.Decelerate;
import fun.Yanderov.events.container.SetScreenEvent;
import fun.Yanderov.events.packet.PacketEvent;
import fun.Yanderov.features.impl.render.Hud;
import fun.Yanderov.utils.display.color.ColorAssist;
import fun.Yanderov.utils.display.geometry.Render2D;
import fun.Yanderov.utils.display.interfaces.QuickImports;
import fun.Yanderov.utils.display.interfaces.QuickLogger;
import fun.Yanderov.utils.interactions.interact.PlayerInteractionHelper;
import net.minecraft.class_332;

public abstract class AbstractDraggable implements Draggable, QuickImports, QuickLogger {
   private String name;
   private int x;
   private int y;
   private int width;
   private int height;
   private boolean dragging;
   private boolean canDrag;
   private int dragX;
   private int dragY;
   public final Animation scaleAnimation = (new Decelerate()).setValue((double)1.0F).setMs(200);

   public AbstractDraggable(String name, int x, int y, int width, int height, boolean canDrag) {
      this.name = name;
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
      this.canDrag = canDrag;
   }

   public boolean visible() {
      return true;
   }

   public void tick() {
   }

   public void packet(PacketEvent e) {
   }

   public void render(class_332 context, int mouseX, int mouseY, float delta) {
      if (!this.dragging) {
         this.dragX = 0;
         this.dragY = 0;
      }

      Hud hud = Hud.getInstance();
      float mouseDragX = (float)(mouseX + this.dragX);
      float mouseDragY = (float)(mouseY + this.dragY);
      int windowWidth = window.method_4486();
      int windowHeight = window.method_4502();
      int radius = 3;
      if (this.dragging) {
         this.x = (int)Math.max(0.0F, Math.min(mouseDragX, (float)(windowWidth - this.width)));
         this.y = (int)Math.max(0.0F, Math.min(mouseDragY, (float)(windowHeight - this.height)));
      }

      for(AbstractDraggable drag : Yanderov.getInstance().getDraggableRepository().draggable()) {
         if (drag.canDraw(hud, drag) && drag.canDrag && drag != this) {
            int x1 = drag.x + drag.width + radius;
            int x2 = drag.x - this.width - radius;
            int y1 = drag.y + drag.height + radius;
            int y2 = drag.y - this.height - radius;
            int y3 = drag.y;
            if (Math.abs((float)x1 - mouseDragX) <= (float)radius) {
               this.drawRect((float)x1 - 1.5F, 0.0F, 1.0F, (float)windowHeight);
               this.x = x1;
            }

            if (Math.abs((float)x2 - mouseDragX) <= (float)radius) {
               this.drawRect((float)(x2 + this.width + 1), 0.0F, 1.0F, (float)windowHeight);
               this.x = x2;
            }

            if (Math.abs((float)y1 - mouseDragY) <= (float)radius) {
               this.drawRect(0.0F, (float)y1 - 1.5F, (float)windowWidth, 1.0F);
               this.y = y1;
            }

            if (Math.abs((float)y2 - mouseDragY) <= (float)radius) {
               this.drawRect(0.0F, (float)(y2 + this.height + 1), (float)windowWidth, 1.0F);
               this.y = y2;
            }

            if (Math.abs((float)y3 - mouseDragY) <= (float)radius) {
               this.drawRect(0.0F, (float)y3 - 1.5F, (float)windowWidth, 1.0F);
               this.y = y3;
            }
         }
      }

      if (Math.abs(this.x + (this.width - windowWidth) / 2) <= radius) {
         this.drawRect((float)windowWidth / 2.0F - 0.5F, 0.0F, 1.0F, (float)windowHeight);
         this.x = (windowWidth - this.width) / 2;
      }

      if (Math.abs(this.y + (this.height - windowHeight) / 2) <= radius) {
         this.drawRect(0.0F, (float)windowHeight / 2.0F - 0.5F, (float)windowWidth, 1.0F);
         this.y = (windowHeight - this.height) / 2;
      }

   }

   public void setScreen(SetScreenEvent e) {
      if (PlayerInteractionHelper.isChat(e.getScreen())) {
         this.dragging = false;
         this.dragX = 0;
         this.dragY = 0;
      }

   }

   public boolean mouseClicked(double mouseX, double mouseY, int button) {
      if (this.isHovered(mouseX, mouseY) && button == 0 && this.canDrag) {
         this.dragging = true;
         this.dragX = this.x - (int)mouseX;
         this.dragY = this.y - (int)mouseY;
         return true;
      } else {
         return false;
      }
   }

   public boolean mouseReleased(double mouseX, double mouseY, int button) {
      this.dragging = false;
      this.dragX = 0;
      this.dragY = 0;
      return true;
   }

   public abstract void drawDraggable(class_332 var1);

   public void drawRect(float x, float y, float width, float height) {
      Render2D.drawQuad(x, y, width, height, ColorAssist.getText(0.5F));
   }

   public void stopAnimation() {
      this.scaleAnimation.setDirection(Direction.BACKWARDS);
   }

   public void startAnimation() {
      this.scaleAnimation.setDirection(Direction.FORWARDS);
   }

   public void validPosition() {
      if (this.x + this.width > window.method_4486()) {
         this.x = window.method_4486() - this.width;
      }

      if (this.y + this.height > window.method_4502()) {
         this.y = window.method_4502() - this.height;
      }

      if (this.y < 0) {
         this.y = 0;
      }

      if (this.x < 0) {
         this.x = 0;
      }

   }

   public boolean isHovered(double mouseX, double mouseY) {
      return mouseX >= (double)this.x && mouseX <= (double)(this.x + this.width) && mouseY >= (double)this.y && mouseY <= (double)(this.y + this.height);
   }

   public boolean isCloseAnimationFinished() {
      return this.scaleAnimation.isFinished(Direction.BACKWARDS);
   }

   public boolean canDraw(Hud hud, AbstractDraggable draggable) {
      return hud.isState() && hud.interfaceSettings.isSelected(draggable.getName()) && this.visible();
   }

   public void setName(String name) {
      this.name = name;
   }

   public void setX(int x) {
      this.x = x;
   }

   public void setY(int y) {
      this.y = y;
   }

   public void setWidth(int width) {
      this.width = width;
   }

   public void setHeight(int height) {
      this.height = height;
   }

   public void setDragging(boolean dragging) {
      this.dragging = dragging;
   }

   public void setCanDrag(boolean canDrag) {
      this.canDrag = canDrag;
   }

   public void setDragX(int dragX) {
      this.dragX = dragX;
   }

   public void setDragY(int dragY) {
      this.dragY = dragY;
   }

   public String getName() {
      return this.name;
   }

   public int getX() {
      return this.x;
   }

   public int getY() {
      return this.y;
   }

   public int getWidth() {
      return this.width;
   }

   public int getHeight() {
      return this.height;
   }

   public boolean isDragging() {
      return this.dragging;
   }

   public boolean isCanDrag() {
      return this.canDrag;
   }

   public int getDragX() {
      return this.dragX;
   }

   public int getDragY() {
      return this.dragY;
   }

   public Animation getScaleAnimation() {
      return this.scaleAnimation;
   }
}

