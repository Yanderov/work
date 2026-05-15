package fun.Yanderov.utils.features.autobuy;

import fun.Yanderov.utils.client.chat.ChatMessage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NetworkManager {
   private static final int PORT = 20001;
   private static final int RECONNECT_DELAY = 2000;
   private ServerSocket serverSocket = null;
   private Socket clientSocket = null;
   private PrintWriter clientOut = null;
   private BufferedReader clientIn = null;
   private List connections = new ArrayList();
   private Map outs = new ConcurrentHashMap();
   private Map ins = new ConcurrentHashMap();
   private Map clientInAuction = new ConcurrentHashMap();
   private ExecutorService executorService = Executors.newFixedThreadPool(10);
   private volatile boolean running = false;
   private volatile boolean isClientMode = false;
   private long lastReconnectAttempt = 0L;
   private ConcurrentLinkedQueue queue = new ConcurrentLinkedQueue();
   private ConcurrentLinkedQueue priorityQueue = new ConcurrentLinkedQueue();

   public void start(String mode) {
      this.running = true;
      this.isClientMode = mode.equals("ÐŸÑ€Ð¾Ð²ÐµÑ€ÑÑŽÑ‰Ð¸Ð¹");
      this.executorService.execute(() -> this.connectionLoop(mode));
   }

   public void stop() {
      this.running = false;
      this.executorService.shutdownNow();
      this.executorService = Executors.newFixedThreadPool(10);
      this.stopAll();
   }

   private void connectionLoop(String mode) {
      while(this.running) {
         if (mode.equals("ÐŸÐ¾ÐºÑƒÐ¿Ð°ÑŽÑ‰Ð¸Ð¹")) {
            this.startServer();
         } else if (mode.equals("ÐŸÑ€Ð¾Ð²ÐµÑ€ÑÑŽÑ‰Ð¸Ð¹")) {
            long currentTime = System.currentTimeMillis();
            if ((this.clientSocket == null || this.clientSocket.isClosed()) && currentTime - this.lastReconnectAttempt >= 2000L) {
               this.startClient();
               this.lastReconnectAttempt = currentTime;
            }
         }

         try {
            Thread.sleep(500L);
         } catch (InterruptedException var4) {
         }
      }

   }

   private void startServer() {
      if (this.serverSocket == null || this.serverSocket.isClosed()) {
         try {
            this.serverSocket = new ServerSocket(20001);
            ChatMessage.brandmessage("Ð¡ÐµÑ€Ð²ÐµÑ€ Ð·Ð°Ð¿ÑƒÑ‰ÐµÐ½ Ð½Ð° Ð¿Ð¾Ñ€Ñ‚Ñƒ 20001");
            this.executorService.execute(this::listenerThread);
         } catch (IOException var2) {
            ChatMessage.brandmessage("ÐžÑˆÐ¸Ð±ÐºÐ° Ð·Ð°Ð¿ÑƒÑÐºÐ° ÑÐµÑ€Ð²ÐµÑ€Ð°");
         }
      }

   }

   private void startClient() {
      try {
         this.clientSocket = new Socket("localhost", 20001);
         this.clientSocket.setTcpNoDelay(true);
         this.clientSocket.setSoTimeout(0);
         this.clientSocket.setKeepAlive(true);
         this.clientOut = new PrintWriter(this.clientSocket.getOutputStream(), true);
         this.clientIn = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
         this.clientOut.println("connect");
         this.executorService.execute(this::clientReaderThread);
         ChatMessage.brandmessage("ÐŸÐ¾Ð´ÐºÐ»ÑŽÑ‡ÐµÐ½Ð¾ Ðº Ð¿Ð¾ÐºÑƒÐ¿Ð°ÑŽÑ‰ÐµÐ¼Ñƒ Ð°ÐºÐºÐ°ÑƒÐ½Ñ‚Ñƒ");
      } catch (IOException var2) {
         this.clientSocket = null;
         this.clientOut = null;
         this.clientIn = null;
      }

   }

   private void listenerThread() {
      try {
         while(this.running && this.serverSocket != null && !this.serverSocket.isClosed()) {
            Socket conn = this.serverSocket.accept();
            conn.setTcpNoDelay(true);
            conn.setKeepAlive(true);
            conn.setSoTimeout(0);
            this.connections.add(conn);
            PrintWriter out = new PrintWriter(conn.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            this.outs.put(conn, out);
            this.ins.put(conn, in);
            this.clientInAuction.put(conn, false);
            ChatMessage.brandmessage("ÐŸÐ¾Ð´ÐºÐ»ÑŽÑ‡ÐµÐ½ Ð°ÐºÐºÐ°ÑƒÐ½Ñ‚ Ñ Ð¿Ñ€Ð¾Ð²ÐµÑ€ÑÑŽÑ‰Ð¸Ð¼");
            this.executorService.execute(() -> this.readerThread(conn));
         }
      } catch (IOException var4) {
      }

   }

   private void readerThread(Socket conn) {
      try {
         BufferedReader in = (BufferedReader)this.ins.get(conn);

         String line;
         while((line = in.readLine()) != null) {
            if (line.startsWith("buy:")) {
               this.handleBuyMessage(line);
            } else if (line.equals("enter_auction")) {
               this.clientInAuction.put(conn, true);
            } else if (line.equals("leave_auction")) {
               this.clientInAuction.put(conn, false);
            } else if (line.equals("ping")) {
               PrintWriter out = (PrintWriter)this.outs.get(conn);
               if (out != null) {
                  out.println("pong");
               }
            }
         }
      } catch (IOException var8) {
      } finally {
         this.removeConnection(conn);
      }

   }

   private void handleBuyMessage(String line) {
      try {
         String[] parts = line.substring(4).split("\\|");
         if (parts.length == 2) {
            String itemName = parts[0];
            int price = Integer.parseInt(parts[1]);
            BuyRequest request = new BuyRequest(itemName, price);
            this.priorityQueue.add(request);
         }
      } catch (NumberFormatException var6) {
      }

   }

   private void clientReaderThread() {
      try {
         String line;
         try {
            while((line = this.clientIn.readLine()) != null) {
               if (line.equals("update_now")) {
                  ClientUpdateHandler.handleUpdate();
               } else if (line.startsWith("switch_server:")) {
                  String cmd = line.substring(14);
                  CommandSender.handleServerSwitch(cmd);
               } else if (line.equals("open_auction")) {
                  CommandSender.openAuction();
               } else if (line.equals("pong")) {
               }
            }
         } catch (IOException var11) {
         }
      } finally {
         this.stopClient();
         if (this.running && this.isClientMode) {
            try {
               Thread.sleep(2000L);
            } catch (InterruptedException var10) {
            }
         }

      }

   }

   private void removeConnection(Socket conn) {
      this.connections.remove(conn);
      this.outs.remove(conn);
      this.ins.remove(conn);
      this.clientInAuction.remove(conn);

      try {
         conn.close();
      } catch (IOException var3) {
      }

   }

   private void stopAll() {
      this.queue.clear();
      this.priorityQueue.clear();
      if (this.serverSocket != null) {
         try {
            this.serverSocket.close();
         } catch (IOException var3) {
         }

         this.serverSocket = null;
      }

      for(Socket conn : new ArrayList(this.connections)) {
         this.removeConnection(conn);
      }

      this.stopClient();
   }

   private void stopClient() {
      if (this.clientSocket != null) {
         try {
            this.clientSocket.close();
         } catch (IOException var2) {
         }

         this.clientSocket = null;
      }

      this.clientOut = null;
      this.clientIn = null;
   }

   public void sendToAllClients(String message) {
      List<Socket> deadConnections = new ArrayList();

      for(Socket conn : new ArrayList(this.connections)) {
         PrintWriter out = (PrintWriter)this.outs.get(conn);
         if (out != null) {
            try {
               out.println(message);
               if (out.checkError()) {
                  deadConnections.add(conn);
               }
            } catch (Exception var7) {
               deadConnections.add(conn);
            }
         }
      }

      for(Socket conn : deadConnections) {
         this.removeConnection(conn);
      }

   }

   public void sendBuy(String itemName, int price) {
      if (this.clientOut != null) {
         try {
            this.clientOut.println("buy:" + itemName + "|" + price);
            if (this.clientOut.checkError()) {
               this.stopClient();
            }
         } catch (Exception var4) {
         }
      }

   }

   public void notifyAuctionEnter() {
      if (this.clientOut != null) {
         try {
            this.clientOut.println("enter_auction");
         } catch (Exception var2) {
         }
      }

   }

   public void notifyAuctionLeave() {
      if (this.clientOut != null) {
         try {
            this.clientOut.println("leave_auction");
         } catch (Exception var2) {
         }
      }

   }

   public void sendUpdateToClients() {
      this.sendToAllClients("update_now");
   }

   public void requestAuctionOpen() {
      this.sendToAllClients("open_auction");
   }

   public long getClientInAuctionCount() {
      return this.clientInAuction.values().stream().filter(Boolean::booleanValue).count();
   }

   public boolean hasConnectedClients() {
      return !this.connections.isEmpty();
   }

   public boolean isConnectedToServer() {
      return this.clientSocket != null && !this.clientSocket.isClosed() && this.clientOut != null;
   }

   public BuyRequest pollRequest() {
      BuyRequest request = (BuyRequest)this.priorityQueue.poll();
      if (request == null) {
         request = (BuyRequest)this.queue.poll();
      }

      return request;
   }

   public int getQueueSize() {
      return this.priorityQueue.size() + this.queue.size();
   }

   public boolean isQueuesEmpty() {
      return this.priorityQueue.isEmpty() && this.queue.isEmpty();
   }

   public void clearQueues() {
      this.queue.clear();
      this.priorityQueue.clear();
   }
}

