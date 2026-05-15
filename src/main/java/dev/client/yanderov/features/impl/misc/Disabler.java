package dev.client.yanderov.features.impl.misc;

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
import io.netty.buffer.Unpooled;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.class_1304;
import net.minecraft.class_1656;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2540;
import net.minecraft.class_2596;
import net.minecraft.class_2708;
import net.minecraft.class_2817;
import net.minecraft.class_2824;
import net.minecraft.class_2827;
import net.minecraft.class_2828;
import net.minecraft.class_2842;
import net.minecraft.class_2846;
import net.minecraft.class_2848;
import net.minecraft.class_2879;
import net.minecraft.class_2960;
import net.minecraft.class_634;
import net.minecraft.class_2846.class_2847;
import net.minecraft.class_2848.class_2849;

public class Disabler extends Module implements QuickImports {
   private final BooleanSetting funSkyCenter = (new BooleanSetting("FunSky", "Ð¢ÐµÐ»ÐµÐ¿Ð¾Ñ€Ñ‚ Ð² Ñ†ÐµÐ½Ñ‚Ñ€ Ð±Ð»Ð¾ÐºÐ°")).setValue(false);
   private final BooleanSetting cancelKeepAlive = (new BooleanSetting("CancelKeepAlive", "ÐžÑ‚Ð¼ÐµÐ½ÑÑ‚ÑŒ KeepAlive")).setValue(false);
   private final BooleanSetting cancelHandSwing = (new BooleanSetting("CancelHandSwing", "ÐžÑ‚Ð¼ÐµÐ½ÑÑ‚ÑŒ Ð²Ð·Ð¼Ð°Ñ… Ñ€ÑƒÐºÐ¸")).setValue(false);
   private final BooleanSetting cancelAction = (new BooleanSetting("CancelPlayerAction", "ÐžÑ‚Ð¼ÐµÐ½ÑÑ‚ÑŒ PlayerAction")).setValue(false);
   private final BooleanSetting cancelConfirm = (new BooleanSetting("CancelConfirmTransaction", "ÐžÑ‚Ð¼ÐµÐ½ÑÑ‚ÑŒ Confirm")).setValue(false);
   private final BooleanSetting cancelPlayerAbilities = (new BooleanSetting("CancelPlayerAbilities", "ÐžÑ‚Ð¼ÐµÐ½ÑÑ‚ÑŒ ÑÐ¿Ð¾ÑÐ¾Ð±Ð½Ð¾ÑÑ‚Ð¸")).setValue(false);
   private final BooleanSetting cancelBrandPing = (new BooleanSetting("CancelPing", "ÐŸÐ¾Ð´Ð¼ÐµÐ½ÑÑ‚ÑŒ brand/ping")).setValue(false);
   private final BooleanSetting pingSpoof = (new BooleanSetting("PingSpoof", "Ð—Ð°Ð´ÐµÑ€Ð¶Ð¸Ð²Ð°Ñ‚ÑŒ KeepAlive")).setValue(false);
   private final SliderSettings pingDelayMs = (new SliderSettings("Delay", "Ð—Ð°Ð´ÐµÑ€Ð¶ÐºÐ° KA (Ð¼Ñ)")).setValue(1000.0F).range(1.0F, 4000.0F).visible(() -> this.pingSpoof.isValue());
   private final BooleanSetting royalPixels = (new BooleanSetting("RoyalPixels", "Ð¡Ð¿ÑƒÑ„ ÑÐ¿Ð¾ÑÐ¾Ð±Ð½Ð¾ÑÑ‚ÐµÐ¹")).setValue(false);
   private final BooleanSetting universoCraft = (new BooleanSetting("UniversoCraft", "Ð¤Ð¸Ð»ÑŒÑ‚Ñ€ confirm Ð² Ð½Ð°Ñ‡Ð°Ð»Ðµ")).setValue(false);
   private final BooleanSetting minemenStrafe = (new BooleanSetting("MinemenStrafe", "ÐšÐ°Ð¶Ð´Ñ‹Ð¹ 3Ð¹ confirm")).setValue(false);
   private final BooleanSetting noSprint = (new BooleanSetting("NoSprint", "ÐžÑ‚Ð¼ÐµÐ½ÑÑ‚ÑŒ ÑÑ‚Ð°Ñ€Ñ‚ ÑÐ¿Ñ€Ð¸Ð½Ñ‚Ð°")).setValue(false);
   private final BooleanSetting ncpTimerSemi = (new BooleanSetting("NCPTimerSemi", "ÐÐµÐ±Ð¾Ð»ÑŒÑˆÐ¾Ð¹ Ñ‚Ð°Ð¹Ð¼ÐµÑ€ Ñ‚Ð²Ð¸Ðº")).setValue(false);
   private final BooleanSetting hac = (new BooleanSetting("HAC", "ÐŸÐµÑ€Ð¸Ð¾Ð´Ð¸Ñ‡ÐµÑÐºÐ¸Ðµ Ð¿Ð°ÐºÐµÑ‚Ñ‹ Ð²Ð½Ð¸Ð·")).setValue(false);
   private final BooleanSetting aac5 = (new BooleanSetting("AAC5", "Ð‘Ñ‘Ñ€ÑÑ‚ Ð¿Ð°ÐºÐµÑ‚Ð¾Ð²")).setValue(false);
   private final BooleanSetting antiVanilla = (new BooleanSetting("NoFlag", "Ð¡Ñ€ÐµÐ·Ð°Ñ‚ÑŒ Ð¼ÐµÐ»ÐºÐ¸Ðµ poslook")).setValue(false);
   private final BooleanSetting matrixElytraSpoofs = (new BooleanSetting("MatrixElytraSpoofs", "Ð¡Ð¿ÑƒÑ„ ÑÑ‚Ð°Ñ€Ñ‚Ð° Ð¿Ð¾Ð»Ñ‘Ñ‚Ð° ÑÐ»Ð¸Ñ‚Ñ€")).setValue(false);
   private final BooleanSetting ncpMovement = (new BooleanSetting("NCPMovement", "ÐŸÐ¾Ð²ÐµÐ´ÐµÐ½Ð¸Ðµ NCP Ð´Ð»Ñ ÑÐ»Ð¸Ñ‚Ñ€")).setValue(false);
   private final BooleanSetting vulcanStrafe = (new BooleanSetting("VulcanStrafe", "Ð¡Ñ‚Ñ€ÐµÐ¹Ñ„ Vulcan (JESUS) Ð´Ð¸Ð·ÐµÐ¹Ð±Ð»ÐµÑ€")).setValue(false);
   private final BooleanSetting noRotationDisabler = (new BooleanSetting("NoRotationDisabler", "ÐŸÐ¾Ð´Ð¼ÐµÐ½Ð° ÑƒÐ³Ð»Ð¾Ð² Ð² Ð´Ð²Ð¸Ð¶ÐµÐ½Ð¸Ð¸")).setValue(false);
   private final SelectSetting rotMode = (new SelectSetting("RotMode", "Ð ÐµÐ¶Ð¸Ð¼ Ð¿Ð¾Ð´Ð¼ÐµÐ½Ñ‹ ÑƒÐ³Ð»Ð¾Ð²")).value("Spoof", "Zero", "OffsetYaw").selected("Spoof");
   private final SliderSettings offsetAmount = (new SliderSettings("OffsetAmount", "Ð¡Ð¼ÐµÑ‰ÐµÐ½Ð¸Ðµ yaw")).setValue(6.0F).range(-180.0F, 180.0F).visible(() -> this.noRotationDisabler.isValue());
   private boolean canHackJesus = false;
   private long lastStartFalling = 0L;
   private final List kaBuffer = new ArrayList();
   private boolean universoDisabling = false;

   public Disabler() {
      super("Disabler", ModuleCategory.MISC);
      this.setup(new Setting[]{this.funSkyCenter, this.cancelKeepAlive, this.cancelHandSwing, this.cancelAction, this.cancelConfirm, this.cancelPlayerAbilities, this.cancelBrandPing, this.pingSpoof, this.pingDelayMs, this.royalPixels, this.universoCraft, this.minemenStrafe, this.noSprint, this.ncpTimerSemi, this.hac, this.aac5, this.antiVanilla, this.matrixElytraSpoofs, this.ncpMovement, this.vulcanStrafe, this.noRotationDisabler, this.rotMode, this.offsetAmount});
   }

   public static Disabler getInstance() {
      return (Disabler)Instance.get(Disabler.class);
   }

   public boolean isFunSkyCenter() {
      return this.funSkyCenter.isValue();
   }

   @EventHandler
   public void onPacket(PacketEvent e) {
      if (mc != null && mc.field_1724 != null && mc.field_1687 != null) {
         class_2596<?> pkt = e.getPacket();
         if (e.getType() == PacketEvent.Type.RECEIVE) {
            if (this.antiVanilla.isValue() && pkt instanceof class_2708) {
               class_2708 s2c = (class_2708)pkt;
               if (mc.field_1724.field_6012 > 20) {
                  double dx = this.getS2CPosX(s2c) - mc.field_1724.method_23317();
                  double dy = this.getS2CPosY(s2c) - mc.field_1724.method_23318();
                  double dz = this.getS2CPosZ(s2c) - mc.field_1724.method_23321();
                  double dist = Math.sqrt(dx * dx + dy * dy + dz * dz);
                  if (dist <= (double)8.0F) {
                     e.cancel();
                     class_634 nh = mc.method_1562();
                     if (nh != null) {
                        class_2828.class_2830 back = new class_2828.class_2830(this.getS2CPosX(s2c), this.getS2CPosY(s2c), this.getS2CPosZ(s2c), this.getS2CYaw(s2c), this.getS2CPitch(s2c), true, false);
                        PlayerInteractionHelper.sendPacketWithOutEvent(back);
                     }
                  }
               }
            }

         } else {
            if (this.noRotationDisabler.isValue() && pkt instanceof class_2828) {
               String mode = this.rotMode.getSelected();
               if (!"Spoof".equals(mode)) {
                  double x = mc.field_1724.method_23317();
                  double y = mc.field_1724.method_23318();
                  double z = mc.field_1724.method_23321();
                  float yaw = mc.field_1724.method_36454();
                  float pitch = mc.field_1724.method_36455();
                  boolean onGround = mc.field_1724.method_24828();
                  if ("Zero".equals(mode)) {
                     yaw = 0.0F;
                     pitch = 0.0F;
                  } else if ("OffsetYaw".equals(mode)) {
                     yaw += this.offsetAmount.getValue();
                  }

                  e.cancel();
                  class_2828 replacement = new class_2828.class_2830(x, y, z, yaw, pitch, onGround, false);
                  PlayerInteractionHelper.sendPacketWithOutEvent(replacement);
                  return;
               }
            }

            if (this.cancelKeepAlive.isValue() && pkt instanceof class_2827) {
               e.cancel();
            } else if (this.cancelHandSwing.isValue() && pkt instanceof class_2879) {
               e.cancel();
            } else if (this.cancelAction.isValue() && pkt instanceof class_2846) {
               e.cancel();
            } else if (this.cancelPlayerAbilities.isValue() && pkt instanceof class_2842) {
               e.cancel();
            } else if (this.cancelConfirm.isValue() && this.isConfirmPacket(pkt)) {
               e.cancel();
            } else if (this.minemenStrafe.isValue() && this.isConfirmPacket(pkt) && mc.field_1724.field_6012 % 3 == 0) {
               e.cancel();
            } else {
               if (this.universoCraft.isValue()) {
                  if (pkt instanceof class_2828 && mc.field_1724.field_6012 <= 10) {
                     this.universoDisabling = true;
                  } else if (pkt instanceof class_2824) {
                     this.universoDisabling = false;
                  } else if (this.isConfirmPacket(pkt) && this.universoDisabling && mc.field_1724.field_6012 < 350 && mc.field_1724.field_6012 % 2 == 0) {
                     e.cancel();
                     return;
                  }
               }

               if (this.noSprint.isValue()) {
                  String name = pkt.getClass().getName();
                  if (name.endsWith("ClientCommandC2SPacket")) {
                     try {
                        for(Field field : pkt.getClass().getDeclaredFields()) {
                           field.setAccessible(true);
                           Object val = field.get(pkt);
                           if (String.valueOf(val).toUpperCase().contains("SPRINT")) {
                              e.cancel();
                              return;
                           }
                        }
                     } catch (Throwable var14) {
                     }
                  }
               }

               if (this.pingSpoof.isValue() && pkt instanceof class_2827) {
                  class_2827 ka = (class_2827)pkt;
                  long id = this.resolveKAId(ka);
                  long extra = (long)(Math.random() * (double)200.0F);
                  this.kaBuffer.add(new PendingKA(id, System.currentTimeMillis() + (long)this.pingDelayMs.getValue() + extra));
                  e.cancel();
               } else {
                  if (this.hac.isValue() && mc.field_1724.field_6012 % 10 == 0) {
                     class_2828.class_2830 down = new class_2828.class_2830(mc.field_1724.method_23317(), mc.field_1724.method_23318() - (double)11.0F, mc.field_1724.method_23321(), mc.field_1724.method_36454(), mc.field_1724.method_36455(), true, false);
                     PlayerInteractionHelper.sendPacketWithOutEvent(down);
                  }

                  if (this.aac5.isValue() && !mc.method_1496()) {
                     class_2828.class_2830 p1 = new class_2828.class_2830(mc.field_1724.method_23317(), mc.field_1724.method_23318() - (double)100.0F, mc.field_1724.method_23321() + (double)10.0F, 0.0F, 0.0F, true, false);
                     class_2828.class_2830 p2 = new class_2828.class_2830(mc.field_1724.method_23317(), mc.field_1724.method_23318(), mc.field_1724.method_23321(), 0.0F, 0.0F, true, false);
                     PlayerInteractionHelper.sendPacketWithOutEvent(p1);
                     PlayerInteractionHelper.sendPacketWithOutEvent(p2);
                  }

                  if (pkt instanceof class_2817) {
                     class_2817 cp = (class_2817)pkt;
                     if (this.cancelBrandPing.isValue()) {
                        class_2960 ch = this.getCustomPayloadChannel(cp);
                        if (ch != null && ("minecraft:brand".equals(ch.toString()) || "MC|Brand".equalsIgnoreCase(ch.method_12832()))) {
                           class_2540 buf = new class_2540(Unpooled.buffer());
                           buf.method_10814("blc");
                           class_2596<?> rep = this.buildCustomPayloadReplacement(cp, ch, buf);
                           if (rep != null) {
                              e.setPacket(rep);
                           }
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
         if (mc != null && mc.field_1724 != null) {
            if (this.vulcanStrafe.isValue()) {
               if (mc.field_1724.field_6012 % 11 == 7) {
                  try {
                     class_2338 pos = class_2338.field_10980.method_10087(61);
                     class_2350 dir = mc.field_1724.method_5735().method_10153();
                     PlayerInteractionHelper.sendPacketWithOutEvent(new class_2846(class_2847.field_12973, pos, dir));
                  } catch (Throwable var11) {
                  }
               }

               this.canHackJesus = mc.field_1724.field_6012 > 8;
            }

            if (this.matrixElytraSpoofs.isValue() && this.hasElytraAnywhere() && System.currentTimeMillis() - this.lastStartFalling > 150L) {
               try {
                  class_2848 pkt = new class_2848(mc.field_1724, class_2849.field_12982);
                  PlayerInteractionHelper.sendPacketWithOutEvent(pkt);
                  PlayerInteractionHelper.sendPacketWithOutEvent(pkt);
                  this.lastStartFalling = System.currentTimeMillis();
               } catch (Throwable var10) {
               }
            }

            if (this.ncpMovement.isValue()) {
               try {
                  class_1799 chest = mc.field_1724.method_6118(class_1304.field_6174);
                  if (!chest.method_31574(class_1802.field_8833)) {
                     return;
                  }

                  if (mc.field_1724.method_24828() && !this.playerIsFallFlying()) {
                     mc.field_1724.method_6043();
                  }

                  if (!this.isMovingKeys()) {
                     mc.field_1724.method_18800((double)0.0F, mc.field_1724.method_18798().field_1351, (double)0.0F);
                  }

                  if ((!this.hasGroundBelow((double)1.5F) || mc.field_1724.field_5992) && this.playerIsFallFlying()) {
                     mc.field_1724.method_18800(mc.field_1724.method_18798().field_1352, mc.field_1724.field_5992 ? (double)1.0F : (double)0.5F, mc.field_1724.method_18798().field_1350);
                  } else if (this.playerIsFallFlying()) {
                     double y = mc.field_1724.field_6012 % 14 == 0 ? (double)-0.25F : -0.05;
                     mc.field_1724.method_18800(mc.field_1724.method_18798().field_1352, y, mc.field_1724.method_18798().field_1350);
                  }

                  if (this.isElytraUsable(chest) && !this.playerIsFallFlying() && !mc.field_1724.method_31549().field_7479 && mc.field_1724.field_6017 >= 0.2F) {
                     class_2848 pkt = new class_2848(mc.field_1724, class_2849.field_12982);
                     PlayerInteractionHelper.sendPacketWithOutEvent(pkt);
                  }
               } catch (Throwable var12) {
               }
            }

            if (this.ncpTimerSemi.isValue()) {
               try {
                  mc.field_1724.method_30634(mc.field_1724.method_23317(), mc.field_1724.method_23318() - 0.017, mc.field_1724.method_23321());
                  mc.field_1724.method_18800(mc.field_1724.method_18798().field_1352, 0.019, mc.field_1724.method_18798().field_1350);
               } catch (Throwable var9) {
               }
            }

            if (this.royalPixels.isValue()) {
               try {
                  class_1656 ab = mc.field_1724.method_31549();
                  ab.field_7478 = true;
                  ab.field_7479 = true;
                  PlayerInteractionHelper.sendPacketWithOutEvent(new class_2842(ab));
               } catch (Throwable var8) {
               }
            }

            if (this.pingSpoof.isValue() && !this.kaBuffer.isEmpty()) {
               long now = System.currentTimeMillis();
               Iterator<PendingKA> it = this.kaBuffer.iterator();

               while(it.hasNext()) {
                  PendingKA pk = (PendingKA)it.next();
                  if (pk.at <= now) {
                     try {
                        PlayerInteractionHelper.sendPacketWithOutEvent(new class_2827(pk.id));
                     } catch (Throwable var7) {
                     }

                     it.remove();
                  }
               }
            }

         }
      }
   }

   private boolean isConfirmPacket(class_2596 pkt) {
      String n = pkt.getClass().getName();
      return n.endsWith("ConfirmScreenActionC2SPacket") || n.endsWith("ConfirmTransactionC2SPacket") || n.endsWith("ConfirmGuiActionC2SPacket");
   }

   private class_2960 getCustomPayloadChannel(class_2817 cp) {
      try {
         return (class_2960)cp.getClass().getMethod("getChannel").invoke(cp);
      } catch (Throwable var6) {
         try {
            return (class_2960)cp.getClass().getMethod("getIdentifier").invoke(cp);
         } catch (Throwable var5) {
            try {
               Field f = cp.getClass().getDeclaredField("channel");
               f.setAccessible(true);
               return (class_2960)f.get(cp);
            } catch (Throwable var4) {
               try {
                  Field f = cp.getClass().getDeclaredField("identifier");
                  f.setAccessible(true);
                  return (class_2960)f.get(cp);
               } catch (Throwable var3) {
                  return null;
               }
            }
         }
      }
   }

   private class_2596 buildCustomPayloadReplacement(class_2817 original, class_2960 ch, class_2540 data) {
      Class<?> cls = original.getClass();

      try {
         for(Constructor ctor : cls.getDeclaredConstructors()) {
            ctor.setAccessible(true);
            Class<?>[] pt = ctor.getParameterTypes();

            try {
               if (pt.length == 2 && class_2540.class.isAssignableFrom(pt[1])) {
                  if (class_2960.class.isAssignableFrom(pt[0])) {
                     Object np = ctor.newInstance(ch, data);
                     if (np instanceof class_2596) {
                        return (class_2596)np;
                     }
                  } else if (pt[0] == String.class) {
                     Object np = ctor.newInstance(ch.toString(), data);
                     if (np instanceof class_2596) {
                        return (class_2596)np;
                     }
                  }
               }
            } catch (Throwable var11) {
            }
         }
      } catch (Throwable var12) {
      }

      return null;
   }

   private long resolveKAId(class_2827 ka) {
      try {
         return (Long)ka.getClass().getMethod("getId").invoke(ka);
      } catch (Throwable var6) {
         try {
            Field f = ka.getClass().getDeclaredField("id");
            f.setAccessible(true);
            Object v = f.get(ka);
            if (v instanceof Long l) {
               return l;
            }

            if (v instanceof Integer i) {
               return i.longValue();
            }
         } catch (Throwable var5) {
         }

         return 0L;
      }
   }

   private boolean isElytraUsable(class_1799 stack) {
      try {
         if (stack != null && stack.method_31574(class_1802.field_8833)) {
            int max = stack.method_7936();
            if (max <= 0) {
               return true;
            } else {
               return stack.method_7919() < max - 1;
            }
         } else {
            return false;
         }
      } catch (Throwable var3) {
         return stack != null && stack.method_31574(class_1802.field_8833);
      }
   }

   private boolean playerIsFallFlying() {
      try {
         return (Boolean)mc.field_1724.getClass().getMethod("isFallFlying").invoke(mc.field_1724);
      } catch (Throwable var2) {
         return false;
      }
   }

   private boolean hasElytraAnywhere() {
      try {
         class_1799 chest = mc.field_1724.method_6118(class_1304.field_6174);
         if (chest != null && chest.method_31574(class_1802.field_8833)) {
            return true;
         }
      } catch (Throwable var2) {
      }

      return false;
   }

   private boolean isMovingKeys() {
      if (mc.field_1690 == null) {
         return false;
      } else {
         try {
            return mc.field_1690.field_1894.method_1434() || mc.field_1690.field_1881.method_1434() || mc.field_1690.field_1913.method_1434() || mc.field_1690.field_1849.method_1434();
         } catch (Throwable var2) {
            return false;
         }
      }
   }

   private boolean hasGroundBelow(double dist) {
      if (mc.field_1687 != null && mc.field_1724 != null) {
         double y = mc.field_1724.method_23318();
         int steps = Math.max(1, (int)Math.ceil(dist / (double)0.5F));

         for(int i = 1; i <= steps; ++i) {
            int checkY = (int)Math.floor(y - (double)i * (double)0.5F);
            class_2338 pos = new class_2338((int)Math.floor(mc.field_1724.method_23317()), checkY, (int)Math.floor(mc.field_1724.method_23321()));
            if (!mc.field_1687.method_8320(pos).method_26215()) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   private double getS2CPosX(class_2708 p) {
      try {
         return (Double)p.getClass().getMethod("getX").invoke(p);
      } catch (Throwable var5) {
         try {
            Field f = p.getClass().getDeclaredField("x");
            f.setAccessible(true);
            return ((Number)f.get(p)).doubleValue();
         } catch (Throwable var4) {
            return mc.field_1724.method_23317();
         }
      }
   }

   private double getS2CPosY(class_2708 p) {
      try {
         return (Double)p.getClass().getMethod("getY").invoke(p);
      } catch (Throwable var5) {
         try {
            Field f = p.getClass().getDeclaredField("y");
            f.setAccessible(true);
            return ((Number)f.get(p)).doubleValue();
         } catch (Throwable var4) {
            return mc.field_1724.method_23318();
         }
      }
   }

   private double getS2CPosZ(class_2708 p) {
      try {
         return (Double)p.getClass().getMethod("getZ").invoke(p);
      } catch (Throwable var5) {
         try {
            Field f = p.getClass().getDeclaredField("z");
            f.setAccessible(true);
            return ((Number)f.get(p)).doubleValue();
         } catch (Throwable var4) {
            return mc.field_1724.method_23321();
         }
      }
   }

   private float getS2CYaw(class_2708 p) {
      try {
         return (Float)p.getClass().getMethod("getYaw").invoke(p);
      } catch (Throwable var5) {
         try {
            Field f = p.getClass().getDeclaredField("yaw");
            f.setAccessible(true);
            return ((Number)f.get(p)).floatValue();
         } catch (Throwable var4) {
            return mc.field_1724.method_36454();
         }
      }
   }

   private float getS2CPitch(class_2708 p) {
      try {
         return (Float)p.getClass().getMethod("getPitch").invoke(p);
      } catch (Throwable var5) {
         try {
            Field f = p.getClass().getDeclaredField("pitch");
            f.setAccessible(true);
            return ((Number)f.get(p)).floatValue();
         } catch (Throwable var4) {
            return mc.field_1724.method_36455();
         }
      }
   }

   private static final class PendingKA {
      final long id;
      final long at;

      PendingKA(long id, long at) {
         this.id = id;
         this.at = at;
      }
   }
}

