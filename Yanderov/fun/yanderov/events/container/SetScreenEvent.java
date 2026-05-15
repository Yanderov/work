package fun.Yanderov.events.container;

import fun.Yanderov.utils.client.managers.event.events.Event;
import net.minecraft.class_437;

public class SetScreenEvent implements Event {
   public class_437 screen;

   public class_437 getScreen() {
      return this.screen;
   }

   public void setScreen(class_437 screen) {
      this.screen = screen;
   }

   public SetScreenEvent(class_437 screen) {
      this.screen = screen;
   }
}

