package fun.Yanderov.features.impl.misc;

import fun.Yanderov.events.packet.PacketEvent;
import fun.Yanderov.events.player.TickEvent;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.features.module.setting.Setting;
import fun.Yanderov.features.module.setting.implement.BindSetting;
import fun.Yanderov.features.module.setting.implement.BooleanSetting;
import fun.Yanderov.features.module.setting.implement.SelectSetting;
import fun.Yanderov.features.module.setting.implement.SliderSettings;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import fun.Yanderov.utils.display.interfaces.QuickImports;
import fun.Yanderov.utils.interactions.interact.PlayerInteractionHelper;
import java.lang.reflect.Field;
import net.minecraft.class_1297;
import net.minecraft.class_1542;
import net.minecraft.class_1657;
import net.minecraft.class_2561;
import net.minecraft.class_2596;
import net.minecraft.class_634;

public class GodMode extends Module implements QuickImports {
   private final SelectSetting mods = (new SelectSetting("ÐœÐ¾Ð´Ñ‹", "Ð’Ñ‹Ð±Ð¾Ñ€ Ð¿Ñ€ÐµÑÐµÑ‚Ð°")).value("MetaHvH").selected("MetaHvH");
   private final BooleanSetting autoGod = (new BooleanSetting("Auto God", "ÐœÐ³Ð½Ð¾Ð²ÐµÐ½Ð½Ð¾ Ð²ÐºÐ»ÑŽÑ‡Ð°Ñ‚ÑŒ Ð¿Ð¾ ÑÐ¾Ð¾Ð±Ñ‰ÐµÐ½Ð¸ÑÐ¼")).setValue(false).visible(() -> this.mods.isSelected("MetaHvH"));
   private final BooleanSetting send = (new BooleanSetting("ÐžÑ‚Ð¿Ñ€Ð°Ð²Ð»ÑÑ‚ÑŒ ÐºÐ¾Ð¼Ð°Ð½Ð´Ñƒ", "ÐŸÐµÑ€Ð¸Ð¾Ð´Ð¸Ñ‡ÐµÑÐºÐ¸ Ð¾Ñ‚Ð¿Ñ€Ð°Ð²Ð»ÑÑ‚ÑŒ ÐºÐ¾Ð¼Ð°Ð½Ð´Ñƒ")).setValue(true).visible(() -> this.mods.isSelected("MetaHvH"));
   private final SliderSettings delayMs = (new SliderSettings("Ð—Ð°Ð´ÐµÑ€Ð¶ÐºÐ°, Ð¼Ñ", "Ð˜Ð½Ñ‚ÐµÑ€Ð²Ð°Ð» Ð¿Ð¾Ð²Ñ‚Ð¾Ñ€Ð° ÐºÐ¾Ð¼Ð°Ð½Ð´Ñ‹")).setValue(3000.0F).range(100.0F, 60000.0F).visible(() -> this.mods.isSelected("MetaHvH") && this.send.isValue() && !this.autoGod.isValue());
   private final BooleanSetting lootOff = (new BooleanSetting("Loot Off", "ÐŸÐ°ÑƒÐ·Ð° Ð¿Ñ€Ð¸ Ð»ÑƒÑ‚Ðµ Ñ€ÑÐ´Ð¾Ð¼ Ð¸ Ð¾Ñ‚ÑÑƒÑ‚ÑÑ‚Ð²Ð¸Ð¸ Ð¸Ð³Ñ€Ð¾ÐºÐ¾Ð²")).setValue(false).visible(() -> this.mods.isSelected("MetaHvH"));
   private final SliderSettings lootDistance = (new SliderSettings("Ð”Ð¸ÑÑ‚Ð°Ð½Ñ†Ð¸Ñ Ð´Ð¾ Ð»ÑƒÑ‚Ð°", "ÐœÐ°ÐºÑ. Ð´Ð¸ÑÑ‚Ð°Ð½Ñ†Ð¸Ñ Ð´Ð¾ Ð¿Ñ€ÐµÐ´Ð¼ÐµÑ‚Ð¾Ð²")).setValue(1.0F).range(0.5F, 6.0F).visible(() -> this.mods.isSelected("MetaHvH") && this.lootOff.isValue());
   private final SliderSettings playerDistance = (new SliderSettings("Ð”Ð¸ÑÑ‚Ð°Ð½Ñ†Ð¸Ñ Ð´Ð¾ Ð¸Ð³Ñ€Ð¾ÐºÐ°", "ÐœÐ¸Ð½. Ð´Ð¸ÑÑ‚Ð°Ð½Ñ†Ð¸Ñ Ð´Ð¾ Ð¸Ð³Ñ€Ð¾ÐºÐ°")).setValue(5.0F).range(1.0F, 16.0F).visible(() -> this.mods.isSelected("MetaHvH") && this.lootOff.isValue());
   private final BooleanSetting bindOff = (new BooleanSetting("Bind Off", "ÐŸÐ°ÑƒÐ·Ð° Ð¿Ð¾ Ð±Ð¸Ð½Ð´-ÐºÐ½Ð¾Ð¿ÐºÐµ")).setValue(false).visible(() -> this.mods.isSelected("MetaHvH"));
   private final BindSetting bindOffKey = (new BindSetting("Bind", "ÐšÐ½Ð¾Ð¿ÐºÐ° Ð¿Ð°ÑƒÐ·Ñ‹")).setKey(-1);
   private long lastSent = 0L;
   private boolean lootSuppressed = false;

   public GodMode() {
      super("GodMode", ModuleCategory.MISC);
      this.setup(new Setting[]{this.mods, this.autoGod, this.send, this.delayMs, this.lootOff, this.lootDistance, this.playerDistance, this.bindOff, this.bindOffKey});
   }

   public void activate() {
      super.activate();
      this.lastSent = 0L;
      this.lootSuppressed = false;
   }

   @EventHandler
   public void onTick(TickEvent e) {
      if (e.getType() == 0) {
         if (mc != null && mc.field_1724 != null && mc.field_1687 != null) {
            if (this.mods.isSelected("MetaHvH") && this.send.isValue()) {
               if (this.bindOff.isValue()) {
                  int code = this.bindOffKey.getKey();
                  if (code != -1 && PlayerInteractionHelper.isKey(PlayerInteractionHelper.getKeyType(code), code)) {
                     return;
                  }
               }

               boolean playersNear = this.isPlayerNear((double)this.playerDistance.getValue());
               boolean lootNear = this.isLootNear((double)this.lootDistance.getValue());
               if (this.lootOff.isValue() && lootNear && !playersNear) {
                  if (!this.lootSuppressed) {
                     this.sendGodCommand();
                     this.lootSuppressed = true;
                  }

               } else {
                  this.lootSuppressed = false;
                  long now = System.currentTimeMillis();
                  long d = (long)this.delayMs.getValue();
                  if (!this.autoGod.isValue() && (this.lastSent == 0L || now - this.lastSent >= d)) {
                     this.sendGodCommand();
                     this.lastSent = now;
                  }

               }
            }
         }
      }
   }

   @EventHandler
   public void onPacket(PacketEvent e) {
      if (e.getType() == PacketEvent.Type.RECEIVE) {
         if (mc != null && mc.field_1724 != null && mc.field_1687 != null) {
            if (this.mods.isSelected("MetaHvH") && this.autoGod.isValue()) {
               try {
                  class_2596<?> pkt = e.getPacket();
                  String txt = this.extractTextString(pkt);
                  if (txt == null || txt.isEmpty()) {
                     return;
                  }

                  String low = txt.toLowerCase();
                  if (low.contains("Ð½Ðµ Ð¼Ð¾Ð¶ÐµÑ‚ Ð±Ñ‹Ñ‚ÑŒ Ð²ÐºÐ»ÑŽÑ‡ÐµÐ½ god") || low.contains("Ð½ÐµÐ»ÑŒÐ·Ñ Ð²ÐºÐ»ÑŽÑ‡Ð¸Ñ‚ÑŒ god") || low.contains("god Ð²Ñ‹ÐºÐ»ÑŽÑ‡ÐµÐ½") || low.contains("god off") || low.contains("god disabled")) {
                     this.sendGodCommand();
                     this.lastSent = System.currentTimeMillis();
                  }
               } catch (Throwable var5) {
               }

            }
         }
      }
   }

   private void sendGodCommand() {
      try {
         class_634 nh = mc.method_1562();

         try {
            nh.getClass().getMethod("sendChatCommand", String.class).invoke(nh, "god");
            return;
         } catch (Throwable var5) {
            try {
               Class<?> cmdCls = Class.forName("net.minecraft.network.packet.c2s.play.CommandExecutionC2SPacket");
               Object pkt = cmdCls.getConstructor(String.class).newInstance("god");
               mc.method_1562().method_52787((class_2596)pkt);
               return;
            } catch (Throwable var4) {
               mc.field_1724.method_7353(class_2561.method_30163("/god"), false);
            }
         }
      } catch (Throwable var6) {
      }

   }

   private boolean isLootNear(double dist) {
      double d2 = dist * dist;

      for(class_1297 ent : mc.field_1687.method_18112()) {
         if (ent instanceof class_1542 item) {
            if (item.method_5858(mc.field_1724) <= d2) {
               return true;
            }
         }
      }

      return false;
   }

   private boolean isPlayerNear(double dist) {
      double d2 = dist * dist;

      for(class_1657 p : mc.field_1687.method_18456()) {
         if (p != mc.field_1724 && p.method_5805() && p.method_5858(mc.field_1724) <= d2) {
            return true;
         }
      }

      return false;
   }

   private String extractTextString(Object pkt) {
      if (pkt == null) {
         return null;
      } else {
         try {
            for(String mName : new String[]{"content", "getContent", "body", "message", "getMessage", "text", "getText"}) {
               try {
                  Object v = pkt.getClass().getMethod(mName).invoke(pkt);
                  if (v instanceof class_2561) {
                     class_2561 t = (class_2561)v;
                     return this.safeString(t);
                  }
               } catch (NoSuchMethodException var10) {
               }
            }

            for(String fName : new String[]{"content", "body", "message", "text"}) {
               try {
                  Field f = pkt.getClass().getDeclaredField(fName);
                  f.setAccessible(true);
                  Object v = f.get(pkt);
                  if (v instanceof class_2561) {
                     class_2561 t = (class_2561)v;
                     return this.safeString(t);
                  }
               } catch (NoSuchFieldException var9) {
               }
            }
         } catch (Throwable var11) {
         }

         return null;
      }
   }

   private String safeString(class_2561 t) {
      try {
         return t.getString();
      } catch (Throwable var3) {
         return String.valueOf(t);
      }
   }
}

