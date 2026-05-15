package fun.Yanderov.utils.display.interfaces;

import com.google.gson.Gson;
import fun.Yanderov.display.screens.clickgui.components.implement.window.WindowManager;
import fun.Yanderov.utils.display.draw.DrawEngine;
import fun.Yanderov.utils.display.draw.DrawEngineImpl;
import fun.Yanderov.utils.display.shape.implement.Arc;
import fun.Yanderov.utils.display.shape.implement.Blur;
import fun.Yanderov.utils.display.shape.implement.Image;
import fun.Yanderov.utils.display.shape.implement.Rectangle;
import net.minecraft.class_1041;
import net.minecraft.class_310;
import net.minecraft.class_9779;

public interface QuickImports extends QuickLogger {
   class_310 mc = class_310.method_1551();
   class_9779 tickCounter = mc.method_61966();
   class_1041 window = mc.method_22683();
   DrawEngine drawEngine = new DrawEngineImpl();
   Rectangle rectangle = new Rectangle();
   Blur blur = new Blur();
   Arc arc = new Arc();
   Image image = new Image();
   Gson gson = new Gson();
   WindowManager windowManager = new WindowManager();
}

