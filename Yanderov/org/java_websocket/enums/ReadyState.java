package org.java_websocket.enums;

public enum ReadyState {
   NOT_YET_CONNECTED,
   OPEN,
   CLOSING,
   CLOSED;

   // $FF: synthetic method
   private static ReadyState[] $values() {
      return new ReadyState[]{NOT_YET_CONNECTED, OPEN, CLOSING, CLOSED};
   }
}
