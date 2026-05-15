package fun.Yanderov.utils.client.window;

import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.win32.StdCallLibrary;
import fun.Yanderov.features.impl.misc.SelfDestruct;
import org.lwjgl.glfw.GLFWNativeWin32;

public class WindowStyle {
   public static void setDarkMode(long windowHandle) {
      if (!SelfDestruct.unhooked) {
         String os = System.getProperty("os.name", "").toLowerCase();
         if (os.contains("win")) {
            long hwnd = GLFWNativeWin32.glfwGetWin32Window(windowHandle);
            WinDef.HWND hwndJna = new WinDef.HWND(new Pointer(hwnd));
            int DWMWA_USE_IMMERSIVE_DARK_MODE = 20;
            Memory darkModeEnabled = new Memory(4L);
            darkModeEnabled.setInt(0L, 1);
            WindowStyle.DwmApi.INSTANCE.DwmSetWindowAttribute(hwndJna, DWMWA_USE_IMMERSIVE_DARK_MODE, darkModeEnabled, 4);
         }
      }
   }

   public interface DwmApi extends StdCallLibrary {
      DwmApi INSTANCE = (DwmApi)Native.loadLibrary("dwmapi", DwmApi.class);

      int DwmSetWindowAttribute(WinDef.HWND var1, int var2, Pointer var3, int var4);
   }
}

