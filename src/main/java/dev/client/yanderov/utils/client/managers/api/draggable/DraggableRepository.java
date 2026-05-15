package dev.client.yanderov.utils.client.managers.api.draggable;

import dev.client.yanderov.display.hud.Binds;
import dev.client.yanderov.display.hud.CoolDowns;
import dev.client.yanderov.display.hud.HotKeys;
import dev.client.yanderov.display.hud.Inventory;
import dev.client.yanderov.display.hud.Notifications;
import dev.client.yanderov.display.hud.PlayerInfo;
import dev.client.yanderov.display.hud.Potions;
import dev.client.yanderov.display.hud.StaffList;
import dev.client.yanderov.display.hud.TargetHud;
import dev.client.yanderov.display.hud.Watermark;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class DraggableRepository {
   private final List draggable = new ArrayList();

   public void setup() {
      this.register(new TargetHud(), new Potions(), new HotKeys(), new Watermark(), new Inventory(), new CoolDowns(), new StaffList(), new Binds(), new Notifications(), new PlayerInfo());
   }

   public void register(AbstractDraggable... module) {
      this.draggable.addAll(List.of(module));
   }

   public List draggable() {
      return this.draggable;
   }

   public AbstractDraggable get(String name) {
      return (AbstractDraggable)this.draggable.stream().filter((module) -> module.getName().equalsIgnoreCase(name)).map((module) -> module).findFirst().orElse((Object)null);
   }

   public AbstractDraggable get(Class clazz) {
      Stream var10000 = this.draggable.stream().filter((module) -> clazz.isAssignableFrom(module.getClass()));
      Objects.requireNonNull(clazz);
      return (AbstractDraggable)var10000.map(clazz::cast).findFirst().orElse((Object)null);
   }
}

