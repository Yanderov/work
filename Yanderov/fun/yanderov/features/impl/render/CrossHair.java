package fun.Yanderov.features.impl.render;

import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.features.module.setting.Setting;
import fun.Yanderov.features.module.setting.implement.BooleanSetting;
import fun.Yanderov.features.module.setting.implement.SliderSettings;
import fun.Yanderov.utils.client.Instance;
import fun.Yanderov.utils.display.color.ColorAssist;
import fun.Yanderov.utils.display.geometry.Render2D;
import fun.Yanderov.utils.math.calc.Calculate;
import net.minecraft.class_3966;

public class CrossHair extends Module {
   private float red = 0.0F;
   private float animatedIndent = 0.0F;
   private final SliderSettings attackSetting = (new SliderSettings("ÐžÑ‚ÑÑ‚ÑƒÐ¿ Ð°Ñ‚Ð°ÐºÐ¸", "ÐžÑ‚ÑÑ‚ÑƒÐ¿ Ð´Ð»Ñ ÐºÑƒÐ»Ð´Ð°ÑƒÐ½Ð° Ð¿Ñ€ÐµÐ´Ð¼ÐµÑ‚Ð°")).setValue(10.0F).range(0, 20);
   private final SliderSettings indentSetting = (new SliderSettings("ÐžÑ‚ÑÑ‚ÑƒÐ¿", "ÐžÑ‚ÑÑ‚ÑƒÐ¿ Ð¾Ñ‚ Ñ†ÐµÐ½Ñ‚Ñ€Ð° ÑÐºÑ€Ð°Ð½Ð°")).setValue(0.0F).range(0, 5);
   private final SliderSettings size1Setting = (new SliderSettings("Ð¨Ð¸Ñ€Ð¸Ð½Ð°", "Ð¨Ð¸Ñ€Ð¸Ð½Ð° Ð¿Ñ€Ð¸Ñ†ÐµÐ»Ð°")).setValue(4.0F).range(2, 10);
   private final SliderSettings size2Setting = (new SliderSettings("Ð’Ñ‹ÑÐ¾Ñ‚Ð°", "Ð’Ñ‹ÑÐ¾Ñ‚Ð° Ð¿Ñ€Ð¸Ñ†ÐµÐ»Ð°")).setValue(1.0F).range(1, 4);
   private final BooleanSetting noOutline = (new BooleanSetting("Ð‘ÐµÐ· Ð¾Ð±Ð²Ð¾Ð´ÐºÐ¸", "Ð£Ð±Ñ€Ð°Ñ‚ÑŒ Ñ‡ÐµÑ€Ð½ÑƒÑŽ Ð¾Ð±Ð²Ð¾Ð´ÐºÑƒ")).setValue(false);

   public static CrossHair getInstance() {
      return (CrossHair)Instance.get(CrossHair.class);
   }

   public CrossHair() {
      super("CrossHair", "Cross Hair", ModuleCategory.RENDER);
      this.setup(new Setting[]{this.attackSetting, this.indentSetting, this.size1Setting, this.size2Setting, this.noOutline});
   }

   public void onRenderCrossHair() {
      this.red = Calculate.interpolateSmooth((double)3.0F, this.red, mc.field_1765 instanceof class_3966 ? 5.0F : 1.0F);
      int firstColor = ColorAssist.multRed(ColorAssist.WHITE, this.red);
      int secondColor = ColorAssist.BLACK;
      float x = (float)window.method_4486() / 2.0F;
      float y = (float)window.method_4502() / 2.0F;
      float attackOffset = (float)this.attackSetting.getInt();
      float progress = mc.field_1724.method_7261(tickCounter.method_60637(false));
      float rawCooldown = attackOffset - attackOffset * progress;
      float targetIndent = (float)this.indentSetting.getInt() + rawCooldown;
      float speed = targetIndent > this.animatedIndent ? 4.0F : 2.0F;
      this.animatedIndent = Calculate.interpolateSmooth((double)speed, this.animatedIndent, targetIndent);
      float size = this.size1Setting.getValue();
      float size2 = this.size2Setting.getValue();
      float offset = size2 / 2.0F;
      float indent = this.animatedIndent;
      if (!this.noOutline.isValue()) {
         this.renderMain(x, y, size, size2, 1.0F, indent, offset, secondColor);
      }

      this.renderMain(x, y, size, size2, 0.0F, indent, offset, firstColor);
   }

   private void renderMain(float x, float y, float size, float size2, float padding, float indent, float offset, int color) {
      Render2D.drawQuad(x - offset - padding / 2.0F, y - size - indent - padding / 2.0F, size2 + padding, size + padding, color);
      Render2D.drawQuad(x - offset - padding / 2.0F, y + indent - padding / 2.0F, size2 + padding, size + padding, color);
      Render2D.drawQuad(x - size - indent - padding / 2.0F, y - offset - padding / 2.0F, size + padding, size2 + padding, color);
      Render2D.drawQuad(x + indent - padding / 2.0F, y - offset - padding / 2.0F, size + padding, size2 + padding, color);
   }
}

