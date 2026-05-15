package fun.Yanderov.features.impl.misc;

import fun.Yanderov.events.packet.PacketEvent;
import fun.Yanderov.events.player.TickEvent;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.features.module.setting.Setting;
import fun.Yanderov.features.module.setting.implement.BooleanSetting;
import fun.Yanderov.features.module.setting.implement.SelectSetting;
import fun.Yanderov.features.module.setting.implement.SliderSettings;
import fun.Yanderov.features.module.setting.implement.TextSetting;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import fun.Yanderov.utils.display.interfaces.QuickImports;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import net.minecraft.class_1703;
import net.minecraft.class_1713;
import net.minecraft.class_2561;
import net.minecraft.class_2596;
import net.minecraft.class_634;
import net.minecraft.class_640;
import net.minecraft.class_7439;

public class AutoDuel extends Module implements QuickImports {
   private final Pattern namePattern = Pattern.compile("^\\w{3,16}$");
   private final SelectSetting mode = (new SelectSetting("Ð ÐµÐ¶Ð¸Ð¼", "Ð’Ñ‹Ð±Ð¾Ñ€ Ð½Ð°Ð±Ð¾Ñ€Ð°")).value("Ð¨Ð°Ñ€Ñ‹", "Ð©Ð¸Ñ‚", "Ð¨Ð¸Ð¿Ñ‹ 3", "ÐÐµÐ·ÐµÑ€Ð¸Ñ‚ÐºÐ°", "Ð§Ð¸Ñ‚ÐµÑ€ÑÐºÐ¸Ð¹ Ñ€Ð°Ð¹", "Ð›ÑƒÐº", "ÐšÐ»Ð°ÑÑÐ¸Ðº", "Ð¢Ð¾Ñ‚ÐµÐ¼Ñ‹", "ÐÐ¾Ð´ÐµÐ±Ð°Ñ„Ñ„").selected("Ð¨Ð°Ñ€Ñ‹");
   private final SliderSettings delayMs = (new SliderSettings("Ð¡ÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Ð¾Ñ‚Ð¿Ñ€Ð°Ð²ÐºÐ¸", "Ð˜Ð½Ñ‚ÐµÑ€Ð²Ð°Ð» Ð¼ÐµÐ¶Ð´Ñƒ /duel, Ð¼Ñ")).setValue(500.0F).range(100.0F, 2000.0F);
   private final BooleanSetting playMoney = (new BooleanSetting("Ð˜Ð³Ñ€Ð°Ñ‚ÑŒ Ð½Ð° Ð´ÐµÐ½ÑŒÐ³Ð¸", "Ð’ÐºÐ»ÑŽÑ‡Ð¸Ñ‚ÑŒ ÑÑ‚Ð°Ð²ÐºÑƒ Ð½Ð° Ð´ÐµÐ½ÑŒÐ³Ð¸")).setValue(false);
   private final TextSetting money;
   private final List sent;
   private long lastSend;
   private long lastClear;
   private long lastChoice;
   private long lastConfirm;

   public AutoDuel() {
      super("AutoDuel", ModuleCategory.MISC);
      TextSetting var10001 = (new TextSetting("ÐœÐ¾Ð½ÐµÑ‚", "Ð¡ÑƒÐ¼Ð¼Ð° Ð´Ð»Ñ Ð´ÑƒÑÐ»Ð¸")).setText("10000");
      BooleanSetting var10002 = this.playMoney;
      Objects.requireNonNull(var10002);
      this.money = var10001.visible(var10002::isValue);
      this.sent = new ArrayList();
      this.lastSend = 0L;
      this.lastClear = 0L;
      this.lastChoice = 0L;
      this.lastConfirm = 0L;
      this.setup(new Setting[]{this.mode, this.delayMs, this.playMoney, this.money});
   }

   public void activate() {
      super.activate();
      this.sent.clear();
      this.lastSend = this.lastClear = this.lastChoice = this.lastConfirm = 0L;
   }

   @EventHandler
   public void onTick(TickEvent e) {
      if (e.getType() == 0) {
         if (mc != null && mc.field_1724 != null && mc.method_1562() != null) {
            List<String> players = this.getOnlinePlayers();
            long now = System.currentTimeMillis();
            if (now - this.lastClear >= Math.max(800L * (long)Math.max(1, players.size()), 2000L)) {
               this.sent.clear();
               this.lastClear = now;
            }

            for(String p : players) {
               if (!p.equals(mc.field_1724.method_7334().getName()) && !this.sent.contains(p) && now - this.lastSend >= (long)this.delayMs.getValue()) {
                  this.sendDuel(p);
                  this.sent.add(p);
                  this.lastSend = now;
               }
            }

            if (mc.field_1755 != null) {
               class_1703 title = mc.field_1724.field_7512;
               if (title instanceof class_1703) {
                  class_1703 handler = title;
                  String title = String.valueOf(mc.field_1755.method_25440().getString());
                  if (title.contains("Ð’Ñ‹Ð±Ð¾Ñ€ Ð½Ð°Ð±Ð¾Ñ€Ð° (1/1)")) {
                     if (now - this.lastChoice >= 150L) {
                        int slotId = this.resolveModeSlot();
                        if (slotId >= 0) {
                           try {
                              mc.field_1761.method_2906(handler.field_7763, slotId, 0, class_1713.field_7794, mc.field_1724);
                           } catch (Throwable var10) {
                           }
                        }

                        this.lastChoice = now;
                     }
                  } else if (title.contains("ÐÐ°ÑÑ‚Ñ€Ð¾Ð¹ÐºÐ° Ð¿Ð¾ÐµÐ´Ð¸Ð½ÐºÐ°") && now - this.lastConfirm >= 150L) {
                     try {
                        mc.field_1761.method_2906(handler.field_7763, 0, 0, class_1713.field_7794, mc.field_1724);
                     } catch (Throwable var9) {
                     }

                     this.lastConfirm = now;
                  }
               }
            }

         }
      }
   }

   @EventHandler
   public void onPacket(PacketEvent e) {
      if (e.getType() == PacketEvent.Type.RECEIVE) {
         class_2596<?> pkt = e.getPacket();

         try {
            if (pkt instanceof class_7439) {
               class_7439 msg = (class_7439)pkt;
               String txt = this.safeString(this.extractText(msg));
               if (txt == null) {
                  return;
               }

               txt = txt.toLowerCase();
               if (txt.contains("Ð½Ð°Ñ‡Ð°Ð»Ð¾") && txt.contains("Ñ‡ÐµÑ€ÐµÐ·") && txt.contains("ÑÐµÐºÑƒÐ½Ð´") || txt.contains("Ð²Ð¾ Ð²Ñ€ÐµÐ¼Ñ Ð¿Ð¾ÐµÐ´Ð¸Ð½ÐºÐ° Ð·Ð°Ð¿Ñ€ÐµÑ‰ÐµÐ½Ð¾ Ð¸ÑÐ¿Ð¾Ð»ÑŒÐ·Ð¾Ð²Ð°Ñ‚ÑŒ ÐºÐ¾Ð¼Ð°Ð½Ð´Ñ‹")) {
                  this.setState(false);
               }
            }
         } catch (Throwable var5) {
         }

      }
   }

   private void sendDuel(String player) {
      try {
         class_634 nh = mc.method_1562();
         if (nh == null) {
            return;
         }

         if (this.playMoney.isValue()) {
            String sum = this.money.getText() == null ? "" : this.money.getText().trim();

            try {
               nh.getClass().getMethod("sendChatCommand", String.class).invoke(nh, ("duel " + player + " " + sum).trim());
               return;
            } catch (Throwable var9) {
               try {
                  Class<?> cls = Class.forName("net.minecraft.network.packet.c2s.play.CommandExecutionC2SPacket");
                  Object pkt = cls.getConstructor(String.class).newInstance(("duel " + player + " " + sum).trim());
                  nh.method_52787((class_2596)pkt);
                  return;
               } catch (Throwable var8) {
               }
            }
         } else {
            try {
               nh.getClass().getMethod("sendChatCommand", String.class).invoke(nh, "duel " + player);
               return;
            } catch (Throwable var10) {
               try {
                  Class<?> cls = Class.forName("net.minecraft.network.packet.c2s.play.CommandExecutionC2SPacket");
                  Object pkt = cls.getConstructor(String.class).newInstance("duel " + player);
                  nh.method_52787((class_2596)pkt);
                  return;
               } catch (Throwable var7) {
               }
            }
         }
      } catch (Throwable var11) {
      }

      try {
         mc.field_1724.method_7353(class_2561.method_30163("/duel " + player), false);
      } catch (Throwable var6) {
      }

   }

   private int resolveModeSlot() {
      if (this.mode.isSelected("Ð©Ð¸Ñ‚")) {
         return 0;
      } else if (this.mode.isSelected("Ð¨Ð¸Ð¿Ñ‹ 3")) {
         return 1;
      } else if (this.mode.isSelected("Ð›ÑƒÐº")) {
         return 2;
      } else if (this.mode.isSelected("Ð¢Ð¾Ñ‚ÐµÐ¼Ñ‹")) {
         return 3;
      } else if (this.mode.isSelected("ÐÐ¾Ð´ÐµÐ±Ð°Ñ„Ñ„")) {
         return 4;
      } else if (this.mode.isSelected("Ð¨Ð°Ñ€Ñ‹")) {
         return 5;
      } else if (this.mode.isSelected("ÐšÐ»Ð°ÑÑÐ¸Ðº")) {
         return 6;
      } else if (this.mode.isSelected("Ð§Ð¸Ñ‚ÐµÑ€ÑÐºÐ¸Ð¹ Ñ€Ð°Ð¹")) {
         return 7;
      } else {
         return this.mode.isSelected("ÐÐµÐ·ÐµÑ€Ð¸Ñ‚ÐºÐ°") ? 8 : -1;
      }
   }

   private List getOnlinePlayers() {
      try {
         return (List)mc.method_1562().method_2880().stream().map(class_640::method_2966).map((p) -> p.getName()).filter((n) -> n != null && this.namePattern.matcher(n).matches()).collect(Collectors.toList());
      } catch (Throwable var2) {
         return List.of();
      }
   }

   private class_2561 extractText(class_7439 pkt) {
      try {
         return (class_2561)pkt.getClass().getMethod("content").invoke(pkt);
      } catch (Throwable var6) {
         try {
            Field f = pkt.getClass().getDeclaredField("content");
            f.setAccessible(true);
            Object v = f.get(pkt);
            if (v instanceof class_2561 t) {
               return t;
            }
         } catch (Throwable var5) {
         }

         return null;
      }
   }

   private String safeString(class_2561 t) {
      if (t == null) {
         return null;
      } else {
         try {
            return t.getString();
         } catch (Throwable var3) {
            return String.valueOf(t);
         }
      }
   }
}

