package fun.Yanderov.display.screens.clickgui;

import fun.Yanderov.common.animation.Easy.Direction;
import fun.Yanderov.common.animation.Easy.EaseBackIn;
import fun.Yanderov.display.screens.clickgui.components.AbstractComponent;
import fun.Yanderov.display.screens.clickgui.components.implement.other.SearchComponent;
import fun.Yanderov.display.screens.clickgui.components.implement.panels.PanelsContainerComponent;
import fun.Yanderov.display.screens.clickgui.components.implement.settings.TextComponent;
import fun.Yanderov.features.impl.misc.SelfDestruct;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.utils.client.sound.SoundManager;
import fun.Yanderov.utils.display.interfaces.QuickImports;
import fun.Yanderov.utils.interactions.inv.InventoryFlowManager;
import fun.Yanderov.utils.math.calc.Calculate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.minecraft.class_2561;
import net.minecraft.class_332;
import net.minecraft.class_437;

public class MenuScreen extends class_437 implements QuickImports {
   public static MenuScreen INSTANCE = new MenuScreen();
   private static final int GUI_WIDTH = 400;
   private static final int GUI_HEIGHT = 280;
   private static final float PANELS_X_PADDING = 20.0F;
   private static final int OPEN_ANIMATION_MS = 325;
   private static final int CLOSE_ANIMATION_MS = 150;
   private static final float SEARCH_WIDTH = 160.0F;
   private static final float SEARCH_HEIGHT = 18.0F;
   private static final float SEARCH_Y_OFFSET = 14.0F;
   private final List components = new ArrayList();
   private final SearchComponent searchComponent = new SearchComponent();
   private final PanelsContainerComponent panelsContainerComponent = new PanelsContainerComponent();
   public final EaseBackIn animation = new EaseBackIn(325, (double)1.0F, 1.5F);
   public ModuleCategory category;
   public int x;
   public int y;
   public int width;
   public int height;
   private double lastTransformedMouseX;
   private double lastTransformedMouseY;

   public void initialize() {
      this.animation.setDuration(325);
      this.animation.setDirection(Direction.FORWARDS);
      this.components.clear();
      this.components.addAll(Arrays.asList(this.searchComponent, this.panelsContainerComponent));
   }

   public MenuScreen() {
      super(class_2561.method_30163("MenuScreen"));
      this.category = ModuleCategory.COMBAT;
      this.lastTransformedMouseX = (double)0.0F;
      this.lastTransformedMouseY = (double)0.0F;
      this.initialize();
   }

   public void method_25393() {
      this.method_25419();
      InventoryFlowManager.canMove = true;
      if (!TextComponent.typing && !SearchComponent.typing) {
         InventoryFlowManager.updateMoveKeys();
      } else {
         InventoryFlowManager.unPressMoveKeys();
      }

      this.components.forEach(AbstractComponent::tick);
      super.method_25393();
   }

   private double[] transformMouseCoords(double mouseX, double mouseY) {
      float scale = this.getScaleAnimation();
      if (scale <= 0.01F) {
         scale = 1.0F;
      }

      float centerX = (float)this.x + (float)this.width / 2.0F;
      float centerY = (float)this.y + (float)this.height / 2.0F;
      double transformedX = (mouseX - (double)centerX) / (double)scale + (double)centerX;
      double transformedY = (mouseY - (double)centerY) / (double)scale + (double)centerY;
      return new double[]{transformedX, transformedY};
   }

   private void layout(int windowWidth, int windowHeight) {
      this.width = 400;
      this.height = 280;
      this.x = windowWidth / 2 - this.width / 2;
      this.y = windowHeight / 2 - this.height / 2;
      this.panelsContainerComponent.position((float)this.x - 20.0F, (float)this.y).size((float)this.width + 40.0F, (float)this.height);
      this.searchComponent.size(160.0F, 18.0F);
      this.searchComponent.position((float)this.x + (float)this.width / 2.0F - 80.0F, (float)(this.y + this.height) + 14.0F);
   }

   public void method_25394(class_332 context, int mouseX, int mouseY, float delta) {
      this.layout(window.method_4486(), window.method_4502());
      double[] transformed = this.transformMouseCoords((double)mouseX, (double)mouseY);
      this.lastTransformedMouseX = transformed[0];
      this.lastTransformedMouseY = transformed[1];
      Calculate.scale(context.method_51448(), (float)this.x + (float)this.width / 2.0F, (float)this.y + (float)this.height / 2.0F, this.getScaleAnimation(), () -> {
         this.components.forEach((component) -> component.render(context, (int)this.lastTransformedMouseX, (int)this.lastTransformedMouseY, delta));
         windowManager.render(context, (int)this.lastTransformedMouseX, (int)this.lastTransformedMouseY, delta);
      });
      super.method_25394(context, mouseX, mouseY, delta);
   }

   public void openGui() {
      if (!SelfDestruct.unhooked) {
         this.animation.setDuration(325);
         this.animation.setDirection(Direction.FORWARDS);
         this.animation.reset();
         mc.method_1507(this);
         SoundManager.playSound(SoundManager.OPEN_GUI);
      }
   }

   public float getScaleAnimation() {
      return (float)this.animation.getOutput();
   }

   private double[] getTransformedMouseCoords(double mouseX, double mouseY) {
      return this.transformMouseCoords(mouseX, mouseY);
   }

   public boolean method_25402(double mouseX, double mouseY, int button) {
      double[] transformed = this.getTransformedMouseCoords(mouseX, mouseY);
      boolean windowHandled = windowManager.mouseClicked(transformed[0], transformed[1], button);
      if (!windowHandled) {
         for(AbstractComponent component : this.components) {
            component.mouseClicked(transformed[0], transformed[1], button);
         }
      }

      return super.method_25402(mouseX, mouseY, button);
   }

   public boolean method_25406(double mouseX, double mouseY, int button) {
      double[] transformed = this.getTransformedMouseCoords(mouseX, mouseY);

      for(AbstractComponent component : this.components) {
         component.mouseReleased(transformed[0], transformed[1], button);
      }

      windowManager.mouseReleased(transformed[0], transformed[1], button);
      return super.method_25406(mouseX, mouseY, button);
   }

   public boolean method_25403(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
      double[] transformed = this.getTransformedMouseCoords(mouseX, mouseY);
      boolean windowHandled = windowManager.mouseDragged(transformed[0], transformed[1], button, deltaX, deltaY);
      if (!windowHandled) {
         for(AbstractComponent component : this.components) {
            component.mouseDragged(transformed[0], transformed[1], button, deltaX, deltaY);
         }
      }

      return super.method_25403(mouseX, mouseY, button, deltaX, deltaY);
   }

   public boolean method_25401(double mouseX, double mouseY, double horizontal, double vertical) {
      double[] transformed = this.getTransformedMouseCoords(mouseX, mouseY);
      boolean windowHandled = windowManager.mouseScrolled(transformed[0], transformed[1], vertical);
      if (!windowHandled) {
         for(AbstractComponent component : this.components) {
            component.mouseScrolled(transformed[0], transformed[1], vertical);
         }
      }

      return super.method_25401(mouseX, mouseY, horizontal, vertical);
   }

   public boolean method_25404(int keyCode, int scanCode, int modifiers) {
      boolean handled = windowManager.keyPressed(keyCode, scanCode, modifiers);
      if (!handled) {
         for(AbstractComponent component : this.components) {
            if (component.keyPressed(keyCode, scanCode, modifiers)) {
               handled = true;
               break;
            }
         }
      }

      if (handled) {
         return true;
      } else if (keyCode == 256 && this.method_25422()) {
         SoundManager.playSound(SoundManager.CLOSE_GUI);
         this.animation.setDuration(150);
         this.animation.setDirection(Direction.BACKWARDS);
         return true;
      } else {
         return super.method_25404(keyCode, scanCode, modifiers);
      }
   }

   public boolean method_25400(char chr, int modifiers) {
      if (!windowManager.charTyped(chr, modifiers)) {
         for(AbstractComponent component : this.components) {
            component.charTyped(chr, modifiers);
         }
      }

      return super.method_25400(chr, modifiers);
   }

   public boolean method_25421() {
      return false;
   }

   public void method_25419() {
      if (this.animation.finished(Direction.BACKWARDS)) {
         TextComponent.typing = false;
         SearchComponent.typing = false;
         this.animation.setDuration(325);
         super.method_25419();
      }

   }

   public void setCategory(ModuleCategory category) {
      this.category = category;
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

   public void setLastTransformedMouseX(double lastTransformedMouseX) {
      this.lastTransformedMouseX = lastTransformedMouseX;
   }

   public void setLastTransformedMouseY(double lastTransformedMouseY) {
      this.lastTransformedMouseY = lastTransformedMouseY;
   }

   public List getComponents() {
      return this.components;
   }

   public SearchComponent getSearchComponent() {
      return this.searchComponent;
   }

   public PanelsContainerComponent getPanelsContainerComponent() {
      return this.panelsContainerComponent;
   }

   public EaseBackIn getAnimation() {
      return this.animation;
   }

   public ModuleCategory getCategory() {
      return this.category;
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

   public double getLastTransformedMouseX() {
      return this.lastTransformedMouseX;
   }

   public double getLastTransformedMouseY() {
      return this.lastTransformedMouseY;
   }
}

