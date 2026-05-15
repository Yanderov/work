package dev.client.yanderov.features.impl.render;

import dev.client.yanderov.events.player.TickEvent;
import dev.client.yanderov.events.render.WorldRenderEvent;
import dev.client.yanderov.features.impl.combat.Aura;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.features.module.setting.Setting;
import dev.client.yanderov.features.module.setting.implement.BooleanSetting;
import dev.client.yanderov.features.module.setting.implement.ColorSetting;
import dev.client.yanderov.features.module.setting.implement.SliderSettings;
import dev.client.yanderov.utils.client.Instance;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import dev.client.yanderov.utils.display.geometry.Render3D;
import dev.client.yanderov.utils.display.interfaces.QuickImports;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_243;
import net.minecraft.class_4587;

public class BacktrackVisual extends Module implements QuickImports {
   private final SliderSettings delayMs = (new SliderSettings("Ð—Ð°Ð´ÐµÑ€Ð¶ÐºÐ°", "Ð˜Ð½Ñ‚ÐµÑ€Ð²Ð°Ð» Ð² Ð¼Ð¸Ð»Ð»Ð¸ÑÐµÐºÑƒÐ½Ð´Ð°Ñ… Ð´Ð»Ñ Ð¼Ð¾Ð´ÐµÐ»Ð¸ Ð±ÐµÐºÑ‚Ñ€ÐµÐºÐ°")).setValue(150.0F).range(10.0F, 1000.0F);
   private final ColorSetting color = (new ColorSetting("Ð¦Ð²ÐµÑ‚", "Ð¦Ð²ÐµÑ‚ ESP Ð¼Ð¾Ð´ÐµÐ»Ð¸ Ð±ÐµÐºÑ‚Ñ€ÐµÐºÐ°")).value(-2130706433);
   private final BooleanSetting selfRender = (new BooleanSetting("ÐÐ° ÑÐµÐ±Ðµ", "ÐŸÐ¾ÐºÐ°Ð·Ñ‹Ð²Ð°Ñ‚ÑŒ Ð±ÐµÐºÑ‚Ñ€ÐµÐº Ð¸ Ð½Ð° ÑÐµÐ±Ðµ (Ð¸Ð³Ð½Ð¾Ñ€Ð¸Ñ€ÑƒÐµÑ‚ÑÑ, ÐµÑÐ»Ð¸ ÐµÑÑ‚ÑŒ Ñ‚Ð°Ñ€Ð³ÐµÑ‚ ÐÑƒÑ€Ñ‹)")).setValue(false);
   private final Map history = new HashMap();

   public BacktrackVisual() {
      super("Backtrack", "BackTrack", ModuleCategory.RENDER);
      this.setup(new Setting[]{this.delayMs, this.color, this.selfRender});
   }

   public static BacktrackVisual getInstance() {
      return (BacktrackVisual)Instance.get(BacktrackVisual.class);
   }

   @EventHandler
   public void onTick(TickEvent e) {
      if (this.state && mc != null && mc.field_1687 != null && mc.field_1724 != null) {
         long now;
         long maxKeep;
         class_1657 target;
         label53: {
            now = System.currentTimeMillis();
            maxKeep = (long)this.delayMs.getValue() + 1000L;
            target = null;
            if (Aura.getInstance() != null) {
               class_1309 var8 = Aura.getInstance().getCurrentTarget();
               if (var8 instanceof class_1657) {
                  class_1657 auraTarget = (class_1657)var8;
                  target = auraTarget;
                  break label53;
               }
            }

            if (this.selfRender.isValue()) {
               target = mc.field_1724;
            }
         }

         if (target != null && !target.method_31481() && target.method_5805()) {
            Deque<Snapshot> deque = (Deque)this.history.computeIfAbsent(target.method_5628(), (id) -> new ArrayDeque());
            deque.addLast(new Snapshot(now, target.method_19538(), target.method_36454(), target.method_36455()));

            while(!deque.isEmpty() && now - ((Snapshot)deque.peekFirst()).time > maxKeep) {
               deque.removeFirst();
            }

            Iterator<Map.Entry<Integer, Deque<Snapshot>>> it = this.history.entrySet().iterator();

            while(it.hasNext()) {
               Map.Entry<Integer, Deque<Snapshot>> entry = (Map.Entry)it.next();
               if (mc.field_1687.method_8469((Integer)entry.getKey()) == null || ((Deque)entry.getValue()).isEmpty()) {
                  it.remove();
               }
            }

         } else {
            this.history.clear();
         }
      }
   }

   @EventHandler
   public void onWorldRender(WorldRenderEvent e) {
      if (this.state && mc != null && mc.field_1687 != null && mc.field_1724 != null) {
         if (!this.history.isEmpty()) {
            long targetTime;
            class_1657 target;
            label64: {
               targetTime = System.currentTimeMillis() - (long)this.delayMs.getValue();
               target = null;
               if (Aura.getInstance() != null) {
                  class_1309 var6 = Aura.getInstance().getCurrentTarget();
                  if (var6 instanceof class_1657) {
                     class_1657 auraTarget = (class_1657)var6;
                     target = auraTarget;
                     break label64;
                  }
               }

               if (this.selfRender.isValue()) {
                  target = mc.field_1724;
               }
            }

            if (target != null && !target.method_31481() && target.method_5805()) {
               Deque<Snapshot> deque = (Deque)this.history.get(target.method_5628());
               if (deque != null && !deque.isEmpty()) {
                  Snapshot before = null;
                  Snapshot after = null;

                  for(Snapshot s : deque) {
                     if (s.time > targetTime) {
                        after = s;
                        break;
                     }

                     before = s;
                  }

                  class_243 pos;
                  float yaw;
                  if (before != null && after != null && after.time != before.time) {
                     float t = (float)((double)(targetTime - before.time) / (double)(after.time - before.time));
                     t = Math.max(0.0F, Math.min(1.0F, t));
                     pos = before.pos.method_35590(after.pos, (double)t);
                     yaw = before.yaw + (after.yaw - before.yaw) * t;
                  } else if (before != null) {
                     pos = before.pos;
                     yaw = before.yaw;
                  } else {
                     Snapshot first = (Snapshot)deque.peekFirst();
                     pos = first.pos;
                     yaw = first.yaw;
                  }

                  class_4587 matrices = e.getStack();
                  class_243 cameraPos = mc.method_1561().field_4686.method_19326();
                  class_243 offset = pos.method_1020(cameraPos);
                  matrices.method_22903();
                  Render3D.drawEntityChams(target, offset, yaw, this.color.getColor(), matrices, e.getPartialTicks());
                  matrices.method_22909();
               }
            }
         }
      }
   }

   private static class Snapshot {
      final long time;
      final class_243 pos;
      final float yaw;
      final float pitch;

      Snapshot(long time, class_243 pos, float yaw, float pitch) {
         this.time = time;
         this.pos = pos;
         this.yaw = yaw;
         this.pitch = pitch;
      }
   }
}

