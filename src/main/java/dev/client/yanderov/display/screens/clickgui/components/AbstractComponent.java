package dev.client.yanderov.display.screens.clickgui.components;

import dev.client.yanderov.utils.client.interfaces.ResizableMovable;
import dev.client.yanderov.utils.display.interfaces.QuickImports;

public abstract class AbstractComponent implements Component, QuickImports, ResizableMovable {
   public float x;
   public float y;
   public float width;
   public float height;
   public double scroll = (double)0.0F;
   public double smoothedScroll = (double)0.0F;

   public ResizableMovable position(float x, float y) {
      this.x = x;
      this.y = y;
      return this;
   }

   public ResizableMovable size(float width, float height) {
      this.width = width;
      this.height = height;
      return this;
   }

   public void tick() {
   }

   public boolean mouseClicked(double mouseX, double mouseY, int button) {
      return false;
   }

   public boolean mouseReleased(double mouseX, double mouseY, int button) {
      return false;
   }

   public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
      return false;
   }

   public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
      return false;
   }

   public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
      return false;
   }

   public boolean charTyped(char chr, int modifiers) {
      return false;
   }

   public boolean isHover(double mouseX, double mouseY) {
      return false;
   }
}

