package dev.client.yanderov.utils.connection.cloud;

import dev.client.yanderov.utils.client.logs.Logger;
import java.net.URI;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

public class CloudConfigWebSocketClient extends WebSocketClient {
   private String lastResponse;
   private CountDownLatch responseLatch;

   public CloudConfigWebSocketClient(URI serverUri) {
      super(serverUri);
   }

   public void onOpen(ServerHandshake handshakedata) {
      Logger.info("WebSocket connection opened");
   }

   public void onMessage(String message) {
      this.lastResponse = message;
      if (this.responseLatch != null) {
         this.responseLatch.countDown();
      }

   }

   public void onClose(int code, String reason, boolean remote) {
      Logger.info("WebSocket connection closed: " + reason);
   }

   public void onError(Exception ex) {
      Logger.error("WebSocket error: " + ex.getMessage());
   }

   public boolean isConnected() {
      return this.isOpen();
   }

   public String sendAndWaitForResponse(String message) {
      if (!this.isConnected()) {
         Logger.error("Cannot send message: WebSocket not connected");
         return null;
      } else {
         try {
            this.responseLatch = new CountDownLatch(1);
            this.lastResponse = null;
            this.send(message);
            this.responseLatch.await(5L, TimeUnit.SECONDS);
         } catch (InterruptedException e) {
            Logger.error("Interrupted while waiting for WebSocket response: " + e.getMessage());
            return null;
         } catch (Exception e) {
            Logger.error("Error sending WebSocket message: " + e.getMessage());
            return null;
         }

         return this.lastResponse;
      }
   }
}

