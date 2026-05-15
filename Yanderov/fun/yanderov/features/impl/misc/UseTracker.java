package fun.Yanderov.features.impl.misc;

import fun.Yanderov.display.hud.Notifications;
import fun.Yanderov.events.packet.PacketEvent;
import fun.Yanderov.events.player.TickEvent;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.features.module.setting.Setting;
import fun.Yanderov.features.module.setting.implement.BooleanSetting;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import fun.Yanderov.utils.display.interfaces.QuickImports;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import net.minecraft.class_1297;
import net.minecraft.class_1542;
import net.minecraft.class_1657;
import net.minecraft.class_1738;
import net.minecraft.class_1799;
import net.minecraft.class_243;
import net.minecraft.class_2561;
import net.minecraft.class_2583;
import net.minecraft.class_2596;
import net.minecraft.class_2616;
import net.minecraft.class_2663;
import net.minecraft.class_2767;
import net.minecraft.class_2775;
import net.minecraft.class_3414;
import net.minecraft.class_3417;
import net.minecraft.class_7923;

public class UseTracker extends Module implements QuickImports {
   private final BooleanSetting trackSelf = (new BooleanSetting("Track Self", "Ð›Ð¾Ð³Ð¸Ñ€Ð¾Ð²Ð°Ñ‚ÑŒ ÑÐµÐ±Ñ")).setValue(false);
   private final BooleanSetting catSwap = (new BooleanSetting("Category: Swap", "Ð›Ð¾Ð³ ÑÐ²Ð°Ð¿Ð°")).setValue(true);
   private final BooleanSetting catEnemy = (new BooleanSetting("Category: Enemy", "Ð›Ð¾Ð³ Ð¸ÑÐ¿Ð¾Ð»ÑŒÐ·Ð¾Ð²Ð°Ð½Ð¸Ñ Ð²Ñ€Ð°Ð³Ð°Ð¼Ð¸")).setValue(true);
   private final BooleanSetting catPickup = (new BooleanSetting("Category: Pickup", "Ð›Ð¾Ð³ Ð¿Ð¾Ð´Ð½ÑÑ‚Ð¸Ð¹")).setValue(true);
   private final BooleanSetting filterAll = (new BooleanSetting("Filter: All", "Ð’ÑÐµ Ð¿Ñ€ÐµÐ´Ð¼ÐµÑ‚Ñ‹")).setValue(true);
   private final BooleanSetting filterArmor = (new BooleanSetting("Filter: Armor", "Ð¢Ð¾Ð»ÑŒÐºÐ¾ Ð±Ñ€Ð¾Ð½Ñ")).setValue(false).visible(() -> !this.filterAll.isValue());
   private final BooleanSetting filterGapple = (new BooleanSetting("Filter: GoldenApple", "Ð¢Ð¾Ð»ÑŒÐºÐ¾ Ð·Ð¾Ð»Ð¾Ñ‚Ñ‹Ðµ ÑÐ±Ð»Ð¾ÐºÐ¸")).setValue(false).visible(() -> !this.filterAll.isValue());
   private final Set inUseNow = new HashSet();
   private final Map lastLogAt = new HashMap();
   private static final long MIN_INTERVAL_MS = 900L;
   private final Map lastMainName = new HashMap();
   private final Map lastOffName = new HashMap();

   public UseTracker() {
      super("UseTracker", ModuleCategory.MISC);
      this.setup(new Setting[]{this.trackSelf, this.catSwap, this.catEnemy, this.catPickup, this.filterAll, this.filterArmor, this.filterGapple});
   }

   @EventHandler
   public void onPacket(PacketEvent e) {
      if (mc != null && mc.field_1687 != null) {
         if (e.getType() != PacketEvent.Type.SEND) {
            class_2596 ent = e.getPacket();
            if (ent instanceof class_2663) {
               class_2663 pkt = (class_2663)ent;
               if (pkt.method_11470() == 9) {
                  class_1297 ent = pkt.method_11469(mc.field_1687);
                  if (ent instanceof class_1657) {
                     class_1657 player = (class_1657)ent;
                     this.tryLogUse(player, "Ð¸ÑÐ¿Ð¾Ð»ÑŒÐ·ÑƒÐµÑ‚ (status)");
                  }
               }

            } else {
               ent = e.getPacket();
               if (ent instanceof class_2616) {
                  class_2616 anim = (class_2616)ent;
                  int entId = -1;

                  try {
                     Method m = anim.getClass().getMethod("getEntityId");
                     entId = (Integer)m.invoke(anim);
                  } catch (Throwable var10) {
                     try {
                        Field f = anim.getClass().getDeclaredField("id");
                        f.setAccessible(true);
                        entId = (Integer)f.get(anim);
                     } catch (Throwable var9) {
                     }
                  }

                  if (entId != -1) {
                     class_1297 ent = mc.field_1687.method_8469(entId);
                     if (ent instanceof class_1657) {
                        class_1657 player = (class_1657)ent;
                        if (this.catEnemy.isValue() && this.passItemFilters(this.safeStack(player))) {
                           this.tryLogUse(player, "Ð²Ð·Ð¼Ð°Ñ… Ñ€ÑƒÐºÐ¾Ð¹");
                        }
                     }
                  }

               } else {
                  ent = e.getPacket();
                  if (ent instanceof class_2767) {
                     class_2767 sndPkt = (class_2767)ent;
                     class_3414 se = (class_3414)sndPkt.method_11894().comp_349();
                     if (this.isEatDrink(se)) {
                        class_1657 nearest = this.getNearestPlayer(new class_243(sndPkt.method_11890(), sndPkt.method_11889(), sndPkt.method_11893()), (double)4.0F);
                        if (nearest != null && this.catEnemy.isValue() && this.passItemFilters(this.safeStack(nearest))) {
                           this.tryLogUse(nearest, "ÐµÑÑ‚/Ð¿ÑŒÑ‘Ñ‚ (sound)");
                        }
                     }

                  } else {
                     ent = e.getPacket();
                     if (ent instanceof class_2775) {
                        class_2775 up = (class_2775)ent;
                        if (!this.catPickup.isValue()) {
                           return;
                        }

                        try {
                           class_1297 itemEnt = mc.field_1687.method_8469(up.method_11915());
                           if (itemEnt instanceof class_1542) {
                              class_1542 it = (class_1542)itemEnt;
                              class_1799 st = it.method_6983();
                              if (!this.filterAll.isValue() && !this.passItemFilters(st)) {
                                 return;
                              }

                              class_1657 who = null;
                              class_1297 collector = mc.field_1687.method_8469(up.method_11912());
                              if (collector instanceof class_1657) {
                                 class_1657 pe = (class_1657)collector;
                                 who = pe;
                              }

                              if (who == null) {
                                 who = this.getNearestPlayer(it.method_19538(), (double)3.5F);
                              }

                              if (who != null) {
                                 this.logPickupColored(who, st);
                              }
                           }
                        } catch (Throwable var11) {
                        }
                     }

                  }
               }
            }
         }
      }
   }

   @EventHandler
   public void onTick(TickEvent e) {
      if (e.getType() == 0) {
         if (mc != null && mc.field_1687 != null) {
            try {
               for(class_1657 p : mc.field_1687.method_18456()) {
                  int id = p.method_5628();
                  boolean using = false;

                  try {
                     using = p.method_6115();
                  } catch (Throwable var11) {
                  }

                  if (!using) {
                     this.inUseNow.remove(id);
                  }

                  if (this.catSwap.isValue()) {
                     String main = this.safe(p.method_6047().method_7964());
                     String off = this.safe(p.method_6079().method_7964());
                     String var10000 = (String)this.lastMainName.get(id);
                     String prevOff = (String)this.lastOffName.get(id);
                     boolean changedOff = prevOff != null && !prevOff.equals(off);
                     if ((p != mc.field_1724 || this.trackSelf.isValue()) && changedOff && (this.filterAll.isValue() || this.passItemFilters(p.method_6079()))) {
                        this.tryLogSwap(p, "ÑÐ²Ð°Ð¿Ð½ÑƒÐ» Ð½Ð° (Ð²Ñ‚Ð¾Ñ€Ð°Ñ): ", p.method_6079());
                     }

                     this.lastMainName.put(id, main);
                     this.lastOffName.put(id, off);
                  }
               }
            } catch (Throwable var12) {
            }

         }
      }
   }

   private void logUse(class_1657 player, String reason) {
      if (player != null) {
         if (this.trackSelf.isValue() || player != mc.field_1724) {
            String name = this.safe(player.method_5476());
            String itemName = this.safe(player.method_6047().method_7964());
            int green = 5025616;
            class_2561 prefix = class_2561.method_30163("[UseTracker] ");
            class_2561 nameTxt = class_2561.method_30163(name).method_27661().method_10862(class_2583.field_24360.method_36139(green));
            class_2561 mid = class_2561.method_30163(" " + reason + ": ");
            class_2561 itemTxt = class_2561.method_30163(itemName).method_27661().method_10862(class_2583.field_24360.method_36139(green));
            class_2561 full = prefix.method_27661().method_10852(nameTxt).method_10852(mid).method_10852(itemTxt);
            Notifications.getInstance().addList(full, 4000L);
         }
      }
   }

   private void logPickupColored(class_1657 player, class_1799 stack) {
      if (player != null) {
         if (this.trackSelf.isValue() || player != mc.field_1724) {
            String name = this.safe(player.method_5476());
            int green = 5025616;
            class_2561 prefix = class_2561.method_30163("[UseTracker] ");
            class_2561 nameTxt = class_2561.method_30163(name).method_27661().method_10862(class_2583.field_24360.method_36139(green));
            class_2561 mid = class_2561.method_30163(" Ð¿Ð¾Ð´Ð½ÑÐ»: ");
            class_2561 itemTxt = stack.method_7964().method_27661().method_10862(class_2583.field_24360.method_36139(green));
            class_2561 full = prefix.method_27661().method_10852(nameTxt).method_10852(mid).method_10852(itemTxt);
            Notifications.getInstance().addList(full, 4000L);
         }
      }
   }

   private void tryLogUse(class_1657 player, String reason) {
      if (player != null) {
         if (this.trackSelf.isValue() || player != mc.field_1724) {
            int id = player.method_5628();
            long now = System.currentTimeMillis();
            if (!this.inUseNow.contains(id)) {
               Long last = (Long)this.lastLogAt.get(id);
               if (last == null || now - last >= 900L) {
                  this.logUse(player, reason);
                  this.lastLogAt.put(id, now);
                  boolean using = false;

                  try {
                     using = player.method_6115();
                  } catch (Throwable var9) {
                  }

                  if (using) {
                     this.inUseNow.add(id);
                  }

               }
            }
         }
      }
   }

   private void tryLogSwap(class_1657 player, String reason, class_1799 to) {
      if (player != null) {
         if (this.trackSelf.isValue() || player != mc.field_1724) {
            if (this.catSwap.isValue()) {
               int id = player.method_5628();
               long now = System.currentTimeMillis();
               Long last = (Long)this.lastLogAt.get(id);
               if (last == null || now - last >= 450L) {
                  int green = 5025616;
                  class_2561 prefix = class_2561.method_30163("[UseTracker] ");
                  class_2561 nameTxt = class_2561.method_30163(this.safe(player.method_5476())).method_27661().method_10862(class_2583.field_24360.method_36139(green));
                  class_2561 mid = class_2561.method_30163(" " + reason);
                  class_2561 itemTxt = to != null ? to.method_7964().method_27661().method_10862(class_2583.field_24360.method_36139(green)) : class_2561.method_30163("?").method_27661().method_10862(class_2583.field_24360.method_36139(green));
                  class_2561 full = prefix.method_27661().method_10852(nameTxt).method_10852(mid).method_10852(itemTxt);
                  Notifications.getInstance().addList(full, 4000L);
                  this.lastLogAt.put(id, now);
               }
            }
         }
      }
   }

   private boolean isEatDrink(class_3414 se) {
      return se == class_3417.field_20613.comp_349() || se == class_3417.field_20614.comp_349() || se == class_3417.field_19149 || se == class_3417.field_15126 || se == class_3417.field_14834 || se == class_3417.field_14779 || se == class_3417.field_14826;
   }

   private boolean passItemFilters(class_1799 stack) {
      if (stack == null) {
         return false;
      } else if (this.filterAll.isValue()) {
         return true;
      } else {
         boolean ok = false;

         try {
            ok |= stack.method_7909() instanceof class_1738;
         } catch (Throwable var10) {
         }

         try {
            String key = "";

            try {
               key = stack.method_7909().method_7876();
            } catch (Throwable var9) {
            }

            if (key.isEmpty()) {
               try {
                  key = class_7923.field_41178.method_10221(stack.method_7909()).method_12832();
               } catch (Throwable var8) {
               }
            }

            String name = this.safe(stack.method_7964()).toLowerCase();
            boolean isGapple = key.contains("golden_apple") || name.contains("Ð·Ð¾Ð»Ð¾Ñ‚") && name.contains("ÑÐ±Ð»Ð¾Ðº") || name.contains("golden") && name.contains("apple");
            ok |= isGapple;
         } catch (Throwable var11) {
         }

         if (this.filterArmor.isValue() && stack.method_7909() instanceof class_1738) {
            return true;
         } else {
            if (this.filterGapple.isValue()) {
               String k = "";

               try {
                  k = stack.method_7909().method_7876();
               } catch (Throwable var7) {
               }

               if (k.isEmpty()) {
                  try {
                     k = class_7923.field_41178.method_10221(stack.method_7909()).method_12832();
                  } catch (Throwable var6) {
                  }
               }

               String n = this.safe(stack.method_7964()).toLowerCase();
               if (k.contains("golden_apple") || n.contains("golden") && n.contains("apple") || n.contains("Ð·Ð¾Ð»Ð¾Ñ‚") && n.contains("ÑÐ±Ð»Ð¾Ðº")) {
                  return true;
               }
            }

            return ok;
         }
      }
   }

   private class_1799 safeStack(class_1657 p) {
      try {
         return p.method_6047();
      } catch (Throwable var3) {
         return class_1799.field_8037;
      }
   }

   private class_1657 getNearestPlayer(class_243 pos, double radius) {
      class_1657 best = null;
      double bestSq = radius * radius;

      for(class_1657 p : mc.field_1687.method_18456()) {
         double d = p.method_5707(pos);
         if (d <= bestSq) {
            bestSq = d;
            best = p;
         }
      }

      return best;
   }

   private String safe(class_2561 t) {
      try {
         return t.getString();
      } catch (Throwable var3) {
         return "?";
      }
   }
}

