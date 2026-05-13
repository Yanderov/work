package dev.client.util.math;

import dev.client.util.other.StorageMatrix;
import dev.client.util.other.DataCluster;
import dev.client.util.other.ValueCache;

public class DecryptTest {
   public static void main(String[] args) {
      int seed = 77;
      
      int[] a = StorageMatrix.GRID_A;
      int[] b = DataCluster.GRID_B;
      int[] c = ValueCache.GRID_C;
      
      StringBuilder result = new StringBuilder();
      int globalIndex = 0;
      
      for (int i = 0; i < a.length; i++) {
         int val = a[i];
         int decrypted = (val + 5 - globalIndex) ^ seed;
         result.append((char)decrypted);
         globalIndex++;
      }
      
      for (int i = 0; i < b.length; i++) {
         int val = b[i];
         int decrypted = (val - 3 + globalIndex) ^ seed;
         result.append((char)decrypted);
         globalIndex++;
      }
      
      for (int i = 0; i < c.length; i++) {
         int val = c[i];
         int decrypted = (val + 7 - (globalIndex * 2)) ^ seed;
         result.append((char)decrypted);
         globalIndex++;
      }
      
      System.out.println("Decrypted: " + result.toString());
   }
}
