package dev.client.modules.impl.render;

import dev.client.event.classes.ReceivePacketEvent;
import dev.client.event.classes.TickEvent;
import dev.client.event.interfaces.IReceivePacketable;
import dev.client.event.interfaces.ITickable;
import dev.client.modules.Category;
import dev.client.modules.IDisableable;
import dev.client.modules.Module;
import dev.client.modules.PlayerModel;
import dev.client.modules.settings.impl.BooleanSetting;
import dev.client.modules.settings.impl.FloatSetting;
import dev.client.modules.settings.impl.ModeSetting;
import dev.client.util.IUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.WorldTimeUpdateS2CPacket;

@Environment(EnvType.CLIENT)
public class BetterWorld extends Module implements IReceivePacketable, ITickable, IDisableable, IUtil {
   public BooleanSetting changeTime = new BooleanSetting().name("Time Changer").value(false);
   public FloatSetting time = new FloatSetting() {
      public boolean isVisible() {
         return BetterWorld.this.changeTime.getValue();
      }
   }.name("Time").incriment(1.0F).minValue(1.0F).maxValue(40.0F).value(1.0F);
   public BooleanSetting fullBright = new BooleanSetting() {
      public void preChangeState(boolean value) {
         if (!value) {
            MinecraftClient.getInstance().player.removeStatusEffect(StatusEffects.NIGHT_VISION);
         }

      }
   }.name("FullBright").value(false);
   public BooleanSetting fog = new BooleanSetting().name("Fog").value(false);
   public FloatSetting start = new FloatSetting() {
      public boolean isVisible() {
         return BetterWorld.this.fog.getValue();
      }
   }.name("StartFog").minValue(0.0F).maxValue(20.0F).value(3.0F).incriment(1.0F);
   public FloatSetting end = new FloatSetting() {
      public boolean isVisible() {
         return BetterWorld.this.fog.getValue();
      }
   }.name("EndFog").minValue(0.0F).maxValue(80.0F).value(50.0F).incriment(1.0F);
   public FloatSetting alphaFog = new FloatSetting() {
      public boolean isVisible() {
         return BetterWorld.this.fog.getValue();
      }
   }.name("AlphaFog").minValue(30.0F).maxValue(100.0F).value(90.0F).incriment(5.0F);
   public final BooleanSetting skyShader = new BooleanSetting() {
      public void onChangeState(boolean value) {
         MinecraftClient.getInstance().options.getBobView().setValue(false);
         super.onChangeState(value);
      }
   }.name("SkyShader").value(false);
   public final BooleanSetting weather = new BooleanSetting() {
   }.name("Weather").value(false);
   public ModeSetting weatherMode = new ModeSetting() {
      public boolean isVisible() {
         return BetterWorld.this.weather.getValue();
      }
   }.name("WeatherMode").value("Clear").modes("Clear", "Rain", "Storm");

   public BetterWorld() {
      super(new PlayerModel("BetterWorld", Category.RENDER, "Изменяет отображение мира"));
      this.addSetting(this.changeTime, this.time, this.fullBright, this.fog, this.start, this.end, this.alphaFog, this.skyShader, this.weather, this.weatherMode);
   }

   public void onReceivePacket(ReceivePacketEvent receivePacketEvent) {
      if (this.changeTime.getValue()) {
         Packet packet = receivePacketEvent.getPacket();
         if (packet instanceof WorldTimeUpdateS2CPacket) {
            receivePacketEvent.cancel();
         }
      }

   }

   public void onTick(TickEvent event) {
      if (MinecraftClient.getInstance().player != null && MinecraftClient.getInstance().world != null) {
         if (this.changeTime.getValue()) {
            MinecraftClient.getInstance().world.setTime(0L, (long)this.time.getValue() * 500L, false);
         }

         if (this.fullBright.getValue()) {
            StatusEffectInstance s = new StatusEffectInstance(StatusEffects.NIGHT_VISION, 400, 2);
            MinecraftClient.getInstance().player.addStatusEffect(s, MinecraftClient.getInstance().player);
         }

         if (this.skyShader.getValue()) {
            MinecraftClient.getInstance().options.getBobView().setValue(false);
         }

         if (this.weather.getValue()) {
            switch (this.weatherMode.getValue()) {
               case "Clear":
                  mc.world.getLevelProperties().setRaining(false);
                  mc.world.setRainGradient(0.0F);
                  mc.world.setThunderGradient(0.0F);
                  break;
               case "Rain":
                  mc.world.getLevelProperties().setRaining(true);
                  mc.world.setRainGradient(1.0F);
                  mc.world.setThunderGradient(0.0F);
                  break;
               case "Storm":
                  mc.world.getLevelProperties().setRaining(true);
                  mc.world.setRainGradient(1.0F);
                  mc.world.setThunderGradient(1.0F);
            }
         }
      }

   }

   public void onDisable() {
      if (this.fullBright.getValue()) {
         MinecraftClient.getInstance().player.removeStatusEffect(StatusEffects.NIGHT_VISION);
      }

      if (this.weather.getValue()) {
         mc.world.getLevelProperties().setRaining(mc.world.getLevelProperties().isRaining());
      }

   }
}

