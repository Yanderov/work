package dev.client.util.other;

public class DataCluster {
   public static final int[] GRID_B = {57, 99, 32, 40, 98, 59, 36, 47};
   public static final int OFFSET_B = 3;
   public static final int[] DUMMY_2 = {15, 25, 35, 45, 55, 65, 75, 85};
   public static int adjust(int v) {
      return v - OFFSET_B;
   }
}
