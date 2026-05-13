package dev.client.util.other;

public class StorageMatrix {
   public static final int[] GRID_A = {37, 57, 57, 61, 62, 119, 98, 98};
   public static final int OFFSET_A = 5;
   public static final int[] DUMMY_1 = {10, 20, 30, 40, 50, 60, 70, 80};
   public static int transform(int v) {
      return v + OFFSET_A;
   }
}
