package dev.client.mixins.hooks;

import dev.client.util.render.MenuBackgroundRenderer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.screen.world.CreateWorldScreen;
import net.minecraft.client.gui.screen.world.SelectWorldScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(Screen.class)
public abstract class ScreenBackgroundMixin {
   @Shadow
   public int width;

   @Shadow
   public int height;

   @Inject(method = "renderBackground", at = @At("RETURN"))
   private void renderWildBackground(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
      Screen screen = (Screen)(Object)this;
      if (screen instanceof OptionsScreen
         || screen instanceof MultiplayerScreen
         || screen instanceof SelectWorldScreen
         || screen instanceof CreateWorldScreen) {
         MenuBackgroundRenderer.render(context, this.width, this.height, 1.0D);
      }
   }
}
