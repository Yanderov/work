package dev.client.yanderov.utils.math.script;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

public class ScriptManager {
   private final Map scripts = new ConcurrentHashMap();

   public Optional getScript(String name) {
      return this.isNullOrEmpty(name) ? Optional.empty() : Optional.of((Script)this.scripts.computeIfAbsent(name, (x) -> new Script()));
   }

   public Script addScript(String name, Script script) {
      if (!this.isNullOrEmpty(name) && script != null) {
         return (Script)this.scripts.put(name, script);
      } else {
         throw new IllegalArgumentException("Script name or instance cannot be null or empty");
      }
   }

   public boolean containsScript(String name) {
      return !this.isNullOrEmpty(name) && this.scripts.containsKey(name);
   }

   public boolean finished(String name) {
      return !this.isNullOrEmpty(name) && this.getScript(name).isPresent() && ((Script)this.getScript(name).get()).isFinished();
   }

   public void removeScript(String name) {
      if (!this.isNullOrEmpty(name)) {
         this.scripts.remove(name);
      }

   }

   public void cleanupScript(String name) {
      if (!this.isNullOrEmpty(name)) {
         this.scripts.computeIfPresent(name, (k, v) -> {
            v.cleanup();
            return v;
         });
      }

   }

   public void cleanupAll() {
      this.scripts.forEach((k, v) -> v.cleanup());
   }

   public void clearAll() {
      this.scripts.clear();
   }

   public void updateScript(String name) {
      this.updateScript(name, () -> true);
   }

   public void updateScript(String name, Supplier condition) {
      if ((Boolean)condition.get() && !this.isNullOrEmpty(name)) {
         this.scripts.computeIfPresent(name, (k, v) -> {
            v.update();
            return v;
         });
      }

   }

   public void updateAll() {
      this.scripts.values().forEach(Script::update);
   }

   public Set getAllScriptNames() {
      return Collections.unmodifiableSet(this.scripts.keySet());
   }

   public Map getAllScripts() {
      return Collections.unmodifiableMap(this.scripts);
   }

   private boolean isNullOrEmpty(String str) {
      return str == null || str.trim().isEmpty();
   }
}

