package oshi.hardware.platform.mac;

import com.sun.jna.Pointer;
import com.sun.jna.platform.mac.CoreFoundation;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import oshi.annotation.concurrent.ThreadSafe;
import oshi.driver.mac.net.NetStat;
import oshi.hardware.NetworkIF;
import oshi.hardware.common.AbstractNetworkIF;
import oshi.jna.platform.mac.SystemConfiguration;

@ThreadSafe
public final class MacNetworkIF extends AbstractNetworkIF {
   private static final Logger LOG = LoggerFactory.getLogger(MacNetworkIF.class);
   private int ifType;
   private long bytesRecv;
   private long bytesSent;
   private long packetsRecv;
   private long packetsSent;
   private long inErrors;
   private long outErrors;
   private long inDrops;
   private long collisions;
   private long speed;
   private long timeStamp;

   public MacNetworkIF(NetworkInterface netint, Map data) throws InstantiationException {
      super(netint, queryIfDisplayName(netint));
      this.updateNetworkStats(data);
   }

   private static String queryIfDisplayName(NetworkInterface netint) {
      String name = netint.getName();
      CoreFoundation.CFArrayRef ifArray = SystemConfiguration.INSTANCE.SCNetworkInterfaceCopyAll();
      if (ifArray != null) {
         try {
            int count = ifArray.getCount();

            for(int i = 0; i < count; ++i) {
               Pointer pNetIf = ifArray.getValueAtIndex(i);
               SystemConfiguration.SCNetworkInterfaceRef scNetIf = new SystemConfiguration.SCNetworkInterfaceRef(pNetIf);
               CoreFoundation.CFStringRef cfName = SystemConfiguration.INSTANCE.SCNetworkInterfaceGetBSDName(scNetIf);
               if (cfName != null && name.equals(cfName.stringValue())) {
                  CoreFoundation.CFStringRef cfDisplayName = SystemConfiguration.INSTANCE.SCNetworkInterfaceGetLocalizedDisplayName(scNetIf);
                  String var9 = cfDisplayName.stringValue();
                  return var9;
               }
            }

            return name;
         } finally {
            ifArray.release();
         }
      } else {
         return name;
      }
   }

   public static List getNetworks(boolean includeLocalInterfaces) {
      Map<Integer, NetStat.IFdata> data = NetStat.queryIFdata(-1);
      List<NetworkIF> ifList = new ArrayList();

      for(NetworkInterface ni : getNetworkInterfaces(includeLocalInterfaces)) {
         try {
            ifList.add(new MacNetworkIF(ni, data));
         } catch (InstantiationException e) {
            LOG.debug("Network Interface Instantiation failed: {}", e.getMessage());
         }
      }

      return ifList;
   }

   public int getIfType() {
      return this.ifType;
   }

   public long getBytesRecv() {
      return this.bytesRecv;
   }

   public long getBytesSent() {
      return this.bytesSent;
   }

   public long getPacketsRecv() {
      return this.packetsRecv;
   }

   public long getPacketsSent() {
      return this.packetsSent;
   }

   public long getInErrors() {
      return this.inErrors;
   }

   public long getOutErrors() {
      return this.outErrors;
   }

   public long getInDrops() {
      return this.inDrops;
   }

   public long getCollisions() {
      return this.collisions;
   }

   public long getSpeed() {
      return this.speed;
   }

   public long getTimeStamp() {
      return this.timeStamp;
   }

   public boolean updateAttributes() {
      int index = this.queryNetworkInterface().getIndex();
      return this.updateNetworkStats(NetStat.queryIFdata(index));
   }

   private boolean updateNetworkStats(Map data) {
      int index = this.queryNetworkInterface().getIndex();
      if (data.containsKey(index)) {
         NetStat.IFdata ifData = (NetStat.IFdata)data.get(index);
         this.ifType = ifData.getIfType();
         this.bytesSent = ifData.getOBytes();
         this.bytesRecv = ifData.getIBytes();
         this.packetsSent = ifData.getOPackets();
         this.packetsRecv = ifData.getIPackets();
         this.outErrors = ifData.getOErrors();
         this.inErrors = ifData.getIErrors();
         this.collisions = ifData.getCollisions();
         this.inDrops = ifData.getIDrops();
         this.speed = ifData.getSpeed();
         this.timeStamp = ifData.getTimeStamp();
         return true;
      } else {
         return false;
      }
   }
}
