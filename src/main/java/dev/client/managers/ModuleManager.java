package dev.client.managers;

import dev.client.modules.Category;
import dev.client.modules.Module;
import dev.client.modules.impl.combat.*;
import dev.client.modules.impl.movement.*;
import dev.client.modules.impl.player.*;
import dev.client.modules.impl.render.*;
import dev.client.modules.impl.util.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class ModuleManager {
   private final List<Module> modules = new ArrayList<>();
   private final HashMap<Class<? extends Module>, Module> modules2 = new HashMap<>();

   public ModuleManager() {
      this.init();
   }

   private void init() {
      this.addModules(
          new Sprint(), new GuiModule(), new Interface(), new BetterWorld(), new RpSpoof(), 
          new TriggerBot(), new InventoryMove(), new AutoTpAccept(), new SeeInvisible(), 
          new NoRender(), new AutoTool(), new ItemSwapFix(), new ElytraBounce(), 
          new SafeWalk(), new JumpCircle(), new Particles(), new PlayerEsp(), 
          new SwingAnimation(), new Wings(), new NameProtect(), new NoFriendDamage(), 
          new BowSpammer(), new AntiAfk(), new BlockOutline(), new ItemPhysics(), 
          new TapeMouse(), new Prediction(), new FastBreak(), new NoEntityTrace(), 
          new ShiftTap(), new AntiBot(), new Velocity(), new HitBox(), new NoInteract(), 
          new NoWeb(), new AirStuck(), new NoSlow(), new ClickPearl(), new AutoRespawn(), 
          new AutoLeave(), new ChestStealer(), new Crosshair(), new HitEffect(), 
          new ChinaHat(), new KillEffect(), new GlyphLines(), new ShaderHand(), 
          new SmoothCamera(), new HpAlert(), new Arrows(), new ClickFriend(), 
          new Blink(), new ItemScroller(), new HitSound(), new FreeCam(), new NoPush(), 
          new BlockEsp(), new ItemEsp(), new AutoTotem(), new Aura(), new ElytraHelper(), 
          new AutoApple(), new FakePlayer(), new ClientSound(), new NoJumpDelay(), 
          new AutoPotion(), new OpenWall(), new NoItemBreak(), new Speed(), new Strafe(), 
          new ElytraBooster(), new ElytraMotion(), new TargetStrafe(), new CordDropper(), 
          new Parkour(), new NoRotation(), new FastUse(), new AutoSwap(), new AutoDuel(), 
          new AutoExplosion(), new SyncTps(), new BetterChat(), new WebTrap(), 
          new LockSlot(), new ReallyWorldHelper(), new ElytraFix(), new GodMode(), 
          new PearlBlockThrow(), new AHHelper(), new ClanUpgrader(), new AntiTarget()
      );
      this.modules.forEach((m) -> this.modules2.put(m.getClass(), m));
   }

   private void addModules(Module... modules) {
      this.modules.addAll(Arrays.asList(modules));
   }

   public List<Module> getModules() {
      return this.modules;
   }

   public <T extends Module> T getByClass(Class<T> clazz) {
      return clazz.cast(this.modules2.get(clazz));
   }

   public void initDesc() {
      this.modules.forEach(Module::initDesc);
   }

   public List<Module> getByCategory(Category category) {
      return this.modules.stream().filter((module) -> module.getModuleBranding().category() == category).collect(Collectors.toList());
   }

   public List<Module> search(String text) {
      String lower = text.toLowerCase();
      return this.modules.stream().filter((module) -> module.getModuleBranding().name().toLowerCase().contains(lower)).collect(Collectors.toList());
   }
}
