package dev.client.yanderov.features.impl.misc;

import dev.client.yanderov.events.packet.PacketEvent;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.features.module.setting.Setting;
import dev.client.yanderov.features.module.setting.implement.BooleanSetting;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import dev.client.yanderov.utils.display.interfaces.QuickImports;
import java.lang.reflect.Method;
import java.util.Random;
import net.minecraft.class_2561;
import net.minecraft.class_2596;
import net.minecraft.class_2797;
import net.minecraft.class_5892;

public class ChatManager extends Module implements QuickImports {
   private final BooleanSetting tag = (new BooleanSetting("Tag", "Ð”Ð¾Ð±Ð°Ð²Ð»ÑÑ‚ÑŒ (Yanderov CLIENT) Ðº ÑÐ¾Ð¾Ð±Ñ‰ÐµÐ½Ð¸ÑÐ¼")).setValue(true);
   private final BooleanSetting killMessage = (new BooleanSetting("Kill Message", "ÐžÑ‚Ð¿Ñ€Ð°Ð²Ð»ÑÑ‚ÑŒ ÑÐ¾Ð¾Ð±Ñ‰ÐµÐ½Ð¸Ðµ Ð¿Ñ€Ð¸ ÑƒÐ±Ð¸Ð¹ÑÑ‚Ð²Ðµ")).setValue(false);
   private final String[] templates = new String[]{"Ð§Ð• Ð¢ÐÐš Ð‘Ð«Ð¡Ð¢Ð Ðž Ð£ÐœÐ•Ð ? Ð’Ð“Ð•Ð¢ÐÐ™ Yanderov CLIENT", "Ð§Ðµ ÐºÐ»Ð°ÑÑÐ½Ð¾ Ð²ÑÐµÐ¼ ÑÐ¾ÑÐ°Ñ‚ÑŒ?) Ð“Ð•Ð¢ÐÐ™ Ð£Ð–Ð• Yanderov CLIENT Ð˜ ÐÐÐ§Ð˜ÐÐÐ™ Ð’Ð¡Ð•Ð¥ Ð•Ð‘ÐÐ¢Ð¬", "Ð—ÐÐ§Ð•Ðœ ÐŸÐžÐ¡Ð¢ÐžÐ¯ÐÐÐž Ð£ÐœÐ˜Ð ÐÐ¢Ð¬ Ð•Ð¡Ð›Ð˜ ÐœÐžÐ–ÐÐž Ð“Ð•Ð¢ÐÐ£Ð¢Ð¬ Ð›Ð£Ð§Ð¨Ð˜Ð™ Ð‘Ð•Ð¡ÐŸÐ›ÐÐ¢ÐÐ«Ð™ Yanderov CLIENT?", "Ð—ÐÐ•Ð‘ÐÐ› Ð¡ÐÐšÐÐ¢Ð¬, Ð“Ð•Ð¢ÐÐ˜ Ð£Ð–Ð• Yanderov CLIENT", "ÐŸÐ Ð•ÐšÐ ÐÐ¢Ð˜ Ð£ÐœÐ˜Ð ÐÐ¢Ð¬ Ð˜ Ð¡Ð›Ð˜Ð’ÐÐ¢Ð¬ ÐšÐ”, Ð’ÐœÐ•Ð¡Ð¢Ðž Ð­Ð¢ÐžÐ“Ðž Ð—ÐÐ™Ð”Ð˜ Ð¡ Yanderov CLIENT"};
   private int lastIndex = -1;
   private final Random rnd = new Random();

   public ChatManager() {
      super("ChatManager", ModuleCategory.MISC);
      this.setup(new Setting[]{this.tag, this.killMessage});
   }

   @EventHandler
   public void onPacket(PacketEvent e) {
      class_2596<?> p = e.getPacket();
      if (e.isSend() && this.tag.isValue() && p instanceof class_2797 chat) {
         try {
            String msg = chat.comp_945();
            if (msg != null && !msg.isEmpty() && !msg.startsWith("/")) {
               String tagged = msg + " (Yanderov CLIENT)";
               e.cancel();
               if (mc != null && mc.field_1724 != null && mc.field_1724.field_3944 != null) {
                  mc.field_1724.field_3944.method_45729(tagged);
               }
            }
         } catch (Throwable var12) {
         }
      }

      if (!e.isSend() && this.killMessage.isValue() && p instanceof class_5892 death) {
         try {
            String plain = null;

            try {
               Method m = death.getClass().getMethod("getDeathMessage");
               Object tx = m.invoke(death);
               if (tx instanceof class_2561 tt) {
                  plain = tt.getString();
               }
            } catch (Throwable var10) {
               try {
                  Method m2 = death.getClass().getMethod("getMessage");
                  Object tx2 = m2.invoke(death);
                  if (tx2 instanceof class_2561 tt2) {
                     plain = tt2.getString();
                  }
               } catch (Throwable var9) {
               }
            }

            if (plain == null) {
               return;
            }

            String self = mc.field_1724 != null ? mc.field_1724.method_7334().getName() : null;
            if (self == null) {
               return;
            }

            if (!plain.contains(self)) {
               return;
            }

            String victim = plain.split(" ", 2)[0];
            if (victim.equalsIgnoreCase(self)) {
               return;
            }

            String template = this.nextTemplate();
            if (template != null && victim != null && !victim.isEmpty()) {
               String out = "! " + victim + " " + template;
               if (mc != null && mc.field_1724 != null && mc.field_1724.field_3944 != null) {
                  mc.field_1724.field_3944.method_45729(out);
               }
            }
         } catch (Throwable var11) {
         }
      }

   }

   private String nextTemplate() {
      if (this.templates.length == 0) {
         return null;
      } else if (this.templates.length == 1) {
         return this.templates[0];
      } else {
         int idx;
         do {
            idx = this.rnd.nextInt(this.templates.length);
         } while(idx == this.lastIndex);

         this.lastIndex = idx;
         return this.templates[idx];
      }
   }
}

