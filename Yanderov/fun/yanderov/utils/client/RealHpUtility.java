package fun.Yanderov.utils.client;

import fun.Yanderov.utils.display.color.ColorAssist;
import net.minecraft.class_266;
import net.minecraft.class_269;
import net.minecraft.class_5250;
import net.minecraft.class_742;
import net.minecraft.class_8646;
import net.minecraft.class_9013;
import net.minecraft.class_9025;

public class RealHpUtility {
   public static float getRealHp(class_742 player) {
      if (player == null) {
         return -1.0F;
      } else {
         class_269 scoreboard = player.method_7327();
         if (scoreboard == null) {
            return -1.0F;
         } else {
            class_266 objective = scoreboard.method_1189(class_8646.field_45158);
            if (objective == null) {
               return -1.0F;
            } else {
               try {
                  class_5250 text = class_9013.method_55398(scoreboard.method_55430(player, objective), objective.method_55380(class_9025.field_47566));
                  String raw = ColorAssist.removeFormatting(text.getString());
                  float hp = Float.parseFloat(raw);
                  return hp > 0.0F ? hp : -1.0F;
               } catch (Exception var6) {
                  return -1.0F;
               }
            }
         }
      }
   }
}

