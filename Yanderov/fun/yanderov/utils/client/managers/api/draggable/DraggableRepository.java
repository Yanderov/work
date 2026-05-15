package fun.Yanderov.utils.client.managers.api.draggable;

import fun.Yanderov.display.hud.Binds;
import fun.Yanderov.display.hud.CoolDowns;
import fun.Yanderov.display.hud.HotKeys;
import fun.Yanderov.display.hud.Inventory;
import fun.Yanderov.display.hud.Notifications;
import fun.Yanderov.display.hud.PlayerInfo;
import fun.Yanderov.display.hud.Potions;
import fun.Yanderov.display.hud.StaffList;
import fun.Yanderov.display.hud.TargetHud;
import fun.Yanderov.display.hud.Watermark;
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

