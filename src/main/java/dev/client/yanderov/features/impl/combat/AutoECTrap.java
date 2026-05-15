package dev.client.yanderov.features.impl.combat;

import dev.client.yanderov.events.packet.PacketEvent;
import dev.client.yanderov.events.player.TickEvent;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.features.module.setting.Setting;
import dev.client.yanderov.features.module.setting.implement.BooleanSetting;
import dev.client.yanderov.features.module.setting.implement.SelectSetting;
import dev.client.yanderov.features.module.setting.implement.SliderSettings;
import dev.client.yanderov.utils.client.Instance;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import dev.client.yanderov.utils.display.interfaces.QuickImports;
import dev.client.yanderov.utils.interactions.interact.PlayerInteractionHelper;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1747;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_2824;
import net.minecraft.class_3965;
import net.minecraft.class_742;
import net.minecraft.class_746;

public class AutoECTrap extends Module implements QuickImports {
   private final SelectSetting mode = (new SelectSetting("Ð ÐµÐ¶Ð¸Ð¼", "Ð’Ñ‹Ð±Ð¾Ñ€ Ñ€ÐµÐ¶Ð¸Ð¼Ð°")).value("Legit", "HvH").selected("Legit");
   private final SliderSettings hvhDistance = (new SliderSettings("Ð”Ð¸ÑÑ‚Ð°Ð½Ñ†Ð¸Ñ", "Ð”Ð¸ÑÑ‚Ð°Ð½Ñ†Ð¸Ñ Ð´Ð»Ñ HvH")).setValue(5.5F).range(3.0F, 12.0F).visible(() -> this.mode.isSelected("HvH"));
   private final SliderSettings delayMs = (new SliderSettings("Ð—Ð°Ð´ÐµÑ€Ð¶ÐºÐ°", "Ð—Ð°Ð´ÐµÑ€Ð¶ÐºÐ° Ð¼ÐµÐ¶Ð´Ñƒ Ð¿Ð¾Ð¿Ñ‹Ñ‚ÐºÐ°Ð¼Ð¸ (Ð¼Ñ)")).setValue(120.0F).range(0.0F, 1000.0F);
   private final BooleanSetting inventoryPlace = (new BooleanSetting("Ð¡Ñ‚Ð°Ð²Ð¸Ñ‚ÑŒ Ð¸Ð· Ð¸Ð½Ð²ÐµÐ½Ñ‚Ð°Ñ€Ñ", "ÐšÐ»Ð°ÑÑ‚ÑŒ Ð¸Ð· Ð¸Ð½Ð²ÐµÐ½Ñ‚Ð°Ñ€Ñ Ð² Ñ…Ð¾Ñ‚Ð±Ð°Ñ€")).setValue(true);
   private final BooleanSetting regionBypass = (new BooleanSetting("Ð ÐµÐ³Ð¸Ð¾Ð½ ÑÐºÑÐ¿Ð»Ð¾Ð¸Ñ‚", "ÐŸÑ€Ð¾Ð¿ÑƒÑÐºÐ°Ñ‚ÑŒ Ð¿Ð¾Ð·Ð¸Ñ†Ð¸Ð¸, Ð³Ð´Ðµ Ð½Ðµ ÑÑ‚Ð°Ð²Ð¸Ñ‚ÑÑ")).setValue(true);
   private UUID lastTargetUUID = null;
   private long lastHitAt = 0L;
   private final Map newObsidian = new HashMap();
   private final Map attemptedPlaceAt = new HashMap();
   private final Set skipPositions = new HashSet();
   private long lastPlaceMs = 0L;

   public AutoECTrap() {
      super("AutoECTrap", ModuleCategory.COMBAT);
      this.setup(new Setting[]{this.mode, this.hvhDistance, this.delayMs, this.inventoryPlace, this.regionBypass});
   }

   public static AutoECTrap getInstance() {
      return (AutoECTrap)Instance.get(AutoECTrap.class);
   }

   @EventHandler
   public void onPacket(PacketEvent e) {
      if (this.isState()) {
         if (e.isSend()) {
            if (mc.field_1687 != null) {
               class_2596 var3 = e.getPacket();
               if (var3 instanceof class_2824) {
                  class_2824 pkt = (class_2824)var3;

                  try {
                     int id = -1;

                     try {
                        Method m = pkt.getClass().getMethod("getEntityId");
                        m.setAccessible(true);
                        id = ((Number)m.invoke(pkt)).intValue();
                     } catch (Throwable var7) {
                     }

                     if (id == -1) {
                        try {
                           Field f = pkt.getClass().getDeclaredField("entityId");
                           f.setAccessible(true);
                           id = ((Number)f.get(pkt)).intValue();
                        } catch (Throwable var6) {
                        }
                     }

                     if (id != -1) {
                        class_1297 ent = mc.field_1687.method_8469(id);
                        if (ent instanceof class_1309) {
                           class_1309 le = (class_1309)ent;
                           this.lastTargetUUID = le.method_5667();
                           this.lastHitAt = System.currentTimeMillis();
                        }
                     }
                  } catch (Throwable var8) {
                  }
               }

            }
         }
      }
   }

   @EventHandler
   public void onTick(TickEvent e) {
      if (this.isState()) {
         if (e.getType() == 0) {
            if (mc != null && mc.field_1687 != null && mc.field_1724 != null) {
               int keyCode = this.getKey();
               if (keyCode != -1 && PlayerInteractionHelper.isKey(PlayerInteractionHelper.getKeyType(keyCode), keyCode)) {
                  class_1309 target = this.findTargetByUUID();
                  if (target != null) {
                     int scanRadius = 3;
                     class_2338 tp = target.method_24515();
                     long now = System.currentTimeMillis();

                     for(int dx = -scanRadius; dx <= scanRadius; ++dx) {
                        for(int dy = -1; dy <= 2; ++dy) {
                           for(int dz = -scanRadius; dz <= scanRadius; ++dz) {
                              class_2338 pos = tp.method_10069(dx, dy, dz);
                              if (!this.skipPositions.contains(pos) && mc.field_1687.method_8320(pos).method_26204() == class_2246.field_10540) {
                                 this.newObsidian.putIfAbsent(pos, now);
                              }
                           }
                        }
                     }

                     long minFreshWindow = 3000L;
                     long delay = (long)this.delayMs.getValue();
                     if (now - this.lastPlaceMs >= delay) {
                        for(Map.Entry entry : new ArrayList(this.newObsidian.entrySet())) {
                           class_2338 ob = (class_2338)entry.getKey();
                           long t = (Long)entry.getValue();
                           if (now - t > minFreshWindow) {
                              this.newObsidian.remove(ob);
                           } else {
                              class_2338 placePos = ob.method_10084();
                              if (PlayerInteractionHelper.isAir(placePos)) {
                                 double maxDist = this.mode.isSelected("Legit") ? (double)4.5F : (double)this.hvhDistance.getValue();
                                 double dist = mc.field_1724.method_33571().method_1022(class_243.method_24953(placePos));
                                 if (!(dist > maxDist)) {
                                    int hotbarSlot = this.findEnderChestInHotbar();
                                    Integer invSlot = null;
                                    boolean movedToHotbar = false;
                                    if (hotbarSlot == -1) {
                                       invSlot = this.findEnderChestInInventory();
                                       if (invSlot == null || !this.inventoryPlace.isValue()) {
                                          continue;
                                       }

                                       movedToHotbar = this.moveInventoryToHotbar(invSlot, 0);
                                       if (!movedToHotbar) {
                                          continue;
                                       }

                                       hotbarSlot = 0;
                                    }

                                    int prevSlot = mc.field_1724.method_31548().field_7545;
                                    mc.field_1724.method_31548().field_7545 = hotbarSlot;
                                    boolean placed = this.tryPlaceOnTop(ob);
                                    mc.field_1724.method_31548().field_7545 = prevSlot;
                                    if (movedToHotbar) {
                                       this.restoreMovedFromHotbar(invSlot, 0);
                                    }

                                    this.lastPlaceMs = now;
                                    this.attemptedPlaceAt.put(ob, now);
                                    if (placed) {
                                       this.newObsidian.remove(ob);
                                    } else if (this.regionBypass.isValue()) {
                                       this.skipPositions.add(ob);
                                       this.newObsidian.remove(ob);
                                    }
                                    break;
                                 }
                              }
                           }
                        }

                        if (this.regionBypass.isValue()) {
                           Iterator<Map.Entry<class_2338, Long>> it = this.attemptedPlaceAt.entrySet().iterator();

                           while(it.hasNext()) {
                              Map.Entry<class_2338, Long> en = (Map.Entry)it.next();
                              class_2338 base = (class_2338)en.getKey();
                              long ts = (Long)en.getValue();
                              if (now - ts > 1500L) {
                                 if (mc.field_1687.method_8320(base.method_10084()).method_26204() != class_2246.field_10443) {
                                    this.skipPositions.add(base);
                                 }

                                 it.remove();
                              }
                           }
                        }

                     }
                  }
               }
            }
         }
      }
   }

   private class_1309 findTargetByUUID() {
      if (this.lastTargetUUID == null) {
         return null;
      } else {
         for(class_742 pl : mc.field_1687.method_18456()) {
            if (pl.method_5667().equals(this.lastTargetUUID)) {
               return pl;
            }
         }

         return null;
      }
   }

   private int findEnderChestInHotbar() {
      for(int i = 0; i < 9; ++i) {
         class_1799 st = mc.field_1724.method_31548().method_5438(i);
         if (this.isEnderChest(st)) {
            return i;
         }
      }

      return -1;
   }

   private Integer findEnderChestInInventory() {
      for(int i = 9; i < mc.field_1724.method_31548().method_5439(); ++i) {
         class_1799 st = mc.field_1724.method_31548().method_5438(i);
         if (this.isEnderChest(st)) {
            return i;
         }
      }

      return null;
   }

   private boolean isEnderChest(class_1799 st) {
      if (st != null && !st.method_7960()) {
         class_1792 it = st.method_7909();
         if (it == class_1802.field_8466) {
            return true;
         } else {
            boolean var10000;
            if (it instanceof class_1747) {
               class_1747 bi = (class_1747)it;
               if (bi.method_7711().method_8389() == class_1802.field_8466) {
                  var10000 = true;
                  return var10000;
               }
            }

            var10000 = false;
            return var10000;
         }
      } else {
         return false;
      }
   }

   private boolean moveInventoryToHotbar(int invSlot, int hotbarSlot) {
      try {
         class_1799 src = mc.field_1724.method_31548().method_5438(invSlot).method_7972();
         class_1799 dst = mc.field_1724.method_31548().method_5438(hotbarSlot).method_7972();
         mc.field_1724.method_31548().method_5447(hotbarSlot, src);
         mc.field_1724.method_31548().method_5447(invSlot, dst);
         return true;
      } catch (Throwable var5) {
         return false;
      }
   }

   private void restoreMovedFromHotbar(int invSlot, int hotbarSlot) {
      try {
         class_1799 src = mc.field_1724.method_31548().method_5438(hotbarSlot).method_7972();
         class_1799 dst = mc.field_1724.method_31548().method_5438(invSlot).method_7972();
         mc.field_1724.method_31548().method_5447(invSlot, src);
         mc.field_1724.method_31548().method_5447(hotbarSlot, dst);
      } catch (Throwable var5) {
      }

   }

   private boolean tryPlaceOnTop(class_2338 base) {
      try {
         class_746 p = mc.field_1724;
         class_243 hitVec = class_243.method_24953(base).method_1031((double)0.0F, (double)0.5F, (double)0.0F);
         class_3965 hit = new class_3965(hitVec, class_2350.field_11036, base, false);
         mc.field_1765 = hit;
         class_1269 res = mc.field_1761.method_2896(p, class_1268.field_5808, hit);
         return res.method_23665();
      } catch (Throwable var6) {
         return false;
      }
   }

   public SelectSetting getMode() {
      return this.mode;
   }

   public SliderSettings getHvhDistance() {
      return this.hvhDistance;
   }

   public SliderSettings getDelayMs() {
      return this.delayMs;
   }

   public BooleanSetting getInventoryPlace() {
      return this.inventoryPlace;
   }

   public BooleanSetting getRegionBypass() {
      return this.regionBypass;
   }

   public UUID getLastTargetUUID() {
      return this.lastTargetUUID;
   }

   public long getLastHitAt() {
      return this.lastHitAt;
   }

   public Map getNewObsidian() {
      return this.newObsidian;
   }

   public Map getAttemptedPlaceAt() {
      return this.attemptedPlaceAt;
   }

   public Set getSkipPositions() {
      return this.skipPositions;
   }

   public long getLastPlaceMs() {
      return this.lastPlaceMs;
   }
}

