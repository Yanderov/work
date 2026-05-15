package dev.client.yanderov.features.impl.render;

import com.mojang.blaze3d.platform.GlStateManager.class_4534;
import com.mojang.blaze3d.platform.GlStateManager.class_4535;
import com.mojang.blaze3d.systems.RenderSystem;
import dev.client.yanderov.events.player.AttackEvent;
import dev.client.yanderov.events.player.EntitySpawnEvent;
import dev.client.yanderov.events.player.TickEvent;
import dev.client.yanderov.events.render.WorldRenderEvent;
import dev.client.yanderov.features.module.Module;
import dev.client.yanderov.features.module.ModuleCategory;
import dev.client.yanderov.features.module.setting.Setting;
import dev.client.yanderov.features.module.setting.implement.BooleanSetting;
import dev.client.yanderov.features.module.setting.implement.ColorSetting;
import dev.client.yanderov.features.module.setting.implement.MultiSelectSetting;
import dev.client.yanderov.features.module.setting.implement.SelectSetting;
import dev.client.yanderov.features.module.setting.implement.SliderSettings;
import dev.client.yanderov.utils.client.Instance;
import dev.client.yanderov.utils.client.managers.event.EventHandler;
import dev.client.yanderov.utils.display.color.ColorAssist;
import dev.client.yanderov.utils.math.frame.FrameRateCounter;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import net.minecraft.class_10142;
import net.minecraft.class_1297;
import net.minecraft.class_1676;
import net.minecraft.class_1802;
import net.minecraft.class_2338;
import net.minecraft.class_243;
import net.minecraft.class_286;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_2960;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_7833;
import net.minecraft.class_2902.class_2903;
import net.minecraft.class_293.class_5596;
import org.joml.Matrix4f;

public class WorldParticles extends Module {
   private MultiSelectSetting modes = (new MultiSelectSetting("Ð ÐµÐ¶Ð¸Ð¼Ñ‹", "Ð’Ñ‹Ð±ÐµÑ€Ð¸Ñ‚Ðµ Ñ€ÐµÐ¶Ð¸Ð¼Ñ‹ Ð¿Ð°Ñ€Ñ‚Ð¸ÐºÐ»Ð¾Ð²")).value("ÐŸÐ¾ Ð¼Ð¸Ñ€Ñƒ", "ÐŸÑ€Ð¸ Ð°Ñ‚Ð°ÐºÐµ", "ÐŸÑ€Ð¸ Ñ…Ð¾Ð´ÑŒÐ±Ðµ", "Ð¡Ð»ÐµÐ´Ð¾Ð²Ð°Ð½Ð¸Ðµ").selected("ÐŸÐ¾ Ð¼Ð¸Ñ€Ñƒ");
   private SelectSetting attackMode = (new SelectSetting("Ð¢Ð¸Ð¿ ÑƒÐ´Ð°Ñ€Ð°", "ÐšÐ¾Ð³Ð´Ð° ÑÐ¿Ð°Ð²Ð½Ð¸Ñ‚ÑŒ Ð¿Ð°Ñ€Ñ‚Ð¸ÐºÐ»Ñ‹ Ð¿Ñ€Ð¸ Ð°Ñ‚Ð°ÐºÐµ")).value("Ð’ÑÐµÐ³Ð´Ð°", "Ð¢Ð¾Ð»ÑŒÐºÐ¾ ÐºÑ€Ð¸Ñ‚", "Ð¢Ð¾Ð»ÑŒÐºÐ¾ Ð¾Ð±Ñ‹Ñ‡Ð½Ñ‹Ð¹").selected("Ð’ÑÐµÐ³Ð´Ð°").visible(() -> this.modes.isSelected("ÐŸÑ€Ð¸ Ð°Ñ‚Ð°ÐºÐµ"));
   private MultiSelectSetting attackParticleType = (new MultiSelectSetting("Ð¢Ð¸Ð¿ Ñ‡Ð°ÑÑ‚Ð¸Ñ† (ÐÑ‚Ð°ÐºÐ°)", "Ð’Ñ‹Ð±Ð¾Ñ€ Ñ‚Ð¸Ð¿Ð° Ð¿Ð°Ñ€Ñ‚Ð¸ÐºÐ»Ð¾Ð² Ð¿Ñ€Ð¸ Ð°Ñ‚Ð°ÐºÐµ")).value("Ð—Ð²ÐµÐ·Ð´Ñ‹", "Ð¡Ð½ÐµÐ³", "Ð‘Ð»ÑƒÐ¼", "Bucks", "Core", "Crest", "Cube", "Cube Blast", "Ded", "Dollar", "Firefly", "Glow", "Heart1", "Star1").selected("Ð—Ð²ÐµÐ·Ð´Ñ‹").visible(() -> this.modes.isSelected("ÐŸÑ€Ð¸ Ð°Ñ‚Ð°ÐºÐµ"));
   private SliderSettings attackParticleCount = (new SliderSettings("ÐšÐ¾Ð»Ð¸Ñ‡ÐµÑÑ‚Ð²Ð¾ (ÐÑ‚Ð°ÐºÐ°)", "ÐšÐ¾Ð»Ð¸Ñ‡ÐµÑÑ‚Ð²Ð¾ Ð¿Ð°Ñ€Ñ‚Ð¸ÐºÐ»Ð¾Ð² Ð¿Ñ€Ð¸ Ð°Ñ‚Ð°ÐºÐµ")).range(1, 50).setValue(10.0F).visible(() -> this.modes.isSelected("ÐŸÑ€Ð¸ Ð°Ñ‚Ð°ÐºÐµÐºÐµ"));
   private SliderSettings attackParticleSize = (new SliderSettings("Ð Ð°Ð·Ð¼ÐµÑ€ (ÐÑ‚Ð°ÐºÐ°)", "Ð Ð°Ð·Ð¼ÐµÑ€ Ð¿Ð°Ñ€Ñ‚Ð¸ÐºÐ»Ð¾Ð² Ð¿Ñ€Ð¸ Ð°Ñ‚Ð°ÐºÐµ")).range(0.5F, 3.0F).setValue(1.0F).visible(() -> this.modes.isSelected("ÐŸÑ€Ð¸ Ð°Ñ‚Ð°ÐºÐµ"));
   private SliderSettings attackParticleLifeTime = (new SliderSettings("Ð’Ñ€ÐµÐ¼Ñ Ð¶Ð¸Ð·Ð½Ð¸ (ÐÑ‚Ð°ÐºÐ°)", "Ð’Ñ€ÐµÐ¼Ñ Ð¶Ð¸Ð·Ð½Ð¸ Ð¿Ð°Ñ€Ñ‚Ð¸ÐºÐ»Ð¾Ð² Ð¿Ñ€Ð¸ Ð°Ñ‚Ð°ÐºÐµ Ð² Ð¼Ñ")).range(250.0F, 3000.0F).setValue(800.0F).visible(() -> this.modes.isSelected("ÐŸÑ€Ð¸ Ð°Ñ‚Ð°ÐºÐµ"));
   private BooleanSetting attackCollision = (new BooleanSetting("ÐšÐ¾Ð»Ð»Ð¸Ð·Ð¸Ñ (ÐÑ‚Ð°ÐºÐ°)", "Ð¤Ð¸Ð·Ð¸ÐºÐ° Ð´Ð»Ñ Ð¿Ð°Ñ€Ñ‚Ð¸ÐºÐ»Ð¾Ð² Ð¿Ñ€Ð¸ Ð°Ñ‚Ð°ÐºÐµ")).setValue(true).visible(() -> this.modes.isSelected("ÐŸÑ€Ð¸ Ð°Ñ‚Ð°ÐºÐµ"));
   private SliderSettings attackGravity = (new SliderSettings("Ð“Ñ€Ð°Ð²Ð¸Ñ‚Ð°Ñ†Ð¸Ñ (ÐÑ‚Ð°ÐºÐ°)", "Ð¡Ð¸Ð»Ð° Ð¿Ñ€Ð¸Ñ‚ÑÐ¶ÐµÐ½Ð¸Ñ Ð¿Ð°Ñ€Ñ‚Ð¸ÐºÐ»Ð¾Ð² Ð¿Ñ€Ð¸ Ð°Ñ‚Ð°ÐºÐµ")).range(-10.0F, 10.0F).setValue(0.5F).visible(() -> this.modes.isSelected("ÐŸÑ€Ð¸ Ð°Ñ‚Ð°ÐºÐµ"));
   private ColorSetting attackParticleColor = (new ColorSetting("Ð¦Ð²ÐµÑ‚ (ÐÑ‚Ð°ÐºÐ°)", "Ð¦Ð²ÐµÑ‚ Ð¿Ð°Ñ€Ñ‚Ð¸ÐºÐ»Ð¾Ð² Ð¿Ñ€Ð¸ Ð°Ñ‚Ð°ÐºÐµ")).value((new Color(255, 255, 255, 255)).getRGB()).presets((new Color(255, 0, 0, 255)).getRGB(), (new Color(0, 255, 0, 255)).getRGB(), (new Color(0, 0, 255, 255)).getRGB(), (new Color(255, 255, 0, 255)).getRGB()).visible(() -> this.modes.isSelected("ÐŸÑ€Ð¸ Ð°Ñ‚Ð°ÐºÐµ"));
   private MultiSelectSetting walkParticleType = (new MultiSelectSetting("Ð¢Ð¸Ð¿ Ñ‡Ð°ÑÑ‚Ð¸Ñ† (Ð¥Ð¾Ð´ÑŒÐ±Ð°)", "Ð’Ñ‹Ð±Ð¾Ñ€ Ñ‚Ð¸Ð¿Ð° Ð¿Ð°Ñ€Ñ‚Ð¸ÐºÐ»Ð¾Ð² Ð¿Ñ€Ð¸ Ñ…Ð¾Ð´ÑŒÐ±Ðµ")).value("Ð—Ð²ÐµÐ·Ð´Ñ‹", "Ð¡Ð½ÐµÐ³", "Ð‘Ð»ÑƒÐ¼", "Bucks", "Core", "Crest", "Cube", "Cube Blast", "Ded", "Dollar", "Firefly", "Glow", "Heart1", "Star1").selected("Ð—Ð²ÐµÐ·Ð´Ñ‹").visible(() -> this.modes.isSelected("ÐŸÑ€Ð¸ Ñ…Ð¾Ð´ÑŒÐ±Ðµ"));
   private SliderSettings walkParticleCount = (new SliderSettings("ÐšÐ¾Ð»Ð¸Ñ‡ÐµÑÑ‚Ð²Ð¾ (Ð¥Ð¾Ð´ÑŒÐ±Ð°)", "ÐšÐ¾Ð»Ð¸Ñ‡ÐµÑÑ‚Ð²Ð¾ Ð¿Ð°Ñ€Ñ‚Ð¸ÐºÐ»Ð¾Ð² Ð¿Ñ€Ð¸ Ñ…Ð¾Ð´ÑŒÐ±Ðµ")).range(1, 20).setValue(3.0F).visible(() -> this.modes.isSelected("ÐŸÑ€Ð¸ Ñ…Ð¾Ð´ÑŒÐ±Ðµ"));
   private SliderSettings walkParticleSize = (new SliderSettings("Ð Ð°Ð·Ð¼ÐµÑ€ (Ð¥Ð¾Ð´ÑŒÐ±Ð°)", "Ð Ð°Ð·Ð¼ÐµÑ€ Ð¿Ð°Ñ€Ñ‚Ð¸ÐºÐ»Ð¾Ð² Ð¿Ñ€Ð¸ Ñ…Ð¾Ð´ÑŒÐ±Ðµ")).range(0.5F, 3.0F).setValue(1.0F).visible(() -> this.modes.isSelected("ÐŸÑ€Ð¸ Ñ…Ð¾Ð´ÑŒÐ±Ðµ"));
   private SliderSettings walkParticleLifeTime = (new SliderSettings("Ð’Ñ€ÐµÐ¼Ñ Ð¶Ð¸Ð·Ð½Ð¸ (Ð¥Ð¾Ð´ÑŒÐ±Ð°)", "Ð’Ñ€ÐµÐ¼Ñ Ð¶Ð¸Ð·Ð½Ð¸ Ð¿Ð°Ñ€Ñ‚Ð¸ÐºÐ»Ð¾Ð² Ð¿Ñ€Ð¸ Ñ…Ð¾Ð´ÑŒÐ±Ðµ Ð² Ð¼Ñ")).range(250.0F, 3000.0F).setValue(600.0F).visible(() -> this.modes.isSelected("ÐŸÑ€Ð¸ Ñ…Ð¾Ð´ÑŒÐ±Ðµ"));
   private BooleanSetting walkCollision = (new BooleanSetting("ÐšÐ¾Ð»Ð»Ð¸Ð·Ð¸Ñ (Ð¥Ð¾Ð´ÑŒÐ±Ð°)", "Ð¤Ð¸Ð·Ð¸ÐºÐ° Ð´Ð»Ñ Ð¿Ð°Ñ€Ñ‚Ð¸ÐºÐ»Ð¾Ð² Ð¿Ñ€Ð¸ Ñ…Ð¾Ð´ÑŒÐ±Ðµ")).setValue(true).visible(() -> this.modes.isSelected("ÐŸÑ€Ð¸ Ñ…Ð¾Ð´ÑŒÐ±Ðµ"));
   private SliderSettings walkGravity = (new SliderSettings("Ð“Ñ€Ð°Ð²Ð¸Ñ‚Ð°Ñ†Ð¸Ñ (Ð¥Ð¾Ð´ÑŒÐ±Ð°)", "Ð¡Ð¸Ð»Ð° Ð¿Ñ€Ð¸Ñ‚ÑÐ¶ÐµÐ½Ð¸Ñ Ð¿Ð°Ñ€Ñ‚Ð¸ÐºÐ»Ð¾Ð² Ð¿Ñ€Ð¸ Ñ…Ð¾Ð´ÑŒÐ±Ðµ")).range(-10.0F, 10.0F).setValue(0.3F).visible(() -> this.modes.isSelected("ÐŸÑ€Ð¸ Ñ…Ð¾Ð´ÑŒÐ±Ðµ"));
   private ColorSetting walkParticleColor = (new ColorSetting("Ð¦Ð²ÐµÑ‚ (Ð¥Ð¾Ð´ÑŒÐ±Ð°)", "Ð¦Ð²ÐµÑ‚ Ð¿Ð°Ñ€Ñ‚Ð¸ÐºÐ»Ð¾Ð² Ð¿Ñ€Ð¸ Ñ…Ð¾Ð´ÑŒÐ±Ðµ")).value((new Color(255, 255, 255, 255)).getRGB()).presets((new Color(0, 246, 255, 255)).getRGB(), (new Color(183, 1, 195, 255)).getRGB(), (new Color(255, 60, 0, 255)).getRGB(), (new Color(171, 253, 0, 255)).getRGB()).visible(() -> this.modes.isSelected("ÐŸÑ€Ð¸ Ñ…Ð¾Ð´ÑŒÐ±Ðµ"));
   private SliderSettings followParticleCount = (new SliderSettings("ÐšÐ¾Ð»Ð¸Ñ‡ÐµÑÑ‚Ð²Ð¾ (Ð¡Ð»ÐµÐ´Ð¾Ð²Ð°Ð½Ð¸Ðµ)", "ÐšÐ¾Ð»Ð¸Ñ‡ÐµÑÑ‚Ð²Ð¾ Ð¿Ð°Ñ€Ñ‚Ð¸ÐºÐ»Ð¾Ð² Ð´Ð»Ñ ÑÐ»ÐµÐ´Ð¾Ð²Ð°Ð½Ð¸Ñ")).range(1, 20).setValue(5.0F).visible(() -> this.modes.isSelected("Ð¡Ð»ÐµÐ´Ð¾Ð²Ð°Ð½Ð¸Ðµ"));
   private SliderSettings followParticleSize = (new SliderSettings("Ð Ð°Ð·Ð¼ÐµÑ€ (Ð¡Ð»ÐµÐ´Ð¾Ð²Ð°Ð½Ð¸Ðµ)", "Ð Ð°Ð·Ð¼ÐµÑ€ Ð¿Ð°Ñ€Ñ‚Ð¸ÐºÐ»Ð¾Ð² ÑÐ»ÐµÐ´Ð¾Ð²Ð°Ð½Ð¸Ñ")).range(0.5F, 3.0F).setValue(1.0F).visible(() -> this.modes.isSelected("Ð¡Ð»ÐµÐ´Ð¾Ð²Ð°Ð½Ð¸Ðµ"));
   private SliderSettings followParticleLifeTime = (new SliderSettings("Ð’Ñ€ÐµÐ¼Ñ Ð¶Ð¸Ð·Ð½Ð¸ (Ð¡Ð»ÐµÐ´Ð¾Ð²Ð°Ð½Ð¸Ðµ)", "Ð’Ñ€ÐµÐ¼Ñ Ð¶Ð¸Ð·Ð½Ð¸ Ð¿Ð°Ñ€Ñ‚Ð¸ÐºÐ»Ð¾Ð² ÑÐ»ÐµÐ´Ð¾Ð²Ð°Ð½Ð¸Ñ Ð² Ð¼Ñ")).range(250.0F, 3000.0F).setValue(500.0F).visible(() -> this.modes.isSelected("Ð¡Ð»ÐµÐ´Ð¾Ð²Ð°Ð½Ð¸Ðµ"));
   private ColorSetting followParticleColor = (new ColorSetting("Ð¦Ð²ÐµÑ‚ (Ð¡Ð»ÐµÐ´Ð¾Ð²Ð°Ð½Ð¸Ðµ)", "Ð¦Ð²ÐµÑ‚ Ð¿Ð°Ñ€Ñ‚Ð¸ÐºÐ»Ð¾Ð² ÑÐ»ÐµÐ´Ð¾Ð²Ð°Ð½Ð¸Ñ")).value((new Color(255, 255, 255, 255)).getRGB()).presets((new Color(0, 246, 255, 255)).getRGB(), (new Color(183, 1, 195, 255)).getRGB(), (new Color(255, 60, 0, 255)).getRGB(), (new Color(171, 253, 0, 255)).getRGB()).visible(() -> this.modes.isSelected("Ð¡Ð»ÐµÐ´Ð¾Ð²Ð°Ð½Ð¸Ðµ"));
   private SelectSetting modetype = (new SelectSetting("ÐœÐ¾Ð´", "Ð’Ñ‹Ð±ÐµÑ€Ð¸Ñ‚Ðµ Ñ‚Ð¸Ð¿ Ð¿Ð°Ñ€Ñ‚Ð¸ÐºÐ»Ð¾Ð²")).value("2D", "3D").selected("2D").visible(() -> this.modes.isSelected("ÐŸÐ¾ Ð¼Ð¸Ñ€Ñƒ"));
   private MultiSelectSetting particleType = (new MultiSelectSetting("Ð¢Ð¸Ð¿ Ñ‡Ð°ÑÑ‚Ð¸Ñ†", "Ð’Ñ‹Ð±Ð¾Ñ€ Ñ‚Ð¸Ð¿Ð°")).value("Ð—Ð²ÐµÐ·Ð´Ñ‹", "Ð¡Ð½ÐµÐ³", "Ð‘Ð»ÑƒÐ¼", "Bucks", "Core", "Crest", "Cube", "Cube Blast", "Ded", "Dollar", "Firefly", "Glow", "Heart1", "Star1").selected("Ð—Ð²ÐµÐ·Ð´Ñ‹").visible(() -> this.modetype.isSelected("2D"));
   private BooleanSetting spawnFromGround = (new BooleanSetting("ÐžÑ‚ Ð·ÐµÐ¼Ð»Ð¸", "Ð¡Ð¿Ð°Ð²Ð½ Ñ‡Ð°ÑÑ‚Ð¸Ñ† Ð¾Ñ‚ Ð·ÐµÐ¼Ð»Ð¸")).setValue(true).visible(() -> this.modetype.isSelected("2D"));
   private BooleanSetting collision = (new BooleanSetting("ÐšÐ¾Ð»Ð»Ð¸Ð·Ð¸Ñ", "ÐšÐ¾Ð»Ð»Ð¸Ð·Ð¸Ñ Ñ‡Ð°ÑÑ‚Ð¸Ñ†")).setValue(true).visible(() -> this.modetype.isSelected("2D"));
   private BooleanSetting scale = (new BooleanSetting("Ð¡ÐºÐµÐ¹Ð»", "ÐœÐ°ÑÑˆÑ‚Ð°Ð±Ð¸Ñ€Ð¾Ð²Ð°Ð½Ð¸Ðµ Ð¿Ð¾ Ð°Ð»ÑŒÑ„Ðµ")).setValue(true).visible(() -> this.modetype.isSelected("2D"));
   private SliderSettings particleCount = (new SliderSettings("ÐšÐ¾Ð»Ð¸Ñ‡ÐµÑÑ‚Ð²Ð¾", "ÐšÐ¾Ð»Ð¸Ñ‡ÐµÑÑ‚Ð²Ð¾ ÐºÑ€Ð¸ÑÑ‚Ð°Ð»Ð»Ð¾Ð² Ð² Ð¼Ð¸Ñ€Ðµ")).range(10, 200).setValue(50.0F).visible(() -> this.modetype.isSelected("3D"));
   private SliderSettings range = (new SliderSettings("Ð”Ð°Ð»ÑŒÐ½Ð¾ÑÑ‚ÑŒ", "Ð”Ð°Ð»ÑŒÐ½Ð¾ÑÑ‚ÑŒ ÑÐ¿Ð°Ð²Ð½Ð° ÐºÑ€Ð¸ÑÑ‚Ð°Ð»Ð»Ð¾Ð² Ð¾Ñ‚ Ð¸Ð³Ñ€Ð¾ÐºÐ°")).range(8, 64).setValue(32.0F).visible(() -> this.modetype.isSelected("3D"));
   private SliderSettings size = (new SliderSettings("Ð Ð°Ð·Ð¼ÐµÑ€ (3D)", "Ð Ð°Ð·Ð¼ÐµÑ€ ÐºÑ€Ð¸ÑÑ‚Ð°Ð»Ð»Ð¾Ð²")).range(0.05F, 0.15F).setValue(0.09F).visible(() -> this.modetype.isSelected("3D"));
   private SliderSettings maxParticles = (new SliderSettings("ÐœÐ°ÐºÑ ÐºÐ¾Ð»Ð¸Ñ‡ÐµÑÑ‚Ð²Ð¾", "ÐœÐ°ÐºÑÐ¸Ð¼Ð°Ð»ÑŒÐ½Ð¾Ðµ ÐºÐ¾Ð»Ð¸Ñ‡ÐµÑÑ‚Ð²Ð¾ Ñ‡Ð°ÑÑ‚Ð¸Ñ†")).range(10, 200).setValue(50.0F).visible(() -> this.modetype.isSelected("2D"));
   private SliderSettings spawnRate = (new SliderSettings("Ð¡Ð¿Ð°Ð²Ð½/ÑÐµÐº", "ÐšÐ¾Ð»Ð¸Ñ‡ÐµÑÑ‚Ð²Ð¾ ÑÐ¿Ð°Ð²Ð½Ð° Ñ‡Ð°ÑÑ‚Ð¸Ñ† Ð² ÑÐµÐºÑƒÐ½Ð´Ñƒ")).range(10.0F, 200.0F).setValue(15.0F).visible(() -> this.modetype.isSelected("2D"));
   private SliderSettings spawnHeight = (new SliderSettings("Ð’Ñ‹ÑÐ¾Ñ‚Ð° ÑÐ¿Ð°Ð²Ð½Ð°", "Ð’Ñ‹ÑÐ¾Ñ‚Ð° ÑÐ¿Ð°Ð²Ð½Ð° Ñ‡Ð°ÑÑ‚Ð¸Ñ†")).range(0.05F, 30.0F).setValue(10.0F).visible(() -> this.modetype.isSelected("2D"));
   private SliderSettings particleGravity = (new SliderSettings("Ð“Ñ€Ð°Ð²Ð¸Ñ‚Ð°Ñ†Ð¸Ñ", "Ð“Ñ€Ð°Ð²Ð¸Ñ‚Ð°Ñ†Ð¸Ñ Ñ‡Ð°ÑÑ‚Ð¸Ñ†")).range(-10.0F, 10.0F).setValue(0.0F).visible(() -> this.modetype.isSelected("2D"));
   private SliderSettings motionPower = (new SliderSettings("Ð¡Ð¸Ð»Ð° Ð´Ð²Ð¸Ð¶ÐµÐ½Ð¸Ñ", "Ð¡Ð¸Ð»Ð° Ð´Ð²Ð¸Ð¶ÐµÐ½Ð¸Ñ Ñ‡Ð°ÑÑ‚Ð¸Ñ†")).range(0.1F, 2.0F).setValue(1.0F).visible(() -> this.modetype.isSelected("2D"));
   private SliderSettings inclineX = (new SliderSettings("ÐÐ°ÐºÐ»Ð¾Ð½ X", "ÐÐ°ÐºÐ»Ð¾Ð½ Ð¿Ð¾Ð»Ñ‘Ñ‚Ð° Ð¿Ð¾ X")).range(-17.5F, 17.5F).setValue(0.0F).visible(() -> this.modetype.isSelected("2D"));
   private SliderSettings inclineZ = (new SliderSettings("ÐÐ°ÐºÐ»Ð¾Ð½ Z", "ÐÐ°ÐºÐ»Ð¾Ð½ Ð¿Ð¾Ð»Ñ‘Ñ‚Ð° Ð¿Ð¾ Z")).range(-17.5F, 17.5F).setValue(0.0F).visible(() -> this.modetype.isSelected("2D"));
   private SliderSettings particleSize = (new SliderSettings("Ð Ð°Ð·Ð¼ÐµÑ€", "Ð Ð°Ð·Ð¼ÐµÑ€ Ñ‡Ð°ÑÑ‚Ð¸Ñ†")).setValue(1.0F).range(0.5F, 2.0F).visible(() -> this.modetype.isSelected("2D"));
   private SliderSettings lifeTime = (new SliderSettings("Ð’Ñ€ÐµÐ¼Ñ Ð¶Ð¸Ð·Ð½Ð¸", "Ð’Ñ€ÐµÐ¼Ñ Ð¶Ð¸Ð·Ð½Ð¸ Ñ‡Ð°ÑÑ‚Ð¸Ñ† Ð² Ð¼Ñ")).setValue(800.0F).range(250.0F, 3000.0F).visible(() -> this.modetype.isSelected("2D"));
   private SliderSettings spawnRange = (new SliderSettings("Ð Ð°Ð´Ð¸ÑƒÑ ÑÐ¿Ð°Ð²Ð½Ð°", "Ð Ð°Ð´Ð¸ÑƒÑ ÑÐ¿Ð°Ð²Ð½Ð°")).setValue(25.0F).range(10.0F, 50.0F).visible(() -> this.modetype.isSelected("2D"));
   private ColorSetting particleColor = (new ColorSetting("Ð¦Ð²ÐµÑ‚", "Ð¦Ð²ÐµÑ‚ ÐºÑ€Ð¸ÑÑ‚Ð°Ð»Ð»Ð¾Ð²")).value((new Color(255, 255, 255, 55)).getRGB()).presets((new Color(0, 246, 255, 255)).getRGB(), (new Color(183, 1, 195, 255)).getRGB(), (new Color(255, 60, 0, 255)).getRGB(), (new Color(171, 253, 0, 255)).getRGB());
   private final List crystalList = new ArrayList();
   private final List particles = new ArrayList();
   private final Random random = new Random();
   private int previousParticleCount;
   private long lastSpawnTime = 0L;
   private final Map followingParticles = new HashMap();
   private class_243 lastPlayerPos = null;

   public static WorldParticles getInstance() {
      return (WorldParticles)Instance.get(WorldParticles.class);
   }

   public WorldParticles() {
      super("WorldParticles", "World Particles", ModuleCategory.RENDER);
      this.setup(new Setting[]{this.modes, this.attackMode, this.attackParticleType, this.attackParticleCount, this.attackParticleSize, this.attackParticleLifeTime, this.attackCollision, this.attackGravity, this.attackParticleColor, this.walkParticleType, this.walkParticleCount, this.walkParticleSize, this.walkParticleLifeTime, this.walkCollision, this.walkGravity, this.walkParticleColor, this.followParticleCount, this.followParticleSize, this.followParticleLifeTime, this.followParticleColor, this.modetype, this.particleType, this.spawnFromGround, this.collision, this.scale, this.particleCount, this.range, this.size, this.maxParticles, this.spawnRate, this.spawnHeight, this.particleGravity, this.motionPower, this.inclineX, this.inclineZ, this.particleSize, this.lifeTime, this.spawnRange, this.particleColor});
      this.previousParticleCount = this.particleCount.getInt();
      this.lastSpawnTime = System.currentTimeMillis();
   }

   public void activate() {
      super.activate();
      if (this.modetype.getSelected().equals("3D")) {
         this.generateCrystals();
      }

      this.previousParticleCount = this.particleCount.getInt();
   }

   public void deactivate() {
      super.deactivate();
      this.crystalList.clear();
      this.particles.clear();
      this.followingParticles.clear();
      this.lastPlayerPos = null;
   }

   @EventHandler
   public void onWorldRender(WorldRenderEvent e) {
      if (mc.field_1724 != null) {
         FrameRateCounter.INSTANCE.recordFrame();
         if (this.modes.isSelected("ÐŸÐ¾ Ð¼Ð¸Ñ€Ñƒ")) {
            if (this.modetype.getSelected().equals("3D")) {
               int currentCount = this.particleCount.getInt();
               if (currentCount != this.previousParticleCount) {
                  this.adjustCrystalCount(currentCount);
                  this.previousParticleCount = currentCount;
               }

               this.updateCrystals();
               this.renderCrystals(e.getStack());
            } else {
               long now = System.currentTimeMillis();
               double spawnInterval = (double)1000.0F / (double)this.spawnRate.getValue();
               if ((double)(now - this.lastSpawnTime) >= spawnInterval && this.particles.size() < this.maxParticles.getInt()) {
                  this.spawnParticle();
                  this.lastSpawnTime = now;
               }

               if (this.particles.size() > this.maxParticles.getInt()) {
                  Iterator<Particle2D> it = this.particles.iterator();

                  while(it.hasNext() && this.particles.size() > this.maxParticles.getInt()) {
                     it.next();
                     it.remove();
                  }
               }
            }
         }

         Iterator<Particle2D> iterator = this.particles.iterator();

         while(iterator.hasNext()) {
            Particle2D particle = (Particle2D)iterator.next();
            particle.update();
            if (particle.isDead()) {
               iterator.remove();
            }
         }

         if (this.modes.isSelected("ÐŸÐ¾ Ð¼Ð¸Ñ€Ñƒ") || this.modes.isSelected("ÐŸÑ€Ð¸ Ð°Ñ‚Ð°ÐºÐµ") || this.modes.isSelected("ÐŸÑ€Ð¸ Ñ…Ð¾Ð´ÑŒÐ±Ðµ")) {
            this.renderParticles(e.getStack());
         }

         if (this.modes.isSelected("Ð¡Ð»ÐµÐ´Ð¾Ð²Ð°Ð½Ð¸Ðµ")) {
            this.renderFollowingParticles(e.getStack());
         }

      }
   }

   @EventHandler
   public void onAttack(AttackEvent e) {
      if (this.modes.isSelected("ÐŸÑ€Ð¸ Ð°Ñ‚Ð°ÐºÐµ") && mc.field_1724 != null && mc.field_1687 != null) {
         class_1297 target = e.getEntity();
         if (target != null) {
            String attackType = this.attackMode.getSelected();
            boolean isCritical = this.isCriticalHit();
            boolean var10000;
            switch (attackType) {
               case "Ð’ÑÐµÐ³Ð´Ð°" -> var10000 = true;
               case "Ð¢Ð¾Ð»ÑŒÐºÐ¾ ÐºÑ€Ð¸Ñ‚" -> var10000 = isCritical;
               case "Ð¢Ð¾Ð»ÑŒÐºÐ¾ Ð¾Ð±Ñ‹Ñ‡Ð½Ñ‹Ð¹" -> var10000 = !isCritical;
               default -> var10000 = true;
            }

            boolean shouldSpawn = var10000;
            if (shouldSpawn) {
               class_243 targetPos = target.method_19538().method_1031((double)0.0F, (double)(target.method_17682() / 2.0F), (double)0.0F);
               this.spawnAttackParticles(targetPos, this.attackParticleCount.getInt());
               this.updateNewParticles();
            }
         }
      }
   }

   private boolean isCriticalHit() {
      if (mc.field_1724 == null) {
         return false;
      } else {
         boolean notOnGround = !mc.field_1724.method_24828();
         boolean isFalling = mc.field_1724.field_6017 > 0.0F;
         boolean notUsingItem = !mc.field_1724.method_6115() || mc.field_1724.method_6115() && mc.field_1724.method_6030().method_7909().equals(class_1802.field_8255);
         return notOnGround && isFalling && notUsingItem;
      }
   }

   @EventHandler
   public void onTick(TickEvent e) {
      if (mc.field_1724 != null && mc.field_1687 != null) {
         if (this.modes.isSelected("ÐŸÑ€Ð¸ Ñ…Ð¾Ð´ÑŒÐ±Ðµ")) {
            class_243 currentPos = mc.field_1724.method_19538();
            if (this.lastPlayerPos != null) {
               double distance = currentPos.method_1022(this.lastPlayerPos);
               if (distance > 0.01) {
                  this.spawnWalkParticles();
                  this.updateNewParticles();
               }
            }

            this.lastPlayerPos = currentPos;
         }

         if (this.modes.isSelected("Ð¡Ð»ÐµÐ´Ð¾Ð²Ð°Ð½Ð¸Ðµ")) {
            this.updateFollowingParticles();
         }

      }
   }

   @EventHandler
   public void onEntitySpawn(EntitySpawnEvent e) {
      if (this.modes.isSelected("Ð¡Ð»ÐµÐ´Ð¾Ð²Ð°Ð½Ð¸Ðµ") && mc.field_1724 != null && mc.field_1687 != null) {
         class_1297 entity = e.getEntity();
         if (this.isPlayerProjectile(entity)) {
            this.followingParticles.put(entity, new ArrayList());
         }

      }
   }

   private boolean isPlayerProjectile(class_1297 entity) {
      if (entity != null && mc.field_1724 != null) {
         if (entity instanceof class_1676) {
            class_1676 projectile = (class_1676)entity;
            class_1297 owner = projectile.method_24921();
            return owner == mc.field_1724;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   private void adjustCrystalCount(int targetCount) {
      int currentSize = this.crystalList.size();
      if (targetCount > currentSize) {
         this.addCrystals(targetCount - currentSize);
      } else if (targetCount < currentSize) {
         this.markCrystalsForRemoval(currentSize - targetCount);
      }

   }

   private void addCrystals(int count) {
      if (mc.field_1724 != null) {
         class_243 playerPos = mc.field_1724.method_19538();
         float rangeValue = this.range.getValue();

         for(int i = 0; i < count; ++i) {
            int attempts = 0;

            class_243 position;
            do {
               double x = playerPos.field_1352 + (this.random.nextDouble() - (double)0.5F) * (double)2.0F * (double)rangeValue;
               double y = playerPos.field_1351 + (this.random.nextDouble() - (double)0.5F) * (double)rangeValue;
               double z = playerPos.field_1350 + (this.random.nextDouble() - (double)0.5F) * (double)2.0F * (double)rangeValue;
               position = new class_243(x, y, z);
               ++attempts;
            } while(!this.isInPlayerView(position) && attempts < 20);

            class_243 velocity = new class_243((this.random.nextDouble() - (double)0.5F) * 0.02, (this.random.nextDouble() - (double)0.5F) * 0.02, (this.random.nextDouble() - (double)0.5F) * 0.02);
            class_243 rotation = new class_243(this.random.nextDouble() * (double)360.0F, this.random.nextDouble() * (double)360.0F, this.random.nextDouble() * (double)360.0F);
            this.crystalList.add(new WorldCrystal(position, velocity, rotation));
         }

      }
   }

   private void markCrystalsForRemoval(int count) {
      int marked = 0;

      for(WorldCrystal crystal : this.crystalList) {
         if (marked >= count) {
            break;
         }

         if (!crystal.markedForDeath && !crystal.isFadingOut) {
            crystal.markedForDeath = true;
            crystal.isFadingOut = true;
            ++marked;
         }
      }

   }

   private void generateCrystals() {
      this.crystalList.clear();
      if (mc.field_1724 != null) {
         class_243 playerPos = mc.field_1724.method_19538();
         int count = this.particleCount.getInt();
         float rangeValue = this.range.getValue();

         for(int i = 0; i < count; ++i) {
            int attempts = 0;

            class_243 position;
            do {
               double x = playerPos.field_1352 + (this.random.nextDouble() - (double)0.5F) * (double)2.0F * (double)rangeValue;
               double y = playerPos.field_1351 + (this.random.nextDouble() - (double)0.5F) * (double)rangeValue;
               double z = playerPos.field_1350 + (this.random.nextDouble() - (double)0.5F) * (double)2.0F * (double)rangeValue;
               position = new class_243(x, y, z);
               ++attempts;
            } while(!this.isInPlayerView(position) && attempts < 20);

            class_243 velocity = new class_243((this.random.nextDouble() - (double)0.5F) * 0.02, (this.random.nextDouble() - (double)0.5F) * 0.02, (this.random.nextDouble() - (double)0.5F) * 0.02);
            class_243 rotation = new class_243(this.random.nextDouble() * (double)360.0F, this.random.nextDouble() * (double)360.0F, this.random.nextDouble() * (double)360.0F);
            this.crystalList.add(new WorldCrystal(position, velocity, rotation));
         }

      }
   }

   private boolean isBlockOccluding(class_243 crystalPos) {
      if (mc.field_1687 != null && mc.field_1724 != null) {
         class_243 cameraPos = mc.field_1773.method_19418().method_19326();
         class_243 direction = crystalPos.method_1020(cameraPos).method_1029();
         double distance = crystalPos.method_1022(cameraPos);
         double step = (double)0.5F;

         for(double d = (double)0.0F; d < distance; d += step) {
            class_243 checkPos = cameraPos.method_1019(direction.method_1021(d));
            class_2338 blockPos = class_2338.method_49638(checkPos);
            if (!mc.field_1687.method_8320(blockPos).method_26215()) {
               return true;
            }
         }

         return false;
      } else {
         return false;
      }
   }

   private void updateCrystals() {
      if (mc.field_1724 != null) {
         class_243 playerPos = mc.field_1724.method_19538();
         float rangeValue = this.range.getValue();
         float fadeSpeedValue = 0.05F;
         Iterator<WorldCrystal> iterator = this.crystalList.iterator();

         while(iterator.hasNext()) {
            WorldCrystal crystal = (WorldCrystal)iterator.next();
            crystal.prevPosition = crystal.position;
            crystal.position = crystal.position.method_1019(crystal.velocity);
            boolean isOccluded = this.isBlockOccluding(crystal.position);
            boolean inView = this.isInPlayerView(crystal.position);
            if (crystal.markedForDeath) {
               crystal.fadeAlpha -= fadeSpeedValue;
               if (crystal.fadeAlpha <= 0.0F) {
                  iterator.remove();
               }
            } else {
               if (!isOccluded && inView) {
                  if (crystal.isFadingOut) {
                     crystal.isFadingOut = false;
                  }
               } else if (!crystal.isFadingOut) {
                  crystal.isFadingOut = true;
               }

               if (crystal.isFadingOut) {
                  crystal.fadeAlpha -= fadeSpeedValue;
                  if (crystal.fadeAlpha <= 0.0F) {
                     crystal.fadeAlpha = 0.0F;
                     int attempts = 0;

                     class_243 newPosition;
                     do {
                        double x = playerPos.field_1352 + (this.random.nextDouble() - (double)0.5F) * (double)2.0F * (double)rangeValue;
                        double y = playerPos.field_1351 + (this.random.nextDouble() - (double)0.5F) * (double)rangeValue;
                        double z = playerPos.field_1350 + (this.random.nextDouble() - (double)0.5F) * (double)2.0F * (double)rangeValue;
                        newPosition = new class_243(x, y, z);
                        ++attempts;
                     } while(!this.isInPlayerView(newPosition) && attempts < 20);

                     crystal.position = newPosition;
                     crystal.prevPosition = crystal.position;
                     crystal.isFadingOut = false;
                  }
               } else {
                  crystal.fadeAlpha += fadeSpeedValue;
                  if (crystal.fadeAlpha > 1.0F) {
                     crystal.fadeAlpha = 1.0F;
                  }
               }

               if (crystal.position.method_1022(playerPos) > (double)rangeValue * (double)1.5F) {
                  int attempts = 0;

                  class_243 newPosition;
                  do {
                     double x = playerPos.field_1352 + (this.random.nextDouble() - (double)0.5F) * (double)2.0F * (double)rangeValue;
                     double y = playerPos.field_1351 + (this.random.nextDouble() - (double)0.5F) * (double)rangeValue;
                     double z = playerPos.field_1350 + (this.random.nextDouble() - (double)0.5F) * (double)2.0F * (double)rangeValue;
                     newPosition = new class_243(x, y, z);
                     ++attempts;
                  } while(!this.isInPlayerView(newPosition) && attempts < 20);

                  crystal.position = newPosition;
                  crystal.prevPosition = crystal.position;
                  crystal.fadeAlpha = 0.0F;
                  crystal.isFadingOut = false;
               }
            }
         }

      }
   }

   private class_243 getRandomMotion() {
      return new class_243((this.random.nextDouble() - (double)0.5F) * 0.08, this.random.nextDouble() * 0.05, (this.random.nextDouble() - (double)0.5F) * 0.08);
   }

   private void spawnParticle() {
      if (mc.field_1724 != null && mc.field_1687 != null) {
         int r = (int)this.spawnRange.getValue();
         double randX = (this.random.nextDouble() * (double)2.0F - (double)1.0F) * (double)r;
         double randZ = (this.random.nextDouble() * (double)2.0F - (double)1.0F) * (double)r;
         class_243 additional = mc.field_1724.method_19538().method_1031(randX, (double)0.0F, randZ);
         class_2338 topPos = mc.field_1687.method_8598(class_2903.field_13197, class_2338.method_49638(additional));
         double x = (double)topPos.method_10263() + this.random.nextDouble();
         double z = (double)topPos.method_10260() + this.random.nextDouble();
         double y = mc.field_1724.method_23318() + this.random.nextDouble() * (double)r;
         class_243 spawnPos = new class_243(x, y, z);
         class_2338 worldHeightPos = mc.field_1687.method_8598(class_2903.field_13202, class_2338.method_49638(spawnPos));

         for(int maxY = worldHeightPos.method_10264() + 2; !mc.field_1687.method_8320(class_2338.method_49638(spawnPos)).method_26215() && spawnPos.field_1351 < (double)maxY; spawnPos = spawnPos.method_1031((double)0.0F, (double)1.0F, (double)0.0F)) {
         }

         if (this.isInPlayerView(spawnPos)) {
            class_243 vel = (new class_243((this.random.nextDouble() - (double)0.5F) * 0.1, (this.random.nextDouble() - (double)0.5F) * 0.1, (this.random.nextDouble() - (double)0.5F) * 0.1)).method_1021((double)this.motionPower.getValue());
            int lifetime = (int)this.lifeTime.getValue() + this.random.nextInt(50) - 25;
            lifetime = Math.max(150, lifetime);
            int color = this.particleColor.getColor();
            this.particles.add(new Particle2D(spawnPos, vel, lifetime, color, this.getRandomSelectedParticleType()));
         }
      }
   }

   private String getRandomSelectedParticleType() {
      if (!this.modes.isSelected("ÐŸÐ¾ Ð¼Ð¸Ñ€Ñƒ")) {
         return "Ð—Ð²ÐµÐ·Ð´Ñ‹";
      } else {
         List<String> selectedTypes = this.particleType.getSelected();
         return selectedTypes.isEmpty() ? "Ð—Ð²ÐµÐ·Ð´Ñ‹" : (String)selectedTypes.get(this.random.nextInt(selectedTypes.size()));
      }
   }

   private String getRandomAttackParticleType() {
      List<String> selectedTypes = this.attackParticleType.getSelected();
      return selectedTypes.isEmpty() ? "Ð—Ð²ÐµÐ·Ð´Ñ‹" : (String)selectedTypes.get(this.random.nextInt(selectedTypes.size()));
   }

   private String getRandomWalkParticleType() {
      List<String> selectedTypes = this.walkParticleType.getSelected();
      return selectedTypes.isEmpty() ? "Ð—Ð²ÐµÐ·Ð´Ñ‹" : (String)selectedTypes.get(this.random.nextInt(selectedTypes.size()));
   }

   private String getTexturePath(String particleTypeName) {
      String var10000;
      switch (particleTypeName) {
         case "Ð¡Ð½ÐµÐ³" -> var10000 = "textures/particles/show1.png";
         case "Ð‘Ð»ÑƒÐ¼" -> var10000 = "textures/particles/glow.png";
         case "Bucks" -> var10000 = "textures/particles/bucks1.png";
         case "Core" -> var10000 = "textures/particles/core1.png";
         case "Crest" -> var10000 = "textures/particles/crest1.png";
         case "Cube" -> var10000 = "textures/particles/cube1.png";
         case "Cube Blast" -> var10000 = "textures/particles/cubeblast1.png";
         case "Ded" -> var10000 = "textures/particles/ded1.png";
         case "Dollar" -> var10000 = "textures/particles/dollar.png";
         case "Firefly" -> var10000 = "textures/particles/firefly.png";
         case "Glow" -> var10000 = "textures/particles/glow.png";
         case "Heart1" -> var10000 = "textures/particles/heart1.png";
         case "Star1" -> var10000 = "textures/particles/star1.png";
         default -> var10000 = "textures/particles/star1.png";
      }

      return var10000;
   }

   private void renderParticles(class_4587 stack) {
      class_4184 camera = mc.field_1773.method_19418();
      class_243 camPos = camera.method_19326();
      RenderSystem.enableDepthTest();
      RenderSystem.depthFunc(515);
      RenderSystem.depthMask(true);

      for(Particle2D particle : this.particles) {
         this.renderSingleParticle(particle, camera, camPos);
      }

      RenderSystem.depthMask(true);
      RenderSystem.disableDepthTest();
   }

   private void renderSingleParticle(Particle2D particle, class_4184 camera, class_243 camPos) {
      float alpha = particle.fade();
      if (!(alpha <= 0.0F)) {
         if (!this.isBlockOccluding(particle.pos)) {
            float scaleFactor = particle.scaleFactor();
            Color baseColor = new Color(particle.colorInt);
            int r = baseColor.getRed();
            int g = baseColor.getGreen();
            int b = baseColor.getBlue();
            int a = (int)(alpha * 255.0F);
            int argb = (new Color(r, g, b, a)).getRGB();
            double posX = particle.pos.field_1352 - camPos.field_1352;
            double posY = particle.pos.field_1351 - camPos.field_1351;
            double posZ = particle.pos.field_1350 - camPos.field_1350;
            class_2960 texture = class_2960.method_60654(this.getTexturePath(particle.type));
            RenderSystem.setShaderTexture(0, texture);
            RenderSystem.disableCull();
            RenderSystem.enableBlend();
            RenderSystem.blendFunc(class_4535.SRC_ALPHA, class_4534.ONE_MINUS_SRC_ALPHA);
            RenderSystem.setShader(class_10142.field_53880);
            RenderSystem.enableDepthTest();
            class_4587 matrix = new class_4587();
            matrix.method_22903();
            matrix.method_22904(posX, posY, posZ);
            float yaw = -camera.method_19330();
            float pitch = camera.method_19329();
            matrix.method_22907(class_7833.field_40716.rotationDegrees(yaw));
            matrix.method_22907(class_7833.field_40714.rotationDegrees(pitch));
            float sizeVal = this.particleSize.getValue() * scaleFactor;
            Matrix4f positionMatrix = matrix.method_23760().method_23761();
            class_287 bufferBuilder = class_289.method_1348().method_60827(class_5596.field_27382, class_290.field_1575);
            float halfSize = sizeVal / 2.0F;
            bufferBuilder.method_22918(positionMatrix, -halfSize, -halfSize, 0.0F).method_22913(0.0F, 0.0F).method_39415(argb);
            bufferBuilder.method_22918(positionMatrix, halfSize, -halfSize, 0.0F).method_22913(1.0F, 0.0F).method_39415(argb);
            bufferBuilder.method_22918(positionMatrix, halfSize, halfSize, 0.0F).method_22913(1.0F, 1.0F).method_39415(argb);
            bufferBuilder.method_22918(positionMatrix, -halfSize, halfSize, 0.0F).method_22913(0.0F, 1.0F).method_39415(argb);
            class_286.method_43433(bufferBuilder.method_60800());
            matrix.method_22909();
            RenderSystem.disableBlend();
            RenderSystem.enableCull();
         }
      }
   }

   private void updateNewParticles() {
      for(Particle2D particle : this.particles) {
         particle.update();
      }

   }

   private void spawnAttackParticles(class_243 pos, int count) {
      for(int i = 0; i < count; ++i) {
         class_243 randomOffset = new class_243((this.random.nextDouble() - (double)0.5F) * (double)0.5F, (this.random.nextDouble() - (double)0.5F) * (double)0.5F, (this.random.nextDouble() - (double)0.5F) * (double)0.5F);
         class_243 spawnPos = pos.method_1019(randomOffset);
         class_243 motion = this.getRandomMotion();
         int lifetime = (int)this.attackParticleLifeTime.getValue() + this.random.nextInt(100) - 50;
         int color = this.attackParticleColor.getColor();
         String type = this.getRandomAttackParticleType();
         this.particles.add(new Particle2D(spawnPos, motion, lifetime, color, type));
      }

   }

   private void spawnWalkParticles() {
      if (mc.field_1724 != null) {
         class_243 basePos = mc.field_1724.method_19538().method_1031((double)0.0F, 0.1, (double)0.0F);
         class_243 vel = this.getRandomMotion();
         int count = this.walkParticleCount.getInt();

         for(int i = 0; i < count; ++i) {
            class_243 motion = vel.method_1031((this.random.nextDouble() - (double)0.5F) * 0.02, (this.random.nextDouble() - (double)0.5F) * 0.02, (this.random.nextDouble() - (double)0.5F) * 0.02);
            int lifetime = (int)this.walkParticleLifeTime.getValue() + this.random.nextInt(100) - 50;
            int color = this.walkParticleColor.getColor();
            String type = this.getRandomWalkParticleType();
            this.particles.add(new Particle2D(basePos, motion, lifetime, color, type));
         }

      }
   }

   private void renderFollowingParticles(class_4587 stack) {
      class_4184 camera = mc.field_1773.method_19418();
      class_243 camPos = camera.method_19326();
      RenderSystem.enableDepthTest();
      RenderSystem.depthFunc(515);
      RenderSystem.depthMask(true);

      for(Map.Entry entry : this.followingParticles.entrySet()) {
         class_1297 entity = (class_1297)entry.getKey();
         if (entity != null && entity.method_5805()) {
            List<Particle2D> entityParticles = (List)entry.getValue();
            entityParticles.removeIf(Particle2D::isDead);

            for(Particle2D particle : entityParticles) {
               this.renderSingleParticle(particle, camera, camPos);
            }
         }
      }

      RenderSystem.depthMask(true);
      RenderSystem.disableDepthTest();
   }

   private void updateFollowingParticles() {
      if (mc.field_1724 != null && mc.field_1687 != null) {
         List<class_1297> toRemove = new ArrayList();

         for(Map.Entry entry : this.followingParticles.entrySet()) {
            class_1297 entity = (class_1297)entry.getKey();
            List<Particle2D> entityParticles = (List)entry.getValue();
            if (entity != null && entity.method_5805()) {
               class_243 pos = entity.method_19538();
               if (entityParticles.size() < this.followParticleCount.getInt()) {
                  class_243 motion = this.getRandomMotion();
                  int lifetime = (int)this.followParticleLifeTime.getValue() + this.random.nextInt(100) - 50;
                  int color = this.followParticleColor.getColor();
                  String type = this.getRandomSelectedParticleType();
                  entityParticles.add(new Particle2D(pos, motion, lifetime, color, type));
               }

               for(Particle2D particle : entityParticles) {
                  particle.pos = particle.pos.method_1019(particle.motion);
                  --particle.lifetime;
               }

               entityParticles.removeIf(Particle2D::isDead);
            } else {
               toRemove.add(entity);
            }
         }

         Map var10001 = this.followingParticles;
         Objects.requireNonNull(var10001);
         toRemove.forEach(var10001::remove);
      }
   }

   private float getCameraYaw() {
      class_4184 camera = mc.field_1773.method_19418();
      return camera.method_19330();
   }

   private class_243 getCameraLookVec() {
      class_4184 camera = mc.field_1773.method_19418();
      return class_243.method_1030(camera.method_19329(), camera.method_19330());
   }

   private boolean isInPlayerView(class_243 pos) {
      if (mc.field_1773.method_19418() == null) {
         return true;
      } else {
         class_4184 cam = mc.field_1773.method_19418();
         class_243 camPos = cam.method_19326();
         class_243 look = this.getCameraLookVec();
         class_243 toParticle = pos.method_1020(camPos).method_1029();
         return look.method_1026(toParticle) > 0.1;
      }
   }

   private void renderCrystals(class_4587 ms) {
      if (mc.field_1724 != null && !this.crystalList.isEmpty()) {
         class_4184 camera = mc.field_1773.method_19418();
         RenderSystem.enableDepthTest();
         RenderSystem.depthFunc(515);

         for(WorldCrystal crystal : this.crystalList) {
            if (!(crystal.fadeAlpha <= 0.0F)) {
               float tickDelta = mc.method_61966().method_60637(false);
               class_243 renderPos = crystal.prevPosition.method_35590(crystal.position, (double)tickDelta);
               if (this.isInPlayerView(renderPos) || crystal.isFadingOut) {
                  ms.method_22903();
                  ms.method_22904(renderPos.field_1352, renderPos.field_1351, renderPos.field_1350);
                  float pulsation = 1.0F + (float)(Math.sin((double)System.currentTimeMillis() / (double)500.0F) * (double)0.1F);
                  ms.method_22905(pulsation, pulsation, pulsation);
                  float selfRotation = (float)(System.currentTimeMillis() % 36000L) / 100.0F * crystal.rotationSpeed;
                  ms.method_22907(class_7833.field_40714.rotationDegrees((float)crystal.rotation.field_1352));
                  ms.method_22907(class_7833.field_40716.rotationDegrees((float)crystal.rotation.field_1351 + selfRotation));
                  ms.method_22907(class_7833.field_40718.rotationDegrees((float)crystal.rotation.field_1350));
                  crystal.render(ms, this.particleColor.getColor(), camera, this.size.getValue(), 8);
                  ms.method_22909();
               }
            }
         }

         RenderSystem.disableDepthTest();
      }
   }

   public static class Particle2D {
      class_243 pos;
      class_243 motion;
      int lifetime;
      final int colorInt;
      final String type;

      public Particle2D(class_243 pos, class_243 motion, int lifetime, int colorInt, String type) {
         this.pos = pos;
         this.motion = motion;
         this.lifetime = lifetime;
         this.colorInt = colorInt;
         this.type = type;
      }

      public void update() {
         this.pos = this.pos.method_1019(this.motion);
         --this.lifetime;
      }

      public boolean isDead() {
         return this.lifetime <= 0;
      }

      public float fade() {
         return Math.max(0.0F, Math.min(1.0F, (float)this.lifetime / 100.0F));
      }

      public float scaleFactor() {
         return 1.0F;
      }
   }

   public static class WorldCrystal {
      class_243 position;
      class_243 prevPosition;
      class_243 velocity;
      class_243 rotation;
      boolean markedForDeath = false;
      boolean isFadingOut = false;
      float fadeAlpha = 1.0F;
      float rotationSpeed = 0.5F + (float)(Math.random() * (double)1.5F);

      public WorldCrystal(class_243 position, class_243 velocity, class_243 rotation) {
         this.position = position;
         this.prevPosition = position;
         this.velocity = velocity;
         this.rotation = rotation;
      }

      public void render(class_4587 ms, int baseColor, class_4184 camera, float size, int detail) {
         int color = ColorAssist.setAlpha(baseColor, (int)(this.fadeAlpha * 255.0F));
         RenderSystem.disableCull();
         RenderSystem.enableBlend();
         RenderSystem.blendFunc(class_4535.SRC_ALPHA, class_4534.ONE_MINUS_SRC_ALPHA);
         RenderSystem.setShader(class_10142.field_53876);
         class_287 bufferBuilder = class_289.method_1348().method_60827(class_5596.field_27379, class_290.field_1576);
         float s = size;
         float hPrism = size * 1.0F;
         float hPyramid = size * 1.5F;
         int numSides = detail;
         List<class_243> topVertices = new ArrayList();
         List<class_243> bottomVertices = new ArrayList();

         for(int i = 0; i < numSides; ++i) {
            float angle = (float)((Math.PI * 2D) * (double)i / (double)numSides);
            float x = (float)((double)s * Math.cos((double)angle));
            float z = (float)((double)s * Math.sin((double)angle));
            topVertices.add(new class_243((double)x, (double)(hPrism / 2.0F), (double)z));
            bottomVertices.add(new class_243((double)x, (double)(-hPrism / 2.0F), (double)z));
         }

         class_243 vTop = new class_243((double)0.0F, (double)(hPrism / 2.0F + hPyramid), (double)0.0F);
         class_243 vBottom = new class_243((double)0.0F, (double)(-hPrism / 2.0F - hPyramid), (double)0.0F);
         Matrix4f mat = ms.method_23760().method_23761();

         for(int i = 0; i < numSides; ++i) {
            class_243 v1 = (class_243)bottomVertices.get(i);
            class_243 v2 = (class_243)bottomVertices.get((i + 1) % numSides);
            class_243 v3 = (class_243)topVertices.get((i + 1) % numSides);
            class_243 v4 = (class_243)topVertices.get(i);
            this.addTriangle(mat, bufferBuilder, v1, v2, v3, color);
            this.addTriangle(mat, bufferBuilder, v1, v3, v4, color);
         }

         for(int i = 0; i < numSides; ++i) {
            class_243 v1 = (class_243)topVertices.get(i);
            class_243 v2 = (class_243)topVertices.get((i + 1) % numSides);
            this.addTriangle(mat, bufferBuilder, vTop, v1, v2, color);
         }

         for(int i = 0; i < numSides; ++i) {
            class_243 v1 = (class_243)bottomVertices.get(i);
            class_243 v2 = (class_243)bottomVertices.get((i + 1) % numSides);
            this.addTriangle(mat, bufferBuilder, vBottom, v2, v1, color);
         }

         class_286.method_43433(bufferBuilder.method_60800());
         RenderSystem.disableBlend();
         RenderSystem.enableCull();
      }

      private void addTriangle(Matrix4f mat, class_287 bb, class_243 v1, class_243 v2, class_243 v3, int color) {
         bb.method_22918(mat, (float)v1.field_1352, (float)v1.field_1351, (float)v1.field_1350).method_39415(color);
         bb.method_22918(mat, (float)v2.field_1352, (float)v2.field_1351, (float)v2.field_1350).method_39415(color);
         bb.method_22918(mat, (float)v3.field_1352, (float)v3.field_1351, (float)v3.field_1350).method_39415(color);
      }
   }
}

