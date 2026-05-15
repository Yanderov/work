package fun.Yanderov.display.screens.clickgui.components.implement.module;

import com.google.common.base.Suppliers;
import fun.Yanderov.Yanderov;
import fun.Yanderov.common.animation.Animation;
import fun.Yanderov.common.animation.Direction;
import fun.Yanderov.common.animation.implement.Decelerate;
import fun.Yanderov.display.screens.clickgui.components.AbstractComponent;
import fun.Yanderov.display.screens.clickgui.components.implement.settings.AbstractSettingComponent;
import fun.Yanderov.display.screens.clickgui.components.implement.settings.BindComponent;
import fun.Yanderov.display.screens.clickgui.components.implement.settings.CheckboxComponent;
import fun.Yanderov.display.screens.clickgui.components.implement.settings.ColorComponent;
import fun.Yanderov.display.screens.clickgui.components.implement.settings.GroupComponent;
import fun.Yanderov.display.screens.clickgui.components.implement.settings.SButtonComponent;
import fun.Yanderov.display.screens.clickgui.components.implement.settings.SliderComponent;
import fun.Yanderov.display.screens.clickgui.components.implement.settings.TextComponent;
import fun.Yanderov.display.screens.clickgui.components.implement.settings.multiselect.MultiSelectComponent;
import fun.Yanderov.display.screens.clickgui.components.implement.settings.select.SelectComponent;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.setting.SettingComponentAdder;
import fun.Yanderov.utils.client.chat.StringHelper;
import fun.Yanderov.utils.display.atlasfont.msdf.MsdfFont;
import fun.Yanderov.utils.display.color.ColorAssist;
import fun.Yanderov.utils.display.font.Fonts;
import fun.Yanderov.utils.display.scissor.ScissorAssist;
import fun.Yanderov.utils.display.shape.ShapeProperties;
import fun.Yanderov.utils.display.systemrender.builders.Builder;
import fun.Yanderov.utils.display.systemrender.renderers.impl.BuiltText;
import fun.Yanderov.utils.math.calc.Calculate;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import net.minecraft.class_332;
import net.minecraft.class_4587;
import org.joml.Matrix4f;

public class PanelsModuleComponent extends AbstractComponent {
   private final Module module;
   private final List settingComponents = new ArrayList();
   private static final float ROW_H = 15.0F;
   private static final float HEADER_TEXT_X = 7.0F;
   private static final float HEADER_TEXT_Y = 5.5F;
   private static final float HEADER_ARROW_X_OFFSET = 12.0F;
   private static final float HEADER_ARROW_Y = 4.0F;
   private static final float HEADER_RADIUS = 3.0F;
   private static final float OUTLINE_THICKNESS = 1.5F;
   private static final float DIVIDER_MARGIN_X = 5.0F;
   private static final float DIVIDER_HEIGHT = 0.5F;
   private static final float DIVIDER_Y_OFFSET = 2.0F;
   private static final float SETTINGS_TOP_PADDING = 2.0F;
   private static final float SETTINGS_BOTTOM_PADDING = 8.0F;
   private static final float SETTINGS_RENDER_THRESHOLD = 0.65F;
   private static final float BIND_PANEL_H = 40.0F;
   private static final int EXPAND_ANIMATION_MS = 281;
   private static final Supplier ICOLIST_FONT = Suppliers.memoize(() -> MsdfFont.builder().atlas("icolist").data("icolist").build());
   private boolean expanded = false;
   private boolean bindExpanded = false;
   private boolean binding = false;
   private long lastBindToggleMs = 0L;
   private float lastExpandedHeight = 23.0F;
   private final Animation expandAnimation = (new Decelerate()).setMs(281).setValue((double)1.0F);
   private final Animation hoverAnimation = (new Decelerate()).setMs(140).setValue((double)1.0F);

   public PanelsModuleComponent(Module module) {
      this.module = module;
      (new SettingComponentAdder()).addSettingComponent(module.settings(), this.settingComponents);
      this.expandAnimation.setDirection(Direction.BACKWARDS);
      this.expandAnimation.reset();
      this.hoverAnimation.setDirection(Direction.BACKWARDS);
      this.hoverAnimation.reset();
   }

   public Module getModule() {
      return this.module;
   }

   private float estimateSettingHeight(AbstractSettingComponent c) {
      if (c instanceof CheckboxComponent) {
         return 15.0F;
      } else if (c instanceof BindComponent) {
         return 15.0F;
      } else if (c instanceof ColorComponent) {
         return 15.0F;
      } else if (c instanceof TextComponent) {
         return 15.0F;
      } else if (c instanceof SliderComponent) {
         return 20.0F;
      } else if (c instanceof GroupComponent) {
         return 15.0F;
      } else if (c instanceof SButtonComponent) {
         return 15.0F;
      } else if (c instanceof SelectComponent) {
         return 15.0F;
      } else {
         return c instanceof MultiSelectComponent ? 15.0F : 15.0F;
      }
   }

   private float getExpandedHeight() {
      float h = 15.0F;
      if (!this.expanded && this.bindExpanded) {
         h += 40.0F;
      }

      if (!this.expanded) {
         return !this.expanded && this.expandAnimation.isDirection(Direction.BACKWARDS) && !this.expandAnimation.isDone() ? this.lastExpandedHeight : h + 8.0F;
      } else {
         for(AbstractSettingComponent c : this.settingComponents) {
            Supplier<Boolean> visible = c.getSetting().getVisible();
            if (visible == null || (Boolean)visible.get()) {
               h += c.height > 0.0F ? c.height : this.estimateSettingHeight(c);
            }
         }

         this.lastExpandedHeight = h + 8.0F;
         return this.lastExpandedHeight;
      }
   }

   private float getAnimatedHeight() {
      boolean anyExpanded = this.expanded || this.bindExpanded;
      this.expandAnimation.setDirection(anyExpanded ? Direction.FORWARDS : Direction.BACKWARDS);
      if (this.module.settings().isEmpty() && !this.bindExpanded) {
         return 15.0F;
      } else {
         float targetH = this.getExpandedHeight();
         float t = this.expandAnimation.getOutput().floatValue();
         return 15.0F + (targetH - 15.0F) * t;
      }
   }

   public float getComponentHeight() {
      return this.getAnimatedHeight();
   }

   private boolean isSettingHover(AbstractSettingComponent c, double mouseX, double mouseY) {
      return Calculate.isHovered(mouseX, mouseY, (double)c.x, (double)c.y, (double)c.width, (double)c.height);
   }

   public void render(class_332 context, int mouseX, int mouseY, float delta) {
      class_4587 matrix = context.method_51448();
      Matrix4f positionMatrix = matrix.method_23760().method_23761();
      float rowH = 15.0F;
      float radius = 3.0F;
      float totalH = this.getAnimatedHeight();
      float t = this.expandAnimation.getOutput().floatValue();
      boolean anyExpanded = this.expanded || this.bindExpanded;
      boolean renderSettings = anyExpanded || t > 0.65F;
      boolean hovered = Calculate.isHovered((double)mouseX, (double)mouseY, (double)this.x, (double)this.y, (double)this.width, (double)rowH);
      this.hoverAnimation.setDirection(hovered ? Direction.FORWARDS : Direction.BACKWARDS);
      float hoverT = this.hoverAnimation.getOutput().floatValue();
      hoverT = Math.max(0.0F, Math.min(1.0F, hoverT));
      int baseAlpha = 220;
      int hoverAlpha = 210;
      float stateT = this.module.getAnimation().getOutput().floatValue();
      stateT = Math.max(0.0F, Math.min(1.0F, stateT));
      int textColor = ColorAssist.lerp(stateT, -8289392, -1);
      int arrowColor = ColorAssist.lerp(stateT, -8289392, -6315602);
      int a = (int)((float)baseAlpha + (float)(hoverAlpha - baseAlpha) * hoverT);
      int disabledBg = (new Color(16, 17, 27, a)).getRGB();
      int enabledBg = (new Color(26, 28, 40, a)).getRGB();
      int moduleBgColor = ColorAssist.lerp(stateT, disabledBg, enabledBg);
      int outline = (new Color(63, 65, 75, 200)).getRGB();
      if (totalH > rowH + 0.5F) {
         blur.render(ShapeProperties.create(matrix, (double)this.x, (double)this.y, (double)this.width, (double)totalH).thickness(1.5F).quality(64.0F).round(radius).outlineColor(outline).color(moduleBgColor).build());
         blur.render(ShapeProperties.create(matrix, (double)(this.x + 5.0F), (double)(this.y + rowH + 2.0F), (double)(this.width - 10.0F), (double)0.5F).outlineColor(outline).color(moduleBgColor).build());
      } else {
         blur.render(ShapeProperties.create(matrix, (double)this.x, (double)this.y, (double)this.width, (double)rowH).thickness(1.5F).quality(64.0F).round(radius).outlineColor(outline).color(moduleBgColor).build());
      }

      Fonts.getSize(14, Fonts.Type.DEFAULT).drawString(matrix, this.module.getVisibleName(), (double)(this.x + 7.0F), (double)(this.y + 5.5F), textColor);
      if (!this.module.settings().isEmpty()) {
         String arrowIcon = this.expanded ? "B" : "H";
         ((BuiltText)Builder.text().font((MsdfFont)ICOLIST_FONT.get()).text(arrowIcon).size(6.0F).color(arrowColor).build()).render(positionMatrix, this.x + this.width - 12.0F, this.y + 4.0F);
      }

      if (totalH <= rowH + 0.5F) {
         this.height = rowH;
      } else if (!renderSettings) {
         this.height = totalH;
      } else {
         float visibleSettingsH = Math.max(0.0F, totalH - (rowH + 2.0F));
         ScissorAssist scissor = Yanderov.getInstance().getScissorManager();
         scissor.push(positionMatrix, this.x, this.y + rowH, this.width, rowH + 2.0F + visibleSettingsH);
         float yOff = rowH + 2.0F;
         if (!this.expanded && this.bindExpanded) {
            this.renderBindPanel(context, mouseX, mouseY, yOff);
            yOff += 40.0F;
         }

         if (this.expanded) {
            for(int i = 0; i < this.settingComponents.size(); ++i) {
               AbstractSettingComponent c = (AbstractSettingComponent)this.settingComponents.get(i);
               Supplier<Boolean> visible = c.getSetting().getVisible();
               if (visible == null || (Boolean)visible.get()) {
                  c.x = this.x - 2.0F;
                  c.y = this.y + yOff;
                  c.width = this.width + 2.0F;
                  c.render(context, mouseX, mouseY, delta);
                  yOff += c.height > 0.0F ? c.height : this.estimateSettingHeight(c);
               }
            }
         }

         scissor.pop();
         this.height = totalH;
      }
   }

   public boolean isHover(double mouseX, double mouseY) {
      return Calculate.isHovered(mouseX, mouseY, (double)this.x, (double)this.y, (double)this.width, (double)this.getComponentHeight());
   }

   public boolean mouseClicked(double mouseX, double mouseY, int button) {
      float rowH = 15.0F;
      boolean overHeader = Calculate.isHovered(mouseX, mouseY, (double)this.x, (double)this.y, (double)this.width, (double)rowH);
      float totalH = this.getAnimatedHeight();
      float t = this.expandAnimation.getOutput().floatValue();
      boolean renderSettings = this.expanded || this.bindExpanded || t > 0.65F;
      if (overHeader) {
         if (button == 2) {
            long now = System.currentTimeMillis();
            if (now - this.lastBindToggleMs < 200L) {
               return true;
            }

            this.lastBindToggleMs = now;
            this.expanded = false;
            this.bindExpanded = !this.bindExpanded;
            this.binding = false;
            return true;
         }

         if (button == 0) {
            this.module.switchState();
            return true;
         }

         if (button == 1) {
            if (!this.module.settings().isEmpty()) {
               this.expanded = !this.expanded;
               if (this.expanded) {
                  this.bindExpanded = false;
                  this.binding = false;
               }

               return true;
            }

            return false;
         }
      }

      if (renderSettings && totalH > rowH + 0.5F && mouseY <= (double)(this.y + totalH)) {
         if (!this.expanded && this.bindExpanded) {
            float bindY = this.y + rowH + 2.0F;
            if (Calculate.isHovered(mouseX, mouseY, (double)this.x, (double)bindY, (double)this.width, (double)40.0F) && this.handleBindPanelClick(mouseX, mouseY, button, bindY)) {
               return true;
            }
         }

         if (this.expanded) {
            boolean any = false;

            for(AbstractSettingComponent c : this.settingComponents) {
               Supplier<Boolean> visible = c.getSetting().getVisible();
               if ((visible == null || (Boolean)visible.get()) && this.isSettingHover(c, mouseX, mouseY)) {
                  any = true;
                  c.mouseClicked(mouseX, mouseY, button);
               }
            }

            if (any) {
               return true;
            }
         }
      }

      return false;
   }

   public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
      if (this.bindExpanded) {
         if (keyCode == 261) {
            this.module.setKey(-1);
            this.bindExpanded = true;
            this.binding = false;
            return true;
         }

         if (this.binding) {
            int key = keyCode == 256 ? -1 : keyCode;
            if (key != 344) {
               this.module.setKey(key);
               this.binding = false;
            }

            return true;
         }
      }

      if (this.expanded && this.getAnimatedHeight() > 15.5F) {
         for(AbstractSettingComponent c : this.settingComponents) {
            c.keyPressed(keyCode, scanCode, modifiers);
         }
      }

      return false;
   }

   private void renderBindPanel(class_332 context, int mouseX, int mouseY, float yOff) {
      class_4587 matrix = context.method_51448();
      float panelX = this.x;
      float panelY = this.y + yOff;
      float panelW = this.width;
      float panelH = 50.0F;
      rectangle.render(ShapeProperties.create(matrix, (double)panelX, (double)panelY, (double)panelW, (double)panelH).round(0.0F).softness(0.0F).thickness(0.0F).outlineColor(ColorAssist.getOutline(0.0F, 1.0F)).color(ColorAssist.getOutline(0.0F, 1.0F)).build());
      Fonts.getSize(15, Fonts.Type.DEFAULT).drawString(matrix, "Ð‘Ð¸Ð½Ð´", (double)(panelX + 5.0F), (double)(panelY + 6.3F), -2828575);
      Fonts.getSize(15, Fonts.Type.DEFAULT).drawString(matrix, "Ð ÐµÐ¶Ð¸Ð¼", (double)(panelX + 5.0F), (double)(panelY + 24.3F), -2828575);
      String keyName = StringHelper.getBindName(this.module.getKey());
      String shownKey = this.binding ? "(" + keyName + ")" : keyName;
      float keyBoxW = Math.max(14.0F, Fonts.getSize(14, Fonts.Type.DEFAULT).getStringWidth(shownKey) + 10.0F);
      float keyBoxX = panelX + panelW - keyBoxW - 5.0F;
      float keyBoxY = panelY + 1.8F;
      rectangle.render(ShapeProperties.create(matrix, (double)keyBoxX, (double)keyBoxY, (double)keyBoxW, (double)13.0F).round(2.0F).thickness(2.0F).softness(1.0F).outlineColor(ColorAssist.getOutline(0.8F, 1.0F)).color(ColorAssist.getOutline(0.1F, 1.0F)).build());
      int keyColor = this.binding ? -8288257 : -2828575;
      Fonts.getSize(14, Fonts.Type.DEFAULT).drawString(matrix, shownKey, (double)(keyBoxX + 5.0F), (double)(keyBoxY + 4.8F), keyColor);
      float toggleBoxX = panelX + panelW - 57.0F;
      float toggleBoxY = panelY + 20.0F;
      float toggleBoxW = 52.0F;
      float toggleBoxH = 13.0F;
      rectangle.render(ShapeProperties.create(matrix, (double)toggleBoxX, (double)toggleBoxY, (double)toggleBoxW, (double)toggleBoxH).round(2.0F).thickness(2.0F).softness(1.0F).outlineColor(ColorAssist.getOutline(0.8F, 1.0F)).color(ColorAssist.getOutline(0.1F, 1.0F)).build());
      if (this.module.getType() == 1) {
         rectangle.render(ShapeProperties.create(matrix, (double)(toggleBoxX + 23.0F), (double)toggleBoxY, (double)29.0F, (double)toggleBoxH).round(2.0F, 2.0F, 0.0F, 0.0F).color(-8288257).build());
      } else {
         rectangle.render(ShapeProperties.create(matrix, (double)toggleBoxX, (double)toggleBoxY, (double)23.0F, (double)toggleBoxH).round(0.0F, 0.0F, 2.0F, 2.0F).color(-8288257).build());
      }

      float halfW = toggleBoxW / 2.0F;
      String holdText = "HOLD";
      String toggleText = "TOGGLE";
      float holdTextW = Fonts.getSize(12).getStringWidth(holdText);
      float toggleTextW = Fonts.getSize(12).getStringWidth(toggleText);
      float holdTextX = toggleBoxX + (halfW - holdTextW) / 2.5F;
      float toggleTextX = toggleBoxX + halfW + (halfW - toggleTextW);
      float textY = toggleBoxY + 5.3F;
      Fonts.getSize(12).drawString(matrix, holdText, (double)holdTextX, (double)textY, -2828575);
      Fonts.getSize(12).drawString(matrix, toggleText, (double)toggleTextX, (double)textY, -2828575);
   }

   private boolean handleBindPanelClick(double mouseX, double mouseY, int button, float bindY) {
      if (button == 0) {
         float panelX = this.x;
         float panelW = this.width;
         String keyName = StringHelper.getBindName(this.module.getKey());
         String shownKey = this.binding ? "(" + keyName + ") ..." : keyName;
         float keyBoxW = Math.max(28.0F, Fonts.getSize(14, Fonts.Type.DEFAULT).getStringWidth(shownKey) + 10.0F);
         float keyBoxX = panelX + panelW - keyBoxW - 5.0F;
         float keyBoxY = bindY + 1.8F;
         if (Calculate.isHovered(mouseX, mouseY, (double)keyBoxX, (double)keyBoxY, (double)keyBoxW, (double)13.0F)) {
            this.binding = !this.binding;
            return true;
         }

         float toggleBoxX = panelX + panelW - 57.0F;
         float toggleBoxY = bindY + 20.0F;
         if (Calculate.isHovered(mouseX, mouseY, (double)toggleBoxX, (double)toggleBoxY, (double)52.0F, (double)13.0F)) {
            this.module.setType(this.module.getType() != 1 ? 1 : 0);
            return true;
         }
      }

      if (this.binding && button > 1) {
         this.module.setKey(button);
         this.binding = false;
         return true;
      } else {
         return false;
      }
   }

   public boolean charTyped(char chr, int modifiers) {
      if (this.expanded && this.getAnimatedHeight() > 15.5F) {
         for(AbstractSettingComponent c : this.settingComponents) {
            if (c.charTyped(chr, modifiers)) {
               return true;
            }
         }
      }

      return false;
   }
}

