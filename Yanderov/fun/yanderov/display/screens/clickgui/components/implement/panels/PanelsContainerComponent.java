package fun.Yanderov.display.screens.clickgui.components.implement.panels;

import fun.Yanderov.display.screens.clickgui.components.AbstractComponent;
import fun.Yanderov.features.module.ModuleCategory;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import net.minecraft.class_332;

public class PanelsContainerComponent extends AbstractComponent {
   private static final float PANEL_WIDTH = 126.0F;
   private static final float PANEL_HEIGHT = 265.0F;
   private static final float PANEL_GAP = 6.0F;
   private static final float TOP_PADDING = 10.0F;
   private final List panels = new ArrayList();

   public PanelsContainerComponent() {
      for(ModuleCategory category : EnumSet.of(ModuleCategory.COMBAT, ModuleCategory.MOVEMENT, ModuleCategory.RENDER, ModuleCategory.PLAYER, ModuleCategory.MISC)) {
         this.panels.add(new CategoryPanelComponent(category));
      }

   }

   public void render(class_332 context, int mouseX, int mouseY, float delta) {
      float totalWidth = (float)this.panels.size() * 126.0F + (float)(this.panels.size() - 1) * 6.0F;
      float startX = this.x + (this.width - totalWidth) / 2.0F;
      float startY = this.y + 10.0F;

      for(int i = 0; i < this.panels.size(); ++i) {
         CategoryPanelComponent panel = (CategoryPanelComponent)this.panels.get(i);
         panel.position(startX + (float)i * 132.0F, startY).size(126.0F, 265.0F);
         panel.render(context, mouseX, mouseY, delta);
      }

   }

   public boolean mouseClicked(double mouseX, double mouseY, int button) {
      for(CategoryPanelComponent panel : this.panels) {
         if (panel.mouseClicked(mouseX, mouseY, button)) {
            return true;
         }
      }

      return false;
   }

   public boolean mouseReleased(double mouseX, double mouseY, int button) {
      for(CategoryPanelComponent panel : this.panels) {
         if (panel.mouseReleased(mouseX, mouseY, button)) {
            return true;
         }
      }

      return false;
   }

   public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
      for(CategoryPanelComponent panel : this.panels) {
         if (panel.mouseScrolled(mouseX, mouseY, amount)) {
            return true;
         }
      }

      return false;
   }

   public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
      for(CategoryPanelComponent panel : this.panels) {
         if (panel.keyPressed(keyCode, scanCode, modifiers)) {
            return true;
         }
      }

      return false;
   }

   public boolean charTyped(char chr, int modifiers) {
      for(CategoryPanelComponent panel : this.panels) {
         if (panel.charTyped(chr, modifiers)) {
            return true;
         }
      }

      return false;
   }
}

