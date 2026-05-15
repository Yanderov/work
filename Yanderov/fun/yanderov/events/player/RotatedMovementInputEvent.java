package fun.Yanderov.events.player;

import fun.Yanderov.utils.client.managers.event.events.Event;

public class RotatedMovementInputEvent implements Event {
   private float forward;
   private float sideways;

   public RotatedMovementInputEvent(float forward, float sideways) {
      this.forward = forward;
      this.sideways = sideways;
   }

   public float getForward() {
      return this.forward;
   }

   public float getSideways() {
      return this.sideways;
   }

   public void setForward(float forward) {
      this.forward = forward;
   }

   public void setSideways(float sideways) {
      this.sideways = sideways;
   }

   public String toString() {
      float var10000 = this.getForward();
      return "RotatedMovementInputEvent(forward=" + var10000 + ", sideways=" + this.getSideways() + ")";
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof RotatedMovementInputEvent)) {
         return false;
      } else {
         RotatedMovementInputEvent other = (RotatedMovementInputEvent)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (Float.compare(this.getForward(), other.getForward()) != 0) {
            return false;
         } else {
            return Float.compare(this.getSideways(), other.getSideways()) == 0;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof RotatedMovementInputEvent;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + Float.floatToIntBits(this.getForward());
      result = result * 59 + Float.floatToIntBits(this.getSideways());
      return result;
   }
}

