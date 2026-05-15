package dev.client.yanderov.display.screens.clickgui.components.implement.other;

import dev.client.yanderov.display.screens.clickgui.components.AbstractComponent;
import dev.client.yanderov.display.screens.clickgui.components.implement.category.CategoryComponent;
import dev.client.yanderov.display.screens.clickgui.components.implement.settings.TextComponent;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.utils.interactions.inv.InventoryFlowManager;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_332;

public class CategoryContainerComponent extends AbstractComponent {
   private final List categoryComponents = new ArrayList();

   public void initializeCategoryComponents() {
      this.categoryComponents.clear();

      for(ModuleCategory category : ModuleCategory.values()) {
         this.categoryComponents.add(new CategoryComponent(category));
      }

   }

   public void render(class_332 context, int mouseX, int mouseY, float delta) {
      float offset = 0.0F;

      for(CategoryComponent component : this.categoryComponents) {
         component.x = this.x + 6.0F;
         component.y = this.y + 40.0F + offset;
         component.width = 73.0F;
         component.height = 17.0F;
         component.render(context, mouseX, mouseY, delta);
         offset += component.height + 12.0F;
      }

   }

   public void tick() {
      if (!TextComponent.typing && !SearchComponent.typing) {
         InventoryFlowManager.updateMoveKeys();
      } else {
         InventoryFlowManager.unPressMoveKeys();
      }

      this.categoryComponents.forEach(AbstractComponent::tick);
      super.tick();
   }

   public boolean mouseClicked(double mouseX, double mouseY, int button) {
      this.categoryComponents.forEach((categoryComponent) -> categoryComponent.mouseClicked(mouseX, mouseY, button));
      return super.mouseClicked(mouseX, mouseY, button);
   }

   public boolean mouseReleased(double mouseX, double mouseY, int button) {
      this.categoryComponents.forEach((categoryComponent) -> categoryComponent.mouseReleased(mouseX, mouseY, button));
      return super.mouseReleased(mouseX, mouseY, button);
   }

   public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
      this.categoryComponents.forEach((categoryComponent) -> categoryComponent.mouseDragged(mouseX, mouseY, button, deltaX, deltaY));
      return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
   }

   public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
      this.categoryComponents.forEach((categoryComponent) -> categoryComponent.mouseScrolled(mouseX, mouseY, amount));
      return super.mouseScrolled(mouseX, mouseY, amount);
   }

   public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
      this.categoryComponents.forEach((categoryComponent) -> categoryComponent.keyPressed(keyCode, scanCode, modifiers));
      return super.keyPressed(keyCode, scanCode, modifiers);
   }

   public boolean charTyped(char chr, int modifiers) {
      this.categoryComponents.forEach((categoryComponent) -> categoryComponent.charTyped(chr, modifiers));
      return super.charTyped(chr, modifiers);
   }
}

