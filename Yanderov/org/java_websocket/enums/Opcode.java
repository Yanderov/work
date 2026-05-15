package org.java_websocket.enums;

public enum Opcode {
   CONTINUOUS,
   TEXT,
   BINARY,
   PING,
   PONG,
   CLOSING;

   // $FF: synthetic method
   private static Opcode[] $values() {
      return new Opcode[]{CONTINUOUS, TEXT, BINARY, PING, PONG, CLOSING};
   }
}
