package dev.client.yanderov.utils.display.gif;

import dev.client.yanderov.utils.display.shape.ShapeProperties;
import dev.client.yanderov.utils.display.shape.implement.Image;
import dev.client.yanderov.utils.math.frame.FrameRateCounter;
import net.minecraft.class_310;
import net.minecraft.class_4587;

public class GifRender {
   private int currentFrame = 0;
   private float frameTime = 0.0F;
   private final float frameDuration = 0.0015F;
   private final String[] frames;
   private final Image image;

   public GifRender(String path, int frameCount) {
      this.frames = new String[frameCount];

      for(int i = 0; i < frameCount; ++i) {
         this.frames[i] = String.format("minecraft:gif/backgrounds/mainmenutype1/%05d.png", i + 1);
      }

      this.image = new Image();
   }

   public void render(class_4587 matrix, float x, float y, float width, float height) {
      if (class_310.method_1551().method_1569()) {
         this.frameTime += FrameRateCounter.INSTANCE.getFps() > 0 ? 1.0F / (float)FrameRateCounter.INSTANCE.getFps() : 0.006F;
         if (this.frameTime >= 0.0015F) {
            this.currentFrame = (this.currentFrame + 1) % this.frames.length;
            this.frameTime = 0.0F;
         }

         this.image.setTexture(this.frames[this.currentFrame]).render(ShapeProperties.create(matrix, (double)x, (double)y, (double)width, (double)height).color(-1).build());
      }
   }
}

