package fun.Yanderov.features.impl.render;

import fun.Yanderov.events.render.DrawEvent;
import fun.Yanderov.events.render.WorldRenderEvent;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.features.module.setting.Setting;
import fun.Yanderov.features.module.setting.implement.BooleanSetting;
import fun.Yanderov.features.module.setting.implement.SliderSettings;
import fun.Yanderov.utils.client.Instance;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import java.util.Objects;
import net.minecraft.class_4587;

public class GlassHands extends Module {
   private final BooleanSetting glow = (new BooleanSetting("Glow", "ÐŸÐ¾Ð´ÑÐ²ÐµÑ‚ÐºÐ° Ñ€ÑƒÐº")).setValue(true);
   private final SliderSettings glowSize;
   private final SliderSettings glowSaturation;
   private final BooleanSetting blur;
   private final SliderSettings blurSaturation;
   private final SliderSettings blurSize;

   public static GlassHands getInstance() {
      return (GlassHands)Instance.get(GlassHands.class);
   }

   public GlassHands() {
      super("GlassHands", "Glass Hands", ModuleCategory.RENDER);
      SliderSettings var10001 = (new SliderSettings("Glow Size", "Ð¡Ð¸Ð»Ð° Ð¿Ð¾Ð´ÑÐ²ÐµÑ‚ÐºÐ¸")).setValue(4.0F).range(1.0F, 5.0F);
      BooleanSetting var10002 = this.glow;
      Objects.requireNonNull(var10002);
      this.glowSize = var10001.visible(var10002::isValue);
      var10001 = (new SliderSettings("Glow Saturation", "Ð¯Ñ€ÐºÐ¾ÑÑ‚ÑŒ Ð¿Ð¾Ð´ÑÐ²ÐµÑ‚ÐºÐ¸")).setValue(1.0F).range(0.0F, 4.0F);
      var10002 = this.glow;
      Objects.requireNonNull(var10002);
      this.glowSaturation = var10001.visible(var10002::isValue);
      this.blur = (new BooleanSetting("Blur", "Ð Ð°Ð·Ð¼Ñ‹Ñ‚Ð¸Ðµ Ñ€ÑƒÐº")).setValue(true);
      var10001 = (new SliderSettings("Blur Saturation", "Ð¯Ñ€ÐºÐ¾ÑÑ‚ÑŒ Ð±Ð»ÑŽÑ€Ð°")).setValue(1.0F).range(0.0F, 2.0F);
      var10002 = this.blur;
      Objects.requireNonNull(var10002);
      this.blurSaturation = var10001.visible(var10002::isValue);
      var10001 = (new SliderSettings("Blur Size", "Ð¡Ð¸Ð»Ð° Ð±Ð»ÑŽÑ€Ð°")).setValue(4.0F).range(1.0F, 5.0F);
      var10002 = this.blur;
      Objects.requireNonNull(var10002);
      this.blurSize = var10001.visible(var10002::isValue);
      this.setup(new Setting[]{this.glow, this.glowSize, this.glowSaturation, this.blur, this.blurSaturation, this.blurSize});
   }

   @EventHandler
   public void onWorldRender(WorldRenderEvent event) {
      if (this.isState()) {
         if (this.glow.isValue() || this.blur.isValue()) {
            ;
         }
      }
   }

   @EventHandler
   public void onDraw(DrawEvent event) {
      if (this.isState()) {
         if (this.glow.isValue() || this.blur.isValue()) {
            class_4587 matrices = event.getDrawContext().method_51448();
         }
      }
   }

   public BooleanSetting glow() {
      return this.glow;
   }

   public SliderSettings glowSize() {
      return this.glowSize;
   }

   public SliderSettings glowSaturation() {
      return this.glowSaturation;
   }

   public BooleanSetting blur() {
      return this.blur;
   }

   public SliderSettings blurSaturation() {
      return this.blurSaturation;
   }

   public SliderSettings blurSize() {
      return this.blurSize;
   }
}

