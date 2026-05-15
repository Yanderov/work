package oshi.driver.windows.perfmon;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import oshi.annotation.concurrent.ThreadSafe;
import oshi.util.tuples.Pair;

@ThreadSafe
public final class LoadAverage {
   private static Thread loadAvgThread = null;
   private static double[] loadAverages = new double[]{(double)-1.0F, (double)-1.0F, (double)-1.0F};
   private static final double[] EXP_WEIGHT = new double[]{Math.exp(-0.08333333333333333), Math.exp(-0.016666666666666666), Math.exp(-0.005555555555555556)};

   private LoadAverage() {
   }

   public static double[] queryLoadAverage(int nelem) {
      synchronized(loadAverages) {
         return Arrays.copyOf(loadAverages, nelem);
      }
   }

   public static synchronized void stopDaemon() {
      if (loadAvgThread != null) {
         loadAvgThread.interrupt();
         loadAvgThread = null;
      }

   }

   public static synchronized void startDaemon() {
      if (loadAvgThread == null) {
         loadAvgThread = new Thread("OSHI Load Average daemon") {
            public void run() {
               Pair<Long, Long> nonIdlePair = LoadAverage.queryNonIdleTicks();
               long nonIdleTicks0 = (Long)nonIdlePair.getA();
               long nonIdleBase0 = (Long)nonIdlePair.getB();
               long initNanos = System.nanoTime();

               try {
                  Thread.sleep(2500L);
               } catch (InterruptedException var22) {
                  Thread.currentThread().interrupt();
               }

               while(!Thread.currentThread().isInterrupted()) {
                  nonIdlePair = LoadAverage.queryNonIdleTicks();
                  long nonIdleTicks = (Long)nonIdlePair.getA() - nonIdleTicks0;
                  long nonIdleBase = (Long)nonIdlePair.getB() - nonIdleBase0;
                  double runningProcesses;
                  if (nonIdleBase > 0L && nonIdleTicks > 0L) {
                     runningProcesses = (double)nonIdleTicks / (double)nonIdleBase;
                  } else {
                     runningProcesses = (double)0.0F;
                  }

                  nonIdleTicks0 = (Long)nonIdlePair.getA();
                  nonIdleBase0 = (Long)nonIdlePair.getB();
                  long queueLength = (Long)SystemInformation.queryProcessorQueueLength().getOrDefault(SystemInformation.ProcessorQueueLengthProperty.PROCESSORQUEUELENGTH, 0L);
                  synchronized(LoadAverage.loadAverages) {
                     if (LoadAverage.loadAverages[0] < (double)0.0F) {
                        Arrays.fill(LoadAverage.loadAverages, runningProcesses);
                     }

                     for(int i = 0; i < LoadAverage.loadAverages.length; ++i) {
                        double[] var10000 = LoadAverage.loadAverages;
                        var10000[i] *= LoadAverage.EXP_WEIGHT[i];
                        var10000 = LoadAverage.loadAverages;
                        var10000[i] += (runningProcesses + (double)queueLength) * ((double)1.0F - LoadAverage.EXP_WEIGHT[i]);
                     }
                  }

                  long delay = 5000L - (System.nanoTime() - initNanos) % 5000000000L / 1000000L;
                  if (delay < 500L) {
                     delay += 5000L;
                  }

                  try {
                     Thread.sleep(delay);
                  } catch (InterruptedException var21) {
                     Thread.currentThread().interrupt();
                  }
               }

            }
         };
         loadAvgThread.setDaemon(true);
         loadAvgThread.start();
      }
   }

   private static Pair queryNonIdleTicks() {
      Pair<List<String>, Map<ProcessInformation.IdleProcessorTimeProperty, List<Long>>> idleValues = ProcessInformation.queryIdleProcessCounters();
      List<String> instances = (List)idleValues.getA();
      Map<ProcessInformation.IdleProcessorTimeProperty, List<Long>> valueMap = (Map)idleValues.getB();
      List<Long> proctimeTicks = (List)valueMap.get(ProcessInformation.IdleProcessorTimeProperty.PERCENTPROCESSORTIME);
      List<Long> proctimeBase = (List)valueMap.get(ProcessInformation.IdleProcessorTimeProperty.ELAPSEDTIME);
      long nonIdleTicks = 0L;
      long nonIdleBase = 0L;

      for(int i = 0; i < instances.size(); ++i) {
         if ("_Total".equals(instances.get(i))) {
            nonIdleTicks += (Long)proctimeTicks.get(i);
            nonIdleBase += (Long)proctimeBase.get(i);
         } else if ("Idle".equals(instances.get(i))) {
            nonIdleTicks -= (Long)proctimeTicks.get(i);
         }
      }

      return new Pair(nonIdleTicks, nonIdleBase);
   }
}
