package dev.client.yanderov.features.impl.render;

import dev.client.yanderov.events.packet.PacketEvent;
import dev.client.yanderov.events.render.WorldRenderEvent;
import dev.client.yanderov.features.impl.combat.Aura;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.features.module.setting.Setting;
import dev.client.yanderov.features.module.setting.implement.BooleanSetting;
import dev.client.yanderov.features.module.setting.implement.ColorSetting;
import dev.client.yanderov.features.module.setting.implement.SelectSetting;
import dev.client.yanderov.features.module.setting.implement.SliderSettings;
import dev.client.yanderov.utils.client.Instance;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import dev.client.yanderov.utils.display.color.ColorAssist;
import dev.client.yanderov.utils.display.geometry.Render3D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1304;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_2248;
import net.minecraft.class_2261;
import net.minecraft.class_2320;
import net.minecraft.class_2338;
import net.minecraft.class_2356;
import net.minecraft.class_2372;
import net.minecraft.class_238;
import net.minecraft.class_239;
import net.minecraft.class_2397;
import net.minecraft.class_243;
import net.minecraft.class_259;
import net.minecraft.class_2596;
import net.minecraft.class_2680;
import net.minecraft.class_2879;
import net.minecraft.class_3966;
import net.minecraft.class_745;

public class HitEffect extends Module {
   private final SelectSetting mode = (new SelectSetting("Mode", "Ð ÐµÐ¶Ð¸Ð¼ ÑÑ„Ñ„ÐµÐºÑ‚Ð°")).value("Soul", "Box", "Wave").selected("Soul");
   private final ColorSetting soulColor = (new ColorSetting("SoulColor", "Ð¦Ð²ÐµÑ‚ Ð´ÑƒÑˆÐ¸")).value(-2130706433).visible(() -> this.mode.isSelected("Soul"));
   private final ColorSetting boxColor = (new ColorSetting("BoxColor", "Ð¦Ð²ÐµÑ‚ Ð±Ð¾ÐºÑÐ°")).value(-2130706433).visible(() -> this.mode.isSelected("Box"));
   private final BooleanSetting noArmorOverlay = (new BooleanSetting("NoArmorOverlay", "ÐÐµ Ð¿Ð¾ÐºÐ°Ð·Ñ‹Ð²Ð°Ñ‚ÑŒ Ð±Ñ€Ð¾Ð½ÑŽ (Soul)")).setValue(false).visible(() -> this.mode.isSelected("Soul"));
   private final BooleanSetting noItemOverlay = (new BooleanSetting("NoItemOverlay", "ÐÐµ Ð¿Ð¾ÐºÐ°Ð·Ñ‹Ð²Ð°Ñ‚ÑŒ Ð¿Ñ€ÐµÐ´Ð¼ÐµÑ‚Ñ‹ (Soul)")).setValue(false).visible(() -> this.mode.isSelected("Soul"));
   private final SliderSettings maxTimeMs = (new SliderSettings("MaxTime", "Ð’Ñ€ÐµÐ¼Ñ Ð¶Ð¸Ð·Ð½Ð¸ ÑÑ„Ñ„ÐµÐºÑ‚Ð° (ms)")).setValue(800.0F).range(100, 5000);
   private final SliderSettings maxHitDistance = (new SliderSettings("MaxHitDistance", "Ð Ð°Ð´Ð¸ÑƒÑ Ð¿Ð¾Ð¸ÑÐºÐ° Ð¸Ð³Ñ€Ð¾ÐºÐ°")).setValue(6.0F).range(1.0F, 16.0F);
   private final List ghosts = new ArrayList();
   private final List waves = new ArrayList();

   public static HitEffect getInstance() {
      return (HitEffect)Instance.get(HitEffect.class);
   }

   public HitEffect() {
      super("HitEffect", "Hit Effect", ModuleCategory.RENDER);
      this.setup(new Setting[]{this.mode, this.soulColor, this.boxColor, this.noArmorOverlay, this.noItemOverlay, this.maxTimeMs, this.maxHitDistance});
   }

   @EventHandler
   public void onPacket(PacketEvent e) {
      if (this.isState()) {
         if (e.isSend()) {
            if (mc.field_1687 != null && mc.field_1724 != null) {
               class_2596<?> packet = e.getPacket();
               if (packet instanceof class_2879) {
                  class_2879 swing = (class_2879)packet;
                  if (swing.method_12512() != class_1268.field_5808) {
                     return;
                  }

                  this.handleHitEffectTrigger();
               }

            }
         }
      }
   }

   private void handleHitEffectTrigger() {
      if (mc.field_1687 != null && mc.field_1724 != null) {
         class_1657 best = null;
         class_239 var4 = mc.field_1765;
         if (var4 instanceof class_3966) {
            class_3966 ehr = (class_3966)var4;
            class_1297 var16 = ehr.method_17782();
            if (var16 instanceof class_1657) {
               class_1657 pe = (class_1657)var16;
               best = pe;
            }
         }

         if (best == null) {
            Aura aura = Aura.getInstance();
            if (aura != null && aura.isState()) {
               class_1309 var17 = aura.getCurrentTarget();
               if (var17 instanceof class_1657) {
                  class_1657 auraTarget = (class_1657)var17;
                  best = auraTarget;
               }
            }
         }

         if (best != null) {
            double maxDist = (double)this.maxHitDistance.getValue();
            double maxDistSq = maxDist * maxDist;
            if (best != mc.field_1724) {
               if (best.method_5805() && !best.method_31481()) {
                  if (!(best.method_5858(mc.field_1724) > maxDistSq)) {
                     class_243 pos = best.method_19538();
                     float yaw = best.method_36454();
                     float pitch = best.method_36455();
                     class_238 box = best.method_5829();
                     long now = System.currentTimeMillis();
                     if (this.mode.isSelected("Soul") || this.mode.isSelected("Box")) {
                        class_745 fake = new class_745(mc.field_1687, best.method_7334());
                        fake.method_23327(best.method_23317(), best.method_23318(), best.method_23321());
                        fake.method_36456(yaw);
                        fake.field_6241 = yaw;
                        fake.field_6283 = yaw;
                        fake.field_5982 = yaw;
                        fake.field_6220 = yaw;
                        fake.field_6259 = yaw;
                        fake.method_36457(pitch);
                        fake.field_6004 = pitch;
                        fake.method_18380(best.method_18376());
                        fake.method_5880(false);
                        fake.method_5673(class_1304.field_6169, best.method_6118(class_1304.field_6169));
                        fake.method_5673(class_1304.field_6174, best.method_6118(class_1304.field_6174));
                        fake.method_5673(class_1304.field_6172, best.method_6118(class_1304.field_6172));
                        fake.method_5673(class_1304.field_6166, best.method_6118(class_1304.field_6166));
                        fake.method_6122(class_1268.field_5808, best.method_6047());
                        fake.method_6122(class_1268.field_5810, best.method_6079());
                        this.ghosts.add(new HitGhost(best, fake, pos, yaw, pitch, box, now));
                     }

                     if (this.mode.isSelected("Wave")) {
                        this.waves.add(new Wave(pos, now));
                     }

                  }
               }
            }
         }
      }
   }

   @EventHandler
   public void onWorldRender(WorldRenderEvent e) {
      if (this.isState()) {
         if (mc.field_1687 != null && mc.field_1724 != null) {
            long now = System.currentTimeMillis();
            long life = (long)this.maxTimeMs.getValue();
            if (life > 0L) {
               if (this.mode.isSelected("Soul") || this.mode.isSelected("Box")) {
                  if (this.ghosts.isEmpty()) {
                     return;
                  }

                  Iterator<HitGhost> it = this.ghosts.iterator();

                  while(it.hasNext()) {
                     HitGhost g = (HitGhost)it.next();
                     long age = now - g.spawnTime;
                     if (age >= life) {
                        it.remove();
                     } else {
                        float t = (float)age / (float)life;
                        if (t < 0.0F) {
                           t = 0.0F;
                        }

                        if (t > 1.0F) {
                           t = 1.0F;
                        }

                        float alphaMul = 1.0F - t;
                        alphaMul = (float)Math.pow((double)alphaMul, (double)1.5F);
                        if (alphaMul <= 0.01F) {
                           it.remove();
                        } else if (this.mode.isSelected("Soul")) {
                           this.renderSoul(g, alphaMul, e);
                        } else if (this.mode.isSelected("Box")) {
                           this.renderBox(g, alphaMul);
                        }
                     }
                  }
               }

               if (this.mode.isSelected("Wave")) {
                  if (this.waves.isEmpty()) {
                     return;
                  }

                  Iterator<Wave> itWave = this.waves.iterator();

                  while(itWave.hasNext()) {
                     Wave w = (Wave)itWave.next();
                     long age = now - w.spawnTime;
                     if (age >= life) {
                        itWave.remove();
                     } else {
                        float t = (float)age / (float)life;
                        if (t < 0.0F) {
                           t = 0.0F;
                        }

                        if (t > 1.0F) {
                           t = 1.0F;
                        }

                        float radius = 1.0F + 4.0F * t;
                        int color = ColorAssist.interpolateColor(-65536, -1, t);
                        int cx = (int)Math.floor(w.center.field_1352);
                        int cy = (int)Math.floor(w.center.field_1351);
                        int cz = (int)Math.floor(w.center.field_1350);
                        float thickness = 0.35F;
                        float innerRadius = Math.max(0.1F, radius - thickness);
                        float outerRadius = radius + thickness;
                        float innerSq = innerRadius * innerRadius;
                        float outerSq = outerRadius * outerRadius;
                        int r = (int)Math.ceil((double)(outerRadius + 1.0F));
                        if (r < 1) {
                           r = 1;
                        }

                        if (r > 6) {
                           r = 6;
                        }

                        for(int x = -r; x <= r; ++x) {
                           for(int z = -r; z <= r; ++z) {
                              double distSq = (double)(x * x + z * z);
                              if (!(distSq < (double)innerSq) && !(distSq > (double)outerSq)) {
                                 int bx = cx + x;
                                 int bz = cz + z;
                                 class_2338 topPos = null;

                                 for(int dy = 3; dy >= -2; --dy) {
                                    class_2338 checkPos = new class_2338(bx, cy + dy, bz);
                                    class_2680 state = mc.field_1687.method_8320(checkPos);
                                    if (!state.method_26215()) {
                                       topPos = checkPos;
                                       break;
                                    }
                                 }

                                 if (topPos != null) {
                                    class_2680 topState = mc.field_1687.method_8320(topPos);
                                    if (!topState.method_26215() && !this.isPlantBlock(topState)) {
                                       Render3D.drawShapeAlternative(topPos, class_259.method_1077(), color, 2.0F, true, true);
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
      }
   }

   private boolean isPlantBlock(class_2680 state) {
      class_2248 block = state.method_26204();
      return block instanceof class_2261 || block instanceof class_2320 || block instanceof class_2356 || block instanceof class_2372 || block instanceof class_2397;
   }

   private void renderSoul(HitGhost g, float alphaMul, WorldRenderEvent e) {
      if (g.target != null && g.target.method_5805()) {
         int baseColor = this.soulColor.getColor();
         int color = ColorAssist.multAlpha(baseColor, alphaMul);
         float alpha = (float)(color >> 24 & 255) / 255.0F;
         class_1799 head = class_1799.field_8037;
         class_1799 chest = class_1799.field_8037;
         class_1799 legs = class_1799.field_8037;
         class_1799 feet = class_1799.field_8037;
         class_1799 mainHand = class_1799.field_8037;
         class_1799 offHand = class_1799.field_8037;
         boolean changedArmor = false;
         boolean changedItems = false;
         if (this.noArmorOverlay.isValue()) {
            head = g.ghost.method_6118(class_1304.field_6169);
            chest = g.ghost.method_6118(class_1304.field_6174);
            legs = g.ghost.method_6118(class_1304.field_6172);
            feet = g.ghost.method_6118(class_1304.field_6166);
            g.ghost.method_5673(class_1304.field_6169, class_1799.field_8037);
            g.ghost.method_5673(class_1304.field_6174, class_1799.field_8037);
            g.ghost.method_5673(class_1304.field_6172, class_1799.field_8037);
            g.ghost.method_5673(class_1304.field_6166, class_1799.field_8037);
            changedArmor = true;
         }

         if (this.noItemOverlay.isValue()) {
            mainHand = g.ghost.method_6047();
            offHand = g.ghost.method_6079();
            g.ghost.method_6122(class_1268.field_5808, class_1799.field_8037);
            g.ghost.method_6122(class_1268.field_5810, class_1799.field_8037);
            changedItems = true;
         }

         try {
            float curYaw = g.target.method_36454();
            float curPitch = g.target.method_36455();
            g.ghost.method_36456(curYaw);
            g.ghost.field_6241 = curYaw;
            g.ghost.field_6283 = curYaw;
            g.ghost.field_5982 = curYaw;
            g.ghost.field_6259 = curYaw;
            g.ghost.field_6220 = curYaw;
            g.ghost.method_36457(curPitch);
            g.ghost.field_6004 = curPitch;
            Render3D.drawEntity(g.ghost, g.pos, curYaw, (int)(alpha * 255.0F), e.getStack(), e.getPartialTicks());
         } finally {
            if (changedArmor) {
               g.ghost.method_5673(class_1304.field_6169, head);
               g.ghost.method_5673(class_1304.field_6174, chest);
               g.ghost.method_5673(class_1304.field_6172, legs);
               g.ghost.method_5673(class_1304.field_6166, feet);
            }

            if (changedItems) {
               g.ghost.method_6122(class_1268.field_5808, mainHand);
               g.ghost.method_6122(class_1268.field_5810, offHand);
            }

         }

      }
   }

   private void renderBox(HitGhost g, float alphaMul) {
      int baseColor = this.boxColor.getColor();
      int color = ColorAssist.multAlpha(baseColor, alphaMul);
      Render3D.drawBox(g.box, color, 2.0F);
   }

   private static class HitGhost {
      final class_1309 target;
      final class_1309 ghost;
      final class_243 pos;
      final float yaw;
      final float pitch;
      final class_238 box;
      final long spawnTime;

      HitGhost(class_1309 target, class_1309 ghost, class_243 pos, float yaw, float pitch, class_238 box, long spawnTime) {
         this.target = target;
         this.ghost = ghost;
         this.pos = pos;
         this.yaw = yaw;
         this.pitch = pitch;
         this.box = box;
         this.spawnTime = spawnTime;
      }
   }

   private static class Wave {
      final class_243 center;
      final long spawnTime;

      Wave(class_243 center, long spawnTime) {
         this.center = center;
         this.spawnTime = spawnTime;
      }
   }
}

