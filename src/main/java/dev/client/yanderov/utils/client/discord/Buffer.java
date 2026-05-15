package dev.client.yanderov.utils.client.discord;

import com.google.common.collect.Maps;
import dev.client.yanderov.utils.display.interfaces.QuickImports;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.imageio.ImageIO;
import net.minecraft.class_1011;
import net.minecraft.class_1043;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWImage;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

public final class Buffer implements QuickImports {
   private static final Map dynamicIdCounters = Maps.newHashMap();

   public static class_1043 getHeadFromURL(String url) throws IOException {
      return url != null && !url.isEmpty() ? new class_1043(parseHead(class_1011.method_4309((new URL(url)).openStream()))) : null;
   }

   public static class_1011 parseHead(class_1011 image) {
      if (image == null) {
         return null;
      } else {
         int imageWidth = 22;
         int imageHeight = 22;
         int imageSrcWidth = image.method_4307();
         int srcHeight = image.method_4323();

         for(int imageSrcHeight = image.method_4323(); imageWidth < imageSrcWidth || imageHeight < imageSrcHeight; imageHeight *= 2) {
            imageWidth *= 2;
         }

         class_1011 imgNew = new class_1011(imageWidth, imageHeight, true);

         for(int x = 0; x < imageSrcWidth; ++x) {
            for(int y = 0; y < srcHeight; ++y) {
               imgNew.method_61941(x, y, image.method_61940(x, y));
            }
         }

         image.close();
         return imgNew;
      }
   }

   public static void registerBufferedImageTexture(class_2960 i, BufferedImage bi) {
      try {
         ByteArrayOutputStream baos = new ByteArrayOutputStream();
         ImageIO.write(bi, "png", baos);
         byte[] bytes = baos.toByteArray();
         registerTexture(i, bytes);
      } catch (Exception var4) {
      }

   }

   public static void registerTexture(class_2960 i, byte[] content) {
      try {
         ByteBuffer data = BufferUtils.createByteBuffer(content.length).put(content);
         data.flip();
         class_1043 tex = new class_1043(class_1011.method_4324(data));
         mc.execute(() -> mc.method_1531().method_4616(i, tex));
      } catch (Exception var4) {
      }

   }

   public static void setWindowIcon(InputStream img16x16, InputStream img32x32) {
      try {
         MemoryStack memorystack = MemoryStack.stackPush();

         try {
            GLFWImage.Buffer buffer = GLFWImage.malloc(2, memorystack);
            List<InputStream> imgList = List.of(img16x16, img32x32);
            List<ByteBuffer> buffers = new ArrayList();

            for(int i = 0; i < imgList.size(); ++i) {
               class_1011 nativeImage = class_1011.method_4309((InputStream)imgList.get(i));
               ByteBuffer bytebuffer = MemoryUtil.memAlloc(nativeImage.method_4307() * nativeImage.method_4323() * 4);
               bytebuffer.asIntBuffer().put(nativeImage.method_48463());
               buffer.position(i);
               buffer.width(nativeImage.method_4307());
               buffer.height(nativeImage.method_4323());
               buffer.pixels(bytebuffer);
               buffers.add(bytebuffer);
            }

            if (GLFW.glfwGetPlatform() != 393219) {
               GLFW.glfwSetWindowIcon(class_310.method_1551().method_22683().method_4490(), buffer);
            }

            buffers.forEach(MemoryUtil::memFree);
         } catch (Throwable var10) {
            if (memorystack != null) {
               try {
                  memorystack.close();
               } catch (Throwable var9) {
                  var10.addSuppressed(var9);
               }
            }

            throw var10;
         }

         if (memorystack != null) {
            memorystack.close();
         }
      } catch (Exception var11) {
      }

   }

   public static class_2960 registerDynamicTexture(String prefix, class_1043 texture) {
      Integer integer = (Integer)dynamicIdCounters.get(prefix);
      if (integer == null) {
         integer = 1;
      } else {
         ++integer;
      }

      dynamicIdCounters.put(prefix, integer);
      class_2960 identifier = class_2960.method_60656(String.format(Locale.ROOT, "dynamic/%s_%d", prefix, integer));
      mc.method_1531().method_4616(identifier, texture);
      return identifier;
   }

   private Buffer() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}

