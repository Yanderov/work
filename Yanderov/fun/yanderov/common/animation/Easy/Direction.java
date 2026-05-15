package fun.Yanderov.common.animation.Easy;

public enum Direction {
   FORWARDS,
   BACKWARDS;

   public Direction opposite() {
      return this == FORWARDS ? BACKWARDS : FORWARDS;
   }

   // $FF: synthetic method
   private static Direction[] $values() {
      return new Direction[]{FORWARDS, BACKWARDS};
   }
}

