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
          // COMBAT (по алфавиту)
          new AntiBot(),
          new AntiTarget(),
          new Aura(),
          new AutoApple(),
          new AutoDuel(),
          new AutoExplosion(),
          new AutoPotion(),
          new AutoSwap(),
          new AutoTotem(),
          new BowSpammer(),
          new ElytraAura(),
          new HitBox(),
          new HitSound(),
          new NoFriendDamage(),
          new NoInteract(),
          new ShiftTap(),
          new SyncTps(),
          new TapeMouse(),
          new TriggerBot(),
          new Velocity(),
          
          // MOVEMENT (по алфавиту)
          new AirStuck(),
          new Blink(),
          new ElytraBooster(),
          new ElytraBounce(),
          new ElytraFix(),
          new ElytraHelper(),
          new ElytraMotion(),
          new InventoryMove(),
          new NoJumpDelay(),
          new NoSlow(),
          new NoWeb(),
          new NoPush(),
          new SafeWalk(),
          new Speed(),
          new Sprint(),
          new Strafe(),
          new TargetStrafe(),
          
          // RENDER (по алфавиту)
          new Arrows(),
          new BetterWorld(),
          new BlockEsp(),
          new BlockOutline(),
          new ChinaHat(),
          new Crosshair(),
          new FreeCam(),
          new GlyphLines(),
          new GuiModule(),
          new HitEffect(),
          new HpAlert(),
          new Interface(),
          new ItemEsp(),
          new ItemPhysics(),
          new JumpCircle(),
          new KillEffect(),
          new NameProtect(),
          new NoRender(),
          new Particles(),
          new PlayerEsp(),
          new Prediction(),
          new SeeInvisible(),
          new ShaderHand(),
          new SmoothCamera(),
          new SwingAnimation(),
          new Wings(),
          
          // PLAYER (по алфавиту)
          new AHHelper(),
          new AutoLeave(),
          new AutoRespawn(),
          new AutoTool(),
          new AutoTpAccept(),
          new ChestStealer(),
          new ClanUpgrader(),
          new ClickFriend(),
          new ClickPearl(),
          new ClientSound(),
          new CordDropper(),
          new FakePlayer(),
          new FastBreak(),
          new FastUse(),
          new GodMode(),
          new ItemScroller(),
          new ItemSwapFix(),
          new LockSlot(),
          new NoEntityTrace(),
          new NoItemBreak(),
          new OpenWall(),
          new PearlBlockThrow(),
          new ReallyWorldHelper(),
          new WebTrap(),
          
          // UTIL (по алфавиту)
          new AntiAfk(),
          new BetterChat(),
          new RpSpoof()
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
