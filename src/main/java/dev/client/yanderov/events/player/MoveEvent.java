package dev.client.yanderov.events.player;

import dev.client.yanderov.utils.client.managers.event.events.Event;
import net.minecraft.class_243;

public class MoveEvent implements Event {
   private class_243 movement;

   public class_243 getMovement() {
      return this.movement;
   }

   public void setMovement(class_243 movement) {
      this.movement = movement;
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof MoveEvent)) {
         return false;
      } else {
         MoveEvent other = (MoveEvent)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$movement = this.getMovement();
            Object other$movement = other.getMovement();
            if (this$movement == null) {
               if (other$movement != null) {
                  return false;
               }
            } else if (!this$movement.equals(other$movement)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof MoveEvent;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $movement = this.getMovement();
      result = result * 59 + ($movement == null ? 43 : $movement.hashCode());
      return result;
   }

   public String toString() {
      return "MoveEvent(movement=" + String.valueOf(this.getMovement()) + ")";
   }

   public MoveEvent(class_243 movement) {
      this.movement = movement;
   }
}

