package fun.Yanderov.features.impl.combat;

import fun.Yanderov.events.keyboard.KeyEvent;
import fun.Yanderov.events.packet.PacketEvent;
import fun.Yanderov.events.player.AttackEvent;
import fun.Yanderov.events.player.MoveEvent;
import fun.Yanderov.events.player.TickEvent;
import fun.Yanderov.events.render.WorldRenderEvent;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.features.module.setting.Setting;
import fun.Yanderov.features.module.setting.implement.BindSetting;
import fun.Yanderov.features.module.setting.implement.BooleanSetting;
import fun.Yanderov.features.module.setting.implement.SliderSettings;
import fun.Yanderov.utils.client.Instance;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import fun.Yanderov.utils.display.interfaces.QuickImports;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import net.minecraft.class_1268;
import net.minecraft.class_1799;
import net.minecraft.class_1829;
import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_2828;
import net.minecraft.class_742;
import net.minecraft.class_746;

public class TimeManipulation extends Module implements QuickImports {
   private final SliderSettings triggerDistance = (new SliderSettings("Distance", "Ð”Ð¸ÑÑ‚Ð°Ð½Ñ†Ð¸Ñ Ñ‚Ñ€Ð¸Ð³Ð³ÐµÑ€Ð°")).range(0.0F, 10.0F).setValue(2.0F);
   private final BooleanSetting onlySword = new BooleanSetting("OnlySword", "Ð¢Ð¾Ð»ÑŒÐºÐ¾ Ñ Ð¼ÐµÑ‡Ð¾Ð¼");
   private final BooleanSetting onlyCriticals = new BooleanSetting("OnlyCriticals", "Ð¢Ð¾Ð»ÑŒÐºÐ¾ ÐºÑ€Ð¸Ñ‚Ñ‹");
   private final BooleanSetting slowFallUtil = new BooleanSetting("SlowFallUtil", "ÐœÐµÐ´Ð»ÐµÐ½Ð½Ð¾Ðµ Ð¿Ð°Ð´ÐµÐ½Ð¸Ðµ");
   private final BooleanSetting waterCriticals = new BooleanSetting("WaterCriticals", "ÐšÑ€Ð¸Ñ‚Ñ‹ Ð² Ð²Ð¾Ð´Ðµ");
   private final BooleanSetting onlyStill = new BooleanSetting("UseStill", "Ð¢Ð¾Ð»ÑŒÐºÐ¾ ÑÑ‚Ð¾Ñ");
   private final BindSetting holdDisableBind = new BindSetting("UseStill Hold Bind", "ÐžÑ‚ÐºÐ»ÑŽÑ‡ÐµÐ½Ð¸Ðµ Ð¿Ñ€Ð¸ Ð·Ð°Ð¶Ð°Ñ‚Ð¸Ð¸");
   private final BooleanSetting useTime = new BooleanSetting("UseTime", "Ð˜ÑÐ¿Ð¾Ð»ÑŒÐ·Ð¾Ð²Ð°Ñ‚ÑŒ Ð²Ñ€ÐµÐ¼Ñ");
   private final SliderSettings timeSeconds = (new SliderSettings("Time", "Ð’Ñ€ÐµÐ¼Ñ Ð² ÑÐµÐºÑƒÐ½Ð´Ð°Ñ…")).range(0.0F, 20.0F).setValue(3.0F);
   private final BooleanSetting freezeBypass = new BooleanSetting("Freeze Bypass", "ÐžÐ±Ñ…Ð¾Ð´ Ð·Ð°Ð¼Ð¾Ñ€Ð¾Ð·ÐºÐ¸");
   private final BindSetting unfreezeBind = new BindSetting("Unfreeze Bind", "Ð Ð°Ð·Ð¼Ð¾Ñ€Ð¾Ð·ÐºÐ°");
   private final SliderSettings unfreezeTickMs = (new SliderSettings("Unfreeze Tick (ms)", "Ð¢Ð¸Ðº Ñ€Ð°Ð·Ð¼Ð¾Ñ€Ð¾Ð·ÐºÐ¸")).range(0.0F, 1000.0F).setValue(20.0F);
   private final BooleanSetting freezeBypassOnlySword = new BooleanSetting("Unfreeze Only Sword", "Ð Ð°Ð·Ð¼Ð¾Ñ€Ð¾Ð·ÐºÐ° Ñ‚Ð¾Ð»ÑŒÐºÐ¾ Ñ Ð¼ÐµÑ‡Ð¾Ð¼");
   private final BooleanSetting velocityTrigger = new BooleanSetting("Velocity Trigger", "Ð¢Ñ€Ð¸Ð³Ð³ÐµÑ€ ÑÐºÐ¾Ñ€Ð¾ÑÑ‚Ð¸");
   private final BooleanSetting autoFreeze = new BooleanSetting("Auto Freeze", "ÐÐ²Ñ‚Ð¾ Ð·Ð°Ð¼Ð¾Ñ€Ð¾Ð·ÐºÐ°");
   private final BooleanSetting teleport = new BooleanSetting("Teleport", "Ð¢ÐµÐ»ÐµÐ¿Ð¾Ñ€Ñ‚");
   private final SliderSettings teleportStep = (new SliderSettings("TeleportStep", "Ð¨Ð°Ð³ Ñ‚ÐµÐ»ÐµÐ¿Ð¾Ñ€Ñ‚Ð°")).range(0.1F, 2.0F).setValue(0.4F);
   private boolean freezeActive = false;
   private boolean latchedFreeze = false;
   private long freezeUntilMs = 0L;
   private long lastAttackAtMs = 0L;
   private long lastJumpAtMs = 0L;
   private double prevYVel = (double)0.0F;
   private static final long ATTACK_WINDOW_MS = 200L;
   private boolean holdDisableActive = false;
   private int missedOutTick = 0;
   private boolean warpInProgress = false;
   private boolean flushing = false;
   private boolean lastFreezeActive = false;
   private boolean vyCaptured = false;
   private double frozenVy = (double)0.0F;
   private double frozenVx = (double)0.0F;
   private double frozenVz = (double)0.0F;
   private boolean restoreHorizNextTick = false;
   private long bypassUntilMs = 0L;
   private boolean bypassResume = false;
   private ArrayList tpBuffer = new ArrayList();
   private double freezeY = (double)0.0F;
   private boolean tpActive = false;
   private boolean vtAwaitNext = false;
   private int lastHurtTime = 0;

   public static TimeManipulation getInstance() {
      return (TimeManipulation)Instance.get(TimeManipulation.class);
   }

   public TimeManipulation() {
      super("TimeManipulation", ModuleCategory.COMBAT);
      this.setup(new Setting[]{this.triggerDistance, this.onlySword, this.onlyCriticals, this.slowFallUtil, this.waterCriticals, this.onlyStill, this.holdDisableBind, this.useTime, this.timeSeconds, this.freezeBypass, this.unfreezeBind, this.unfreezeTickMs, this.freezeBypassOnlySword, this.velocityTrigger, this.autoFreeze, this.teleport, this.teleportStep});
   }

   @EventHandler
   public void onAttack(AttackEvent e) {
      this.lastAttackAtMs = System.currentTimeMillis();
   }

   @EventHandler
   public void onWorldRender(WorldRenderEvent e) {
   }

   @EventHandler
   public void onTick(TickEvent e) {
      if (e.getType() == 0) {
         if (mc.field_1724 != null && mc.field_1687 != null) {
            class_746 player = mc.field_1724;
            long nowTs = System.currentTimeMillis();
            double yv = player.method_18798().field_1351;
            if (yv > 0.08 && this.prevYVel <= (double)0.0F) {
               this.lastJumpAtMs = nowTs;
            }

            if (this.freezeBypass.isValue()) {
               long nowBy = System.currentTimeMillis();
               if (nowBy < this.bypassUntilMs) {
                  this.freezeActive = false;
               } else if (this.bypassResume && this.bypassUntilMs != 0L) {
                  this.freezeActive = true;
                  this.latchedFreeze = true;
                  this.bypassUntilMs = 0L;
                  this.bypassResume = false;
               }
            } else {
               this.bypassUntilMs = 0L;
               this.bypassResume = false;
            }

            this.prevYVel = yv;
            if (!this.warpInProgress) {
               if (this.onlySword.isValue() && !this.isHoldingSword(player)) {
                  this.clearAll();
               } else {
                  if (this.onlyStill.isValue()) {
                     if (this.holdDisableActive) {
                        this.clearAll();
                        return;
                     }

                     boolean moving = false;
                     if (mc.field_1690 != null) {
                        moving = mc.field_1690.field_1894.method_1434() || mc.field_1690.field_1881.method_1434() || mc.field_1690.field_1913.method_1434() || mc.field_1690.field_1849.method_1434();
                     }

                     if (moving) {
                        this.clearAll();
                        return;
                     }
                  }

                  if (this.onlyCriticals.isValue()) {
                     boolean swinging = player.field_6252;
                     boolean recentAttack = System.currentTimeMillis() - this.lastAttackAtMs <= 200L;
                     boolean inWater = player.method_5799() || player.method_5869();
                     boolean recentJump = System.currentTimeMillis() - this.lastJumpAtMs <= 300L;
                     boolean waterCritTrigger = this.waterCriticals.isValue() && inWater && recentJump;
                     if (!swinging && !recentAttack && !waterCritTrigger && !this.latchedFreeze) {
                        this.freezeActive = false;
                        return;
                     }
                  }

                  double minSq = Double.MAX_VALUE;

                  for(class_742 other : mc.field_1687.method_18456()) {
                     if (other != player) {
                        double d = other.method_5858(player);
                        if (d < minSq) {
                           minSq = d;
                        }
                     }
                  }

                  double dist = Math.sqrt(minSq == Double.MAX_VALUE ? Double.MAX_VALUE : minSq);
                  float trigger = this.triggerDistance.getValue();
                  boolean within = trigger > 0.0F && dist <= (double)trigger;
                  if (this.velocityTrigger.isValue() && this.vtAwaitNext && this.autoFreeze.isValue()) {
                     boolean fallingNow = player.method_18798().field_1351 < (double)0.0F;
                     boolean jumpingNow = mc.field_1690.field_1903.method_1434();
                     if (fallingNow && within && !jumpingNow) {
                        if (this.useTime.isValue()) {
                           long dur = (long)(this.timeSeconds.getValue() * 1000.0F);
                           this.freezeUntilMs = System.currentTimeMillis() + Math.max(0L, dur);
                        }

                        this.latchedFreeze = true;
                        this.freezeActive = true;
                        this.vtAwaitNext = false;
                     }
                  }

                  if (this.onlyCriticals.isValue()) {
                     boolean swinging = player.field_6252;
                     boolean recentAttack = System.currentTimeMillis() - this.lastAttackAtMs <= 200L;
                     boolean inWater = player.method_5799() || player.method_5869();
                     boolean recentJump = System.currentTimeMillis() - this.lastJumpAtMs <= 300L;
                     boolean waterCritTrigger = this.waterCriticals.isValue() && inWater && recentJump;
                     boolean attackTrigger = swinging || recentAttack || waterCritTrigger;
                     if (!this.latchedFreeze) {
                        if (this.velocityTrigger.isValue() && this.vtAwaitNext && (!within || !attackTrigger)) {
                           this.freezeActive = false;
                           return;
                        }

                        if (within && attackTrigger) {
                           if (this.velocityTrigger.isValue() && this.vtAwaitNext) {
                              this.vtAwaitNext = false;
                           }

                           if (this.useTime.isValue()) {
                              long dur = (long)(this.timeSeconds.getValue() * 1000.0F);
                              this.freezeUntilMs = System.currentTimeMillis() + Math.max(0L, dur);
                              this.latchedFreeze = true;
                              this.freezeActive = true;
                           } else {
                              this.latchedFreeze = true;
                              this.freezeActive = true;
                           }
                        } else {
                           this.freezeActive = false;
                        }
                     } else if (this.useTime.isValue()) {
                        if (System.currentTimeMillis() >= this.freezeUntilMs || !within) {
                           this.clearAll();
                           return;
                        }

                        this.freezeActive = true;
                     } else {
                        if (!within) {
                           this.clearAll();
                           return;
                        }

                        this.freezeActive = true;
                     }
                  } else if (this.useTime.isValue()) {
                     boolean swinging = player.field_6252;
                     boolean recentAttack = System.currentTimeMillis() - this.lastAttackAtMs <= 200L;
                     boolean attackTrigger = swinging || recentAttack;
                     if (!this.latchedFreeze) {
                        if (this.velocityTrigger.isValue() && this.vtAwaitNext && (!within || !attackTrigger)) {
                           this.freezeActive = false;
                           return;
                        }

                        if (within && attackTrigger) {
                           if (this.velocityTrigger.isValue() && this.vtAwaitNext) {
                              this.vtAwaitNext = false;
                           }

                           long dur = (long)(this.timeSeconds.getValue() * 1000.0F);
                           this.freezeUntilMs = System.currentTimeMillis() + Math.max(0L, dur);
                           this.latchedFreeze = true;
                           this.freezeActive = true;
                        } else {
                           this.freezeActive = false;
                        }
                     } else {
                        if (System.currentTimeMillis() >= this.freezeUntilMs || !within) {
                           this.clearAll();
                           return;
                        }

                        this.freezeActive = true;
                     }
                  } else {
                     if (this.velocityTrigger.isValue() && this.vtAwaitNext && !within) {
                        this.freezeActive = false;
                     } else if (within) {
                        if (this.velocityTrigger.isValue() && this.vtAwaitNext) {
                           this.vtAwaitNext = false;
                        }

                        this.freezeActive = true;
                        this.latchedFreeze = true;
                     } else {
                        this.freezeActive = false;
                        this.latchedFreeze = false;
                     }

                     if (!within) {
                        this.freezeUntilMs = 0L;
                     }
                  }

                  if (this.slowFallUtil.isValue()) {
                     if (this.freezeActive && !this.lastFreezeActive) {
                        class_243 v0 = player.method_18798();
                        this.frozenVx = v0.field_1352;
                        this.frozenVy = v0.field_1351;
                        this.frozenVz = v0.field_1350;
                        this.vyCaptured = true;
                     } else if (!this.freezeActive && this.lastFreezeActive) {
                        if (this.vyCaptured) {
                           this.restoreHorizNextTick = true;
                        }

                        this.vyCaptured = false;
                     }

                     if (this.freezeActive && this.vyCaptured) {
                        player.method_18800((double)0.0F, this.frozenVy, (double)0.0F);
                     }
                  } else {
                     this.vyCaptured = false;
                  }

                  this.lastFreezeActive = this.freezeActive;
                  if (this.restoreHorizNextTick && !this.freezeActive) {
                     class_243 v = player.method_18798();
                     player.method_18800(this.frozenVx, v.field_1351, this.frozenVz);
                     this.restoreHorizNextTick = false;
                  }

                  if (this.freezeActive && this.teleport.isValue()) {
                     if (!this.holdDisableActive) {
                        boolean moving = false;
                        float fwd = 0.0F;
                        float str = 0.0F;
                        if (mc.field_1690 != null) {
                           moving = mc.field_1690.field_1894.method_1434() || mc.field_1690.field_1881.method_1434() || mc.field_1690.field_1913.method_1434() || mc.field_1690.field_1849.method_1434();
                           if (mc.field_1690.field_1894.method_1434()) {
                              fwd = 1.0F;
                           }

                           if (mc.field_1690.field_1881.method_1434()) {
                              fwd = -1.0F;
                           }

                           if (mc.field_1690.field_1913.method_1434()) {
                              str = 1.0F;
                           }

                           if (mc.field_1690.field_1849.method_1434()) {
                              str = -1.0F;
                           }
                        }

                        if (!this.tpActive && moving) {
                           this.freezeY = player.method_23318();
                           this.tpBuffer.clear();
                           this.tpActive = true;
                        }

                        if (this.tpActive && moving) {
                           double yawRad = Math.toRadians((double)player.method_36454());
                           double sin = Math.sin(yawRad);
                           double cos = Math.cos(yawRad);
                           double dx = (double)fwd * -sin + (double)str * cos;
                           double dz = (double)fwd * cos + (double)str * sin;
                           double len = Math.hypot(dx, dz);
                           if (len > 1.0E-6) {
                              dx /= len;
                              dz /= len;
                              double step = (double)this.teleportStep.getValue();
                              double nx = player.method_23317() + dx * step;
                              double nz = player.method_23321() + dz * step;
                              class_243 np = new class_243(nx, this.freezeY, nz);
                              player.method_5814(np.field_1352, np.field_1351, np.field_1350);
                              this.tpBuffer.add(np);
                           }
                        }
                     } else if (this.tpActive) {
                        this.tpActive = false;
                        this.tpBuffer.clear();
                     }
                  } else if (this.tpActive) {
                     this.tpActive = false;
                     this.tpBuffer.clear();
                  }

                  if (this.freezeActive && this.teleport.isValue() && this.tpActive) {
                     boolean recentAttack = System.currentTimeMillis() - this.lastAttackAtMs <= 200L;
                     boolean falling = player.method_18798().field_1351 < (double)0.0F;
                     boolean sameY = Math.abs(player.method_23318() - this.freezeY) < 0.05;
                     if ((player.field_6252 || recentAttack) && falling && sameY && !this.flushing) {
                        this.flushing = true;
                        this.freezeActive = false;
                        this.latchedFreeze = false;
                        if (!this.tpBuffer.isEmpty()) {
                           for(class_243 pnt : this.tpBuffer) {
                              try {
                                 boolean onGround = player.method_24828();
                                 this.trySendPosition(pnt.field_1352, pnt.field_1351, pnt.field_1350, onGround);
                              } catch (Throwable var35) {
                              }
                           }
                        }

                        this.tpBuffer.clear();
                        this.tpActive = false;
                        this.flushing = false;
                     }
                  }

               }
            }
         }
      }
   }

   @EventHandler
   public void onMove(MoveEvent e) {
      if (this.freezeActive) {
         if (this.slowFallUtil.isValue()) {
            ++this.missedOutTick;
         }

         e.setMovement(class_243.field_1353);
      }
   }

   @EventHandler
   public void onPacket(PacketEvent e) {
      if (this.freezeActive || this.warpInProgress) {
         if (e.isSend()) {
            class_2596 var3 = e.getPacket();
            if (var3 instanceof class_2828) {
               class_2828 move = (class_2828)var3;
               if (!this.teleport.isValue() || !this.flushing) {
                  e.cancel();
               }
            }
         }

      }
   }

   private boolean isHoldingSword(class_746 player) {
      class_1799 stack = player.method_5998(class_1268.field_5808);
      return stack != null && stack.method_7909() instanceof class_1829;
   }

   private void clearAll() {
      this.freezeActive = false;
      this.latchedFreeze = false;
      this.freezeUntilMs = 0L;
      this.missedOutTick = 0;
   }

   @EventHandler
   public void onKey(KeyEvent e) {
      if (this.onlyStill.isValue()) {
         int bind = this.holdDisableBind.getKey();
         if (bind != -1 && e.isKeyDown(bind)) {
            this.holdDisableActive = true;
         } else if (bind != -1 && e.isKeyReleased(bind)) {
            this.holdDisableActive = false;
         }
      }

      if (this.freezeBypass.isValue()) {
         int ub = this.unfreezeBind.getKey();
         if (ub != -1 && e.isKeyDown(ub) && (!this.freezeBypassOnlySword.isValue() || this.isHoldingSword(mc.field_1724))) {
            this.bypassUntilMs = System.currentTimeMillis() + (long)this.unfreezeTickMs.getValue();
            this.bypassResume = true;
         }
      }

   }

   private void trySendPosition(double x, double y, double z, boolean onGround) {
      try {
         Class<?> clsOuter = Class.forName("net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket");

         for(Class inner : clsOuter.getDeclaredClasses()) {
            String n = inner.getSimpleName().toLowerCase();
            if (n.contains("position") && n.contains("onground")) {
               for(Constructor c : inner.getDeclaredConstructors()) {
                  if (c.getParameterCount() == 4) {
                     c.setAccessible(true);
                     Object pkt = c.newInstance(x, y, z, onGround);
                     mc.method_1562().method_52787((class_2596)pkt);
                     return;
                  }
               }
            }
         }
      } catch (Throwable var20) {
      }

      try {
         Class<?> clsOuter = Class.forName("net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket");

         for(Class inner : clsOuter.getDeclaredClasses()) {
            String n = inner.getSimpleName().toLowerCase();
            if (n.contains("full") || n.contains("position") && n.contains("rotation")) {
               for(Constructor c : inner.getDeclaredConstructors()) {
                  if (c.getParameterCount() == 6) {
                     c.setAccessible(true);
                     Object pkt = c.newInstance(x, y, z, 0.0F, 0.0F, onGround);
                     mc.method_1562().method_52787((class_2596)pkt);
                     return;
                  }
               }
            }
         }
      } catch (Throwable var19) {
      }

   }
}

