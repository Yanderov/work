package fun.Yanderov.features.impl.render;

import com.mojang.authlib.GameProfile;
import fun.Yanderov.events.player.EntityDeathEvent;
import fun.Yanderov.events.render.WorldRenderEvent;
import fun.Yanderov.features.module.Module;
import fun.Yanderov.features.module.ModuleCategory;
import fun.Yanderov.features.module.setting.Setting;
import fun.Yanderov.features.module.setting.implement.BooleanSetting;
import fun.Yanderov.features.module.setting.implement.SelectSetting;
import fun.Yanderov.features.module.setting.implement.SliderSettings;
import fun.Yanderov.utils.client.managers.event.EventHandler;
import fun.Yanderov.utils.client.sound.SoundManager;
import fun.Yanderov.utils.display.geometry.Render3D;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_243;
import net.minecraft.class_2561;
import net.minecraft.class_3419;
import net.minecraft.class_4050;
import net.minecraft.class_4587;
import net.minecraft.class_745;
import net.minecraft.class_1297.class_5529;

public class KillEffect extends Module {
   private final SliderSettings volume = (new SliderSettings("Volume", "Volume")).setValue(100.0F).range(0, 100);
   private final BooleanSetting playSound = (new BooleanSetting("Play Sound", "Play Sound")).setValue(true);
   private final BooleanSetting mobs = (new BooleanSetting("Mobs", "Mobs")).setValue(false);
   private final SelectSetting effectType = (new SelectSetting("Effect Type", "Effect Type")).value("Cross", "Soul").selected("Soul");
   private final Map renderEntities = new ConcurrentHashMap();

   public KillEffect() {
      super("KillEffect", "Kill Effect", ModuleCategory.RENDER);
      this.setup(new Setting[]{this.volume, this.playSound, this.mobs, this.effectType});
   }

   @EventHandler
   public void onEntityDeath(EntityDeathEvent event) {
      if (mc.field_1687 != null && mc.field_1724 != null) {
         class_1297 entity = event.getEntity();
         if (entity instanceof class_1309) {
            if (this.mobs.isValue() || entity instanceof class_1657) {
               if (entity != mc.field_1724 && !this.renderEntities.containsKey(entity)) {
                  if (this.playSound.isValue()) {
                     mc.field_1687.method_8396(mc.field_1724, entity.method_24515(), SoundManager.ORTHODOX, class_3419.field_15245, this.volume.getValue() / 100.0F, 1.0F);
                  }

                  class_745 fakePlayer = null;
                  if (this.effectType.isSelected("Soul") && entity instanceof class_1657) {
                     fakePlayer = new class_745(mc.field_1687, ((class_1657)entity).method_7334());
                     fakePlayer.method_36457(-30.0F);
                     fakePlayer.method_36456(entity.method_36454());
                     fakePlayer.field_6241 = entity.method_36454();
                     fakePlayer.field_6283 = entity.method_36454();
                     fakePlayer.method_5880(false);
                     fakePlayer.method_5665(class_2561.method_43470("Ghost_" + String.valueOf(((class_1657)entity).method_7334().getId())));
                     mc.field_1687.method_53875(fakePlayer);
                  }

                  this.renderEntities.put(entity, new EntityRenderData(System.currentTimeMillis(), entity.method_36454(), entity.method_19538(), entity, fakePlayer));
               }
            }
         }
      }
   }

   @EventHandler
   public void onWorldRender(WorldRenderEvent e) {
      if (mc.field_1687 != null && mc.field_1724 != null) {
         class_4587 stack = e.getStack();
         float tickDelta = e.getPartialTicks();
         List<class_1297> entitiesToRemove = new ArrayList();
         this.renderEntities.forEach((entity, data) -> {
            if (System.currentTimeMillis() - data.getTimestamp() > 3000L) {
               entitiesToRemove.add(entity);
               if (data.getFakePlayer() != null) {
                  mc.field_1687.method_2945(data.getFakePlayer().method_5628(), class_5529.field_26999);
               }
            } else {
               float timeProgress = (float)(System.currentTimeMillis() - data.getTimestamp()) / 3000.0F;
               if (this.effectType.isSelected("Cross")) {
                  int color = (new Color(255, 255, 255, (int)(150.0F * (1.0F - timeProgress)))).getRGB();
                  float yaw = (float)Math.toRadians((double)(data.getYaw() + 95.0F));
                  class_243 pos = data.getStartPos();
                  Render3D.drawLine(pos.method_1031((double)0.0F, (double)0.0F, (double)0.0F), pos.method_1031((double)0.0F, (double)3.0F, (double)0.0F), color, 5.0F, true);
                  float armLength = 1.0F;
                  float yOffset = 2.3F;
                  class_243 start = pos.method_1031((double)(-armLength) * Math.sin((double)yaw), (double)yOffset, (double)armLength * Math.cos((double)yaw));
                  class_243 end = pos.method_1031((double)armLength * Math.sin((double)yaw), (double)yOffset, (double)(-armLength) * Math.cos((double)yaw));
                  Render3D.drawLine(start, end, color, 5.0F, true);
               } else if (this.effectType.isSelected("Soul")) {
                  float yOffset = timeProgress * 3.0F;
                  int alpha = (int)(255.0F * (1.0F - timeProgress));
                  class_243 soulPos = data.getStartPos().method_1031((double)0.0F, (double)yOffset, (double)0.0F);
                  class_1297 renderEntity = data.getEntity();
                  if (data.getFakePlayer() != null) {
                     renderEntity = data.getFakePlayer();
                     renderEntity.method_23327(soulPos.field_1352, soulPos.field_1351, soulPos.field_1350);
                  }

                  Render3D.drawEntity(renderEntity, soulPos, data.getYaw(), alpha, stack, tickDelta);
               }
            }

         });
         Map var10001 = this.renderEntities;
         Objects.requireNonNull(var10001);
         entitiesToRemove.forEach(var10001::remove);
      }
   }

   private static class EntityRenderData {
      private final long timestamp;
      private final float yaw;
      private final class_243 startPos;
      private final class_1297 entity;
      private final GameProfile gameProfile;
      private final class_4050 pose;
      private final class_745 fakePlayer;

      public EntityRenderData(long timestamp, float yaw, class_243 startPos, class_1297 entity, class_745 fakePlayer) {
         this.timestamp = timestamp;
         this.yaw = yaw;
         this.startPos = startPos;
         this.entity = entity;
         this.gameProfile = entity instanceof class_1657 ? ((class_1657)entity).method_7334() : null;
         this.pose = entity.method_18376();
         this.fakePlayer = fakePlayer;
      }

      public long getTimestamp() {
         return this.timestamp;
      }

      public float getYaw() {
         return this.yaw;
      }

      public class_243 getStartPos() {
         return this.startPos;
      }

      public class_1297 getEntity() {
         return this.entity;
      }

      public GameProfile getGameProfile() {
         return this.gameProfile;
      }

      public class_4050 getPose() {
         return this.pose;
      }

      public class_745 getFakePlayer() {
         return this.fakePlayer;
      }
   }
}

