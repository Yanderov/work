package oshi.software.common;

import java.util.List;
import oshi.driver.unix.NetStat;
import oshi.software.os.InternetProtocolStats;

public abstract class AbstractInternetProtocolStats implements InternetProtocolStats {
   public InternetProtocolStats.TcpStats getTCPv6Stats() {
      return new InternetProtocolStats.TcpStats(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L);
   }

   public InternetProtocolStats.UdpStats getUDPv6Stats() {
      return new InternetProtocolStats.UdpStats(0L, 0L, 0L, 0L);
   }

   public List getConnections() {
      return NetStat.queryNetstat();
   }
}
