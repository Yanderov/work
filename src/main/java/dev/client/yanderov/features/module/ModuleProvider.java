package dev.client.yanderov.features.module;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class ModuleProvider {
   private final List modules;

   public Module get(String name) {
      return (Module)this.modules.stream().filter((module) -> module.getName().equalsIgnoreCase(name)).map((module) -> module).findFirst().orElse((Object)null);
   }

   public Module get(Class clazz) {
      Stream var10000 = this.modules.stream().filter((module) -> clazz.isAssignableFrom(module.getClass()));
      Objects.requireNonNull(clazz);
      return (Module)var10000.map(clazz::cast).findFirst().orElse((Object)null);
   }

   public List getModules() {
      return this.modules;
   }

   public ModuleProvider(List modules) {
      this.modules = modules;
   }
}

