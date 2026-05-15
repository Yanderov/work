package fun.Yanderov.display.screens.clickgui.components.implement.module;

import fun.Yanderov.features.impl.combat.AntiBot;
import fun.Yanderov.features.impl.combat.Aura;
import fun.Yanderov.features.impl.combat.AutoArmor;
import fun.Yanderov.features.impl.combat.AutoCrystal;
import fun.Yanderov.features.impl.combat.AutoSwap;
import fun.Yanderov.features.impl.combat.AutoTotem;
import fun.Yanderov.features.impl.combat.HitBoxModule;
import fun.Yanderov.features.impl.combat.NoFriendDamage;
import fun.Yanderov.features.impl.combat.NoInteract;
import fun.Yanderov.features.impl.combat.ProjectileHelper;
import fun.Yanderov.features.impl.combat.ShiftTap;
import fun.Yanderov.features.impl.combat.TargetPearl;
import fun.Yanderov.features.impl.combat.TriggerBot;
import fun.Yanderov.features.impl.combat.Velocity;
import fun.Yanderov.features.impl.misc.AutoBuy;
import fun.Yanderov.features.impl.misc.AutoLeave;
import fun.Yanderov.features.impl.misc.AutoTpAccept;
import fun.Yanderov.features.impl.misc.ChestStealer;
import fun.Yanderov.features.impl.misc.ClickFriend;
import fun.Yanderov.features.impl.misc.ClickPearl;
import fun.Yanderov.features.impl.misc.ElytraHelper;
import fun.Yanderov.features.impl.misc.FreeCam;
import fun.Yanderov.features.impl.misc.IRC;
import fun.Yanderov.features.impl.misc.JoinerHelper;
import fun.Yanderov.features.impl.misc.SelfDestruct;
import fun.Yanderov.features.impl.misc.ServerHelper;
import fun.Yanderov.features.impl.misc.ServerRPSpoofer;
import fun.Yanderov.features.impl.misc.WindJump;
import fun.Yanderov.features.impl.movement.AirStuck;
import fun.Yanderov.features.impl.movement.AutoSprint;
import fun.Yanderov.features.impl.movement.Blink;
import fun.Yanderov.features.impl.movement.ElytraMotion;
import fun.Yanderov.features.impl.movement.Fly;
import fun.Yanderov.features.impl.movement.InventoryMove;
import fun.Yanderov.features.impl.movement.Jesus;
import fun.Yanderov.features.impl.movement.LongJump;
import fun.Yanderov.features.impl.movement.NoClip;
import fun.Yanderov.features.impl.movement.NoFallDamage;
import fun.Yanderov.features.impl.movement.NoSlow;
import fun.Yanderov.features.impl.movement.NoWeb;
import fun.Yanderov.features.impl.movement.Speed;
import fun.Yanderov.features.impl.movement.Spider;
import fun.Yanderov.features.impl.movement.Strafe;
import fun.Yanderov.features.impl.movement.SuperFireWork;
import fun.Yanderov.features.impl.movement.TargetStrafe;
import fun.Yanderov.features.impl.movement.WaterSpeed;
import fun.Yanderov.features.impl.player.AntiAFK;
import fun.Yanderov.features.impl.player.AutoPilot;
import fun.Yanderov.features.impl.player.AutoRespawn;
import fun.Yanderov.features.impl.player.AutoTool;
import fun.Yanderov.features.impl.player.AutoUse;
import fun.Yanderov.features.impl.player.EnderChestPlus;
import fun.Yanderov.features.impl.player.FastBreak;
import fun.Yanderov.features.impl.player.FreeLook;
import fun.Yanderov.features.impl.player.ItemScroller;
import fun.Yanderov.features.impl.player.NameProtect;
import fun.Yanderov.features.impl.player.NoDelay;
import fun.Yanderov.features.impl.player.NoPush;
import fun.Yanderov.features.impl.render.Arrows;
import fun.Yanderov.features.impl.render.AspectRatio;
import fun.Yanderov.features.impl.render.AuctionHelper;
import fun.Yanderov.features.impl.render.BlockESP;
import fun.Yanderov.features.impl.render.BlockOverlay;
import fun.Yanderov.features.impl.render.CameraSettings;
import fun.Yanderov.features.impl.render.CrossHair;
import fun.Yanderov.features.impl.render.Esp;
import fun.Yanderov.features.impl.render.Hud;
import fun.Yanderov.features.impl.render.KillEffect;
import fun.Yanderov.features.impl.render.NoRender;
import fun.Yanderov.features.impl.render.ProjectilePrediction;
import fun.Yanderov.features.impl.render.SeeInvisible;
import fun.Yanderov.features.impl.render.SwingAnimation;
import fun.Yanderov.features.impl.render.TargetESP;
import fun.Yanderov.features.impl.render.ViewModel;
import fun.Yanderov.features.impl.render.WorldParticles;
import fun.Yanderov.features.impl.render.WorldTweaks;
import fun.Yanderov.features.impl.render.XRay;
import fun.Yanderov.features.module.Module;

public class ModuleDescriptions {
   public static String getDescription(Module module) {
      if (module instanceof ServerHelper) {
         return "ÐŸÐ¾Ð¼Ð¾Ð³Ð°ÐµÑ‚ Ð²Ð·Ð°Ð¸Ð¼Ð¾Ð´ÐµÐ¹ÑÑ‚Ð²Ð¾Ð²Ð°Ñ‚ÑŒ Ñ ÑÐµÑ€Ð²ÐµÑ€Ð¾Ð¼, Ð¿Ñ€ÐµÐ´Ð¾ÑÑ‚Ð°Ð²Ð»ÑÑ Ð¿Ð¾Ð»ÐµÐ·Ð½Ñ‹Ðµ Ñ„ÑƒÐ½ÐºÑ†Ð¸Ð¸.";
      } else if (module instanceof WaterSpeed) {
         return "Ð£Ð²ÐµÐ»Ð¸Ñ‡Ð¸Ð²Ð°ÐµÑ‚ ÑÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Ð¿ÐµÑ€ÐµÐ´Ð²Ð¸Ð¶ÐµÐ½Ð¸Ñ Ð² Ð²Ð¾Ð´Ðµ.";
      } else if (module instanceof ItemScroller) {
         return "ÐÐ°ÑÑ‚Ñ€Ð°Ð¸Ð²Ð°ÐµÑ‚ Ð¿Ð¾Ð²ÐµÐ´ÐµÐ½Ð¸Ðµ Ð¿Ñ€ÐµÐ´Ð¼ÐµÑ‚Ð¾Ð² Ð´Ð»Ñ ÑƒÐ´Ð¾Ð±ÑÑ‚Ð²Ð°.";
      } else if (module instanceof Hud) {
         return "ÐžÑ‚Ð¾Ð±Ñ€Ð°Ð¶Ð°ÐµÑ‚ Ð´Ð¾Ð¿Ð¾Ð»Ð½Ð¸Ñ‚ÐµÐ»ÑŒÐ½ÑƒÑŽ Ð¸Ð½Ñ„Ð¾Ñ€Ð¼Ð°Ñ†Ð¸ÑŽ Ð½Ð° ÑÐºÑ€Ð°Ð½Ðµ.";
      } else if (module instanceof AuctionHelper) {
         return "ÐŸÐ¾Ð¼Ð¾Ð³Ð°ÐµÑ‚ ÑƒÐ¿Ñ€Ð°Ð²Ð»ÑÑ‚ÑŒ Ð°ÑƒÐºÑ†Ð¸Ð¾Ð½Ð°Ð¼Ð¸ Ð½Ð° ÑÐµÑ€Ð²ÐµÑ€Ðµ.";
      } else if (module instanceof ProjectilePrediction) {
         return "ÐŸÑ€ÐµÐ´ÑÐºÐ°Ð·Ñ‹Ð²Ð°ÐµÑ‚ Ñ‚Ñ€Ð°ÐµÐºÑ‚Ð¾Ñ€Ð¸ÑŽ Ð¿Ð¾Ð»ÐµÑ‚Ð° ÑÐ½Ð°Ñ€ÑÐ´Ð¾Ð².";
      } else if (module instanceof AntiAFK) {
         return "Ð’Ñ‹Ð¿Ð¾Ð»Ð½ÑÐµÑ‚ ÐºÐ°ÐºÐ¾Ðµ-Ñ‚Ð¾ Ð´ÐµÐ¹ÑÑ‚Ð²Ð¸Ðµ Ñ‡Ñ‚Ð¾Ð±Ñ‹ Ð¸Ð³Ñ€Ð¾ÐºÐ° Ð½Ðµ ÐºÐ¸ÐºÐ½ÑƒÐ»Ð¾ Ð·Ð° Ð°Ñ„Ðº.";
      } else if (module instanceof Strafe) {
         return "ÐŸÐ¾Ð¼Ð¾Ð³Ð°ÐµÑ‚ Ð¸Ð³Ñ€Ð¾ÐºÑƒ Ð¿Ñ€Ð¸ Ñ…Ð¾Ð´ÑŒÐ±Ðµ.";
      } else if (module instanceof TargetStrafe) {
         return "ÐšÑ€ÑƒÑ‚Ð¸Ñ‚ÑÑ Ð²Ð¾ÐºÑ€ÑƒÐ³ Ñ‚Ð°Ñ€Ð³ÐµÑ‚Ð° Ð² aura.";
      } else if (module instanceof Jesus) {
         return "Ð”Ð°ÐµÑ‚ Ð²Ð¾Ð·Ð¼Ð¾Ð¶Ð½Ð¾ÑÑ‚ÑŒ Ð¸Ð³Ñ€Ð¾ÐºÑƒ Ñ…Ð¾Ð´Ð¸Ñ‚ÑŒ Ð¿Ð¾ Ð²Ð¾Ð´Ðµ.";
      } else if (module instanceof ProjectileHelper) {
         return "ÐŸÐ¾Ð¼Ð¾Ð³Ð°ÐµÑ‚ Ð¸Ð³Ñ€Ð¾ÐºÑƒ Ð½Ð°Ð²Ð¾Ð´Ð¸Ñ‚ÑÑ Ð½Ð° Ñ†ÐµÐ»ÑŒ.";
      } else if (module instanceof XRay) {
         return "ÐŸÐ¾Ð·Ð²Ð¾Ð»ÑÐµÑ‚ Ð²Ð¸Ð´ÐµÑ‚ÑŒ ÑÐºÐ²Ð¾Ð·ÑŒ Ð±Ð»Ð¾ÐºÐ¸ Ð´Ð»Ñ Ð¿Ð¾Ð¸ÑÐºÐ° Ñ€ÐµÑÑƒÑ€ÑÐ¾Ð².";
      } else if (module instanceof TriggerBot) {
         return "Ð‘ÑŒÐµÑ‚ ÑÑƒÑ‰Ð½Ð¾ÑÑ‚ÑŒ ÐµÑÐ»Ð¸ Ð¸Ð³Ñ€Ð¾Ðº ÑÐ¼Ð¾Ñ‚Ñ€Ð¸Ñ‚ Ð½Ð° Ð½ÐµÐµ.";
      } else if (module instanceof Aura) {
         return "ÐÐ²Ñ‚Ð¾Ð¼Ð°Ñ‚Ð¸Ñ‡ÐµÑÐºÐ¸ Ð°Ñ‚Ð°ÐºÑƒÐµÑ‚ Ð±Ð»Ð¸Ð¶Ð°Ð¹ÑˆÐ¸Ñ… Ð²Ñ€Ð°Ð³Ð¾Ð².";
      } else if (module instanceof AutoSwap) {
         return "ÐÐ²Ñ‚Ð¾Ð¼Ð°Ñ‚Ð¸Ñ‡ÐµÑÐºÐ¸ Ð¼ÐµÐ½ÑÐµÑ‚ Ð¿Ñ€ÐµÐ´Ð¼ÐµÑ‚Ñ‹ Ð² Ñ€ÑƒÐºÐµ.";
      } else if (module instanceof AutoPilot) {
         return "ÐÐ°Ð²Ð¾Ð´Ð¸Ñ‚ ÐºÐ°Ð¼ÐµÑ€Ñƒ Ð¸Ð³Ñ€Ð¾ÐºÐ° Ð½Ð° Ñ†ÐµÐ½Ð½Ñ‹Ð¹ Ð¿Ñ€ÐµÐ´Ð¼ÐµÑ‚ ÑÐµÑ€Ð²ÐµÑ€Ð° ReallyWorld";
      } else if (module instanceof NoFriendDamage) {
         return "ÐŸÑ€ÐµÐ´Ð¾Ñ‚Ð²Ñ€Ð°Ñ‰Ð°ÐµÑ‚ ÑƒÑ€Ð¾Ð½ Ð¿Ð¾ ÑÐ¾ÑŽÐ·Ð½Ð¸ÐºÐ°Ð¼.";
      } else if (module instanceof SelfDestruct) {
         return "Ð¡ÐºÑ€Ñ‹Ð²Ð°ÐµÑ‚ Ñ‡Ð¸Ñ‚ Ñ Ð¸Ð³Ñ€Ñ‹, Ð½Ð°Ñ…Ð¾Ð´Ð¸Ñ‚ÑÑ Ð² Ñ€Ð°Ð·Ñ€Ð°Ð±Ð¾Ñ‚ÐºÐµ.";
      } else if (module instanceof AutoBuy) {
         return "ÐÐ²Ñ‚Ð¾Ð¼Ð°Ñ‚Ð¸Ñ‡ÐµÑÐºÐ¸ ÑÐºÑƒÐ¿Ð°ÐµÑ‚ Ñ€ÐµÑÑƒÑ€ÑÑ‹ Ñ Ð°ÑƒÐºÑ†Ð¸Ð¾Ð½Ð°.";
      } else if (module instanceof HitBoxModule) {
         return "Ð˜Ð·Ð¼ÐµÐ½ÑÐµÑ‚ Ñ€Ð°Ð·Ð¼ÐµÑ€ Ñ…Ð¸Ñ‚Ð±Ð¾ÐºÑÐ¾Ð² ÑÑƒÑ‰Ð½Ð¾ÑÑ‚ÐµÐ¹.";
      } else if (module instanceof AntiBot) {
         return "ÐžÐ±Ð½Ð°Ñ€ÑƒÐ¶Ð¸Ð²Ð°ÐµÑ‚ Ð¸ Ð¸Ð³Ð½Ð¾Ñ€Ð¸Ñ€ÑƒÐµÑ‚ Ð±Ð¾Ñ‚Ð¾Ð² Ð½Ð° ÑÐµÑ€Ð²ÐµÑ€Ðµ.";
      } else if (module instanceof AutoCrystal) {
         return "ÐÐ²Ñ‚Ð¾Ð¼Ð°Ñ‚Ð¸Ð·Ð¸Ñ€ÑƒÐµÑ‚ Ñ€Ð°Ð·Ð¼ÐµÑ‰ÐµÐ½Ð¸Ðµ Ð¸ ÑƒÐ½Ð¸Ñ‡Ñ‚Ð¾Ð¶ÐµÐ½Ð¸Ðµ ÐºÑ€Ð¸ÑÑ‚Ð°Ð»Ð»Ð¾Ð².";
      } else if (module instanceof AutoSprint) {
         return "ÐÐ²Ñ‚Ð¾Ð¼Ð°Ñ‚Ð¸Ñ‡ÐµÑÐºÐ¸ Ð²ÐºÐ»ÑŽÑ‡Ð°ÐµÑ‚ ÑÐ¿Ñ€Ð¸Ð½Ñ‚ Ð¿Ñ€Ð¸ Ð´Ð²Ð¸Ð¶ÐµÐ½Ð¸Ð¸.";
      } else if (module instanceof NoPush) {
         return "ÐŸÑ€ÐµÐ´Ð¾Ñ‚Ð²Ñ€Ð°Ñ‰Ð°ÐµÑ‚ Ð¾Ñ‚Ñ‚Ð°Ð»ÐºÐ¸Ð²Ð°Ð½Ð¸Ðµ Ð¸Ð³Ñ€Ð¾ÐºÐ¾Ð² Ð¸ ÑÑƒÑ‰Ð½Ð¾ÑÑ‚ÐµÐ¹.";
      } else if (module instanceof ElytraHelper) {
         return "Ð£Ð»ÑƒÑ‡ÑˆÐ°ÐµÑ‚ ÑƒÐ¿Ñ€Ð°Ð²Ð»ÐµÐ½Ð¸Ðµ ÑÐ»Ð¸Ñ‚Ñ€Ð°Ð¼Ð¸.";
      } else if (module instanceof JoinerHelper) {
         return "ÐžÐ±Ð»ÐµÐ³Ñ‡Ð°ÐµÑ‚ Ð¿Ñ€Ð¾Ñ†ÐµÑÑ Ð²Ñ…Ð¾Ð´Ð° Ð½Ð° ÑÐµÑ€Ð²ÐµÑ€.";
      } else if (module instanceof NoDelay) {
         return "Ð£Ð±Ð¸Ñ€Ð°ÐµÑ‚ Ð·Ð°Ð´ÐµÑ€Ð¶ÐºÐ¸ Ð¿Ñ€Ð¸ Ð²Ñ‹Ð¿Ð¾Ð»Ð½ÐµÐ½Ð¸Ð¸ Ð´ÐµÐ¹ÑÑ‚Ð²Ð¸Ð¹.";
      } else if (module instanceof Velocity) {
         return "Ð£Ð¼ÐµÐ½ÑŒÑˆÐ°ÐµÑ‚ Ð¾Ñ‚Ð±Ñ€Ð°ÑÑ‹Ð²Ð°Ð½Ð¸Ðµ Ð¾Ñ‚ Ð°Ñ‚Ð°Ðº.";
      } else if (module instanceof AutoRespawn) {
         return "ÐÐ²Ñ‚Ð¾Ð¼Ð°Ñ‚Ð¸Ñ‡ÐµÑÐºÐ¸ Ð²Ð¾Ð·Ñ€Ð¾Ð¶Ð´Ð°ÐµÑ‚ Ð¸Ð³Ñ€Ð¾ÐºÐ° Ð¿Ð¾ÑÐ»Ðµ ÑÐ¼ÐµÑ€Ñ‚Ð¸.";
      } else if (module instanceof NoSlow) {
         return "Ð£ÑÑ‚Ñ€Ð°Ð½ÑÐµÑ‚ Ð·Ð°Ð¼ÐµÐ´Ð»ÐµÐ½Ð¸Ðµ Ð¿Ñ€Ð¸ Ð¾Ð¿Ñ€ÐµÐ´ÐµÐ»ÐµÐ½Ð½Ñ‹Ñ… Ð´ÐµÐ¹ÑÑ‚Ð²Ð¸ÑÑ….";
      } else if (module instanceof InventoryMove) {
         return "ÐŸÐ¾Ð·Ð²Ð¾Ð»ÑÐµÑ‚ Ð´Ð²Ð¸Ð³Ð°Ñ‚ÑŒÑÑ Ð¿Ñ€Ð¸ Ð¾Ñ‚ÐºÑ€Ñ‹Ñ‚Ð¾Ð¼ Ð¸Ð½Ñ‚ÐµÑ€Ñ„ÐµÐ¹ÑÐµ.";
      } else if (module instanceof Blink) {
         return "Ð¡Ð¾Ð·Ð´Ð°ÐµÑ‚ Ð¸Ð»Ð»ÑŽÐ·Ð¸ÑŽ Ñ‚ÐµÐ»ÐµÐ¿Ð¾Ñ€Ñ‚Ð°Ñ†Ð¸Ð¸ Ð´Ð»Ñ Ð´Ñ€ÑƒÐ³Ð¸Ñ… Ð¸Ð³Ñ€Ð¾ÐºÐ¾Ð².";
      } else if (module instanceof AutoTool) {
         return "ÐÐ²Ñ‚Ð¾Ð¼Ð°Ñ‚Ð¸Ñ‡ÐµÑÐºÐ¸ Ð²Ñ‹Ð±Ð¸Ñ€Ð°ÐµÑ‚ Ð¿Ð¾Ð´Ñ…Ð¾Ð´ÑÑ‰Ð¸Ð¹ Ð¸Ð½ÑÑ‚Ñ€ÑƒÐ¼ÐµÐ½Ñ‚.";
      } else if (module instanceof Fly) {
         return "ÐŸÐ¾Ð·Ð²Ð¾Ð»ÑÐµÑ‚ Ð»ÐµÑ‚Ð°Ñ‚ÑŒ Ð² Ñ€ÐµÐ¶Ð¸Ð¼Ðµ Ð²Ñ‹Ð¶Ð¸Ð²Ð°Ð½Ð¸Ñ.";
      } else if (module instanceof FastBreak) {
         return "Ð£ÑÐºÐ¾Ñ€ÑÐµÑ‚ Ñ€Ð°Ð·Ñ€ÑƒÑˆÐµÐ½Ð¸Ðµ Ð±Ð»Ð¾ÐºÐ¾Ð².";
      } else if (module instanceof CameraSettings) {
         return "ÐÐ°ÑÑ‚Ñ€Ð°Ð¸Ð²Ð°ÐµÑ‚ Ð¿Ð¾Ð²ÐµÐ´ÐµÐ½Ð¸Ðµ ÐºÐ°Ð¼ÐµÑ€Ñ‹ Ð¸Ð³Ñ€Ð¾ÐºÐ°.";
      } else if (module instanceof BlockOverlay) {
         return "ÐŸÐ¾Ð´ÑÐ²ÐµÑ‡Ð¸Ð²Ð°ÐµÑ‚ Ð±Ð»Ð¾ÐºÐ¸ Ð´Ð»Ñ Ð»ÑƒÑ‡ÑˆÐµÐ¹ Ð²Ð¸Ð´Ð¸Ð¼Ð¾ÑÑ‚Ð¸.";
      } else if (module instanceof Esp) {
         return "ÐŸÐ¾ÐºÐ°Ð·Ñ‹Ð²Ð°ÐµÑ‚ Ð¼ÐµÑÑ‚Ð¾Ð¿Ð¾Ð»Ð¾Ð¶ÐµÐ½Ð¸Ðµ ÑÑƒÑ‰Ð½Ð¾ÑÑ‚ÐµÐ¹ Ñ‡ÐµÑ€ÐµÐ· ÑÑ‚ÐµÐ½Ñ‹.";
      } else if (module instanceof AutoTotem) {
         return "ÐÐ²Ñ‚Ð¾Ð¼Ð°Ñ‚Ð¸Ñ‡ÐµÑÐºÐ¸ Ð¸ÑÐ¿Ð¾Ð»ÑŒÐ·ÑƒÐµÑ‚ Ñ‚Ð¾Ñ‚ÐµÐ¼Ñ‹ Ð±ÐµÑÑÐ¼ÐµÑ€Ñ‚Ð¸Ñ.";
      } else if (module instanceof EnderChestPlus) {
         return "Ð£Ð»ÑƒÑ‡ÑˆÐ°ÐµÑ‚ Ñ„ÑƒÐ½ÐºÑ†Ð¸Ð¾Ð½Ð°Ð» ÑÐ½Ð´ÐµÑ€-ÑÑƒÐ½Ð´ÑƒÐºÐ°.";
      } else if (module instanceof FreeCam) {
         return "ÐŸÑ€ÐµÐ´Ð¾ÑÑ‚Ð°Ð²Ð»ÑÐµÑ‚ Ð¸Ð½ÑÑ‚Ñ€ÑƒÐ¼ÐµÐ½Ñ‚Ñ‹ Ð´Ð»Ñ Ð¾Ñ‚Ð»Ð°Ð´ÐºÐ¸ ÐºÐ°Ð¼ÐµÑ€Ñ‹.";
      } else if (module instanceof ChestStealer) {
         return "Ð‘Ñ‹ÑÑ‚Ñ€Ð¾ Ð·Ð°Ð±Ð¸Ñ€Ð°ÐµÑ‚ Ð¿Ñ€ÐµÐ´Ð¼ÐµÑ‚Ñ‹ Ð¸Ð· ÐºÐ¾Ð½Ñ‚ÐµÐ¹Ð½ÐµÑ€Ð¾Ð².";
      } else if (module instanceof AutoTpAccept) {
         return "ÐÐ²Ñ‚Ð¾Ð¼Ð°Ñ‚Ð¸Ñ‡ÐµÑÐºÐ¸ Ð¿Ñ€Ð¸Ð½Ð¸Ð¼Ð°ÐµÑ‚ Ð·Ð°Ð¿Ñ€Ð¾ÑÑ‹ Ð½Ð° Ñ‚ÐµÐ»ÐµÐ¿Ð¾Ñ€Ñ‚Ð°Ñ†Ð¸ÑŽ.";
      } else if (module instanceof Arrows) {
         return "Ð£Ð»ÑƒÑ‡ÑˆÐ°ÐµÑ‚ Ð¼ÐµÑ…Ð°Ð½Ð¸ÐºÑƒ ÑÑ‚Ñ€ÐµÐ»ÑŒÐ±Ñ‹ Ð¸Ð· Ð»ÑƒÐºÐ°.";
      } else if (module instanceof AutoLeave) {
         return "ÐÐ²Ñ‚Ð¾Ð¼Ð°Ñ‚Ð¸Ñ‡ÐµÑÐºÐ¸ Ð¿Ð¾ÐºÐ¸Ð´Ð°ÐµÑ‚ ÑÐµÑ€Ð²ÐµÑ€ Ð¿Ñ€Ð¸ ÑƒÐ³Ñ€Ð¾Ð·Ðµ.";
      } else if (module instanceof WorldTweaks) {
         return "ÐÐ°ÑÑ‚Ñ€Ð°Ð¸Ð²Ð°ÐµÑ‚ Ð¿Ð°Ñ€Ð°Ð¼ÐµÑ‚Ñ€Ñ‹ Ð¼Ð¸Ñ€Ð° Ð´Ð»Ñ ÑƒÐ´Ð¾Ð±ÑÑ‚Ð²Ð°.";
      } else if (module instanceof NoClip) {
         return "ÐŸÐ¾Ð·Ð²Ð¾Ð»ÑÐµÑ‚ Ð¿Ñ€Ð¾Ñ…Ð¾Ð´Ð¸Ñ‚ÑŒ ÑÐºÐ²Ð¾Ð·ÑŒ Ð±Ð»Ð¾ÐºÐ¸.";
      } else if (module instanceof NoRender) {
         return "ÐžÑ‚ÐºÐ»ÑŽÑ‡Ð°ÐµÑ‚ Ñ€ÐµÐ½Ð´ÐµÑ€Ð¸Ð½Ð³ Ð¾Ð¿Ñ€ÐµÐ´ÐµÐ»ÐµÐ½Ð½Ñ‹Ñ… Ð¾Ð±ÑŠÐµÐºÑ‚Ð¾Ð².";
      } else if (module instanceof TargetPearl) {
         return "ÐÐ²Ñ‚Ð¾Ð¼Ð°Ñ‚Ð¸Ñ‡ÐµÑÐºÐ¸ Ð±Ñ€Ð¾ÑÐ°ÐµÑ‚ Ð¶ÐµÐ¼Ñ‡ÑƒÐ¶Ð¸Ð½Ñ‹ Ð² Ñ†ÐµÐ»ÑŒ.";
      } else if (module instanceof NameProtect) {
         return "Ð¡ÐºÑ€Ñ‹Ð²Ð°ÐµÑ‚ Ð¸Ð¼ÐµÐ½Ð° Ð¸Ð³Ñ€Ð¾ÐºÐ¾Ð² Ð´Ð»Ñ Ð·Ð°Ñ‰Ð¸Ñ‚Ñ‹.";
      } else if (module instanceof SeeInvisible) {
         return "ÐŸÐ¾Ð·Ð²Ð¾Ð»ÑÐµÑ‚ Ð²Ð¸Ð´ÐµÑ‚ÑŒ Ð½ÐµÐ²Ð¸Ð´Ð¸Ð¼Ñ‹Ñ… Ð¸Ð³Ñ€Ð¾ÐºÐ¾Ð².";
      } else if (module instanceof AutoArmor) {
         return "ÐÐ²Ñ‚Ð¾Ð¼Ð°Ñ‚Ð¸Ñ‡ÐµÑÐºÐ¸ Ð½Ð°Ð´ÐµÐ²Ð°ÐµÑ‚ Ð»ÑƒÑ‡ÑˆÑƒÑŽ Ð±Ñ€Ð¾Ð½ÑŽ.";
      } else if (module instanceof AutoUse) {
         return "ÐÐ²Ñ‚Ð¾Ð¼Ð°Ñ‚Ð¸Ñ‡ÐµÑÐºÐ¸ Ð¸ÑÐ¿Ð¾Ð»ÑŒÐ·ÑƒÐµÑ‚ Ð¿Ñ€ÐµÐ´Ð¼ÐµÑ‚Ñ‹.";
      } else if (module instanceof NoInteract) {
         return "Ð‘Ð»Ð¾ÐºÐ¸Ñ€ÑƒÐµÑ‚ Ð²Ð·Ð°Ð¸Ð¼Ð¾Ð´ÐµÐ¹ÑÑ‚Ð²Ð¸Ðµ Ñ Ð¾Ð±ÑŠÐµÐºÑ‚Ð°Ð¼Ð¸.";
      } else if (module instanceof CrossHair) {
         return "ÐÐ°ÑÑ‚Ñ€Ð°Ð¸Ð²Ð°ÐµÑ‚ Ð¾Ñ‚Ð¾Ð±Ñ€Ð°Ð¶ÐµÐ½Ð¸Ðµ Ð¿Ñ€Ð¸Ñ†ÐµÐ»Ð°.";
      } else if (module instanceof SuperFireWork) {
         return "Ð£ÑÐ¸Ð»Ð¸Ð²Ð°ÐµÑ‚ Ñ„ÐµÐ¹ÐµÑ€Ð²ÐµÑ€ÐºÐ¸ Ð´Ð»Ñ Ð¿Ð¾Ð»ÐµÑ‚Ð°.";
      } else if (module instanceof Spider) {
         return "ÐŸÐ¾Ð·Ð²Ð¾Ð»ÑÐµÑ‚ Ð²Ð·Ð±Ð¸Ñ€Ð°Ñ‚ÑŒÑÑ Ð¿Ð¾ ÑÑ‚ÐµÐ½Ð°Ð¼ ÐºÐ°Ðº Ð¿Ð°ÑƒÐº.";
      } else if (module instanceof ServerRPSpoofer) {
         return "ÐŸÐ¾Ð´Ð´ÐµÐ»Ñ‹Ð²Ð°ÐµÑ‚ Ð´Ð°Ð½Ð½Ñ‹Ðµ Ð´Ð»Ñ ÑÐµÑ€Ð²ÐµÑ€Ð¾Ð².";
      } else if (module instanceof LongJump) {
         return "Ð”Ð»Ð¸Ð½Ð½Ñ‹Ðµ Ð¿Ñ€Ñ‹Ð¶ÐºÐ¸, ÑÐºÐ²Ð¸Ð²Ð°Ð»ÐµÐ½Ñ‚ HighJump.";
      } else if (module instanceof ShiftTap) {
         return "ÐÐ²Ñ‚Ð¾Ð¼Ð°Ñ‚Ð¸Ñ‡ÐµÑÐºÐ¸ Ð¿Ñ€Ð¸ÑÐµÐ´Ð°ÐµÑ‚ Ð¿Ñ€Ð¸ ÑƒÐ´Ð°Ñ€Ðµ.";
      } else if (module instanceof AspectRatio) {
         return "Ð˜Ð·Ð¼ÐµÐ½ÑÐµÑ‚ ÑÐ¾Ð¾Ñ‚Ð½Ð¾ÑˆÐµÐ½Ð¸Ðµ ÑÑ‚Ð¾Ñ€Ð¾Ð½ ÑÐºÑ€Ð°Ð½Ð°.";
      } else if (module instanceof FreeLook) {
         return "ÐŸÐ¾Ð·Ð²Ð¾Ð»ÑÐµÑ‚ ÑÐ²Ð¾Ð±Ð¾Ð´Ð½Ð¾ Ð²Ñ€Ð°Ñ‰Ð°Ñ‚ÑŒ ÐºÐ°Ð¼ÐµÑ€Ñƒ Ð±ÐµÐ· Ð´Ð²Ð¸Ð¶ÐµÐ½Ð¸Ñ Ð¸Ð³Ñ€Ð¾ÐºÐ°.";
      } else if (module instanceof ClickPearl) {
         return "ÐÐ²Ñ‚Ð¾Ð¼Ð°Ñ‚Ð¸Ñ‡ÐµÑÐºÐ¸ Ð¸ÑÐ¿Ð¾Ð»ÑŒÐ·ÑƒÐµÑ‚ Ð¶ÐµÐ¼Ñ‡ÑƒÐ¶Ð¸Ð½Ñ‹ Ð¿Ñ€Ð¸ ÐºÐ»Ð¸ÐºÐµ.";
      } else if (module instanceof ClickFriend) {
         return "Ð”Ð¾Ð±Ð°Ð²Ð»ÑÐµÑ‚ Ð¸Ð³Ñ€Ð¾ÐºÐ¾Ð² Ð² ÑÐ¿Ð¸ÑÐ¾Ðº Ð´Ñ€ÑƒÐ·ÐµÐ¹ Ð¿Ð¾ ÐºÐ»Ð¸ÐºÑƒ.";
      } else if (module instanceof WindJump) {
         return "Ð£ÑÐ¸Ð»Ð¸Ð²Ð°ÐµÑ‚ Ð¿Ñ€Ñ‹Ð¶ÐºÐ¸ Ñ ÑƒÑ‡ÐµÑ‚Ð¾Ð¼ Ð½Ð°Ð¿Ñ€Ð°Ð²Ð»ÐµÐ½Ð¸Ñ Ð²ÐµÑ‚Ñ€Ð°.";
      } else if (module instanceof TargetESP) {
         return "ÐŸÐ¾Ð´ÑÐ²ÐµÑ‡Ð¸Ð²Ð°ÐµÑ‚ Ñ†ÐµÐ»Ð¸ Ð´Ð»Ñ Ð»ÑƒÑ‡ÑˆÐµÐ¹ Ð²Ð¸Ð´Ð¸Ð¼Ð¾ÑÑ‚Ð¸.";
      } else if (module instanceof NoWeb) {
         return "ÐŸÐ¾Ð·Ð²Ð¾Ð»ÑÐµÑ‚ Ð´Ð²Ð¸Ð³Ð°Ñ‚ÑŒÑÑ Ñ‡ÐµÑ€ÐµÐ· Ð¿Ð°ÑƒÑ‚Ð¸Ð½Ñƒ Ð±ÐµÐ· Ð·Ð°Ð¼ÐµÐ´Ð»ÐµÐ½Ð¸Ñ.";
      } else if (module instanceof IRC) {
         return "Ð’ÑÑ‚Ñ€Ð¾ÐµÐ½Ð½Ñ‹Ð¹ Ñ‡Ð°Ñ‚ Ð´Ð»Ñ Ð¾Ð±Ñ‰ÐµÐ½Ð¸Ñ.";
      } else if (module instanceof Speed) {
         return "Ð£Ð²ÐµÐ»Ð¸Ñ‡Ð¸Ð²Ð°ÐµÑ‚ ÑÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Ð¿ÐµÑ€ÐµÐ´Ð²Ð¸Ð¶ÐµÐ½Ð¸Ñ Ð¸Ð³Ñ€Ð¾ÐºÐ°.";
      } else if (module instanceof SwingAnimation) {
         return "ÐÐ°ÑÑ‚Ñ€Ð°Ð¸Ð²Ð°ÐµÑ‚ Ð°Ð½Ð¸Ð¼Ð°Ñ†Ð¸ÑŽ Ð²Ð·Ð¼Ð°Ñ…Ð° Ñ€ÑƒÐºÐ¸.";
      } else if (module instanceof ViewModel) {
         return "Ð˜Ð·Ð¼ÐµÐ½ÑÐµÑ‚ Ð¾Ñ‚Ð¾Ð±Ñ€Ð°Ð¶ÐµÐ½Ð¸Ðµ Ð¼Ð¾Ð´ÐµÐ»Ð¸ Ð¿Ñ€ÐµÐ´Ð¼ÐµÑ‚Ð¾Ð² Ð² Ñ€ÑƒÐºÐµ.";
      } else if (module instanceof AirStuck) {
         return "Ð¤Ñ€Ð¸Ð·Ð¸Ñ‚ Ð¸Ð³Ñ€Ð¾ÐºÐ° Ð² Ð²Ð¾Ð·Ð´ÑƒÑ…Ðµ, Ð¿Ñ€ÐµÐ´Ð¾Ñ‚Ð²Ñ€Ð°Ñ‰Ð°Ñ Ð´Ð²Ð¸Ð¶ÐµÐ½Ð¸Ðµ.";
      } else if (module instanceof NoFallDamage) {
         return "ÐŸÑ€ÐµÐ´Ð¾Ñ‚Ð²Ñ€Ð°Ñ‰Ð°ÐµÑ‚ Ð¿Ð¾Ð»ÑƒÑ‡ÐµÐ½Ð¸Ðµ ÑƒÑ€Ð¾Ð½Ð° Ð¾Ñ‚ Ð¿Ð°Ð´ÐµÐ½Ð¸Ñ.";
      } else if (module instanceof ElytraMotion) {
         return "Ð£Ð»ÑƒÑ‡ÑˆÐ°ÐµÑ‚ ÑƒÐ¿Ñ€Ð°Ð²Ð»ÐµÐ½Ð¸Ðµ Ð¸ ÑÐºÐ¾Ñ€Ð¾ÑÑ‚ÑŒ Ð¿Ð¾Ð»ÐµÑ‚Ð° Ð½Ð° ÑÐ»Ð¸Ñ‚Ñ€Ð°Ñ….";
      } else if (module instanceof WorldParticles) {
         return "Ð”Ð¾Ð±Ð°Ð²Ð»ÑÐµÑ‚ Ð¸Ð»Ð¸ Ð¸Ð·Ð¼ÐµÐ½ÑÐµÑ‚ Ñ‡Ð°ÑÑ‚Ð¸Ñ†Ñ‹ Ð² Ð¼Ð¸Ñ€Ðµ Ð´Ð»Ñ Ð²Ð¸Ð·ÑƒÐ°Ð»ÑŒÐ½Ð¾Ð³Ð¾ ÑÑ„Ñ„ÐµÐºÑ‚Ð°.";
      } else if (module instanceof BlockESP) {
         return "ÐŸÐ¾Ð´ÑÐ²ÐµÑ‡Ð¸Ð²Ð°ÐµÑ‚ Ð¾Ð¿Ñ€ÐµÐ´ÐµÐ»ÐµÐ½Ð½Ñ‹Ðµ Ð±Ð»Ð¾ÐºÐ¸ Ñ‡ÐµÑ€ÐµÐ· ÑÑ‚ÐµÐ½Ñ‹.";
      } else {
         return module instanceof KillEffect ? "Ð”Ð¾Ð±Ð°Ð²Ð»ÑÐµÑ‚ Ð²Ð¸Ð·ÑƒÐ°Ð»ÑŒÐ½Ñ‹Ðµ ÑÑ„Ñ„ÐµÐºÑ‚Ñ‹ Ð¿Ñ€Ð¸ ÑƒÐ±Ð¸Ð¹ÑÑ‚Ð²Ðµ." : "ÐžÐ¿Ð¸ÑÐ°Ð½Ð¸Ðµ Ð¼Ð¾Ð´ÑƒÐ»Ñ Ð¾Ñ‚ÑÑƒÑ‚ÑÑ‚Ð²ÑƒÐµÑ‚.";
      }
   }
}

