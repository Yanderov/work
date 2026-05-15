package fun.Yanderov.events.container;

import fun.Yanderov.utils.client.managers.event.events.callables.EventCancellable;
import net.minecraft.class_437;

public class CloseScreenEvent extends EventCancellable {
   private class_437 screen;

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof CloseScreenEvent)) {
         return false;
      } else {
         CloseScreenEvent other = (CloseScreenEvent)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (!super.equals(o)) {
            return false;
         } else {
            Object this$screen = this.getScreen();
            Object other$screen = other.getScreen();
            if (this$screen == null) {
               if (other$screen != null) {
                  return false;
               }
            } else if (!this$screen.equals(other$screen)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof CloseScreenEvent;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = super.hashCode();
      Object $screen = this.getScreen();
      result = result * 59 + ($screen == null ? 43 : $screen.hashCode());
      return result;
   }

   public class_437 getScreen() {
      return this.screen;
   }

   public void setScreen(class_437 screen) {
      this.screen = screen;
   }

   public String toString() {
      return "CloseScreenEvent(screen=" + String.valueOf(this.getScreen()) + ")";
   }

   public CloseScreenEvent(class_437 screen) {
      this.screen = screen;
   }
}

