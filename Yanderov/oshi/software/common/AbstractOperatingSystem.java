package oshi.software.common;

import com.sun.jna.Platform;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;
import oshi.util.GlobalConfig;
import oshi.util.Memoizer;
import oshi.util.tuples.Pair;

public abstract class AbstractOperatingSystem implements OperatingSystem {
   protected static final boolean USE_WHO_COMMAND = GlobalConfig.get("oshi.os.unix.whoCommand", false);
   private final Supplier manufacturer = Memoizer.memoize(this::queryManufacturer);
   private final Supplier familyVersionInfo = Memoizer.memoize(this::queryFamilyVersionInfo);
   private final Supplier bitness = Memoizer.memoize(this::queryPlatformBitness);

   public String getManufacturer() {
      return (String)this.manufacturer.get();
   }

   protected abstract String queryManufacturer();

   public String getFamily() {
      return (String)((Pair)this.familyVersionInfo.get()).getA();
   }

   public OperatingSystem.OSVersionInfo getVersionInfo() {
      return (OperatingSystem.OSVersionInfo)((Pair)this.familyVersionInfo.get()).getB();
   }

   protected abstract Pair queryFamilyVersionInfo();

   public int getBitness() {
      return (Integer)this.bitness.get();
   }

   private int queryPlatformBitness() {
      if (Platform.is64Bit()) {
         return 64;
      } else {
         int jvmBitness = System.getProperty("os.arch").contains("64") ? 64 : 32;
         return this.queryBitness(jvmBitness);
      }
   }

   protected abstract int queryBitness(int var1);

   public List getProcesses(Predicate filter, Comparator sort, int limit) {
      return (List)this.queryAllProcesses().stream().filter(filter == null ? OperatingSystem.ProcessFiltering.ALL_PROCESSES : filter).sorted(sort == null ? OperatingSystem.ProcessSorting.NO_SORTING : sort).limit(limit > 0 ? (long)limit : Long.MAX_VALUE).collect(Collectors.toList());
   }

   protected abstract List queryAllProcesses();

   public List getChildProcesses(int parentPid, Predicate filter, Comparator sort, int limit) {
      List<OSProcess> childProcs = this.queryChildProcesses(parentPid);
      OSProcess parent = (OSProcess)childProcs.stream().filter((p) -> p.getProcessID() == parentPid).findAny().orElse((Object)null);
      long parentStartTime = parent == null ? 0L : parent.getStartTime();
      return (List)this.queryChildProcesses(parentPid).stream().filter(filter == null ? OperatingSystem.ProcessFiltering.ALL_PROCESSES : filter).filter((p) -> p.getProcessID() != parentPid && p.getStartTime() >= parentStartTime).sorted(sort == null ? OperatingSystem.ProcessSorting.NO_SORTING : sort).limit(limit > 0 ? (long)limit : Long.MAX_VALUE).collect(Collectors.toList());
   }

   protected abstract List queryChildProcesses(int var1);

   public List getDescendantProcesses(int parentPid, Predicate filter, Comparator sort, int limit) {
      List<OSProcess> descendantProcs = this.queryDescendantProcesses(parentPid);
      OSProcess parent = (OSProcess)descendantProcs.stream().filter((p) -> p.getProcessID() == parentPid).findAny().orElse((Object)null);
      long parentStartTime = parent == null ? 0L : parent.getStartTime();
      return (List)this.queryDescendantProcesses(parentPid).stream().filter(filter == null ? OperatingSystem.ProcessFiltering.ALL_PROCESSES : filter).filter((p) -> p.getProcessID() != parentPid && p.getStartTime() >= parentStartTime).sorted(sort == null ? OperatingSystem.ProcessSorting.NO_SORTING : sort).limit(limit > 0 ? (long)limit : Long.MAX_VALUE).collect(Collectors.toList());
   }

   protected abstract List queryDescendantProcesses(int var1);

   protected static Set getChildrenOrDescendants(Collection allProcs, int parentPid, boolean allDescendants) {
      Map<Integer, Integer> parentPidMap = (Map)allProcs.stream().collect(Collectors.toMap(OSProcess::getProcessID, OSProcess::getParentProcessID));
      return getChildrenOrDescendants(parentPidMap, parentPid, allDescendants);
   }

   protected static Set getChildrenOrDescendants(Map parentPidMap, int parentPid, boolean allDescendants) {
      Set<Integer> descendantPids = new HashSet();
      descendantPids.add(parentPid);
      Queue<Integer> queue = new ArrayDeque();
      queue.add(parentPid);

      do {
         for(int pid : getChildren(parentPidMap, (Integer)queue.poll())) {
            if (!descendantPids.contains(pid)) {
               descendantPids.add(pid);
               queue.add(pid);
            }
         }
      } while(allDescendants && !queue.isEmpty());

      return descendantPids;
   }

   private static Set getChildren(Map parentPidMap, int parentPid) {
      return (Set)parentPidMap.entrySet().stream().filter((e) -> ((Integer)e.getValue()).equals(parentPid) && !((Integer)e.getKey()).equals(parentPid)).map(Map.Entry::getKey).collect(Collectors.toSet());
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append(this.getManufacturer()).append(' ').append(this.getFamily()).append(' ').append(this.getVersionInfo());
      return sb.toString();
   }
}
