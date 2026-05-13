package dev.client.ui.mainmenu;

import dev.client.WildClient;
import dev.client.managers.FontManager;
import dev.client.ui.mainmenu.button.Button;
import dev.client.util.animations.Animation;
import dev.client.util.animations.Direction;
import dev.client.util.animations.impl.EaseBackIn;
import dev.client.util.color.ColorUtil;
import dev.client.util.math.MouseUtil;
import dev.client.util.render.AnimationUtil;
import dev.client.util.render.MenuBackgroundRenderer;
import dev.client.util.render.Scissor;
import dev.client.util.render.builders.Builder;
import dev.client.util.render.builders.states.QuadColorState;
import dev.client.util.render.builders.states.QuadRadiusState;
import dev.client.util.render.builders.states.SizeState;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.screen.world.SelectWorldScreen;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
import org.joml.Matrix4f;

@Environment(EnvType.CLIENT)
public class MainScreen extends Screen {
   private final Button singlePlayerButton = new Button("Singleplayer", "Z", () -> MinecraftClient.getInstance().setScreen(new SelectWorldScreen(WildClient.INSTANCE.getMainScreen())));
   private final Button multiPlayerButton = new Button("Multiplayer", "K", () -> MinecraftClient.getInstance().setScreen(new MultiplayerScreen(WildClient.INSTANCE.getMainScreen())));
   private final Button altManagerButton = new Button("Altmanager", "A", () -> MinecraftClient.getInstance().setScreen(WildClient.INSTANCE.getAltManagerScreen()));
   private final Button exitButton = new Button("Exit", "J", () -> MinecraftClient.getInstance().scheduleStop());
   private final Button options = new Button("Options", "H", () -> MinecraftClient.getInstance().setScreen(new OptionsScreen(WildClient.INSTANCE.getMainScreen(), MinecraftClient.getInstance().options)));
   private final Animation animation;
   private final List<Button> buttons = new ArrayList<>();
   private boolean init = false;

   public MainScreen() {
      super(Text.of(""));
      this.animation = new EaseBackIn(400, 1.0D, 0.1F, Direction.FORWARDS);
   }

   protected void init() {
      this.animation.reset();
      this.animation.setDirection(Direction.FORWARDS);
      if (!this.init) {
         WildClient.INSTANCE.getModuleManager().initDesc();

         try {
            WildClient.INSTANCE.getConfigManager().loadConfig("default");
            WildClient.INSTANCE.getConfigManager().loadDraggables();
            WildClient.INSTANCE.getConfigManager().loadTheme();
            WildClient.INSTANCE.getConfigManager().loadNickNames();
            MinecraftClient.getInstance().options.getGuiScale().setValue(2);
         } catch (Exception exception) {
         }

         this.init = true;
      }

      this.buttons.clear();
      this.buttons.add(this.exitButton);
      this.buttons.add(this.options);
      this.buttons.add(this.altManagerButton);
      this.buttons.add(this.multiPlayerButton);
      this.buttons.add(this.singlePlayerButton);

      for(int i = 0; i < this.buttons.size(); ++i) {
         Button button = (Button)this.buttons.get(i);
         button.updatePos((double)(this.width / 2) - button.getWidth() / 2.0D, (double)(this.height / 2) - button.getHeight() / 2.0D - (double)(i * 33) + 100.0D);
      }

      super.init();
   }

   public void render(DrawContext context, int mouseX, int mouseY, float delta) {
      Matrix4f matrix4f = context.getMatrices().peek().getPositionMatrix();
      AnimationUtil.sizeAnimation(context, (double)(this.width / 2), (double)(this.height / 2), 1.35D - this.animation.getOutput() * 0.35);
      Builder.rectangle().size(new SizeState((float)this.width, (float)this.height)).color(new QuadColorState(Color.black)).radius(new QuadRadiusState(0.0F)).smoothness(1.15F).build().render(matrix4f, 0.0F, 0.0F);
      MenuBackgroundRenderer.render(context, this.width, this.height, 0.45D);
      Builder.text().font(FontManager.BOLD.get()).text("YANDEROV CLIENT").color(new Color(255, 255, 255, 77)).size(8.0F).thickness(0.05F).build().render(matrix4f, (float)(this.width / 2) - FontManager.BOLD.get().getWidth("YANDEROV CLIENT", 8.0F) / 2.0F, (float)(this.height / 2 - 100 + 7));
      Builder.text().font(FontManager.BOLD.get()).text("VERSION 1.0").color(new Color(255, 255, 255, 46)).size(7.8F).thickness(0.05F).build().render(matrix4f, (float)(this.width / 2) - FontManager.BOLD.get().getWidth("VERSION 1.0", 7.8F) / 2.0F, (float)(this.height / 2 - 100 + 17));

      for(Button button : this.buttons) {
         button.render(matrix4f);
         button.isHovered((double)mouseX, (double)mouseY);
      }

      this.renderMusicPlayer(context, mouseX, mouseY, matrix4f);
   }

   private void renderMusicPlayer(DrawContext context, int mouseX, int mouseY, Matrix4f matrix) {
      double x = 10.0D;
      double y = (double)this.height - 80.0D;
      double width = 175.0D;
      double height = 70.0D;
      
      Builder.rectangle().size(new SizeState((float)width, (float)height)).color(new QuadColorState(new Color(0, 0, 0, 180))).radius(new QuadRadiusState(5.0F)).smoothness(1.15F).build().render(matrix, x, y);
      
      AbstractTexture avatarTex = MinecraftClient.getInstance().getTextureManager().getTexture(Identifier.of("wild", "images/gui/music_avatar.png"));
      Builder.texture().size(new SizeState(28.0F, 28.0F)).radius(new QuadRadiusState(14.0F)).texture(0.0F, 0.0F, 1.0F, 1.0F, avatarTex).color(new QuadColorState(Color.WHITE)).build().render(matrix, x + 8.0D, y + 8.0D);

      String track = WildClient.INSTANCE.getMusicManager().getCurrentTrackName();
      
      Builder.text().font(FontManager.SUISSEINTMEDIUM.get()).text("Yanderov Music").color(Color.WHITE).size(7.0F).thickness(0.05F).build().render(matrix, x + 40.0D, y + 8.0D);
      
      double trackX = x + 40.0D;
      double trackY = y + 18.0D;
      double trackWidth = 125.0D;
      
      Scissor.StartScissor((float)trackX, (float)trackY, (float)trackWidth, 10.0F);
      float fullTrackWidth = FontManager.SUISSEINTREGULAR.get().getWidth(track, 6.0F);
      double scrollOffset = 0.0D;
      if (fullTrackWidth > trackWidth - 5) {
          scrollOffset = (System.currentTimeMillis() % 10000L) / 10000.0D * (fullTrackWidth + 40.0D);
      }
      
      float hue = (float)(System.currentTimeMillis() % 4000L) / 4000.0F;
      Color trackColor = Color.getHSBColor(hue, 0.4F, 1.0F);
      
      Builder.text().font(FontManager.SUISSEINTREGULAR.get()).text(track).color(trackColor).size(6.0F).thickness(0.05F).build().render(matrix, trackX - scrollOffset, trackY);
      if (fullTrackWidth > trackWidth - 5) {
          Builder.text().font(FontManager.SUISSEINTREGULAR.get()).text(track).color(trackColor).size(6.0F).thickness(0.05F).build().render(matrix, trackX - scrollOffset + fullTrackWidth + 40.0D, trackY);
      }
      Scissor.stopScissor();
      
      float progress = WildClient.INSTANCE.getMusicManager().getProgress();
      double barX = x + 40.0D;
      double barY = y + 35.0D;
      double barWidth = 120.0D;
      
      for (int i = 0; i < (int)barWidth; i++) {
          double waveOffset = Math.sin((double)(i + System.currentTimeMillis() / 20.0D) * 0.1D) * 2.5D;
          // Use solid white for played part, and semi-transparent for unplayed, but with more thickness
          if (i < barWidth * progress) {
              Builder.rectangle().size(new SizeState(3.0F, 3.0F)).color(new QuadColorState(Color.WHITE)).radius(new QuadRadiusState(1.5F)).build().render(matrix, barX + i, barY + waveOffset);
          } else {
              Builder.rectangle().size(new SizeState(3.0F, 3.0F)).color(new QuadColorState(new Color(255, 255, 255, 60))).radius(new QuadRadiusState(1.5F)).build().render(matrix, barX + i, barY + waveOffset);
          }
      }
      // Dot thumb at the end of progress - even bigger and clearer
      double thumbX = barX + (barWidth * progress);
      double thumbWaveOffset = Math.sin((double)(thumbX - barX + System.currentTimeMillis() / 20.0D) * 0.1D) * 2.5D;
      Builder.rectangle().size(new SizeState(10.0F, 10.0F)).color(new QuadColorState(Color.WHITE)).radius(new QuadRadiusState(5.0F)).build().render(matrix, thumbX - 5.0D, barY + thumbWaveOffset - 3.5D);
      
      boolean isPlaying = WildClient.INSTANCE.getMusicManager().isPlaying();
      this.drawMusicIcon(matrix, "music_prev", x + 40.0D, y + 48.0D, mouseX, mouseY); 
      this.drawMusicIcon(matrix, isPlaying ? "music_pause" : "music_play", x + 75.0D, y + 48.0D, mouseX, mouseY); 
      this.drawMusicIcon(matrix, "music_next", x + 110.0D, y + 48.0D, mouseX, mouseY); 
      this.drawMusicIcon(matrix, "music_refresh", x + 145.0D, y + 48.0D, mouseX, mouseY); 
   }

   private void drawMusicIcon(Matrix4f matrix, String iconName, double x, double y, int mouseX, int mouseY) {
      boolean hovered = MouseUtil.isHovered(x, y, 18.0D, 18.0D, (double)mouseX, (double)mouseY);
      Builder.rectangle().size(new SizeState(18.0F, 18.0F)).color(new QuadColorState(hovered ? new Color(255, 255, 255, 40) : new Color(255, 255, 255, 15))).radius(new QuadRadiusState(9.0F)).build().render(matrix, x, y);
      AbstractTexture tex = MinecraftClient.getInstance().getTextureManager().getTexture(Identifier.of("wild", "images/gui/" + iconName + ".png"));
      Builder.texture().size(new SizeState(10.0F, 10.0F)).texture(0.0F, 0.0F, 1.0F, 1.0F, tex).color(new QuadColorState(Color.WHITE)).build().render(matrix, x + 4.0D, y + 4.0D);
   }

   public boolean mouseClicked(double mouseX, double mouseY, int button) {
      if (button == 0) {
         double x = 10.0D;
         double y = (double)this.height - 80.0D;
         
         double barX = x + 40.0D;
         double barY = y + 35.0D;
         double barWidth = 120.0D;
         if (MouseUtil.isHovered(barX, barY - 5.0D, barWidth, 10.0D, mouseX, mouseY)) {
             float pct = (float)((mouseX - barX) / barWidth);
             WildClient.INSTANCE.getMusicManager().setPosition(Math.max(0, Math.min(1, pct)));
             return true;
         }

         if (MouseUtil.isHovered(x + 40.0D, y + 48.0D, 18.0D, 18.0D, mouseX, mouseY)) {
            WildClient.INSTANCE.getMusicManager().previous();
            return true;
         }
         
         if (MouseUtil.isHovered(x + 75.0D, y + 48.0D, 18.0D, 18.0D, mouseX, mouseY)) {
            if (WildClient.INSTANCE.getMusicManager().isPlaying()) {
               WildClient.INSTANCE.getMusicManager().pause();
            } else {
               WildClient.INSTANCE.getMusicManager().play();
            }
            return true;
         }
         
         if (MouseUtil.isHovered(x + 110.0D, y + 48.0D, 18.0D, 18.0D, mouseX, mouseY)) {
            WildClient.INSTANCE.getMusicManager().next();
            return true;
         }

         if (MouseUtil.isHovered(x + 145.0D, y + 48.0D, 18.0D, 18.0D, mouseX, mouseY)) {
            WildClient.INSTANCE.getMusicManager().updatePlaylist();
            return true;
         }
      }
      for(Button buttonV : this.buttons) {
         if (MouseUtil.isHovered(buttonV.getX(), buttonV.getY(), buttonV.getWidth(), buttonV.getHeight(), mouseX, mouseY)) {
            buttonV.onClick();
         }
      }

      return super.mouseClicked(mouseX, mouseY, button);
   }

   public boolean isInit() {
      return this.init;
   }
}
