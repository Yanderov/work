package dev.client.ui.draggable;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public interface IDraggable {
   int getX();

   int getY();

   int getHeight();

   int getWeight();

   int getMouseX();

   int getMouseY();

   void setX(int x);

   void setY(int y);

   void setHeight(int height);

   void setWeight(int weight);

   void setMouseX(int mouseX);

   void setMouseY(int mouseY);

   boolean drag();

   void mouseClick();

   void setNum(int num);

   void endDrag();
}
